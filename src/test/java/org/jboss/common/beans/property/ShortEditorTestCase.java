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
