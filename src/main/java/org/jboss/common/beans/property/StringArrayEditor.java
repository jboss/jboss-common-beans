/*
 * Copyright Red Hat
 * SPDX-License-Identifier: Apache-2.0
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
public class StringArrayEditor extends GenericArrayPropertyEditor<String[]> {

    public StringArrayEditor() {
        super(String[].class);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.jboss.common.beans.property.PropertyEditorSupport#setAsText(java.lang.String)
     */
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if(text == null){
            setValue(null);
            return;
        }
        setValue(tokenize(text));
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
            return null;
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
