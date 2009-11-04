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
package org.eclipse.wst.xquery.core.model.ast;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.jface.text.TypedPosition;

public class XQueryXmlAttributeValueText extends ASTNode implements IXQDTSemanticPositionProvider {

    private String fValue;

    public XQueryXmlAttributeValueText(int start, int end, String value) {
        super(start, end);
        fValue = value;
    }

    public String getValue() {
        return fValue;
    }

    public TypedPosition getTypedPosition(String type) {
        return new TypedPosition(sourceStart(), sourceEnd() - sourceStart() + 1, type);
    }

    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj instanceof XQueryXmlAttributeValueText) {
            XQueryXmlAttributeValueText s = (XQueryXmlAttributeValueText)obj;
            if (s.sourceEnd() < 0 || s.sourceStart() < 0) {
                return false;
            }
            return sourceStart() == s.sourceStart() && sourceEnd() == s.sourceEnd() && getValue().equals(s.getValue());
        }

        return false;
    }

    @Override
    public void traverse(ASTVisitor visitor) throws Exception {
    }

    public int hashCode() {
        return this.sourceEnd() * 1001 + this.sourceEnd();
    }
}
