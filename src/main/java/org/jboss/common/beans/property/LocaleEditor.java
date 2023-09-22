/*
 * Copyright Red Hat
 * SPDX-License-Identifier: Apache-2.0
 */
package org.jboss.common.beans.property;

import java.util.Locale;
import java.util.StringTokenizer;

/**
 * Locale editor.
 *
 * @author <a href="mailto:ales.justin@jboss.com">Ales Justin</a>
 */
public class LocaleEditor extends PropertyEditorSupport<Locale> {

    public LocaleEditor() {
        super(Locale.class);
    }

    @Override
    public void setAsText(String text) {
        if (BeanUtils.isNull(text)) {
            setValue(null);
            return;
        }
        try {
            setValue(parseLocaleString(text));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Failed to parse locale.", e);
        }
    }

    private static Locale parseLocaleString(String localeString) {
        // split into array.
        StringTokenizer stringTokenizer = new StringTokenizer(localeString, "_ ");

        String[] parts = new String[stringTokenizer.countTokens()];
        int index = 0;
        while (stringTokenizer.countTokens() > 0) {
            parts[index++] = stringTokenizer.nextToken();
        }

        String language = (parts.length > 0 ? parts[0] : "");
        String country = (parts.length > 1 ? parts[1] : "");
        String variant = "";
        if (parts.length >= 2) {
            // There is definitely a variant, and it is everything after the country
            // code sans the separator between the country code and the variant.
            int endIndexOfCountryCode = localeString.indexOf(country) + country.length();
            // Strip off any leading '_' and whitespace, what's left is the variant.
            variant = trimLeadingWhitespace(localeString.substring(endIndexOfCountryCode));
            if (variant.startsWith("_")) {
                variant = variant.replaceFirst("_*", "");
            }
        }
        return (language.length() > 0 ? new Locale(language, country, variant) : null);
    }

    /**
     * @param substring
     * @return
     */
    private static String trimLeadingWhitespace(String substring) {
        StringBuffer buf = new StringBuffer(substring);
        while (buf.length() > 0 && Character.isWhitespace(buf.charAt(0))) {
            buf.deleteCharAt(0);
        }
        return buf.toString();
    }
}
