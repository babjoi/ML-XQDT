package org.eclipse.wst.xquery.set.internal.ui.actions;

import java.io.OutputStream;

import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.wst.xquery.set.internal.launching.jobs.SETDeleteDataJob;

@SuppressWarnings("restriction")
public class SETDeleteDataAction extends SETCoreSDKCommandAction {

    @Override
    protected Job getActionJob(OutputStream output) {
        return new SETDeleteDataJob(getProject(), output);
    }
}
