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

public class VariablesMessage extends AbstractCommandMessage {

    public VariablesMessage() {
        super(ICommandSets.COMMAND_SET_DYNAMIC, ICommandSets.COMMAND_VARIABLES);
    }

    // @Override
    // public ReplyMessage buildReplyMessage(int errorCode, Object data) {
    // Variable[] variables = (Variable[])data;
    // Gson g = new Gson();
    // String json = g.toJson(new VariableContainer(variables));
    // return new ReplyMessage(getId(), IErrorCodes.NO_ERROR, json.getBytes());
    // }

    public VariablesPayload getVariables(ReplyMessage reply) {
        Gson g = new Gson();
        String payload = new String(reply.getData());
        return g.fromJson(payload.replaceAll("\\},\\]\\}", "}]}"), VariablesPayload.class);
    }

    @Override
    public byte[] serializeData() {
        return "{\"locals\":[],\"globals\":[]}".getBytes();
    }
}
