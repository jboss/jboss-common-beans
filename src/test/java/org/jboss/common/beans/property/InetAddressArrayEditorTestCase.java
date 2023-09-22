/*
 * Copyright Red Hat
 * SPDX-License-Identifier: Apache-2.0
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
