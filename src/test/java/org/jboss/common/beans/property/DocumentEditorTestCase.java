/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2011, Red Hat, Inc., and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
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

import java.io.StringReader;
import java.util.Comparator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

/**
 * @author baranowb
 * 
 */
public class DocumentEditorTestCase extends PropertyEditorTester<Document> {

    @Override
    public String[] getInputData() {
        return new String[] { "<!-- header comment --><doc name='whatever'/><!-- footer comment -->" };
    }

    @Override
    public Object[] getOutputData() throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        StringReader sr = new StringReader(getInputData()[0]);
        InputSource is = new InputSource(sr);
        Document d = db.parse(is);
        return new Object[] { d };
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
