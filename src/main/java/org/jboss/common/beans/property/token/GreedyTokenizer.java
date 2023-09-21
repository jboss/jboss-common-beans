/*
 * Copyright Red Hat
 * SPDX-License-Identifier: Apache-2.0
 */

package org.jboss.common.beans.property.token;

/**
 * Greedy array tokenizer. Delimiters used are
 * <ul>
 * <li>','</li>
 * <li>' '</li>
 * <li>'\t'</li>
 * <li>'\r'</li>
 * <li>'\n'</li>
 * </ul>
 *
 * @author baranowb
 *
 */
public class GreedyTokenizer extends ArrayTokenizer {

    private static final String DELIMITERS = ", \t\r\n";

    /*
     * (non-Javadoc)
     *
     * @see org.jboss.common.beans.property.token.ArrayTokenizer#getDelimiters()
     */
    @Override
    protected String getDelimiters() {
        return DELIMITERS;
    }

}
