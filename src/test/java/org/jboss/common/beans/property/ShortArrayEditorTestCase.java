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
public class ShortArrayEditorTestCase extends PropertyEditorTester<short[]> {

    @Override
    public String[] getInputData() {
        return new String[]{"1,-1,0,0xA0"};
    }

    @Override
    public Object[] getOutputData() {
        return new Object[]{new short[]{1, -1, 0, 0xA0}};
    }

    @Override
    public String[] getConvertedToText() {
        return new String[]{"1,-1,0,160"};
    }

    @Override
    public Comparator<short[]> getComparator() {
        return new ShortArrayComparator();
    }

    @Override
    public Class getType() {
        return short[].class;
    }

    @Override
    public String logT(short[] t) {
        if (t == null) {
            return "<null>";
        } else {
            return Arrays.toString(t);
        }
    }

    class ShortArrayComparator implements Comparator<short[]> {

        @Override
        public int compare(short[] o1, short[] o2) {
            return Arrays.equals(o1, o2) ? 0 : 1;
        }

    }
}
