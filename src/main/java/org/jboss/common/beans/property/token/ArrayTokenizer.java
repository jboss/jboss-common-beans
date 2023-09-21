/*
 * Copyright Red Hat
 * SPDX-License-Identifier: Apache-2.0
 */

package org.jboss.common.beans.property.token;

import java.util.StringTokenizer;

/**
 * Simple abstract class to abstract tokenization process. Implementation provides rules of tokenization, ie. chars(delimiters) based on which
 * String is broken down into tokens.
 *
 * @author baranowb
 *
 */
public abstract class ArrayTokenizer {

    /**
     * Implementation of this method breaks down passed string into tokens.
     *
     * @param value
     * @return
     */
    public final String[] tokenize(String value) {
        StringTokenizer stringTokenizer = new StringTokenizer(value, getDelimiters());
        String[] broken = new String[stringTokenizer.countTokens()];
        for(int index = 0; index< broken.length;index++){
            broken[index] = stringTokenizer.nextToken();
        }
        return broken;
    }
    /**
     * Returns delimiters which are used in tokenization.
     * @return
     */
    protected abstract String getDelimiters();
}
