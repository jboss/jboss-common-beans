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
import org.xml.sax.InputSource;

/**
 * @author baranowb
 */
public class DocumentEditorTestCase extends PropertyEditorTester<Document> {

    @Override
    public String[] getInputData() {
        return new String[]{"<!-- header comment --><doc name='whatever'/><!-- footer comment -->"};
    }

    @Override
    public Object[] getOutputData() throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        StringReader sr = new StringReader(getInputData()[0]);
        InputSource is = new InputSource(sr);
        Document d = db.parse(is);
        return new Object[]{d};
    }

    @Override
    public String[] getConvertedToText() {
        return getInputData();
    }

    @Override
    public Comparator<Document> getComparator() {
        return new DocumentComparator();
    }

    @Override
    public Class getType() {
        return Document.class;
    }

    class DocumentComparator implements Comparator<Document> {

        @Override
        public int compare(Document o1, Document o2) {
            o1.normalize();
            o2.normalize();
            return o1.isEqualNode(o2) ? 0 : 1;
        }

    }
}
