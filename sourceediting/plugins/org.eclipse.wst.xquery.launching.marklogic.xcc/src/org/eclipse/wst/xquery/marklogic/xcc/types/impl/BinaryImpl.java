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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.wst.xquery.marklogic.io.IOHelper;
import org.eclipse.wst.xquery.marklogic.xcc.types.ValueType;
import org.eclipse.wst.xquery.marklogic.xcc.types.XdmBinary;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;


public class BinaryImpl extends AbstractItem implements XdmBinary, StreamableItem {
    private InputStream stream;
    private byte[] bytes = null;

    public BinaryImpl(InputStream bodyStream, boolean cache) {
        super(ValueType.BINARY);

        this.stream = bodyStream;

        if (cache) {
            asBinaryData(); // sucks the data off the stream and buffers it
        }
    }

    // -----------------------------------------------------
    // StreamableItem

    public boolean isFetchable() {
        return ((bytes != null) || (stream != null));
    }

    public void invalidate() {
        bytes = null;

        if (stream != null) {
            try {
                //noinspection ResultOfMethodCallIgnored
                stream.skip(Long.MAX_VALUE);
                stream.close();
            } catch (IOException e) {
                // do nothing, may have been closed already
            }

            stream = null;
        }
    }

    // -----------------------------------------------------

    public boolean isCached() {
        return (bytes != null);
    }

    public Reader asReader() {
        return new InputStreamReader(asInputStream());
    }

    public InputStream asInputStream() {
        if (bytes != null) {
            return new ByteArrayInputStream(bytes);
        }

        InputStream tmp = stream;

        stream = null;

        return (tmp);
    }

    public String asString() {
        try {
            return new String(asBinaryData(), "UFT-8");
        } catch (UnsupportedEncodingException e) {
            return new String(asBinaryData());
        }
    }

    public byte[] asBinaryData() {
        if (bytes != null) {
            return bytes.clone();
        }

        if (stream == null) {
            throw new IllegalStateException("stream data has already been consumed");
        }

        try {
            bytes = IOHelper.byteArrayFromStream(stream);
        } catch (IOException e) {
            throw new RuntimeException("IOException buffering binary data", e);
        }

        stream = null;

        return bytes.clone();
    }

    public Node asW3cNode(DocumentBuilder docBuilder) throws IOException, SAXException {
        throw new UnsupportedOperationException("binary() cannot be converted to a W3C Node");
    }

    public Node asW3cNode() throws ParserConfigurationException, IOException, SAXException {
        return asW3cNode(null);
    }
}
