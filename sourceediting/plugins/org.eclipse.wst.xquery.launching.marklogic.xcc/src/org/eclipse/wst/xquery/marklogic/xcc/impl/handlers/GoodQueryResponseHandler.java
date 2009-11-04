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
import java.util.logging.Logger;

import org.eclipse.wst.xquery.marklogic.http.HttpChannel;
import org.eclipse.wst.xquery.marklogic.http.MultipartBuffer;
import org.eclipse.wst.xquery.marklogic.http.MultipartSplitter;
import org.eclipse.wst.xquery.marklogic.http.NullPartSplitter;
import org.eclipse.wst.xquery.marklogic.http.SimpleBoundaryPartSplitter;
import org.eclipse.wst.xquery.marklogic.xcc.Request;
import org.eclipse.wst.xquery.marklogic.xcc.RequestOptions;
import org.eclipse.wst.xquery.marklogic.xcc.impl.CachedResultSequence;
import org.eclipse.wst.xquery.marklogic.xcc.impl.SessionImpl;
import org.eclipse.wst.xquery.marklogic.xcc.impl.StreamingResultSequence;
import org.eclipse.wst.xquery.marklogic.xcc.spi.ServerConnection;


public class GoodQueryResponseHandler implements ResponseHandler {
    public Object handleResponse(HttpChannel http, int responseCode, Request request, Object attachment, Logger logger)
            throws IOException {
        RequestOptions options = request.getEffectiveOptions();
        String boundary = http.getResponseContentBoundary();
        MultipartSplitter splitter = (boundary == null) ? (MultipartSplitter)new NullPartSplitter()
                : new SimpleBoundaryPartSplitter(http.getResponseStream(), boundary.getBytes(), options
                        .getResultBufferSize(), logger);
        MultipartBuffer mbuf = new MultipartBuffer(splitter);

        if (options.getCacheResult()) {
            logger.fine("ResultSequence is to be cached, reading");

            return new CachedResultSequence(mbuf, options);
        }

        logger.fine("ResultSequence is streaming");

        return new StreamingResultSequence((SessionImpl)request.getSession(), (ServerConnection)attachment, mbuf,
                options, logger);
    }
}
