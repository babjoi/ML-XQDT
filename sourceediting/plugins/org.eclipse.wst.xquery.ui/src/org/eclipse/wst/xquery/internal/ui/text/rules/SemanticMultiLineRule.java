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
package org.eclipse.wst.xquery.internal.ui.text.rules;

import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;

public class SemanticMultiLineRule extends MultiLineRule implements ISemanticRule {

    public SemanticMultiLineRule(String startSequence, String endSequence, IToken token) {
        this(startSequence, endSequence, token, (char)0);
    }

    public SemanticMultiLineRule(String startSequence, String endSequence, IToken token, char escapeCharacter) {
        this(startSequence, endSequence, token, escapeCharacter, false);
    }

    public SemanticMultiLineRule(String startSequence, String endSequence, IToken token, char escapeCharacter,
            boolean breaksOnEOF) {
        super(startSequence, endSequence, token, escapeCharacter, breaksOnEOF);
    }

    public String getContentType() {
        return (String)fToken.getData();
    }

}
