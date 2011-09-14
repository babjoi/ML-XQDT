/*******************************************************************************
 * Copyright (c) 2008, 2009 28msec Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Gabriel Petrovay (28msec) - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.xquery.core.model.ast;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.declarations.Declaration;

public class XQueryModuleImport extends Declaration implements IChildProcessor {

    private XQueryStringLiteral fNamespaceUri;
    private List<XQueryStringLiteral> fHints = new ArrayList<XQueryStringLiteral>();

    public XQueryModuleImport(String name, int nameStart, int nameEnd, int declStart, int declEnd) {
        super(declStart, declEnd);
        this.setName(name);
        this.setNameStart(nameStart);
        this.setNameEnd(nameEnd);
    }

    public String getNamespacePrefix() {
        return getName();
    }

    public void setNamespacePrefix(String namespacePrefix) {
        setName(namespacePrefix);
    }

    public XQueryStringLiteral getNamespaceUri() {
        return fNamespaceUri;
    }

    public void setNamespaceUri(XQueryStringLiteral namespaceUri) {
        fNamespaceUri = namespaceUri;
    }

    public void addHint(XQueryStringLiteral hint) {
        fHints.add(hint);
    }

    /**
     * Gets the hints of this import.
     * 
     * @return The list of hints used in this import or an empty lists if no hints were used.
     */
    public List<XQueryStringLiteral> getHints() {
        return fHints;
    }

    public void processChild(ASTNode child) {
        if (child instanceof XQueryStringLiteral) {
            if (fNamespaceUri == null) {
                setNamespaceUri((XQueryStringLiteral)child);
            } else {
                addHint((XQueryStringLiteral)child);
            }
        }
    }

    @Override
    public int getKind() {
        return XQDTExpressionConstants.XQDT_MODULE_IMPORT;
    }

    @Override
    public void traverse(ASTVisitor visitor) throws Exception {
        if (visitor.visit(this)) {
            visitor.endvisit(this);
        }
    }

    @Override
    public String toString() {
        return getNamespacePrefix() + " = '" + getNamespaceUri().getValue() + "'";
    }

    @Override
    public int matchStart() {
        return getNameStart();
    }

    @Override
    public int matchLength() {
        return getNameEnd() - getNameStart();
    }

}