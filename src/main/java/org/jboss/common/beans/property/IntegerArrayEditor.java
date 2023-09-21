/*
 * Copyright Red Hat
 * SPDX-License-Identifier: Apache-2.0
 */
package org.jboss.common.beans.property;

/**
 * A property editor for int[].
 *
 */
public class IntegerArrayEditor extends GenericArrayPropertyEditor<int[]> {

    public IntegerArrayEditor() {
        super(int[].class);

    }
}
