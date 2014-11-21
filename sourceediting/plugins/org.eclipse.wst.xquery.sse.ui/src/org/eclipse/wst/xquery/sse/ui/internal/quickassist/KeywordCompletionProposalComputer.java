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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.CompletionProposal;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocumentRegion;
import org.eclipse.wst.sse.ui.contentassist.CompletionProposalInvocationContext;
import org.eclipse.wst.sse.ui.internal.contentassist.ContentAssistUtils;
import org.eclipse.wst.xquery.core.IXQDTLanguageConstants;
import org.eclipse.wst.xquery.core.codeassist.XQDTKeywords;

/**
 * Computer proposing keyword completion
 * 
 * @author <a href="villard@us.ibm.com">Lionel Villard</a>
 */
public class KeywordCompletionProposalComputer extends AbstractCompletionProposalComputer {

    // Constructors

    public KeywordCompletionProposalComputer() {
    }

    // Implements ICompletionProposalComputer

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List computeCompletionProposals(CompletionProposalInvocationContext context, IProgressMonitor monitor) {
        monitor.beginTask("Keyword completion proposal", 1);

        List<ICompletionProposal> proposals = Collections.EMPTY_LIST;

        final ITextViewer viewer = context.getViewer();
        final int offset = context.getInvocationOffset();

        IStructuredDocumentRegion sdregion = ContentAssistUtils.getStructuredDocumentRegion(viewer, offset);

        if (sdregion != null) {
            String prefix = extractPrefix(sdregion, offset);
            String[] keywords = XQDTKeywords.findByPrefix(prefix, IXQDTLanguageConstants.LANGUAGE_XQUERY_SCRIPTING);
            if (keywords != null && keywords.length > 0) {
                proposals = new ArrayList<ICompletionProposal>(keywords.length);

                for (int i = 0; i < keywords.length; i++) {
                    String replacementString = keywords[i].substring(prefix.length());
                    proposals.add(new CompletionProposal(replacementString, offset, 0, replacementString.length(),
                            null, keywords[i], null, null));
                }
            }
        }

        monitor.done();
        return proposals;
    }
}
