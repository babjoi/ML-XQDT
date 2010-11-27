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

import java.util.Stack;

import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocumentRegion;
import org.eclipse.wst.sse.core.internal.provisional.text.ITextRegion;
import org.eclipse.wst.sse.core.internal.provisional.text.ITextRegionList;
import org.eclipse.wst.xquery.core.IXQDTLanguageConstants;
import org.eclipse.wst.xquery.sse.core.internal.XQueryMessages;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.ASTApply;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.ASTBindingClause;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.ASTClause;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.ASTCompAttrConstructor;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.ASTCompCommentConstructor;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.ASTCompDocConstructor;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.ASTCompElemConstructor;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.ASTCompPIConstructor;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.ASTCompTextConstructor;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.ASTContextItem;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.ASTExprSingleClause;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.ASTExtension;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.ASTFLWOR;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.ASTFunctionCall;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.ASTFunctionDecl;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.ASTIf;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.ASTKindTest;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.ASTLiteral;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.ASTModule;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.ASTNameTest;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.ASTNamespaceDecl;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.ASTNodeFactory;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.ASTOperator;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.ASTOrderByClause;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.ASTParentherized;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.ASTPath;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.ASTQuantified;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.ASTSequenceType;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.ASTSingleType;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.ASTStep;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.ASTTypeswitch;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.ASTValidate;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.ASTVarDecl;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.ASTVarRef;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.IASTNode;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.update.ASTDelete;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.update.ASTInsert;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.update.ASTRename;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.update.ASTReplace;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.update.ASTTransform;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.xml.ASTDirAttribute;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.xml.ASTDirComment;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.xml.ASTDirElement;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.xml.ASTDirPI;
import org.eclipse.wst.xquery.sse.core.internal.regions.XQueryRegions;
import org.eclipse.wst.xquery.sse.core.internal.sdregions.ModuleDeclStructuredDocumentRegion;
import org.eclipse.wst.xquery.sse.core.internal.sdregions.SDRegionUtils;
import org.eclipse.wst.xquery.sse.core.internal.sdregions.VersionDeclStructuredDocumentRegion;
import org.eclipse.wst.xquery.sse.core.internal.sdregions.XQueryStructuredDocumentRegion;

/**
 * Update the XQuery AST.
 * 
 * <p>
 * For now, the new structured document regions are fully traversed, along with the existing AST (if
 * any). The goal is to minimize AST nodes changes.
 * 
 * <p>
 * In the future, it is possible to skip reparsing subtrees based on changes location.
 * 
 * <p>
 * Checks the language syntax and provides appropriate messages for downstream validators. Note that
 * it performs only minimal validation to speed up reparsing.
 * 
 * @author <a href="villard@us.ibm.com">Lionel Villard</a>
 */
public class ModelBuilder {

    // State

    /** The model */
    protected XQueryStructuredModel model;

    /** AST node factory */
    final protected ASTNodeFactory nodeFactory;

    /** Ending region for reparsing */
    protected IStructuredDocumentRegion endSDRegion; // TODO: not used yet

    /** Current structured document region */
    protected XQueryStructuredDocumentRegion currentSDRegion;

    /** Previous structured document region */
    protected XQueryStructuredDocumentRegion previousSDRegion;

    /** Starting offset of the change */
    protected int offset;

    /** Length of the change */
    protected int length;

    /** Language being parsed */
    protected int language;

    /**
     * Whether or not the current construct being parsed is not in the target language
     */
    protected Stack<Boolean> isValidLanguage;

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

    final protected OperatorFilter relativePathFilter = new OperatorFilter(new int[] { ASTOperator.PATH_SLASH,
            ASTOperator.PATH_SLASHSLASH, }, new String[] { XQueryRegions.PATH_SLASH, XQueryRegions.PATH_SLASHSLASH });

    final protected RegionFilter stepFilter = new RegionFilter(new String[] { XQueryRegions.PATH_ABBREVATTRIBUTE,
            XQueryRegions.PATH_ABBREVPARENT, XQueryRegions.PATH_ANCESTOR, XQueryRegions.PATH_ANCESTOR_OR_SELF,
            XQueryRegions.PATH_ATTRIBUTE, XQueryRegions.PATH_CHILD, XQueryRegions.PATH_DESCENDANT,
            XQueryRegions.PATH_DESCENDANT_OR_SELF, XQueryRegions.PATH_FOLLOWING, XQueryRegions.PATH_FOLLOWING_SIBLING,
            XQueryRegions.PATH_PARENT, XQueryRegions.PATH_PRECEDING, XQueryRegions.PATH_PRECEDING_SIBLING,
            XQueryRegions.PATH_SELF });

    final protected RegionFilter kindTestFilter = new RegionFilter(new String[] { XQueryRegions.KT_ANYKIND,
            XQueryRegions.KT_ATTRIBUTE, XQueryRegions.KT_COMMENT, XQueryRegions.KT_DOCUMENTNODE,
            XQueryRegions.KT_ELEMENT, XQueryRegions.KT_PI, XQueryRegions.KT_SCHEMAATTRIBUTE,
            XQueryRegions.KT_SCHEMAELEMENT, XQueryRegions.KT_TEXT });

    final protected RegionFilter sequenceTypeFilter = new RegionFilter(new String[] { XQueryRegions.ST_ATOMICTYPE,
            XQueryRegions.ST_EMPTY, XQueryRegions.ST_ITEM, XQueryRegions.KT_ANYKIND, XQueryRegions.KT_ATTRIBUTE,
            XQueryRegions.KT_COMMENT, XQueryRegions.KT_DOCUMENTNODE, XQueryRegions.KT_ELEMENT, XQueryRegions.KT_PI,
            XQueryRegions.KT_SCHEMAATTRIBUTE, XQueryRegions.KT_SCHEMAELEMENT, XQueryRegions.KT_TEXT });

    final protected RegionFilter nameTestFilter = new RegionFilter(new String[] { XQueryRegions.PATH_NAMETEST });

    final protected RegionFilter directConstructorFilter = new RegionFilter(new String[] { XQueryRegions.XML_TAG_OPEN,
            XQueryRegions.XML_COMMENT, XQueryRegions.XML_PI });

    final protected RegionFilter commonContentFilter = new RegionFilter(new String[] { XQueryRegions.XML_PE_REFERENCE,
            XQueryRegions.XML_CHAR_REF, XQueryRegions.XML_ESCAPE_START_EXPR, XQueryRegions.XML_ESCAPE_CLOSE_EXPR,
            XQueryRegions.LCURLY });

    final protected RegionFilter forLetFilter = new RegionFilter(new String[] { XQueryRegions.KW_FOR,
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
    final protected Continuation relativePathContinuation = new RelativePathContinuation();

    // Constructor

    protected ModelBuilder() {
        nodeFactory = new ASTNodeFactory(); // TODO: extension point

    }

    // Methods

    public void setModel(XQueryStructuredModel model) {
        this.model = model;
    }

    // (Re)Parsing...

    /**
     * Reparse the XQuery
     * 
     * @param region
     *            first structured document region (after update)
     * @param offset
     *            of the change
     * @param length
     *            of the change
     * @param language
     *            target language
     * @param node
     *            the top-level AST node. Might be null for new document
     * @see {@link IXQDTLanguageConstants}
     */
    public ASTModule reparseQuery(ASTModule module, IStructuredDocumentRegion region, int offset, int length,
            int language) {
        currentSDRegion = (XQueryStructuredDocumentRegion)region;
        previousSDRegion = null;

        if (region != null) {
            this.language = language;
            this.isValidLanguage = new Stack<Boolean>();

            previousSDRegion = (XQueryStructuredDocumentRegion)currentSDRegion.getPrevious();
            this.offset = offset;
            this.length = length;
            if (module == null) {
                module = nodeFactory.newModule();

                // Make sure that the change covers the entire document
                this.offset = 0;
                this.length = Integer.MAX_VALUE;
            }

            module.setFirstStructuredDocumentRegion(region);

            reparseVersionDecl(module);
            reparseLibraryOrModule(module);

            module.setLastStructuredDocumentRegion(previousSDRegion);

            if (currentSDRegion != null) {
                model.reportError(currentSDRegion, "Syntax error: expected end of file.", false);
            }

            return module;
        }

        reportError(XQueryMessages.errorXQSE_MissingExpr_UI_);

        return null;
    }

    /** Reparse <tt>VersionDecl?</tt> */
    protected void reparseVersionDecl(ASTModule module) {
        if (sameRegionType(XQueryRegions.KW_XQUERY)) {
            final VersionDeclStructuredDocumentRegion vdregion = (VersionDeclStructuredDocumentRegion)currentSDRegion;
            module.setVersionRegion(vdregion.getVersion());
            module.setEncodingRegion(vdregion.getEncoding());

            nextSDRegion();
        }
    }

    /**
     * Reparse <tt>(LibraryModule | MainModule)</tt>
     */
    protected void reparseLibraryOrModule(ASTModule module) {
        if (sameRegionType(XQueryRegions.KW_MODULE)) {
            reparseLibraryModule(module);
        } else {
            reparseMainModule(module);
        }
    }

    /**
     * Reparse <tt>Prolog QueryBody</tt>
     */
    protected void reparseMainModule(ASTModule module) {
        final int last = reparseProlog(module);
        module.removeChildASTNodesAfter(last);

        reparseQueryBody(module);
    }

    /**
     * Reparse <tt>ModuleDecl Prolog</tt>
     */
    protected void reparseLibraryModule(ASTModule module) {
        reparseModuleDecl(module);

        final int last = reparseProlog(module);
        module.removeChildASTNodesAfter(last);
    }

    /**
     * Reparse <tt>"module" "namespace" NCName "=" URILiteral Separator</tt>
     */
    protected void reparseModuleDecl(ASTModule module) {
        if (overlap()) {
            final ModuleDeclStructuredDocumentRegion moduleDeclRegion = (ModuleDeclStructuredDocumentRegion)currentSDRegion;

            module.setModuleDeclStructuredDocumentRegion(moduleDeclRegion);
        }
        nextSDRegion();
    }

    /**
     * Reparse prolog <tt>
     * Prolog	   ::=   	((DefaultNamespaceDecl | Setter | NamespaceDecl | Import) Separator)* ((VarDecl | FunctionDecl | OptionDecl) Separator)*
     * </tt>
     * 
     * @return the number of prolog declaration
     */
    protected int reparseProlog(ASTModule node) {
        int from = reparseProlog1(node);
        return reparseProlog2(node, from);
    }

    /**
     * Reparse prolog part 1:
     * <tt>((DefaultNamespaceDecl | Setter | NamespaceDecl | Import) Separator)* </tt>
     * 
     * @return the number of prolog 1 declarations
     */
    protected int reparseProlog1(ASTModule module) {
        int count = 0; // number of global declarations

        while (currentSDRegion != null) {
            if (sameRegionType(XQueryRegions.KW_DECLARE)) {

                final ITextRegion region2 = getTextRegion(1);
                if (region2 != null) {
                    final String type2 = region2.getType();

                    if (type2 == XQueryRegions.KW_NAMESPACE) {
                        IASTNode oldDecl = module.getChildASTNodeAt(count);
                        ASTNamespaceDecl newDecl = reparseNamespaceDecl(oldDecl);
                        module.setChildASTNodeAt(count++, newDecl);
                    } else if (type2 == XQueryRegions.KW_BOUNDARY_SPACE || type2 == XQueryRegions.KW_DEFAULT
                            || type2 == XQueryRegions.KW_BASEURI || type2 == XQueryRegions.KW_CONSTRUCTION
                            || type2 == XQueryRegions.KW_ORDERING || type2 == XQueryRegions.KW_COPYNAMESPACES) {
                        // Just skip for now...
                        nextSDRegion();
                    } else {
                        // Certainly a prolog2 declaration.. exit.
                        break;
                    }
                } else {
                    // This can occurs when the sdregion contains nested comments
                    nextSDRegion();
                }

            } else if (sameRegionType(XQueryRegions.KW_IMPORT)) {
                // Just skip for now...
                nextSDRegion();
            } else {
                // Certainly a prolog2 declaration...
                break;
            }

            if (checkAndReport(XQueryRegions.SEPARATOR, XQueryMessages.errorXQSE_MissingSemicolon_UI_)) {
                nextSDRegion(); // ";"
            }
        }

        return count;
    }

    /**
     * Reparse <tt>NamespaceDecl	   ::=   	"declare" "namespace" NCName "=" URILiteral</tt>
     */
    protected ASTNamespaceDecl reparseNamespaceDecl(IASTNode decl) {
        ASTNamespaceDecl nsdecl = asNamespaceDecl(decl);
        nsdecl.setFirstStructuredDocumentRegion(currentSDRegion);
        nextSDRegion();
        return nsdecl;
    }

    /**
     * Reparse <tt>"declare" "default" ("element" | "function") "namespace" URILiteral</tt>
     */
    protected void reparseDefaultNSDecl(ASTModule node) {
        nextSDRegion();
    }

    /**
     * Reparse prolog part 2: <tt>((VarDecl | FunctionDecl | OptionDecl) Separator)*</tt>
     * 
     * @param from
     *            last global declaration position.
     * @return
     */
    protected int reparseProlog2(ASTModule module, int from) {
        while (sameRegionType(XQueryRegions.KW_DECLARE)) {
            ITextRegion region2 = getTextRegion(1);
            if (region2 != null) {
                String type2 = region2.getType();

                IASTNode oldDecl = module.getChildASTNodeAt(from);

                if (type2 == XQueryRegions.KW_VARIABLE) {
                    ASTVarDecl newDecl = reparseVarDecl(oldDecl);
                    module.setVariableDecl(from++, newDecl.getName(), newDecl);
                } else if (type2 == XQueryRegions.KW_FUNCTION) {
                    ASTFunctionDecl newDecl = reparseFunctionDecl(oldDecl);
                    module.setFunctionDecl(from++, newDecl.getName(), newDecl);
                } else if (type2 == XQueryRegions.KW_OPTION) {
                    reparseOptionDecl(module);
                } else {
                    // Probably a Prolog1 declaration in the wrong place
                    // TODO: report error
                    break;
                }
            } else {
                // Probably contains an invalid nested xquery comments.
                nextSDRegion();
            }

            if (sameRegionType(XQueryRegions.SEPARATOR)) {
                nextSDRegion(); // ';'
            } else {
                reportError(XQueryMessages.errorXQSE_MissingSemicolon_UI_);
            }
        }

        return from;
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
     * 
     * XQuery Update:
     * <tt>declare" "updating"? "function" QName "(" ParamList? ")" ("as" SequenceType)? (EnclosedExpr | "external")
     * 
     * XQuery Scripting:
     * <tt>"declare" ("simple"? | "updating") "function" QName "(" ParamList? ")" ("as" SequenceType)? (EnclosedExpr | "external"))
     *      | ("declare" "sequential" "function" QName "(" ParamList? ")" ("as" SequenceType)? (Block | "external"))
     */
    protected ASTFunctionDecl reparseFunctionDecl(IASTNode node) {
        ASTFunctionDecl decl = asFunctionDecl(node);
        decl.setFirstStructuredDocumentRegion(currentSDRegion);

        nextSDRegion(); // "declare" .... "function"

        if (sameRegionType(XQueryRegions.FUNCTIONNAME)) {
            String functionName = currentSDRegion.getText(currentSDRegion.getFirstRegion());
            decl.setName(functionName);

            if (currentSDRegion.getNumberOfRegions() == 2) {
                nextSDRegion(); // QName (

                if (reparseParamListOpt(decl)) {
                    if (sameRegionType(XQueryRegions.RPAR)) {
                        nextSDRegion(); // ')'

                        reparseTypeDeclarationOpt(null);

                        if (sameRegionType(XQueryRegions.KW_EXTERNAL)) {
                            nextSDRegion(); // 'external'
                        } else if (SDRegionUtils.containsSequential(currentSDRegion)) {
                            reparseBlock();
                        } else {

                            IASTNode newBody = reparseEnclosedExpr(decl.getBody());
                            decl.setBody(newBody);

                        }

                        decl.setLastStructuredDocumentRegion(currentSDRegion); // Should be ';'

                    } else {
                        reportError(XQueryMessages.errorXQSE_MissingRPar_UI_);
                    }
                }
            } else {
                // Missing (
                reportError(XQueryMessages.errorXQSE_MissingLPar_UI_);
            }

        } else {
            // Function name not typed yet.
            decl.setName(null);
            reportError(XQueryMessages.errorXQSE_MissingFunctionName_UI_);
        }

        return decl;
    }

    /**
     * Reparse <tt>"{" BlockDecls BlockBody "}"</tt>
     * 
     * @return
     */
    protected IASTNode reparseBlock() {
        nextSDRegion(); // "{"
        reparseBlockDecls();
        reparseBlockBody();
        nextSDRegion(); // "}"
        return null;
    }

    /**
     * Reparse <tt>Expr</tt>
     */
    protected void reparseBlockBody() {
        reparseExpr(null);
    }

    /**
     * Reparse <tt>(BlockVarDecl ";")*</tt>
     */
    protected void reparseBlockDecls() {
        while (sameRegionType(XQueryRegions.KW_DECLARE)) {
            reparseBlockVarDecl();

            nextSDRegion(); // ";"
        }

    }

    /**
     * Reparse
     * <tt>"declare" "$" VarName TypeDeclaration? (":=" ExprSingle)? ("," "$" VarName TypeDeclaration? (":=" ExprSingle)?)*</tt>
     */
    protected void reparseBlockVarDecl() {
        nextSDRegion(); // Skip the whole thing...
    }

    /**
     * Reparse <tt>"{" Expr "}"</tt>
     */
    protected IASTNode reparseEnclosedExpr(IASTNode node) {
        if (sameRegionType(XQueryRegions.LCURLY)) {
            nextSDRegion(); // "{"

            IASTNode enclosed = reparseExpr(node);

            if (enclosed == null) {
                reportError(XQueryMessages.errorXQSE_MissingExpr_UI_);
            }

            if (sameRegionType(XQueryRegions.RCURLY)) {
                nextSDRegion(); // "}"
            } else {
                // Report error only if there wasn't an error before
                if (enclosed != null) {
                    reportError(XQueryMessages.errorXQSE_MissingRCurly_UI_);
                }
            }

            return enclosed;
        }

        reportError(XQueryMessages.errorXQSE_MissingLCurly_UI_);
        return null;
    }

    /**
     * Reparse <tt>(Param ("," Param)*)?</tt>
     * 
     * @return true is no syntax error have been raised.
     */
    protected boolean reparseParamListOpt(ASTFunctionDecl decl) {
        if (sameRegionType(XQueryRegions.RPAR)) {
            return true;
        }

        int index = 0;
        do {
            if (sameRegionType(XQueryRegions.DOLLAR)) {
                if (reparseParam(decl, index)) {

                    if (!sameRegionType(XQueryRegions.COMMA)) {
                        decl.removeParamNamesAfter(index);
                        break;
                    }
                    nextSDRegion(); // ","
                }
            } else {
                if (index == 0) {
                    reportError(XQueryMessages.errorXQSE_MissingVarNameOrRPar_UI_);
                } else {
                    reportError(XQueryMessages.errorXQSE_MissingVarName_UI_);
                }
                return false;
            }
            index++;

        } while (currentSDRegion != null);

        return true;
    }

    /**
     * Reparse <tt>"$" QName TypeDeclaration?</tt>
     */
    protected boolean reparseParam(ASTFunctionDecl decl, int index) {
        decl.setParamName(index, currentSDRegion);

        if (currentSDRegion.getNumberOfRegions() == 1) {
            nextSDRegion(); // "$" QName
            reportError(XQueryMessages.errorXQSE_MissingVarName_UI_);
            return false;
        }

        nextSDRegion(); // "$" QName
        reparseTypeDeclarationOpt(null);
        return true;
    }

    /**
     * Reparse
     * <tt>"declare" "variable" "$" QName TypeDeclaration? ((":=" ExprSingle) | "external")</tt>
     * 
     * @param index
     *            of the variable declaration in the module
     */
    protected ASTVarDecl reparseVarDecl(IASTNode node) {
        ASTVarDecl decl = asVarDecl(node);
        decl.setFirstStructuredDocumentRegion(currentSDRegion);

        nextSDRegion(); // "declare" ... "variable"

        if (sameRegionType(XQueryRegions.DOLLAR)) {
            final String name = currentSDRegion.getFullText();
            decl.setName(name);

            nextSDRegion(); // "$" QName

            IASTNode type = reparseTypeDeclarationOpt(null);

            if (sameRegionType(XQueryRegions.KW_EXTERNAL)) {
                nextSDRegion(); // 'external'
                decl.setExpr(null);
            } else if (sameRegionType(XQueryRegions.ASSIGN)) {
                nextSDRegion(); // ':='

                IASTNode oldExpr = decl.getExpr();
                IASTNode newExpr = reparseExprSingle(oldExpr);
                decl.setExpr(newExpr);

                if (newExpr == null) {
                    reportError(XQueryMessages.errorXQSE_MissingExprSingle_UI_);
                }
            } else {
                if (type == null) {
                    reportError(XQueryMessages.errorXQSE_MissingTypeOrAssignOrExternal_UI_);
                } else {
                    reportError(XQueryMessages.errorXQSE_MissingAssignOrExternal_UI_);
                }
            }
        } else {
            // Variable name hasn't be typed yet
            decl.setName(null);
            reportError(XQueryMessages.errorXQSE_MissingVarName_UI_);
        }

        decl.setLastStructuredDocumentRegion(currentSDRegion); // Should be ';'

        return decl;
    }

    /**
     * Reparse <tt>QueryBody	   ::=   	Expr</tt>
     */
    protected void reparseQueryBody(ASTModule module) {
        IASTNode oldBody = module.getQueryBody();
        IASTNode newBody = reparseExpr(oldBody);
        module.setQueryBody(newBody);
    }

    /**
     * Reparse <tt>Expr	::=	ExprSingle ("," ExprSingle)*</tt>
     * <p>
     * XQuery Scripting: <tt>Expr	::= ApplyExpr</tt>
     */
    protected IASTNode reparseExpr(IASTNode expr) {
        // Parse as if the language is XQuery Scripting to report meaningful
        // language-violation errors

        return reparseApplyExpr(expr);
    }

    /**
     * Reparse <tt>ApplyExpr	   ::=   	ConcatExpr (";" (ConcatExpr ";")*)?</tt>
     * 
     * @param expr
     * @return
     */
    protected IASTNode reparseApplyExpr(IASTNode expr) {
        ASTApply apply = asApply(expr);

        int index = 0;
        do {
            IASTNode oldExpr = apply.getChildASTNodeAt(index);
            IASTNode concatExpr = reparseConcatExpr(oldExpr);
            apply.setChildASTNodeAt(index, concatExpr);

            if (sameRegionType(XQueryRegions.SEPARATOR)) {
                nextSDRegion(); // ';'
            } else {
                if (index >= 1) {
                    reportError(XQueryMessages.errorXQSE_MissingSemicolon_UI_);
                } else {
                    // Unwrap
                    return concatExpr;
                }

                break;
            }

            index++;
        } while (currentSDRegion != null);

        return apply;
    }

    /**
     * Reparse
     * 
     * <tt>	ConcatExpr	   ::=   	ExprSingle ("," ExprSingle)*</tt>
     * 
     * @param expr
     */
    protected IASTNode reparseConcatExpr(IASTNode expr) {
        return reparseOperatorStar(expr, sequenceFilter, exprContinuation);
    }

    /**
     * Reparse
     * 
     * <tt>ExprSingle</tt>
     * 
     * @return an AST node or null if no expression single have been parsed.
     */
    protected IASTNode reparseExprSingle(IASTNode expr) {
        IASTNode newExpr = null;
        if (sameRegionType(forLetFilter)) {
            newExpr = reparseFLWORExpr(expr);
        } else if (sameRegionType(quantifiedFilter)) {
            newExpr = reparseQuantifiedExpr(expr);
        } else if (sameRegionType(XQueryRegions.KW_TYPESWITCH)) {
            newExpr = reparseTypeSwitch(expr);
        } else if (sameRegionType(XQueryRegions.KW_IF)) {
            newExpr = reparseIfExpr(expr);
        } else if (sameRegionType(XQueryRegions.KW_INSERT)) {
            newExpr = reparseInsertExpr(expr);
        } else if (sameRegionType(XQueryRegions.KW_DELETE)) {
            newExpr = reparseDeleteExpr(expr);
        } else if (sameRegionType(XQueryRegions.KW_REPLACE)) {
            newExpr = reparseReplaceExpr(expr);
        } else if (sameRegionType(XQueryRegions.KW_RENAME)) {
            newExpr = reparseRenameExpr(expr);
        } else if (sameRegionType(XQueryRegions.KW_COPY)) {
            newExpr = reparseTransformExpr(expr);
        } else {
            newExpr = reparseOrExpr(expr);
        }

        return newExpr;
    }

    /**
     * Reparse
     * <tt>"copy" "$" VarName ":=" ExprSingle ("," "$" VarName ":=" ExprSingle)* "modify" ExprSingle "return" ExprSingle</tt>
     */
    protected IASTNode reparseTransformExpr(IASTNode node) {
        final IStructuredDocumentRegion first = currentSDRegion;

        ASTTransform transform = asTransformExpr(node);

        nextSDRegion(); // 'copy'

        int index = 0;
        do {
            if (checkAndReport(XQueryRegions.DOLLAR, XQueryMessages.errorXQSE_MissingVarName_UI_)) {
                transform.setBindingVariable(index, currentSDRegion.getFullText().trim());
                nextSDRegion(); // '$' VarName

                if (checkAndReport(XQueryRegions.ASSIGN, XQueryMessages.errorXQSE_MissingAssign_UI_)) {
                    nextSDRegion(); // ":="

                    IASTNode oldExpr = transform.getBindingExpr(index);
                    IASTNode newExpr = reparseExprSingle(oldExpr);
                    transform.setBindingExpr(index, newExpr);

                } else {
                    break;
                }
            } else {
                break;
            }

            if (!sameRegionType(XQueryRegions.COMMA)) {
                break;
            }

            nextSDRegion(); // ','
            index++;
        } while (currentSDRegion != null);

        if (checkAndReport(XQueryRegions.KW_MODIFY, XQueryMessages.errorXQSE_MissingModify_UI_)) {
            nextSDRegion(); // 'modify'

            IASTNode oldModifyExpr = transform.getModifyExpr();
            IASTNode newModifyExpr = reparseExprSingle(oldModifyExpr);
            transform.setModifyExpr(newModifyExpr);

            if (checkAndReport(XQueryRegions.KW_RETURN, XQueryMessages.errorXQSE_MissingReturn_UI_)) {
                nextSDRegion(); // 'return'

                IASTNode oldReturnExpr = transform.getReturnExpr();
                IASTNode newReturnExpr = reparseExprSingle(oldReturnExpr);
                transform.setReturnExpr(newReturnExpr);
            }
        }

        final IStructuredDocumentRegion last = currentSDRegion == null ? previousSDRegion : currentSDRegion;

        checkAndReportLanguage(IXQDTLanguageConstants.LANGUAGE_XQUERY_UPDATE, first, last,
                XQueryMessages.errorXQSE_XULanguageNotAllowed_UI_);

        return transform;
    }

    /**
     * Reparse <tt>"rename" "node" TargetExpr "as" NewNameExpr</tt>
     */
    protected IASTNode reparseRenameExpr(IASTNode node) {
        final IStructuredDocumentRegion first = currentSDRegion;

        ASTRename renameExpr = asRenameExpr(node);

        nextSDRegion(); // "rename" "node"

        IASTNode oldTargetExpr = renameExpr.getTargetExpr();
        IASTNode newTargetExpr = reparseExprSingle(oldTargetExpr);
        renameExpr.setTargetExpr(newTargetExpr);

        if (sameRegionType(XQueryRegions.KW_AS)) {
            nextSDRegion(); // 'as'

            IASTNode oldExpr = renameExpr.getNewNameExpr();
            IASTNode newExpr = reparseExprSingle(oldExpr);
            renameExpr.setNewNameExpr(newExpr);
        } else {
            reportError(XQueryMessages.errorXQSE_MissingAs_UI_);
        }

        final IStructuredDocumentRegion last = currentSDRegion == null ? previousSDRegion : currentSDRegion;

        checkAndReportLanguage(IXQDTLanguageConstants.LANGUAGE_XQUERY_UPDATE, first, last,
                XQueryMessages.errorXQSE_XULanguageNotAllowed_UI_);

        return renameExpr;
    }

    /**
     * Reparse <tt>"replace" ("value" "of")? "node" TargetExpr "with" ExprSingle</tt>
     */
    protected IASTNode reparseReplaceExpr(IASTNode node) {
        final IStructuredDocumentRegion first = currentSDRegion;

        ASTReplace replaceExpr = asReplaceExpr(node);

        nextSDRegion(); // "replace" ("value" "of")? "node"

        IASTNode oldTargetExpr = replaceExpr.getTargetExpr();
        IASTNode newTargetExpr = reparseExprSingle(oldTargetExpr);
        replaceExpr.setTargetExpr(newTargetExpr);

        if (sameRegionType(XQueryRegions.KW_WITH)) {
            nextSDRegion(); // 'with'

            IASTNode oldExpr = replaceExpr.getExprSingle();
            IASTNode newExpr = reparseExprSingle(oldExpr);
            replaceExpr.setExprSingle(newExpr);
        } else {
            reportError(XQueryMessages.errorXQSE_MissingWith_UI_);
        }

        final IStructuredDocumentRegion last = currentSDRegion == null ? previousSDRegion : currentSDRegion;
        checkAndReportLanguage(IXQDTLanguageConstants.LANGUAGE_XQUERY_UPDATE, first, last,
                XQueryMessages.errorXQSE_XULanguageNotAllowed_UI_);

        return replaceExpr;
    }

    /**
     * Reparse <tt>"delete" ("node" | "nodes") TargetExpr</tt>
     */
    protected IASTNode reparseDeleteExpr(IASTNode node) {
        final IStructuredDocumentRegion first = currentSDRegion;

        ASTDelete deleteExpr = asDeleteExpr(node);

        nextSDRegion(); // "delete" ("node" | "nodes")

        IASTNode oldTargetExpr = deleteExpr.getTargetExpr();
        IASTNode newTargetExpr = reparseExprSingle(oldTargetExpr);
        deleteExpr.setTargetExpr(newTargetExpr);

        final IStructuredDocumentRegion last = currentSDRegion == null ? previousSDRegion : currentSDRegion;

        checkAndReportLanguage(IXQDTLanguageConstants.LANGUAGE_XQUERY_UPDATE, first, last,
                XQueryMessages.errorXQSE_XULanguageNotAllowed_UI_);

        return deleteExpr;
    }

    /**
     * Reparse <tt>"insert" ("node" | "nodes") SourceExpr InsertExprTargetChoice TargetExpr</tt>
     */
    protected IASTNode reparseInsertExpr(IASTNode node) {
        final IStructuredDocumentRegion first = currentSDRegion;

        ASTInsert insertExpr = asInsertExpr(node);

        nextSDRegion(); // "insert" ("node" | "nodes")

        IASTNode oldSourceExpr = insertExpr.getSourceExpr();
        IASTNode newSourceExpr = reparseExprSingle(oldSourceExpr);
        insertExpr.setSourceExpr(newSourceExpr);

        if (sameRegionType(XQueryRegions.KW_AS) || sameRegionType(XQueryRegions.KW_BEFORE)
                || sameRegionType(XQueryRegions.KW_AFTER)) {
            nextSDRegion(); // (("as" ("first" | "last"))? "into") | "after" |
                            // "before"

            IASTNode oldTargetExpr = insertExpr.getTargetExpr();
            IASTNode newTargetExpr = reparseExprSingle(oldTargetExpr);
            insertExpr.setTargetExpr(newTargetExpr);
        } else {
            reportError(XQueryMessages.errorXQSE_MissingTargetChoice_UI_);
        }

        final IStructuredDocumentRegion last = currentSDRegion == null ? previousSDRegion : currentSDRegion;
        checkAndReportLanguage(IXQDTLanguageConstants.LANGUAGE_XQUERY_UPDATE, first, last,
                XQueryMessages.errorXQSE_XULanguageNotAllowed_UI_);

        return insertExpr;
    }

    /**
     * Reparse <tt>"if" "(" Expr ")" "then" ExprSingle "else" ExprSingle</tt>
     */
    protected IASTNode reparseIfExpr(IASTNode expr) {
        ASTIf ifexpr = asIf(expr);

        nextSDRegion(); // "if"
        nextSDRegion(); // ( (always there)

        IASTNode oldCondition = ifexpr.getConditionExpr();
        IASTNode newCondition = reparseExpr(oldCondition);
        ifexpr.setConditionExpr(newCondition);

        if (newCondition == null) {
            reportError(XQueryMessages.errorXQSE_MissingExpr_UI_);
        } else {
            if (checkAndReport(XQueryRegions.RPAR, XQueryMessages.errorXQSE_MissingRPar_UI_)) {
                nextSDRegion(); // )

                if (checkAndReport(XQueryRegions.KW_THEN, XQueryMessages.errorXQSE_MissingThen_UI_)) {
                    nextSDRegion(); // then

                    IASTNode newThen = reparseExprSingle(ifexpr.getThenExpr());
                    ifexpr.setThenExpr(newThen);
                    if (newThen == null) {
                        reportError(XQueryMessages.errorXQSE_MissingExprSingle_UI_);
                    } else {
                        if (checkAndReport(XQueryRegions.KW_ELSE, XQueryMessages.errorXQSE_MissingElse_UI_)) {
                            nextSDRegion(); // else

                            IASTNode newElse = reparseExprSingle(ifexpr.getElseExpr());
                            ifexpr.setElseExpr(newElse);

                            if (newElse == null) {
                                reportError(XQueryMessages.errorXQSE_MissingExprSingle_UI_);
                            }
                        }
                    }
                }
            }
        }
        return ifexpr;
    }

    /**
     * Reparse
     * <tt>"typeswitch" "(" Expr ")" CaseClause+ "default" ("$" VarName)? "return" ExprSingle</tt>
     */
    protected IASTNode reparseTypeSwitch(IASTNode expr) {
        ASTTypeswitch typeswitch = asTypeswitch(expr);

        nextSDRegion(); // typeswitch
        nextSDRegion(); // ( (always there)

        IASTNode oldOperand = typeswitch.getOperandExpr();
        IASTNode newOperand = reparseExpr(oldOperand);
        typeswitch.setOperandExpr(newOperand);

        if (newOperand == null) {
            reportError(XQueryMessages.errorXQSE_MissingExpr_UI_);
        } else {
            if (checkAndReport(XQueryRegions.RPAR, XQueryMessages.errorXQSE_MissingRPar_UI_)) {
                nextSDRegion(); // )

                if (reparseCaseClauses(typeswitch)) {

                    if (checkAndReport(XQueryRegions.KW_DEFAULT, XQueryMessages.errorXQSE_MissingDefault_UI_)) {
                        nextSDRegion(); // default

                        if (sameRegionType(XQueryRegions.DOLLAR)) {
                            typeswitch.setDefaultCaseVarname(currentSDRegion);
                            nextSDRegion(); // "$" Varname
                        }

                        if (checkAndReport(XQueryRegions.KW_RETURN, XQueryMessages.errorXQSE_MissingReturn_UI_)) {
                            nextSDRegion(); // return

                            IASTNode oldDefaultReturn = typeswitch.getDefaultCaseExpr();
                            IASTNode newDefaultReturn = reparseExprSingle(oldDefaultReturn);
                            typeswitch.setDefaultCaseExpr(newDefaultReturn);

                            if (newDefaultReturn == null) {
                                reportError(XQueryMessages.errorXQSE_MissingExprSingle_UI_);
                            }
                        }
                    }
                }
            }
        }
        return typeswitch;
    }

    /**
     * Reparse <tt>("case" ("$" VarName "as")? SequenceType "return" ExprSingle)+</tt>
     */
    protected boolean reparseCaseClauses(ASTTypeswitch typeswitch) {
        int index = 0;
        while (sameRegionType(XQueryRegions.KW_CASE)) {
            nextSDRegion(); // case

            boolean varspecified = sameRegionType(XQueryRegions.DOLLAR);
            if (varspecified) {
                typeswitch.setCaseVarname(index, currentSDRegion);
                nextSDRegion(); // "$" Varname

                if (checkAndReport(XQueryRegions.KW_AS, XQueryMessages.errorXQSE_MissingAs_UI_)) {
                    nextSDRegion(); // as
                } else {
                    return false;
                }
            }

            IASTNode type = reparseSequenceType(null);
            if (type == null) {
                if (varspecified) {
                    reportError(XQueryMessages.errorXQSE_MissingSequenceType_UI_);
                } else {
                    reportError(XQueryMessages.errorXQSE_MissingVarOrSequenceType_UI_);
                }

                return false;
            }

            if (checkAndReport(XQueryRegions.KW_RETURN, XQueryMessages.errorXQSE_MissingReturn_UI_)) {
                nextSDRegion(); // return

                IASTNode oldExpr = typeswitch.getCaseExpr(index);
                IASTNode newExpr = reparseExprSingle(oldExpr);
                typeswitch.setCaseExpr(index, newExpr);

                if (newExpr == null) {
                    reportError(XQueryMessages.errorXQSE_MissingExprSingle_UI_);
                    return false;
                }
            } else {
                return false;
            }

            index++;

        }

        if (index == 0) {
            reportError(XQueryMessages.errorXQSE_MissingFirstTSCase_UI_);
            return false;
        }

        return true;
    }

    /**
     * Reparse
     * 
     * <tt>("some" | "every") "$" VarName TypeDeclaration? "in" ExprSingle ("," "$" VarName TypeDeclaration? "in" ExprSingle)* "satisfies" ExprSingle</tt>
     */
    protected IASTNode reparseQuantifiedExpr(IASTNode expr) {
        ASTQuantified quantified = asQuantified(expr);

        ASTClause oldClause = quantified.getBindingClause();
        ASTBindingClause newClause = reparseForLetQuantifyClause(oldClause);
        quantified.setBindingClause(newClause);

        if (sameRegionType(XQueryRegions.KW_SATIFIES)) {
            nextSDRegion(); // satisfies

            IASTNode oldExpr = quantified.getSatisfiesExpr();
            quantified.setSatisfiesExpr(reparseExprSingle(oldExpr));
        } else {
            reportError(XQueryMessages.errorXQSE_MissingSatisfies_UI_);
        }

        return quantified;
    }

    /**
     * Reparse <tt>(ForClause | LetClause)+ WhereClause? OrderByClause? "return" ExprSingle</tt>
     */
    protected IASTNode reparseFLWORExpr(IASTNode expr) {
        ASTFLWOR flwor = asFLWOR(expr);
        flwor.setFirstStructuredDocumentRegion(currentSDRegion);

        int index = 0;
        do {
            ASTClause oldClause = flwor.getClause(index);
            ASTClause newClause = reparseForLetQuantifyClause(oldClause);
            flwor.setClause(index++, newClause);

            if (!sameRegionType(forLetFilter)) {
                break;
            }
        } while (currentSDRegion != null);

        // WhereClause
        if (sameRegionType(XQueryRegions.KW_WHERE)) {
            IASTNode oldWhere = flwor.getClause(index);
            ASTClause newWhere = reparseWhereClause(oldWhere);
            flwor.setClause(index++, newWhere);
        }

        // Order by clause
        if (sameRegionType(XQueryRegions.KW_ORDER) || sameRegionType(XQueryRegions.KW_STABLE)) {
            nextSDRegion(); // 'Order by' or 'stable order by'

            IASTNode oldClause = flwor.getClause(index);
            flwor.setClause(index++, reparseOrderByClause(oldClause));
        }

        // Return
        if (checkAndReport(XQueryRegions.KW_RETURN, XQueryMessages.errorXQSE_MissingReturn_UI_)) {
            nextSDRegion(); // 'return'

            IASTNode returnExpr = reparseExprSingle(flwor.getReturnExpr());
            flwor.setReturnExpr(returnExpr);
        }

        return flwor;
    }

    /**
     * Reparse <tt>(("order" "by") | ("stable" "order" "by")) OrderSpecList</tt>
     */
    protected ASTClause reparseOrderByClause(IASTNode node) {
        ASTOrderByClause clause = asOrderByClause(node);
        clause.setClauseType(IASTNode.ORDERBYCLAUSE);
        reparseOrderSpecList(clause);
        return clause;
    }

    /**
     * Reparse <tt>"where" ExprSingle</tt>
     */
    protected ASTClause reparseWhereClause(IASTNode node) {
        ASTExprSingleClause clause = asExprSingleClause(node);
        clause.setClauseType(IASTNode.WHERECLAUSE);
        clause.setFirstStructuredDocumentRegion(currentSDRegion);

        nextSDRegion(); // 'where'

        IASTNode oldExpr = clause.getExpr();
        IASTNode newExpr = reparseExprSingle(oldExpr);
        clause.setExpr(newExpr);

        if (newExpr == null) {
            reportError(XQueryMessages.errorXQSE_MissingExprSingle_UI_);
        }
        clause.setLastStructuredDocumentRegion(currentSDRegion);

        return clause;
    }

    /**
     * Reparse <tt>OrderSpec ("," OrderSpec)*</tt>
     */
    protected void reparseOrderSpecList(ASTOrderByClause clause) {
        int index = 0;
        while (currentSDRegion != null) {
            reparseOrderSpec(clause, index);

            if (!sameRegionType(XQueryRegions.COMMA)) {
                break;
            }

            nextSDRegion(); // ","
        }
    }

    /**
     * Reparse <tt>ExprSingle OrderModifier</tt>
     */
    protected void reparseOrderSpec(ASTOrderByClause clause, int index) {
        IASTNode oldOrderExpr = clause.getOrderSpecExpr(index);
        clause.setOrderSpecExpr(index, reparseExprSingle(oldOrderExpr));
        reparseOrderModifier(clause, index);
    }

    /**
     * Reparse
     * <tt>("ascending" | "descending")? ("empty" ("greatest" | "least"))? ("collation" URILiteral)?</tt>
     * 
     * @param index
     */
    protected void reparseOrderModifier(ASTOrderByClause clause, int index) {
        if (sameRegionType(XQueryRegions.KW_ASCENDING) || sameRegionType(XQueryRegions.KW_DESCENDING)) {
            nextSDRegion();
        }

        if (sameRegionType(XQueryRegions.KW_EMPTY)) {
            nextSDRegion(); // 'empty' ("greatest" | "least")
        }

        if (sameRegionType(XQueryRegions.KW_COLLATION)) {
            nextSDRegion();
            if (sameRegionType(XQueryRegions.URILITERAL)) {
                nextSDRegion();
            }
        }
    }

    /**
     * Reparse
     * <tt>"for" "$" VarName TypeDeclaration? PositionalVar? "in" ExprSingle ("," "$" VarName TypeDeclaration? PositionalVar? "in" ExprSingle)* </tt>
     * <tt>"let" "$" VarName TypeDeclaration? ":=" ExprSingle ("," "$" VarName TypeDeclaration? ":=" ExprSingle)*</tt>
     * <tt>("some" | "every") "$" VarName TypeDeclaration? "in" ExprSingle ("," "$" VarName TypeDeclaration? "in" ExprSingle)*</tt>
     */
    protected ASTBindingClause reparseForLetQuantifyClause(IASTNode node) {
        ASTBindingClause clause = asBindingClause(node);
        clause.setFirstStructuredDocumentRegion(currentSDRegion);

        final String clauseType = currentSDRegion.getType();
        if (clauseType == XQueryRegions.KW_LET) {
            clause.setClauseType(IASTNode.LETCLAUSE);
        } else if (clauseType == XQueryRegions.KW_FOR) {
            clause.setClauseType(IASTNode.FORCLAUSE);
        } else {
            clause.setClauseType(IASTNode.QUANTIFIEDCLAUSE);
        }

        nextSDRegion(); // 'for', 'let', 'some', 'every'
        // The first '$' is always there

        int index = 0;
        do {

            if (checkAndReport(XQueryRegions.DOLLAR, XQueryMessages.errorXQSE_MissingVarName_UI_)) {

                clause.setBindingVariable(index, currentSDRegion);

                if (nextSDRegion()) {
                    IASTNode oldTypeDecl = clause.getTypeDeclaration(index);
                    IASTNode newTypeDecl = reparseTypeDeclarationOpt(oldTypeDecl);
                    clause.setTypeDeclaration(index, newTypeDecl);

                    if (clause.getType() == IASTNode.FORCLAUSE) {
                        reparsePositionalVarOpt(clause, index);
                    }

                    if (nextSDRegion()) // Either := or in
                    {
                        IASTNode oldExpr = clause.getBindingExpr(index);
                        IASTNode newExpr = reparseExprSingle(oldExpr);
                        clause.setBindingExpr(index, newExpr);

                        if (sameRegionType(XQueryRegions.COMMA)) {
                            nextSDRegion(); // ','
                            index++;
                        } else {
                            break; // done parsing
                        }
                    } else {
                        break; // missing := or in
                    }

                } else {
                    reportError("Syntax error: missing token");
                    break; // missing token
                }

            } else {
                break; // wrong token (expected '$')
            }

        } while (currentSDRegion != null);

        return clause;
    }

    /**
     * Reparse
     * 
     * <tt>("at" "$" VarName)?</tt>
     * 
     * @param index
     */
    protected void reparsePositionalVarOpt(ASTBindingClause clause, int index) {
        if (sameRegionType(XQueryRegions.KW_AT)) {
            nextSDRegion(); // 'at'

            if (checkAndReport(XQueryRegions.DOLLAR, XQueryMessages.errorXQSE_MissingVarName_UI_)) {
                clause.setPositionalVar(index, currentSDRegion);
                nextSDRegion(); // '$' VarName
            }
        }
    }

    /**
     * Reparse <tt>TypeDeclaration?</tt>
     */
    protected IASTNode reparseTypeDeclarationOpt(IASTNode node) {
        if (sameRegionType(XQueryRegions.KW_AS)) {
            nextSDRegion(); // skip 'as'
            return reparseSequenceType(node);
        }

        return null;
    }

    /**
     * Reparse <tt>AtomicType "?"?</tt>
     */
    public IASTNode reparseSingleType(IASTNode node) {
        if (sameRegionType(XQueryRegions.ST_ATOMICTYPE)) {
            ASTSingleType type = asSingleType(node);

            type.setFirstStructuredDocumentRegion(currentSDRegion);
            nextSDRegion(); // AtomicType

            if (sameRegionType(XQueryRegions.OCC_OPTIONAL)) {
                nextSDRegion(); // '?'
            }
            return type;
        }

        return null;
    }

    /**
     * Reparse <tt>SequenceType</tt>
     */
    protected IASTNode reparseSequenceType(IASTNode node) {
        if (sameRegionType(sequenceTypeFilter)) {
            ASTSequenceType type = asSequenceType(node);

            type.setFirstStructuredDocumentRegion(currentSDRegion);
            nextSDRegion();
            return type;
        }
        return null;
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
     * Reparse <tt> RangeExpr ( (ValueComp | GeneralComp | NodeComp) RangeExpr )?</tt>
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
     * Reparse <tt>IntersectExceptExpr ( ("union" | "|") IntersectExceptExpr )*</tt>
     */
    protected IASTNode reparseUnionExpr(IASTNode expr) {
        return reparseOperatorStar(expr, unionFilter, unionContinuation);
    }

    /**
     * Reparse <tt>InstanceofExpr ( ("intersect" | "except") InstanceofExpr )*</tt>
     */
    protected IASTNode reparseIntersectExceptExpr(IASTNode expr) {
        return reparseOperatorStar(expr, intersectExceptFilter, intersectExceptContinuation);
    }

    /**
     * Reparse <tt>TreatExpr ( "instance" "of" SequenceType )?</tt>
     */
    protected IASTNode reparseInstanceOfExpr(IASTNode node) {
        ASTOperator operator = asOperator(node, ASTOperator.OP_INSTANCEOF);

        IASTNode oldOperand = operator.getChildASTNodeAt(0);
        IASTNode newOperand = reparseTreatExpr(oldOperand);
        operator.setChildASTNodeAt(0, newOperand);

        if (sameRegionType(XQueryRegions.OP_INSTANCEOF)) {
            nextSDRegion(); // 'instance of'
            IASTNode oldType = operator.getChildASTNodeAt(1);
            IASTNode newType = reparseSequenceType(oldType);
            operator.setChildASTNodeAt(1, newType);

            if (newType == null) {
                reportError(XQueryMessages.errorXQSE_MissingSequenceType_UI_);
            }
            return operator;
        }

        return newOperand;
    }

    /**
     * Reparse <tt>CastableExpr ( "treat" "as" SequenceType )?</tt>
     */
    protected IASTNode reparseTreatExpr(IASTNode node) {
        ASTOperator operator = asOperator(node, ASTOperator.OP_TREATAS);

        IASTNode oldOperand = operator.getChildASTNodeAt(0);
        IASTNode newOperand = reparseCastableExpr(oldOperand);
        operator.setChildASTNodeAt(0, newOperand);

        if (sameRegionType(XQueryRegions.OP_TREATAS)) {
            nextSDRegion(); // 'treat as'
            IASTNode oldType = operator.getChildASTNodeAt(1);
            IASTNode newType = reparseSequenceType(oldType);
            operator.setChildASTNodeAt(1, newType);

            if (newType == null) {
                reportError(XQueryMessages.errorXQSE_MissingSequenceType_UI_);
            }
            return operator;
        }

        return newOperand;
    }

    /**
     * Reparse <tt>CastExpr ( "castable" "as" SingleType )?</tt>
     */
    protected IASTNode reparseCastableExpr(IASTNode node) {
        ASTOperator operator = asOperator(node, ASTOperator.OP_CASTABLEAS);

        IASTNode oldOperand = operator.getChildASTNodeAt(0);
        IASTNode newOperand = reparseCastAsExpr(oldOperand);
        operator.setChildASTNodeAt(0, newOperand);

        if (sameRegionType(XQueryRegions.OP_CASTABLEAS)) {
            nextSDRegion(); // 'castable as'
            IASTNode oldType = operator.getChildASTNodeAt(1);
            IASTNode newType = reparseSingleType(oldType);
            operator.setChildASTNodeAt(1, newType);

            if (newType == null) {
                reportError(XQueryMessages.errorXQSE_MissingSingleType_UI_);
            }
            return operator;
        }

        return newOperand;
    }

    /**
     * Reparse <tt>UnaryExpr ( "cast" "as" SingleType )?</tt>
     */
    protected IASTNode reparseCastAsExpr(IASTNode node) {
        ASTOperator operator = asOperator(node, ASTOperator.OP_CASTAS);

        IASTNode oldOperand = operator.getChildASTNodeAt(0);
        IASTNode newOperand = reparseUnaryExpr(oldOperand);
        operator.setChildASTNodeAt(0, newOperand);

        if (sameRegionType(XQueryRegions.OP_CASTAS)) {
            nextSDRegion(); // 'cast as'
            IASTNode oldType = operator.getChildASTNodeAt(1);
            IASTNode newType = reparseSingleType(oldType);
            operator.setChildASTNodeAt(1, newType);

            if (newType == null) {
                reportError(XQueryMessages.errorXQSE_MissingSingleType_UI_);
            }
            return operator;
        }

        return newOperand;
    }

    /**
     * Reparse <tt>("-" | "+")* ValueExpr</tt>
     */
    protected IASTNode reparseUnaryExpr(IASTNode expr) {
        while (sameRegionType(additiveFilter)) {
            nextSDRegion();
        }

        return reparseValueExpr(expr);
    }

    /**
     * Reparse <tt>ValidateExpr | PathExpr | ExtensionExpr</tt>
     * 
     * @return an AST node or null if no matching expression has been parsed.
     */
    protected IASTNode reparseValueExpr(IASTNode expr) {
        if (sameRegionType(XQueryRegions.KW_VALIDATE)) {
            return reparseValidateExpr(expr);
        }

        if (sameRegionType(XQueryRegions.LPRAGMA)) {
            return reparseExtensionExpr(expr);
        }

        return reparsePathExpr(expr);

    }

    /**
     * Reparse <tt>Pragma+ "{" Expr? "}"</tt>
     */
    protected IASTNode reparseExtensionExpr(IASTNode node) {
        // TODO: handle white spaces properly.

        ASTExtension extension = asExtension(node);

        int index = 0;
        while (sameRegionType(XQueryRegions.LPRAGMA)) {
            nextSDRegion(); // (#

            if (sameRegionType(XQueryRegions.PRAGMAQNAME)) {
                nextSDRegion(); // QName

                if (sameRegionType(XQueryRegions.PRAGMACONTENT)) {
                    nextSDRegion(); // Pragma content

                    if (sameRegionType(XQueryRegions.RPRAGMA)) {

                        nextSDRegion(); // #)

                        if (sameRegionType(XQueryRegions.LCURLY)) {
                            break;
                        }
                    } else {
                        reportError(XQueryMessages.errorXQSE_MissingRPragma_UI_);
                        return extension; // interrupt parsing
                    }

                } else {
                    reportError(XQueryMessages.errorXQSE_MissingPragmaContent_UI_);
                    return extension; // interrupt parsing
                }

            } else {
                reportError(XQueryMessages.errorXQSE_MissingPragmaName_UI_);
                return extension; // interrupt parsing
            }

            index++;
        }

        if (sameRegionType(XQueryRegions.LCURLY)) {
            nextSDRegion(); // {
            if (sameRegionType(XQueryRegions.RCURLY)) {
                nextSDRegion(); // }
                return extension;
            }

            IASTNode oldExpr = extension.getChildASTNodeAt(0);
            IASTNode newExpr = reparseExpr(oldExpr);
            extension.setChildASTNodeAt(0, newExpr);

            if (sameRegionType(XQueryRegions.RCURLY)) {
                nextSDRegion(); // }
                return extension;
            }

            reportError(XQueryMessages.errorXQSE_MissingRCurly_UI_);
        } else {
            reportError(XQueryMessages.errorXQSE_MissingLCurly_UI_);
        }
        return extension;
    }

    /**
     * Reparse <tt>"validate" ValidationMode? "{" Expr "}"</tt>
     */
    protected IASTNode reparseValidateExpr(IASTNode node) {
        ASTValidate validate = asValidate(node);

        nextSDRegion(); // validate

        reparseValidationModeOpt(validate);

        IASTNode oldExpr = validate.getChildASTNodeAt(0);
        IASTNode newExpr = reparseEnclosedExpr(oldExpr);
        validate.setChildASTNodeAt(0, newExpr);

        return validate;
    }

    /**
     * Reparse <tt>("lax" | "strict")?</tt>
     */
    protected void reparseValidationModeOpt(ASTValidate node) {
        if (sameRegionType(XQueryRegions.KW_LAX) || sameRegionType(XQueryRegions.KW_STRICT)) {
            nextSDRegion();
        }
    }

    /**
     * Reparse <tt>("/" RelativePathExpr?) | ("//" RelativePathExpr) | RelativePathExpr</tt>
     */
    protected IASTNode reparsePathExpr(IASTNode node) {
        ASTPath path = asPath(node);

        if (sameRegionType(XQueryRegions.PATH_SLASH)) {
            nextSDRegion(); // '/'

            IASTNode oldRelPath = path.getRelativePath();
            IASTNode newRelPath = reparseRelativePathExpr(oldRelPath);
            path.setRelativePath(newRelPath);
        } else {
            final boolean slashslash = sameRegionType(XQueryRegions.PATH_SLASHSLASH);
            if (slashslash) {
                nextSDRegion(); // '//'
            }

            IASTNode oldRelPath = path.getRelativePath();
            IASTNode newRelPath = reparseRelativePathExpr(oldRelPath);
            path.setRelativePath(newRelPath);

            // Syntax checking..
            if (newRelPath == null) {
                if (slashslash) {
                    reportError(XQueryMessages.errorXQSE_MissingRelPath_UI_);
                } else {
                    return null; // Error will be notified by the containing
                                 // expression
                }
            }

            return slashslash ? path : newRelPath;
        }

        return path;
    }

    /**
     * Reparse <tt>StepExpr (("/" | "//") StepExpr)*</tt>
     * 
     * @return a {@link ASTOperator} or null when failing parsing a relative path
     */
    protected IASTNode reparseRelativePathExpr(IASTNode expr) {
        return reparseOperatorStar(expr, relativePathFilter, relativePathContinuation);
    }

    /**
     * Reparse <tt>(PrimaryExpr | AxisStep) PredicateList</tt>
     */
    protected IASTNode reparseStepExpr(IASTNode node) {
        ASTStep step = asStep(node);
        IASTNode primary = null;

        if (sameRegionType(XQueryRegions.NUMERICLITERAL) // Literal
                || sameRegionType(XQueryRegions.STRINGLITERAL)) {
            primary = reparseLiteral(step.getPrimaryExpr());
        } else if (sameRegionType(XQueryRegions.DOLLAR)) {
            primary = reparseVarRef(step.getPrimaryExpr());
        } else if (sameRegionType(XQueryRegions.LPAR)) {
            primary = reparseParentherizeExpr(step.getPrimaryExpr());
        } else if (sameRegionType(XQueryRegions.PATH_CONTEXTITEM)) {
            primary = reparseContextItemExpr(step.getPrimaryExpr());
        } else if (sameRegionType(XQueryRegions.FUNCTIONNAME)) {
            primary = reparseFunctionCall(step.getPrimaryExpr());
        } else if (sameRegionType(XQueryRegions.KW_ORDERED) || sameRegionType(XQueryRegions.KW_UNORDERED)) {
            primary = reparseOrderedUnordered(step.getPrimaryExpr());
        } else if (sameRegionType(XQueryRegions.XML_TAG_OPEN)) {
            primary = reparseDirElemConstructor(step.getPrimaryExpr());
        } else if (sameRegionType(XQueryRegions.XML_COMMENT)) {
            primary = reparseDirCommentConstructor(step.getPrimaryExpr());
        } else if (sameRegionType(XQueryRegions.XML_PI)) {
            primary = reparseDirPIConstructor(step.getPrimaryExpr());
        } else if (sameRegionType(XQueryRegions.KW_DOCUMENT)) {
            primary = reparseCompDocConstructor(step.getPrimaryExpr());
        } else if (sameRegionType(XQueryRegions.KW_ELEMENT)) {
            primary = reparseCompElementConstructor(step.getPrimaryExpr());
        } else if (sameRegionType(XQueryRegions.KW_ATTRIBUTE)) {
            primary = reparseCompAttrConstructor(step.getPrimaryExpr());
        } else if (sameRegionType(XQueryRegions.KW_TEXT)) {
            primary = reparseCompTextConstructor(step.getPrimaryExpr());
        } else if (sameRegionType(XQueryRegions.KW_COMMENT)) {
            primary = reparseCompCommentConstructor(step.getPrimaryExpr());
        } else if (sameRegionType(XQueryRegions.KW_PI)) {
            primary = reparseCompPIConstructor(step.getPrimaryExpr());
        } else {
            if (!reparseAxisStep(step)) {
                return null;
            }
        }

        // PredicateList

        if (sameRegionType(XQueryRegions.LSQUARE)) {
            step.setPrimaryExpr(primary);
            reparsePredicateList(step);

            return step;
        }

        return primary == null ? step : primary;
    }

    /**
     * Reparse <tt>ReverseStep | ForwardStep</tt>
     * 
     * @return true when an axis step has been parsed (even partially)
     */
    protected boolean reparseAxisStep(ASTStep step) {
        boolean nonvoid = false;

        if (sameRegionType(stepFilter)) {
            nextSDRegion(); // Axis name
            nonvoid = true;
        }

        IASTNode oldNodeTest = step.getNodeTest();
        IASTNode newNodeTest = reparseNodeTest(oldNodeTest);

        if (newNodeTest == null && nonvoid) {
            reportError(XQueryMessages.errorXQSE_MissingNodeTest_UI_);
        }

        nonvoid |= newNodeTest != null;

        step.setNodeTest(newNodeTest);

        return nonvoid;
    }

    /**
     * Reparse <tt>KindTest | NameTest</tt>
     */
    protected IASTNode reparseNodeTest(IASTNode expr) {
        if (sameRegionType(nameTestFilter)) {
            return reparseNameTest(expr);
        }

        // Must be a kindtest
        return reparseKindTest(expr);
    }

    /**
     * Reparse
     * <tt>DocumentTest | ElementTest | AttributeTest | SchemaElementTest | SchemaAttributeTest 
     *                  | PITest | CommentTest | TextTest | AnyKindTest</tt>
     * 
     * @return a kind test AST node or null if the current sdregion is not a kind test.
     */
    protected IASTNode reparseKindTest(IASTNode node) {
        if (sameRegionType(kindTestFilter)) {
            ASTKindTest test = asKindTest(node);
            test.setFirstStructuredDocumentRegion(currentSDRegion);
            nextSDRegion(); // the kind test
            return test;
        }

        // Not a kind test...
        return null;

    }

    /**
     * Reparse <tt>QName | Wildcard</tt>
     */
    protected IASTNode reparseNameTest(IASTNode node) {
        ASTNameTest test = asNameTest(node);

        test.setFirstStructuredDocumentRegion(currentSDRegion);
        nextSDRegion();

        return test;
    }

    /**
     * Reparse <tt>"document" "{" Expr "}"</tt>
     */
    protected IASTNode reparseCompDocConstructor(IASTNode node) {
        ASTCompDocConstructor constructor = asCompDocConstructor(node);
        return reparseCompTextDocComment(constructor);
    }

    /**
     * Reparse <tt>"comment" "{" Expr "}"</tt>
     */
    protected IASTNode reparseCompCommentConstructor(IASTNode node) {
        ASTCompCommentConstructor constructor = asCompCommentConstructor(node);
        return reparseCompTextDocComment(constructor);
    }

    /**
     * Reparse <tt>"text" "{" Expr "}"</tt>
     */
    protected IASTNode reparseCompTextConstructor(IASTNode node) {
        ASTCompTextConstructor constructor = asCompTextConstructor(node);
        return reparseCompTextDocComment(constructor);
    }

    /** Helper method reparsing text, doc or comment constructor */
    protected IASTNode reparseCompTextDocComment(IASTNode node) {
        nextSDRegion(); // 'text'/'comment'/'doc'

        IASTNode oldExpr = node.getChildASTNodeAt(0);
        IASTNode newExpr = reparseEnclosedExpr(oldExpr);
        node.setChildASTNodeAt(0, newExpr);

        return node;
    }

    /**
     * Reparse <tt>"attribute" (QName | ("{" Expr "}")) "{" Expr? "}"</tt>
     */
    protected IASTNode reparseCompAttrConstructor(IASTNode node) {
        ASTCompAttrConstructor constructor = asCompAttrConstructor(node);
        return reparseCompElemAttrPI(constructor, XQueryRegions.QNAME);
    }

    /**
     * Reparse <tt>element" (QName | ("{" Expr "}")) "{" ContentExpr? "}"</tt>
     */
    protected IASTNode reparseCompElementConstructor(IASTNode node) {
        ASTCompElemConstructor constructor = asCompElemConstructor(node);
        return reparseCompElemAttrPI(constructor, XQueryRegions.QNAME);
    }

    /**
     * Reparse <tt>"processing-instruction" (NCName | ("{" Expr "}")) "{" Expr? "}"</tt>
     */
    protected IASTNode reparseCompPIConstructor(IASTNode node) {
        ASTCompPIConstructor constructor = asCompPIConstructor(node);
        return reparseCompElemAttrPI(constructor, XQueryRegions.NCNAME);
    }

    /** Helper method reparsing elem/attr/pi expression */
    protected IASTNode reparseCompElemAttrPI(IASTNode node, String nameRegionType) {
        nextSDRegion(); // "processing-instruction"

        if (sameRegionType(nameRegionType)) {
            nextSDRegion(); // NCName
        } else if (sameRegionType(XQueryRegions.LCURLY)) {
            IASTNode oldExpr = node.getChildASTNodeAt(0);
            IASTNode newExpr = reparseEnclosedExpr(oldExpr);
            node.setChildASTNodeAt(0, newExpr);
        } else {
            reportError(XQueryMessages.errorXQSE_MissingPIName_UI_);
        }

        if (checkAndReport(XQueryRegions.LCURLY, XQueryMessages.errorXQSE_MissingLCurly_UI_)) {

            nextSDRegion(); // '{'

            if (!sameRegionType(XQueryRegions.RCURLY)) {
                // There is an expression

                IASTNode oldContentExpr = node.getChildASTNodeAt(0);
                IASTNode newContentExpr = reparseExpr(oldContentExpr);
                node.setChildASTNodeAt(0, newContentExpr);

                if (!sameRegionType(XQueryRegions.RCURLY)) {
                    reportError(XQueryMessages.errorXQSE_MissingRCurly_UI_);
                } else {
                    nextSDRegion(); // '}'
                }
            } else {
                nextSDRegion(); // '}'
            }
        }
        return node;
    }

    /**
     * Reparse <tt>"<?" PITarget (S DirPIContents)? "?>"</tt>
     */
    protected IASTNode reparseDirPIConstructor(IASTNode expr) {
        // TODO: 

        nextSDRegion(); // only one region for the PI
        return new ASTDirPI();
    }

    /**
     * Reparse <tt>"<!--" DirCommentContents "-->"</tt>
     */
    protected IASTNode reparseDirCommentConstructor(IASTNode expr) {
        // TODO
        nextSDRegion(); // only one region for the comment
        return new ASTDirComment();
    }

    /**
     * Reparse <tt>"<" QName DirAttributeList ("/>" | (">" DirElemContent* "</" QName S? ">"))</tt>
     */
    protected IASTNode reparseDirElemConstructor(IASTNode expr) {
        // TODO
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
     * Reparse <tt>(DirectConstructor | CDataSection | CommonContent | ElementContentChar )*</tt>
     */
    protected void reparseDirElemContentStar(ASTDirElement element) {
        // TODO
        int index = 0;
        while (currentSDRegion != null) {
            if (sameRegionType(XQueryRegions.XML_END_TAG_OPEN)) {
                element.removeChildASTNodesAfter(index);
                break;
            }

            if (sameRegionType(XQueryRegions.XML_CDATA)) {
                reparseCDataSection();
            } else if (sameRegionType(directConstructorFilter) || sameRegionType(commonContentFilter)) {
                IASTNode oldChild = element.getChildASTNodeAt(index);
                IASTNode newChild = sameRegionType(directConstructorFilter) ? reparseDirectConstructor(oldChild)
                        : reparseCommonContent(oldChild);

                if (newChild != null) {
                    element.setChildASTNodeAt(index, newChild);
                    index++;
                }
            } else {
                reparseElementContentChar();
            }

        }

    }

    /**
     * Reparse <tt>Char - [{}<&]</tt>
     */
    protected void reparseElementContentChar() {
        // TODO
        nextSDRegion(); // Checked by the tokenizer
    }

    /**
     * Reparse <tt>PredefinedEntityRef | CharRef | "{{" | "}}" | EnclosedExpr</tt>
     */
    protected IASTNode reparseCommonContent(IASTNode node) {
        // TODO
        if (sameRegionType(XQueryRegions.LCURLY)) {
            reparseEnclosedExpr(node);
        } else {
            nextSDRegion();
        }

        return null;
    }

    /**
     * Reparse <tt>DirElemConstructor | DirCommentConstructor | DirPIConstructor</tt>
     */
    protected IASTNode reparseDirectConstructor(IASTNode expr) {
        if (sameRegionType(XQueryRegions.XML_TAG_OPEN)) {
            return reparseDirElemConstructor(expr);
        }

        if (sameRegionType(XQueryRegions.XML_COMMENT)) {
            return reparseDirCommentConstructor(expr);
        }

        if (sameRegionType(XQueryRegions.XML_PI)) {
            return reparseDirPIConstructor(expr);
        }

        return null;
    }

    /**
     * Reparse <tt>"<![CDATA[" CDataSectionContents "]]>"</tt>
     */
    protected void reparseCDataSection() {
        // TODO
        nextSDRegion();
    }

    /**
     * Reparse <tt>(S (QName S? "=" S? DirAttributeValue)?)*</tt>
     */
    protected void reparseDirAttributeList(ASTDirElement element) {
        // TODO
        while (currentSDRegion != null) {
            if (!sameRegionType(XQueryRegions.XML_TAG_ATTRIBUTE_NAME)) {
                break;
            }

            String attrName = currentSDRegion.getText().trim();
            ASTDirAttribute attr = (ASTDirAttribute)element.getAttributeNode(attrName);
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
        // TODO
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
        // TODO
        if (sameRegionType(commonContentFilter)) {
            return reparseCommonContent(node);
        }

        nextSDRegion();
        return null;
    }

    /**
     * Reparse <tt>(EscapeQuot | QuotAttrValueContent)*</tt> or
     * <tt>(EscapeApos | AposAttrValueContent)*</tt>
     */
    protected void reparseDirAttributeValue(ASTDirAttribute attr, String escapeType) {
        // TODO
        int index = 0;
        while (currentSDRegion != null) {
            if (sameRegionType(XQueryRegions.XML_END_ATTR_VALUE)) {
                attr.removeChildASTNodesAfter(index);
                break;
            }

            if (sameRegionType(escapeType)) {
                nextSDRegion();
            } else {
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
     * Reparse <tt>"ordered" "{" Expr "}"</tt> and <tt>"unordered" "{" Expr "}"</tt>
     */
    protected IASTNode reparseOrderedUnordered(IASTNode expr) {
        nextSDRegion(); // "ordered" "{" or "unordered" "{"

        IASTNode innerExpr = reparseExpr(expr);

        if (checkAndReport(XQueryRegions.RCURLY, XQueryMessages.errorXQSE_MissingRCurly_UI_)) {
            nextSDRegion(); // '}'
        }

        return innerExpr;
    }

    /**
     * Reparse <tt>QName "(" (ExprSingle ("," ExprSingle)*)? ")"</tt>
     */
    protected IASTNode reparseFunctionCall(IASTNode expr) {
        // TODO
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
    protected IASTNode reparseContextItemExpr(IASTNode node) {
        ASTContextItem item = asContextItem(node);
        item.setFirstStructuredDocumentRegion(currentSDRegion);
        item.setLastStructuredDocumentRegion(currentSDRegion);

        nextSDRegion(); // .

        return item;
    }

    /**
     * Reparse <tt>"(" Expr? ")"</tt>
     */
    protected IASTNode reparseParentherizeExpr(IASTNode node) {
        ASTParentherized expr = asParentherized(node);
        expr.setFirstStructuredDocumentRegion(currentSDRegion);

        nextSDRegion(); // "("

        if (!sameRegionType(XQueryRegions.RPAR)) {
            IASTNode oldExpr = expr.getExpr();
            IASTNode newExpr = reparseExpr(oldExpr);
            expr.setExpr(newExpr);

            if (newExpr == null) {
                reportError(XQueryMessages.errorXQSE_MissingExprSingle_UI_);
                return expr;
            }
        }

        expr.setLastStructuredDocumentRegion(currentSDRegion);

        if (checkAndReport(XQueryRegions.RPAR, XQueryMessages.errorXQSE_MissingRPar_UI_)) {
            nextSDRegion(); // ")"
        }

        return expr;
    }

    /**
     * reparse <tt>NumericLiteral | StringLiteral</tt>
     */
    protected IASTNode reparseLiteral(IASTNode expr) {
        ASTLiteral literal = asLiteral(expr);

        literal.setFirstStructuredDocumentRegion(currentSDRegion);
        literal.setLastStructuredDocumentRegion(currentSDRegion);

        nextSDRegion(); // Literal
        return literal;
    }

    /**
     * reparse <tt>"$" VarName</tt>
     */
    protected IASTNode reparseVarRef(IASTNode expr) {
        ASTVarRef varRef;
        if (expr == null || expr.getType() != IASTNode.VARREF) {
            varRef = nodeFactory.newVarRef();
        } else {
            varRef = (ASTVarRef)expr;
        }

        varRef.setFirstStructuredDocumentRegion(currentSDRegion);
        varRef.setLastStructuredDocumentRegion(currentSDRegion);
        nextSDRegion(); // $ and VarName has been grouped together
        return varRef;
    }

    /**
     * Reparse <tt>("[" Expr "]")*</tt>
     */
    protected IASTNode reparsePredicateList(IASTNode expr) {
        nextSDRegion(); // [

        IASTNode predicate = reparseExpr(expr);

        if (checkAndReport(XQueryRegions.RSQUARE, XQueryMessages.errorXQSE_MissingRSquare_UI_)) {
            nextSDRegion(); // ']'
        }

        return predicate;
    }

    // Reparse operator helpers

    /**
     * Reparse operator following this grammar (Expr (op Expr)*)
     */
    protected IASTNode reparseOperatorStar(IASTNode expr, OperatorFilter operatorFilter, Continuation continuation) {
        IASTNode oldChild = getFirstOperand(expr, operatorFilter);
        IASTNode newChild = continuation.reparse(oldChild);

        if (newChild == null) {
            return null;
        }

        if (!sameRegionType(operatorFilter)) {
            // No operator=>just return the expression.
            return newChild;
        }

        int operatorType = getOperatorType();
        nextSDRegion(); // skip operator

        ASTOperator operator = asOperator(expr, operatorType);
        operator.setChildASTNodeAt(0, newChild);
        operator.setFirstStructuredDocumentRegion(newChild.getFirstStructuredDocumentRegion());

        int index = 1;
        do {
            oldChild = operator.getChildASTNodeAt(index);
            newChild = continuation.reparse(oldChild);
            operator.setChildASTNodeAt(index, newChild);

            if (newChild == null) {
                reportError(XQueryMessages.errorXQSE_MissingExprSingle_UI_);
                return operator;
            }

            if (!sameRegionType(operatorFilter)) {
                operator.removeChildASTNodesAfter(index);
                break;
            }

            nextSDRegion(); // skip operator
            index++;
        } while (currentSDRegion != null);

        operator.setLastStructuredDocumentRegion(newChild.getLastStructuredDocumentRegion());
        return operator;
    }

    /** Reparse operator following this grammar (Expr (op Expr)?) */
    protected IASTNode reparseOperatorOptional(IASTNode expr, OperatorFilter operatorFilter, Continuation continuation) {
        IASTNode oldChild = getFirstOperand(expr, operatorFilter);
        IASTNode newChild = continuation.reparse(oldChild);

        if (!sameRegionType(operatorFilter)) {
            return newChild;
        }

        int operatorType = getOperatorType();
        nextSDRegion(); // skip operator

        ASTOperator operator = asOperator(expr, operatorType);
        operator.setChildASTNodeAt(0, newChild);

        oldChild = operator.getChildASTNodeAt(1);
        newChild = continuation.reparse(oldChild);
        operator.setChildASTNodeAt(1, newChild);

        operator.removeChildASTNodesAfter(1);
        return operator;
    }

    // Helpers

    /** Gets AST node as {@link ASTApply} */
    protected ASTApply asApply(IASTNode node) {
        if (node != null && node.getType() == IASTNode.APPLY) {
            return (ASTApply)node;
        }

        return nodeFactory.newApply();
    }

    /** Gets AST node as {@link ASTLiteral} */
    protected ASTLiteral asLiteral(IASTNode node) {
        if (node != null && node.getType() == IASTNode.LITERAL) {
            return (ASTLiteral)node;
        }

        return nodeFactory.newLiteral();
    }

    /** Gets AST node as {@link ASTIf} */
    protected ASTStep asStep(IASTNode node) {
        if (node != null && node.getType() == IASTNode.STEP) {
            return (ASTStep)node;
        }

        return nodeFactory.newStep();
    }

    /** Gets AST node as {@link ASTIf} */
    protected ASTIf asIf(IASTNode node) {
        if (node != null && node.getType() == IASTNode.IF) {
            return (ASTIf)node;
        }

        return nodeFactory.newIf();
    }

    /** Gets AST node as {@link ASTValidate} */
    protected ASTValidate asValidate(IASTNode node) {
        if (node != null && node.getType() == IASTNode.VALIDATE) {
            return (ASTValidate)node;
        }

        return nodeFactory.newValidate();
    }

    /** Gets AST node as {@link ASTExtension} */
    protected ASTExtension asExtension(IASTNode node) {
        if (node != null && node.getType() == IASTNode.EXTENSION) {
            return (ASTExtension)node;
        }

        return nodeFactory.newExtension();
    }

    /** Gets AST node as {@link ASTCompPIConstructor} */
    protected ASTCompPIConstructor asCompPIConstructor(IASTNode node) {
        if (node != null && node.getType() == IASTNode.COMPPICONSTRUCTOR) {
            return (ASTCompPIConstructor)node;
        }

        return nodeFactory.newCompPIConstructor();
    }

    /** Gets AST node as {@link ASTCompDocConstructor} */
    protected ASTCompDocConstructor asCompDocConstructor(IASTNode node) {

        if (node != null && node.getType() == IASTNode.COMPDOCCONSTRUCTOR) {
            return (ASTCompDocConstructor)node;
        }

        return nodeFactory.newCompDocConstructor();
    }

    /** Gets AST node as {@link ASTCompElemConstructor} */
    protected ASTCompElemConstructor asCompElemConstructor(IASTNode node) {
        if (node != null && node.getType() == IASTNode.COMPPICONSTRUCTOR) {
            return (ASTCompElemConstructor)node;
        }

        return nodeFactory.newCompElemConstructor();
    }

    /** Gets AST node as {@link ASTCompTextConstructor} */
    protected ASTCompTextConstructor asCompTextConstructor(IASTNode node) {
        if (node != null && node.getType() == IASTNode.COMPTEXTCONSTRUCTOR) {
            return (ASTCompTextConstructor)node;
        }

        return nodeFactory.newCompTextConstructor();
    }

    /** Gets AST node as {@link ASTCompCommentConstructor} */
    protected ASTCompCommentConstructor asCompCommentConstructor(IASTNode node) {
        if (node != null && node.getType() == IASTNode.COMPTEXTCONSTRUCTOR) {
            return (ASTCompCommentConstructor)node;
        }

        return nodeFactory.newCompCommentConstructor();
    }

    /** Gets AST node as {@link ASTCompAttrConstructor} */
    protected ASTCompAttrConstructor asCompAttrConstructor(IASTNode node) {
        if (node != null && node.getType() == IASTNode.COMPATTRCONSTRUCTOR) {
            return (ASTCompAttrConstructor)node;
        }

        return nodeFactory.newCompAttrConstructor();
    }

    /** Gets AST node as {@link ASTSingleType} */
    protected ASTSingleType asSingleType(IASTNode node) {
        if (node != null && node.getType() == IASTNode.SINGLETYPE) {
            return (ASTSingleType)node;
        }

        return nodeFactory.newSingleType();
    }

    /** Gets AST node as {@link ASTIf} */
    protected ASTContextItem asContextItem(IASTNode node) {
        if (node != null && node.getType() == IASTNode.CONTEXTITEM) {
            return (ASTContextItem)node;
        }

        return nodeFactory.newContextItem();
    }

    /** Gets AST node as {@link ASTIf} */
    protected ASTParentherized asParentherized(IASTNode node) {
        if (node != null && node.getType() == IASTNode.PARENTHERIZED) {
            return (ASTParentherized)node;
        }

        return nodeFactory.newParentherized();
    }

    /** Gets AST node as {@link ASTNameTest} */
    protected ASTNameTest asNameTest(IASTNode node) {
        if (node != null && node.getType() == IASTNode.NAMETEST) {
            return (ASTNameTest)node;
        }

        return nodeFactory.newNameTest();
    }

    /** Gets AST node as {@link ASTKindTest} */
    protected ASTKindTest asKindTest(IASTNode node) {
        if (node != null && node.getType() == IASTNode.KINDTEST) {
            return (ASTKindTest)node;
        }

        return nodeFactory.newKindTest();
    }

    /** Gets AST node as {@link ASTPath} */
    protected ASTPath asPath(IASTNode node) {
        if (node != null && node.getType() == IASTNode.PATH) {
            return (ASTPath)node;
        }

        return nodeFactory.newPath();
    }

    /** Gets AST node as {@link ASTInsert} */
    protected ASTInsert asInsertExpr(IASTNode node) {
        if (node != null && node.getType() == IASTNode.XUINSERT) {
            return (ASTInsert)node;
        }

        return nodeFactory.newInsertExpr();
    }

    /** Gets AST node as {@link ASTDelete} */
    protected ASTDelete asDeleteExpr(IASTNode node) {
        if (node != null && node.getType() == IASTNode.XUDELETE) {
            return (ASTDelete)node;
        }

        return nodeFactory.newDeleteExpr();
    }

    /** Gets AST node as {@link ASTReplace} */
    protected ASTReplace asReplaceExpr(IASTNode node) {
        if (node != null && node.getType() == IASTNode.XUREPLACE) {
            return (ASTReplace)node;
        }

        return nodeFactory.newReplaceExpr();
    }

    /** Gets AST node as {@link ASTRename} */
    protected ASTRename asRenameExpr(IASTNode node) {
        if (node != null && node.getType() == IASTNode.XURENAME) {
            return (ASTRename)node;
        }

        return nodeFactory.newRenameExpr();
    }

    /** Gets AST node as {@link ASTTransform} */
    protected ASTTransform asTransformExpr(IASTNode node) {
        if (node != null && node.getType() == IASTNode.XUTRANSFORM) {
            return (ASTTransform)node;
        }

        return nodeFactory.newTransformExpr();
    }

    /** Gets AST node as {@link ASTNamespaceDecl} */
    protected ASTNamespaceDecl asNamespaceDecl(IASTNode node) {
        if (node != null && node.getType() == IASTNode.NAMESPACEDECL) {
            return (ASTNamespaceDecl)node;
        }

        return nodeFactory.newNamespaceDecl();
    }

    /** Gets AST node as {@link ASTQuantified} */
    protected ASTQuantified asQuantified(IASTNode node) {
        if (node != null && node.getType() == IASTNode.IF) {
            return (ASTQuantified)node;
        }

        return nodeFactory.newQuantified();
    }

    /** Gets AST node as {@link ASTTypeswitch} */
    protected ASTTypeswitch asTypeswitch(IASTNode node) {
        if (node != null && node.getType() == IASTNode.TYPESWITCH) {
            return (ASTTypeswitch)node;
        }

        return nodeFactory.newTypeswitch();
    }

    /** Gets AST node as {@link ASTSequenceType} */
    protected ASTSequenceType asSequenceType(IASTNode node) {
        if (node != null && node.getType() == IASTNode.SEQUENCETYPE) {
            return (ASTSequenceType)node;
        }

        return nodeFactory.newSequenceType();
    }

    /** Gets AST node as {@link ASTFunctionCall} */
    protected ASTFunctionCall asFunctionCall(IASTNode node) {
        if (node != null && node.getType() == IASTNode.FUNCTIONCALL) {
            return (ASTFunctionCall)node;
        }

        return nodeFactory.newFunctionCall();
    }

    /** Gets AST node as {@link ASTFLWOR} */
    protected ASTFLWOR asFLWOR(IASTNode expr) {
        if (expr != null && expr.getType() == IASTNode.FLWOR) {
            return (ASTFLWOR)expr;
        }

        return nodeFactory.newFLOWR();
    }

    /** Gets AST node as {@link ASTBindingClause} */
    protected ASTBindingClause asBindingClause(IASTNode node) {
        if (node instanceof ASTBindingClause) {
            return (ASTBindingClause)node;
        }

        return nodeFactory.newBindingClause();
    }

    /** Gets AST node as {@link ASTExprSingleClause} */
    protected ASTExprSingleClause asExprSingleClause(IASTNode node) {
        if (node instanceof ASTExprSingleClause) {
            return (ASTExprSingleClause)node;
        }

        return nodeFactory.newExprSingleClause();
    }

    /** Gets AST node as {@link ASTOrderByClause} */
    protected ASTOrderByClause asOrderByClause(IASTNode node) {
        if (node instanceof ASTOrderByClause) {
            return (ASTOrderByClause)node;
        }

        return nodeFactory.newOrderByClause();
    }

    /** Gets AST node as {@link ASTDirElement} */
    protected ASTDirElement asDirElement(IASTNode node) {
        if (node != null && node.getType() == IASTNode.DIRELEMENT) {
            return (ASTDirElement)node;
        }

        return nodeFactory.newDirElement();
    }

    /** Gets AST node as {@link ASTFunctionDecl} */
    protected ASTFunctionDecl asFunctionDecl(IASTNode node) {
        if (node != null && node.getType() == IASTNode.FUNCTIONDECL) {
            return (ASTFunctionDecl)node;
        }

        return nodeFactory.newFunctionDecl();
    }

    /** Gets AST node as {@link ASTVarDecl} */
    protected ASTVarDecl asVarDecl(IASTNode node) {
        if (node != null && node.getType() == IASTNode.VARDECL) {
            return (ASTVarDecl)node;
        }

        return nodeFactory.newVariableDecl();
    }

    /**
     * Test structured document region type against given type. Ignore separators (white spaces and
     * comments)
     */
    protected boolean sameRegionType(String type) {
        skipWhitespace();
        return ModelHelper.sameRegionType(currentSDRegion, type);
    }

    /**
     * Test structured document region type against given type filter. Ignore separators (white
     * spaces and comments)
     */
    protected boolean sameRegionType(OperatorFilter filter) {
        skipWhitespace();
        return currentSDRegion != null && filter.accept(currentSDRegion.getType());
    }

    /**
     * Test structured document region type against given region type filter. Ignore separators
     * (white spaces and comments)
     */
    protected boolean sameRegionType(RegionFilter filter) {
        skipWhitespace();
        return currentSDRegion != null && filter.accept(currentSDRegion.getType());
    }

    /** Skip white spaces and XQuery comments */
    protected void skipWhitespace() {
        currentSDRegion = ModelHelper.skipWhitespace(currentSDRegion);
    }

    /**
     * Test if the current structured document region overlaps the changed text region
     */
    final protected boolean overlap() {
        return currentSDRegion != null
                && !(currentSDRegion.getStart() > offset + length || currentSDRegion.getEnd() < offset);
    }

    /**
     * Move to the next structured document region. Ignore whitespaces
     * 
     * @return true if move successfully to a non-null region.
     */
    protected boolean nextSDRegion() {
        if (currentSDRegion != null) {
            previousSDRegion = currentSDRegion;
            currentSDRegion = (XQueryStructuredDocumentRegion)currentSDRegion.getNext();

            skipWhitespace();
        }

        return currentSDRegion != null;
    }

    /** Move to the structured document region of the given type */
    protected void moveToSDRegion(String type) {
        while (currentSDRegion != null && currentSDRegion.getType().equals(type)) {
            nextSDRegion();
        }
    }

    /**
     * Check the current region (ignoring white spaces) is of a given type. If not report problem
     * 
     * @return true if current region of the given type, otherwise false.
     */
    protected boolean checkAndReport(String type, String text) {
        skipWhitespace();
        if (!sameRegionType(type)) {
            reportError(text);

            return false;
        }

        return true;
    }

    /**
     * @param languageXqueryUpdate
     * @param first
     * @param last
     */
    final protected void checkAndReportLanguage(int language, IStructuredDocumentRegion first,
            IStructuredDocumentRegion last, String text) {
        if ((language & this.language) == 0) {
            this.model.reportError(first, last, text);
        }
    }

    /**
     * Report problem. Attach problem to current sd region or previous one, whichever is non-null
     * 
     * @return true if current region of the given type, otherwise false.
     */
    protected void reportError(String text) {
        // Report error
        if (currentSDRegion == null) {
            if (previousSDRegion == null) {
                model.reportError(text);
            } else {
                model.reportError(previousSDRegion, text, true);
            }
        } else {
            model.reportError(currentSDRegion, text, false);
        }
    }

    /**
     * Gets AST operator
     * 
     * @param expr
     * @return
     */
    protected ASTOperator asOperator(IASTNode expr) {
        if (expr != null && expr.getType() == IASTNode.OPERATOR) {
            return (ASTOperator)expr;
        }

        return new ASTOperator();

    }

    /**
     * Gets AST operator of the given type
     * 
     * @param expr
     * @return
     */
    protected ASTOperator asOperator(IASTNode expr, int operatorType) {
        ASTOperator operator = asOperator(expr);
        if (operator.getOperatorType() == operatorType) {
            return operator;
        }
        return new ASTOperator(operatorType);
    }

    /**
     * Gets first operator operand, only if the given expression matches the operator type
     * 
     * @param expr
     * @param sequence
     * @return
     */
    protected IASTNode getFirstOperand(IASTNode expr, OperatorFilter filter) {
        if (expr != null && expr.getType() == IASTNode.OPERATOR) {
            final ASTOperator operator = (ASTOperator)expr;
            if (filter.accept(operator.getType()) && operator.getChildASTNodesCount() >= 1) {
                return operator.getChildASTNodeAt(0);
            }
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
            final ASTOperator operator = (ASTOperator)expr;
            if (operator.getChildASTNodesCount() >= 1) {
                return operator.getChildASTNodeAt(0);
            }
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
        if (regionType == XQueryRegions.OP_OR) {
            return ASTOperator.OP_OR;
        } else if (regionType == XQueryRegions.OP_AND) {
            return ASTOperator.OP_AND;
        } else if (regionType == XQueryRegions.COMMA) {
            return ASTOperator.OP_COMMA;
        } else if (regionType == XQueryRegions.OP_TO) {
            return ASTOperator.OP_TO;
        } else {
            return -1;
        }
    }

    /**
     * Gets the ith text region in the current structured document region, ignoring XQuery comments.
     * 
     * @param i
     * @return
     */
    protected ITextRegion getTextRegion(int index) {
        if (currentSDRegion == null) {
            return null;
        }
        final ITextRegionList list = currentSDRegion.getRegions();
        if (index >= list.size()) {
            return null;
        }

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getType() != XQueryRegions.XQUERY_COMMENT) {
                if (index == 0) {
                    return list.get(i);
                }

                index--;
            }
        }

        return null;
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
            for (int i = 0; i < regionType.length; i++) {
                if (type.equals(regionType[i])) {
                    return true;
                }
            }
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
            for (int i = 0; i < operatorType.length; i++) {
                if (type == operatorType[i]) {
                    return true;
                }
            }
            return false;
        }

        public boolean accept(String type) {
            for (int i = 0; i < regionType.length; i++) {
                if (type.equals(regionType[i])) {
                    return true;
                }
            }
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

    /** Reparse continuation for RelativePathExpr */
    protected class RelativePathContinuation extends Continuation {
        IASTNode reparse(IASTNode expr) {
            return reparseStepExpr(expr);
        }
    }

}
