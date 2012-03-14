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
public abstract class PropertyEditorSupport<T> extends java.beans.PropertyEditorSupport implements PropertyEditor<T> {
    private T value;

    public void addPropertyChangeListener(final PropertyChangeListener<T> listener) {
        super.addPropertyChangeListener(listener);
    }

    @Override
    public String getAsText() {
        return value.toString();
    }

    @Override
    public T getValue() {
        return value;
    }

    public void removePropertyChangeListener(final PropertyChangeListener<T> listener) {
        super.removePropertyChangeListener(listener);
    }

    @Override
    public abstract void setAsText(final String text) throws IllegalArgumentException;

    protected void setValue(final Class<T> type, final Object value) {
        if (!type.isAssignableFrom(value.getClass()))
            throw new IllegalArgumentException("Unsupported value: " + value);
        this.value = type.cast(value);
    }

    @Override
    public abstract void setValue(final Object value);
}
