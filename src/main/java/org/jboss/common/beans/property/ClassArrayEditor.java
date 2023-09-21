/*
 * Copyright Red Hat
 * SPDX-License-Identifier: Apache-2.0
 */
package org.jboss.common.beans.property;

/**
 * A property editor for Class[].
 *
 * @author baranowb
 */
public class ClassArrayEditor extends GenericArrayPropertyEditor<Class[]> {

    public ClassArrayEditor() {
        super(Class[].class);
    }

}