/*******************************************************************************
 * Copyright (c) 2008 Dominik Schadow - http://www.xml-sicherheit.de
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Dominik Schadow - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.xml.security.core.preferences;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.preference.RadioGroupFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.wst.xml.security.core.XmlSecurityPlugin;
import org.eclipse.wst.xml.security.core.utils.IContextHelpIds;


/**
 * <p>This class represents the first general preference page of the XML Security Tools.</p>
 *
 * @author Dominik Schadow
 * @version 0.5.0
 */
public class PreferencePageXmlSecurity extends PreferencePage implements IWorkbenchPreferencePage {
  /** Canonicalization type radio buttons. */
  private RadioGroupFieldEditor canonType = null;
  /** Canonicalization document radio buttons. */
  private RadioGroupFieldEditor canonDocument = null;

  /**
   * Constructor.
   */
  public PreferencePageXmlSecurity() {
    super();
  }

  /**
   * Initializes the preference page.
   *
   * @param iWorkbench The current workbench
   */
  public void init(IWorkbench iWorkbench) {
    setPreferenceStore(XmlSecurityPlugin.getDefault().getPreferenceStore());
    setDescription(Messages.xmlSecurityPrefsDesc);
  }

  /**
   * Fills this preference page with content.
   *
   * @param parent Parent composite
   * @return The Control
   */
  protected Control createContents(Composite parent) {
    initializeDialogUnits(parent);

    Composite top = new Composite(parent, SWT.NULL);
    GridLayout layout = new GridLayout();
    layout.marginHeight = PreferenceConstants.MARGIN;
    layout.marginWidth = PreferenceConstants.MARGIN;
    layout.numColumns = PreferenceConstants.COLUMNS;
    top.setLayout(layout);

    canonType = new RadioGroupFieldEditor(PreferenceConstants.CANON_TYPE,
        Messages.xmlSecurityPrefsCanonType, PreferenceConstants.SMALL_GROUP,
        PreferenceValues.CANON_TYPES, top, true);
    canonType.setPage(this);
    canonType.setPreferenceStore(this.getPreferenceStore());
    canonType.load();

    canonDocument = new RadioGroupFieldEditor(PreferenceConstants.CANON_TARGET,
        Messages.xmlSecurityPrefsCanonTarget, PreferenceConstants.SMALL_GROUP,
        PreferenceValues.CANON_TARGETS, top, true);
    canonDocument.setPage(this);
    canonDocument.setPreferenceStore(this.getPreferenceStore());
    canonDocument.load();

    return top;
  }

  /**
   * Adds context sensitive help to this preference page.
   *
   * @param parent The parent composite
   */
  public void createControl(Composite parent) {
    super.createControl(parent);
    PlatformUI.getWorkbench().getHelpSystem().setHelp(getControl(), IContextHelpIds.PREFERENCES_GENERAL);
  }

  /**
   * Loads the default values for the page.
   */
  protected void performDefaults() {
    canonType.loadDefault();
    canonDocument.loadDefault();
    super.performDefaults();
  }

  /**
   * Called after click on apply button.
   */
  protected void performApply() {
    performOk();
  }

  /**
   * Stores the current settings of the page.
   *
   * @return Result of store process
   */
  public boolean performOk() {
    canonType.store();
    canonDocument.store();
    return super.performOk();
  }
}
