package org.eclipse.wst.xquery.internal.ui.text.correction;

import java.util.Collection;
import java.util.LinkedList;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.dltk.ui.editor.IScriptAnnotation;
import org.eclipse.dltk.ui.text.IScriptCorrectionContext;
import org.eclipse.dltk.ui.text.IScriptCorrectionProcessor;
import org.eclipse.wst.xquery.ui.XQDTUIPlugin;

public class XQueryCorrectionProcessor implements IScriptCorrectionProcessor {

    private static final String QUICKFIX_PROCESSOR_CONTRIBUTION_ID = "quickFixProcessors"; //$NON-NLS-1$

    private static Collection<IScriptCorrectionProcessor> processors = new LinkedList<IScriptCorrectionProcessor>();

    public XQueryCorrectionProcessor() {
        IConfigurationElement[] elements = Platform.getExtensionRegistry().getConfigurationElementsFor(
                XQDTUIPlugin.PLUGIN_ID, QUICKFIX_PROCESSOR_CONTRIBUTION_ID);
        for (IConfigurationElement element : elements) {
            try {
                Object extension = element.createExecutableExtension("class");
                if (extension instanceof IScriptCorrectionProcessor) {
                    processors.add((IScriptCorrectionProcessor)extension);
                } else {
                    //TODO: log
                    System.err.println(QUICKFIX_PROCESSOR_CONTRIBUTION_ID
                            + ": class attribute must be of subtype IScriptCorrectionProcessor");
                }
            } catch (CoreException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean canFix(IScriptAnnotation annotation) {
        for (IScriptCorrectionProcessor processor : processors) {
            if (processor.canFix(annotation)) {
                return true;
            }
        }
        return false;
    }

    public boolean canFix(IMarker marker) {
        for (IScriptCorrectionProcessor processor : processors) {
            if (processor.canFix(marker)) {
                return true;
            }
        }
        return false;
    }

    public void computeQuickAssistProposals(IScriptAnnotation annotation, IScriptCorrectionContext context) {
        for (IScriptCorrectionProcessor processor : processors) {
            processor.computeQuickAssistProposals(annotation, context);
        }
    }

    public void computeQuickAssistProposals(IMarker marker, IScriptCorrectionContext context) {
        for (IScriptCorrectionProcessor processor : processors) {
            processor.computeQuickAssistProposals(marker, context);
        }
    }

}
