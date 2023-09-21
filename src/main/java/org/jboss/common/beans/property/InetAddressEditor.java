/*
 * Copyright Red Hat
 * SPDX-License-Identifier: Apache-2.0
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

    public InetAddressEditor() {
        super(InetAddress.class);
    }

    @Override
    public void setAsText(final String text) throws IllegalArgumentException {
        try {
            if (BeanUtils.isNull(text)) {
                setValue(null);
                return;
            }
            String value = text;
            if (text.startsWith("/")) {
                // seems like localhost sometimes will look like:
                // /127.0.0.1 and the getByNames barfs on the slash - JGH
                value = text.substring(1);
            }
            setValue(InetAddress.getByName(value));
        } catch (UnknownHostException e) {
            throw new IllegalArgumentException("Failed to parse: " + text, e);
        }
    }

    @Override
    public String getAsText() {
        InetAddress inetAddress = getValue();
        if (inetAddress == null) {
            return null;
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
}
