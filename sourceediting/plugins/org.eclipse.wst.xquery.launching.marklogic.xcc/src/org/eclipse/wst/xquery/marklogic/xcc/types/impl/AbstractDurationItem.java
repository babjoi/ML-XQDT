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

import org.eclipse.wst.xquery.marklogic.xcc.types.Duration;
import org.eclipse.wst.xquery.marklogic.xcc.types.ItemType;
import org.eclipse.wst.xquery.marklogic.xcc.types.XdmDuration;

abstract public class AbstractDurationItem extends AbstractStringItem {
    private final XdmDuration value;

    public AbstractDurationItem(ItemType type, String value) {
        super(type, value);

        this.value = new Duration(value);
    }

    public XdmDuration asDuration() {
        return value;
    }
}
