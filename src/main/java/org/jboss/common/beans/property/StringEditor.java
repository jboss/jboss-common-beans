/*
 * Copyright Red Hat
 * SPDX-License-Identifier: Apache-2.0
 */
package org.jboss.common.beans.property;

/**
 * A property editor for {@link java.lang.String}.
 *
 * It is really a no-op.
 *
 * @author <a href="dimitris@jboss.org">Dimitris Andreadis</a>
 */
public class StringEditor extends PropertyEditorSupport<String> {

    public StringEditor() {
        super(String.class);
    }

    /**
     * Keep the provided String as is.
     */
    public void setAsText(String text) {
        //TODO: removed BeanUtils.isNull, since its a string, we want the thing
        setValue(text);
    }
}
