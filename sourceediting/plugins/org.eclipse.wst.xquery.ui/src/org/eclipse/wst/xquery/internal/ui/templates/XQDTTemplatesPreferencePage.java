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
package org.eclipse.wst.xquery.internal.ui.templates;

import org.eclipse.dltk.ui.templates.ScriptTemplateAccess;
import org.eclipse.dltk.ui.templates.ScriptTemplatePreferencePage;
import org.eclipse.dltk.ui.text.ScriptSourceViewerConfiguration;
import org.eclipse.jface.text.IDocument;
import org.eclipse.wst.xquery.internal.ui.text.IXQDTPartitions;
import org.eclipse.wst.xquery.internal.ui.text.XQDTSimpleSourceViewerConfiguration;
import org.eclipse.wst.xquery.internal.ui.text.XQDTTextTools;
import org.eclipse.wst.xquery.ui.XQDTUIPlugin;

public class XQDTTemplatesPreferencePage extends ScriptTemplatePreferencePage {

    protected ScriptSourceViewerConfiguration createSourceViewerConfiguration() {
        return new XQDTSimpleSourceViewerConfiguration(getTextTools().getColorManager(), getPreferenceStore(), null,
                IXQDTPartitions.XQDT_PARTITIONING, false);
    }

    protected ScriptTemplateAccess getTemplateAccess() {
        return XQDTTemplateAccess.getInstance();
    }

    protected void setDocumentPartitioner(IDocument document) {
        getTextTools().setupDocumentPartitioner(document, IXQDTPartitions.XQDT_PARTITIONING);
    }

    protected void setPreferenceStore() {
        setPreferenceStore(XQDTUIPlugin.getDefault().getPreferenceStore());
    }

    private XQDTTextTools getTextTools() {
        return XQDTUIPlugin.getDefault().getTextTools();
    }

}
