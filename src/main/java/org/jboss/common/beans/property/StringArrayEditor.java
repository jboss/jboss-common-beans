/*
 * JBoss, Home of Professional Open Source
 * Copyright 2005, JBoss Inc., and individual contributors as indicated
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
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

import java.util.ArrayList;

/**
 * A property editor for String[]. The text format of a string array is a comma or \n, \r seperated list with \, representing an
 * escaped comma to include in the string element.
 *
 * @author <a href="mailto:jason@planet57.com">Jason Dillon</a>
 * @author Scott.Stark@jboss.org
 */
public class StringArrayEditor extends ArrayPropertyEditorSupport {

    /**
     * Build a String[] from comma or eol seperated elements
     *
     */
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (PropertyEditors.isNull(text)) {
            setValue(null);
            return;
        }
        String[] tokens = tokenize(text);
        super.setValue(tokens);
    }

    /**
     * @return a comma seperated string of the array elements
     */
    public String getAsText() {
        String[] theValue = (String[]) getValue();
        return encode(theValue);
    }

    protected String[] tokenize(String text) {
        ArrayList<String> list = new ArrayList<String>();
        StringBuffer tmp = new StringBuffer();
        for (int n = 0; n < text.length(); n++) {
            char c = text.charAt(n);
            switch (c) {
                case '\\':
                    tmp.append("\\");
                    if (n < text.length() && text.charAt(n + 1) == ',') {
                        tmp.setCharAt(tmp.length() - 1, ',');
                        n++;
                    }
                    break;
                case ',':
                case '\n':
                case '\r':
                    if (tmp.length() > 0)
                        list.add(tmp.toString());
                    tmp.setLength(0);
                    break;
                default:
                    tmp.append(c);
                    break;
            }
        }
        if (tmp.length() > 0)
            list.add(tmp.toString());

        String[] x = new String[list.size()];
        list.toArray(x);
        return x;
    }

    protected String encode(String[] v) {
        if (v == null) {
            return "";
        }
        StringBuffer text = new StringBuffer();

        for (int n = 0; n < v.length; n++) {
            String s = (String) v[n];
            if (s.contains(","))
                s = s.replace(",", "\\,");
            text.append(s);
            text.append(',');
        }
        // Remove the trailing ','
        if (text.length() > 0)
            text.setLength(text.length() - 1);
        return text.toString();
    }

}
