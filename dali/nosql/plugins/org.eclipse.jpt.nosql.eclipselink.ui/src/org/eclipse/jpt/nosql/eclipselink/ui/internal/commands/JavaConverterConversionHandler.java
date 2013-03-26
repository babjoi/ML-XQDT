/*******************************************************************************
 * Copyright (c) 2011, 2013 Oracle. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0, which accompanies this distribution
 * and is available at http://www.eclipse.org/legal/epl-v10.html.
 *
 * Contributors:
 *     Oracle - initial API and implementation
 ******************************************************************************/
package org.eclipse.jpt.nosql.eclipselink.ui.internal.commands;

import org.eclipse.jpt.jpa.core.JpaProject;
import org.eclipse.jpt.jpa.ui.JpaPlatformUi;
import org.eclipse.jpt.jpa.ui.internal.handlers.AbstractJavaMetadataConversionHandler;
import org.eclipse.jpt.nosql.eclipselink.ui.internal.platform.EclipseLinkJpaPlatformUi;

public class JavaConverterConversionHandler
	extends AbstractJavaMetadataConversionHandler
{
	public JavaConverterConversionHandler() {
		super();
	}

	@Override
	protected void convertJavaMetadata(JpaPlatformUi ui, JpaProject jpaProject) {
		((EclipseLinkJpaPlatformUi) ui).convertJavaConverterMetadataToGlobal(jpaProject);
	}
}
