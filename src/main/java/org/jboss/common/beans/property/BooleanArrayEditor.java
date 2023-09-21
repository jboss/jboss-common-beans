/*
 * Copyright Red Hat
 * SPDX-License-Identifier: Apache-2.0
 */
package org.jboss.common.beans.property;


/**
 * A property editor for boolean[].
 *
 * @author baranowb
 */
public class BooleanArrayEditor extends GenericArrayPropertyEditor<boolean[]> {

    public BooleanArrayEditor() {
        super(boolean[].class);
    }

    private static final String[] BOOLEAN_TAGS = { "true", "false" };

    @Override
    public String[] getTags() {
        return BOOLEAN_TAGS;
    }

}