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

/**
 * @author <a href="mailto:cdewolf@redhat.com">Carlo de Wolf</a>
 */
public interface PropertyEditor<T> {
    /**
     * Set (or change) the object that is to be edited.  Primitive types such
     * as "int" must be wrapped as the corresponding object type such as
     * "java.lang.Integer".
     *
     * @param value The new target object to be edited.  Note that this
     *     object should not be modified by the PropertyEditor, rather
     *     the PropertyEditor should create a new object to hold any
     *     modified value.
     */
    void setValue(Object value);

    /**
     * Gets the property value.
     *
     * @return The value of the property.  Primitive types such as "int" will
     * be wrapped as the corresponding object type such as "java.lang.Integer".
     */
    T getValue();

    /**
     * Gets the property value as text.
     *
     * @return The property value as a human editable string.
     * <p>   Returns null if the value can't be expressed as an editable string.
     * <p>   If a non-null value is returned, then the PropertyEditor should
     *       be prepared to parse that string back in setAsText().
     */
    String getAsText();

    /**
     * Set the property value by parsing a given String.  May raise
     * java.lang.IllegalArgumentException if either the String is
     * badly formatted or if this kind of property can't be expressed
     * as text.
     * @param text  The string to be parsed.
     */
    void setAsText(String text) throws java.lang.IllegalArgumentException;

    /**
     * Adds a listener for the value change.
     * When the property editor changes its value
     * it should fire a {@link java.beans.PropertyChangeEvent}
     * on all registered {@link java.beans.PropertyChangeListener}s,
     * specifying the {@code null} value for the property name
     * and itself as the source.
     *
     * @param listener  the {@link java.beans.PropertyChangeListener} to add
     */
    void addPropertyChangeListener(PropertyChangeListener<T> listener);

    /**
     * Removes a listener for the value change.
     *
     * @param listener  the {@link PropertyChangeListener} to remove
     */
    void removePropertyChangeListener(PropertyChangeListener<T> listener);
}
