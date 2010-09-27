package org.eclipse.wst.xquery.set.internal.ui.actions;

import java.io.OutputStream;

import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.wst.xquery.set.internal.core.ISETConstants;
import org.eclipse.wst.xquery.set.internal.launching.jobs.SETDeleteDataJob;

public class SETDeleteDataAction extends SETCoreSDKCommandAction {

    protected Job createActionJob(OutputStream output) {
        return new SETDeleteDataJob(getProject(), output);
    }

    protected String getActionLabel() {
        return ISETConstants.SAUSALITO_COMMAND_DELETE_DATA;
    }

}
