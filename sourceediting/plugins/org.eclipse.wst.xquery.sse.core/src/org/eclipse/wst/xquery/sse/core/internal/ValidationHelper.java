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
public class ValidationHelper {

    /**
     * Create an error message from the given parameters.
     * 
     * @param sdregion
     * @param text
     * @param tip
     *            whether or not to only highlight the tip of the given sdregion
     * @return
     */
    public static IMessage createErrorMessage(IStructuredDocumentRegion sdregion, String text, boolean tip) {
        IMessage message = new LocalizedMessage(IMessage.HIGH_SEVERITY, text);

        if (tip) {
            int offset = sdregion.getStartOffset() + sdregion.getFullText().trim().length() - 1;
            message.setOffset(offset);
            message.setLength(1);
            message.setLineNo(sdregion.getParentDocument().getLineOfOffset(offset));
        } else {
            int offset = sdregion.getStartOffset();
            message.setOffset(offset);
            message.setLength(sdregion.getLength());
            message.setLineNo(sdregion.getParentDocument().getLineOfOffset(offset));
        }

        return message;
    }

    /**
     * Create an error message from the given parameters.
     * 
     * @param sdregion
     * @param region
     * @param text
     * @return
     */
    public static IMessage createErrorMessage(IStructuredDocumentRegion sdregion, ITextRegion region, String text) {
        if (region == null) {
            return createErrorMessage(sdregion, text, false);
        }

        IMessage message = new LocalizedMessage(IMessage.HIGH_SEVERITY, text);

        message.setOffset(sdregion.getStartOffset() + region.getStart());
        message.setLength(region.getLength());
        message.setLineNo(sdregion.getParentDocument().getLineOfOffset(sdregion.getStartOffset() + region.getStart()));

        return message;
    }

    /**
     * Create an error message from the given parameters.
     * 
     * @param first
     * @param last
     * @param text
     * @return
     */
    public static IMessage createErrorMessage(IStructuredDocumentRegion first, IStructuredDocumentRegion last,
            String text) {
        IMessage message = new LocalizedMessage(IMessage.HIGH_SEVERITY, text);

        message.setOffset(first.getStartOffset());
        message.setLength(last.getEndOffset() - first.getStartOffset());
        message.setLineNo(first.getParentDocument().getLineOfOffset(first.getStartOffset()));

        return message;
    }

    /**
     * Create an error message from the given parameters.
     * 
     * @param first
     * @param last
     * @param text
     * @return
     */
    public static IMessage createErrorMessage(String text) {
        IMessage message = new LocalizedMessage(IMessage.HIGH_SEVERITY, text);

        message.setOffset(0);
        message.setLength(0);
        message.setLineNo(0);

        return message;
    }

}
