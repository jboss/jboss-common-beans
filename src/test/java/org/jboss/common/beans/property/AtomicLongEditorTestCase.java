/*
 * Copyright Red Hat
 * SPDX-License-Identifier: Apache-2.0
 */

package org.jboss.common.beans.property;

import java.util.Comparator;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author baranowb
 */
public class AtomicLongEditorTestCase extends PropertyEditorTester<AtomicLong> {

    @Override
    public String[] getInputData() {
        return new String[]{"-1", "0", "1"};
    }

    @Override
    public Object[] getOutputData() {
        return new Object[]{new AtomicLong(-1), new AtomicLong(0), new AtomicLong(1)};
    }

    @Override
    public String[] getConvertedToText() {
        return getInputData();
    }

    @Override
    public Comparator<AtomicLong> getComparator() {
        return new NumberComparator();
    }

    @Override
    public Class<AtomicLong> getType() {
        return AtomicLong.class;
    }

    class NumberComparator implements Comparator<AtomicLong> {
        public int compare(AtomicLong o1, AtomicLong o2) {
            return o1.intValue() - o2.intValue();
        }
    }

}
