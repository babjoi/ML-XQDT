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
package org.eclipse.wst.xquery.marklogic.xcc.impl.handlers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.eclipse.wst.xquery.marklogic.http.HttpChannel;
import org.eclipse.wst.xquery.marklogic.xcc.Request;
import org.eclipse.wst.xquery.marklogic.xcc.RequestOptions;
import org.eclipse.wst.xquery.marklogic.xcc.ResultSequence;
import org.eclipse.wst.xquery.marklogic.xcc.Session;
import org.eclipse.wst.xquery.marklogic.xcc.exceptions.RequestException;
import org.eclipse.wst.xquery.marklogic.xcc.exceptions.RequestPermissionException;
import org.eclipse.wst.xquery.marklogic.xcc.exceptions.RequestServerException;
import org.eclipse.wst.xquery.marklogic.xcc.exceptions.RetryableXQueryException;
import org.eclipse.wst.xquery.marklogic.xcc.exceptions.ServerConnectionException;
import org.eclipse.wst.xquery.marklogic.xcc.impl.SessionImpl;
import org.eclipse.wst.xquery.marklogic.xcc.spi.ConnectionErrorAction;
import org.eclipse.wst.xquery.marklogic.xcc.spi.ConnectionProvider;
import org.eclipse.wst.xquery.marklogic.xcc.spi.ServerConnection;


public abstract class AbstractRequestController implements HttpRequestController {
    protected final Map<Integer, ResponseHandler> handlers;
    protected static final Integer DEFAULT_HANDLER_KEY = new Integer(0);

    protected AbstractRequestController(Map<Integer, ResponseHandler> handlers) {
        if (handlers == null) {
            this.handlers = new HashMap<Integer, ResponseHandler>();
        } else {
            this.handlers = handlers;
        }
    }

    // -------------------------------------------------------------

    public abstract ResultSequence serverDialog(ServerConnection connection, Request request,
            RequestOptions effectiveOptions, Logger logger) throws IOException, RequestException;

    // -------------------------------------------------------------
    // HttpRequestController interface

    public ResultSequence runRequest(ConnectionProvider provider, Request request, Logger logger)
            throws RequestException {
        Session session = request.getSession();
        RequestOptions options = request.getEffectiveOptions();
        long delayMillis = options.getAutoRetryDelayMillis();
        int retries = options.getMaxAutoRetry();
        int tries = Math.max(retries + 1, 1);
        RequestException re = null;

        logger.fine("submitting request, max tries=" + tries);

        for (int t = 0; t < tries; t++) {
            ServerConnection connection = null;

            sleepFor(interTryDelay(delayMillis, t));

            try {
                while (true) {

                    connection = provider.obtainConnection(session, request, logger);

                    try {
                        ResultSequence rs = serverDialog(connection, request, options, logger);

                        if ((rs == null) || rs.isCached()) {
                            provider.returnConnection(connection, logger);
                        }

                        return rs;
                    } catch (RequestPermissionException e) {
                        if (e.isRetryAdvised()) {
                            provider.returnConnection(connection, logger);
                        } else {
                            throw e;
                        }
                    }
                }
            } catch (RetryableXQueryException e) {
                logger.fine("retryable server exception caught: " + e);
                provider.returnConnection(connection, logger);
                re = e;

            } catch (RequestServerException e) {
                logger.fine("non-retryable server exception caught: " + e);
                provider.returnConnection(connection, logger);
                throw e;

            } catch (IOException e) {
                logger.fine("connection IOException caught: " + e);

                ConnectionErrorAction action = null;

                if (connection != null) {
                    action = provider.returnErrorConnection(connection, e, logger);
                }

                re = new ServerConnectionException(e.getMessage(), request, e);

                if (action != ConnectionErrorAction.RETRY) {
                    if (action == null) {
                        logger.warning("Cannot obtain connection: " + e.getMessage());
                    } else {
                        logger.fine("Provider error action=" + action + ", throwing: " + re);
                    }

                    throw re;
                }
            }
        }

        logger.info("automatic query retries (" + retries + ") exhausted, throwing: " + re);

        if (re != null) {
            throw re;
        }

        String msg = "BAD INTERNAL STATE: retries exhausted, no prior retryable exception";
        logger.severe(msg);

        throw new RequestException(msg, request);
    }

    // -------------------------------------------------------------
    // subclass accessor methods

    protected void addHandler(int code, ResponseHandler handler) {
        addHandler(handlers, code, handler);
    }

    protected static void addHandler(Map<Integer, ResponseHandler> handlers, int code, ResponseHandler handler) {
        handlers.put(new Integer(code), handler);
    }

    protected void addDefaultHandler(ResponseHandler handler) {
        addDefaultHandler(handlers, handler);
    }

    protected static void addDefaultHandler(Map<Integer, ResponseHandler> handlers, ResponseHandler handler) {
        handlers.put(DEFAULT_HANDLER_KEY, handler);
    }

    protected ResponseHandler findHandler(int responseCode) {
        ResponseHandler handler = handlers.get(new Integer(responseCode));

        if (handler != null) {
            return handler;
        }

        return handlers.get(DEFAULT_HANDLER_KEY);
    }

    // -------------------------------------------------------------

    public static boolean dontSleep = false; // this is a unit test hook

    protected abstract long interTryDelay(long delay, int currentTry);

    private void sleepFor(long millis) {
        if (dontSleep || (millis <= 0))
            return;

        long wakeupTime = System.currentTimeMillis() + millis;
        long now;

        while ((now = System.currentTimeMillis()) < wakeupTime) {
            long sleepTime = wakeupTime - now;

            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                // nothing, go around
            }
        }
    }

    protected void setConnectionTimeout(ServerConnection connection, HttpChannel http) {
        long expiryTime = 0;

        try {
            expiryTime = http.getResponseKeepaliveExpireTime();
        } catch (IOException e) {
            // do nothing, default to 0
        }

        connection.setTimeoutTime(expiryTime);
    }

    protected void addCommonHeaders(HttpChannel http, SessionImpl session, String method, String uri,
            RequestOptions options) {
        String authorization = session.getAuthString(method, uri);

        if (authorization != null) {
            http.setRequestHeader("Authorization", authorization);
        }

        http.setRequestHeader("User-Agent", session.userAgentString());

        // TODO: get this from the provider/connection?
//		setSendHeader ("Host", connInfo.getHost() + ":" + connInfo.getPort());

        // TODO: Research HTTP spec for specifying acceptable encodings
        // TODO: Is this header needed?  Alternate uses?
        http.setRequestHeader("Accept", "text/html, text/xml, image/gif, image/jpeg, */*");

        if (options.getRequestName() != null) {
            http.setRequestHeader("Referer", options.getRequestName());
        }
    }
}
