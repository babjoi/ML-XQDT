/*******************************************************************************
 * Copyright (c) 2009, 2013 Oracle. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0, which accompanies this distribution
 * and is available at http://www.eclipse.org/legal/epl-v10.html.
 *
 * Contributors:
 *     Oracle - initial API and implementation
 ******************************************************************************/
package org.eclipse.jpt.nosql.eclipselink.core.internal.context.persistence;

import org.eclipse.jpt.jpa.core.context.persistence.PersistenceUnit;
import org.eclipse.jpt.jpa.core.context.persistence.PersistenceUnitProperties;
import org.eclipse.jpt.nosql.eclipselink.core.internal.context.persistence.connection.EclipseLinkMongoDBConnection;

public class EclipseLinkPersistenceXmlContextNodeFactory extends AbstractEclipseLinkPersistenceXmlContextNodeFactory {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PersistenceUnitProperties buildConnection(PersistenceUnit parent) {
		// TODO
		return new EclipseLinkMongoDBConnection(parent);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PersistenceUnitProperties buildOptions(PersistenceUnit parent) {
		return new EclipseLinkOptions(parent);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PersistenceUnitProperties buildLogging(PersistenceUnit parent) {
		return new EclipseLinkLogging(parent);
	}
}