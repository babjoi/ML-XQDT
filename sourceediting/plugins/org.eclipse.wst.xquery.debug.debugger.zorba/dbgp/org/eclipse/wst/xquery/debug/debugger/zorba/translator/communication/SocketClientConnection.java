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

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;

import org.eclipse.wst.xquery.debug.debugger.zorba.ZorbaDebuggerPlugin;

public final class SocketClientConnection extends SocketConnection {

    private static final byte[] handshakeBytes = "XQHandshake".getBytes();

    private int fPort;
    private int fHandshakeTimeout = 50000;
    private String fHost;

    public SocketClientConnection(int port) {
        this("127.0.0.1", port);
    }

    public SocketClientConnection(String host, int port) {
        fHost = host;
        fPort = port;
    }

    public SocketClientConnection(String host, int port, int handshakeTimeout) {
        fHost = host;
        fPort = port;
        fHandshakeTimeout = handshakeTimeout;
    }

    public void connect() throws IOException {
        fSocket = new Socket(fHost, fPort);
        fInput = fSocket.getInputStream();
        fOutput = fSocket.getOutputStream();

        performHandshake(fHandshakeTimeout);
    }

    public void performHandshake(int timeout) throws TransportTimeoutException {
        final boolean[] handshakeCompleted = new boolean[1];

        Thread t = new Thread(new Runnable() {

            public void run() {
                try {
                    fOutput.write(handshakeBytes);

                    DataInputStream in = new DataInputStream(fInput);
                    byte[] handshakeInput = new byte[handshakeBytes.length];
                    in.readFully(handshakeInput);
                    if (!Arrays.equals(handshakeInput, handshakeBytes)) {
                        throw new IOException("Received invalid handshake");
                    }
                } catch (IOException e) {
                    throw new ProtocolException("Protocol handshake error", e);
                }
                if (ZorbaDebuggerPlugin.DEBUG_DEBUGGER_ENGINE) {
                    System.out.println("Handshake successful");
                }
                handshakeCompleted[0] = true;
            }
        });
        t.setDaemon(true);
        t.start();

        try {
            t.join(timeout);
        } catch (InterruptedException e1) {
        }

        if (handshakeCompleted[0]) {
            return;
        }

        try {
            fInput.close();
            fOutput.close();
        } catch (IOException e) {
        }

        throw new TransportTimeoutException();
    }
}
