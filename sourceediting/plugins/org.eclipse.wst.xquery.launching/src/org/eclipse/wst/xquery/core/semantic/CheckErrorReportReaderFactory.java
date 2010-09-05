package org.eclipse.wst.xquery.core.semantic;

import org.eclipse.dltk.core.ISourceModule;

public interface CheckErrorReportReaderFactory {
    public SemanticCheckErrorReportReader make(ISourceModule module, String data);
}
