/*
 * Copyright 2008 Martin Probst
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.martinprobst.xqpretty;

import java.util.ArrayList;

import org.antlr.runtime.CharStream;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenSource;
import org.antlr.runtime.TokenStream;

public class LazyTokenStream implements TokenStream {

  private ArrayList<XQToken> tokens = new ArrayList<XQToken>();
  private int offset;
  private int lastMarker;
  private TokenSource source;

  public LazyTokenStream(TokenSource source) {
    this.source = source;
  }

  private boolean assertSize(int size) {
    if (size < tokens.size()) return true;

    int current = tokens.size();
    XQToken hiddenToken = null;
    while (current < size) {
      Token t = source.nextToken();
      if (t == Token.EOF_TOKEN) {
        return false;
      }
      XQToken xqt = (XQToken) t;
      xqt.setHiddenPredecessor(hiddenToken);
      if (xqt.getType() == CharStream.EOF) {
        return false;
      }
      if (xqt.getChannel() == Token.HIDDEN_CHANNEL) {
        // move offset over hidden tokens
        hiddenToken = xqt;
        continue;
      } else {
        xqt.setTokenIndex(current);
      }
      current++;
      tokens.add(xqt);
    }
    return true;
  }

  public void consume() {
    offset++;
  }

  public Token LT(int k) {
    if (k == 0) {
      return null;
    } else if (k < 0) {
      if (offset + k < 0) {
        return null;
      }
      if (!assertSize(offset + k + 1)) return Token.EOF_TOKEN;
      return tokens.get(offset + k);
    }

    if (!assertSize(offset + k)) return Token.EOF_TOKEN;
    return tokens.get(offset + k - 1);
  }

  public Token get(int i) {
    if (!assertSize(i + 1)) return Token.EOF_TOKEN;
    return tokens.get(i);
  }

  public TokenSource getTokenSource() {
    return source;
  }

  public String toString(int start, int stop) {
    assertSize(stop);
    StringBuilder sb = new StringBuilder();
    for (int i = start; i < stop && i < tokens.size(); i++) {
      XQToken token = tokens.get(i);
      appendWithPredecessors(sb, token);
    }
    return sb.toString();
  }

  public void appendWithPredecessors(StringBuilder target, XQToken t) {
    if (t.getHiddenPredecessor() != null) {
      appendWithPredecessors(target, t.getHiddenPredecessor());
    }
    target.append(t.getText());
  }

  public String toString(Token start, Token stop) {
    return toString(start.getTokenIndex(), stop.getTokenIndex());
  }

  public int LA(int i) {
    return LT(i).getType();
  }

  public String getSourceName() {
    return source.getSourceName();
  }

  public int index() {
    return offset;
  }

  public int mark() {
    lastMarker = offset;
    return lastMarker;
  }

  public void release(int marker) {
    // no-op
  }

  public void rewind() {
    seek(lastMarker);
  }

  public void rewind(int marker) {
    seek(marker);
  }

  public void seek(int index) {
    offset = index;
  }

  public int size() {
    return tokens.size();
  }

  public void setSource(TokenSource source) {
    this.source = source;
  }

  public TokenSource getSource() {
    return source;
  }
}
