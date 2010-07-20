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

import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.compiler.env.IModuleSource;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.SourceParserUtil;
import org.eclipse.dltk.ui.editor.highlighting.AbstractSemanticHighlighter;
import org.eclipse.dltk.ui.editor.highlighting.SemanticHighlighting;
import org.eclipse.dltk.ui.preferences.PreferencesMessages;
import org.eclipse.jface.text.Position;
import org.eclipse.wst.xquery.core.model.ast.XQueryModule;

public class XQDTSemanticHighlighter extends AbstractSemanticHighlighter {

    protected boolean doHighlighting(IModuleSource code) throws Exception {
        IModelElement modelElem = code.getModelElement();
        if (modelElem instanceof org.eclipse.dltk.core.ISourceModule) {
            ModuleDeclaration xqModule = SourceParserUtil
                    .getModuleDeclaration((org.eclipse.dltk.core.ISourceModule)modelElem);
            if (xqModule != null) {
                for (Position p : ((XQueryModule)xqModule).getKeywords()) {
                    addPosition(p.offset, p.offset + p.length, 0);
                }
                return true;
            }
        }
        return false;
    }

    public static SemanticHighlighting[] getSemanticHighlightings() {
        return new SemanticHighlighting[] { new XQDTSemanticHighlighting(IXQDTColorConstants.XQDT_KEYWORD,
                PreferencesMessages.DLTKEditorPreferencePage_keywords) };
    }

}
