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

import org.eclipse.wst.xquery.marklogic.io.Base64;
import org.eclipse.wst.xquery.marklogic.xcc.types.ValueType;
import org.eclipse.wst.xquery.marklogic.xcc.types.XSBase64Binary;

public class XsBase64BinaryImpl extends AbstractStringItem implements XSBase64Binary {
    public XsBase64BinaryImpl(String bodyString) {
        super(ValueType.XS_BASE64_BINARY, bodyString);

        Base64.decode(asString());
    }

    public byte[] asBinaryData() {
        return Base64.decode(asString());
    }
}
