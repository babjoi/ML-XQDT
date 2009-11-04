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

public class RequestMessageHeader extends MessageHeader {

    private int commandSet;
    private int command;

    public RequestMessageHeader(int length, int id, int flags, int commandSet, int command) {
        super(length, id, flags);
        this.commandSet = commandSet;
        this.command = command;
    }

    public int getCommandSet() {
        return commandSet;
    }

    public int getCommand() {
        return command;
    }

    @Override
    public boolean isReplyMessageHeader() {
        return false;
    }

}
