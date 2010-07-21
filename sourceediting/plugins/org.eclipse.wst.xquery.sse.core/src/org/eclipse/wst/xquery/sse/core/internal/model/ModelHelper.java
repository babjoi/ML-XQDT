package org.eclipse.wst.xquery.sse.core.internal.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocumentRegion;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.ASTClauses;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.ASTFunctionDecl;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.ASTTypeswitch;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.IASTNode;

/**
 * Collection of utility methods related to the XQuery model
 * 
 * @author villardl
 */
@SuppressWarnings("restriction")
public class ModelHelper {

	/** Gets list of in-scope variable region definition */
	public static List<String> getInScopeVariables(IASTNode node) {
		if (node != null) {
			List<String> vars = new ArrayList<String>();
			getInScopeVariables(node, vars);
			return vars;
		}

		return null;
	}

	/**
	 * @param node
	 * @param vars
	 * @return
	 */
	private static void getInScopeVariables(IASTNode node, List<String> vars) {
		// TODO: should be implemented in IASTNode for extensiblity purpose.
		
		IASTNode parent = node.getASTParent();
		while (parent != null) {
			switch (parent.getType()) {
			case IASTNode.FLWOR:
			case IASTNode.QUANTIFIED:
				final ASTClauses clauses = (ASTClauses) parent;

				// Compute the last binding expression to include
				int lastBinding = 0;
				while (lastBinding < clauses.getBindingExprCount()) {
					if (clauses.getBindingExpr(lastBinding) == node) {
						break;
					}
					lastBinding++;
				}
				lastBinding--;
				while (lastBinding >= 0) {
					IStructuredDocumentRegion var = clauses
							.getBindingVariable(lastBinding--);
					vars.add(var.getFullText().trim());
				}
				break;
			case IASTNode.FUNCTIONDECL:
				final ASTFunctionDecl decl = (ASTFunctionDecl) parent;
				// Include parameters..
				for (int i = decl.getParamCount() - 1; i >= 0; i--) {
					vars.add(decl.getParamNameAt(i).getFullText().trim());
				}
				break;
			case IASTNode.TYPESWITCH:
				final ASTTypeswitch ts = (ASTTypeswitch) parent;

				// Identify which case
				if (ts.getDefaultCaseExpr() == node) {
					IStructuredDocumentRegion var = ts.getDefaultCaseVarname();
					if (var != null)
						vars.add(var.getFullText().trim());
				} else {
					for (int i = ts.getCaseCount() - 1; i >= 0; i--) {
						if (ts.getCaseExpr(i) == node) {
							IStructuredDocumentRegion var = ts
									.getCaseVarname(i);
							if (var != null)
								vars.add(var.getFullText().trim());
							break;
						}
					}
				}

				break;
			case IASTNode.VARDECL:
				// Include variable declarations preceding this one...
				// TODO
			}
			node = parent;
			parent = parent.getASTParent();
		}
	}

}
