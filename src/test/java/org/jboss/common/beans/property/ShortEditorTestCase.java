/*
 * Copyright Red Hat
 * SPDX-License-Identifier: Apache-2.0
 */

package org.jboss.common.beans.property;

import java.util.Comparator;

/**
 * @author baranowb
 */
public class ShortEditorTestCase extends PropertyEditorTester<Short> {

    @Override
    public String[] getInputData() {
        return new String[]{"1", "-1", "0", "0xA0"};
    }

    @Override
    public Object[] getOutputData() {
        return new Object[]{Short.valueOf("1"), Short.valueOf("-1"), Short.valueOf("0"), Short.decode("0xA0")};
    }

    @Override
    public String[] getConvertedToText() {
        return new String[]{"1", "-1", "0", "160"};
    }

    @Override
    public Comparator<Short> getComparator() {
        return null;
    }

    @Override
    public Class getType() {
        return Short.TYPE;
    }

}
