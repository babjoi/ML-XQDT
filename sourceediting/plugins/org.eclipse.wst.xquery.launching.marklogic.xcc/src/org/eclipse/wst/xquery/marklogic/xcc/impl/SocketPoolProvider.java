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
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.channels.ByteChannel;
import java.nio.channels.SocketChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.wst.xquery.marklogic.io.SocketChannelPool;
import org.eclipse.wst.xquery.marklogic.xcc.Request;
import org.eclipse.wst.xquery.marklogic.xcc.RequestOptions;
import org.eclipse.wst.xquery.marklogic.xcc.Session;
import org.eclipse.wst.xquery.marklogic.xcc.spi.ConnectionErrorAction;
import org.eclipse.wst.xquery.marklogic.xcc.spi.ConnectionProvider;
import org.eclipse.wst.xquery.marklogic.xcc.spi.ServerConnection;
import org.eclipse.wst.xquery.marklogic.xcc.spi.SingleHostAddress;


public class SocketPoolProvider implements ConnectionProvider, SingleHostAddress {
    private static final int DEFAULT_SOCKET_POOL_SIZE = 64;
    private static final int DEFAULT_SOCKET_BUFFER_SIZE = 128 * 1024;
    private static final String POOL_SIZE_PROPERTY = "xcc.socket.pool.max";
    private static final String SOCKET_SEND_BUFFER_PROPERTY = "xcc.socket.sendbuf";
    private static final String SOCKET_RECV_BUFFER_PROPERTY = "xcc.socket.recvbuf";

    private final int poolSize = Integer.getInteger(POOL_SIZE_PROPERTY, DEFAULT_SOCKET_POOL_SIZE).intValue();
    private static final int socketSendBuffSize = Integer.getInteger(SOCKET_SEND_BUFFER_PROPERTY,
            DEFAULT_SOCKET_BUFFER_SIZE).intValue();
    private static final int socketRecvBuffSize = Integer.getInteger(SOCKET_RECV_BUFFER_PROPERTY,
            DEFAULT_SOCKET_BUFFER_SIZE).intValue();
    private final SocketChannelPool<SocketAddress> connectionPool;
    private final SocketAddress address;
    private final Logger logger;

    public SocketPoolProvider(SocketAddress address) {
        logger = Logger.getLogger(ConnectionProvider.class.getName());

        logger.fine("constructing new SocketPoolProvider");

        this.address = address;
        connectionPool = new SocketChannelPool<SocketAddress>(poolSize);
    }

    public SocketPoolProvider(String host, int port) {
        this(new InetSocketAddress(host, port));
    }

    int getPoolSize() {
        return poolSize;
    }

    // -----------------------------------------------------------------
    // Impl of ConnectionProvider interface

    public ServerConnection obtainConnection(Session session, Request request, Logger logger) throws IOException {
        if (getLogger(logger).isLoggable(Level.FINE)) {
            getLogger(logger).fine("obtainConnection for " + address);
        }

        SocketChannel channel = connectionPool.get(address);

        if (channel == null) {
            channel = SocketChannel.open(address);
            Socket socket = channel.socket();

            socket.setSendBufferSize(socketSendBuffSize);
            socket.setReceiveBufferSize(socketRecvBuffSize);
            socket.setTcpNoDelay(true);
            socket.setSoLinger(false, 0);
            socket.setKeepAlive(true);

            if (request != null) {
                RequestOptions options = request.getEffectiveOptions();
                int timeout = options.getTimeoutMillis();

                if (timeout >= 0) {
                    socket.setSoTimeout(timeout);
                }
            }

            getLogger(logger).fine("  pool empty, created new connection");
        } else {
            getLogger(logger).fine("  using connection from pool");
        }

        return new SimpleConnection(channel, this);
    }

    public void returnConnection(ServerConnection connection, Logger logger) {
        if (getLogger(logger).isLoggable(Level.FINE)) {
            getLogger(logger).fine("returnConnection for " + address + ", expire=" + connection.getTimeoutMillis());
        }

        ByteChannel channel = connection.channel();

        if ((channel == null) || (!(channel instanceof SocketChannel))) {
            getLogger(logger).fine("channel is not eligible for pooling, dropping");
            return;
        }

        SocketChannel socketChannel = (SocketChannel)channel;
        Socket socket = socketChannel.socket();

        if ((!socketChannel.isOpen()) || socket.isInputShutdown() || socket.isOutputShutdown()) {
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

        connectionPool.put(address, (SocketChannel)channel, timeoutTime);
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

        SocketChannel channel;

        while ((channel = connectionPool.get(address)) != null) {
            try {
                channel.close();
            } catch (IOException e) {
                // do nothing
            }
        }
    }

    @Override
    public String toString() {
        return "address=" + address.toString() + ", pool=" + connectionPool.size(address) + "/" + poolSize;
    }

    // --------------------------------------------------------
    // SingleHostAddress implementation

    public InetSocketAddress getAddress() {
        return (InetSocketAddress)((address instanceof InetSocketAddress) ? address : null);
    }

    // --------------------------------------------------------

    private Logger getLogger(Logger clientLogger) {
        return ((clientLogger == null) ? this.logger : clientLogger);
    }
}
