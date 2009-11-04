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
package org.eclipse.wst.xquery.internal.ui.folding;

import org.eclipse.core.runtime.ILog;
import org.eclipse.dltk.ui.text.folding.AbstractASTFoldingStructureProvider;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.rules.IPartitionTokenScanner;
import org.eclipse.wst.xquery.core.XQDTNature;
import org.eclipse.wst.xquery.internal.ui.text.IXQDTPartitions;
import org.eclipse.wst.xquery.internal.ui.text.XQDTPartitionScanner;
import org.eclipse.wst.xquery.internal.ui.text.rules.XQDTPartitioner;
import org.eclipse.wst.xquery.ui.XQDTUIPlugin;

public class XQDTFoldingStructureProvider extends AbstractASTFoldingStructureProvider {

    protected String getCommentPartition() {
        return IXQDTPartitions.XQDT_COMMENT;
    }

    protected ILog getLog() {
        return XQDTUIPlugin.getDefault().getLog();
    }

    protected String getNatureId() {
        return XQDTNature.NATURE_ID;
    }

    protected String getPartition() {
        return IXQDTPartitions.XQDT_PARTITIONING;
    }

    protected IPartitionTokenScanner getPartitionScanner() {
        return new XQDTPartitionScanner();
    }

    @Override
    protected IDocumentPartitioner getDocumentPartitioner() {
        return new XQDTPartitioner(getPartitionScanner(), getPartitionTypes());
    }

    protected String[] getPartitionTypes() {
        return IXQDTPartitions.XQDT_LEGAL_PARTITION_TYPES;
    }

}
