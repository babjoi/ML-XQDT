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

import org.eclipse.wst.xquery.marklogic.xcc.ResultSequence;

public class EmptyResultSequence extends CachedResultSequence {
    public EmptyResultSequence(ResultSequence primary) {
        super(primary);
    }

    @Override
    public String toString() {
        return "Empty ResultSequence";
    }
}
