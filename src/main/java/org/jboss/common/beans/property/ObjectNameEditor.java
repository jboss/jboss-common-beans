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

import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;

/**
 * @author baranowb
 *
 */
public class ObjectNameEditor extends PropertyEditorSupport<ObjectName> {

    /**
     *
     */
    public ObjectNameEditor() {
        super(ObjectName.class);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param source
     */
    public ObjectNameEditor(Object source) {
        super(ObjectName.class, source);
        // TODO Auto-generated constructor stub
    }

    /* (non-Javadoc)
     * @see org.jboss.common.beans.property.PropertyEditorSupport#setAsText(java.lang.String)
     */
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        try {
            setValue(new ObjectName(text));
        } catch (MalformedObjectNameException e) {
            throw new IllegalArgumentException(e);
        }

    }

}
