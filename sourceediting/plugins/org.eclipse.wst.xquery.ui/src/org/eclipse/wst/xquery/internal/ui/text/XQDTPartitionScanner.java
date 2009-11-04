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
import java.util.Arrays;
import java.util.List;

import org.eclipse.jface.text.IDocumentExtension3;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.RuleBasedPartitionScanner;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.wst.xquery.internal.ui.text.rules.SemanticMultiEndMultiLineRule;
import org.eclipse.wst.xquery.internal.ui.text.rules.ISemanticRule;
import org.eclipse.wst.xquery.internal.ui.text.rules.SemanticMultiLineRule;
import org.eclipse.wst.xquery.internal.ui.text.rules.XQDTPartitioner;

public class XQDTPartitionScanner extends RuleBasedPartitionScanner implements IXQDTPartitions {

    public XQDTPartitionScanner() {
        IToken xmlCDATA = new Token(XQDT_XML_CDATA);
        IToken xmlComment = new Token(XQDT_XML_COMMENT);
        IToken comment = new Token(XQDT_COMMENT);

        IToken string = new Token(XQDT_STRING);

        IToken elemContent = new Token(XQDT_XML_ELEMENT_CONTENT);
        IToken attrValue = new Token(XQDT_XML_ATTRIBUTE_VALUE);

        List<IRule> rules = new ArrayList<IRule>();

        // add the rule for the simple quote string
        rules.add(new SemanticMultiLineRule("'", "'", string, (char)0, true));
        // add the rule for the double quote string
        rules.add(new SemanticMultiLineRule("\"", "\"", string, (char)0, true));

        // add the rules for the element content
        rules.add(new SemanticMultiEndMultiLineRule(true, ">", new String[] { "<", "{" }, elemContent, (char)0, true));
        rules.add(new SemanticMultiEndMultiLineRule(true, "}", new String[] { "<", "{" }, elemContent, (char)0, true));

        // add the rules for the attribute value
        rules.add(new SemanticMultiEndMultiLineRule(true, "\"", new String[] { "{", "\"" }, attrValue, (char)0, true));
        rules.add(new SemanticMultiEndMultiLineRule(true, "}", new String[] { "\"", "{" }, attrValue, (char)0, true));
        rules.add(new SemanticMultiEndMultiLineRule(true, "'", new String[] { "{", "'" }, attrValue, (char)0, true));
        rules.add(new SemanticMultiEndMultiLineRule(true, "}", new String[] { "'", "{" }, attrValue, (char)0, true));

        // add the rule for the XQuery comment
        rules.add(new MultiLineRule("(:", ":)", comment, (char)0, true));
        // add the rule for the XML comment
        rules.add(new MultiLineRule("<!--", "-->", xmlComment, (char)0, true));
        // add the rule for the XML CDATA
        rules.add(new MultiLineRule("<![CDATA[", "]]>", xmlCDATA, (char)0, true));

        setPredicateRules(rules.toArray(new IPredicateRule[rules.size()]));
    }

    @Override
    public IToken nextToken() {
        if (fRules == null) {
            return super.nextToken();
        }

        // inside a partition

        fColumn = UNDEFINED;
        boolean resume = false;// (fPartitionOffset > -1 && fPartitionOffset < fOffset);
        fTokenOffset = resume ? fPartitionOffset : fOffset;

        IPredicateRule rule;
        IToken token;

        String offsetPartition = getOffsetPartitionType();
        if (isSemanticPartitionType(offsetPartition)) {
            for (int i = 0; i < fRules.length; i++) {
                rule = (IPredicateRule)fRules[i];
                if ((fRules[i] instanceof ISemanticRule)
                        && ((ISemanticRule)fRules[i]).getContentType() == offsetPartition) {
                    token = (rule.evaluate(this, resume));
                    if (!token.isUndefined())
                        return token;
                }
            }
        } else {
            for (int i = 0; i < fRules.length; i++) {
                rule = (IPredicateRule)fRules[i];
                if (!(rule instanceof ISemanticRule)) {
                    token = (rule.evaluate(this, resume));
                    if (!token.isUndefined())
                        return token;
                }
            }
        }

        if (resume)
            fOffset = fPartitionOffset;

        if (read() == EOF)
            return Token.EOF;
        return fDefaultReturnToken;
    }

    public static boolean isSemanticPartitionType(String type) {
        return Arrays.binarySearch(IXQDTPartitions.XQDT_SEMANTIC_PARTITION_TYPES, type) >= 0;
    }

    private String getOffsetPartitionType() {
        ITypedRegion partition = null;
        try {
            if (fOffset > fDocument.getLength()
                    || ((IDocumentExtension3)fDocument).getDocumentPartitioner(IXQDTPartitions.XQDT_PARTITIONING) == null)
                return "";
            XQDTPartitioner partitioner = (XQDTPartitioner)((IDocumentExtension3)fDocument)
                    .getDocumentPartitioner(IXQDTPartitions.XQDT_PARTITIONING);
            partition = partitioner.getPartitionFromDocument(fOffset);
            return partition.getType();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public boolean acceptsStartSequence(char[] sequence) {
        return fOffset >= sequence.length;
    }

}