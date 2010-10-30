/*******************************************************************************
 * Copyright (c) 2010 28msec Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Gabriel Petrovay (28msec) - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.xquery.set.internal.ui.dialogs;

import java.net.URL;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWebBrowser;

public class InfoLinkMessageDialog extends MessageDialog {

    private String fLinkText;
    private URL fLinkUrl;

    public InfoLinkMessageDialog(Shell parentShell, String dialogTitle, String dialogMessage, String linkText,
            URL linkUrl) {
        super(parentShell, dialogTitle, null, dialogMessage, MessageDialog.INFORMATION,
                new String[] { IDialogConstants.OK_LABEL }, 0);
        fLinkText = linkText;
        fLinkUrl = linkUrl;
    }

    @Override
    protected Control createMessageArea(Composite composite) {
        Composite parent = (Composite)super.createMessageArea(composite);

        Link link = new Link(parent, SWT.NONE);
        link.setText("\n" + fLinkText);
        GridDataFactory.fillDefaults().span(2, 1).applyTo(link);
        link.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                if (fLinkUrl != null) {
                    openBrowser();
                }
            }
        });

        return parent;
    }

    private void openBrowser() {
        Display.getDefault().syncExec(new Runnable() {
            public void run() {
                try {
                    IWebBrowser browser = PlatformUI.getWorkbench().getBrowserSupport().getExternalBrowser();
                    browser.openURL(fLinkUrl);
                } catch (PartInitException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
