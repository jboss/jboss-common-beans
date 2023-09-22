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
public class DoubleArrayEditorTestCase extends PropertyEditorTester<double[]> {

    @Override
    public String[] getInputData() {
        return new String[]{"1.0,-1.0,0.0,1000.1"};
    }

    @Override
    public Object[] getOutputData() {
        return new Object[]{new double[]{1, -1, 0, 1000.1}};
    }

    @Override
    public String[] getConvertedToText() {
        return getInputData();
    }

    @Override
    public Comparator<double[]> getComparator() {
        return new DoubleArrayComparator();
    }

    @Override
    public Class getType() {
        return double[].class;
    }

    @Override
    public String logT(double[] t) {
        if (t == null) {
            return "<null>";
        } else {
            return Arrays.toString(t);
        }
    }

    class DoubleArrayComparator implements Comparator<double[]> {

        @Override
        public int compare(double[] o1, double[] o2) {
            return Arrays.equals(o1, o2) ? 0 : 1;
        }

    }
}
