package org.eclipse.wst.rng.contentassist.internal.resolver;

import org.eclipse.wst.rng.contentassist.internal.resolver.test.AbstractInferenceTest;
import org.eclipse.wst.rng.contentassist.internal.resolver.test.ElementNamesAmongCompletionsTest;
import org.eclipse.wst.rng.contentassist.internal.resolver.test.ElementResolvableTest;
import org.junit.Test;



public class SimpleDocumentTest extends AbstractInferenceTest {
	public SimpleDocumentTest() {
		super("simple1.rnc");
	}
	
	@Override
	protected INodeTest[] getNodeTests() {
		return new INodeTest[] {
				new ElementResolvableTest(getResolver()),
				new ElementNamesAmongCompletionsTest(getCompletionProposalCalculator())};
	}
	
	@Test
	public void testSimpleDocument() {
		executeTestsOnDocument("simple1.xml");
	}
}
