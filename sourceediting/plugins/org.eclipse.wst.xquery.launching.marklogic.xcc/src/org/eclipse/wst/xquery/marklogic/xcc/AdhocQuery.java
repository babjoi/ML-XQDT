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
package org.eclipse.wst.xquery.marklogic.xcc;

/**
 * A specialization of {@link Request} which contains an ad-hoc query (XQuery code as a literal
 * String) to be submitted and evaluated by the MarkLogic Server.
 */
public interface AdhocQuery extends Request {
    /**
     * Replace the XQuery code to be submitted and evaluated with this {@link Request}.
     * 
     * @param query
     */
    void setQuery(String query);

    /**
     * Returns the currently set ad-hoc XQuery string.
     * 
     * @return A String which is the ad-hoc query to run when this {@link Request} is next passed to
     *         {@link Session#submitRequest(Request)}.
     */
    String getQuery();
}
