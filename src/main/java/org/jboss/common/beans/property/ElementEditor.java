/*
 * Copyright Red Hat
 * SPDX-License-Identifier: Apache-2.0
 */
package org.jboss.common.beans.property;

import org.w3c.dom.Element;

/**
 * A property editor for {@link org.w3c.dom.Element}.
 *
 * @author <a href="mailto:eross@noderunner.net">Elias Ross</a>
 */
public class ElementEditor extends XMLEditorSupport<Element> {

    public ElementEditor() {
        super(Element.class);
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
        setValue(getAsDocument(text).getDocumentElement());
    }
}