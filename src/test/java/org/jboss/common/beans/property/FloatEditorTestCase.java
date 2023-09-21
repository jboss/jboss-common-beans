/*
 * Copyright Red Hat
 * SPDX-License-Identifier: Apache-2.0
 */

package org.jboss.common.beans.property;

import java.util.Comparator;

/**
 * @author baranowb
 */
public class FloatEditorTestCase extends PropertyEditorTester<Float> {

    @Override
    public String[] getInputData() {
        return new String[]{"1", "-1", "0", "1000.1"};
    }

    @Override
    public Object[] getOutputData() {
        return new Object[]{Float.valueOf("1"), Float.valueOf("-1"), Float.valueOf("0"), Float.valueOf("1000.1")};
    }

    @Override
    public String[] getConvertedToText() {
        return new String[]{"1.0", "-1.0", "0.0", "1000.1"};
    }

    @Override
    public Comparator<Float> getComparator() {
        return null;
    }

    @Override
    public Class getType() {
        return Float.TYPE;
    }

}
