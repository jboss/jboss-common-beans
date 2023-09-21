/*
 * Copyright Red Hat
 * SPDX-License-Identifier: Apache-2.0
 */
package org.jboss.common.beans.property;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * @author <a href="mailto:cdewolf@redhat.com">Carlo de Wolf</a>
 */
public class SystemPropertyITCase {

    @Test
    public void testUserDir() {
        try {
            final String userDir = PropertiesValueResolver.replaceProperties("${user.dir}");
            System.out.println(userDir);
            fail("Expected a SecurityException, because the test-classes don't have the proper permission");
        } catch (SecurityException e) {
            // good
            e.printStackTrace();
        }
    }

    @Test
    public void testUserName() {
        final String userName = PropertiesValueResolver.replaceProperties("${user.name}");
        assertNotNull(userName);
    }
}
