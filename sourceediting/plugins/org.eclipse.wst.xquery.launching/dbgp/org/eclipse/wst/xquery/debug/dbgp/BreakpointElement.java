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

public abstract class BreakpointElement extends XmlElement {

    public enum BreakpointType {
        LINE, CALL, RETURN, EXCEPTION, CONDITIONAL, WATCH
    }

    public enum BreakpointState {
        ENABLED("enabled"), DISABLED("disabled");

        private final String fState;

        private BreakpointState(String state) {
            fState = state;
        }

        @Override
        public String toString() {
            return fState;
        }
    }

    public BreakpointElement(String id, int hitValue, String hitCondition, int hitCount) {
        this(id, BreakpointState.ENABLED, hitValue, hitCondition, hitCount);
    }

    public BreakpointElement(String id, BreakpointState state, int hitValue, String hitCondition, int hitCount) {
        super("breakpoint");
        addAttribute("id", id);
        addAttribute("state", state.toString());
        addAttribute("hit_value", "" + hitValue);
        addAttribute("hit_condition", hitCondition);
        addAttribute("hit_count", "" + hitCount);
    }

    public abstract BreakpointType getType();

}
