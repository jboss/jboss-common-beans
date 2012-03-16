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

import java.util.StringTokenizer;

/**
 * Class to add some common behavior for array editors.
 *
 * @author baranowb
 */
public abstract class ArrayPropertyEditorSupport<T> extends PropertyEditorSupport<T> {
    /**
     * @param type
     */
    public ArrayPropertyEditorSupport(Class<T> type) {
        super(type);
    }

    /*
     * NOTE: not a good place, generics cant be used here, since java primitives cannot be referenced as parametrized type...
     */

    /**
     * Method to provide common tokenization mechanism. By default this method expects array elements to be limited by
     * '\r','\n', or ',' characters.
     *
     * @param text
     * @return
     */
    protected String[] tokenize(String text) {
        // makes us iterate twice...
        StringTokenizer tokenizer = new StringTokenizer(text, "\r\n,");
        String[] tokens = new String[tokenizer.countTokens()];
        int index = 0;
        while (tokenizer.hasMoreElements()) {
            tokens[index++] = tokenizer.nextToken();
        }
        return tokens;
    }

    /**
     * Produces comma delimited string containg values from passed array.
     *
     * @param v
     * @return
     */
    protected String encode(boolean[] v) {
        if (v == null) {
            return null;
        }
        StringBuffer text = new StringBuffer();
        int length = v == null ? 0 : v.length;
        for (int n = 0; n < length; n++) {
            if (n > 0)
                text.append(',');
            text.append(v[n]);
        }
        return text.toString();
    }

    /**
     * Produces comma delimited string containg values from passed array.
     *
     * @param v
     * @return
     */
    protected String encode(byte[] v) {
        if (v == null) {
            return null;
        }
        StringBuffer text = new StringBuffer();
        int length = v == null ? 0 : v.length;
        for (int n = 0; n < length; n++) {
            if (n > 0)
                text.append(',');
            text.append(v[n]);
        }
        return text.toString();
    }

    /**
     * Produces comma delimited string containg values from passed array.
     *
     * @param v
     * @return
     */
    protected String encode(short[] v) {
        if (v == null) {
            return null;
        }
        StringBuffer text = new StringBuffer();
        int length = v == null ? 0 : v.length;
        for (int n = 0; n < length; n++) {
            if (n > 0)
                text.append(',');
            text.append(v[n]);
        }
        return text.toString();
    }

    /**
     * Produces comma delimited string containg values from passed array.
     *
     * @param v
     * @return
     */
    protected String encode(int[] v) {
        if (v == null) {
            return null;
        }
        StringBuffer text = new StringBuffer();
        int length = v == null ? 0 : v.length;
        for (int n = 0; n < length; n++) {
            if (n > 0)
                text.append(',');
            text.append(v[n]);
        }
        return text.toString();
    }

    /**
     * Produces comma delimited string containg values from passed array.
     *
     * @param v
     * @return
     */
    protected String encode(char[] v) {
        if (v == null) {
            return null;
        }
        StringBuffer text = new StringBuffer();
        int length = v == null ? 0 : v.length;
        for (int n = 0; n < length; n++) {
            if (n > 0)
                text.append(',');
            text.append(v[n]);
        }
        return text.toString();
    }

    /**
     * Produces comma delimited string containg values from passed array.
     *
     * @param v
     * @return
     */
    protected String encode(long[] v) {
        if (v == null) {
            return null;
        }
        StringBuffer text = new StringBuffer();
        int length = v == null ? 0 : v.length;
        for (int n = 0; n < length; n++) {
            if (n > 0)
                text.append(',');
            text.append(v[n]);
        }
        return text.toString();
    }

    /**
     * Produces comma delimited string containg values from passed array.
     *
     * @param v
     * @return
     */
    protected String encode(float[] v) {
        if (v == null) {
            return null;
        }
        StringBuffer text = new StringBuffer();
        int length = v == null ? 0 : v.length;
        for (int n = 0; n < length; n++) {
            if (n > 0)
                text.append(',');
            text.append(v[n]);
        }
        return text.toString();
    }

    /**
     * Produces comma delimited string containg values from passed array.
     *
     * @param v
     * @return
     */
    protected String encode(double[] v) {
        if (v == null) {
            return null;
        }
        StringBuffer text = new StringBuffer();
        int length = v == null ? 0 : v.length;
        for (int n = 0; n < length; n++) {
            if (n > 0)
                text.append(',');
            text.append(v[n]);
        }
        return text.toString();
    }

    /**
     * Produces comma delimited string containg values from passed array.
     *
     * @param v
     * @return
     */
    protected String encode(Object[] v) {
        if (v == null) {
            return null;
        }
        StringBuffer text = new StringBuffer();
        int length = v == null ? 0 : v.length;
        for (int n = 0; n < length; n++) {
            if (n > 0)
                text.append(',');
            text.append(v[n]);
        }
        return text.toString();
    }
}
