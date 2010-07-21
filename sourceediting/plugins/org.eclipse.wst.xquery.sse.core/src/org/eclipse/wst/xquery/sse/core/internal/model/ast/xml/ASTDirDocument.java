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
package org.eclipse.wst.xquery.sse.core.internal.model.ast.xml;

import org.eclipse.wst.xml.core.internal.document.DocumentImpl;
import org.w3c.dom.DOMException;
import org.w3c.dom.Element;

/**
 * Direct document constructor
 * 
 * <p>Not an XQuery constructor, just overrides create** methods creating AST nodes
 *
 * @author <a href="villard@us.ibm.com">Lionel Villard</a> 
 */
@SuppressWarnings("restriction")
public class ASTDirDocument extends DocumentImpl {

	@Override
	public Element createElement(String tagName) throws DOMException {
		checkTagNameValidity(tagName);

		return new ASTDirElement(this, tagName);
	}

}
