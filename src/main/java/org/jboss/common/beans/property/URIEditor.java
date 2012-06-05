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
