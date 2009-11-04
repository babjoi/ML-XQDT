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
import org.eclipse.wst.xquery.marklogic.xcc.types.XdmElement;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;


public class ElementImpl extends AbstractStreamableNodeItem implements XdmElement {
    public ElementImpl(String value) {
        super(ValueType.ELEMENT, value);
    }

    public ElementImpl(InputStream stream) {
        super(ValueType.ELEMENT, stream);
    }

    public Element asW3cElement(DocumentBuilder docBuilder) throws IOException, SAXException {
        return asW3cDocument(docBuilder).getDocumentElement();
    }

    public Element asW3cElement() throws ParserConfigurationException, IOException, SAXException {
        return asW3cElement(DocumentBuilderFactory.newInstance().newDocumentBuilder());
    }

    public Document asW3cDocument(DocumentBuilder docBuilder) throws IOException, SAXException {
        return docBuilder.parse(asInputStream());
    }

    public Document asW3cDocument() throws ParserConfigurationException, IOException, SAXException {
        return asW3cDocument(DocumentBuilderFactory.newInstance().newDocumentBuilder());
    }
}
