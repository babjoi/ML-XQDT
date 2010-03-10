package org.eclipse.wst.xquery.set.debug.debugger.preferences;

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
import org.eclipse.wst.xquery.debug.ui.preferences.TranslatableDebuggingEngineOptionsBlock;
import org.eclipse.wst.xquery.set.debug.debugger.SETDebuggerConstants;

public class SETDebuggerBlock extends TranslatableDebuggingEngineOptionsBlock {

    private Spinner fCommandPortSpinner;
    private Spinner fEventPortSpinner;

    public SETDebuggerBlock(IStatusChangeListener context, IProject project, PreferenceKey[] allKeys,
            IWorkbenchPreferenceContainer container) {
        super(context, project, allKeys, container);
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

    protected PreferenceKey getDebuggingEngineDbgpTranslatorEnabledKey() {
        return SETDebuggerPreferencePage.ENGINE_NEEDS_DBGP_TRANSLATOR;
    }

    protected void createEngineBlock(Composite parent) {
    }

    protected PreferenceKey getLogFileNamePreferenceKey() {
        return SETDebuggerPreferencePage.LOG_FILE_NAME;
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

    private void setSpinnersEnabled(boolean enabled) {
        int backColor = enabled ? SWT.COLOR_LIST_BACKGROUND : SWT.COLOR_WIDGET_BACKGROUND;
        int foreColor = enabled ? SWT.COLOR_WIDGET_FOREGROUND : SWT.COLOR_TITLE_INACTIVE_FOREGROUND;

        Composite comp = fCommandPortSpinner.getParent();
        fCommandPortSpinner.setBackground(comp.getDisplay().getSystemColor(backColor));
        fCommandPortSpinner.setForeground(comp.getDisplay().getSystemColor(foreColor));
        fEventPortSpinner.setBackground(comp.getDisplay().getSystemColor(backColor));
        fEventPortSpinner.setForeground(comp.getDisplay().getSystemColor(foreColor));
    }

    @Override
    protected void initialize() {
        super.initialize();

        setSpinnersEnabled(needsTranslator());

        String portStr = getString(SETDebuggerPreferencePage.ENGINE_SERVER_PORTS);
        String[] ports = portStr.split(":");
        int port = SETDebuggerConstants.SERVER_COMMAND_PORT;
        try {
            port = Integer.parseInt(ports[0]);
        } catch (NumberFormatException nfe) {
        }
        fCommandPortSpinner.setSelection(port);

        port = SETDebuggerConstants.SERVER_EVENT_PORT;
        try {
            port = Integer.parseInt(ports[1]);
        } catch (NumberFormatException nfe) {
        }
        fEventPortSpinner.setSelection(port);
    }

    @Override
    protected boolean processChanges(IWorkbenchPreferenceContainer container) {
        setString(SETDebuggerPreferencePage.ENGINE_SERVER_PORTS, fCommandPortSpinner.getSelection() + ":"
                + fEventPortSpinner.getSelection());
        return super.processChanges(container);
    }

}
