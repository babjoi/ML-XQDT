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

import java.util.Arrays;
import java.util.Comparator;

import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.PatternRule;

public class SemanticMultiEndPatternRule extends PatternRule implements ISemanticRule {

    private static class DecreasingCharArrayLengthComparator implements Comparator<char[]> {

        public int compare(char[] o1, char[] o2) {
            return o2.length - o1.length;
        }
    }

    private Comparator<char[]> fLineDelimiterComparator = new DecreasingCharArrayLengthComparator();
    private char[][] fLineDelimiters;
    private char[][] fSortedLineDelimiters;

    protected String[] fEndSequences;
    protected boolean isMultiEnd = false;
    protected boolean fIsExclusive = false;

    public SemanticMultiEndPatternRule(String startSequence, String endSequence, IToken token, char escapeCharacter,
            boolean breaksOnEOL) {
        this(startSequence, endSequence, token, escapeCharacter, breaksOnEOL, false);
    }

    public SemanticMultiEndPatternRule(String startSequence, String endSequence, IToken token, char escapeCharacter,
            boolean breaksOnEOL, boolean breaksOnEOF) {
        this(startSequence, endSequence, token, escapeCharacter, breaksOnEOL, breaksOnEOF, false);
    }

    public SemanticMultiEndPatternRule(String startSequence, String endSequence, IToken token, char escapeCharacter,
            boolean breaksOnEOL, boolean breaksOnEOF, boolean escapeContinuesLine) {
        super(startSequence, endSequence, token, escapeCharacter, breaksOnEOL, breaksOnEOF, escapeContinuesLine);
    }

    public SemanticMultiEndPatternRule(boolean isExclusive, String startSequence, String endSequence, IToken token,
            char escapeCharacter, boolean breaksOnEOL) {
        super(startSequence, endSequence, token, escapeCharacter, breaksOnEOL);
        this.fIsExclusive = isExclusive;
    }

    public SemanticMultiEndPatternRule(boolean isExclusive, String startSequence, String endSequence, IToken token,
            char escapeCharacter, boolean breaksOnEOL, boolean breaksOnEOF) {
        this(isExclusive, startSequence, endSequence, token, escapeCharacter, breaksOnEOL, breaksOnEOF, false);
    }

    public SemanticMultiEndPatternRule(boolean isExclusive, String startSequence, String endSequence, IToken token,
            char escapeCharacter, boolean breaksOnEOL, boolean breaksOnEOF, boolean escapeContinuesLine) {
        super(startSequence, endSequence, token, escapeCharacter, breaksOnEOL, breaksOnEOF, escapeContinuesLine);
        this.fIsExclusive = isExclusive;
    }

    public SemanticMultiEndPatternRule(String startSequence, String[] endSequences, IToken token, char escapeCharacter,
            boolean breaksOnEOL) {
        this(startSequence, "", token, escapeCharacter, breaksOnEOL, false);
    }

    public SemanticMultiEndPatternRule(String startSequence, String[] endSequences, IToken token, char escapeCharacter,
            boolean breaksOnEOL, boolean breaksOnEOF) {
        this(startSequence, "", token, escapeCharacter, breaksOnEOL, breaksOnEOF, false);
    }

    public SemanticMultiEndPatternRule(String startSequence, String[] endSequences, IToken token, char escapeCharacter,
            boolean breaksOnEOL, boolean breaksOnEOF, boolean escapeContinuesLine) {
        super(startSequence, "", token, escapeCharacter, breaksOnEOL, breaksOnEOF, escapeContinuesLine);
        fEndSequences = endSequences;
        isMultiEnd = true;
    }

    public SemanticMultiEndPatternRule(boolean isExclusive, String startSequence, String[] endSequences, IToken token,
            char escapeCharacter, boolean breaksOnEOL) {
        this(isExclusive, startSequence, endSequences, token, escapeCharacter, breaksOnEOL, false);
    }

    public SemanticMultiEndPatternRule(boolean isExclusive, String startSequence, String[] endSequences, IToken token,
            char escapeCharacter, boolean breaksOnEOL, boolean breaksOnEOF) {
        this(isExclusive, startSequence, endSequences, token, escapeCharacter, breaksOnEOL, breaksOnEOF, false);
    }

    public SemanticMultiEndPatternRule(boolean isExclusive, String startSequence, String[] endSequences, IToken token,
            char escapeCharacter, boolean breaksOnEOL, boolean breaksOnEOF, boolean escapeContinuesLine) {
        super(startSequence, "", token, escapeCharacter, breaksOnEOL, breaksOnEOF, escapeContinuesLine);
        fEndSequences = endSequences;
        isMultiEnd = true;
        fIsExclusive = isExclusive;
    }

    @Override
    protected boolean endSequenceDetected(ICharacterScanner scanner) {
        if (!fIsExclusive && !isMultiEnd)
            return super.endSequenceDetected(scanner);

        char[][] originalDelimiters = scanner.getLegalLineDelimiters();
        int count = originalDelimiters.length;
        if (fLineDelimiters == null || originalDelimiters.length != count) {
            fSortedLineDelimiters = new char[count][];
        } else {
            while (count > 0 && fLineDelimiters[count - 1] == originalDelimiters[count - 1])
                count--;
        }
        if (count != 0) {
            fLineDelimiters = originalDelimiters;
            System.arraycopy(fLineDelimiters, 0, fSortedLineDelimiters, 0, fLineDelimiters.length);
            Arrays.sort(fSortedLineDelimiters, fLineDelimiterComparator);
        }

        int c;
        while ((c = scanner.read()) != ICharacterScanner.EOF) {
            if (c == fEscapeCharacter) {
                if (fEndSequence.length == 1 && fEndSequence[0] == c) {
                    if (scanner.read() == c)
                        continue;
                    else {
                        scanner.unread();
                        return true;
                    }
                }

                // Skip escaped character(s)
                if (fEscapeContinuesLine) {
                    c = scanner.read();
                    for (int i = 0; i < fSortedLineDelimiters.length; i++) {
                        if (c == fSortedLineDelimiters[i][0]
                                && sequenceDetected(scanner, fSortedLineDelimiters[i], true))
                            break;
                    }
                } else
                    c = scanner.read();

            } else if (firstCharacterMatch(c)) {
                // Check if the specified end sequence has been found.
                if (oneEndSequenceDetected(scanner, c))
                    return true;
            } else if (fBreaksOnEOL) {
                // Check for end of line since it can be used to terminate the pattern.
                for (int i = 0; i < fSortedLineDelimiters.length; i++) {
                    if (c == fSortedLineDelimiters[i][0] && sequenceDetected(scanner, fSortedLineDelimiters[i], true))
                        return true;
                }
            }
        }

        if (fBreaksOnEOF)
            return true;
        return false;
    }

    protected boolean oneEndSequenceDetected(ICharacterScanner scanner, int startChar) {
        for (int i = 0; i < fEndSequences.length; i++)
            if ((fEndSequences[i].length() > 0 && fEndSequences[i].charAt(0) == (char)startChar))
                if (sequenceDetected(scanner, fEndSequences[i].toCharArray(), true)) {
                    if (fIsExclusive)
                        for (int j = 0; j < fEndSequences[i].length(); j++) {
                            scanner.unread();
                        }
                    return true;
                }
        return false;
    }

    protected boolean directEndSequenceDetected(ICharacterScanner scanner) {
        if (fIsExclusive) {
            int c = scanner.read();
            for (int i = 0; i < fEndSequences.length; i++) {
                if (fEndSequences[i].length() > 0 && fEndSequences[i].charAt(0) == (char)c)
                    if (sequenceDetected(scanner, fEndSequences[i].toCharArray(), true)) {
                        for (int j = 0; j < fEndSequences[i].length(); j++) {
                            scanner.unread();
                        }
                        return true;
                    }
            }
            scanner.unread();
        }
        return false;
    }

    private boolean firstCharacterMatch(int c) {
        for (String end : fEndSequences) {
            if (end.length() > 0 && end.charAt(0) == c)
                return true;
        }

        return false;
    }

    public String getContentType() {
        return (String)fToken.getData();
    }
}
