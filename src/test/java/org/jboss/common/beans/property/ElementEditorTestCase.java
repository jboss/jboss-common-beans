/*
 * Copyright Red Hat
 * SPDX-License-Identifier: Apache-2.0
 */

package org.jboss.common.beans.property;

import java.io.StringReader;
import java.util.Comparator;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 * @author baranowb
 */
public class ElementEditorTestCase extends PropertyEditorTester<Element> {

    @Override
    public String[] getInputData() {
        return new String[]{"<element>text</element>"};
    }

    @Override
    public Object[] getOutputData() throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        StringReader sr = new StringReader(getInputData()[0]);
        InputSource is = new InputSource(sr);
        Document d = db.parse(is);
        // annoying...
        NodeList list = d.getChildNodes();
        Element e = null;
        for (int index = 0; list.getLength() > index; index++) {
            Node n = list.item(index);
            if ((n instanceof Element) && n.getNodeName().equals("element")) {
                e = (Element) n;
                break;
            }
        }
        return new Object[]{e};
    }

    @Override
    public String[] getConvertedToText() {
        return getInputData();
    }

    @Override
    public Comparator<Element> getComparator() {
        return new ElementComparator();
    }

    @Override
    public Class getType() {
        return Element.class;
    }

    class ElementComparator implements Comparator<Element> {

        @Override
        public int compare(Element o1, Element o2) {
            o1.normalize();
            o2.normalize();
            return o1.isEqualNode(o2) ? 0 : 1;
        }

    }
}
