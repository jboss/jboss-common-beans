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

import java.io.File;
import java.util.Comparator;

import org.junit.internal.runners.statements.Fail;

/**
 * @author baranowb
 *
 */
public class FileEditorTestCase extends PropertyEditorTester<File> {

    @Override
    public String[] getInputData() {
        return new String[] { "/a/test1", "/a/subdir/../test2" };
    }

    @Override
    public Object[] getOutputData() throws Exception {
        return new Object[] { new File("/a/test1").getCanonicalFile(), new File("/a/test2").getCanonicalFile() };
    }

    @Override
    public String[] getConvertedToText() {
        try{
            return new String[] { new File("/a/test1").getCanonicalFile().toString(), new File("/a/subdir/../test2").getCanonicalFile().toString() };
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Comparator<File> getComparator() {
        return null;
    }

    @Override
    public Class getType() {
        return File.class;
    }

}
