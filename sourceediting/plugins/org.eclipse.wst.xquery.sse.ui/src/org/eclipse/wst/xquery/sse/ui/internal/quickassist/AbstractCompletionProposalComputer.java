/*******************************************************************************
 * Copyright (c) 2011 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.xquery.sse.ui.internal.quickassist;

import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocumentRegion;
import org.eclipse.wst.sse.core.internal.provisional.text.ITextRegion;
import org.eclipse.wst.sse.ui.contentassist.CompletionProposalInvocationContext;
import org.eclipse.wst.sse.ui.contentassist.ICompletionProposalComputer;

/**
 * Base class for completion proposal computer.
 * 
 * <p>
 * Provide facility to get ASTNode corresponding to the caret current location
 * 
 * @author <a href="villard@us.ibm.com">Lionel Villard</a>
 */
public abstract class AbstractCompletionProposalComputer implements ICompletionProposalComputer {

    // Methods

    /**
     * Extracts the prefix considering only the current text region of the given structured document
     * region
     * 
     * @param region
     *            the region
     * @param offset
     *            offset into document
     * @return the prefix to consider
     */
    protected String extractPrefix(IStructuredDocumentRegion region, int offset) {
        ITextRegion subregion = region.getRegionAtCharacterOffset(offset);
        if (subregion != null) {
            int length = offset - region.getStart() - subregion.getStart();
            if (length > 0 && length <= subregion.getLength()) {
                return region.getFullText(subregion).substring(0, length);
            }
        }

        return "";
    }

    // Implements ICompletionProposalComputer

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.wst.sse.ui.contentassist.ICompletionProposalComputer#sessionStarted()
     */
    public void sessionStarted() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.wst.sse.ui.contentassist.ICompletionProposalComputer#computeContextInformation
     * (org.eclipse.wst.sse.ui.contentassist.CompletionProposalInvocationContext,
     * org.eclipse.core.runtime.IProgressMonitor)
     */
    @SuppressWarnings("rawtypes")
    public List computeContextInformation(CompletionProposalInvocationContext context, IProgressMonitor monitor) {
        return Collections.EMPTY_LIST;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.wst.sse.ui.contentassist.ICompletionProposalComputer#getErrorMessage()
     */
    public String getErrorMessage() {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.wst.sse.ui.contentassist.ICompletionProposalComputer#sessionEnded()
     */
    public void sessionEnded() {

    }

}
