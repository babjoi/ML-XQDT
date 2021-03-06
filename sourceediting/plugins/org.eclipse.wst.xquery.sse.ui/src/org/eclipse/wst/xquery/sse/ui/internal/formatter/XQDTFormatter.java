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
package org.eclipse.wst.xquery.sse.ui.internal.formatter;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.text.edits.MalformedTreeException;
import org.eclipse.text.edits.MultiTextEdit;
import org.eclipse.wst.sse.core.internal.format.AbstractStructuredFormatProcessor;
import org.eclipse.wst.sse.core.internal.format.IStructuredFormatter;
import org.eclipse.wst.sse.core.internal.provisional.IStructuredModel;
import org.eclipse.wst.sse.ui.internal.Logger;
import org.eclipse.wst.xquery.sse.core.internal.model.XQueryStructuredModel;
import org.w3c.dom.Node;

/**
 * XQuery formatter.
 * 
 * @author <a href="villard@us.ibm.com">Lionel Villard</a>
 */
public class XQDTFormatter extends AbstractStructuredFormatProcessor {

    // State

    /** Formatting preferences */
    protected XQDTStructuredFormatPreferences preferences;

    // Constructor

    public XQDTFormatter() {
        preferences = new XQDTStructuredFormatPreferences();
    }

    // Methods

    /**
     * Load formatting preferences
     * 
     * @return
     */
    protected XQDTStructuredFormatPreferences loadXQDTStructuredFormatPreferences() {
        XQDTStructuredFormatPreferences prefs = new XQDTStructuredFormatPreferences();

        prefs.setIndent("");
        prefs.pushTrailingWhitespaceLength(0, 0);
        return prefs;
    }

    // Overrides

    @Override
    public void formatModel(IStructuredModel model, int start, int length) {
        if (model != null) {
            try {
                final XQueryStructuredModel xmodel = (XQueryStructuredModel)model;

                // Only format if the model is syntactically correct.
                if (xmodel.isSyntacticallyValid()) {

                    MultiTextEdit edit = new MultiTextEdit();
                    DefaultXQDTPartitionFormatter.SINGLETON.format(xmodel.getModule(), edit,
                            loadXQDTStructuredFormatPreferences());
                    if (edit != null) {
                        try {
                            model.aboutToChangeModel();
                            edit.apply(model.getStructuredDocument());
                        } finally {
                            model.changedModel();
                        }
                    }
                }
            } catch (MalformedTreeException e) {
                // log for now, unless we find reason not to
                Logger.log(Logger.INFO, e.getMessage());
            } catch (BadLocationException e) {
                // log for now, unless we find reason not to
                Logger.log(Logger.INFO, e.getMessage());
            }
        }

    }

    @Override
    protected String getFileExtension() {
        return "xq";
    }

    @Override
    protected IStructuredFormatter getFormatter(Node node) {
        return null; // TODO.
    }

    @Override
    protected void refreshFormatPreferences() {

    }

}
