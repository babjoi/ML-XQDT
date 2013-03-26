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
package org.eclipse.jpt.nosql.eclipselink.core.context.persistence.connection;

import org.eclipse.jpt.common.utility.internal.StringTools;

/**
 * This defines the connection settings for a MongoDB database.
 * <p>
 * Provisional API: This interface is part of an interim API that is still under development and
 * expected to change significantly before reaching stability. It is available at this early stage
 * to solicit feedback from pioneering adopters on the understanding that any code that uses this
 * API will almost certainly be broken (repeatedly) as the API evolves.</p>
 *
 * @version 3.4
 */
@SuppressWarnings("nls")
public interface MongoDBConnection extends NoSqlConnection {

	/**
	 * The {@link EISConnectionSpec} for MongoDB.
	 */
	String DEFAULT_CONNECTION_SPEC = "org.eclipse.persistence.nosql.adapters.mongo.MongoConnectionSpec";

	/**
	 * The comma separated list of NoSQL hosts.
	 */
	String getDefaultDatabaseName();
	String getDatabaseName();
	void setDatabaseName(String databaseName);
		String DATABASE_NAME_PROPERTY = "databaseName";
		String ECLIPSELINK_DATABASE_NAME = "eclipselink.nosql.property.mongo.db";
		String DEFAULT_DATABASE_NAME = StringTools.EMPTY_STRING;

	/**
	 * The comma separated list of NoSQL hosts.
	 */
	String getDefaultHost();
	String getHost();
	void setHost(String host);
		String HOST_PROPERTY = "host";
		String ECLIPSELINK_HOST = "eclipselink.nosql.property.mongo.host";
		String DEFAULT_HOST = StringTools.EMPTY_STRING;

	/**
	 * The comma separated list of NoSQL host's ports.
	 */
	String getDefaultPort();
	String getPort();
	void setPort(String port);
		String PORT_PROPERTY = "port";
		String ECLIPSELINK_PORT = "eclipselink.nosql.property.mongo.port";
		String DEFAULT_PORT = "27017";

	/**
	 * Read preference describes how MongoDB clients route read operations to members of a replica set.
	 */
	String getDefaultReadPreference();
	String getReadPreference();
	void setReadPreference(String readPreference);
		String READ_PREFERENCE_PROPERTY = "read-preference";
		String ECLIPSELINK_READ_PREFERENCE = "eclipselink.nosql.property.mongo.read-preference";
		String DEFAULT_READ_PREFERENCE = "PRIMARY";
		String READ_PREFERENCE_PRIMARY = "PRIMARY";
		String READ_PREFERENCE_SECONDARY = "SECONDARY";

	/**
	 * The write concern confirms the success of write operations to a replica set’s primary.
	 */
	String getDefaultWriteConcern();
	String getWriteConcern();
	void setWriteConcern(String writeConcern);
		String WRITE_CONCERN_PROPERTY = "write-preference";
		String ECLIPSELINK_WRITE_CONCERN = "eclipselink.nosql.property.mongo.write-concern";
		String DEFAULT_WRITE_CONCERN = "NONE";

	/**
	 * A series of bitwise options.
	 */
	String getDefaultOptions();
	String getOptions();
	void setOptions(String options);
		String OPTIONS_PROPERTY = "options";
		String ECLIPSELINK_OPTIONS = "eclipselink.nosql.property.mongo.options";
		String DEFAULT_OPTIONS = StringTools.EMPTY_STRING;

	/**
	 * The choices for write concern.
	 */
	enum WriteConcern {
		FSYNC_SAFE,
		JOURNAL_SAFE,
		MAJORITY,
		NONE,
		NORMAL,
		REPLICAS_SAFE,
		SAFE
	}
}