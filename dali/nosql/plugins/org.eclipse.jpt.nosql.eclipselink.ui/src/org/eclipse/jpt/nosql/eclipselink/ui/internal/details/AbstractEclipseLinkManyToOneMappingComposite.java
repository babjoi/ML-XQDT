/*******************************************************************************
 * Copyright (c) 2006, 2012 Oracle. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0, which accompanies this distribution
 * and is available at http://www.eclipse.org/legal/epl-v10.html.
 *
 * Contributors:
 *     Oracle - initial API and implementation
 ******************************************************************************/
package org.eclipse.jpt.nosql.eclipselink.ui.internal.details;

import org.eclipse.jface.resource.ResourceManager;
import org.eclipse.jpt.common.ui.WidgetFactory;
import org.eclipse.jpt.common.utility.internal.model.value.PropertyAspectAdapter;
import org.eclipse.jpt.common.utility.model.value.PropertyValueModel;
import org.eclipse.jpt.jpa.core.context.Cascade;
import org.eclipse.jpt.jpa.core.context.ManyToOneRelationship;
import org.eclipse.jpt.jpa.ui.JptJpaUiMessages;
import org.eclipse.jpt.jpa.ui.details.JptJpaUiDetailsMessages;
import org.eclipse.jpt.jpa.ui.internal.details.AbstractManyToOneMappingComposite;
import org.eclipse.jpt.jpa.ui.internal.details.CascadePane;
import org.eclipse.jpt.jpa.ui.internal.details.FetchTypeComboViewer;
import org.eclipse.jpt.jpa.ui.internal.details.OptionalTriStateCheckBox;
import org.eclipse.jpt.jpa.ui.internal.details.TargetEntityClassChooser;
import org.eclipse.jpt.nosql.eclipselink.core.context.EclipseLinkJoinFetch;
import org.eclipse.jpt.nosql.eclipselink.core.context.EclipseLinkManyToOneMapping;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.forms.widgets.Hyperlink;

/**
 * Here the layout of this pane:
 * <pre>
 * -----------------------------------------------------------------------------
 * | ------------------------------------------------------------------------- |
 * | |                                                                       | |
 * | | TargetEntityComposite                                                 | |
 * | |                                                                       | |
 * | ------------------------------------------------------------------------- |
 * | ------------------------------------------------------------------------- |
 * | |                                                                       | |
 * | | JoiningStrategyComposite                                              | |
 * | |                                                                       | |
 * | ------------------------------------------------------------------------- |
 * | ------------------------------------------------------------------------- |
 * | |                                                                       | |
 * | | FetchTypeComposite                                                    | |
 * | |                                                                       | |
 * | ------------------------------------------------------------------------- |
 * | ------------------------------------------------------------------------- |
 * | |                                                                       | |
 * | | OptionalComposite                                                     | |
 * | |                                                                       | |
 * | ------------------------------------------------------------------------- |
 * | ------------------------------------------------------------------------- |
 * | |                                                                       | |
 * | | CascadeComposite                                                      | |
 * | |                                                                       | |
 * | ------------------------------------------------------------------------- |
 * -----------------------------------------------------------------------------</pre>
 */
public abstract class AbstractEclipseLinkManyToOneMappingComposite<T extends EclipseLinkManyToOneMapping, C extends Cascade> 
	extends AbstractManyToOneMappingComposite<T, ManyToOneRelationship, C>
{
	protected AbstractEclipseLinkManyToOneMappingComposite(
			PropertyValueModel<? extends T> mappingModel,
			PropertyValueModel<Boolean> enabledModel,
			Composite parentComposite,
	        WidgetFactory widgetFactory,
	        ResourceManager resourceManager) {
		super(mappingModel, enabledModel, parentComposite, widgetFactory, resourceManager);
	}

	@Override
	protected Control initializeManyToOneSection(Composite container) {
		container = this.addSubPane(container, 2, 0, 0, 0, 0);

		// Target entity widgets
		Hyperlink targetEntityHyperlink = this.addHyperlink(container, JptJpaUiDetailsMessages.TargetEntityChooser_label);
		new TargetEntityClassChooser(this, container, targetEntityHyperlink);

		// Fetch type widgets
		this.addLabel(container, JptJpaUiDetailsMessages.BasicGeneralSection_fetchLabel);
		new FetchTypeComboViewer(this, container);

		// Join fetch widgets
		this.addLabel(container, EclipseLinkUiDetailsMessages.EclipseLinkJoinFetchComposite_label);
		new EclipseLinkJoinFetchComboViewer(this, buildJoinFetchModel(), container);

		// Optional widgets
		OptionalTriStateCheckBox optionalCheckBox = new OptionalTriStateCheckBox(this, container);
		GridData gridData = new GridData();
		gridData.horizontalSpan = 2;
		optionalCheckBox.getControl().setLayoutData(gridData);

		// Cascade widgets
		CascadePane cascadeComposite = new CascadePane(this, buildCascadeModel(), container);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.horizontalSpan = 2;
		cascadeComposite.getControl().setLayoutData(gridData);

		return container;
	}
	
	protected PropertyValueModel<EclipseLinkJoinFetch> buildJoinFetchModel() {
		return new PropertyAspectAdapter<T, EclipseLinkJoinFetch>(getSubjectHolder()) {
			@Override
			protected EclipseLinkJoinFetch buildValue_() {
				return this.subject.getJoinFetch();
			}
		};
	}
}
