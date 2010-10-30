/*******************************************************************************
 * Copyright (c) 2010 28msec Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     William Candillon (28msec) - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.xquery.set.internal.launching.jobs;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;

public class SETCreateDocJob extends SETCoreSDKCommandJob {

    public SETCreateDocJob(IProject project, OutputStream output) {
        super("Creating XQDoc for project: \"" + project.getName() + "\"...", project, output);
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

    @Override
    protected IStatus run(IProgressMonitor monitor) {
        IStatus status = super.run(monitor);
        try {
            fProject.refreshLocal(IResource.DEPTH_INFINITE, monitor);
        } catch (CoreException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return status;
    }
}
