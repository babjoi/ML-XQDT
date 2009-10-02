/**********************************************************************
 * Copyright (c) 2009 Martin Schmied and others. All rights reserved.   This
 * program and the accompanying materials are made available under the terms of
 * the Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Martin Schmied - Initial API and implementation
 **********************************************************************/
package org.eclipse.wst.rng.contentassist;

import org.eclipse.wst.rng.contentassist.internal.DefaultCompletionProposalCalculator;
import org.eclipse.wst.rng.contentassist.internal.DefaultRngResolver;
import org.eclipse.wst.rng.contentassist.internal.DefaultSchemaBinder;

public class RngTools {
	private static final RngTools INSTANCE = new RngTools();
	
	public static RngTools getInstance() {
		return INSTANCE;
	}
	
	private IRngResolver rngResolver;
	
	private ICompletionProposalCalculator completionProposalCalculator;
	
	private IRngSchemaBinder schemaBinder;
	
	private RngTools() {
		rngResolver = new DefaultRngResolver();
		completionProposalCalculator = new DefaultCompletionProposalCalculator(rngResolver);
		schemaBinder = DefaultSchemaBinder.getInstance();
	}
	
	public IRngResolver getRngResolver() {
		return rngResolver;
	}
	
	public ICompletionProposalCalculator getCompletionProposalCalculator() {
		return completionProposalCalculator;
	}
	
	public IRngSchemaBinder getSchemaBinder() {
		return schemaBinder;
	}
}
