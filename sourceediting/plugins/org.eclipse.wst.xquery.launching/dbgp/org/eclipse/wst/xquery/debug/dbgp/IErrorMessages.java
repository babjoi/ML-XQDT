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
package org.eclipse.wst.xquery.debug.dbgp;

public interface IErrorMessages {

    public static final String NO_ERROR_MESSAGE = "No error";
    public static final String COMMAND_PARSE_ERROR_MESSAGE = "Command parse error";
    public static final String COMMAND_DUPLICATE_ARGUMENT_ERROR_MESSAGE = "The command has duplicate arguments";
    public static final String COMMAND_INVALID_OPTION_ERROR_MESSAGE = "the option format is not vsalid (please check "
            + "if all required options are present, and all the options have valid values)";
    public static final String COMMAND_UNIMPLEMENTED_ERROR_MESSAGE = "Command not implemented";
    public static final String COMMAND_NOT_AVAILABLE_ERROR_MESSAGE = "Command not available in this state";
    public static final String FILE_CANNOT_OPEN_ERROR_MESSAGE = "The source file could not be open";
    public static final String FILE_STREAM_REDIRECT_ERROR_MESSAGE = "Failed to redirect stream";
    public static final String BREAKPOINT_SET_ERROR_MESSAGE = "Could not set the breakpoint";
    public static final String BREAKPOINT_UNSUPPORTED_TYPE_ERROR_MESSAGE = "Unsupported breakpoint type";
    public static final String BREAKPOINT_INVALID_LINE_ERROR_MESSAGE = "The breakpoint location is not valid";
    public static final String BREAKPOINT_LINE_NO_CODE_ERROR_MESSAGE = "The breakpoint line contains no code";
    public static final String BREAKPOINT_INVALID_STATE_ERROR_MESSAGE = "The breakpoint state is not valid";
    public static final String BREAKPOINT_NO_SUCH_ID_ERROR_MESSAGE = "No breakpoint was found with the provided ID";
    public static final String BREAKPOINT_EXPRESSION_EVALUATION_ERROR_MESSAGE = "An error occurred while evaluating an "
            + "eval or property-get expression";
    public static final String BREAKPOINT_INVALID_EXPRESSION_ERROR_MESSAGE = "A non-eval expression is invalid";
    public static final String DATA_PROPERTY_ERROR_MESSAGE = "The requested property could not be retrieved";
    public static final String DATA_INVALID_STACK_DEPTH_ERROR_MESSAGE = "Stack depth not valid (Cannot request a "
            + "negative number of stack elements or a number greater than the actual number of avaialble stack "
            + "elements. Please check the -d parameter)";
    public static final String DATA_INVALID_CONTEXT_ERROR_MESSAGE = "Context not valid (no such context exists)";
    public static final String PROTOCOL_UNSUPPORTED_ENCODING_ERROR_MESSAGE = "Unsupported encoding";
    public static final String PROTOCOL_INTERNAL_EXCEPTION_ERROR_MESSAGE = "Internal debugger exception";
    public static final String PROTOCOL_UNKNOWN_ERROR_MESSAGE = "Unknown error";

}
