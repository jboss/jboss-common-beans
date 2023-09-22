/*
 * Copyright Red Hat
 * SPDX-License-Identifier: Apache-2.0
 */
package org.jboss.common.beans.property;

import java.math.BigInteger;

/**
 * A property editor for {@link java.math.BigInteger}.
 *
 * @author baranowb
 */
public class BigIntegerEditor extends PropertyEditorSupport<BigInteger> {

    public BigIntegerEditor() {
        super(BigInteger.class);
    }

    @Override
    public void setAsText(final String text) {
        if (BeanUtils.isNull(text)) {
            setValue(null);
        } else {
            try {
                setValue(new BigInteger(text));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Failed to parse");
            }
        }
    }
}
