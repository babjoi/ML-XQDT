/*******************************************************************************
 * Copyright (c) 2010 28msec Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Gabriel Petrovay (28msec) - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.xquery.set.internal.ui.handlers;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.wst.xquery.set.core.ISETCoreConstants;
import org.eclipse.wst.xquery.set.internal.launching.jobs.SETDeleteDataJob;

public class SETDeleteDataCommandHandler extends SETCoreSDKCommandHandler {

    protected String getLabel(IProject project) {
        return "Delete Data";
    }

    protected Job createHandlerJob(IProject project) {
        return new SETDeleteDataJob(project);
    }

    @Override
    protected boolean isProjectCapable(IProject project) {
        IPath path = new Path(ISETCoreConstants.PROJECT_DIRECTORY_TEST)
                .append(ISETCoreConstants.PROJECT_DIRECTORY_DATA).append(
                        ISETCoreConstants.PROJECT_DIRECTORY_COLLECTIONS);
        return project.getFolder(path).exists();
    }

}
