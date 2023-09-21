/*
 * Copyright Red Hat
 * SPDX-License-Identifier: Apache-2.0
 */

package org.jboss.common.beans.property;

import java.util.Comparator;

/**
 * @author baranowb
 */
public class DoubleEditorTestCase extends PropertyEditorTester<Double> {

    @Override
    public String[] getInputData() {
        return new String[]{"1", "-1", "0", "1000.1"};
    }

    @Override
    public Object[] getOutputData() {
        return new Object[]{Double.valueOf("1"), Double.valueOf("-1"), Double.valueOf("0"), Double.valueOf("1000.1")};
    }

    @Override
    public String[] getConvertedToText() {
        return new String[]{"1.0", "-1.0", "0.0", "1000.1"};
    }

    @Override
    public Comparator<Double> getComparator() {
        return null;
    }

    @Override
    public Class getType() {
        return Double.TYPE;
    }

}
