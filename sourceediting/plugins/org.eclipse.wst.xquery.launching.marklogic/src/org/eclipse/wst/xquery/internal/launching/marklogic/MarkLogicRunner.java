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

import java.io.BufferedReader;
import java.io.FileReader;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.debug.core.model.IStreamsProxy;
import org.eclipse.dltk.launching.IInterpreterRunner;
import org.eclipse.dltk.launching.InterpreterConfig;
import org.eclipse.wst.xquery.marklogic.xcc.Request;
import org.eclipse.wst.xquery.marklogic.xcc.ResultSequence;
import org.eclipse.wst.xquery.marklogic.xcc.Session;
import org.eclipse.wst.xquery.marklogic.xcc.exceptions.XQueryException;
import org.eclipse.wst.xquery.marklogic.xcc.jndi.ContentSourceBean;

public class MarkLogicRunner implements IInterpreterRunner {

    MarkLogicInstall install;

    public MarkLogicRunner(MarkLogicInstall install) {
        this.install = install;
    }

    public void run(InterpreterConfig config, ILaunch launch, IProgressMonitor monitor) throws CoreException {
        if (monitor == null) {
            monitor = new NullProgressMonitor();
        }
        monitor.beginTask("Executing on server.", 1);

        MarkLogicProcess process = new MarkLogicProcess(install, config, launch);

        // if (!DLTKDebugLaunchConstants.isDebugConsole(launch)) {
        launch.addProcess(process);
        // }

        process.start();

        monitor.worked(1);
        monitor.done();
    }

    static class MarkLogicProcess extends Thread implements IProcess {

        protected ContentSourceBean contentSource = new ContentSourceBean();
        protected MockStreamsProxy streamsProxy = new MockStreamsProxy();

        protected MarkLogicInstall install;
        protected ILaunch launch;
        protected InterpreterConfig config;
        protected String xccUrl;
        protected String username;
        protected String password;

        public MarkLogicProcess(MarkLogicInstall install, InterpreterConfig config, ILaunch launch) {

            this.install = install;
            this.launch = launch;
            this.config = config;

            String[] parts = install.getInterpreterArgs().trim().split("\\|");
            xccUrl = parts[0];
            username = parts[1];
            password = parts[2];
        }

        @Override
        public void run() {

            try {
                contentSource.setUrl(xccUrl);
            } catch (Throwable e) {
                streamsProxy.err("Unable to create content source.");
                // throw new CoreException(new Status(Status.ERROR, XQDTLaunchingPlugin.PLUGIN_ID, "Unable to create content source.", e));
            }

            Session session = contentSource.newSession(username, password);
            try {
                BufferedReader reader = new BufferedReader(new FileReader(config.getScriptFilePath().toFile()));
                StringBuilder buffer = new StringBuilder();
                String chunk;
                while ((chunk = reader.readLine()) != null) {
                    buffer.append(chunk);
                    buffer.append("\n");
                }
                String source = buffer.toString();

                Request request = session.newAdhocQuery(source);

                /*
                 * Request request = session.newAdhocQuery( "xquery version \"1.0\";\n" +
                 * "declare namespace sdn=\"http://tempuri.org/sdnList.xsd\";\n" + "foo:bar()");
                 * Request request = session.newAdhocQuery( "xquery version \"1.0\";\n" +
                 * "declare namespace sdn=\"http://tempuri.org/sdnList.xsd\";\n" +
                 * "for $entry in doc(\"Sandbox-WebDAV/raw/sdn.xml\")/sdn:sdnList/sdn:sdnEntry\n" +
                 * "    return $entry/sdn:lastName");
                 */

                ResultSequence result = session.submitRequest(request);

                if (result.isEmpty()) {
                    streamsProxy.out("No results.\n");
                } else {
                    streamsProxy.out(result.asString());
                }
            } catch (XQueryException xqe) {
                String message = xqe.toString();
                message = message.substring(message.indexOf(':') + 2);
                streamsProxy.err(message);
            } catch (Throwable e) {
                streamsProxy.err("Execution failed: ");
                streamsProxy.err(e.toString());
                e.printStackTrace();
                // throw new CoreException(new Status(Status.ERROR, XQDTLaunchingPlugin.PLUGIN_ID, "Execution failed.", e));
            } finally {
                session.close();
            }
        }

        @SuppressWarnings("unchecked")
        public Object getAdapter(Class adapter) {
            // TODO Auto-generated method stub
            return null;
        }

        public String getAttribute(String key) {
            // TODO Auto-generated method stub
            return null;
        }

        public int getExitValue() throws DebugException {
            // TODO Auto-generated method stub
            return 0;
        }

        public String getLabel() {
            return "Mark Logic Server " + xccUrl + " [user=" + username + "]";
        }

        public ILaunch getLaunch() {
            return launch;
        }

        public IStreamsProxy getStreamsProxy() {
            return streamsProxy;
        }

        public void setAttribute(String key, String value) {
            // TODO Auto-generated method stub

        }

        public boolean canTerminate() {
            // TODO Auto-generated method stub
            return false;
        }

        public boolean isTerminated() {
            // TODO Auto-generated method stub
            return false;
        }

        public void terminate() throws DebugException {
            // TODO Auto-generated method stub

        }
    }
}
