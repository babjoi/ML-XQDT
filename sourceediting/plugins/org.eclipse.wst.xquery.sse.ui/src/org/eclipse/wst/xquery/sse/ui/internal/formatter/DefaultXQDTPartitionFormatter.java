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
import org.eclipse.text.edits.InsertEdit;
import org.eclipse.text.edits.TextEdit;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocumentRegion;
import org.eclipse.wst.sse.core.internal.provisional.text.ITextRegion;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.ASTBindingClause;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.ASTClause;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.ASTExprSingleClause;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.ASTFLWOR;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.ASTFunctionDecl;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.ASTLiteral;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.ASTModule;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.ASTOperator;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.ASTParentherized;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.ASTVarDecl;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.ASTVarRef;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.IASTNode;
import org.eclipse.wst.xquery.sse.core.internal.regions.XQueryRegions;

/**
 * Formatter for default XQDT partition.
 * 
 * @author <a href="villard@us.ibm.com">Lionel Villard</a>
 */
public class DefaultXQDTPartitionFormatter {

    public static DefaultXQDTPartitionFormatter SINGLETON = new DefaultXQDTPartitionFormatter();

    // Constructors

    private DefaultXQDTPartitionFormatter() {
    }

    // Formatting methods

    /**
     * Format query module
     * 
     * @param edit
     * @param prefs
     * 
     */
    public void format(IASTNode node, TextEdit edit, XQDTStructuredFormatPreferences prefs) {
        // Remove leading white spaces
        if (node.getFirstStructuredDocumentRegion() != null) {
            normalizeWhitespace(node.getFirstStructuredDocumentRegion(), edit, 0, 0, 0);
        }

        formatNode(node, edit, prefs);
    }

    /**
     * Dispatch formatting to proper sub-methods based on node type
     * 
     * @param node
     * @param edit
     * @param prefs
     */
    protected void formatNode(IASTNode node, TextEdit edit, XQDTStructuredFormatPreferences prefs) {
        // TODO:a visitor pattern could be of a help here... Wait for DLTK/SSE integration.

        if (node != null) {
            switch (node.getType()) {
            case IASTNode.MODULE:
                formatModule((ASTModule)node, edit, prefs);
                break;
            case IASTNode.OPERATOR:
                formatOperator((ASTOperator)node, edit, prefs);
                break;
            case IASTNode.FUNCTIONDECL:
                formatFunctionDecl((ASTFunctionDecl)node, edit, prefs);
                break;
            case IASTNode.VARDECL:
                formatVarDecl((ASTVarDecl)node, edit, prefs);
                break;
            case IASTNode.FLWOR:
                formatFLWOR((ASTFLWOR)node, edit, prefs);
                break;
            case IASTNode.VARREF:
                formatVarRef((ASTVarRef)node, edit, prefs);
                break;
            case IASTNode.PARENTHERIZED:
                formatParentherized((ASTParentherized)node, edit, prefs);
                break;
            case IASTNode.LITERAL:
                formatLiteral((ASTLiteral)node, edit, prefs);
                break;
            case IASTNode.WHERECLAUSE:
                formatExprSingleClause((ASTExprSingleClause)node, edit, prefs);
                break;
            }

        }
    }

    protected void formatModule(ASTModule node, TextEdit edit, XQDTStructuredFormatPreferences prefs) {
        final int count = node.getChildASTNodesCount();
        for (int i = 0; i < count; i++) {
            IASTNode child = node.getChildASTNodeAt(i);

            prefs.pushTrailingWhitespaceLength(1, 1);

            formatNode(child, edit, prefs);

            prefs.popTrailingWhitespaceLength();
        }

        if (node.getQueryBody() != null) {
            formatNode(node.getQueryBody(), edit, prefs);
        }

    }

    /**
     * @param node
     * @param edit
     * @param prefs
     */
    protected void formatOperator(ASTOperator node, TextEdit edit, XQDTStructuredFormatPreferences prefs) {
        final int count = node.getChildASTNodesCount();
        for (int i = 0; i < count; i++) {
            IASTNode child = node.getChildASTNodeAt(i);

            if (i < count - 1) {
                prefs.pushTrailingWhitespaceLength(1, 0);
            }

            formatNode(child, edit, prefs);

            if (i < count - 1) {
                prefs.popTrailingWhitespaceLength();
            }

            if (child.getLastStructuredDocumentRegion() != null) {
                IStructuredDocumentRegion opRegion = child.getLastStructuredDocumentRegion().getNext();
                if (opRegion != null) {
                    normalizeWhitespace(opRegion, edit, 0, 1, 0);
                }
            }

        }
    }

    /**
     * @param node
     * @param edit
     * @param prefs
     */
    protected void formatParentherized(ASTParentherized node, TextEdit edit, XQDTStructuredFormatPreferences prefs) {
        IStructuredDocumentRegion sdregion = node.getFirstStructuredDocumentRegion(); // '('

        normalizeWhitespace(sdregion, edit, 0, 0, 0);

        if (node.getExpr() != null) {
            prefs.pushTrailingWhitespaceLength(0, 0);
            formatNode(node.getExpr(), edit, prefs);
            prefs.popTrailingWhitespaceLength();
        }

        sdregion = node.getLastStructuredDocumentRegion(); // ')'
        normalizeWhitespace(sdregion, edit, 0, prefs.getTrailingWhitespaceLength(), prefs.getLineSeparatorCount());
    }

    /**
     * @param node
     * @param edit
     * @param prefs
     */
    protected void formatFLWOR(ASTFLWOR node, TextEdit edit, XQDTStructuredFormatPreferences prefs) {
        // Default formatting
        // for/let $fffds as xs:type in ...,
        //         $ff  in ...
        // for/let ...
        // order by ...
        // return
        // ...

        // Format clauses
        final int count = node.getClauseCount();
        for (int i = 0; i < count; i++) {
            formatClause(node.getClause(i), edit, prefs);
        }

        // Format return
        IASTNode returnNode = node.getReturnExpr();
        if (returnNode != null) {
            final String savedIndent = prefs.getIndent();
            prefs.pushTrailingWhitespaceLength(0, 0);
            formatNode(returnNode, edit, prefs);
            prefs.setIndent(savedIndent);
            prefs.popTrailingWhitespaceLength();
        }

    }

    /**
     * @param clause
     * @param edit
     * @param prefs
     */
    protected void formatClause(ASTClause clause, TextEdit edit, XQDTStructuredFormatPreferences prefs) {
        switch (clause.getType()) {
        case IASTNode.FORCLAUSE:
        case IASTNode.LETCLAUSE:
        case IASTNode.QUANTIFIEDCLAUSE:
            formatBindingClause((ASTBindingClause)clause, edit, prefs);
            break;
        case IASTNode.WHERECLAUSE:
            formatExprSingleClause((ASTExprSingleClause)clause, edit, prefs);
            break;
        }

    }

    /**
     * 
     * @param clause
     * @param edit
     * @param prefs
     */
    protected void formatBindingClause(ASTBindingClause clause, TextEdit edit, XQDTStructuredFormatPreferences prefs) {
        //compressLines(clause.getFirstStructuredDocumentRegion(), edit, prefs, 1);
        indent(clause.getFirstStructuredDocumentRegion(), edit, prefs);

        final int kwLength = clause.getFirstStructuredDocumentRegion().getFullText().trim().length(); // 'let' or 'for' or 'some' or 'every' ...
        final String savedIndent = prefs.getIndent();

        // Normalize spaces between keyword and first variable binding
        IStructuredDocumentRegion sdregion = clause.getFirstStructuredDocumentRegion();
        normalizeWhitespace(sdregion, edit, 0, 1, 0);

        final int bindingCount = clause.getBindingExprCount();
        for (int i = 0; i < bindingCount; i++) {
            IStructuredDocumentRegion var = clause.getBindingVariable(i);
            if (var != null) {
                if (i > 0) {
                    // Normalize space/line separator after preceding comma
                    normalizeWhitespace(var.getPrevious(), edit, 0, 0, 1);

                    setIndent(prefs.getIndent().length() + kwLength + 1, prefs);
                    indent(var, edit, prefs);
                }

                normalizeWhitespace(var, edit, 0, 1, 0); // ws between '$' and variable name

                // This might change..
                IStructuredDocumentRegion exprRegion = clause.getBindingExpr(i).getFirstStructuredDocumentRegion();
                IStructuredDocumentRegion next = var.getNext();
                while (next != exprRegion) {
                    normalizeWhitespace(next, edit, 1, 1, 0);
                    next = next.getNext();
                }

                boolean lastBinding = i == bindingCount - 1;
                prefs.pushTrailingWhitespaceLength(lastBinding ? 1 : 0, lastBinding ? 1 : 0);
                formatNode(clause.getBindingExpr(i), edit, prefs);
                prefs.popTrailingWhitespaceLength();
            }

        }

        // Restore indent
        prefs.setIndent(savedIndent);
    }

    protected void formatFunctionDecl(ASTFunctionDecl node, TextEdit edit, XQDTStructuredFormatPreferences prefs) {
        IStructuredDocumentRegion region = node.getFirstStructuredDocumentRegion();
        normalizeWhitespace(region, edit, 1, 1, 0);
    }

    protected void formatVarDecl(ASTVarDecl node, TextEdit edit, XQDTStructuredFormatPreferences prefs) {
        // Format 'declare ... variable '
        IStructuredDocumentRegion region = node.getFirstStructuredDocumentRegion();
        normalizeWhitespace(region, edit, 1, 1, 0);

        region = node.getNameStructuredDocumentRegion();
        normalizeWhitespace(region, edit, 0, 1, 0);
        region = skipComment(region.getNext());

        // Either := or external
        if (region.getType() == XQueryRegions.KW_EXTERNAL) {
            normalizeWhitespace(region, edit, 0, 0, 0); // external
            region = skipComment(region.getNext());
            normalizeWhitespace(region, edit, 0, 0, 1); // ';'
        } else {
            normalizeWhitespace(region, edit, 0, 1, 0); // :=
            String saveIndent = prefs.getIndent();
            prefs.setIndent(prefs.getIndent() + "  ");
            prefs.pushTrailingWhitespaceLength(0, 0);
            formatNode(node.getExpr(), edit, prefs);
            prefs.popTrailingWhitespaceLength();
            prefs.setIndent(saveIndent);

            region = node.getLastStructuredDocumentRegion(); // ;
            normalizeWhitespace(region, edit, 0, 0, 1);
        }

    }

    protected void formatExprSingleClause(ASTExprSingleClause node, TextEdit edit, XQDTStructuredFormatPreferences prefs) {
        formatNode(node.getExpr(), edit, prefs);
    }

    protected void formatVarRef(ASTVarRef node, TextEdit edit, XQDTStructuredFormatPreferences prefs) {
        normalizeWhitespace(node.getFirstStructuredDocumentRegion(), edit, 0, prefs.getTrailingWhitespaceLength(),
                prefs.getLineSeparatorCount());

    }

    /**
     * Format Literal
     * 
     * @param edit
     */
    protected void formatLiteral(ASTLiteral node, TextEdit edit, XQDTStructuredFormatPreferences prefs) {
        normalizeWhitespace(node.getFirstStructuredDocumentRegion(), edit, 0, prefs.getTrailingWhitespaceLength(),
                prefs.getLineSeparatorCount());

    }

    /**
     * Normalize whitespace between all the text regions contained in the given structured region
     * 
     * @param wsc
     *            number of whitespace characters to normalize to
     * @param lastwsc
     *            number of whitespace characters to normalize to for the last text region.
     * @param lineSepCount
     *            number of line separators to have after the last text region
     */
    protected void normalizeWhitespace(IStructuredDocumentRegion region, TextEdit edit, int wsc, int lastwsc,
            int lineSepCount) {
        if (region != null) {

            final int count = region.getNumberOfRegions();
            for (int i = 0; i < count - 1; i++) {
                normalizeWhitespace(region, region.getRegions().get(i), edit, wsc, 0);
            }

            normalizeWhitespace(region, region.getLastRegion(), edit, lastwsc, lineSepCount);
        }
    }

    /**
     * Normalize whitespace characters at the end of the given text region
     * 
     * @param sdregion
     * @param region
     * @param edit
     * @param wsc
     *            number of white space
     * @param lineSepCount
     *            number of line separators
     */
    protected void normalizeWhitespace(IStructuredDocumentRegion sdregion, ITextRegion region, TextEdit edit, int wsc,
            int lineSepCount) {
        final IDocument document = sdregion.getParentDocument();

        int endOffset = sdregion.getStart() + region.getStart() + region.getLength();
        int wscount = 0;
        try {
            // Delete all whitespace chars and line separators

            while (endOffset - wscount > 0 && isWhitespace(document.getChar(endOffset - wscount - 1))) {
                wscount++;
            }

            if (wscount > 0) {
                edit.addChild(new DeleteEdit(endOffset - wscount, wscount));
            }

            // Add proper number of wsc and line seps
            for (int i = 0; i < wsc; i++) {
                edit.addChild(new InsertEdit(endOffset, " "));
            }

            String lineDelim = sdregion.getParentDocument().getLineDelimiter();
            for (int i = 0; i < lineSepCount; i++) {
                edit.addChild(new InsertEdit(endOffset, lineDelim));
            }

        } catch (BadLocationException e) {
            // TODO: log
            // e.printStackTrace();
        }
    }

    /**
     * Apply indent on the given region starts
     * 
     * @param sdregion
     * @param prefs
     */
    protected void indent(IStructuredDocumentRegion sdregion, TextEdit edit, XQDTStructuredFormatPreferences prefs) {
        edit.addChild(new InsertEdit(sdregion.getStart(), prefs.getIndent()));
    }

    /**
     * Make sure there are X number of lines before the given region
     * 
     * @param sdregion
     * @param edit
     * @param prefs
     * @param lineCount
     */
//    protected void normalizeLines(IStructuredDocumentRegion sdregion, TextEdit edit,
//            XQDTStructuredFormatPreferences prefs, int lineCount) {
//        IStructuredDocumentRegion prev = sdregion.getPrevious();
//
//        if (prev != null) {
//            String text = prev.getFullText(prev.getLastRegion());
//
//            int lastws = text.length() - 1;
//            while (lastws >= 0 && isWhitespace(text.charAt(lastws))) {
//                lastws--;
//            }
//
//            lastws++;
//            int delete = -(lastws - text.length());
//            if (delete > 0) {
//                edit.addChild(new DeleteEdit(prev.getEndOffset() - delete, delete));
//            }
//
//            // Add line separator(s)
//            if (prev.getPrevious() != null) {
//                String lineDelim = sdregion.getParentDocument().getLineDelimiter();
//                for (int i = 0; i < lineCount; i++) {
//                    edit.addChild(new InsertEdit(prev.getEndOffset(), lineDelim));
//                }
//            }
//        }
//
//    }

    // Utility methods..

    final protected IStructuredDocumentRegion skipComment(IStructuredDocumentRegion region) {
        while (region != null && region.getType() == XQueryRegions.XQUERY_COMMENT) {
            region = region.getNext();
        }
        return region;
    }

    final protected boolean isWhitespace(char c) {
        return c == '\n' || c == '\r' || c == ' ' || c == '\t';
    }

    final protected void setIndent(int length, XQDTStructuredFormatPreferences prefs) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(' ');
        }
        prefs.setIndent(builder.toString());
    }
}
