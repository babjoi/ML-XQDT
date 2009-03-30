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

public class CommentsTest extends FormatterTestBase {

  @Test
  public void testComment1() {
    assertFmt("(: hello :)\n1\n", "(: hello :) 1");
  }

  @Test
  public void testComment2() {
    assertFmt("(: hello \n : wurst :)\n1\n", "(: hello \n wurst :) 1");
  }

  @Test
  public void testComment3() {
    assertFmt("(: hello \n : wurst :)\n1\n", "(: hello \n : wurst :) 1");
  }

  @Test
  public void testComment4() {
    assertFmt("(: hello \n : wurst :)\n1\n", "(: hello \n: wurst :) 1");
  }

  @Test
  public void testComment5() {
    assertFmt("(: hello \n : wurst \n : brot :)\n1\n", "(: hello \n: wurst \nbrot :) 1");
  }
}
