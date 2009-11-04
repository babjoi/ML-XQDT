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
package org.eclipse.wst.xquery.debug.debugger.zorba.translator.model;

public class Variable extends Expression {

    private transient boolean fIsGlobal = false;

    @SuppressWarnings("unused")
    private Variable() {
    }

    public Variable(String name, String type, boolean isGlobal) {
        super(name, type);
        fIsGlobal = isGlobal;
    }

    public Variable(String name, String type, String value, String error, boolean isGlobal) {
        super(name, type, value, error);
        fIsGlobal = isGlobal;
    }

    public String getName() {
        return fExpr;
    }

    @Override
    public String getExpressionString() {
        return "$" + getName();
    }

    public boolean isGlobal() {
        return fIsGlobal;
    }

    public void setIsGlobal(boolean isGlobal) {
        fIsGlobal = isGlobal;
    }
}
