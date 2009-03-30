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

import org.antlr.runtime.CharStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;
import org.antlr.runtime.Token;

public abstract class Lexer extends org.antlr.runtime.Lexer {
  public Lexer() {
    super();
  }

  public Lexer(CharStream input) {
    this(input, new RecognizerSharedState());
  }

  public Lexer(CharStream input, RecognizerSharedState state) {
    super(input, state);
  }

  @Override
  public void reportError(RecognitionException e) {
    throw new RuntimeException(getErrorHeader(e) + " " + getErrorMessage(e, getTokenNames()), e);
  }

  @Override
  public Token emit() {
    Token t = new XQToken(input, state.type, state.channel, state.tokenStartCharIndex,
        getCharIndex() - 1);
    t.setLine(state.tokenStartLine);
    t.setText(state.text);
    t.setCharPositionInLine(state.tokenStartCharPositionInLine);
    emit(t);
    return t;
  }
}
