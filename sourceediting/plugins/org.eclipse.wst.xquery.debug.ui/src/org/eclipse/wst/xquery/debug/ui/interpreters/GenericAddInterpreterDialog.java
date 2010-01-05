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
package org.eclipse.wst.xquery.debug.ui.interpreters;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.dltk.core.environment.EnvironmentManager;
import org.eclipse.dltk.core.environment.IEnvironment;
import org.eclipse.dltk.internal.debug.ui.interpreters.IAddInterpreterDialogRequestor;
import org.eclipse.dltk.internal.debug.ui.interpreters.IScriptInterpreterDialog;
import org.eclipse.dltk.internal.debug.ui.interpreters.InterpretersMessages;
import org.eclipse.dltk.internal.ui.wizards.dialogfields.ComboDialogField;
import org.eclipse.dltk.internal.ui.wizards.dialogfields.DialogField;
import org.eclipse.dltk.internal.ui.wizards.dialogfields.IDialogFieldListener;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.IInterpreterInstallType;
import org.eclipse.dltk.launching.InterpreterStandin;
import org.eclipse.dltk.ui.util.IStatusChangeListener;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.StatusDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wst.xquery.core.XQDTCorePlugin;
import org.eclipse.wst.xquery.debug.ui.IXQDTDebugUIConstants;
import org.eclipse.wst.xquery.debug.ui.XQDTDebugUIPlugin;

@SuppressWarnings("restriction")
public class GenericAddInterpreterDialog extends StatusDialog implements IScriptInterpreterDialog,
        IStatusChangeListener {

    protected final IAddInterpreterDialogRequestor fRequestor;
    private IInterpreterInstallType[] fInterpreterTypes;
    private IInterpreterInstallType fSelectedInterpreterType;
    private final IInterpreterInstall fEditedInterpreter;

    private int fPrevIndex = -1;
    private IEnvironment fEnvironment;

    private ComboDialogField fInterpreterTypeCombo;

    // the container for the custom component that implementors can provide 
    private Composite fDialogBlockContainer;
    // the part of the dialog that implementors can provide 
    private AbstractAddInterpreterDialogBlock fDialogBlock;

    public GenericAddInterpreterDialog(IAddInterpreterDialogRequestor requestor, Shell shell,
            IInterpreterInstallType[] interpreterInstallTypes, IInterpreterInstall editedInterpreter) {
        super(shell);
        setShellStyle(getShellStyle() | SWT.RESIZE);
        fRequestor = requestor;

        fInterpreterTypes = interpreterInstallTypes;
        fSelectedInterpreterType = editedInterpreter != null ? editedInterpreter.getInterpreterInstallType()
                : interpreterInstallTypes[0];
        fEditedInterpreter = editedInterpreter;
    }

    public boolean execute() {
        return open() == Window.OK;
    }

    /**
     * @see org.eclipse.jface.dialogs.Dialog#setButtonLayoutData(org.eclipse.swt.widgets.Button)
     */
    @Override
    public void setButtonLayoutData(Button button) {
        super.setButtonLayoutData(button);
        ((GridData)button.getLayoutData()).grabExcessHorizontalSpace = true;
    }

    public void updateStatusLine() {
        // updateStatus(fDialogBlock.getStatus());
    }

    protected void updateButtonsEnableState(IStatus status) {
        Button ok = getButton(IDialogConstants.OK_ID);
        if (ok != null && !ok.isDisposed()) {
            ok.setEnabled(status.getSeverity() == IStatus.OK);
        }
    }

    protected void okPressed() {
        doOkPressed();
        super.okPressed();
    }

    @Override
    public void create() {
        super.create();
        fDialogBlock.setFocus();
        selectInterpreterType();
    }

    protected Control createDialogArea(Composite ancestor) {
        Composite parent = (Composite)super.createDialogArea(ancestor);

        int numColumns = 3;

        fDialogBlockContainer = new Composite(parent, SWT.NONE);
        GridLayout layout = new GridLayout(numColumns, false);
        layout.marginWidth = 0;
        layout.marginHeight = 0;
        layout.verticalSpacing = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_SPACING);
        layout.horizontalSpacing = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_SPACING);
        fDialogBlockContainer.setLayout(layout);
        fDialogBlockContainer.setLayoutData(new GridData(GridData.FILL_BOTH));

        fInterpreterTypeCombo = new ComboDialogField(SWT.READ_ONLY);
        fInterpreterTypeCombo.setLabelText(getInterpreterTypeLabel());
        fInterpreterTypeCombo.doFillIntoGrid(fDialogBlockContainer, 2);
        GridData gd = (GridData)fInterpreterTypeCombo.getComboControl(null).getLayoutData();
        gd.horizontalSpan = 2;
        gd.horizontalAlignment = SWT.END;

        createAddInterpreterDialogBlock();
        fDialogBlock.createControls(fDialogBlockContainer);

        initializeFields(fEditedInterpreter);
        createFieldListeners();
        applyDialogFont(fDialogBlockContainer);

        return parent;
    }

    private IInterpreterInstall lastInstall = null;

    private void doOkPressed() {
        if (fEditedInterpreter == null) {
            IInterpreterInstall install = new InterpreterStandin(fSelectedInterpreterType,
                    createUniqueId(fSelectedInterpreterType));
            setFieldValuesToInterpreter(install);
            fRequestor.interpreterAdded(install);
            lastInstall = install;
        } else {
            setFieldValuesToInterpreter(fEditedInterpreter);
            lastInstall = fEditedInterpreter;
        }
    }

    private String createUniqueId(IInterpreterInstallType InterpreterType) {
        String id = null;
        do {
            id = String.valueOf(System.currentTimeMillis());
        } while (InterpreterType.findInterpreterInstall(id) != null);
        return id;
    }

    protected void setFieldValuesToInterpreter(IInterpreterInstall install) {
        fDialogBlock.setFieldValuesToInterpreter(install);
    }

    private void updateInterpreterType() {
        boolean redrawBlock = false;
        if (fPrevIndex != -1) {
            redrawBlock = true;
        }

        int selIndex = fInterpreterTypeCombo.getSelectionIndex();
        if (selIndex == fPrevIndex) {
            return;
        }
        fPrevIndex = selIndex;
        if (selIndex >= 0 && selIndex < fInterpreterTypes.length) {
            fSelectedInterpreterType = fInterpreterTypes[selIndex];
        }

        if (redrawBlock) {
            createAddInterpreterDialogBlock();
            fDialogBlock.createControls(fDialogBlockContainer);
            fDialogBlock.initializeFields(null);
            fDialogBlock.createFieldListeners();
            fDialogBlockContainer.layout(true);
            fDialogBlockContainer.getParent().pack();
            getShell().pack();
        }
        updateStatusLine();
    }

    public IEnvironment getEnvironment() {
        return this.fEnvironment;
    }

    public void setEnvironment(IEnvironment environment) {
        this.fEnvironment = environment;
    }

    public IInterpreterInstallType[] getInterpreterTypes() {
        return fInterpreterTypes;

    }

    /**
     * This method could be used only after okPressed.
     */
    protected IInterpreterInstall getLastInterpreterInstall() {
        return this.lastInstall;
    }

    protected void createFieldListeners() {

        fInterpreterTypeCombo.setDialogFieldListener(new IDialogFieldListener() {

            public void dialogFieldChanged(DialogField field) {
                updateInterpreterType();
            }
        });

        fDialogBlock.createFieldListeners();
    }

    private void createAddInterpreterDialogBlock() {
        if (fDialogBlock != null) {
            fDialogBlock.dispose();
            fDialogBlock = null;
        }

        IExtensionPoint extensionPoint = Platform.getExtensionRegistry().getExtensionPoint(XQDTDebugUIPlugin.PLUGIN_ID,
                IXQDTDebugUIConstants.EXTENSION_POINT_INTERPRETER_INSTALL_TYPE_ADD_DIALOG_BLOCK);

        if (fSelectedInterpreterType == null) {
            XQDTDebugUIPlugin.getDefault().getLog().log(
                    new Status(IStatus.ERROR, XQDTDebugUIPlugin.PLUGIN_ID,
                            "No IInterpreterInstallType definition can be associated with this environment: "
                                    + getEnvironment().getClass().getName()));
            return;
        }

        IConfigurationElement[] infos = extensionPoint.getConfigurationElements();
        for (int i = 0; i < infos.length; i++) {
            String id = infos[i].getAttribute("interpreterInstallTypeID");
            if (fSelectedInterpreterType.getId().equals(id)) {
                try {
                    fDialogBlock = (AbstractAddInterpreterDialogBlock)infos[i].createExecutableExtension("class");
                    break;
                } catch (CoreException e) {
                    if (XQDTCorePlugin.DEBUG) {
                        e.printStackTrace();
                    }
                }
            }

        }
        // if no custom dialog block is provided use the default one
        if (fDialogBlock == null) {
            if (getEnvironment() == EnvironmentManager.getLocalEnvironment()) {
                fDialogBlock = new AddLocalInterpreterDialogBlock();
            }
        }

        if (fDialogBlock == null) {
            XQDTDebugUIPlugin
                    .getDefault()
                    .getLog()
                    .log(
                            new Status(
                                    IStatus.ERROR,
                                    XQDTDebugUIPlugin.PLUGIN_ID,
                                    "No AbstractAddInterpreterDialogBlock definition was found for the interpreter install type: "
                                            + fSelectedInterpreterType.getClass().getName()
                                            + ". Use the org.eclipse.wst.xquery.debug.ui.interpreterInstallTypeAddDialogBlock extension point to provide one."));
            return;
        }

        fDialogBlock.initialize(this, fRequestor, fEditedInterpreter);
    }

    public IInterpreterInstallType getInterpreterType() {
        return fSelectedInterpreterType;
    }

    private String[] getInterpreterTypeNames() {
        String[] names = new String[fInterpreterTypes.length];
        for (int i = 0; i < fInterpreterTypes.length; i++) {
            names[i] = fInterpreterTypes[i].getName();
        }
        return names;
    }

    protected String getInterpreterTypeLabel() {
        return InterpretersMessages.addInterpreterDialog_InterpreterEnvironmentType;
    }

    protected String getInterpreterNameLabel() {
        return InterpretersMessages.addInterpreterDialog_InterpreterEnvironmentName;
    }

    private void selectInterpreterType() {
        for (int i = 0; i < fInterpreterTypes.length; i++) {
            if (fSelectedInterpreterType == fInterpreterTypes[i]) {
                fInterpreterTypeCombo.selectItem(i);
                return;
            }
        }
    }

    @Override
    public int convertWidthInCharsToPixels(int chars) {
        return super.convertWidthInCharsToPixels(chars);
    }

    @Override
    public int convertVerticalDLUsToPixels(int dlus) {
        return super.convertVerticalDLUsToPixels(dlus);
    }

    @Override
    protected int convertHorizontalDLUsToPixels(int dlus) {
        return super.convertHorizontalDLUsToPixels(dlus);
    }

    protected void initializeFields(IInterpreterInstall install) {
        fInterpreterTypeCombo.setItems(getInterpreterTypeNames());
        if (install != null) {
            fInterpreterTypeCombo.setText(install.getInterpreterInstallType().getName());
            fInterpreterTypeCombo.setEnabled(false);

        }
        fDialogBlock.initializeFields(install);
        updateStatusLine();
    }

    public void statusChanged(IStatus status) {
        updateStatus(status);
    }

}