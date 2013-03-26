/*******************************************************************************
 * Copyright (c) 2010, 2013 Oracle. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0, which accompanies this distribution
 * and is available at http://www.eclipse.org/legal/epl-v10.html.
 * 
 * Contributors:
 *     Oracle - initial API and implementation
 ******************************************************************************/
package org.eclipse.jpt.nosql.eclipselink.ui.internal.wizards.proj;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jpt.jpa.ui.JptJpaUiImages;
import org.eclipse.jpt.jpa.ui.internal.perspective.JpaPerspectiveFactory;
import org.eclipse.jpt.jpa.ui.internal.wizards.proj.model.JpaProjectCreationDataModelProvider;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.project.facet.core.IFacetedProjectTemplate;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;
import org.eclipse.wst.web.ui.internal.wizards.NewProjectDataModelFacetWizard;

public class NoSqlPersistenceProjectWizard
		extends NewProjectDataModelFacetWizard {
	
	public NoSqlPersistenceProjectWizard() {
		super();
		setWindowTitle("New NoSQL Persistence Project");
	}
	
	public NoSqlPersistenceProjectWizard(IDataModel dataModel) {
		super(dataModel);
		setWindowTitle("New NoSQL Persistence Project");
	}
	
	
	@Override
	protected ImageDescriptor getDefaultPageImageDescriptor() {
		return JptJpaUiImages.JPA_PROJECT_BANNER;
	}
	
	@Override
	protected IWizardPage createFirstPage() {
		return new NoSqlPersistenceProjectWizardFirstPage(this.model, "first.page"); //$NON-NLS-1$ 
	}
	
	@Override
	protected IDataModel createDataModel() {
		return DataModelFactory.createDataModel(new JpaProjectCreationDataModelProvider());
	}
	
	@Override
	protected IFacetedProjectTemplate getTemplate() {
		return ProjectFacetsManager.getTemplate("jpt.jpa.template"); //$NON-NLS-1$
	}
	
	@Override
	protected String getFinalPerspectiveID() {
		return JpaPerspectiveFactory.ID;
	}
}
