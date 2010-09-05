package org.eclipse.wst.xquery.set.internal.ui.quickfixes;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.ui.editor.IScriptAnnotation;
import org.eclipse.dltk.ui.editor.ScriptMarkerAnnotation;
import org.eclipse.dltk.ui.text.IScriptCorrectionContext;
import org.eclipse.dltk.ui.text.IScriptCorrectionProcessor;

public class ModuleDeclQuickFix implements IScriptCorrectionProcessor {

    public static final String INVALID_LOGICAL_URI = "[SAUSA0001]:";
    public static final String INVALID_MODULE_NAME = "[SAUSA0002]:";

    public ModuleDeclQuickFix() {
    }

    public boolean canFix(IScriptAnnotation annotation) {
        if (annotation instanceof ScriptMarkerAnnotation) {
            IMarker marker = ((ScriptMarkerAnnotation)annotation).getMarker();
            try {
                String message = (String)marker.getAttribute(IMarker.MESSAGE);
                return message.startsWith(INVALID_LOGICAL_URI) || message.startsWith(INVALID_MODULE_NAME);
            } catch (CoreException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean canFix(IMarker marker) {
        throw new IllegalStateException();
    }

    public void computeQuickAssistProposals(IScriptAnnotation annotation, IScriptCorrectionContext context) {
        if (annotation instanceof ScriptMarkerAnnotation) {
            IMarker marker = ((ScriptMarkerAnnotation)annotation).getMarker();
            try {
                String message = (String)marker.getAttribute(IMarker.MESSAGE);
                if (message.startsWith(INVALID_LOGICAL_URI) || message.startsWith(INVALID_MODULE_NAME)) {
                    context.addProposal(new ModuleHandlerURICorrection(annotation));
                }
                /*
                 * if (message.startsWith(INVALID_LOGICAL_URI)) { context.addProposal(new
                 * ModuleHandlerURICorrection(annotation)); //context.addProposal(new
                 * ChangeProjectLogicalURICorrection(annotation)); } else if
                 * (message.startsWith(INVALID_MODULE_NAME)) { context.addProposal(new
                 * ModuleHandlerURICorrection(annotation)); }
                 */
            } catch (CoreException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public void computeQuickAssistProposals(IMarker marker, IScriptCorrectionContext context) {
    }
}
