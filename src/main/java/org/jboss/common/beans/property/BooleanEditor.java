/*
 * Copyright Red Hat
 * SPDX-License-Identifier: Apache-2.0
 */
package org.jboss.common.beans.property;

/**
 * A property editor for {@link java.lang.Boolean}.
 *
 * @author Scott.Stark@jboss.org
 */
public class BooleanEditor extends PropertyEditorSupport<Boolean> {

    public BooleanEditor() {
        super(Boolean.class);
    }

    private static final String[] BOOLEAN_TAGS = { "true", "false" };

    /**
     * Map the argument text into Boolean.TRUE or Boolean.FALSE using Boolean.valueOf.
     */
    @Override
    public void setAsText(final String text) {
        if (BeanUtils.isNull(text)) {
            setValue(null);
            return;
        }
        Object newValue = Boolean.valueOf(text);
        setValue(newValue);
    }

    /**
     * @return the values {"true", "false"}
     */
    @Override
    public String[] getTags() {
        return BOOLEAN_TAGS;
    }
}