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
package org.eclipse.wst.xquery.internal.ui;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.dltk.ui.CodeFormatterConstants;
import org.eclipse.dltk.ui.PreferenceConstants;
import org.eclipse.dltk.ui.editor.highlighting.SemanticHighlightingUtils;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.editors.text.EditorsUI;
import org.eclipse.wst.xquery.internal.ui.text.IXQDTColorConstants;
import org.eclipse.wst.xquery.ui.XQDTUIPlugin;

public class XQDTUIPreferenceInitializer extends AbstractPreferenceInitializer {

    public void initializeDefaultPreferences() {
        IPreferenceStore store = XQDTUIPlugin.getDefault().getPreferenceStore();

        EditorsUI.useAnnotationsPreferencePage(store);
        EditorsUI.useQuickDiffPreferencePage(store);

        // Initialize DLTK default values
        PreferenceConstants.initializeDefaultValues(store);

        // Initialize XQDT constants
        PreferenceConverter.setDefault(store, IXQDTColorConstants.XQDT_COMMENT, new RGB(63, 127, 95));
        PreferenceConverter.setDefault(store, IXQDTColorConstants.XQDT_XML_COMMENT, new RGB(62, 93, 209));
        PreferenceConverter.setDefault(store, IXQDTColorConstants.XQDT_XML_CDATA, new RGB(150, 150, 150));
        PreferenceConverter.setDefault(store, IXQDTColorConstants.XQDT_STRING, new RGB(9, 121, 17));
        PreferenceConverter.setDefault(store, IXQDTColorConstants.XQDT_XML_ATTRIBUTE_VALUE, new RGB(9, 121, 17));
        PreferenceConverter.setDefault(store, IXQDTColorConstants.XQDT_XML_ELEMENT_CONTENT, new RGB(63, 124, 136));

        PreferenceConverter.setDefault(store, IXQDTColorConstants.XQDT_KEYWORD, new RGB(0, 0, 175));
        PreferenceConverter.setDefault(store, IXQDTColorConstants.XQDT_VARIABLE, new RGB(49, 132, 149));
        PreferenceConverter.setDefault(store, IXQDTColorConstants.XQDT_FUNCTION, new RGB(60, 76, 114));//new RGB(60, 76, 114));
        PreferenceConverter.setDefault(store, IXQDTColorConstants.XQDT_ITEM_TYPE, new RGB(128, 0, 128));
        PreferenceConverter.setDefault(store, IXQDTColorConstants.XQDT_XML_TAG, new RGB(0, 0, 175));

        store.setDefault(IXQDTColorConstants.XQDT_COMMENT + PreferenceConstants.EDITOR_ITALIC_SUFFIX, true);
        store.setDefault(IXQDTColorConstants.XQDT_XML_COMMENT + PreferenceConstants.EDITOR_ITALIC_SUFFIX, true);
        store.setDefault(IXQDTColorConstants.XQDT_XML_CDATA + PreferenceConstants.EDITOR_ITALIC_SUFFIX, true);
        store.setDefault(IXQDTColorConstants.XQDT_KEYWORD + PreferenceConstants.EDITOR_BOLD_SUFFIX, true);
        //store.setDefault(IXQDTColorConstants.XQDT_FUNCTION + PreferenceConstants.EDITOR_BOLD_SUFFIX, true);
        //store.setDefault(IXQDTColorConstants.XQDT_XML_TAG + PreferenceConstants.EDITOR_BOLD_SUFFIX, true);

        store.setDefault(PreferenceConstants.EDITOR_CLOSE_BRACES, true);
        store.setDefault(PreferenceConstants.EDITOR_CLOSE_BRACKETS, true);
        store.setDefault(PreferenceConstants.EDITOR_CLOSE_STRINGS, true);
        store.setDefault(PreferenceConstants.EDITOR_COMMENTS_FOLDING_ENABLED, true);
        store.setDefault(PreferenceConstants.EDITOR_FOLDING_ENABLED, true);
        store.setDefault(PreferenceConstants.EDITOR_MATCHING_BRACKETS, true);
        store.setDefault(PreferenceConstants.EDITOR_MATCHING_BRACKETS_COLOR, true);
        store.setDefault(PreferenceConstants.EDITOR_SEMANTIC_HIGHLIGHTING_ENABLED_SUFFIX, "_enabled");
        store.setDefault(PreferenceConstants.EDITOR_SMART_HOME_END, true);
        store.setDefault(PreferenceConstants.EDITOR_SMART_INDENT, true);
        store.setDefault(PreferenceConstants.EDITOR_SMART_PASTE, true);
        store.setDefault(PreferenceConstants.EDITOR_SMART_TAB, true);
        store.setDefault(PreferenceConstants.EDITOR_SYNC_OUTLINE_ON_CURSOR_MOVE, true);
        store.setDefault(PreferenceConstants.EDITOR_TAB_ALWAYS_INDENT, true);
        store.setDefault(PreferenceConstants.EDITOR_TAB_WIDTH, 4);

        store.setDefault(PreferenceConstants.CODEASSIST_AUTOACTIVATION_TRIGGERS, ":");

        store.setDefault(CodeFormatterConstants.FORMATTER_TAB_CHAR, CodeFormatterConstants.SPACE);
        store.setDefault(CodeFormatterConstants.FORMATTER_TAB_SIZE, "4");
        store.setDefault(CodeFormatterConstants.FORMATTER_INDENTATION_SIZE, "4");

        SemanticHighlightingUtils.initializeDefaultValues(store, XQDTUIPlugin.getDefault().getTextTools()
                .getSemanticHighlightings());

    }
}
