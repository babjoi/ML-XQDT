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

import org.antlr.runtime.Token;
import org.antlr.runtime.debug.ParseTreeBuilder;

public class DecisionAwareParseTreeBuilder extends ParseTreeBuilder {

  @Override
  public void consumeToken(Token token) {
    if (inDecision == 0) super.consumeToken(token);
  }

  private int inDecision = 0;

  @Override
  public void enterDecision(int decisionNumber) {
    super.enterDecision(decisionNumber);
    inDecision++;
  }

  @Override
  public void exitDecision(int decisionNumber) {
    super.exitDecision(decisionNumber);
    inDecision--;
  }

  public DecisionAwareParseTreeBuilder(String grammarName) {
    super(grammarName);
  }

}
