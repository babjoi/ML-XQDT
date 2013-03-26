/*******************************************************************************
 * Copyright (c) 2008, 2013 Oracle. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0, which accompanies this distribution
 * and is available at http://www.eclipse.org/legal/epl-v10.html.
 *
 * Contributors:
 *     Oracle - initial API and implementation
 *******************************************************************************/
package org.eclipse.jpt.nosql.eclipselink.core.context.persistence;

import org.eclipse.jpt.jpa.core.context.persistence.PersistenceUnitProperties;

/**
 *  Connection
 */
@SuppressWarnings("nls")
public interface Connection extends PersistenceUnitProperties
{
	Boolean getDefaultNativeSql();
	Boolean getNativeSql();
	void setNativeSql(Boolean newNativeSql);
		static final String NATIVE_SQL_PROPERTY = "nativeSql";
		// EclipseLink key string
		static final String ECLIPSELINK_NATIVE_SQL = "eclipselink.jdbc.native-sql";
		static final Boolean DEFAULT_NATIVE_SQL = Boolean.FALSE;

	BatchWriting getDefaultBatchWriting();
	BatchWriting getBatchWriting();
	void setBatchWriting(BatchWriting newBatchWriting);
		static final String BATCH_WRITING_PROPERTY = "batchWriting";
		// EclipseLink key string
		static final String ECLIPSELINK_BATCH_WRITING = "eclipselink.jdbc.batch-writing";
		static final BatchWriting DEFAULT_BATCH_WRITING = BatchWriting.none;

	Boolean getDefaultCacheStatements();
	Boolean getCacheStatements();
	void setCacheStatements(Boolean newCacheStatements);
		static final String CACHE_STATEMENTS_PROPERTY = "cacheStatements";
		// EclipseLink key string
		static final String ECLIPSELINK_CACHE_STATEMENTS = "eclipselink.jdbc.cache-statements";
		static final Boolean DEFAULT_CACHE_STATEMENTS = Boolean.FALSE;

	Integer getDefaultCacheStatementsSize();
	Integer getCacheStatementsSize();
	void setCacheStatementsSize(Integer newCacheStatementsSize);
		static final String CACHE_STATEMENTS_SIZE_PROPERTY = "cacheStatementsSize";
		// EclipseLink key string
		static final String ECLIPSELINK_CACHE_STATEMENTS_SIZE = "eclipselink.jdbc.cache-statements.size";
		static final Integer DEFAULT_CACHE_STATEMENTS_SIZE = Integer.valueOf(50);

	String getDefaultDriver();
	String getDriver();
	void setDriver(String newDriver);
		static final String DRIVER_PROPERTY = "driver";
		// EclipseLink key string
		static final String ECLIPSELINK_DRIVER = "eclipselink.jdbc.driver";
		static final String DEFAULT_DRIVER = "";

	String getDefaultUrl();
	String getUrl();
	void setUrl(String newUrl);
		static final String URL_PROPERTY = "url";
		// EclipseLink key string
		static final String ECLIPSELINK_URL = "eclipselink.jdbc.url";
		static final String DEFAULT_URL = "";

	String getDefaultUser();
	String getUser();
	void setUser(String newUser);
		static final String USER_PROPERTY = "user";
		// EclipseLink key string
		static final String ECLIPSELINK_USER = "eclipselink.jdbc.user";
		static final String DEFAULT_USER = "";

	String getDefaultPassword();
	String getPassword();
	void setPassword(String newPassword);
		static final String PASSWORD_PROPERTY = "password";
		// EclipseLink key string
		static final String ECLIPSELINK_PASSWORD = "eclipselink.jdbc.password";
		static final String DEFAULT_PASSWORD = "";

	Boolean getDefaultBindParameters();
	Boolean getBindParameters();
	void setBindParameters(Boolean newBindParameters);
		static final String BIND_PARAMETERS_PROPERTY = "bindParameters";
		// EclipseLink key string
		static final String ECLIPSELINK_BIND_PARAMETERS = "eclipselink.jdbc.bind-parameters";
		static final Boolean DEFAULT_BIND_PARAMETERS = Boolean.TRUE;

	Boolean getDefaultReadConnectionsShared();
	Boolean getReadConnectionsShared();
	void setReadConnectionsShared(Boolean newReadConnectionsShared);
		static final String READ_CONNECTIONS_SHARED_PROPERTY = "readConnectionsShared";
		// EclipseLink key string
		static final String ECLIPSELINK_READ_CONNECTIONS_SHARED = "eclipselink.jdbc.read-connections.shared";
		static final Boolean DEFAULT_READ_CONNECTIONS_SHARED = Boolean.FALSE;

	Integer getDefaultReadConnectionsMin();
	Integer getReadConnectionsMin();
	void setReadConnectionsMin(Integer newReadConnectionsMin);
		static final String READ_CONNECTIONS_MIN_PROPERTY = "readConnectionsMin";
		// EclipseLink key string
		static final String ECLIPSELINK_READ_CONNECTIONS_MIN = "eclipselink.jdbc.read-connections.min";
		static final Integer DEFAULT_READ_CONNECTIONS_MIN = Integer.valueOf(2);

	Integer getDefaultReadConnectionsMax();
	Integer getReadConnectionsMax();
	void setReadConnectionsMax(Integer newReadConnectionsMax);
		static final String READ_CONNECTIONS_MAX_PROPERTY = "readConnectionsMax";
		// EclipseLink key string
		static final String ECLIPSELINK_READ_CONNECTIONS_MAX = "eclipselink.jdbc.read-connections.max";
		static final Integer DEFAULT_READ_CONNECTIONS_MAX = Integer.valueOf(2);

	Integer getDefaultWriteConnectionsMin();
	Integer getWriteConnectionsMin();
	void setWriteConnectionsMin(Integer newWriteConnectionsMin);
		static final String WRITE_CONNECTIONS_MIN_PROPERTY = "writeConnectionsMin";
		// EclipseLink key string
		static final String ECLIPSELINK_WRITE_CONNECTIONS_MIN = "eclipselink.jdbc.write-connections.min";
		static final Integer DEFAULT_WRITE_CONNECTIONS_MIN = Integer.valueOf(5);

	Integer getDefaultWriteConnectionsMax();
	Integer getWriteConnectionsMax();
	void setWriteConnectionsMax(Integer newWriteConnectionsMax);
		static final String WRITE_CONNECTIONS_MAX_PROPERTY = "writeConnectionsMax";
		// EclipseLink key string
		static final String ECLIPSELINK_WRITE_CONNECTIONS_MAX = "eclipselink.jdbc.write-connections.max";
		static final Integer DEFAULT_WRITE_CONNECTIONS_MAX = Integer.valueOf(10);

	ExclusiveConnectionMode getDefaultExclusiveConnectionMode();
	ExclusiveConnectionMode getExclusiveConnectionMode();
	void setExclusiveConnectionMode(ExclusiveConnectionMode newExclusiveConnectionMode);
		static final String EXCLUSIVE_CONNECTION_MODE_PROPERTY = "exclusiveConnectionMode";
		// EclipseLink key string
		static final String ECLIPSELINK_EXCLUSIVE_CONNECTION_MODE = "eclipselink.jdbc.exclusive-connection.mode";
		static final ExclusiveConnectionMode DEFAULT_EXCLUSIVE_CONNECTION_MODE = ExclusiveConnectionMode.transactional;

	Boolean getDefaultLazyConnection();
	Boolean getLazyConnection();
	void setLazyConnection(Boolean newLazyConnection);
		static final String LAZY_CONNECTION_PROPERTY = "lazyConnection";
		// EclipseLink key string
		static final String ECLIPSELINK_LAZY_CONNECTION = "eclipselink.jdbc.exclusive-connection.is-lazy";
		static final Boolean DEFAULT_LAZY_CONNECTION = Boolean.TRUE;
}