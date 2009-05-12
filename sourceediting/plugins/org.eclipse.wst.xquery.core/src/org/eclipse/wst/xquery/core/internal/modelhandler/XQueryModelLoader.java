/*******************************************************************************
 * Copyright (c) 2008 by Buddhika Laknath <blaknath@gmail.com>  
 * This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0, and is available at
 * http://www.eclipse.org/legal/epl-v10.html 
 *******************************************************************************/

package org.eclipse.wst.xquery.core.internal.modelhandler;

import org.eclipse.wst.xml.core.internal.modelhandler.XMLModelLoader;
import org.eclipse.wst.sse.core.internal.document.IDocumentLoader;
import org.eclipse.wst.xquery.core.internal.encoding.XQueryDocumentLoader;
//import org.eclipse.wst.xquery.ui.internal.style.XQueryStyleAdapterFactory;
import org.eclipse.wst.sse.core.internal.provisional.IModelLoader;

public class XQueryModelLoader extends XMLModelLoader {

	//	private static final String STR_ENCODING = "encoding"; //$NON-NLS-1$
	
	/**
	 * XMLLoader constructor comment.
	 */
	public XQueryModelLoader(){
		super();
	}

	@Override
	public IDocumentLoader getDocumentLoader() {
		if (documentLoaderInstance == null) {
			documentLoaderInstance = new XQueryDocumentLoader();
		}
		return documentLoaderInstance;
	}
	
	@Override
	public IModelLoader newInstance() {
		return new XQueryModelLoader();
	}

//	/**
//	 * This method must return those factories which must be attached to the
//	 * structuredModel before content is applied.
//	 */
//	@Override	
//	public List getAdapterFactories() {
//		List result = new ArrayList();
//		INodeAdapterFactory factory = null;
//		factory = XQueryStyleAdapterFactory.getInstance();
//		result.add(factory);
//
//		return result;
//	}
}
