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

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.Parser;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;
import org.antlr.runtime.TokenSource;
import org.antlr.runtime.TokenStream;

public class ParserBase extends Parser {

  private final LazyTokenStream lazyInput;
  private ANTLRStringStream source;
  private ArrayList<TokenSource> lexerStack = new ArrayList<TokenSource>();

  public ParserBase(TokenStream input) {
    this(input, new RecognizerSharedState());
  }

  public ParserBase(TokenStream input, RecognizerSharedState state) {
    super(input, state);
    lazyInput = (LazyTokenStream) input;
  }

  public void setCharSource(ANTLRStringStream source) {
    this.source = source;
  }
  
  @Override
  public void reportError(RecognitionException e) {
    throw new RuntimeException(getErrorHeader(e) + " " + getErrorMessage(e, getTokenNames()), e);
  }

  public void pushXMLLexer() {
    lexerStack.add(lazyInput.getSource());
    XMLLexer elementLexer = new XMLLexer(source);
    lazyInput.setSource(elementLexer);
  }

  public void pushXQueryLexer() {
    lexerStack.add(lazyInput.getSource());
    XQueryLexer xqueryLexer = new XQueryLexer(source);
    lazyInput.setSource(xqueryLexer);
  }

  public void popLexer() {
    TokenSource tokenSource = lexerStack.remove(lexerStack.size() - 1);
    lazyInput.setSource(tokenSource);
  }

}
