/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2011, Red Hat, Inc., and individual contributors
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
 * @author baranowb
 *
 */
public final class BeanUtils {
    /** The null string */
    private static final String NULL = "null";

    public static Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            if (loader == null) {
                loader = ClassLoader.getSystemClassLoader();
            }
            if (loader != null) {
                return Class.forName(name, false, loader);
            }

        } catch (Exception e) {
        }
        return Class.forName(name);
    }

    public static Class<?> findClass(String name, ClassLoader loader) throws ClassNotFoundException {
        if (loader != null) {
            try {
                return Class.forName(name, false, loader);
            } catch (Exception e) {
            }
        }
        return findClass(name);
    }

    /**
     * Whether a string is interpreted as the null value, including the empty string.
     *
     * @param value the value
     * @return true when the string has the value null
     */
    public static boolean isNull(final String value) {
        return isNull(value, true, true);
    }

    /**
     * Whether a string is interpreted as the null value
     *
     * @param value the value
     * @param trim whether to trim the string
     * @param empty whether to include the empty string as null
     * @return true when the string has the value null
     */
    public static boolean isNull(final String value, final boolean trim, final boolean empty) {

        // No value?
        if (value == null)
            return true;
        // Trim the text when requested
        String trimmed = trim ? value.trim() : value;
        // Is the empty string null?
        if (empty && trimmed.length() == 0)
            return true;
        // Just check it.
        return NULL.equalsIgnoreCase(trimmed);
    }
}
