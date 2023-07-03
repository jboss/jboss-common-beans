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
