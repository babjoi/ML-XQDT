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
package org.eclipse.wst.xquery.sse.core.internal.document;

import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.wst.sse.core.internal.document.AbstractDocumentLoader;
import org.eclipse.wst.sse.core.internal.document.IDocumentCharsetDetector;
import org.eclipse.wst.sse.core.internal.document.IDocumentLoader;
import org.eclipse.wst.sse.core.internal.document.StructuredDocumentFactory;
import org.eclipse.wst.sse.core.internal.provisional.document.IEncodedDocument;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocument;
import org.eclipse.wst.sse.core.internal.text.BasicStructuredDocument;
import org.eclipse.wst.xquery.sse.core.internal.encoding.XQueryEncodingDetector;
import org.eclipse.wst.xquery.sse.core.internal.parser.XQueryReParser;
import org.eclipse.wst.xquery.sse.core.internal.parser.XQueryRegionParser;

/**
 * Loader loader for XQuery files
 * 
 * @author <a href="mailto:villard@us.ibm.com">Lionel Villard</a>
 */
@SuppressWarnings("restriction")
public class XQueryDocumentLoader extends AbstractDocumentLoader implements IDocumentLoader {

	// Overrides

	@Override
	public IDocumentPartitioner getDefaultDocumentPartitioner() {
		return new XQDTDocumentPartitioner();
	}

	@Override
	protected IEncodedDocument newEncodedDocument() {
		IStructuredDocument structuredDocument = StructuredDocumentFactory
				.getNewStructuredDocumentInstance(new XQueryRegionParser());

		if (structuredDocument instanceof BasicStructuredDocument) {
			((BasicStructuredDocument) structuredDocument).setReParser(new XQueryReParser());
		}

		return structuredDocument;
	}

	// Implement IDocumentLoader

	public IDocumentCharsetDetector getDocumentEncodingDetector() {
		return new XQueryEncodingDetector();
	}
	
	// Extension point for Reparser
	
	

}
