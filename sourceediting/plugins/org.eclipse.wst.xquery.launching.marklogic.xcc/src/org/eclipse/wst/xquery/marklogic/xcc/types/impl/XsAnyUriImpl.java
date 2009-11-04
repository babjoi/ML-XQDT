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
package org.eclipse.wst.xquery.marklogic.xcc.types.impl;

import java.net.URI;
import java.net.URISyntaxException;

import org.eclipse.wst.xquery.marklogic.xcc.types.ValueType;
import org.eclipse.wst.xquery.marklogic.xcc.types.XSAnyURI;


public class XsAnyUriImpl extends AbstractStringItem implements XSAnyURI {
    public XsAnyUriImpl(String value) {
        super(ValueType.XS_ANY_URI, value);
    }

    public URI asUri() throws URISyntaxException {
        return (new URI(asString()));
    }
}
