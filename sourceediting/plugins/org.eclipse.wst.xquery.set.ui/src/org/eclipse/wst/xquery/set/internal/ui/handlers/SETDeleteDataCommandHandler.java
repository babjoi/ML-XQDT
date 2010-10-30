package org.eclipse.wst.xquery.set.internal.ui.handlers;

import java.io.OutputStream;

import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.wst.xquery.set.internal.launching.jobs.SETImportDataJob;

public class SETDeleteDataCommandHandler extends SETCoreSDKCommandHandler {

    protected String getLabel() {
        return "Import Data";
    }

    protected Job createHandlerJob(OutputStream output) {
        return new SETImportDataJob(getProject(), output);
    }
}
