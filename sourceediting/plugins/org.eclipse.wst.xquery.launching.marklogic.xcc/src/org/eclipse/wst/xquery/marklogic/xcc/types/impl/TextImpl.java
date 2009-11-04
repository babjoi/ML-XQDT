/*
 * Copyright (c) 2003-2009 Mark Logic Corporation. All rights reserved.
 *
 * This program and the accompanying materials are made available 
 * under the terms of the Eclipse Public License v1.0 which 
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Mark Logic, Inc.
 */
package org.eclipse.wst.xquery.marklogic.xcc.types.impl;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.wst.xquery.marklogic.xcc.types.ValueType;
import org.eclipse.wst.xquery.marklogic.xcc.types.XdmText;
import org.w3c.dom.Node;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;


public class TextImpl extends AbstractStreamableItem implements XdmText {
    public TextImpl(String bodyAsString) {
        super(ValueType.TEXT, bodyAsString);
    }

    public TextImpl(InputStream stream) {
        super(ValueType.TEXT, stream);
    }

    public Text asW3cText(DocumentBuilder docBuilder) {
        return docBuilder.newDocument().createTextNode(asString());
    }

    public Text asW3cText() throws ParserConfigurationException {
        return (asW3cText(DocumentBuilderFactory.newInstance().newDocumentBuilder()));
    }

    public Node asW3cNode(DocumentBuilder docBuilder) throws IOException, SAXException {
        return asW3cText(docBuilder);
    }

    public Node asW3cNode() throws ParserConfigurationException, IOException, SAXException {
        return asW3cText();
    }
}
