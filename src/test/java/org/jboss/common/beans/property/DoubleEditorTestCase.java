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
public class DoubleEditorTestCase extends PropertyEditorTester<Double> {

    @Override
    public String[] getInputData() {
        return new String[]{"1", "-1", "0", "1000.1"};
    }

    @Override
    public Object[] getOutputData() {
        return new Object[]{Double.valueOf("1"), Double.valueOf("-1"), Double.valueOf("0"), Double.valueOf("1000.1")};
    }

    @Override
    public String[] getConvertedToText() {
        return new String[]{"1.0", "-1.0", "0.0", "1000.1"};
    }

    @Override
    public Comparator<Double> getComparator() {
        return null;
    }

    @Override
    public Class getType() {
        return Double.TYPE;
    }

}
