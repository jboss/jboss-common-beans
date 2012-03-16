/*
 * JBoss, Home of Professional Open Source.
 * Copyright (c) 2012, Red Hat, Inc., and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
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
    private Class<T> type;

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
     * @param source
     */
    public PropertyEditorSupport(Object source) {
        super(source);
        // TODO Auto-generated constructor stub
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

    @Override
    public abstract void setAsText(final String text) throws IllegalArgumentException;

}
