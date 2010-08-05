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
package org.eclipse.wst.xquery.internal.core.parser.visitors;

import java.util.List;

import org.eclipse.dltk.ast.declarations.Declaration;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.compiler.ISourceElementRequestor;
import org.eclipse.dltk.compiler.SourceElementRequestVisitor;
import org.eclipse.wst.xquery.core.model.ast.XQDTExpressionConstants;
import org.eclipse.wst.xquery.core.model.ast.XQueryFunctionDecl;
import org.eclipse.wst.xquery.core.model.ast.XQueryModuleDecl;
import org.eclipse.wst.xquery.core.model.ast.XQueryParam;
import org.eclipse.wst.xquery.core.model.ast.XQueryQueryBody;
import org.eclipse.wst.xquery.core.model.ast.XQueryVarDecl;
import org.eclipse.wst.xquery.core.model.compiler.IXQDTSourceElementRequestor.FunctionInfo;
import org.eclipse.wst.xquery.core.model.compiler.IXQDTSourceElementRequestor.VariableInfo;

public class XQDTSourceElementRequestorVisitor extends SourceElementRequestVisitor {

    public XQDTSourceElementRequestorVisitor(ISourceElementRequestor requesor) {
        super(requesor);
    }

    @SuppressWarnings("rawtypes")
    public boolean visit(MethodDeclaration method) throws Exception {
        XQueryFunctionDecl function = (XQueryFunctionDecl)method;
        List args = function.getArguments();

        String[] parameterNames = new String[args.size()];
        String[] parameterTypes = new String[args.size()];
        for (int a = 0; a < args.size(); a++) {
            XQueryParam param = (XQueryParam)args.get(a);
            parameterNames[a] = param.getName();
            parameterTypes[a] = param.getType();
        }

        FunctionInfo fi = new FunctionInfo();
        fi.parameterNames = parameterNames;
        fi.parameterTypes = parameterTypes;
        fi.name = function.getName();
        fi.modifiers = function.getModifiers();
        fi.nameSourceStart = function.getNameStart();
        fi.nameSourceEnd = function.getNameEnd() - 1;
        fi.declarationStart = function.sourceStart();
        fi.isExternal = function.isExternal();

        this.fRequestor.enterMethod(fi);

        this.fInMethod = true;
        this.fCurrentMethod = function;

        return true;
    }

    public boolean endvisit(MethodDeclaration method) throws Exception {
        XQueryFunctionDecl function = (XQueryFunctionDecl)method;

        this.fRequestor.exitMethod(function.sourceEnd());
        this.fInMethod = false;
        this.fCurrentMethod = null;

        return true;
    }

    public boolean visit(XQueryVarDecl varDecl) throws Exception {
        VariableInfo vi = new VariableInfo();
        vi.name = varDecl.getName();
        vi.modifiers = varDecl.getModifiers();
        vi.nameSourceStart = varDecl.getNameStart();
        vi.nameSourceEnd = varDecl.getNameEnd() - 1;
        vi.declarationStart = varDecl.sourceStart();
        vi.isExternal = varDecl.isExternal();
        vi.isConstant = varDecl.isConstant();

        this.fRequestor.enterField(vi);

        return true;
    }

    public boolean endvisit(XQueryVarDecl varDecl) throws Exception {
        this.fRequestor.exitField(varDecl.sourceEnd());

        return true;
    }

//    public boolean visit(XQueryModuleImport modImp) throws Exception {
//        ImportInfo ii = new ImportInfo();
//        ii.name = modImp.getName();
//        ii.sourceStart = modImp.sourceStart();
//        ii.sourceEnd = modImp.sourceEnd();
//        ii.version = modImp.getNamespaceUri().getValue();
//        // ii.nameSourceStart = modImp.getNameStart();
//        // ii.nameSourceEnd = modImp.getNameEnd();
//        ii.containerName = "module imports";
//
//        this.fRequestor.acceptImport(ii);
//
//        return true;
//    }
//
//    public boolean endvisit(XQueryModuleImport modImp) throws Exception {
//        return true;
//    }

    public boolean visit(XQueryModuleDecl moduleDecl) throws Exception {
        this.fRequestor
                .acceptPackage(moduleDecl.sourceStart(), moduleDecl.sourceEnd(), moduleDecl.getNamespacePrefix());

        return true;
    }

    public boolean endvisit(XQueryModuleDecl moduleDecl) throws Exception {
        return true;
    }

    public boolean visit(XQueryQueryBody queryBody) throws Exception {
        // this.fRequestor.acceptPackage(moduleDecl.sourceStart(),
        // moduleDecl.sourceEnd(),
        // moduleDecl.getNamespacePrefix().toCharArray());

        return true;
    }

    public boolean endvisit(XQueryQueryBody queryBody) throws Exception {
        return true;
    }

    @Override
    public boolean visit(Statement statement) throws Exception {
        switch (statement.getKind()) {
        case Declaration.D_VAR_DECL:
            return visit((XQueryVarDecl)statement);
        case XQDTExpressionConstants.XQDT_MODULE_DECL:
            return visit((XQueryModuleDecl)statement);
//        case XQDTExpressionConstants.XQDT_MODULE_IMPORT:
//            return visit(statement);
        default:
            return super.visit(statement);
        }
    }

    @Override
    public boolean endvisit(Statement statement) throws Exception {
        switch (statement.getKind()) {
        case Declaration.D_VAR_DECL:
            return endvisit((XQueryVarDecl)statement);
        case XQDTExpressionConstants.XQDT_MODULE_DECL:
            return endvisit((XQueryModuleDecl)statement);
//        case XQDTExpressionConstants.XQDT_MODULE_IMPORT:
//            return endvisit(statement);
        default:
            return super.endvisit(statement);
        }
    }

    @Override
    public boolean visit(Expression expression) throws Exception {
        switch (expression.getKind()) {
        case XQDTExpressionConstants.XQDT_QUERY_BODY:
            return visit((XQueryQueryBody)expression);
        default:
            return super.visit(expression);
        }
    }

    @Override
    public boolean endvisit(Expression expression) throws Exception {
        switch (expression.getKind()) {
        case XQDTExpressionConstants.XQDT_QUERY_BODY:
            return endvisit((XQueryQueryBody)expression);
        default:
            return super.endvisit(expression);
        }
    }
}
