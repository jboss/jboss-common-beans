/*
 * Copyright Red Hat
 * SPDX-License-Identifier: Apache-2.0
 */
package org.jboss.common.beans.property;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;

/**
 * A property editor for {@link java.net.URI}.
 *
 * @author baranowb
 */
public class URIEditor extends PropertyEditorSupport<URI> {

    public URIEditor() {
        super(URI.class);
    }

    public void setAsText(String text) {
        if (BeanUtils.isNull(text)) {
            setValue(null);
            return;
        }

        try {
            setValue(toURI(text));
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Failed to parse to URI!", e);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException("Failed to parse to URI!", e);
        }

    }

    public String getAsText() {
        URI u = (URI) getValue();
        if (u == null) {
            return null;
        }
        String stringValue = u.toString();
        if (stringValue.startsWith("file")) {
            try {
                stringValue = URLDecoder.decode(stringValue,"UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new IllegalArgumentException("Failed to parse to URI!", e);
            }
        }
        return stringValue;

    }

    public static URI toURI(String urispec) throws URISyntaxException, UnsupportedEncodingException {
        urispec = urispec.trim();

        URI uri;

        if (urispec.startsWith("file:")) {
            uri = makeURIFromFilespec(urispec.substring(5));
        } else {
            uri = new URI(urispec);
        }

        return uri;
    }

    private static URI makeURIFromFilespec(final String filespec) throws UnsupportedEncodingException {
        // make sure the file is absolute & canonical file url
        String decoded = URLDecoder.decode(filespec, "UTF-8");
        File file = new File(decoded);

        return file.toURI();
    }
}
