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
 * A property editor for boolean[].
 *
 * @author baranowb
 */
public class BooleanArrayEditor extends ArrayPropertyEditorSupport<boolean[]> {

    public BooleanArrayEditor() {
        super(boolean[].class);
    }

    private static final String[] BOOLEAN_TAGS = { "true", "false" };

    /**
     * Build a boolean[] from comma or eol seperated elements
     *
     */
    @Override
    public void setAsText(final String text) {
        if (PropertyEditors.isNull(text)) {
            setValue(null);
            return;
        }
        String[] tokens = super.tokenize(text);
        boolean[] theValue = new boolean[tokens.length];

        for (int index = 0; index < tokens.length; index++) {
            theValue[index] = Boolean.parseBoolean(tokens[index]);
        }
        setValue(theValue);
    }

    /**
     * @return a comma seperated string of the array elements
     */
    @Override
    public String getAsText() {
        boolean[] theValue = (boolean[]) getValue();
        return super.encode(theValue);
    }

    @Override
    public String[] getTags() {
        return BOOLEAN_TAGS;
    }

}