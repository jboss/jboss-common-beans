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
