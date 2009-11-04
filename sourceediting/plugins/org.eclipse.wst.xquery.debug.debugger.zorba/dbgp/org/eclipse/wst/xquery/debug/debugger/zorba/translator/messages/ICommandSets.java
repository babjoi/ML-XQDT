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

public interface ICommandSets {

    // command sets
    public static final int COMMAND_SET_EXECUTION = 0xF1;
    public static final int COMMAND_SET_BREAKPOINTS = 0xF2;
    public static final int COMMAND_SET_STATIC = 0xF3;
    public static final int COMMAND_SET_DYNAMIC = 0xF4;
    public static final int COMMAND_SET_ENGINE_EVENTS = 0xF8;

    // EXECUTION commands
    public static final int COMMAND_RUN = (COMMAND_SET_EXECUTION << 8) | 0x01;
    public static final int COMMAND_SUSPEND = (COMMAND_SET_EXECUTION << 8) | 0x02;
    public static final int COMMAND_RESUME = (COMMAND_SET_EXECUTION << 8) | 0x03;
    public static final int COMMAND_TERMINATE = (COMMAND_SET_EXECUTION << 8) | 0x04;
    public static final int COMMAND_STEP = (COMMAND_SET_EXECUTION << 8) | 0x05;

    // BREAKPOINTS commands
    public static final int COMMAND_SET = (COMMAND_SET_BREAKPOINTS << 8) | 0x01;
    public static final int COMMAND_CLEAR = (COMMAND_SET_BREAKPOINTS << 8) | 0x02;

    // STATIC commands
    public static final int COMMAND_OPTIONS = (COMMAND_SET_STATIC << 8) | 0x01;
    public static final int COMMAND_DEFAULTS = (COMMAND_SET_STATIC << 8) | 0x02;
    public static final int COMMAND_SETS = (COMMAND_SET_STATIC << 8) | 0x03;

    // DYNAMIC commands
    public static final int COMMAND_DATA = (COMMAND_SET_DYNAMIC << 8) | 0x01;
    public static final int COMMAND_VARIABLES = (COMMAND_SET_DYNAMIC << 8) | 0x02;
    public static final int COMMAND_FOCUS = (COMMAND_SET_DYNAMIC << 8) | 0x03;
    public static final int COMMAND_TIME = (COMMAND_SET_DYNAMIC << 8) | 0x04;
    public static final int COMMAND_DOCUMENTS = (COMMAND_SET_DYNAMIC << 8) | 0x05;
    public static final int COMMAND_COLLECTIONS = (COMMAND_SET_DYNAMIC << 8) | 0x06;
    public static final int COMMAND_COLLECTION = (COMMAND_SET_DYNAMIC << 8) | 0x07;
    public static final int COMMAND_EVALUATE = (COMMAND_SET_DYNAMIC << 8) | 0x08;
    public static final int COMMAND_FRAMES = (COMMAND_SET_DYNAMIC << 8) | 0x09;

    // ENGINE EVENT commands
    public static final int COMMAND_STARTED = (COMMAND_SET_ENGINE_EVENTS << 8) | 0x01;
    public static final int COMMAND_TERMINATED = (COMMAND_SET_ENGINE_EVENTS << 8) | 0x02;
    public static final int COMMAND_SUSPENDED = (COMMAND_SET_ENGINE_EVENTS << 8) | 0x03;
    public static final int COMMAND_RESUMED = (COMMAND_SET_ENGINE_EVENTS << 8) | 0x04;
    public static final int COMMAND_EVALUATED = (COMMAND_SET_ENGINE_EVENTS << 8) | 0x05;

    // FLAGS
    public static final int FLAG_REPLY = 0x80;
    public static final int FLAG_EVALUATE = 0x04;
}