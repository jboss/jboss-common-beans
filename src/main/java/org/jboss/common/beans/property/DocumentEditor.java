/*
 * JBoss, Home of Professional Open Source
 * Copyright 2005, JBoss Inc., and individual contributors as indicated
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
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

import org.w3c.dom.Document;

/**
 * A property editor for {@link org.w3c.dom.Document}.
 *
 * @author <a href="mailto:eross@noderunner.net">Elias Ross</a>
 */
public class DocumentEditor extends XMLEditorSupport<Document> {

    public DocumentEditor() {
        super(Document.class);
    }

    /**
     * Sets as an Document created from a String.
     *
     * @throws IllegalArgumentException A parse exception occured
     */
    @Override
    public void setAsText(String text) {
        if (BeanUtils.isNull(text)) {
            setValue(null);
            return;
        }
        setValue(getAsDocument(text));
    }
}
