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

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class ClearMessage extends AbstractCommandMessage {

    @SerializedName("ids")
    private List<Integer> fBreakpointIds = new ArrayList<Integer>();

    public ClearMessage() {
        super(ICommandSets.COMMAND_SET_BREAKPOINTS, ICommandSets.COMMAND_CLEAR);
    }

    public void addBreakpointId(int id) {
        if (fBreakpointIds == null)
            fBreakpointIds = new ArrayList<Integer>();
        fBreakpointIds.add(id);
    }

    public List<Integer> getBreakpointPositions() {
        return fBreakpointIds;
    }

    @Override
    public byte[] serializeData() {
        Gson g = new Gson();
        return g.toJson(this).getBytes();
    }
}
