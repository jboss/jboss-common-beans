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
 * A property editor for {@link java.lang.String}.
 *
 * It is really a no-op but it is hoped to provide slightly better performance, by avoiding the continuous lookup/failure of a
 * property editor for plain Strings within the org.jboss.util.propertyeditor package, before falling back to the jdk provided
 * String editor.
 *
 * @author <a href="dimitris@jboss.org">Dimitris Andreadis</a>
 */
public class StringEditor extends PropertyEditorSupport<String> {

    public StringEditor() {
        super(String.class);
    }

    /**
     * Keep the provided String as is.
     */
    public void setAsText(String text) {
        if (BeanUtils.isNull(text)){
            setValue(null);
            return;
        }
        setValue(text);
    }
}
