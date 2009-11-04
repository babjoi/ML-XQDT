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
package org.eclipse.wst.xquery.marklogic.xcc.types;

/**
 * Base type for enumerators that represent node types.
 */
public final class NodeType extends ItemType {
    NodeType(String name) {
        super(name);
    }

    // --------------------------------------------------

    /**
     * Always true.
     * 
     * @return Always true for node types.
     */
    @Override
    public boolean isNode() {
        return (true);
    }

    /**
     * Always false.
     * 
     * @return Always false for node types.
     */
    @Override
    public boolean isAtomic() {
        return (false);
    }

    // --------------------------------------------------

}
