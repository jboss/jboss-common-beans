/*
* Copyright [2023] [Red Hat, Inc.]
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package org.jboss.common.beans.property.finder;

import java.beans.PropertyEditor;
import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * Simple abstract class to provide base for PropertyEditorFinder service.
 *
 * @author baranowb
 *
 */
public abstract class PropertyEditorFinder {

    protected static final String EDITOR = "Editor";
    protected static final String EDITOR_ARRAY = "Array" + EDITOR;

    /**
     * Returns PropertyEditor which capable of converting String into instances of {@code type} parameter.
     *
     * @param type - class object representing type of property beeing converted from String to object instance.
     * @return
     */
    public abstract PropertyEditor find(Class<?> type);

    /**
     * If supported, this method register editor class for specific type. When queried, finder will return registered editor
     * class. Check {{@link #find(Class)} method.
     *
     * @param type - class object representing type of property, ie. int[].class, InetAddress.class
     * @param editor - editor class which is capable of converting type, to and from String.
     */
    public abstract void register(Class<?> type, Class<? extends PropertyEditor> editor);

    /**
     * If supported this sets search packages. Finder will search those packages for viable editor classe.
     *
     * @param packages - set of packages, ie. {"com.sun.beans","custom.editors"}
     */
    public abstract void setEditorSearchPackages(String[] packages);

    /**
     *
     * @return - current search packages.
     */
    public abstract String[] getEditorSearchPackages();

    private static final Object _LOCK = new Object();

    private static volatile PropertyEditorFinder _INSTANCE;

    private static void init(ClassLoader classLoader) {
        final ServiceLoader<PropertyEditorFinder> service = ServiceLoader.load(PropertyEditorFinder.class, classLoader);
        final Iterator<PropertyEditorFinder> it = service.iterator();
        if (it.hasNext()) {
            _INSTANCE = it.next();
        }
    }

    /**
     * Returns instance of property finder. Instance is loaded as {@link java.util.ServiceLoader} service.
     * If no service is found, this method, this method will return <b>null</b>.
     *
     * @return Instance of PropertyEditorFinder.
     */
    public static PropertyEditorFinder getInstance(ClassLoader classLoader) {
        PropertyEditorFinder result = _INSTANCE;
        if (result == null) {
            synchronized (_LOCK) {
                result = _INSTANCE;
                if (result == null) {
                    init(classLoader);
                    result = _INSTANCE;
                }
            }
        }
        return result;
    }

    public static PropertyEditorFinder getInstance() {
        return getInstance(PropertyEditorFinder.class.getClassLoader());
    }
}
