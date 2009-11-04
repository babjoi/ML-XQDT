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
package org.eclipse.wst.xquery.debug.dbgp;

public abstract class LineBreakpointElement extends BreakpointElement {

    public LineBreakpointElement(String id, int hitValue, String hitCondition, int hitCount, int lineNumber,
            String fileName) {
        this(id, BreakpointState.ENABLED, hitValue, hitCondition, hitCount, lineNumber, fileName);
    }

    public LineBreakpointElement(String id, BreakpointState state, int hitValue, String hitCondition, int hitCount,
            int lineNumber, String fileName) {
        super(id, state, hitValue, hitCondition, hitCount);
        addAttribute("lineno", "" + lineNumber);
        addAttribute("filename", fileName);
    }

    public BreakpointType getType() {
        return BreakpointType.LINE;
    }

}
