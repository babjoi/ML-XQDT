/*******************************************************************************
 * Copyright (c) 2012 Oracle. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0, which accompanies this distribution
 * and is available at http://www.eclipse.org/legal/epl-v10.html.
 * 
 * Contributors:
 *     Oracle - initial API and implementation
 ******************************************************************************/
package org.eclipse.jpt.nosql.eclipselink.core.context;

import org.eclipse.jpt.jpa.core.context.AccessType;

/**
 * Context model corresponding to:<ul>
 * <li>the XML resource model
 * {@link org.eclipse.jpt.jpa.eclipselink.core.resource.orm.v2_1.EclipseLinkAccessType},
 * which corresponds to the <code>access</code> element in the
 * <code>orm.xml</code> file.
 * <li>the Java resource model {@link org.eclipse.jpt.jpa.core.resource.java.AccessType}
 * which corresponds to the <code>javax.persistence.Access</code> annotation.
 * </ul>
 * Provisional API: This interface is part of an interim API that is still
 * under development and expected to change significantly before reaching
 * stability. It is available at this early stage to solicit feedback from
 * pioneering adopters on the understanding that any code that uses this API
 * will almost certainly be broken (repeatedly) as the API evolves.
 * 
 * @version 3.2
 * @since 3.2
 */
public class EclipseLinkAccessType extends AccessType {

	public static final AccessType VIRTUAL = new AccessType(
			null,
			org.eclipse.jpt.jpa.eclipselink.core.resource.orm.v2_1.EclipseLinkAccessType.VIRTUAL,
			"Virtual" //$NON-NLS-1$
		);


	public EclipseLinkAccessType(org.eclipse.jpt.jpa.core.resource.java.AccessType javaAccessType, String ormAccessType, String displayString) {
		super(javaAccessType, ormAccessType, displayString);
	}
}
