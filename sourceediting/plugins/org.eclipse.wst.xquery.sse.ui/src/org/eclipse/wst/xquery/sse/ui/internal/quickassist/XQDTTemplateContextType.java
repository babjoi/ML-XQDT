/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.xquery.sse.ui.internal.quickassist;

import org.eclipse.jface.text.templates.TemplateContextType;

/**
 * Default template context type
 * 
 * @author <a href="villard@us.ibm.com">Lionel Villard</a>
 */
public class XQDTTemplateContextType extends TemplateContextType {

    // Constants

    final static private String ID = "org.eclipse.wst.xquery.sse.ui.contextType.expr";

    // Constructors 

    public XQDTTemplateContextType() {
        super(ID);
    }
}
