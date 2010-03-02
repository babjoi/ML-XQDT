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

import org.eclipse.wst.xquery.set.launching.deploy.DeployInfo;

abstract public class SETCoreSDKDeployCommandJob extends SETCoreSDKCommandJob {

    protected static enum DeployType {
        DEPLOY_PROJECT("project"), DEPLOY_DATA("data");

        private String fType;

        private DeployType(String type) {
            fType = type;
        }

        @Override
        public String toString() {
            return fType;
        }
    }

    protected DeployInfo fInfo;

    public SETCoreSDKDeployCommandJob(String name, DeployInfo info, OutputStream output) {
        super(name, info.getProject().getProject(), output);
        fInfo = info;
    }

    protected List<String> getCommandParameters() {
        List<String> params = new ArrayList<String>();
        params.add("deploy");
        params.add(getDeployType().toString());
        params.add("-d");
        params.add(fProject.getLocation().toOSString());
        params.add("-a");
        params.add(fInfo.getApplicationName());
        params.add("-us");
        params.add(fInfo.getUserName());
        params.add("-pw");
        params.add(fInfo.getPassword());
        String host = fInfo.getHost().trim();
        if (host != null && !host.equals("")) {
            params.add("-s");
            params.add(host);
        }

        return params;
    }

    abstract protected DeployType getDeployType();

    protected boolean useConsole() {
        return false;
    }

    public void setDeployInfo(DeployInfo info) {
        fInfo = info;
    }

}
