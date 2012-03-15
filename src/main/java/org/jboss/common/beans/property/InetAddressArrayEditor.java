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
import java.net.UnknownHostException;

/**
 * A property editor for {@link java.net.InetAddress}[].
 *
 * @author baranowb
 */
public class InetAddressArrayEditor extends ArrayPropertyEditorSupport {
    /**
     * Build a InetAddress[] from comma or eol seperated elements
     *
     */
    @Override
    public void setAsText(final String text) throws IllegalArgumentException {
        try {
            if (PropertyEditors.isNull(text)) {
                super.setValue(null);
                return;
            }
            final String[] tokens = super.tokenize(text);
            final InetAddress[] values = new InetAddress[tokens.length];
            for (int index = 0; index < tokens.length; index++) {
                String value = tokens[index];
                if (text.startsWith("/")) {
                    // seems like localhost sometimes will look like:
                    // /127.0.0.1 and the getByNames barfs on the slash - JGH
                    value = text.substring(1);
                }
                values[index] = InetAddress.getByName(PropertiesValueResolver.replaceProperties(value));
            }
            super.setValue(values);
        } catch (UnknownHostException e) {
            throw new IllegalArgumentException("Failed to parse to InetAddress!", e);
        }
    }

    /**
     * @return a comma seperated string of the array elements
     */
    @Override
    public String getAsText() {
        InetAddress[] values = (InetAddress[]) getValue();
        if (values == null) {
            return "";
        }

        /*
         * Bad impl... it will produce something like: 'hostname/IP' - if hostname is not there '/IP'. But STILL!!! if hostname
         * is not set, the InetAddress.getHostName() ... will return something. To make it even funnier... InetAddress has no
         * method to parse what toString() returns.
         */
        String[] normalizedValues = new String[values.length];
        for (int index = 0; index < normalizedValues.length; index++) {
            String value = values[index].toString();
            String[] tokens = value.split("/");
            if (tokens[0].length() > 0) {
                normalizedValues[index] = tokens[0];
            } else {
                normalizedValues[index] = tokens[1];
            }
        }
        return super.encode(normalizedValues);

    }
}
