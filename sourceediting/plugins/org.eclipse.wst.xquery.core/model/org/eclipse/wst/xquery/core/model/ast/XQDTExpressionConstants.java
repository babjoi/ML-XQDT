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

import org.eclipse.dltk.ast.expressions.ExpressionConstants;

public class XQDTExpressionConstants {

    private XQDTExpressionConstants() {
    }

    public static final int XQDT_MODULE_DECL = ExpressionConstants.USER_EXPRESSION_START + 1;

    public static final int XQDT_MODULE_IMPORT = ExpressionConstants.USER_EXPRESSION_START + 2;

    public static final int XQDT_SCHEMA_IMPORT = ExpressionConstants.USER_EXPRESSION_START + 3;

    public static final int XQDT_BASE_URI_DECL = ExpressionConstants.USER_EXPRESSION_START + 4;

    public static final int XQDT_QUERY_BODY = ExpressionConstants.USER_EXPRESSION_START + 5;

    // public static final int XQDT_FUNCTION_DECL = ExpressionConstants.USER_EXPRESSION_START + 5;
}
