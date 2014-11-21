/*******************************************************************************
 * Copyright (c) 2009 Mark Logic Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sam Neth (Mark Logic) - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.xquery.internal.launching.marklogic;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.wst.xquery.core.semantic.ISemanticValidator;
import org.eclipse.wst.xquery.core.semantic.SemanticCheckError;
import org.eclipse.wst.xquery.marklogic.xcc.Request;
import org.eclipse.wst.xquery.marklogic.xcc.ResultSequence;
import org.eclipse.wst.xquery.marklogic.xcc.Session;
import org.eclipse.wst.xquery.marklogic.xcc.exceptions.XQueryException;
import org.eclipse.wst.xquery.marklogic.xcc.jndi.ContentSourceBean;

public class MarkLogicSemanticValidator implements ISemanticValidator {

    private IInterpreterInstall fInterpreterInstall;

    protected ContentSourceBean contentSource = new ContentSourceBean();
    protected String xccUrl;
    protected MockStreamsProxy streamsProxy = new MockStreamsProxy();

    public MarkLogicSemanticValidator(IInterpreterInstall install) {
        fInterpreterInstall = install;
    }

    protected IInterpreterInstall getInterpreterInstall() {
        return fInterpreterInstall;
    }

    public List<SemanticCheckError> check(ISourceModule module) throws CoreException {

        String[] parts = fInterpreterInstall.getInterpreterArgs().trim().split("\\|");
        String url = parts[0];
        String username = parts[1];
        String password = parts[2];

        try {
            contentSource.setUrl(url);
        } catch (Throwable e) {
            streamsProxy.err("Unable to create content source.");
        }

        Session session = contentSource.newSession(username, password);

        try {
            Request request = session.newAdhocQuery("xquery version \"1.0-ml\";\n" + "xdmp:eval(\""
                    + module.getSource().replace("\"", "\"\"") + ",SEMANTIC:OK\")");

            ResultSequence result = null;

            try {
                result = session.submitRequest(request);

                if (result.isEmpty()) {
                    streamsProxy.err("No request id returned from debugger.\n");
                } else {
                    streamsProxy.out(result.asString());
                }
            } catch (XQueryException e) {
                if (!"XDMP-UNBPRFX".equals(e.getCode()) || !"SEMANTIC".equals(e.getData()[0])) {
                    throw e;
                }
            }
        } catch (XQueryException e) {
            List<SemanticCheckError> errors = new ArrayList<SemanticCheckError>();
            new SemanticCheckError(module.getResource(), e.getCode(), e.getFormatString(),
                    e.getStack()[0].getLineNumber() - 1);
            return errors;
        } catch (Throwable e) {
            streamsProxy.err("Execution failed: ");
            streamsProxy.err(e.toString());
            e.printStackTrace();
            // throw new CoreException(new Status(Status.ERROR, XQDTLaunchingPlugin.PLUGIN_ID,
            // "Execution failed.", e));
        } finally {
            session.close();
        }
        /*
         * IExecutionEnvironment exeEnv = fInterpreterInstall.getExecEnvironment();
         * 
         * String[] cmdLine = buildCommandLine(module);
         * 
         * final Process p = exeEnv.exec(cmdLine, null, null); final StringBuffer[] buffer = new
         * StringBuffer[1];
         * 
         * if (p == null) { abort("Could not invoke the Semantic Validator"); } try { Thread t = new
         * Thread(new Runnable() { public void run() { try { BufferedReader errstr = new
         * BufferedReader(new InputStreamReader(p.getErrorStream())); StringBuffer errorReport = new
         * StringBuffer(); for(String line; (line = errstr.readLine()) != null; ) {
         * errorReport.append(line); } buffer[0] = errorReport; } catch (IOException e) {
         * //abort("Exception while reading the the Semantic Validator output streams"); } } });
         * t.start();
         * 
         * int errorCode = p.waitFor(); if (errorCode == 0) { return null; }
         * 
         * t.join(); if (buffer[0] == null || buffer[0].length() == 0) return null;
         * 
         * //System.out.println("error = " + buffer[0]);
         * 
         * SemanticCheckErrorReportReader builder = new
         * SemanticCheckErrorReportReader(buffer[0].toString()); return builder.getErrors();
         * 
         * } catch (InterruptedException e) {
         * abort("Exception while waiting for the Semantic Validator to terminate"); }
         */
        return null;
    }
}
