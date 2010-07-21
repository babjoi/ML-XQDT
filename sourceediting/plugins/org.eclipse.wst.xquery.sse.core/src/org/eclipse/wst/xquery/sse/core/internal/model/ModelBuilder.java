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
package org.eclipse.wst.xquery.sse.core.internal.model;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocumentRegion;
import org.eclipse.wst.validation.internal.provisional.core.IMessage;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.ASTFLWOR;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.ASTFunctionCall;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.ASTFunctionDecl;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.ASTIf;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.ASTLiteral;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.ASTModule;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.ASTNodeFactory;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.ASTOperator;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.ASTQuantified;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.ASTSequenceType;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.ASTTypeswitch;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.ASTVarDecl;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.ASTVarRef;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.IASTNode;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.xml.ASTDirAttribute;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.xml.ASTDirElement;
import org.eclipse.wst.xquery.sse.core.internal.regions.XQueryRegions;
import org.eclipse.wst.xquery.sse.core.internal.sdregions.ModuleDeclStructuredDocumentRegion;
import org.eclipse.wst.xquery.sse.core.internal.sdregions.VersionDeclStructuredDocumentRegion;
import org.eclipse.wst.xquery.sse.core.internal.sdregions.XQueryStructuredDocumentRegion;

/**
 * Update the XQuery AST.
 * 
 * <p>For now, the new structured document regions are fully traversed, along with
 * the existing AST (if any). The goal is to minimize AST nodes changes.
 * 
 * <p>In the future, it is possible to skip reparsing subtrees based on changes
 * location.
 * 
 * <p>Checks the language syntax and provides appropriate messages for downstream validators. Note that it performs only
 * minimal validation to speed up reparsing.
 * 
 * @author <a href="villard@us.ibm.com">Lionel Villard</a>
 */
@SuppressWarnings("restriction")
public class ModelBuilder {

	// State

	/** AST node factory */
	final protected ASTNodeFactory nodeFactory;

	/** Ending region for reparsing */
	protected IStructuredDocumentRegion endSDRegion; // TODO: not used yet

	/** Current structured document region */
	protected IStructuredDocumentRegion currentSDRegion;

	/** Current region index in the current structured document region */
	protected int currentRegionIdx;

	/** Starting offset of the change */
	protected int offset;

	/** Length of the change */
	protected int length;

	/** Validation messages */
	protected List<IMessage> messages;
	
	// Some filters...
	final protected OperatorFilter sequenceFilter = new OperatorFilter(new int[] { ASTOperator.OP_COMMA },
			new String[] { XQueryRegions.COMMA });

	final protected OperatorFilter orFilter = new OperatorFilter(new int[] { ASTOperator.OP_OR },
			new String[] { XQueryRegions.OP_OR });

	final protected OperatorFilter andFilter = new OperatorFilter(new int[] { ASTOperator.OP_AND },
			new String[] { XQueryRegions.OP_AND });

	final protected OperatorFilter comparisonFilter = new OperatorFilter(new int[] { ASTOperator.OP_EQ,
			ASTOperator.OP_NEQ, ASTOperator.OP_LT, ASTOperator.OP_LTE, ASTOperator.OP_GT, ASTOperator.OP_GTE,
			ASTOperator.OP_GEQ, ASTOperator.OP_GNEQ, ASTOperator.OP_GLT, ASTOperator.OP_GLTE, ASTOperator.OP_GGT,
			ASTOperator.OP_GGTE, ASTOperator.OP_IS, ASTOperator.OP_AFTER, ASTOperator.OP_BEFORE }, new String[] {
			XQueryRegions.OP_EQ, XQueryRegions.OP_NEQ, XQueryRegions.OP_LT, XQueryRegions.OP_LTE, XQueryRegions.OP_GT,
			XQueryRegions.OP_GTE, XQueryRegions.OP_GEQ, XQueryRegions.OP_GNEQ, XQueryRegions.OP_GLT,
			XQueryRegions.OP_GLTE, XQueryRegions.OP_GGT, XQueryRegions.OP_GGTE, XQueryRegions.OP_IS,
			XQueryRegions.OP_AFTER, XQueryRegions.OP_BEFORE });

	final protected OperatorFilter rangeFilter = new OperatorFilter(new int[] { ASTOperator.OP_TO },
			new String[] { XQueryRegions.OP_TO });

	final protected OperatorFilter additiveFilter = new OperatorFilter(new int[] { ASTOperator.OP_PLUS,
			ASTOperator.OP_MINUS }, new String[] { XQueryRegions.OP_PLUS, XQueryRegions.OP_MINUS });

	final protected OperatorFilter multiplicativeFilter = new OperatorFilter(new int[] { ASTOperator.OP_MULTIPLY,
			ASTOperator.OP_DIV, ASTOperator.OP_IDIV, ASTOperator.OP_MOD }, new String[] { XQueryRegions.OP_MULTIPLY,
			XQueryRegions.OP_DIV, XQueryRegions.OP_IDIV, XQueryRegions.OP_MOD });

	final protected OperatorFilter unionFilter = new OperatorFilter(new int[] { ASTOperator.OP_UNION },
			new String[] { XQueryRegions.OP_UNION });

	final protected OperatorFilter intersectExceptFilter = new OperatorFilter(new int[] { ASTOperator.OP_INTERSECT,
			ASTOperator.OP_EXCEPT }, new String[] { XQueryRegions.OP_INTERSECT, XQueryRegions.OP_EXCEPT });

	final protected OperatorFilter instanceOfFilter = new OperatorFilter(new int[] { ASTOperator.OP_INSTANCEOF },
			new String[] { XQueryRegions.OP_INSTANCEOF });

	final protected OperatorFilter treatFilter = new OperatorFilter(new int[] { ASTOperator.OP_TREATAS },
			new String[] { XQueryRegions.OP_TREATAS });

	final protected OperatorFilter castFilter = new OperatorFilter(new int[] { ASTOperator.OP_CASTAS },
			new String[] { XQueryRegions.OP_CASTAS });

	final protected OperatorFilter castableFilter = new OperatorFilter(new int[] { ASTOperator.OP_CASTABLEAS },
			new String[] { XQueryRegions.OP_CASTABLEAS });

	final protected OperatorFilter relativePathFilter = new OperatorFilter(new int[] { ASTOperator.PATH_SLASH,
			ASTOperator.PATH_SLASHSLASH, }, new String[] { XQueryRegions.PATH_SLASH, XQueryRegions.PATH_SLASHSLASH });

	final protected RegionFilter stepFilter = new RegionFilter(new String[] { XQueryRegions.PATH_ABBREVATTRIBUTE,
			XQueryRegions.PATH_ABBREVPARENT, XQueryRegions.PATH_ANCESTOR, XQueryRegions.PATH_ANCESTOR_OR_SELF,
			XQueryRegions.PATH_ATTRIBUTE, XQueryRegions.PATH_CHILD, XQueryRegions.PATH_DESCENDANT,
			XQueryRegions.PATH_DESCENDANT_OR_SELF, XQueryRegions.PATH_FOLLOWING, XQueryRegions.PATH_FOLLOWING_SIBLING,
			XQueryRegions.PATH_PARENT, XQueryRegions.PATH_PRECEDING, XQueryRegions.PATH_PRECEDING_SIBLING,
			XQueryRegions.PATH_SELF });

	final protected RegionFilter nodeTestFilter = new RegionFilter(new String[] { XQueryRegions.KT_ANYKINDTEST,
			XQueryRegions.KT_ATTRIBUTETEST, XQueryRegions.KT_COMMENTTEST, XQueryRegions.KT_DOCUMENTTEST,
			XQueryRegions.KT_ELEMENTTEST, XQueryRegions.KT_PITEST, XQueryRegions.KT_SCHEMAATTRIBUTETEST,
			XQueryRegions.KT_SCHEMAELEMENTTEST, XQueryRegions.KT_TEXTTEST, XQueryRegions.QNAME });

	final protected RegionFilter directConstructorFilter = new RegionFilter(new String[] { XQueryRegions.XML_TAG_OPEN,
			XQueryRegions.XML_COMMENT, XQueryRegions.XML_PI });

	final protected RegionFilter commonContentFilter = new RegionFilter(new String[] { XQueryRegions.XML_PE_REFERENCE,
			XQueryRegions.XML_CHAR_REF, XQueryRegions.XML_ESCAPE_START_EXPR, XQueryRegions.XML_ESCAPE_CLOSE_EXPR,
			XQueryRegions.XML_START_EXPR });

	final protected RegionFilter flworFilter = new RegionFilter(new String[] { XQueryRegions.KW_FOR,
			XQueryRegions.KW_LET });

	final protected RegionFilter quantifiedFilter = new RegionFilter(new String[] { XQueryRegions.KW_SOME,
			XQueryRegions.KW_EVERY });

	// And associated continuations....
	final protected Continuation exprContinuation = new ExprContinuation();
	final protected Continuation orContinuation = new OrContinuation();
	final protected Continuation andContinuation = new AndContinuation();
	final protected Continuation comparisonContinuation = new ComparisonContinuation();
	final protected Continuation rangeContinuation = new RangeContinuation();
	final protected Continuation additiveContinuation = new AdditiveContinuation();
	final protected Continuation multiplicativeContinuation = new MultiplicativeContinuation();
	final protected Continuation unionContinuation = new UnionContinuation();
	final protected Continuation intersectExceptContinuation = new IntersectExceptContinuation();
	final protected Continuation instanceOfContinuation = new InstanceofContinuation();
	final protected Continuation treatContinuation = new TreatContinuation();
	final protected Continuation castableContinuation = new CastableContinuation();
	final protected Continuation castContinuation = new CastContinuation();
	final protected Continuation relativePathContinuation = new RelativePathContinuation();

	// Constructor

	protected ModelBuilder() {
		nodeFactory = new ASTNodeFactory(); // TODO: extension point
	}

	// (Re)Parsing...

	/**
	 * Reparse the XQuery
	 * 
	 * @param node
	 *            the top-level AST node. Might be null for new document
	 * @param region
	 *            first structured document region (after update)
	 * @param offset
	 *            of the change
	 * @param length
	 *            of the change
	 */
	public ASTModule reparseQuery(ASTModule module, IStructuredDocumentRegion region, int offset, int length) {
		messages = new LinkedList<IMessage>();
		
		currentSDRegion = region;
		currentRegionIdx = 0;
		this.offset = offset;
		this.length = length;
		if (module == null) {
			module = nodeFactory.newModule();

			// Make sure that the change covers the entire document
			this.offset = 0;
			this.length = Integer.MAX_VALUE;
		}

		reparseVersionDecl(module);
		reparseLibraryOrModule(module);
		return module;
	}

	/** Reparse <tt>VersionDecl?</tt> */
	protected void reparseVersionDecl(ASTModule module) {
		if (sameRegionType(XQueryRegions.KW_XQUERY)) {
			final VersionDeclStructuredDocumentRegion vdregion = (VersionDeclStructuredDocumentRegion) currentSDRegion;
			module.setVersionRegion(vdregion.getVersion());
			module.setEncodingRegion(vdregion.getEncoding());

			nextSDRegion();
		}
	}

	/**
	 * Reparse <tt>(LibraryModule | MainModule)</tt>
	 */
	protected void reparseLibraryOrModule(ASTModule module) {
		if (sameRegionType(XQueryRegions.KW_MODULE))
			reparseLibrary(module);

		reparseMainModule(module);
	}

	/**
	 * Reparse <tt>Prolog QueryBody</tt>
	 */
	protected void reparseMainModule(ASTModule module) {
		reparseProlog(module);
		reparseQueryBody(module);
	}

	/**
	 * Reparse <tt>ModuleDecl Prolog</tt>
	 */
	protected void reparseLibrary(ASTModule module) {
		reparseModuleDecl(module);
		reparseProlog(module);
	}

	/**
	 * Reparse <tt>"module" "namespace" NCName "=" URILiteral Separator</tt>
	 */
	protected void reparseModuleDecl(ASTModule module) {
		if (overlap()) {
			final ModuleDeclStructuredDocumentRegion moduleDeclRegion = (ModuleDeclStructuredDocumentRegion) currentSDRegion;

			module.setModuleDeclStructuredDocumentRegion(moduleDeclRegion);
		}
		nextSDRegion();
	}

	/**
	 * Reparse prolog
	 */
	protected void reparseProlog(ASTModule node) {
		reparseProlog1(node);
		reparseProlog2(node);
	}

	/**
	 * Reparse prolog part 1:
	 * <tt>((DefaultNamespaceDecl | Setter | NamespaceDecl | Import) Separator)* </tt>
	 */
	protected void reparseProlog1(ASTModule node) {
		while (currentSDRegion != null) {
			if (sameRegionType(XQueryRegions.KW_DECLARE)) {
				String type2 = currentSDRegion.getRegions().get(1).getType();

				if (type2 == XQueryRegions.KW_NAMESPACE || type2 == XQueryRegions.KW_BOUNDARY_SPACE
						|| type2 == XQueryRegions.KW_DEFAULT || type2 == XQueryRegions.KW_BASEURI
						|| type2 == XQueryRegions.KW_CONSTRUCTION || type2 == XQueryRegions.KW_ORDERING
						|| type2 == XQueryRegions.KW_COPYNAMESPACES)
					nextSDRegion();
				else {
					// Certainly a prolog2 declaration...
					break;
				}

			} else if (sameRegionType(XQueryRegions.KW_IMPORT)) {
				nextSDRegion();
			} else {
				// Certainly a prolog2 declaration...
				break;
			}

			nextSDRegion(); // ";"
		}
	}

	/**
	 * Reparse
	 * <tt>"declare" "default" ("element" | "function") "namespace" URILiteral</tt>
	 */
	protected void reparseDefaultNSDecl(ASTModule node) {
		nextSDRegion();
	}

	/**
	 * Reparse prolog part 2:
	 * <tt>((VarDecl | FunctionDecl | OptionDecl) Separator)*</tt>
	 */
	protected void reparseProlog2(ASTModule node) {
		while (sameRegionType(XQueryRegions.KW_DECLARE)) {

			String type2 = currentSDRegion.getRegions().get(1).getType();

			if (type2 == XQueryRegions.KW_VARIABLE) {
				reparseVarDecl(node);
			} else if (type2 == XQueryRegions.KW_FUNCTION) {
				reparseFunctionDecl(node);
			} else if (type2 == XQueryRegions.KW_OPTION) {
				reparseOptionDecl(node);
			} else {
				// Probably a Prolog1 declaration: for now just break the
				// loop
				break;
			}

			nextSDRegion(); // ';'
		}
	}

	/**
	 * Reparse <tt>"declare" "option" QName StringLiteral</tt>
	 */
	protected void reparseOptionDecl(ASTModule node) {
		nextSDRegion();
	}

	/**
	 * Reparse
	 * <tt>"declare" "function" QName "(" ParamList? ")" ("as" SequenceType)? (EnclosedExpr | "external")</tt>
	 */
	protected void reparseFunctionDecl(ASTModule node) {
		nextSDRegion(); // "declare" "function"
		if (sameRegionType(XQueryRegions.FUNCTIONNAME)) {
			String functionName = currentSDRegion.getText(currentSDRegion.getFirstRegion());

			ASTFunctionDecl decl = node.getFunctionDecl(functionName);
			if (decl == null) {
				decl = nodeFactory.newFunctionDecl();
				node.addFunctionDecl(functionName, decl);
			}

			nextSDRegion(); // QName (

			reparseParamListOpt(decl);

			nextSDRegion(); // ")"

			reparseTypeDeclarationOpt(null);

			if (sameRegionType(XQueryRegions.KW_EXTERNAL)) {
				nextSDRegion(); // 'external'
			} else {
				IASTNode newBody = reparseEnclosedExpr(decl.getBody());
				decl.setBody(newBody);
			}

		} else {
			// Function name not there yet..
		}
	}

	/**
	 * Reparse <tt>"{" Expr "}"</tt>
	 */
	protected IASTNode reparseEnclosedExpr(IASTNode node) {
		nextSDRegion(); // "{"
		IASTNode enclosed = reparseExpr(node);
		nextSDRegion(); // "}"
		return enclosed;
	}

	/**
	 * Reparse <tt>(Param ("," Param)*)?</tt>
	 */
	protected void reparseParamListOpt(ASTFunctionDecl decl) {
		if (sameRegionType(XQueryRegions.RPAR))
			return;

		int index = 0;
		while (currentSDRegion != null) {
			reparseParam(decl, index++);

			if (!sameRegionType(XQueryRegions.COMMA)) {
				decl.removeParamNamesAfter(index - 1);
				break;
			}
			nextSDRegion(); // ","
		}
	}

	/**
	 * Reparse <tt>"$" QName TypeDeclaration?</tt>
	 */
	protected void reparseParam(ASTFunctionDecl decl, int index) {
		decl.setParamName(index, currentSDRegion);
		nextSDRegion(); // "$" QName

		reparseTypeDeclarationOpt(null);
	}

	/**
	 * Reparse
	 * <tt>"declare" "variable" "$" QName TypeDeclaration? ((":=" ExprSingle) | "external")</tt>
	 */
	protected void reparseVarDecl(ASTModule module) {
		nextSDRegion(); // skip "declare" "variable"

		String name = currentSDRegion.getFullText(currentSDRegion.getLastRegion());

		ASTVarDecl decl = module.getVariableDecl(name);
		if (decl == null) {
			decl = nodeFactory.newVariableDecl();
			module.addVariableDecl(name, decl);
		}
		decl.setName(currentSDRegion);
		nextSDRegion(); // skip "$" QName

		reparseTypeDeclarationOpt(null);
		if (sameRegionType(XQueryRegions.KW_EXTERNAL)) {
			nextSDRegion(); // 'external'
			decl.setExpr(null);
		} else {
			nextSDRegion(); // ':='

			decl.setExpr(reparseExprSingle(decl.getExpr()));
		}
	}

	/**
	 * Reparse <tt>QueryBody	   ::=   	Expr</tt>
	 */
	protected void reparseQueryBody(ASTModule module) {
		IASTNode queryBody = reparseExpr(module.getQueryBody());
		module.setQueryBody(queryBody);
	}

	/**
	 * Reparse <tt>ExprSingle ("," ExprSingle)*</tt>
	 */
	protected IASTNode reparseExpr(IASTNode expr) {
		return reparseOperatorStar(expr, sequenceFilter, exprContinuation);
	}

	/**
	 * Reparse <tt>ExprSingle</tt>
	 */
	protected IASTNode reparseExprSingle(IASTNode expr) {
		if (sameRegionType(flworFilter))
			return reparseFLWORExpr(expr);
		else if (sameRegionType(quantifiedFilter))
			return reparseQuantifiedExpr(expr);
		else if (sameRegionType(XQueryRegions.KW_TYPESWITCH))
			return reparseTypeSwitch(expr);
		else if (sameRegionType(XQueryRegions.KW_IF))
			return reparseIfExpr(expr);
		return reparseOrExpr(expr);
	}

	/**
	 * Reparse <tt>"if" "(" Expr ")" "then" ExprSingle "else" ExprSingle</tt>
	 */
	protected IASTNode reparseIfExpr(IASTNode expr) {
		ASTIf ifexpr = asIf(expr);

		nextSDRegion(); // "if"
		nextSDRegion(); // (

		IASTNode newCondition = reparseExpr(ifexpr.getConditionExpr());
		ifexpr.setConditionExpr(newCondition);

		nextSDRegion(); // )
		nextSDRegion(); // then

		IASTNode newThen = reparseExprSingle(ifexpr.getThenExpr());
		ifexpr.setThenExpr(newThen);

		nextSDRegion(); // else

		IASTNode newElse = reparseExprSingle(ifexpr.getElseExpr());
		ifexpr.setElseExpr(newElse);

		return ifexpr;
	}

	/**
	 * Reparse
	 * <tt>"typeswitch" "(" Expr ")" CaseClause+ "default" ("$" VarName)? "return" ExprSingle</tt>
	 */
	protected IASTNode reparseTypeSwitch(IASTNode expr) {
		ASTTypeswitch typeswitch = asTypeswitch(expr);

		nextSDRegion(); // typeswitch
		nextSDRegion(); // (

		IASTNode oldOperand = typeswitch.getOperandExpr();
		typeswitch.setOperandExpr(reparseExpr(oldOperand));

		nextSDRegion(); // )

		reparseCaseClauses(typeswitch);

		nextSDRegion(); // default

		if (sameRegionType(XQueryRegions.DOLLAR)) {
			typeswitch.setDefaultCaseVarname((XQueryStructuredDocumentRegion) currentSDRegion);
			nextSDRegion(); // "$" Varname
		}

		nextSDRegion(); // return

		IASTNode oldDefaultReturn = typeswitch.getDefaultCaseExpr();
		typeswitch.setDefaultCaseExpr(reparseExprSingle(oldDefaultReturn));

		return typeswitch;
	}

	/**
	 * Reparse
	 * <tt>("case" ("$" VarName "as")? SequenceType "return" ExprSingle)+</tt>
	 */
	protected void reparseCaseClauses(ASTTypeswitch typeswitch) {
		int index = 0;
		do {
			nextSDRegion(); // case

			if (sameRegionType(XQueryRegions.DOLLAR)) {
				typeswitch.setCaseVarname(index, (XQueryStructuredDocumentRegion) currentSDRegion);
				nextSDRegion(); // "$" Varname
				nextSDRegion(); // as
			}

			reparseSequenceType(null);

			nextSDRegion(); // return

			IASTNode oldExpr = typeswitch.getCaseExpr(index);
			typeswitch.setCaseExpr(index, reparseExprSingle(oldExpr));

			index++;

		} while (currentSDRegion != null);

		if (index == 0) {
			// TODO: Bad..
		}
	}

	/**
	 * Reparse
	 * 
	 * <tt>("some" | "every") "$" VarName TypeDeclaration? "in" ExprSingle ("," "$" VarName TypeDeclaration? "in" ExprSingle)* "satisfies" ExprSingle</tt>
	 */
	protected IASTNode reparseQuantifiedExpr(IASTNode expr) {
		ASTQuantified quantified = asQuantified(expr);

		nextSDRegion(); // some or every

		int index = 0;
		do {
			quantified.setBindingVariable(index, currentSDRegion);
			
			nextSDRegion(); // $ VarName
			
			reparseTypeDeclarationOpt(null);
			
			nextSDRegion(); // in
			
			IASTNode oldExpr = quantified.getBindingExpr(index);
			quantified.setBindingExpr(index, reparseExprSingle(oldExpr));
			
			if (sameRegionType(XQueryRegions.KW_SATIFIES))
				break;
			
			nextSDRegion(); // ","
			
			index ++;
		} while (currentSDRegion != null);

		nextSDRegion(); // satifies
		
		IASTNode oldExpr = quantified.getSatisfiesExpr();
		quantified.setSatisfiesExpr(reparseExprSingle(oldExpr));
		
		return quantified;
	}

	/**
	 * Reparse
	 * <tt>(ForClause | LetClause)+ WhereClause? OrderByClause? "return" ExprSingle</tt>
	 */
	protected IASTNode reparseFLWORExpr(IASTNode expr) {
		ASTFLWOR flwor = asFLWOR(expr);

		//final String clauseType = currentSDRegion.getType();
		nextSDRegion(); // for/let keyword

		reparseFLWORClause(flwor);

		// WhereClause
		if (sameRegionType(XQueryRegions.KW_WHERE)) {
			nextSDRegion(); // 'where'

			IASTNode oldWhere = flwor.getWhereExpr();
			flwor.setWhereExpr(reparseExprSingle(oldWhere));
		}

		if (sameRegionType(XQueryRegions.KW_ORDER) || sameRegionType(XQueryRegions.KW_STABLE)) {
			nextSDRegion(); // 'Order by' or 'stable order by'

			reparseOrderSpecList(flwor);
		}

		// Return

		if (sameRegionType(XQueryRegions.KW_RETURN)) {
			nextSDRegion(); // 'return'

			IASTNode returnExpr = reparseExprSingle(null);
			flwor.setReturnExpr(returnExpr);
		}

		return flwor;
	}

	/**
	 * Reparse <tt>OrderSpec ("," OrderSpec)*</tt>
	 */
	protected void reparseOrderSpecList(ASTFLWOR flwor) {
		int index = 0;
		while (currentSDRegion != null) {
			reparseOrderSpec(flwor, index);

			if (!sameRegionType(XQueryRegions.COMMA))
				break;

			nextSDRegion(); // ","
		}
	}

	/**
	 * Reparse <tt>ExprSingle OrderModifier</tt>
	 */
	protected void reparseOrderSpec(ASTFLWOR flwor, int index) {
		IASTNode oldOrderExpr = flwor.getOrderSpecExpr(index);
		flwor.setOrderSpecExpr(index, reparseExprSingle(oldOrderExpr));
		reparseOrderModifier(flwor, index);
	}

	/**
	 * Reparse
	 * <tt>("ascending" | "descending")? ("empty" ("greatest" | "least"))? ("collation" URILiteral)?</tt>
	 * @param index 
	 */
	protected void reparseOrderModifier(ASTFLWOR flwor, int index) {
		if (sameRegionType(XQueryRegions.KW_ASCENDING) || sameRegionType(XQueryRegions.KW_DESCENDING))
			nextSDRegion();

		if (sameRegionType(XQueryRegions.KW_EMPTY))
			nextSDRegion(); // 'empty' ("greatest" | "least")

		if (sameRegionType(XQueryRegions.KW_COLLATION)) {
			nextSDRegion();
			if (sameRegionType(XQueryRegions.URILITERAL))
				nextSDRegion();
		}
	}

	/**
	 * Reparse
	 * <tt>"for" "$" VarName TypeDeclaration? PositionalVar? "in" ExprSingle ("," "$" VarName TypeDeclaration? PositionalVar? "in" ExprSingle)* </tt>
	 * <tt>"let" "$" VarName TypeDeclaration? ":=" ExprSingle ("," "$" VarName TypeDeclaration? ":=" ExprSingle)*</tt>
	 */
	protected void reparseFLWORClause(ASTFLWOR flwor) {
		boolean keepGoing;
		int index = 0;
		do {
			flwor.setBindingVariable(index, currentSDRegion);

			nextSDRegion(); // Skip $var. Always there (ensured by the
			// tokenizer)

			IASTNode oldTypeDecl = flwor.getTypeDeclaration(index);
			IASTNode newTypeDecl = reparseTypeDeclarationOpt(oldTypeDecl);
			flwor.setTypeDeclaration(index, newTypeDecl);

			reparsePositionalVarOpt(flwor, index);

			nextSDRegion(); // Either := or in

			IASTNode oldExpr = flwor.getBindingExpr(index);
			IASTNode newExpr = reparseExprSingle(oldExpr);
			flwor.setBindingExpr(index, newExpr);

			keepGoing = sameRegionType(XQueryRegions.COMMA);
			if (keepGoing) {
				nextSDRegion();
				index++;
			}

		} while (keepGoing);

	}

	/**
	 * Reparse PositionalVar? <tt>"at" "$" VarName</tt>
	 * 
	 * @param index
	 */
	protected void reparsePositionalVarOpt(ASTFLWOR node, int index) {
		if (sameRegionType(XQueryRegions.KW_AT)) {
			nextSDRegion();

			if (sameRegionType(XQueryRegions.DOLLAR)) {
				node.setPositionalVar(index, currentSDRegion);
				nextSDRegion();
			}
		}

	}

	/**
	 * Reparse TypeDeclaration?
	 */
	protected IASTNode reparseTypeDeclarationOpt(IASTNode node) {
		if (sameRegionType(XQueryRegions.KW_AS)) {
			nextSDRegion(); // skip 'as'
			return reparseSequenceType(node);
		}

		return null;
	}

	/**
	 * Reparse <tt>SequenceType</tt>
	 */
	protected IASTNode reparseSequenceType(IASTNode node) {
		ASTSequenceType typeNode = asSequenceType(node);

		if (sameRegionType(XQueryRegions.ST_EMPTY)) {
			nextSDRegion(); // empty-sequence ( )

		} else {
			// (ItemType OccurrenceIndicator?)

			// The tokenizer generates only one region for both constructions.
			// For now, just skip region
			nextSDRegion();
			// reparseItemType(typeNode);
			// reparseOccurrenceIndicator(typeNode);
		}
		return typeNode;
	}

	/**
	 * Reparse <tt>AndExpr ( "or" AndExpr )*</tt>
	 */
	protected IASTNode reparseOrExpr(IASTNode expr) {
		return reparseOperatorStar(expr, orFilter, orContinuation);

	}

	/**
	 * Reparse <tt>ComparisonExpr ( "and" ComparisonExpr )*</tt>
	 */
	protected IASTNode reparseAndExpr(IASTNode expr) {
		return reparseOperatorStar(expr, andFilter, andContinuation);
	}

	/**
	 * Reparse
	 * <tt> RangeExpr ( (ValueComp | GeneralComp | NodeComp) RangeExpr )?</tt>
	 */
	protected IASTNode reparseComparisonExpr(IASTNode expr) {
		return reparseOperatorOptional(expr, comparisonFilter, comparisonContinuation);
	}

	/**
	 * Reparse <tt>AdditiveExpr ( "to" AdditiveExpr )?</tt>
	 */
	protected IASTNode reparseRangeExpr(IASTNode expr) {
		return reparseOperatorOptional(expr, rangeFilter, rangeContinuation);
	}

	/**
	 * Reparse <tt>MultiplicativeExpr ( ("+" | "-") MultiplicativeExpr )*</tt>
	 */
	protected IASTNode reparseAdditiveExpr(IASTNode expr) {
		return reparseOperatorStar(expr, additiveFilter, additiveContinuation);
	}

	/**
	 * Reparse <tt>UnionExpr ( ("*" | "div" | "idiv" | "mod") UnionExpr )*</tt>
	 */
	protected IASTNode reparseMultiplicativeExpr(IASTNode expr) {
		return reparseOperatorStar(expr, multiplicativeFilter, multiplicativeContinuation);
	}

	/**
	 * Reparse
	 * <tt>IntersectExceptExpr ( ("union" | "|") IntersectExceptExpr )*</tt>
	 */
	protected IASTNode reparseUnionExpr(IASTNode expr) {
		return reparseOperatorStar(expr, unionFilter, unionContinuation);
	}

	/**
	 * Reparse
	 * <tt>InstanceofExpr ( ("intersect" | "except") InstanceofExpr )*</tt>
	 */
	protected IASTNode reparseIntersectExceptExpr(IASTNode expr) {
		return reparseOperatorStar(expr, intersectExceptFilter, intersectExceptContinuation);
	}

	/**
	 * Reparse <tt>TreatExpr ( "instance" "of" SequenceType )?</tt>
	 */
	protected IASTNode reparseInstanceOfExpr(IASTNode expr) {
		return reparseOperatorOptional(expr, instanceOfFilter, instanceOfContinuation);
	}

	/**
	 * Reparse <tt>CastableExpr ( "treat" "as" SequenceType )?</tt>
	 */
	protected IASTNode reparseTreatExpr(IASTNode expr) {
		return reparseOperatorOptional(expr, treatFilter, treatContinuation);
	}

	/**
	 * Reparse <tt>CastExpr ( "castable" "as" SingleType )?</tt>
	 */
	protected IASTNode reparseCastableExpr(IASTNode expr) {
		return reparseOperatorOptional(expr, castableFilter, castableContinuation);
	}

	/**
	 * Reparse <tt>UnaryExpr ( "cast" "as" SingleType )?</tt>
	 */
	protected IASTNode reparseCastAsExpr(IASTNode expr) {
		return reparseOperatorOptional(expr, castFilter, castContinuation);
	}

	/**
	 * Reparse <tt>("-" | "+")* ValueExpr</tt>
	 */
	protected IASTNode reparseUnaryExpr(IASTNode expr) {
		while (sameRegionType(additiveFilter))
			nextSDRegion();

		return reparseValueExpr(expr);
	}

	/**
	 * Reparse <tt>ValidateExpr | PathExpr | ExtensionExpr</tt>
	 */
	protected IASTNode reparseValueExpr(IASTNode expr) {
		if (sameRegionType(XQueryRegions.KW_VALIDATE))
			return reparseValidateExpr(expr);

		if (sameRegionType(XQueryRegions.LPRAGMA))
			return reparseExtensionExpr(expr);

		return reparsePathExpr(expr);

	}

	/**
	 * Reparse <tt>Pragma+ "{" Expr? "}"</tt>
	 */
	protected IASTNode reparseExtensionExpr(IASTNode expr) {
		while (currentSDRegion != null) {
			nextSDRegion(); // (#
			nextSDRegion(); // QName
			if (sameRegionType(XQueryRegions.PRAGMACONTENT))
				nextSDRegion();
			nextSDRegion(); // #)

			if (sameRegionType(XQueryRegions.LCURLY))
				break;
		}

		nextSDRegion(); // {
		if (sameRegionType(XQueryRegions.RCURLY)) {
			nextSDRegion(); // }
			return null;
		}

		IASTNode pragmaExpr = reparseExpr(expr);
		nextSDRegion(); // }
		return pragmaExpr;
	}

	/**
	 * Reparse <tt>("lax" | "strict")?</tt>
	 */
	protected void reparseValidationModeOpt(IASTNode expr) {
		if (sameRegionType(XQueryRegions.KW_LAX) || sameRegionType(XQueryRegions.KW_STRICT))
			nextSDRegion();
	}

	/**
	 * Reparse <tt>"validate" ValidationMode? "{" Expr "}"</tt>
	 */
	protected IASTNode reparseValidateExpr(IASTNode expr) {
		nextSDRegion(); // validate
		reparseValidationModeOpt(expr);
		return reparseEnclosedExpr(expr);
	}

	/**
	 * Reparse
	 * <tt>("/" RelativePathExpr?) | ("//" RelativePathExpr) | RelativePathExpr</tt>
	 */
	protected IASTNode reparsePathExpr(IASTNode expr) {
		if (sameRegionType(XQueryRegions.PATH_SLASH)) {
			nextSDRegion();
			return reparseRelativePathExprOpt(expr);
		}

		if (sameRegionType(XQueryRegions.PATH_SLASHSLASH))
			nextSDRegion();

		return reparseRelativePathExpr(expr);

	}

	/**
	 * Reparse <tt>RelativePathExpr?<tt>
	 */
	protected IASTNode reparseRelativePathExprOpt(IASTNode expr) {
		return reparseStepExpr(expr, true);
	}

	/**
	 * Reparse <tt>StepExpr (("/" | "//") StepExpr)</tt>
	 */
	protected IASTNode reparseRelativePathExpr(IASTNode expr) {
		return reparseOperatorStar(expr, relativePathFilter, relativePathContinuation);
	}

	/**
	 * Reparse <tt>(FilterExpr | AxisStep) PredicateList</tt>
	 * 
	 * @param optional
	 *            TODO
	 */
	protected IASTNode reparseStepExpr(IASTNode expr, boolean optional) {
		IASTNode stepExpr = null;

		if (sameRegionType(XQueryRegions.NUMERICLITERAL) // Literal
				|| sameRegionType(XQueryRegions.STRINGLITERAL))
			stepExpr = reparseLiteral(expr);
		else if (sameRegionType(XQueryRegions.DOLLAR)) // VarRef
			stepExpr = reparseVarRef(expr);
		else if (sameRegionType(XQueryRegions.LPAR)) // Parentherize
			stepExpr = reparseParentherizeExpr(expr);
		else if (sameRegionType(XQueryRegions.PATH_CONTEXTITEM)) // ContextITem
			stepExpr = reparseContextItemExpr(expr);
		else if (sameRegionType(XQueryRegions.FUNCTIONNAME)) // Function call
			stepExpr = reparseFunctionCall(expr);
		else if (sameRegionType(XQueryRegions.KW_ORDERED) || sameRegionType(XQueryRegions.KW_UNORDERED)) // Ordered/Unordered
			stepExpr = reparseOrderedUnordered(expr);
		else if (sameRegionType(XQueryRegions.XML_TAG_OPEN)) // '<'
			stepExpr = reparseDirElemConstructor(expr);
		else if (sameRegionType(XQueryRegions.XML_COMMENT))
			stepExpr = reparseDirCommentConstructor(expr);
		else if (sameRegionType(XQueryRegions.XML_PI))
			stepExpr = reparseDirPIConstructor(expr);
		else if (sameRegionType(XQueryRegions.KW_DOCUMENT))
			stepExpr = reparseCompDocConstructor(expr);
		else if (sameRegionType(XQueryRegions.KW_ELEMENT))
			stepExpr = reparseCompElementConstructor(expr);
		else if (sameRegionType(XQueryRegions.KW_ATTRIBUTE))
			stepExpr = reparseCompAttrConstructor(expr);
		else if (sameRegionType(XQueryRegions.KW_TEXT))
			stepExpr = reparseCompTextConstructor(expr);
		else if (sameRegionType(XQueryRegions.KW_COMMENT))
			stepExpr = reparseCompCommentConstructor(expr);
		else if (sameRegionType(XQueryRegions.KW_PI))
			stepExpr = reparseCompPIConstructor(expr);
		else
			stepExpr = reparseAxisStep(expr, optional);

		// PredicateList

		if (sameRegionType(XQueryRegions.LSQUARE)) {
			reparsePredicateList(expr);
		}

		return stepExpr;

	}

	/**
	 * Reparse <tt>ReverseStep | ForwardStep)</tt>
	 * 
	 * @param optional
	 */
	protected IASTNode reparseAxisStep(IASTNode expr, boolean optional) {
		if (sameRegionType(stepFilter))
			nextSDRegion(); // Axis name

		return reparseNodeTest(expr, optional);
	}

	/**
	 * Reparse <tt>KindTest | NameTest</tt>
	 * 
	 * @param optional
	 */
	protected IASTNode reparseNodeTest(IASTNode expr, boolean optional) {
		if (optional) {
			if (sameRegionType(nodeTestFilter))
				nextSDRegion();
		} else
			nextSDRegion();
		return null;
	}

	/**
	 * Reparse
	 * <tt>"processing-instruction" (NCName | ("{" Expr "}")) "{" Expr? "}"</tt>
	 */
	protected IASTNode reparseCompPIConstructor(IASTNode expr) {
		nextSDRegion(); // "processing-instruction"
		if (sameRegionType(XQueryRegions.NCNAME))
			nextSDRegion();
		else
			reparseEnclosedExpr(null);

		nextSDRegion(); // {
		if (sameRegionType(XQueryRegions.RCURLY)) {
			nextSDRegion(); // }
			return null;
		}

		reparseExpr(null);
		nextSDRegion(); // }
		return null;
	}

	/**
	 * Reparse <tt>"comment" "{" Expr "}"</tt>
	 */
	protected IASTNode reparseCompCommentConstructor(IASTNode expr) {
		nextSDRegion();

		nextSDRegion(); // {
		if (sameRegionType(XQueryRegions.RCURLY)) {
			nextSDRegion(); // }
			return null;
		}

		reparseExpr(null);
		nextSDRegion(); // }
		return null;
	}

	/**
	 * Reparse <tt>"text" "{" Expr "}"</tt>
	 */
	protected IASTNode reparseCompTextConstructor(IASTNode expr) {
		nextSDRegion();

		nextSDRegion(); // {
		if (sameRegionType(XQueryRegions.RCURLY)) {
			nextSDRegion(); // }
			return null;
		}

		reparseExpr(null);
		nextSDRegion(); // }
		return null;
	}

	/**
	 * Reparse <tt>"attribute" (QName | ("{" Expr "}")) "{" Expr? "}"</tt>
	 */
	protected IASTNode reparseCompAttrConstructor(IASTNode expr) {
		nextSDRegion();

		if (sameRegionType(XQueryRegions.QNAME))
			nextSDRegion();
		else
			reparseEnclosedExpr(null);

		nextSDRegion(); // {
		if (sameRegionType(XQueryRegions.RCURLY)) {
			nextSDRegion(); // }
			return null;
		}

		reparseExpr(null);
		nextSDRegion(); // }
		return null;
	}

	/**
	 * Reparse <tt>element" (QName | ("{" Expr "}")) "{" ContentExpr? "}"</tt>
	 */
	protected IASTNode reparseCompElementConstructor(IASTNode expr) {
		nextSDRegion();

		if (sameRegionType(XQueryRegions.QNAME))
			nextSDRegion();
		else
			reparseEnclosedExpr(null);

		nextSDRegion(); // {
		if (sameRegionType(XQueryRegions.RCURLY)) {
			nextSDRegion(); // }
			return null;
		}

		reparseExpr(null);
		nextSDRegion(); // }
		return null;
	}

	/**
	 * Reparse <tt>"document" "{" Expr "}"</tt>
	 */
	protected IASTNode reparseCompDocConstructor(IASTNode expr) {
		nextSDRegion();
		reparseEnclosedExpr(null);
		return null;
	}

	/**
	 * Reparse <tt>"<?" PITarget (S DirPIContents)? "?>"</tt>
	 */
	protected IASTNode reparseDirPIConstructor(IASTNode expr) {
		nextSDRegion(); // only one region for the PI
		return null;
	}

	/**
	 * Reparse <tt>"<!--" DirCommentContents "-->"</tt>
	 */
	protected IASTNode reparseDirCommentConstructor(IASTNode expr) {
		nextSDRegion(); // only one region for the comment
		return null;
	}

	/**
	 * Reparse
	 * <tt>"<" QName DirAttributeList ("/>" | (">" DirElemContent* "</" QName S? ">"))</tt>
	 */
	protected IASTNode reparseDirElemConstructor(IASTNode expr) {
		ASTDirElement element = asDirElement(expr);

		String tagName = currentSDRegion.getText(currentSDRegion.getLastRegion());
		element.setTagName(tagName);

		nextSDRegion(); // "<" QName
		reparseDirAttributeList(element);

		if (sameRegionType(XQueryRegions.XML_TAG_CLOSE)) {
			nextSDRegion(); // ">"

			reparseDirElemContentStar(element);

			nextSDRegion(); // "</"
			nextSDRegion(); // QName TODO: check same name
			nextSDRegion(); // >
		} else {
			// must be />
			nextSDRegion();
		}

		return element;
	}

	/**
	 * Reparse
	 * <tt>(DirectConstructor | CDataSection | CommonContent | ElementContentChar )*</tt>
	 */
	protected void reparseDirElemContentStar(ASTDirElement element) {
		int index = 0;
		while (currentSDRegion != null) {
			if (sameRegionType(XQueryRegions.XML_END_TAG_OPEN)) {
				element.removeChildASTNodesAfter(index);
				break;
			}

			if (sameRegionType(XQueryRegions.XML_CDATA))
				reparseCDataSection();
			else if (sameRegionType(directConstructorFilter) || sameRegionType(commonContentFilter)) {
				IASTNode oldChild = element.getChildASTNodeAt(index);
				IASTNode newChild = sameRegionType(directConstructorFilter) ? reparseDirectConstructor(oldChild)
						: reparseCommonContent(oldChild);

				if (newChild != null) {
					element.setChildASTNodeAt(index, newChild);
					index++;
				}
			} else
				reparseElementContentChar();

		}

	}

	/**
	 * Reparse <tt>Char - [{}<&]</tt>
	 */
	protected void reparseElementContentChar() {
		nextSDRegion(); // Checked by the tokenizer
	}

	/**
	 * Reparse
	 * <tt>PredefinedEntityRef | CharRef | "{{" | "}}" | EnclosedExpr</tt>
	 */
	protected IASTNode reparseCommonContent(IASTNode node) {
		if (sameRegionType(XQueryRegions.XML_START_EXPR)) {
			return reparseEnclosedExpr(node);
		}

		nextSDRegion();
		return null;
	}

	/**
	 * Reparse
	 * <tt>DirElemConstructor | DirCommentConstructor | DirPIConstructor</tt>
	 */
	protected IASTNode reparseDirectConstructor(IASTNode expr) {
		if (sameRegionType(XQueryRegions.XML_TAG_OPEN))
			return reparseDirElemConstructor(expr);

		if (sameRegionType(XQueryRegions.XML_COMMENT))
			return reparseDirCommentConstructor(expr);

		if (sameRegionType(XQueryRegions.XML_PI))
			return reparseDirPIConstructor(expr);

		return null;
	}

	/**
	 * Reparse <tt>"<![CDATA[" CDataSectionContents "]]>"</tt>
	 */
	protected void reparseCDataSection() {
		nextSDRegion();
	}

	/**
	 * Reparse <tt>(S (QName S? "=" S? DirAttributeValue)?)*</tt>
	 */
	protected void reparseDirAttributeList(ASTDirElement element) {
		while (currentSDRegion != null) {
			if (!sameRegionType(XQueryRegions.XML_TAG_ATTRIBUTE_NAME))
				break;

			String attrName = currentSDRegion.getText().trim();
			ASTDirAttribute attr = (ASTDirAttribute) element.getAttributeNode(attrName);
			if (attr == null) {
				attr = nodeFactory.newDirAttribute();
				attr.setName(attrName);
				element.setAttributeNode(attr);
			}
			nextSDRegion(); // QName
			nextSDRegion(); // Equals

			reparseDirAttributeValue(attr);
		}

	}

	/**
	 * Reparse
	 * <tt>('"' (EscapeQuot | QuotAttrValueContent)* '"') | ("'" (EscapeApos | AposAttrValueContent)* "'")</tt>
	 */
	protected void reparseDirAttributeValue(ASTDirAttribute attr) {
		final String escapeType = sameRegionType(XQueryRegions.XML_ATTR_QUOT) ? XQueryRegions.XML_ESCAPE_QUOT
				: XQueryRegions.XML_ESCAPE_APOS;

		nextSDRegion(); // '"' or "'"
		reparseDirAttributeValue(attr, escapeType);

		nextSDRegion(); // ' or "
	}

	/**
	 * Reparse <tt>{Apos/Quot}AttrContentChar | CommonContent</tt>
	 */
	protected IASTNode reparseAttrValueContent(IASTNode node) {
		if (sameRegionType(commonContentFilter))
			return reparseCommonContent(node);

		nextSDRegion();
		return null;
	}

	/**
	 * Reparse <tt>(EscapeQuot | QuotAttrValueContent)*</tt> or
	 * <tt>(EscapeApos | AposAttrValueContent)*</tt>
	 */
	protected void reparseDirAttributeValue(ASTDirAttribute attr, String escapeType) {
		int index = 0;
		while (currentSDRegion != null) {
			if (sameRegionType(XQueryRegions.XML_END_ATTR_VALUE)) {
				attr.removeChildASTNodesAfter(index);
				break;
			}

			if (sameRegionType(escapeType))
				nextSDRegion();
			else {
				IASTNode oldAttr = attr.getChildASTNodeAt(index);
				IASTNode newAttr = reparseAttrValueContent(oldAttr);

				if (newAttr != null) {
					attr.setChildASTNodeAt(index, newAttr);
					index++;
				}
			}
		}
	}

	/**
	 * Reparse <tt>"ordered" "{" Expr "}"</tt> and
	 * <tt>"unordered" "{" Expr "}"</tt>
	 */
	protected IASTNode reparseOrderedUnordered(IASTNode expr) {
		nextSDRegion(); // "ordered" "{" or "unordered" "{"

		IASTNode innerExpr = reparseExpr(expr);

		checkAndReport(XQueryRegions.RCURLY);
		nextSDRegion();
		return innerExpr;
	}

	/**
	 * Reparse <tt>QName "(" (ExprSingle ("," ExprSingle)*)? ")"</tt>
	 */
	protected IASTNode reparseFunctionCall(IASTNode expr) {
		nextSDRegion(); // QName "("

		ASTFunctionCall fc = asFunctionCall(expr);
		int index = 0;

		// No parameters?
		if (sameRegionType(XQueryRegions.RPAR)) {
			nextSDRegion(); // ')'
		} else {
			do {
				IASTNode value = reparseExprSingle(null); // todo reuse param
				fc.setChildASTNodeAt(index++, value);

				if (sameRegionType(XQueryRegions.RPAR)) {
					nextSDRegion();
					break;
				}

				// Must be a comma
				nextSDRegion(); // ','

			} while (currentSDRegion != null);
		}
		fc.removeChildASTNodesAfter(index);

		return fc;
	}

	/**
	 * Reparse <tt>"."</tt>
	 */
	protected IASTNode reparseContextItemExpr(IASTNode expr) {
		nextSDRegion();
		return null;
	}

	/**
	 * Reparse <tt>"(" Expr? ")"</tt>
	 */
	protected IASTNode reparseParentherizeExpr(IASTNode expr) {
		nextSDRegion(); // "("

		if (sameRegionType(XQueryRegions.RPAR))
			return null;

		IASTNode innerExpr = reparseExpr(expr);
		checkAndReport(XQueryRegions.RPAR);
		nextSDRegion(); // ")"
		return innerExpr;
	}

	/**
	 * reparse <tt>NumericLiteral | StringLiteral</tt>
	 */
	protected IASTNode reparseLiteral(IASTNode expr) {
		ASTLiteral literal;
		if (expr == null || expr.getType() != IASTNode.LITERAL)
			literal = nodeFactory.newLiteral();
		else
			literal = (ASTLiteral) expr;

		literal.setStructuredDocumentRegion(currentSDRegion);
		nextSDRegion();
		return literal;
	}

	/**
	 * reparse <tt>"$" VarName</tt>
	 */
	protected IASTNode reparseVarRef(IASTNode expr) {
		ASTVarRef varRef;
		if (expr == null || expr.getType() != IASTNode.VARREF)
			varRef = nodeFactory.newVarRef();
		else
			varRef = (ASTVarRef) expr;

		varRef.setStructuredDocumentRegion(currentSDRegion);
		nextSDRegion(); // $ and VarName has been grouped together
		return varRef;
	}

	/**
	 * Reparse <tt>("[" Expr "]")*</tt>
	 */
	protected IASTNode reparsePredicateList(IASTNode expr) {
		nextSDRegion(); // [

		IASTNode predicate = reparseExpr(expr);

		checkAndReport(XQueryRegions.RCURLY);
		nextSDRegion();

		return predicate;
	}

	// Reparse operator helpers

	/** Reparse operator following this grammar (Expr (op Expr)*) */
	protected IASTNode reparseOperatorStar(IASTNode expr, OperatorFilter operatorFilter, Continuation continuation) {
		IASTNode oldChild = getFirstOperand(expr, operatorFilter);
		IASTNode newChild = continuation.reparse(oldChild);

		skipWhitespace();
		if (currentSDRegion == null || !sameRegionType(operatorFilter))
			return newChild;

		int operatorType = getOperatorType();
		nextSDRegion(); // skip operator

		ASTOperator operator = getOperator(expr, operatorType);
		if (oldChild != newChild)
			operator.setChildASTNodeAt(0, newChild);

		int index = 1;
		while (true) {
			oldChild = operator.getChildASTNodeAt(index);
			newChild = continuation.reparse(oldChild);
			if (oldChild != newChild)
				operator.setChildASTNodeAt(index, newChild);

			skipWhitespace();
			if (currentSDRegion == null || !sameRegionType(operatorFilter)) {
				operator.removeChildASTNodesAfter(index);
				return operator;
			}
			nextSDRegion(); // skip operator
			index++;
		}
	}

	/** Reparse operator following this grammar (Expr (op Expr)?) */
	protected IASTNode reparseOperatorOptional(IASTNode expr, OperatorFilter operatorFilter, Continuation continuation) {
		IASTNode oldChild = getFirstOperand(expr, operatorFilter);
		IASTNode newChild = continuation.reparse(oldChild);

		skipWhitespace();
		if (currentSDRegion == null || !sameRegionType(operatorFilter))
			return newChild;

		int operatorType = getOperatorType();
		nextSDRegion(); // skip operator

		ASTOperator operator = getOperator(expr, operatorType);
		if (oldChild != newChild)
			operator.setChildASTNodeAt(0, newChild);

		oldChild = operator.getChildASTNodeAt(1);
		newChild = continuation.reparse(oldChild);
		if (oldChild != newChild)
			operator.setChildASTNodeAt(1, newChild);

		skipWhitespace();

		operator.removeChildASTNodesAfter(1);
		return operator;
	}

	// Helpers

	/** Gets AST node as {@link ASTIf} */
	protected ASTIf asIf(IASTNode node) {
		if (node != null && node.getType() == IASTNode.IF)
			return (ASTIf) node;

		return nodeFactory.newIf();
	}

	/** Gets AST node as {@link ASTQuantified} */
	protected ASTQuantified asQuantified(IASTNode node) {
		if (node != null && node.getType() == IASTNode.IF)
			return (ASTQuantified) node;

		return nodeFactory.newQuantified();
	}

	/** Gets AST node as {@link ASTTypeswitch} */
	protected ASTTypeswitch asTypeswitch(IASTNode node) {
		if (node != null && node.getType() == IASTNode.TYPESWITCH)
			return (ASTTypeswitch) node;

		return nodeFactory.newTypeswitch();
	}

	/** Gets AST node as {@link ASTSequenceType} */
	protected ASTSequenceType asSequenceType(IASTNode node) {
		if (node != null && node.getType() == IASTNode.SEQUENCETYPE)
			return (ASTSequenceType) node;

		return nodeFactory.newSequenceType();
	}

	/** Gets AST node as {@link ASTFunctionCall} */
	protected ASTFunctionCall asFunctionCall(IASTNode node) {
		if (node != null && node.getType() == IASTNode.FUNCTIONCALL)
			return (ASTFunctionCall) node;

		return nodeFactory.newFunctionCall();
	}

	/** Gets AST node as {@link ASTFLWOR} */
	protected ASTFLWOR asFLWOR(IASTNode expr) {
		if (expr != null && expr.getType() == IASTNode.FLWOR)
			return (ASTFLWOR) expr;

		return nodeFactory.newFLOWR();
	}

	/** Gets AST node as {@link ASTDirElement} */
	protected ASTDirElement asDirElement(IASTNode node) {
		if (node != null && node.getType() == IASTNode.DIRELEMENT)
			return (ASTDirElement) node;

		return nodeFactory.newDirElement();
	}

	/**
	 * Test structured document region type against given type. Ignore
	 * separators (white spaces and comments)
	 */
	protected boolean sameRegionType(String type) {
		skipWhitespace();
		return currentSDRegion != null && currentSDRegion.getType() == type;
	}

	/**
	 * Test structured document region type against given type filter. Ignore
	 * separators (white spaces and comments)
	 */
	protected boolean sameRegionType(OperatorFilter filter) {
		skipWhitespace();
		return currentSDRegion != null && filter.accept(currentSDRegion.getType());
	}

	/**
	 * Test structured document region type against given region type filter.
	 * Ignore separators (white spaces and comments)
	 */
	protected boolean sameRegionType(RegionFilter filter) {
		skipWhitespace();
		return currentSDRegion != null && filter.accept(currentSDRegion.getType());
	}

	/** Skip white spaces */
	protected void skipWhitespace() {
		while (currentSDRegion != null && isIgnorableWhitespace(currentSDRegion.getType())) {
			currentSDRegion = currentSDRegion.getNext();
			currentRegionIdx = 0;
		}
	}

	/**
	 * Test whether the given region type is a ignorable white space (whitespace
	 * and comments)
	 */
	protected boolean isIgnorableWhitespace(String type) {
		return type == XQueryRegions.WHITE_SPACE || type == XQueryRegions.XQUERY_COMMENT;
	}

	/**
	 * Test if the current structured document region overlaps the changed text
	 * region
	 */
	final protected boolean overlap() {
		return currentSDRegion != null
				&& !(currentSDRegion.getStart() > offset + length || currentSDRegion.getEnd() < offset);
	}

	/** Move to the next structured document region. Ignore whitespaces */
	protected void nextSDRegion() {
		if (currentSDRegion != null) {
			currentSDRegion = currentSDRegion.getNext();
			currentRegionIdx = 0;

			skipWhitespace();
		}
	}

	/** Move to the structured document region of the given type */
	protected void moveToSDRegion(String type) {
		while (currentSDRegion != null && currentSDRegion.getType() != type)
			nextSDRegion();
	}

	/**
	 * Check the current region (ignoring white spaces) is of a given type. If
	 * not report problem
	 */
	protected void checkAndReport(String type) {
		skipWhitespace();
		if (!sameRegionType(type)) {
			// TODO: report error
		}
	}

	/**
	 * Gets AST operator
	 * 
	 * @param expr
	 * @return
	 */
	protected ASTOperator getOperator(IASTNode expr) {
		if (expr != null && expr.getType() == IASTNode.OPERATOR)
			return (ASTOperator) expr;

		return new ASTOperator();

	}

	/**
	 * Gets AST operator of the given type
	 * 
	 * @param expr
	 * @return
	 */
	protected ASTOperator getOperator(IASTNode expr, int operatorType) {
		ASTOperator operator = getOperator(expr);
		if (operator.getOperatorType() == operatorType)
			return operator;
		return new ASTOperator(operatorType);
	}

	/**
	 * Gets first operator operand, only if the given expression matches the
	 * operator type
	 * 
	 * @param expr
	 * @param sequence
	 * @return
	 */
	protected IASTNode getFirstOperand(IASTNode expr, OperatorFilter filter) {
		if (expr != null && expr.getType() == IASTNode.OPERATOR) {
			final ASTOperator operator = (ASTOperator) expr;
			if (filter.accept(operator.getType()) && operator.getChildASTNodesCount() >= 1)
				return operator.getChildASTNodeAt(0);
		}
		return null;
	}

	/**
	 * Gets first operator operand
	 * 
	 * @param expr
	 * @param sequence
	 * @return
	 */
	protected IASTNode getFirstOperand(IASTNode expr) {
		if (expr.getType() == IASTNode.OPERATOR) {
			final ASTOperator operator = (ASTOperator) expr;
			if (operator.getChildASTNodesCount() >= 1)
				return operator.getChildASTNodeAt(0);
		}
		return null;
	}

	/**
	 * Get the operator type corresponding to the current region type.
	 * 
	 * @return
	 */
	protected int getOperatorType() {
		final String regionType = currentSDRegion.getType();

		// TODO: optimize (or wait for java 7)
		if (regionType == XQueryRegions.OP_OR)
			return ASTOperator.OP_OR;
		else if (regionType == XQueryRegions.OP_AND)
			return ASTOperator.OP_AND;
		else if (regionType == XQueryRegions.COMMA)
			return ASTOperator.OP_COMMA;
		else if (regionType == XQueryRegions.OP_TO)
			return ASTOperator.OP_TO;
		else
			return -1;
	}

	// Inner classes

	/** General purpose region filter */
	protected class RegionFilter {
		// State

		/** Region types */
		final private String[] regionType;

		// Constructors

		public RegionFilter(String[] regionType) {
			super();
			this.regionType = regionType;
		}

		// Methods

		public boolean accept(String type) {
			for (int i = 0; i < regionType.length; i++)
				if (type == regionType[i])
					return true;
			return false;
		}
	}

	/** Filter used when reparsing operator */
	protected class OperatorFilter {
		// State

		/** Operator type */
		final private int[] operatorType;
		/** Region type */
		final private String[] regionType;

		// Constructors

		public OperatorFilter(int[] operatorType, String[] regionType) {
			super();
			this.operatorType = operatorType;
			this.regionType = regionType;
		}

		// Methods

		public boolean accept(int type) {
			for (int i = 0; i < operatorType.length; i++)
				if (type == operatorType[i])
					return true;
			return false;
		}

		public boolean accept(String type) {
			for (int i = 0; i < regionType.length; i++)
				if (type == regionType[i])
					return true;
			return false;
		}

	}

	/** Reparse continuation */
	protected abstract class Continuation {
		abstract IASTNode reparse(IASTNode expr);
	}

	/** Reparse continuation for Expr */
	protected class ExprContinuation extends Continuation {
		IASTNode reparse(IASTNode expr) {
			return reparseExprSingle(expr);
		}
	}

	/** Reparse continuation for OrExpr */
	protected class OrContinuation extends Continuation {
		IASTNode reparse(IASTNode expr) {
			return reparseAndExpr(expr);
		}
	}

	/** Reparse continuation for AndExpr */
	protected class AndContinuation extends Continuation {
		IASTNode reparse(IASTNode expr) {
			return reparseComparisonExpr(expr);
		}
	}

	/** Reparse continuation for ComparisonExpr */
	protected class ComparisonContinuation extends Continuation {
		IASTNode reparse(IASTNode expr) {
			return reparseRangeExpr(expr);
		}
	}

	/** Reparse continuation for RangeExpr */
	protected class RangeContinuation extends Continuation {
		IASTNode reparse(IASTNode expr) {
			return reparseAdditiveExpr(expr);
		}
	}

	/** Reparse continuation for AdditiveExpr */
	protected class AdditiveContinuation extends Continuation {
		IASTNode reparse(IASTNode expr) {
			return reparseMultiplicativeExpr(expr);
		}
	}

	/** Reparse continuation for MultiplicativeExpr */
	protected class MultiplicativeContinuation extends Continuation {
		IASTNode reparse(IASTNode expr) {
			return reparseUnionExpr(expr);
		}
	}

	/** Reparse continuation for UnionExpr */
	protected class UnionContinuation extends Continuation {
		IASTNode reparse(IASTNode expr) {
			return reparseIntersectExceptExpr(expr);
		}
	}

	/** Reparse continuation for IntersectExceptExpr */
	protected class IntersectExceptContinuation extends Continuation {
		IASTNode reparse(IASTNode expr) {
			return reparseInstanceOfExpr(expr);
		}
	}

	/** Reparse continuation for InstanceofExpr */
	protected class InstanceofContinuation extends Continuation {
		IASTNode reparse(IASTNode expr) {
			return reparseTreatExpr(expr);
		}
	}

	/** Reparse continuation for TreatExpr */
	protected class TreatContinuation extends Continuation {
		IASTNode reparse(IASTNode expr) {
			return reparseCastableExpr(expr);
		}
	}

	/** Reparse continuation for CastableExpr */
	protected class CastableContinuation extends Continuation {
		IASTNode reparse(IASTNode expr) {
			return reparseCastAsExpr(expr);
		}
	}

	/** Reparse continuation for CastExpr */
	protected class CastContinuation extends Continuation {
		IASTNode reparse(IASTNode expr) {
			return reparseUnaryExpr(expr);
		}
	}

	/** Reparse continuation for RelativePathExpr */
	protected class RelativePathContinuation extends Continuation {
		IASTNode reparse(IASTNode expr) {
			return reparseStepExpr(expr, false);
		}
	}
}
