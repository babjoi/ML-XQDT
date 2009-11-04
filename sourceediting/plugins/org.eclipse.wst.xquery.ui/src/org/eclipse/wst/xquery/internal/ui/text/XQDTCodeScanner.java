/*******************************************************************************
 * Copyright (c) 2008, 2009 28msec Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Gabriel Petrovay (28msec) - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.xquery.internal.ui.text;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.dltk.ui.text.AbstractScriptScanner;
import org.eclipse.dltk.ui.text.IColorManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.wst.xquery.internal.ui.text.rules.ExpressionRule;

public class XQDTCodeScanner extends AbstractScriptScanner {

    private static String fgTokenProperties[] = new String[] { IXQDTColorConstants.XQDT_KEYWORD,
            IXQDTColorConstants.XQDT_DEFAULT, IXQDTColorConstants.XQDT_VARIABLE, IXQDTColorConstants.XQDT_FUNCTION,
            IXQDTColorConstants.XQDT_ITEM_TYPE, IXQDTColorConstants.XQDT_XML_TAG };

    public XQDTCodeScanner(IColorManager manager, IPreferenceStore store) {
        super(manager, store);
        this.initialize();
    }

    @Override
    protected List<IRule> createRules() {
        List<IRule> rules = new ArrayList<IRule>();

        addDolarRule(rules);
        // addLetRule(rules);
        // addForRule(rules);
        // addReturnRule(rules);
        // addWhereRule(rules);
        addElementEndTagRule(rules);
        addElementEmptyTagRule(rules);
        addElementStartTagNoAttrRule(rules);
        addElementStartTagAttrRule(rules);
        addFunctionRule(rules);
        addItemType(rules);
        // addDeclareVariableRule(rules);
        // addDeclareFunctionRule(rules);
        // addDeclareUpdatingFunctionRule(rules);
        // addDeclareSequentialFunctionRule(rules);
        // addIfRule(rules);
        // addTypeswitchRule(rules);
        // addWhileRule(rules);

        // IToken keyword = this.getToken(IXQDTColorConstants.XQDT_KEYWORD);

        // // Add generic whitespace rule.
        // rules.add(new WhitespaceRule(new XQDTWhitespaceDetector()));

        // // Add word rule for keywords.
        // WordRule wordRule = new WordRule(new XQDTWordDetector(), other);
        // for (int i = 0; i < XQDT_KEYWORDS.length; i++) {
        // wordRule.addWord(XQDT_KEYWORDS[i], keyword);
        // }
        // rules.add(wordRule);

        IToken other = this.getToken(IXQDTColorConstants.XQDT_DEFAULT);
        this.setDefaultReturnToken(other);
        return rules;
    }

    @Override
    protected String[] getTokenProperties() {
        return fgTokenProperties;
    }

    private void addDolarRule(List<IRule> rules) {
        IToken token;
        ExpressionRule expression;
        expression = new ExpressionRule();
        token = getToken(IXQDTColorConstants.XQDT_DEFAULT);
        expression.addRuleSegment("$", token);
        token = getToken(IXQDTColorConstants.XQDT_VARIABLE);
        expression.addRuleSegment(ExpressionRule.WORD_ANY_QNAME, token);
        rules.add(expression);
    }

    private void addFunctionRule(List<IRule> rules) {
        IToken token;
        ExpressionRule expression;
        expression = new ExpressionRule();
        token = getToken(IXQDTColorConstants.XQDT_FUNCTION);
        expression.addRuleSegment(ExpressionRule.WORD_FUNCTION_QNAME, token);
        token = getToken(IXQDTColorConstants.XQDT_DEFAULT);
        expression.addRuleSegment("(", token);
        rules.add(expression);
    }

    private void addItemType(List<IRule> rules) {
        IToken token;
        ExpressionRule expression;
        expression = new ExpressionRule();
        token = getToken(IXQDTColorConstants.XQDT_ITEM_TYPE);
        expression.addRuleSegment(ExpressionRule.WORD_ITEM_TYPE, token);
        token = getToken(IXQDTColorConstants.XQDT_DEFAULT);
        expression.addRuleSegment("(", token);
        rules.add(expression);
    }

    @SuppressWarnings("unused")
    private void addLetRule(List<IRule> rules) {
        IToken token;
        ExpressionRule expression;
        expression = new ExpressionRule();
        token = getToken(IXQDTColorConstants.XQDT_KEYWORD);
        expression.addRuleSegment("let", token);
        token = getToken(IXQDTColorConstants.XQDT_VARIABLE);
        expression.addRuleSegment("$", token, true);
        rules.add(expression);
    }

    @SuppressWarnings("unused")
    private void addForRule(List<IRule> rules) {
        IToken token;
        ExpressionRule expression;
        expression = new ExpressionRule();
        token = getToken(IXQDTColorConstants.XQDT_KEYWORD);
        expression.addRuleSegment("for", token);
        token = getToken(IXQDTColorConstants.XQDT_DEFAULT);
        expression.addRuleSegment("$", token, true);
        rules.add(expression);
    }

    @SuppressWarnings("unused")
    private void addReturnRule(List<IRule> rules) {
        IToken token;
        ExpressionRule expression;
        expression = new ExpressionRule();
        token = getToken(IXQDTColorConstants.XQDT_KEYWORD);
        expression.addRuleSegment("return", token);
        token = getToken(IXQDTColorConstants.XQDT_DEFAULT);
        expression.addRuleSegment("$", token, true);
        rules.add(expression);
    }

    @SuppressWarnings("unused")
    private void addWhereRule(List<IRule> rules) {
        IToken token;
        ExpressionRule expression;
        expression = new ExpressionRule();
        token = getToken(IXQDTColorConstants.XQDT_KEYWORD);
        expression.addRuleSegment("where", token);
        token = getToken(IXQDTColorConstants.XQDT_DEFAULT);
        expression.addRuleSegment("$", token, true);
        rules.add(expression);
    }

    @SuppressWarnings("unused")
    private void addIfRule(List<IRule> rules) {
        IToken token;
        ExpressionRule expression;
        expression = new ExpressionRule();
        token = getToken(IXQDTColorConstants.XQDT_KEYWORD);
        expression.addRuleSegment("if", token);
        token = getToken(IXQDTColorConstants.XQDT_DEFAULT);
        expression.addRuleSegment("(", token);
        rules.add(expression);
    }

    @SuppressWarnings("unused")
    private void addTypeswitchRule(List<IRule> rules) {
        IToken token;
        ExpressionRule expression;
        expression = new ExpressionRule();
        token = getToken(IXQDTColorConstants.XQDT_KEYWORD);
        expression.addRuleSegment("typeswitch", token);
        token = getToken(IXQDTColorConstants.XQDT_DEFAULT);
        expression.addRuleSegment("(", token);
        rules.add(expression);
    }

    @SuppressWarnings("unused")
    private void addWhileRule(List<IRule> rules) {
        IToken token;
        ExpressionRule expression;
        expression = new ExpressionRule();
        token = getToken(IXQDTColorConstants.XQDT_KEYWORD);
        expression.addRuleSegment("while", token);
        token = getToken(IXQDTColorConstants.XQDT_DEFAULT);
        expression.addRuleSegment("(", token);
        rules.add(expression);
    }

    /**
     * Adds the rule for matching XML element start tags with no attributes. E.g.: &lt;elem&gt;
     * 
     * @param rules
     *            the list to which this rule will be added
     */
    private void addElementStartTagNoAttrRule(List<IRule> rules) {
        IToken token;
        ExpressionRule expression;
        expression = new ExpressionRule();
        expression.setTolleratesWhitespaces(false);
        token = getToken(IXQDTColorConstants.XQDT_DEFAULT);
        expression.addRuleSegment("<", token);
        token = getToken(IXQDTColorConstants.XQDT_XML_TAG);
        expression.addRuleSegment(ExpressionRule.WORD_ANY_QNAME, token);
        token = getToken(IXQDTColorConstants.XQDT_DEFAULT);
        expression.addRuleSegment(ExpressionRule.WORD_WHITESPACE, token);
        expression.addRuleSegment(">", token);
        rules.add(expression);
    }

    /**
     * Adds the rule for matching XML element start tags with attributes. E.g.: &lt;elem
     * attr="..."&gt;
     * 
     * @param rules
     *            the list to which this rule will be added
     */
    private void addElementStartTagAttrRule(List<IRule> rules) {
        IToken token;
        ExpressionRule expression;
        expression = new ExpressionRule();
        expression.setTolleratesWhitespaces(false);
        token = getToken(IXQDTColorConstants.XQDT_DEFAULT);
        expression.addRuleSegment("<", token);
        token = getToken(IXQDTColorConstants.XQDT_XML_TAG);
        expression.addRuleSegment(ExpressionRule.WORD_ANY_QNAME, token);
        token = getToken(IXQDTColorConstants.XQDT_DEFAULT);
        expression.addRuleSegment(ExpressionRule.WORD_WHITESPACE, token);
        expression.addRuleSegment(ExpressionRule.WORD_ANY_QNAME, token, true);
        expression.addRuleSegment(ExpressionRule.WORD_WHITESPACE, token);
        expression.addRuleSegment("=", token);
        rules.add(expression);
    }

    /**
     * Adds the rule for matching empty XML element tags. E.g.: &lt;elem /&gt;
     * 
     * @param rules
     *            the list to which this rule will be added
     */
    private void addElementEmptyTagRule(List<IRule> rules) {
        IToken token;
        ExpressionRule expression;
        expression = new ExpressionRule();
        expression.setTolleratesWhitespaces(false);
        token = getToken(IXQDTColorConstants.XQDT_DEFAULT);
        expression.addRuleSegment("<", token);
        token = getToken(IXQDTColorConstants.XQDT_XML_TAG);
        expression.addRuleSegment(ExpressionRule.WORD_ANY_QNAME, token);
        token = getToken(IXQDTColorConstants.XQDT_DEFAULT);
        expression.addRuleSegment(ExpressionRule.WORD_WHITESPACE, token);
        expression.addRuleSegment("/>", token);
        rules.add(expression);
    }

    /**
     * Adds the rule for matching XML element end tags. E.g.: &lt;/elem&gt;
     * 
     * @param rules
     *            the list to which this rule will be added
     */
    private void addElementEndTagRule(List<IRule> rules) {
        IToken token;
        ExpressionRule expression;
        expression = new ExpressionRule();
        expression.setTolleratesWhitespaces(false);
        token = getToken(IXQDTColorConstants.XQDT_DEFAULT);
        expression.addRuleSegment("</", token);
        token = getToken(IXQDTColorConstants.XQDT_XML_TAG);
        expression.addRuleSegment(ExpressionRule.WORD_ANY_QNAME, token);
        token = getToken(IXQDTColorConstants.XQDT_DEFAULT);
        expression.addRuleSegment(ExpressionRule.WORD_WHITESPACE, token);
        expression.addRuleSegment(">", token);
        rules.add(expression);
    }

    @SuppressWarnings("unused")
    private void addDeclareFunctionRule(List<IRule> rules) {
        IToken token;
        ExpressionRule expression;
        expression = new ExpressionRule();
        token = getToken(IXQDTColorConstants.XQDT_KEYWORD);
        expression.addRuleSegment("declare", token);
        token = getToken(IXQDTColorConstants.XQDT_KEYWORD);
        expression.addRuleSegment("function", token);
        token = getToken(IXQDTColorConstants.XQDT_DEFAULT);
        expression.addRuleSegment(ExpressionRule.WORD_ANY_QNAME, token, true);
        rules.add(expression);
    }

    @SuppressWarnings("unused")
    private void addDeclareUpdatingFunctionRule(List<IRule> rules) {
        IToken token;
        ExpressionRule expression;
        expression = new ExpressionRule();
        token = getToken(IXQDTColorConstants.XQDT_KEYWORD);
        expression.addRuleSegment("declare", token);
        token = getToken(IXQDTColorConstants.XQDT_KEYWORD);
        expression.addRuleSegment("updating", token);
        token = getToken(IXQDTColorConstants.XQDT_KEYWORD);
        expression.addRuleSegment("function", token);
        token = getToken(IXQDTColorConstants.XQDT_DEFAULT);
        expression.addRuleSegment(ExpressionRule.WORD_ANY_QNAME, token, true);
        rules.add(expression);
    }

    @SuppressWarnings("unused")
    private void addDeclareVariableRule(List<IRule> rules) {
        IToken token;
        ExpressionRule expression;
        expression = new ExpressionRule();
        token = getToken(IXQDTColorConstants.XQDT_KEYWORD);
        expression.addRuleSegment("declare", token);
        token = getToken(IXQDTColorConstants.XQDT_KEYWORD);
        expression.addRuleSegment("$", token, true);
        token = getToken(IXQDTColorConstants.XQDT_DEFAULT);
        expression.addRuleSegment(ExpressionRule.WORD_ANY_QNAME, token);
        rules.add(expression);
    }

    @SuppressWarnings("unused")
    private void addDeclareSequentialFunctionRule(List<IRule> rules) {
        IToken token;
        ExpressionRule expression;
        expression = new ExpressionRule();
        token = getToken(IXQDTColorConstants.XQDT_KEYWORD);
        expression.addRuleSegment("declare", token);
        token = getToken(IXQDTColorConstants.XQDT_KEYWORD);
        expression.addRuleSegment("sequential", token);
        token = getToken(IXQDTColorConstants.XQDT_KEYWORD);
        expression.addRuleSegment("function", token);
        token = getToken(IXQDTColorConstants.XQDT_DEFAULT);
        expression.addRuleSegment(ExpressionRule.WORD_ANY_QNAME, token, true);
        rules.add(expression);
    }

}