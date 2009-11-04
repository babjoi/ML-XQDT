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
package org.eclipse.wst.xquery.debug.debugger.zorba.translator;

public interface IErrorCodes {

    public static final int NO_ERROR = 0;
    public static final int ERROR_UNKNOWN = 1;
    public static final int ERROR_INVALID_MESSAGE_FORMAT = 11;
    public static final int ERROR_INVALID_COMMAND = 12;
    public static final int ERROR_COMMAND_NOT_IMPLEMENTED = 13;

    public static final int ERROR_REPLY_MESSAGE_BUILD_ERROR = 21;
    public static final int ERROR_REPLY_MESSAGE_INVALID_DATA = 22;

}
