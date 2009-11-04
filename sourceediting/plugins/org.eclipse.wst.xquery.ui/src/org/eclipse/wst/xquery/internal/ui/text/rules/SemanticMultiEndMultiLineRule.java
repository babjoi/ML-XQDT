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

import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.wst.xquery.internal.ui.text.XQDTPartitionScanner;

public class SemanticMultiEndMultiLineRule extends SemanticMultiEndPatternRule {

    public SemanticMultiEndMultiLineRule(String startSequence, String endSequence, IToken token) {
        this(startSequence, endSequence, token, (char)0);
    }

    public SemanticMultiEndMultiLineRule(String startSequence, String endSequence, IToken token, char escapeCharacter) {
        this(startSequence, endSequence, token, escapeCharacter, false);
    }

    public SemanticMultiEndMultiLineRule(String startSequence, String endSequence, IToken token, char escapeCharacter,
            boolean breaksOnEOF) {
        super(false, startSequence, endSequence, token, escapeCharacter, false, breaksOnEOF);
        fEndSequences = new String[1];
        fEndSequences[0] = endSequence;
    }

    public SemanticMultiEndMultiLineRule(boolean isExclusive, String startSequence, String endSequence, IToken token) {
        this(isExclusive, startSequence, endSequence, token, (char)0);
    }

    public SemanticMultiEndMultiLineRule(boolean isExclusive, String startSequence, String endSequence, IToken token,
            char escapeCharacter) {
        this(isExclusive, startSequence, endSequence, token, escapeCharacter, false);
    }

    public SemanticMultiEndMultiLineRule(boolean isExclusive, String startSequence, String endSequence, IToken token,
            char escapeCharacter, boolean breaksOnEOF) {
        super(isExclusive, startSequence, endSequence, token, escapeCharacter, false, breaksOnEOF);
        fEndSequences = new String[1];
        fEndSequences[0] = endSequence;
    }

    public SemanticMultiEndMultiLineRule(String startSequence, String[] endSequences, IToken token) {
        this(startSequence, endSequences, token, (char)0);
    }

    public SemanticMultiEndMultiLineRule(String startSequence, String[] endSequences, IToken token, char escapeCharacter) {
        this(startSequence, endSequences, token, escapeCharacter, false);
    }

    public SemanticMultiEndMultiLineRule(String startSequence, String[] endSequences, IToken token,
            char escapeCharacter, boolean breaksOnEOF) {
        super(false, startSequence, endSequences, token, escapeCharacter, false, breaksOnEOF);
    }

    public SemanticMultiEndMultiLineRule(boolean isExclusive, String startSequence, String[] endSequences, IToken token) {
        this(isExclusive, startSequence, endSequences, token, (char)0);
    }

    public SemanticMultiEndMultiLineRule(boolean isExclusive, String startSequence, String[] endSequences,
            IToken token, char escapeCharacter) {
        this(isExclusive, startSequence, endSequences, token, escapeCharacter, false);
    }

    public SemanticMultiEndMultiLineRule(boolean isExclusive, String startSequence, String[] endSequences,
            IToken token, char escapeCharacter, boolean breaksOnEOF) {
        super(isExclusive, startSequence, endSequences, token, escapeCharacter, false, breaksOnEOF);
    }

    @Override
    protected IToken doEvaluate(ICharacterScanner scanner, boolean resume) {
        if (!fIsExclusive)
            return super.doEvaluate(scanner, resume);

        if (resume) {
            if (endSequenceDetected(scanner))
                return fToken;
        } else {
            if (((XQDTPartitionScanner)scanner).acceptsStartSequence(fStartSequence)) {
                // int c = scanner.read();
                // scanner.unread();
                if (startSequenceDetected(scanner, fStartSequence, false) && !(directEndSequenceDetected(scanner))) {
                    // System.out.println("Detected start: " + (char)c);
                    if (endSequenceDetected(scanner)) {
                        return fToken;
                    }
                }

                for (int i = 1; i < fStartSequence.length; i++) {
                    scanner.read();
                }
            }
        }

        return Token.UNDEFINED;
    }

    protected boolean startSequenceDetected(ICharacterScanner scanner, char[] sequence, boolean eofAllowed) {
        for (int i = 0; i < sequence.length; i++) {
            scanner.unread();
        }
        for (int i = 0; i < sequence.length; i++) {
            int c = scanner.read();
            if (c == ICharacterScanner.EOF && eofAllowed) {
                return true;
            } else if (c != sequence[i]) {
                // Non-matching character detected, WIND the scanner back to the start.
                for (int j = sequence.length - i - 1; j > 0; j--)
                    scanner.read();
                return false;
            }
        }

        return true;
    }
}
