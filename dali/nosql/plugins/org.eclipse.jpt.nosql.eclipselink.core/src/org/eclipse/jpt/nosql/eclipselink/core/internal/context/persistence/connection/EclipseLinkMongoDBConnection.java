/*******************************************************************************
 * Copyright (c) 2013 Oracle. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0, which accompanies this distribution
 * and is available at http://www.eclipse.org/legal/epl-v10.html.
 *
 * Contributors:
 *     Oracle - initial API and implementation
 *
 *******************************************************************************/
package org.eclipse.jpt.nosql.eclipselink.core.internal.context.persistence.connection;

import java.util.Map;
import org.eclipse.jpt.jpa.core.context.persistence.PersistenceUnit;
import org.eclipse.jpt.nosql.eclipselink.core.context.persistence.connection.MongoDBConnection;

/**
 * This is the concrete implementation of a connection to MongoDB.
 *
 * @version 3.4
 */
public class EclipseLinkMongoDBConnection extends EclipseLinkNoSqlConnection
                                          implements MongoDBConnection {

	private String databaseName;
	private String host;
	private String options;
	private String port;
	private String readPreference;
	private String writeConcern;

	/**
	 * Creates a new <code>EclipseLinkNoSqlConnection</code>.
	 *
	 * @param parent The parent of this model object
	 */
	public EclipseLinkMongoDBConnection(PersistenceUnit parent) {
		super(parent);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void addDatabaseConnectionPropertyNames(Map<String, String> propertyNames) {
		super.addDatabaseConnectionPropertyNames(propertyNames);
		propertyNames.put(ECLIPSELINK_DATABASE_NAME,   DATABASE_NAME_PROPERTY);
		propertyNames.put(ECLIPSELINK_HOST,            HOST_PROPERTY);
		propertyNames.put(ECLIPSELINK_PORT,            PORT_PROPERTY);
		propertyNames.put(ECLIPSELINK_READ_PREFERENCE, READ_PREFERENCE_PROPERTY);
		propertyNames.put(ECLIPSELINK_WRITE_CONCERN,   WRITE_CONCERN_PROPERTY);
		propertyNames.put(ECLIPSELINK_OPTIONS,         OPTIONS_PROPERTY);
	}

	private void databaseNameChanged(String databaseName) {
		String oldDatabaseName = this.databaseName;
		this.databaseName = databaseName;
		firePropertyChanged(DATABASE_NAME_PROPERTY, oldDatabaseName, databaseName);
	}

	/**
	 * {@inheritDoc}
	 */
	public String getDatabaseName() {
		return databaseName;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getDefaultConnectionSpec() {
		return DEFAULT_CONNECTION_SPEC;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getDefaultDatabaseName() {
		return DEFAULT_DATABASE_NAME;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getDefaultHost() {
		return DEFAULT_HOST;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getDefaultOptions() {
		return DEFAULT_OPTIONS;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getDefaultPort() {
		return DEFAULT_PORT;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getDefaultReadPreference() {
		return DEFAULT_READ_PREFERENCE;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getDefaultWriteConcern() {
		return DEFAULT_WRITE_CONCERN;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getHost() {
		return host;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getOptions() {
		return options;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getPort() {
		return port;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getReadPreference() {
		return readPreference;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getWriteConcern() {
		return writeConcern;
	}

	private void hostChanged(String host) {
		String oldHost = this.host;
		this.host = host;
		firePropertyChanged(HOST_PROPERTY, oldHost, host);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void initializeDatabaseConnectionProperties() {
		super.initializeDatabaseConnectionProperties();

		databaseName   = getStringValue(ECLIPSELINK_DATABASE_NAME);
		host           = getStringValue(ECLIPSELINK_HOST);
		port           = getStringValue(ECLIPSELINK_PORT);
		readPreference = getStringValue(ECLIPSELINK_READ_PREFERENCE);
		writeConcern   = getStringValue(ECLIPSELINK_WRITE_CONCERN);
		options        = getStringValue(ECLIPSELINK_OPTIONS);
	}

	private void optionsChanged(String options) {
		String oldOptions = this.options;
		this.options = options;
		firePropertyChanged(OPTIONS_PROPERTY, oldOptions, options);
	}

	private void portChanged(String port) {
		String oldPort = this.port;
		this.port = port;
		firePropertyChanged(PORT_PROPERTY, oldPort, port);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void propertyRemoved(String propertyName) {

		if (propertyName.equals(ECLIPSELINK_DATABASE_NAME)) {
			databaseNameChanged(null);
		}
		else if (propertyName.equals(ECLIPSELINK_HOST)) {
			hostChanged(null);
		}
		else if (propertyName.equals(ECLIPSELINK_PORT)) {
			portChanged(null);
		}
		else if (propertyName.equals(ECLIPSELINK_READ_PREFERENCE)) {
			readPreferenceChanged(null);
		}
		else if (propertyName.equals(ECLIPSELINK_WRITE_CONCERN)) {
			writeConcernChanged(null);
		}
		else if (propertyName.equals(ECLIPSELINK_OPTIONS)) {
			optionsChanged(null);
		}
		else {
			super.propertyRemoved(propertyName);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void propertyValueChanged(String propertyName, String newValue) {

		if (propertyName.equals(ECLIPSELINK_DATABASE_NAME)) {
			databaseNameChanged(newValue);
		}
		else if (propertyName.equals(ECLIPSELINK_HOST)) {
			hostChanged(newValue);
		}
		else if (propertyName.equals(ECLIPSELINK_PORT)) {
			portChanged(newValue);
		}
		else if (propertyName.equals(ECLIPSELINK_READ_PREFERENCE)) {
			readPreferenceChanged(newValue);
		}
		else if (propertyName.equals(ECLIPSELINK_WRITE_CONCERN)) {
			writeConcernChanged(newValue);
		}
		else if (propertyName.equals(ECLIPSELINK_OPTIONS)) {
			optionsChanged(newValue);
		}
		else {
			super.propertyValueChanged(propertyName, newValue);
		}
	}

	private void readPreferenceChanged(String readPreference) {
		String oldReadPreference = this.readPreference;
		this.readPreference = readPreference;
		firePropertyChanged(READ_PREFERENCE_PROPERTY, oldReadPreference, readPreference);
	}

	/**
	 * {@inheritDoc}
	 */
	public void setDatabaseName(String databaseName) {

		preSetProperty();

		String oldDatabaseName = this.databaseName;
		this.databaseName = databaseName;
		putProperty(DATABASE_NAME_PROPERTY, databaseName);
		firePropertyChanged(DATABASE_NAME_PROPERTY, oldDatabaseName, databaseName);
	}

	/**
	 * {@inheritDoc}
	 */
	public void setHost(String host) {

		preSetProperty();

		String oldHost = this.host;
		this.host = host;
		putProperty(HOST_PROPERTY, host);
		firePropertyChanged(HOST_PROPERTY, oldHost, host);
	}

	/**
	 * {@inheritDoc}
	 */
	public void setOptions(String options) {

		preSetProperty();

		String oldOptions = this.options;
		this.options = options;
		putProperty(OPTIONS_PROPERTY, options);
		firePropertyChanged(OPTIONS_PROPERTY, oldOptions, options);
	}

	/**
	 * {@inheritDoc}
	 */
	public void setPort(String port) {

		preSetProperty();

		String oldPort = this.port;
		this.port = port;
		putProperty(PORT_PROPERTY, port);
		firePropertyChanged(PORT_PROPERTY, oldPort, port);
	}

	/**
	 * {@inheritDoc}
	 */
	public void setReadPreference(String readPreference) {

		preSetProperty();

		String oldReadPreference = this.readPreference;
		this.readPreference = readPreference;
		putProperty(READ_PREFERENCE_PROPERTY, readPreference);
		firePropertyChanged(READ_PREFERENCE_PROPERTY, oldReadPreference, readPreference);
	}

	/**
	 * {@inheritDoc}
	 */
	public void setWriteConcern(String writeConcern) {

		preSetProperty();

		String oldWriteConcern = this.writeConcern;
		this.writeConcern = writeConcern;
		putProperty(WRITE_CONCERN_PROPERTY, writeConcern);
		firePropertyChanged(WRITE_CONCERN_PROPERTY, oldWriteConcern, writeConcern);
	}

	private void writeConcernChanged(String writeConcern) {
		String oldWriteConcern = this.writeConcern;
		this.writeConcern = writeConcern;
		firePropertyChanged(WRITE_CONCERN_PROPERTY, oldWriteConcern, writeConcern);
	}
}