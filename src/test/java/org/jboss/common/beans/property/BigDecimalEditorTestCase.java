/*
 * Copyright Red Hat
 * SPDX-License-Identifier: Apache-2.0
 */

package org.jboss.common.beans.property;

import java.math.BigDecimal;
import java.util.Comparator;

/**
 * @author baranowb
 */
public class BigDecimalEditorTestCase extends PropertyEditorTester<BigDecimal> {

    @Override
    public String[] getInputData() {
        return new String[]{"-1", "+1234567890", "-1234567890"};
    }

    @Override
    public Object[] getOutputData() {
        return new Object[]{new BigDecimal("-1"), new BigDecimal("+1234567890"), new BigDecimal("-1234567890")};
    }

    @Override
    public String[] getConvertedToText() {
        return new String[]{"-1", "1234567890", "-1234567890"};
    }

    @Override
    public Comparator<BigDecimal> getComparator() {
        // BigDecimal is a star class, not like AtomicX, it overrides equals!
        return null;
    }

    @Override
    public Class getType() {
        return BigDecimal.class;
    }
}
