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

import javax.xml.namespace.QName;

import org.kohsuke.rngom.binary.AttributePattern;
import org.kohsuke.rngom.binary.GroupPattern;
import org.kohsuke.rngom.nc.SimpleNameClass;



public class AttributeNameCompletionFunction extends AbstractQNameCompletionFunction {

	@Override
	public Object caseAttribute(AttributePattern p) {
		if (p.getNameClass() instanceof SimpleNameClass) {
			QName name = ((SimpleNameClass) p.getNameClass()).name;
			String doc = p.getDocumentation() != null ? p.getDocumentation().getText() : null;
			return new ProposedName[] {new ProposedName(name.getNamespaceURI(), name.getLocalPart(), name.getPrefix(), doc)};
		}
		return null;
	}
	
	@Override
	public Object caseGroup(GroupPattern p) {
		return caseBranchingPattern(p, false);
	}

}
