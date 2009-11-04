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

import java.util.ArrayList;
import java.util.List;

import org.antlr.runtime.CommonToken;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenSource;
import org.antlr.runtime.TokenStream;

public class NewLazyTokenStream implements TokenStream {

    private XQDTLexer tokenSource;
    private List<Token> tokens;
    private boolean isWsExplicit = false;
    private int p = 0;
    protected int channel = Token.DEFAULT_CHANNEL;
    protected int lastMarker;

    public NewLazyTokenStream() {
        tokens = new ArrayList<Token>(500);
    }

    public NewLazyTokenStream(XQDTLexer tokenSource) {
        this();
        this.tokenSource = tokenSource;
    }

    /**
     * Get Token at current input pointer + i ahead where i=1 is next Token. i<0 indicates tokens in
     * the past. So -1 is previous token and -2 is two tokens ago. LT(0) is undefined. For i>=n,
     * return Token.EOFToken. Return null for LT(0) and any index that results in an absolute
     * address that is negative.
     */
    public Token LT(int k) {
        if (k == 0)
            return null;
        if (k < 0)
            return readReverseNthGoodToken(-k);

        return readNthGoodToken(k);
    }

    public Token get(int i) {
        if (i >= tokens.size())
            return Token.EOF_TOKEN;
        return tokens.get(i);
    }

    public TokenSource getTokenSource() {
        return tokenSource;
    }

    public String toString(int start, int stop) {
        if (start < 0)
            start = 0;
        if (p <= stop) {
            readNTokens(stop - p + 1);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = start; i <= stop && i < tokens.size(); i++) {
            sb.append(((Token)tokens.get(i)).getText());
        }
        return sb.toString();

    }

    public String toString(Token start, Token stop) {
        return toString(start.getTokenIndex(), stop.getTokenIndex());
    }

    public int LA(int i) {
        return LT(i).getType();
    }

    boolean done = false;

    // public void consume() {
    // if (done && p >= tokens.size())
    // return;
    //
    // Token t = null;
    // do {
    // p++;
    // t = LT(1);
    // if (t == Token.EOF_TOKEN) {
    // done = true;
    // return;
    // }
    // p = t.getTokenIndex();
    //
    // } while (!isWsExplicit && t.getChannel() != channel);
    //		
    // if (LT(1) == Token.EOF_TOKEN) {
    // done = true;
    // }
    // }

    public void consume() {
        if (done)
            return;
        p++;
        if (!isWsExplicit) {
            jumpToFirstValidToken();
        }
    }

    public String getSourceName() {
        return getTokenSource().getSourceName();
    }

    public int index() {
        return p;
    }

    public int mark() {
        lastMarker = index();
        return lastMarker;
    }

    public void release(int marker) {
    }

    public void rewind() {
        seek(lastMarker);
    }

    public void rewind(int marker) {
        seek(marker);
    }

    public void seek(int index) {
        p = index;
        done = false;
    }

    public int size() {
        return tokens.size();
    }

    @Override
    public String toString() {
        return toString(0, tokens.size() - 1);
    }

    public void setTokenSource(XQDTLexer source) {
        tokenSource = source;
        setWsExplicit(source.isWsExplicit());

        // un-read the unused tokens
        // they are different for the new source
        if (p < tokens.size()) {
            int rIndex = p > 0 ? ((CommonToken)tokens.get(p - 1)).getStopIndex() : 0;
            tokenSource.rewindToIndex(rIndex + 1);
            for (int i = tokens.size() - 1; i >= p; i--) {
                tokens.remove(i);
            }
        }

        // if we ignore WS, jump to next token
        if (!isWsExplicit) {
            jumpToFirstValidToken();
        }
    }

    public void setWsExplicit(boolean explicit) {
        isWsExplicit = explicit;
        if (!explicit) {
            jumpToFirstValidToken();
        }
    }

    private Token readNthGoodToken(int n) {
        int count = tokens.size();
        // number of buffered tokens available
        int avt = count - p;
        // i counts good tokens, j counts all tokens
        int i = 1, j = 0;
        Token t = null;
        while (i <= n) {
            if (j < avt) // read from buffer
                t = (Token)tokens.get(p + j);
            else { // read from source
                t = tokenSource.nextToken();
                if (t == Token.EOF_TOKEN) {
                    return t;
                }
                t.setTokenIndex(count++);
                tokens.add(t);
            }

            if (isWsExplicit || t.getChannel() == channel) {
                i++;
            }
            j++;
        }
        return t;
    }

    private Token readReverseNthGoodToken(int n) {
        if (n == 0 || (p - n) < 0)
            return null;

        // i counts good tokens, j counts all tokens
        int i = 1, j = 0;
        Token t = null;
        while (p - 1 - j >= 0) {
            t = get(p - 1 - j);

            if (isWsExplicit || t.getChannel() == channel) {
                if (i++ == n)
                    return t;
            }
            j++;
        }
        return null;
    }

    private void readNTokens(int n) {
        Token t = null;
        for (int i = 0; i < n; i++) {
            if (tokens.size() > p + i)
                continue;

            t = tokenSource.nextToken();
            if (t == Token.EOF_TOKEN)
                return;

            t.setTokenIndex(p + i);
            tokens.add(t);
        }
    }

    public void jumpToFirstValidToken() {
        Token t = LT(1);
        if (t != Token.EOF_TOKEN) {
            done = false;
            p = t.getTokenIndex();
        }
    }
}
