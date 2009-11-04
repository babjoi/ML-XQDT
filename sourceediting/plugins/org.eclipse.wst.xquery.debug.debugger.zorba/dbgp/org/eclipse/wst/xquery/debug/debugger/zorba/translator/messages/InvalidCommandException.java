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

import org.eclipse.wst.xquery.debug.debugger.zorba.translator.communication.ProtocolException;

public class InvalidCommandException extends ProtocolException {

    private static final long serialVersionUID = 833712273342753852L;

    private int commandSet;
    private int command;

    public InvalidCommandException(int command) {
        this.commandSet = (command >> 8) & 0xFF;
        this.command = command & 0xFF;
    }

    @Override
    public String getMessage() {
        return "No such command (Command Set ID: " + commandSet + "; Command ID: " + command + ")";
    }

}
