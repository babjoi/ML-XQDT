/*******************************************************************************
 * Copyright (c) 2008 Dominik Schadow - http://www.xml-sicherheit.de
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Dominik Schadow - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.xml.security.core.canonicalize;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.xml.security.c14n.CanonicalizationException;
import org.apache.xml.security.c14n.Canonicalizer;
import org.apache.xml.security.c14n.InvalidCanonicalizerException;
import org.xml.sax.SAXException;

/**
 * <p>Does the canonicalization for the selected XML document with or without comments. This decision
 * is based on the clicked menu item. Depending on the preference settings a new editor with the
 * canonicalized version is opened or the current editor content (or file) is updated directly.</p>
 *
 * @author Dominik Schadow
 * @version 0.5.0
 */
public class Canonicalization {
    /**
     * Canonicalizes the selected XML document with the determined canonicalization method
     * identified by the given URI.
     *
     * @param input The XML document to canonicalize as InputStream
     * @param uri Defines the canonicalization URI (with or without comments)
     * @return Byte Array of XML data
     * @throws InvalidCanonicalizerException Invalid canonicalizer
     * @throws CanonicalizationException Canonicalization exception
     * @throws IOException during document preparation
     * @throws SAXException during document generation
     * @throws ParserConfigurationException during document builder factory initialization
     */
    public byte[] canonicalize(final InputStream input, final String uri)
        throws InvalidCanonicalizerException, CanonicalizationException,
        SAXException, IOException, ParserConfigurationException {
        byte[] content = new byte[input.available()];
        input.read(content);

        Canonicalizer c14n = Canonicalizer.getInstance(uri);
        return c14n.canonicalize(content);
    }
}
