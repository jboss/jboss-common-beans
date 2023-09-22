/*
 * Copyright Red Hat
 * SPDX-License-Identifier: Apache-2.0
 */
package org.jboss.common.beans.property;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author <a href="mailto:cdewolf@redhat.com">Carlo de Wolf</a>
 * @author baranowb
 */
public abstract class PropertyEditorSupport<T> extends java.beans.PropertyEditorSupport implements PropertyEditor<T> {
    private T value;
    private final Class<T> type;

    // override listeners, its private in JDK class....
    //TODO: should this manage ONLY org.jboss listeners?
    private CopyOnWriteArrayList<java.beans.PropertyChangeListener> listeners = new CopyOnWriteArrayList<java.beans.PropertyChangeListener>();

    /**
     * Creates PropertyEditorSupport instance. Requires T class to enforce runtime type checks.
     */
    public PropertyEditorSupport(Class<T> type) {
        super();
        super.setSource(this);
        this.type = type;
    }

    /**
     * Creates PropertyEditorSupport instance. Requires T class to enforce runtime type checks and a source.
     */
    public PropertyEditorSupport(Class<T> type, Object source) {
        super();
        super.setSource(source);
        this.type = type;
    }

    public void addPropertyChangeListener(final PropertyChangeListener<T> listener) {
        if (!this.listeners.contains(listener)) {
            this.listeners.add(listener);
        }
    }

    @Override
    public void addPropertyChangeListener(final java.beans.PropertyChangeListener listener) {
        if (!this.listeners.contains(listener)) {
            this.listeners.add(listener);
        }
    }

    @Override
    public String getAsText() {
        return (this.value != null) ? this.value.toString() : null;
    }

    @Override
    public T getValue() {
        return value;
    }

    public void removePropertyChangeListener(final PropertyChangeListener<T> listener) {
        this.listeners.remove(listener);
    }

    @Override
    public void removePropertyChangeListener(final java.beans.PropertyChangeListener listener) {
        this.listeners.remove(listener);
    }

    @Override
    public void setValue(final Object value) {
        final T oldValue = this.value;
        if (value == null) {
            this.value = null;
        } else {
            if (!this.type.isAssignableFrom(value.getClass())) {
                throw new IllegalArgumentException("Unsupported value: " + value);
            }
            this.value = type.cast(value);
        }
        if (oldValue != this.value)
            firePropertyChange(oldValue, this.value);
    }

    @SuppressWarnings("unchecked")
    public void firePropertyChange(T oldValue, T newValue) {
        List<java.beans.PropertyChangeListener> targets;
        synchronized (this) {
            if (this.listeners == null) {
                return;
            }
            targets = (List<java.beans.PropertyChangeListener>) listeners.clone();
        }
        // how should we get prop name
        PropertyChangeEvent<T> evt = new PropertyChangeEvent<T>(getSource(), null, oldValue, newValue);

        for (int i = 0; i < targets.size(); i++) {
            java.beans.PropertyChangeListener target = (java.beans.PropertyChangeListener) targets.get(i);
            try {
                if (target instanceof PropertyChangeListener<?>) {
                    PropertyChangeListener<T> localTarget = (PropertyChangeListener<T>) target;
                    localTarget.propertyChange(evt);
                } else {
                    target.propertyChange(evt);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // super?
        // super.firePropertyChange();
    }

    protected Class<T> getType(){
        return this.type;
    }
    @Override
    public abstract void setAsText(final String text) throws IllegalArgumentException;

}
