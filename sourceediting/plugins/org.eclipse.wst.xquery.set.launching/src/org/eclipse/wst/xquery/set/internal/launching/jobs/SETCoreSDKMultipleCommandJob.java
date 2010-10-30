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
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.IJobChangeListener;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.wst.xquery.set.launching.deploy.DeployInfo;

public class SETCoreSDKMultipleCommandJob extends Job {

    private DeployInfo fDeployInfo;
    private IStatus fStatus;
    private List<Job> fJobs = new ArrayList<Job>(1);

    public SETCoreSDKMultipleCommandJob(DeployInfo info, OutputStream output) {
        super("");
        fDeployInfo = info;
        initJobs(output);
        setSystem(true);
    }

    public void initJobs(OutputStream output) {
        switch (fDeployInfo.getDeployType()) {
        case PROJECT:
            fJobs.add(new SETDeployProjectJob(fDeployInfo, output));
            break;
        case DATA:
            fJobs.add(new SETDeployDataJob(fDeployInfo, output));
            break;
        case PROJECT_AND_DATA:
            fJobs.add(new SETDeployProjectJob(fDeployInfo, output));
            fJobs.add(new SETDeployDataJob(fDeployInfo, output));
            break;
        }
    }

    public void addJobChangeListenerToChildren(IJobChangeListener listener) {
        for (Job job : fJobs) {
            if (listener != null) {
                job.addJobChangeListener(listener);
            }
        }
    }

    public DeployInfo getDeployInfo() {
        return fDeployInfo;
    }

    public void setDeployInfo(DeployInfo info) {
        fDeployInfo = info;
        for (Job job : fJobs) {
            if (job instanceof AbstractSETCoreSDKDeployCommandJob) {
                ((AbstractSETCoreSDKDeployCommandJob)job).setDeployInfo(fDeployInfo);
            }
        }
    }

    @Override
    protected IStatus run(IProgressMonitor monitor) {
        final Iterator<Job> iterator = fJobs.iterator();

        IJobChangeListener trigger = new JobChangeAdapter() {
            public void done(IJobChangeEvent event) {
                fStatus = event.getResult();
                synchronized (SETCoreSDKMultipleCommandJob.this) {
                    SETCoreSDKMultipleCommandJob.this.notify();
                }
            }
        };

        while (iterator.hasNext()) {
            Job currentJob = iterator.next();
            currentJob.addJobChangeListener(trigger);
            currentJob.schedule();

            setName(currentJob.getName());

            waitForNextJob();
            if (!fStatus.isOK()) {
                break;
            }
        }

        return Status.OK_STATUS;
    }

    synchronized private void waitForNextJob() {
        try {
            wait();
        } catch (InterruptedException e) {
        }
    }

}
