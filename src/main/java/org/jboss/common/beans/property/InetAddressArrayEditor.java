/*
 * Copyright Red Hat
 * SPDX-License-Identifier: Apache-2.0
 */
package org.jboss.common.beans.property;

import java.net.InetAddress;

/**
 * A property editor for {@link java.net.InetAddress}[].
 *
 * @author baranowb
 */
public class InetAddressArrayEditor extends GenericArrayPropertyEditor<InetAddress[]> {

    public InetAddressArrayEditor() {
        super(InetAddress[].class);
    }
}
