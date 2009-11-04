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
package org.eclipse.wst.xquery.debug.debugger.zorba.translator.messages;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public abstract class AbstractCommandMessage extends AbstractMessage {

    private static int idgen;

    private transient int commandSet;
    private transient int command;

    public AbstractCommandMessage(int commandSet, int command) {
        super(nextId());
        this.commandSet = commandSet;
        this.command = command & 0xFF;
    }

    public byte[] toByteArray() {
        int size = 11;

        byte[] data = serializeData();
        if (data.length > 0)
            size += data.length;

        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(outStream);

        try {
            dos.writeInt(size);
            dos.writeInt(getId());
            dos.writeByte(getFlags());
            dos.writeByte(commandSet);
            dos.writeByte(command);

            dos.write(data);
        } catch (IOException e) {
        }

        return outStream.toByteArray();
    }

    @Override
    public boolean equals(AbstractMessage message) {
        if (message instanceof AbstractCommandMessage) {
            AbstractCommandMessage commandMessage = (AbstractCommandMessage)message;
            if (this.getId() == commandMessage.getId() && this.getFlags() == commandMessage.getFlags()
                    && this.getCommandSet() == commandMessage.getCommandSet()
                    && this.getCommand() == commandMessage.getCommand() && isDataEqual(message)) {
                return true;
            }
        }
        return false;
    }

    public int getCommand() {
        return command;
    }

    public void setCommand(int command) {
        this.command = command;
    }

    public int getCommandSet() {
        return commandSet;
    }

    public void setCommandSet(int commandSet) {
        this.commandSet = commandSet;
    }

    public AbstractReplyMessage buildReplyMessage(int errorCode) {
        return new ReplyMessage(getId(), errorCode);
    }

    public AbstractReplyMessage buildReplyMessage(int errorCode, Object data) {
        return new ReplyMessage(getId(), errorCode);
    }

    @Override
    public byte[] serializeData() {
        return new byte[0];
    }

    @Override
    public void deserializeData(byte[] data) {
    }

    protected static int nextId() {
        return ++idgen;
    }
}
