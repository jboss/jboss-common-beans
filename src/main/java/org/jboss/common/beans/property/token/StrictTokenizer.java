/*
 * Copyright Red Hat
 * SPDX-License-Identifier: Apache-2.0
 */

package org.jboss.common.beans.property.token;


/**
 * Strict array tokenizer. Delimiters used are
 * <ul>
 * <li>','</li>
 * <li>'\n'</li>
 * </ul>
 *
 * @author baranowb
 *
 */
public class StrictTokenizer extends ArrayTokenizer {

    /**
     * Delimiters for this class, '\n' and ','
     */
    private static final String DELIMITERS = ",\n";

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
