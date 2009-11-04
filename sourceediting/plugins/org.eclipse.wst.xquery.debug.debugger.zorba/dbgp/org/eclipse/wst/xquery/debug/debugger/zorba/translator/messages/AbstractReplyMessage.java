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

public abstract class AbstractReplyMessage extends AbstractMessage {

    private int errorCode;

    public AbstractReplyMessage(int id, int errorCode) {
        super(id);
        setFlags(ICommandSets.FLAG_REPLY);
        this.errorCode = errorCode;
    }

    public byte[] toByteArray() throws IOException {
        int size = 11;

        byte[] data = serializeData();
        size += data.length;

        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(outStream);

        dos.writeInt(size);
        dos.writeInt(getId());
        dos.writeByte(getFlags());
        dos.writeShort(errorCode);
        dos.write(data);

        return outStream.toByteArray();
    }

    @Override
    public boolean equals(AbstractMessage message) {
        if (message instanceof AbstractReplyMessage) {
            AbstractReplyMessage replyMessage = (AbstractReplyMessage)message;
            if (this.getId() == replyMessage.getId() && this.getFlags() == replyMessage.getFlags()
                    && this.getErrorCode() == replyMessage.getErrorCode() && isDataEqual(message)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void setFlags(int flags) {
        super.setFlags((flags | 0x80));
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public void deserializeData(byte[] data) {
    }

    @Override
    public byte[] serializeData() {
        return new byte[0];
    }

}
