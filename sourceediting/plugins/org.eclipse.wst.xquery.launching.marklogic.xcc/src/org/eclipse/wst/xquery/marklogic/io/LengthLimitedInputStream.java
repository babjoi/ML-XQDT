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
package org.eclipse.wst.xquery.marklogic.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * An InputStream decorator that returns EOF after a specified number of bytes have been read from
 * the underlying stream. If EOF is encountered on the underlying stream before the read limit is
 * hit, EOF is returned at that point.
 */
public class LengthLimitedInputStream extends InputStream {
    private InputStream stream;
    private boolean limitReached = false;
    private long limit;
    private long readCount = 0;

    public LengthLimitedInputStream(InputStream stream, long limit) {
        this.stream = stream;
        this.limit = (limit < 0) ? Long.MAX_VALUE : limit;
    }

    @Override
    public int read() throws IOException {
        if (checkLimit()) {
            return (-1);
        }

        int rc = stream.read();

        if (rc != -1) {
            advanceReadCount(1);
        }

        return (rc);
    }

    @Override
    public int read(byte buffer[], int off, int len) throws IOException {
        if (checkLimit()) {
            return (-1);
        }

        int toRead = (int)maxReadable(len);
        int rc = stream.read(buffer, off, toRead);

        if (rc <= 0) {
            return (rc);
        }

        advanceReadCount(rc);

        return (rc);
    }

    @Override
    public int read(byte buffer[]) throws IOException {
        return read(buffer, 0, buffer.length);
    }

    @Override
    public void close() throws IOException {
        limitReached = true;

        stream.close();
    }

    @Override
    public long skip(long n) throws IOException {
        long toSkip = maxReadable(n);
        long skipped = super.skip(toSkip);

        advanceReadCount(skipped);

        return (skipped);
    }

    // ------------------------------------------------------------

    private void advanceReadCount(long n) {
        readCount += n;

        checkLimit();
    }

    private long maxReadable(long n) {
        return (Math.min(n, limit - readCount));
    }

    private boolean checkLimit() {
        if (limitReached) {
            return (true);
        }

        if (readCount == limit) {
            limitReached = true;
        }

        return (limitReached);
    }
}
