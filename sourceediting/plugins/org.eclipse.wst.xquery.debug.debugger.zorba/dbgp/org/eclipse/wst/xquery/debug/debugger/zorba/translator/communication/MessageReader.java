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

import org.eclipse.wst.xquery.debug.debugger.zorba.translator.messages.AbstractMessage;
import org.eclipse.wst.xquery.debug.debugger.zorba.translator.messages.MessageFactory;
import org.eclipse.wst.xquery.debug.debugger.zorba.translator.messages.MessageFormatException;

public class MessageReader {

    private DataInputStream fInputStream;

    public MessageReader(InputStream inputStream) {
        fInputStream = new DataInputStream(inputStream);
    }

    public synchronized AbstractMessage readMessage() throws ProtocolException, IOException {
        MessageHeader header = readMessageHeader();
        if (header.getMessageLength() == 1481721953 && header.getId() == 1852076904 && header.getFlags() == 97) {
            return MessageFactory.buildHandshake();
        }
        byte[] data = readMessageData(header);
        AbstractMessage message = MessageFactory.buildMessage(header, data);
        return message;
    }

    private MessageHeader readMessageHeader() throws ProtocolException, IOException {
        int length = fInputStream.readInt();
        if (length < 11) {
            throw new MessageFormatException();
        }

        int id = fInputStream.readInt();
        if (id < 0) {
            throw new MessageFormatException();
        }

        int oneByte = fInputStream.read() & 0xFF;
        if (oneByte == -1) {
            throw new MessageFormatException();
        }
        int flags = oneByte;
        boolean isReply = (flags & 0x80) != 0;

        if (isReply) {
            int errorCode = fInputStream.readShort() & 0xFFFF;
            if (errorCode < 0) {
                throw new MessageFormatException();
            }

            return new ReplyMessageHeader(length, id, flags, errorCode);
        }

        int commandSet = fInputStream.readByte() & 0xFF;
        if (commandSet < 0) {
            throw new MessageFormatException();
        }

        int command = fInputStream.readByte() & 0xFF;
        if (command < 0) {
            throw new MessageFormatException();
        }

        return new RequestMessageHeader(length, id, flags, commandSet, command);

    }

    private byte[] readMessageData(MessageHeader header) throws ProtocolException, IOException {
        if (header.getMessageLength() == 11) {
            return null;
        }

        byte[] ba = new byte[header.getMessageLength() - 11];

        int bytesRead = fInputStream.read(ba);
        if (bytesRead != header.getMessageLength() - 11) {
            throw new MessageFormatException();
        }

        return ba;
    }

    public void close() throws IOException {
        fInputStream.close();
    }
}
