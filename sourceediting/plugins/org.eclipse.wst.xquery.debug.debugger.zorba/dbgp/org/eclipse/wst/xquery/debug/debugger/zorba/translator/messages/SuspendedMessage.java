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

import org.eclipse.wst.xquery.debug.debugger.zorba.translator.model.QueryLocation;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class SuspendedMessage extends AbstractCommandMessage {

    public static final int CAUSE_USER = 0x1;
    public static final int CAUSE_BREAKPOINT = 0x2;
    public static final int CAUSE_STEP = 0x3;

    @SerializedName("cause")
    private int fCause;
    @SerializedName("location")
    private QueryLocation fLocation;

    public SuspendedMessage() {
        super(ICommandSets.COMMAND_SET_ENGINE_EVENTS, ICommandSets.COMMAND_SUSPENDED);
    }

    public SuspendedMessage(int cause, QueryLocation location) {
        super(ICommandSets.COMMAND_SET_ENGINE_EVENTS, ICommandSets.COMMAND_SUSPENDED);
        fCause = cause;
        fLocation = location;
    }

    public int getCause() {
        return fCause;
    }

    public void setCause(int cause) {
        this.fCause = cause;
    }

    public boolean hasLocation() {
        return fLocation != null;
    }

    public QueryLocation getLocation() {
        return fLocation;
    }

    public void setLocation(QueryLocation location) {
        fLocation = location;
    }

    @Override
    public byte[] serializeData() {
        Gson g = new Gson();
        return g.toJson(this).getBytes();
    }

}
