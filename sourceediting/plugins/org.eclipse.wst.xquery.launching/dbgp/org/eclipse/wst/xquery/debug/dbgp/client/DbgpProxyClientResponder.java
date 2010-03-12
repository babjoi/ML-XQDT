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
package org.eclipse.wst.xquery.debug.dbgp.client;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.eclipse.dltk.dbgp.internal.DbgpWorkingThread;
import org.eclipse.dltk.dbgp.internal.IDbgpTerminationListener;
import org.eclipse.wst.xquery.debug.core.XQDTDebugCorePlugin;
import org.eclipse.wst.xquery.debug.dbgp.XmlElement;

@SuppressWarnings("restriction")
public class DbgpProxyClientResponder extends DbgpWorkingThread implements IDbgpTerminationListener {

    final private OutputStream fOutputStream;
    final private Queue<XmlElement> fResponseQueue = new ConcurrentLinkedQueue<XmlElement>();

    private final Object fTerminatedLock = new Object();
    private boolean fTerminated = false;

    private DbgpWorkingThread fTranslator;

    public DbgpProxyClientResponder(DbgpWorkingThread translator, OutputStream outputStream) {
        super(DbgpProxyClientResponder.class.getSimpleName());
        fOutputStream = outputStream;

        fTranslator = translator;
        fTranslator.addTerminationListener(this);
    }

    protected void workingCycle() throws Exception {
        try {
            while (!hasTerminated() || !fResponseQueue.isEmpty()) {
                while (fResponseQueue.isEmpty()) {
                    Thread.sleep(150);
                    if (hasTerminated()) {
                        return;
                    }
                }
                XmlElement xe = fResponseQueue.remove();
                String data = xe.toXml();
                String length = "" + data.length();

                fOutputStream.write(length.getBytes());
                fOutputStream.write(0);
                fOutputStream.write(data.getBytes());
                fOutputStream.write(0);
                fOutputStream.flush();
                if (XQDTDebugCorePlugin.DEBUG_DBGP_PROTOCOL) {
                    System.out.println("sent: " + data);
                }
            }
        } catch (InterruptedException ie) {
            // ie.printStackTrace();
            System.err.println("Responder exception: " + ie.getMessage());
            System.out.println("Responder exception: terminating responder");
        } catch (IOException ioe) {
            // ioe.printStackTrace();
            System.err.println("Responder exception: " + ioe.getMessage());
            System.out.println("Responder exception: terminating responder");
        }
    }

    private boolean hasTerminated() {
        return fTerminated;
    }

    public void terminate() {
        fTerminated = true;
    }

    public void send(XmlElement response) {
        if (!fTerminated) {
            fResponseQueue.add(response);
        }
    }

    public void objectTerminated(Object object, Exception e) {
        synchronized (fTerminatedLock) {
            if (fTerminated) {
                return;
            }

            try {
                fOutputStream.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            fTranslator.removeTerminationListener(this);
            try {
                fTranslator.waitTerminated();
            } catch (InterruptedException e1) {
                // OK, interrupted
            }

            fTerminated = true;
        }

        fireObjectTerminated(e);
    }

}