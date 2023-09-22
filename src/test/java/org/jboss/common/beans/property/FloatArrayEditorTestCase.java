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
public class FloatArrayEditorTestCase extends PropertyEditorTester<float[]> {

    @Override
    public String[] getInputData() {
        return new String[]{"1.0,-1.0,0.0,1000.1"};
    }

    @Override
    public Object[] getOutputData() {
        return new Object[]{new float[]{1, -1, 0, 1000.1f}};
    }

    @Override
    public String[] getConvertedToText() {
        return getInputData();
    }

    @Override
    public Comparator<float[]> getComparator() {
        return new FloatArrayComparator();
    }

    @Override
    public Class getType() {
        return float[].class;
    }

    @Override
    public String logT(float[] t) {
        if (t == null) {
            return "<null>";
        } else {
            return Arrays.toString(t);
        }
    }

    class FloatArrayComparator implements Comparator<float[]> {

        @Override
        public int compare(float[] o1, float[] o2) {
            return Arrays.equals(o1, o2) ? 0 : 1;
        }

    }
}
