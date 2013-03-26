package org.eclipse.jpt.nosql.eclipselink.ui.internal.wizards.gen;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.jpt.common.utility.internal.StringTools;
import org.eclipse.jpt.jpa.core.JpaProject;
import org.eclipse.jpt.jpa.core.context.persistence.PersistenceUnit.Property;
import org.eclipse.jpt.jpa.gen.internal.ORMGenCustomizer;
import org.eclipse.jpt.nosql.eclipselink.core.context.persistence.connection.MongoDBConnection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Text;

public class NoSqlConnectionConfigPage extends WizardPage {

	private Label databaseNameLabel;
	private Text databaseNameText;
	
	private Label hostLabel;
	private Text hostText;
	
	private Label portLabel;
	private Text portText;
	
	private Label rowSampleSizeLabel;
	private Text rowSampleSizeText;

	public NoSqlConnectionConfigPage(){
		super("NoSqlConnectionConfigPage"); ////$NON-NLS-N$
		this.setTitle("NoSQL Connection Configuration");
		this.setDescription("Configure the NoSQL database connection.");
	}
	
	public void createControl(Composite parent) {
		initializeDialogUnits(parent);
		
		Composite composite = new Composite(parent, SWT.NULL);
		int nColumns= 2;
		GridLayout layout = new GridLayout();
		layout.numColumns = nColumns;
		composite.setLayout(layout);
		
		// Populate from persistence.xml hyperlink
		
		Link populateConnectionSettingsLink = new Link(composite, SWT.NONE);
		GridData data = new GridData(GridData.BEGINNING, GridData.CENTER, false, false);
		data.horizontalSpan = 2;
		populateConnectionSettingsLink.setLayoutData(data);
		populateConnectionSettingsLink.setText("<a>Populate from persistence.xml settings (if available)</a>");
		populateConnectionSettingsLink.addSelectionListener(
			new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					attemptPopulateConnectionInfo();
				}
			}
		);
		
		createConnectionConfigurationGroup(composite);

		setControl(composite);
		
		this.setPageComplete( true );
	}

	private void createConnectionConfigurationGroup(Composite parent) {
		Composite composite = new Composite(parent, SWT.NULL);
		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));	

		this.databaseNameLabel = new Label(composite, SWT.LEFT);
		this.databaseNameLabel.setText("Database Name:");
		this.databaseNameLabel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING));

		this.databaseNameText = new Text(composite, SWT.SINGLE | SWT.BORDER);
		this.databaseNameText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		this.databaseNameText.addModifyListener(new ModifyListener() {
			
			public void modifyText(ModifyEvent e) {
				handleDatabaseNameTextModified();
			}
		});
		
		this.hostLabel = new Label(composite, SWT.LEFT);
		this.hostLabel.setText("Host:");
		this.hostLabel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING));

		this.hostText = new Text(composite, SWT.SINGLE | SWT.BORDER);
		this.hostText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		this.hostText.addModifyListener(new ModifyListener() {
			
			public void modifyText(ModifyEvent e) {
				handleHostTextModified();
			}
		});
		
		
		this.portLabel = new Label(composite, SWT.LEFT);
		this.portLabel.setText("Port:");
		this.portLabel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING));

		this.portText = new Text(composite, SWT.SINGLE | SWT.BORDER);
		this.portText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		this.portText.addModifyListener(new ModifyListener() {
			
			public void modifyText(ModifyEvent e) {
				handlePortTextModified();
			}
		});
		
		this.rowSampleSizeLabel = new Label(composite, SWT.LEFT);
		this.rowSampleSizeLabel.setText("Collection Row Sample Size: ");
		this.rowSampleSizeLabel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING));

		this.rowSampleSizeText = new Text(composite, SWT.SINGLE | SWT.BORDER);
		this.rowSampleSizeText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		this.rowSampleSizeText.addModifyListener(new ModifyListener() {
			
			public void modifyText(ModifyEvent e) {
				handleRowSampleSizeTextModified();
			}
		});
		
	}


	
	protected void handleRowSampleSizeTextModified() {
		this.getCustomizer().setProperty(GenerateDynamicEntitiesFromNoSqlDb.ROW_SAMPLE_SIZE_PROPERTY, this.rowSampleSizeText.getText(), null, null);	
	}

	protected void handlePortTextModified() {
		this.getCustomizer().setProperty(GenerateDynamicEntitiesFromNoSqlDb.PORT_PROPERTY, this.portText.getText(), null, null);	
	}

	protected void handleHostTextModified() {
		this.getCustomizer().setProperty(GenerateDynamicEntitiesFromNoSqlDb.HOST_PROPERTY, this.hostText.getText(), null, null);	
	}

	protected void handleDatabaseNameTextModified() {
		this.getCustomizer().setProperty(GenerateDynamicEntitiesFromNoSqlDb.DATABASE_NAME_PROPERTY, this.databaseNameText.getText(), null, null);	
	}

	protected ORMGenCustomizer getCustomizer() {
		return this.getWizard().getCustomizer();
	}
	
	@Override
	public GenerateDynamicEntitiesFromNoSqlDb getWizard() {
		return (GenerateDynamicEntitiesFromNoSqlDb) super.getWizard();
	}

	
	void attemptPopulateConnectionInfo() {
		this.databaseNameText.setText(getMongoDBConnectionPropertyValue(MongoDBConnection.ECLIPSELINK_DATABASE_NAME));
		this.hostText.setText(getMongoDBConnectionPropertyValue(MongoDBConnection.ECLIPSELINK_HOST));
		this.portText.setText(getMongoDBConnectionPropertyValue(MongoDBConnection.ECLIPSELINK_PORT));
	}
	
	private String getMongoDBConnectionPropertyValue(String connectionProperty){
		JpaProject jpaProject = this.getWizard().getJpaProject();
		Property property = jpaProject.getRootContextNode().getPersistenceXml().getRoot().getPersistenceUnit(0).getProperty(connectionProperty);
		return property == null ? StringTools.EMPTY_STRING : property.getValue();
	}
	
	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if (visible) {
			ORMGenCustomizer customizer = getCustomizer();
			
			String databaseName = customizer.getProperty(GenerateDynamicEntitiesFromNoSqlDb.DATABASE_NAME_PROPERTY, null, null);
			this.databaseNameText.setText(databaseName == null ? "" : databaseName);
			
			String host = customizer.getProperty(GenerateDynamicEntitiesFromNoSqlDb.HOST_PROPERTY, null, null);
			this.hostText.setText(host == null ? "" : host);
			
			String port = customizer.getProperty(GenerateDynamicEntitiesFromNoSqlDb.PORT_PROPERTY, null, null);
			this.portText.setText(port == null ? "" : port);
			
			String rowSampleSize = customizer.getProperty(GenerateDynamicEntitiesFromNoSqlDb.ROW_SAMPLE_SIZE_PROPERTY, null, null);
			this.rowSampleSizeText.setText(rowSampleSize == null ? "" : rowSampleSize);
			
		}
	}
	
}
