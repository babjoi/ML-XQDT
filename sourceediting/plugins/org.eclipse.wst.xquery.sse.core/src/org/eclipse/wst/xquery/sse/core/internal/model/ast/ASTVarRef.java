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
package org.eclipse.wst.xquery.sse.core.internal.model.ast;

import java.util.List;

import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocument;
import org.eclipse.wst.validation.internal.provisional.core.IReporter;
import org.eclipse.wst.validation.internal.provisional.core.IValidator;
import org.eclipse.wst.xquery.sse.core.internal.XQueryMessages;
import org.eclipse.wst.xquery.sse.core.internal.model.ModelHelper;

/**
 * Variable reference
 * 
 * @author <a href="villard@us.ibm.com">Lionel Villard</a>
 */
public class ASTVarRef extends ASTNode {

    // Methods

    /** Gets the variable reference name */
    public String getName() {
        if (firstRegion != null) {
            return ASTHelper.variableNameAsString(firstRegion);
        }

        return null;
    }

    /**
     * Check if variable definition is in scope
     * 
     * @return
     */
    protected boolean isVariableInScope() {
        final String varName = getName();
        final List<String> vars = ModelHelper.getInScopeVariables(this);
        return vars != null && vars.contains(varName);
    }

    // Overrides

    @Override
    public int getType() {
        return VARREF;
    }

    @Override
    public void staticCheck(IStructuredDocument document, IValidator validator, IReporter reporter) {
        // It is a static error [err:XPST0008] to reference a variable that is
        // not in scope.
        if (!isVariableInScope()) {
            ASTHelper.reportError(firstRegion, null, XQueryMessages.errorXQST0088_VR_UI_, validator, reporter);
        }

        super.staticCheck(document, validator, reporter);
    }

}
