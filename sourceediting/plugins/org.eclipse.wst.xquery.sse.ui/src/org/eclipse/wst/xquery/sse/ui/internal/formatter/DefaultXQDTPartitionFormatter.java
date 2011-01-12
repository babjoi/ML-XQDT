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
import org.eclipse.wst.xquery.sse.core.internal.model.ModelHelper;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.IASTFLWOR;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.IASTFunctionDecl;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.IASTNode;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.impl.ASTBindingClause;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.impl.ASTClause;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.impl.ASTContextItem;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.impl.ASTExprSingleClause;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.impl.ASTFunctionDecl;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.impl.ASTLiteral;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.impl.ASTModule;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.impl.ASTOperator;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.impl.ASTParentherized;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.impl.ASTVarDecl;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.impl.ASTVarRef;
import org.eclipse.wst.xquery.sse.core.internal.regions.XQueryRegions;

/**
 * Formatter for default XQDT partition.
 * 
 * @author <a href="villard@us.ibm.com">Lionel Villard</a>
 */
public class DefaultXQDTPartitionFormatter {

    public final static DefaultXQDTPartitionFormatter SINGLETON = new DefaultXQDTPartitionFormatter();

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
        // Delete leading white spaces
        IStructuredDocumentRegion first = node.getFirstStructuredDocumentRegion().getParentDocument()
                .getFirstStructuredDocumentRegion();
        if (first.getFullText().trim().equals("")) {
            normalizeWhitespace(first, edit, 0, 0, 0);
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
                formatFLWOR((IASTFLWOR)node, edit, prefs);
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
            case IASTNode.CONTEXTITEM:
                formatContextItem((ASTContextItem)node, edit, prefs);
                break;
            }

        }
    }

    protected void formatModule(ASTModule node, TextEdit edit, XQDTStructuredFormatPreferences prefs) {
        final int count = node.getChildASTNodesCount();
        for (int i = 0; i < count; i++) {
            IASTNode child = node.getChildASTNodeAt(i);

            prefs.pushTrailingWhitespaceLength(0, 2);
            formatNode(child, edit, prefs);
            prefs.popTrailingWhitespaceLength();
        }

        if (node.getQueryBody() != null) {
            formatNode(node.getQueryBody(), edit, prefs);
        }

    }

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

            if (i < count - 1 && child.getLastStructuredDocumentRegion() != null) {
                IStructuredDocumentRegion opRegion = child.getLastStructuredDocumentRegion().getNext();
                if (opRegion != null) {
                    // Start the next expression on a new line if it is complex
                    int type = node.getChildASTNodeAt(i + 1).getType();
                    if (type == IASTNode.LITERAL || type == IASTNode.CONTEXTITEM || type == IASTNode.VARREF) {
                        normalizeWhitespace(opRegion, edit, 0, 0, 1);
                    } else {
                        normalizeWhitespace(opRegion, edit, 0, 1, 0);
                    }
                }
            }

        }
    }

    protected void formatParentherized(ASTParentherized node, TextEdit edit, XQDTStructuredFormatPreferences prefs) {
        IStructuredDocumentRegion sdregion = node.getFirstStructuredDocumentRegion(); // '('

        normalizeWhitespace(sdregion, edit, 0, 0, 0);

        if (node.getExpr() != null) {
            prefs.pushTrailingWhitespaceLength(0, 0);
            formatNode(node.getExpr(), edit, prefs);
            prefs.popTrailingWhitespaceLength();
        }

        sdregion = node.getLastStructuredDocumentRegion(); // ')'
        normalizeWhitespace(sdregion, edit, 0, prefs.getLineSeparatorCount(), prefs.getTrailingWhitespaceLength());
    }

    protected void formatFLWOR(IASTFLWOR node, TextEdit edit, XQDTStructuredFormatPreferences prefs) {
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

    protected void formatBindingClause(ASTBindingClause clause, TextEdit edit, XQDTStructuredFormatPreferences prefs) {
        IStructuredDocumentRegion sdregion = clause.getFirstStructuredDocumentRegion();

        final int kwLength = sdregion.getFullText().trim().length(); // 'let' or 'for' or 'some' or 'every' ...
        final int bindingIndentLength = prefs.getIndent().length() + kwLength + 1;

        // Normalize spaces between keyword and first variable binding
        normalizeWhitespace(sdregion, edit, 0, 0, 1);

        final int bindingCount = clause.getBindingExprCount();
        int i = 0;
        do {
            sdregion = skipComment(sdregion.getNext()); // '$ ... var'
            normalizeWhitespace(sdregion, edit, 0, 0, 1); // ws between '$' and variable name

            sdregion = skipComment(sdregion.getNext());
            sdregion = formatTypeDeclarationOpt(sdregion, edit, prefs);
            sdregion = formatPositionalVarOpt(sdregion, edit, prefs);

            normalizeWhitespace(sdregion, edit, 0, 0, 1); // token before binding expression

            sdregion = skipComment(sdregion.getNext());

            // Format binding expression
            boolean lastBinding = i == bindingCount - 1;
            prefs.pushTrailingWhitespaceLength(lastBinding ? 1 : 0, lastBinding ? 1 : 0);
            formatNode(clause.getBindingExpr(i), edit, prefs);
            prefs.popTrailingWhitespaceLength();

            sdregion = skipComment(clause.getBindingExpr(i).getLastStructuredDocumentRegion().getNext());

            if (ModelHelper.sameRegionType(sdregion, XQueryRegions.COMMA)) {
                normalizeWhitespace(sdregion, edit, 0, 1, bindingIndentLength);
            } else {
                break;
            }

            i++;

        } while (sdregion != null);
    }

    protected void formatFunctionDecl(ASTFunctionDecl node, TextEdit edit, XQDTStructuredFormatPreferences prefs) {
        IStructuredDocumentRegion region = node.getFirstStructuredDocumentRegion(); // declare ... function
        normalizeWhitespace(region, edit, 1, 0, 1);

        region = region.getNext(); // function name
        region = skipComment(region);

        normalizeWhitespace(region, edit, 0, 0, 0);

        region = region.getNext(); // First param
        region = skipComment(region);

        if (region.getType() != XQueryRegions.RPAR) {
            region = formatFunctionParams(region, node, edit, prefs);
        }

        normalizeWhitespace(region, edit, 0, 0, 1); // RPar

        region = skipComment(region.getNext());
        region = formatTypeDeclarationOpt(region, edit, prefs);

        if (ModelHelper.sameRegionType(region, XQueryRegions.KW_EXTERNAL)) {
            normalizeWhitespace(region, edit, 0, 0, 0); // 'external'
            region = skipComment(region.getNext());

        } else {
            // TODO: sequential

            normalizeWhitespace(region, edit, 0, 1, 2);

            String saveIndent = prefs.getIndent();
            setIndent(saveIndent.length() + 2, prefs);
            prefs.pushTrailingWhitespaceLength(0, 1);
            formatNode(node.getBody(), edit, prefs);

            prefs.setIndent(saveIndent);
            prefs.popTrailingWhitespaceLength();
        }

        region = node.getLastStructuredDocumentRegion(); // Should be ';'
        normalizeWhitespace(region, edit, 0, prefs.getLineSeparatorCount(), prefs.getTrailingWhitespaceLength());

    }

    protected void formatVarDecl(ASTVarDecl node, TextEdit edit, XQDTStructuredFormatPreferences prefs) {
        // Format 'declare ... variable '
        IStructuredDocumentRegion region = node.getFirstStructuredDocumentRegion();
        normalizeWhitespace(region, edit, 1, 0, 1);

        region = node.getNameStructuredDocumentRegion();
        normalizeWhitespace(region, edit, 0, 0, 1);
        region = skipComment(region.getNext());

        // Either := or external
        if (region.getType() == XQueryRegions.KW_EXTERNAL) {
            normalizeWhitespace(region, edit, 0, 0, 0); // external
            region = skipComment(region.getNext()); // ;
        } else {
            normalizeWhitespace(region, edit, 0, 0, 1); // :=
            String saveIndent = prefs.getIndent();
            prefs.setIndent(prefs.getIndent() + "  ");
            prefs.pushTrailingWhitespaceLength(0, 0);
            formatNode(node.getExpr(), edit, prefs);
            prefs.popTrailingWhitespaceLength();
            prefs.setIndent(saveIndent);

            region = node.getLastStructuredDocumentRegion(); // ; 
        }

        normalizeWhitespace(region, edit, 0, prefs.getLineSeparatorCount(), prefs.getTrailingWhitespaceLength());
    }

    protected void formatExprSingleClause(ASTExprSingleClause node, TextEdit edit, XQDTStructuredFormatPreferences prefs) {
        IStructuredDocumentRegion region = node.getFirstStructuredDocumentRegion();
        normalizeWhitespace(region, edit, 0, 0, 1);

        prefs.pushTrailingWhitespaceLength(1, 1);
        formatNode(node.getExpr(), edit, prefs);
        prefs.popTrailingWhitespaceLength();
    }

    protected void formatVarRef(ASTVarRef node, TextEdit edit, XQDTStructuredFormatPreferences prefs) {
        normalizeWhitespace(node.getFirstStructuredDocumentRegion(), edit, 0, prefs.getLineSeparatorCount(),
                prefs.getTrailingWhitespaceLength());

    }

    protected void formatLiteral(ASTLiteral node, TextEdit edit, XQDTStructuredFormatPreferences prefs) {
        normalizeWhitespace(node.getFirstStructuredDocumentRegion(), edit, 0, prefs.getLineSeparatorCount(),
                prefs.getTrailingWhitespaceLength());

    }

    protected void formatContextItem(ASTContextItem node, TextEdit edit, XQDTStructuredFormatPreferences prefs) {
        normalizeWhitespace(node.getFirstStructuredDocumentRegion(), edit, 0, prefs.getLineSeparatorCount(),
                prefs.getTrailingWhitespaceLength());
    }

    /**
     * Normalize whitespace between all the text regions contained in the given structured region
     * 
     * @param inwsc
     *            number of whitespace characters to normalize to
     * @param lineSepCount
     *            number of line separators to have after the last text region
     * @param lastwsc
     *            number of whitespace characters to normalize to for the last text region. Applied
     *            after line delimiters
     * 
     */
    protected void normalizeWhitespace(IStructuredDocumentRegion region, TextEdit edit, int inwsc, int lineSepCount,
            int lastwsc) {
        if (region != null) {

            final int count = region.getNumberOfRegions();
            for (int i = 0; i < count - 1; i++) {
                normalizeWhitespace(region, region.getRegions().get(i), edit, 0, inwsc);
            }

            normalizeWhitespace(region, region.getLastRegion(), edit, lineSepCount, lastwsc);
        }
    }

    /**
     * Normalize whitespace characters at the end of the given text region
     * 
     * @param sdregion
     * @param region
     * @param edit
     * @param lineSepCount
     *            number of line separators
     * @param wsc
     *            number of white space characters
     */
    protected void normalizeWhitespace(IStructuredDocumentRegion sdregion, ITextRegion region, TextEdit edit,
            int lineSepCount, int wsc) {
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

            if (lineSepCount > 0 || wsc > 0) {
                StringBuffer buffer = new StringBuffer();
                String lineDelim = sdregion.getParentDocument().getLineDelimiter();
                for (int i = 0; i < lineSepCount; i++) {
                    buffer.append(lineDelim);
                }

                for (int i = 0; i < wsc; i++) {
                    buffer.append(' ');
                }
                edit.addChild(new InsertEdit(endOffset, buffer.toString()));
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

    IStructuredDocumentRegion formatFunctionParams(IStructuredDocumentRegion region, IASTFunctionDecl node,
            TextEdit edit, XQDTStructuredFormatPreferences prefs) {
        do {
            IStructuredDocumentRegion next = skipComment(region.getNext());

            final boolean isComma = next.getType() == XQueryRegions.COMMA;
            boolean isRPar = next.getType() == XQueryRegions.RPAR;

            normalizeWhitespace(region, edit, 0, 0, isComma || isRPar ? 0 : 1);
            if (!isComma && !isRPar) {
                // Must be a type declaration
                region = next; // as
                normalizeWhitespace(region, edit, 0, 0, 1);

                region = skipComment(region.getNext()); // Type
                normalizeWhitespace(region, edit, 0, 0, 0);

                next = skipComment(region.getNext()); // comma or rpar
                isRPar = next.getType() == XQueryRegions.RPAR;
            }

            if (isRPar) {
                region = next;
                break;
            }

            region = next;
            normalizeWhitespace(region, edit, 0, 0, 1);
            region = skipComment(region.getNext());
        } while (region != null);

        return region;
    }

    protected IStructuredDocumentRegion formatTypeDeclarationOpt(IStructuredDocumentRegion region, TextEdit edit,
            XQDTStructuredFormatPreferences prefs) {
        if (ModelHelper.sameRegionType(region, XQueryRegions.KW_AS)) {
            normalizeWhitespace(region, edit, 0, 0, 1);
            region = skipComment(region.getNext());

            return formatSequenceType(region, edit, prefs);
        }

        return region;

    }

    protected IStructuredDocumentRegion formatPositionalVarOpt(IStructuredDocumentRegion region, TextEdit edit,
            XQDTStructuredFormatPreferences prefs) {
        if (ModelHelper.sameRegionType(region, XQueryRegions.KW_AT)) {
            normalizeWhitespace(region, edit, 0, 0, 1);
            region = skipComment(region.getNext()); // '$' .. varname

            normalizeWhitespace(region, edit, 0, 0, 1);
            return skipComment(region.getNext());
        }

        return region;
    }

    protected IStructuredDocumentRegion formatSequenceType(IStructuredDocumentRegion region, TextEdit edit,
            XQDTStructuredFormatPreferences prefs) {
        normalizeWhitespace(region, edit, 0, 0, 1);
        return skipComment(region.getNext());
    }

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
