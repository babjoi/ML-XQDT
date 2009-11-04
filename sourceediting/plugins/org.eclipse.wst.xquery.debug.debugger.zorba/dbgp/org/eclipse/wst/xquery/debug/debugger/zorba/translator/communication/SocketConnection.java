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
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public abstract class SocketConnection {

    protected Socket fSocket;
    protected OutputStream fOutput;
    protected InputStream fInput;

    public InputStream getInput() {
        return fInput;
    }

    public OutputStream getOutput() {
        return fOutput;
    }

    public abstract void connect() throws IOException;

    public boolean isOpen() {
        return fSocket != null && !fSocket.isClosed();
    }

    public void close() {
        if (fSocket != null) {
            try {
                fSocket.close();
                fInput.close();
                fOutput.close();
            } catch (IOException e) {
                System.out.println("++++++++++++++++++++++++++");
                e.printStackTrace();
            }
        }

        fSocket = null;
        fInput = null;
        fOutput = null;
    }

    public byte[] readPacket() throws IOException {
        DataInputStream stream;
        synchronized (this) {
            if (!isOpen()) {
                throw new IOException();
            }
            stream = new DataInputStream(fInput);
        }
        synchronized (stream) {
            int packetLength = 0;
            try {
                packetLength = stream.readInt();
            } catch (IOException e) {
                throw new IOException();
            }

            if (packetLength < 11) {
                throw new IOException("Packet under 11 bytes"); //$NON-NLS-1$
            }

            byte[] packet = new byte[packetLength];
            packet[0] = (byte)((packetLength >>> 24) & 0xFF);
            packet[1] = (byte)((packetLength >>> 16) & 0xFF);
            packet[2] = (byte)((packetLength >>> 8) & 0xFF);
            packet[3] = (byte)((packetLength >>> 0) & 0xFF);

            stream.readFully(packet, 4, packetLength - 4);
            return packet;
        }
    }

    public void writePacket(byte[] packet) throws IOException {
        if (!isOpen()) {
            throw new IOException();
        }
        if (packet == null) {
            throw new IllegalArgumentException("Packet cannot be null");
        }
        if (packet.length < 11) {
            throw new IllegalArgumentException("Invalid Packet, must be at least 11 bytes. PacketSize: "
                    + packet.length);
        }

        int packetSize = getPacketLength(packet);
        if (packetSize != packet.length) {
            throw new IllegalArgumentException("Invalid Packet: The pachet size doe not match the length field."
                    + packetSize);
        }

        synchronized (this) {
            if (fOutput == null) {
                throw new IOException();
            }
        }

        synchronized (fOutput) {
            // packet.length can be > packetSize. Sending too much will cause
            // errors on the other side
            fOutput.write(packet, 0, packetSize);
        }
    }

    private int getPacketLength(byte[] packet) {
        int len = 0;
        if (packet.length >= 4) {
            len = (((packet[0] & 0xFF) << 24) + ((packet[1] & 0xFF) << 16) + ((packet[2] & 0xFF) << 8) + ((packet[3] & 0xFF) << 0));
        }
        return len;
    }
}
