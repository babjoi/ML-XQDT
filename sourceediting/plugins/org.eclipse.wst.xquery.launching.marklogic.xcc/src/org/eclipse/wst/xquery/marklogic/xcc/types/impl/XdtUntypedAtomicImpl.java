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

/**
 * @deprecated To be phased out.
 */
@Deprecated
public class XdtUntypedAtomicImpl extends AbstractStringItem implements org.eclipse.wst.xquery.marklogic.xcc.types.XDTUntypedAtomic {
    public XdtUntypedAtomicImpl(String bodyString) {
        super(ValueType.XDT_UNTYPED_ATOMIC, bodyString);
    }
}
