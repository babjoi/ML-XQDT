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

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;

public class SETDeleteDataJob extends SETCoreSDKCommandJob {

    public SETDeleteDataJob(IProject project, OutputStream output) {
        super("Deleting project data", project, output);
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

    @Override
    protected String getJobTaskName() {
        return "Deleting data for project: " + fProject.getName();
    }

}
