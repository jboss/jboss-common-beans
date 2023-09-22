/*
 * Copyright Red Hat
 * SPDX-License-Identifier: Apache-2.0
 */
package org.jboss.common.beans.property;


/**
 * A property editor for {@link java.lang.Class}.
 *
 * @author baranowb
 */
public class ClassEditor extends PropertyEditorSupport<Class>{

    public ClassEditor() {
        super(Class.class);
    }

    @Override
    public void setAsText(final String text) {
        if (BeanUtils.isNull(text)) {
            setValue(null);
        } else {
            try {
                ClassLoader loader = Thread.currentThread().getContextClassLoader();
                Class<?> type = loader.loadClass(text);

                setValue(type);
            } catch (Exception e) {
                e.printStackTrace();
                throw new IllegalArgumentException("Failed to parse to Class!", e);
            }

        }
    }

    @Override
    public String getAsText() {
        Class c = (Class) getValue();
        if (c == null) {
            return null;
        }

        return c.getName();

    }
}
