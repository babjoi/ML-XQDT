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

import org.junit.Test;

public class OperatorWhitespaceTest extends FormatterTestBase {
  @Test
  public void testAndWS() {
    assertFmt("1 and 2\n", "1    and 2");

    assertFmt("foo(1) and bar(2)\n", "foo(1)and bar(2)");
  }

  @Test
  public void testCommaWSPreserve() {
    assertFmt("foo(1, 2)\n", "foo(1, 2)");
  }

  @Test
  public void testCommaWSPreserveLiteral() {
    assertFmt("foo(1, '2')\n", "foo(1, '2')");
  }

  @Test
  public void testCommaWS() {
    assertFmt("foo(1, 2)\n", "foo(1,2)");
  }

  @Test
  public void testCommaWSPure() {
    assertFmt("1, 2\n", "1,2");
  }

  @Test
  public void testNoWSBeforeParen() {
    assertFmt("foo(bar(1))\n", "foo(bar(1) )");

    assertFmt("foo(bar(1) or bam)\n", "foo(bar(1)   or bam)");
  }

  @Test
  public void testPreserveIfWS() {
    assertFmt("if (x) \nthen 1\nelse 2\n", "if(x)then 1 else 2");
  }

  @Test
  public void testWSBracket() {
    assertFmt("test[x]\n", "test    [   x ]");
  }

  @Test
  public void testDoubleBracket() {
    assertFmt("test[.[x]]\n", "test    [ .  [  x ] ]");
  }

  @Test
  public void testWSBracketOp() {
    assertFmt("test[x] and z\n", "test    [   x ]  and z");
  }

  @Test
  public void testWSBracketAfter() {
    assertFmt("test[x]/xxx\n", "test    [   x ] / xxx");
  }

  @Test
  public void testPredicateDPath() {
    assertFmt("x[x]//y\n", "x  [ x ] // y");
  }

  @Test
  public void testPredicateFn() {
    assertFmt("foo(x[x])\n", "foo   ( x  [ x ]  )");
  }

  @Test
  public void testPredicateFn2() {
    assertFmt("x[foo()]\n", "x [ foo  ( )   ]  ");
  }

  @Test
  public void testFuncCallAfter() {
    assertFmt("test(x)/foo\n", "test    (   x )  / foo");
  }

  @Test
  public void testPathExprPreserve() {
    assertFmt("/foo//bar\n", "/foo//bar");
  }

  @Test
  public void testPathExprReduce() {
    assertFmt("/foo//bar\n", " /    foo    //   bar    ");
  }

  @Test
  public void testSlash() {
    assertFmt("/ and 1\n", "/    and    1");
  }

  @Test
  public void testSlash2() {
    assertFmt("1 and /\n", "1    and    /");
  }

  @Test
  public void testSlash3() {
    assertFmt("1 and /foo\n", "1    and    /foo");
  }

  @Test
  public void testDSlash() {
    assertFmt("//x and 1\n", "// x    and    1");
  }

  @Test
  public void testDSlash2() {
    assertFmt("1 and //x\n", "1    and    // x  ");
  }

  @Test
  public void testDSlash3() {
    assertFmt("1 and //foo\n", "1    and    //  foo");
  }

  @Test
  public void testAxisWS() {
    assertFmt("ancestor::hello\n", "ancestor::hello");
  }

  @Test
  public void testAxisWSCollapse() {
    assertFmt("ancestor::hello\n", "ancestor     ::  \t   hello");
  }

  @Test
  public void testAxisWSPrecending() {
    assertFmt("preceding::hello\n", "preceding     ::  \t   hello");
  }
}
