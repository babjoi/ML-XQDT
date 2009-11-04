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
 * This exception indicates that an unrecognized response was received from the server. This may
 * indicate a misconfiguration such that XCC is connecting to something other than a Mark Logic XDBC
 * server port.
 */
public class ServerResponseException extends ServerConnectionException {
    private static final long serialVersionUID = -4888769581702598169L;
    private int responseCode;
    private String responseMessage;

    public ServerResponseException(String message, Request request, int responseCode, String responseMessage) {
        super(message, request);

        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
    }

//	public ServerResponseException (String message, int responseCode, String responseMessage, Throwable cause)
//	{
//		super (message, cause);
//
//		this.responseCode = responseCode;
//		this.responseMessage = responseMessage;
//	}

    public int getResponseCode() {
        return responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }
}
