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

import java.io.InputStream;

import org.eclipse.wst.xquery.marklogic.xcc.types.ValueType;


public class NodeImpl extends AbstractStreamableNodeItem {
    public NodeImpl(String value) {
        super(ValueType.NODE, value);
    }

    public NodeImpl(InputStream stream) {
        super(ValueType.NODE, stream);
    }
}
