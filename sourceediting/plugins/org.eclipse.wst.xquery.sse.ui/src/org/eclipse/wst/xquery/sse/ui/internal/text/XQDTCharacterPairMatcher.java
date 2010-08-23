/*******************************************************************************
 * Copyright (c) 2010 28msec Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Gabriel Petrovay (28msec) - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.xquery.sse.ui.internal.text;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.source.DefaultCharacterPairMatcher;
import org.eclipse.wst.xquery.sse.core.text.IXQDTPartitions;

public class XQDTCharacterPairMatcher extends DefaultCharacterPairMatcher {

    //public static final char[] PAIRS = { '"', '"', '\'', '\''  '{', '}', '(', ')' , '[', ']' };
    public static final char[] PAIRS = {};

    public XQDTCharacterPairMatcher() {
        super(PAIRS, IXQDTPartitions.XQUERY_STRING);
    }

    public IRegion match(IDocument doc, int offset) {
        return super.match(doc, offset);
    }

}