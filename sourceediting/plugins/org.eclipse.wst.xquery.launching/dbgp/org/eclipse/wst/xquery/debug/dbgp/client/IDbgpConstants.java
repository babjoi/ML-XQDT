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
package org.eclipse.wst.xquery.debug.dbgp.client;

public interface IDbgpConstants {

    public final static String COMMAND_FEATURE_SET = "feature_set";
    public final static String COMMAND_FEATURE_GET = "feature_get";

    public final static String COMMAND_STDOUT = "stdout";
    public final static String COMMAND_STDERR = "stderr";

    public final static String COMMAND_RUN = "run";
    public final static String COMMAND_STEP_INTO = "step_into";
    public final static String COMMAND_STEP_OVER = "step_over";
    public final static String COMMAND_STEP_OUT = "step_out";
    public final static String COMMAND_STOP = "stop";
    public final static String COMMAND_DETACH = "detach";

    public final static String COMMAND_BREAK = "break";
    public final static String COMMAND_STACK_GET = "stack_get";
    public final static String COMMAND_CONTEXT_NAMES = "context_names";
    public final static String COMMAND_CONTEXT_GET = "context_get";

    public final static String COMMAND_PROPERTY_SET = "property_set";

    public final static String COMMAND_SOURCE = "source";

    public final static String COMMAND_BREAKPOINT_GET = "breakpoint_get";
    public final static String COMMAND_BREAKPOINT_SET = "breakpoint_set";
    public final static String COMMAND_BREAKPOINT_REMOVE = "breakpoint_remove";
    public final static String COMMAND_BREAKPOINT_UPDATE = "breakpoint_rupdate";

    public final static String COMMAND_EVAL = "eval";

    public final static String STATUS_STARTING = "starting";
    public final static String STATUS_STOPPING = "stopping";
    public final static String STATUS_STOPPED = "stopped";
    public final static String STATUS_RUNNING = "running";
    public final static String STATUS_BREAK = "break";

    public final static String REASON_OK = "ok";
    public final static String REASON_ERROR = "error";
    public final static String REASON_ABORTED = "aborted";
    public final static String REASON_EXCEPTION = "exception";

}
