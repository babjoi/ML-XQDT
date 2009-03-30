/*******************************************************************************
 * Copyright (c) 2008 by Buddhika Laknath <blaknath@gmail.com>  
 * This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0, and is available at
 * http://www.eclipse.org/legal/epl-v10.html 
 *******************************************************************************/

package org.eclipse.wst.xquery.core.internal.provisional.adapters;

import org.eclipse.wst.sse.core.internal.provisional.INodeAdapter;
import org.w3c.dom.Element;

public interface IStyleSelectorAdapter extends INodeAdapter {

	boolean match(Element element, String pseudoName);	
	
}
