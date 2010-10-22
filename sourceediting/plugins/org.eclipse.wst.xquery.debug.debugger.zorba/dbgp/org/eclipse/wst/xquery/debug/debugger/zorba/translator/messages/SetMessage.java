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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.wst.xquery.debug.debugger.zorba.translator.model.BreakpointPosition;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class SetMessage extends AbstractCommandMessage {

    // We need to make this generic list based on Object otherwise the JSON
    // serialization will consider the abstract type only for serialization
    @SerializedName("breakpoints")
    private List<Object> fBreakpoints = new ArrayList<Object>();

    public SetMessage() {
        super(ICommandSets.COMMAND_SET_BREAKPOINTS, ICommandSets.COMMAND_SET);
    }

    public void addBreakpoint(BreakpointPosition breakpoint) {
        fBreakpoints.add(breakpoint);
    }

    public void addBreakpoints(List<BreakpointPosition> breakpoints) {
        fBreakpoints.addAll(breakpoints);
    }

    public List<BreakpointPosition> getBreakpointPositions() {
        List<BreakpointPosition> result = new ArrayList<BreakpointPosition>();
        for (Object object : fBreakpoints) {
            if (object instanceof BreakpointPosition) {
                result.add((BreakpointPosition)object);
            }
        }
        return result;
    }

    @Override
    public byte[] serializeData() {
        Gson g = new Gson();
        return g.toJson(this).getBytes();
    }

    public SetPayload getReplyPayload(ReplyMessage reply) {
        Gson g = new Gson();
        String payload = new String(reply.getData());
        return g.fromJson(payload.replaceAll("\\},\\]\\}", "}]}"), SetPayload.class);
    }

}
