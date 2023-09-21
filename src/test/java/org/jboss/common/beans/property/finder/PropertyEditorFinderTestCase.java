/*
 * Copyright Red Hat
 * SPDX-License-Identifier: Apache-2.0
 */

package org.jboss.common.beans.property.finder;

import static org.junit.Assert.assertNotNull;

import java.beans.PropertyEditor;

import org.jboss.common.beans.property.ShortEditor;
import org.junit.Test;

/**
 * @author baranowb
 */
public class PropertyEditorFinderTestCase {

    @Test
    public void testInstance() {
        assertNotNull(PropertyEditorFinder.getInstance());
    }

    @Test
    public void testPackagedEditor() {
        DefaultPropertyEditorFinder finder = (DefaultPropertyEditorFinder) PropertyEditorFinder.getInstance();
        String[] packages = new String[]{ShortEditor.class.getPackage().getName(), this.getClass().getPackage().getName()};
        finder.setEditorSearchPackages(packages);
        PropertyEditor editor = finder.find(Uranus.class);
        assertNotNull(editor);
        editor = finder.find(Uranus[].class);
        assertNotNull(editor);
    }
}
