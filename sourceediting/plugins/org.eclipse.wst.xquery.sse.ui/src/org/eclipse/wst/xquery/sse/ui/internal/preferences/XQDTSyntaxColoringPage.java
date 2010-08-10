/*******************************************************************************
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Lionel Vilard (IBM) - adoption in XQDT
 *     Gabriel Petrovay (28msec)
 *       - change to ignore background colors
 *       - added color scheme templates
 *     
 *******************************************************************************/
package org.eclipse.wst.xquery.sse.ui.internal.preferences;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.jface.preference.ColorSelector;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.accessibility.ACC;
import org.eclipse.swt.accessibility.AccessibleAdapter;
import org.eclipse.swt.accessibility.AccessibleEvent;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.dialogs.PreferencesUtil;
import org.eclipse.wst.sse.core.StructuredModelManager;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocument;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocumentRegion;
import org.eclipse.wst.sse.core.internal.provisional.text.ITextRegion;
import org.eclipse.wst.sse.core.internal.provisional.text.ITextRegionList;
import org.eclipse.wst.sse.ui.internal.SSEUIMessages;
import org.eclipse.wst.sse.ui.internal.SSEUIPlugin;
import org.eclipse.wst.sse.ui.internal.preferences.OverlayPreferenceStore;
import org.eclipse.wst.sse.ui.internal.preferences.OverlayPreferenceStore.OverlayKey;
import org.eclipse.wst.sse.ui.internal.util.EditorUtility;
import org.eclipse.wst.xquery.sse.core.IXQDTSSECoreConstants;
import org.eclipse.wst.xquery.sse.core.internal.parser.XQueryTokenizer;
import org.eclipse.wst.xquery.sse.ui.XQDTSSEUIPlugin;
import org.eclipse.wst.xquery.sse.ui.internal.XQDTEditorUIPreferenceInitializer;
import org.eclipse.wst.xquery.sse.ui.internal.XQDTUIMessages;
import org.eclipse.wst.xquery.sse.ui.internal.restrictions.ColorHelper;
import org.eclipse.wst.xquery.sse.ui.internal.style.XQDTLineStyleProvider;

/**
 * A preference page to configure the XQuery syntax color.
 * 
 * The code is almost identical to the XML syntax coloring preference page.
 * 
 * TODO: The similar XML page should be extended such that languages like XQuery can benefit from
 * the base XML functionality by inheriting from it and add only the extra functionality.
 */
@SuppressWarnings("restriction")
public class XQDTSyntaxColoringPage extends PreferencePage implements IWorkbenchPreferencePage {

    private Combo fSchemeCombo;

    private Button fBold;
    private Button fItalic;
    private Button fStrike;
    private Button fUnderline;

    private Button fClearStyle;
    private ColorSelector fColorSelector;
    private Label fColorLabel;
    private StyledText fText;

    private Color fDefaultColor = null;
    private IStructuredDocument fDocument;
    private OverlayPreferenceStore fOverlayStore;
    private StructuredViewer fStylesViewer = null;
    private Collection<String> fStylePreferenceKeys;
    private Map<String, String> fContextToStyleMap;
    private Map<String, String> fStyleToDescriptionMap;

    // activate controls based on the given local color type
    private void activate(String namedStyle) {
        Color color = fDefaultColor;
        if (namedStyle == null) {
            fClearStyle.setEnabled(false);
            fBold.setEnabled(false);
            fItalic.setEnabled(false);
            fStrike.setEnabled(false);
            fUnderline.setEnabled(false);
            fColorLabel.setEnabled(false);
            fColorSelector.setEnabled(false);
            fBold.setSelection(false);
            fItalic.setSelection(false);
            fStrike.setSelection(false);
            fUnderline.setSelection(false);
        } else {
            TextAttribute attribute = getAttributeFor(namedStyle);
            fClearStyle.setEnabled(true);
            fBold.setEnabled(true);
            fItalic.setEnabled(true);
            fStrike.setEnabled(true);
            fUnderline.setEnabled(true);
            fColorLabel.setEnabled(true);
            fColorSelector.setEnabled(true);
            fBold.setSelection((attribute.getStyle() & SWT.BOLD) != 0);
            fItalic.setSelection((attribute.getStyle() & SWT.ITALIC) != 0);
            fStrike.setSelection((attribute.getStyle() & TextAttribute.STRIKETHROUGH) != 0);
            fUnderline.setSelection((attribute.getStyle() & TextAttribute.UNDERLINE) != 0);
            if (attribute.getForeground() != null) {
                color = attribute.getForeground();
            }
        }

        fColorSelector.setColorValue(color.getRGB());
    }

    /**
     * Color the text in the sample area according to the current preferences
     */
    void applyStyles() {
        if (fText == null || fText.isDisposed()) {
            return;
        }
        IStructuredDocumentRegion documentRegion = fDocument.getFirstStructuredDocumentRegion();
        while (documentRegion != null) {
            ITextRegionList regions = documentRegion.getRegions();
            for (int i = 0; i < regions.size(); i++) {
                ITextRegion currentRegion = regions.get(i);
                // lookup the local coloring type and apply it
                String namedStyle = fContextToStyleMap.get(currentRegion.getType());
                if (namedStyle == null) {
                    continue;
                }
                TextAttribute attribute = getAttributeFor(namedStyle);
                if (attribute == null) {
                    continue;
                }
                StyleRange style = new StyleRange(documentRegion.getStartOffset(currentRegion),
                        currentRegion.getTextLength(), attribute.getForeground(), attribute.getBackground(),
                        attribute.getStyle());
                style.strikeout = (attribute.getStyle() & TextAttribute.STRIKETHROUGH) != 0;
                style.underline = (attribute.getStyle() & TextAttribute.UNDERLINE) != 0;
                fText.setStyleRange(style);
            }
            documentRegion = documentRegion.getNext();
        }
    }

    Button createCheckbox(Composite parent, String label) {
        Button button = new Button(parent, SWT.CHECK);
        button.setText(label);
        button.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
        return button;
    }

    /**
     * Creates composite control and sets the default layout data.
     */
    private Composite createComposite(Composite parent, int numColumns) {
        Composite composite = new Composite(parent, SWT.NULL);

        // GridLayout
        GridLayout layout = new GridLayout();
        layout.numColumns = numColumns;
        layout.makeColumnsEqualWidth = false;
        layout.marginHeight = 0;
        layout.marginWidth = 0;
        composite.setLayout(layout);

        // GridData
        GridData data = new GridData(SWT.FILL, SWT.FILL, true, false);
        composite.setLayoutData(data);
        return composite;
    }

    protected Control createContents(final Composite parent) {
        initializeDialogUnits(parent);

        fDefaultColor = parent.getDisplay().getSystemColor(SWT.COLOR_LIST_FOREGROUND);
        Composite pageComponent = createComposite(parent, 2);
        // PlatformUI.getWorkbench().getHelpSystem().setHelp(pageComponent, IHelpContextIds.XML_PREFWEBX_STYLES_HELPID);

        Link link = new Link(pageComponent, SWT.WRAP);
        link.setText(SSEUIMessages.SyntaxColoring_Link);
        link.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                PreferencesUtil.createPreferenceDialogOn(parent.getShell(), e.text, null, null);
            }
        });

        GridData linkData = new GridData(SWT.FILL, SWT.BEGINNING, true, false, 2, 1);
        linkData.widthHint = 150; // only expand further if anyone else requires it
        link.setLayoutData(linkData);

        Composite schemeComp = createComposite(pageComponent, 2);
        GridLayout compLayout = (GridLayout)schemeComp.getLayout();
        compLayout.marginBottom = compLayout.marginTop = 5;

        Label schemeLabel = createLabel(schemeComp, XQDTUIMessages.SyntaxColoringPage_SchemesLabel);
        ((GridData)schemeLabel.getLayoutData()).verticalAlignment = SWT.CENTER;

        fSchemeCombo = new Combo(schemeComp, SWT.SIMPLE | SWT.READ_ONLY | SWT.DROP_DOWN);
        GridData comboData = new GridData(SWT.FILL, SWT.CENTER, true, false);
        fSchemeCombo.setLayoutData(comboData);
        String[] schemes = getColorSchemes();
        if (schemes.length == 0) {
            fSchemeCombo.setEnabled(false);
            schemes = new String[] { XQDTUIMessages.SyntaxColoringPage_NoSchemeItem };
        }
        fSchemeCombo.setItems(schemes);
        fSchemeCombo.setText("Choose one color scheme...");
        fSchemeCombo.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent event) {
                fStylesViewer.setSelection(StructuredSelection.EMPTY);
                activate(null);

                String schemeName = fSchemeCombo.getItem(fSchemeCombo.getSelectionIndex());
                Map<String, String> schemeStyles = XQDTColorSchemeRegistry.getColorSchemes().get(schemeName);

                List<String> stylesToChange = new ArrayList<String>(fStylePreferenceKeys);
                for (String key : schemeStyles.keySet()) {
                    String style = schemeStyles.get(key);
                    getOverlayStore().setValue(key, style);
                    stylesToChange.remove(key);
                }
                for (String key : stylesToChange) {
                    getOverlayStore().setValue(key, XQDTEditorUIPreferenceInitializer.DEFAULT_TEXT_STYLE);
                }

                applyStyles();
                fText.redraw();
            }
        });

        SashForm editor = new SashForm(pageComponent, SWT.VERTICAL);
        GridData gridData2 = new GridData(SWT.FILL, SWT.FILL, true, true);
        gridData2.horizontalSpan = 2;
        editor.setLayoutData(gridData2);
        SashForm top = new SashForm(editor, SWT.HORIZONTAL);
        Composite styleEditor = createComposite(top, 1);
        ((GridLayout)styleEditor.getLayout()).marginRight = 5;
        ((GridLayout)styleEditor.getLayout()).marginLeft = 0;
        createLabel(styleEditor, XQDTUIMessages.SyntaxColoringPage_0);
        fStylesViewer = createStylesViewer(styleEditor);
        GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
        gridData.horizontalIndent = 0;
        Iterator<String> iterator = fStyleToDescriptionMap.values().iterator();
        while (iterator.hasNext()) {
            gridData.widthHint = Math.max(gridData.widthHint, convertWidthInCharsToPixels(iterator.next().toString()
                    .length()));
        }
        gridData.heightHint = convertHeightInCharsToPixels(5);
        fStylesViewer.getControl().setLayoutData(gridData);

        Composite editingComposite = createComposite(top, 1);
        ((GridLayout)styleEditor.getLayout()).marginLeft = 5;
        createLabel(editingComposite, ""); //$NON-NLS-1$
        Button enabler = createCheckbox(editingComposite, XQDTUIMessages.SyntaxColoringPage_2);
        enabler.setEnabled(false);
        enabler.setSelection(true);
        Composite editControls = createComposite(editingComposite, 2);
        ((GridLayout)editControls.getLayout()).marginLeft = 20;

        fColorLabel = createLabel(editControls, XQDTUIMessages.SyntaxColoringPage_1);
        ((GridData)fColorLabel.getLayoutData()).verticalAlignment = SWT.CENTER;
        fColorLabel.setEnabled(false);

        fColorSelector = new ColorSelector(editControls);
        Button fForegroundColor = fColorSelector.getButton();
        GridData gd = new GridData(SWT.BEGINNING, SWT.FILL, false, false);
        fForegroundColor.setLayoutData(gd);
        fColorSelector.setEnabled(false);

        fBold = createCheckbox(editControls, XQDTUIMessages.SyntaxColoringPage_3);
        fBold.setEnabled(false);
        ((GridData)fBold.getLayoutData()).horizontalSpan = 2;
        fItalic = createCheckbox(editControls, XQDTUIMessages.SyntaxColoringPage_4);
        fItalic.setEnabled(false);
        ((GridData)fItalic.getLayoutData()).horizontalSpan = 2;
        fStrike = createCheckbox(editControls, XQDTUIMessages.SyntaxColoringPage_5);
        fStrike.setEnabled(false);
        ((GridData)fStrike.getLayoutData()).horizontalSpan = 2;
        fUnderline = createCheckbox(editControls, XQDTUIMessages.SyntaxColoringPage_6);
        fUnderline.setEnabled(false);
        ((GridData)fUnderline.getLayoutData()).horizontalSpan = 2;
        fClearStyle = new Button(editingComposite, SWT.PUSH);
        fClearStyle.setText(SSEUIMessages.Restore_Default_UI_); //$NON-NLS-1$ = "Restore Default"
        fClearStyle.setLayoutData(new GridData(SWT.BEGINNING));
        ((GridData)fClearStyle.getLayoutData()).horizontalIndent = 20;
        fClearStyle.setEnabled(false);

        Composite sampleArea = createComposite(editor, 1);

        ((GridLayout)sampleArea.getLayout()).marginLeft = 5;
        ((GridLayout)sampleArea.getLayout()).marginTop = 5;
        createLabel(sampleArea, SSEUIMessages.Sample_text__UI_); //$NON-NLS-1$ = "&Sample text:"
        SourceViewer viewer = new SourceViewer(sampleArea, null, SWT.BORDER | SWT.LEFT_TO_RIGHT | SWT.MULTI
                | SWT.V_SCROLL | SWT.H_SCROLL | SWT.READ_ONLY);
        fText = viewer.getTextWidget();
        GridData gridData3 = new GridData(SWT.FILL, SWT.FILL, true, true);
        gridData3.widthHint = convertWidthInCharsToPixels(20);
        gridData3.heightHint = convertHeightInCharsToPixels(5);
        gridData3.horizontalSpan = 2;
        fText.setLayoutData(gridData3);
        fText.setEditable(false);
        fText.setFont(JFaceResources.getFont("org.eclipse.wst.sse.ui.textfont")); //$NON-NLS-1$
        fText.addKeyListener(getTextKeyListener());
        fText.addSelectionListener(getTextSelectionListener());
        fText.addMouseListener(getTextMouseListener());
        fText.addTraverseListener(getTraverseListener());
        setAccessible(fText, SSEUIMessages.Sample_text__UI_);
        fDocument = StructuredModelManager.getModelManager().createStructuredDocumentFor(
                IXQDTSSECoreConstants.XQUERY_CONTENT_TYPE_ID);
        fDocument.set(getExampleText());
        viewer.setDocument(fDocument);

        top.setWeights(new int[] { 1, 1 });
        editor.setWeights(new int[] { 1, 1 });
        // PlatformUI.getWorkbench().getHelpSystem().setHelp(pageComponent, IHelpContextIds.XML_PREFWEBX_STYLES_HELPID);

        fStylesViewer.setInput(getStylePreferenceKeys());

        applyStyles();

        fStylesViewer.addSelectionChangedListener(new ISelectionChangedListener() {
            public void selectionChanged(SelectionChangedEvent event) {
                if (!event.getSelection().isEmpty()) {
                    Object o = ((IStructuredSelection)event.getSelection()).getFirstElement();
                    String namedStyle = o.toString();
                    activate(namedStyle);
                    if (namedStyle == null) {
                        return;
                    }
                }
            }
        });

        fColorSelector.addListener(new IPropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent event) {
                if (event.getProperty().equals(ColorSelector.PROP_COLORCHANGE)) {
                    Object o = ((IStructuredSelection)fStylesViewer.getSelection()).getFirstElement();
                    String namedStyle = o.toString();
                    String prefString = getOverlayStore().getString(namedStyle);
                    String[] stylePrefs = ColorHelper.unpackStylePreferences(prefString);
                    if (stylePrefs != null) {
                        String oldValue = stylePrefs[0];
                        // open color dialog to get new color
                        String newValue = ColorHelper.toRGBString(fColorSelector.getColorValue());

                        if (!newValue.equals(oldValue)) {
                            stylePrefs[0] = newValue;
                            String newPrefString = ColorHelper.packStylePreferences(stylePrefs);
                            getOverlayStore().setValue(namedStyle, newPrefString);
                            applyStyles();
                            fText.redraw();
                        }
                    }
                }
            }
        });

        fBold.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                super.widgetSelected(e);
                // get current (newly old) style
                Object o = ((IStructuredSelection)fStylesViewer.getSelection()).getFirstElement();
                String namedStyle = o.toString();
                String prefString = getOverlayStore().getString(namedStyle);
                String[] stylePrefs = ColorHelper.unpackStylePreferences(prefString);
                if (stylePrefs != null) {
                    String oldValue = stylePrefs[2];
                    String newValue = String.valueOf(fBold.getSelection());
                    if (!newValue.equals(oldValue)) {
                        stylePrefs[2] = newValue;
                        String newPrefString = ColorHelper.packStylePreferences(stylePrefs);
                        getOverlayStore().setValue(namedStyle, newPrefString);
                        applyStyles();
                        fText.redraw();
                    }
                }
            }
        });

        fItalic.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                super.widgetSelected(e);
                // get current (newly old) style
                Object o = ((IStructuredSelection)fStylesViewer.getSelection()).getFirstElement();
                String namedStyle = o.toString();
                String prefString = getOverlayStore().getString(namedStyle);
                String[] stylePrefs = ColorHelper.unpackStylePreferences(prefString);
                if (stylePrefs != null) {
                    String oldValue = stylePrefs[3];
                    String newValue = String.valueOf(fItalic.getSelection());
                    if (!newValue.equals(oldValue)) {
                        stylePrefs[3] = newValue;
                        String newPrefString = ColorHelper.packStylePreferences(stylePrefs);
                        getOverlayStore().setValue(namedStyle, newPrefString);
                        applyStyles();
                        fText.redraw();
                    }
                }
            }
        });

        fStrike.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                super.widgetSelected(e);
                // get current (newly old) style
                Object o = ((IStructuredSelection)fStylesViewer.getSelection()).getFirstElement();
                String namedStyle = o.toString();
                String prefString = getOverlayStore().getString(namedStyle);
                String[] stylePrefs = ColorHelper.unpackStylePreferences(prefString);
                if (stylePrefs != null) {
                    String oldValue = stylePrefs[4];
                    String newValue = String.valueOf(fStrike.getSelection());
                    if (!newValue.equals(oldValue)) {
                        stylePrefs[4] = newValue;
                        String newPrefString = ColorHelper.packStylePreferences(stylePrefs);
                        getOverlayStore().setValue(namedStyle, newPrefString);
                        applyStyles();
                        fText.redraw();
                    }
                }
            }
        });

        fUnderline.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                super.widgetSelected(e);
                // get current (newly old) style
                Object o = ((IStructuredSelection)fStylesViewer.getSelection()).getFirstElement();
                String namedStyle = o.toString();
                String prefString = getOverlayStore().getString(namedStyle);
                String[] stylePrefs = ColorHelper.unpackStylePreferences(prefString);
                if (stylePrefs != null) {
                    String oldValue = stylePrefs[5];
                    String newValue = String.valueOf(fUnderline.getSelection());
                    if (!newValue.equals(oldValue)) {
                        stylePrefs[5] = newValue;
                        String newPrefString = ColorHelper.packStylePreferences(stylePrefs);
                        getOverlayStore().setValue(namedStyle, newPrefString);
                        applyStyles();
                        fText.redraw();
                    }
                }
            }
        });

        fClearStyle.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                if (fStylesViewer.getSelection().isEmpty()) {
                    return;
                }
                String namedStyle = ((IStructuredSelection)fStylesViewer.getSelection()).getFirstElement().toString();
                getOverlayStore().setToDefault(namedStyle);
                applyStyles();
                fText.redraw();
                activate(namedStyle);
            }
        });

        return pageComponent;
    }

    private String[] getColorSchemes() {
        Set<String> nameSet = XQDTColorSchemeRegistry.getColorSchemes().keySet();
        List<String> orderedList = new ArrayList<String>();
        for (String name : nameSet) {
            if (name.startsWith("WTP")) {
                orderedList.add(0, name);
            } else if (name.startsWith("XQDT")) {
                orderedList.add(0, name);
            } else {
                orderedList.add(name);
            }
        }
        return orderedList.toArray(new String[orderedList.size()]);
    }

    private Label createLabel(Composite parent, String text) {
        Label label = new Label(parent, SWT.WRAP);
        label.setText(text);
        GridData data = new GridData(SWT.FILL, SWT.FILL, false, false);
        label.setLayoutData(data);
        label.setBackground(parent.getBackground());
        return label;
    }

    // protected Label createDescriptionLabel(Composite parent) {
    // return null;
    // }

    /**
     * Set up all the style preference keys in the overlay store
     */
    private OverlayKey[] createOverlayStoreKeys() {
        List<OverlayPreferenceStore.OverlayKey> overlayKeys = new ArrayList<OverlayPreferenceStore.OverlayKey>();

        Iterator<String> i = getStylePreferenceKeys().iterator();
        while (i.hasNext()) {
            overlayKeys.add(new OverlayPreferenceStore.OverlayKey(OverlayPreferenceStore.STRING, i.next()));
        }

        OverlayPreferenceStore.OverlayKey[] keys = new OverlayPreferenceStore.OverlayKey[overlayKeys.size()];
        overlayKeys.toArray(keys);
        return keys;
    }

    /**
     * Creates the List viewer where we see the various syntax element display names--would it ever
     * be a Tree like JDT's?
     * 
     * @param parent
     * @return
     */
    private StructuredViewer createStylesViewer(Composite parent) {
        StructuredViewer stylesViewer = new ListViewer(parent, SWT.SINGLE | SWT.V_SCROLL | SWT.H_SCROLL | SWT.BORDER);
        stylesViewer.setComparator(new ViewerComparator(Collator.getInstance()));
        stylesViewer.setLabelProvider(new LabelProvider() {
            public String getText(Object element) {
                Object description = fStyleToDescriptionMap.get(element);
                if (description != null) {
                    return description.toString();
                }
                return super.getText(element);
            }
        });
        stylesViewer.setContentProvider(new ITreeContentProvider() {
            public void dispose() {
            }

            public Object[] getChildren(Object parentElement) {
                return getStylePreferenceKeys().toArray();
            }

            public Object[] getElements(Object inputElement) {
                return getChildren(inputElement);
            }

            public Object getParent(Object element) {
                return getStylePreferenceKeys();
            }

            public boolean hasChildren(Object element) {
                return false;
            }

            public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
            }
        });
        return stylesViewer;
    }

    public void dispose() {
        if (fOverlayStore != null) {
            fOverlayStore.stop();
        }
        super.dispose();
    }

    protected IPreferenceStore doGetPreferenceStore() {
        return XQDTSSEUIPlugin.getDefault().getPreferenceStore();
    }

    private TextAttribute getAttributeFor(String namedStyle) {
        TextAttribute ta = new TextAttribute(fDefaultColor, null, SWT.NORMAL);

        if (namedStyle != null && fOverlayStore != null) {
            // note: "namedStyle" *is* the preference key
            String prefString = getOverlayStore().getString(namedStyle);
            String[] stylePrefs = ColorHelper.unpackStylePreferences(prefString);
            if (stylePrefs != null) {
                RGB foreground = ColorHelper.toRGB(stylePrefs[0]);
                RGB background = ColorHelper.toRGB(stylePrefs[1]);

                int fontModifier = SWT.NORMAL;

                if (stylePrefs.length > 2) {
                    boolean on = Boolean.valueOf(stylePrefs[2]).booleanValue();
                    if (on) {
                        fontModifier = fontModifier | SWT.BOLD;
                    }
                }
                if (stylePrefs.length > 3) {
                    boolean on = Boolean.valueOf(stylePrefs[3]).booleanValue();
                    if (on) {
                        fontModifier = fontModifier | SWT.ITALIC;
                    }
                }
                if (stylePrefs.length > 4) {
                    boolean on = Boolean.valueOf(stylePrefs[4]).booleanValue();
                    if (on) {
                        fontModifier = fontModifier | TextAttribute.STRIKETHROUGH;
                    }
                }
                if (stylePrefs.length > 5) {
                    boolean on = Boolean.valueOf(stylePrefs[5]).booleanValue();
                    if (on) {
                        fontModifier = fontModifier | TextAttribute.UNDERLINE;
                    }
                }

                ta = new TextAttribute((foreground != null) ? EditorUtility.getColor(foreground) : null,
                        (background != null) ? EditorUtility.getColor(background) : null, fontModifier);
            }
        }
        return ta;
    }

    private String getExampleText() {
        return XQDTUIMessages.Sample_XQuery_doc;
    }

    private String getNamedStyleAtOffset(int offset) {
        // ensure the offset is clean
        if (offset >= fDocument.getLength()) {
            return getNamedStyleAtOffset(fDocument.getLength() - 1);
        } else if (offset < 0) {
            return getNamedStyleAtOffset(0);
        }
        IStructuredDocumentRegion documentRegion = fDocument.getFirstStructuredDocumentRegion();
        while (documentRegion != null && !documentRegion.containsOffset(offset)) {
            documentRegion = documentRegion.getNext();
        }
        if (documentRegion != null) {
            // find the ITextRegion's Context at this offset
            ITextRegion interest = documentRegion.getRegionAtCharacterOffset(offset);
            if (interest == null) {
                return null;
            }
            if (offset > documentRegion.getTextEndOffset(interest)) {
                return null;
            }
            String regionContext = interest.getType();
            if (regionContext == null) {
                return null;
            }
            // find the named style (internal/selectable name) for that
            // context
            String namedStyle = fContextToStyleMap.get(regionContext);
            if (namedStyle != null) {
                return namedStyle;
            }
        }
        return null;
    }

    private OverlayPreferenceStore getOverlayStore() {
        return fOverlayStore;
    }

    private Collection<String> getStylePreferenceKeys() {
        if (fStylePreferenceKeys == null) {
            List<String> styles = new ArrayList<String>();
            styles.add(XQDTLineStyleProvider.CK_KEYWORD);
            styles.add(XQDTLineStyleProvider.CK_STRING_LITERAL);
            styles.add(XQDTLineStyleProvider.CK_DOLLAR_EXPR);
            styles.add(XQDTLineStyleProvider.CK_TYPE);
            styles.add(XQDTLineStyleProvider.CK_COMMENT);
            styles.add(XQDTLineStyleProvider.CK_OPERATOR);
            styles.add(XQDTLineStyleProvider.CK_PRAGMA);
            styles.add(XQDTLineStyleProvider.CK_FUNCTION_NAME);
            styles.add(XQDTLineStyleProvider.CK_XML_ATTR_EQUAL);
            styles.add(XQDTLineStyleProvider.CK_XML_ATTR_NAME);
            styles.add(XQDTLineStyleProvider.CK_XML_ATTR_VALUE);
            styles.add(XQDTLineStyleProvider.CK_XML_CDATA_CONTENT);
            styles.add(XQDTLineStyleProvider.CK_XML_COMMENT);
            styles.add(XQDTLineStyleProvider.CK_XML_CONTENT);
            styles.add(XQDTLineStyleProvider.CK_XML_ENTITY_REFERENCE);
            styles.add(XQDTLineStyleProvider.CK_XML_PI_CONTENT);
            styles.add(XQDTLineStyleProvider.CK_XML_TAG_DELIMITER);
            styles.add(XQDTLineStyleProvider.CK_XML_TAG_NAME);
            fStylePreferenceKeys = styles;
        }
        return fStylePreferenceKeys;
    }

    private KeyListener getTextKeyListener() {
        return new KeyListener() {
            public void keyPressed(KeyEvent e) {
                if (e.widget instanceof StyledText) {
                    int x = ((StyledText)e.widget).getCaretOffset();
                    selectColorAtOffset(x);
                }
            }

            public void keyReleased(KeyEvent e) {
                if (e.widget instanceof StyledText) {
                    int x = ((StyledText)e.widget).getCaretOffset();
                    selectColorAtOffset(x);
                }
            }
        };
    }

    private MouseListener getTextMouseListener() {
        return new MouseListener() {
            public void mouseDoubleClick(MouseEvent e) {
            }

            public void mouseDown(MouseEvent e) {
            }

            public void mouseUp(MouseEvent e) {
                if (e.widget instanceof StyledText) {
                    int x = ((StyledText)e.widget).getCaretOffset();
                    selectColorAtOffset(x);
                }
            }
        };
    }

    private SelectionListener getTextSelectionListener() {
        return new SelectionListener() {
            public void widgetDefaultSelected(SelectionEvent e) {
                selectColorAtOffset(e.x);
                if (e.widget instanceof StyledText) {
                    ((StyledText)e.widget).setSelection(e.x);
                }
            }

            public void widgetSelected(SelectionEvent e) {
                selectColorAtOffset(e.x);
                if (e.widget instanceof StyledText) {
                    ((StyledText)e.widget).setSelection(e.x);
                }
            }
        };
    }

    private TraverseListener getTraverseListener() {
        return new TraverseListener() {
            /**
             * @see org.eclipse.swt.events.TraverseListener#keyTraversed(TraverseEvent)
             */
            public void keyTraversed(TraverseEvent e) {
                if (e.widget instanceof StyledText) {
                    if ((e.detail == SWT.TRAVERSE_TAB_NEXT) || (e.detail == SWT.TRAVERSE_TAB_PREVIOUS)) {
                        e.doit = true;
                    }
                }
            }
        };
    }

    public void init(IWorkbench workbench) {
        setDescription(SSEUIMessages.SyntaxColoring_Description);

        fStyleToDescriptionMap = new HashMap<String, String>();
        fContextToStyleMap = new HashMap<String, String>();

        initStyleToDescriptionMap();
        initRegionContextToStyleMap();

        fOverlayStore = new OverlayPreferenceStore(getPreferenceStore(), createOverlayStoreKeys());
        fOverlayStore.load();
        fOverlayStore.start();
    }

    private void initRegionContextToStyleMap() {
        fContextToStyleMap.put(XQueryTokenizer.XQUERY_COMMENT, XQDTLineStyleProvider.CK_COMMENT);

        fContextToStyleMap.put(XQueryTokenizer.KW_DECLARE, XQDTLineStyleProvider.CK_KEYWORD);
        fContextToStyleMap.put(XQueryTokenizer.KW_FUNCTION, XQDTLineStyleProvider.CK_KEYWORD);
        fContextToStyleMap.put(XQueryTokenizer.KW_AS, XQDTLineStyleProvider.CK_KEYWORD);
        fContextToStyleMap.put(XQueryTokenizer.KW_LET, XQDTLineStyleProvider.CK_KEYWORD);
        fContextToStyleMap.put(XQueryTokenizer.KW_RETURN, XQDTLineStyleProvider.CK_KEYWORD);

        fContextToStyleMap.put(XQueryTokenizer.FUNCTIONNAME, XQDTLineStyleProvider.CK_FUNCTION_NAME);
        fContextToStyleMap.put(XQueryTokenizer.VARREF, XQDTLineStyleProvider.CK_DOLLAR_EXPR);
        fContextToStyleMap.put(XQueryTokenizer.ST_ATOMICTYPE, XQDTLineStyleProvider.CK_TYPE);

        fContextToStyleMap.put(XQueryTokenizer.OP_MINUS, XQDTLineStyleProvider.CK_OPERATOR);

        fContextToStyleMap.put(XQueryTokenizer.STRINGLITERAL, XQDTLineStyleProvider.CK_STRING_LITERAL);

        fContextToStyleMap.put(XQueryTokenizer.XML_TAG_OPEN, XQDTLineStyleProvider.CK_XML_TAG_DELIMITER);
        fContextToStyleMap.put(XQueryTokenizer.XML_TAG_CLOSE, XQDTLineStyleProvider.CK_XML_TAG_DELIMITER);
        fContextToStyleMap.put(XQueryTokenizer.XML_TAG_NAME, XQDTLineStyleProvider.CK_XML_TAG_NAME);

        fContextToStyleMap.put(XQueryTokenizer.XML_TAG_ATTRIBUTE_NAME, XQDTLineStyleProvider.CK_XML_ATTR_NAME);
        fContextToStyleMap.put(XQueryTokenizer.XML_TAG_ATTRIBUTE_EQUALS, XQDTLineStyleProvider.CK_XML_ATTR_EQUAL);
        fContextToStyleMap.put(XQueryTokenizer.XML_ATTR_CHAR, XQDTLineStyleProvider.CK_XML_ATTR_VALUE);

        fContextToStyleMap.put(XQueryTokenizer.XML_ELEM_CONTENT_CHAR, XQDTLineStyleProvider.CK_XML_CONTENT);
        fContextToStyleMap.put(XQueryTokenizer.XML_END_TAG_OPEN, XQDTLineStyleProvider.CK_XML_TAG_DELIMITER);

        fContextToStyleMap.put(XQueryTokenizer.XML_COMMENT_OPEN, XQDTLineStyleProvider.CK_XML_COMMENT);
        fContextToStyleMap.put(XQueryTokenizer.XML_COMMENT_TEXT, XQDTLineStyleProvider.CK_XML_COMMENT);
        fContextToStyleMap.put(XQueryTokenizer.XML_COMMENT_CLOSE, XQDTLineStyleProvider.CK_XML_COMMENT);
    }

    private void initStyleToDescriptionMap() {
        fStyleToDescriptionMap.put(XQDTLineStyleProvider.CK_KEYWORD, XQDTUIMessages.Keywords_UI_);
        fStyleToDescriptionMap.put(XQDTLineStyleProvider.CK_STRING_LITERAL, XQDTUIMessages.StringLiteral_UI_);
        fStyleToDescriptionMap.put(XQDTLineStyleProvider.CK_DOLLAR_EXPR, XQDTUIMessages.VarRef_UI_);
        fStyleToDescriptionMap.put(XQDTLineStyleProvider.CK_TYPE, XQDTUIMessages.Type_UI_);
        fStyleToDescriptionMap.put(XQDTLineStyleProvider.CK_COMMENT, XQDTUIMessages.Comment_UI_);
        fStyleToDescriptionMap.put(XQDTLineStyleProvider.CK_OPERATOR, XQDTUIMessages.Operator_UI_);
        fStyleToDescriptionMap.put(XQDTLineStyleProvider.CK_XML_TAG_DELIMITER, XQDTUIMessages.Tag_Delimiters_UI_);
        fStyleToDescriptionMap.put(XQDTLineStyleProvider.CK_PRAGMA, XQDTUIMessages.Pragma_UI_);
        fStyleToDescriptionMap.put(XQDTLineStyleProvider.CK_FUNCTION_NAME, XQDTUIMessages.FunctionCall_UI_);
        fStyleToDescriptionMap.put(XQDTLineStyleProvider.CK_XML_ATTR_EQUAL, XQDTUIMessages.Attribute_Equals_UI_);
        fStyleToDescriptionMap.put(XQDTLineStyleProvider.CK_XML_ATTR_NAME, XQDTUIMessages.Attribute_Names_UI_);
        fStyleToDescriptionMap.put(XQDTLineStyleProvider.CK_XML_ATTR_VALUE, XQDTUIMessages.Attribute_Values_UI_);
        fStyleToDescriptionMap.put(XQDTLineStyleProvider.CK_XML_CDATA_CONTENT, XQDTUIMessages.CDATA_Content_UI_);
        fStyleToDescriptionMap.put(XQDTLineStyleProvider.CK_XML_CONTENT, XQDTUIMessages.Content_UI_);
        fStyleToDescriptionMap
                .put(XQDTLineStyleProvider.CK_XML_ENTITY_REFERENCE, XQDTUIMessages.Entity_Reference_UI_);
        fStyleToDescriptionMap.put(XQDTLineStyleProvider.CK_XML_PI_CONTENT,
                XQDTUIMessages.Processing_Instruction_Con_UI__UI_);
        fStyleToDescriptionMap.put(XQDTLineStyleProvider.CK_XML_TAG_NAME, XQDTUIMessages.Tag_Names_UI_);
        fStyleToDescriptionMap.put(XQDTLineStyleProvider.CK_XML_COMMENT, XQDTUIMessages.Comment_Content_UI_);

    }

    protected void performDefaults() {
        super.performDefaults();
        getOverlayStore().loadDefaults();
        applyStyles();
        fStylesViewer.setSelection(StructuredSelection.EMPTY);
        activate(null);
        fText.redraw();
    }

    @SuppressWarnings("deprecation")
    public boolean performOk() {
        getOverlayStore().propagate();

        XQDTSSEUIPlugin.getDefault().savePluginPreferences();
        SSEUIPlugin.getDefault().savePluginPreferences();
        return true;
    }

    private void selectColorAtOffset(int offset) {
        String namedStyle = getNamedStyleAtOffset(offset);
        if (namedStyle != null) {
            fStylesViewer.setSelection(new StructuredSelection(namedStyle));
            fStylesViewer.reveal(namedStyle);
        } else {
            fStylesViewer.setSelection(StructuredSelection.EMPTY);
        }
        activate(namedStyle);
    }

    /**
     * Specifically set the reporting name of a control for accessibility
     */
    private void setAccessible(Control control, String name) {
        if (control == null) {
            return;
        }
        final String n = name;
        control.getAccessible().addAccessibleListener(new AccessibleAdapter() {
            public void getName(AccessibleEvent e) {
                if (e.childID == ACC.CHILDID_SELF) {
                    e.result = n;
                }
            }
        });
    }
}
