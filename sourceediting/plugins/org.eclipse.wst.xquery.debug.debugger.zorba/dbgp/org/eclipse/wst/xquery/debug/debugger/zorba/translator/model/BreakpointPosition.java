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

public abstract class BreakpointPosition {

    @SerializedName("id")
    protected int fId;

    protected BreakpointPosition() {
    }

    public BreakpointPosition(int id) {
        fId = id;
    }

    public int getId() {
        return fId;
    }

    public void setId(int id) {
        fId = id;
    }

    public abstract String getFileName();

}
