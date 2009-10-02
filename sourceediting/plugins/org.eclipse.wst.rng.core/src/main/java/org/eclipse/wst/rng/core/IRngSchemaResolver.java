/**********************************************************************
 * Copyright (c) 2009 Martin Schmied and others. All rights reserved.   This
 * program and the accompanying materials are made available under the terms of
 * the Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Martin Schmied - Initial API and implementation
 **********************************************************************/
package org.eclipse.wst.rng.core;

import org.eclipse.wst.rng.contentassist.IRngSchema;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;


public interface IRngSchemaResolver {
	public IRngSchema getSchema(InputSource documentInputSource);
	
	public IRngSchema getSchema(Document document);
}