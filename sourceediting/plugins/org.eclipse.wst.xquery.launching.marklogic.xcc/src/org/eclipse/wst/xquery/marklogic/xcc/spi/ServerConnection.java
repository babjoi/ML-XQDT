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
package org.eclipse.wst.xquery.marklogic.xcc.spi;

import java.nio.channels.ByteChannel;

/**
 * This class encapsulates a server connection as returned from a {@link ConnectionProvider}
 * instance.
 */
public interface ServerConnection {
    /**
     * The channel to be used for server communication.
     * 
     * @return An open, bi-directional {@link ByteChannel} connected to a server.
     */
    ByteChannel channel();

    /**
     * The {@link ConnectionProvider} to which this connection should be returned.
     * 
     * @return An instance of {@link ConnectionProvider}.
     */
    ConnectionProvider provider();

    /**
     * <p>
     * A timeout value, in milliseconds, which is a hint to the provider upon return to indicate how
     * long this connection should be considered valid. Providers are expected to set this value to
     * zero when giving out a connection via
     * {@link ConnectionProvider#obtainConnection(org.eclipse.wst.xquery.marklogic.xcc.Session,org.eclipse.wst.xquery.marklogic.xcc.Request,java.util.logging.Logger)}
     * .
     * </p>
     * <p>
     * As of 3.2-6 and later, this method returns the number of milliseconds until this connection
     * will expire. If the connection is already expired, then zero is returned.
     * </p>
     * 
     * @return A duration, in milliseconds, or zero if already expired.
     */
    long getTimeoutMillis();

    /**
     * Set the suggested timeout value for this connection. This value is only used by the provider
     * when a connection is passed to
     * {@link ConnectionProvider#returnConnection(ServerConnection, java.util.logging.Logger)}.
     * 
     * @param timeoutMillis
     *            A duration, in milliseconds.
     * @deprecated Use {@link #setTimeoutTime(long)} instead.
     */
    @Deprecated
    void setTimeoutMillis(long timeoutMillis);

    /**
     * Get the exact time, in milliseconds, at which this connection is considered to be expired.
     * 
     * @return A time in milliseconds, as for {@link System#currentTimeMillis()}
     * @since 3.2-6
     */
    long getTimeoutTime();

    /**
     * Set the exact time, as milliseconds since the epoch, at which this connection is to be
     * considered expired.
     * 
     * @param timeMillis
     *            A time in milliseconds, as for {@link System#currentTimeMillis()}
     * @since 3.2-6
     */
    void setTimeoutTime(long timeMillis);

    /**
     * Close this server connection. If a connection is closed when returned to the provider, it
     * will be silently dropped.
     */
    void close();

    /**
     * Indicates whether the underlying connection channel is open.
     * 
     * @return true is open, false if closed.
     */
    boolean isOpen();
}
