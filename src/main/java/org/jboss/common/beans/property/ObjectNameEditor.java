/*
 * Copyright Red Hat
 * SPDX-License-Identifier: Apache-2.0
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
