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
package org.eclipse.wst.xquery.sse.core.internal.model.ast;

import org.w3c.dom.Attr;

/**
 * Direct attribute constructor
 * 
 * <p>
 * Might contains XQuery expressions as child AST nodes.
 * 
 * @author <a href="villard@us.ibm.com">Lionel Villard</a>
 */
public interface IASTDirAttribute extends IASTNode, Attr {

    public void setName(String name);

}