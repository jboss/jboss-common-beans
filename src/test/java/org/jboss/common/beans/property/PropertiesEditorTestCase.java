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
import java.util.Properties;

import org.junit.Before;

/**
 * @author baranowb
 */
public class PropertiesEditorTestCase extends PropertyEditorTester<Properties> {

    private Properties testedProperties;

    @Before
    public void initProperties() {
        testedProperties = new Properties();
        testedProperties.setProperty("prop1", "value1");
        testedProperties.setProperty("prop2", "value2");
        testedProperties.setProperty("prop3", "value3");
        testedProperties.setProperty("prop32", "value3");
    }

    @Override
    public String[] getInputData() {
        return new String[]{"prop1=value1\nprop2=value2\nprop3=value3\nprop32=value3\n"};
    }

    @Override
    public Object[] getOutputData() {
        // The expected instance for each inputData value

        return new Object[]{testedProperties};
    }

    @Override
    public String[] getConvertedToText() {
        return new String[]{testedProperties.toString()};
    }

    @Override
    public Comparator<Properties> getComparator() {
        return null;
    }

    @Override
    public Class getType() {
        return Properties.class;
    }

}
