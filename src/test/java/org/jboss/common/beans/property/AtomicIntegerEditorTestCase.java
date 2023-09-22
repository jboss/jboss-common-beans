/*
 * Copyright Red Hat
 * SPDX-License-Identifier: Apache-2.0
 */

package org.jboss.common.beans.property;

import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author baranowb
 */
public class AtomicIntegerEditorTestCase extends PropertyEditorTester<AtomicInteger> {

    @Override
    public String[] getInputData() {
        return new String[]{"-1", "0", "1"};
    }

    @Override
    public Object[] getOutputData() {
        return new Object[]{new AtomicInteger(-1), new AtomicInteger(0), new AtomicInteger(1)};
    }

    @Override
    public String[] getConvertedToText() {
        return getInputData();
    }

    @Override
    public Comparator<AtomicInteger> getComparator() {
        return new NumberComparator();
    }

    @Override
    public Class getType() {
        return AtomicInteger.class;
    }

    class NumberComparator implements Comparator<AtomicInteger> {
        public int compare(AtomicInteger o1, AtomicInteger o2) {
            return o1.intValue() - o2.intValue();
        }
    }

}
