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
import static org.junit.Assert.assertTrue;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorManager;
import java.util.Comparator;
import java.util.Locale;
import java.util.Properties;
import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author baranowb
 * 
 */
public abstract class PropertyEditorTester<T> {

    protected final Logger logger = Logger.getLogger(this.getClass().getName());

    @Before
    public void init() {
        // initialize locale, this is done for date editor, it wont pass test unless its run on EN system :).
        Locale testLocale = Locale.US;
        Locale.setDefault(testLocale);
        // init editors
        PropertyEditors.init();
    }

    @Test
    public void testPropertyEditor() throws Exception {

        // test
        Class<T> type = getType();
        PropertyEditor editor = PropertyEditorManager.findEditor(type);

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
        for (int i = 0; i < list.getLength(); i++)
            log(list.item(i), indent + indent);
    }

    protected String logString(String s) {
        return s != null ? s : "<null>";
    }
    /**
     * Default log method for T. Impl should override to provide something better.
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

}
