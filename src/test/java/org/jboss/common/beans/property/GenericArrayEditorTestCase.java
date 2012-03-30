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

import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author baranowb
 * 
 */
public class GenericArrayEditorTestCase extends PropertyEditorTester<AtomicBoolean[]> {

    @Override
    public String[] getInputData() {
        return new String[] { "true,false,TRUE,FALSE,tRuE,FaLsE"/* , null */};
    }

    @Override
    public Object[] getOutputData() {
        return new Object[] { new AtomicBoolean[] { new AtomicBoolean(Boolean.TRUE), new AtomicBoolean(Boolean.FALSE), new AtomicBoolean(Boolean.TRUE), new AtomicBoolean(Boolean.FALSE), new AtomicBoolean(Boolean.TRUE),
                new AtomicBoolean(Boolean.FALSE) } /* , null */};
    }

    @Override
    public String[] getConvertedToText() {
        return new String[] { "true,false,true,false,true,false"/* , null */};
    }

    @Override
    public Comparator<AtomicBoolean[]> getComparator() {
        return new AtomicBooleanArrayComparator();
    }

    @Override
    public Class getType() {
        return AtomicBoolean[].class;
    }

    @Override
    public String logT(AtomicBoolean[] t) {
        if (t == null) {
            return "<null>";
        } else {
            return Arrays.toString(t);
        }
    }

    class AtomicBooleanArrayComparator implements Comparator<AtomicBoolean[]> {

        @Override
        public int compare(AtomicBoolean[] o1, AtomicBoolean[] o2) {
            if(o1.length!=o2.length){
                return 1;
            }
            
            for(int index = 0; index< o1.length; index++){
                AtomicBoolean a1 = o1[index];
                AtomicBoolean a2 = o2[index];
                if(a1.get()!= a2.get()){
                    return 1;
                }
            }

            return 0;
        }

    }

}
