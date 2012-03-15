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

import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author baranowb
 * 
 */
public class AtomicIntegerEditorTestCase extends PropertyEditorTester<AtomicInteger> {

    @Override
    public String[] getInputData() {
        return new String[] { "-1", "0", "1" };
    }

    @Override
    public Object[] getOutputData() {
        return new Object[] { new AtomicInteger(-1), new AtomicInteger(0), new AtomicInteger(1) };
    }

    @Override
    public String[] getConvertedToText() {
        return getInputData();
    }

    @Override
    public Comparator<AtomicInteger> getComparator() {
        return new NumberComparator();
    }

    @Override
    public Class getType() {
        return AtomicInteger.class;
    }

    class NumberComparator implements Comparator<AtomicInteger> {
        public int compare(AtomicInteger o1, AtomicInteger o2) {
            return o1.intValue() - o2.intValue();
        }
    }

}
