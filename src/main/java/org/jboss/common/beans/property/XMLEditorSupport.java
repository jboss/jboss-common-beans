/*
 * Copyright Red Hat
 * SPDX-License-Identifier: Apache-2.0
 */
package org.jboss.common.beans.property;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * A property editor for {@link org.w3c.dom.Document}.
 *
 * @author <a href="mailto:eross@noderunner.net">Elias Ross</a>
 * @author baranowb
 */
public abstract class XMLEditorSupport<T> extends PropertyEditorSupport<T> {

    /**
     * @param type
     */
    public XMLEditorSupport(Class<T> type) {
        super(type);
        // TODO Auto-generated constructor stub
    }

    /**
     * Returns the property as a String.
     */
    @Override
    public String getAsText() {
        if (getValue() == null) {
            return null;
        }
        return DOMWriter.printNode((Node) getValue(), false);
    }

    protected Document getAsDocument(String text) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            StringReader sr = new StringReader(text);
            InputSource is = new InputSource(sr);
            Document d = db.parse(is);
            return d;
        } catch (ParserConfigurationException e) {
            throw new IllegalArgumentException("Failed to parse to Document!", e);
        } catch (SAXException e) {
            throw new IllegalArgumentException("Failed to parse to Document!", e);
        } catch (IOException e) {
            throw new IllegalArgumentException("Failed to parse to Document!", e);
        }
    }
}
