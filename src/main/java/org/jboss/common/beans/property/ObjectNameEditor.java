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

import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;

/**
 * @author baranowb
 *
 */
public class ObjectNameEditor extends PropertyEditorSupport<ObjectName> {

    /**
     * @param type
     */
    public ObjectNameEditor() {
        super(ObjectName.class);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param type
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
