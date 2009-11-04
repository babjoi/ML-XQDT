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
package org.eclipse.wst.xquery.internal.launching.xqdoc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.dltk.core.environment.IExecutionEnvironment;
import org.eclipse.dltk.launching.EnvironmentVariable;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.wst.xquery.core.XQDTCorePlugin;

public class XQDocRuntime {

    private final IInterpreterInstall processor;
    private final Collection<IPath> queries;
    private IPath folder;
    private IProgressMonitor monitor;
    private String stylesheet;

    public XQDocRuntime(IInterpreterInstall processor, Collection<IPath> queries, IPath folder, String stylesheet,
            IProgressMonitor monitor) {
        this.processor = processor;
        this.queries = queries;
        this.folder = folder;
        this.monitor = monitor;
        this.stylesheet = stylesheet;
    }

    public XQDocRuntime run() throws Exception {
        String bundlePath = FileLocator.getBundleFile(XQDTCorePlugin.getDefault().getBundle()).getAbsolutePath()
                + "/Resources/xqDoc/xqDoc.xq";
        String stylesheet = FileLocator.getBundleFile(XQDTCorePlugin.getDefault().getBundle()).getAbsolutePath()
                + "/Resources/xqDoc/stylesheets/" + this.stylesheet;
        monitor.beginTask("Copy stylesheet", 1);
        FileCopy.copy(stylesheet, folder.toOSString() + "/" + this.stylesheet);
        monitor.worked(1);
        monitor.beginTask("Generating documentation", queries.size());
        for (IPath query : queries) {
            String queryFileName = query.lastSegment();
            monitor.setTaskName("Generating documentation for " + queryFileName);
            List<String> cmdLine = new ArrayList<String>();
            cmdLine.add(processor.getInstallLocation().getPath().toOSString());
            cmdLine.add("-q");
            cmdLine.add(bundlePath);
            cmdLine.add("-f");
            cmdLine.add("-e");
            cmdLine.add("query:=" + query);
            cmdLine.add("-e");
            cmdLine.add("stylesheet:=" + this.stylesheet);

            IExecutionEnvironment exeEnv = processor.getExecEnvironment();

            EnvironmentVariable[] environmentVariables = processor.getEnvironmentVariables();
            String[] vars = null;
            if (environmentVariables != null) {
                vars = new String[environmentVariables.length];
                for (int j = 0; j < environmentVariables.length; j++) {
                    vars[j] = environmentVariables[j].toString();
                }
            }
            final Process p = exeEnv.exec(cmdLine.toArray(new String[cmdLine.size()]), null, vars);
            InputStream in = p.getInputStream();
            String outputFileName = folder.toOSString() + "/"
                    + queryFileName.substring(0, queryFileName.lastIndexOf('.')) + ".html";
            File outputFile = new File(outputFileName);
            outputFile.createNewFile();
            BufferedWriter output = new BufferedWriter(new FileWriter(outputFile));
            BufferedReader pin = new BufferedReader(new InputStreamReader(in));
            String pline;
            while ((pline = pin.readLine()) != null) {
                output.write(pline);
            }
            in.close();
            pin = new BufferedReader(new InputStreamReader(p.getErrorStream()));

            while ((pline = pin.readLine()) != null) {
                System.err.println(pline);
            }
            output.close();
            p.waitFor();
            monitor.worked(1);
        }
        return this;
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
