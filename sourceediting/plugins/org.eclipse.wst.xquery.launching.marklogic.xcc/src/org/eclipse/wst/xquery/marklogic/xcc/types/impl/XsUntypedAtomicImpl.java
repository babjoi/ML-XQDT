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

import org.eclipse.wst.xquery.marklogic.xcc.types.ValueType;
import org.eclipse.wst.xquery.marklogic.xcc.types.XSUntypedAtomic;

public class XsUntypedAtomicImpl extends AbstractStringItem implements XSUntypedAtomic {
    public XsUntypedAtomicImpl(String bodyString) {
        super(ValueType.XS_UNTYPED_ATOMIC, bodyString);
    }
}
