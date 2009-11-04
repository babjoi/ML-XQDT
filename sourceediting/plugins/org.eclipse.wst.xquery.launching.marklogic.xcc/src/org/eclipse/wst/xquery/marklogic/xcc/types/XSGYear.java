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

import java.util.GregorianCalendar;

/**
 * XDM type: xs:gYear.
 */
public interface XSGYear extends XdmAtomic {
    /**
     * @return The value of this item as a GregorianCalendar object.
     */
    GregorianCalendar asGregorianCalendar();
}
