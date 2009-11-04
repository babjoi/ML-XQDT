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

public interface MultipartSplitter {
    public static final int DEF_BUFFER_SIZE = 16 * 1024;

    boolean hasNext() throws IOException;

    void next() throws IOException;

    int read(byte[] buffer, int offset, int length) throws IOException;

    void close() throws IOException;
}
