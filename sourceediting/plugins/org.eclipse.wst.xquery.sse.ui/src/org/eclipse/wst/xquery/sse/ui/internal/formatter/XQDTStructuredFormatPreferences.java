/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.xquery.sse.ui.internal.formatter;

import java.util.Stack;

import org.eclipse.wst.xml.core.internal.provisional.format.StructuredFormatPreferencesXML;

/**
 * Formatting preferences for XQDT
 * 
 * <p>
 * Represents both the user preferences, and the current formatter state.
 * 
 * @author <a href="villard@us.ibm.com">Lionel Villard</a>
 */
public class XQDTStructuredFormatPreferences extends StructuredFormatPreferencesXML {

    // State

    /** Number of white space characters after the expression */
    final Stack<Integer> trailingWSLength;

    /** Number of line separator characters after the expression */
    final Stack<Integer> lineSeparator;

    // Constructor

    public XQDTStructuredFormatPreferences() {
        trailingWSLength = new Stack<Integer>();
        lineSeparator = new Stack<Integer>();
    }

    // Methods

    /**
     * Get the number of trailing white space characters
     */
    public int getTrailingWhitespaceLength() {
        return trailingWSLength.size() > 0 ? trailingWSLength.peek() : 0;
    }

    /**
     * Get the number of line separators
     */
    public int getLineSeparatorCount() {
        return lineSeparator.size() > 0 ? lineSeparator.peek() : 0;
    }

    /**
     * Push the number of trailing white space characters and line separator count
     */
    public void pushTrailingWhitespaceLength(int length, int lineCount) {
        trailingWSLength.push(length);
        lineSeparator.push(lineCount);
    }

    /**
     * Pop the number of trailing white space characters
     */
    public void popTrailingWhitespaceLength() {
        if (trailingWSLength.size() > 0) {
            trailingWSLength.pop();
            lineSeparator.pop();
        }
    }
}
