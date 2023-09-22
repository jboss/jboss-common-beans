/*
 * Copyright Red Hat
 * SPDX-License-Identifier: Apache-2.0
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
