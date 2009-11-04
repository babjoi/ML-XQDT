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

import com.google.gson.annotations.SerializedName;

public class Expression {

    @SerializedName("name")
    protected String fExpr;
    @SerializedName("type")
    protected String fType;
    @SerializedName("value")
    protected String fValue;
    @SerializedName("error")
    protected String fError;

    protected Expression() {
    }

    public Expression(String expression, String type) {
        this(expression, type, null, null);
    }

    public Expression(String expression, String type, String value) {
        this(expression, type, value, "");
    }

    public Expression(String expression, String type, String value, String error) {
        fExpr = expression;
        fType = type;
        fValue = value;
        fError = error;
    }

    public String getExpressionString() {
        return fExpr;
    }

    public void setExpressionString(String expression) {
        fExpr = expression;
    }

    public String getType() {
        return fType;
    }

    public void setType(String type) {
        fType = type;
    }

    public String getValue() {
        return fValue;
    }

    public void setValue(String value) {
        fValue = value;
    }

    public String getError() {
        return fError;
    }

    public void setError(String error) {
        fError = error;
    }

    public boolean isComputed() {
        return fValue != null;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;

        if (obj instanceof Variable) {
            Variable var = (Variable)obj;
            if (fExpr != null && fExpr.equals(var.fExpr))
                return true;
        }
        return false;
    }

}