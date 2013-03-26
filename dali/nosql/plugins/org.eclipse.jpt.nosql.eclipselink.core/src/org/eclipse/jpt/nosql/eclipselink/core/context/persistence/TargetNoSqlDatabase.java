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
package org.eclipse.jpt.nosql.eclipselink.core.context.persistence;

import java.util.Iterator;

import org.eclipse.jpt.common.utility.internal.iterator.IteratorTools;
import org.eclipse.jpt.common.utility.internal.transformer.TransformerAdapter;
import org.eclipse.jpt.jpa.core.context.persistence.PersistenceXmlEnumValue;

/**
 * This enum defines the list of supported NoSQL databases.
 *
 * @version 3.4
 */
@SuppressWarnings("nls")
public enum TargetNoSqlDatabase implements PersistenceXmlEnumValue {

	/**
	 * The constant for the MongoDB database.
	 */
	mongodb("org.eclipse.persistence.nosql.adapters.mongo.MongoPlatform");

	private final String propertyValue;

	TargetNoSqlDatabase(String propertyValue) {
		this.propertyValue = propertyValue;
	}

	/**
	 * Return the {@link TargetNoSqlDatabase} value corresponding to the given literal.
	 *
	 * @param literal The literal for which a constant might exists
	 * @return Either the constant for the given literal or <code>null</code> if none could be found
	 */
	public static TargetNoSqlDatabase getTargetNoSqlDatabaseFor(String literal) {

		for (TargetNoSqlDatabase database : TargetNoSqlDatabase.values()) {
			if (database.name().equals(literal)) {
				return database;
			}
		}

		return null;
	}

	public static Iterator<String> getTargetNoSqlDatabaseAdapterNames(){
		return IteratorTools.transform(
				IteratorTools.iterator(TargetNoSqlDatabase.values()),
				ENUM_ADAPTER_NAME_TRANSFORMER
			);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public String getPropertyValue() {
		return propertyValue;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return propertyValue;
	}
	
	static TransformerAdapter<Enum<?>, String> ENUM_ADAPTER_NAME_TRANSFORMER = new EnumAdapterNameTransformer();
	static class EnumAdapterNameTransformer
		extends TransformerAdapter<Enum<?>, String>
	{
		@Override
		public String transform(Enum<?> e) {
			return e.toString();
		}
	}
	
}