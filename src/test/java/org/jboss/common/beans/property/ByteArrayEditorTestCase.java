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
public class ByteArrayEditorTestCase extends PropertyEditorTester<byte[]> {

    @Override
    public String[] getInputData() {
        return new String[]{"1,-1,0,0x1A"};
    }

    @Override
    public Object[] getOutputData() {
        return new Object[]{new byte[]{1, -1, 0, 0x1A}};
    }

    @Override
    public String[] getConvertedToText() {
        return new String[]{"1,-1,0,26"};
    }

    @Override
    public Comparator<byte[]> getComparator() {
        return new ByteArrayComparator();
    }

    @Override
    public Class getType() {
        return byte[].class;
    }

    @Override
    public String logT(byte[] t) {
        if (t == null) {
            return "<null>";
        } else {
            return Arrays.toString(t);
        }
    }

    class ByteArrayComparator implements Comparator<byte[]> {

        @Override
        public int compare(byte[] o1, byte[] o2) {
            return Arrays.equals(o1, o2) ? 0 : 1;
        }

    }
}
