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
public class GreedyTokenizerTestCase {
    private final String string = "aa,bbb\rxxx zxcv\t\tnnn\nn zzz";
    private final String[] expected = {"aa", "bbb", "xxx", "zxcv", "nnn", "n", "zzz"};

    @Test
    public void testGreediness() {
        //, \t\r\n
        GreedyTokenizer greedyTokenizer = new GreedyTokenizer();
        final String[] result = greedyTokenizer.tokenize(string);
        Assert.assertArrayEquals(expected, result);
    }
}
