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

import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocumentRegion;

/**
 * Function declaration
 * 
 * @author <a href="villard@us.ibm.com">Lionel Villard</a>
 */
public interface IASTFunctionDecl extends IASTNode {

    /**
     * Gets the function raw name
     */
    public String getName();

    /**
     * Gets the function raw name as a {@link IStructuredDocumentRegion}
     * 
     * <p>
     * Valid only when the model is syntactically correct.
     */
    public IStructuredDocumentRegion getNameStructuredDocumentRegion();

    /**
     * Sets the function name
     * 
     * @param object
     */
    public void setName(String name);

    /**
     * Return the function body expression
     * 
     * @return
     */
    public IASTNode getBody();

    /**
     * @param body
     *            the body to set
     */
    public void setBody(IASTNode body);

    /**
     * @param index
     * @param region
     */
    public void setParamName(int index, IStructuredDocumentRegion region);

    /**
     * @param index
     * @param region
     */
    public int getParamCount();

    /**
     * 
     * @param index
     * @return
     */
    public IStructuredDocumentRegion getParamNameAt(int index);

    /**
     * @param i
     */
    public void removeParamNamesAfter(int i);

}