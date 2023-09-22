/*
 * Copyright Red Hat
 * SPDX-License-Identifier: Apache-2.0
 */

package org.jboss.common.beans.property;

import java.io.File;
import java.util.Comparator;

/**
 * @author baranowb
 */
public class FileEditorTestCase extends PropertyEditorTester<File> {

    @Override
    public String[] getInputData() {
        return new String[]{"/a/test1", "/a/subdir/../test2"};
    }

    @Override
    public Object[] getOutputData() throws Exception {
        return new Object[]{new File("/a/test1").getCanonicalFile(), new File("/a/test2").getCanonicalFile()};
    }

    @Override
    public String[] getConvertedToText() {
        try {
            return new String[]{new File("/a/test1").getCanonicalFile().toString(), new File("/a/subdir/../test2").getCanonicalFile().toString()};
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Comparator<File> getComparator() {
        return null;
    }

    @Override
    public Class getType() {
        return File.class;
    }

}
