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
package org.eclipse.wst.xquery.marklogic.xcc.impl;

import java.util.logging.Logger;

import org.eclipse.wst.xquery.marklogic.io.IOHelper;
import org.eclipse.wst.xquery.marklogic.xcc.AdhocQuery;
import org.eclipse.wst.xquery.marklogic.xcc.RequestOptions;
import org.eclipse.wst.xquery.marklogic.xcc.Session;


public class AdhocImpl extends RequestImpl implements AdhocQuery {
    private String query;

    public AdhocImpl(Session session, String query, RequestOptions options) {
        super(session, options);

        this.query = query;
    }

    // --------------------------------------------------

    public void setQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

    // --------------------------------------------------

    @Override
    String requestVar() {
        return "xquery";
    }

    @Override
    String serverPath() {
        return "/eval";
    }

    @Override
    void urlEncodeXQueryString(StringBuffer sb, Logger logger) {
        IOHelper.urlEncodeToStringBuffer(sb, query);
    }
}
