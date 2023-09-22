/*
 * Copyright Red Hat
 * SPDX-License-Identifier: Apache-2.0
 */

package org.jboss.common.beans.property;

import java.util.Comparator;

/**
 * @author baranowb
 */
public class ByteEditorTestCase extends PropertyEditorTester<Byte> {

    @Override
    public String[] getInputData() {
        return new String[]{"1", "-1", "0", "0x1A"};
    }

    @Override
    public Object[] getOutputData() {
        return new Object[]{Byte.valueOf("1"), Byte.valueOf("-1"), Byte.valueOf("0"), Byte.decode("0x1A")};
    }

    @Override
    public String[] getConvertedToText() {
        return new String[]{"1", "-1", "0", "26"};
    }

    @Override
    public Comparator<Byte> getComparator() {
        return null;
    }

    @Override
    public Class getType() {
        return Byte.TYPE;
    }

}
