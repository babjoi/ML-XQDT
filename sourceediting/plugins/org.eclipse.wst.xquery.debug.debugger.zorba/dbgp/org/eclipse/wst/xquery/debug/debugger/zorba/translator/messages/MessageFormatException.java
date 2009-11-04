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

public class MessageFormatException extends ProtocolException {

    private static final long serialVersionUID = 833712273342753852L;

    public MessageFormatException() {
    }

    public MessageFormatException(String message) {
        super(message);
    }

    public MessageFormatException(Throwable cause) {
        super(cause);
    }

    public MessageFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}
