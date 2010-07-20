/*******************************************************************************
 * Copyright (c) 2005, 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.xquery.sse.core.internal.parser;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.wst.sse.core.internal.ltk.parser.BlockTokenizer;
import org.eclipse.wst.sse.core.internal.ltk.parser.RegionParser;
import org.eclipse.wst.sse.core.internal.ltk.parser.StructuredDocumentRegionHandler;
import org.eclipse.wst.sse.core.internal.ltk.parser.StructuredDocumentRegionParser;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocumentRegion;
import org.eclipse.wst.sse.core.internal.provisional.text.ITextRegion;
import org.eclipse.wst.sse.core.internal.provisional.text.ITextRegionList;
import org.eclipse.wst.xquery.sse.core.XQDTPlugin;
import org.eclipse.wst.xquery.sse.core.internal.regions.XQueryRegions;
import org.eclipse.wst.xquery.sse.core.internal.sdregions.ModuleDeclStructuredDocumentRegion;
import org.eclipse.wst.xquery.sse.core.internal.sdregions.SDRegionUtils;
import org.eclipse.wst.xquery.sse.core.internal.sdregions.StructuredDocumentRegionFactory;
import org.eclipse.wst.xquery.sse.core.internal.sdregions.VersionDeclStructuredDocumentRegion;
import org.eclipse.wst.xquery.sse.core.internal.sdregions.XQueryStructuredDocumentRegion;

/**
 * Parser for XQuery files.
 * 
 * <p>
 * Creates an ordered list of {@link IStructuredDocumentRegion structured
 * regions}.
 * 
 * <p>
 * Namespace prefixes are resolved at this level.
 * 
 * 
 * @author <a href="villard@us.ibm.com">Lionel Villard</a>
 */
public class XQueryRegionParser implements StructuredDocumentRegionParser, StatementTypes {

	// Constants

	/** Regions starting a new group */
	final protected static Map<String, StructuredDocumentRegionFactory> STARTING_GROUP_FACTORIES = new HashMap<String, StructuredDocumentRegionFactory>();
	{
		STARTING_GROUP_FACTORIES.put(XQueryRegions.DOLLAR, XQueryStructuredDocumentRegion.Factory.INSTANCE);
		STARTING_GROUP_FACTORIES.put(XQueryRegions.ST_EMPTY, XQueryStructuredDocumentRegion.Factory.INSTANCE);
		STARTING_GROUP_FACTORIES.put(XQueryRegions.ST_ITEM, XQueryStructuredDocumentRegion.Factory.INSTANCE);
		STARTING_GROUP_FACTORIES.put(XQueryRegions.FUNCTIONNAME, XQueryStructuredDocumentRegion.Factory.INSTANCE);
		STARTING_GROUP_FACTORIES.put(XQueryRegions.KW_ORDERED, XQueryStructuredDocumentRegion.Factory.INSTANCE);
		STARTING_GROUP_FACTORIES.put(XQueryRegions.KW_UNORDERED, XQueryStructuredDocumentRegion.Factory.INSTANCE);
		STARTING_GROUP_FACTORIES.put(XQueryRegions.PATH_PARENT, XQueryStructuredDocumentRegion.Factory.INSTANCE);
		STARTING_GROUP_FACTORIES.put(XQueryRegions.PATH_ANCESTOR, XQueryStructuredDocumentRegion.Factory.INSTANCE);
		STARTING_GROUP_FACTORIES.put(XQueryRegions.PATH_ANCESTOR_OR_SELF,
				XQueryStructuredDocumentRegion.Factory.INSTANCE);
		STARTING_GROUP_FACTORIES.put(XQueryRegions.PATH_ATTRIBUTE, XQueryStructuredDocumentRegion.Factory.INSTANCE);
		STARTING_GROUP_FACTORIES.put(XQueryRegions.PATH_CHILD, XQueryStructuredDocumentRegion.Factory.INSTANCE);
		STARTING_GROUP_FACTORIES.put(XQueryRegions.PATH_DESCENDANT, XQueryStructuredDocumentRegion.Factory.INSTANCE);
		STARTING_GROUP_FACTORIES.put(XQueryRegions.PATH_DESCENDANT_OR_SELF,
				XQueryStructuredDocumentRegion.Factory.INSTANCE);
		STARTING_GROUP_FACTORIES.put(XQueryRegions.PATH_FOLLOWING, XQueryStructuredDocumentRegion.Factory.INSTANCE);
		STARTING_GROUP_FACTORIES.put(XQueryRegions.PATH_FOLLOWING_SIBLING,
				XQueryStructuredDocumentRegion.Factory.INSTANCE);
		STARTING_GROUP_FACTORIES.put(XQueryRegions.PATH_PRECEDING, XQueryStructuredDocumentRegion.Factory.INSTANCE);
		STARTING_GROUP_FACTORIES.put(XQueryRegions.PATH_PRECEDING_SIBLING,
				XQueryStructuredDocumentRegion.Factory.INSTANCE);
		STARTING_GROUP_FACTORIES.put(XQueryRegions.PATH_SELF, XQueryStructuredDocumentRegion.Factory.INSTANCE);
		STARTING_GROUP_FACTORIES.put(XQueryRegions.XML_TAG_OPEN, XQueryStructuredDocumentRegion.Factory.INSTANCE);
		STARTING_GROUP_FACTORIES.put(XQueryRegions.KW_MODULE, ModuleDeclStructuredDocumentRegion.Factory.INSTANCE);
		STARTING_GROUP_FACTORIES.put(XQueryRegions.KW_XQUERY, VersionDeclStructuredDocumentRegion.Factory.INSTANCE);
		STARTING_GROUP_FACTORIES.put(XQueryRegions.KW_ORDER, XQueryStructuredDocumentRegion.Factory.INSTANCE);
		STARTING_GROUP_FACTORIES.put(XQueryRegions.KW_STABLE, XQueryStructuredDocumentRegion.Factory.INSTANCE);
		STARTING_GROUP_FACTORIES.put(XQueryRegions.KW_EMPTY, XQueryStructuredDocumentRegion.Factory.INSTANCE);

	}

	/** Regions belonging to an existing group */
	final protected static Map<String, String[]> IN_GROUP = new HashMap<String, String[]>();
	{
		IN_GROUP.put(XQueryRegions.DOLLAR, new String[] { XQueryRegions.VARREF });
		IN_GROUP.put(XQueryRegions.ST_EMPTY, new String[] { XQueryRegions.LPAR, XQueryRegions.RPAR });
		IN_GROUP.put(XQueryRegions.ST_ITEM, new String[] { XQueryRegions.LPAR, XQueryRegions.RPAR });
		IN_GROUP.put(XQueryRegions.FUNCTIONNAME, new String[] { XQueryRegions.LPAR });
		IN_GROUP.put(XQueryRegions.KW_ORDERED, new String[] { XQueryRegions.LCURLY });
		IN_GROUP.put(XQueryRegions.KW_UNORDERED, new String[] { XQueryRegions.LCURLY });
		IN_GROUP.put(XQueryRegions.PATH_PARENT, new String[] { XQueryRegions.COLONCOLON });
		IN_GROUP.put(XQueryRegions.PATH_ANCESTOR, new String[] { XQueryRegions.COLONCOLON });
		IN_GROUP.put(XQueryRegions.PATH_ANCESTOR_OR_SELF, new String[] { XQueryRegions.COLONCOLON });
		IN_GROUP.put(XQueryRegions.PATH_ATTRIBUTE, new String[] { XQueryRegions.COLONCOLON });
		IN_GROUP.put(XQueryRegions.PATH_CHILD, new String[] { XQueryRegions.COLONCOLON });
		IN_GROUP.put(XQueryRegions.PATH_DESCENDANT, new String[] { XQueryRegions.COLONCOLON });
		IN_GROUP.put(XQueryRegions.PATH_DESCENDANT_OR_SELF, new String[] { XQueryRegions.COLONCOLON });
		IN_GROUP.put(XQueryRegions.PATH_FOLLOWING, new String[] { XQueryRegions.COLONCOLON });
		IN_GROUP.put(XQueryRegions.PATH_FOLLOWING_SIBLING, new String[] { XQueryRegions.COLONCOLON });
		IN_GROUP.put(XQueryRegions.PATH_PRECEDING, new String[] { XQueryRegions.COLONCOLON });
		IN_GROUP.put(XQueryRegions.PATH_PRECEDING_SIBLING, new String[] { XQueryRegions.COLONCOLON });
		IN_GROUP.put(XQueryRegions.PATH_SELF, new String[] { XQueryRegions.COLONCOLON });
		IN_GROUP.put(XQueryRegions.XML_TAG_OPEN, new String[] { XQueryRegions.XML_TAG_NAME });

		IN_GROUP.put(XQueryRegions.KW_MODULE, new String[] { XQueryRegions.EQUALS, XQueryRegions.KW_NAMESPACE,
				XQueryRegions.NCNAME, XQueryRegions.SEPARATOR, XQueryRegions.URILITERAL });

		IN_GROUP.put(XQueryRegions.KW_XQUERY, new String[] { XQueryRegions.KW_ENCODING, XQueryRegions.KW_VERSION,
				XQueryRegions.SEPARATOR, XQueryRegions.STRINGLITERAL });

		IN_GROUP.put(XQueryRegions.KW_ORDER, new String[] { XQueryRegions.KW_BY });
		IN_GROUP.put(XQueryRegions.KW_STABLE, new String[] { XQueryRegions.KW_BY, XQueryRegions.KW_ORDER });
		IN_GROUP.put(XQueryRegions.KW_EMPTY, new String[] { XQueryRegions.KW_GREATEST, XQueryRegions.KW_LEAST });

	}
	// State

	/** Region Handlers */
	protected List<StructuredDocumentRegionHandler> fStructuredDocumentRegionHandlers;

	/** XQuery Tokenizer */
	final private ITokenizer fTokenizer;

	/** Stack of statement types */
	private IntStack fTypes;

	/** Current structured document region (if any) */
	private XQueryStructuredDocumentRegion fCurrentStructuredDocumentRegion;

	/** Should grouping be interrupted? */
	private boolean interruptGroup;

	// Constructor

	public XQueryRegionParser() {
		fTokenizer = getContributionTokenizer();
		fTypes = new IntStack();
	}

	// Methods

	/** Receive the notification of the beginning of an XQuery statement */
	public void start(int type) {
		fTypes.push(type);
	}

	/** Receive the notification of the beginning of an XQuery statement */
	public void end() {
		// Be conservative since an error might have occurred in the tokenizer
		if (!fTypes.empty())
			fTypes.pop();
		else {
			XQDTPlugin.log(IStatus.WARNING, "Unbalanced statement");
		} 
	}

	// Implements StructuredDocumentRegionParser

	public void addStructuredDocumentRegionHandler(StructuredDocumentRegionHandler handler) {
		if (fStructuredDocumentRegionHandlers == null)
			fStructuredDocumentRegionHandlers = new LinkedList<StructuredDocumentRegionHandler>();

		fStructuredDocumentRegionHandlers.add(handler);
	}

	public void removeStructuredDocumentRegionHandler(StructuredDocumentRegionHandler handler) {
		if (fStructuredDocumentRegionHandlers != null)
			fStructuredDocumentRegionHandlers.remove(handler);

	}

	public void resetHandlers() {
		if (fStructuredDocumentRegionHandlers != null) {
			int size = fStructuredDocumentRegionHandlers.size();
			for (int i = 0; i < size; i++)
				fStructuredDocumentRegionHandlers.get(i).resetNodes();
		}
	}

	public IStructuredDocumentRegion getDocumentRegions() {
		ITextRegion region;
		IStructuredDocumentRegion head = null;
		XQueryStructuredDocumentRegion last = null;
		XQueryStructuredDocumentRegion current = null;

		while ((region = getNextRegion()) != null) {
			current = getStructuredDocumentRegion(region);
			current.addRegion(region);

			if (current.getNumberOfRegions() == 1) {
				current.setLength(region.getLength());
				current.setStart(region.getStart());
				region.adjustStart(-region.getStart());

				if (head == null)
					head = current;

				if (last != null) {
					last.setNext(current);
					current.setPrevious(last);
				}

				last = current;
			} else {
				region.adjustStart(-region.getStart() + current.getLength());
				current.setLength(current.getLength() + region.getLength());
			}
		}

		primReset();
		return head;
	}

	public List getRegions() {
		IStructuredDocumentRegion currentNode = null;

		if (!getTokenizer().isEOF())
			currentNode = getDocumentRegions();

		List allRegions = new ArrayList();
		while (currentNode != null) {
			ITextRegionList nodeRegions = currentNode.getRegions();
			for (int i = 0; i < nodeRegions.size(); i++) {
				allRegions.add(nodeRegions.get(i));
			}
			currentNode = currentNode.getNext();
		}

		return allRegions;
	}

	public RegionParser newInstance() {
		return new XQueryRegionParser();
	}

	public void reset(Reader reader) {
		reset(reader, 0);
	}

	public void reset(String input) {
		reset(input, 0);
	}

	public void reset(Reader reader, int offset) {
		fTokenizer.reset(reader, offset);
	}

	public void reset(String input, int offset) {
		reset(new StringReader(input), offset);
	}

	// Helpers

	protected ITokenizer getTokenizer() {
		return fTokenizer;
	}

	protected void primReset() {
		fTypes.reset();
		fCurrentStructuredDocumentRegion = null;
		fTokenizer.reset(new char[0]);
	}

	protected ITextRegion getNextRegion() {
		try {
			return fTokenizer.getNextToken();
		} catch (Exception e) {
			e.printStackTrace();
			//	Logger.logException(getClass().getName() + ": input could not be parsed correctly at position " + getTokenizer().getOffset() + " (" + e.getLocalizedMessage() + ")", e); //$NON-NLS-3$//$NON-NLS-2$//$NON-NLS-1$
		}
		return null;
	}

	/**
	 * Gets region based on statement context
	 * 
	 * @param region
	 * 
	 * @return
	 */
	private XQueryStructuredDocumentRegion getStructuredDocumentRegion(ITextRegion region) {
		if (inGroup(region))
			return fCurrentStructuredDocumentRegion;

		if (startGroup(region))
			return fCurrentStructuredDocumentRegion;

		fCurrentStructuredDocumentRegion = null;
		return new XQueryStructuredDocumentRegion();
	}

	/**
	 * @param region
	 * @return
	 */
	private boolean startGroup(ITextRegion region) {
		final StructuredDocumentRegionFactory factory = STARTING_GROUP_FACTORIES.get(region.getType());
		if (factory != null) {
			fCurrentStructuredDocumentRegion = factory.create();
			return true;
		}

		// For now, treat 'declare' and 'import' a bit differently
		if (region.getType() == XQueryRegions.KW_DECLARE || region.getType() == XQueryRegions.KW_IMPORT)
		{
			fCurrentStructuredDocumentRegion = new XQueryStructuredDocumentRegion();
			interruptGroup = false;
			return true;
		}

		return false;
	}

	/**
	 * @param region
	 * @return
	 */
	private boolean inGroup(ITextRegion region) {
		if (fCurrentStructuredDocumentRegion != null) {
			final String currentType = fCurrentStructuredDocumentRegion.getType();

			String[] validRegions = IN_GROUP.get(currentType);
			if (validRegions != null && Arrays.binarySearch(validRegions, region.getType()) >= 0) {
				return true;
			}

			if (currentType == XQueryRegions.KW_DECLARE || currentType == XQueryRegions.KW_IMPORT) {
				if (interruptGroup || region.getType() == XQueryRegions.SEPARATOR)
					return false;

				if (region.getType() == XQueryRegions.KW_VARIABLE || region.getType() == XQueryRegions.KW_FUNCTION)
					interruptGroup = true; // Interrupt for the next token

				return true;
			}
		}

		return false;
	}

	/**
	 * Gets the XQuery tokenizer. Allow other plugins to provide an alternative
	 * tokenizer.
	 * 
	 * @return
	 */
	protected ITokenizer getContributionTokenizer() {
		ITokenizer tokenizer = null;

		IExtensionRegistry registry = Platform.getExtensionRegistry();
		IExtensionPoint point = registry.getExtensionPoint("org.eclipse.wst.xquery.sse.core", "tokenizer");
		IExtension[] extensions = point.getExtensions();
		if (extensions.length > 0) {
			IConfigurationElement[] elements = extensions[0].getConfigurationElements();
			if (elements.length > 0) {
				try {
					tokenizer = (ITokenizer) elements[0].createExecutableExtension("class");
				} catch (CoreException e) {
					// TODO
				}
			}
		}

		if (tokenizer == null)
			tokenizer = new XQueryTokenizer();

		tokenizer.setParser(this);
		return tokenizer;
	}
}
