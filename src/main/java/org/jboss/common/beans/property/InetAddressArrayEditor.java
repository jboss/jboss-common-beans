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

import java.net.InetAddress;

/**
 * A property editor for {@link java.net.InetAddress}[].
 *
 * @author baranowb
 */
public class InetAddressArrayEditor extends ArrayPropertyEditorSupport<InetAddress[]> {

    // use CDI?
    private PropertyEditorSupport<InetAddress> editor;

    public InetAddressArrayEditor() {
        super(InetAddress[].class);
        // this.editor = PropertyEditors.findEditor(InetAddress.class);
        this.editor = new InetAddressEditor();
    }

    /**
     * Build a InetAddress[] from comma or eol seperated elements
     *
     */
    @Override
    public void setAsText(final String text) throws IllegalArgumentException {
        if (PropertyEditors.isNull(text)) {
            super.setValue(null);
            return;
        }
        final String[] tokens = super.tokenize(text);
        final InetAddress[] values = new InetAddress[tokens.length];
        for (int index = 0; index < tokens.length; index++) {
            String value = tokens[index];
            editor.setAsText(value);
            values[index] = editor.getValue();
        }
        super.setValue(values);

    }

    /**
     * @return a comma seperated string of the array elements
     */
    @Override
    public String getAsText() {
        InetAddress[] values = (InetAddress[]) getValue();
        if (values == null) {
            return null;
        }

        String[] normalizedValues = new String[values.length];
        for (int index = 0; index < normalizedValues.length; index++) {
            this.editor.setValue(values[index]);
            normalizedValues[index] = this.editor.getAsText();
        }
        return super.encode(normalizedValues);

    }
}
