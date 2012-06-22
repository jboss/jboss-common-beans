/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2011, Red Hat, Inc., and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.jboss.common.beans.property;

import java.beans.IntrospectionException;
import java.util.Properties;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author baranowb
 *
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
    public void testStripPackage(){
        final String expected = "Integer";
        Class clazz = Integer.class;
        String striped = BeanUtils.stripPackage(clazz);
        Assert.assertEquals(expected,striped);
        striped = BeanUtils.stripPackage(clazz.getName());
        Assert.assertEquals(expected,striped);
    }

    @Test
    public void testStripClass(){
        final String expected = "java.lang";
        Class clazz = Integer.class;
        String striped = BeanUtils.stripClass(clazz);
        Assert.assertEquals(expected,striped);
        striped = BeanUtils.stripClass(clazz.getName());
        Assert.assertEquals(expected,striped);
    }

    @Test
    public void testGetPrimitiveTypeForName(){
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
    public void testMapJavaBeanPropertiesSuccess() throws Exception{
        final TestBean testBean = new TestBean();
        Properties beanProperties = new Properties();
        final int expected_x = 1;
        final Double expected_dummy = new Double(11.1d);
        final String expected_info = "X Marks The Spot";

        beanProperties.setProperty("x", ""+expected_x);
        beanProperties.setProperty("dummy", ""+expected_dummy);
        beanProperties.setProperty("info", expected_info);

        BeanUtils.mapJavaBeanProperties(testBean, beanProperties);
        Assert.assertEquals(expected_x, testBean.getX());
        Assert.assertEquals(expected_dummy, testBean.getDummy());
        Assert.assertEquals(expected_info, testBean.getInfo());
        
    }

    @Test
    public void testMapJavaBeanPropertiesNoEditor() throws Exception{
        //fail cause there wont be a bean
        final TestBean testBean = new TestBean();
        Properties beanProperties = new Properties();
        final int expected_x = 1;
        final Double expected_dummy = new Double(11.1d);
        final String expected_info = "X Marks The Spot";

        beanProperties.setProperty("x", ""+expected_x);
        beanProperties.setProperty("dummy", ""+expected_dummy);
        beanProperties.setProperty("info", expected_info);
        beanProperties.setProperty("iWillFail", "xxx");

        BeanUtils.mapJavaBeanProperties(testBean, beanProperties);
        Assert.assertEquals(expected_x, testBean.getX());
        Assert.assertEquals(expected_dummy, testBean.getDummy());
        Assert.assertEquals(expected_info, testBean.getInfo());
        Assert.assertNull(testBean.getiWillFail());
    }
    
    @Test
    public void testMapJavaBeanPropertiesStrictPass() throws Exception{
        //fail cause there wont be a bean
        TestBean testBean = new TestBean();
        Properties beanProperties = new Properties();
        final int expected_x = 1;
        final Double expected_dummy = new Double(11.1d);
        final String expected_info = "X Marks The Spot";

        beanProperties.setProperty("x", ""+expected_x);
        beanProperties.setProperty("dummy", ""+expected_dummy);
        beanProperties.setProperty("info", expected_info);
        beanProperties.setProperty("iWillFail", "xxx");
        beanProperties.setProperty("iWillThrowException", "xxx");

        BeanUtils.mapJavaBeanProperties(testBean, beanProperties,false);
        Assert.assertEquals(expected_x, testBean.getX());
        Assert.assertEquals(expected_dummy, testBean.getDummy());
        Assert.assertEquals(expected_info, testBean.getInfo());
        Assert.assertNull(testBean.getiWillFail());
    }

    @Test(expected=IntrospectionException.class)
    public void testMapJavaBeanPropertiesStrictFail() throws Exception{
        //fail cause there wont be a bean
        TestBean testBean = new TestBean();
        Properties beanProperties = new Properties();
        final int expected_x = 1;
        final Double expected_dummy = new Double(11.1d);
        final String expected_info = "X Marks The Spot";

        beanProperties.setProperty("x", ""+expected_x);
        beanProperties.setProperty("dummy", ""+expected_dummy);
        beanProperties.setProperty("info", expected_info);
        beanProperties.setProperty("iWillFail", "xxx");
        beanProperties.setProperty("iWillThrowException", "xxx");
        
        BeanUtils.mapJavaBeanProperties(testBean, beanProperties,true);
    }

    @Test(expected=IntrospectionException.class)
    public void testMapJavaBeanPropertiesStrictFail2() throws Exception{
        //fail cause there wont be a bean
        TestBean testBean = new TestBean();
        Properties beanProperties = new Properties();
        final int expected_x = 1;
        final Double expected_dummy = new Double(11.1d);
        final String expected_info = "X Marks The Spot";

        beanProperties.setProperty("x", ""+expected_x);
        beanProperties.setProperty("dummy", ""+expected_dummy);
        beanProperties.setProperty("info", expected_info);
        beanProperties.setProperty("iWillFail", "xxx");
        beanProperties.setProperty("iWillThrowException", "xxx");
        
        BeanUtils.mapJavaBeanProperties(testBean, beanProperties);
    }    

    @Test
    public void testConvertValue() throws Exception{
       final Double expected_double = new Double(11.1d);
       final Double result = (Double) BeanUtils.convertValue(expected_double.toString(), expected_double.getClass().getName());
       Assert.assertEquals(expected_double, result);
    }
}
