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

import org.eclipse.dltk.ast.expressions.StringLiteral;
import org.eclipse.jface.text.TypedPosition;

public class XQueryStringLiteral extends StringLiteral implements IXQDTSemanticPositionProvider {

    public XQueryStringLiteral(int start, int end, String value) {
        super(start, end, value);
    }

    public TypedPosition getTypedPosition(String type) {
        return new TypedPosition(sourceStart(), sourceEnd() - sourceStart() + 1, type);
    }

}
