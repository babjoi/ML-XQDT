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
import org.eclipse.wst.xquery.ui.templates.XQDTTemplateContext;
import org.eclipse.wst.xquery.ui.templates.XQueryTemplateVariables;

public class XQueryTemplateContentType extends ScriptTemplateContextType {

    public static final String CONTEXT_TYPE_ID = "org.eclipse.wst.xquery.ui.templates.XQueryTemplateContentType";

    public XQueryTemplateContentType() {
    }

    public XQueryTemplateContentType(String id) {
        super(id);
    }

    public XQueryTemplateContentType(String id, String name) {
        super(id, name);
    }

    public ScriptTemplateContext createContext(IDocument document, int completionPosition, int length,
            ISourceModule sourceModule) {
        return new XQDTTemplateContext(this, document, completionPosition, length, sourceModule);
    }

    @Override
    protected void addGlobalResolvers() {
        super.addGlobalResolvers();

        addResolver(new XQueryTemplateVariables.IterationVariables());
        addResolver(new XQueryTemplateVariables.PositionalVariables());
        addResolver(new XQueryTemplateVariables.Variables());
        addResolver(new XQueryTemplateVariables.FunctionName());
        addResolver(new XQueryTemplateVariables.FunctionNamespace());
        addResolver(new XQueryTemplateVariables.FunctionParams());
        addResolver(new XQueryTemplateVariables.Quantifiers());
        addResolver(new XQueryTemplateVariables.SequenceTypes());
        addResolver(new XQueryTemplateVariables.BoundarySpaceTypes());
        addResolver(new XQueryTemplateVariables.DefaultNamespaceTypes());
        addResolver(new XQueryTemplateVariables.OrderingModes());
        addResolver(new XQueryTemplateVariables.EmptyOrderModes());
        addResolver(new XQueryTemplateVariables.PreserveModes());
        addResolver(new XQueryTemplateVariables.InheritModes());
        addResolver(new XQueryTemplateVariables.ConstructionModes());
        addResolver(new XQueryTemplateVariables.StrictOrder());
        addResolver(new XQueryTemplateVariables.OrderModifiers());
        addResolver(new XQueryTemplateVariables.ValidationModes());
        addResolver(new XQueryTemplateVariables.XQueryVersion());
        addResolver(new XQueryTemplateVariables.XQueryEncoding());
        addResolver(new XQueryTemplateVariables.ImportBuiltinLibraryModuleURIs());
        addResolver(new XQueryTemplateVariables.ImportLibraryModuleURIsAndHints());
        addResolver(new XQueryTemplateVariables.ImportSchemaPrefix());
        addResolver(new XQueryTemplateVariables.ImportSchemaUriAndHints());
    }

    public String[] getCompatibleContentTypes() {
        return new String[0];
    }
}
