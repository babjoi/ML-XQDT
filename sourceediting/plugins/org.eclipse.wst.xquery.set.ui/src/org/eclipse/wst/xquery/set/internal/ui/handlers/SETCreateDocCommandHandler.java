package org.eclipse.wst.xquery.set.internal.ui.handlers;

import java.io.OutputStream;

import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.wst.xquery.set.internal.launching.jobs.SETCreateDocJob;

public class SETCreateDocCommandHandler extends SETCoreSDKCommandHandler {

    protected String getLabel() {
        return "Create Doc";
    }

    protected Job createHandlerJob(OutputStream output) {
        return new SETCreateDocJob(getProject(), output);
    }
}
