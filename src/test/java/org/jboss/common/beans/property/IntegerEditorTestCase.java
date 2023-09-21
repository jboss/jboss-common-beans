/*
 * Copyright Red Hat
 * SPDX-License-Identifier: Apache-2.0
 */

package org.jboss.common.beans.property;

import java.util.Comparator;

/**
 * @author baranowb
 */
public class IntegerEditorTestCase extends PropertyEditorTester<Integer> {

    @Override
    public String[] getInputData() {
        return new String[]{"1", "-1", "0", "0xA0"};
    }

    @Override
    public Object[] getOutputData() {
        return new Object[]{Integer.valueOf("1"), Integer.valueOf("-1"), Integer.valueOf("0"), Integer.decode("0xA0")};
    }

    @Override
    public String[] getConvertedToText() {
        return new String[]{"1", "-1", "0", "160"};
    }

    @Override
    public Comparator<Integer> getComparator() {
        return null;
    }

    @Override
    public Class getType() {
        return Integer.TYPE;
    }

}
