package org.eclipse.wst.rng.contentassist.internal.resolver.test;

import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.eclipse.wst.rng.contentassist.ICompletionProposalCalculator;
import org.eclipse.wst.rng.contentassist.IQNameCompletionProposal;
import org.eclipse.wst.rng.contentassist.internal.DefaultQNameCompletionProposal;
import org.eclipse.wst.rng.contentassist.internal.function.ProposedName;
import org.eclipse.wst.rng.contentassist.internal.resolver.test.AbstractInferenceTest.INodeTest;
import org.w3c.dom.Element;
import org.w3c.dom.Node;



public class ElementNamesAmongCompletionsTest implements INodeTest {
	private ICompletionProposalCalculator proposalCalc;
	
	public ElementNamesAmongCompletionsTest(ICompletionProposalCalculator proposalCalc) {
		super();
		this.proposalCalc = proposalCalc;
	}

	// @Override
	public boolean runOnNode(Node node) {
		return node instanceof Element;
	}
	
	// @Override
	public void execute(Node node) {
		Element element = (Element)node;
		Set<IQNameCompletionProposal> completionProposals = proposalCalc.getElementNameProposals(element);
		assertTrue("Element " + element + " not found among completion proposals.", completionProposals.contains(qName(element)));
	}
	
	private IQNameCompletionProposal qName(Node node) {
		return new DefaultQNameCompletionProposal(new ProposedName(node.getNamespaceURI(), node.getLocalName(), null, null), null, null);
	}
}
