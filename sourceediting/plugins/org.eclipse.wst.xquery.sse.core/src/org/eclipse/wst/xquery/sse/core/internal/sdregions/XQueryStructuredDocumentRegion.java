/*******************************************************************************
 * Copyright (c) 2005, 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.xquery.sse.core.internal.sdregions;

import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocumentRegion;
import org.eclipse.wst.sse.core.internal.provisional.text.ITextRegion;
import org.eclipse.wst.sse.core.internal.provisional.text.ITextRegionList;
import org.eclipse.wst.sse.core.internal.text.BasicStructuredDocumentRegion;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.IASTNode;
import org.eclipse.wst.xquery.sse.core.internal.regions.XQueryRegion;

/**
 * Base class for XQuery structured document region.
 * 
 * <p>
 * Extends basic behavior by making it aware of lexical errors stored in {@link XQueryRegion}
 * 
 * @author <a href="villard@us.ibm.com">Lionel Villard</a>
 */
@SuppressWarnings("restriction")
public class XQueryStructuredDocumentRegion extends BasicStructuredDocumentRegion {

    // Factory

    final public static class Factory implements StructuredDocumentRegionFactory {

        final public static Factory INSTANCE = new Factory();

        private Factory() {
        }

        // Implements StructuredDocumentRegionFactory

        public XQueryStructuredDocumentRegion create() {
            return new XQueryStructuredDocumentRegion();
        }

    }

    // State

    /** Corresponding AST node */
    protected IASTNode astNode;

    // Constructors

    public XQueryStructuredDocumentRegion() {
    }

    // Methods

    /** The wrapping AST node (if any) */
    public IASTNode getASTNode() {
        return astNode;
    }

    /**
     * @param node
     *            the AST Node to set
     */
    public void setASTNode(IASTNode node) {
        this.astNode = node;
    }

    // Overrides

    @Override
    public boolean sameAs(IStructuredDocumentRegion region, int shift) {
        return super.sameAs(region, shift) && sameLexicalState(region);
    }

    @Override
    public boolean sameAs(ITextRegion oldRegion, IStructuredDocumentRegion newDocumentRegion, ITextRegion newRegion,
            int shift) {
        return super.sameAs(oldRegion, newDocumentRegion, newRegion, shift) && sameLexicalState(oldRegion, newRegion);
    }

    // Helpers

    private boolean sameLexicalState(ITextRegion oldRegion, ITextRegion newRegion) {
        if (oldRegion instanceof XQueryRegion && newRegion instanceof XQueryRegion) {
            return ((XQueryRegion)oldRegion).getErrorLexicalState() == ((XQueryRegion)newRegion).getErrorLexicalState();
        }

        return true;
    }

    private boolean sameLexicalState(IStructuredDocumentRegion region) {
        return sameLexicalState(region.getFirstRegion(), getFirstRegion());
    }

}
