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
package org.eclipse.wst.xquery.marklogic.xcc.impl;

import java.io.IOException;
import java.nio.channels.ByteChannel;

import org.eclipse.wst.xquery.marklogic.xcc.spi.ConnectionProvider;
import org.eclipse.wst.xquery.marklogic.xcc.spi.ServerConnection;


public class SimpleConnection implements ServerConnection {
    private final ByteChannel channel;
    private final ConnectionProvider provider;
    private long timeoutTime = 0;

    public SimpleConnection(ByteChannel channel, ConnectionProvider provider) {
        this.channel = channel;
        this.provider = provider;
    }

    public ByteChannel channel() {
        return channel;
    }

    public ConnectionProvider provider() {
        return provider;
    }

    public long getTimeoutMillis() {
        long millis = timeoutTime - System.currentTimeMillis();

        return (millis < 0) ? 0 : millis;
    }

    /**
     * Set timeout as a number of milliseconds in the future.
     * 
     * @param timeoutMillis
     *            A number of miliseconds.
     * @deprecated Use {@link #setTimeoutTime(long)} instead.
     */
    @Deprecated
    public void setTimeoutMillis(long timeoutMillis) {
        this.timeoutTime = System.currentTimeMillis() + timeoutMillis;
    }

    public long getTimeoutTime() {
        return timeoutTime;
    }

    public void setTimeoutTime(long timeMillis) {
        this.timeoutTime = timeMillis;
    }

    public void close() {
        try {
            channel.close();
        } catch (IOException e) {
            // ignore
        }
    }

    public boolean isOpen() {
        return channel.isOpen();
    }

    @Override
    public String toString() {
        return "SimpleConnection [provider: " + provider.toString() + "]";
    }
}
