package org.eclipse.wst.xquery.sse.core.internal.model;

import java.util.List;

import org.eclipse.wst.xquery.sse.core.internal.model.ast.IASTNode;

/**
 * Collection of utility methods related to the XQuery model
 * 
 * @author villardl
 */ 
public class ModelHelper {

	/** Gets list of in-scope variable region definition */
	public static List<String> getInScopeVariables(IASTNode node) {
		if (node != null) {
			return node.getInScopeVariables();
		}

		return null;
	}

}
