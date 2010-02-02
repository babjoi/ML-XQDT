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
package org.eclipse.wst.xquery.debug.debugger.zorba.translator.messages;

import java.util.List;

import org.eclipse.wst.xquery.debug.debugger.zorba.translator.model.SequenceItemResult;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class EvaluatedMessage extends AbstractCommandMessage {

    @SerializedName("id")
    private int fExprId;
    @SerializedName("expr")
    private String fExpr;
    @SerializedName("results")
    private List<SequenceItemResult> fResultSequence;
    @SerializedName("error")
    private String fError;

    public EvaluatedMessage() {
        super(ICommandSets.COMMAND_SET_ENGINE_EVENTS, ICommandSets.COMMAND_EVALUATED);
    }

    public EvaluatedMessage(int exprID, String expr, List<SequenceItemResult> sequence, String error) {
        super(ICommandSets.COMMAND_SET_ENGINE_EVENTS, ICommandSets.COMMAND_EVALUATED);
        fExprId = exprID;
        fExpr = expr;
        fResultSequence = sequence;
        fError = error;
    }

    public String getError() {
        return fError;
    }

    public void setErrorCode(String error) {
        fError = error;
    }

    public int getExprId() {
        return fExprId;
    }

    public void setExprId(int exprId) {
        fExprId = exprId;
    }

    public String getExpr() {
        return fExpr;
    }

    public void setExpr(String expr) {
        fExpr = expr;
    }

    public List<SequenceItemResult> getResultSequence() {
        return fResultSequence;
    }

    public void setResultSequence(List<SequenceItemResult> sequence) {
        fResultSequence = sequence;
    }

    public String getResults() {
        if (fError.length() > 0) {
            return fError;
        }
        if (fResultSequence == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (SequenceItemResult item : fResultSequence) {
            sb.append(item.getValue() + " ");
        }
        String result = sb.toString().trim();
        if (fResultSequence.size() == 1) {
            return result;
        }
        return "(" + result + ")";
    }

    public String getType() {
        if (fError.length() > 0) {
            return "string";
        }
        if (fResultSequence.size() == 1) {
            return fResultSequence.get(0).getType();
        }
        return "string";
    }

    @Override
    public byte[] serializeData() {
        Gson g = new Gson();
        return g.toJson(this).getBytes();
    }
}
