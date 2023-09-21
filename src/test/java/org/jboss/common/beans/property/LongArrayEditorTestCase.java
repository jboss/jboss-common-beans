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
public class LongArrayEditorTestCase extends PropertyEditorTester<long[]> {

    @Override
    public String[] getInputData() {
        return new String[]{"1,-1,0,1000"};
    }

    @Override
    public Object[] getOutputData() {
        return new Object[]{new long[]{1, -1, 0, 1000}};
    }

    @Override
    public String[] getConvertedToText() {
        return getInputData();
    }

    @Override
    public Comparator<long[]> getComparator() {
        return new LongArrayComparator();
    }

    @Override
    public Class getType() {
        return long[].class;
    }

    @Override
    public String logT(long[] t) {
        if (t == null) {
            return "<null>";
        } else {
            return Arrays.toString(t);
        }
    }

    class LongArrayComparator implements Comparator<long[]> {

        @Override
        public int compare(long[] o1, long[] o2) {
            return Arrays.equals(o1, o2) ? 0 : 1;
        }

    }
}
