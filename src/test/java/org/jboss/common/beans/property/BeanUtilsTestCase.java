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

import java.beans.IntrospectionException;
import java.util.Properties;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author baranowb
 */
public class BeanUtilsTestCase {

    @Test
    public void testGetWrapper() {
        Assert.assertEquals(Integer.class, BeanUtils.getWrapperTypeFor(int.class));
        Assert.assertEquals(Long.class, BeanUtils.getWrapperTypeFor(long.class));
        Assert.assertEquals(Float.class, BeanUtils.getWrapperTypeFor(float.class));
        Assert.assertEquals(Double.class, BeanUtils.getWrapperTypeFor(double.class));
        Assert.assertEquals(Character.class, BeanUtils.getWrapperTypeFor(char.class));
        Assert.assertEquals(Short.class, BeanUtils.getWrapperTypeFor(short.class));
        Assert.assertEquals(Byte.class, BeanUtils.getWrapperTypeFor(byte.class));
        Assert.assertEquals(Boolean.class, BeanUtils.getWrapperTypeFor(boolean.class));
    }

    @Test
    public void testStripPackage() {
        final String expected = "Integer";
        Class clazz = Integer.class;
        String striped = BeanUtils.stripPackage(clazz);
        Assert.assertEquals(expected, striped);
        striped = BeanUtils.stripPackage(clazz.getName());
        Assert.assertEquals(expected, striped);
    }

    @Test
    public void testStripClass() {
        final String expected = "java.lang";
        Class clazz = Integer.class;
        String striped = BeanUtils.stripClass(clazz);
        Assert.assertEquals(expected, striped);
        striped = BeanUtils.stripClass(clazz.getName());
        Assert.assertEquals(expected, striped);
    }

    @Test
    public void testGetPrimitiveTypeForName() {
        Assert.assertEquals(int.class, BeanUtils.getPrimitiveTypeForName("int"));
        Assert.assertEquals(long.class, BeanUtils.getPrimitiveTypeForName("long"));
        Assert.assertEquals(float.class, BeanUtils.getPrimitiveTypeForName("float"));
        Assert.assertEquals(double.class, BeanUtils.getPrimitiveTypeForName("double"));
        Assert.assertEquals(char.class, BeanUtils.getPrimitiveTypeForName("char"));
        Assert.assertEquals(short.class, BeanUtils.getPrimitiveTypeForName("short"));
        Assert.assertEquals(byte.class, BeanUtils.getPrimitiveTypeForName("byte"));
        Assert.assertEquals(boolean.class, BeanUtils.getPrimitiveTypeForName("boolean"));
    }

    @Test
    public void testMapJavaBeanPropertiesSuccess() throws Exception {
        final TestBean testBean = new TestBean();
        Properties beanProperties = new Properties();
        final int expected_x = 1;
        final Double expected_dummy = new Double(11.1d);
        final String expected_info = "X Marks The Spot";

        beanProperties.setProperty("x", "" + expected_x);
        beanProperties.setProperty("dummy", "" + expected_dummy);
        beanProperties.setProperty("info", expected_info);

        BeanUtils.mapJavaBeanProperties(testBean, beanProperties);
        Assert.assertEquals(expected_x, testBean.getX());
        Assert.assertEquals(expected_dummy, testBean.getDummy());
        Assert.assertEquals(expected_info, testBean.getInfo());

    }

    @Test
    public void testMapJavaBeanPropertiesNoEditor() throws Exception {
        //fail cause there wont be a bean
        final TestBean testBean = new TestBean();
        Properties beanProperties = new Properties();
        final int expected_x = 1;
        final Double expected_dummy = new Double(11.1d);
        final String expected_info = "X Marks The Spot";

        beanProperties.setProperty("x", "" + expected_x);
        beanProperties.setProperty("dummy", "" + expected_dummy);
        beanProperties.setProperty("info", expected_info);
        beanProperties.setProperty("iWillFail", "xxx");

        BeanUtils.mapJavaBeanProperties(testBean, beanProperties);
        Assert.assertEquals(expected_x, testBean.getX());
        Assert.assertEquals(expected_dummy, testBean.getDummy());
        Assert.assertEquals(expected_info, testBean.getInfo());
        Assert.assertNull(testBean.getiWillFail());
    }

    @Test
    public void testMapJavaBeanPropertiesStrictPass() throws Exception {
        //fail cause there wont be a bean
        TestBean testBean = new TestBean();
        Properties beanProperties = new Properties();
        final int expected_x = 1;
        final Double expected_dummy = new Double(11.1d);
        final String expected_info = "X Marks The Spot";

        beanProperties.setProperty("x", "" + expected_x);
        beanProperties.setProperty("dummy", "" + expected_dummy);
        beanProperties.setProperty("info", expected_info);
        beanProperties.setProperty("iWillFail", "xxx");
        beanProperties.setProperty("iWillThrowException", "xxx");

        BeanUtils.mapJavaBeanProperties(testBean, beanProperties, false);
        Assert.assertEquals(expected_x, testBean.getX());
        Assert.assertEquals(expected_dummy, testBean.getDummy());
        Assert.assertEquals(expected_info, testBean.getInfo());
        Assert.assertNull(testBean.getiWillFail());
    }

    @Test(expected = IntrospectionException.class)
    public void testMapJavaBeanPropertiesStrictFail() throws Exception {
        //fail cause there wont be a bean
        TestBean testBean = new TestBean();
        Properties beanProperties = new Properties();
        final int expected_x = 1;
        final Double expected_dummy = new Double(11.1d);
        final String expected_info = "X Marks The Spot";

        beanProperties.setProperty("x", "" + expected_x);
        beanProperties.setProperty("dummy", "" + expected_dummy);
        beanProperties.setProperty("info", expected_info);
        beanProperties.setProperty("iWillFail", "xxx");
        beanProperties.setProperty("iWillThrowException", "xxx");

        BeanUtils.mapJavaBeanProperties(testBean, beanProperties, true);
    }

    @Test(expected = IntrospectionException.class)
    public void testMapJavaBeanPropertiesStrictFail2() throws Exception {
        //fail cause there wont be a bean
        TestBean testBean = new TestBean();
        Properties beanProperties = new Properties();
        final int expected_x = 1;
        final Double expected_dummy = new Double(11.1d);
        final String expected_info = "X Marks The Spot";

        beanProperties.setProperty("x", "" + expected_x);
        beanProperties.setProperty("dummy", "" + expected_dummy);
        beanProperties.setProperty("info", expected_info);
        beanProperties.setProperty("iWillFail", "xxx");
        beanProperties.setProperty("iWillThrowException", "xxx");

        BeanUtils.mapJavaBeanProperties(testBean, beanProperties);
    }

    @Test
    public void testConvertValue() throws Exception {
        final Double expected_double = new Double(11.1d);
        final Double result = (Double) BeanUtils.convertValue(expected_double.toString(), expected_double.getClass().getName());
        Assert.assertEquals(expected_double, result);
    }
}
