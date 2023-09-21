/*
 * Copyright Red Hat
 * SPDX-License-Identifier: Apache-2.0
 */

package org.jboss.common.beans.property;

import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author baranowb
 */
public class GenericArrayEditorTestCase extends PropertyEditorTester<AtomicBoolean[]> {

    @Override
    public String[] getInputData() {
        return new String[]{"true,false,TRUE,FALSE,tRuE,FaLsE"/* , null */};
    }

    @Override
    public Object[] getOutputData() {
        return new Object[]{new AtomicBoolean[]{new AtomicBoolean(Boolean.TRUE), new AtomicBoolean(Boolean.FALSE), new AtomicBoolean(Boolean.TRUE), new AtomicBoolean(Boolean.FALSE), new AtomicBoolean(Boolean.TRUE),
                new AtomicBoolean(Boolean.FALSE)} /* , null */};
    }

    @Override
    public String[] getConvertedToText() {
        return new String[]{"true,false,true,false,true,false"/* , null */};
    }

    @Override
    public Comparator<AtomicBoolean[]> getComparator() {
        return new AtomicBooleanArrayComparator();
    }

    @Override
    public Class getType() {
        return AtomicBoolean[].class;
    }

    @Override
    public String logT(AtomicBoolean[] t) {
        if (t == null) {
            return "<null>";
        } else {
            return Arrays.toString(t);
        }
    }

    class AtomicBooleanArrayComparator implements Comparator<AtomicBoolean[]> {

        @Override
        public int compare(AtomicBoolean[] o1, AtomicBoolean[] o2) {
            if (o1.length != o2.length) {
                return 1;
            }

            for (int index = 0; index < o1.length; index++) {
                AtomicBoolean a1 = o1[index];
                AtomicBoolean a2 = o2[index];
                if (a1.get() != a2.get()) {
                    return 1;
                }
            }

            return 0;
        }

    }

}
