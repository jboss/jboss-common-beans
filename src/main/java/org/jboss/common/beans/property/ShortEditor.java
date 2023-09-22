/*
 * Copyright Red Hat
 * SPDX-License-Identifier: Apache-2.0
 */
package org.jboss.common.beans.property;

/**
 * A property editor for {@link java.lang.Short}.
 *
 * @author Scott.Stark@jboss.org
 */
public class ShortEditor extends PropertyEditorSupport<Short> {

    public ShortEditor() {
        super(Short.class);
    }

    /**
     * Map the argument text into and Short using Short.decode.
     */
    public void setAsText(final String text) {
        if (BeanUtils.isNull(text)) {
            setValue(null);
            return;
        }
        try {
            Object newValue = Short.decode(text);
            setValue(newValue);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Failed to parse short.", e);
        }
    }

}
