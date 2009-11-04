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
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.wst.xquery.marklogic.http.HttpChannel;
import org.eclipse.wst.xquery.marklogic.xcc.Request;
import org.eclipse.wst.xquery.marklogic.xcc.RequestOptions;
import org.eclipse.wst.xquery.marklogic.xcc.ResultSequence;
import org.eclipse.wst.xquery.marklogic.xcc.exceptions.RequestException;
import org.eclipse.wst.xquery.marklogic.xcc.impl.SessionImpl;
import org.eclipse.wst.xquery.marklogic.xcc.spi.ServerConnection;


public class EvalRequestController extends AbstractRequestController {
    private static final Map<Integer, ResponseHandler> handlers = new HashMap<Integer, ResponseHandler>(8);

    static {
        addDefaultHandler(handlers, new UnrecognizedCodeHandler());
        addHandler(handlers, HttpURLConnection.HTTP_UNAVAILABLE, new ServiceUnavailableHandler());
        addHandler(handlers, HttpURLConnection.HTTP_INTERNAL_ERROR, new ServerExceptionHandler());
        addHandler(handlers, HttpURLConnection.HTTP_UNAUTHORIZED, new UnauthorizedHandler());
        addHandler(handlers, HttpURLConnection.HTTP_NOT_FOUND, new NotFoundCodeHandler());
        addHandler(handlers, HttpURLConnection.HTTP_BAD_REQUEST, new NotFoundCodeHandler());
        addHandler(handlers, HttpURLConnection.HTTP_OK, new GoodQueryResponseHandler());
    }

    // --------------------------------------------------------

    private final String body;
    private final String path;

    // --------------------------------------------------------

    public EvalRequestController(String path, String body) {
        super(handlers);

        this.path = path;
        this.body = body;
    }

    // --------------------------------------------------------
    // Invoked by superclass template method

    @Override
    public ResultSequence serverDialog(ServerConnection connection, Request request, RequestOptions options,
            Logger logger) throws IOException, RequestException {
        SessionImpl session = (SessionImpl)request.getSession();

        HttpChannel http = buildChannel(connection, path, session, options, logger);

        issueRequest(http, body, logger);

        int code = http.getResponseCode();

        setConnectionTimeout(connection, http);

        return (ResultSequence)findHandler(code).handleResponse(http, code, request, connection, logger);
    }

    // --------------------------------------------------------

    @Override
    protected long interTryDelay(long delay, int currentTry) {
        if ((currentTry == 0) || (delay <= 0)) {
            return 0;
        }

        return delay;
    }

    // --------------------------------------------------------

    private HttpChannel buildChannel(ServerConnection connection, String path, SessionImpl session,
            RequestOptions options, Logger logger) {
        String method = "POST";

        HttpChannel http = new HttpChannel(connection.channel(), method, path, body.length(), options
                .getTimeoutMillis(), logger);

        http.setRequestContentType("application/x-www-form-urlencoded");
        http.setCloseOutputIfNoContentLength(true);

        addCommonHeaders(http, session, method, path, options);

        return (http);
    }

    // -----------------------------------------------------

    private void issueRequest(HttpChannel http, String encodedQuery, Logger logger) throws IOException {
        if (logger.isLoggable(Level.FINE)) {
            logger.fine("encoded query: " + encodedQuery);
        }

        logger.fine("writing query to HttpChannel");
        http.writeString(encodedQuery);

        int code = http.getResponseCode();

        if (logger.isLoggable(Level.FINE)) {
            logger.fine("response: " + code + " (" + http.getResponseMessage() + ")");
        }
    }
}
