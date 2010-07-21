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
package org.eclipse.wst.xquery.sse.core.internal.parser;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import org.eclipse.wst.sse.core.internal.provisional.text.ITextRegion;

/**
 * Decompose character stream in tokens.
 * 
 * @author <a href="villard@us.ibm.com">Lionel Villard</a>
 */
@SuppressWarnings("restriction")
public interface ITokenizer {

	/**
	 * Gets the next token
	 * @return
	 * @throws IOException
	 */ 
	ITextRegion getNextToken() throws IOException;

	/**
	 * Whether or not the end-of-stream has been reached
	 * @return
	 */
	boolean isEOF();

	/**
	 * Reset the tokenizer
	 * @param charArray
	 */
	void reset(char[] charArray);

	/**
	 * Reset the tokenizer
	 * @param charArray
	 * @param newOffset
	 */
	void reset(char[] charArray, int newOffset);

	/**
	 * Reset the tokenizer
	 * @param in
	 */
	void reset(InputStream in);

	/**
	 * Reset the tokenizer
	 * @param in
	 * @param newOffset
	 */
	void reset(InputStream in, int newOffset);

	/**
	 * Reset the tokenizer
	 * @param in
	 */
	void reset(Reader in);

	/**
	 * Reset the tokenizer
	 * @param in
	 * @param newOffset
	 */
	void reset(Reader in, int newOffset);

	/**
	 * @param xQueryRegionParser
	 */
	void setParser(XQueryRegionParser parser);
}
