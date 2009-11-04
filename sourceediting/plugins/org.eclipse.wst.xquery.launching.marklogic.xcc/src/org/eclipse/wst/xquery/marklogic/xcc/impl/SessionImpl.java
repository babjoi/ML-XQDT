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

import java.math.BigInteger;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Logger;

import org.eclipse.wst.xquery.marklogic.xcc.AdhocQuery;
import org.eclipse.wst.xquery.marklogic.xcc.Content;
import org.eclipse.wst.xquery.marklogic.xcc.ContentSource;
import org.eclipse.wst.xquery.marklogic.xcc.ContentbaseMetaData;
import org.eclipse.wst.xquery.marklogic.xcc.ModuleInvoke;
import org.eclipse.wst.xquery.marklogic.xcc.ModuleSpawn;
import org.eclipse.wst.xquery.marklogic.xcc.Request;
import org.eclipse.wst.xquery.marklogic.xcc.RequestOptions;
import org.eclipse.wst.xquery.marklogic.xcc.ResultItem;
import org.eclipse.wst.xquery.marklogic.xcc.ResultSequence;
import org.eclipse.wst.xquery.marklogic.xcc.Session;
import org.eclipse.wst.xquery.marklogic.xcc.UserCredentials;
import org.eclipse.wst.xquery.marklogic.xcc.Version;
import org.eclipse.wst.xquery.marklogic.xcc.exceptions.RequestException;
import org.eclipse.wst.xquery.marklogic.xcc.exceptions.StreamingResultException;
import org.eclipse.wst.xquery.marklogic.xcc.impl.handlers.ContentInsertController;
import org.eclipse.wst.xquery.marklogic.xcc.impl.handlers.EvalRequestController;
import org.eclipse.wst.xquery.marklogic.xcc.impl.handlers.HttpRequestController;
import org.eclipse.wst.xquery.marklogic.xcc.spi.ConnectionProvider;
import org.eclipse.wst.xquery.marklogic.xcc.spi.SingleHostAddress;
import org.eclipse.wst.xquery.marklogic.xcc.types.XSDecimal;
import org.eclipse.wst.xquery.marklogic.xcc.types.XSInteger;


public class SessionImpl implements Session {
    public static enum AuthType {
        NONE, BASIC, DIGEST
    };

    private final Set<StreamingResultSequence> activeResultSeqs;
    private final ContentSource contentSource;
    private final ConnectionProvider provider;
    private final UserCredentials credentials;
    private final String contentBase;
    private Logger logger = null;
    private RequestOptions defaultOptions = new RequestOptions();
    private boolean autoCommit = true;
    private boolean closed = false;
    private Object userObject = null;
    private AuthType authType = AuthType.NONE;
    private String challenge;

    private static final String agentString = "Java/" + System.getProperty("java.version") + " MarkXDBC/"
            + Version.getVersionMajor() + "." + Version.getVersionMinor() + "-" + Version.getVersionPatch();

    public SessionImpl(ContentSource contentSource, ConnectionProvider connectionProvider, UserCredentials credentials,
            String contentBase) {
        this.contentSource = contentSource;
        this.provider = connectionProvider;
        this.credentials = credentials;
        this.contentBase = contentBase;

        activeResultSeqs = Collections.synchronizedSet(new HashSet<StreamingResultSequence>());
    }

    // -------------------------------------------------------------
    // Session interface

    public UserCredentials getUserCredentials() {
        return credentials;
    }

    public String getContentBaseName() {
        return contentBase;
    }

    public ContentSource getContentSource() {
        return (contentSource);
    }

    public void setAutoCommit(boolean autoCommit) {
        if (!autoCommit) {
            throw new UnsupportedOperationException("Multi-request transactions not yet supported");
        }

        this.autoCommit = autoCommit;
    }

    public boolean getAutoCommit() {
        return (autoCommit);
    }

    public void commit() {
        assertAutoCommit();
    }

    public void rollback() {
        assertAutoCommit();
    }

    public void close() {
        if (closed) {
            return;
        }

        closed = true;

        synchronized (activeResultSeqs) {
            for (Iterator<StreamingResultSequence> it = activeResultSeqs.iterator(); it.hasNext();) {
                StreamingResultSequence rs = it.next();

                it.remove();

                try {
                    rs.close();
                } catch (StreamingResultException e) {
                    getLogger().warning("Exception closing streaming result seq: " + e);
                    // carry on
                }
            }
        }
    }

    public boolean isClosed() {
        return closed;
    }

    public ModuleInvoke newModuleInvoke(String moduleUri, RequestOptions options) {
        assertSessionOpen();

        return new ModuleImpl(this, moduleUri, options, false);
    }

    public ModuleInvoke newModuleInvoke(String moduleUri) {
        return (newModuleInvoke(moduleUri, null));
    }

    public ModuleSpawn newModuleSpawn(String moduleUri, RequestOptions options) {
        assertSessionOpen();

        return new ModuleImpl(this, moduleUri, options, true);
    }

    public ModuleSpawn newModuleSpawn(String moduleUri) {
        return (newModuleSpawn(moduleUri, null));
    }

    public AdhocQuery newAdhocQuery(String queryText, RequestOptions options) {
        assertSessionOpen();

        return new AdhocImpl(this, queryText, options);
    }

    public AdhocQuery newAdhocQuery(String queryText) {
        assertSessionOpen();

        return (newAdhocQuery(queryText, null));
    }

    public void insertContent(Content[] contents) throws RequestException {
        assertSessionOpen();

        boolean autoCommit = getAutoCommit();
        Request request = newAdhocQuery("()");
        RequestOptions sessionOptions = getDefaultRequestOptions();

        // These numbers correspond to the server values, about two minutes overall
        if ((sessionOptions.getMaxAutoRetry() == -1) || (sessionOptions.getAutoRetryDelayMillis() == -1)) {
            RequestOptions options = new RequestOptions();
            if (sessionOptions.getMaxAutoRetry() == -1)
                options.setMaxAutoRetry(64);
            if (sessionOptions.getAutoRetryDelayMillis() == -1)
                options.setAutoRetryDelayMillis(125);
            request.setOptions(options);
        }

        assertNoTimeStamp(request);
        assertNonEmptyUris(request, contents);

        HttpRequestController controller = new ContentInsertController(contents, autoCommit);

        controller.runRequest(provider, request, getLogger());
    }

    private void assertNonEmptyUris(Request request, Content[] contents) throws RequestException {
        for (int i = 0; i < contents.length; i++) {
            String uri = contents[i].getUri();

            if ((uri == null) || (uri.length() == 0)) {
                throw new RequestException("Content insertion with empty URI is not allowed", request);
            }
        }
    }

    private void assertNoTimeStamp(Request request) throws RequestException {
        RequestOptions options = getEffectiveRequestOptions();

        if (options.getEffectivePointInTime() == null) {
            return;
        }

        if (options.getEffectivePointInTime().equals(BigInteger.ZERO)) {
            return;
        }

        throw new RequestException("Content insertion not allowed with non-zero Point-In-Time", request);
    }

    public void insertContent(Content content) throws RequestException {
        insertContent(new Content[] { content });
    }

    public ContentbaseMetaData getContentbaseMetaData() {
        return new CBMetaDataImpl(this);
    }

    public void setDefaultRequestOptions(RequestOptions options) {
        if (options == null) {
            this.defaultOptions = new RequestOptions();
        } else {
            this.defaultOptions = options;
        }
    }

    public RequestOptions getDefaultRequestOptions() {
        return defaultOptions;
    }

    public Logger getLogger() {
        return (logger == null) ? contentSource.getDefaultLogger() : logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public ResultSequence submitRequest(Request request) throws RequestException {
        assertSessionOpen();

        if ((request.getSession() != this) || (!(request instanceof RequestImpl))) {
            throwIllegalArg("Request object was not created by this session", getLogger());
        }

        RequestImpl req = (RequestImpl)request;

        HttpRequestController controller = new EvalRequestController(req.serverPath(), req
                .encodedQueryString(getLogger()));

        return controller.runRequest(provider, request, getLogger());
    }

    public BigInteger getCurrentServerPointInTime() throws RequestException {
        Request pitReq = newAdhocQuery("xdmp:request-timestamp()");

        ResultSequence rs = submitRequest(pitReq);
        ResultItem item = rs.next();
        BigInteger stamp = null;

        if (item.getItem() instanceof XSDecimal) {
            stamp = ((XSDecimal)item.getItem()).asBigDecimal().toBigInteger();
        } else {
            stamp = ((XSInteger)item.getItem()).asBigInteger();
        }

        rs.close();

        return stamp;
    }

    public Object getUserObject() {
        return userObject;
    }

    public void setUserObject(Object userObject) {
        this.userObject = userObject;
    }

    public URI getConnectionUri() {
        if (!(provider instanceof SingleHostAddress)) {
            return null;
        }

        InetSocketAddress addr = ((SingleHostAddress)provider).getAddress();

        try {
            return new URI("xcc", getUserCredentials().getUserName() + ":xxxx", addr.getHostName(), addr.getPort(),
                    (getContentBaseName() == null) ? null : ("/" + getContentBaseName()), null, null);
        } catch (URISyntaxException e) {
            return null;
        }
    }

    // ---------------------------------------------------------------
    // Implementation-specific accessors

    public void registerResultSequence(StreamingResultSequence resultSequence) {
        activeResultSeqs.add(resultSequence);
    }

    public void deRegisterResultSequence(StreamingResultSequence resultSequence) {
        synchronized (activeResultSeqs) {
            if (activeResultSeqs.contains(resultSequence)) {
                activeResultSeqs.remove(resultSequence);
            }
        }
    }

    public RequestOptions getEffectiveRequestOptions() {
        RequestOptions eff = new RequestOptions();
        RequestOptions ses = getDefaultRequestOptions();

        eff.applyEffectiveValues(new RequestOptions[] { ses });

        return eff;
    }

    public ConnectionProvider getProvider() {
        return provider;
    }

    public String userAgentString() {
        return (agentString);
    }

    public boolean processAuthChallenge(String challenge) {
        AuthType authType = AuthType.valueOf(challenge.split(" ")[0].toUpperCase());
        boolean retryAdvised = (authType != this.authType);
        this.authType = authType;
        this.challenge = challenge;
        return retryAdvised;
    }

    public String getAuthString(String method, String uri) {
        switch (authType) {
        case BASIC:
            return credentials.toHttpBasicAuth();
        case DIGEST:
            return ((ContentSourceImpl.Credentials)credentials).toHttpDigestAuth(method, uri, challenge);
        default:
            return null;
        }
    }

    // ---------------------------------------------------------------

    private void assertSessionOpen() {
        if (isClosed()) {
            throw new IllegalStateException("Session has been closed");
        }

    }

    private void assertAutoCommit() {
        if (!autoCommit) {
            throw new UnsupportedOperationException("Explicit transactions not yet supported");
        }
    }

    private void throwIllegalArg(String msg, Logger logger) {
        logger.severe(msg);

        throw new IllegalArgumentException(msg);
    }

    // ---------------------------------------------------------------

    @Override
    public String toString() {
        return credentials.toString() + ", cb=" + ((contentBase == null) ? "{default}" : contentBase)
                + " [ContentSource: " + contentSource.toString() + "]";
    }
}
