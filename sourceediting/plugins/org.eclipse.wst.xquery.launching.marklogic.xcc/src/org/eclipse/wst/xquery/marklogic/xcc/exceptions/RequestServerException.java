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
package org.eclipse.wst.xquery.marklogic.xcc.exceptions;

import org.eclipse.wst.xquery.marklogic.xcc.Request;

/**
 * This is the base class for for exceptions originating in the server (XQuery syntax errors, etc)
 * as opposed exceptions related to communicating with the server.
 */
public class RequestServerException extends RequestException {
    private static final long serialVersionUID = 5878916853339528551L;

    public RequestServerException(String message, Request request) {
        super(message, request);
    }

    public RequestServerException(String message, Request request, Throwable cause) {
        super(message, request, cause);
    }
}
