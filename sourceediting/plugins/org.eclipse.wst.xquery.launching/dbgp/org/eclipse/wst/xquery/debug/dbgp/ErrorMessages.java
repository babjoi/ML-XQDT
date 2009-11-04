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

import java.util.HashMap;
import java.util.Map;

public class ErrorMessages implements IErrorMessages {

    private static final int NO_ERROR = 0;
    private static final int COMMAND_PARSE_ERROR = 1;
    private static final int COMMAND_DUPLICATE_ARGUMENT_ERROR = 2;
    private static final int COMMAND_INVALID_OPTION_ERROR = 3;
    private static final int COMMAND_UNIMPLEMENTED_ERROR = 4;
    private static final int COMMAND_NOT_AVAILABLE_ERROR = 5;

    private static final int FILE_CANNOT_OPEN_ERROR = 100;
    private static final int FILE_STREAM_REDIRECT_ERROR = 101;

    private static final int BREAKPOINT_SET_ERROR = 200;
    private static final int BREAKPOINT_UNSUPPORTED_TYPE_ERROR = 201;
    private static final int BREAKPOINT_INVALID_LINE_ERROR = 202;
    private static final int BREAKPOINT_LINE_NO_CODE_ERROR = 203;
    private static final int BREAKPOINT_INVALID_STATE_ERROR = 204;
    private static final int BREAKPOINT_NO_SUCH_ID_ERROR = 205;
    private static final int BREAKPOINT_EXPRESSION_EVALUATION_ERROR = 206;
    private static final int BREAKPOINT_INVALID_EXPRESSION_ERROR = 207;

    private static final int DATA_PROPERTY_ERROR = 300;
    private static final int DATA_INVALID_STACK_DEPTH_ERROR = 301;
    private static final int DATA_INVALID_CONTEXT_ERROR = 302;

    private static final int PROTOCOL_UNSUPPORTED_ENCODING_ERROR = 900;
    private static final int PROTOCOL_INTERNAL_EXCEPTION_ERROR = 998;
    private static final int PROTOCOL_UNKNOWN_ERROR = 999;

    public static Map<Integer, String> ERROR_MESSAGES = new HashMap<Integer, String>(22);

    static {
        // 000 Command parsing 
        ERROR_MESSAGES.put(NO_ERROR, NO_ERROR_MESSAGE);
        ERROR_MESSAGES.put(COMMAND_PARSE_ERROR, COMMAND_PARSE_ERROR_MESSAGE);
        ERROR_MESSAGES.put(COMMAND_DUPLICATE_ARGUMENT_ERROR, COMMAND_DUPLICATE_ARGUMENT_ERROR_MESSAGE);
        ERROR_MESSAGES.put(COMMAND_INVALID_OPTION_ERROR, COMMAND_INVALID_OPTION_ERROR_MESSAGE);
        ERROR_MESSAGES.put(COMMAND_UNIMPLEMENTED_ERROR, COMMAND_UNIMPLEMENTED_ERROR_MESSAGE);
        ERROR_MESSAGES.put(COMMAND_NOT_AVAILABLE_ERROR, COMMAND_NOT_AVAILABLE_ERROR_MESSAGE);

        // 100 File
        ERROR_MESSAGES.put(FILE_CANNOT_OPEN_ERROR, FILE_CANNOT_OPEN_ERROR_MESSAGE);
        ERROR_MESSAGES.put(FILE_STREAM_REDIRECT_ERROR, FILE_STREAM_REDIRECT_ERROR_MESSAGE);

        // 200 Code flow and breakpoint
        ERROR_MESSAGES.put(BREAKPOINT_SET_ERROR, BREAKPOINT_SET_ERROR_MESSAGE);
        ERROR_MESSAGES.put(BREAKPOINT_UNSUPPORTED_TYPE_ERROR, BREAKPOINT_UNSUPPORTED_TYPE_ERROR_MESSAGE);
        ERROR_MESSAGES.put(BREAKPOINT_INVALID_LINE_ERROR, BREAKPOINT_INVALID_LINE_ERROR_MESSAGE);
        ERROR_MESSAGES.put(BREAKPOINT_LINE_NO_CODE_ERROR, BREAKPOINT_LINE_NO_CODE_ERROR_MESSAGE);
        ERROR_MESSAGES.put(BREAKPOINT_INVALID_STATE_ERROR, BREAKPOINT_INVALID_STATE_ERROR_MESSAGE);
        ERROR_MESSAGES.put(BREAKPOINT_NO_SUCH_ID_ERROR, BREAKPOINT_NO_SUCH_ID_ERROR_MESSAGE);
        ERROR_MESSAGES.put(BREAKPOINT_EXPRESSION_EVALUATION_ERROR, BREAKPOINT_EXPRESSION_EVALUATION_ERROR_MESSAGE);
        ERROR_MESSAGES.put(BREAKPOINT_INVALID_EXPRESSION_ERROR, BREAKPOINT_INVALID_EXPRESSION_ERROR_MESSAGE);

        // 300 Data
        ERROR_MESSAGES.put(DATA_PROPERTY_ERROR, DATA_PROPERTY_ERROR_MESSAGE);
        ERROR_MESSAGES.put(DATA_INVALID_STACK_DEPTH_ERROR, DATA_INVALID_STACK_DEPTH_ERROR_MESSAGE);
        ERROR_MESSAGES.put(DATA_INVALID_CONTEXT_ERROR, DATA_INVALID_CONTEXT_ERROR_MESSAGE);

        // 900 Protocol
        ERROR_MESSAGES.put(PROTOCOL_UNSUPPORTED_ENCODING_ERROR, PROTOCOL_UNSUPPORTED_ENCODING_ERROR_MESSAGE);
        ERROR_MESSAGES.put(PROTOCOL_INTERNAL_EXCEPTION_ERROR, PROTOCOL_INTERNAL_EXCEPTION_ERROR_MESSAGE);
        ERROR_MESSAGES.put(PROTOCOL_UNKNOWN_ERROR, PROTOCOL_UNKNOWN_ERROR_MESSAGE);
    };

    public static String getMessage(int code) {
        return ERROR_MESSAGES.get(new Integer(code));
    }

    public static enum DbgpError {
        NO_ERROR(0), COMMAND_PARSE_ERROR(1), COMMAND_DUPLICATE_ARGUMENT_ERROR(2), COMMAND_INVALID_OPTION_ERROR(3), COMMAND_UNIMPLEMENTED_ERROR(
                4), COMMAND_NOT_AVAILABLE_ERROR(5);

        private final int fCode;

        private DbgpError(int code) {
            fCode = code;
        }

        @Override
        public String toString() {
            return ERROR_MESSAGES.get(fCode);
        }

    }

}
