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
package org.eclipse.wst.xquery.debug.ui.preferences;

import org.eclipse.core.resources.IProject;
import org.eclipse.dltk.debug.ui.preferences.DebuggingEngineConfigOptionsBlock;
import org.eclipse.dltk.ui.preferences.PreferenceKey;
import org.eclipse.dltk.ui.util.IStatusChangeListener;
import org.eclipse.dltk.ui.util.SWTFactory;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.preferences.IWorkbenchPreferenceContainer;

public abstract class TranslatableDebuggingEngineOptionsBlock extends DebuggingEngineConfigOptionsBlock {

    protected Button fDbgpTranslatorButton;

    public TranslatableDebuggingEngineOptionsBlock(IStatusChangeListener context, IProject project,
            PreferenceKey[] allKeys, IWorkbenchPreferenceContainer container) {
        super(context, project, allKeys, container);
    }

    @Override
    protected void createOtherBlock(Composite parent) {
        final Group group = SWTFactory.createGroup(parent, "Options", 1, 1, GridData.FILL_BOTH);
        fDbgpTranslatorButton = SWTFactory.createCheckButton(group, "Engine needs DBGP translator");
        addOptions(group);
    }

    @Override
    protected boolean processChanges(IWorkbenchPreferenceContainer container) {
        setBoolean(getDebuggingEngineDbgpTranslatorEnabledKey(), fDbgpTranslatorButton.getSelection());
        return super.processChanges(container);
    }

    @Override
    protected void initialize() {
        super.initialize();
        fDbgpTranslatorButton.setSelection(getBoolean(getDebuggingEngineDbgpTranslatorEnabledKey()));
    }

    protected abstract void addOptions(Composite parent);

    protected abstract PreferenceKey getDebuggingEngineDbgpTranslatorEnabledKey();

    protected boolean needsTranslator() {
        return fDbgpTranslatorButton.getSelection();
    }
}