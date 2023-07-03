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
public class DoubleArrayEditorTestCase extends PropertyEditorTester<double[]> {

    @Override
    public String[] getInputData() {
        return new String[]{"1.0,-1.0,0.0,1000.1"};
    }

    @Override
    public Object[] getOutputData() {
        return new Object[]{new double[]{1, -1, 0, 1000.1}};
    }

    @Override
    public String[] getConvertedToText() {
        return getInputData();
    }

    @Override
    public Comparator<double[]> getComparator() {
        return new DoubleArrayComparator();
    }

    @Override
    public Class getType() {
        return double[].class;
    }

    @Override
    public String logT(double[] t) {
        if (t == null) {
            return "<null>";
        } else {
            return Arrays.toString(t);
        }
    }

    class DoubleArrayComparator implements Comparator<double[]> {

        @Override
        public int compare(double[] o1, double[] o2) {
            return Arrays.equals(o1, o2) ? 0 : 1;
        }

    }
}
