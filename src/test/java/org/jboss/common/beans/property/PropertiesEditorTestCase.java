/*
 * Copyright Red Hat
 * SPDX-License-Identifier: Apache-2.0
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
