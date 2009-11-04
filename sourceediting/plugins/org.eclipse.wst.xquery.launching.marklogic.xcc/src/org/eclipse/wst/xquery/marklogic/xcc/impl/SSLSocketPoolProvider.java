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
import java.net.SocketAddress;
import java.nio.channels.ByteChannel;
import java.nio.channels.SocketChannel;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.wst.xquery.marklogic.io.GenericResourcePool;
import org.eclipse.wst.xquery.marklogic.io.SslByteChannel;
import org.eclipse.wst.xquery.marklogic.xcc.Request;
import org.eclipse.wst.xquery.marklogic.xcc.SecurityOptions;
import org.eclipse.wst.xquery.marklogic.xcc.Session;
import org.eclipse.wst.xquery.marklogic.xcc.spi.ConnectionErrorAction;
import org.eclipse.wst.xquery.marklogic.xcc.spi.ConnectionProvider;
import org.eclipse.wst.xquery.marklogic.xcc.spi.ServerConnection;
import org.eclipse.wst.xquery.marklogic.xcc.spi.SingleHostAddress;


public class SSLSocketPoolProvider implements ConnectionProvider, SingleHostAddress {
    private final SocketAddress address;
    private final SecurityOptions securityOptions;
    private final SocketPoolProvider socketProvider;
    private final SSLConnectionResourcePool sslPool;
    private final Logger logger;

    public SSLSocketPoolProvider(SocketAddress address, SecurityOptions options) throws NoSuchAlgorithmException,
            KeyManagementException {
        logger = Logger.getLogger(ConnectionProvider.class.getName());

        logger.fine("constructing new SSLSocketPoolProvider");

        this.address = address;
        this.socketProvider = new SocketPoolProvider(address);
        this.securityOptions = options;

        sslPool = new SSLConnectionResourcePool(socketProvider.getPoolSize());
    }

    // -----------------------------------------------------------
    // Impl of SingleHostAddress interface

    public InetSocketAddress getAddress() {
        return (InetSocketAddress)((address instanceof InetSocketAddress) ? address : null);
    }

    // -----------------------------------------------------------
    // Impl of ConnectionProvider interface

    public ServerConnection obtainConnection(Session session, Request request, Logger logger) throws IOException {
        ServerConnection conn = sslPool.get(address);

        if (conn != null) {
            return conn;
        }

        conn = socketProvider.obtainConnection(session, request, logger);

        return new SSLConnection(conn, securityOptions, this, logger);
    }

    public void returnConnection(ServerConnection connection, Logger logger) {
        if (getLogger(logger).isLoggable(Level.FINE)) {
            getLogger(logger).fine("returnConnection for " + address + ", expire=" + connection.getTimeoutMillis());
        }

        ByteChannel channel = connection.channel();

        if ((channel == null) || (!(channel instanceof SslByteChannel))) {
            getLogger(logger).fine("channel is not eligible for pooling, dropping");
            try {
                channel.close();
            } catch (IOException e) {
                getLogger(logger).fine("unable to close channel");
            }
            return;
        }

        SslByteChannel socketChannel = (SslByteChannel)channel;

        if (!socketChannel.isOpen()) {
            getLogger(logger).fine("channel has been closed, dropping");
            return;
        }

        long timeoutMillis = connection.getTimeoutMillis();

        if (timeoutMillis <= 0) {
            getLogger(logger).fine("channel has already expired, closing");

            connection.close();

            return;
        }

        long timeoutTime = connection.getTimeoutTime();

        if (getLogger(logger).isLoggable(Level.FINE)) {
            getLogger(logger).fine("returning socket to pool (" + address + "), timeout time=" + timeoutTime);
        }

        sslPool.put(address, connection, timeoutTime);
    }

    public ConnectionErrorAction returnErrorConnection(ServerConnection connection, Throwable exception, Logger logger) {
        getLogger(logger).fine("error return: " + exception);

        ByteChannel channel = connection.channel();

        if (channel != null) {
            if (channel.isOpen()) {
                try {
                    channel.close();
                } catch (IOException e) {
                    // do nothing, don't care anymore
                }
            } else {
                getLogger(logger).warning("returned error connection is closed, retrying");

                return (ConnectionErrorAction.RETRY);
            }
        }

        getLogger(logger).fine("returning FAIL action");

        return ConnectionErrorAction.FAIL;
    }

    public void shutdown(Logger logger) {
        getLogger(logger).fine("shutting down socket pool provider");

        ServerConnection conn;

        while ((conn = sslPool.get(address)) != null) {
            SocketChannel channel = (SocketChannel)conn.channel();

            try {
                channel.close();
            } catch (IOException e) {
                // do nothing
            }
        }

        socketProvider.shutdown(logger);
    }

    // ---------------------------------------------------------------

    @Override
    public String toString() {
        // TODO: Add more SSL info here?
        return "SSLconn address=" + address.toString() + ", pool=" + sslPool.size(address) + "/"
                + socketProvider.getPoolSize();
    }

    // ---------------------------------------------------------------

    private static class SSLConnectionResourcePool extends GenericResourcePool<SocketAddress, ServerConnection> {
        SSLConnectionResourcePool(int limit) {
            super(limit);
        }

        @Override
        protected void itemExpired(PoolItem item) {
            ServerConnection conn = item.getValue();
            conn.close();
        }
    }

    // --------------------------------------------------------

    private Logger getLogger(Logger clientLogger) {
        return ((clientLogger == null) ? this.logger : clientLogger);
    }
}
