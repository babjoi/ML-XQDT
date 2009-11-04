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

public class StepMessage extends AbstractCommandMessage {

    @SerializedName("stepType")
    private int fStepType;

    public StepMessage() {
        super(ICommandSets.COMMAND_SET_EXECUTION, ICommandSets.COMMAND_STEP);
    }

    public StepMessage(int stepType) {
        super(ICommandSets.COMMAND_SET_EXECUTION, ICommandSets.COMMAND_STEP);
        fStepType = stepType;
    }

    public int getStepType() {
        return fStepType;
    }

    public void setStepType(int stepType) {
        fStepType = stepType;
    }

    @Override
    public byte[] serializeData() {
        Gson g = new Gson();
        return g.toJson(this).getBytes();
    }
}
