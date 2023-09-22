/*
 * Copyright Red Hat
 * SPDX-License-Identifier: Apache-2.0
 */
package org.jboss.common.beans.property;

/**
 * A property editor for {@link java.lang.Character}.
 *
 * TODO REVIEW: look at possibly parsing escape sequences?
 * @author adrian@jboss.org
 */
public class CharacterEditor extends PropertyEditorSupport<Character> {

    public CharacterEditor() {
        super(Character.class);
    }

    @Override
    public void setAsText(final String text) {
        if (BeanUtils.isNull(text)) {
            setValue(null);
            return;
        }
        if (text.length() != 1)
            throw new IllegalArgumentException("Too many (" + text.length() + ") characters: '" + text + "'");
        Object newValue = new Character(text.charAt(0));
        setValue(newValue);
    }
}