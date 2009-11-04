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
package org.eclipse.wst.xquery.set.internal.ui.wizards;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.wst.xquery.set.internal.ui.preferences.PreferenceConstants;
import org.eclipse.wst.xquery.set.launching.CoreSdkUtil;
import org.eclipse.wst.xquery.set.ui.SETUIPlugin;

public class CoreSDKTemplateUtility {

    private static final String[] EMPTY_STRING_LIST = new String[0];

    private static final class ProjectContentCreator {

        private IProject fProject;
        private Map<String, String> fProperties;

        private static IPreferenceStore store = SETUIPlugin.getDefault().getPreferenceStore();

        /**
         * This constructor is used when a project is imported from the local file system.
         * 
         * @param project
         *            - the project in which the contents must be copied
         */
        public ProjectContentCreator(IProject project) {
            this(project, null);
        }

        /**
         * This constructor is used when a project needs to be created and the project logical URI
         * adjusted.
         * 
         * @param project
         *            - the project in which the contents must be copied
         * @param projectUri
         *            - the URI that will be set as the logical project URI
         */
        public ProjectContentCreator(IProject project, URI projectUri) {
            fProject = project;
            fProperties = new HashMap<String, String>();
            if (projectUri != null) {
                fProperties.put(PreferenceConstants.TEMPLATES_CONFIG_VAR_PROJECT_URI, projectUri.toString());
                fProperties.put(PreferenceConstants.TEMPLATES_MODULES_VAR_DEFAULT_NAMESPACE, projectUri.resolve(
                        "default").toString());
                fProperties.put(PreferenceConstants.TEMPLATES_MODULES_VAR_ERROR_NAMESPACE, projectUri.resolve(
                        "lib/error").toString());

            }
        }

        /**
         * This function copies a template from the templates directory to the destination
         * container.
         */
        private void createContentFromTemplate(String templateName, IProgressMonitor monitor) throws CoreException {
            monitor = (monitor == null ? new NullProgressMonitor() : monitor);

            monitor.beginTask("Creating project content from template", IProgressMonitor.UNKNOWN);
            try {
                IPath templatePath = getProjectCoreSDKTemplatePath(fProject).append(templateName);

                // copy first the static template content
                copyDirectoryStructure(templatePath, monitor);

                // only for the new project (the default template) we have to configure the files
                if (templateName.equals(store.getString(PreferenceConstants.TEMPLATES_DEFAULT_PROJECT_DIR))) {
                    configureFiles();
                }
            } finally {
                monitor.done();
            }
        }

        private void createContentFromPath(IPath importLocation, IProgressMonitor monitor) throws CoreException {
            monitor = (monitor == null ? new NullProgressMonitor() : monitor);
            copyDirectoryStructure(importLocation, monitor);
        }

        private void copyDirectoryStructure(IPath dirPath, IProgressMonitor monitor) throws CoreException {
            // copy the template recursively in the new project

            // if we are in development mode or if the plug-in is shipped unpacked
            // the template URL is pointing to a file system directory
            //			if ("file".equals(templateUrl.getProtocol())) { //$NON-NLS-1$
            File directory = dirPath.toFile();
            if (!directory.exists()) {
                return;
            }

            // generateFiles() is a recursive function
            // so better create the monitor for sub this task now
            int ticks = (int)getFileSize(directory) / 1000;

            monitor.beginTask("", ticks);

            SubProgressMonitor sm = new SubProgressMonitor(monitor, ticks);
            sm.beginTask("", ticks);

            try {
                generateFiles(directory, fProject, sm);
            } finally {
                sm.done();
                monitor.done();
            }

            // else we if the plug-in is shipped as a jar file the template
            // URL is pointing to an entry in this jar (ZIP) archive
            //			} else if ("jar".equals(templateUrl.getProtocol())) { //$NON-NLS-1$
            // String file = templateUrl.getFile();
            // int exclamation = file.indexOf('!');
            // if (exclamation < 0)
            // return;
            // URL fileUrl = null;
            // try {
            // fileUrl = new URL(file.substring(0, exclamation));
            // } catch (MalformedURLException mue) {
            // return;
            // }
            // File pluginJar = new File(fileUrl.getFile());
            // if (!pluginJar.exists())
            // return;
            // String templateDirectory = file.substring(exclamation + 1); // "/some/path/"
            // IPath path = new Path(templateDirectory);
            // ZipFile zipFile = null;
            // try {
            // zipFile = new ZipFile(pluginJar);
            // generateFiles(zipFile, path, fProject, null);
            // } catch (ZipException ze) {
            // } catch (IOException ioe) {
            // } finally {
            // if (zipFile != null) {
            // try {
            // zipFile.close();
            // } catch (IOException e) {
            // }
            // }
            // }
            // }
        }

        private void configureFiles() throws CoreException {
            String templateRoot = store.getString(PreferenceConstants.TEMPLATES_ROOT_DIR);
            String moduleDir = store.getString(PreferenceConstants.TEMPLATES_MODULES_DIR);
            String configDir = store.getString(PreferenceConstants.TEMPLATES_CONFIG_DIR);

            String configFilePath = templateRoot + "/" + configDir + "/"
                    + store.getString(PreferenceConstants.TEMPLATES_CONFIG_SAUSALITO_XML);
            String defaultModulePath = templateRoot + "/" + moduleDir + "/"
                    + store.getString(PreferenceConstants.TEMPLATES_MODULES_DEFAULT_MODULE_HANDLER);
            String errorLibPath = templateRoot + "/" + moduleDir + "/"
                    + store.getString(PreferenceConstants.TEMPLATES_MODULES_DEFAULT_ERROR_LIBRARY);

            String[] srcFiles = new String[] { configFilePath, defaultModulePath, errorLibPath };

            String[] dstFiles = new String[] { "/.config/sausalito.xml", "/handlers/default.xq", "/lib/error.xq" };

            IPath installPath = CoreSdkUtil.getProjectCoreSDKInstallationPath(fProject);

            for (int i = 0; i < srcFiles.length; i++) {
                IPath modulePath = installPath.append(srcFiles[i]);

                //				if ("file".equals(moduleUrl.getProtocol())) { //$NON-NLS-1$
                File moduleFile = modulePath.toFile();
                if (!moduleFile.exists()) {
                    return;
                }

                InputStream in = null;
                try {
                    in = getProcessedStream(new FileInputStream(moduleFile));
                    copyFile(dstFiles[i], in, fProject, null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //				} else if ("jar".equals(moduleUrl.getProtocol())) { //$NON-NLS-1$
                // String file = moduleUrl.getFile();
                // int exclamation = file.indexOf('!');
                // if (exclamation < 0)
                // return;
                // URL fileUrl = null;
                // try {
                // fileUrl = new URL(file.substring(0, exclamation));
                // } catch (MalformedURLException mue) {
                // return;
                // }
                //		
                // File pluginJar = new File(fileUrl.getFile());
                // if (!pluginJar.exists())
                // return;
                // String moduleFile = file.substring(exclamation + 1); // "/some/path/"
                // ZipFile zipFile = null;
                // try {
                // zipFile = new ZipFile(pluginJar);
                // ZipEntry zipEntry = zipFile.getEntry(moduleFile);
                // InputStream in = zipFile.getInputStream(zipEntry);
                // copyFile(new Path(zipEntry.getName()).lastSegment().toString(), in, fProject,
                // null);
                // } catch (ZipException ze) {
                // } catch (IOException ioe) {
                // } finally {
                // if (zipFile != null) {
                // try {
                // zipFile.close();
                // } catch (IOException e) {
                // }
                // }
                // }
                // }
            }
        }

        private void generateFiles(File src, IContainer dst, IProgressMonitor monitor) throws CoreException {
            File[] members = src.listFiles();

            for (int i = 0; i < members.length; i++) {
                File member = members[i];
                if (member.isDirectory()) {
                    String folderName = member.getName();
                    if (folderName.equals(".svn")) {
                        continue;
                    }
                    member.length();
                    IContainer dstContainer = dst.getFolder(new Path(folderName));

                    if (monitor.isCanceled()) {
                        throw new OperationCanceledException();
                    }

                    monitor.setTaskName("Copying directory: " + member.getPath());
                    if (dstContainer instanceof IFolder && !dstContainer.exists()) {
                        ((IFolder)dstContainer).create(true, true, null);
                    }
                    generateFiles(member, dstContainer, monitor);
                } else {
                    InputStream in = null;
                    try {
                        in = new FileInputStream(member);
                        int workTicks = (int)member.length() / 1000;
                        copyFile(member.getName(), in, dst, null);
                        monitor.worked(workTicks);
                    } catch (IOException ioe) {
                    } finally {
                        if (in != null) {
                            try {
                                in.close();
                            } catch (IOException ioe2) {
                            }
                        }
                    }
                }
            }
        }

        @SuppressWarnings( { "unchecked", "unused" })
        private static void generateFiles(ZipFile zipFile, IPath path, IContainer dst, IProgressMonitor monitor)
                throws CoreException {
            int pathLength = path.segmentCount();
            // Immidiate children
            Map<String, ZipEntry> childZipEntries = new HashMap<String, ZipEntry>(); // "dir/" or
            // "dir/file.java"

            for (Enumeration<ZipEntry> zipEntries = (Enumeration<ZipEntry>)zipFile.entries(); zipEntries
                    .hasMoreElements();) {
                ZipEntry zipEntry = zipEntries.nextElement();
                IPath entryPath = new Path(zipEntry.getName());
                if (entryPath.segmentCount() <= pathLength) {
                    // ancestor or current directory
                    continue;
                }
                if (!path.isPrefixOf(entryPath)) {
                    // not a descendant
                    continue;
                }
                if (entryPath.segmentCount() == pathLength + 1) {
                    childZipEntries.put(zipEntry.getName(), zipEntry);
                } else {
                    String name = entryPath.uptoSegment(pathLength + 1).addTrailingSeparator().toString();
                    if (!childZipEntries.containsKey(name)) {
                        ZipEntry dirEntry = new ZipEntry(name);
                        childZipEntries.put(name, dirEntry);
                    }
                }
            }

            for (Iterator<ZipEntry> it = childZipEntries.values().iterator(); it.hasNext();) {
                ZipEntry zipEnry = it.next();
                String name = new Path(zipEnry.getName()).lastSegment().toString();
                if (zipEnry.isDirectory()) {
                    IContainer dstContainer = null;

                    if (dstContainer == null) {
                        dstContainer = dst.getFolder(new Path(name));
                    }
                    if (dstContainer instanceof IFolder && !dstContainer.exists()) {
                        ((IFolder)dstContainer).create(true, true, monitor);
                    }
                    generateFiles(zipFile, path.append(name), dstContainer, monitor);
                } else {
                    InputStream in = null;
                    try {
                        in = zipFile.getInputStream(zipEnry);
                        copyFile(name, in, dst, monitor);
                    } catch (IOException ioe) {
                    } finally {
                        if (in != null) {
                            try {
                                in.close();
                            } catch (IOException ioe2) {
                            }
                        }
                    }
                }
            }
        }

        private InputStream getProcessedStream(InputStream input) throws IOException, CoreException {
            StringBuffer sb = new StringBuffer();
            String endl = "\n";
            if (Platform.getOS().equals(Platform.OS_WIN32)) {
                endl = "\r\n";
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            String line = reader.readLine();
            while (line != null) {
                while (line.matches(".*\\$\\{.+\\}\\$.*")) {
                    int start = line.indexOf("${");
                    String var = line.substring(start + 2, line.indexOf("}$", start + 2));
                    String repl = fProperties.get(var);
                    line = line.replace("${" + var + "}$", repl == null ? "" : repl);
                }

                sb.append(line + endl);
                line = reader.readLine();
            }

            return new ByteArrayInputStream(sb.toString().getBytes("UTF-8"));
        }

    }

    /**
     * This function returns the list of project templates found in the location provided by the
     * <code>org.eclipse.wst.xquery.set.internal.ui.preferences.PreferenceConstants.TEMPLATES_PROJECTS_DIR</code>.
     * 
     * The list does not include the default template name which is provided by the
     * <code>org.eclipse.wst.xquery.set.internal.ui.preferences.PreferenceConstants.TEMPLATES_DEFAULT_PROJECT_DIR</code>
     * .
     * 
     * @throws CoreException
     */
    public static String[] getTemplateProjectNames() throws CoreException {
        List<String> templateList = new ArrayList<String>(4);

        IPath templatePath = getDefaultCoreSDKTemplatePath();
        if (templatePath == null) {
            return EMPTY_STRING_LIST;
        }

        IPreferenceStore store = SETUIPlugin.getDefault().getPreferenceStore();
        final String defaultTemplate = store.getString(PreferenceConstants.TEMPLATES_DEFAULT_PROJECT_DIR);

        File templateDirectory = new File(templatePath.toOSString());
        if (!templateDirectory.exists()) {
            return EMPTY_STRING_LIST;
        }

        File[] members = templateDirectory.listFiles(new FileFilter() {

            public boolean accept(File dir) {
                String dirName = dir.getName();
                return dir.isDirectory() && !dirName.equals(defaultTemplate) && !dirName.equals(".svn");
            }
        });
        templateList = new ArrayList<String>();

        for (File file : members) {
            templateList.add(file.getName());
        }
        return templateList.toArray(new String[templateList.size()]);
    }

    public static String[] getTemplateProjectNames(IInterpreterInstall interpreter) throws CoreException {
        List<String> templateList = new ArrayList<String>(4);

        IPath templatePath = getCoreSDKTemplatePath(interpreter);

        IPreferenceStore store = SETUIPlugin.getDefault().getPreferenceStore();
        final String defaultTemplate = store.getString(PreferenceConstants.TEMPLATES_DEFAULT_PROJECT_DIR);

        File templateDirectory = new File(templatePath.toOSString());
        if (!templateDirectory.exists()) {
            return EMPTY_STRING_LIST;
        }

        File[] members = templateDirectory.listFiles(new FileFilter() {

            public boolean accept(File dir) {
                String dirName = dir.getName();
                return dir.isDirectory() && !dirName.equals(defaultTemplate) && !dirName.equals(".svn");
            }
        });
        templateList = new ArrayList<String>();

        for (File file : members) {
            templateList.add(file.getName());
        }

        return templateList.toArray(new String[templateList.size()]);
    }

    public static void createProjectContent(IProject currProject, String templateName, String projectUriString)
            throws CoreException {
        URI projectURI = null;
        try {
            projectURI = new URI(projectUriString);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        ProjectContentCreator creator = new ProjectContentCreator(currProject, projectURI);
        creator.createContentFromTemplate(templateName, null);

    }

    public static void createProjectContent(IProject currProject, IPath path, IProgressMonitor monitor)
            throws CoreException {
        ProjectContentCreator creator = new ProjectContentCreator(currProject);
        creator.createContentFromPath(path, monitor);
    }

    private static void copyFile(String fileName, InputStream input, IContainer dst, IProgressMonitor monitor)
            throws CoreException {
        IFile dstFile = dst.getFile(new Path(fileName));

        try {
            if (dstFile.exists()) {
                dstFile.setContents(input, true, true, monitor);
            } else {
                dstFile.create(input, true, monitor);
            }
            input.close();

        } catch (IOException e) {
        }
    }

    @SuppressWarnings( { "unchecked", "unused" })
    private static Map<String, ZipEntry> getChildEntriesFromZip(IPath path, ZipFile zipFile) {
        int pathLength = path.segmentCount();
        Map<String, ZipEntry> childZipEntries = new HashMap<String, ZipEntry>();
        for (Enumeration<ZipEntry> zipEntries = (Enumeration<ZipEntry>)zipFile.entries(); zipEntries.hasMoreElements();) {
            ZipEntry zipEntry = zipEntries.nextElement();
            IPath entryPath = new Path(zipEntry.getName());
            if (entryPath.segmentCount() <= pathLength) {
                // ancestor or current directory
                continue;
            }
            if (!path.isPrefixOf(entryPath)) {
                // not a descendant
                continue;
            }
            if (entryPath.segmentCount() == pathLength + 1) {
                childZipEntries.put(zipEntry.getName(), zipEntry);
            } else {
                String name = entryPath.uptoSegment(pathLength + 1).addTrailingSeparator().toString();
                if (!childZipEntries.containsKey(name)) {
                    ZipEntry dirEntry = new ZipEntry(name);
                    childZipEntries.put(name, dirEntry);
                }
            }
        }
        return childZipEntries;
    }

    // private static URL getResolvedTemplateLocationUrl() throws CoreException {
    // return getResolvedTemplateUrl("");
    // }

    private static IPath getProjectCoreSDKTemplatePath(IProject project) throws CoreException {
        IPreferenceStore store = SETUIPlugin.getDefault().getPreferenceStore();

        IPath coresdkPath = CoreSdkUtil.getProjectCoreSDKInstallationPath(project);
        IPath templatePath = coresdkPath.append(store.getString(PreferenceConstants.TEMPLATES_ROOT_DIR)).append(
                store.getString(PreferenceConstants.TEMPLATES_PROJECTS_DIR));

        return templatePath;
    }

    private static IPath getDefaultCoreSDKTemplatePath() throws CoreException {
        IPreferenceStore store = SETUIPlugin.getDefault().getPreferenceStore();

        IPath coresdkPath = CoreSdkUtil.getDefaultCoreSDKInstallationPath();
        if (coresdkPath == null) {
            return null;
        }
        IPath templatePath = coresdkPath.append(store.getString(PreferenceConstants.TEMPLATES_ROOT_DIR)).append(
                store.getString(PreferenceConstants.TEMPLATES_PROJECTS_DIR));

        return templatePath;
    }

    private static IPath getCoreSDKTemplatePath(IInterpreterInstall interpreter) throws CoreException {
        IPreferenceStore store = SETUIPlugin.getDefault().getPreferenceStore();

        IPath coresdkPath = interpreter.getInstallLocation().getPath().removeLastSegments(2);
        IPath templatePath = coresdkPath.append(store.getString(PreferenceConstants.TEMPLATES_ROOT_DIR)).append(
                store.getString(PreferenceConstants.TEMPLATES_PROJECTS_DIR));

        return templatePath;
    }

    private static long getFileSize(File folder) {
        if (folder.isFile()) {
            return folder.length();
        }

        long foldersize = 0;
        File[] filelist = folder.listFiles();
        for (int i = 0; i < filelist.length; i++) {
            foldersize += getFileSize(filelist[i]);
        }
        return foldersize;
    }
}
