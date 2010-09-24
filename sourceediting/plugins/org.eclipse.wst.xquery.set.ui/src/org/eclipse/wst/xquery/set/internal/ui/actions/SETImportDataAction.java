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
package org.eclipse.wst.xquery.set.internal.ui.actions;

import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.wst.xquery.set.internal.launching.jobs.SETImportDataJob;
import org.eclipse.wst.xquery.set.internal.ui.dialogs.InfoLinkMessageDialog;

public class SETImportDataAction extends SETCoreSDKCommandAction {

    private static final String MESSAGE_NOTHING_TO_DO_URL = "http://www.28msec.com/support_sausalito_project_structure/index";
    private static final String MESSAGE_NOTHING_TO_DO_DETAILS = "Find our more on how to use the bulkloader in the "
            + "<a href=\"" + MESSAGE_NOTHING_TO_DO_URL + "\">Sausalito Documentation</a>.";

    @Override
    protected Job getActionJob(OutputStream output) {
        Job job = new SETImportDataJob(getProject(), output);
        addJobListeners(job);
        return job;
    }

    private void addJobListeners(Job job) {
        job.addJobChangeListener(fListener);
    }

    private JobChangeAdapter fListener = new JobChangeAdapter() {
        @Override
        public void done(IJobChangeEvent event) {
            IStatus result = event.getResult();
            if (result.getSeverity() == IStatus.INFO) {
                final String message = result.getMessage();
                if (message.startsWith(SETImportDataJob.MESSAGE_NOTHING_TO_DO)) {
                    Display.getDefault().syncExec(new Runnable() {
                        public void run() {
                            URL url = null;
                            try {
                                url = new URL(MESSAGE_NOTHING_TO_DO_URL);
                            } catch (MalformedURLException e) {
                            }
                            InfoLinkMessageDialog md = new InfoLinkMessageDialog(Display.getDefault().getActiveShell(),
                                    "Sausalito CoreSDK Bulkloader", message, MESSAGE_NOTHING_TO_DO_DETAILS, url);
                            md.open();
                        }
                    });
                }
            } else if (result.getSeverity() == IStatus.OK) {
                Display.getDefault().syncExec(new Runnable() {
                    public void run() {
                        MessageDialog md = new MessageDialog(Display.getDefault().getActiveShell(),
                                "Sausalito CoreSDK Bulkloader", null, "Data succesfully imported into project: "
                                        + getProject().getName(), MessageDialog.INFORMATION,
                                new String[] { IDialogConstants.OK_LABEL }, 0);
                        md.open();
                    }
                });
            }

        }
    };
}
