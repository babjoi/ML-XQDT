package org.eclipse.wst.xquery.set.internal.launching.jobs;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;

public class SETCreateDocJob extends SETCoreSDKCommandJob {

    public SETCreateDocJob(String name, IProject project, OutputStream output) {
        super(name, project, output);
    }

    @Override
    protected List<String> getCommandParameters() {
        List<String> params = new ArrayList<String>();
        params.add("create");
        params.add("doc");
        params.add("-d");
        params.add(fProject.getLocation().toOSString());
        params.add("-n");
        params.add(fProject.getName());
        params.add("-f");
        return params;
    }

}
