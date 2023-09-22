/*
 * Copyright Red Hat
 * SPDX-License-Identifier: Apache-2.0
 */

package org.jboss.common.beans.property;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author baranowb
 */
public class IntegerArrayEditorTestCase extends PropertyEditorTester<int[]> {

    @Override
    public String[] getInputData() {
        return new String[]{"0,#123,-123"};
    }

    @Override
    public Object[] getOutputData() {
        return new Object[]{new int[]{0, 0x123, -123}};
    }

    @Override
    public String[] getConvertedToText() {
        return new String[]{"0,291,-123"};
    }

    @Override
    public Comparator<int[]> getComparator() {
        return new IntArrayComparator();
    }

    @Override
    public Class getType() {
        return int[].class;
    }

    @Override
    public String logT(int[] t) {
        if (t == null) {
            return "<null>";
        } else {
            return Arrays.toString(t);
        }
    }

    class IntArrayComparator implements Comparator<int[]> {
        public int compare(int[] o1, int[] o2) {
            return Arrays.equals(o1, o2) ? 0 : 1;
        }
    }
}
