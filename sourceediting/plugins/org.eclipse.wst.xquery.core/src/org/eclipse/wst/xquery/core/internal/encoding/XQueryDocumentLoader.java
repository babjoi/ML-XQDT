/*******************************************************************************
 * Copyright (c) 2008 by Buddhika Laknath <blaknath@gmail.com>  
 * This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0, and is available at
 * http://www.eclipse.org/legal/epl-v10.html 
 *******************************************************************************/


package org.eclipse.wst.xquery.core.internal.encoding;

import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.wst.sse.core.internal.document.AbstractDocumentLoader;
import org.eclipse.wst.sse.core.internal.document.IDocumentCharsetDetector;
import org.eclipse.wst.sse.core.internal.ltk.parser.RegionParser;
import org.eclipse.wst.sse.core.internal.provisional.document.IEncodedDocument;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocument;
import org.eclipse.wst.sse.core.internal.document.StructuredDocumentFactory;
import org.eclipse.wst.sse.core.internal.text.BasicStructuredDocument;
import org.eclipse.wst.xml.core.internal.encoding.XMLDocumentCharsetDetector;
import org.eclipse.wst.xml.core.internal.parser.XMLStructuredDocumentReParser;
import org.eclipse.wst.xquery.core.internal.text.rules.StructuredTextPartitionerForXQuery;
import org.eclipse.wst.xquery.core.internal.parser.XQuerySourceParser;
import org.eclipse.wst.sse.core.internal.document.IDocumentLoader;


/**
 * This class reads a XQuery file and creates a XML Structured Model.
 * 
 */
public class XQueryDocumentLoader extends AbstractDocumentLoader{

	public XQueryDocumentLoader() {
		super();
	}

	@Override
	public IDocumentPartitioner getDefaultDocumentPartitioner() {
		return new StructuredTextPartitionerForXQuery();
	}

	@Override
	protected IEncodedDocument newEncodedDocument() {
		IStructuredDocument structuredDocument = StructuredDocumentFactory.getNewStructuredDocumentInstance(getParser());
		if (structuredDocument instanceof BasicStructuredDocument) {
			((BasicStructuredDocument) structuredDocument).setReParser(new XMLStructuredDocumentReParser());
		}
		return structuredDocument;
	}			
	
	public IDocumentCharsetDetector getDocumentEncodingDetector() {
		if (fDocumentEncodingDetector == null) {
			fDocumentEncodingDetector = new XMLDocumentCharsetDetector();
		}
		return fDocumentEncodingDetector;
	}

	public RegionParser getParser() {
		return new XQuerySourceParser();
	}

	protected String getSpecDefaultEncoding() {
		final String enc = "UTF-8";
		return enc;
	}

	public IDocumentLoader newInstance() {
		return new XQueryDocumentLoader();
	}	
}
