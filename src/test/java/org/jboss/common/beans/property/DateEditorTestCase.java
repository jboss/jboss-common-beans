/*
* Copyright [2023] [Red Hat, Inc.]
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package org.jboss.common.beans.property;

import static org.junit.Assert.assertTrue;

import java.beans.PropertyEditor;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.junit.Test;

/**
 * @author baranowb
 */
public class DateEditorTestCase extends PropertyEditorTester<Date> {

    @Override
    public String[] getInputData() {
        return new String[]{"Jan 4, 2005", "Tue Jan  4 23:38:21 PST 2005", "Tue, 04 Jan 2005 23:38:48 -0800"};
    }

    @Override
    public Object[] getOutputData() {
        // The expected instance for each inputData value
        Calendar calendar = Calendar.getInstance();
        calendar.set(2005, 0, 4, 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date date1 = calendar.getTime();
        calendar.setTimeZone(TimeZone.getTimeZone("PST"));
        calendar.set(2005, 0, 4, 23, 38, 21);
        Date date2 = calendar.getTime();
        calendar.set(2005, 0, 4, 23, 38, 48);
        Date date3 = calendar.getTime();
        return new Object[]{date1, date2, date3};
    }

    @Override
    public String[] getConvertedToText() {
        return getInputData();
    }

    @Override
    public Comparator<Date> getComparator() {
        return null;
    }

    @Override
    public Class getType() {
        return Date.class;
    }

    @Test
    public void testDifferentLocales() throws Exception {

        logger.finest("+++ testDateEditor");

        Locale locale = Locale.getDefault();

        try {
            // Use the default locale
            logger.finest("Current Locale: " + Locale.getDefault());

            // An important date
            String text = "Fri, 25 Jun 1971 00:30:00 +0200";
            DateFormat format = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");
            Date date = format.parse(text);

            PropertyEditor editor = new DateEditor();
            editor.setAsText(text);
            logger.finest("setAsText('" + text + "') --> getValue() = '" + editor.getValue() + "'");
            assertTrue("Compare date1: " + date + ", date2: " + editor.getValue(),
                    date.compareTo((Date) editor.getValue()) == 0);

            editor.setValue(date);
            logger.finest("setValue('" + date + "') --> getAsText() - '" + editor.getAsText() + "'");
            Date date2 = format.parse(editor.getAsText());
            assertTrue("Compare date1: " + date + ", date2: " + date2, date.compareTo(date2) == 0);

            // Try in French
            Locale.setDefault(Locale.FRENCH);
            logger.finest("Current Locale: " + Locale.getDefault());
            DateEditor.initialize();

            // An important date
            text = "ven., 25 juin 1971 00:30:00 +0200";
            format = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");
            date = format.parse(text);

            editor = new DateEditor();
            editor.setAsText(text);
            logger.finest("setAsText('" + text + "') --> getValue() = '" + editor.getValue() + "'");
            assertTrue("Compare date1: " + date + ", date2: " + editor.getValue(),
                    date.compareTo((Date) editor.getValue()) == 0);

            editor.setValue(date);
            logger.finest("setValue('" + date + "') --> getAsText() = '" + editor.getAsText() + "'");
            date2 = format.parse(editor.getAsText());
            assertTrue("Compare date1: " + date + ", date2: " + date2, date.compareTo(date2) == 0);
        } finally {
            // reset locale
            Locale.setDefault(locale);
            DateEditor.initialize();
        }
    }

}
