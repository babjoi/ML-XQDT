package org.eclipse.wst.xquery.set.internal.debug.ui.preferences;

import org.eclipse.core.resources.IProject;
import org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPropertyAndPreferencePage;
import org.eclipse.dltk.ui.preferences.AbstractOptionsBlock;
import org.eclipse.dltk.ui.preferences.PreferenceKey;
import org.eclipse.dltk.ui.util.IStatusChangeListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.preferences.IWorkbenchPreferenceContainer;
import org.eclipse.wst.xquery.set.core.SETNature;
import org.eclipse.wst.xquery.set.debug.ui.SETDebugUIPlugin;

public class SETDebugPreferencePage extends AbstractConfigurationBlockPropertyAndPreferencePage {

    private static final String PREFERENCE_PAGE_ID = "org.eclipse.wst.xquery.set.preferences.debug";
    private static final String PROPERTY_PAGE_ID = "org.eclipse.wst.xquery.set.properties.debug";

    protected AbstractOptionsBlock createOptionsBlock(IStatusChangeListener newStatusChangedListener, IProject project,
            IWorkbenchPreferenceContainer container) {
        return new AbstractOptionsBlock(newStatusChangedListener, project, new PreferenceKey[] {}, container) {
            @Override
            protected Control createOptionsBlock(Composite parent) {
                Composite composite = new Composite(parent, SWT.NONE);
                return composite;
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
        setDescription("General settings for Sausalito debugging");
    }

    protected void setPreferenceStore() {
        setPreferenceStore(SETDebugUIPlugin.getDefault().getPreferenceStore());
    }

    protected String getPreferencePageId() {
        return PREFERENCE_PAGE_ID;
    }

    protected String getPropertyPageId() {
        return PROPERTY_PAGE_ID;
    }

    @Override
    protected String getNatureId() {
        return SETNature.NATURE_ID;
    }

}
