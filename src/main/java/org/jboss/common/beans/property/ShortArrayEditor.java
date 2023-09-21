/*
 * Copyright Red Hat
 * SPDX-License-Identifier: Apache-2.0
 */
package org.jboss.common.beans.property;

/**
 * A property editor for short[].
 *
 */
public class ShortArrayEditor extends GenericArrayPropertyEditor<short[]> {

    public ShortArrayEditor() {
        super(short[].class);
    }
}
