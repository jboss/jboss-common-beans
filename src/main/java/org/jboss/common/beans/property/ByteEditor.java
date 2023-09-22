/*
 * Copyright Red Hat
 * SPDX-License-Identifier: Apache-2.0
 */
package org.jboss.common.beans.property;

/**
 * A property editor for {@link java.lang.Byte}.
 *
 */
public class ByteEditor extends PropertyEditorSupport<Byte> {

    public ByteEditor() {
        super(Byte.class);
        // TODO Auto-generated constructor stub
    }

    /**
     * Map the argument text into and Byte using Byte.decode.
     */
    @Override
    public void setAsText(final String text) {
        if (BeanUtils.isNull(text)) {
            setValue(null);
            return;
        }
        try {
            Object newValue = Byte.decode(text);
            setValue(newValue);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Failed to parse byte.", e);
        }
    }
}
