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

public class FunctionBreakpointPosition extends BreakpointPosition {

    @SerializedName("func")
    private FunctionLocation fLocation;

    protected FunctionBreakpointPosition() {
        super();
    }

    public FunctionBreakpointPosition(int id, FunctionLocation location) {
        super(id);
        fLocation = location;
    }

    public FunctionLocation getLocation() {
        return fLocation;
    }

    public void setLocation(FunctionLocation location) {
        fLocation = location;
    }

    public String getFileName() {
        return fLocation.getFileName();
    }

    @Override
    public String toString() {
        return fId + " - " + fLocation.toString();
    }

}
