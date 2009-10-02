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

import org.kohsuke.rngom.binary.Pattern;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

/**
 * 
 * 
 * @author Martin Schmied, schmeedy.com
 */
public interface IRngResolver {
	static final String KEY_RNG_PATTERN = "com.schmeedy.relaxng.contentassist:element-pattern";
	
	Pattern getRngPattern(Element element);
	
	Pattern getRngPattern(Text unfinishedElement);
	
	Pattern getRngPatternForContent(Element element);
	
	Pattern matchAttributes(Element element, Pattern elementContentPattern, Attr except);
}
