/*
 * Copyright Red Hat
 * SPDX-License-Identifier: Apache-2.0
 */

package org.jboss.common.beans.property;

import java.io.File;
import java.net.URI;
import java.util.Comparator;

/**
 * @author baranowb
 */
public class URIEditorTestCase extends PropertyEditorTester<URI> {

    @Override
    public String[] getInputData() {
        if (isOSWindows()) {
            return new String[]{"http://www.jboss.org", "file:/X:/path with space/tst.xml"};
        } else {
            return new String[]{"http://www.jboss.org", "file:/path with space/tst.xml"};
        }
    }

    @Override
    public Object[] getOutputData() throws Exception {
        if (isOSWindows()) {
            return new Object[]{new URI("http://www.jboss.org"), new File("X:/path with space/tst.xml").getCanonicalFile().toURI()};
        } else {
            return new Object[]{new URI("http://www.jboss.org"), new File("/path with space/tst.xml").getCanonicalFile().toURI()};
        }
    }

    @Override
    public String[] getConvertedToText() {
        return getInputData();
    }

    @Override
    public Comparator<URI> getComparator() {
        return null;
    }

    @Override
    public Class getType() {
        return URI.class;
    }

}
