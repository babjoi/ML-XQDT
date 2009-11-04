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
package org.eclipse.wst.xquery.internal.ui.editor;

import org.eclipse.core.filebuffers.IDocumentSetupParticipant;
import org.eclipse.jface.text.IDocument;
import org.eclipse.wst.xquery.internal.ui.text.IXQDTPartitions;
import org.eclipse.wst.xquery.internal.ui.text.XQDTTextTools;
import org.eclipse.wst.xquery.ui.XQDTUIPlugin;

public class XQDTDocumentSetupParticipant implements IDocumentSetupParticipant {

    public void setup(IDocument document) {
        XQDTTextTools tools = XQDTUIPlugin.getDefault().getTextTools();
        tools.setupDocumentPartitioner(document, IXQDTPartitions.XQDT_PARTITIONING);
    }

}
