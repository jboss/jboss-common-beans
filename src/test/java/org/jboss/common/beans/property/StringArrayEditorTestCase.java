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

package org.jboss.common.beans.property;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author baranowb
 */
public class StringArrayEditorTestCase extends PropertyEditorTester<String[]> {

    @Override
    public String[] getInputData() {
        //return new String[] { "1,2,3", "a,b,c", "", "#,%,\\,,.,_$,\\,v" };
        return new String[]{"#,%,\\,,.,_$,\\,v"};
    }

    @Override
    public Object[] getOutputData() {
        //return new Object[] { new String[] { "1", "2", "3" }, new String[] { "a", "b", "c" }, /*new String[] {}*/null, new String[] { "#", "%", ",", ".", "_$", ",v" } };
        return new Object[]{new String[]{"#", "%", ",", ".", "_$", ",v"}};
    }

    @Override
    public String[] getConvertedToText() {
        // TODO: check why escaped chars are not returned as they were.
        //return new String[] { "1,2,3", "a,b,c", "", "#,%,\\,,.,_$,,v" };
        return new String[]{"#,%,\\,,.,_$,\\,v"};
    }

    @Override
    public Comparator<String[]> getComparator() {
        return new StringArrayComparator();
    }

    @Override
    public Class getType() {
        return String[].class;
    }

    @Override
    public String logT(String[] t) {
        if (t == null) {
            return "<null>";
        } else {
            return Arrays.toString(t);
        }
    }

    class StringArrayComparator implements Comparator<String[]> {
        public int compare(String[] o1, String[] o2) {
            return Arrays.equals(o1, o2) ? 0 : 1;
        }
    }
}
