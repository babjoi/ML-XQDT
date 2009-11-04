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
package org.eclipse.wst.xquery.ui;

import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.ui.AbstractDLTKUILanguageToolkit;
import org.eclipse.dltk.ui.IDLTKUILanguageToolkit;
import org.eclipse.dltk.ui.ScriptElementLabels;
import org.eclipse.dltk.ui.text.ScriptSourceViewerConfiguration;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.wst.xquery.core.XQDTLanguageToolkit;
import org.eclipse.wst.xquery.internal.ui.text.XQDTSimpleSourceViewerConfiguration;

public class XQDTUILanguageToolkit extends AbstractDLTKUILanguageToolkit {

    private static final String INTERPRETERS_PREFERENCE_PAGE_ID = "org.eclipse.wst.xquery.preferences.interpreters"; //$NON-NLS-1$

    private static XQDTUILanguageToolkit sToolkit = null;

    public IPreferenceStore getPreferenceStore() {
        return XQDTUIPlugin.getDefault().getPreferenceStore();
    }

    public IDLTKLanguageToolkit getCoreToolkit() {
        return XQDTLanguageToolkit.getDefault();
    }

    public String getInterpreterPreferencePage() {
        return INTERPRETERS_PREFERENCE_PAGE_ID;
    }

    public static IDLTKUILanguageToolkit getInstance() {
        if (sToolkit == null)
            sToolkit = new XQDTUILanguageToolkit();
        return sToolkit;
    }

    @Override
    public ScriptSourceViewerConfiguration createSourceViewerConfiguration() {
        return new XQDTSimpleSourceViewerConfiguration(getTextTools().getColorManager(), getPreferenceStore(), null,
                getPartitioningId(), false);
    }

    @Override
    public ScriptElementLabels getScriptElementLabels() {
        return new XQDTScriptElementLabels();
    }

    public String getInterpreterContainerId() {
        return "org.eclipse.wst.xquery.launching.INTERPRETER_CONTAINER";
    }

}
