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
package org.eclipse.wst.xquery.sse.ui.internal.editor;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.DocumentRewriteSession;
import org.eclipse.jface.text.DocumentRewriteSessionType;
import org.eclipse.jface.text.IDocumentExtension4;
import org.eclipse.wst.sse.core.internal.format.AbstractStructuredFormatProcessor;
import org.eclipse.wst.sse.core.internal.format.IStructuredFormatter;
import org.eclipse.wst.sse.core.internal.provisional.IStructuredModel;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocument;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocumentRegion;
import org.eclipse.wst.sse.core.internal.provisional.text.ITextRegion;
import org.eclipse.wst.xquery.sse.core.internal.model.XQueryStructuredModel;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.ASTExprSingleClause;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.ASTVarRef;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.IASTNode;
import org.w3c.dom.Node;

/**
 * XQuery formatter.
 * 
 * @author <a href="villard@us.ibm.com">Lionel Villard</a>
 */
@SuppressWarnings("restriction")
public class XQueryFormatter extends AbstractStructuredFormatProcessor {

	// State

	/** Formatting preferences */
	protected XQueryStructuredFormatPreferences preferences;

	/** Document being formatted */
	protected IStructuredDocument document;

	// Constructor

	public XQueryFormatter() {
		preferences = new XQueryStructuredFormatPreferences();
	}

	// Overrides

	@Override
	public void formatModel(IStructuredModel structuredModel, int start,
			int length) {
		if (structuredModel != null
				&& structuredModel instanceof XQueryStructuredModel) {
			IDocumentExtension4 docExt4 = null;
			if (structuredModel.getStructuredDocument() instanceof IDocumentExtension4) {
				docExt4 = (IDocumentExtension4) structuredModel
						.getStructuredDocument();
			}
			DocumentRewriteSession rewriteSession = null;

			try {
				// whenever formatting model, fire abouttochange/modelchanged
				structuredModel.aboutToChangeModel();
				DocumentRewriteSessionType rewriteType = (length > 1000) ? DocumentRewriteSessionType.UNRESTRICTED
						: DocumentRewriteSessionType.UNRESTRICTED_SMALL;
				rewriteSession = (docExt4 == null || docExt4
						.getActiveRewriteSession() != null) ? null : docExt4
						.startRewriteSession(rewriteType);

				// Perform formatting.
				final XQueryStructuredModel model = (XQueryStructuredModel) structuredModel;
				this.document = model.getStructuredDocument();
				format(model.getModule());

			} finally {
				// we need two finally's, just in case first fails
				try {
					if ((docExt4 != null) && (rewriteSession != null))
						docExt4.stopRewriteSession(rewriteSession);
				} finally {
					// always make sure to fire changedmodel when done
					structuredModel.changedModel();
				}
			}
		}
	}

	@Override
	protected String getFileExtension() {
		return "xq";
	}

	@Override
	protected IStructuredFormatter getFormatter(Node node) {
		return null; // TODO.
	}

	@Override
	protected void refreshFormatPreferences() {

	}

	// Formatting methods

	/**
	 * Format node
	 */
	protected void format(IASTNode node) {
		if (node != null) {
			switch (node.getType()) {
			case IASTNode.VARREF:
				formatVarRef((ASTVarRef) node);
				break;
			case IASTNode.WHERECLAUSE:
				formatExprSingleClause((ASTExprSingleClause) node);
			}

			final int count = node.getChildASTNodesCount();
			for (int i = 0; i < count; i++) {
				format(node.getChildASTNodeAt(i));
			}
		}
	}

	/**
	 * @param node
	 */
	protected void formatExprSingleClause(ASTExprSingleClause node) {
		format(node.getExpr());
		
	}

	/**
	 * Format varref
	 */
	protected void formatVarRef(ASTVarRef node) {
		removeWhitespace(node.getStructuredDocumentRegion());

	}

	/**
	 * Remove whitespace between regions, except the last one.
	 */
	protected void removeWhitespace(IStructuredDocumentRegion region) {
		if (region != null) {
			final int count = region.getNumberOfRegions();
			for (int i = 0; i < count - 1; i++) {
				ITextRegion reg = region.getRegions().get(i);

				int from = region.getStart() + reg.getStart() + reg.getLength();
				int wscount = 0;
				try {
					while (from - wscount >= 0
							&& isWhitespace(document.getChar(from - wscount - 1)))
						wscount ++;
 
					if (wscount > 0)
						document.replaceText(this, from - wscount, wscount,
								"");
				} catch (BadLocationException e) {
					// TODO: log
					// e.printStackTrace();
				}

			}
		}

	}

	final protected boolean isWhitespace(char c) {
		return c == '\n' || c == '\r' || c == ' ' || c == '\t';
	}
}
