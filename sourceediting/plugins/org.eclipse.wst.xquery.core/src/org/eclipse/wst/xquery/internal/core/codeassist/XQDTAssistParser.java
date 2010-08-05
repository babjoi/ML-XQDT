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
package org.eclipse.wst.xquery.internal.core.codeassist;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.codeassist.IAssistParser;
import org.eclipse.dltk.compiler.env.IModuleSource;
import org.eclipse.dltk.core.DLTKLanguageManager;
import org.eclipse.wst.xquery.core.XQDTNature;
import org.eclipse.wst.xquery.internal.core.parser.XQDTSourceParser;

public class XQDTAssistParser implements IAssistParser {

    protected XQDTSourceParser fParser = null;
    protected ModuleDeclaration fModule;
    protected ASTNode fAssistNodeParent = null;

    public XQDTAssistParser() {
        fParser = (XQDTSourceParser)DLTKLanguageManager.getSourceParser(XQDTNature.NATURE_ID);
    }

    public ModuleDeclaration parse(IModuleSource sourceModule) {
        fModule = (ModuleDeclaration)fParser.parse(sourceModule, null);
        fModule.rebuild();

        return fModule;
    }

    public ASTNode getAssistNodeParent() {
        return fAssistNodeParent;
    }

    public ModuleDeclaration getModule() {
        return fModule;
    }

    public void handleNotInElement(ASTNode unit, int position) {
        // TODO Auto-generated method stub

    }

    public void parseBlockStatements(ASTNode node, ASTNode unit, int position) {
        // TODO Auto-generated method stub
    }

    public void setSource(ModuleDeclaration unit) {
        fModule = unit;
    }

}
