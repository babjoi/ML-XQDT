/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     
 *******************************************************************************/
package org.eclipse.wst.xquery.sse.ui.internal.quickassist;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.contentassist.CompletionProposal;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocument;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocumentRegion;
import org.eclipse.wst.xquery.sse.core.internal.model.ModelHelper;
import org.eclipse.wst.xquery.sse.core.internal.model.XQueryStructuredModel;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.IASTNode;
import org.eclipse.wst.xquery.sse.core.internal.regions.XQueryRegions;
import org.eclipse.wst.xquery.sse.core.internal.sdregions.XQueryStructuredDocumentRegion;

/**
 * XQuery variable content assistant processor.
 * 
 * <p>
 * For now it's really a basic content assist for demo purposes.
 * 
 * <p>
 * Should be hooked up somehow to the XML content assist processor.
 * 
 * @author <a href="villard@us.ibm.com">Lionel Villard</a>
 */
@SuppressWarnings("restriction")
public class XQDTVariableContentAssistProcessor extends AbstractContentAssistProcessor {

	// Overrides

	@Override
	public char[] getCompletionProposalAutoActivationCharacters() {
		return new char[] { '$' };
	}

	@Override
	public char[] getContextInformationAutoActivationCharacters() {
		return new char[] { '$' };
	}

	@Override
	protected ICompletionProposal[] propose(int offset, IStructuredDocument document, IStructuredDocumentRegion region,
			XQueryStructuredModel model, IASTNode node) {
		return proposeInScopeVariables(offset, document, region, model, node);
	}

	// Helpers
 
	private ICompletionProposal[] proposeInScopeVariables(int offset, IStructuredDocument document,
			IStructuredDocumentRegion region, XQueryStructuredModel model, IASTNode node) {
		// In the context of a variable?
		boolean varrefCtx = region.getType() == XQueryRegions.DOLLAR;
		if (!varrefCtx) {
			// Maybe the cursor is at the beginning of a region following a
			// varref region
			varrefCtx = offset == region.getStartOffset() && region.getPrevious() != null
					&& region.getPrevious().getType() == XQueryRegions.DOLLAR;

			if (varrefCtx) {
				region = region.getPrevious();
				node = ((XQueryStructuredDocumentRegion) region).getASTNode();
			}
		}

		if (varrefCtx && node != null) {
			List<String> vars = ModelHelper.getInScopeVariables(node);
			if (vars != null) {
				try {
					final String prefix;
					final int suffixLength;
					if (offset == region.getStart() + 1
							&& (document.getLength() <= offset || isWhitespace(document.getChar(offset)))) {
						// Right after the '$' sign and the next character is a
						// whitespace => ignore the variable name which might be
						// part of the next token
						prefix = "$";
						suffixLength = 0;
					} else {
						prefix = document.get(region.getStart(), offset - region.getStart());
						suffixLength = region.getText().trim().length() - prefix.length();
					}

					List<ICompletionProposal> proposals = new ArrayList<ICompletionProposal>();

					for (int i = vars.size() - 1; i >= 0; i--) {
						String var = vars.get(i);
						if (var.startsWith(prefix)) {
							final String addStr = var.substring(prefix.length());

							proposals.add(new CompletionProposal(addStr, offset, suffixLength, addStr.length(), null,
									var, null, "Local variable"));
						}
					}

					if (proposals.size() > 0) {
						ICompletionProposal array[] = new ICompletionProposal[proposals.size()];
						return proposals.toArray(array);
					}
				} catch (BadLocationException e) {
					// TODO: log
				}
			}
		}

		return null;

	}

	private static boolean isWhitespace(char c) {
		return c == '\n' || c == '\r' || c == ' ' || c == '\t';
	}

}
