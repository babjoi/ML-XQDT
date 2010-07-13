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
package org.eclipse.wst.xquery.sse.core.internal.encoding;

import java.io.IOException;

import org.eclipse.jface.text.IDocument;
import org.eclipse.wst.sse.core.internal.document.IDocumentCharsetDetector;
import org.eclipse.wst.sse.core.internal.encoding.util.UnicodeBOMEncodingDetector;

/**
 * Encoding detector.
 * 
 * Based on the SSE framework unicode encoding detector, except for IDocument.
 * 
 * @author <a href="villard@us.ibm.com">Lionel Villard</a>
 */
public class XQueryEncodingDetector extends UnicodeBOMEncodingDetector implements IDocumentCharsetDetector {

	// State

	/** Set document */
	private IDocument fDocument;

	// Implements IDocumentCharsetDetector

	public String getSpecDefaultEncoding() {
		return "UTF-8";
	}

	public String getEncoding() throws IOException {
		if (fDocument != null)
			return "UTF-8";

		return super.getEncoding();
	}

	public void set(IDocument document) {
		fDocument = document;
	}

}
