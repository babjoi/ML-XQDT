/*******************************************************************************
 * Copyright (c) 2008, 2009 28msec Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Gabriel Petrovay (28msec) - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.xquery.internal.core.parser.antlr;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.CommonErrorNode;
import org.antlr.runtime.tree.CommonTree;
import org.eclipse.jface.text.Position;
import org.eclipse.wst.xquery.core.IXQDTLanguageConstants;
import org.eclipse.wst.xquery.internal.core.parser.visitors.XQDTCommonTreeVisitor;

public class Test {

    public static void main(String[] args) {
        // String input =
        // "declare sequential function declare () {declare $declare := declare; 1;}; 2;.;1,2,3; while(a) {block {block}};";
        String input = "exit with with + (exit with exit)";

        // "<a>{'1'}</b>";
        ANTLRStringStream source = new ANTLRStringStream(input);
        XQueryLexer lexer = new XQueryLexer(source);

        // int i = 0;
        // Token token = lexer.nextToken();
        // while (token != Token.EOF_TOKEN) {
        // token.setTokenIndex(i++);
        // System.out.println(token);
        // token = lexer.nextToken();
        // }
        // if (1==1) {
        // return;
        // }

        NewLazyTokenStream stream = new NewLazyTokenStream(lexer);
        stream.jumpToFirstValidToken();

        XQueryParser p = new XQueryParser(stream);
        p.setCharSource(source);
        p.setTreeAdaptor(new XQDTCommonTreeAdaptor(null));
        p.setLanguageLevel(IXQDTLanguageConstants.LANGUAGE_XQUERY_SCRIPTING);

        try {
            XQueryParser.p_Module_return m = p.p_Module();

            if (m != null)
                System.out.println(((CommonTree)m.tree).toStringTree());
            // System.out.println("------------");
            // System.out.println("Done building");
            // System.out.println("------------");
            System.out.println("------KW------");
            for (Position pos : p.getKeywords()) {
                System.out.println(input.substring(pos.offset, pos.offset + pos.length));
            }
            System.out.println("--------------");

            CommonTree tree = (CommonTree)m.getTree();

            XQDTCommonTreeVisitor visitor = new XQDTCommonTreeVisitor(input.toCharArray(), null);
            ((XQDTCommonTree)tree).accept(visitor);
            // System.out.println("------------\nDone visiting\n------------");

            if (tree instanceof XQDTCommonErrorNode) {
                System.out.println("Error\n-----");
                analyzeError((CommonErrorNode)tree);
                return;
            }
            if (tree.getChildCount() > 0 && tree.getChild(0) instanceof CommonErrorNode) {
                analyzeError((CommonErrorNode)tree.getChild(0));
                return;
            }

            System.out.println(m.getTree().toString());
            printTree(tree, 1);

        } catch (RecognitionException e) {
            e.printStackTrace();
        }
    }

    private static void analyzeError(CommonErrorNode error) throws RecognitionException {
        throw error.trappedException;
    }

    private static void printTree(CommonTree tree, int i) {
        for (Object oc : tree.getChildren()) {
            CommonTree child = (CommonTree)oc;
            if (child.token != null && child.token.getType() != XQueryLexer.EOF)
                System.out.println(getIndent(i) + child.token.getText() + " (" + child.getType() + ")");
            else
                System.out.println("---------EOF---------");

            if (child.getChildren() != null)
                printTree(child, i + 1);
        }
    }

    private static String getIndent(int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append("\t");
        }
        return sb.toString();
    }
}
