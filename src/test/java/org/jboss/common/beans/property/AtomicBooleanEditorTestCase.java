/*
 * Copyright Red Hat
 * SPDX-License-Identifier: Apache-2.0
 */

package org.jboss.common.beans.property;

import java.util.Comparator;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author baranowb
 */
public class AtomicBooleanEditorTestCase extends PropertyEditorTester<AtomicBoolean> {

    @Override
    public String[] getInputData() {
        return new String[]{"true", "false", "TRUE", "FALSE", "tRuE", "FaLsE"/* , null */};
    }

    @Override
    public Object[] getOutputData() {
        return new Object[]{new AtomicBoolean(Boolean.TRUE), new AtomicBoolean(Boolean.FALSE),
                new AtomicBoolean(Boolean.TRUE), new AtomicBoolean(Boolean.FALSE), new AtomicBoolean(Boolean.TRUE),
                new AtomicBoolean(Boolean.FALSE) /* , new AtomicBoolean(Boolean.FALSE) */};
    }

    @Override
    public String[] getConvertedToText() {
        return new String[]{"true", "false", "true", "false", "true", "false"/* , null */};
    }

    @Override
    public Comparator<AtomicBoolean> getComparator() {
        return new AtomicBooleanComparator();
    }

    @Override
    public Class getType() {
        return AtomicBoolean.class;
    }

    class AtomicBooleanComparator implements Comparator<AtomicBoolean> {
        @Override
        public int compare(AtomicBoolean o1, AtomicBoolean o2) {
            if (o1 == null && !o2.get()) {
                return 0;
            }
            return o1.get() == o2.get() ? 0 : 1;
        }

    }
}
