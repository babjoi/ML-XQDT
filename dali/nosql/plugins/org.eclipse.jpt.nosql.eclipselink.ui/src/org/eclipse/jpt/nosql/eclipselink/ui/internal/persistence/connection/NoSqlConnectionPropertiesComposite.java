/*******************************************************************************
 * Copyright (c) 2008, 2013 Oracle. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0, which accompanies this distribution
 * and is available at http://www.eclipse.org/legal/epl-v10.html.
 *
 * Contributors:
 *     Oracle - initial API and implementation
 ******************************************************************************/
package org.eclipse.jpt.nosql.eclipselink.ui.internal.persistence.connection;

import org.eclipse.jpt.common.ui.JptCommonUiMessages;
import org.eclipse.jpt.common.ui.internal.widgets.Pane;
import org.eclipse.jpt.common.ui.internal.widgets.TriStateCheckBox;
import org.eclipse.jpt.common.utility.internal.model.value.PropertyAspectAdapter;
import org.eclipse.jpt.common.utility.internal.model.value.TransformationPropertyValueModel;
import org.eclipse.jpt.common.utility.model.value.ModifiablePropertyValueModel;
import org.eclipse.jpt.common.utility.model.value.PropertyValueModel;
import org.eclipse.jpt.jpa.ui.internal.JpaHelpContextIds;
import org.eclipse.jpt.nosql.eclipselink.core.context.persistence.connection.NoSqlConnection;
import org.eclipse.jpt.nosql.eclipselink.ui.internal.EclipseLinkUiMessages;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

public abstract class NoSqlConnectionPropertiesComposite<T extends NoSqlConnection> extends Pane<T> {

	public NoSqlConnectionPropertiesComposite(Pane<T> parent, Composite parentComposite) {
		super(parent, parentComposite);
	}

	@Override
	protected Composite addComposite(Composite parent) {
		return this.addSubPane(parent, 2, 0, 0, 0, 0);
	}

	@Override
	protected void initializeLayout(Composite container) {

		// NoSQL database specific settings
		initializeNoSqlConnection(container);

		// Bind Parameters
		TriStateCheckBox bindParametersCheckBox = this.addTriStateCheckBoxWithDefault(
			container,
			EclipseLinkUiMessages.PersistenceXmlConnectionTab_bindParametersLabel,
			this.buildBindParametersHolder(),
			this.buildBindParametersStringHolder(),
			JpaHelpContextIds.PERSISTENCE_XML_CONNECTION
		);
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.horizontalSpan = 2;
		bindParametersCheckBox.getCheckBox().setLayoutData(gridData);
	}

	protected abstract void initializeNoSqlConnection(Composite container);

	private ModifiablePropertyValueModel<Boolean> buildBindParametersHolder() {
		return new PropertyAspectAdapter<NoSqlConnection, Boolean>(getSubjectHolder(), NoSqlConnection.BIND_PARAMETERS_PROPERTY) {
			@Override
			protected Boolean buildValue_() {
				return this.subject.getBindParameters();
			}

			@Override
			protected void setValue_(Boolean value) {
				this.subject.setBindParameters(value);
			}
		};
	}

	private PropertyValueModel<String> buildBindParametersStringHolder() {
		return new TransformationPropertyValueModel<Boolean, String>(buildDefaultBindParametersHolder()) {
			@Override
			protected String transform(Boolean value) {
				if (value != null) {
					String defaultStringValue = value.booleanValue() ? JptCommonUiMessages.BOOLEAN_TRUE : JptCommonUiMessages.BOOLEAN_FALSE;
					return NLS.bind(EclipseLinkUiMessages.PersistenceXmlConnectionTab_bindParametersLabelDefault, defaultStringValue);
				}
				return EclipseLinkUiMessages.PersistenceXmlConnectionTab_bindParametersLabel;
			}
		};
	}

	private PropertyValueModel<Boolean> buildDefaultBindParametersHolder() {
		return new PropertyAspectAdapter<NoSqlConnection, Boolean>(getSubjectHolder(), NoSqlConnection.BIND_PARAMETERS_PROPERTY) {
			@Override
			protected Boolean buildValue_() {
				if (this.subject.getBindParameters() != null) {
					return null;
				}
				return this.subject.getDefaultBindParameters();
			}
		};
	}
}