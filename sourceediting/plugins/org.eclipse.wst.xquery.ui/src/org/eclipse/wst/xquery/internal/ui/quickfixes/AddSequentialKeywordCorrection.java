package org.eclipse.wst.xquery.internal.ui.quickfixes;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.Token;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.core.SourceParserUtil;
import org.eclipse.dltk.ui.editor.ScriptMarkerAnnotation;
import org.eclipse.dltk.ui.text.completion.IScriptCompletionProposal;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.wst.xquery.core.model.ast.XQueryFunctionDecl;
import org.eclipse.wst.xquery.internal.core.parser.antlr.XQueryLexer;
import org.eclipse.wst.xquery.internal.ui.XQDTImages;

public class AddSequentialKeywordCorrection implements IScriptCompletionProposal {

    private XQueryFunctionDecl function = null;
    private Point selection = null;

    public AddSequentialKeywordCorrection(ScriptMarkerAnnotation markerAnnotation) {
        try {
            int charStart = (Integer)markerAnnotation.getMarker().getAttribute("charStart");
            ModuleDeclaration ast = SourceParserUtil.getModuleDeclaration(markerAnnotation.getSourceModule());
            for (MethodDeclaration function : ast.getFunctions()) {
                assert function instanceof XQueryFunctionDecl;
                if (function.sourceStart() < charStart && function.sourceEnd() > charStart) {
                    this.function = (XQueryFunctionDecl)function;
                }
            }
            assert this.function != null;
        } catch (ModelException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (CoreException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void apply(IDocument document) {
        try {
            int start = function.sourceStart();
            String modifiers = document.get(start + "declare".length(), function.getNameStart() - start);
            CharStream input = new ANTLRStringStream(modifiers);
            XQueryLexer lexer = new XQueryLexer(input);
            Token token;
            int index = 0;
            while ((token = lexer.nextToken()) != Token.EOF_TOKEN) {
                final String updating = "updating";
                if (updating.equals(token.getText())) {
                    System.err.println("getCharIndex: " + lexer.getCharIndex());
                    index = start + lexer.getCharIndex() - 1;
                    document.replace(index, updating.length(), "sequential ");
                    return;
                }
            }
            index = start + "declare".length();
            document.replace(index, 0, " sequential");
        } catch (BadLocationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public Point getSelection(IDocument document) {
        return selection;
    }

    public String getAdditionalProposalInfo() {
        return getDisplayString() + " to function " + function.getName() + "()";
    }

    public String getDisplayString() {
        return "Add sequential keyword.";
    }

    public Image getImage() {
        return XQDTImages.OBJ_ADD.createImage();
    }

    public IContextInformation getContextInformation() {
        return null;
    }

    public int getRelevance() {
        return 10;
    }

}
