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
package org.eclipse.wst.xquery.sse.core.internal.model.ast.impl;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocument;
import org.eclipse.wst.sse.core.internal.provisional.text.ITextRegion;
import org.eclipse.wst.validation.internal.provisional.core.IReporter;
import org.eclipse.wst.validation.internal.provisional.core.IValidator;
import org.eclipse.wst.xquery.sse.core.internal.XQueryMessages;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.IASTFunctionDecl;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.IASTModule;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.IASTNode;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.IASTVarDecl;
import org.eclipse.wst.xquery.sse.core.internal.sdregions.ModuleDeclStructuredDocumentRegion;

/**
 * XQuery module declaration
 * 
 * @author <a href="villard@us.ibm.com">Lionel Villard</a>
 */
public class ASTModule extends ASTParentNode implements IASTModule {

    // State

    /** Version text region */
    protected ITextRegion versionTextRegion;

    /** Encoding text region */
    protected ITextRegion encodingTextRegion;

    /** Module namespace prefix (library only) */
    protected ITextRegion namespacePrefixTextRegion;

    /** Module declaration (library only) */
    protected ModuleDeclStructuredDocumentRegion moduleSDRegion;

    /** List of function declaration, indexed by their name */
    // TODO: namespaces..
    protected Map<String, IASTFunctionDecl> functionDecls;

    /** List of variable declarations, indexed by their name */
    protected Map<String, IASTVarDecl> variableDecls;

    /** Query body expression (null for library module) */
    protected IASTNode queryBody;

    // Constructors

    protected ASTModule() {
        functionDecls = new HashMap<String, IASTFunctionDecl>();
        variableDecls = new HashMap<String, IASTVarDecl>();
    }

    // Methods

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.wst.xquery.sse.core.internal.model.ast.impl.IASTModule#setVersionRegion(org.eclipse
     * .wst.sse.core.internal.provisional.text.ITextRegion)
     */
    public void setVersionRegion(ITextRegion version) {
        this.versionTextRegion = version;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.wst.xquery.sse.core.internal.model.ast.impl.IASTModule#setEncodingRegion(org.
     * eclipse.wst.sse.core.internal.provisional.text.ITextRegion)
     */
    public void setEncodingRegion(ITextRegion encoding) {
        this.encodingTextRegion = encoding;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.wst.xquery.sse.core.internal.model.ast.impl.IASTModule#
     * setModuleDeclStructuredDocumentRegion
     * (org.eclipse.wst.xquery.sse.core.internal.sdregions.ModuleDeclStructuredDocumentRegion)
     */
    public void setModuleDeclStructuredDocumentRegion(ModuleDeclStructuredDocumentRegion region) {
        moduleSDRegion = region;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.wst.xquery.sse.core.internal.model.ast.impl.IASTModule#setNamespacePrefixRegion
     * (org.eclipse.wst.sse.core.internal.provisional.text.ITextRegion)
     */
    public void setNamespacePrefixRegion(ITextRegion version) {
        this.namespacePrefixTextRegion = version;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.wst.xquery.sse.core.internal.model.ast.impl.IASTModule#getModuleNamespace()
     */
    public String getModuleNamespace() {
        if (moduleSDRegion != null) {
            try {
                ITextRegion region = moduleSDRegion.getNamespace();
                if (region != null) {
                    // Return namespace without surrounding ""

                    return moduleSDRegion.getParentDocument().get(region.getStart() + region.getStart() + 1,
                            region.getLength() - 2);
                }
            } catch (BadLocationException e) {
                // Ignore..
                return null;
            }
        }

        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.wst.xquery.sse.core.internal.model.ast.impl.IASTModule#getModulePrefix()
     */
    public String getModulePrefix() {
        if (moduleSDRegion != null) {
            ITextRegion region = moduleSDRegion.getNamespacePrefix();
            if (region != null) {
                return moduleSDRegion.getFullText(region).trim();
            }
        }

        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.wst.xquery.sse.core.internal.model.ast.impl.IASTModule#getFunctionDecl(java.lang
     * .String)
     */
    public IASTFunctionDecl getFunctionDecl(String name) {
        return functionDecls.get(name);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.wst.xquery.sse.core.internal.model.ast.impl.IASTModule#setFunctionDecl(int,
     * java.lang.String, org.eclipse.wst.xquery.sse.core.internal.model.ast.impl.ASTFunctionDecl)
     */
    public void setFunctionDecl(int index, String name, IASTFunctionDecl decl) {
        setChildASTNodeAt(index, decl);
        functionDecls.put(name, decl);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.wst.xquery.sse.core.internal.model.ast.impl.IASTModule#setVariableDecl(int,
     * java.lang.String, org.eclipse.wst.xquery.sse.core.internal.model.ast.impl.ASTVarDecl)
     */
    public void setVariableDecl(int index, String name, IASTVarDecl decl) {
        setChildASTNodeAt(index, decl);
        variableDecls.put(name, decl);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.wst.xquery.sse.core.internal.model.ast.impl.IASTModule#getVariableDecl(java.lang
     * .String)
     */
    public IASTVarDecl getVariableDecl(String name) {
        return variableDecls.get(name);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.wst.xquery.sse.core.internal.model.ast.impl.IASTModule#getQueryBody()
     */
    public IASTNode getQueryBody() {
        return queryBody;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.wst.xquery.sse.core.internal.model.ast.impl.IASTModule#setQueryBody(org.eclipse
     * .wst.xquery.sse.core.internal.model.ast.IASTNode)
     */
    public void setQueryBody(IASTNode expr) {
        queryBody = expr;
    }

    // Overrides

    @Override
    public void staticCheck(IStructuredDocument document, IValidator validator, IReporter reporter) {
        // ModuleDecl: The URILiteral must be of nonzero length [err:XQST0059]
        String moduleNS = getModuleNamespace();

        if (moduleNS != null && moduleNS.length() == 0) {
            ASTHelper.reportError(moduleSDRegion, moduleSDRegion.getNamespace(), XQueryMessages.errorXQST0088_UI_,
                    validator, reporter);
        }

        // ModuleDecl: The namespace prefix specified in a module declaration must not be xml or xmlns [err:XQST0070]
        final String modulePrefix = getModulePrefix();
        if (modulePrefix != null && (modulePrefix.equals("xml") || modulePrefix.equals("xmlns"))) {
            ASTHelper.reportError(moduleSDRegion, moduleSDRegion.getNamespacePrefix(),
                    XQueryMessages.errorXQST0070_MD_UI_, validator, reporter);
        }

        super.staticCheck(document, validator, reporter);
    }

    @Override
    public int getType() {
        return MODULE;
    }

}
