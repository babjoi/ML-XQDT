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

import org.eclipse.wst.xquery.marklogic.xcc.Content;
import org.eclipse.wst.xquery.marklogic.xcc.Request;

/**
 * This exception indicates a failed attempt to insert a {@link org.eclipse.wst.xquery.marklogic.xcc.Content} object.
 * Such failures are automatically retried up the the number of times allowed by the request's
 * options ({@link org.eclipse.wst.xquery.marklogic.xcc.RequestOptions#setMaxAutoRetry(int)}). If this execption
 * ocurrs, either all retries have been exhausted, no retries were configured, or the specific
 * {@link org.eclipse.wst.xquery.marklogic.xcc.Content} instance was not retryable (
 * {@link org.eclipse.wst.xquery.marklogic.xcc.Content#isRewindable()}).
 */
public class ContentInsertException extends RequestServerException {
    private static final long serialVersionUID = -4061934165729146460L;
    private final transient Content content;

    public ContentInsertException(String message, Request request, Content content) {
        super(message, request);

        this.content = content;
    }

    public ContentInsertException(String message, Request request, Content content, Throwable cause) {
        super(message, request, cause);

        this.content = content;
    }

    /**
     * The {@link Content} object that could not be inserted.
     * 
     * @return an instance of {@link Content}.
     */
    public Content getContent() {
        return content;
    }

    /**
     * The URI, as a String, of the {@link org.eclipse.wst.xquery.marklogic.xcc.Content} that could not be inserted.
     * 
     * @return a URI String.
     */
    public String getContentUri() {
        return content.getUri();
    }
}
