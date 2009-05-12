/*******************************************************************************
 * Copyright (c) 2008 by Buddhika Laknath <blaknath@gmail.com>  
 * This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0, and is available at
 * http://www.eclipse.org/legal/epl-v10.html 
 *******************************************************************************/

package org.eclipse.wst.xquery.core.internal.modelhandler;

import org.eclipse.wst.xml.core.internal.modelhandler.ModelHandlerForXML;
import org.eclipse.wst.sse.core.internal.ltk.modelhandler.IModelHandler;
import org.eclipse.wst.sse.core.internal.document.IDocumentLoader;
import org.eclipse.wst.sse.core.internal.provisional.IModelLoader;
import org.eclipse.wst.xquery.core.internal.encoding.XQueryDocumentLoader;



public class ModelHandlerForXQuery  extends ModelHandlerForXML implements IModelHandler {
	
	/**
	 * Needs to match what's in plugin registry. In fact, can be overwritten
	 * at run time with what's in registry! (so should never be 'final')
	 */
	static String AssociatedContentTypeID = "org.eclipse.wst.xquery.core.xqlsource"; 
	/**
	 * Needs to match what's in plugin registry. In fact, can be overwritten
	 * at run time with what's in registry! (so should never be 'final')
	 */
	private static String ModelHandlerID = "org.eclipse.wst.xquery.core.handler";
	
	
	public ModelHandlerForXQuery() {
		super();	
		setId(ModelHandlerID);
		setAssociatedContentTypeId(AssociatedContentTypeID);
	}

	@Override
	public IDocumentLoader getDocumentLoader() {
		return new XQueryDocumentLoader();
	}

	@Override
	public IModelLoader getModelLoader() {
		return new XQueryModelLoader();
	}	

}
