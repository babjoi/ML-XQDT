/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.xquery.sse.core.internal.model.ast;

/**
 * Operator expression
 * 
 * @author <a href="villard@us.ibm.com">Lionel Villard</a>
 */
public class ASTOperator extends ASTParentNode {

	// Constants

	public static final int OP_COMMA = 0;

	public static final int OP_OR = 1;
	public static final int OP_AND = 2;
	public static final int OP_TO = 3;
	public static final int OP_EQ = 4;
	public static final int OP_NEQ = 5;
	public static final int OP_LT = 6;
	public static final int OP_LTE = 7;
	public static final int OP_GT = 8;
	public static final int OP_GTE = 9;
	public static final int OP_GEQ = 10;
	public static final int OP_GNEQ = 11;
	public static final int OP_GLT = 12;
	public static final int OP_GLTE = 13;
	public static final int OP_GGT = 14;
	public static final int OP_GGTE = 15;
	public static final int OP_IS = 16;
	public static final int OP_AFTER = 17;
	public static final int OP_BEFORE = 18;
	public static final int OP_PLUS = 19;
	public static final int OP_MINUS = 20;
	public static final int OP_MULTIPLY = 21;
	public static final int OP_DIV = 22;
	public static final int OP_IDIV = 23;
	public static final int OP_MOD = 24;
	public static final int OP_UNION = 25;
	public static final int OP_INTERSECT = 26;
	public static final int OP_EXCEPT = 27;
	public static final int OP_INSTANCEOF = 28;
	public static final int OP_TREATAS = 29;
	public static final int OP_CASTAS = 30;
	public static final int OP_CASTABLEAS = 31;

	public static final int PATH_SLASH = 32;
	public static final int PATH_SLASHSLASH = 33;

	// State

	/** Operator Type */
	protected int operatorType;

	// Constructors

	public ASTOperator(int operatorType) {
		this.operatorType = operatorType;
	}

	public ASTOperator() {
	}

	// Methods

	public int getOperatorType() {
		return operatorType;
	}

	public void setOperatorType(int type) {
		this.operatorType = type;

	}

	// Override

	@Override
	public int getType() {
		return OPERATOR;
	}

}
