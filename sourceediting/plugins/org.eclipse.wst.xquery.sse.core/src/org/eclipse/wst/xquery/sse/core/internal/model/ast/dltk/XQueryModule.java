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
package org.eclipse.wst.xquery.sse.core.internal.model.ast.dltk;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.Declaration;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.jface.text.Position;
import org.eclipse.wst.xquery.core.model.ast.IChildProcessor;
import org.eclipse.wst.xquery.core.model.ast.XQDTExpressionConstants;
import org.eclipse.wst.xquery.core.model.ast.XQueryBaseURIDecl;
import org.eclipse.wst.xquery.core.model.ast.XQueryFunctionDecl;
import org.eclipse.wst.xquery.core.model.ast.XQueryModuleImport;
import org.eclipse.wst.xquery.core.model.ast.XQueryStringLiteral;
import org.eclipse.wst.xquery.core.model.ast.XQueryVarDecl;
import org.eclipse.wst.xquery.core.model.ast.XQueryXmlAttributeValueText;
import org.eclipse.wst.xquery.core.model.ast.XQueryXmlElementContentText;

public abstract class XQueryModule extends ModuleDeclaration implements IChildProcessor {

    private String fVersion;
    private String fEncoding;

    private XQueryBaseURIDecl fBaseUri;

    private List<XQueryModuleImport> fImports = new ArrayList<XQueryModuleImport>(5);

    private HashMap<String, XQueryFunctionDecl> fFunctionDecls = new HashMap<String, XQueryFunctionDecl>(5);
    private HashMap<String, XQueryVarDecl> fVarDecls = new HashMap<String, XQueryVarDecl>(5);

    private List<XQueryStringLiteral> fStringLiterals = new ArrayList<XQueryStringLiteral>(5);
    private List<XQueryXmlElementContentText> fXmlElementContentTexts = new ArrayList<XQueryXmlElementContentText>(5);
    private List<XQueryXmlAttributeValueText> fXmlAttributeValuesTexts = new ArrayList<XQueryXmlAttributeValueText>(5);
    private List<Position> fKeywordPositions = new ArrayList<Position>();

    public XQueryModule(int length) {
        super(length);
        fVersion = "1.0";
        fEncoding = "utf-8";
    }

    public String getVersion() {
        return fVersion;
    }

    public void setVersion(String version) {
        this.fVersion = version;
    }

    public String getEncoding() {
        return fEncoding;
    }

    public void setEncoding(String encoding) {
        this.fEncoding = encoding;
    }

    public URI getBaseUri() {
        if (fBaseUri != null) {
            try {
                return new URI(fBaseUri.getUri().getValue());
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void setBaseUri(XQueryBaseURIDecl baseUri) {
        fBaseUri = baseUri;
    }

    public void addStatement(Statement statement) {
        super.addStatement(statement);

        switch (statement.getKind()) {
        case Declaration.D_VAR_DECL:
            XQueryVarDecl varDecl = (XQueryVarDecl)statement;
            fVarDecls.put(varDecl.getName(), varDecl);
            break;
        case Declaration.D_METHOD:
            XQueryFunctionDecl functionDecl = (XQueryFunctionDecl)statement;
            fFunctionDecls.put(functionDecl.getUniqueName(), functionDecl);
            break;
        case XQDTExpressionConstants.XQDT_MODULE_IMPORT:
            fImports.add((XQueryModuleImport)statement);
            break;
        case XQDTExpressionConstants.XQDT_BASE_URI_DECL:
            fBaseUri = (XQueryBaseURIDecl)statement;
            break;
        }
    }

    @Override
    public void removeStatement(Statement statement) {
        super.removeStatement(statement);
        if (statement instanceof XQueryFunctionDecl) {
            XQueryFunctionDecl functionDecl = (XQueryFunctionDecl)statement;
            fFunctionDecls.remove(functionDecl.getUniqueName());
        } else if (statement instanceof XQueryVarDecl) {
            XQueryVarDecl varDecl = (XQueryVarDecl)statement;
            fVarDecls.remove(varDecl.getName());
        }
    }

    public void addXmlElementContents(XQueryXmlElementContentText content) {
        fXmlElementContentTexts.add(content);
    }

    public List<XQueryXmlElementContentText> getXmlElementContentText() {
        return fXmlElementContentTexts;
    }

    public void addXmlAttributeValuesText(XQueryXmlAttributeValueText content) {
        fXmlAttributeValuesTexts.add(content);
    }

    public List<XQueryXmlAttributeValueText> getXmlAttributeValuesText() {
        return fXmlAttributeValuesTexts;
    }

    public abstract String getNamespacePrefix();

    public XQueryFunctionDecl getFunction(String name) {
        return fFunctionDecls.get(name);
    }

    public XQueryVarDecl getVariable(String name) {
        return fVarDecls.get(name);
    }

    public List<XQueryStringLiteral> getStringLiterals() {
        return fStringLiterals;
    }

    public void addStringLiteral(XQueryStringLiteral string) {
        fStringLiterals.add(string);
    }

    public List<Position> getKeywords() {
        return fKeywordPositions;
    }

    public List<XQueryModuleImport> getImports() {
        return fImports;
    }

    public void setKeywordPositions(List<Position> keywordPositions) {
        fKeywordPositions = keywordPositions;
    }

    public void processChild(ASTNode child) {
        if (child instanceof Statement) {
            Statement statement = (Statement)child;
            switch (statement.getKind()) {
            case XQDTExpressionConstants.XQDT_MODULE_IMPORT:
                addStatement(statement);
                break;
            case XQDTExpressionConstants.XQDT_BASE_URI_DECL:
                addStatement(statement);
                break;
            case Declaration.D_METHOD:
                addStatement(statement);
                break;
            case Declaration.D_VAR_DECL:
                addStatement(statement);
                break;
            }
        }
    }
}