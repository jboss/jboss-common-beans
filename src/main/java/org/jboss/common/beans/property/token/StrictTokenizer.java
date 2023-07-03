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
