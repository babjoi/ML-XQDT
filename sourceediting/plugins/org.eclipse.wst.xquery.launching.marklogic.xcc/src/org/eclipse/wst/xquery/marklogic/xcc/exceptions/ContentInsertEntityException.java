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
 * This is a specialization of {@link ContentInsertException} that indicates the insertion faled
 * because an XML entity embedded in the document could not be resolved. Entity resolution is not
 * done by default, is must be enabled in the options (
 * {@link org.eclipse.wst.xquery.marklogic.xcc.ContentCreateOptions}) associated with the insertion request. Entity
 * resolution failures are not retryable.
 */
public class ContentInsertEntityException extends ContentInsertException {
    private static final long serialVersionUID = 9138223923804613836L;
    private final String entityLocation;

    public ContentInsertEntityException(String message, Request request, Content content, String entityLocation) {
        super(message, request, content);

        this.entityLocation = entityLocation;
    }

    public ContentInsertEntityException(String message, Request request, Content content, String entityLocation,
            Throwable cause) {
        super(message, request, content, cause);

        this.entityLocation = entityLocation;
    }

    /**
     * Returns the location, as a URI String, of the requested entity.
     * 
     * @return A URI String.
     */
    public String getEntityLocation() {
        return entityLocation;
    }
}
