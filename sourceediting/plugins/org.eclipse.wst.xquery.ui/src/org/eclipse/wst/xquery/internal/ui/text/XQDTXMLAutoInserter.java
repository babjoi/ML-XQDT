/*******************************************************************************
 * Copyright (c) 2010 28msec Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     William Candillon (28msec) - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.xquery.internal.ui.text;

import java.util.LinkedList;
import java.util.List;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.Token;
import org.eclipse.dltk.internal.ui.editor.ScriptEditor;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.swt.custom.VerifyKeyListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.wst.xquery.internal.core.parser.antlr.XMLLexer;

public class XQDTXMLAutoInserter implements VerifyKeyListener {

    private final ScriptEditor editor;

    public XQDTXMLAutoInserter(ScriptEditor editor) {
        this.editor = editor;
    }

    public void verifyKey(VerifyEvent event) {
        // early pruning to slow down normal typing as little as possible
        if (!event.doit || this.editor.getInsertMode() != ScriptEditor.SMART_INSERT) {
            return;
        }

        switch (event.character) {
        case '>':
        case '/':
        case '"':
        case '\'':
            break;
        default:
            return;
        }

        final ISourceViewer sourceViewer = this.editor.getScriptSourceViewer();
        IDocument document = sourceViewer.getDocument();

        final Point selection = sourceViewer.getSelectedRange();
        final int offset = selection.x;
        final int length = selection.y;

        String source = document.get();
        String begin = source.substring(0, offset);
//        @SuppressWarnings("unused")
//        String end = source.substring(offset);

        try {

            IRegion startLine = document.getLineInformationOfOffset(offset);
//            @SuppressWarnings("unused")
//            IRegion endLine = document.getLineInformationOfOffset(offset + length);
            begin = begin.substring(startLine.getOffset());
            // end = end.substring(0, endLine.getLength());

            if (event.character == '>') {
                computeClosingTag(document, begin, offset, length);
                return;
            } else if (event.character == '/') {
                computeClosingTag(document, begin, offset, length, true);
                return;
            }

            ANTLRStringStream input = new ANTLRStringStream(begin + event.character);

            XMLLexer lexer = new XMLLexer(input);
            Token token = null;
            Token lastToken = null;
            boolean isInQuotAttr = false;
            boolean isInAposAttr = false;
            while ((token = lexer.nextToken()).getType() != -1) {
                lastToken = token;
                isInQuotAttr = lexer.isInQuotAttr();
                isInAposAttr = lexer.isInAposAttribute();
            }
            if ((event.character == '"' && lastToken.getType() == XMLLexer.QUOT && isInQuotAttr && !isInAposAttr)
                    || (event.character == '\'' && lastToken.getType() == XMLLexer.APOS && isInAposAttr && !isInQuotAttr)) {
                document.replace(offset, length, String.valueOf(event.character));
            }
        } catch (Exception e) {
            // TODO: log the exception
            e.printStackTrace();
        }
    }

    private XMLLexer getXMLLexer(String source) {
        return new XMLLexer(new ANTLRStringStream(source));
    }

    private XQDTXMLAutoInserter computeClosingTag(IDocument document, String source, int offset, int length)
            throws Exception {
        return computeClosingTag(document, source, offset, length, false);
    }

    private XQDTXMLAutoInserter computeClosingTag(IDocument document, String source, int offset, int length,
            boolean isSingleTag) throws Exception {
        if (document.get().charAt(offset - 1) == '/') {
            return this;
        }
        XMLLexer lexer = getXMLLexer(source);
        Token token = null;
        List<String> tagName = new LinkedList<String>();
        List<Token> tokens = new LinkedList<Token>();

        while ((token = lexer.nextToken()).getType() != -1 && token.getCharPositionInLine() < offset) {
            tokens.add(token);
        }

        for (int i = tokens.size() - 1; i >= 0; i--) {
            token = tokens.get(i);
            if (token.getType() == XMLLexer.SMALLER) {
                String tag = "";
                for (int j = tagName.size() - 1; j >= 0; j--) {
                    tag += tagName.get(j);
                }
                String[] tags = tag.split(" ");
                if (tags[0].matches("[A-Za-z_][A-Za-z_:\\-]*(:[A-Za-z_][A-Za-z_:\\-]*)?") && tags.length == 1) {
                    if (isSingleTag) {
                        document.replace(offset, length, ">");
                    } else {
                        document.replace(offset, length, "</" + tags[0] + '>');
                    }
                }
                break;
            } else {
                tagName.add(token.getText());
            }
        }
        return this;
    }
}
