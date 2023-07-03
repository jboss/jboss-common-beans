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
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jboss.common.beans.property.BeanUtils;
import org.jboss.common.beans.property.BooleanArrayEditor;
import org.jboss.common.beans.property.BooleanEditor;
import org.jboss.common.beans.property.ByteArrayEditor;
import org.jboss.common.beans.property.ByteEditor;
import org.jboss.common.beans.property.CharacterArrayEditor;
import org.jboss.common.beans.property.CharacterEditor;
import org.jboss.common.beans.property.ClassArrayEditor;
import org.jboss.common.beans.property.DoubleArrayEditor;
import org.jboss.common.beans.property.DoubleEditor;
import org.jboss.common.beans.property.FloatArrayEditor;
import org.jboss.common.beans.property.FloatEditor;
import org.jboss.common.beans.property.GenericArrayPropertyEditor;
import org.jboss.common.beans.property.InetAddressArrayEditor;
import org.jboss.common.beans.property.IntegerArrayEditor;
import org.jboss.common.beans.property.IntegerEditor;
import org.jboss.common.beans.property.LongArrayEditor;
import org.jboss.common.beans.property.LongEditor;
import org.jboss.common.beans.property.ShortArrayEditor;
import org.jboss.common.beans.property.ShortEditor;
import org.jboss.common.beans.property.StringArrayEditor;

/**
 * @author baranowb
 *
 */
public class DefaultPropertyEditorFinder extends PropertyEditorFinder {
    private static final Logger logger = Logger.getLogger(DefaultPropertyEditorFinder.class.getName());
    protected Map<Class<?>, Class<? extends PropertyEditor>> register = new HashMap<Class<?>, Class<? extends PropertyEditor>>();

    public DefaultPropertyEditorFinder() {
        this.packages = new String[] { "org.jboss.common.beans.property" };
        // register primitive types
        register(Byte.TYPE, ByteEditor.class);
        register(Short.TYPE, ShortEditor.class);
        register(Integer.TYPE, IntegerEditor.class);
        register(Long.TYPE, LongEditor.class);
        register(Boolean.TYPE, BooleanEditor.class);
        register(Float.TYPE, FloatEditor.class);
        register(Double.TYPE, DoubleEditor.class);
        register(Character.TYPE, CharacterEditor.class);
        // register wrapper
        register(Byte.class, ByteEditor.class);
        register(Short.class, ShortEditor.class);
        register(Integer.class, IntegerEditor.class);
        register(Long.class, LongEditor.class);
        register(Boolean.class, BooleanEditor.class);
        register(Float.class, FloatEditor.class);
        register(Double.class, DoubleEditor.class);
        register(Character.class, CharacterEditor.class);
        // register arrays
        Class<?> strArrayType = String[].class;
        register(strArrayType, StringArrayEditor.class);
        Class<?> clsArrayType = Class[].class;
        register(clsArrayType, ClassArrayEditor.class);
        Class<?> intArrayType = int[].class;
        register(intArrayType, IntegerArrayEditor.class);
        Class<?> byteArrayType = byte[].class;
        register(byteArrayType, ByteArrayEditor.class);
        Class<?> booleanArrayType = boolean[].class;
        register(booleanArrayType, BooleanArrayEditor.class);
        Class<?> charArrayType = char[].class;
        register(charArrayType, CharacterArrayEditor.class);
        Class<?> doubleArrayType = double[].class;
        register(doubleArrayType, DoubleArrayEditor.class);
        Class<?> floatArrayType = float[].class;
        register(floatArrayType, FloatArrayEditor.class);
        Class<?> longArrayType = long[].class;
        register(longArrayType, LongArrayEditor.class);
        Class<?> shortArrayType = short[].class;
        register(shortArrayType, ShortArrayEditor.class);
        Class<?> inetAddressArrayType = InetAddress[].class;
        register(inetAddressArrayType, InetAddressArrayEditor.class);
    }

    private String[] packages;

    /**
     * Sets packages in which editors should be looked up.
     *
     * @param packages
     */
    public void setEditorSearchPackages(String[] packages) {
        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            sm.checkPropertiesAccess();
        }
        this.packages = packages.clone();
    }

    /**
     * Get packages in which editors should be looked up.
     *
     * @return
     */
    public String[] getEditorSearchPackages() {
        return this.packages.clone();
    }

    public void register(Class<?> type, Class<? extends PropertyEditor> editor) {

        if (type == null) {
            throw new IllegalArgumentException("Object type must not be null.");
        }
        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            sm.checkPropertiesAccess();
        }
        this.register.put(type, editor);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.jboss.common.beans.property.finder.PropertyEditorFinder#find(java.lang.Class)
     */
    @SuppressWarnings("unchecked")
    @Override
    public PropertyEditor find(Class<?> type) {
        if(type == null){
            return null;
        }
        Class<? extends PropertyEditor> editorClass = this.register.get(type);
        if (editorClass != null) {
            try {
                return editorClass.newInstance();
            } catch (Exception e) {
                 //e.printStackTrace();
            }
        }

        final boolean isArray = type.isArray();
        String editorName = BeanUtils.stripPackage(type);
        if (isArray) {
            editorName += EDITOR_ARRAY;
        } else {
            editorName += EDITOR;
        }
        String searchName = null;
        try {
            searchName = BeanUtils.stripClass(type) + "." + editorName;
            editorClass = (Class<? extends PropertyEditor>) BeanUtils.findClass(searchName);
            return editorClass.newInstance();
        } catch (Exception e) {
            //catching, to be compliant with JDK PEM
            if(logger.isLoggable(Level.FINEST)){
                logger.log(Level.FINEST,"Failed to instantiate property editor class  '"+searchName+"'.",e);
            }
        }

        for (String pkg : this.packages) {
            try {
                searchName = pkg + "." + editorName;
                editorClass = (Class<? extends PropertyEditor>) BeanUtils.findClass(searchName);
                return editorClass.newInstance();
            } catch (Exception e) {
                //catching, to be compliant with JDK PEM
                if(logger.isLoggable(Level.FINEST)){
                    logger.log(Level.FINEST,"Failed to instantiate property editor class  '"+searchName+"'.",e);
                }
            }
        }

        if (isArray) {
            Class<?> cellType = type.getComponentType();
            if (find(cellType) != null) {
                return new GenericArrayPropertyEditor(type);
            }
            return null;
        }  else if(type.isPrimitive()){
            return find(BeanUtils.getWrapperTypeFor(type));
        }

        return null;
    }


}
