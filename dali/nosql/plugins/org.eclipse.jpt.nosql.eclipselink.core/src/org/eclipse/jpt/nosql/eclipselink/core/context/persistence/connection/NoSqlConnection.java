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

import org.eclipse.jpt.nosql.eclipselink.core.context.persistence.Connection;

/**
 * This defines the connection settings for a NoSQL database.
 * <p>
 * Provisional API: This interface is part of an interim API that is still under development and
 * expected to change significantly before reaching stability. It is available at this early stage
 * to solicit feedback from pioneering adopters on the understanding that any code that uses this
 * API will almost certainly be broken (repeatedly) as the API evolves.</p>
 *
 * @version 3.4
 */
@SuppressWarnings("nls")
public interface NoSqlConnection extends Connection {

	/**
	 * The {@link EISConnectionSpec} class name that defines how to connect to the NoSQL datasource.
	 */
	String getDefaultConnectionSpec();
	String getConnectionSpec();
	void setConnectionSpec(String connectionSpec);
		String CONNECTION_SPEC_PROPERTY = "connection-spec";
		String ECLIPSELINK_CONNECTION_SPEC = "eclipselink.nosql.connection-spec";
}