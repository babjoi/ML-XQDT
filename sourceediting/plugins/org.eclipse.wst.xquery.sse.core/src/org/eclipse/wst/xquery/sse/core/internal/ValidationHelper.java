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
package org.eclipse.wst.xquery.sse.core.internal;

import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocumentRegion;
import org.eclipse.wst.sse.core.internal.provisional.text.ITextRegion;
import org.eclipse.wst.validation.internal.operations.LocalizedMessage;
import org.eclipse.wst.validation.internal.provisional.core.IMessage;

/**
 * Collection of utility methods related to validation
 * 
 * @author <a href="villard@us.ibm.com">Lionel Villard</a>
 */
@SuppressWarnings("restriction")
public class ValidationHelper {

	/**
	 * Create an error message from the given parameters.
	 * @param sdregion
	 * @param region
	 * @param text
	 * @return
	 */
	public static IMessage createErrorMessage(
			IStructuredDocumentRegion sdregion, ITextRegion region, String text) {
		IMessage message = new LocalizedMessage(IMessage.HIGH_SEVERITY, text);

		if (region != null) {
			message.setOffset(sdregion.getStartOffset() + region.getStart());
			message.setLength(region.getTextLength());
			message.setLineNo(sdregion.getParentDocument().getLineOfOffset(
					sdregion.getStartOffset() + region.getStart()));
		} else {
			message.setOffset(sdregion.getStartOffset());
			message.setLength(sdregion.getTextLength());
			message.setLineNo(sdregion.getParentDocument().getLineOfOffset(
					sdregion.getStartOffset()));

		}
		return message;
	}
}
