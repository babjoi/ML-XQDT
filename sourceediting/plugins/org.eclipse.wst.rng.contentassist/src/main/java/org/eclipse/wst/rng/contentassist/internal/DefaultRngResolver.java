/**********************************************************************
 * Copyright (c) 2009 Martin Schmied and others. All rights reserved.   This
 * program and the accompanying materials are made available under the terms of
 * the Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Martin Schmied - Initial API and implementation
 **********************************************************************/
package org.eclipse.wst.rng.contentassist.internal;

import org.eclipse.wst.rng.contentassist.IRngResolver;
import org.eclipse.wst.rng.contentassist.UnresolvableElementException;
import org.eclipse.wst.rng.contentassist.internal.function.AttributeDerivativeFunction;
import org.eclipse.wst.rng.contentassist.internal.function.AttributeEliminationFunction;
import org.eclipse.wst.rng.contentassist.internal.function.ChildPatternFunction;
import org.eclipse.wst.rng.contentassist.internal.function.ElementDerivativeFunction;
import org.eclipse.wst.rng.contentassist.internal.helper.CommonPattern;
import org.kohsuke.rngom.binary.Pattern;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;



public class DefaultRngResolver implements IRngResolver {	
	public Pattern getRngPattern(Element element) {
		return getRngPatternInternal(element);
	}
	
	public Pattern getRngPattern(Text unfinishedElement) {
		return getRngPatternInternal(unfinishedElement);
	}

	public Pattern getRngPatternForContent(Element element) {
		Pattern elementPattern = getRngPatternInternal(element);
		return (Pattern) elementPattern.apply(new ChildPatternFunction(element));
	}
	
	private Pattern getRngPatternInternal(Node node) {
		if (node instanceof Element || node instanceof Text) {
			return derivePatternFromParent(node);
		} else if (node instanceof Document) {
			Pattern boundPattern = (Pattern)node.getUserData(IRngResolver.KEY_RNG_PATTERN);
			if (boundPattern == null) {
				throw new UnresolvableElementException("Document node has no associated RELAX NG pattern.");
			} else {
				return boundPattern;
			}
		}
		throw new IllegalArgumentException("Can only retrieve patterns from Document and Element nodes.");
	}
	
	private Pattern derivePatternFromParent(Node node) {
		Node parentNode = node.getParentNode();
		if (parentNode == null) {
			throw new UnresolvableElementException("Node " + node + " has no parent node.");
		}
		Pattern residualPattern = getRngPatternInternal(parentNode); // recursive call
		if (parentNode instanceof Element) {
			Element parentElement = (Element) parentNode;
			residualPattern = (Pattern) residualPattern.apply(new ChildPatternFunction(parentElement));
			residualPattern = matchOrEliminateAttributes(parentElement, residualPattern);
		}
		// now we have pattern for the content of the parentNode without attribute patterns related to the parentNode
		
		NodeList siblingsAndNode = parentNode.getChildNodes();
		for (int i = 0; i < siblingsAndNode.getLength(); i++) {
			Node nodeOrItsSibling = siblingsAndNode.item(i);
			if (nodeOrItsSibling == node) {
				return residualPattern; // here we should have the pattern for the node itself
			}
			if (nodeOrItsSibling instanceof Element) {
				Element sibling = (Element) nodeOrItsSibling;
				residualPattern = (Pattern) residualPattern.apply(new ElementDerivativeFunction(sibling)); // apply derivative function with respect to the preceding sibling
			}
		}
		
		return CommonPattern.NOT_ALLOWED_PATTERN;
	}
	
	private Pattern matchOrEliminateAttributes(Element element, Pattern elementContentPattern) {
		Pattern residualPattern = matchAttributes(element, elementContentPattern, null);
		residualPattern = (Pattern) residualPattern.apply(new AttributeEliminationFunction());
		return residualPattern;
	}
	
	// @Override
	public Pattern matchAttributes(Element element, Pattern elementContentPattern, Attr except) {
		return (Pattern) elementContentPattern.apply(new AttributeDerivativeFunction(element, except));		
	}
}
