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
import static org.junit.Assert.assertTrue;

import java.beans.PropertyEditor;
import java.util.Comparator;
import java.util.Locale;
import java.util.Properties;
import java.util.logging.Logger;

import org.jboss.common.beans.property.finder.PropertyEditorFinder;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author baranowb
 */
public abstract class PropertyEditorTester<T> {

    protected final Logger logger = Logger.getLogger(this.getClass().getName());
    protected PropertyEditorFinder finder;

    @Before
    public void init() {
        // initialize locale, this is done for date editor, it wont pass test unless its run on EN system :).
        Locale testLocale = Locale.US;
        Locale.setDefault(testLocale);
        // init editors
        finder = PropertyEditorFinder.getInstance();
    }

    @Test
    public void testPropertyEditor() throws Exception {

        // test
        Class<T> type = getType();
        PropertyEditor editor = finder.find(type);

        String[] inputData = getInputData();
        Object[] expectedData = getOutputData();
        String[] expectedStringData = getConvertedToText();
        try {
            assertTrue("Did not find property editor for type: " + type, editor != null);
            assertEquals("Editor has wrong package!", this.getClass().getPackage(), editor.getClass().getPackage());
            logger.finest("Found property editor for: " + type + ", editor=" + editor.getClass().getName());
            assertEquals(editor + " input length", inputData.length, expectedData.length);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        for (int i = 0; i < inputData.length; i++) {
            String input = inputData[i];
            editor.setAsText(input);
            // TODO: check instance?
            T expected = (T) expectedData[i];
            T output = (T) editor.getValue();

            Comparator<T> c = getComparator();
            boolean equals = false;
            if (c == null) {
                equals = output != null ? output.equals(expected) : expected == null;
            } else {
                equals = c.compare(output, expected) == 0;
            }

            assertTrue("Test at index '" + i + "'. Transform(" + editor + ") of " + input + "\n does not equal! \nexpected = " + logT(expected) + "\noutput = "
                    + logT(output), equals);

            String expectedStringOutput = expectedStringData[i];
            String stringOutput = editor.getAsText();
            logger.finest("setAsText '" + logString(input) + "'");
            logger.finest("getAsText '" + logString(stringOutput) + "'");
            if (type != Properties.class) {
                // We can't meaningfully compare the PropertiesEditor string output
                String msg = "Test at index '" + i + "'. PropertyEditor: " + editor.getClass().getName()
                        + ", getAsText() == expectedStringOutput '";
                assertEquals(msg, expectedStringOutput, stringOutput);
            }

        }

    }

    /**
     * Log a Node hierarchy
     */
    protected void log(Node node, String indent) {
        String name = node.getNodeName();
        String value = node.getNodeValue();
        logger.finest(indent + "Name=" + name + ", Value=" + value);
        NodeList list = node.getChildNodes();
        for (int i = 0; i < list.getLength(); i++) { log(list.item(i), indent + indent); }
    }

    protected String logString(String s) {
        return s != null ? s : "<null>";
    }

    /**
     * Default log method for T. Impl should override to provide something better.
     *
     * @param t
     * @return
     */
    public String logT(T t) {
        if (t == null) {
            return "<null>";
        } else {
            return t.toString();
        }
    }

    public abstract String[] getInputData();

    public abstract Object[] getOutputData() throws Exception;

    public abstract String[] getConvertedToText();

    public abstract Comparator<T> getComparator();

    public abstract Class<T> getType();

    public static boolean isOSWindows() {
        final String val = System.getProperty("os.name");
        if (val != null && val.startsWith("Windows")) {
            return true;
        } else {
            return false;
        }
    }
}
