/*
 * Copyright Red Hat
 * SPDX-License-Identifier: Apache-2.0
 */

package org.jboss.common.beans.property;

import java.util.Comparator;

/**
 * @author baranowb
 */
public class CharacterEditorTestCase extends PropertyEditorTester<Character> {

    @Override
    public String[] getInputData() {
        return new String[]{"A", "a", "Z", "z"};
    }

    @Override
    public Object[] getOutputData() {
        return new Object[]{new Character('A'), new Character('a'), new Character('Z'), new Character('z')};
    }

    @Override
    public String[] getConvertedToText() {
        return new String[]{"A", "a", "Z", "z"};
    }

    @Override
    public Comparator<Character> getComparator() {
        return null;
    }

    @Override
    public Class getType() {
        return Character.TYPE;
    }

}
