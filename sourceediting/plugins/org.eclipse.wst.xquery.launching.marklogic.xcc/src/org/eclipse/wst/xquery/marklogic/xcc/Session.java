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

import java.math.BigInteger;
import java.net.URI;
import java.util.logging.Logger;

import org.eclipse.wst.xquery.marklogic.xcc.exceptions.RequestException;


/**
 * <p>
 * A Session object represents a conversation with a contentbase (database) on a MarkLogic Server
 * instance ({@link ContentSource}) and holds state information related to that conversation.
 * Connections to the server are created and released as needed and are automatically pooled.
 * </p>
 * <p>
 * Sessions are created by invoking one of the {@link ContentSource#newSession()} factory methods.
 * </p>
 */
public interface Session {
    /**
     * Obtain a reference to the {@link ContentSource} object from which this Session instance was
     * obtained.
     * 
     * @return The {@link ContentSource} from which this session was instantiated.
     */
    ContentSource getContentSource();

    /**
     * Returns the user identity associated with this Session.
     * 
     * @return The user identity as a String.
     */
    UserCredentials getUserCredentials();

    /**
     * <p>
     * Return the contentbase name provided when this Session was created, if any. Note that this is
     * the name given, if any, when the Session and/or {@link ContentSource} was created. If no
     * explicit name was given then a default was used and this method will return null. To
     * determine the actual name of the contentbase associated with a Session, call
     * {@link org.eclipse.wst.xquery.marklogic.xcc.ContentbaseMetaData#getContentBaseName()}.
     * </p>
     * <p>
     * For example:
     * </p>
     * <p class="codesample">
     * String cbname = session.getContentbaseMetaData().getContentBaseName();
     * </p>
     * <p>
     * The above code makes a round-trip call to the server. This method is a convenience that
     * returns a locally stored {@link String}, or null.
     * </p>
     * 
     * @return The contentbase name stored in the Session, or null.
     * @see ContentSourceFactory#newContentSource(java.net.URI)
     * @see ContentSourceFactory#newContentSource(String, int, String, String, String)
     * @see ContentSource#newSession(String)
     * @see ContentSource#newSession(String, String, String)
     * @see ContentbaseMetaData
     * @see #getContentbaseMetaData()
     */
    String getContentBaseName();

    /**
     * <p>
     * Set auto-commit to the given value (initial value is true).
     * </p>
     * <p>
     * <strong>NOTE:</strong> If multi-request transactions are not supported by the server,
     * attempting to set auto-commit to false may cause an exception to be thrown here.
     * </p>
     * 
     * @param newValue
     *            True to enable auto-commit, false to enable explicit transaction demarcation with
     *            {@link #commit()} and {@link #rollback()}.
     * @throws UnsupportedOperationException
     *             If the server represented by this Session does not support the requested
     *             auto-commit setting.
     */
    void setAutoCommit(boolean newValue);

    /**
     * Get the current value of auto-commit.
     * 
     * @return The current auto-commit setting.
     */
    boolean getAutoCommit();

    /**
     * Commit the current transaction. If no operations are pending, nothing is done. Calling
     * commit() is only meaningful if {@link #getAutoCommit()} returns false. New Session objects
     * are created with auto-commit set to true.
     * 
     * @see #setAutoCommit(boolean)
     */
    void commit();

    /**
     * Rollback (undo) any pending changes since the last commit or rollback. If no operations are
     * pending, nothing is done. Calling rollback() is only meaningful if {@link #getAutoCommit()}
     * returns false. New Session objects are created with auto-commit set to true.
     * 
     * @see #setAutoCommit(boolean)
     */
    void rollback();

    /**
     * Shutdown and invalidate this Session and release any resources it may be holding. Any
     * currently open {@link ResultSequence} objects created from this Session will also be
     * invalidated and closed.
     */
    void close();

    /**
     * True if this Session object is closed.
     * 
     * @return true if this Session has been closed.
     */
    boolean isClosed();

    /**
     * Submit a {@link Request} to the contentbase and return the (possibly empty)
     * {@link ResultSequence}.
     * 
     * @param request
     *            A {@link Request} instance (either {@link ModuleInvoke} or {@link AdhocQuery} that
     *            specifies the query to be run, associated options and variables.
     * @return A {@link ResultSequence} instance encapsulating the result of the query execution.
     * @throws IllegalStateException
     *             If this Session has been closed.
     * @throws RequestException
     *             If there is a problem communicating with the server.
     */
    ResultSequence submitRequest(Request request) throws RequestException;

    /**
     * Create a new {@link AdhocQuery} object and initialize it with the given query string and
     * {@link RequestOptions} object.
     * 
     * @param queryText
     *            The ad-hoc XQuery code to be evaluated.
     * @param options
     *            An instance of {@link RequestOptions} to be set on the return {@link AdhocQuery}
     *            object. This can be overridden later.
     * @return An initialized instance of {@link AdhocQuery}.
     */
    AdhocQuery newAdhocQuery(String queryText, RequestOptions options);

    /**
     * Create a new {@link AdhocQuery} object and initialize it with the given query string.
     * 
     * @param queryText
     *            The ad-hoc XQuery code to be evaluated.
     * @return An initialized instance of {@link AdhocQuery}.
     */
    AdhocQuery newAdhocQuery(String queryText);

    /**
     * Create a new {@link ModuleInvoke} object and initialize it with the given module URI and
     * {@link RequestOptions} object.
     * 
     * @param moduleUri
     *            The URI of a module on the server to be invoked.
     * @param options
     *            An instance of {@link RequestOptions} to be set on the returned
     *            {@link ModuleInvoke} object. This can be overridden later.
     * @return An initialized instance of {@link ModuleInvoke}.
     * @see ModuleInvoke
     */
    ModuleInvoke newModuleInvoke(String moduleUri, RequestOptions options);

    /**
     * Create a new {@link ModuleInvoke} object and initialize it with the given module URI.
     * 
     * @param moduleUri
     *            The URI of a module on the server to be invoked.
     * @return An initialized instance of {@link ModuleInvoke}.
     * @see ModuleInvoke
     */
    ModuleInvoke newModuleInvoke(String moduleUri);

    /**
     * Create a new {@link ModuleSpawn} object and initialize it with the given module URI and
     * {@link RequestOptions} object.
     * 
     * @param moduleUri
     *            The URI of a module on the server to be invoked.
     * @param options
     *            An instance of {@link org.eclipse.wst.xquery.marklogic.xcc.RequestOptions} to be set on the returned
     *            {@link org.eclipse.wst.xquery.marklogic.xcc.ModuleSpawn} object. This can be overridden later.
     * @return An initialized instance of {@link ModuleSpawn}.
     * @see ModuleInvoke
     */
    ModuleSpawn newModuleSpawn(String moduleUri, RequestOptions options);

    /**
     * Create a new {@link ModuleSpawn} object and initialize it with the given module URI.
     * 
     * @param moduleUri
     *            The URI of a module on the server to be invoked.
     * @return An initialized instance of {@link ModuleSpawn}.
     * @see ModuleInvoke
     */
    ModuleSpawn newModuleSpawn(String moduleUri);

    /**
     * <p>
     * Insert the given {@link Content} into the contentbase. This is equivalent to calling
     * {@link #insertContent(Content[])} with an array length of one. Upon return, the content will
     * have been inserted and committed, unless auto-commit is false (note that in the current
     * release auto-commit may not be set to false).
     * </p>
     * <p>
     * {@link Content} objects that are rewindable will be automatically retried if a recoverable
     * error occurs during transmission to the server. To specify the maximum number of retry
     * attemtps, set an instance of {@link RequestOptions} with the desired value (
     * {@link RequestOptions#setMaxAutoRetry(int)}) using the
     * {@link #setDefaultRequestOptions(RequestOptions)} method prior to invoking this method.
     * </p>
     * <p>
     * The retry/timeout algorithm is different for document insert than for query requests. For
     * queries, a constant delay ocurrs between each retry. For inserts, an exponentially increasing
     * backoff delay is used. Retryable exceptions usually ocurr during document insert when a
     * cluster communication recover is in progress. Inter-try delays increase up to a maximum of
     * about two seconds until the retry count is exhausted. The default first delay is 125
     * milliseconds with a count of 64, which works out to an overall retry interval of about two
     * minutes before giving up.
     * </p>
     * 
     * @param content
     *            A single {@link Content} instance to be inserted in the contentbase.
     * @throws IllegalStateException
     *             If this Session has been closed.
     * @throws RequestException
     *             If there is a problem communicating with the server.
     */
    void insertContent(Content content) throws RequestException;

    /**
     * <p>
     * Insert all the {@link Content} objects in the contentbase as a transactional unit. Upon
     * successful return, all documents will have been committed. If an exception is thrown, none of
     * the documents will have been committed.
     * </p>
     * <p>
     * Note that this atomic commit behavior is true if auto-commit is true. If auto-commit is false
     * the document set will commit or rollback with all other requests in the transaction.
     * </p>
     * <p>
     * Note that multi-statement transactions are not available in the 3.x or 4.x server, so
     * auto-commit will always be true and setAutoCommit(false) will always throw an exception.
     * </p>
     * 
     * @param content
     *            An array of {@link Content} objects that are inserted as a group atomically.
     * @throws IllegalStateException
     *             If this Session has been closed.
     * @see #setAutoCommit(boolean)
     * @throws RequestException
     *             If there is a problem communicating with the server.
     */
    void insertContent(Content[] content) throws RequestException;

    /**
     * Meta-information about the contentbase associated with this Session.
     * 
     * @return An instance of {@link ContentbaseMetaData}.
     */
    ContentbaseMetaData getContentbaseMetaData();

    /**
     * This method accepts an instance of {@link RequestOptions} which acts as the default settings
     * for invocations of {@link #submitRequest(Request)}. These defaults may be overridden by a
     * {@link RequestOptions} instance on individual requests. If a {@link RequestOptions} object is
     * set on both the Session and the {@link Request}, then both are applied with the values of the
     * {@link Request} object taking precedence.
     * 
     * @param options
     *            An instance of {@link RequestOptions}. A value of null indicates that defaults
     *            should be re-applied.
     */
    void setDefaultRequestOptions(RequestOptions options);

    /**
     * Returns the {@link RequestOptions} instance set on this Session object. An instance of
     * {@link RequestOptions} with default settings is created when the {@link Session} is created
     * and whenever you pass null to {@link #setDefaultRequestOptions(RequestOptions)}, so this
     * method always returns a non-null value.
     * 
     * @return An instance of {@link RequestOptions}.
     */
    RequestOptions getDefaultRequestOptions();

    /**
     * Returns an instance of {@link RequestOptions} that represents the effective default request
     * options for this Session (ie, the options that would be applied if no options are applied to
     * a specific {@link Request}. The values in the returned instance reflect the builtin defaults
     * merged with the values in the options set by
     * {@link #setDefaultRequestOptions(RequestOptions)} (if any). The object returned is a copy,
     * making changes to it will not affect option settings for the Session.
     * 
     * @return An instance of {@link RequestOptions}.
     */
    RequestOptions getEffectiveRequestOptions();

    /**
     * Issues a query to the server and returns the most recent system commit timestamp. The value
     * returned is as-of the time the query executes on the server. The returned value represents a
     * point-in-time, or snapshot, of the contentbase. This value may be passed to
     * {@link RequestOptions#setEffectivePointInTime(java.math.BigInteger)} to run queries as-of
     * that contentbase state.
     * 
     * @return A {@link java.math.BigInteger} value containing a point-in-time timestamp.
     * @throws RequestException
     *             If there is a problem communicating with the server.
     */
    BigInteger getCurrentServerPointInTime() throws RequestException;

    /**
     * Get the java.util.logging.Logger instance set on this Session. If not explictly set with
     * {@link #setLogger(Logger)}, the Logger inherited from the creating {@link ContentSource} is
     * returned.
     * 
     * @return A Logger instance
     * @see org.eclipse.wst.xquery.marklogic.xcc.ContentSource#getDefaultLogger()
     * @see org.eclipse.wst.xquery.marklogic.xcc.ContentSource#setDefaultLogger(Logger)
     */
    Logger getLogger();

    /**
     * Set the Logger instance to which log messages resulting from operations on this Session
     * should be written.
     * 
     * @param logger
     *            An instance of java.util.Logger
     */
    void setLogger(Logger logger);

    /**
     * Attach, or clear, an opaque user-provided object to this Session. This object is not used or
     * examined in any way by XCC. Client code may use this hook in any way it sees fit to associate
     * state with the Session.
     * 
     * @param userObject
     *            An opaque {@link Object} that may later be retrieved with {@link #getUserObject()}
     *            . A value of null is acceptable.
     */
    void setUserObject(Object userObject);

    /**
     * Fetch the current user object set for this Session.
     * 
     * @return Return the object reference previously passed to {@link #setUserObject(Object)}, or
     *         null.
     */
    Object getUserObject();

    /**
     * <p>
     * Return a URI that describes connection information for this Session, if available. Connection
     * information is dependent on the {@link org.eclipse.wst.xquery.marklogic.xcc.spi.ConnectionProvider} encapsulated
     * in the {@link org.eclipse.wst.xquery.marklogic.xcc.ContentSource} that created this Session. In most cases, the
     * provider will be the built-in socket-based provider included with XCC. If a custom provider
     * is in use and does not implement point-to-point socket connections, then this method will
     * return null.
     * </p>
     * <p>
     * Otherwise, a {@link java.net.URI} instance is returned that contains information describing
     * the connections that will be made to the server. For security reasons, the password provided
     * when the {@link org.eclipse.wst.xquery.marklogic.xcc.ContentSource} or Session was created is not returned. The
     * password will be masked as "xxxx". The returned {@link java.net.URI} may be used to
     * instantiate a new {@link org.eclipse.wst.xquery.marklogic.xcc.ContentSource}, but the real password will need to
     * be provided when calling {@link org.eclipse.wst.xquery.marklogic.xcc.ContentSource#newSession(String, String)}.
     * </p>
     * 
     * @return An instance of {@link java.net.URI}, or null if the underlying
     *         {@link org.eclipse.wst.xquery.marklogic.xcc.spi.ConnectionProvider} does not implement convetional
     *         point-to-point socket connections, or if a {@link java.net.URI} object cannot be
     *         created from this Session.
     * @since 3.2
     * @see ContentSourceFactory#newContentSource(java.net.URI)
     */
    URI getConnectionUri();
}
