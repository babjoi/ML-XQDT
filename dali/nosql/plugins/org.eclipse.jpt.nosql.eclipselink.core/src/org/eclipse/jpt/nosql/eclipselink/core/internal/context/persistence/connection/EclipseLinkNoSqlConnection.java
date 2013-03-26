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
import org.eclipse.jpt.nosql.eclipselink.core.context.persistence.connection.NoSqlConnection;
import org.eclipse.jpt.nosql.eclipselink.core.internal.context.persistence.EclipseLinkConnection;

/**
 * This is the generic implementation of a connection to a NoSQL datasource.
 *
 * @version 3.4
 */
public abstract class EclipseLinkNoSqlConnection extends EclipseLinkConnection
                                                 implements NoSqlConnection {

	private String connectionSpec;

	/**
	 * Creates a new <code>EclipseLinkNoSqlConnection</code>.
	 *
	 * @param parent The parent of this model object
	 */
	protected EclipseLinkNoSqlConnection(PersistenceUnit parent) {
		super(parent);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void addDatabaseConnectionPropertyNames(Map<String, String> propertyNames) {
		super.addDatabaseConnectionPropertyNames(propertyNames);
		propertyNames.put(ECLIPSELINK_CONNECTION_SPEC, CONNECTION_SPEC_PROPERTY);
	}

	private void connectionSpecChanged(String connectionSpec) {
		String oldConnectionSpec = this.connectionSpec;
		this.connectionSpec = connectionSpec;
		firePropertyChanged(CONNECTION_SPEC_PROPERTY, oldConnectionSpec, connectionSpec);
	}

	/**
	 * {@inheritDoc}
	 */
	public String getConnectionSpec() {
		return connectionSpec;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void initializeDatabaseConnectionProperties() {
		super.initializeDatabaseConnectionProperties();
		connectionSpec = getStringValue(ECLIPSELINK_CONNECTION_SPEC);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void propertyRemoved(String propertyName) {

		if (propertyName.equals(ECLIPSELINK_CONNECTION_SPEC)) {
			connectionSpecChanged(null);
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

		if (propertyName.equals(ECLIPSELINK_CONNECTION_SPEC)) {
			connectionSpecChanged(newValue);
		}
		else {
			super.propertyValueChanged(propertyName, newValue);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void setConnectionSpec(String connectionSpec) {

		preSetProperty();

		String oldConnectionSpec = this.connectionSpec;
		this.connectionSpec = connectionSpec;
		putProperty(CONNECTION_SPEC_PROPERTY, connectionSpec);
		firePropertyChanged(CONNECTION_SPEC_PROPERTY, oldConnectionSpec, connectionSpec);
	}
}