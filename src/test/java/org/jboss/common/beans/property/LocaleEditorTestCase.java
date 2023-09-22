/*
 * Copyright Red Hat
 * SPDX-License-Identifier: Apache-2.0
 */

package org.jboss.common.beans.property;

import java.util.Comparator;
import java.util.Locale;

/**
 * @author baranowb
 */
public class LocaleEditorTestCase extends PropertyEditorTester<Locale> {

    @Override
    public String[] getInputData() {
        return new String[]{Locale.getDefault().toString(), "ja_JP"};
    }

    @Override
    public Object[] getOutputData() throws Exception {
        return new Object[]{Locale.getDefault(), Locale.JAPAN};
    }

    @Override
    public String[] getConvertedToText() {
        return getInputData();
    }

    @Override
    public Comparator<Locale> getComparator() {
        return null;
    }

    @Override
    public Class getType() {
        return Locale.class;
    }

}
