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
import org.eclipse.wst.xquery.set.internal.launching.jobs.SETCreateDocJob;

public class SETCreateDocCommandHandler extends SETCoreSDKCommandHandler {

    protected String getLabel(IProject project) {
        return "Create XQDoc";
    }

    protected Job createHandlerJob(IProject project) {
        return new SETCreateDocJob(project);
    }

}
