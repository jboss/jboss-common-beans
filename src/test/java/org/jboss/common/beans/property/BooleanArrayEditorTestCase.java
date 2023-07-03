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
public class BooleanArrayEditorTestCase extends PropertyEditorTester<boolean[]> {

    @Override
    public String[] getInputData() {
        return new String[]{"true,false,TRUE,FALSE,tRuE,FaLsE"/* , null */};
    }

    @Override
    public Object[] getOutputData() {
        return new Object[]{new boolean[]{Boolean.TRUE, Boolean.FALSE, Boolean.TRUE, Boolean.FALSE, Boolean.TRUE,
                Boolean.FALSE} /* , null */};
    }

    @Override
    public String[] getConvertedToText() {
        return new String[]{"true,false,true,false,true,false"/* , null */};
    }

    @Override
    public Comparator<boolean[]> getComparator() {
        return new BooleanArrayComparator();
    }

    @Override
    public Class getType() {
        return boolean[].class;
    }

    @Override
    public String logT(boolean[] t) {
        if (t == null) {
            return "<null>";
        } else {
            return Arrays.toString(t);
        }
    }

    class BooleanArrayComparator implements Comparator<boolean[]> {

        @Override
        public int compare(boolean[] o1, boolean[] o2) {
            return Arrays.equals(o1, o2) ? 0 : 1;
        }

    }

}
