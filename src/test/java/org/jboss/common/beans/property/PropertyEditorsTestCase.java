package org.jboss.common.beans.property;

/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2006, Red Hat Middleware LLC, and individual contributors
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

import static org.junit.Assert.assertTrue;

import java.beans.PropertyEditorManager;
import java.util.Arrays;
import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for PropertyEditors
 */
@SuppressWarnings("unchecked")
public class PropertyEditorsTestCase {

    private static Logger log = Logger.getLogger(PropertyEditorsTestCase.class.getName());

    @Before
    public void init() {
        PropertyEditors.init();
        String[] paths = PropertyEditorManager.getEditorSearchPath();
        log.info(Arrays.asList(paths).toString());
    }

    @Test
    public void testEditorSearchPath() throws Exception {
        log.finest("+++ testEditorSearchPath");
        String[] searchPath = PropertyEditorManager.getEditorSearchPath();
        boolean foundJBossPath = false;
        Package _package = this.getClass().getPackage();
        String packageName = _package.getName();
        for (int p = 0; p < searchPath.length; p++) {
            String path = searchPath[p];
            log.finest("path[" + p + "]=" + path);
            foundJBossPath |= path.equals(packageName);
        }
        assertTrue("Did not find '" + packageName + "' in search path", foundJBossPath);
    }

}
