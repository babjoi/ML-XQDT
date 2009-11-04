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
package org.eclipse.wst.xquery.marklogic.xcc;

import java.util.logging.Logger;

/**
 * <p>
 * A ContentSource object describes a content source (MarkLogic Server instance) and serves as a
 * factory that creates {@link Session} objects. Instances of ContentSource may be obtained from a
 * JNDI lookup service or from one of the static factory methods on the {@link ContentSourceFactory}
 * class.
 * </p>
 * <p>
 * See the {@link #getDefaultLogger()} method for information about configuring logging behavior.
 * </p>
 * 
 * @see #getDefaultLogger()
 * @see ContentSourceFactory
 * @see Session
 */
public interface ContentSource {
    /**
     * Attempts to establish a {@link Session} with the default contentbase for this ContentSource.
     * Login credentials are the defaults established when the instance was created or bound to the
     * JNDI service.
     * 
     * @return A {@link Session} instance.
     * @throws IllegalStateException
     *             If this ContentSource was created without specifying user/password credentials.
     */
    Session newSession();

    /**
     * Attempts to establish a {@link Session} with the specified contentbase on the server
     * represented by this ContentSource.
     * 
     * @param contentbaseId
     *            A contentbase name or numeric ID.
     * @return A {@link Session} instance.
     * @throws IllegalStateException
     *             If this ContentSource was created without specifying default user credentials.
     */
    Session newSession(String contentbaseId);

    /**
     * Attempts to establish a {@link Session} with the default contentbase for this ContentSource
     * using the provided login credentials.
     * 
     * @param userName
     *            The user name to connect as.
     * @param password
     *            The password associated with the user name.
     * @return A {@link Session} instance.
     */
    Session newSession(String userName, String password);

    /**
     * Attempts to establish a {@link Session} with the specified contentbase on the server
     * represented by this ContentSource, using the provided user credentials.
     * 
     * @param userName
     *            The user name to connect as.
     * @param password
     *            The password associated with the user name.
     * @param contentbaseId
     *            A contentbase name or numeric ID.
     * @return A {@link Session} instance.
     */
    Session newSession(String userName, String password, String contentbaseId);

    /**
     * <p>
     * Returns the current Logger to which log messages will be sent. If not overridden with
     * {@link #setDefaultLogger(Logger)}, an implementation default logger is returned. This Logger
     * will be inherited by {@link Session} instances created from this ContentSource. The
     * {@link Logger} for individual {@link Session}s can be overridden with the
     * {@link Session#setLogger(java.util.logging.Logger)} method.
     * </p>
     * <p>
     * The name of the implementation default logger is <code>org.eclipse.wst.xquery.marklogic.xcc</code>. This is the
     * name which should be used in a logging properties file (see
     * {@link java.util.logging.LogManager}) to customize the logger.
     * </p>
     * <p>
     * XCC includes a bundled properties file that augments the default JVM logging properties. This
     * file, xcc.logging.properties, is read from the classpath when the first ContentSource
     * instance is created. The properties file in xcc.jar <a
     * href="doc-files/xcc.logging.properties.txt">looks like this</a>. If you wish to customize
     * these logging properties, place a copy of this file in your classpath ahead of xcc.jar.
     * </p>
     * <p>
     * <a href="doc-files/xcc.logging.properties.txt">Default logging properties file in
     * xcc.jar</a>.
     * </p>
     * 
     * @return The currently set Logger instance, or a default created by the implementation.
     */
    Logger getDefaultLogger();

    // TODO: Provide an example/solution for using log4J loggers.
    /**
     * <p>
     * Set the default java.util.Logger instance which will be inherited by new {@link Session}
     * instances.
     * <p>
     * 
     * @param logger
     *            An instance of java.util.logging.Logger
     */
    void setDefaultLogger(Logger logger);
}
