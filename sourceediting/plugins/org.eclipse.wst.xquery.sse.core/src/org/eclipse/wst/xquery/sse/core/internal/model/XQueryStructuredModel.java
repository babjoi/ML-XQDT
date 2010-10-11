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
import org.eclipse.wst.sse.core.internal.provisional.text.ITextRegion;
import org.eclipse.wst.validation.internal.provisional.core.IMessage;
import org.eclipse.wst.xquery.core.IXQDTCorePreferences;
import org.eclipse.wst.xquery.core.IXQDTLanguageConstants;
import org.eclipse.wst.xquery.core.XQDTCorePlugin;
import org.eclipse.wst.xquery.sse.core.internal.ValidationHelper;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.ASTModule;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.IASTNode;
import org.eclipse.wst.xquery.sse.core.internal.sdregions.XQueryStructuredDocumentRegion;

/**
 * Structured model for XQuery document.
 * 
 * @author <a href="villard@us.ibm.com">Lionel Villard</a>
 */
public class XQueryStructuredModel extends AbstractStructuredModel implements IStructuredDocumentListener {

    // State

    /** Model AST builder */
    final protected ModelBuilder builder;

    /** Root AST node (module) */
    private ASTModule module;

    /** Current language */
    protected int language;

    /** List of error messages reported by the builder */
    protected List<IMessage> messages;

    // Constructors

    public XQueryStructuredModel() {
        builder = getContributionModelBuilder();
        messages = new ArrayList<IMessage>();
    }

    // Methods

    /**
     * Gets error messages issued by the model builder
     */
    public List<IMessage> getErrorMessages() {
        return messages;
    }

    /**
     * @param sdregion
     * @param text
     * @param tip
     *            whether or not to only highlight the tip of the given sdregion
     */
    protected void reportError(XQueryStructuredDocumentRegion sdregion, String text, boolean tip) {
        messages.add(ValidationHelper.createErrorMessage(sdregion, text, tip));

    }

    /**
     * @param sdregion
     * @param text
     */
    protected void reportError(XQueryStructuredDocumentRegion sdregion, ITextRegion region, String text) {
        messages.add(ValidationHelper.createErrorMessage(sdregion, region, text));

    }

    /**
     * @param sdregion
     * @param text
     */
    protected void reportError(IStructuredDocumentRegion first, IStructuredDocumentRegion last, String text) {
        messages.add(ValidationHelper.createErrorMessage(first, last, text));

    }

    /**
     * @param text
     */
    public void reportError(String text) {
        messages.add(ValidationHelper.createErrorMessage(text));
    }

    /** Gets module managed by this model */
    public ASTModule getModule() {
        return module;
    }

    /** Get the AST node attached to the given region */
    public IASTNode getASTNode(IStructuredDocumentRegion region) {
        if (region instanceof XQueryStructuredDocumentRegion) {
            final XQueryStructuredDocumentRegion xregion = (XQueryStructuredDocumentRegion)region;
            return xregion.getASTNode();
        }
        return null;
    }

    /** Get what is the target language for this model */
    protected int getLanguage() {
        String pref = Platform.getPreferencesService().getString(XQDTCorePlugin.PLUGIN_ID,
                IXQDTCorePreferences.LANGUAGE_LEVEL, "", null);
        if (IXQDTCorePreferences.LANGUAGE_NAME_XQUERY.equals(pref)) {
            return IXQDTLanguageConstants.LANGUAGE_XQUERY;
        }

        if (IXQDTCorePreferences.LANGUAGE_NAME_XQUERY_UPDATE.equals(pref)) {
            return IXQDTLanguageConstants.LANGUAGE_XQUERY | IXQDTLanguageConstants.LANGUAGE_XQUERY_UPDATE;
        }

        if (IXQDTCorePreferences.LANGUAGE_NAME_XQUERY_SCRIPTING.equals(pref)) {
            return IXQDTLanguageConstants.LANGUAGE_XQUERY | IXQDTLanguageConstants.LANGUAGE_XQUERY_UPDATE
                    | IXQDTLanguageConstants.LANGUAGE_XQUERY_SCRIPTING;
        }

        return IXQDTLanguageConstants.LANGUAGE_XQUERY;
    }

    // Overrides

    @Override
    public IndexedRegion getIndexedRegion(int offset) {
        return (IndexedRegion)fStructuredDocument.getRegionAtCharacterOffset(offset);
    }

    @Override
    public void setStructuredDocument(IStructuredDocument newStructuredDocument) {
        if (fStructuredDocument != null) {
            fStructuredDocument.removeDocumentChangingListener(this);
        }

        super.setStructuredDocument(newStructuredDocument);

        if (fStructuredDocument != null) {
            fStructuredDocument.addDocumentChangingListener(this);

            // Parse..
            if (fStructuredDocument != null) {
                rebuild(fStructuredDocument.getFirstStructuredDocumentRegion(), 0, fStructuredDocument.getLength());
            }
        }
    }

    /**
     * Incrementally rebuilt AST
     */
    protected void rebuild(IStructuredDocumentRegion sdregion, int offset, int length) {
        messages.clear();
        module = builder.reparseQuery(module, sdregion, offset, length, getLanguage());

    }

    // Implements IStructuredDocumentListener

    public void newModel(NewDocumentEvent event) {
        rebuild(event.getStructuredDocument().getFirstStructuredDocumentRegion(), 0, event.getDocument().getLength());
    }

    public void noChange(NoChangeEvent event) {
    }

    public void nodesReplaced(StructuredDocumentRegionsReplacedEvent event) {
        rebuild(event.getStructuredDocument().getFirstStructuredDocumentRegion(), event.getOffset(), event.getLength());
    }

    public void regionChanged(RegionChangedEvent event) {
        // AST does not have to be rebuilt.
        // The rest is handled locally (in regions)
    }

    public void regionsReplaced(RegionsReplacedEvent event) {
        // TODO: see if we can do better here.

        rebuild(event.getStructuredDocument().getFirstStructuredDocumentRegion(), event.getOffset(), event.getLength());
    }

    // ModelBuilder Extension point

    /**
     * Gets the XQuery model builder. Allow other plugins to provide an alternative
     * 
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
                    modelBuilder = (ModelBuilder)elements[0].createExecutableExtension("class");
                } catch (CoreException e) {
                    // TODO
                }
            }
        }

        modelBuilder = modelBuilder == null ? new ModelBuilder() : modelBuilder;
        modelBuilder.setModel(this);
        return modelBuilder;
    }

}
