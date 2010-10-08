package org.eclipse.wst.xquery.set.debug.debugger.translator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.eclipse.wst.xquery.debug.debugger.zorba.translator.model.BreakpointPosition;

public class SETBreakpointManager {

    private class BreakpointState {
        BreakpointPosition fBreakpoint;
        boolean fEnabled;
        boolean fValid;

        public BreakpointState(BreakpointPosition breakpoint, boolean enabled, boolean valid) {
            fBreakpoint = breakpoint;
            fEnabled = enabled;
            fValid = valid;
        }

        @Override
        public String toString() {
            return fBreakpoint.toString() + " " + (fEnabled ? "ENABLED" : "DISABLED") + ", "
                    + (fValid ? "VALID" : "NOT VALID");
        }
    }

    private Map<Integer, BreakpointState> fBreakpoints = new TreeMap<Integer, BreakpointState>();

    public void clear() {
        fBreakpoints.clear();
    }

    public void addBreakpoint(BreakpointPosition breakpoint, boolean enabled, boolean valid) {
        int id = breakpoint.getId();
        BreakpointState state = new BreakpointState(breakpoint, enabled, valid);
        fBreakpoints.put(id, state);
    }

    public void deleteBreakpoint(int id) {
        fBreakpoints.remove(id);
    }

    public List<BreakpointPosition> getBreakpoints(boolean validOnly) {
        List<BreakpointPosition> breakpoints = new ArrayList<BreakpointPosition>();
        for (BreakpointState state : fBreakpoints.values()) {
            if (state.fEnabled) {
                if (validOnly) {
                    if (state.fValid) {
                        breakpoints.add(state.fBreakpoint);
                    }
                } else {
                    breakpoints.add(state.fBreakpoint);
                }
            }
        }
        return breakpoints;
    }

    public BreakpointPosition enableBreakpoint(int id) {
        return setBreakpointEnablement(id, true);
    }

    public BreakpointPosition disableBreakpoint(int id) {
        return setBreakpointEnablement(id, false);
    }

    private BreakpointPosition setBreakpointEnablement(int id, boolean enabled) {
        BreakpointState state = fBreakpoints.get(id);
        if (state != null) {
            state.fEnabled = enabled;
            return state.fBreakpoint;
        }
        return null;
    }

    public BreakpointPosition setBreakpointState(int id, boolean enabled, boolean pending) {
        BreakpointState state = fBreakpoints.get(id);
        if (state != null) {
            state.fEnabled = enabled;
            state.fValid = pending;
            return state.fBreakpoint;
        }
        return null;
    }

    public BreakpointPosition getBreakpoint(int id) {
        BreakpointState state = fBreakpoints.get(id);
        if (state != null) {
            return state.fBreakpoint;
        }
        return null;
    }

    public boolean isBreakpointValid(int id) {
        BreakpointState state = fBreakpoints.get(id);
        if (state != null) {
            return state.fValid;
        }
        return false;
    }

    @Override
    public String toString() {
        return fBreakpoints.toString();
    }

}
