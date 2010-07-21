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
package org.eclipse.wst.xquery.sse.core.internal.model;

import org.eclipse.wst.sse.core.internal.document.IDocumentCharsetDetector;
import org.eclipse.wst.sse.core.internal.document.IDocumentLoader;
import org.eclipse.wst.sse.core.internal.ltk.modelhandler.AbstractModelHandler;
import org.eclipse.wst.sse.core.internal.provisional.IModelLoader;
import org.eclipse.wst.xquery.sse.core.internal.document.XQueryDocumentLoader;
import org.eclipse.wst.xquery.sse.core.internal.encoding.XQueryEncodingDetector;

/**
 * XQuery model handler
 * 
 * @author <a href="villard@us.ibm.com">Lionel Villard</a>
 */
@SuppressWarnings("restriction")
public class XQueryModelHandler extends AbstractModelHandler {

	// Constructors

	public XQueryModelHandler() {
	}

	// Overrides

	@Override
	public IDocumentCharsetDetector getEncodingDetector() {
		return new XQueryEncodingDetector();
	}

	// Implements IModelHandler

	public IModelLoader getModelLoader() {
		return new XQueryModelLoader();
	}

	public IDocumentLoader getDocumentLoader() {
		return new XQueryDocumentLoader();
	}

}
