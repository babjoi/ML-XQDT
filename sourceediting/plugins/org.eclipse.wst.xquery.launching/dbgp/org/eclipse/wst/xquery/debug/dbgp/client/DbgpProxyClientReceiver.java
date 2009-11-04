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
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.eclipse.dltk.dbgp.DbgpRequest;
import org.eclipse.dltk.dbgp.exceptions.DbgpException;
import org.eclipse.dltk.dbgp.internal.DbgpWorkingThread;
import org.eclipse.dltk.dbgp.internal.IDbgpTerminationListener;
import org.eclipse.wst.xquery.debug.dbgp.DbgpRequestParser;

@SuppressWarnings("restriction")
public class DbgpProxyClientReceiver extends DbgpWorkingThread implements IDbgpTerminationListener {

    final private InputStream fInputStream;
    final private Queue<DbgpRequest> fCommandQueue = new ConcurrentLinkedQueue<DbgpRequest>();

    List<IDbgpCommandListener> fListeners = new ArrayList<IDbgpCommandListener>(1);

    private final Object fTerminatedLock = new Object();
    private boolean fTerminated = false;

    private DbgpWorkingThread fTranslator;

    public DbgpProxyClientReceiver(DbgpWorkingThread translator, InputStream inputStream) {
        super(DbgpProxyClientReceiver.class.getSimpleName());
        fInputStream = inputStream;

        fTranslator = translator;
        fTranslator.addTerminationListener(this);
    }

    protected void workingCycle() throws Exception {
        try {
            while (!fTerminated) {
                StringBuffer sb = new StringBuffer();
                int c = fInputStream.read();
                while (c != -1 && c != 0) {
                    sb.append((char)c);
                    c = fInputStream.read();
                }
                if (c == 0)
                    enqueueCommand(DbgpRequestParser.parse(sb.toString()));
            }
        } catch (IOException ioe) {
            // ioe.printStackTrace();
            System.err.println("Receiver exception: " + ioe.getMessage());
            System.out.println("Receiver exception: terminating receiver");
        } catch (DbgpException de) {
            // de.printStackTrace();
            System.err.println("Receiver exception: " + de.getMessage());
            System.out.println("Receiver exception: terminating receiver");
        }
    }

    public DbgpRequest retrieveCommand() {
        return fCommandQueue.poll();
    }

    public boolean hasAvailableCommand() {
        return !fCommandQueue.isEmpty();
    }

    private void enqueueCommand(DbgpRequest request) {
        fCommandQueue.offer(request);
        System.out.println("enqueued: " + request.toString());
        notifyListeners();
    }

    private void notifyListeners() {
        for (IDbgpCommandListener listener : fListeners) {
            listener.commandReceived();
        }
    }

    public void addCommandListener(IDbgpCommandListener listener) {
        fListeners.add(listener);
    }

    public void removeCommandListener(IDbgpCommandListener listener) {
        fListeners.remove(listener);
    }

    public void objectTerminated(Object object, Exception e) {
        synchronized (fTerminatedLock) {
            if (fTerminated)
                return;

            try {
                fInputStream.close();
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