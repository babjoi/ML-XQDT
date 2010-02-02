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

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.eclipse.wst.xquery.debug.debugger.zorba.translator.communication.MessageHeader;
import org.eclipse.wst.xquery.debug.debugger.zorba.translator.communication.MessageReader;
import org.eclipse.wst.xquery.debug.debugger.zorba.translator.communication.ProtocolException;
import org.eclipse.wst.xquery.debug.debugger.zorba.translator.communication.ReplyMessageHeader;
import org.eclipse.wst.xquery.debug.debugger.zorba.translator.communication.RequestMessageHeader;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;

public class MessageFactory implements ICommandSets {

    public static AbstractCommandMessage buildCommand(int command) {
        return buildCommand(command, null);
    }

    public static AbstractCommandMessage buildCommand(int command, byte[] data) {
        int commandSet = (command >> 8) & 0xFF;
        if (data == null) {
            data = "{}".getBytes();
        }

        Gson g = new Gson();

        System.out.println("Building message with data: " + new String(data));
        try {
            switch (commandSet) {
            case COMMAND_SET_EXECUTION:
                switch (command) {
                case COMMAND_RUN:
                    return g.fromJson(new String(data), RunMessage.class);
                case COMMAND_SUSPEND:
                    return g.fromJson(new String(data), SuspendMessage.class);
                case COMMAND_RESUME:
                    return g.fromJson(new String(data), ResumeMessage.class);
                case COMMAND_TERMINATE:
                    return g.fromJson(new String(data), TerminateMessage.class);
                case COMMAND_STEP:
                    return g.fromJson(new String(data), StepMessage.class);
                }
            case COMMAND_SET_ENGINE_EVENTS:
                switch (command) {
                case COMMAND_STARTED:
                    return g.fromJson(new String(data), StartedMessage.class);
                case COMMAND_SUSPENDED:
                    try {
                        g.fromJson(new String(data), SuspendedMessage.class);
                    } catch (JsonParseException e) {
                        e.printStackTrace();
                        data = new String(data).replaceAll("\\\\", "\\\\\\\\").getBytes();
                    }
                    return g.fromJson(new String(data), SuspendedMessage.class);
                case COMMAND_RESUMED:
                    return g.fromJson(new String(data), ResumedMessage.class);
                case COMMAND_TERMINATED:
                    return g.fromJson(new String(data), TerminatedMessage.class);
                case COMMAND_EVALUATED:
                    return g.fromJson(new String(data), EvaluatedMessage.class);
                }
            case COMMAND_SET_BREAKPOINTS:
                switch (command) {
                case COMMAND_SET:
                    return g.fromJson(new String(data), SetMessage.class);
                case COMMAND_CLEAR:
                    return g.fromJson(new String(data), ClearMessage.class);
                }
            case COMMAND_SET_DYNAMIC:
                switch (command) {
                case COMMAND_VARIABLES:
                    return g.fromJson(new String(data), VariablesMessage.class);
                case COMMAND_FOCUS:
                    return null;
                case COMMAND_TIME:
                    return null;
                case COMMAND_DOCUMENTS:
                    return null;
                case COMMAND_COLLECTIONS:
                    return null;
                case COMMAND_COLLECTION:
                    return null;
                case COMMAND_FRAMES:
                    return g.fromJson(new String(data), FramesMessage.class);
                }
            }
            throw new InvalidCommandException(command);

        } catch (JsonParseException jpe) {
            throw new ProtocolException(jpe.getMessage(), jpe);
        }
    }

    public static AbstractMessage buildReply(int errorCode, byte[] data) {
        return new ReplyMessage(555, errorCode, data);
    }

    public static AbstractMessage buildReply(int errorCode, String message) {
        return new ReplyMessage(555, errorCode, message);
    }

    public static AbstractMessage buildMessage(MessageHeader header, byte[] data) throws InvalidCommandException {
        if (header instanceof ReplyMessageHeader) {
            AbstractReplyMessage message = null;
            ReplyMessageHeader replyHeader = (ReplyMessageHeader)header;
            message = (AbstractReplyMessage)buildReply(replyHeader.getErrorCode(), data);
            message.setId(replyHeader.getId());
            message.setLength(replyHeader.getMessageLength());
            message.setFlags(replyHeader.getFlags());
            message.setErrorCode(replyHeader.getErrorCode());

            return message;
        } else if (header instanceof RequestMessageHeader) {
            AbstractCommandMessage message = null;
            RequestMessageHeader requestHeader = (RequestMessageHeader)header;
            message = buildCommand((requestHeader.getCommandSet() << 8) | requestHeader.getCommand(), data);
            message.setId(requestHeader.getId());
            message.setLength(requestHeader.getMessageLength());
            message.setFlags(requestHeader.getFlags());
            message.setCommandSet(requestHeader.getCommandSet());
            message.setCommand(requestHeader.getCommand());

            return message;
        }

        return null;
    }

    public static AbstractMessage buildMessage(byte[] serialized) {
        MessageReader reader = new MessageReader(new ByteArrayInputStream(serialized));
        try {
            return reader.readMessage();
        } catch (IOException e) {
        }

        return null;
    }

    public static AbstractMessage buildHandshake() {
        return new AbstractReplyMessage(0, 0) {

            @Override
            public byte[] toByteArray() throws IOException {
                return "XQHandshake".getBytes();
            }

            @Override
            public int getId() {
                return 1852076904;
            }

            @Override
            public int getLength() {
                return 1481721953;
            }
        };
    }
}