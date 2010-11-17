/*******************************************************************************
 * Copyright (c) 2008, 2009 28msec Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     William Candillon (28msec) - initial API and implementation
 *     Gabriel Petrovay (28msec)
 *******************************************************************************/
package org.eclipse.wst.xquery.internal.ui.perspectives;

import org.eclipse.debug.ui.IDebugUIConstants;
import org.eclipse.search.ui.NewSearchUI;
import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.console.IConsoleConstants;
import org.eclipse.ui.progress.IProgressConstants;
import org.eclipse.wst.xquery.internal.ui.wizards.XQDTNewModuleWizard;
import org.eclipse.wst.xquery.internal.ui.wizards.XQDTNewProjectWizard;

public class XQDTEditorPerspective implements IPerspectiveFactory {

    public static final String PERSPECTIVE_ID = "org.eclipse.wst.xquery.ui.perspectives.XQueryPerspective";

    public void createInitialLayout(IPageLayout layout) {
        addFolders(layout);
        addActionSets(layout);
        addViews(layout);
        addShowViewShortcuts(layout);
        addNewWizardShortcuts(layout);
        addPerspectiveShotcuts(layout);
    }

    protected void addFolders(IPageLayout layout) {
        IFolderLayout leftFolder = layout.createFolder("left", IPageLayout.LEFT, (float)0.2, layout.getEditorArea()); //$NON-NLS-1$
        leftFolder.addView("org.eclipse.dltk.ui.ScriptExplorer"); //$NON-NLS-1$
        //leftFolder.addView("org.eclipse.dltk.testing.ResultView"); //$NON-NLS-1$
        leftFolder.addPlaceholder("org.eclipse.dltk.ui.TypeHierarchy"); //$NON-NLS-1$
        leftFolder.addPlaceholder(IPageLayout.ID_BOOKMARKS);

        IFolderLayout bottomFolder = layout.createFolder(
                "bottom", IPageLayout.BOTTOM, (float)0.75, layout.getEditorArea()); //$NON-NLS-1$
        bottomFolder.addView(IPageLayout.ID_PROBLEM_VIEW);
        bottomFolder.addView(IPageLayout.ID_TASK_LIST);
        // TODO: add documentation view
        bottomFolder.addView(IConsoleConstants.ID_CONSOLE_VIEW);

        bottomFolder.addPlaceholder("org.eclipse.dltk.callhierarchy.view"); //$NON-NLS-1$
        bottomFolder.addPlaceholder(NewSearchUI.SEARCH_VIEW_ID);
        bottomFolder.addPlaceholder(IProgressConstants.PROGRESS_VIEW_ID);
    }

    protected void addActionSets(IPageLayout layout) {
        layout.addActionSet(IPageLayout.ID_NAVIGATE_ACTION_SET);
        // TODO: add XQDT action set
        layout.addActionSet(IDebugUIConstants.LAUNCH_ACTION_SET);
        layout.addActionSet(IDebugUIConstants.DEBUG_ACTION_SET);
        layout.addActionSet("org.eclipse.debug.ui.breakpointActionSet"); //$NON-NLS-1$
    }

    protected void addViews(IPageLayout layout) {
        layout.addView(IPageLayout.ID_OUTLINE, IPageLayout.RIGHT, (float)0.75, layout.getEditorArea());
    }

    protected void addShowViewShortcuts(IPageLayout layout) {
        layout.addShowViewShortcut(IPageLayout.ID_OUTLINE);
        layout.addShowViewShortcut(IPageLayout.ID_PROBLEM_VIEW);
        layout.addShowViewShortcut("org.eclipse.dltk.ui.ScriptExplorer"); //$NON-NLS-1$
        layout.addShowViewShortcut("org.eclipse.dltk.testing.ResultView"); //$NON-NLS-1$
        layout.addShowViewShortcut(IPageLayout.ID_TASK_LIST);
        layout.addShowViewShortcut(IProgressConstants.PROGRESS_VIEW_ID);
        layout.addShowViewShortcut(IConsoleConstants.ID_CONSOLE_VIEW);
        // TODO: add documentation view
        // TODO: add search shortcut
        layout.addShowViewShortcut("org.eclipse.dltk.ui.TypeHierarchy"); //$NON-NLS-1$
        layout.addShowViewShortcut("org.eclipse.dltk.callhierarchy.view"); //$NON-NLS-1$
    }

    protected void addNewWizardShortcuts(IPageLayout layout) {
        // XQDT
        layout.addNewWizardShortcut("org.eclipse.wst.xquery.set.ui.wizards.SausalitoProject");
        layout.addNewWizardShortcut(XQDTNewProjectWizard.WIZARD_ID);
        layout.addNewWizardShortcut(XQDTNewModuleWizard.WIZARD_ID);
        layout.addNewWizardShortcut("org.eclipse.wst.xml.ui.internal.wizards.NewXMLWizard");

        // General
        layout.addNewWizardShortcut("org.eclipse.ui.wizards.new.folder");//$NON-NLS-1$
        layout.addNewWizardShortcut("org.eclipse.ui.wizards.new.file");//$NON-NLS-1$
        layout.addNewWizardShortcut("org.eclipse.ui.editors.wizards.UntitledTextFileWizard");//$NON-NLS-1$
    }

    protected void addPerspectiveShotcuts(IPageLayout layout) {
        layout.addPerspectiveShortcut("org.eclipse.debug.ui.DebugPerspective"); //$NON-NLS-1$
        layout.addPerspectiveShortcut("org.eclipse.ui.resourcePerspective"); //$NON-NLS-1$
        layout.addPerspectiveShortcut("org.eclipse.team.ui.TeamSynchronizingPerspective"); //$NON-NLS-1$
    }
}
