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
package org.eclipse.wst.xquery.set.internal.launching.jobs;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.wst.xquery.set.core.ISETCoreConstants;

public class SETDeleteDataJob extends AbstractSETCoreSDKCommandJob {

    public SETDeleteDataJob(IProject project) {
        super("Deleting data from project: \"" + project.getName() + "\"...", project);
    }

    protected List<String> getCommandParameters() {
        List<String> params = new ArrayList<String>();
        params.add("delete");
        params.add("data");
        params.add("-f");
        params.add("-d");
        params.add(getProject().getLocation().toOSString());

        return params;
    }

    protected String getCommandConsleLabel() {
        return "Delete data";
    }

    @Override
    protected String getJobTaskName() {
        return "Deleting data for project: " + fProject.getName();
    }

    @Override
    protected String getWarningLinePrefix() {
        return "No data available";
    }

    @Override
    protected boolean needsResourceRefresh() {
        return true;
    }

    @Override
    protected void refresh(IProgressMonitor monitor) {
        IPath path = new org.eclipse.core.runtime.Path(ISETCoreConstants.PROJECT_DIRECTORY_TEST);
        try {
            fProject.getFolder(path).refreshLocal(IResource.DEPTH_INFINITE, monitor);
        } catch (CoreException e) {
            // nothing to do
        }
    }
}
