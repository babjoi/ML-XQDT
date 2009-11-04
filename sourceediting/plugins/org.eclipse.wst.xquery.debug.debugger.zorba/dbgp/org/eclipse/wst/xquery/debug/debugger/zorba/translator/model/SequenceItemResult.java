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

public class SequenceItemResult {

    @SerializedName("result")
    private String fItem;
    @SerializedName("type")
    private String fType;

    public SequenceItemResult() {
    }

    public SequenceItemResult(String item, String type) {
        fItem = item;
        fType = type;
    }

    public String getValue() {
        return fItem;
    }

    public void setItem(String item) {
        fItem = item;
    }

    public String getType() {
        return fType;
    }

    public void setType(String type) {
        fType = type;
    }
}
