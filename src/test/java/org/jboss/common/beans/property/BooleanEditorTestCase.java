/*
 * Copyright Red Hat
 * SPDX-License-Identifier: Apache-2.0
 */

package org.jboss.common.beans.property;

import java.util.Comparator;

/**
 * @author baranowb
 */
public class BooleanEditorTestCase extends PropertyEditorTester<Boolean> {

    @Override
    public String[] getInputData() {
        return new String[]{"true", "false", "TRUE", "FALSE", "tRuE", "FaLsE"/* , null */};
    }

    @Override
    public Object[] getOutputData() {
        return new Object[]{Boolean.TRUE, Boolean.FALSE, Boolean.TRUE, Boolean.FALSE, Boolean.TRUE, Boolean.FALSE /* , null */};
    }

    @Override
    public String[] getConvertedToText() {
        return new String[]{"true", "false", "true", "false", "true", "false"/* , null */};
    }

    @Override
    public Comparator<Boolean> getComparator() {
        return null;
    }

    @Override
    public Class getType() {
        return Boolean.TYPE;
    }

}
