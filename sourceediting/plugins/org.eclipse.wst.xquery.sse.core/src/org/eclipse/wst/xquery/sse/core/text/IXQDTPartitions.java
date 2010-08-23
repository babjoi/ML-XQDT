/*******************************************************************************
 * Copyright (c) 2010 28msec Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Gabriel Petrovay (28msec) - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.xquery.sse.core.text;

public interface IXQDTPartitions {

    // Partition types

    /** Default XQuery partition */
    final public static String XQUERY_DEFAULT = "org.eclipse.wst.xquery.DEFAULT";
    /** XQuery comment partition */
    final public static String XQUERY_COMMENT = "org.eclipse.wst.xquery.COMMENT";
    /** XQuery string partition */
    final public static String XQUERY_STRING = "org.eclipse.wst.xquery.STRING";

}