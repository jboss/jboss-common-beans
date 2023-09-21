/*
 * Copyright Red Hat
 * SPDX-License-Identifier: Apache-2.0
 */
package org.jboss.common.beans.property;

/**
 * A property editor for float[].
 *
 * @author baranowb
 */
public class FloatArrayEditor extends GenericArrayPropertyEditor<float[]> {

    public FloatArrayEditor() {
        super(float[].class);
    }
}
