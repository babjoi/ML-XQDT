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

import org.eclipse.wst.xquery.set.launching.deploy.DeployInfo;

public class SETDeployDataJob extends AbstractSETCoreSDKDeployCommandJob {

    public SETDeployDataJob(DeployInfo info, OutputStream output) {
        super("Deploying data for project: \"" + info.getProject().getElementName() + "\"...", info, output);
    }

    @Override
    protected String getJobTaskName() {
        return "Deploying data for project: " + fProject.getName();
    }

    protected DeployType getDeployType() {
        return DeployType.DEPLOY_DATA;
    }
}
