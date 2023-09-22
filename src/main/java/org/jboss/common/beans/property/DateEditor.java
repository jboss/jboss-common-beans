/*
 * Copyright Red Hat
 * SPDX-License-Identifier: Apache-2.0
 */
package org.jboss.common.beans.property;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * A property editor for {@link java.util.Date}.
 *
 * @author <a href="mailto:jason@planet57.com">Jason Dillon</a>
 * @author <a href="mailto:d_jencks@users.sourceforge.net">David Jencks</a>
 * @author <a href="mailto:scott.stark@jboss.org">Scott Stark</a>
 * @author <a href="mailto:adrian.brock@jboss.org">Adrian Brock</a>
 * @author <a href="mailto:ales.justin@jboss.org">Ales Justin</a>
 * @author <a href="mailto:dimitris@jboss.org">Dimitris Andreadis</a>
 */
@SuppressWarnings("unchecked")
public class DateEditor extends PropertyEditorSupport<Date> {

    public DateEditor() {
        super(Date.class);
        // TODO Auto-generated constructor stub
    }

    /** The formats to use when parsing the string date */
    private static DateFormat[] formats;
    static {
        initialize();
    }

    /**
     * Setup the parsing formats. Offered as a separate static method to allow testing of locale changes, since SimpleDateFormat
     * will use the default locale upon construction. Should not be normally used!
     */
    public static void initialize() {
        PrivilegedAction action = new PrivilegedAction() {
            public Object run() {
                String defaultFormat = System.getProperty("org.jboss.common.beans.property.DateEditor.format", "MMM d, yyyy");
                String defaultLocale = System.getProperty("org.jboss.common.beans.property.DateEditor.locale");
                DateFormat defaultDateFormat;
                if (defaultLocale == null || defaultLocale.length() == 0) {
                    defaultDateFormat = new SimpleDateFormat(defaultFormat);
                } else {
                    LocaleEditor localeEditor = new LocaleEditor();
                    localeEditor.setAsText(defaultLocale);
                    Locale locale = (Locale) localeEditor.getValue();
                    defaultDateFormat = new SimpleDateFormat(defaultFormat, locale);
                }

                formats = new DateFormat[] { defaultDateFormat,
                        // Tue Jan 04 00:00:00 PST 2005
                        new SimpleDateFormat("EEE MMM d HH:mm:ss z yyyy"),
                        // Wed, 4 Jul 2001 12:08:56 -0700
                        new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z") };
                return null;
            }
        };
        AccessController.doPrivileged(action);
    }

    /** Keep the text version of the date */
    private String text;

    /**
     * Sets directly the java.util.Date value
     *
     * @param value a java.util.Date
     */
    @Override
    public void setValue(Object value) {
        if (value instanceof Date || value == null) {
            this.text = null;
            super.setValue(value);
        } else {
            throw new IllegalArgumentException("setValue() expected java.util.Date value, got " + value.getClass().getName());
        }
    }

    /**
     * Parse the text into a java.util.Date by trying one by one the registered DateFormat(s).
     *
     * @param text the string date
     */
    @Override
    public void setAsText(String text) {
        if (BeanUtils.isNull(text)) {
            setValue(null);
            return;
        }
        ParseException pe = null;

        for (int i = 0; i < formats.length; i++) {
            try {
                // try to parse the date
                DateFormat df = formats[i];
                Date date = df.parse(text);

                // store the date in both forms
                this.text = text;
                super.setValue(date);

                // done
                return;
            } catch (ParseException e) {
                // remember the last seen exception
                pe = e;
            }
        }
        // couldn't parse
        throw new IllegalArgumentException("Failed to parse to Date!", pe);
    }

    /**
     * Returns either the cached string date, or the stored java.util.Date instance formated to string using the last of the
     * registered DateFormat(s)
     *
     * @return date as string
     */
    @Override
    public String getAsText() {
        if (text == null) {
            DateFormat df = formats[formats.length - 1];
            Date date = (Date) getValue();
            text = df.format(date);
        }
        return text;
    }

}