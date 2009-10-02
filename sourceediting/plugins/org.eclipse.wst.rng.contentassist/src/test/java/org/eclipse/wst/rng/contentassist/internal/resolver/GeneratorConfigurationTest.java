package org.eclipse.wst.rng.contentassist.internal.resolver;

import org.eclipse.wst.rng.contentassist.internal.resolver.test.AbstractInferenceTest;
import org.eclipse.wst.rng.contentassist.internal.resolver.test.AttributeNamesAmongCompletionsTest;
import org.eclipse.wst.rng.contentassist.internal.resolver.test.ElementNamesAmongCompletionsTest;
import org.eclipse.wst.rng.contentassist.internal.resolver.test.ElementResolvableTest;
import org.junit.Test;



public class GeneratorConfigurationTest extends AbstractInferenceTest {
	public GeneratorConfigurationTest() {
		super("generatorConfiguration.rnc");
	}
	
	@Override
	protected INodeTest[] getNodeTests() {
		return new INodeTest[] {
				new ElementResolvableTest(getResolver()),
				new AttributeNamesAmongCompletionsTest(getCompletionProposalCalculator()),
				new ElementNamesAmongCompletionsTest(getCompletionProposalCalculator())};
	}
	
	@Test
	public void testGeneratorConfiguration() {
		executeTestsOnDocument("generatorConfiguration.xml");
	}
}
