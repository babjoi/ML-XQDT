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

import org.eclipse.wst.sse.core.internal.provisional.text.ITextRegion;
import org.eclipse.wst.xquery.sse.core.internal.sdregions.ModuleDeclStructuredDocumentRegion;

/**
 * XQuery module declaration.
 * 
 * <p>
 * Prolog declarations are represented as child nodes. See {@link #getQueryBody()} and
 * {@link #setQueryBody(IASTNode)} for getting and setting the query body
 * 
 * @author <a href="villard@us.ibm.com">Lionel Villard</a>
 */
public interface IASTModule extends IASTNode {

    public void setVersionRegion(ITextRegion version);

    public void setEncodingRegion(ITextRegion encoding);

    public void setModuleDeclStructuredDocumentRegion(ModuleDeclStructuredDocumentRegion region);

    public void setNamespacePrefixRegion(ITextRegion version);

    public String getModuleNamespace();

    /**
     * Gets the module prefix or null is none
     * 
     * @return
     */
    public String getModulePrefix();

    /**
     * Get the function of the given name, or null if none exist
     * 
     * @param name
     * @return
     */
    public IASTFunctionDecl getFunctionDecl(String name);

    /**
     * Get the variable of the given name, or null if none exist
     * 
     * @param name
     * @return
     */
    public IASTVarDecl getVariableDecl(String name);

    /**
     * @return Query body
     */
    public IASTNode getQueryBody();

    /**
     * @param queryBody
     */
    public void setQueryBody(IASTNode expr);

}