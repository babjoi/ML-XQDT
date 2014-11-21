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
package org.eclipse.wst.xquery.set.internal.ui.preferences;

import org.eclipse.core.resources.IProject;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.PropertyPage;
import org.eclipse.wst.xquery.set.core.SETProjectConfig;
import org.eclipse.wst.xquery.set.core.utils.SETProjectConfigUtil;
import org.eclipse.wst.xquery.set.internal.ui.SETEditProjectConfigDialog;

public class SETSausalitoPropertyPage extends PropertyPage {

    private IProject fProject;
    private SETProjectConfig fConfig;

    private Label fUriLabel;
    private Label fVersionLabel;
    private Label fStartPageLabel;
    private Label fApiVersionLabel;

    protected Control createContents(Composite parent) {
        final Composite composite = new Composite(parent, SWT.NONE);
        GridLayout layout = new GridLayout();
        layout.marginHeight = 0;
        layout.marginWidth = 0;
        composite.setLayout(layout);
        composite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        if (getElement() instanceof IProject) {
            fProject = (IProject)getElement();
            fConfig = SETProjectConfigUtil.readProjectConfig(fProject);

            composite.setLayout(new GridLayout(2, false));

            createProjectConfigControls(composite);
        } else if (getElement() instanceof IScriptProject) {
            fProject = ((IScriptProject)getElement()).getProject();
            fConfig = SETProjectConfigUtil.readProjectConfig(fProject);

            composite.setLayout(new GridLayout(2, false));

            createProjectConfigControls(composite);
        }
        return composite;
    }

    private void createProjectConfigControls(final Composite composite) {
        Composite configBlock = new Composite(composite, SWT.NONE);
        GridLayout layout = new GridLayout(2, false);
        layout.marginHeight = 0;
        layout.marginWidth = 0;
        configBlock.setLayout(layout);
        configBlock.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        Label label = new Label(configBlock, SWT.NONE);
        label.setText("Project logical URI:");

        fUriLabel = new Label(configBlock, SWT.NONE);
        fUriLabel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));

        label = new Label(configBlock, SWT.NONE);
        label.setText("Version:");

        fVersionLabel = new Label(configBlock, SWT.NONE);
        fVersionLabel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));

        label = new Label(configBlock, SWT.NONE);
        label.setText("Start page:");

        fStartPageLabel = new Label(configBlock, SWT.NONE);
        fStartPageLabel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));
//        fStartPageLink.addSelectionListener(new SelectionAdapter() {
//            @Override
//            public void widgetSelected(SelectionEvent e) {
//                String startPage = SETEditProjectConfigDialog.getHandlerFunctionStartPage(fProject, getShell());
//                fStartPageLink.setText(startPage);
//                fConfig.setStartPage(startPage);
//            }
//        });

        label = new Label(configBlock, SWT.NONE);
        label.setText("API Version:");

        fApiVersionLabel = new Label(configBlock, SWT.NONE);
        fApiVersionLabel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));

        updateConfigControls();

        createSeparator(configBlock);

        Composite buttonBar = new Composite(configBlock, SWT.NONE);
        layout = new GridLayout(1, false);
        layout.marginHeight = 0;
        layout.marginWidth = 0;
        buttonBar.setLayout(layout);

        GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_END | GridData.FILL_HORIZONTAL);
        gd.horizontalSpan = 2;
        buttonBar.setLayoutData(gd);

        Button changeButton = new Button(buttonBar, SWT.PUSH);
        int widthHint = convertHorizontalDLUsToPixels(IDialogConstants.BUTTON_WIDTH);
        gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        Point minButtonSize = changeButton.computeSize(SWT.DEFAULT, SWT.DEFAULT, true);
        gd.widthHint = Math.max(widthHint, minButtonSize.x);
        changeButton.setLayoutData(gd);
        changeButton.setText("&Change...");
        changeButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                Shell shell = composite.getShell();
                SETEditProjectConfigDialog dialog = new SETEditProjectConfigDialog(shell, fProject, fConfig);
                if (dialog.open() == Window.OK) {
                    fConfig = dialog.getProjectConfig();
                    updateConfigControls();
                }
            }
        });
    }

    private void updateConfigControls() {
        fUriLabel.setText(fConfig.getLogicalUri().toString());
        fVersionLabel.setText(fConfig.getVersion());

        String startPage = fConfig.getStartPage();
        if (startPage != null) {
            fStartPageLabel.setText(startPage);
        } else {
            fStartPageLabel.setText("not set");
        }

        String apiVersion = fConfig.getApiVersion();
        if (apiVersion.equals("")) {
            fApiVersionLabel.setText("will be set after the first run");
        } else {
            fApiVersionLabel.setText(apiVersion);
        }
    }

    private void createSeparator(Composite composite) {
        Label separator = new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL);
        GridData gridData = new GridData();
        gridData.horizontalAlignment = GridData.FILL;
        gridData.horizontalSpan = 2;
        gridData.grabExcessHorizontalSpace = true;
        separator.setLayoutData(gridData);
    }

    @Override
    public boolean performOk() {
        SETProjectConfigUtil.writeProjectConfig(fProject, fConfig);
        return true;
    }
}
