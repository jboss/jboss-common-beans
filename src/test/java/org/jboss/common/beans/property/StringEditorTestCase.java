/*
 * Copyright Red Hat
 * SPDX-License-Identifier: Apache-2.0
 */

package org.jboss.common.beans.property;

import java.util.Comparator;

/**
 * @author baranowb
 */
public class StringEditorTestCase extends PropertyEditorTester<String> {

    @Override
    public String[] getInputData() {
        return new String[]{"JBoss, Home of Professional Open Source"};
    }

    @Override
    public Object[] getOutputData() {
        return new Object[]{"JBoss, Home of Professional Open Source"};
    }

    @Override
    public String[] getConvertedToText() {
        return getInputData();
    }

    @Override
    public Comparator<String> getComparator() {
        return null;
    }

    @Override
    public Class getType() {
        return String.class;
    }

}
