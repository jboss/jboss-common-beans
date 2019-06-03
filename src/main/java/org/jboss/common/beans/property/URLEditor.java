/*
 * JBoss, Home of Professional Open Source
 * Copyright 2005, JBoss Inc., and individual contributors as indicated
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

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;

/**
 * A property editor for {@link java.net.URL}.
 *
 * @author baranowb
 */
public class URLEditor extends PropertyEditorSupport<URL> {

    public URLEditor() {
        super(URL.class);
    }
    public void setAsText(String text) {
        if (BeanUtils.isNull(text)) {
            setValue(null);
            return;
        }

        try {
            setValue(toURL(text));
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Failed to parse to URI!", e);
        }
    }

    public String getAsText() {
        URL u = (URL) getValue();
        if (u == null) {
            return null;
        }
        String stringValue = u.toString();
        if (stringValue.startsWith("file")) {
            try {
                stringValue = URLDecoder.decode(stringValue,"UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new IllegalArgumentException("Failed to parse to URL!", e);
            }
        }
        return stringValue;

    }

    public static URL toURL(String urlspec) throws MalformedURLException {
        urlspec = urlspec.trim();

        URL url;

        try {
            url = new URL(urlspec);
            if (url.getProtocol().equals("file")) {
                url = makeURLFromFilespec(url.getFile());
            }
        } catch (Exception e) {
            // make sure we have a absolute & canonical file url
            try {
                url = makeURLFromFilespec(urlspec);
            } catch (IOException n) {
                //
                // jason: or should we rethrow e?
                //
                throw new MalformedURLException(n.toString());
            }
        }

        return url;
    }

    /** A helper to make a URL from a filespec. */
    private static URL makeURLFromFilespec(final String filespec) throws IOException {
        String decoded = URLDecoder.decode(filespec, "UTF-8");
        File file = new File(decoded);
        // make sure it is canonical (no ../ and such)
        file = file.getCanonicalFile();

        return file.toURI().toURL();
    }
}
