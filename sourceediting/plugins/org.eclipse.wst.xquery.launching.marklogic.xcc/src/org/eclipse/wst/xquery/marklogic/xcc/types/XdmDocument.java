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

import javax.xml.parsers.DocumentBuilder;

/**
 * An XDM value which is a document().
 * 
 * This interface currently does not have any implementations, it is defined for future expansion.
 */
public interface XdmDocument extends XdmNode {
    /**
     * <p>
     * Returns a W3C Document equivalent of this document. Buffers the document item from the server
     * and converts it to a W3C DOM Cocument object. The document is buffered as a String object.
     * Subsequent calls will create a new DOM tree from the same String. The buffered String may
     * also be used by {@link #asString()} and {@link #asInputStream()}.
     * </p>
     * <p>
     * If you are using JDOM and want to create a JDOM Document for this node, do the following:
     * <code>doc = new org.jdom.input.SAXBuilder().build (new StringReader (node.asString()))</code>
     * </p>
     * 
     * @param docBuilder
     *            The javax.xml.parsers.DocumentBuilder object to use to construct the Document. If
     *            null, the default implementation will be used. See the JDK documentation for the
     *            javax.xml.parsers.DocumentBuilderFactory class for details on configuring the
     *            system default builder.
     * @return This item as a W3C element (org.w3c.dom.Element) instance.
     * @throws IllegalStateException
     *             If called after the InputStream has already been consumed.
     * @see #asInputStream()
     * @see #asString()
     * @see #isCached()
     * @see #asW3cNode()
     */
    org.w3c.dom.Document asW3cDocument(DocumentBuilder docBuilder);

    /**
     * This is equivalent to <code>asW3cDocument (null)</code>.
     * 
     * @return This item as a W3C document (org.w3c.dom.Document) instance.
     */
    org.w3c.dom.Document asW3cDocument();
}
