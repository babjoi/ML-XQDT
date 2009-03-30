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

public class IndentationTest extends FormatterTestBase {
  @Test
  public void testifIndent() {
    String query = "if (x) then foo else    bar";
    assertFmt("if (x)\nthen foo\nelse bar\n", query);
  }

  @Test
  public void testIfIndent2() {
    assertFmt("if (x)\nthen foo\nelse bar\n",
        "    if (     x  )       \n\n\n\n then foo\n else    bar");
  }

  @Test
  public void testBreakLongLine() {
    String query = "foofunc(lotsofparams,lotsofparams,lotsofparams,lotsofparams,lotsofparams,"
        + "lotsofparams,lotsofparams)";
    assertFmt("foofunc(lotsofparams, lotsofparams, lotsofparams, lotsofparams, lotsofparams,\n"
        + "        lotsofparams, lotsofparams)\n", query);
  }

  @Test
  public void testLongThenStmt() {
    String query = "if (1) then reallylongconditionreallylongconditionreallylong"
        + "conditionreallylongcondition\nelse ()\n";
    assertFmt("if (1)\nthen\n    reallylongconditionreallylongconditionreallylong"
        + "conditionreallylongcondition\nelse ()\n", query);
  }
}
