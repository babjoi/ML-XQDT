/*
 * Copyright (c) 2003-2009 Mark Logic Corporation. All rights reserved.
 *
 * This program and the accompanying materials are made available 
 * under the terms of the Eclipse Public License v1.0 which 
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Mark Logic, Inc.
 */
package org.eclipse.wst.xquery.marklogic.xcc.exceptions;

import org.eclipse.wst.xquery.marklogic.xcc.Request;
import org.eclipse.wst.xquery.marklogic.xcc.types.XdmVariable;

/**
 * A server exception resulting from an error encountered while evaluating XQuery. Note that some
 * such exceptions are retryable.
 * 
 * @see #isRetryable()
 * @see RetryableXQueryException
 */
public class XQueryException extends RequestServerException {
    private static final long serialVersionUID = 9187877171979743149L;
    private final String code;
    private final String w3cCode;
    private final String xqueryVersion;
    private final String formatString;
    private final String expr;
    private final boolean retryable;
    private final String[] data;
    private final transient XQueryStackFrame[] stack;

    /**
     * Constructs an XQueryException
     * 
     * @param request
     *            The {@link org.eclipse.wst.xquery.marklogic.xcc.Request} object to which this exception applies
     * @param code
     *            the symbolic exception code
     * @param w3cCode
     *            the exception code defined by the XQuery spec, if any
     * @param xqueryVersion
     *            the XQuery version of the module that threw an exception
     * @param message
     *            the exception message
     * @param formatString
     *            the exception format string
     * @param expr
     *            The expression that caused the exception, if applicable
     * @param retryable
     *            retrying the operation may succeed
     * @param data
     *            the exception data
     * @param stack
     *            the xquery evaluator stack trace
     */
    public XQueryException(Request request, String code, String w3cCode, String xqueryVersion, String message,
            String formatString, String expr, boolean retryable, String[] data, XQueryStackFrame[] stack) {
        super(((message == null) || message.length() == 0) ? code : message, request);

        this.code = code;
        this.w3cCode = w3cCode;
        this.xqueryVersion = xqueryVersion;
        this.formatString = formatString;
        this.expr = expr;
        this.retryable = retryable;
        this.data = data.clone();
        this.stack = stack.clone();
    }

    // ------------------------------------------------------------

    /**
     * Returns the server error code for this XQuery exception.
     * 
     * @return A string that is a server error code, such as XDMP-FOO.
     */
    public String getCode() {
        return code;
    }

    /**
     * Returns the error code defined by the WC3 XQuery spec that corresponds to this exception, if
     * any.
     * 
     * @return A string that is the W3C error code, such as err:BLAH123, if one is defined, else
     *         null.
     * @since 4.0
     */
    public String getW3CCode() {
        return w3cCode;
    }

    /**
     * Returns the XQuery version (0.9-ml, 1.0-ml or 1.0) of the module that threw the exception.
     * 
     * @return A String that represents the XQuery version.
     */
    public String getXQueryVersion() {
        return xqueryVersion;
    }

    public String getFormatString() {
        return formatString;
    }

    /**
     * <p>
     * Returns true if the server indicates that this request might succeeed if resubmitted.
     * </p>
     * <p>
     * Note that if method returns true, then this exception will also be an instance of
     * {@link RetryableXQueryException}.
     * </p>
     * <p>
     * Note also that retryable exceptions will be automatically resubmitted if the
     * {@link org.eclipse.wst.xquery.marklogic.xcc.RequestOptions} associated with the request indicate to do so.
     * </p>
     * 
     * @return true if the request is retryable, false if the request cannot be processed as
     *         submitted.
     */
    public boolean isRetryable() {
        return retryable;
    }

    public String[] getData() {
        return data.clone();
    }

    /**
     * Get an array of {@link XQueryStackFrame} objects that represent the XQuery stack frame
     * returned by the server.
     * 
     * @return An array (possibly zero length) of XQuery evaluator stack frames.
     */
    public XQueryStackFrame[] getStack() {
        return stack.clone();
    }

    // ----------------------------------------------------

    /**
     * Returns a string representation (possibly multiline) of the XQuery error message and stack
     * frame.
     * 
     * @return A string representation of this XQuery exception.
     */
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getName());
        sb.append(": ");

        if (formatString != null && !formatString.equals("")) {
            sb.append(formatString);
        } else {
            sb.append(code);
            for (int i = 0; i < data.length; ++i) {
                sb.append(" ");
                sb.append(data[i]);
            }
        }

        if (stack != null) {
            for (int i = 0; i < stack.length; ++i) {
                XQueryStackFrame frame = stack[i];
                boolean b = false;
                String uri = frame.getUri();

                if (uri != null && uri.length() > 0) {
                    b = true;
                    sb.append("\nin ");
                    sb.append(uri);
                }

                int line = frame.getLineNumber();

                if (line != 0) {
                    if (b) {
                        sb.append(", ");
                    } else {
                        sb.append('\n');
                        b = true;
                    }
                    sb.append("on line ");
                    sb.append(line);
                }

                if ((expr != null) && (expr.length() != 0)) {
                    sb.append("\nexpr: ");
                    sb.append(expr);
                }

                String operation = frame.getOperation();

                if (operation != null && operation.length() > 0) {
                    if (b) {
                        sb.append(',');
                    } else {
                        b = true;
                    }
                    sb.append("\nin ");
                    sb.append(operation);
                }

                XdmVariable[] variables = frame.getVariables();

                if (variables != null) {

                    for (int j = 0; j < variables.length; ++j) {
                        XdmVariable variable = variables[j];
                        String name = variable.getName().getLocalname();
                        String value = variable.getValue().asString();

                        if (name != null && name.length() > 0 && value != null && value.length() > 0) {
                            sb.append("\n  $");
                            sb.append(name);
                            sb.append(" = ");
                            sb.append(value);
                        }
                    }

                    String contextItem = frame.getContextItem();

                    if (contextItem != null && contextItem.length() > 0) {
                        sb.append("\n  context-item() = ");
                        sb.append(contextItem);
                    }

                    int contextPosition = frame.getContextPosition();

                    if (contextPosition != 0) {
                        sb.append("\n  context-position() = ");
                        sb.append(contextPosition);
                    }
                }
            }
        }

//	  XDBCException nextException = getNextException();
//	  if (nextException != null) {
//	    sb.append("\n");
//	    sb.append(nextException.toString());
//	  }

        return sb.toString();
    }
}
