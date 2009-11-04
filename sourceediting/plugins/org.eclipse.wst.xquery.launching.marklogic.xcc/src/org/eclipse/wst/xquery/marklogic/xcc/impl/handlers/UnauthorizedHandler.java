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
import org.eclipse.wst.xquery.marklogic.xcc.UserCredentials;
import org.eclipse.wst.xquery.marklogic.xcc.exceptions.RequestException;
import org.eclipse.wst.xquery.marklogic.xcc.exceptions.RequestPermissionException;
import org.eclipse.wst.xquery.marklogic.xcc.exceptions.ServerResponseException;
import org.eclipse.wst.xquery.marklogic.xcc.impl.SessionImpl;


public class UnauthorizedHandler implements ResponseHandler {
    public Object handleResponse(HttpChannel http, int responseCode, Request request, Object attachment, Logger logger)
            throws RequestException {
        String challenge;

        try {
            challenge = http.getResponseHeader("www-authenticate");
        } catch (IOException e) {
            throw new ServerResponseException("Failed checking authenticate header.", request, responseCode,
                    getResponseMessage(http));
        }

        SessionImpl session = (SessionImpl)request.getSession();

        boolean retryAdvised = (challenge != null) ? session.processAuthChallenge(challenge) : false;

        UserCredentials credentials = request.getSession().getUserCredentials();
        String userName = credentials.getUserName();
        String message = "Authorization failed for user '" + userName + "'.";

        throw new RequestPermissionException(message, request, userName, retryAdvised);
    }

    private String getResponseMessage(HttpChannel http) {
        try {
            return http.getResponseMessage();
        } catch (IOException e) {
            return ("No Message");
        }
    }
}
