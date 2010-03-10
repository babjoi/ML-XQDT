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
package org.eclipse.wst.xquery.internal.ui.preferences;

import java.util.Arrays;
import java.util.Collection;

import org.eclipse.core.resources.IProject;
import org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPropertyAndPreferencePage;
import org.eclipse.dltk.ui.preferences.AbstractOptionsBlock;
import org.eclipse.dltk.ui.preferences.IPreferenceChangeRebuildPrompt;
import org.eclipse.dltk.ui.preferences.PreferenceChangeRebuildPrompt;
import org.eclipse.dltk.ui.preferences.PreferenceKey;
import org.eclipse.dltk.ui.preferences.PreferencesMessages;
import org.eclipse.dltk.ui.util.IStatusChangeListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.preferences.IWorkbenchPreferenceContainer;
import org.eclipse.wst.xquery.core.IXQDTCorePreferences;
import org.eclipse.wst.xquery.core.XQDTCorePlugin;
import org.eclipse.wst.xquery.ui.XQDTUIPlugin;

public class XQDTLanguagePreferencePage extends AbstractConfigurationBlockPropertyAndPreferencePage {

    public static final String[] languageTypes = { IXQDTCorePreferences.LANGUAGE_NAME_XQUERY,
            IXQDTCorePreferences.LANGUAGE_NAME_XQUERY_UPDATE, IXQDTCorePreferences.LANGUAGE_NAME_XQUERY_SCRIPTING };

    private static final PreferenceKey[] KEYS = new PreferenceKey[] { new PreferenceKey(XQDTCorePlugin.PLUGIN_ID,
            IXQDTCorePreferences.LANGUAGE_LEVEL),
    // new PreferenceKey(XQDTCorePlugin.PLUGIN_ID,
    // IXQDTCorePreferences.LANGUAGE_OPTION_USE_FULL_TEXT),
    };

    protected AbstractOptionsBlock createOptionsBlock(IStatusChangeListener newStatusChangedListener, IProject project,
            IWorkbenchPreferenceContainer container) {
        return new AbstractOptionsBlock(newStatusChangedListener, project, KEYS, container) {

            private Combo fLanguageCombo;

            // private Button fEnableFulltextButton;

            @Override
            protected void initialize() {
                super.initialize();
                initLanguageOptions();
            }

            @Override
            public void performDefaults() {
                super.performDefaults();
                initLanguageOptions();
            }

            private void initLanguageOptions() {
                fLanguageCombo.select(Arrays.asList(languageTypes).indexOf(getString(KEYS[0])));
                // fEnableFulltextButton.setSelection(Boolean.parseBoolean(getString(KEYS[1])));
            }

            protected Control createOptionsBlock(Composite parent) {
                Composite composite = new Composite(parent, SWT.NONE);
                GridLayout layout = new GridLayout();
                layout.marginHeight = 0;
                layout.marginWidth = 0;
                composite.setLayout(layout);

                Group languageComplianceGroup = new Group(composite, SWT.NONE);
                languageComplianceGroup.setText("XQuery language compliance level");
                GridData gd = new GridData(GridData.FILL_HORIZONTAL);
                languageComplianceGroup.setLayoutData(gd);
                layout = new GridLayout(3, false);
                languageComplianceGroup.setLayout(layout);

                fLanguageCombo = PropertyPageUtility.addComboBox(languageComplianceGroup, "XQuery laguage",
                        languageTypes);
                bindControl(fLanguageCombo, KEYS[0], languageTypes);

                // fEnableFulltextButton = new Button(languageComplianceGroup, SWT.CHECK);
                // fEnableFulltextButton.setText("Fulltext support");
                // bindControl(fEnableFulltextButton, KEYS[1], null);
                // fEnableFulltextButton.setVisible(false);

                return composite;
            }

            @Override
            @SuppressWarnings("unchecked")
            protected IPreferenceChangeRebuildPrompt getPreferenceChangeRebuildPrompt(boolean workspaceSettings,
                    Collection changedOptions) {
                return PreferenceChangeRebuildPrompt.create(workspaceSettings,
                        PreferencesMessages.TodoTaskConfigurationBlock_needsbuild_title,
                        PreferencesMessages.TodoTaskConfigurationBlock_needsbuild_message);
            }

        };
    }

    protected String getHelpId() {
        return null;
    }

    protected String getProjectHelpId() {
        return null;
    }

    protected void setDescription() {
        setDescription("XQuery language level compliance settings");
    }

    protected void setPreferenceStore() {
        setPreferenceStore(XQDTUIPlugin.getDefault().getPreferenceStore());
    }

    protected String getPreferencePageId() {
        return "org.eclipse.wst.xquery.preferences.language";
    }

    protected String getPropertyPageId() {
        return "org.eclipse.wst.xquery.properties.language";
    }

}
