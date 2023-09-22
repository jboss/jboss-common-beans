/*
 * Copyright Red Hat
 * SPDX-License-Identifier: Apache-2.0
 */
package org.jboss.common.beans.property;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * A property editor for {@link java.util.Properties}.
 *
 * @author baranowb
 */
@SuppressWarnings("unchecked")
public class PropertiesEditor extends PropertyEditorSupport<Properties> {

    public PropertiesEditor() {
        super(Properties.class);
    }

    @Override
    public void setAsText(final String text) {
        if (BeanUtils.isNull(text)) {
            setValue(null);
            return;
        }
        try {
            Properties props = new Properties();
            props.load(new ByteArrayInputStream(text.getBytes()));
            setValue(props);
        } catch (IOException e) {
            throw new IllegalArgumentException("Failed to parse properties.", e);
        }
    }
}
