/*
 * Copyright Red Hat
 * SPDX-License-Identifier: Apache-2.0
 */
package org.jboss.common.beans.property;

/**
 * A property editor for long[].
 *
 */
public class LongArrayEditor extends GenericArrayPropertyEditor<long[]> {

    public LongArrayEditor() {
        super(long[].class);
    }
}
