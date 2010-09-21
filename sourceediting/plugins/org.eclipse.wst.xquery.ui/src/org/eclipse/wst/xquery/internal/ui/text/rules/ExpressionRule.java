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
package org.eclipse.wst.xquery.internal.ui.text.rules;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.dltk.ui.text.rules.CombinedWordRule.CharacterBuffer;
import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.wst.xquery.core.codeassist.IXQDTKeywords;
import org.eclipse.wst.xquery.core.text.XQDTWhitespaceDetector;
import org.eclipse.wst.xquery.core.text.XQDTWordDetector;

public class ExpressionRule implements IRule {

    public static final String WORD_ANY_QNAME = "${qn}";
    public static final String WORD_FUNCTION_QNAME = "${fqn}";
    public static final String WORD_ITEM_TYPE = "${it}";
    public static final String WORD_WHITESPACE = "${ws}";

    private List<ExpressionSegment> fSegments = new ArrayList<ExpressionSegment>();
    private CharacterBuffer fBuffer = new CharacterBuffer(30);
    private XQDTWhitespaceDetector fWhitespaceDetector = new XQDTWhitespaceDetector();
    private XQDTWordDetector fWordDetector = new XQDTWordDetector();
    // private int fSegmentNumber = 0;
    // private boolean fSegmentBreak = false;

    private boolean fTolleratesWhitespaces = true;

    public class ExpressionSegment extends CharacterBuffer {

        private int fCardinality;
        private IToken fToken;
        private int fLength = 0;
        private boolean fEndsRule = false;

        public ExpressionSegment(String word, int cardinality, IToken token) {
            this(word, cardinality, token, false);
        }

        public ExpressionSegment(String word, int cardinality, IToken token, boolean endsRule) {
            super(word);
            fCardinality = cardinality;
            fToken = token;
            fEndsRule = endsRule;
        }

        public ExpressionSegment(String word, IToken token) {
            this(word, 1, token, false);
        }

        public ExpressionSegment(String word, IToken token, boolean endsRule) {
            this(word, 1, token, endsRule);
        }

        public ExpressionSegment(String word, int cardinality) {
            this(word, cardinality, Token.UNDEFINED);
        }

        public ExpressionSegment(String word) {
            this(word, 1, Token.UNDEFINED, false);
        }

        public ExpressionSegment(String word, boolean endRule) {
            this(word, 1, Token.UNDEFINED, endRule);
        }

        public int getCardinality() {
            return fCardinality;
        }

        public IToken getToken() {
            return fToken;
        }

        public int length() {
            if (toString().matches("\\$\\{\\w+\\}")) {
                return fLength;
            } else {
                return super.length();
            }
        }

        public void setLength(int length) {
            fLength = length;
        }

        public boolean endsRule() {
            return fEndsRule;
        }
    }

    public ExpressionRule() {
    }

    public void addRuleSegment(String word, IToken token) {
        fSegments.add(new ExpressionSegment(word, token));
    }

    public void addRuleSegment(String word, IToken token, boolean endRule) {
        fSegments.add(new ExpressionSegment(word, token, endRule));
    }

    public void addRuleSegment(String word, int cardinality, IToken token) {
        Assert.isLegal(cardinality > 0);
        fSegments.add(new ExpressionSegment(word, cardinality, token));
    }

    public void addRuleSegment(String word, int cardinality, IToken token, boolean endsRule) {
        Assert.isLegal(cardinality > 0);
        fSegments.add(new ExpressionSegment(word, cardinality, token, endsRule));
    }

    public boolean getTolleratesWhitespaces() {
        return fTolleratesWhitespaces;
    }

    public void setTolleratesWhitespaces(boolean tolleratesWhitespaces) {
        fTolleratesWhitespaces = tolleratesWhitespaces;
    }

    public IToken evaluate(ICharacterScanner scanner) {

        if (!fExpressionChecked) {
            fExpressionChecked = checkCompleteExpression(scanner);
            rewindScanner(scanner);
            if (fExpressionChecked) {
                return fSegments.get(0).getToken();
            }
            return Token.UNDEFINED;
        }

        if (fTolleratesWhitespaces && fCurrentSegment < fSegments.size() - 1) {
            IToken token = consumeWhitespaces(scanner);
            if (token != null) {
                return token;
            }
        }

        int segment = ++fCurrentSegment;
        if (segment == fSegments.size() - 1 || fSegments.get(segment).endsRule()) {
            fCurrentSegment = 0;
            fExpressionChecked = false;
        }

        consumeSegment(scanner, segment);
        return fSegments.get(segment).getToken();
    }

    private boolean fExpressionChecked = false;
    private int fCurrentSegment = 0;
    private int fTotalRewindLength = 0;
    private int fSecondSegmentStart = 0;

    public boolean mustCompleteExpression() {
        return fExpressionChecked;
    }

    private boolean checkCompleteExpression(ICharacterScanner scanner) {

        for (int i = 0; i < fSegments.size(); i++) {
            ExpressionSegment segment = fSegments.get(i);
            if (segment.equals(WORD_WHITESPACE)) {
                int c = scanner.read();
                if (fWhitespaceDetector.isWhitespace((char)c)) {
                    while (fWhitespaceDetector.isWhitespace((char)consume(scanner))) {
                        ;
                    }
                }
                scanner.unread();
            } else if (segment.equals(WORD_ANY_QNAME) || segment.equals(WORD_FUNCTION_QNAME)
                    || segment.equals(WORD_ITEM_TYPE)) {
                String name = scanName(scanner);
                int segmentLength = name.length();
                if (segmentLength == 0) {
                    return false;
                }

                int c;

                // if (!segment.equals(WORD_ITEM_TYPE)) {
                c = consume(scanner);
                if ((char)c == ':') {
                    segmentLength++;
                    int localNameLength = scanName(scanner).length();
                    // the following lines were causing a bug that was blocking the
                    // editing if the localname of a QName was an empty string
                    // if (localNameLength == 0) {
                    // unconsume(scanner);
                    // return true;
                    // }
                    segmentLength += localNameLength;
                } else {
                    unconsume(scanner);
                    if ((segment.equals(WORD_FUNCTION_QNAME) && Arrays.binarySearch(
                            IXQDTKeywords.XQUERY_KEYWORDS_RESERVED_FN_NAMES, name) >= 0)
                            || (segment.equals(WORD_ITEM_TYPE) && Arrays.binarySearch(
                                    IXQDTKeywords.XQUERY_KEYWORDS_ITEM_TYPES, name) < 0)) {
                        return false;
                    }
                }
                // }
                segment.setLength(segmentLength);

                if (i == 0) {
                    fSecondSegmentStart = segmentLength;
                }

                if (fTolleratesWhitespaces && i < fSegments.size() - 1) {
                    c = scanner.read();
                    if (fWhitespaceDetector.isWhitespace((char)c)) {
                        while (fWhitespaceDetector.isWhitespace((char)consume(scanner))) {
                            ;
                        }
                    }
                    scanner.unread();
                }
            } else {
                fBuffer.clear();

                int c = scanner.read();
                while (c != ICharacterScanner.EOF && fBuffer.length() < segment.length()) {
                    fBuffer.append((char)c);
                    c = consume(scanner);
                    if (i == 0) {
                        fSecondSegmentStart++;
                    }
                }

                if (fTolleratesWhitespaces && fWhitespaceDetector.isWhitespace((char)c)) {
                    while (fWhitespaceDetector.isWhitespace((char)consume(scanner))) {
                        ;
                    }
                }

                scanner.unread();

                if (segment.length() != fBuffer.length() || !segment.equals(fBuffer)) {
                    return false;
                }
            }
        }

        return true;
    }

    private String scanName(ICharacterScanner scanner) {
        fBuffer.clear();
        int c = scanner.read();
        if (fWordDetector.isWordStart((char)c)) {
            while (fWordDetector.isWordPart((char)c)) {
                fBuffer.append((char)c);
                c = consume(scanner);
            }
        }
        scanner.unread();
        return fBuffer.toString();
    }

    private void rewindScanner(ICharacterScanner scanner) {
        int length = fExpressionChecked ? fTotalRewindLength - fSecondSegmentStart : fTotalRewindLength;
        for (int i = 0; i < length; i++) {
            scanner.unread();
        }
        fTotalRewindLength = 0;
        fSecondSegmentStart = 0;
        // fExpressionChecked = fExpressionChecked;
    }

    private int consume(ICharacterScanner scanner) {
        fTotalRewindLength++;
        return scanner.read();
    }

    private void unconsume(ICharacterScanner scanner) {
        fTotalRewindLength--;
        scanner.unread();
    }

    private void consumeSegment(ICharacterScanner scanner, int segment) {
        for (int i = 0; i < fSegments.get(segment).length(); i++) {
            scanner.read();
        }
    }

    private IToken consumeWhitespaces(ICharacterScanner scanner) {
        int c = scanner.read();
        if (!fWhitespaceDetector.isWhitespace((char)c)) {
            scanner.unread();
            return null;
        }

        while (fWhitespaceDetector.isWhitespace((char)c)) {
            c = scanner.read();
        }

        scanner.unread();
        return Token.WHITESPACE;
    }

    // private void unreadBuffer(ICharacterScanner scanner) {
    // for (int i= fBuffer.length() - 1; i >= 0; i--)
    // scanner.unread();
    // }

}
