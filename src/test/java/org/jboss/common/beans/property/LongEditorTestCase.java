/*
 * Copyright Red Hat
 * SPDX-License-Identifier: Apache-2.0
 */

package org.jboss.common.beans.property;

import java.util.Comparator;

/**
 * @author baranowb
 */
public class LongEditorTestCase extends PropertyEditorTester<Long> {

    @Override
    public String[] getInputData() {
        return new String[]{"1", "-1", "0", "1000"};
    }

    @Override
    public Object[] getOutputData() {
        return new Object[]{Long.valueOf("1"), Long.valueOf("-1"), Long.valueOf("0"), Long.valueOf("1000")};
    }

    @Override
    public String[] getConvertedToText() {
        return new String[]{"1", "-1", "0", "1000"};
    }

    @Override
    public Comparator<Long> getComparator() {
        return null;
    }

    @Override
    public Class getType() {
        return Long.TYPE;
    }

}
