package org.eclipse.wst.xquery.set.internal.ui.handlers;

import java.io.OutputStream;

import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.wst.xquery.set.internal.launching.jobs.SETDeleteDataJob;

public class SETImportDataCommandHandler extends SETCoreSDKCommandHandler {

    protected String getLabel() {
        return "Delete Data";
    }

    protected Job createHandlerJob(OutputStream output) {
        return new SETDeleteDataJob(getProject(), output);
    }
}
