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

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class EvaluateMessage extends AbstractCommandMessage {

    @SerializedName("id")
    private int fExprId;
    @SerializedName("expr")
    private String fExpression;

    public EvaluateMessage(int id, String expr) {
        super(ICommandSets.COMMAND_SET_DYNAMIC, ICommandSets.COMMAND_EVALUATE);
        fExpression = expr;
        fExprId = id;
    }

    public String getExpression() {
        return fExpression;
    }

    public int getExprId() {
        return fExprId;
    }

    @Override
    public byte[] serializeData() {
        Gson g = new Gson();
        return g.toJson(this).getBytes();
    }
}
