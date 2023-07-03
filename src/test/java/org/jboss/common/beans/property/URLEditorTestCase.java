/*
* Copyright [2023] [Red Hat, Inc.]
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package org.jboss.common.beans.property;

import java.io.File;
import java.net.URL;
import java.util.Comparator;

/**
 * @author baranowb
 */
public class URLEditorTestCase extends PropertyEditorTester<URL> {

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
            return new Object[]{new URL("http://www.jboss.org"),
                    new File("X:/path with space/tst.xml").getCanonicalFile().toURI().toURL()};
        } else {
            return new Object[]{new URL("http://www.jboss.org"),
                    new File("/path with space/tst.xml").getCanonicalFile().toURI().toURL()};
        }

    }

    @Override
    public String[] getConvertedToText() {
        return getInputData();
    }

    @Override
    public Comparator<URL> getComparator() {
        return null;
    }

    @Override
    public Class getType() {
        return URL.class;
    }

}
