/**********************************************************************
 * Copyright (c) 2009 Martin Schmied and others. All rights reserved.   This
 * program and the accompanying materials are made available under the terms of
 * the Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Martin Schmied - Initial API and implementation
 **********************************************************************/
package org.eclipse.wst.rng.contentassist.internal.function;

import org.kohsuke.rngom.binary.GroupPattern;
import org.kohsuke.rngom.binary.Pattern;

public class ElementValueCompletionFunction extends AbstractValueCompletionFunction {
	public ElementValueCompletionFunction(String[] ids) {
		super(ids);
	}
	
	@Override
	public Object caseGroup(GroupPattern p) {
		Pattern operand1 = p.getOperand1();
		if (operand1.isNullable()) {
			return caseBranchingPattern(p);
		} else {
			return p.getOperand1().apply(this);
		}
	}
}
