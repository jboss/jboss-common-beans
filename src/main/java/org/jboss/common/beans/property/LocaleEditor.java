/*
 * JBoss, Home of Professional Open Source
 * Copyright 2006, JBoss Inc., and individual contributors as indicated
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jboss.common.beans.property;

import java.beans.PropertyEditorSupport;
import java.util.Locale;
import java.util.StringTokenizer;

/**
 * Locale editor.
 *
 * @author <a href="mailto:ales.justin@jboss.com">Ales Justin</a>
 */
public class LocaleEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) {
        if (PropertyEditors.isNull(text)) {
            setValue(null);
            return;
        }
        try {
            setValue(parseLocaleString(text));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Failed to parse locale.", e);
        }
    }

    @Override
    public String getAsText() {
        Object value = getValue();
        return (value != null ? value.toString() : "");
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
