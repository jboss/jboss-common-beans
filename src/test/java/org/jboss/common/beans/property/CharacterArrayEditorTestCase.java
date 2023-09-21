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
public class CharacterArrayEditorTestCase extends PropertyEditorTester<char[]> {

    @Override
    public String[] getInputData() {
        return new String[]{"A,a,Z,z"};
    }

    @Override
    public Object[] getOutputData() {
        return new Object[]{new char[]{'A', 'a', 'Z', 'z'}};
    }

    @Override
    public String[] getConvertedToText() {
        return getInputData();
    }

    @Override
    public Comparator<char[]> getComparator() {
        return new CharacterArrayComparator();
    }

    @Override
    public Class getType() {
        return char[].class;
    }

    @Override
    public String logT(char[] t) {
        if (t == null) {
            return "<null>";
        } else {
            return Arrays.toString(t);
        }
    }

    class CharacterArrayComparator implements Comparator<char[]> {

        @Override
        public int compare(char[] o1, char[] o2) {
            return Arrays.equals(o1, o2) ? 0 : 1;
        }

    }
}
