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
 * A property editor for {@link java.net.InetAddress}.
 *
 * @author baranowb
 * @author <a href="mailto:cdewolf@redhat.com">Carlo de Wolf</a>
 */
public class InetAddressEditor extends PropertyEditorSupport<InetAddress> {
    @Override
    public void setAsText(final String text) throws IllegalArgumentException {
        try {
            if (PropertyEditors.isNull(text)) {
                setValue(null);
                return;
            }
            String value = text;
            if (text.startsWith("/")) {
                // seems like localhost sometimes will look like:
                // /127.0.0.1 and the getByNames barfs on the slash - JGH
                value = text.substring(1);
            }
            setValue(InetAddress.getByName(PropertiesValueResolver.replaceProperties(value)));
        } catch (UnknownHostException e) {
            throw new IllegalArgumentException("Failed to parse: " + text, e);
        }
    }

    @Override
    public String getAsText() {
        InetAddress inetAddress = getValue();
        if (inetAddress == null) {
            return "";
        }

        /*
         * Bad impl... it will produce something like: 'hostname/IP' - if hostname is not there '/IP'. But STILL!!! if hostname
         * is not set, the InetAddress.getHostName() ... will return something. To make it even funnier... InetAddress has no
         * method to parse what toString() returns.
         */
        String value = inetAddress.toString();
        String[] tokens = value.split("/");
        if (tokens[0].length() > 0) {
            return tokens[0];
        } else {
            return tokens[1];
        }
    }

    @Override
    public void setValue(final Object value) {
        super.setValue(InetAddress.class, value);
    }
}
