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
package org.eclipse.wst.xquery.sse.core.internal.parser;


/**
 * Base class for {@link XQueryTokenizer}
 * 
 * @author villardl
 */
public abstract class AbstractTokenizer implements ITokenizer {

	// State

	final protected XQueryParserRegionFactory regionFactory;

	// Constructor

	protected AbstractTokenizer() {
		regionFactory = createRegionFactory();
	}
	
	// Methods
	
	/**
	 * Gets the factory to create region.
	 */
	protected XQueryParserRegionFactory createRegionFactory()
	{
		return new XQueryParserRegionFactory();
	}

	 
}
