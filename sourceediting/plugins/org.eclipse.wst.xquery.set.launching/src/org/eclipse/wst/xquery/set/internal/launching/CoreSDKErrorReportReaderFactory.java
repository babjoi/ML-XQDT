package org.eclipse.wst.xquery.set.internal.launching;

import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.wst.xquery.core.semantic.CheckErrorReportReaderFactory;
import org.eclipse.wst.xquery.core.semantic.SemanticCheckErrorReportReader;

public class CoreSDKErrorReportReaderFactory implements CheckErrorReportReaderFactory {

    public SemanticCheckErrorReportReader make(ISourceModule module, String data) {
        return new CoreSDKErrorReportReader(module, data);
    }

}
