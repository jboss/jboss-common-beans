/*
 * Copyright Red Hat
 * SPDX-License-Identifier: Apache-2.0
 */
package org.jboss.common.beans.property;


/**
 * A property editor for char[].
 *
 * TODO REVIEW: look at possibly parsing escape sequences?
 * @author baranowb
 */
public class CharacterArrayEditor extends GenericArrayPropertyEditor<char[]> {

    public CharacterArrayEditor() {
        super(char[].class);
    }

    /**
     * Build a char[] from comma or eol seperated elements
     *
     */
    @Override
    public void setAsText(final String text) {
        if (BeanUtils.isNull(text)){
            setValue(null);
            return;
        }
        String[] tokens = super.tokenize(text);
        char[] theValue = new char[tokens.length];

        for (int index = 0; index < tokens.length; index++) {
            if (tokens[index].length() != 1)
                throw new IllegalArgumentException("Too many (" + tokens[index].length() + ") characters: '" + tokens[index]
                        + "'");
            theValue[index] = tokens[index].charAt(0);
        }
        setValue(theValue);
    }
}