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
