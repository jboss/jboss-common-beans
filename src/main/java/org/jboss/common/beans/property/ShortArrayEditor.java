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
 * License ashort with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jboss.common.beans.property;

/**
 * A property editor for short[].
 *
 */
public class ShortArrayEditor extends ArrayPropertyEditorSupport {

    /**
     * Build a short[] from comma or eol seperated elements
     *
     */
    @Override
    public String getAsText() {
        short[] value = (short[]) super.getValue();
        return super.encode(value);
    }
    /**
     * @return a comma seperated string of the array elements
     */
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (PropertyEditors.isNull(text)) {
            setValue(null);
            return;
        }
        String[] tokens = super.tokenize(text);
        short[] value = new short[tokens.length];
        for (int index = 0; index < tokens.length; index++) {
            try {
                value[index] = Short.decode(tokens[index]).shortValue();
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Failed to parse short.", e);
            }
        }
        super.setValue(value);
    }
}
