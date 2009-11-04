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
import org.eclipse.wst.xquery.marklogic.xcc.Request;
import org.eclipse.wst.xquery.marklogic.xcc.exceptions.RequestException;
import org.eclipse.wst.xquery.marklogic.xcc.exceptions.ServerConnectionException;
import org.eclipse.wst.xquery.marklogic.xcc.types.XdmNode;
import org.eclipse.wst.xquery.marklogic.xcc.types.impl.NodeImpl;


public class ServerExceptionHandler implements ResponseHandler {
    public Object handleResponse(HttpChannel http, int responseCode, Request request, Object attachment, Logger logger)
            throws RequestException {
        try {
            XdmNode errorNode = new NodeImpl(http.getResponseStream());
            RequestException ex = ServerErrorParser.makeException(request, errorNode.asString());
            Exception e = new Exception("dummy");

            // report stack trace from here, not from inside SAX parser
            ex.setStackTrace(e.getStackTrace());

            throw ex;
        } catch (IOException e) {
            throw new ServerConnectionException("Trouble parsing server error response: " + e, request, e);
        }
    }
}
