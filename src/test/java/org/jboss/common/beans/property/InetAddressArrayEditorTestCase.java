/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2011, Red Hat, Inc., and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
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
