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
/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.xquery.sse.core.internal.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.wst.sse.core.internal.model.AbstractStructuredModel;
import org.eclipse.wst.sse.core.internal.provisional.IndexedRegion;
import org.eclipse.wst.sse.core.internal.provisional.events.IStructuredDocumentListener;
import org.eclipse.wst.sse.core.internal.provisional.events.NewDocumentEvent;
import org.eclipse.wst.sse.core.internal.provisional.events.NoChangeEvent;
import org.eclipse.wst.sse.core.internal.provisional.events.RegionChangedEvent;
import org.eclipse.wst.sse.core.internal.provisional.events.RegionsReplacedEvent;
import org.eclipse.wst.sse.core.internal.provisional.events.StructuredDocumentRegionsReplacedEvent;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocument;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocumentRegion;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.ASTClauses;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.ASTFunctionDecl;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.ASTModule;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.ASTTypeswitch;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.IASTNode;
import org.eclipse.wst.xquery.sse.core.internal.sdregions.XQueryStructuredDocumentRegion;

/**
 * Structured model for XQuery document.
 * 
 * For now it's just a placeholder and this class does nothing.
 * 
 * @author <a href="villard@us.ibm.com">Lionel Villard</a>
 */
public class XQueryStructuredModel extends AbstractStructuredModel implements IStructuredDocumentListener {

	// State

	/** Model AST builder */
	final protected ModelBuilder builder;

	/** Root AST node (module) */
	protected ASTModule module;

	// Constructors

	public XQueryStructuredModel() {
		builder = getContributionModelBuilder();
	}

	// Methods

	/** Gets list of in-scope variable region definition */
	public List<String> getInScopeVariables(IASTNode node) {
		if (node != null) {
			List<String> vars = new ArrayList<String>();
			getInScopeVariables(node, vars);
			return vars;
		}

		return null;
	}

	/**
	 * @param node
	 * @param vars
	 * @return
	 */
	private void getInScopeVariables(IASTNode node, List<String> vars) {
		IASTNode parent = node.getASTParent();
		while (parent != null) {
			switch (parent.getType()) {
			case IASTNode.FLWOR:
			case IASTNode.QUANTIFIED:
				final ASTClauses clauses = (ASTClauses) parent;

				// Compute the last binding expression to include
				int lastBinding = 0;
				while (lastBinding < clauses.getBindingExprCount()) {
					if (clauses.getBindingExpr(lastBinding) == node) {  
						break;
					}
					lastBinding ++;
				}
				lastBinding--;
				while (lastBinding >= 0) {
					IStructuredDocumentRegion var = clauses.getBindingVariable(lastBinding--);
					vars.add(var.getFullText().trim());
				}
				break;
			case IASTNode.FUNCTIONDECL:
				final ASTFunctionDecl decl = (ASTFunctionDecl) parent;
				// Include parameters..
				for (int i = decl.getParamCount() - 1; i >= 0; i --)
				{
					vars.add(decl.getParamNameAt(i).getFullText().trim());
				}
				break;
			case IASTNode.TYPESWITCH:
				final ASTTypeswitch ts = (ASTTypeswitch) parent;
				
				// Identify which case
				if (ts.getDefaultCaseExpr() == node)
				{
					IStructuredDocumentRegion var = ts.getDefaultCaseVarname();
					if (var != null)
						vars.add(var.getFullText().trim());
				} else
				{
					for (int i = ts.getCaseCount() - 1; i >= 0; i --)
					{
						if (ts.getCaseExpr(i) == node)
						{
							IStructuredDocumentRegion var = ts.getCaseVarname(i);
							if (var != null)
								vars.add(var.getFullText().trim());
							break;
						}
					}
				}
				
				break;
			case IASTNode.VARDECL:
				// Include variable declarations preceding this one...
				// TODO
			}
			node = parent;
			parent = parent.getASTParent();
		}
	}

	/** Get the AST node attached to the given region */
	public IASTNode getASTNode(IStructuredDocumentRegion region) {
		if (region instanceof XQueryStructuredDocumentRegion) {
			final XQueryStructuredDocumentRegion xregion = (XQueryStructuredDocumentRegion) region;
			return xregion.getASTNode();
		}
		return null;
	}

	// Overrides

	@Override
	public IndexedRegion getIndexedRegion(int offset) {
		return null;

		// return (IndexedRegion) fStructuredDocument
		// .getRegionAtCharacterOffset(offset);
	}

	@Override
	public void setStructuredDocument(IStructuredDocument newStructuredDocument) {
		if (fStructuredDocument != null)
			fStructuredDocument.removeDocumentChangingListener(this);

		super.setStructuredDocument(newStructuredDocument);

		if (fStructuredDocument != null) {
			fStructuredDocument.addDocumentChangingListener(this);

			// Parse..
			if (newStructuredDocument != null)
				module = builder.reparseQuery(module, fStructuredDocument.getFirstStructuredDocumentRegion(), 0,
						fStructuredDocument.getLength());
		}
	}

	// Implements IStructuredDocumentListener

	public void newModel(NewDocumentEvent event) {
		module = builder.reparseQuery(module, event.getStructuredDocument().getFirstStructuredDocumentRegion(), 0,
				event.getDocument().getLength());
	}

	public void noChange(NoChangeEvent event) {
	}

	public void nodesReplaced(StructuredDocumentRegionsReplacedEvent event) {
		module = builder.reparseQuery(module, event.getStructuredDocument().getFirstStructuredDocumentRegion(), event
				.getOffset(), event.getLength());
	}

	public void regionChanged(RegionChangedEvent event) {
		// AST does not have to be rebuilt.
		// The rest is handled locally (in regions)
	}

	public void regionsReplaced(RegionsReplacedEvent event) {
		// TODO: see if we can do better here.

		module = builder.reparseQuery(module, event.getStructuredDocument().getFirstStructuredDocumentRegion(), event
				.getOffset(), event.getLength());
	}

	// ModelBuilder Extension point
	

	/**
	 * Gets the XQuery model builder. Allow other plugins to provide an alternative
	 * @return
	 */
	protected ModelBuilder getContributionModelBuilder() {
		ModelBuilder modelBuilder = null;

		IExtensionRegistry registry = Platform.getExtensionRegistry();
		IExtensionPoint point = registry.getExtensionPoint("org.eclipse.wst.xquery.sse.core", "modelBuilder");
		IExtension[] extensions = point.getExtensions();
		if (extensions.length > 0) {
			IConfigurationElement[] elements = extensions[0].getConfigurationElements();
			if (elements.length > 0) {
				try {
					modelBuilder = (ModelBuilder) elements[0].createExecutableExtension("class");
				} catch (CoreException e) {
					// TODO
				}
			}
		}

		return modelBuilder == null ? new ModelBuilder() : modelBuilder;
	}
}
