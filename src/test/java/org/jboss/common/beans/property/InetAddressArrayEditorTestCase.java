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

import java.net.InetAddress;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @author baranowb
 */
public class InetAddressArrayEditorTestCase extends PropertyEditorTester<InetAddress[]> {

    @Override
    public String[] getInputData() {
        return new String[]{"127.0.0.1,localhost"};
    }

    @Override
    public Object[] getOutputData() throws Exception {
        return new Object[]{new InetAddress[]{InetAddress.getByName("127.0.0.1"), InetAddress.getByName("localhost")}};
    }

    @Override
    public String[] getConvertedToText() {
        return getInputData();
    }

    @Override
    public Comparator<InetAddress[]> getComparator() {
        return new InetAddressComparator();
    }

    @Override
    public Class getType() {
        return InetAddress[].class;
    }

    @Override
    public String logT(InetAddress[] t) {
        if (t == null) {
            return "<null>";
        } else {
            return Arrays.toString(t);
        }
    }

    class InetAddressComparator implements Comparator<InetAddress[]> {

        /*
         * (non-Javadoc)
         *
         * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
         */
        @Override
        public int compare(InetAddress[] o1, InetAddress[] o2) {
            return Arrays.equals(o1, o2) ? 0 : 1;
        }
    }
}
