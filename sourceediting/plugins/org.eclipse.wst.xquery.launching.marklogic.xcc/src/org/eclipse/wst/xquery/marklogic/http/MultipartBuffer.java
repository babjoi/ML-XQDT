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
package org.eclipse.wst.xquery.marklogic.http;

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.wst.xquery.marklogic.io.IOHelper;


public class MultipartBuffer {
    private InputStream partInputStream;
    private MultipartSplitter splitter;
    private HttpHeaders headers = new HttpHeaders();

    // multipart boundary characters:
    //  \n--bbbbbbbb\n between parts,
    //  \n--bbbbbbbb--\n following last part

    // ---------------------------------------------------------------

    public MultipartBuffer(MultipartSplitter splitter) {
        this.splitter = splitter;
    }

    public String getHeader(String name) {
        return (headers.getHeaderNormalized(name));
    }

    public void close() throws IOException {
        if (partInputStream != null) {
            partInputStream.close();
            partInputStream = null;
        }

        if (splitter != null) {
            splitter.close();
            splitter = null;
        }
    }

    public boolean isClosed() {
        return (splitter == null);
    }

    public int read(byte[] buf, int off, int len) throws IOException {
        if (partInputStream == null) {
            throw new IllegalStateException("No active part stream");
        }

        return (partInputStream.read(buf, off, len));
    }

    public String getBodyAsString() throws IOException {
        if (partInputStream == null) {
            throw new IllegalStateException("No active part stream");
        }

        return IOHelper.literalStringFromStream(partInputStream);
    }

    /**
     * Get an input stream on the next chunk of the buffer.
     * 
     * @return a java.io.InputStream that reads just the next chunk
     */
    public InputStream nextStream() throws IOException {
        if (!next()) {
            return (null);
        }

        return (partInputStream);
    }

    public boolean next() throws IOException {
        if (!splitter.hasNext()) {
            partInputStream = null;

            return false;
        }

        splitter.next();

        partInputStream = new PartInputStream(splitter);

        headers.clear();
        headers.parsePlainHeaders(partInputStream);

        return (true);
    }

    public boolean hasNext() throws IOException {
        return (splitter.hasNext());
    }

//	public void setBufferSize (int size)
//	{
//		splitter.setBufferSize (size);
//	}
//
//	public int getBufferSize()
//	{
//		return splitter.getBufferSize();
//	}

    public InputStream getBodyStream() {
        if (partInputStream == null) {
            throw new IllegalStateException("No active part stream");
        }

        return partInputStream;
    }
}
