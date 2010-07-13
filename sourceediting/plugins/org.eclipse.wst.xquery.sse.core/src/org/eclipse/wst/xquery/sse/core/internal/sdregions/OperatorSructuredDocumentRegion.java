/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.xquery.sse.core.internal.sdregions;

import org.eclipse.wst.sse.core.internal.provisional.text.ITextRegion;
import org.eclipse.wst.xquery.sse.core.internal.regions.XQueryRegions;

/**
 * SD Region containing only one {@link ITextRegion} of type
 * {@link XQueryRegions#SEQUENCECOMMA}
 * 
 * @author <a href="villard@us.ibm.com">Lionel Villard</a>
 */
public class OperatorSructuredDocumentRegion extends XQueryStructuredDocumentRegion {

	// Static

	// State

	/** Cached previous {@link OperatorSructuredDocumentRegion} */
	protected OperatorSructuredDocumentRegion previousSequenceComma;

	// Methods

	// Helpers

}
