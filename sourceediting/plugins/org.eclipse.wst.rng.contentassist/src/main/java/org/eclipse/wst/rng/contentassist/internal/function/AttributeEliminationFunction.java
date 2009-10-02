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

import org.eclipse.wst.rng.contentassist.internal.helper.CommonPattern;
import org.kohsuke.rngom.binary.AttributePattern;
import org.kohsuke.rngom.binary.ChoicePattern;
import org.kohsuke.rngom.binary.NotAllowedPattern;
import org.kohsuke.rngom.binary.OneOrMorePattern;
import org.kohsuke.rngom.binary.Pattern;


public class AttributeEliminationFunction extends AbstractAttributeFunction {
	@Override
	public Object caseAttribute(AttributePattern p) {
		return patternBuilder.makeNotAllowed();
	}
	
	@Override
	public Object caseChoice(ChoicePattern p) {
		Pattern derivative1 = (Pattern) p.getOperand1().apply(this);
		Pattern derivative2 = (Pattern) p.getOperand2().apply(this);
		return patternBuilder.makeChoice(derivative1, derivative2);
	}
	
	// mostly because of attributes with open name classes, which can be found inside oneOrMore
	@Override
	public Object caseOneOrMore(OneOrMorePattern p) {
		Pattern derivative = (Pattern) p.getOperand().apply(this);
		if (derivative instanceof NotAllowedPattern) {
			return CommonPattern.NOT_ALLOWED_PATTERN;
		} else {
			return p;
		}
	}
}
