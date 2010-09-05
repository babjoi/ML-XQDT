package org.eclipse.wst.xquery.internal.ui.quickfixes;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.ui.editor.IScriptAnnotation;
import org.eclipse.dltk.ui.editor.ScriptMarkerAnnotation;
import org.eclipse.dltk.ui.text.IScriptCorrectionContext;
import org.eclipse.dltk.ui.text.IScriptCorrectionProcessor;

public class SequentialQuickFix implements IScriptCorrectionProcessor {

    public static final String XUST0001A = "[XUST0001]: A sequantial function is called from a non sequential function";
    public static final String XUST0001B = "[XPST0003]: Only a sequential function can have a body that is sequential expression";

    //public static final String XUST0001C = "[XUST0001]: A sequential expression is not allowed at this position";

    public SequentialQuickFix() {
    }

    public boolean canFix(IScriptAnnotation annotation) {
        if (annotation instanceof ScriptMarkerAnnotation) {
            ScriptMarkerAnnotation markerAnnotation = (ScriptMarkerAnnotation)annotation;
            try {
                String message = (String)markerAnnotation.getMarker().getAttribute("message");
                if (message.startsWith(XUST0001A) || message.startsWith(XUST0001B)) {
                    return true;
                }
            } catch (CoreException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean canFix(IMarker marker) {
        return false;
    }

    public void computeQuickAssistProposals(IScriptAnnotation annotation, IScriptCorrectionContext context) {
        if (annotation instanceof ScriptMarkerAnnotation) {
            ScriptMarkerAnnotation markerAnnotation = (ScriptMarkerAnnotation)annotation;
            try {
                String message = (String)markerAnnotation.getMarker().getAttribute("message");
                if (message.startsWith(XUST0001A)) {
                    context.addProposal(new AddSequentialKeywordCorrection(markerAnnotation));
                } else if (message.startsWith(XUST0001B)) {
                    context.addProposal(new AddSequentialKeywordCorrection(markerAnnotation));
                }
            } catch (CoreException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public void computeQuickAssistProposals(IMarker marker, IScriptCorrectionContext context) {
    }

}
