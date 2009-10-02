/**********************************************************************
 * Copyright (c) 2009 Martin Schmied and others. All rights reserved.   This
 * program and the accompanying materials are made available under the terms of
 * the Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Martin Schmied - Initial API and implementation
 **********************************************************************/
package org.eclipse.wst.rng.core.internal.contenttype;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import org.eclipse.core.runtime.content.IContentDescription;
import org.eclipse.core.runtime.content.XMLContentDescriber;
import org.eclipse.wst.rng.core.IRngSchemaResolver;
import org.eclipse.wst.rng.core.internal.DefaultRngSchemaResolver;
import org.xml.sax.InputSource;


public class RngDocumentContentDescriber extends XMLContentDescriber {
	private IRngSchemaResolver schemaResolver = DefaultRngSchemaResolver.INSTANCE;	
	
	private int checkRootNamespace(InputSource contents) throws IOException {
		if (schemaResolver.getSchema(contents) != null) {
			return VALID;
		}
		return INDETERMINATE;
	}

	public int describe(InputStream contents, IContentDescription description) throws IOException {
		if (super.describe(contents, description) == INVALID)
			return INVALID;
		contents.reset();
		return checkRootNamespace(new InputSource(contents));
	}

	public int describe(Reader contents, IContentDescription description) throws IOException {
		if (super.describe(contents, description) == INVALID)
			return INVALID;
		contents.reset();
		return checkRootNamespace(new InputSource(contents));
	}
}
