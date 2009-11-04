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
package org.eclipse.wst.xquery.internal.ui.templates;

import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.ui.templates.ScriptTemplateContext;
import org.eclipse.dltk.ui.templates.ScriptTemplateContextType;
import org.eclipse.jface.text.IDocument;

public class XQueryFullTextTemplateContentType extends ScriptTemplateContextType {

    public static final String CONTEXT_TYPE_ID = "org.eclipse.wst.xquery.ui.templates.XQueryTemplateContentType";

    public XQueryFullTextTemplateContentType() {
    }

    public XQueryFullTextTemplateContentType(String id) {
        super(id);
    }

    public XQueryFullTextTemplateContentType(String id, String name) {
        super(id, name);
    }

    public ScriptTemplateContext createContext(IDocument document, int completionPosition, int length,
            ISourceModule sourceModule) {
        return new XQDTTemplateContext(this, document, completionPosition, length, sourceModule);
    }

    @Override
    protected void addGlobalResolvers() {
        super.addGlobalResolvers();

        addResolver(new XQueryTemplateVariables.SimpleFtOption());
    }
}
