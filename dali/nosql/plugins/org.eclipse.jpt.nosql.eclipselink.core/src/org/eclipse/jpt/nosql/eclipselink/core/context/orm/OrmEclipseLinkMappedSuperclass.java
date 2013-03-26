/*******************************************************************************
 * Copyright (c) 2008, 2010 Oracle. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0, which accompanies this distribution
 * and is available at http://www.eclipse.org/legal/epl-v10.html.
 * 
 * Contributors:
 *     Oracle - initial API and implementation
 ******************************************************************************/
package org.eclipse.jpt.nosql.eclipselink.core.context.orm;

import org.eclipse.jpt.jpa.core.context.orm.OrmMappedSuperclass;
import org.eclipse.jpt.jpa.core.context.orm.OrmQueryContainer;
import org.eclipse.jpt.jpa.eclipselink.core.resource.orm.XmlMappedSuperclass;
import org.eclipse.jpt.nosql.eclipselink.core.context.EclipseLinkMappedSuperclass;
import org.eclipse.jpt.nosql.eclipselink.core.context.java.JavaEclipseLinkMappedSuperclass;

/**
 * EclipseLink
 * <code>orm.xml</code> mapped superclass
 * <p>
 * Provisional API: This interface is part of an interim API that is still
 * under development and expected to change significantly before reaching
 * stability. It is available at this early stage to solicit feedback from
 * pioneering adopters on the understanding that any code that uses this API
 * will almost certainly be broken (repeatedly) as the API evolves.
 * 
 * @version 2.1
 * @since 2.1
 */
public interface OrmEclipseLinkMappedSuperclass
	extends OrmMappedSuperclass, EclipseLinkMappedSuperclass, OrmEclipseLinkNonEmbeddableTypeMapping
{
	XmlMappedSuperclass getXmlTypeMapping();

	JavaEclipseLinkMappedSuperclass getJavaTypeMapping();

	JavaEclipseLinkMappedSuperclass getJavaTypeMappingForDefaults();

	OrmQueryContainer getQueryContainer();
}
