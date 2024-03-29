/*
 * Copyright Red Hat
 * SPDX-License-Identifier: Apache-2.0
 */

package org.jboss.common.beans.property;

import java.beans.PropertyEditorManager;
import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.ServiceLoader;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jboss.common.beans.property.finder.PropertyEditorFinder;
import org.jboss.common.beans.property.token.ArrayTokenizer;
import org.jboss.common.beans.property.token.StrictTokenizer;

/**
 * Generic array support editor. Depending on type of array it performs all required operations to transform from/to text. It
 * requires array cell property editor to be present - <b>ProperyEditorManager.findEditor(arrayClass.getComponentType()) !=
 * null</b> <br>
 * This class is not registered as property editor in {@link PropertyEditorManager}. It is created at runtime in following
 * condition:
 * <ul>
 * <li>Any of {@link PropertyEditorFinder} fetch methods is passed array type for which there is no editor</li>
 * <li> {@link PropertyEditorFinder} or {@link PropertyEditorManager} can access editor for {@link Class#getComponentType}.</li>
 * </ul>
 *
 * @author baranowb
 *
 */
public class GenericArrayPropertyEditor<T> extends PropertyEditorSupport<T> {

    //note this will hide real class, but its better than having it init on each PE instance.
    private static final Logger logger = Logger.getLogger(GenericArrayPropertyEditor.class.getName());
    private final Class<?> cellType;

    /**
     * @param initType
     */
    public GenericArrayPropertyEditor(Class<T> initType) {
        super(initType);
        // quick checks
        if (!initType.isArray()) {
            throw new IllegalArgumentException("Type is not array! " + initType);
        }

        this.cellType = initType.getComponentType();
        // generic interface.

        java.beans.PropertyEditor cellPropertyEditor = PropertyEditorFinder.getInstance().find(this.cellType);
        // jic
        if (cellPropertyEditor == null) {
            throw new IllegalArgumentException("No editor found for '" + this.cellType + "'");
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see org.jboss.common.beans.property.PropertyEditorSupport#setAsText(java.lang.String)
     */
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (BeanUtils.isNull(text)) {
            this.setValue(null);
            return;
        }
        // generic interface.
        java.beans.PropertyEditor cellPropertyEditor = PropertyEditorFinder.getInstance().find(this.cellType);

        String[] cellStringValues = tokenize(text);
        Object reflectiveArray = Array.newInstance(this.cellType, cellStringValues.length);
        for (int index = 0; index < cellStringValues.length; index++) {
            cellPropertyEditor.setAsText(cellStringValues[index]);
            Object cellValue = cellPropertyEditor.getValue();
            Array.set(reflectiveArray, index, cellValue);
        }
        this.setValue(reflectiveArray);

    }

    /*
     * (non-Javadoc)
     *
     * @see org.jboss.common.beans.property.PropertyEditorSupport#getAsText()
     */
    @Override
    public String getAsText() {
        Object reflectiveArray = getValue();
        if (reflectiveArray == null) {
            return null;
        }
        // generic interface.
        java.beans.PropertyEditor cellPropertyEditor = PropertyEditorFinder.getInstance().find(this.cellType);

        int length = Array.getLength(reflectiveArray);
        String[] cellStringValues = new String[length];

        for (int index = 0; index < length; index++) {
            Object cellValue = Array.get(reflectiveArray, index);
            cellPropertyEditor.setValue(cellValue);
            cellStringValues[index]=cellPropertyEditor.getAsText();
        }

        return encode(cellStringValues);
    }

    protected String[] tokenize(String text) {
        // makes us iterate twice...
        ArrayTokenizer arrayTokenizer = getTokenizer();
        return arrayTokenizer.tokenize(text);
    }

    protected String encode(String[] v) {
        StringBuffer text = new StringBuffer();
        for (int index = 0; index < v.length; index++) {
            if (index > 0)
                text.append(',');
            text.append(v[index]);
        }
        return text.toString();
    }
    protected Class<?> getCellType() {
        return this.cellType;
    }

   protected ArrayTokenizer getTokenizer(){
       try{
           ServiceLoader<ArrayTokenizer> service = ServiceLoader.load(ArrayTokenizer.class);
           Iterator<ArrayTokenizer> it = service.iterator();
           if(it.hasNext()){
               return it.next();
           }
       }catch(Exception e){
           if(logger.isLoggable(Level.FINEST)){
               logger.log(Level.FINEST,"Failed to load tokenizer via ServiceLoader, falling back to '"+StrictTokenizer.class.getName()+"'.",e);
           }
       }
       return new StrictTokenizer();
   }
}
