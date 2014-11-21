package org.eclipse.wst.xquery.debug.debugger.zorba;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.ILogListener;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.IBreakpointManager;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.dltk.dbgp.exceptions.DbgpDebuggingEngineException;

public class ZorbaDbgpLogListener implements ILogListener {

    public void logging(IStatus status, String plugin) {
        if (status.getException() instanceof DbgpDebuggingEngineException) {
            DbgpDebuggingEngineException exception = (DbgpDebuggingEngineException)status.getException();
            String message = exception.getMessage();
            String toSearchLine = "breakpoint could not be set at line ";
            String toSearchFile = " in file: ";

            int index = message.indexOf(toSearchLine);
            if (index > 0) {
                int starting = index + toSearchLine.length();
                int upto = message.indexOf(" ", starting);
                int line = Integer.parseInt(message.substring(starting, upto));
                String file = message.substring(message.indexOf(toSearchFile) + toSearchFile.length());

                IBreakpointManager manager = DebugPlugin.getDefault().getBreakpointManager();
                IBreakpoint[] brks = manager.getBreakpoints();

                for (IBreakpoint brk : brks) {
                    if (brk.getMarker().getResource() instanceof IFile) {
                        IFile brkFile = (IFile)brk.getMarker().getResource();
                        String brkFilePath = brkFile.getLocation().toOSString();
                        if (brkFilePath.equals(file)) {
                            int brkLine = brk.getMarker().getAttribute(IMarker.LINE_NUMBER, -1);
                            if (brkLine == line) {
                                try {
                                    manager.removeBreakpoint(brk, true);
                                } catch (CoreException ce) {
                                }
                                break;
                            }
                        }
                    }
                }

            }

            System.out.println(plugin + ": " + status.getMessage());
        }
    }
}
