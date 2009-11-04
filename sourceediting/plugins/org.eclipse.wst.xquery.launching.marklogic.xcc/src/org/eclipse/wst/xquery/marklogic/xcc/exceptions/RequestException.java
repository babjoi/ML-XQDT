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
 * The base class for exceptions related to submitting requests to the server.
 */
public class RequestException extends XccException {
    private static final long serialVersionUID = -7193834172330203276L;
    private transient Request request;

    public RequestException(String message, Request request) {
        super(message);

        this.request = request;
    }

    public RequestException(String message, Request request, Throwable cause) {
        super(message, cause);

        this.request = request;
    }

    /**
     * The initiating request to which this exception applies.
     * 
     * @return An instance of {@link Request}.
     */
    public Request getRequest() {
        return request;
    }

    @Override
    public String toString() {
        return super.toString() + System.getProperty("line.separator") + " [Session: "
                + request.getSession().toString() + "]";
    }
}
