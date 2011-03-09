/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.xquery.sse.ui.internal.quickassist;


/**
 * Define some utility methods to determinate the location of a region in the query
 * 
 * @author <a href="villard@us.ibm.com">Lionel Villard</a>
 */
public interface XQDTRegionLocator {

    int PROLOG1_ID = 0x01;
    int PROLOG2_ID = 0x02;
    int EXPR_ID = 0x4;
}
