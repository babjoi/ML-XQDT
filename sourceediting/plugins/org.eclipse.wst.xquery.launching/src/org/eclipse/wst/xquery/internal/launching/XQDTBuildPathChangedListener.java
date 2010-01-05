/*******************************************************************************
 * Copyright (c) 2008, 2009 28msec Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Gabriel Petrovay (28msec) - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.xquery.internal.launching;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.dltk.core.ElementChangedEvent;
import org.eclipse.dltk.core.IElementChangedListener;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IModelElementDelta;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.IInterpreterInstallType;
import org.eclipse.dltk.launching.ScriptRuntime;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;
import org.eclipse.wst.common.project.facet.core.IProjectFacet;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;
import org.eclipse.wst.common.project.facet.core.IFacetedProject.Action;
import org.eclipse.wst.xquery.core.IXQDTConstants;
import org.eclipse.wst.xquery.launching.XQDTInterpreterInstallType;

public class XQDTBuildPathChangedListener implements IElementChangedListener {
    public void elementChanged(ElementChangedEvent event) {
        IModelElementDelta delta = event.getDelta();
        processDelta(delta);
    }

    private void processDelta(IModelElementDelta delta) {
        IModelElement element = delta.getElement();
        // only when a project changes and don't go deeper
        if (element.getElementType() == IModelElement.SCRIPT_PROJECT) {
            boolean hasNewBuildPath = false;

            // if the project is newly created
            if (delta.getKind() == IModelElementDelta.ADDED) {
                hasNewBuildPath = true;
            }
            // if the project interpreter was changed
            else if ((delta.getFlags() & IModelElementDelta.F_BUILDPATH_CHANGED) != 0) {
                hasNewBuildPath = true;
            }
            // or if we see at least one "ADDED TO BUILDPATH" change in the buildpath among the children deltas
            // this catches both project interpreter and workspace interpreter changes
            // TODO: this also catches all the additions to the buildpath (e.g. adding a new source folder)
            else {
                IModelElementDelta[] deltas = delta.getAffectedChildren();
                for (IModelElementDelta childDelta : deltas) {
                    if ((childDelta.getFlags() & IModelElementDelta.F_ADDED_TO_BUILDPATH) != 0) {
                        hasNewBuildPath = true;
                        break;
                    }
                }
            }
            if (!hasNewBuildPath) {
                return;
            }
            IScriptProject sProject = (IScriptProject)element;
            try {
                IInterpreterInstall interpreter = ScriptRuntime.getInterpreterInstall(sProject);
                IInterpreterInstallType installType = interpreter.getInterpreterInstallType();

                if (installType instanceof XQDTInterpreterInstallType) {
                    String resolverFacetId = ((XQDTInterpreterInstallType)installType).getResolverFacetId();
                    final IProject project = sProject.getProject();
                    final IFacetedProject fProject = ProjectFacetsManager.create(project);
                    final Set<Action> todoActions = new HashSet<Action>();
                    final boolean createFacets[] = { fProject == null && resolverFacetId != null };

                    if (fProject != null && resolverFacetId != null) {
                        IProjectFacet resolverFacet = ProjectFacetsManager.getProjectFacet(resolverFacetId);
                        IProjectFacetVersion resolverFacetVersion = resolverFacet.getLatestVersion();
                        if (!fProject.hasProjectFacet(resolverFacet)) {
                            Set<IProjectFacetVersion> facets = fProject.getProjectFacets();
                            for (IProjectFacetVersion facet : facets) {
                                todoActions.add(new Action(Action.Type.UNINSTALL, facet, null));
                            }
                            todoActions.add(new Action(Action.Type.INSTALL, resolverFacetVersion, null));
                        }
                    } else if (fProject != null && resolverFacetId == null) {
                        // uninstall facets in the resolver category
                        Set<IProjectFacetVersion> facets = fProject.getProjectFacets();
                        for (IProjectFacetVersion facet : facets) {
                            if (facet.getProjectFacet().getCategory().getId().equals(
                                    IXQDTConstants.URI_RESOLVER_FACET_CATEGORY_ID)) {
                                todoActions.add(new Action(Action.Type.UNINSTALL, facet, null));
                            }
                        }
                    } else if (fProject == null && resolverFacetId != null) {
                        IProjectFacet resolverFacet = ProjectFacetsManager.getProjectFacet(resolverFacetId);
                        IProjectFacetVersion resolverFacetVersion = resolverFacet.getLatestVersion();
                        todoActions.add(new Action(Action.Type.INSTALL, resolverFacetVersion, null));
                    }

                    if (todoActions.size() != 0) {
                        WorkspaceJob job = new WorkspaceJob("Configuring project URI resolver") {
                            public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
                                IFacetedProject proj = fProject;
                                if (createFacets[0]) {
                                    proj = ProjectFacetsManager.create(project, true, null);
                                }
                                proj.modify(todoActions, monitor);
                                return Status.OK_STATUS;
                            }
                        };
                        job.schedule();
                    }
                }

            } catch (CoreException e) {
                e.printStackTrace();
            }
        } else {
            IModelElementDelta[] deltas = delta.getAffectedChildren();
            for (IModelElementDelta childDelta : deltas) {
                processDelta(childDelta);
            }
        }
    }
}
