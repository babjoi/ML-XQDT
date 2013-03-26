/*******************************************************************************
 * Copyright (c) 2007, 2013 Oracle. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0, which accompanies this distribution
 * and is available at http://www.eclipse.org/legal/epl-v10.html.
 *
 * Contributors:
 *     Oracle - initial API and implementation
 ******************************************************************************/

package org.eclipse.jpt.nosql.eclipselink.ui.internal.wizards.gen;

import java.lang.reflect.InvocationTargetException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.resource.ResourceManager;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.jpt.common.ui.JptCommonUiImages;
import org.eclipse.jpt.common.ui.internal.util.SWTUtil;
import org.eclipse.jpt.common.ui.internal.util.TableLayoutComposite;
import org.eclipse.jpt.common.utility.internal.StringMatcher;
import org.eclipse.jpt.jpa.core.JpaProject;
import org.eclipse.jpt.jpa.gen.internal.ORMGenCustomizer;
import org.eclipse.jpt.jpa.ui.JptJpaUiMessages;
import org.eclipse.jpt.jpa.ui.internal.JpaHelpContextIds;
import org.eclipse.jpt.jpa.ui.wizards.gen.JptJpaUiWizardsEntityGenMessages;
import org.eclipse.jpt.nosql.eclipselink.ui.internal.plugin.JptNoSQLEclipseLinkUiPlugin;
import org.eclipse.persistence.tools.gen.nosql.mongo.MongoEntityGenerator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.help.IWorkbenchHelpSystem;

import com.mongodb.MongoException;

public class CollectionSelectorWizardPage extends WizardPage {

	private static final int TABLE_COLUMN_INDEX = 0;
	
	private JpaProject jpaProject;
	private ORMGenCustomizer customizer;
	private boolean updatePersistenceXml = false;
	private boolean isDynamic;

	private CheckboxTableViewer collectionTable;
	private Button updatePersistenceXmlCheckBox;
	private Button refreshCollectionsButton;
	private Button selectAllButton;
	private Button deselectAllButton;
	private Text searchText;
	private TableFilter filter;

	private WorkspaceJob fetchCollectionsJob;
	protected final ResourceManager resourceManager;

	// ********** constructors **********
	
	public CollectionSelectorWizardPage(JpaProject jpaProject, ResourceManager resourceManager, boolean isDynamic) {
		super("CollectionSelectorWizardPage"); //$NON-NLS-1$
		
		this.jpaProject = jpaProject;
		this.resourceManager = resourceManager;
		this.isDynamic = isDynamic;
		this.setTitle("Select Collections" );
		this.setMessage("Select collections to be used for generation");
	}

	// ********** IDialogPage implementation  **********
	
	public void createControl(Composite parent) {
		this.initializeDialogUnits(parent);

		this.setPageComplete(true);
		this.setControl(this.buildTopLevelControl(parent));
	}

	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		
		if (visible){
			this.updateSelectionState();
			this.doStatusUpdate();
		}
	}

    @Override
    public final void performHelp() {
//    	this.getHelpSystem().displayHelp(this.getWizard().getHelpContextID());
    }
    
	@Override
	public GenerateDynamicEntitiesFromNoSqlDb getWizard() {
		return (GenerateDynamicEntitiesFromNoSqlDb) super.getWizard();
	}

	protected final IWorkbenchHelpSystem getHelpSystem() {
		return PlatformUI.getWorkbench().getHelpSystem();
	}

	// ********** internal methods **********

	private Composite buildTopLevelControl(Composite parent) {
		//Create the ORMGenCustomizer
		this.customizer = this.getWizard().createORMGenCustomizer(null);
		
		Composite composite = new Composite(parent, SWT.NULL);
		int nColumns= 3;
		GridLayout layout = new GridLayout();
		layout.numColumns = nColumns;
		composite.setLayout(layout);
//		this.getHelpSystem().setHelp(composite, JpaHelpContextIds.GENERATE_ENTITIES_WIZARD_SELECT_TABLES);

		this.createCollectionSelectionControl(composite, nColumns);

		if (!isDynamic) {
			this.updatePersistenceXmlCheckBox = this.buildUpdatePersistenceXmlCheckBox(composite);
			this.fillColumns(this.updatePersistenceXmlCheckBox, 3);
		}
		
		//Filler column
		new Label( composite, SWT.NONE);
		//Restore defaults button
		this.buildRestoreDefaultsButton(composite);

		this.getHelpSystem().setHelp(this.collectionTable.getControl(), JpaHelpContextIds.DIALOG_GENERATE_ENTITIES_TABLES);
		return composite;
	}

	private void createCollectionSelectionControl(Composite parent, int columns) {
		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.verticalAlignment = GridData.BEGINNING;
		
		Label tableLabel = new Label(parent, SWT.NONE);
		tableLabel.setLayoutData(gridData);
		tableLabel.setText("&Collections");
		
		this.searchText = this.buildSearchText(parent);

		// build two empty labels to align the components
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);
		
		TableLayoutComposite layout = new TableLayoutComposite(parent, SWT.NONE);
		this.addColumnLayoutData(layout);

		filter = new TableFilter();
		this.collectionTable = this.buildCheckboxTableViewer(this.buildTable(layout));
		this.collectionTable.addFilter(filter);

		this.createButtonComposite(parent);
		this.initCollectionsSelectionControl(Collections.<String> emptyList());		
	}

	private void createButtonComposite(Composite parent){
		Composite buttonComposite = new Composite(parent, SWT.NULL);
		GridLayout buttonLayout = new GridLayout(1, false);
		buttonLayout.marginHeight = 0;
		buttonLayout.marginWidth = 0;
		
		buttonComposite.setLayout(buttonLayout);
		GridData data =  new GridData();
		data.horizontalAlignment = GridData.FILL;
		data.verticalAlignment = GridData.BEGINNING;
		buttonComposite.setLayoutData(data);

		this.selectAllButton = this.buildSelectAllButton(buttonComposite);
		this.deselectAllButton = this.buildDeselectAllButton(buttonComposite);
		this.refreshCollectionsButton = this.buildRefreshCollectionsButton(buttonComposite);
	}
	
	

	// ********** private methods **********

//	private Schema getSchema() {
//		return this.schema;
//	}

//	private void setSchema(Schema s) {
//		this.schema = s;
//	}


//	private Collection<Table> getTables(Schema schema) {
//		if(this.fetchTablesJobIsRunning()) {
//			return Collections.<Table> emptyList();
//		}
//		return CollectionTools.collection(schema.getTables());
//	}


	
	private String getCustomizerProperty(String propertyName){
		return this.customizer.getProperty(propertyName, null, null);
	}
	
	private boolean updatePersistenceXml() {
		return this.updatePersistenceXml;
	}

	private void setShouldUpdatePersistenceXml(boolean updatePersistenceXml){
		this.updatePersistenceXml = updatePersistenceXml;
		this.doStatusUpdate();
	}
	
	private void restoreUpdatePersistenceXmlDefault() {
		this.updatePersistenceXmlCheckBox.setSelection(true);
		this.setShouldUpdatePersistenceXml(true);
	}

	private void selectAllCollections() {
		this.collectionTable.setAllChecked(true);
		this.doStatusUpdate();
	}

	private void deselectAllCollections() {
		this.collectionTable.setAllChecked(false);
		this.doStatusUpdate();
	}

	private void refreshCollections() {
		this.updateTablesListViewer(Collections.<String> emptyList());
		this.updateSelectionState();
	}

	private void initCollectionsSelectionControl(Collection<String> possibleCollections) {
		this.collectionTable.setInput(possibleCollections);
	}

	// ********** UI components **********
	
	private org.eclipse.swt.widgets.Table buildTable(Composite parent) {
		org.eclipse.swt.widgets.Table table = new org.eclipse.swt.widgets.Table(parent, SWT.H_SCROLL | SWT.V_SCROLL | SWT.SINGLE | SWT.FULL_SELECTION | SWT.BORDER | SWT.CHECK);

		TableColumn tableNameColumn = new TableColumn(table, SWT.NONE, TABLE_COLUMN_INDEX);
		tableNameColumn.setText("Collection");
		tableNameColumn.setResizable(true);

		table.addKeyListener(this.buildTableKeyListener());
		
		GridData gridData= new GridData(GridData.FILL_BOTH);
		gridData.heightHint= SWTUtil.getTableHeightHint(table, 20);
		gridData.widthHint = 250;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true ;
		
		Color backgroundColor = new Color(parent.getDisplay(), 255, 0,0);
		parent.setLayoutData(gridData);
		parent.setBackground(backgroundColor);
		backgroundColor.dispose();
		
		return table;
	}
	
	private CheckboxTableViewer buildCheckboxTableViewer(org.eclipse.swt.widgets.Table parent) {
		CheckboxTableViewer tableViewer = new CheckboxTableViewer(parent);
		tableViewer.setUseHashlookup(true);
		
		tableViewer.setLabelProvider(this.buildCollectionTableLabelProvider());
		tableViewer.setContentProvider(this.buildCollectionTableContentProvider());
		
//		tableViewer.setSorter(this.buildTableViewerSorter());
		tableViewer.setComparator(new ViewerComparator());
		tableViewer.addPostSelectionChangedListener(this.buildTableSelectionChangedListener());
		
		return tableViewer;
	}
	
//	private ViewerSorter buildTableViewerSorter() {
//		return new ViewerSorter() {
//			@Override
//			public int compare(Viewer viewer, Object e1, Object e2) {
//				return ((String)e1).compareTo((String)e2);
//			}
//		};
//	}
	
	private Button buildUpdatePersistenceXmlCheckBox(Composite parent) {
		Button checkBox = new Button(parent, SWT.CHECK);
		checkBox.setText(JptJpaUiWizardsEntityGenMessages.GenerateEntitiesWizard_tableSelectPage_updatePersistenceXml );
		checkBox.setSelection(this.updatePersistenceXml());
		checkBox.addSelectionListener(this.buildUpdatePersistenceXmlSelectionListener());
		
		return checkBox;
	}

	private Button buildRestoreDefaultsButton(Composite parent) {
		Button button = new Button(parent, SWT.PUSH);
		GridData gridData = new GridData();
		gridData.horizontalAlignment = SWT.END;
		button.setLayoutData(gridData);
		button.setText(JptJpaUiWizardsEntityGenMessages.GenerateEntitiesWizard_tableSelectPage_Restore_Defaults);
		button.addSelectionListener(this.buildRestoreDefaultsSelectionListener());
		
		return button;
	}

	private Button buildSelectAllButton(Composite parent) {
		Button button = this.buildSelectionButton(parent,
			JptJpaUiMessages.General_selectAll,
			this.resourceManager.createImage(JptCommonUiImages.SELECT_ALL_BUTTON));
		
		button.addSelectionListener(this.buildSelectAllSelectionListener());
		return button;
	}

	private Button buildDeselectAllButton(Composite parent) {
		Button button = this.buildSelectionButton(parent,
			JptJpaUiMessages.General_deselectAll,
			this.resourceManager.createImage(JptCommonUiImages.DESELECT_ALL_BUTTON));
		
		button.addSelectionListener(this.buildDeselectAllSelectionListener());
		return button;
	}

	private Button buildRefreshCollectionsButton(Composite parent) {
		Button button = this.buildSelectionButton(parent,
			JptJpaUiMessages.General_refresh,
			this.resourceManager.createImage(JptCommonUiImages.REFRESH_BUTTON));
		
		button.addSelectionListener(this.buildRefreshCollectionsSelectionListener());
		return button;
	}

	private Button buildSelectionButton(Composite parent, String toolTipText, Image buttonImage) {
		Button button = new Button(parent, SWT.PUSH);
		button.setToolTipText(toolTipText);
		button.setImage(buttonImage);
		GridData gridData =  new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		button.setLayoutData(gridData);
		return button;
	}
	
	private void addColumnLayoutData(TableLayoutComposite layout) {
		layout.addColumnData(new ColumnWeightData(50, true));
	}

	/**
	 * Set the layoutData of the input control to occupy specified number of columns
	 * @param c
	 * @param columns
	 */
	private void fillColumns(Control c, int columns){
		GridData layoutData = new GridData();
		layoutData.horizontalSpan = columns;
		layoutData.verticalAlignment = SWT.FILL;
		layoutData.horizontalAlignment = SWT.FILL;
		layoutData.grabExcessHorizontalSpace = true;
		layoutData.grabExcessVerticalSpace = false;
		c.setLayoutData(layoutData);
		return ;
	}

	private IBaseLabelProvider buildCollectionTableLabelProvider() {
		return new CollectionTableLabelProvider();
	}

	private IContentProvider buildCollectionTableContentProvider() {
		return new CollectionTableContentProvider();
	}

	private Text buildSearchText(Composite parent) {
		GridData gridData = new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL);
		Text text = new Text(parent, SWT.BORDER | SWT.SEARCH | SWT.ICON_SEARCH | SWT.ICON_CANCEL);
		text.setMessage(JptJpaUiWizardsEntityGenMessages.GenerateEntitiesWizard_tableSelectPage_tableSearch);
		text.setLayoutData(gridData);
		text.addSelectionListener(this.buildClearSearchTextSelectionListener());
		text.addKeyListener(this.buildSearchTextKeyListener());
		return text;
	}

	// ********** listeners callbacks **********

	private void handleTablesListSelectionChanged(SelectionChangedEvent event) {
		this.doStatusUpdate();
	}

	// ********** listeners **********
	
	private KeyAdapter buildTableKeyListener() {
		return new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode == SWT.F2 && e.stateMask == SWT.NONE) {
					editEntityNameIfPossible();
					e.doit= false;
				}
			}
		};
	}
	
	private ISelectionChangedListener buildTableSelectionChangedListener() {
		return new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				handleTablesListSelectionChanged(event);
			}
		};
	}
	
	private SelectionListener buildUpdatePersistenceXmlSelectionListener() {
		return new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent e) {}

			public void widgetSelected(SelectionEvent e) {
				setShouldUpdatePersistenceXml(updatePersistenceXmlCheckBox.getSelection());
			}
		};
	}
	
	private SelectionListener buildRestoreDefaultsSelectionListener() {
		return new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent e) {}
			
			public void widgetSelected(SelectionEvent e) {
				if(customizer != null && customizer.getFile() != null ){
					if(customizer.getFile().exists() ){
						customizer.getFile().delete();
					}
					deselectAllCollections();
					restoreUpdatePersistenceXmlDefault();
				}
			}
		};
	}
	
	private SelectionListener buildSelectAllSelectionListener() {
		return new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent e) {}

			public void widgetSelected(SelectionEvent e) {
				selectAllCollections();
			}
		};
	}
	
	private SelectionListener buildDeselectAllSelectionListener() {
		return new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent e) {}

			public void widgetSelected(SelectionEvent e) {
				deselectAllCollections();
			}
		};
	}
	
	private SelectionListener buildRefreshCollectionsSelectionListener() {
		return new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent e) {}

			public void widgetSelected(SelectionEvent e) {
				refreshCollections();
			}
		};
	}

	private SelectionListener buildClearSearchTextSelectionListener() {
		return new SelectionAdapter() {
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				filter.setPattern("");
				collectionTable.refresh();
			}
		};
	}

	private KeyListener buildSearchTextKeyListener() {
		return new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent event) {
				filter.setPattern(searchText.getText());
				collectionTable.refresh();
			}
		};
	}

	// ********** table behaviors **********

	private void editEntityNameIfPossible() {
		Object[] selected = ((IStructuredSelection) this.collectionTable.getSelection()).toArray();
		if (selected.length != 1) {
			return;
		}
	}
	
	private Collection<String> getSelectedCollections() {
		ArrayList<String> selectedCollections = new ArrayList<String>();
		for (Object selectedTable : this.collectionTable.getCheckedElements())
			selectedCollections.add((String) selectedTable);
		return selectedCollections;
	}

	private boolean hasCollectionSelected() {
		return (this.collectionTable != null) ? (this.getSelectedCollections().size() > 0) : false;
	}

	private void updateTablesListViewer(Collection<String> possibleCollections) {
		if(this.collectionTable != null) {
			this.initCollectionsSelectionControl(possibleCollections);
		}
	}

	private boolean tableInitialized() {
		return (this.collectionTable != null) && (this.collectionTable.getTable().getItemCount() > 0);
	}

	// ********** fetch tables **********

	private boolean fetchCollectionsJobIsRunning() {
		return this.fetchCollectionsJob != null;
	}

	private WorkspaceJob buildFetchCollectionsJob() {
		final Collection<String> collections = new ArrayList<String>();

		WorkspaceJob workspaceJob = new WorkspaceJob("Get Collections") {

			@Override
			public IStatus runInWorkspace(IProgressMonitor monitor) {
				if(monitor.isCanceled()) {
					return Status.CANCEL_STATUS;
				}
				SubMonitor subMonitor = SubMonitor.convert(monitor, 
					"Fetching Collections", 75);
				try {
					subMonitor.beginTask("NoSql DB", 100);
					subMonitor.subTask("Fetching collections");
					subMonitor.worked(20);

					collections.addAll(possibleCollections());
					
					subMonitor.worked(95);
				}
				catch(OperationCanceledException e) {
					return Status.CANCEL_STATUS;
				}
				finally {
					subMonitor.done();
				}
				return Status.OK_STATUS;
			}
			
			private Collection<String> possibleCollections() {
				
				try {
					MongoEntityGenerator generator = new MongoEntityGenerator(getCustomizerProperty(GenerateDynamicEntitiesFromNoSqlDb.HOST_PROPERTY), Integer.parseInt(getCustomizerProperty(GenerateDynamicEntitiesFromNoSqlDb.PORT_PROPERTY)),
							getCustomizerProperty(GenerateDynamicEntitiesFromNoSqlDb.DATABASE_NAME_PROPERTY), Integer.parseInt(getCustomizerProperty(GenerateDynamicEntitiesFromNoSqlDb.ROW_SAMPLE_SIZE_PROPERTY)));
					return generator.listCollectionNames();
				} catch (NumberFormatException e) {
					JptNoSQLEclipseLinkUiPlugin.instance().logError(e);
				} catch (MongoException e) {
					JptNoSQLEclipseLinkUiPlugin.instance().logError(e);
				} catch (UnknownHostException e) {
					JptNoSQLEclipseLinkUiPlugin.instance().logError(e);
				}
				
				return Collections.<String> emptyList();
			}
			
		};

		workspaceJob.addJobChangeListener(new JobChangeAdapter() {
			@Override
			public void done(final IJobChangeEvent event) {
				
				 SWTUtil.asyncExec(new Runnable() {
		               public void run() {
		            	   updateTablesListViewer(collections);
		               }
				 });
				event.getJob().removeJobChangeListener(this);
				fetchCollectionsJob = null;
			}
		});
		return workspaceJob;
	}

	// ********** updates **********
	
	/**
	 * Update the status line and the OK button according to the given status
	 */
	private void doStatusUpdate() {
		if( ! this.hasCollectionSelected()) {
			this.setPageComplete(false);
		}
		else {
			this.setPageComplete(true);
			try{
				this.getContainer().run(false, false, new IRunnableWithProgress() {
					public void run( final IProgressMonitor monitor ) 
				    	throws InvocationTargetException, InterruptedException
				    {
						monitor.beginTask(JptJpaUiWizardsEntityGenMessages.GenerateEntitiesWizard_tableSelectPage_statusUpdate_taskName, 10);
				
						Collection<String> ret = CollectionSelectorWizardPage.this.getSelectedCollections();
						ArrayList<String> collectionNames = new ArrayList<String>();
						for(String t : ret) {
							collectionNames.add(t);
						}
						customizer.setTableNames(collectionNames);
						customizer.setUpdatePersistenceXml(updatePersistenceXml);
						monitor.done();
				    }
				});
			} 
			catch (Exception e) {
				JptNoSQLEclipseLinkUiPlugin.instance().logError(e);
			}
		}
	}

	private void updateSelectionState() {

		if( ! this.fetchCollectionsJobIsRunning() && ! this.tableInitialized()) {
			this.fetchCollectionsJob = this.buildFetchCollectionsJob();
			this.fetchCollectionsJob.schedule();
		}

		if(this.collectionTable!=null && this.customizer != null) {
			this.restoreWizardState();
		}
		this.doStatusUpdate();
	}

	private boolean restoreWizardState() {
		boolean pageComplete = false;
		if (this.updatePersistenceXmlCheckBox != null){
			this.updatePersistenceXmlCheckBox.setSelection(this.customizer.updatePersistenceXml());
		}
		List<String> preSelectedTableNames = this.customizer.getTableNames();
		if(preSelectedTableNames!=null && preSelectedTableNames.size()>0) {
			Set<String> set = new HashSet<String>();
			for(String s : preSelectedTableNames){
				set.add(s);
			}
	        TableItem[] items = this.collectionTable.getTable().getItems();
	        for(int i = 0; i < items.length; ++i) {
	            TableItem item = items[i];
	            String element = (String)item.getData();
	            if(element != null) {
	                boolean check = set.contains(element);
	                // only set if different, to avoid flicker
	                if(item.getChecked() != check) {
	                    item.setChecked(check);
	                    pageComplete = true;
	                }
	            }
	        }
		}
		return pageComplete;
	}

	// ********** inner classes **********
	//TODO njh - This class is probably unecessary for Collction impl...LabelProvider should be sufficient
	private class CollectionTableLabelProvider extends LabelProvider implements ITableLabelProvider {

		// ********** constructors **********
		
		CollectionTableLabelProvider() {
			super();
		}

		@Override
		public String getText(Object element) {
			return (String) element;
		}

		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		public String getColumnText(Object element, int columnIndex) {
			if(element == null) {
				return null;
			}
			switch(columnIndex) {
				case TABLE_COLUMN_INDEX:
					return (String) element;
			}
			throw new IllegalArgumentException("invalid column index: " + columnIndex);// $NON-NLS-1$
		}
	}


	private class CollectionTableContentProvider implements IStructuredContentProvider {

		// ********** constructors **********
		
		CollectionTableContentProvider() {
			super();
		}

		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {}

		public void dispose() {}

		public Object[] getElements(Object inputElement) {
			return ((Collection<?>) inputElement).toArray();
		}
	}

	private class TableFilter extends ViewerFilter {

		private String pattern;
		private StringMatcher matcher;
		private static final String ALL = "*"; //$NON-NLS-1$

		/**
		 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
		 */
		@Override
		public boolean select (Viewer viewer, Object parentElement, Object element) {
			String collection = (String) element;
			if (pattern == null || pattern.length() == 0) {
				return true;
			}
			// if a table is previously selected, it will show up 
			// in the viewer along with the list of tables filtered out
			if (collectionTable.getChecked(collection)) {
				return true;
			}
			if (matcher.match(collection)) {
				return true;
			}
			return false;
		}

		/**
		 * Set the pattern to filter out tables for the table viewer
		 * <p>
		 * The following characters have special meaning:
		 *   ? => any character
		 *   * => any string
		 * </p>
		 * @param newPattern the new search pattern
		 */
		protected void setPattern(String newPattern) {
			if (newPattern == null || newPattern.trim().length() == 0) {
				matcher = new StringMatcher(ALL, true, false);
			} else {
				pattern = newPattern + ALL; 
				matcher = new StringMatcher(pattern, true, false);
			}
		}
	}
}