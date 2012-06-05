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

package org.jboss.common.beans.property.finder;

import java.beans.PropertyEditor;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.logging.Logger;

/**
 * Simple abstract class to provide base for PropertyEditor Finders.
 *
 * @author baranowb
 *
 */
public abstract class PropertyEditorFinder {

    protected static Logger logger = Logger.getLogger(PropertyEditorFinder.class.getName());

    protected static final String _EDITOR = "Editor";
    protected static final String _EDITOR_ARRAY = "Array" + _EDITOR;

    /** Primitive type name -> class map. */
    private static final Map<String, Class<?>> PRIMITIVE_NAME_TYPE_MAP;

    /** Setup the primitives map. */
    static {
        Map<String, Class<?>> tmp = new HashMap<String, Class<?>>();
        tmp.put("boolean", Boolean.TYPE);
        tmp.put("byte", Byte.TYPE);
        tmp.put("char", Character.TYPE);
        tmp.put("short", Short.TYPE);
        tmp.put("int", Integer.TYPE);
        tmp.put("long", Long.TYPE);
        tmp.put("float", Float.TYPE);
        tmp.put("double", Double.TYPE);
        PRIMITIVE_NAME_TYPE_MAP = Collections.unmodifiableMap(tmp);
    }
    private static final Map<Class<?>, Class<?>> PRIMITIVES_TO_WRAPPERS;
    static {
        Map<Class<?>, Class<?>> tmp2 = new HashMap<Class<?>, Class<?>>();
        tmp2.put(boolean.class, Boolean.class);
        tmp2.put(byte.class, Byte.class);
        tmp2.put(char.class, Character.class);
        tmp2.put(double.class, Double.class);
        tmp2.put(float.class, Float.class);
        tmp2.put(int.class, Integer.class);
        tmp2.put(long.class, Long.class);
        tmp2.put(short.class, Short.class);
        tmp2.put(void.class, Void.class);
        PRIMITIVES_TO_WRAPPERS = Collections.unmodifiableMap(tmp2);
    }

    protected static String stripPackage(String fqn) {
        return fqn.substring(fqn.lastIndexOf('.') + 1, fqn.length());
    }

    protected static String stripPackage(Class<?> clazz) {
        final String fqn = clazz.getName();
        return fqn.substring(fqn.lastIndexOf('.') + 1, fqn.length());
    }

    protected static Class<?> getPrimitiveTypeForName(final String name) {
        return (Class<?>) PRIMITIVE_NAME_TYPE_MAP.get(name);
    }

    protected static Class<?> getWrapperTypeFor(final Class<?> primitive) {
        return (Class<?>) PRIMITIVES_TO_WRAPPERS.get(primitive);
    }

    public abstract PropertyEditor find(Class<?> type);

    private static final Object _LOCK = new Object();

    private static volatile PropertyEditorFinder _INSTANCE;

    private static void init() {
        final ServiceLoader<PropertyEditorFinder> service =  ServiceLoader.load(PropertyEditorFinder.class);
        final Iterator<PropertyEditorFinder> it = service.iterator();
        if (it.hasNext()) {
            _INSTANCE = it.next();
        }
    }

    public static PropertyEditorFinder getInstance() {
        PropertyEditorFinder result = _INSTANCE;
        if (result == null) {
            synchronized (_LOCK) {
                result = _INSTANCE;
                if (result == null) {
                    init();
                    result = _INSTANCE;
                }
            }
        }
        return result;
    }
}
