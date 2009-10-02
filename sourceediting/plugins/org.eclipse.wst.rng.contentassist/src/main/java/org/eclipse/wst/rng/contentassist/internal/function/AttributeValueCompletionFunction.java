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
import org.kohsuke.rngom.binary.Pattern;
import org.w3c.dom.Attr;

public class AttributeValueCompletionFunction extends AbstractValueCompletionFunction {
	private QName contextAttributeName;
	
	public AttributeValueCompletionFunction(Attr contextAttribute, String[] ids) {
		super(ids);
		
		contextAttributeName = new QName(
				contextAttribute.getNamespaceURI(),
				contextAttribute.getLocalName());
	}
	
	@Override
	public Object caseAttribute(AttributePattern p) {
		if (p.getNameClass().contains(contextAttributeName)) {
			Pattern content = p.getContent();
			return content.apply(this);
		}
		return null;
	}
	
	@Override
	public Object caseGroup(GroupPattern p) {
		return caseBranchingPattern(p);
	}
}
