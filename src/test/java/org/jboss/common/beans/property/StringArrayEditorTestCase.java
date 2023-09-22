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
public class StringArrayEditorTestCase extends PropertyEditorTester<String[]> {

    @Override
    public String[] getInputData() {
        //return new String[] { "1,2,3", "a,b,c", "", "#,%,\\,,.,_$,\\,v" };
        return new String[]{"#,%,\\,,.,_$,\\,v"};
    }

    @Override
    public Object[] getOutputData() {
        //return new Object[] { new String[] { "1", "2", "3" }, new String[] { "a", "b", "c" }, /*new String[] {}*/null, new String[] { "#", "%", ",", ".", "_$", ",v" } };
        return new Object[]{new String[]{"#", "%", ",", ".", "_$", ",v"}};
    }

    @Override
    public String[] getConvertedToText() {
        // TODO: check why escaped chars are not returned as they were.
        //return new String[] { "1,2,3", "a,b,c", "", "#,%,\\,,.,_$,,v" };
        return new String[]{"#,%,\\,,.,_$,\\,v"};
    }

    @Override
    public Comparator<String[]> getComparator() {
        return new StringArrayComparator();
    }

    @Override
    public Class getType() {
        return String[].class;
    }

    @Override
    public String logT(String[] t) {
        if (t == null) {
            return "<null>";
        } else {
            return Arrays.toString(t);
        }
    }

    class StringArrayComparator implements Comparator<String[]> {
        public int compare(String[] o1, String[] o2) {
            return Arrays.equals(o1, o2) ? 0 : 1;
        }
    }
}
