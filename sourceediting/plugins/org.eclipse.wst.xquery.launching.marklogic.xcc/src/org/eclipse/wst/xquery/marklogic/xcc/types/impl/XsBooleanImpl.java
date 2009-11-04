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
import org.eclipse.wst.xquery.marklogic.xcc.types.XSBoolean;

public class XsBooleanImpl extends AbstractStringItem implements XSBoolean {
    private Boolean value;

    public XsBooleanImpl(String value) {
        super(ValueType.XS_BOOLEAN, value);

        this.value = Boolean.valueOf(value);
    }

    public XsBooleanImpl(Boolean value) {
        super(ValueType.XS_BOOLEAN, value.toString());

        this.value = value;
    }

    public Boolean asBoolean() {
        return value;
    }

    public boolean asPrimitiveBoolean() {
        return value.booleanValue();
    }
}
