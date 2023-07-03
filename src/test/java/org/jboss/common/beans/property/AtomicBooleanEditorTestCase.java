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

import java.util.Comparator;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author baranowb
 */
public class AtomicBooleanEditorTestCase extends PropertyEditorTester<AtomicBoolean> {

    @Override
    public String[] getInputData() {
        return new String[]{"true", "false", "TRUE", "FALSE", "tRuE", "FaLsE"/* , null */};
    }

    @Override
    public Object[] getOutputData() {
        return new Object[]{new AtomicBoolean(Boolean.TRUE), new AtomicBoolean(Boolean.FALSE),
                new AtomicBoolean(Boolean.TRUE), new AtomicBoolean(Boolean.FALSE), new AtomicBoolean(Boolean.TRUE),
                new AtomicBoolean(Boolean.FALSE) /* , new AtomicBoolean(Boolean.FALSE) */};
    }

    @Override
    public String[] getConvertedToText() {
        return new String[]{"true", "false", "true", "false", "true", "false"/* , null */};
    }

    @Override
    public Comparator<AtomicBoolean> getComparator() {
        return new AtomicBooleanComparator();
    }

    @Override
    public Class getType() {
        return AtomicBoolean.class;
    }

    class AtomicBooleanComparator implements Comparator<AtomicBoolean> {
        @Override
        public int compare(AtomicBoolean o1, AtomicBoolean o2) {
            if (o1 == null && !o2.get()) {
                return 0;
            }
            return o1.get() == o2.get() ? 0 : 1;
        }

    }
}
