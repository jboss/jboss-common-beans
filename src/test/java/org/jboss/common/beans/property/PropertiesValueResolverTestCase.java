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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Map;

import org.junit.Test;

/**
 * Test case for Resolver.
 *
 * @author baranowb
 */
public class PropertiesValueResolverTestCase {

    private String userEnvPropertyName;


    public PropertiesValueResolverTestCase() {
        super();

        if (PropertyEditorTester.isOSWindows()) {
            userEnvPropertyName = "USERNAME";
        } else {
            userEnvPropertyName = "USER";
        }
    }

    @Test
    public void testBaseReplacement() {
        String val1 = "replaced-1";
        String val2 = "coocooBird";
        System.setProperty("val1", val1);
        System.setProperty("val2", val2);

        String expected = "x=" + val1 + ";y=" + val2;
        String toParse = "x=${val1};y=${val2}";
        String replaced = PropertiesValueResolver.replaceProperties(toParse);
        assertEquals("Simple replacement from System.properties failed!", expected, replaced);
    }

    @Test
    public void testBaseReplacementFail() {
        String notReplecable = "x=${IDontExist}";
        try {
            PropertiesValueResolver.replaceProperties(notReplecable);
            fail("IES is expected here...");
        } catch (IllegalStateException e) {
            // OK :)
        }

    }

    @Test
    public void testAlternativeReplacement() {
        String val1 = "replaced-1";
        String val2 = "coocooBird";
        System.setProperty("val1", val1);
        System.setProperty("val2", val2);

        String expected = "x=" + val1 + ";y=" + val2;
        String toParse = "x=${val1};y=${dontExist,val2}";
        String replaced = PropertiesValueResolver.replaceProperties(toParse);
        assertEquals("Simple replacement from System.properties failed!", expected, replaced);

    }

    @Test
    public void testAlternativeReplacementFail() {

        String val1 = "replaced-1";
        System.setProperty("val1", val1);

        String toParse = "x=${val1};y=${dontExist,DontExistEither}";
        try {
            PropertiesValueResolver.replaceProperties(toParse);
            fail("IES is expected here...");
        } catch (IllegalStateException e) {
            // OK :)
        }

    }

    @Test
    public void testAlternativeReplacementWithDefault() {
        String val1 = "replaced-1";
        String val2 = "coocooBird";
        String val3 = "Mocca";
        System.setProperty("val1", val1);
        System.setProperty("val2", val2);
        System.setProperty("val3", val3);

        String expected = "x=" + val1 + ";y=" + val2;
        String toParse = "x=${val1};y=${dontExist,val2:val3}";
        String replaced = PropertiesValueResolver.replaceProperties(toParse);
        assertEquals("Simple replacement from System.properties failed!", expected, replaced);
    }

    @Test
    public void testDefaultReplacement() {
        String val1 = "replaced-1";
        String val2 = "coocooBird";
        String val3 = "Mocca";
        System.setProperty("val1", val1);
        System.setProperty("val2", val2);
        System.setProperty("val3", val3);

        String expected = "x=DEFAULT;y=" + val2;
        String toParse = "x=${xx:DEFAULT};y=${dontExist,val2:val3}";
        String replaced = PropertiesValueResolver.replaceProperties(toParse);
        assertEquals("Simple replacement from System.properties failed!", expected, replaced);
    }

    @Test
    public void testEnvReplacement() {
        // cant set ENV, we need to use real vars here.
        Map<String, String> env = System.getenv();

        String xyz = "Mocca";
        System.setProperty("xyz", xyz);
        String expected = "x=" + env.get(userEnvPropertyName) + ";home=" + env.get("HOME") + ";last=" + xyz;
        String toParse = "x=${env." + userEnvPropertyName + "};home=${env.HOME};last=${xyz}";
        String replaced = PropertiesValueResolver.replaceProperties(toParse);

        assertEquals("Simple replacement from System.properties failed!", expected, replaced);

    }

    @Test
    public void testEnvReplacementFail() {
        // cant set ENV, we need to use real vars here.
        Map<String, String> env = System.getenv();
        String xyz = "Mocca";
        System.setProperty("xyz", xyz);
        try {
            String toParse = "x=${env.USER_X};home=${env.HOME};last=${xyz}";
            String replaced = PropertiesValueResolver.replaceProperties(toParse);

            fail("IES is expected here... Replaced '" + replaced + "'");
        } catch (IllegalStateException e) {
            // OK :)
        }

    }

    @Test
    public void testEnvAlternativeReplacement() {
        // cant set ENV, we need to use real vars here.
        Map<String, String> env = System.getenv();

        String xyz = "Mocca";
        System.setProperty("xyz", xyz);
        String expected = "x=" + env.get(userEnvPropertyName) + ";home=" + env.get("HOME") + ";last=" + xyz;
        String toParse = "x=${IDontExist,env." + userEnvPropertyName + "};home=${env.IdontExist,env.HOME};last=${xyz}";
        String replaced = PropertiesValueResolver.replaceProperties(toParse);

        assertEquals("Simple replacement from System.properties failed!", expected, replaced);

    }

    @Test
    public void testEnvAlternativeReplacementFail() {
        // cant set ENV, we need to use real vars here.
        Map<String, String> env = System.getenv();
        String xyz = "Mocca";
        System.setProperty("xyz", xyz);
        try {
            String toParse = "x=${env.USER_X,env.USER_Z};home=${env.HOME};last=${xyz}";
            String replaced = PropertiesValueResolver.replaceProperties(toParse);

            fail("IES is expected here... Replaced '" + replaced + "'");
        } catch (IllegalStateException e) {
            // OK :)
        }

    }

    @Test
    public void testEnvAlternativeReplacementWithDefault() {
        // cant set ENV, we need to use real vars here.
        Map<String, String> env = System.getenv();
        String xyz = "Mocca";
        System.setProperty("xyz", xyz);
        String expected = "x=" + env.get(userEnvPropertyName) + ";home=" + env.get("HOME") + ";last=" + xyz;
        String toParse = "x=${IDontExist,env." + userEnvPropertyName + ":env.DISPLAY};home=${env.IdontExist,env.HOME};last=${xyz}";
        String replaced = PropertiesValueResolver.replaceProperties(toParse);

        assertEquals("Simple replacement from System.properties failed!", expected, replaced);

    }
}
