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

import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.core.runtime.ListenerList;
import org.eclipse.core.runtime.SafeRunner;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.IStreamListener;
import org.eclipse.debug.core.model.IStreamMonitor;

public class MockStreamMonitor implements IStreamMonitor {

    private ListenerList fListeners = new ListenerList();
    private StringBuffer fContents;

    public MockStreamMonitor() {
        fContents = new StringBuffer();
    }

    public synchronized void addListener(IStreamListener listener) {
        fListeners.add(listener);
    }

    private void fireStreamAppended(String text) {
        getNotifier().notifyAppend(text);
    }

    public synchronized String getContents() {
        return fContents.toString();
    }

    public synchronized void write(String text) {
        fContents.append(text);
        fireStreamAppended(text);
    }

    public synchronized void removeListener(IStreamListener listener) {
        fListeners.remove(listener);
    }

    private ContentNotifier getNotifier() {
        return new ContentNotifier();
    }

    class ContentNotifier implements ISafeRunnable {

        private IStreamListener fListener;
        private String fText;

        public void handleException(Throwable exception) {
            DebugPlugin.log(exception);
        }

        public void run() throws Exception {
            fListener.streamAppended(fText, MockStreamMonitor.this);
        }

        public void notifyAppend(String text) {
            if (text == null)
                return;
            fText = text;
            Object[] copiedListeners = fListeners.getListeners();
            for (int i = 0; i < copiedListeners.length; i++) {
                fListener = (IStreamListener)copiedListeners[i];
                SafeRunner.run(this);
            }
            fListener = null;
            fText = null;
        }
    }
}
