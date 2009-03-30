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
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;
import org.antlr.runtime.TokenSource;
import org.antlr.runtime.TokenStream;
import org.antlr.runtime.debug.DebugEventListener;
import org.antlr.runtime.debug.DebugParser;

public class DebugParserBase extends DebugParser {

  private final LazyTokenStream lazyInput;
  private ANTLRStringStream source;
  private ArrayList<TokenSource> lexerStack = new ArrayList<TokenSource>();

  public DebugParserBase(TokenStream input) {
    this(input, new RecognizerSharedState());
  }

  public DebugParserBase(TokenStream input, RecognizerSharedState state) {
    super(input, state);
    lazyInput = (LazyTokenStream) input;
  }

  public DebugParserBase(TokenStream input, DebugEventListener dbg) {
    super(input, dbg);
    lazyInput = (LazyTokenStream) input;
  }

  public void setCharSource(ANTLRStringStream source) {
    this.source = source;
  }

  @Override
  public void reportError(RecognitionException e) {
    throw new RuntimeException(getErrorHeader(e) + " " + getErrorMessage(e, getTokenNames()), e);
  }

  private static final boolean debug = false;

  public void pushXMLLexer() {
    // if (lazyInput.getSource() instanceof XMLLexer) return;
    if (debug) System.out.println("XML Lexer");
    lexerStack.add(lazyInput.getSource());
    XMLLexer xmlLexer = new XMLLexer(source);
    lazyInput.setSource(xmlLexer);
  }

  public void pushXQueryLexer() {
    // if (lazyInput.getSource() instanceof XQueryLexer) return;
    if (debug) System.out.println("XQuery Lexer");
    lexerStack.add(lazyInput.getSource());
    XQueryLexer xqueryLexer = new XQueryLexer(source);
    lazyInput.setSource(xqueryLexer);
  }

  public void popXMLLexer() {
    // if (lazyInput.getSource() instanceof XQueryLexer) return;
    if (debug) System.out.println("Pop XML Lexer");
    TokenSource tokenSource = lexerStack.remove(lexerStack.size() - 1);
    if (tokenSource instanceof XMLLexer) {
      ((XMLLexer) tokenSource).inElem = false;
    }
    lazyInput.setSource(tokenSource);
  }

  public void popXQueryLexer() {
    // if (lazyInput.getSource() instanceof XMLLexer) return;
    if (debug) System.out.println("Pop XQuery Lexer");
    TokenSource tokenSource = lexerStack.remove(lexerStack.size() - 1);
    lazyInput.setSource(tokenSource);
  }

}
