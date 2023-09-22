/*
 * Copyright Red Hat
 * SPDX-License-Identifier: Apache-2.0
 */
package org.jboss.common.beans.property;

import java.math.BigDecimal;

/**
 * A property editor for {@link java.math.BigDecimal}.
 *
 * @author baranowb
 */
public class BigDecimalEditor extends PropertyEditorSupport<BigDecimal> {

    public BigDecimalEditor() {
        super(BigDecimal.class);
    }

    @Override
    public void setAsText(final String text) {
        if (BeanUtils.isNull(text)) {
            setValue(null);
        } else {
            try {
                setValue(new BigDecimal(text));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Failed to parse");
            }
        }
    }
}
