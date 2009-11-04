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
package org.eclipse.wst.xquery.debug.debugger.zorba.translator.communication;

import java.io.IOException;
import java.net.ServerSocket;

public final class SocketServerConnection extends SocketConnection {

    private ServerSocket fServerSocket;
    private int fPort;

    public SocketServerConnection(int port) {
        fPort = port;
    }

    public void connect() throws IOException {
        fServerSocket = new ServerSocket(fPort);
        fServerSocket.setSoTimeout(0);
        fSocket = fServerSocket.accept();
        fInput = fSocket.getInputStream();
        fOutput = fSocket.getOutputStream();
    }

    public void close() {
        super.close();

        if (fServerSocket != null) {
            try {
                fServerSocket.close();
            } catch (IOException e) {
            }
        }
        fServerSocket = null;
    }
}
