/*
 * JBoss, Home of Professional Open Source.
 * Copyright (c) 2012, Red Hat, Inc., and individual contributors
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

import java.security.AccessController;
import java.security.PrivilegedAction;

/**
 * @author <a href="mailto:cdewolf@redhat.com">Carlo de Wolf</a>
 * @author baranowb
 */
class PrivilegedActions {
    static class GetPropertyAction implements PrivilegedAction<String> {
        private final String key;
        private final String def;

        GetPropertyAction(final String key, final String def) {
            this.key = key;
            this.def = def;
        }

        @Override
        public String run() {
            return System.getProperty(key, def);
        }
    }

    static class GetEnvAction implements PrivilegedAction<String> {
        private final String key;
        private final String def;

        GetEnvAction(final String key, final String def) {
            this.key = key;
            this.def = def;
        }

        @Override
        public String run() {
            String envValue = System.getenv(key);
            return envValue == null ? def : envValue;
        }
    }

    static String getProperty(final String key, final String def) {
        final PrivilegedAction<String> action = new GetPropertyAction(key, def);
        if (System.getSecurityManager() == null)
            return action.run();
        return AccessController.doPrivileged(action);
    }

    static String getEnv(final String key, final String def) {
        final PrivilegedAction<String> action = new GetEnvAction(key, def);
        if (System.getSecurityManager() == null)
            return action.run();
        return AccessController.doPrivileged(action);
    }
}
