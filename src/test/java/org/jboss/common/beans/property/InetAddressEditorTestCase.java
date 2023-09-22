/*
 * Copyright Red Hat
 * SPDX-License-Identifier: Apache-2.0
 */

package org.jboss.common.beans.property;

import java.net.InetAddress;
import java.util.Comparator;

/**
 * @author baranowb
 */
public class InetAddressEditorTestCase extends PropertyEditorTester<InetAddress> {

    @Override
    public String[] getInputData() {
        return new String[]{"127.0.0.1", "localhost"};
    }

    @Override
    public Object[] getOutputData() throws Exception {
        return new Object[]{InetAddress.getByName("127.0.0.1"), InetAddress.getByName("localhost")};
    }

    @Override
    public String[] getConvertedToText() {
        return getInputData();
    }

    @Override
    public Comparator<InetAddress> getComparator() {
        return null;
    }

    @Override
    public Class getType() {
        return InetAddress.class;
    }
}
