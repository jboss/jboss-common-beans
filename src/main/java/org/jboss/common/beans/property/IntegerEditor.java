/*
 * Copyright Red Hat
 * SPDX-License-Identifier: Apache-2.0
 */
package org.jboss.common.beans.property;

/**
 * A property editor for {@link java.lang.Integer}.
 *
 * @author Scott.Stark@jboss.org
 */
public class IntegerEditor extends PropertyEditorSupport<Integer> {

    public IntegerEditor() {
        super(Integer.class);
    }

    /**
     * Map the argument text into and Integer using Integer.decode.
     */
    @Override
    public void setAsText(final String text) {
        if (BeanUtils.isNull(text)) {
            setValue(null);
            return;
        }
        try {
            Object newValue = Integer.decode(text);
            setValue(newValue);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Failed to parse integer.", e);
        }
    }
}
