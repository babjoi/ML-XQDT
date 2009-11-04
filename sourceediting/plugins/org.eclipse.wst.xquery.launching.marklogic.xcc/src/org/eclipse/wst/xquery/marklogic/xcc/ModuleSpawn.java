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
 * An extension of {@link org.eclipse.wst.xquery.marklogic.xcc.Request} that contains the URI of an XQuery module
 * (resident server-side) to be spawned asynchronously by the MarkLogic Server.
 */
public interface ModuleSpawn extends Request {
    /**
     * Replace the URI associated with this {@link org.eclipse.wst.xquery.marklogic.xcc.Request}, which specifies an
     * XQuery module (server-side code) to be spawned (run asynchronously) on the server.
     * 
     * @param uri
     *            A String that represents the URI of a text document known to the server which is
     *            an XQuery module.
     */
    void setModuleUri(String uri);

    /**
     * Returns the currently set URI for this {@link org.eclipse.wst.xquery.marklogic.xcc.Request}.
     * 
     * @return The URI, as a String, of a module to invoke upon the next invocation of
     *         {@link org.eclipse.wst.xquery.marklogic.xcc.Session#submitRequest(org.eclipse.wst.xquery.marklogic.xcc.Request)}.
     */
    String getModuleUri();

//	String getModuleRoot();
}
