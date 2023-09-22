/*
 * Copyright Red Hat
 * SPDX-License-Identifier: Apache-2.0
 */

package org.jboss.common.beans.property.token;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author baranowb
 */
public class StrictTokenizerTestCase {
    private final String string = "aa,bbb\rxxx zxcv\t\tnnn\nn zzz";
    private final String[] expected = {"aa", "bbb\rxxx zxcv\t\tnnn", "n zzz"};

    @Test
    public void testGreediness() {
        //, \t\r\n
        StrictTokenizer greedyTokenizer = new StrictTokenizer();
        final String[] result = greedyTokenizer.tokenize(string);
        Assert.assertArrayEquals(expected, result);
    }
}