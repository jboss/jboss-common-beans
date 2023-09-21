/*
 * Copyright Red Hat
 * SPDX-License-Identifier: Apache-2.0
 */
package org.jboss.common.beans.property;

/**
 * @author <a href="mailto:cdewolf@redhat.com">Carlo de Wolf</a>
 */
public class PropertyChangeEvent<T> extends java.beans.PropertyChangeEvent {

    /**
     *
     */
    private static final long serialVersionUID = -514527097496679097L;

    /**
     * Constructs a new <code>PropertyChangeEvent</code>.
     *
     * @param source       The bean that fired the event.
     * @param propertyName The programmatic name of the property
     *                     that was changed.
     * @param oldValue     The old value of the property.
     * @param newValue     The new value of the property.
     */
    public PropertyChangeEvent(Object source, String propertyName, T oldValue, T newValue) {
        super(source, propertyName, oldValue, newValue);
    }

    @Override
    public T getNewValue() {
        return (T) super.getNewValue();
    }

    @Override
    public T getOldValue() {
        return (T) super.getOldValue();
    }
}
