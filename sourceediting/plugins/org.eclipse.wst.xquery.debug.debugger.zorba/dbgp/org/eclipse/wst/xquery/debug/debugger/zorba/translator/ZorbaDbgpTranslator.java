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
package org.eclipse.wst.xquery.debug.debugger.zorba.translator;

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

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.core.SourceParserUtil;
import org.eclipse.dltk.dbgp.DbgpRequest;
import org.eclipse.dltk.dbgp.internal.DbgpWorkingThread;
import org.eclipse.dltk.dbgp.internal.IDbgpTerminationListener;
import org.eclipse.dltk.dbgp.internal.utils.Base64Helper;
import org.eclipse.wst.xquery.core.model.ast.XQueryLibraryModule;
import org.eclipse.wst.xquery.core.model.ast.XQueryMainModule;
import org.eclipse.wst.xquery.debug.dbgp.DbgpResponse;
import org.eclipse.wst.xquery.debug.dbgp.InitPacket;
import org.eclipse.wst.xquery.debug.dbgp.client.DbgpProxyClientReceiver;
import org.eclipse.wst.xquery.debug.dbgp.client.DbgpProxyClientResponder;
import org.eclipse.wst.xquery.debug.dbgp.client.IDbgpConstants;
import org.eclipse.wst.xquery.debug.dbgp.client.IDbgpTranslator;
import org.eclipse.wst.xquery.debug.debugger.zorba.ZorbaDebuggerPlugin;
import org.eclipse.wst.xquery.debug.debugger.zorba.translator.messages.AbstractCommandMessage;
import org.eclipse.wst.xquery.debug.debugger.zorba.translator.messages.ClearMessage;
import org.eclipse.wst.xquery.debug.debugger.zorba.translator.messages.EvaluatedMessage;
import org.eclipse.wst.xquery.debug.debugger.zorba.translator.messages.ICommandSets;
import org.eclipse.wst.xquery.debug.debugger.zorba.translator.messages.ReplyMessage;
import org.eclipse.wst.xquery.debug.debugger.zorba.translator.messages.SetMessage;
import org.eclipse.wst.xquery.debug.debugger.zorba.translator.messages.SuspendedMessage;
import org.eclipse.wst.xquery.debug.debugger.zorba.translator.messages.TerminateMessage;
import org.eclipse.wst.xquery.debug.debugger.zorba.translator.messages.TerminatedMessage;
import org.eclipse.wst.xquery.debug.debugger.zorba.translator.messages.VariablesMessage;
import org.eclipse.wst.xquery.debug.debugger.zorba.translator.messages.VariablesPayload;
import org.eclipse.wst.xquery.debug.debugger.zorba.translator.model.LineBreakpointPosition;
import org.eclipse.wst.xquery.debug.debugger.zorba.translator.model.QueryLocation;
import org.eclipse.wst.xquery.debug.debugger.zorba.translator.model.Variable;

@SuppressWarnings("restriction")
public class ZorbaDbgpTranslator extends DbgpWorkingThread implements IDbgpTranslator, IDebugEventListener,
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

    private ZorbaDebuggerEngine fEngine;

    private boolean fStarted = false;
    private boolean fTerminated = false;

    public ZorbaDbgpTranslator(IScriptProject project, InetAddress ideAdress, int port, String ideKey, URI fileUri,
            String debuggerPorts) throws IOException {
        super(ZorbaDbgpTranslator.class.getSimpleName());

        fProject = project;

        String[] ports = debuggerPorts.split(":");
        fEngine = new ZorbaDebuggerEngine(Integer.parseInt(ports[0]), Integer.parseInt(ports[1]));
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
            String type = request.getOption("-t");
            String state = request.getOption("-s");
            //boolean enabled = state.equals(BreakpointState.ENABLED.toString());
            // set the id of this breakpoint to this DBGP command id
            int id = Integer.parseInt(request.getOption("-i"));
            String filename = request.getOption("-f");
            //String namespace = getModuleNamespace(filename);

            if (type.equals("line")) {
                int line = Integer.parseInt(request.getOption("-n"));

                // TODO: this should be the final code when Zorba can work with namespaces
                // String namespace = getModuleNamespace(filename);

                // the following lines are just a temporary solution
                // ------------------------------
                String namespace = null;
                try {
                    URI fileUri = new URI(filename);
                    namespace = new Path(fileUri.getPath()).toOSString();
                } catch (URISyntaxException e) {
                }
                // ------------------------------

                if (namespace != null) {
                    QueryLocation ql = new QueryLocation(namespace, line, 0, line, 0);

                    SetMessage set = new SetMessage();
                    set.addBreakpoint(new LineBreakpointPosition(id, ql));
                    fEngine.sendCommand(set);

                    response = new DbgpResponse(request);
                    response.addAttribute("state", state);
                    response.addAttribute("id", "" + id);
                }
            } else {
                state = "disabled";
            }

            response = new DbgpResponse(request);
            response.addAttribute("state", state);

        } else if (command.equals(IDbgpConstants.COMMAND_BREAKPOINT_GET)) {
            response = new DbgpResponse(request);
            response.addAttribute("success", "0");
        } else if (command.equals(IDbgpConstants.COMMAND_BREAKPOINT_REMOVE)) {
            ClearMessage clear = new ClearMessage();
            clear.addBreakpointId(Integer.parseInt(request.getOption("-d")));
            fEngine.sendCommand(clear);
            response = new DbgpResponse(request);
        } else if (command.equals(IDbgpConstants.COMMAND_BREAKPOINT_UPDATE)) {
            response = new DbgpResponse(request);
            response.addAttribute("success", "0");
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

    @SuppressWarnings("unused")
    private String getModuleNamespace(String filename) {
        try {
            URI fileUri = new URI(filename);
            String fileStr = fileUri.getPath();
            IPath filePath = new Path(fileStr);
            IPath projectPath = fProject.getResource().getLocation();

            if (projectPath.isPrefixOf(filePath)) {
                IPath relativePath = filePath.removeFirstSegments(projectPath.segmentCount());
                relativePath = relativePath.setDevice(null);
                try {
                    IModelElement element = fProject.findElement(relativePath);
                    if (element instanceof ISourceModule) {
                        ModuleDeclaration decl = SourceParserUtil.getModuleDeclaration((ISourceModule)element);
                        if (decl instanceof XQueryMainModule) {
                            return "main module";
                        } else if (decl instanceof XQueryLibraryModule) {
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
            response = new DbgpResponse(fLastContinuationCommand);
            response.addAttribute("status", IDbgpConstants.STATUS_STOPPING);
            response.addAttribute("reason", IDbgpConstants.REASON_OK);
            fResponder.send(response);

            try {
                // this is needed in case zorba finishes execution
                // in order to terminate the process. Zorba blocks
                // in a loop and does not check the internal termination
                // status until smth else is received on the wire.
                // so we send a Terminated command (anything can be sent)
                fEngine.sendCommand(new TerminateMessage());
            } catch (Exception e) {
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