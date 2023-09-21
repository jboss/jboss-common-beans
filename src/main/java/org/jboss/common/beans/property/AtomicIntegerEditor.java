/*
 * Copyright Red Hat
 * SPDX-License-Identifier: Apache-2.0
 */
package org.jboss.common.beans.property;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * AtomicInteger property editor.
 *
 * @author <a href="mailto:ales.justin@jboss.com">Ales Justin</a>
 * @author baranowb
 */
public class AtomicIntegerEditor extends PropertyEditorSupport<AtomicInteger> {

    public AtomicIntegerEditor() {
        super(AtomicInteger.class);
    }

    @Override
    public void setAsText(final String text) {
        if (BeanUtils.isNull(text)) {
            setValue(null);
        } else {
            try {
                setValue(new AtomicInteger(Integer.decode(text)));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Failed to parse");
            }
        }
    }
}
