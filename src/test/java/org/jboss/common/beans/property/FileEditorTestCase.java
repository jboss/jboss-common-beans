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
