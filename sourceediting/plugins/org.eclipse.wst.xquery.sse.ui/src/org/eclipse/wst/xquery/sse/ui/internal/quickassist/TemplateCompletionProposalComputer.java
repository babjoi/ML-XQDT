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

import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.wst.sse.ui.contentassist.CompletionProposalInvocationContext;

/**
 * Computer proposing template completion
 * 
 * @author <a href="villard@us.ibm.com">Lionel Villard</a>
 */
public class TemplateCompletionProposalComputer extends AbstractCompletionProposalComputer {

    //  State

    /** The template processor */
    private XQDTTemplateCompletionProcessor processor;

    // Constructors

    public TemplateCompletionProposalComputer() {
        this.processor = new XQDTTemplateCompletionProcessor();
    }

    // Implement ICompletionProposalComputer
    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.wst.sse.ui.contentassist.ICompletionProposalComputer#computeCompletionProposals
     * (org.eclipse.wst.sse.ui.contentassist.CompletionProposalInvocationContext,
     * org.eclipse.core.runtime.IProgressMonitor)
     */
    @SuppressWarnings({ "rawtypes" })
    public List computeCompletionProposals(CompletionProposalInvocationContext context, IProgressMonitor monitor) {
        monitor.beginTask("Template proposal", 1);

        ICompletionProposal[] proposals = processor.computeCompletionProposals(context.getViewer(),
                context.getInvocationOffset());
        List<ICompletionProposal> list = Arrays.asList(proposals);
        monitor.done();
        return list;
    }

}
