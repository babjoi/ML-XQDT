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
package org.eclipse.wst.xquery.set.debug.debugger.translator;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.IBreakpointManager;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.core.IScriptFolder;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.core.SourceParserUtil;
import org.eclipse.dltk.dbgp.DbgpRequest;
import org.eclipse.dltk.dbgp.internal.DbgpWorkingThread;
import org.eclipse.dltk.dbgp.internal.IDbgpTerminationListener;
import org.eclipse.dltk.dbgp.internal.utils.Base64Helper;
import org.eclipse.dltk.internal.debug.core.model.ScriptMarkerFactory;
import org.eclipse.wst.xquery.core.model.ast.XQueryLibraryModule;
import org.eclipse.wst.xquery.debug.dbgp.DbgpResponse;
import org.eclipse.wst.xquery.debug.dbgp.InitPacket;
import org.eclipse.wst.xquery.debug.dbgp.client.DbgpProxyClientReceiver;
import org.eclipse.wst.xquery.debug.dbgp.client.DbgpProxyClientResponder;
import org.eclipse.wst.xquery.debug.dbgp.client.IDbgpConstants;
import org.eclipse.wst.xquery.debug.dbgp.client.IDbgpTranslator;
import org.eclipse.wst.xquery.debug.debugger.zorba.ZorbaDebuggerPlugin;
import org.eclipse.wst.xquery.debug.debugger.zorba.translator.IDebugEventListener;
import org.eclipse.wst.xquery.debug.debugger.zorba.translator.ZorbaDebuggerEngine;
import org.eclipse.wst.xquery.debug.debugger.zorba.translator.messages.AbstractCommandMessage;
import org.eclipse.wst.xquery.debug.debugger.zorba.translator.messages.ClearMessage;
import org.eclipse.wst.xquery.debug.debugger.zorba.translator.messages.EvaluatedMessage;
import org.eclipse.wst.xquery.debug.debugger.zorba.translator.messages.ICommandSets;
import org.eclipse.wst.xquery.debug.debugger.zorba.translator.messages.ReplyMessage;
import org.eclipse.wst.xquery.debug.debugger.zorba.translator.messages.SetMessage;
import org.eclipse.wst.xquery.debug.debugger.zorba.translator.messages.SetPayload;
import org.eclipse.wst.xquery.debug.debugger.zorba.translator.messages.SuspendedMessage;
import org.eclipse.wst.xquery.debug.debugger.zorba.translator.messages.TerminatedMessage;
import org.eclipse.wst.xquery.debug.debugger.zorba.translator.messages.VariablesMessage;
import org.eclipse.wst.xquery.debug.debugger.zorba.translator.messages.VariablesPayload;
import org.eclipse.wst.xquery.debug.debugger.zorba.translator.model.BreakpointPosition;
import org.eclipse.wst.xquery.debug.debugger.zorba.translator.model.QueryLocation;
import org.eclipse.wst.xquery.debug.debugger.zorba.translator.model.Variable;
import org.eclipse.wst.xquery.set.core.utils.SETProjectUtil;
import org.eclipse.wst.xquery.set.debug.debugger.SETDebuggerPlugin;
import org.eclipse.wst.xquery.set.launching.SETLaunchUtil;

@SuppressWarnings("restriction")
public class SETDbgpTranslator extends DbgpWorkingThread implements IDbgpTranslator, IDebugEventListener,
        IDbgpTerminationListener {

    private static final Map<String, String> SUPPORTED_FEATURES = new HashMap<String, String>();

    static {
        SUPPORTED_FEATURES.put("language_supports_threads", "0");
        SUPPORTED_FEATURES.put("language_name", "XQuery");
        SUPPORTED_FEATURES.put("language_version", "1.1");
        SUPPORTED_FEATURES.put("encoding", "UTF-8");
        SUPPORTED_FEATURES.put("protocol_version", "1");
        SUPPORTED_FEATURES.put("supports_async", "1");
        SUPPORTED_FEATURES.put("breakpoint_types", "line");
        SUPPORTED_FEATURES.put("multiple_sessions", "0");
        SUPPORTED_FEATURES.put("max_children", "32");
        SUPPORTED_FEATURES.put("max_data", "1024");
        SUPPORTED_FEATURES.put("max_depth", "1");
    }

    private IScriptProject fProject;
    private String fIdeKey;
    private URI fFileUri;

    private DbgpProxyClientReceiver fReceiver;
    private DbgpProxyClientResponder fResponder;

    private SETDebuggerEngine fEngine;

    private boolean fStarted = false;
    private boolean fTerminated = false;

    public SETDbgpTranslator(IScriptProject project, InetAddress ideAdress, int port, String ideKey, URI fileUri,
            String debuggerPorts) throws IOException {
        super(SETDbgpTranslator.class.getSimpleName());

        fProject = project;

        String[] ports = debuggerPorts.split(":");
        fEngine = new SETDebuggerEngine(Integer.parseInt(ports[0]), Integer.parseInt(ports[1]));
        fEngine.addTerminationListener(this);
        fEngine.addDebugEventListener(this);

        fEngine.connect();

        Socket client = new Socket(ideAdress, port);
        fReceiver = new DbgpProxyClientReceiver(this, new DataInputStream(client.getInputStream()));
        fResponder = new DbgpProxyClientResponder(this, new DataOutputStream(client.getOutputStream()));

        fReceiver.addCommandListener(this);

        fIdeKey = ideKey;
        fFileUri = fileUri;
    }

    public void commandReceived() {
        processNext();
    }

    public void init() {
        if (ZorbaDebuggerPlugin.DEBUG_DBGP_TRANSLATOR) {
            System.out.println("Initializing DBGP translator.");
        }
        fReceiver.start();
        fResponder.start();

        InitPacket ip = new InitPacket(fIdeKey, Thread.currentThread().getId(), fFileUri);
        fResponder.send(ip);
    }

    protected synchronized void workingCycle() throws Exception {
        try {
            while (true) {
                try {
                    if (!fReceiver.hasAvailableCommand()) {
                        wait();
                        if (fTerminated) {
                            break;
                        }
                    }

                    DbgpRequest request = fReceiver.retrieveCommand();
                    if (request != null) {
                        DbgpResponse response = processCommand(request);
                        if (response != null) {
                            fResponder.send(response);
                        }
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
            if (ZorbaDebuggerPlugin.DEBUG_DBGP_TRANSLATOR) {
                System.out.println("Exiting the command translator");
            }
        } catch (Exception e) {
            e.printStackTrace();
            fireObjectTerminated(e);
            return;
        }

        fireObjectTerminated(null);
    }

    private synchronized void processNext() {
        notify();
    }

    private DbgpRequest fLastContinuationCommand;
    private DbgpRequest fLastStackGetCommand;
    private SuspendedMessage fLastSuspendedEvent;
    private boolean fGlobalTerminate;

    private Map<Integer, BreakpointPosition> fPendingBreakpoints = new TreeMap<Integer, BreakpointPosition>();
    private Map<Integer, BreakpointPosition> fDisabledBreakpoints = new TreeMap<Integer, BreakpointPosition>();
    private Map<Integer, BreakpointPosition> fToDisableBreakpoints = new TreeMap<Integer, BreakpointPosition>();

    private synchronized DbgpResponse processCommand(final DbgpRequest request) {
        DbgpResponse response = null;
        String command = request.getCommand();
        if (command.equals(IDbgpConstants.COMMAND_FEATURE_SET)) {
            response = new DbgpResponse(request);
            response.addAttribute("feature", request.getOption("-n"));
            response.addAttribute("success", "1");
        } else if (command.equals(IDbgpConstants.COMMAND_FEATURE_GET)) {
            response = new DbgpResponse(request);
            String name = request.getOption("-n");
            String value = SUPPORTED_FEATURES.get(name);
            response.addAttribute("feature", name);
            response.addAttribute("supported", (value != null ? "1" : "0"));
            if (value != null) {
                response.setData(SUPPORTED_FEATURES.get(name));
            }
        } else if (command.equals(IDbgpConstants.COMMAND_STDOUT)) {
            response = new DbgpResponse(request);
            response.addAttribute("success", "1");
        } else if (command.equals(IDbgpConstants.COMMAND_STDERR)) {
            response = new DbgpResponse(request);
            response.addAttribute("success", "1");
        } else if (command.equals(IDbgpConstants.COMMAND_RUN)) {
            fLastContinuationCommand = request;
            if (!fStarted) {
                while (!fEngine.isInitialized()) {
                    try {
                        Thread.sleep(250);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                // send all set breakpoint to the engine in one SET message
                setPendingBreakpoints();

                fStarted = true;
                fEngine.run();
            } else {
                fEngine.resume();
            }
        } else if (command.equals(IDbgpConstants.COMMAND_STEP_INTO)) {
            fLastContinuationCommand = request;
            fEngine.step(ZorbaDebuggerEngine.STEP_INTO);
        } else if (command.equals(IDbgpConstants.COMMAND_STEP_OUT)) {
            fLastContinuationCommand = request;
            fEngine.step(ZorbaDebuggerEngine.STEP_RETURN);
        } else if (command.equals(IDbgpConstants.COMMAND_STEP_OVER)) {
            fLastContinuationCommand = request;
            fEngine.step(ZorbaDebuggerEngine.STEP_OVER);
        } else if (command.equals(IDbgpConstants.COMMAND_STOP)) {
            fGlobalTerminate = true;
            fLastContinuationCommand = request;
            fEngine.terminate();
            response = new DbgpResponse(request);
            response.addAttribute("status", IDbgpConstants.STATUS_STOPPED);
            response.addAttribute("reason", IDbgpConstants.REASON_OK);
            fireObjectTerminated(null);
        } else if (command.equals(IDbgpConstants.COMMAND_BREAK)) {
            fLastContinuationCommand = request;
            fEngine.suspend();
        } else if (command.equals(IDbgpConstants.COMMAND_STACK_GET)) {
            fLastStackGetCommand = request;
            processStackRequestAndSuspendedMesage();
        } else if (command.equals(IDbgpConstants.COMMAND_CONTEXT_NAMES)) {
            response = new DbgpResponse(request);
            response.setData("<context name='Local' id='0'/>" + "<context name='Global' id='1'/>");
        } else if (command.equals(IDbgpConstants.COMMAND_CONTEXT_GET)) {
            response = new DbgpResponse(request);
            String contextID = request.getOption("-c");
            response.addAttribute("context", contextID);

            VariablesMessage varsCmd = new VariablesMessage();
            varsCmd.setFlags(ICommandSets.FLAG_EVALUATE);
            ReplyMessage reply = (ReplyMessage)fEngine.sendCommand(varsCmd);
            VariablesPayload payload = varsCmd.getVariables(reply);

            List<Variable> variables = payload.getGlobalVariables();
            if (contextID == null) {
                variables.addAll(payload.getLocalVariables());
            }
            if (contextID.equals("0")) {
                variables = payload.getLocalVariables();
            }

            StringBuffer data = new StringBuffer();
            for (Variable variable : variables) {
                String varProperty = evaluateVariable(variable);
                data.append(varProperty);
            }
            response.setData(data.toString());
        } else if (command.equals(IDbgpConstants.COMMAND_PROPERTY_SET)) {
            response = new DbgpResponse(request);
            response.addAttribute("success", "0");
        } else if (command.equals(IDbgpConstants.COMMAND_BREAKPOINT_SET)) {
            // construct the query location
            int line = Integer.parseInt(request.getOption("-n"));
            String filename = request.getOption("-f");
            String namespace = getModuleNamespace(filename);
            QueryLocation ql = new QueryLocation(namespace, line, 0, line, 0);

            int id = Integer.parseInt(request.getOption("-i"));
            BreakpointPosition breakpoint = new BreakpointPosition(id, ql);

            String state = request.getOption("-s");
            if (state.equals("enabled")) {
                if (!fEngine.isInitialized()) {
                    // add to pending breakpoint for later sending
                    fPendingBreakpoints.put(id, breakpoint);
                } else {
                    // build the SET message and sent it now
                    SetMessage set = new SetMessage();
                    set.addBreakpoint(breakpoint);
                    fEngine.sendCommand(set);
                }
            } else {
                fDisabledBreakpoints.put(id, breakpoint);
            }

            response = new DbgpResponse(request);
            response.addAttribute("state", state);
            response.addAttribute("id", "" + id);
        } else if (command.equals(IDbgpConstants.COMMAND_BREAKPOINT_GET)) {
            response = new DbgpResponse(request);
            response.addAttribute("success", "0");
        } else if (command.equals(IDbgpConstants.COMMAND_BREAKPOINT_REMOVE)) {
            int id = Integer.parseInt(request.getOption("-d"));

            if (fEngine.isInitialized()) {
                ClearMessage clear = new ClearMessage();
                clear.addBreakpointId(id);
                fEngine.sendCommand(clear);
            } else {
                fPendingBreakpoints.remove(id);
            }
            response = new DbgpResponse(request);
        } else if (command.equals(IDbgpConstants.COMMAND_BREAKPOINT_UPDATE)) {
            int id = Integer.parseInt(request.getOption("-d"));
            String state = request.getOption("-s");

            BreakpointPosition position = fToDisableBreakpoints.remove(id);
            if (position == null) {

                if (state.equals("enabled")) {
                    BreakpointPosition breakpoint = fDisabledBreakpoints.remove(id);
                    if (fEngine.isInitialized()) {
                        if (breakpoint != null) {
                            // build and send the SET message
                            SetMessage set = new SetMessage();
                            set.addBreakpoint(breakpoint);
                            fEngine.sendCommand(set);
                        }
                    } else {
                        // add to pending breakpoint for later sending
                        fPendingBreakpoints.put(id, breakpoint);
                    }
                }
            }
            response = new DbgpResponse(request);
            response.addAttribute("state", state);
            response.addAttribute("id", "" + id);
        } else if (command.equals(IDbgpConstants.COMMAND_SOURCE)) {
            response = new DbgpResponse(request);
            response.addAttribute("success", "0");
        } else if (command.equals(IDbgpConstants.COMMAND_EVAL)) {
            final int tID = Integer.parseInt(request.getOption("-i"));
            fEngine.addEvalEventListener(new IDebugEventListener() {
                public void handleDebugEvent(AbstractCommandMessage event) {
                    if (event instanceof EvaluatedMessage) {
                        EvaluatedMessage evald = (EvaluatedMessage)event;

                        fEngine.removeDebugEventListener(this);

                        DbgpResponse response = new DbgpResponse(request);
                        response.addAttribute("success", "1");
                        String encodedValue = Base64Helper.encodeString(evald.getResults());
                        String type = evald.getType();
                        response.setData("<property type='" + type + "' encoding='base64'>" + encodedValue
                                + "</property>");
                        fResponder.send(response);
                    }
                }
            }, 0);

            fEngine.evaluate(request.getData(), tID);
        }
        return response;
    }

    private String buildVarProperty(String name, String type, String value) {
        StringBuffer data = new StringBuffer();

        data.append("<property ");
        data.append("name='" + name + "' ");
        data.append("fullname='" + name + "' ");
        data.append("type='" + type + "' ");
        data.append("constant='0' ");
        data.append("encoding='base64'>");
        String encodedValue = Base64Helper.encodeString(value);
        data.append(encodedValue);
        data.append("</property>");

        return data.toString();
    }

    private class SynchronizingEvalListener implements IDebugEventListener {
        private Object fBlockedObject;

        private EvaluatedMessage fEvaluated;

        public SynchronizingEvalListener(Object blockdObject) {
            fBlockedObject = blockdObject;
        }

        public void handleDebugEvent(AbstractCommandMessage event) {
            if (event instanceof EvaluatedMessage) {
                fEngine.removeEvalEventListener(this);
                fEvaluated = (EvaluatedMessage)event;

                if (fBlockedObject != null) {
                    try {
                        synchronized (fBlockedObject) {
                            fBlockedObject.notify();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        public EvaluatedMessage getEvaluated() {
            return fEvaluated;
        }

    }

    private String evaluateVariable(Variable variable) {
        // correct the variable name (to be a valid XQuery expression)
        String name = variable.getName();
        String type = "";
        String value = "";
        if (name.equals("$$dot")) {
            name = ".";
        } else {
            name = "$" + name;
        }

        Object lock = new Object();
        SynchronizingEvalListener listener = new SynchronizingEvalListener(lock);
        fEngine.addEvalEventListener(listener);
        fEngine.evaluate(name, listener.hashCode());

        synchronized (lock) {
            try {
                lock.wait();
            } catch (InterruptedException e) {
                return "";
            }
        }

        EvaluatedMessage result = listener.getEvaluated();
        if (result != null) {
            type = result.getType();
            value = result.getResults();
        }

        return buildVarProperty(name, type, value);
    }

    private synchronized void processStackRequestAndSuspendedMesage() {
        if (fLastStackGetCommand == null || fLastSuspendedEvent == null) {
            return;
        }

        QueryLocation ql = fLastSuspendedEvent.getLocation();
        String filename = ql.getFileName();
        if (filename.startsWith("file:/")) {
            try {
                URI fileUri = new URI(filename);
                filename = new Path(fileUri.getPath()).toOSString();
            } catch (URISyntaxException e) {
            }
        }
        filename = new File(filename).toURI().toString();

        int lineno = ql.getLineBegin();
        String cmdbegin = ql.getLineBegin() + ":" + (ql.getColumnBegin() - 1);
        String cmdend = ql.getLineEnd() + ":" + (ql.getColumnEnd() - 2);

        // send an answer to the stack_get command
        DbgpResponse response = new DbgpResponse(fLastStackGetCommand);
        response.setData("<stack " + "level='0' " + "type='file' " + "filename='" + filename + "' " +
        // "filename='xquery://main_module' " +
                "lineno='" + lineno + "' " + "cmdbegin='" + cmdbegin + "' " + "cmdend='" + cmdend + "' " + "/>");
        fResponder.send(response);

        fLastSuspendedEvent = null;
        fLastStackGetCommand = null;
    }

    private String getModuleNamespace(String filename) {
        try {
            URI fileUri = new URI(filename);
            String fileStr = fileUri.getPath();
            IPath filePath = new Path(fileStr);
            IPath projectPath = fProject.getResource().getLocation();

            if (projectPath.isPrefixOf(filePath)) {
                try {
                    IPath folder = filePath.removeFirstSegments(projectPath.segmentCount()).removeLastSegments(1);
                    IScriptFolder scriptFolder = fProject.findScriptFolder(fProject.getPath().append(folder));
                    ISourceModule module = scriptFolder.getSourceModule(filePath.lastSegment());

                    if (module instanceof ISourceModule && module.exists()) {
                        ModuleDeclaration decl = SourceParserUtil.getModuleDeclaration(module);
                        if (decl instanceof XQueryLibraryModule) {
                            XQueryLibraryModule library = (XQueryLibraryModule)decl;
                            return library.getNamespaceUri().getValue();
                        }
                    }
                } catch (ModelException e) {
                }
            }
        } catch (URISyntaxException use) {
        }

        return null;
    }

    public void handleDebugEvent(AbstractCommandMessage event) {
        if (ZorbaDebuggerPlugin.DEBUG_ZORBA_DEBUG_PROTOCOL) {
            System.out.println("Received event: " + event.toString());
        }
        DbgpResponse response = null;
        if (event instanceof TerminatedMessage) {
            if (!fGlobalTerminate) {
                try {
                    System.out.println("Reconnecting the engine after request termination...");
                    SETLaunchUtil.bringBrowserOnTop();
                    fEngine.reconnect();
                    while (!fEngine.isInitialized()) {
                        Thread.sleep(250);
                    }

                    setPendingBreakpoints();

                    fEngine.run();
                    Thread.sleep(10000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                fireObjectTerminated(null);
            }
        } else if (event instanceof SuspendedMessage) {
            SuspendedMessage sm = (SuspendedMessage)event;
            fLastSuspendedEvent = sm;
            processStackRequestAndSuspendedMesage();

            response = new DbgpResponse(fLastContinuationCommand);
            if (sm.getCause() == SuspendedMessage.CAUSE_USER) {
                // send an answer to a break command
                response.addAttribute("success", "1");
            } else {
                // send an answer to a continuation command
                response.addAttribute("status", IDbgpConstants.STATUS_BREAK);
                response.addAttribute("reason", IDbgpConstants.REASON_OK);
            }
            fResponder.send(response);
        }
    }

    private void setPendingBreakpoints() {
        // send all set breakpoint to the engine in one SET message
        if (fPendingBreakpoints.size() > 0) {
            SetMessage setMessage = new SetMessage();
            for (BreakpointPosition breakpoint : fPendingBreakpoints.values()) {
                setMessage.addBreakpoint(breakpoint);
            }
            // send the command to the debugger engine
            ReplyMessage reply = (ReplyMessage)fEngine.sendCommand(setMessage);

            // check for "not set" breakpoints and disable them
            SetPayload paylod = setMessage.getReplyPayload(reply);
            for (BreakpointPosition breakpoint : paylod.getBreakpointPositions()) {
                // if the breakpoint was not set disable it
                if (breakpoint.getLocation().getFileName().equals("")) {
                    int id = breakpoint.getId();
                    BreakpointPosition originalBreakpoint = fPendingBreakpoints.remove(id);
                    fDisabledBreakpoints.put(id, originalBreakpoint);

                    // disable it in the UI
                    try {
                        notifyBreakpointDisabled(originalBreakpoint);
                    } catch (CoreException ce) {
                        SETDebuggerPlugin
                                .getDefault()
                                .getLog()
                                .log(new Status(IStatus.ERROR, SETDebuggerPlugin.PLUGIN_ID,
                                        "An error ocured while disabling breakpoints.", ce));
                    }
                }
            }
        }
    }

    private void notifyBreakpointDisabled(BreakpointPosition originalBreakpoint) throws CoreException {
        IBreakpointManager manager = DebugPlugin.getDefault().getBreakpointManager();

        String moduleNamespace = originalBreakpoint.getLocation().getFileName();
        IFile file = SETProjectUtil.getModuleFileForNamespace(fProject.getProject(), moduleNamespace);
        if (file == null) {
            return;
        }

        // find the appropriate marker in the file
        IMarker[] markers = file.findMarkers(null, false, IResource.DEPTH_ZERO);
        if (markers.length == 0) {
            return;
        }
        IMarker breakMarker = null;
        for (IMarker marker : markers) {
            int line = marker.getAttribute(IMarker.LINE_NUMBER, -1);
            if (line == -1) {
                continue;
            }
            if (marker.getType().equals(ScriptMarkerFactory.LINE_BREAKPOINT_MARKER_ID)
                    && line == originalBreakpoint.getLocation().getLineBegin()) {
                breakMarker = marker;
                break;
            }
        }

        // no marker matches the criteria, so nothing to do
        if (breakMarker == null) {
            return;
        }

        IBreakpoint breakpoint = manager.getBreakpoint(breakMarker);
        if (breakpoint != null) {
            fToDisableBreakpoints.put(originalBreakpoint.getId(), originalBreakpoint);
            breakpoint.setEnabled(false);
        }
    }

    public void objectTerminated(Object object, Exception e) {
        try {
            if (ZorbaDebuggerPlugin.DEBUG_DBGP_TRANSLATOR) {
                System.out.println("waiting for engine");
            }
            fEngine.waitTerminated();
            if (ZorbaDebuggerPlugin.DEBUG_DBGP_TRANSLATOR) {
                System.out.println("waited for engine");
            }
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        fTerminated = true;

        synchronized (this) {
            notify();
        }

    }

}
