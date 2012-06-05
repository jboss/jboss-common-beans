/*
 * JBoss, Home of Professional Open Source
 * Copyright 2005, JBoss Inc., and individual contributors as indicated
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
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
 * A property editor for {@link java.lang.Class}.
 *
 * @author baranowb
 */
public class ClassEditor extends PropertyEditorSupport<Class>{

    public ClassEditor() {
        super(Class.class);
    }

    @Override
    public void setAsText(final String text) {
        if (BeanUtils.isNull(text)) {
            setValue(null);
        } else {
            try {
                ClassLoader loader = Thread.currentThread().getContextClassLoader();
                Class<?> type = loader.loadClass(text);

                setValue(type);
            } catch (Exception e) {
                e.printStackTrace();
                throw new IllegalArgumentException("Failed to parse to Class!", e);
            }

        }
    }

    @Override
    public String getAsText() {
        Class c = (Class) getValue();
        if (c == null) {
            return null;
        }

        return c.getName();

    }
}
