/*
 * Copyright Red Hat
 * SPDX-License-Identifier: Apache-2.0
 */

package org.jboss.common.beans.property;

import java.math.BigInteger;
import java.util.Comparator;

/**
 * @author baranowb
 */
public class BigIntegerEditorTestCase extends PropertyEditorTester<BigInteger> {

    // NOTE: BigInteger fails if we pass the same numbers as in BigDecimal - to be precise in explodes on leadi '+'

    @Override
    public String[] getInputData() {
        return new String[]{"-1", "1234567890", "-1234567890"};
    }

    @Override
    public Object[] getOutputData() {
        return new Object[]{new BigInteger("-1"), new BigInteger("1234567890"), new BigInteger("-1234567890")};
    }

    @Override
    public String[] getConvertedToText() {
        return getInputData();
    }

    @Override
    public Comparator<BigInteger> getComparator() {
        // BigInteger is a star class, not like AtomicX, it overrides equals!
        return null;
    }

    @Override
    public Class getType() {
        return BigInteger.class;
    }
}
