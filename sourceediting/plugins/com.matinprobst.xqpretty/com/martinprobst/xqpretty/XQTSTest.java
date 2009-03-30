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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.debug.ParseTreeBuilder;

public class XQTSTest {

  static void parse(String filename) throws IOException, RecognitionException {
    ANTLRStringStream source = new ANTLRFileStream(filename);
    XQueryLexer lexer = new XQueryLexer(source);
    LazyTokenStream tokenStream = new LazyTokenStream(lexer);
    ParseTreeBuilder builder = new DecisionAwareParseTreeBuilder("XQuery");
    XQueryParser parser = new XQueryParser(tokenStream, builder);
    parser.setCharSource(source);
    parser.module();
  }

  public static void main(String[] args) {
    File basepath = new File("/Users/martin/tmp/xqts/2006/xquery-test-suite/TestSuiteStagingArea");
    File catalog = new File(basepath, "noerror.txt");
    try {
      BufferedReader catalogReader = new BufferedReader(new FileReader(catalog));
      String testFile = catalogReader.readLine();
      boolean expectParseError = false;
      if (testFile.startsWith("!")) {
        expectParseError = true;
        testFile = testFile.substring(1);
      }
      int errors = 0;
      int count = 0;
      long time = System.currentTimeMillis();
      while (testFile != null) {
        String path = new File(basepath, testFile).getAbsolutePath();
        try {
          parse(path);
          if (expectParseError) {
//            System.err.println();
            System.err.println(path + ": expected parse error");
            errors++;
          } else {
            count++;
          }
//          System.out.print(".");
//          if (count % 50 == 0) System.out.println();
        } catch (RuntimeException e) {
          if (!expectParseError) {
            errors++;
            System.err.println();
            System.err.println("*** Could not parse " + path);
            e.printStackTrace();
            System.exit(1);
          } else {
            count++;
          }
        } catch (RecognitionException e) {
          if (!expectParseError) {
            errors++;
            System.err.println();
            System.err.println("*** Could not parse " + path);
            e.printStackTrace();
            System.exit(1);
          } else {
            count++;
          }
        }
        testFile = catalogReader.readLine();
        if (testFile != null && testFile.startsWith("!")) {
          expectParseError = true;
          testFile = testFile.substring(1);
        } else {
          expectParseError = false;
        }
      }
      System.out.println("Parsed: " + count + " in " + (System.currentTimeMillis() - time));
      System.out.println("Errors: " + errors);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
