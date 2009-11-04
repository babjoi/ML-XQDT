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

public class QueryLocation {

    @SerializedName("fileName")
    private String fFileName;
    @SerializedName("lineBegin")
    private int fLineBegin;
    @SerializedName("columnBegin")
    private int fColumnBegin;
    @SerializedName("lineEnd")
    private int fLineEnd;
    @SerializedName("columnEnd")
    private int fColumnEnd;

    protected QueryLocation() {
    }

    public QueryLocation(String fileName, int lineBegin, int columnBegin, int lineEnd, int columnEnd) {
        fFileName = fileName;
        fLineBegin = lineBegin;
        fColumnBegin = columnBegin;
        fLineEnd = lineEnd;
        fColumnEnd = columnEnd;
    }

    public String getFileName() {
        return fFileName;
    }

    // public void setFileName(String fileName) {
    // this.fFileName = fileName;
    // }
    public int getLineBegin() {
        return fLineBegin;
    }

    // public void setLineBegin(int lineBegin) {
    // this.fLineBegin = lineBegin;
    // }
    public int getColumnBegin() {
        return fColumnBegin;
    }

    // public void setColumnBegin(int columnBegin) {
    // this.fColumnBegin = columnBegin;
    // }
    public int getLineEnd() {
        return fLineEnd;
    }

    // public void setLineEnd(int lineEnd) {
    // this.fLineEnd = lineEnd;
    // }
    public int getColumnEnd() {
        return fColumnEnd;
    }

    // public void setColumnEnd(int columnEnd) {
    // this.fColumnEnd = columnEnd;
    // }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof QueryLocation) {
            QueryLocation location = (QueryLocation)obj;
            return location.fFileName.equals(fFileName) && location.fLineBegin == this.fLineBegin
                    && location.fColumnBegin == this.fColumnBegin && location.fLineEnd == this.fLineEnd;
        }
        return false;
    }
}