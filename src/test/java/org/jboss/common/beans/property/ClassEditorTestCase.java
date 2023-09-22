/*
 * Copyright Red Hat
 * SPDX-License-Identifier: Apache-2.0
 */

package org.jboss.common.beans.property;

import java.util.Comparator;

/**
 * @author baranowb
 */
public class ClassEditorTestCase extends PropertyEditorTester<Class> {

    @Override
    public String[] getInputData() {
        return new String[]{"java.util.Arrays"};
    }

    @Override
    public Object[] getOutputData() {
        return new Object[]{java.util.Arrays.class};
    }

    @Override
    public String[] getConvertedToText() {
        return getInputData();
    }

    @Override
    public Comparator<Class> getComparator() {
        return null;
    }

    @Override
    public Class getType() {
        return Class.class;
    }

}
