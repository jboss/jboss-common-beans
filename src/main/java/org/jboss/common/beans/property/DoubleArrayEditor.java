/*
 * Copyright Red Hat
 * SPDX-License-Identifier: Apache-2.0
 */
package org.jboss.common.beans.property;

/**
 * A property editor for double[].
 *
 * @author baranowb
 */
public class DoubleArrayEditor extends GenericArrayPropertyEditor<double[]> {

    public DoubleArrayEditor() {
        super(double[].class);
    }
}