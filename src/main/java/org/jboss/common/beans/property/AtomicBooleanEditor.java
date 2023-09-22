/*
 * Copyright Red Hat
 * SPDX-License-Identifier: Apache-2.0
 */
package org.jboss.common.beans.property;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * AtomicBoolean property editor.
 *
 * @author <a href="mailto:ales.justin@jboss.com">Ales Justin</a>
 * @author baranowb
 */
public class AtomicBooleanEditor extends PropertyEditorSupport<AtomicBoolean> {

    public AtomicBooleanEditor() {
        super(AtomicBoolean.class);
    }

    private static final String[] BOOLEAN_TAGS = { "true", "false" };

    @Override
    public void setAsText(final String text) {
        if (BeanUtils.isNull(text)) {
            setValue(null);
        } else {
            setValue(new AtomicBoolean(Boolean.parseBoolean(text)));
        }
    }

    /**
     * @return the values {"true", "false"}
     */
    @Override
    public String[] getTags() {
        return BOOLEAN_TAGS;
    }
}