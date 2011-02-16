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

import org.eclipse.wst.xquery.sse.core.internal.model.ast.ASTVisitor;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.IASTContextItem;

/**
 * Context item '.'
 * 
 * @author <a href="villard@us.ibm.com">Lionel Villard</a>
 */
public class ASTContextItem extends ASTNode implements IASTContextItem {

    // Overrides

    @Override
    public int getType() {
        return CONTEXTITEM;
    }

    @Override
    protected void accept0(ASTVisitor visitor) {
        visitor.visit(this);

        visitor.endVisit(this);
    }
}
