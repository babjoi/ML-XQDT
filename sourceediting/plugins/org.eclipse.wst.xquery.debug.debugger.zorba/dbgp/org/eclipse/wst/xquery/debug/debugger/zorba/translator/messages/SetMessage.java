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

    @SerializedName("breakpoints")
    private List<BreakpointPosition> fBreakpoints = new ArrayList<BreakpointPosition>();

    public SetMessage() {
        super(ICommandSets.COMMAND_SET_BREAKPOINTS, ICommandSets.COMMAND_SET);
    }

    public void addBreakpoint(BreakpointPosition breakpoint) {
        if (fBreakpoints == null)
            fBreakpoints = new ArrayList<BreakpointPosition>();
        fBreakpoints.add(breakpoint);
    }

    public List<BreakpointPosition> getBreakpointPositions() {
        return fBreakpoints;
    }

    @Override
    public byte[] serializeData() {
        Gson g = new Gson();
        return g.toJson(this).getBytes();
    }
}