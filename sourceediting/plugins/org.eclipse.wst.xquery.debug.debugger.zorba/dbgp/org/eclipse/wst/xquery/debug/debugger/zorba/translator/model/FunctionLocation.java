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

public class FunctionLocation {

    @SerializedName("fileName")
    private String fFileName;
    @SerializedName("functionName")
    private String fFunctionName;

    protected FunctionLocation() {
    }

    public FunctionLocation(String fileName, String functionName) {
        fFileName = fileName;
        fFunctionName = functionName;
    }

    public String getFileName() {
        return fFileName;
    }

    // public void setFileName(String fileName) {
    // this.fFileName = fileName;
    // }
    public String getFunctionName() {
        return fFunctionName;
    }

    // public void setFunctionName(String functionName) {
    // this.fFunctionName = functionName;
    // }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof FunctionLocation) {
            FunctionLocation location = (FunctionLocation)obj;
            return location.fFileName.equals(fFileName) && location.fFunctionName == this.fFunctionName;
        }
        return false;
    }

    @Override
    public String toString() {
        return fFileName + " (" + fFunctionName + ")";
    }

    @Override
    public int hashCode() {
        assert false : "hashCode not designed";
        return 28;
    }
}
