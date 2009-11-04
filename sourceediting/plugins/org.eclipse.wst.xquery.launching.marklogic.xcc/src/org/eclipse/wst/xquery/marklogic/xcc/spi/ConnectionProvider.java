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

import java.io.IOException;
import java.util.logging.Logger;

import org.eclipse.wst.xquery.marklogic.xcc.Request;
import org.eclipse.wst.xquery.marklogic.xcc.Session;


/**
 * This interface defines a provider of Sockets for a {@link org.eclipse.wst.xquery.marklogic.xcc.ContentSource}.
 * Policies such as load balancing or failover can be implemented by supplying a custom
 * ConnectionProvider.
 */
public interface ConnectionProvider {
    /**
     * <p>
     * Provide a {@link ServerConnection}) that is open and ready to communicate with the server.
     * </p>
     * <p>
     * Note: The signature of this method changed in the 4.0 release to add the
     * {@link org.eclipse.wst.xquery.marklogic.xcc.Request} parameter. Code ported from earlier XCC releases will need
     * to be modified to add the new parameter.
     * </p>
     * 
     * @param session
     *            The {@link org.eclipse.wst.xquery.marklogic.xcc.Session} requesting a connection.
     * @param request
     *            The {@link org.eclipse.wst.xquery.marklogic.xcc.Request} that is about to be submitted on the
     *            connection.
     * @param logger
     *            A {@link java.util.logging.Logger} instance to use. @return An open, ready to use
     *            {@link ServerConnection} object.
     * @throws java.io.IOException
     *             If an underlying connection cannot be created.
     * @return An initialized {@link org.eclipse.wst.xquery.marklogic.xcc.spi.ServerConnection} instance.
     */
    ServerConnection obtainConnection(Session session, Request request, Logger logger) throws IOException;

    /**
     * Return a {@link ServerConnection} object (obtained from a previous call to
     * {@link #obtainConnection(org.eclipse.wst.xquery.marklogic.xcc.Session,org.eclipse.wst.xquery.marklogic.xcc.Request,java.util.logging.Logger)}
     * ) to the provider, possibly to be pooled and re-used. If the connection has a non-zero
     * timeout value set ({@link org.eclipse.wst.xquery.marklogic.xcc.spi.ServerConnection#setTimeoutMillis(long)}),
     * that value will inform the provider to discard the object after that amount of time has
     * elapsed.
     * 
     * @param connection
     *            A previously obtained {@link ServerConnection} instance.
     * @param logger
     *            A {@link Logger} instance to use.
     */
    void returnConnection(ServerConnection connection, Logger logger);

    /**
     * Return a connection that experienced an error. The provider will indicate the action the
     * client should take.
     * 
     * @param connection
     *            A previously obtained {@link ServerConnection} instance.
     * @param exception
     *            The (possibly null) exception that occurred. The provider may wish to decide which
     *            action to recommend depending on the type of exception.
     * @param logger
     *            A {@link Logger} instance to use.
     * @return An instance of {@link ConnectionErrorAction} instance that advises the client which
     *         action to take.
     */
    ConnectionErrorAction returnErrorConnection(ServerConnection connection, Throwable exception, Logger logger);

    /**
     * Tell the provider to shutdown and release any resources being used. It's possible this method
     * may never be called.
     * 
     * @param logger
     *            A {@link Logger} instance to use.
     */
    void shutdown(Logger logger);
}
