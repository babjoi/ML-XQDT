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

import org.eclipse.wst.sse.core.internal.document.IDocumentLoader;
import org.eclipse.wst.sse.core.internal.model.AbstractModelLoader;
import org.eclipse.wst.sse.core.internal.provisional.IModelLoader;
import org.eclipse.wst.sse.core.internal.provisional.IStructuredModel;
import org.eclipse.wst.xquery.sse.core.internal.document.XQueryDocumentLoader;

/**
 * XQuery model loader
 * 
 * @author <a href="villard@us.ibm.com">Lionel Villard</a>
 */
public class XQueryModelLoader extends AbstractModelLoader {

    // Overrides

    @Override
    public IDocumentLoader getDocumentLoader() {
        if (documentLoaderInstance == null) {
            documentLoaderInstance = new XQueryDocumentLoader();
        }
        return documentLoaderInstance;
    }

    @Override
    public IStructuredModel newModel() {
        return new XQueryStructuredModel();
    }

    // Implements IModelLoader

    public IModelLoader newInstance() {
        return new XQueryModelLoader();
    }

}
