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

import java.io.IOException;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.Token;
import org.antlr.runtime.debug.ParseTreeBuilder;
import org.junit.Test;

public class ParseTest {
  
  @Test
  public void testParseXMLContent() throws IOException, RecognitionException {
    parse("<x>{ 1 }</x>");
  }
  
  static void parse(String query) throws IOException, RecognitionException {
    ANTLRStringStream source = new ANTLRStringStream(query);
    XQueryLexer lexer = new XQueryLexer(source);
    LazyTokenStream tokenStream = new LazyTokenStream(lexer);
    ParseTreeBuilder builder = new DecisionAwareParseTreeBuilder("XQuery");
    XQueryParser parser = new XQueryParser(tokenStream, builder);
    parser.setCharSource(source);
    parser.module();
    System.out.println(builder.getTree().toStringTree());
  }

  public static void main(String[] args) {
    String test = "<x>{ 1 }</x>";
    try {
      if (true) lexXQ(test);
      if (true) lexXML(test);
      if (true) parse(test);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (RecognitionException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  private static void lexXQ(String test) {
    ANTLRStringStream source = new ANTLRStringStream(test);
    XQueryLexer lexer = new XQueryLexer(source);
    Token t = lexer.nextToken();
    while (t.getType() != Token.EOF) {
      System.out.println(t);
      t = lexer.nextToken();
    }
  }

  private static void lexXML(String test) {
    ANTLRStringStream source = new ANTLRStringStream(test);
    XMLLexer lexer = new XMLLexer(source);
    lexer.inElem = false;
    Token t = lexer.nextToken();
    while (t.getType() != Token.EOF) {
      System.out.println(t);
      t = lexer.nextToken();
    }
  }
}
