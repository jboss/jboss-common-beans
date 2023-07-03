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
import java.util.Locale;

/**
 * @author baranowb
 */
public class LocaleEditorTestCase extends PropertyEditorTester<Locale> {

    @Override
    public String[] getInputData() {
        return new String[]{Locale.getDefault().toString(), "ja_JP"};
    }

    @Override
    public Object[] getOutputData() throws Exception {
        return new Object[]{Locale.getDefault(), Locale.JAPAN};
    }

    @Override
    public String[] getConvertedToText() {
        return getInputData();
    }

    @Override
    public Comparator<Locale> getComparator() {
        return null;
    }

    @Override
    public Class getType() {
        return Locale.class;
    }

}
