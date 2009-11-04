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
 * A specialization of {@link XQueryException} which is always retryable, to define a distinct
 * exception type that can be caught separately. Note that in most cases, retryable exceptions will
 * be retried automatically.
 * 
 * @see org.eclipse.wst.xquery.marklogic.xcc.RequestOptions#setMaxAutoRetry(int)
 */
public class RetryableXQueryException extends XQueryException {
    private static final long serialVersionUID = -4495822742402772526L;

    public RetryableXQueryException(Request request, String code, String w3cCode, String xqueryVersion, String message,
            String formatString, String expr, boolean retryable, String[] data, XQueryStackFrame[] stack) {
        super(request, code, w3cCode, xqueryVersion, message, formatString, expr, retryable, data, stack);
    }
}
