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

import org.eclipse.wst.xquery.internal.core.parser.antlr.XQueryParser;

public interface IXQDTASTNode extends ICollectible {

    public static int XQUERY_FUNCTION_DECL = XQueryParser.FunctionDecl;
    public static int XQUERY_LIBRARY_MODULE = XQueryParser.LibraryModule;
    public static int XQUERY_MAIN_MODULE = XQueryParser.MainModule;
    public static int XQUERY_MODULE_IMPORT = XQueryParser.ModuleImport;
    public static int XQUERY_PARAM = XQueryParser.Param;
    // public static int XQUERY_QUERY_BODY = 0;
    public static int XQUERY_STRING_LITERAL = XQueryParser.StringLiteral;
    public static int XQUERY_VAR_DECL = XQueryParser.VarDecl;
    // public static int XQUERY_XML_ATTRIBUTE_VALUE_TEXT = 0;
    // public static int XQUERY_XML_ELEMENT_CONTENT_TEXT = 0;

}