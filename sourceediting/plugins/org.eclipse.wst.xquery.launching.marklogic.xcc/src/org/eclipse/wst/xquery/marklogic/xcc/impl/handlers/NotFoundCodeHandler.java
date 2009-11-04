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
import org.eclipse.wst.xquery.marklogic.xcc.ModuleInvoke;
import org.eclipse.wst.xquery.marklogic.xcc.ModuleSpawn;
import org.eclipse.wst.xquery.marklogic.xcc.Request;
import org.eclipse.wst.xquery.marklogic.xcc.exceptions.RequestException;
import org.eclipse.wst.xquery.marklogic.xcc.exceptions.ServerResponseException;


public class NotFoundCodeHandler implements ResponseHandler {
    public Object handleResponse(HttpChannel http, int responseCode, Request request, Object attachment, Logger logger)
            throws RequestException {
        String responseMsg = getResponseMessage(http);
        String queryType = "Query evaluation";

        if (request instanceof ModuleInvoke)
            queryType = "Module invocation";
        if (request instanceof ModuleSpawn)
            queryType = "Module spawn";

        throw new ServerResponseException(queryType + " request rejected (" + responseCode + ", " + responseMsg
                + "). Is this an XDBC server?", request, responseCode, getResponseMessage(http));
    }

    private String getResponseMessage(HttpChannel http) {
        try {
            return http.getResponseMessage();
        } catch (IOException e) {
            return ("No Message");
        }
    }
}
