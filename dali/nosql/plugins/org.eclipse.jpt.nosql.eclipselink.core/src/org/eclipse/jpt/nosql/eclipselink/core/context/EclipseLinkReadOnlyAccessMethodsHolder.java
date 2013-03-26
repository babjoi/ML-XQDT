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

import org.eclipse.jpt.common.utility.model.Model;

/**
 * Common interface that can be used by clients interested only in a type
 * or attribute's access methods setting (e.g. a UI composite).
 * <p>
 * Provisional API: This interface is part of an interim API that is still
 * under development and expected to change significantly before reaching
 * stability. It is available at this early stage to solicit feedback from
 * pioneering adopters on the understanding that any code that uses this API
 * will almost certainly be broken (repeatedly) as the API evolves.
 * 
 * @version 3.2
 * @since 3.2
 */
public interface EclipseLinkReadOnlyAccessMethodsHolder
	extends Model
{
	/**
	 * Return the get method, whether specified or defaulted.
	 * This should never return <code>null</code> since at least the default
	 * will be set.
	 */
	String getGetMethod();

	/**
	 * Return the set method, whether specified or defaulted.
	 * This should never return <code>null</code> since at least the default
	 * will be set.
	 */
	String getSetMethod();
}
