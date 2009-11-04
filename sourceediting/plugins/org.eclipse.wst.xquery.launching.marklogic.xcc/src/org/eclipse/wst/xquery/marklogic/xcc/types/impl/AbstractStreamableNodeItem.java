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

import org.eclipse.wst.xquery.marklogic.xcc.types.ItemType;
import org.eclipse.wst.xquery.marklogic.xcc.types.XdmNode;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;


abstract public class AbstractStreamableNodeItem extends AbstractStreamableItem implements XdmNode {
    public AbstractStreamableNodeItem(ItemType type, String stringVal) {
        super(type, stringVal);
    }

    public AbstractStreamableNodeItem(ItemType type, InputStream stream) {
        super(type, stream);
    }

    // FIXME: These have not been tested
    public Node asW3cNode(DocumentBuilder docBuilder) throws IOException, SAXException {
        return docBuilder.parse(asInputStream()).getFirstChild();
    }

    public Node asW3cNode() throws ParserConfigurationException, IOException, SAXException {
        return asW3cNode(DocumentBuilderFactory.newInstance().newDocumentBuilder());
    }
}
