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

import java.util.ArrayList;

import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.preference.RadioGroupFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.wst.xml.security.core.XmlSecurityPlugin;
import org.eclipse.wst.xml.security.core.utils.Algorithms;
import org.eclipse.wst.xml.security.core.utils.Globals;

/**
 * <p>This class represents the Encryption and Decryption preference page of the XML-Security
 * Plug-In.</p>
 *
 * @author Dominik Schadow
 * @version 0.5.0
 */
public class PreferencePageEncryption extends PreferencePage implements IWorkbenchPreferencePage {
  /** Encryption type radio buttons. */
  private RadioGroupFieldEditor encryptionType = null;
  /** Key file. */
  private FileFieldEditor keyFile = null;
  /** Radio button encrypt document. */
  private Button encryptDocument;
  /** Radio button encrypt selection. */
  private Button encryptSelection;
  /** Radio button encrypt XPath expression. */
  private Button encryptXPath;
  /** Label XPath expression. */
  private Label lEncryptXPath;
  /** Text XPath expression. */
  private Text encryptXPathText;
  /** Label encryption algorithm. */
  private Label lKeyName;
  /** Combo encryption algorithm. */
  private Text tKeyName;
  /** Label key wrap algorithm. */
  private Label algoKeyWrapLabel;
  /** Combo key wrap algorithm. */
  private Combo algoKeyWrapCombo;
  /** Combo key file algorithm. */
  private Combo algoKeyFileCombo;
  /** Combo key file size algorithm. */
  private Combo algoKeyFileSizeCombo;
  /** Label encryption id. */
  private Label encryptIdLabel;
  /** Text encryption id. */
  private Text encryptIdText;
  /** All checkboxes on the page. */
  private ArrayList<Button> checkBoxes;
  /** All radio buttons on the page. */
  private ArrayList<Button> radioButtons;
  /** All textfields on the page. */
  private ArrayList<Text> textControls;
  /** All comboboxes on the page. */
  private ArrayList<Combo> comboBoxes;
  /** Selection listener. */
  private SelectionListener selectionListener;
  /** Modify listerner. */
  private ModifyListener modifyListener;
  /** Number of characters for pixel width conversion. */
  private static final int CHARS_TO_PIXEL = 30;

  /**
   * Constructor.
   */
  public PreferencePageEncryption() {
    super();

    checkBoxes = new ArrayList<Button>();
    comboBoxes = new ArrayList<Combo>();
    radioButtons = new ArrayList<Button>();
    textControls = new ArrayList<Text>();

    addListener();
  }

  /**
   * Adds all listeners for the current preference page.
   */
  private void addListener() {
    selectionListener = new SelectionAdapter() {
      public void widgetSelected(SelectionEvent e) {
        controlChanged(e.widget);
      }
    };

    modifyListener = new ModifyListener() {
      public void modifyText(ModifyEvent e) {
        controlModified(e.widget);
      }
    };
  }

  /**
   * Initializes the preference page.
   *
   * @param iWorkbench The current workbench
   */
  public void init(IWorkbench iWorkbench) {
    setPreferenceStore(XmlSecurityPlugin.getDefault().getPreferenceStore());
    setDescription(Messages.encryptionPrefsDesc);
  }

  /**
   * Fills this preference page with content.
   *
   * @param parent Parent composite
   * @return The Control
   */
  protected Control createContents(Composite parent) {
    initializeDialogUnits(parent);

    Composite top = new Composite(parent, SWT.NONE);
    GridLayout layout = new GridLayout();
    layout.marginHeight = PreferenceConstants.MARGIN;
    layout.marginWidth = PreferenceConstants.MARGIN;
    layout.numColumns = PreferenceConstants.COLUMNS;
    top.setLayout(layout);

    GridData gd = new GridData(GridData.FILL_HORIZONTAL);
    gd.horizontalSpan = PreferenceConstants.SMALL_GROUP;

    Group encryptionResourceGroup = new Group(top, SWT.NONE);
    layout = new GridLayout();
    layout.numColumns = PreferenceConstants.SMALL_GROUP;
    encryptionResourceGroup.setLayout(layout);
    encryptionResourceGroup.setLayoutData(gd);
    encryptionResourceGroup.setText(Messages.encryptionPrefsResource);

    int indent = 0;

    encryptDocument = addRadioButton(encryptionResourceGroup,
        Messages.encryptionPrefsResourceDocument, PreferenceConstants.ENCRYPT_RESOURCE, "document",
        indent);
    encryptDocument.addSelectionListener(selectionListener);

    encryptSelection = addRadioButton(encryptionResourceGroup,
        Messages.encryptionPrefsResourceSelection, PreferenceConstants.ENCRYPT_RESOURCE,
        "selection", indent);
    encryptSelection.addSelectionListener(selectionListener);

    encryptXPath = addRadioButton(encryptionResourceGroup, Messages.encryptionPrefsResourceXPath,
        PreferenceConstants.ENCRYPT_RESOURCE, "xpath", indent);
    encryptXPath.addSelectionListener(selectionListener);

    indent = convertWidthInCharsToPixels(4);

    lEncryptXPath = new Label(encryptionResourceGroup, SWT.NONE);
    lEncryptXPath.setText(Messages.encryptionPrefsResourceXPathExpression);
    lEncryptXPath.setEnabled(encryptXPath.getSelection());
    encryptXPathText = addTextControl(encryptionResourceGroup, lEncryptXPath,
        PreferenceConstants.ENCRYPT_XPATH, indent);
    encryptXPathText.addModifyListener(modifyListener);
    encryptXPathText.setEnabled(encryptXPath.getSelection());

    gd = new GridData(GridData.FILL_HORIZONTAL);
    gd.horizontalSpan = PreferenceConstants.SMALL_GROUP;

    encryptionType = new RadioGroupFieldEditor(PreferenceConstants.ENCRYPT_TYPE,
        Messages.encryptionPrefsEncryptionType, PreferenceConstants.SMALL_GROUP,
        PreferenceValues.ENCRYPTION_TYPES, top, true);
    encryptionType.setPage(this);
    encryptionType.setPreferenceStore(this.getPreferenceStore());
    encryptionType.load();
    encryptionType.fillIntoGrid(top, PreferenceConstants.SMALL_GROUP);

    gd = new GridData(GridData.FILL_HORIZONTAL);
    gd.horizontalSpan = PreferenceConstants.SMALL_GROUP;

    Group encryptionAlgorithmsGroup = new Group(top, SWT.NONE);
    layout = new GridLayout();
    layout.numColumns = PreferenceConstants.SMALL_GROUP;
    encryptionAlgorithmsGroup.setLayout(layout);
    encryptionAlgorithmsGroup.setLayoutData(gd);
    encryptionAlgorithmsGroup.setText(Messages.encryptionPrefsAlgorithms);

    indent = 0;

    algoKeyWrapLabel = new Label(encryptionAlgorithmsGroup, SWT.NONE);
    algoKeyWrapLabel.setText(Messages.encryptionPrefsAlgoKeyWrap);
    algoKeyWrapCombo = addComboControl(encryptionAlgorithmsGroup, algoKeyWrapLabel,
        PreferenceConstants.ENCRYPT_KEY_WRAP, indent, Algorithms.KEY_WRAP_ALGORITHMS);
    algoKeyWrapCombo.addModifyListener(modifyListener);

    gd = new GridData(GridData.FILL_HORIZONTAL);
    gd.horizontalSpan = PreferenceConstants.SMALL_GROUP;

    Group keyStoreGroup = new Group(top, SWT.NONE);
    layout = new GridLayout();
    layout.numColumns = PreferenceConstants.LARGE_GROUP;
    keyStoreGroup.setLayout(layout);
    keyStoreGroup.setLayoutData(gd);
    keyStoreGroup.setText(Messages.encryptionPrefsKey);

    indent = 0;

    keyFile = new FileFieldEditor(PreferenceConstants.ENCRYPT_KEY_STORE,
        Messages.encryptionPrefsKeyFile, keyStoreGroup);
    keyFile.setFileExtensions(Globals.KEY_STORE_EXTENSION);
    keyFile.setChangeButtonText(Messages.encryptionPrefsKeyFileSelect);
    keyFile.setPage(this);
    keyFile.setPreferenceStore(this.getPreferenceStore());
    keyFile.load();
    keyFile.fillIntoGrid(keyStoreGroup, PreferenceConstants.LARGE_GROUP);

    lKeyName = new Label(keyStoreGroup, SWT.NONE);
    lKeyName.setText(Messages.encryptionPrefsKeyName);
    tKeyName = addTextControl(keyStoreGroup, lKeyName,
        PreferenceConstants.ENCRYPT_KEY_NAME, indent);
    tKeyName.addModifyListener(modifyListener);

    Group encryptionIdGroup = new Group(top, SWT.NONE);
    layout = new GridLayout();
    layout.numColumns = PreferenceConstants.SMALL_GROUP;
    encryptionIdGroup.setLayout(layout);
    encryptionIdGroup.setLayoutData(gd);
    encryptionIdGroup.setText(Messages.encryptionPrefsId);

    indent = 0;

    encryptIdLabel = new Label(encryptionIdGroup, SWT.NONE);
    encryptIdLabel.setText(Messages.encryptionPrefsEncryptionId);
    encryptIdText = addTextControl(encryptionIdGroup, encryptIdLabel,
        PreferenceConstants.ENCRYPT_ID, indent);
    encryptIdText.addModifyListener(modifyListener);

    return top;
  }

  /**
   * Adds context sensitive help to this preference page.
   *
   * @param parent The parent composite
   */
  public void createControl(Composite parent) {
    super.createControl(parent);
    PlatformUI.getWorkbench().getHelpSystem().setHelp(getControl(),
        "org.eclipse.wst.xml.security.doc.prefsenc");
  }

  /**
   * Adds a radio button to this preference page.
   *
   * @param parent The parent composite
   * @param label The label for the radio button
   * @param key The key of the radio button
   * @param value The value of the radio button
   * @param indent The indent
   * @return The complete radio button
   */
  private Button addRadioButton(Composite parent, String label, String key,
    String value, int indent) {
    IPreferenceStore store = getPreferenceStore();
    GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
    gd.horizontalSpan = 2;
    gd.horizontalIndent = indent;

    Button button = new Button(parent, SWT.RADIO);
    button.setText(label);
    button.setData(new String[] {key, value});
    button.setLayoutData(gd);
    button.setSelection(value.equals(store.getString(key)));

    radioButtons.add(button);
    return button;
  }

  /**
   * Adds a textfield to this preference page.
   *
   * @param parent The parent composite
   * @param labelControl The label
   * @param key The key of the textfield
   * @param indent The indent
   * @return The complete textfield
   */
  private Text addTextControl(Composite parent, Label labelControl, String key, int indent) {
    IPreferenceStore store = getPreferenceStore();
    GridData gd = new GridData();
    gd.horizontalIndent = indent;

    labelControl.setLayoutData(gd);

    gd = new GridData(GridData.FILL_HORIZONTAL);
    gd.widthHint = convertWidthInCharsToPixels(CHARS_TO_PIXEL);

    Text text = new Text(parent, SWT.SINGLE | SWT.BORDER);
    text.setData(key);
    text.setLayoutData(gd);
    text.setText(store.getString(key));

    textControls.add(text);
    return text;
  }

  /**
   * Adds a combo box to this preference page.
   *
   * @param parent The parent composite
   * @param labelControl The label
   * @param key The key of the combo box
   * @param indent The indent
   * @param items The items to add
   * @return The complete combo box
   */
  private Combo addComboControl(Composite parent, Label labelControl, String key, int indent,
      String[] items) {
    IPreferenceStore store = XmlSecurityPlugin.getDefault().getPreferenceStore();
    GridData gd = new GridData();
    gd.horizontalIndent = indent;

    labelControl.setLayoutData(gd);

    gd = new GridData(GridData.FILL_HORIZONTAL);
    gd.widthHint = convertWidthInCharsToPixels(CHARS_TO_PIXEL);

    Combo combo = new Combo(parent, SWT.SINGLE | SWT.BORDER | SWT.READ_ONLY);
    combo.setItems(items);
    combo.setData(key);
    combo.setLayoutData(gd);
    for (int i = 0; i < combo.getItemCount(); i++) {
      if (combo.getItem(i).equals(store.getString(key))) {
        combo.select(i);
      }
    }

    comboBoxes.add(combo);
    return combo;
  }

  /**
   * Loads the default values for the page.
   */
  protected void performDefaults() {
    IPreferenceStore store = getPreferenceStore();

    for (Button b : checkBoxes) {
      String key = (String) b.getData();
      b.setSelection(store.getDefaultBoolean(key));
    }
    for (Button b : radioButtons) {
      String[] info = (String[]) b.getData();
      b.setSelection(info[1].equals(store.getDefaultString(info[0])));
    }
    for (Text t : textControls) {
      String key = (String) t.getData();
      t.setText(store.getDefaultString(key));
    }
    for (Combo c : comboBoxes) {
      String key = (String) c.getData();
      for (int i = 0; i < c.getItemCount(); i++) {
        if (c.getItem(i).equals(store.getDefaultString(key))) {
          c.select(i);
        }
      }
    }

    encryptionType.loadDefault();
    keyFile.loadDefault();

    super.performDefaults();

    validateXPath();
  }

  /**
   * Validates the XPath expression and enables (or disables) the XPath expression text.
   */
  private void validateXPath() {
    boolean useXPathExpression = encryptXPath.getSelection();

    encryptXPathText.setEnabled(useXPathExpression);
    lEncryptXPath.setEnabled(useXPathExpression);
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
    IPreferenceStore store = getPreferenceStore();

    for (Button b : checkBoxes) {
      String key = (String) b.getData();
      store.setValue(key, b.getSelection());
    }
    for (Button b : radioButtons) {
      if (b.getSelection()) {
        String[] info = (String[]) b.getData();
        store.setValue(info[0], info[1]);
      }
    }
    for (Text t : textControls) {
      String key = (String) t.getData();
      store.setValue(key, t.getText());
    }
    for (Combo c : comboBoxes) {
      String key = (String) c.getData();
      store.setValue(key, c.getItem(c.getSelectionIndex()));
    }

    encryptionType.store();
    keyFile.store();

    return super.performOk();
  }

  /**
   * Updates widgets after controls have changed.
   *
   * @param widget The widget that has changed
   */
  private void controlChanged(Widget widget) {
    if (widget == encryptDocument || widget == encryptSelection) {
      lEncryptXPath.setEnabled(false);
      encryptXPathText.setEnabled(false);
    } else if (widget == encryptXPath) {
      lEncryptXPath.setEnabled(true);
      encryptXPathText.setEnabled(true);
    }
  }

  /**
   * Called after a control has been modified.
   *
   * @param widget The modified widget
   */
  private void controlModified(Widget widget) {
    if (widget == algoKeyFileCombo) {
      String selectedKFA = algoKeyFileCombo.getItem(algoKeyFileCombo.getSelectionIndex());

      if (selectedKFA == null || selectedKFA.equals("")) {
        algoKeyFileSizeCombo.setItems(Algorithms.KEY_FILE_ALGORITHMS_SIZES);
      } else if (selectedKFA.equalsIgnoreCase("AES")) {
        algoKeyFileSizeCombo.setItems(Algorithms.KEY_SIZES_AES);
      } else if (selectedKFA.equalsIgnoreCase("Blowfish")) {
        algoKeyFileSizeCombo.setItems(Algorithms.KEY_SIZES_BLOWFISH);
      } else if (selectedKFA.equalsIgnoreCase("DES")) {
        algoKeyFileSizeCombo.setItems(Algorithms.KEY_SIZES_DES);
      } else if (selectedKFA.equalsIgnoreCase("Triple DES")) {
        algoKeyFileSizeCombo.setItems(Algorithms.KEY_SIZES_DESEDE);
      }
    }
  }
}
