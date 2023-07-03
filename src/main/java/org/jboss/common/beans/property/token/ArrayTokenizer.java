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
