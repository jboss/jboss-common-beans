/*
 * Copyright Red Hat
 * SPDX-License-Identifier: Apache-2.0
 */

package org.jboss.common.beans.property.finder;

import org.jboss.common.beans.property.PropertyEditorSupport;

/**
 * @author baranowb
 */
public class UranusEditor extends PropertyEditorSupport<Uranus> {


    public UranusEditor() {
        super(Uranus.class);
    }

    /* (non-Javadoc)
     * @see org.jboss.common.beans.property.PropertyEditorSupport#setAsText(java.lang.String)
     */
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        this.setValue(new Uranus(text));
    }


}
