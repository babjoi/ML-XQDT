/*******************************************************************************
 * Copyright (c) 2008, 2009 28msec Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     William Candillon (28msec) - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.xquery.launching.xqdoc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IBuildpathEntry;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.wst.xquery.core.utils.PathUtil;

public abstract class AbstractXQDocRuntime {

    protected IScriptProject fProject;
    protected IProgressMonitor fMonitor;

    protected Collection<IPath> fQueries = new ArrayList<IPath>();
    protected IPath fFolder;
    protected String fStylesheet;

    public AbstractXQDocRuntime(IScriptProject projet, IProgressMonitor monitor) {
        fProject = projet;
        fMonitor = monitor;
    }

    public void init(Collection<IPath> queries, IPath destination, String stylesheet) {
        for (IPath path : queries) {
            fQueries.add(path);
        }
        fFolder = destination;
        fStylesheet = stylesheet;
    }

    public void run() throws Exception {

        // delegate the generation of XQDoc files to the concrete classes
        generateXQDoc();

        // exclude the destination folder from the buildpath
        excludeDestinationFromBuildPath();

        fProject.getProject().refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
    }

    protected abstract void generateXQDoc() throws Exception;

    private void excludeDestinationFromBuildPath() {
        IPath relDir = PathUtil.makePathRelativeTo(fFolder, fProject.getProject().getLocation());
        IPath absDir = fProject.getPath().append(relDir);

        try {
            IBuildpathEntry[] originalPath = fProject.getRawBuildpath();
            List<IBuildpathEntry> newEntries = new ArrayList<IBuildpathEntry>();
            for (IBuildpathEntry entry : originalPath) {
                if (entry.getPath().isPrefixOf(absDir)) {
                    IPath[] excls = entry.getExclusionPatterns();
                    List<IPath> newExcls = new ArrayList<IPath>(excls.length);

                    IPath newEx = PathUtil.makePathRelativeTo(relDir, entry.getPath()).addTrailingSeparator();
                    boolean alreadyExcluded = false;

                    for (IPath path : excls) {
                        if (newEx.equals(path)) {
                            alreadyExcluded = true;
                        }
                        newExcls.add(path);
                    }
                    if (!alreadyExcluded) {
                        newExcls.add(newEx);
                    }

                    IBuildpathEntry newEntry = DLTKCore.newSourceEntry(entry.getPath(), newExcls
                            .toArray(new IPath[newExcls.size()]));
                    newEntries.add(newEntry);
                } else {
                    newEntries.add(entry);
                }
            }
            IBuildpathEntry[] newPath = new IBuildpathEntry[newEntries.size()];
            newEntries.toArray(newPath);
            fProject.setRawBuildpath(newPath, new NullProgressMonitor());
        } catch (ModelException e) {
        }
    }

    public static class FileCopy {

        public static void copy(String fromFileName, String toFileName) throws IOException {
            File fromFile = new File(fromFileName);
            File toFile = new File(toFileName);

            if (!fromFile.exists()) {
                throw new IOException("FileCopy: " + "no such source file: " + fromFileName);
            }
            if (!fromFile.isFile()) {
                throw new IOException("FileCopy: " + "can't copy directory: " + fromFileName);
            }
            if (!fromFile.canRead()) {
                throw new IOException("FileCopy: " + "source file is unreadable: " + fromFileName);
            }

            if (toFile.isDirectory()) {
                toFile = new File(toFile, fromFile.getName());
            }

            if (toFile.exists()) {
                if (!toFile.canWrite()) {
                    throw new IOException("FileCopy: " + "destination file is unwriteable: " + toFileName);
                }
            } else {
                String parent = toFile.getParent();
                if (parent == null) {
                    parent = System.getProperty("user.dir");
                }
                File dir = new File(parent);
                if (!dir.exists()) {
                    throw new IOException("FileCopy: " + "destination directory doesn't exist: " + parent);
                }
                if (dir.isFile()) {
                    throw new IOException("FileCopy: " + "destination is not a directory: " + parent);
                }
                if (!dir.canWrite()) {
                    throw new IOException("FileCopy: " + "destination directory is unwriteable: " + parent);
                }
            }

            FileInputStream from = null;
            FileOutputStream to = null;
            try {
                from = new FileInputStream(fromFile);
                to = new FileOutputStream(toFile);
                byte[] buffer = new byte[4096];
                int bytesRead;

                while ((bytesRead = from.read(buffer)) != -1) {
                    to.write(buffer, 0, bytesRead); // write
                }
            } finally {
                if (from != null) {
                    try {
                        from.close();
                    } catch (IOException e) {
                        ;
                    }
                }
                if (to != null) {
                    try {
                        to.close();
                    } catch (IOException e) {
                        ;
                    }
                }
            }
        }
    }

}
