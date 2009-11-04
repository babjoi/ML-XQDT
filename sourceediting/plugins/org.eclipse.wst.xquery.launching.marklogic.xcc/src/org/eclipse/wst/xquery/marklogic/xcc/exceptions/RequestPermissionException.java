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
 * This exception indicates missing or incorrect permissions for a server request.
 */
public class RequestPermissionException extends RequestException {
    private static final long serialVersionUID = 4979097514231434943L;
    private String user;
    private boolean retryAdvised = false;

    public RequestPermissionException(String message, Request request, String user) {
        super(message, request);

        this.user = user;
    }

    public RequestPermissionException(String message, Request request, String user, boolean retryAdvised) {
        super(message, request);

        this.user = user;
        this.retryAdvised = retryAdvised;
    }

    public RequestPermissionException(String message, Request request, String user, Throwable cause) {
        super(message, request, cause);

        this.user = user;
    }

    /**
     * The user to which this permission issue applies.
     * 
     * @return A user name as a String.
     */
    public String getUser() {
        return user;
    }

    /**
     * Set to true when a request is expected to succeed if retried with valid credentials.
     * 
     * @return true if the request should be retried.
     */
    public boolean isRetryAdvised() {
        return retryAdvised;
    }
}
