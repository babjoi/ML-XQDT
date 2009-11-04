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
package org.eclipse.wst.xquery.ui;

import java.util.List;

import org.eclipse.dltk.core.IField;
import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.core.SourceParserUtil;
import org.eclipse.dltk.ui.ScriptElementLabels;
import org.eclipse.wst.xquery.core.model.ast.XQueryFunctionDecl;
import org.eclipse.wst.xquery.core.model.ast.XQueryLibraryModule;
import org.eclipse.wst.xquery.core.model.ast.XQueryModule;
import org.eclipse.wst.xquery.core.model.ast.XQueryParam;
import org.eclipse.wst.xquery.core.model.ast.XQueryVarDecl;

public class XQDTScriptElementLabels extends ScriptElementLabels {

    @SuppressWarnings("unchecked")
    @Override
    protected void getMethodLabel(IMethod method, long flags, StringBuffer buf) {
        String name = method.getElementName();
        String key = null;
        try {
            key = name + method.getParameters().length;
        } catch (ModelException e) {
            key = name;
        }

        ISourceModule sModule = (ISourceModule)method.getParent();
        XQueryModule xqModule = (XQueryModule)SourceParserUtil.getModuleDeclaration(sModule);
        XQueryFunctionDecl functionDecl = xqModule.getFunction(key);

        buf.append(name);

        // parameters
        buf.append('(');

        if (functionDecl == null) {
            buf.append("...)");
            return;
        }
        List params = functionDecl.getArguments();
        for (int i = 0; i < params.size(); i++) {
            XQueryParam param = (XQueryParam)params.get(i);

            if (i > 0) {
                buf.append(COMMA_STRING);
            }

            buf.append("$" + param.getName());

            if (param.getType() != null) {
                buf.append(" as " + param.getType());
            }
        }

        buf.append(')');

        if (functionDecl.getReturnType() != null) {
            buf.append(" as " + functionDecl.getReturnType());
        }
    }

//    @Override
//    protected void getImportContainerLabel(IModelElement element, long flags, StringBuffer buf) {
//        buf.append(((IImportContainer)element).getContainerName());
//    }
//
//    @Override
//    protected void getImportDeclarationLabel(IModelElement element, long flags, StringBuffer buf) {
//        IImportDeclaration imp = (IImportDeclaration)element;
//        buf.append(imp.getElementName());
//        buf.append(" = '");
//        buf.append(imp.getVersion());
//        buf.append("'");
//    }

    @Override
    protected void getFieldLabel(IField field, long flags, StringBuffer buf) {
        ISourceModule sModule = (ISourceModule)field.getParent();
        XQueryModule xqModule = (XQueryModule)SourceParserUtil.getModuleDeclaration(sModule);
        XQueryVarDecl varDecl = xqModule.getVariable(field.getElementName());

        buf.append("$" + varDecl.getName());

        if (varDecl.getType() != null) {
            buf.append(" as " + varDecl.getType());
        }

    }

    @Override
    public void getDeclarationLabel(IModelElement declaration, long flags, StringBuffer buf) {
        ISourceModule sModule = (ISourceModule)declaration.getParent();
        XQueryLibraryModule xqModule = (XQueryLibraryModule)SourceParserUtil.getModuleDeclaration(sModule);
        buf.append(xqModule.getNamespacePrefix() + " = '" + xqModule.getNamespaceUri().getValue() + "'");
    }
}
