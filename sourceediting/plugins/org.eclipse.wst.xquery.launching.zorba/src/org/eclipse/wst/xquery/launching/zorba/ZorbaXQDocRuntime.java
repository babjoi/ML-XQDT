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
package org.eclipse.wst.xquery.launching.zorba;

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
import org.eclipse.core.runtime.Path;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.environment.IExecutionEnvironment;
import org.eclipse.dltk.launching.EnvironmentVariable;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.ScriptRuntime;
import org.eclipse.wst.xquery.launching.xqdoc.AbstractXQDocRuntime;

public class ZorbaXQDocRuntime extends AbstractXQDocRuntime {

    private static final String XQDOC_RESOUCES_PATH = "Resources/xqDoc";
    private static final String XQDOC_MAIN_QUERY_PATH = "src/xqdoc.xq";
    private static final String XQDOC_STYLES_PATH = "styles";

    public ZorbaXQDocRuntime(IScriptProject project, IProgressMonitor monitor) {
        super(project, monitor);
    }

    public void generateXQDoc() throws Exception {
        IInterpreterInstall install = ScriptRuntime.getInterpreterInstall(fProject);
        if (install == null) {
            return;
        }

        File dir = new File(fFolder.toOSString());
        if (!dir.exists()) {
            dir.mkdirs();
        }

        IPath bundlePath = new Path(FileLocator.getBundleFile(ZorbaLaunchingPlugin.getDefault().getBundle())
                .getAbsolutePath());
        IPath xqdocResourcesPath = bundlePath.append(XQDOC_RESOUCES_PATH);

        IPath mainQueryPath = xqdocResourcesPath.append(XQDOC_MAIN_QUERY_PATH);
        IPath stylesheetPath = xqdocResourcesPath.append(XQDOC_STYLES_PATH).append(this.fStylesheet);

        fMonitor.beginTask("Copy stylesheet", 1);
        FileCopy.copy(stylesheetPath.toOSString(), fFolder.toOSString() + "/" + this.fStylesheet);
        fMonitor.worked(1);

        fMonitor.beginTask("Generating documentation", fQueries.size());
        for (IPath query : fQueries) {
            String queryFileName = query.lastSegment();
            fMonitor.setTaskName("Generating documentation for " + queryFileName);
            List<String> cmdLine = new ArrayList<String>();
            cmdLine.add(install.getInstallLocation().getPath().toOSString());
            cmdLine.add("-q");
            cmdLine.add(mainQueryPath.toOSString());
            cmdLine.add("-f");
            cmdLine.add("-e");
            cmdLine.add("modulePath:=" + query.toOSString());
            cmdLine.add("-e");
            cmdLine.add("stylesheet:=" + this.fStylesheet);

            IExecutionEnvironment exeEnv = install.getExecEnvironment();

            EnvironmentVariable[] environmentVariables = install.getEnvironmentVariables();
            String[] vars = null;
            if (environmentVariables != null && environmentVariables.length > 0) {
                vars = new String[environmentVariables.length];
                for (int j = 0; j < environmentVariables.length; j++) {
                    vars[j] = environmentVariables[j].toString();
                }
            }
            final Process p = exeEnv.exec(cmdLine.toArray(new String[cmdLine.size()]), null, vars);
            InputStream in = p.getInputStream();
            String outputFileName = fFolder.toOSString() + "/"
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
            fMonitor.worked(1);
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

    public void init(Collection<IPath> queries, IPath destination, String stylesheet) {
        for (IPath path : queries) {
            fQueries.add(path);
        }
        fFolder = destination;
        fStylesheet = stylesheet;
    }
}
