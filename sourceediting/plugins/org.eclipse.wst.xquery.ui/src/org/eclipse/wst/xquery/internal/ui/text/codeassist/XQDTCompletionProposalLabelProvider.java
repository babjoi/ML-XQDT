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
package org.eclipse.wst.xquery.internal.ui.text.codeassist;

import org.eclipse.dltk.ui.text.completion.CompletionProposalLabelProvider;

public class XQDTCompletionProposalLabelProvider extends CompletionProposalLabelProvider {

    @Override
    protected StringBuffer appendParameterSignature(StringBuffer buffer, char[][] parameterTypes,
            char[][] parameterNames) {
        if (parameterNames != null) {
            for (int i = 0; i < parameterNames.length; i++) {
                if (i > 0) {
                    buffer.append(',');
                    buffer.append(' ');
                }
                buffer.append('$');
                buffer.append(parameterNames[i]);
            }
        }
        return buffer;
    }
}
