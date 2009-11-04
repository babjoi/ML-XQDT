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
 * Base class for type-safe enumeration instances that represent atomic types.
 */
public final class AtomicType extends ItemType {
    AtomicType(String name) {
        super(name);
    }

    // --------------------------------------------------

    /**
     * Always false.
     * 
     * @return Always false for atomic types.
     */
    @Override
    public boolean isNode() {
        return (false);
    }

    /**
     * Always true.
     * 
     * @return Always true for atomic types.
     */
    @Override
    public boolean isAtomic() {
        return (true);
    }

    // --------------------------------------------------
}
