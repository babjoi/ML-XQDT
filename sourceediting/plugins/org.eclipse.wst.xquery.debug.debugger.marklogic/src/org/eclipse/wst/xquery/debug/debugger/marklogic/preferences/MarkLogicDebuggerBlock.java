/*******************************************************************************
 * Copyright (c) 2008, 2009 28msec Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Gabriel Petrovay (28msec) - initial API and implementation
 *     Sam Neth (Mark Logic) - adapted to the MarkLogic XQDT Feature
 *******************************************************************************/
package org.eclipse.wst.xquery.debug.debugger.marklogic.preferences;

import org.eclipse.core.resources.IProject;
import org.eclipse.dltk.ui.preferences.PreferenceKey;
import org.eclipse.dltk.ui.util.IStatusChangeListener;
import org.eclipse.dltk.ui.util.SWTFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.ui.preferences.IWorkbenchPreferenceContainer;
import org.eclipse.wst.xquery.debug.engines.marklogic.MarkLogicDebuggerConstants;
import org.eclipse.wst.xquery.debug.ui.preferences.TranslatableDebuggingEngineOptionsBlock;

public class MarkLogicDebuggerBlock extends TranslatableDebuggingEngineOptionsBlock {

    private Spinner fCommandPortSpinner;
    private Spinner fEventPortSpinner;

    public MarkLogicDebuggerBlock(IStatusChangeListener context, IProject project, PreferenceKey[] allKeys,
            IWorkbenchPreferenceContainer container) {
        super(context, project, allKeys, container);
    }

    protected PreferenceKey getLogFileNamePreferenceKey() {
        return MarkLogicDebuggerPreferencePage.LOG_FILE_NAME;
    }

    protected PreferenceKey getDebuggingEngineDbgpTranslatorEnabledKey() {
        return MarkLogicDebuggerPreferencePage.ENGINE_NEEDS_DBGP_TRANSLATOR;
    }

    protected void createEngineBlock(Composite parent) {
    }

    @Override
    protected void createOtherBlock(Composite parent) {
        super.createOtherBlock(parent);
    }

    private Spinner addPortSpinner(String labelText, Composite parent) {
        Label label = new Label(parent, SWT.NONE);
        label.setText(labelText);

        Spinner spinner = new Spinner(parent, SWT.BORDER | SWT.RIGHT);
        spinner.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        spinner.setMinimum(1024);
        spinner.setMaximum(65535);
        spinner.setIncrement(1);
        spinner.setPageIncrement(100);

        return spinner;
    }

    protected void addOptions(Composite parent) {
        final Composite comp = SWTFactory.createComposite(parent, parent.getFont(), 2, 1, GridData.FILL);
        fDbgpTranslatorButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                comp.setEnabled(needsTranslator());
                setSpinnersEnabled(needsTranslator());
            }
        });
        fCommandPortSpinner = addPortSpinner("Command port:", comp);
        fEventPortSpinner = addPortSpinner("Event port:", comp);
    }

    @Override
    protected void initialize() {
        super.initialize();

        setSpinnersEnabled(needsTranslator());

        String portStr = getString(MarkLogicDebuggerPreferencePage.ENGINE_SERVER_PORTS);
        String[] ports = portStr.split(":");
        int port = MarkLogicDebuggerConstants.SERVER_COMMAND_PORT;
        try {
            port = Integer.parseInt(ports[0]);
        } catch (NumberFormatException nfe) {
        }
        fCommandPortSpinner.setSelection(port);

        port = MarkLogicDebuggerConstants.SERVER_EVENT_PORT;
        try {
            port = Integer.parseInt(ports[1]);
        } catch (NumberFormatException nfe) {
        }
        fEventPortSpinner.setSelection(port);
    }

    @Override
    protected boolean processChanges(IWorkbenchPreferenceContainer container) {
        setString(MarkLogicDebuggerPreferencePage.ENGINE_SERVER_PORTS, fCommandPortSpinner.getSelection() + ":"
                + fEventPortSpinner.getSelection());
        return super.processChanges(container);
    }

    private void setSpinnersEnabled(boolean enabled) {
        int backColor = enabled ? SWT.COLOR_LIST_BACKGROUND : SWT.COLOR_WIDGET_BACKGROUND;
        int foreColor = enabled ? SWT.COLOR_WIDGET_FOREGROUND : SWT.COLOR_TITLE_INACTIVE_FOREGROUND;

        Composite comp = fCommandPortSpinner.getParent();
        fCommandPortSpinner.setBackground(comp.getDisplay().getSystemColor(backColor));
        fCommandPortSpinner.setForeground(comp.getDisplay().getSystemColor(foreColor));
        fEventPortSpinner.setBackground(comp.getDisplay().getSystemColor(backColor));
        fEventPortSpinner.setForeground(comp.getDisplay().getSystemColor(foreColor));

    }
}