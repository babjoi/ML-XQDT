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
import java.net.InetSocketAddress;
import java.nio.channels.ByteChannel;
import java.nio.channels.SocketChannel;
import java.util.logging.Logger;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;

import org.eclipse.wst.xquery.marklogic.io.SslByteChannel;
import org.eclipse.wst.xquery.marklogic.xcc.SecurityOptions;
import org.eclipse.wst.xquery.marklogic.xcc.spi.ConnectionProvider;
import org.eclipse.wst.xquery.marklogic.xcc.spi.ServerConnection;


public class SSLConnection implements ServerConnection {
    private final ServerConnection plainConn;
    private final ConnectionProvider provider;
    private final ByteChannel sslChannel;

    public SSLConnection(ServerConnection conn, SecurityOptions securityOptions, SSLSocketPoolProvider provider,
            Logger logger) throws IOException {
        if (!(conn.channel() instanceof SocketChannel)) {
            throw new IllegalArgumentException("Underlying channel is not a SocketChannel");
        }

        // SocketChannel socketChannel = (SocketChannel)conn.channel();
        InetSocketAddress addr = provider.getAddress();
        SSLContext context = securityOptions.getSslContext();
        SSLEngine sslEngine = context.createSSLEngine(addr.getHostName(), addr.getPort());

        this.plainConn = conn;
        this.provider = provider;

//		socketChannel.configureBlocking (false);

        sslEngine.setUseClientMode(true);

        String[] protocols = securityOptions.getEnabledProtocols();
        if (protocols != null) {
            sslEngine.setEnabledProtocols(protocols);
        }

        String[] ciphers = securityOptions.getEnabledCipherSuites();
        if (ciphers != null) {
            sslEngine.setEnabledCipherSuites(ciphers);
        }

        sslChannel = new SslByteChannel(plainConn.channel(), sslEngine, logger);
    }

    public ByteChannel channel() {
        return sslChannel;
    }

    public ConnectionProvider provider() {
        return provider;
    }

    public long getTimeoutMillis() {
        return plainConn.getTimeoutMillis();
    }

    /**
     * @param timeoutMillis
     *            A duration, in milliseconds.
     * @deprecated Use {@link #setTimeoutTime(long)} instead.
     */
    @Deprecated
    public void setTimeoutMillis(long timeoutMillis) {
        plainConn.setTimeoutTime(timeoutMillis);
    }

    public long getTimeoutTime() {
        return plainConn.getTimeoutTime();
    }

    public void setTimeoutTime(long timeMillis) {
        plainConn.setTimeoutTime(timeMillis);
    }

    public void close() {
        try {
            sslChannel.close();
        } catch (IOException e) {
            // ignore
        }
    }

    public boolean isOpen() {
        // FIXME: finish this
        return plainConn.isOpen();
    }
}
