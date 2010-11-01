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
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.wst.xquery.set.core.ISETCoreConstants;
import org.eclipse.wst.xquery.set.internal.launching.jobs.SETImportDataJob;

public class SETImportDataCommandHandler extends SETCoreSDKCommandHandler {

    protected String getLabel(IProject project) {
        return "Import Data";
    }

    protected Job createHandlerJob(IProject project) {
        return new SETImportDataJob(project);
    }

    @Override
    protected boolean isProjectCapable(IProject project) {
        return project.getFolder(ISETCoreConstants.PROJECT_DIRECTORY_BULKLOAD).exists();
    }

}
