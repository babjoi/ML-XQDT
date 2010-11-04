/*******************************************************************************
 * Copyright (c) Standards for Technology in Automotive Retail and others
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     David Carver -  - initial API and implementation
 *     Lionel Villard - XQDT
 *******************************************************************************/

package org.eclipse.wst.xquery.ui.tests.style;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocumentExtension3;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextViewerExtension5;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.TextUtilities;
import org.eclipse.wst.sse.core.internal.util.Debug;
import org.eclipse.wst.sse.ui.internal.provisional.style.Highlighter;
import org.eclipse.wst.sse.ui.internal.provisional.style.LineStyleProvider;
import org.eclipse.wst.xquery.sse.core.text.IXQDTPartitions;
import org.eclipse.wst.xquery.ui.tests.AbstractXQDTUITest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests about syntax highlighting
 */
@SuppressWarnings("restriction")
public class TestXQDTLineStyleProvider extends AbstractXQDTUITest {

	// Constructors

	public TestXQDTLineStyleProvider() {

	}

	// Methods

	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();

	}

	@Test
	public void testHasLineStyleProvider() throws Exception {
		setUpTest("simple1.xq");

		LineStyleProvider[] lineStyleProviders = getLineStyleProviders();
		assertNotNull("No line style providers found.", lineStyleProviders);
		assertEquals("Wrong number of providers", 1, lineStyleProviders.length);
	}

	@SuppressWarnings("rawtypes")
	@Test
	public void testPrepareRegion() throws Exception {
		setUpTest("simple1.xq");

		LineStyleProvider provider = getLineStyleProvider();

		provider.init(getStructuredDocument(), new Highlighter());

		ITypedRegion[] partitions = setupPartitions();

		//assertTrue("No Partitions found.", partitions.length > 0);

		ArrayList holdStyleResults = new ArrayList();
		applyStyles(provider, partitions, holdStyleResults);
		assertFalse("No styles applied.", holdStyleResults.isEmpty());
		assertEquals("Unexpected StyleRange size", 43, holdStyleResults.size());
	}

	// Helpers

	/** Gets all line providers */
	protected LineStyleProvider[] getLineStyleProviders() {
		LineStyleProvider[] lineStyleProviders = configuration
				.getLineStyleProviders(sourceViewer,
						IXQDTPartitions.XQUERY_DEFAULT);
		return lineStyleProviders;
	}

	/** Gets the first line style provider */
	private LineStyleProvider getLineStyleProvider() {
		LineStyleProvider[] lineStyleProviders = getLineStyleProviders();
		LineStyleProvider lineStyleProvider = lineStyleProviders[0];

		return lineStyleProvider;
	}

	private IRegion getDocumentRangeFromWidgetRange(int offset, int length) {
		IRegion styleRegion = null;
		if (sourceViewer instanceof ITextViewerExtension5) {
			ITextViewerExtension5 extension = sourceViewer;
			styleRegion = extension.widgetRange2ModelRange(new Region(offset,
					length));
		} else {
			IRegion vr = null;
			if (sourceViewer != null)
				vr = sourceViewer.getVisibleRegion();
			else
				vr = new Region(0, document.getLength());

			if (offset <= vr.getLength()) {
				styleRegion = new Region(offset + vr.getOffset(), length);
			}
		}
		return styleRegion;
	}

	@SuppressWarnings("rawtypes")
	private void applyStyles(LineStyleProvider provider,
			ITypedRegion[] partitions, ArrayList holdStyleResults) {
		for (int i = 0; i < partitions.length; i++) {
			ITypedRegion currentPartition = partitions[i];
			boolean handled = provider.prepareRegions(currentPartition,
					currentPartition.getOffset(), currentPartition.getLength(),
					holdStyleResults);
			if (Debug.syntaxHighlighting) {
				if (!handled) {
					System.out
							.println("Did not handle highlighting in Highlighter inner while"); //$NON-NLS-1$
				}
			}
		}
	}

	private ITypedRegion[] setupPartitions() throws BadLocationException {
		int startOffset = document.getFirstStructuredDocumentRegion()
				.getStartOffset();
		int endLineLength = document.getLength();

		IRegion styleRegion = getDocumentRangeFromWidgetRange(startOffset,
				endLineLength);
		ITypedRegion[] partitions = TextUtilities.computePartitioning(document,
				IDocumentExtension3.DEFAULT_PARTITIONING, styleRegion.getOffset(),
				styleRegion.getLength(), false);
		return partitions;
	}

}
