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
package org.eclipse.wst.xquery.sse.ui.internal.quickassist;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationValidator;
import org.eclipse.wst.sse.core.StructuredModelManager;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocument;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocumentRegion;
import org.eclipse.wst.xquery.sse.core.internal.model.XQueryStructuredModel;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.IASTNode;

/**
 * Base class for XQDT content assist processors
 * 
 * @author <a href="villard@us.ibm.com">Lionel Villard</a>
 */
abstract class AbstractContentAssistProcessor implements IContentAssistProcessor {

	// Methods

	/**
	 * Compute completion proposals
	 */
	protected abstract ICompletionProposal[] propose(int offset, IStructuredDocument document,
			IStructuredDocumentRegion region, XQueryStructuredModel model, IASTNode node);

	// Implements IContentAssistProcessor

	public ICompletionProposal[] computeCompletionProposals(ITextViewer viewer, int offset) {
		final IStructuredDocument document = (IStructuredDocument) viewer.getDocument();
		final IStructuredDocumentRegion region = document.getRegionAtCharacterOffset(offset);

		if (region != null) {
			final XQueryStructuredModel model = (XQueryStructuredModel) StructuredModelManager.getModelManager()
					.getModelForRead(document);

			final IASTNode node = model.getASTNode(region);

			ICompletionProposal[] proposals = propose(offset, document, region, model, node);

			model.releaseFromRead();

			return proposals;
		}
		return null;
	}

	public IContextInformation[] computeContextInformation(ITextViewer viewer, int offset) {

		return null;
	}

	public char[] getCompletionProposalAutoActivationCharacters() {
		return null;
	}

	public char[] getContextInformationAutoActivationCharacters() {
		return null;
	}

	public IContextInformationValidator getContextInformationValidator() {
		return null;
	}

	public String getErrorMessage() {
		return null;
	}
}
