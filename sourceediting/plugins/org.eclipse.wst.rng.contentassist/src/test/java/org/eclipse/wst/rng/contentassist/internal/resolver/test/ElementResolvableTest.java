package org.eclipse.wst.rng.contentassist.internal.resolver.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.eclipse.wst.rng.contentassist.IRngResolver;
import org.eclipse.wst.rng.contentassist.internal.resolver.test.AbstractInferenceTest.INodeTest;
import org.kohsuke.rngom.binary.NotAllowedPattern;
import org.kohsuke.rngom.binary.Pattern;
import org.w3c.dom.Element;
import org.w3c.dom.Node;



public final class ElementResolvableTest implements INodeTest {
	private IRngResolver resolver;

	public ElementResolvableTest(IRngResolver resolver) {
		this.resolver = resolver;
	}

	// @Override
	public boolean runOnNode(Node node) {
		return node instanceof Element;
	}

	// @Override
	public void execute(Node node) {
		Element element = (Element) node;
		Pattern pattern = resolver.getRngPatternForContent(element);
		assertNotNull(pattern);
		assertFalse("Element " + element + " resolved to NotAllowed.", pattern instanceof NotAllowedPattern);
	}
}