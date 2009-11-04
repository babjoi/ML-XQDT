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
package org.eclipse.wst.xquery.marklogic.xcc.types;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

/**
 * An XDM value which is a node().
 */
public interface XdmNode extends XdmItem {
    /**
     * <p>
     * Buffers the node() item from the server and converts it to a W3C DOM Node object. The node()
     * is first buffered as a String object. Subsequent calls will create a new DOM tree from the
     * buffered String. The buffered String will also be used by subsequent calls to asString() and
     * asInputStream().
     * </p>
     * <p>
     * If you are using JDOM and want to create a JDOM Document for this node, do the following:
     * <code>doc = new org.jdom.input.SAXBuilder().build (new StringReader (node.asString()))</code>
     * </p>
     * 
     * @param docBuilder
     *            The javax.xml.parsers.DocumentBuilder object to use to construct the Node. If
     *            null, the default implementation will be used. See the JDK documentation for the
     *            javax.xml.parsers.DocumentBuilderFactory class for details on configuring the
     *            system default builder.
     * @return This item as a W3C node (org.w3c.dom.Node) instance.
     * @throws IllegalStateException
     *             If called after the InputStream has already been consumed.
     * @see #asInputStream()
     * @see #asString()
     * @see #isCached()
     */
    org.w3c.dom.Node asW3cNode(DocumentBuilder docBuilder) throws IOException, SAXException;

    /**
     * This is equivalent to <code>asW3cNode (null);</code>
     * 
     * @return This item as a W3C node (org.w3c.dom.Node) instance.
     * @see #asW3cNode(javax.xml.parsers.DocumentBuilder)
     */
    org.w3c.dom.Node asW3cNode() throws ParserConfigurationException, IOException, SAXException;
}
