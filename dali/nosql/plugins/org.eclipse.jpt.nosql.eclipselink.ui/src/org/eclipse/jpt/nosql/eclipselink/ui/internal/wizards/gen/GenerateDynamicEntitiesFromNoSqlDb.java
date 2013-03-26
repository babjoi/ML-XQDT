/*******************************************************************************
 * Copyright (c) 2012, 2013 Oracle. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0, which accompanies this distribution
 * and is available at http://www.eclipse.org/legal/epl-v10.html.
 * 
 * Contributors:
 *     Oracle - initial API and implementation
 ******************************************************************************/
package org.eclipse.jpt.nosql.eclipselink.ui.internal.wizards.gen;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.UnknownHostException;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceRuleFactory;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.core.runtime.content.IContentDescription;
import org.eclipse.core.runtime.content.IContentType;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.resource.ResourceManager;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jpt.common.core.internal.utility.JDTTools;
import org.eclipse.jpt.common.core.internal.utility.PlatformTools;
import org.eclipse.jpt.common.core.resource.ProjectResourceLocator;
import org.eclipse.jpt.common.core.resource.xml.JptXmlResource;
import org.eclipse.jpt.common.ui.internal.dialogs.OptionalMessageDialog;
import org.eclipse.jpt.common.ui.internal.util.SWTUtil;
import org.eclipse.jpt.common.utility.internal.StringTools;
import org.eclipse.jpt.jpa.core.EntityGeneratorDatabaseAnnotationNameBuilder;
import org.eclipse.jpt.jpa.core.JpaPlatform;
import org.eclipse.jpt.jpa.core.JpaPreferences;
import org.eclipse.jpt.jpa.core.JpaProject;
import org.eclipse.jpt.jpa.db.Column;
import org.eclipse.jpt.jpa.db.ForeignKey;
import org.eclipse.jpt.jpa.db.Schema;
import org.eclipse.jpt.jpa.db.Table;
import org.eclipse.jpt.jpa.gen.internal.BaseEntityGenCustomizer;
import org.eclipse.jpt.jpa.gen.internal.DatabaseAnnotationNameBuilder;
import org.eclipse.jpt.jpa.gen.internal.ORMGenCustomizer;
import org.eclipse.jpt.jpa.gen.internal.ORMGenTable;
import org.eclipse.jpt.jpa.ui.JpaWorkbench;
import org.eclipse.jpt.jpa.ui.JptJpaUiMessages;
import org.eclipse.jpt.nosql.eclipselink.ui.internal.plugin.JptNoSQLEclipseLinkUiPlugin;
import org.eclipse.osgi.util.NLS;
import org.eclipse.persistence.tools.gen.nosql.mongo.MongoEntityGenerator;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.wst.sse.core.internal.exceptions.MalformedInputExceptionWithDetail;
import org.eclipse.wst.sse.core.internal.format.IStructuredFormatProcessor;
import org.eclipse.wst.sse.ui.internal.FormatProcessorsExtensionReader;
import org.eclipse.wst.sse.ui.internal.SSEUIMessages;

public class GenerateDynamicEntitiesFromNoSqlDb
	extends Wizard
	implements INewWizard
{	 
	protected JpaProject jpaProject;

	protected IStructuredSelection selection;

	private ORMGenCustomizer customizer = null;	

	private PromptEclipseLinkProjectWizardPage projectPage;	
	private NoSqlConnectionConfigPage noSqlConnectionConfigPage;
	private CollectionSelectorWizardPage collectionSelectorWizardPage;
	protected DynamicDefaultTableGenerationWizardPage dynamicDefaultTableGenerationPage;
	
	protected final ResourceManager resourceManager;

	static final String DATABASE_NAME_PROPERTY = "databaseNameText";
	static final String HOST_PROPERTY = "hostText";
	static final String PORT_PROPERTY = "portText";
	static final String ROW_SAMPLE_SIZE_PROPERTY = "rowSampleSizeText";
	static final String PACKAGE_NAME = "packageName";

	
	private static final String HELP_CONTEXT_ID = JptNoSQLEclipseLinkUiPlugin.instance().getPluginID() + ".GenerateEntitiesFromSchemaWizard"; //$NON-NLS-1$
	
	// ********** constructor **********

	public GenerateDynamicEntitiesFromNoSqlDb() {
		this.resourceManager = this.buildResourceManager();
		this.setWindowTitle(JptJpaEclipseLinkUiEntityGenMessages.GenerateDynamicEntitiesWizard_generateEntities);
	}
	
	public GenerateDynamicEntitiesFromNoSqlDb( JpaProject jpaProject, IStructuredSelection selection) {
		super();
		this.jpaProject = jpaProject;
		this.selection = selection;
		this.resourceManager = this.buildResourceManager();
		this.setWindowTitle(JptJpaEclipseLinkUiEntityGenMessages.GenerateDynamicEntitiesWizard_generateEntities);
	}

	protected ResourceManager buildResourceManager() {
		JpaWorkbench jpaWorkbench = this.getJpaWorkbench();
		return (jpaWorkbench != null) ? jpaWorkbench.buildLocalResourceManager() : new LocalResourceManager(JFaceResources.getResources(this.getWorkbench().getDisplay()));
	}
	
	protected JpaWorkbench getJpaWorkbench() {
		return PlatformTools.getAdapter(this.getWorkbench(), JpaWorkbench.class);
	}
	
	protected IWorkbench getWorkbench() {
		return PlatformUI.getWorkbench();
	}
	
	@Override
    public IWizardPage getStartingPage() {
		if (this.projectPage != null) {
			if (this.dynamicDefaultTableGenerationPage != null) {
				return this.dynamicDefaultTableGenerationPage;
			}
			return this.projectPage;
		}
		return super.getStartingPage();
    }
	
	@Override
	public void addPages() {
		this.setForcePreviousAndNextButtons(true);

		if(this.jpaProject == null && this.selection != null) {
			this.jpaProject = this.getJpaProjectFromSelection(this.selection);
		}
			
		//If this.jpaProject is not initialized because user didn't select a JPA project
		if( ! this.projectIsValidSelection(this.jpaProject)) {
			this.projectPage = this.buildProjectWizardPage();
			this.addPage(this.projectPage);
			return;
		}
		this.addMainPages();
	}
	
	protected JpaProject getJpaProjectFromSelection(IStructuredSelection selection) {
    	if(selection == null || selection.isEmpty()) {
    		return null;
    	}
		Object firstElement = selection.getFirstElement();
		if(firstElement instanceof IProject) {
			return this.getJpaProject((IProject)firstElement);
		}
		else if(firstElement instanceof IJavaElement) {
			IProject project = ((IJavaElement)firstElement).getJavaProject().getProject();
			return this.getJpaProject(project);
		}
		return null;
    }
	
	public JpaProject getJpaProject() {
		return this.jpaProject;
	}
	
	protected void addMainPages() {
		
		//create customizer for use in child pages
		this.createORMGenCustomizer(null);
		
		//page to gather connection info
		this.noSqlConnectionConfigPage = new NoSqlConnectionConfigPage();
		this.addPage(noSqlConnectionConfigPage);
		
		// page for picking collections to generate from
		this.collectionSelectorWizardPage = new CollectionSelectorWizardPage(this.jpaProject, this.resourceManager, true);
		this.addPage(this.collectionSelectorWizardPage);
		
		//keep default settings page
		this.dynamicDefaultTableGenerationPage = new DynamicDefaultTableGenerationWizardPage(this.jpaProject);
		this.addPage(this.dynamicDefaultTableGenerationPage);
		this.dynamicDefaultTableGenerationPage.init(this.selection);
			
	}
	
	protected String getCustomizationFileName() {
	//	ConnectionProfile profile = getProjectConnectionProfile();
	//	String connection = profile == null ? "" : profile.getName();
		return "org.eclipse.jpt.nosql.gen.dynamic";  //$NON-NLS-1$
	}
	
	@Override
	public boolean performFinish() {
		if (this.jpaProject == null) {
			return true;
		}
		// the customizer will be null if the user presses "Finish" on the
		// Select JPA Project page (Don't ask....)
		if (this.customizer == null) {
			return false;
		}
		try {
			this.customizer.setDatabaseAnnotationNameBuilder(this.buildDatabaseAnnotationNameBuilder());
			this.customizer.save();
		} catch (IOException e) {
			JptNoSQLEclipseLinkUiPlugin.instance().logError(e);
		}
		OverwriteConfirmer overwriteConfirmer = null;
		if (showOverwriteWarning()) {
			overwriteConfirmer = new OverwriteConfirmer();
		}
		
		scheduleGenerateEntitiesJob(overwriteConfirmer);
		return true;
	}
	
	private DatabaseAnnotationNameBuilder buildDatabaseAnnotationNameBuilder() {
		return new LocalDatabaseAnnotationNameBuilder(this.jpaProject.getJpaPlatform().getEntityGeneratorDatabaseAnnotationNameBuilder());
	}
	protected void scheduleGenerateEntitiesJob(
			OverwriteConfirmer overwriteConfirmer) {
		
		WorkspaceJob genEntitiesJob = new GenerateEntitiesJob(this.jpaProject, getCustomizer(), this.dynamicDefaultTableGenerationPage.getPackageText(), overwriteConfirmer, true);
		genEntitiesJob.schedule();
		
	}

	
	// ********** generate entities job **********

	public static class GenerateEntitiesJob extends WorkspaceJob {
		final JpaProject jpaProject;
		final ORMGenCustomizer customizer;
		final String packageName;
		final OverwriteConfirmer confirmer;
		final boolean generateXml;
		public GenerateEntitiesJob(JpaProject jpaProject, ORMGenCustomizer customizer, String packageName, OverwriteConfirmer confirmer, boolean generateXml) {
			super(JptJpaUiMessages.EntitiesGenerator_jobName);
			this.customizer = customizer;
			this.packageName = packageName; 
			this.jpaProject = jpaProject;
			this.confirmer = confirmer;
			this.generateXml = generateXml;
			IResourceRuleFactory ruleFactory = ResourcesPlugin.getWorkspace().getRuleFactory();
			this.setRule(ruleFactory.modifyRule(jpaProject.getProject()));
		}
		
		@Override
		public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
			
			String xmlFileContents;
			try {		
				MongoEntityGenerator generator = new MongoEntityGenerator(getCustomizerProperty(HOST_PROPERTY), Integer.parseInt(getCustomizerProperty(PORT_PROPERTY)),
						getCustomizerProperty(DATABASE_NAME_PROPERTY), Integer.parseInt(getCustomizerProperty(ROW_SAMPLE_SIZE_PROPERTY)));
				xmlFileContents = generator.generateXML(this.customizer.getTableNames(), this.packageName);

				String xmlMappingFileLocation = this.customizer.getXmlMappingFile();
				JptXmlResource jpaXmlResource = this.jpaProject.getMappingFileXmlResource(new Path(xmlMappingFileLocation));
				IFile mappingFile;
				if(jpaXmlResource!=null){
					mappingFile = jpaXmlResource.getFile();
				}
				else{
					IProject project = jpaProject.getProject();
					IContainer container = ((ProjectResourceLocator) project.getAdapter(ProjectResourceLocator.class)).getDefaultLocation();
					mappingFile = container.getFile(new Path(xmlMappingFileLocation.substring(xmlMappingFileLocation.lastIndexOf("/")))); //$NON-NLS-1$
				}
				
				if(mappingFile.exists()){
					byte[] content = xmlFileContents.toString().getBytes(mappingFile.getCharset());
					mappingFile.setContents(new ByteArrayInputStream(content), false, true, null);
				}
				else {
					byte[] content = xmlFileContents.toString().getBytes(mappingFile.getCharset());
					createFile(mappingFile, new ByteArrayInputStream(content));
				}
				
				format(new NullProgressMonitor(), mappingFile);
				mappingFile.refreshLocal(1, null);
			
				OpenXmlMappingFileJob openXmlMappingFileJob = new OpenXmlMappingFileJob(this.jpaProject, mappingFile);
				openXmlMappingFileJob.schedule();
			
			}catch (UnknownHostException uhe){
				JptNoSQLEclipseLinkUiPlugin.instance().logError(uhe, uhe.getMessage());
			}catch (CoreException ce){
				JptNoSQLEclipseLinkUiPlugin.instance().logError(ce, ce.getMessage());
			}catch (UnsupportedEncodingException uee){
				JptNoSQLEclipseLinkUiPlugin.instance().logError(uee, uee.getMessage());
			}
			
			return Status.OK_STATUS;
		}
		
		private String getCustomizerProperty(String propertyName){
			return this.customizer.getProperty(propertyName, null, null);
		}
		
		public void createFile(IFile file, java.io.InputStream contents) throws CoreException {		
			file.create(contents, false, null/*monitor*/);
		}
		
		protected void format(IProgressMonitor monitor, IFile file) {
			if(monitor == null || monitor.isCanceled())
				return;
			
			try {
				monitor.beginTask("", 100);
				IContentDescription contentDescription = file.getContentDescription();
				monitor.worked(5);
				if (contentDescription != null) {
					IContentType contentType = contentDescription.getContentType();
					IStructuredFormatProcessor formatProcessor = getFormatProcessor(contentType.getId());
					if (formatProcessor != null && (monitor == null || !monitor.isCanceled())) {
						String message = NLS.bind(SSEUIMessages.FormatActionDelegate_3, new String[]{file.getFullPath().toString().substring(1)});					monitor.subTask(message);
						formatProcessor.setProgressMonitor(new SubProgressMonitor(monitor, 95));
						formatProcessor.formatFile(file);
					}
				}
				monitor.done();
			} catch (MalformedInputExceptionWithDetail e) {
				String message = NLS.bind(SSEUIMessages.FormatActionDelegate_5, new String[]{file.getFullPath().toString()});
				JptNoSQLEclipseLinkUiPlugin.instance().logError(message, e);
			} catch (IOException e) {
				String message = NLS.bind(SSEUIMessages.FormatActionDelegate_4, new String[]{file.getFullPath().toString()});
				JptNoSQLEclipseLinkUiPlugin.instance().logError(message, e);
			} catch (CoreException e) {
				String message = NLS.bind(SSEUIMessages.FormatActionDelegate_4, new String[]{file.getFullPath().toString()});
				JptNoSQLEclipseLinkUiPlugin.instance().logError(message, e);
			}
		}

		protected IStructuredFormatProcessor getFormatProcessor(String contentTypeId) {
			return FormatProcessorsExtensionReader.getInstance().getFormatProcessor(contentTypeId);
		}
	}
	
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		Object sel = selection.getFirstElement();
		if ( sel instanceof IResource ) {
			IProject proj = ((IResource) sel).getProject();
			JpaProject jpaProj = this.getJpaProject(proj);
			this.jpaProject = jpaProj;
		} else if( sel instanceof org.eclipse.jdt.core.IPackageFragmentRoot ) {
			org.eclipse.jdt.core.IPackageFragmentRoot root = (org.eclipse.jdt.core.IPackageFragmentRoot) sel;
			IProject proj = root.getJavaProject().getProject();
			JpaProject jpaProj = this.getJpaProject(proj);
			this.jpaProject = jpaProj;
		} else if( sel instanceof org.eclipse.jdt.core.IPackageFragment) {
			org.eclipse.jdt.core.IPackageFragment frag = (org.eclipse.jdt.core.IPackageFragment) sel;
			IProject proj = frag.getJavaProject().getProject();
			JpaProject jpaProj = this.getJpaProject(proj);
			this.jpaProject = jpaProj;
		}
		
		this.selection = selection;
		this.setWindowTitle(JptJpaEclipseLinkUiEntityGenMessages.GenerateDynamicEntitiesWizard_generateEntities);
	}

	protected PromptEclipseLinkProjectWizardPage buildProjectWizardPage() {
		return new PromptEclipseLinkProjectWizardPage(HELP_CONTEXT_ID);
	}

	public void setJpaProject(JpaProject jpaProject) {
		if ( ! this.projectIsValidSelection(this.jpaProject)) {
			this.jpaProject = jpaProject;
			IWizardPage currentPage = this.getContainer().getCurrentPage();
			if (this.projectPage != null && currentPage.equals(this.projectPage)) {
				this.addMainPages();
			}
		}
	}
	
	protected boolean projectIsValidSelection(JpaProject jpaProj) {
		return (jpaProject != null) &&
				jpaProj.getJpaPlatform().getConfig().getGroupConfig().getId().equals("eclipselink_nosql");  //TODO njh - need to make this more specific to NoSQL
	}
	
	public ORMGenCustomizer createORMGenCustomizer(Schema schema) {
		JpaPlatform jpaPlatform = this.jpaProject.getJpaPlatform();
		ORMGenCustomizer obj = PlatformTools.getAdapter(jpaPlatform, ORMGenCustomizer.class);
		
		if (obj != null) {
			this.customizer = (ORMGenCustomizer) obj; 
			this.customizer.init(this.getCustomizationFile(), schema);  
		} 
		else {
			this.customizer = new BaseEntityGenCustomizer();
			this.customizer.init(this.getCustomizationFile(), schema);  
		}

		ORMGenTable newDefaultTable = this.getCustomizer().createGenTable(null);
		if ( this.selection!=null && this.selection.getFirstElement() instanceof IPackageFragment ) {
			IPackageFragment packageFrag = (IPackageFragment)this.selection.getFirstElement();
			newDefaultTable.setPackage( packageFrag.getElementName() );
			for (IPackageFragmentRoot root : JDTTools.getJavaSourceFolders(this.jpaProject.getJavaProject())) {
				String srcFolder = root.getPath().toPortableString();
				if( packageFrag.getPath().toPortableString().startsWith( srcFolder +'/' )) {
					newDefaultTable.setSourceFolder(srcFolder.substring(1));
				}
			}
		}
		else if (newDefaultTable.getPackage().equals(StringTools.EMPTY_STRING)) {
			newDefaultTable.setPackage(JpaPreferences.getEntityGenDefaultPackageName(this.jpaProject.getProject()));
		}
		
		customizer.setPlatformVersion(getJpaProject().getJpaPlatform().getJpaVersion().getVersion());

		return this.customizer;
	}
	
	public ORMGenCustomizer getCustomizer() {
		return customizer;
	} 
	
	private File getCustomizationFile() {
		String projectPath = this.jpaProject.getProject().getLocation().toPortableString();
		File genDir = new File(projectPath + "/.settings");//$NON-NLS-1$
        genDir.mkdirs();
		return new File(genDir, getCustomizationFileName());
	}
	
	protected JpaProject getJpaProject(IProject project) {
		return (JpaProject) project.getAdapter(JpaProject.class);
	}

	@Override
	public void dispose() {
		this.resourceManager.dispose();
		super.dispose();
	}
	
    public static boolean showOverwriteWarning(){
    	return OptionalMessageDialog.isDialogEnabled(OverwriteConfirmerDialog.ID);
    }
	
	// ********** open xml mapping file job **********
	
	public static class OpenXmlMappingFileJob extends WorkspaceJob {
		private final JpaProject jpaProject;
		private final IFile mappingFile;

		public OpenXmlMappingFileJob(JpaProject jpaProject, IFile mappingFile) {
			super("Open XML File");
			this.jpaProject = jpaProject;
			this.mappingFile = mappingFile;
			IResourceRuleFactory ruleFactory = ResourcesPlugin.getWorkspace().getRuleFactory();
			this.setRule(ruleFactory.modifyRule(jpaProject.getProject()));
		}

		@Override
		public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
			try {
				postGeneration(this.jpaProject,this.mappingFile);
			} catch (InvocationTargetException e) {
				throw new CoreException(JptNoSQLEclipseLinkUiPlugin.instance().buildErrorStatus());
			}
			return Status.OK_STATUS;
		}
		
		private void postGeneration(JpaProject jpaProject, IFile mappingFile) throws InvocationTargetException {
			try {
				openEditor(mappingFile);
			}
			catch (Exception cantOpen) {
				throw new InvocationTargetException(cantOpen);
			} 
		}
		
		private void openEditor(final IFile file) {
			if (file != null) {
				SWTUtil.asyncExec(new Runnable() {
					public void run() {
						try {
							IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
							IDE.openEditor(page, file, true);
						}
						catch (PartInitException e) {
							JptNoSQLEclipseLinkUiPlugin.instance().logError(e);
						}
					}
				});
			}
		}
	}
	
	   // ********** overwrite confirmer **********

		public static class OverwriteConfirmer implements org.eclipse.jpt.jpa.gen.internal.OverwriteConfirmer {
			private boolean overwriteAll = false;
			private boolean skipAll = false;

			OverwriteConfirmer() {
			}

			public boolean overwrite(final String className) {
				if (this.overwriteAll) {
					return true;
				}
				if (this.skipAll) {
					return false;
				}
				return this.promptUser(className);
			}

			private boolean promptUser(final String className) {
				// get on the UI thread synchronously, need feedback before continuing
				final boolean ret[]=new boolean[1];
				org.eclipse.jpt.common.ui.internal.util.SWTUtil.syncExec(new Runnable() {
					public void run() {
						final OverwriteConfirmerDialog dialog = new OverwriteConfirmerDialog(Display.getCurrent().getActiveShell(), className);
						dialog.open();
						if (dialog.getReturnCode() == Window.CANCEL) {
							//throw new OperationCanceledException();
							skipAll = true;
							ret[0] = false;
							return;
						}
						if (dialog.yes()) {
							ret[0] = true;
						}
						if (dialog.yesToAll()) {
							overwriteAll = true;
							ret[0] = true;
						}
						if (dialog.no()) {
							ret[0] = false;
						}
						if (dialog.noToAll()) {
							skipAll = true;
							ret[0] = false;
						}
					}
				});
				return ret[0];
			}

		}
	
	
	// ********** overwrite dialog **********

	static class OverwriteConfirmerDialog extends OptionalMessageDialog {
		private boolean yes = false;
		private boolean yesToAll = false;
		private boolean no = false;
		private boolean noToAll = false;

		private static final String ID= "dontShowOverwriteEntitesFromSchemas.warning"; //$NON-NLS-1$

		OverwriteConfirmerDialog(Shell parent, String className) {
			super(ID, parent,
					JptJpaUiMessages.OverwriteConfirmerDialog_title,
					NLS.bind(JptJpaUiMessages.OverwriteConfirmerDialog_text, className),
					MessageDialog.WARNING,
					new String[] {IDialogConstants.YES_LABEL, 
									IDialogConstants.YES_TO_ALL_LABEL, 
									IDialogConstants.NO_LABEL, 
									IDialogConstants.NO_TO_ALL_LABEL, 
									IDialogConstants.CANCEL_LABEL},
					2);
		}
		
		@Override
		protected void createButtonsForButtonBar(Composite parent) {
			this.createButton(parent, IDialogConstants.YES_ID, IDialogConstants.YES_LABEL, false);
			this.createButton(parent, IDialogConstants.YES_TO_ALL_ID, IDialogConstants.YES_TO_ALL_LABEL, false);
			this.createButton(parent, IDialogConstants.NO_ID, IDialogConstants.NO_LABEL, true);
			this.createButton(parent, IDialogConstants.NO_TO_ALL_ID, IDialogConstants.NO_TO_ALL_LABEL, false);
			this.createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
		}

		@Override
		protected void buttonPressed(int buttonId) {
			switch (buttonId) {
				case IDialogConstants.YES_ID :
					this.yesPressed();
					break;
				case IDialogConstants.YES_TO_ALL_ID :
					this.yesToAllPressed();
					break;
				case IDialogConstants.NO_ID :
					this.noPressed();
					break;
				case IDialogConstants.NO_TO_ALL_ID :
					this.noToAllPressed();
					break;
				case IDialogConstants.CANCEL_ID :
					this.cancelPressed();
					break;
				default :
					break;
			}
		}

		private void yesPressed() {
			this.yes = true;
			this.setReturnCode(OK);
			this.close();
		}

		private void yesToAllPressed() {
			this.yesToAll = true;
			this.setReturnCode(OK);
			this.close();
		}

		private void noPressed() {
			this.no = true;
			this.setReturnCode(OK);
			this.close();
		}

		private void noToAllPressed() {
			this.noToAll = true;
			this.setReturnCode(OK);
			this.close();
		}

		boolean yes() {
			return this.yes;
		}

		boolean yesToAll() {
			return this.yesToAll;
		}

		boolean no() {
			return this.no;
		}

		boolean noToAll() {
			return this.noToAll;
		}
	}
	
	// ********** name builder adapter **********

	/**
	 * adapt the JPA platform-supplied builder to the builder interface
	 * expected by the entity generator
	 */
	static class LocalDatabaseAnnotationNameBuilder implements DatabaseAnnotationNameBuilder {
		private EntityGeneratorDatabaseAnnotationNameBuilder builder;
		LocalDatabaseAnnotationNameBuilder(EntityGeneratorDatabaseAnnotationNameBuilder builder) {
			super();
			this.builder = builder;
		}
		public String buildTableAnnotationName(String entityName, Table table) {
			return this.builder.buildTableAnnotationName(entityName, table);
		}
		public String buildColumnAnnotationName(String attributeName, Column column) {
			return this.builder.buildColumnAnnotationName(attributeName, column);
		}
		public String buildJoinColumnAnnotationName(String attributeName, ForeignKey foreignKey) {
			return this.builder.buildJoinColumnAnnotationName(attributeName, foreignKey);
		}
		public String buildJoinColumnAnnotationName(Column column) {
			return this.builder.buildJoinColumnAnnotationName(column);
		}
		public String buildJoinTableAnnotationName(Table table) {
			return this.builder.buildJoinTableAnnotationName(table);
		}
	}
	
}
