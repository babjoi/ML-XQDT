/*******************************************************************************
 * Copyright (c) 2005, 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.xquery.sse.core.internal.regions;

import org.eclipse.wst.sse.core.internal.provisional.events.StructuredDocumentEvent;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocumentRegion;
import org.eclipse.wst.sse.core.internal.provisional.text.ITextRegion;

/**
 * General purpose XQuery region.
 *  
 * <p>
 * If an lexical error occurred during the parsing of this region, the lexical
 * state when this error happened is preserved.
 * 
 * @author <a href="villard@us.ibm.com">Lionel Villard</a>
 */
@SuppressWarnings("restriction")
public class XQueryRegion implements ITextRegion {

	// State

	/** If set, lexical state when a lexical error occurred */
	protected int fErrorLexicalState;

	protected int fLength;

	protected int fStart;
	
	protected int fTextLength;
	
	protected String fType;

	/** Whether or not this region represent a keyword */
	protected boolean fIsKeyword; // TODO: not here.
	
	// Constructors

	public XQueryRegion(String type, int start, int textLength, int length, int lexicalState, boolean isKeyword) {
		super();

		this.fType = type;
		this.fStart = start;
		this.fTextLength = textLength;
		this.fLength = length;
		this.fErrorLexicalState = lexicalState;
		this.fIsKeyword = isKeyword;
	}

	// Methods

	/** Whether or not this region represent a keyword */
	public boolean isKeyword()
	{
		return fIsKeyword;
	}
	
	/**
	 * @return the lexicalState, or -1 if no error.
	 */
	public int getErrorLexicalState() {
		return fErrorLexicalState;
	}

	public void setLength(int i) {
		fLength = i;
	}

	public void setStart(int i) {
		fStart = i;
	}

	public void setTextLength(int i) {
		fTextLength = i;
	}

	public void setType(String string) {
		fType = string;
	}

	// Implements ITextRegion

	public void adjust(int i) {
		fStart += i;

	}

	public void adjustLength(int i) {
		fLength += i;
	}

	public void adjustStart(int i) {
		fStart += i;
	}

	public void adjustTextLength(int i) {
		fTextLength += i;
	}

	public int getEnd() {
		return fStart + fLength;
	}

	public int getLength() {
		return fLength;
	}

	public int getStart() {
		return fStart;
	}

	public void equatePositions(ITextRegion region) {
		fStart = region.getStart();
		fLength = region.getLength();
		fTextLength = region.getTextLength();
	}

	public int getTextEnd() {
		return fStart + fTextLength;
	}

	public int getTextLength() {
		return fTextLength;
	}

	public String getType() {
		return fType;
	}

	public StructuredDocumentEvent updateRegion(Object requester, IStructuredDocumentRegion parent, String changes,
			int requestStart, int lengthToReplace) {

		return null;
	}

}
