/*
 * Copyright Red Hat
 * SPDX-License-Identifier: Apache-2.0
 */
package org.jboss.common.beans.property;

import java.io.File;
import java.io.IOException;

/**
 * A property editor for {@link java.io.File}.
 *
 * @author baranowb
 */
public class FileEditor extends PropertyEditorSupport<File> {

    public FileEditor() {
        super(File.class);
    }

    @Override
    public void setAsText(final String text) {
        if (BeanUtils.isNull(text)) {
            setValue(null);
        } else {
            try {
                File f = new File(text).getCanonicalFile();
                setValue(f);
            } catch (IOException e) {
                throw new IllegalArgumentException("Failed to parse to Class!", e);
            }

        }
    }

}
