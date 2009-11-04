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

public class NullPartSplitter implements MultipartSplitter {
    public boolean hasNext() throws IOException {
        return false;
    }

    public void next() throws IOException {
        throw new IOException("Empty result");
    }

    public int read(byte[] buffer, int offset, int length) throws IOException {
        return -1;
    }

    public void close() {
        // do nothing
    }

    public void setBufferSize(int size) {
        // do nothing
    }

    public int getBufferSize() {
        return 0;
    }
}
