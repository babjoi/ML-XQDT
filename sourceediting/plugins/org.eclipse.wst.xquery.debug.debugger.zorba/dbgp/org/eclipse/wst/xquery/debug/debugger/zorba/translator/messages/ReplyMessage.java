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

import java.util.Arrays;

public class ReplyMessage extends AbstractReplyMessage {

    private byte[] fData;

    public ReplyMessage(int id, int errorCode) {
        super(id, errorCode);
    }

    public ReplyMessage(int id, int errorCode, String message) {
        super(id, errorCode);
        if (message != null)
            fData = message.getBytes();
    }

    public ReplyMessage(int id, int errorCode, byte[] data) {
        super(id, errorCode);
        fData = data;
    }

    public byte[] getData() {
        return fData;
    }

    public void setData(byte[] data) {
        fData = data;
    }

    @Override
    public byte[] serializeData() {
        if (fData == null)
            return new byte[0];
        return fData;
    }

    @Override
    public void deserializeData(byte[] data) {
        fData = data;
    }

    @Override
    protected boolean isDataEqual(AbstractMessage message) {
        if (message instanceof ReplyMessage && Arrays.equals(((ReplyMessage)message).fData, fData))
            return true;
        return false;
    }
}
