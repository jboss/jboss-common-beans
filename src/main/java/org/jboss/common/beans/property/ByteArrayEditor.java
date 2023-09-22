/*
 * Copyright Red Hat
 * SPDX-License-Identifier: Apache-2.0
 */
package org.jboss.common.beans.property;

/**
 * A property editor for byte[].
 *
 * @author baranowb
 */
public class ByteArrayEditor extends GenericArrayPropertyEditor<byte[]> {

    public ByteArrayEditor() {
        super(byte[].class);
    }
}
