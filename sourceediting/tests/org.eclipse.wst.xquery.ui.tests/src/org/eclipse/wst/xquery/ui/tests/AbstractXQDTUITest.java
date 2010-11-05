/*******************************************************************************
 * Copyright (c) 2008-2010 Chase Technology Ltd - http://www.chasetechnology.co.uk
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Doug Satchwell (Chase Technology Ltd) - initial API and implementation
 *     Lionel Villard (IBM) - XQDT adaptation
 *******************************************************************************/

package org.eclipse.wst.xquery.ui.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Enumeration;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.wst.sse.core.StructuredModelManager;
import org.eclipse.wst.sse.core.internal.model.AbstractModelLoader;
import org.eclipse.wst.sse.core.internal.provisional.IModelManager;
import org.eclipse.wst.sse.core.internal.provisional.IStructuredModel;
import org.eclipse.wst.sse.core.internal.provisional.exceptions.ResourceAlreadyExists;
import org.eclipse.wst.sse.core.internal.provisional.exceptions.ResourceInUse;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocument;
import org.eclipse.wst.sse.ui.internal.StructuredTextViewer;
import org.eclipse.wst.xquery.sse.core.internal.model.XQueryModelLoader;
import org.eclipse.wst.xquery.sse.ui.internal.editor.XQueryStructuredTextViewerConfiguration;
import org.junit.After;
import org.junit.Before;
import org.osgi.framework.Bundle;

/**
 * Base class for XQDT UI tests
 */
@SuppressWarnings("restriction")
public abstract class AbstractXQDTUITest {
	protected static IProject fTestProject;
	protected static boolean fTestProjectInitialized;
	protected static final String PROJECT_FILES = "projectfiles";
	protected static final String TEST_PROJECT_NAME = "xquerytestfiles";

	/** Current file name being tested (if any) */
	protected String fileName;

	/** Current file being tested (if any) */
	protected IFile file;

	protected IEditorPart textEditorPart;
	protected ITextEditor editor;
	protected StructuredTextViewer sourceViewer;
	private AbstractModelLoader modelLoader;
	private IStructuredModel model;
	protected IStructuredDocument document;
	protected XQueryStructuredTextViewerConfiguration configuration;
	protected Shell shell;
	protected Composite parent;

	// Constructor

	protected AbstractXQDTUITest() {
		configuration = new XQueryStructuredTextViewerConfiguration();
		modelLoader = new XQueryModelLoader();
	}

	// Methods

	@Before
	public void setUp() throws Exception {
		getWorkspace().getRoot().delete(true, true, new NullProgressMonitor());
		setupTestProjectFiles(XQDTUITestsPlugin.PLUGIN_ID);
		fTestProject.refreshLocal(IResource.DEPTH_INFINITE, null);
	}

	@After
	public void tearDown() throws Exception {
		String projName = TEST_PROJECT_NAME;

		IProject project = ResourcesPlugin.getWorkspace().getRoot()
				.getProject(projName);
		if (project.isAccessible()) {
			project.delete(true, true, new NullProgressMonitor());
		}
		getWorkspace().getRoot().refreshLocal(2, new NullProgressMonitor());
		

		if (parent != null)
			parent.dispose();

		if (model != null) {
			model.releaseFromEdit();
		}
	}

	/**
	 * Returns the workspace instance.
	 */
	public static IWorkspace getWorkspace() {
		return ResourcesPlugin.getWorkspace();
	}

	/** Setup files in test project */
	protected void setupTestProjectFiles(String bundleId) throws CoreException,
			IOException, URISyntaxException {
		getAndCreateProject();

		Bundle coreBundle = Platform.getBundle(bundleId);
		@SuppressWarnings("unchecked")
		Enumeration<String> e = coreBundle.getEntryPaths("/projectfiles");
		while (e.hasMoreElements()) {
			String path = e.nextElement();
			URL url = coreBundle.getEntry(path);
			if (!url.getFile().endsWith("/")) {
				String relativePath = path;
				url = FileLocator.resolve(url);
				path = path.substring("projectfiles".length());
				IFile destFile = fTestProject.getFile(path);
				if (url.toExternalForm().startsWith("jar:file")) {
					InputStream source = FileLocator.openStream(coreBundle,
							new Path(relativePath), false);
					if (destFile.exists()) {
						destFile.delete(true, new NullProgressMonitor());
					}
					destFile.create(source, true, new NullProgressMonitor());
					source.close();
				} else {
					// if resource is not compressed, link
					destFile.createLink(url.toURI(), IResource.REPLACE,
							new NullProgressMonitor());
				}
			}
		}
	}

	protected static void getAndCreateProject() throws CoreException {
		IWorkspace workspace = getWorkspace();
		IWorkspaceRoot root = workspace.getRoot();
		fTestProject = root.getProject(TEST_PROJECT_NAME);
		createProject(fTestProject, null, null);
		fTestProject.refreshLocal(IResource.DEPTH_INFINITE, null);
		assertTrue(fTestProject.exists());
	}

	private static void createProject(IProject project, IPath locationPath,
			IProgressMonitor monitor) throws CoreException {
		if (monitor == null) {
			monitor = new NullProgressMonitor();
		}
		monitor.beginTask("creating test project", 10);
		// create the project
		try {
			if (!project.exists()) {
				IProjectDescription desc = project.getWorkspace()
						.newProjectDescription(project.getName());
				if (Platform.getLocation().equals(locationPath)) {
					locationPath = null;
				}
				desc.setLocation(locationPath);
				project.create(desc, monitor);
				monitor = null;
			}
			if (!project.isOpen()) {
				project.open(monitor);
				monitor = null;
			}
		} finally {
			if (monitor != null) {
				monitor.done();
			}
		}
	}
	

	/**
	 * Gets source viewer structured document
	 */
	protected IStructuredDocument getStructuredDocument() {
		return (IStructuredDocument) sourceViewer.getDocument();
	}

	/** Load given file and setup source viewer */
	protected void setUpTest(String file) throws ResourceAlreadyExists,
			ResourceInUse, IOException, CoreException {
		fileName = file;

		String filePath = TEST_PROJECT_NAME + File.separator + fileName;
		loadFileForTesting(filePath);
		IStructuredDocument document = (IStructuredDocument) sourceViewer
				.getDocument();

		assertNotNull("Missing Document Partitioner",
				document.getDocumentPartitioner());
	}

	private void loadFileForTesting(String path) throws ResourceAlreadyExists,
			ResourceInUse, IOException, CoreException {
		file = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(path));
		if (file != null && !file.exists()) {
			fail("Unable to locate " + fileName + " stylesheet.");
		}

		loadFile();
		initializeSourceViewer();
	}

	/** Load query file and open it for edit */
	private void loadFile() throws ResourceAlreadyExists, ResourceInUse,
			IOException, CoreException {
		IModelManager modelManager = StructuredModelManager.getModelManager();
		model = modelManager.getNewModelForEdit(file, true);
		document = model.getStructuredDocument();

		IDocumentPartitioner partitioner = modelLoader.getDocumentLoader()
				.getDefaultDocumentPartitioner();
		partitioner.connect(document);
		document.setDocumentPartitioner(partitioner);
	}

	private void initializeSourceViewer() {
		// some test environments might not have a "real" display
		if (Display.getCurrent() != null) {

			if (PlatformUI.isWorkbenchRunning()) {
				shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
						.getShell();
			} else {
				shell = new Shell(Display.getCurrent());
			}
			parent = new Composite(shell, SWT.NONE);

			// dummy viewer
			sourceViewer = new StructuredTextViewer(parent, null, null, false,
					SWT.NONE);
		} else {
			fail("Unable to run the test as a display must be available.");
		}

		sourceViewer.configure(configuration);
		sourceViewer.setDocument(document);
	}
}
