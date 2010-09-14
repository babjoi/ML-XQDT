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
package org.eclipse.wst.xquery.sse.ui.internal.formatter;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.text.edits.DeleteEdit;
import org.eclipse.text.edits.TextEdit;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocumentRegion;
import org.eclipse.wst.sse.core.internal.provisional.text.ITextRegion;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.ASTExprSingleClause;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.ASTVarRef;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.IASTNode;

/**
 * Formatter for default XQDT partition.
 * 
 * @author <a href="villard@us.ibm.com">Lionel Villard</a>
 */
public class DefaultXQDTPartitionFormatter {

    public static DefaultXQDTPartitionFormatter SINGLETON = new DefaultXQDTPartitionFormatter();

    // Formatting methods

    /**
     * Format node
     * 
     * @param edit
     */
    public void format(IASTNode node, TextEdit edit) {
        if (node != null) {
            switch (node.getType()) {
            case IASTNode.VARREF:
                formatVarRef((ASTVarRef)node, edit);
                break;
            case IASTNode.WHERECLAUSE:
                formatExprSingleClause((ASTExprSingleClause)node, edit);
            }

            final int count = node.getChildASTNodesCount();
            for (int i = 0; i < count; i++) {
                format(node.getChildASTNodeAt(i), edit);
            }
        }
    }

    /**
     * @param node
     * @param edit
     */
    protected void formatExprSingleClause(ASTExprSingleClause node, TextEdit edit) {
        format(node.getExpr(), edit);

    }

    /**
     * Format varref
     * 
     * @param edit
     * 
     */
    protected void formatVarRef(ASTVarRef node, TextEdit edit) {
        TextEdit wsedit = removeWhitespace(node.getStructuredDocumentRegion());
        if (wsedit != null) {
            edit.addChild(wsedit);
        }
    }

    /**
     * Remove whitespace between regions, except the last one.
     */
    protected TextEdit removeWhitespace(IStructuredDocumentRegion region) {

        if (region != null) {
            final IDocument document = region.getParentDocument();

            final int count = region.getNumberOfRegions();
            for (int i = 0; i < count - 1; i++) {
                ITextRegion reg = region.getRegions().get(i);

                int from = region.getStart() + reg.getStart() + reg.getLength();
                int wscount = 0;
                try {
                    while (from - wscount >= 0 && isWhitespace(document.getChar(from - wscount - 1))) {
                        wscount++;
                    }

                    if (wscount > 0) {
                        return new DeleteEdit(from - wscount, wscount);
                    }
                } catch (BadLocationException e) {
                    // TODO: log
                    // e.printStackTrace();
                }

            }
        }

        return null;
    }

    final protected boolean isWhitespace(char c) {
        return c == '\n' || c == '\r' || c == ' ' || c == '\t';
    }
}
