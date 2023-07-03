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