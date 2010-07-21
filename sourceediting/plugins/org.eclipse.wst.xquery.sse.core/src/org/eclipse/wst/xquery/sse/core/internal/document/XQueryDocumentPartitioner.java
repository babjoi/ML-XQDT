/*******************************************************************************
 * Copyright (c) 2005, 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.xquery.sse.core.internal.document;

import org.eclipse.wst.sse.core.internal.text.rules.StructuredTextPartitioner;

/**
 * XQuery document partitioner.
 * 
 * For now, all regions live in a single big partition.
 * 
 * @author <a href="mailto:villard@us.ibm.com">Lionel Villard</a>
 */
@SuppressWarnings("restriction")
public class XQueryDocumentPartitioner extends StructuredTextPartitioner {

	// Partition types

	/** Default XQuery partition */
	final public static String DEFAULT_XQUERY_PARTITION = "org.eclipse.wst.xquery.DEFAULT";

	// Overrides

	@Override
	public String getDefaultPartitionType() {
		return DEFAULT_XQUERY_PARTITION;
	}

}
