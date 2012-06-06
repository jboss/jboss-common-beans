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

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.beans.PropertyEditor;
import java.beans.PropertyEditorManager;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jboss.common.beans.property.finder.PropertyEditorFinder;

/**
 * @author baranowb
 *
 */
public final class BeanUtils {
    private static final Logger logger = Logger.getLogger(BeanUtils.class.getName());
    /** The null string */
    private static final String NULL = "null";

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

    public static String stripPackage(String fqn) {
        return fqn.substring(fqn.lastIndexOf('.') + 1, fqn.length());
    }

    public static String stripPackage(Class<?> clazz) {
        final String fqn = clazz.getName();
        return fqn.substring(fqn.lastIndexOf('.') + 1, fqn.length());
    }

    public static Class<?> getPrimitiveTypeForName(final String name) {
        return (Class<?>) PRIMITIVE_NAME_TYPE_MAP.get(name);
    }

    public static Class<?> getWrapperTypeFor(final Class<?> primitive) {
        return (Class<?>) PRIMITIVES_TO_WRAPPERS.get(primitive);
    }

    public static Class<?> findClass(String name) throws ClassNotFoundException {
        if (PRIMITIVE_NAME_TYPE_MAP.containsKey(name)) {
            return PRIMITIVE_NAME_TYPE_MAP.get(name);
        }
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

    /**
     * Convert a string value into the true value for typeName using the PropertyEditor associated with typeName.
     *
     * @param text the string represention of the value. This is passed to the PropertyEditor.setAsText method.
     * @param typeName the fully qualified class name of the true value type
     * @return the PropertyEditor.getValue() result
     * @exception ClassNotFoundException thrown if the typeName class cannot be found
     * @exception IntrospectionException thrown if a PropertyEditor for typeName cannot be found
     */
    public static Object convertValue(String text, String typeName) throws ClassNotFoundException, IntrospectionException {
        // see if it is a primitive type first
        Class<?> typeClass = getPrimitiveTypeForName(typeName);
        if (typeClass == null) {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            typeClass = loader.loadClass(typeName);
        }
        PropertyEditor editor = PropertyEditorFinder.getInstance().find(typeClass);
        if (editor == null) {
            throw new IntrospectionException("No property editor for type=" + typeClass);
        }

        editor.setAsText(text);
        return editor.getValue();
    }

    /**
     * This method takes the properties found in the given beanProps to the bean using the property editor registered for the
     * property. Any property in beanProps that does not have an associated java bean property will result in an
     * IntrospectionException. The string property values are converted to the true java bean property type using the java bean
     * PropertyEditor framework. If a property in beanProps does not have a PropertyEditor registered it will be ignored.
     *
     * @param bean - the java bean instance to apply the properties to
     * @param beanProps - map of java bean property name to property value.
     * @throws IntrospectionException thrown on introspection of bean and if a property in beanProps does not map to a property
     *         of bean.
     */
    public static void mapJavaBeanProperties(Object bean, Properties beanProps) throws IntrospectionException {
        mapJavaBeanProperties(bean, beanProps, true);
    }

    /**
     * This method takes the properties found in the given beanProps to the bean using the property editor registered for the
     * property. Any property in beanProps that does not have an associated java bean property will result in an
     * IntrospectionException. The string property values are converted to the true java bean property type using the java bean
     * PropertyEditor framework. If a property in beanProps does not have a PropertyEditor registered it will be ignored.
     *
     * @param bean - the java bean instance to apply the properties to
     * @param beanProps - map of java bean property name to property value.
     * @param isStrict - indicates if should throw exception if bean property can not be matched. True for yes, false for no.
     * @throws IntrospectionException thrown on introspection of bean and if a property in beanProps does not map to a property
     *         of bean.
     */
    public static void mapJavaBeanProperties(Object bean, Properties beanProps, boolean isStrict) throws IntrospectionException {

        HashMap<String, PropertyDescriptor> propertyMap = new HashMap<String, PropertyDescriptor>();
        BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
        PropertyDescriptor[] props = beanInfo.getPropertyDescriptors();
        for (int p = 0; p < props.length; p++) {
            String fieldName = props[p].getName();
            propertyMap.put(fieldName, props[p]);
        }

        boolean trace = logger.isLoggable(Level.FINEST);
        Iterator keys = beanProps.keySet().iterator();
        if (trace)
            logger.finest("Mapping properties for bean: " + bean);
        while (keys.hasNext()) {
            String name = (String) keys.next();
            String text = beanProps.getProperty(name);
            PropertyDescriptor pd = propertyMap.get(name);
            if (pd == null) {
                /*
                 * Try the property name with the first char uppercased to handle a property name like dLQMaxResent whose
                 * expected introspected property name would be DLQMaxResent since the JavaBean Introspector would view
                 * setDLQMaxResent as the setter for a DLQMaxResent property whose Introspector.decapitalize() method would also
                 * return "DLQMaxResent".
                 */
                if (name.length() > 1) {
                    char first = name.charAt(0);
                    String exName = Character.toUpperCase(first) + name.substring(1);
                    pd = propertyMap.get(exName);

                    // Be lenient and check the other way around, e.g. ServerName -> serverName
                    if (pd == null) {
                        exName = Character.toLowerCase(first) + name.substring(1);
                        pd = propertyMap.get(exName);
                    }
                }

                if (pd == null) {
                    if (isStrict) {
                        String msg = "No property found for: " + name + " on JavaBean: " + bean;
                        throw new IntrospectionException(msg);
                    } else {
                        // since is not strict, ignore that this property was not found
                        continue;
                    }
                }
            }
            Method setter = pd.getWriteMethod();
            if (trace)
                logger.finest("Property editor found for: " + name + ", editor: " + pd + ", setter: " + setter);
            if (setter != null) {
                Class<?> ptype = pd.getPropertyType();
                PropertyEditor editor = PropertyEditorManager.findEditor(ptype);
                if (editor == null) {
                    if (trace)
                        logger.finest("Failed to find property editor for: " + name);
                }
                try {
                    editor.setAsText(text);
                    Object[] args = { editor.getValue() };
                    setter.invoke(bean, args);
                } catch (Exception e) {
                    if (trace)
                        logger.log(Level.FINEST,"Failed to write property", e);
                }
            }
        }
    }
}
