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
 * Simple abstract class to provide base for PropertyEditorFinder service.
 *
 * @author baranowb
 *
 */
public abstract class PropertyEditorFinder {

    protected static Logger logger = Logger.getLogger(PropertyEditorFinder.class.getName());

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

    private static void init() {
        final ServiceLoader<PropertyEditorFinder> service = ServiceLoader.load(PropertyEditorFinder.class);
        final Iterator<PropertyEditorFinder> it = service.iterator();
        if (it.hasNext()) {
            _INSTANCE = it.next();
        }
    }

    /**
     * Returns instance of property finder. Instance is loaded as {@java.util.ServiceLoader} service.
     * If no service is found, this method, this method will return <b>null</b>.
     *
     * @return Instance of PropertyEditorFinder.
     */
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
