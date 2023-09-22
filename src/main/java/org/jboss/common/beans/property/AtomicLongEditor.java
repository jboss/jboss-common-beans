/*
 * Copyright Red Hat
 * SPDX-License-Identifier: Apache-2.0
 */
package org.jboss.common.beans.property;

import java.util.concurrent.atomic.AtomicLong;

/**
 * AtomicLong property editor.
 *
 * @author <a href="mailto:ales.justin@jboss.com">Ales Justin</a>
 */
public class AtomicLongEditor extends PropertyEditorSupport<AtomicLong> {

    public AtomicLongEditor() {
        super(AtomicLong.class);
    }

    @Override
    public void setAsText(final String text) {
        if (BeanUtils.isNull(text)) {
            setValue(null);
        } else {
            try {
                setValue(new AtomicLong(Long.decode(text)));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Failed to parse");
            }
        }
    }
}