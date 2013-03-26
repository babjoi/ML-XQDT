/*******************************************************************************
 * Copyright (c) 2013 Oracle. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0, which accompanies this distribution
 * and is available at http://www.eclipse.org/legal/epl-v10.html.
 *
 * Contributors:
 *     Oracle - initial API and implementation
 ******************************************************************************/
package org.eclipse.jpt.nosql.eclipselink.ui.internal.persistence.connection;

import com.ibm.icu.text.Collator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jpt.common.ui.JptCommonUiMessages;
import org.eclipse.jpt.common.ui.internal.widgets.ClassChooserPane;
import org.eclipse.jpt.common.ui.internal.widgets.Pane;
import org.eclipse.jpt.common.utility.internal.StringTools;
import org.eclipse.jpt.common.utility.internal.collection.ListTools;
import org.eclipse.jpt.common.utility.internal.model.value.CompositeListValueModel;
import org.eclipse.jpt.common.utility.internal.model.value.PropertyAspectAdapter;
import org.eclipse.jpt.common.utility.internal.model.value.PropertyListValueModelAdapter;
import org.eclipse.jpt.common.utility.internal.model.value.SimpleListValueModel;
import org.eclipse.jpt.common.utility.internal.model.value.TransformationPropertyValueModel;
import org.eclipse.jpt.common.utility.model.value.ListValueModel;
import org.eclipse.jpt.common.utility.model.value.ModifiablePropertyValueModel;
import org.eclipse.jpt.common.utility.model.value.PropertyValueModel;
import org.eclipse.jpt.common.utility.transformer.Transformer;
import org.eclipse.jpt.nosql.eclipselink.core.context.persistence.connection.MongoDBConnection;
import org.eclipse.jpt.nosql.eclipselink.core.context.persistence.connection.MongoDBConnection.WriteConcern;
import org.eclipse.jpt.nosql.eclipselink.core.context.persistence.connection.NoSqlConnection;
import org.eclipse.jpt.nosql.eclipselink.ui.internal.EclipseLinkUiMessages;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.Section;

@SuppressWarnings("nls")
public class MongoDBConnectionPropopertiesComposite<T extends MongoDBConnection> extends NoSqlConnectionPropertiesComposite<T> {

	/**
	 * A key used to represent the default value, this is required to convert the selected item
	 * from a combo to <code>null</code>. This key is most likely never typed by the user and it
	 * will help to convert the value to <code>null</code> when it's time to set the new selected
	 * value into the model.
	 */
	private static String DEFAULT_KEY = "?!#!?#?#?default?#?!#?!#?";

	public MongoDBConnectionPropopertiesComposite(Pane<T> parent, Composite parentComposite) {
		super(parent, parentComposite);
	}

	private void addMongoDBOptions(Composite container) {

		Section section = getWidgetFactory().createSection(
			container,
			ExpandableComposite.TWISTIE |
			ExpandableComposite.CLIENT_INDENT
		);
		section.setText(EclipseLinkUiMessages.PersistenceXmlConnectionTab_optionsLabel);

		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.horizontalSpan = 2;
		section.setLayoutData(gridData);

		// Section's container
		container = getWidgetFactory().createComposite(section);
		section.setClient(container);

		GridLayout gridLayout = new GridLayout(2, false);
		gridLayout.marginWidth  = 0;
		container.setLayout(gridLayout);
		container.setLayoutData(new GridData(GridData.FILL_BOTH));

		// User
		addLabel(container, EclipseLinkUiMessages.PersistenceXmlConnectionTab_userLabel);
		addText(container, buildUserHolder());

		// Password
		addLabel(container, EclipseLinkUiMessages.PersistenceXmlConnectionTab_passwordLabel);
		addText(container, buildPasswordHolder());

		// Read Preference
		addLabel(container, EclipseLinkUiMessages.PersistenceXmlConnectionTab_readPreferenceLabel);
		addCombo(
			container,
			buildExtendedReadPreferenceListHolder(),
			buildReadPreferenceHolder(),
			buildReadPreferenceConverter(),
			buildReadPreferenceEnabledHolder()
		);

		// Write Concern
		addLabel(container, EclipseLinkUiMessages.PersistenceXmlConnectionTab_writeConcernLabel);
		addCombo(
			container,
			buildExtendedWriteConcernListHolder(),
			buildWriteConcernHolder(),
			buildWriteConcernConverter(),
			buildWriteConcernEnabledHolder()
		);

		//leaving this out for now
		// Options
//		addLabel(container, EclipseLinkUiMessages.PersistenceXmlConnectionTab_optionsLabel);
//		addText(container, buildOptionsHolder());
	}

	private ModifiablePropertyValueModel<String> buildDatabaseNameHolder() {
		return new PropertyAspectAdapter<MongoDBConnection, String>(getSubjectHolder(), MongoDBConnection.DATABASE_NAME_PROPERTY) {
			@Override
			protected String buildValue_() {
				return subject.getDatabaseName();
			}
			@Override
			protected void setValue_(String value) {
				subject.setDatabaseName(value);
			}
		};
	}

	private PropertyValueModel<String> buildDefaultReadPreferenceHolder() {
		return new TransformationPropertyValueModel<MongoDBConnection, String>(getSubjectHolder()) {
			@Override
			protected String transform_(MongoDBConnection connection) {
				String value = connection.getDefaultReadPreference();
				return StringTools.isBlank(value) ? DEFAULT_KEY : DEFAULT_KEY + value;
			}
		};
	}

	private ListValueModel<String> buildDefaultReadPreferenceListHolder() {
		return new PropertyListValueModelAdapter<String>(
			buildDefaultReadPreferenceHolder()
		);
	}

	private PropertyValueModel<String> buildDefaultWriteConcernHolder() {
		return new TransformationPropertyValueModel<MongoDBConnection, String>(getSubjectHolder()) {
			@Override
			protected String transform_(MongoDBConnection connection) {
				String value = connection.getDefaultWriteConcern();
				return StringTools.isBlank(value) ? DEFAULT_KEY : DEFAULT_KEY + value;
			}
		};
	}

	private ListValueModel<String> buildDefaultWriteConcernListHolder() {
		return new PropertyListValueModelAdapter<String>(
			buildDefaultWriteConcernHolder()
		);
	}

	private ListValueModel<String> buildExtendedReadPreferenceListHolder() {
		ArrayList<ListValueModel<String>> holders = new ArrayList<ListValueModel<String>>(2);
		holders.add(buildDefaultReadPreferenceListHolder());
		holders.add(buildReadPreferenceListHolder());
		return CompositeListValueModel.forModels(holders);
	}

	private ListValueModel<String> buildExtendedWriteConcernListHolder() {
		ArrayList<ListValueModel<String>> holders = new ArrayList<ListValueModel<String>>(2);
		holders.add(buildDefaultWriteConcernListHolder());
		holders.add(buildWriteConcernListHolder());
		return CompositeListValueModel.forModels(holders);
	}

	private ModifiablePropertyValueModel<String> buildHostHolder() {
		return new PropertyAspectAdapter<MongoDBConnection, String>(getSubjectHolder(), MongoDBConnection.HOST_PROPERTY) {
			@Override
			protected String buildValue_() {
				return subject.getHost();
			}
			@Override
			protected void setValue_(String value) {
				subject.setHost(value);
			}
		};
	}

	private ModifiablePropertyValueModel<String> buildOptionsHolder() {
		return new PropertyAspectAdapter<MongoDBConnection, String>(getSubjectHolder(), MongoDBConnection.OPTIONS_PROPERTY) {
			@Override
			protected String buildValue_() {
				return subject.getOptions();
			}
			@Override
			protected void setValue_(String value) {
				subject.setOptions((value.length() == 0) ? null : value);
			}
		};
	}

	private ModifiablePropertyValueModel<String> buildPasswordHolder() {
		return new PropertyAspectAdapter<MongoDBConnection, String>(getSubjectHolder(), MongoDBConnection.PASSWORD_PROPERTY) {
			@Override
			protected String buildValue_() {
				return subject.getPassword();
			}
			@Override
			protected void setValue_(String value) {
				subject.setPassword((value.length() == 0) ? null : value);
			}
		};
	}

	private ModifiablePropertyValueModel<String> buildPortdHolder() {
		return new PropertyAspectAdapter<MongoDBConnection, String>(getSubjectHolder(), MongoDBConnection.PORT_PROPERTY) {
			@Override
			protected String buildValue_() {
				return subject.getPort();
			}
			@Override
			protected void setValue_(String value) {
				subject.setPort(value);
			}
		};
	}

	private Comparator<String> buildReadPreferenceComparator() {
		return new Comparator<String>() {
			public int compare(String value1, String value2) {
				String displayString1 = readPreferenceDisplayString(value1);
				String displayString2 = readPreferenceDisplayString(value2);
				return Collator.getInstance().compare(displayString1, displayString2);
			}
		};
	}

	private Transformer<String, String> buildReadPreferenceConverter() {
		return new Transformer<String, String>() {
			public String transform(String value) {

				if (getSubject() == null) {
					return null;
				}

				if ((value == null) || value.startsWith(DEFAULT_KEY)) {
					return NLS.bind(
						JptCommonUiMessages.DEFAULT_WITH_ONE_PARAM,
						readPreferenceDisplayString(getSubject().getDefaultReadPreference())
					);
				}

				return readPreferenceDisplayString(value);
			}
		};
	}

	private PropertyValueModel<Boolean> buildReadPreferenceEnabledHolder() {
		return buildNotNullSubjectModel();
	}

	private ModifiablePropertyValueModel<String> buildReadPreferenceHolder() {
		return new PropertyAspectAdapter<MongoDBConnection, String>(getSubjectHolder(), MongoDBConnection.READ_PREFERENCE_PROPERTY) {
			@Override
			protected String buildValue_() {
				return subject.getReadPreference();
			}
			@Override
			protected void setValue_(String value) {
				if (value.startsWith(DEFAULT_KEY)) {
					value = null;
				}
				subject.setReadPreference(value);
			}
		};
	}

	private ListValueModel<String> buildReadPreferenceListHolder() {
		return new SimpleListValueModel<String>(
			buildSortedReadPreferenceList()
		);
	}

	private List<String> buildSortedReadPreferenceList() {
		return ListTools.sort(
			ListTools.list(
				MongoDBConnection.READ_PREFERENCE_PRIMARY,
				MongoDBConnection.READ_PREFERENCE_SECONDARY
			),
			buildReadPreferenceComparator()
		);
	}

	private List<String> buildSortedWriteConcernList() {
		return ListTools.sort(ListTools.list(writeConcerns()),  Collator.getInstance());
	}

	private ModifiablePropertyValueModel<String> buildUserHolder() {
		return new PropertyAspectAdapter<MongoDBConnection, String>(getSubjectHolder(), MongoDBConnection.USER_PROPERTY) {
			@Override
			protected String buildValue_() {
				return subject.getUser();
			}
			@Override
			protected void setValue_(String value) {
				subject.setUser((value.length() == 0) ? null : value);
			}
		};
	}

	private Transformer<String, String> buildWriteConcernConverter() {
		return new Transformer<String, String>() {
			public String transform(String value) {

				if (getSubject() == null) {
					return null;
				}

				if ((value == null) || value.startsWith(DEFAULT_KEY)) {
					return NLS.bind(
						JptCommonUiMessages.DEFAULT_WITH_ONE_PARAM,
						getSubject().getDefaultWriteConcern()
					);
				}

				return value;
			}
		};
	}

	private PropertyValueModel<Boolean> buildWriteConcernEnabledHolder() {
		return buildNotNullSubjectModel();
	}

	private ModifiablePropertyValueModel<String> buildWriteConcernHolder() {
		return new PropertyAspectAdapter<MongoDBConnection, String>(getSubjectHolder(), MongoDBConnection.WRITE_CONCERN_PROPERTY) {
			@Override
			protected String buildValue_() {
				return subject.getWriteConcern();
			}
			@Override
			protected void setValue_(String value) {
				if (value.startsWith(DEFAULT_KEY)) {
					value = null;
				}
				subject.setWriteConcern((value == null) ? null : value);
			}
		};
	}

	private ListValueModel<String> buildWriteConcernListHolder() {
		return new SimpleListValueModel<String>(
			buildSortedWriteConcernList()
		);
	}

	private ClassChooserPane<NoSqlConnection> initializeConnectionSpecClassChooser(Composite container) {
		return new ClassChooserPane<NoSqlConnection>(this, container) {
			@Override
			protected ModifiablePropertyValueModel<String> buildTextHolder() {
				return new PropertyAspectAdapter<NoSqlConnection, String>(getSubjectHolder(), NoSqlConnection.CONNECTION_SPEC_PROPERTY) {
					@Override
					protected String buildValue_() {
						return this.subject.getConnectionSpec();
					}

					@Override
					protected void setValue_(String value) {
						this.subject.setConnectionSpec((value.length() == 0) ? null : value);
					}
				};
			}
			@Override
			protected String getClassName() {
				return this.getSubject().getConnectionSpec();
			}
			@Override
			protected IJavaProject getJavaProject() {
				return getSubject().getJpaProject().getJavaProject();
			}
			@Override
			protected void setClassName(String className) {
				this.getSubject().setConnectionSpec(className);
			}
		};
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void initializeNoSqlConnection(Composite container) {

		// Connection Spec
		addLabel(container, EclipseLinkUiMessages.PersistenceXmlConnectionTab_connectionSpecLabel);
		initializeConnectionSpecClassChooser(container);

		// Name
		addLabel(container, EclipseLinkUiMessages.PersistenceXmlConnectionTab_nameLabel);
		addText(container, buildDatabaseNameHolder());

		// Host
		addLabel(container, EclipseLinkUiMessages.PersistenceXmlConnectionTab_hostLabel);
		addText(container, buildHostHolder());

		// Port
		addLabel(container, EclipseLinkUiMessages.PersistenceXmlConnectionTab_portLabel);
		addText(container, buildPortdHolder());

		// Options section
		addMongoDBOptions(container);
	}

	private String readPreferenceDisplayString(String value) {

		if (MongoDBConnection.READ_PREFERENCE_PRIMARY.equals(value)) {
			return EclipseLinkUiMessages.PersistenceXmlConnectionTab_readPreference_primary;
		}

		if (MongoDBConnection.READ_PREFERENCE_SECONDARY.equals(value)) {
			return EclipseLinkUiMessages.PersistenceXmlConnectionTab_readPreference_secondary;
		}

		return EclipseLinkUiMessages.PersistenceXmlConnectionTab_readPreference_unknown;
	}

	private Iterable<String> writeConcerns() {
		List<String> names = new ArrayList<String>();
		for (WriteConcern value : WriteConcern.values()) {
			names.add(value.name());
		}
		return names;
	}
}