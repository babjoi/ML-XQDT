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
package org.eclipse.wst.xquery.set.internal.launching;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.dltk.core.environment.EnvironmentPathUtils;
import org.eclipse.dltk.core.environment.IFileHandle;
import org.eclipse.dltk.launching.EnvironmentVariable;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.LibraryLocation;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wst.xquery.launching.XQDTInterpreterInstallType;
import org.eclipse.wst.xquery.set.core.SETNature;
import org.eclipse.wst.xquery.set.internal.launching.jobs.SETInstallValidator;
import org.eclipse.wst.xquery.set.launching.SETLaunchingPlugin;

public class CoreSDKInstallType extends XQDTInterpreterInstallType {

    private static final String INSTALL_TYPE_NAME = "Sausalito CoreSDK";
    private static final String INSTALL_TYPE_ID = "org.eclipse.wst.xquery.set.launching.CoreSDKInstallType";

    private class InstallMSVCRPDialog extends TitleAreaDialog {

        public InstallMSVCRPDialog(Shell parentShell) {
            super(parentShell);
        }

        @Override
        protected Control createDialogArea(Composite parent) {

            Composite control = (Composite)super.createDialogArea(parent);
            setTitle("Sausalito CoreSDK Configuration");
            setMessage("Install required system libraries");

            createText(control);

            return control;
        }

        private void createText(Composite parent) {
            Composite panel = new Composite(parent, SWT.NONE);

            GridLayout layout = new GridLayout(3, false);
            layout.marginHeight = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_MARGIN);
            layout.marginWidth = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_MARGIN);
            layout.verticalSpacing = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_SPACING);
            layout.horizontalSpacing = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_SPACING);
            panel.setLayout(layout);
            panel.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
            panel.setFont(parent.getFont());

            Link link = new Link(panel, SWT.NONE);
            link
                    .setText("The Microsoft Visual C++ 2008 Redistributable Package (x86) must be installed in order to run\n"
                            + "Sausalito Applications.\n\n"
                            + "<a>Click on this link</a> to install the the Microsoft Visual C++ 2008 Redistributable Package (x86).\n"
                            + "After the installation press \"Try again\".\n\n"
                            + "By pressing \"Ignore\" no Sausalito CoreSDK will be configured.\n");
            link.addSelectionListener(new SelectionAdapter() {
                @Override
                public void widgetSelected(SelectionEvent e) {
                    try {
                        ProcessBuilder pb = new ProcessBuilder("explorer.exe",
                                "C:\\Users\\Gabriel\\Eclipse Workspace WTP\\org.eclipse.wst.xquery.set.launching.win32\\vcredist");
                        pb.start();
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    }
                }
            });
        }

        protected void createButtonsForButtonBar(Composite parent) {
            createButton(parent, IDialogConstants.OK_ID, "Try again", true);
            createButton(parent, IDialogConstants.CANCEL_ID, "Ignore", false);
        }

    }

    protected IInterpreterInstall doCreateInterpreterInstall(String id) {
        return new CoreSDKInstall(this, id);
    }

    @Override
    public String getId() {
        return INSTALL_TYPE_ID;
    }

    public String getName() {
        return INSTALL_TYPE_NAME;
    }

    public String getNatureId() {
        return SETNature.NATURE_ID;
    }

    protected String getPluginId() {
        return SETLaunchingPlugin.PLUGIN_ID;
    }

    @Override
    protected String[] getPossibleInterpreterNames() {
        return new String[] { "sausalito" };
    }

    @Override
    public synchronized LibraryLocation[] getDefaultLibraryLocations(IFileHandle installLocation,
            EnvironmentVariable[] variables, IProgressMonitor monitor) {
        IPath sausalitoRoot = new Path(installLocation.getParent().getParent().getCanonicalPath()).append("modules");
        LibraryLocation loc = new LibraryLocation(EnvironmentPathUtils.getFullPath(getEnvironment(), sausalitoRoot));
        return new LibraryLocation[] { loc };
    }

    @Override
    public IStatus validatePossiblyName(IFileHandle installLocation) {
        IStatus status = super.validatePossiblyName(installLocation);

        if (!status.isOK()) {
            return status;
        }

        if (Platform.getOS().equals(Platform.OS_WIN32)) {
            status = validateInstall(installLocation);
        }

        return status;
    }

    private IStatus validateInstall(IFileHandle installLocation) {
        boolean done = false;
        IStatus status = null;

        while (!done) {
            SETInstallValidator validator = new SETInstallValidator(installLocation.getPath());
            ProgressMonitorDialog dialog = new ProgressMonitorDialog(Display.getCurrent().getActiveShell());
            try {
                dialog.run(true, true, validator);
                status = validator.getResult();
                if (!status.isOK()) {
                    InstallMSVCRPDialog installDialog = new InstallMSVCRPDialog(Display.getCurrent().getActiveShell());
                    if (installDialog.open() == Dialog.OK) {
                        continue;
                    }
                }
                break;
            } catch (InvocationTargetException ite) {
                ite.printStackTrace();
                return new Status(IStatus.ERROR, SETLaunchingPlugin.PLUGIN_ID, "ite");
            } catch (InterruptedException ie) {
                ie.printStackTrace();
                return new Status(IStatus.ERROR, SETLaunchingPlugin.PLUGIN_ID, "ie");
            }
        }
        return status;
    }
}
