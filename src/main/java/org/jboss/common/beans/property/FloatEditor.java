/*
 * Copyright Red Hat
 * SPDX-License-Identifier: Apache-2.0
 */
package org.jboss.common.beans.property;

/**
 * A property editor for {@link Integer}.
 *
 * @author Scott.Stark@jboss.org
 */
public class FloatEditor extends PropertyEditorSupport<Float> {

    public FloatEditor() {
        super(Float.class);
    }

    /**
     * Map the argument text into and Integer using Integer.valueOf.
     */
    @Override
    public void setAsText(final String text) {
        if (BeanUtils.isNull(text)) {
            setValue(null);
            return;
        }
        try {
            Object newValue = Float.valueOf(text);
            setValue(newValue);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Failed to parse float.", e);
        }
    }
}
