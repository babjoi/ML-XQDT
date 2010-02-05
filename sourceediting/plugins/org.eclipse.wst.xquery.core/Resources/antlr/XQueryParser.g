/*
Rule names
==========

All parser grammar rules are prefixed with 'px_' in order to comply with the
ANTLR naming scheme for grammar rules. The 'x' letter in the prefix is an
optional field to indicate the status of the grammar rule compared to the
original EBNF production. If missing, the rule is the same as in EBNF. Other
leters are:
m - The grammar rule is a modified version of the original EBNF production
g - The grammar rule is a helper rule needed to achieve different behaviour

The lexer rules were prefixed with 'L_'.

*/

parser grammar XQueryParser;

options {
output=AST;
ASTLabelType=XQDTCommonTree;
TokenLabelType=CommonToken;
superClass='XQDTParser';
tokenVocab=XQueryLexer;
}

tokens {
// define the tokens from side-lexers (String and XML)
// in order to avoid token ID overlapping
L_QuotStringLiteralChar;
L_AposStringLiteralChar;
L_AnyChar;
L_CDataSection;

// Imaginary AST tree nodes
LibraryModule;
MainModule;
VersionDecl;
VersionDeclEncoding;          // container
VersionDeclVersion;           // container
ModuleDecl;
Prolog;
DefaultNamespaceDecls;        // container
DefaultNamespaceDecl;
Setters;                      // container
Setter;
NamespaceDecls;               // container
NamespaceDecl;
Imports;                      // container
SchemaImport;
SchemaPrefix;
NamespaceName;                // container
DefaultElementNamespace;
AtHints;                      // container
ModuleImport;
BaseURIDecl;
OrderedDecls;                 // container
VarDecl;
VarType;                      // container
VarValue;
VarDefaultValue;
VarConstantDecl;              // container
VarVariableDecl;              // container
FunctionDecl;
ParamList;                    // container
ReturnType;                   // container
OptionDecl;
TypeDeclaration;
Param;
EnclosedExpr;
QueryBody;

UnaryExpr;

DirElemConstructor;
DirAttributeList;
DirAttributeValue;
DirElemContent;
CommonContent;

SequenceType;
EmptySequenceTest;            // container
KindTest;
ItemTest;                     // container
AtomicType;

StringLiteral;
ElementContentChar;
AttributeValueChar;
QName;


Block;
BlockDecls;
BlockVarDecl;

// Mark Logic
BinaryTest;
}

@parser::header {
/*******************************************************************************
 * Copyright (c) 2008, 2009 28msec Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Gabriel Petrovay (28msec) - initial API and implementation
 *     Sam Neth (Mark Logic)
 *******************************************************************************/
package org.eclipse.wst.xquery.internal.core.parser.antlr;
}

@parser::members {
// dummy list for warning elimination
List<Stack<Object>> dummy1 = new ArrayList<Stack<Object>>();
Map<Object, Object> dummy2 = new HashMap<Object, Object>();
}


// ****************
// EBNF Productions
// ****************

//[1]
p_Module
        : vd=p_VersionDecl?
            (
                lm=p_LibraryModule[$vd.tree]    -> {$lm.tree}
            | mm=p_MainModule[$vd.tree]         -> {$mm.tree}
            ) EOF
        ;

//[2]
p_VersionDecl
        : k+=XQUERY ((k+=ENCODING enc=p_StringLiteral) | 
        			 (k+=VERSION ver=p_StringLiteral {setLanguageVersion(((XQDTCommonTree)ver.getTree()).getChild(0).getText());} (k+=ENCODING enc=p_StringLiteral)?)) SEMICOLON {ak($k);}
                -> ^(VersionDecl ^(VersionDeclVersion $ver?) ^(VersionDeclEncoding $enc?))
        ;

//[3]
p_MainModule [CommonTree vd]
        : pm_Prolog p_QueryBody
                -> ^(MainModule {$vd} pm_Prolog p_QueryBody)
        ;

//[3]
p_LibraryModule [CommonTree vd]
        : p_ModuleDecl pm_Prolog
                -> ^(LibraryModule {$vd} p_ModuleDecl pm_Prolog)
        ;

//[5]
p_ModuleDecl
        : k+=MODULE k+=NAMESPACE p_NCName EQUAL p_StringLiteral SEMICOLON {ak($k);}
                ->  ^(ModuleDecl p_NCName p_StringLiteral)
        ;

//[6]
// The SEMICOLON was pushed back in all the Prolog declarations
// in order to be contained by the declaration trees.
pm_Prolog
        : ((dnd+=pm_DefaultNamespaceDecl | s+=p_Setter | nd+=pm_NamespaceDecl | i+=p_Import))* od=pg_OrderedDecl
                ->  ^(Prolog
                                ^(DefaultNamespaceDecls $dnd*)
                                ^(Setters $s*)
                                ^(NamespaceDecls $nd*)
                                ^(Imports $i*)
                                ^(OrderedDecls $od*)
                        )
        ;

// *************************************************
// This is not in the EBNF grammar.
// A special node is needed to keep track of the prolog
// declarations for which the order is important.
pg_OrderedDecl
        : (pm_VarDecl | pm_ContextItemDecl | pm_FunctionDecl | pm_OptionDecl
        | {lc(ZORBA)}?=> p_CollectionDecl
        | {lc(ZORBA)}?=> p_IndexDecl
        | {lc(ZORBA)}?=> p_ICDecl)*
        ;
// *************************************************

//[7]
p_Setter
        : pm_BoundarySpaceDecl
        | pm_DefaultCollationDecl
        | pm_BaseURIDecl
        | pm_ConstructionDecl
        | pm_OrderingModeDecl
        | pm_EmptyOrderDecl
        | {lc(XQU)}?=> pm_RevalidationDecl
        | pm_CopyNamespacesDecl
        ;

//[8]
p_Import
        : pm_SchemaImport | pm_ModuleImport
        ;

//[9]
//SEMICOLON

//[10]
pm_NamespaceDecl
        : k+=DECLARE k+=NAMESPACE nn=p_NCName EQUAL us=p_StringLiteral SEMICOLON {ak($k);} 
                -> ^(NamespaceDecl $nn $us)
        ;

//[11]
pm_BoundarySpaceDecl    
        :   k+=DECLARE k+=BOUNDARY_SPACE (k+=PRESERVE | k+=STRIP) SEMICOLON {ak($k);}
        ;

//[12]
pm_DefaultNamespaceDecl
        : k+=DECLARE k+=DEFAULT (k+=ELEMENT | k+=FUNCTION) k+=NAMESPACE p_StringLiteral SEMICOLON {ak($k);}
        ;

//[13]
pm_OptionDecl
        :   k+=DECLARE k+=OPTION p_QName p_StringLiteral SEMICOLON {ak($k);}
        ;

//[14]
pm_OrderingModeDecl
        :   k+=DECLARE k+=ORDERING (k+=ORDERED | k+=UNORDERED) SEMICOLON {ak($k);}
        ;

//[15]
pm_EmptyOrderDecl
        :   k+=DECLARE k+=DEFAULT k+=ORDER k+=EMPTY (k+=GREATEST | k+=LEAST) SEMICOLON {ak($k);}
        ;

//[16]
pm_CopyNamespacesDecl
        :   k+=DECLARE k+=COPY_NAMESPACES p_PreserveMode COMMA p_InheritMode SEMICOLON {ak($k);}
        ;

//[17]
p_DecimalFormatDecl
        :   k+=DECLARE ((DECIMAL_FORMAT p_QName) | (DEFAULT DECIMAL_FORMAT)) (p_DFPropertyName) EQ p_StringLiteral SEMICOLON {ak($k);}
        ;

//[18]
p_DFPropertyName
        : k=DECIMAL_SEPARATOR | k=GROUPING_SEPARATOR | k=INFINITY | k=MINUS_SIGN | k=NAN | k=PERCENT | k=PER_MILLE | k=ZERO_DIGIT | k=DIGIT | k=PATTERN_SEPARATOR {ak($k);}
        ;

//[19]
p_PreserveMode
        :   (k+=PRESERVE | k+=NO_PRESERVE) {ak($k);}
        ;

//[20]
p_InheritMode
        :   (k+=INHERIT | k+=NO_INHERIT) {ak($k);}
        ;
        
//[21]
pm_DefaultCollationDecl
        :   k+=DECLARE k+=DEFAULT k+=COLLATION p_StringLiteral SEMICOLON {ak($k);}
        ;
        
//[22]
pm_BaseURIDecl
        :   k+=DECLARE k+=BASE_URI sl=p_StringLiteral SEMICOLON {ak($k);}
                -> ^(BaseURIDecl $sl)
        ;

//[23]
pm_SchemaImport
        : k+=IMPORT k+=SCHEMA sp=p_SchemaPrefix? us=p_StringLiteral (k+=AT ah+=p_StringLiteral (COMMA ah+=p_StringLiteral)*)? SEMICOLON {ak($k);}
                -> ^(SchemaImport ^(SchemaPrefix $sp?) $us ^(AtHints $ah*))
        ;

//[24]
p_SchemaPrefix 
        : k+=NAMESPACE nn=p_NCName EQUAL {ak($k);}
                -> ^(NamespaceName $nn)
        | k+=DEFAULT k+=ELEMENT k+=NAMESPACE {ak($k);}
                -> DefaultElementNamespace
        ;

//[25]
pm_ModuleImport
        : k+=IMPORT k+=MODULE (k+=NAMESPACE nn=p_NCName EQUAL)? us=p_StringLiteral (k+=AT ah+=p_StringLiteral (COMMA ah+=p_StringLiteral)*)? SEMICOLON {ak($k);}
                -> ^(ModuleImport ^(NamespaceName $nn?) $us ^(AtHints $ah*))
        ;

//[26]
pm_VarDecl
        : k+=DECLARE p_PrivateOption vdt=pg_VarDeclType DOLLAR qn=p_QName td=p_TypeDeclaration? ((BIND es=p_ExprSingle) | (k+=EXTERNAL (BIND des=p_ExprSingle)?)) SEMICOLON {ak($k);}
                -> ^(VarDecl $vdt $qn ^(VarType $td?) ^(VarValue $es? ^(VarDefaultValue $des?)))
        ;

// *************************************************
// This is not in the EBNF grammar.
// This is needed to decide between the CONSTANT and the 
// VARIABLE keywords in the Scripting 1.0 specification
pg_VarDeclType
        : {lc(XQS)}?=> ku=CONSTANT {ak($ku);}
                -> VarConstantDecl
        | kv=VARIABLE {ak($kv);}
                -> VarVariableDecl
        ;
// *************************************************

//[27]
//VarValue

//[28]
//VarDefaultValue

//[29]
pm_ContextItemDecl
        :   k+=DECLARE k+=CONTEXT k+=ITEM (k+=AS p_ItemType)? ((BIND p_ExprSingle) | (k+=EXTERNAL (BIND p_ExprSingle)?)) SEMICOLON {ak($k);}
        ;

//[30]
//BindingExpression

//[31]
pm_ConstructionDecl
        :   k+=DECLARE k+=CONSTRUCTION (k+=STRIP | k+=PRESERVE) SEMICOLON {ak($k);}
        ;

//?????????????????????????
//("deterministic" | "nondeterministic")?
//[32]
pm_FunctionDecl
        :   {lc(XQS)}?=> k+=DECLARE k+=SEQUENTIAL k+=FUNCTION qn=p_QName LPAREN pl=p_ParamList? RPAREN td=p_TypeDeclaration? (b=p_Block | k+=EXTERNAL) SEMICOLON {ak($k);}
                -> ^(FunctionDecl $qn ^(ParamList $pl?) ^(ReturnType $td?) $b?)
        |   k+=DECLARE p_PrivateOption p_FunctionType k+=FUNCTION qn=p_QName LPAREN pl=p_ParamList? RPAREN td=p_TypeDeclaration? (ee=p_EnclosedExpr | k+=EXTERNAL) SEMICOLON {ak($k);}
                -> ^(FunctionDecl $qn ^(ParamList $pl?) ^(ReturnType $td?) $ee?)
        ;

// *************************************************
// This is not in the EBNF grammar.
// This is needed to allow the UPDATING (Update 1.0)
// and SIMPLE keywords (Scripting 1.0)
p_FunctionType
        : {lc(XQU)}?=> ku=UPDATING {ak($ku);}
        | {lc(XQS)}?=> ks=SIMPLE {ak($ks);}
        | /* nothing */
        ;
// *************************************************
p_PrivateOption
        : {lc(MLS)}?=> kv=PRIVATE {ak($kv);}
        | /* nothing */
        ;

//[33]
//FunctionBody

//[34]
p_ParamList
        : p+=p_Param (COMMA p+=p_Param)*
                -> $p+
        ;
        
//[35]
p_Param
        : DOLLAR qn=p_QName td=p_TypeDeclaration?
                -> ^(Param $qn $td?)
        ;

//[36]
p_EnclosedExpr
        : LBRACKET pm_Expr RBRACKET
                -> ^(EnclosedExpr pm_Expr)
        ;

//[37]
p_QueryBody
        : pm_Expr
                -> ^(QueryBody pm_Expr)
        ;

//[38]
//[31] Scripting 1.0
pm_Expr
        : p_ConcatExpr
          ({lc(XQS)}?=> (SEMICOLON pm_ApplyExpr*)? | /* nothing */)
          ({lc(MLS)}?=> (SEMICOLON p_ConcatExpr)+ | /* nothing */)
        ;

//[39]
//[32] Update 1.0
p_ExprSingle
        : p_FLWORExpr
        | p_QuantifiedExpr
        | p_TypeswitchExpr
        | p_IfExpr
        | p_TryCatchExpr
        | {lc(ZORBA)}?=> p_EvalExpr
        | {lc(XQU)}?=> p_InsertExpr
        | {lc(XQU)}?=> p_DeleteExpr
        | {lc(XQU)}?=> p_RenameExpr
        | {lc(XQU)}?=> p_ReplaceExpr
        | {lc(XQU)}?=> p_TransformExpr
        | {lc(XQS)}?=> p_BlockExpr
        | {lc(XQS)}?=> p_AssignmentExpr
        | {lc(XQS)}?=> p_ExitExpr
        | {lc(XQS)}?=> p_WhileExpr
        | p_OrExpr
        ;

//[40]
p_FLWORExpr
        : p_InitialClause p_IntermediateClause* p_ReturnClause
        ;

//[41]
p_InitialClause
        : p_ForClause | p_LetClause | p_WindowClause
        ;

//[42]
p_IntermediateClause
        : p_InitialClause | p_WhereClause | p_GroupByClause | p_OrderByClause | p_CountClause
        ;

//[43]
p_ForClause
        : k+=FOR DOLLAR p_VarName p_TypeDeclaration? p_PositionalVar? k+=IN p_ExprSingle (COMMA p_QName p_TypeDeclaration? p_PositionalVar? k+=IN p_ExprSingle)* {ak($k);}
        ;

//[44]
p_PositionalVar
        : ka=AT {ak($ka);} DOLLAR p_VarName
        ;

//[45]
p_LetClause
        : kl=LET {ak($kl);} DOLLAR p_VarName p_TypeDeclaration? BIND p_ExprSingle (COMMA DOLLAR p_VarName p_TypeDeclaration? BIND p_ExprSingle)*
        ;

//[46]
p_WindowClause
        : kf=FOR {ak($kf);} (p_TumblingWindowClause | p_SlidingWindowClause)
        ;
        
//[47]
p_TumblingWindowClause
        : k+=TUMBLING k+=WINDOW DOLLAR p_VarName p_TypeDeclaration? IN p_ExprSingle p_WindowStartCondition p_WindowEndCondition? {ak($k);}
        ;

//[48]
p_SlidingWindowClause
        : k+=SLIDING k+=WINDOW DOLLAR p_VarName p_TypeDeclaration? IN p_ExprSingle p_WindowStartCondition p_WindowEndCondition? {ak($k);}
        ;

//[49]
p_WindowStartCondition
        : k+=START p_WindowVars k+=WHEN p_ExprSingle {ak($k);}
        ;

//[50]
p_WindowEndCondition
        : k+=ONLY? k+=END p_WindowVars k+=WHEN p_ExprSingle {ak($k);}
        ;

//[51]
p_WindowVars
        : (DOLLAR p_QName)? p_PositionalVar? (k+=PREVIOUS DOLLAR p_QName)? (k+=NEXT DOLLAR p_QName)? {ak($k);}
        ;

//[52]
//CurrentItem

//[53]
//PreviousItem

//[54]
//NextItem

//[55]
p_CountClause
        : kc+=COUNT {ak($kc);} DOLLAR p_VarName
        ;
        
//[56]
p_WhereClause
        : kw+=WHERE {ak($kw);} p_ExprSingle
        ;

//[57]
p_GroupByClause
        :   k+=GROUP k+=BY p_GroupingSpecList {ak($k);}
        ;

//[58]
p_GroupingSpecList
        :   p_GroupingSpec (COMMA p_GroupingSpec)*
        ;

//[59]
p_GroupingSpec
        :   DOLLAR p_VarName (kc=COLLATION p_StringLiteral {ak($kc);})?
        ;

//[60]
p_OrderByClause
        :   ((k+=ORDER k+=BY) | (k+=STABLE k+=ORDER k+=BY)) p_OrderSpecList {ak($k);}
        ;

//[61]
p_OrderSpecList
        :   p_OrderSpec (COMMA p_OrderSpec)*
        ;

//[62]
p_OrderSpec
        :   p_ExprSingle p_OrderModifier
        ;

//[63]
p_OrderModifier
        : (ka=ASCENDING | k+=DESCENDING)? (k+=EMPTY (k+=GREATEST | k+=LEAST))? (k+=COLLATION p_StringLiteral)? {ak($k);}
        ;

//[64]
p_ReturnClause
        : kr=RETURN {ak($kr);} p_ExprSingle
        ;

//[65]
p_QuantifiedExpr
        :   (k+=SOME | k+=EVERY) DOLLAR p_VarName p_TypeDeclaration? k+=IN p_ExprSingle (COMMA DOLLAR p_QName p_TypeDeclaration? k+=IN p_ExprSingle)* k+=SATISFIES p_ExprSingle {ak($k);}
        ;

//[66]
p_TypeswitchExpr
        : k+=TYPESWITCH LPAREN pm_Expr RPAREN p_CaseClause+ k+=DEFAULT (DOLLAR p_VarName)? k+=RETURN p_ExprSingle {ak($k);}
        ;

//[67]
p_CaseClause
        : k+=CASE (DOLLAR p_VarName k+=AS)? p_SequenceType k+=RETURN p_ExprSingle {ak($k);}
        ;

//[68]
p_IfExpr
        : k+=IF LPAREN pm_Expr RPAREN k+=THEN p_ExprSingle k+=ELSE p_ExprSingle {ak($k);}
        ;

//[69]
p_OrExpr
        : p_AndExpr ( k=OR {ak($k);} p_AndExpr )*
        ;

//[70]
p_AndExpr
        :   p_ComparisonExpr ( k=AND {ak($k);} p_ComparisonExpr )*
        ;

//[71]
p_ComparisonExpr
        :   p_RangeExpr ( (p_ValueComp | p_GeneralComp | p_NodeComp) p_RangeExpr )?
        ;

//[72]
p_RangeExpr
        :   p_AdditiveExpr ( k=TO {ak($k);} p_AdditiveExpr )?
        ;

//[73]
p_AdditiveExpr
        :   p_MultiplicativeExpr ( (PLUS | MINUS) p_MultiplicativeExpr )*
        ;

//[74]
p_MultiplicativeExpr
        :   p_UnionExpr ( (STAR | kd=DIV {ak($kd);} | ki=IDIV {ak($ki);} | km=MOD {ak($km);}) p_UnionExpr )*
        ;

//[75]
p_UnionExpr
        :   p_IntersectExceptExpr ( (ku=UNION {ak($ku);} | VBAR) p_IntersectExceptExpr )*
        ;

//[76]
p_IntersectExceptExpr
        :   p_InstanceofExpr ( (k=INTERSECT | k=EXCEPT) {ak($k);} p_InstanceofExpr )*
        ;

//[77]
p_InstanceofExpr
        :   p_TreatExpr ( ki=INSTANCE {ak($ki);} ko=OF {ak($ko);} p_SequenceType)?
        ;

//[78]
p_TreatExpr
        :   p_CastableExpr ( kt=TREAT {ak($kt);} ka=AS {ak($ka);} p_SequenceType )?
        ;
        
//[79]
p_CastableExpr
        :   p_CastExpr ( kc=CASTABLE {ak($kc);} ka=AS {ak($ka);} p_SingleType )?
        ;
        
//[80]
p_CastExpr
        :   p_UnaryExpr ( kc=CAST {ak($kc);} ka=AS {ak($ka);} p_SingleType )?
        ;

//[81]
p_UnaryExpr
        :   (PLUS | MINUS)* p_ValueExpr
                -> ^(UnaryExpr PLUS* p_ValueExpr)
        ;

//[82]
// TODO: remove the warning generated by this rule
// XQueryParser.g:599:3: Decision can match input such as "VALIDATE" using multiple alternatives: 1, 2
// "validate + validate { 1 }" is wrongly reported as incorrect code (missing LBRACKET at '+')
p_ValueExpr
        : p_ValidateExpr
        | p_PathExpr
        | p_ExtensionExpr
        ;

//[83]
p_GeneralComp
        :   EQUAL | NOTEQUAL | SMALLER | SMALLEREQ | GREATER | GREATEREQ
        ;

//[84]
p_ValueComp
        :   (k=EQ | k=NE | k=LT | k=LE | k=GT | k=GE) {ak($k);}
        ;

//[85]
p_NodeComp
        :   ki=IS {ak($ki);} | SMALLER_SMALLER | GREATER_GREATER
        ;

//[86]
p_ValidateExpr
        :   kv=VALIDATE {ak($kv);} p_ValidationMode? LBRACKET pm_Expr RBRACKET
        ;

//[87]
p_ValidationMode
        :   (k=LAX | k=STRICT | (k=AS p_QName)) {ak($k);}
        ;

//[88]
p_ExtensionExpr
        :   L_Pragma+ LBRACKET pm_Expr? RBRACKET
        ;

//[89]  /* ws: explicit */
//Pragma       ::=      "(#" S? QName (S PragmaContents)? "#)"
//L_Pragma

//[90]
//PragmaContents       ::=      (Char* - (Char* '#)' Char*))

//[91]  /* xgs: leading-lone-slash */
p_PathExpr
        : (SLASH p_RelativePathExpr) => (SLASH p_RelativePathExpr)
        | SLASH
        | SLASH_SLASH p_RelativePathExpr
        | p_RelativePathExpr
        ;

//[92]
p_RelativePathExpr  
        : p_StepExpr ((SLASH | SLASH_SLASH) p_StepExpr)*
        ;

//[93]
p_StepExpr
        : p_AxisStep
        | p_FilterExpr
        ;

//[94]
p_AxisStep
        : (p_ReverseStep | p_ForwardStep) p_PredicateList
        ;

//[95]
p_ForwardStep
        : (p_ForwardAxis p_NodeTest) | p_AbbrevForwardStep
        ;

//[96]
p_ForwardAxis
        : CHILD COLON_COLON
        | DESCENDANT COLON_COLON
        | ATTRIBUTE COLON_COLON
        | SELF COLON_COLON
        | DESCENDANT_OR_SELF COLON_COLON
        | FOLLOWING_SIBLING COLON_COLON
        | FOLLOWING COLON_COLON
        ;

//[97]
p_AbbrevForwardStep
        : ATTR_SIGN? p_NodeTest
        ;

//[98]
p_ReverseStep
        : (p_ReverseAxis p_NodeTest) | p_AbbrevReverseStep
        ;

//[99]
p_ReverseAxis
        : PARENT COLON_COLON
        | ANCESTOR COLON_COLON
        | PRECEDING_SIBLING COLON_COLON
        | PRECEDING COLON_COLON
        | ANCESTOR_OR_SELF COLON_COLON
        ;

//[100]
p_AbbrevReverseStep
        : DOT_DOT
        ;

//[101]
p_NodeTest
        : p_KindTest | p_NameTest
        ;

//[102]
p_NameTest
        : p_QName | p_Wildcard
        ;

//[103] /* ws: explicit */
p_Wildcard @init{setWsExplicit(true);}
        : STAR (COLON L_NCName)? | (L_NCName COLON STAR)
        ;
        finally {setWsExplicit(false);}

//[104]
p_FilterExpr
        :   p_PrimaryExpr p_PredicateList
        ;

//[105]
p_PredicateList
        :   p_Predicate*
        ;

//[106]
p_Predicate
        :   LSQUARE pm_Expr RSQUARE
        ;

//[107]
p_PrimaryExpr
        :   p_Literal
        | p_VarRef
        | p_ParenthesizedExpr
        | p_ContextItemExpr
        | p_FunctionCall
        | p_OrderedExpr
        | p_UnorderedExpr
        | p_Constructor
        ;

//[108]
p_Literal
        :   p_NumericLiteral | p_StringLiteral
        ;

//[109]
p_NumericLiteral
        :   L_IntegerLiteral | L_DecimalLiteral | L_DoubleLiteral
        ;
        
//[110]
p_VarRef
        :   DOLLAR p_VarName
        ;

//[111]
p_VarName
        :   p_QName
        ;

//[112]
p_ParenthesizedExpr
        :   LPAREN pm_Expr? RPAREN
        ;

//[113]
p_ContextItemExpr
        :   DOT
        ;

//[114]
p_OrderedExpr
        :   k=ORDERED {ak($k);} LBRACKET pm_Expr RBRACKET
        ;

//[115]
p_UnorderedExpr
        :   k=UNORDERED {ak($k);} LBRACKET pm_Expr RBRACKET
        ;

//[116] /* xgs: reserved-function-names */ - resolved through p_FQName production
//          /* gn: parens */
p_FunctionCall
        :   p_FQName LPAREN (p_ExprSingle (COMMA p_ExprSingle)*)? RPAREN
        ;
    
//[117]
p_Constructor
        :   p_DirectConstructor | p_ComputedConstructor
        ;

//[118]
p_DirectConstructor
        :   p_DirElemConstructor
        | L_DirCommentConstructor
        | L_DirPIConstructor
        ;

//[119] /* ws: explicit */ - resolved through the XMLLexer
p_DirElemConstructor //@init {setWsExplicit(true);}
        :   SMALLER {pushXMLLexer();}
            p_QName p_DirAttributeList 
            (EMPTY_CLOSE_TAG | (GREATER pm_DirElemContent* CLOSE_TAG p_QName S? GREATER))
                -> ^(DirElemConstructor ^(DirAttributeList p_DirAttributeList*) ^(DirElemContent pm_DirElemContent*))
        ;
        finally {popLexer();}

//[120] /* ws: explicit */ - resolved through the XMLLexer
p_DirAttributeList
        : (S (p_QName S? EQUAL S? p_DirAttributeValue)?)*
        ;

//[121] /* ws: explicit */ - resolved through the XMLLexer
p_DirAttributeValue
        : (QUOT (ESCAPE_QUOT | pm_QuotAttrValueContent)* QUOT)
                -> ^(DirAttributeValue pm_QuotAttrValueContent*)
        | (APOS (ESCAPE_APOS | pm_AposAttrValueContent)* APOS)
                -> ^(DirAttributeValue pm_AposAttrValueContent*)
        ;

//[122]
pm_QuotAttrValueContent
        : pg_QuotAttrContentChar | pg_CommonContent | p_ElemEnclosedExpr
        ;

// *************************************************
// This is not in the EBNF grammar.
// This is needed in order to generate nodes for
// each L_QuotAttrContentChar token
pg_QuotAttrContentChar
        : L_QuotAttrContentChar
                -> ^(AttributeValueChar L_QuotAttrContentChar)
        ;
// *************************************************

//[123]
pm_AposAttrValueContent
        :   pg_AposAttrContentChar | pg_CommonContent | p_ElemEnclosedExpr
        ;

// *************************************************
// This is not in the EBNF grammar.
// This is needed in order to generate nodes for
// each L_AposAttrContentChar token
pg_AposAttrContentChar
        : L_AposAttrContentChar
                -> ^(AttributeValueChar L_AposAttrContentChar)
        ;
// *************************************************

//[124]
pm_DirElemContent
        : p_DirectConstructor
        | L_CDataSection
        | p_ElemEnclosedExpr
        | pg_CommonContent
        | pg_ElementContentChar
        ;

// *************************************************
// This is not in the EBNF grammar.
// This is needed in order to generate nodes for
// each L_ElementContentChar token
pg_ElementContentChar
        : L_ElementContentChar
                -> ^(ElementContentChar L_ElementContentChar)
        ;
// *************************************************

// *************************************************
// This is not in the EBNF grammar.
// This is needed in order to generate the same kind
// parent node for each pm_CommonContent alternative
pg_CommonContent
        :   pm_CommonContent
                -> ^(CommonContent pm_CommonContent)
        ;
// *************************************************

//[125]
pm_CommonContent
        :   L_PredefinedEntityRef | L_CharRef | ESCAPE_LBRACKET | ESCAPE_RBRACKET
        ;

// *************************************************
// This is not in the EBNF grammar.
// This is needed in order to switch the lexer from
// XML back to XQuery
p_ElemEnclosedExpr
        :   LBRACKET
            ({pushXQueryLexer();} pm_Expr {popLexer();})
            RBRACKET 
        ;
// *************************************************

//[126] /* ws: explicit */
//L_DirCommentConstructor   

//[127] /* ws: explicit */
//L_DirCommentContents

//[128] /* ws: explicit */
//L_DirPIConstructor    

//[129] /* ws: explicit */
//L_DirPIContents

//[130] /* ws: explicit */
//L_CDataSection

//[131] /* ws: explicit */
//L_CDataSectionContents

//[132]
p_ComputedConstructor   
        :   p_CompDocConstructor
        | p_CompElemConstructor
        | p_CompAttrConstructor
        | p_CompNamespaceConstructor
        | p_CompTextConstructor
        | p_CompCommentConstructor
        | p_CompPIConstructor
        | {lc(MLS)}?=> p_CompBinaryConstructor
        ;

//[133]
p_CompDocConstructor    
        :   k=DOCUMENT {ak($k);} LBRACKET pm_Expr RBRACKET
        ;
        
//[134]
p_CompElemConstructor   
        :   k=ELEMENT {ak($k);} (p_QName | (LBRACKET pm_Expr RBRACKET)) LBRACKET pm_Expr? RBRACKET
        ;

//[135]
//ContentExpr      ::=      Expr

//[136]
p_CompAttrConstructor
        :   k=ATTRIBUTE {ak($k);} (p_QName | (LBRACKET pm_Expr RBRACKET)) LBRACKET pm_Expr? RBRACKET
        ;

//[137]
p_CompNamespaceConstructor
        : k=NAMESPACE {ak($k);} (p_NCName | (LBRACKET pm_Expr RBRACKET)) LBRACKET pm_Expr? RBRACKET
        ;

//[138]
//PrefixExpr       ::=      Expr

//[139]
//URIExpr      ::=      Expr

//[140]
p_CompTextConstructor
        :   k=TEXT {ak($k);} LBRACKET pm_Expr RBRACKET
        ;

// MarkLogic Server Extension
p_CompBinaryConstructor
        :   k=BINARY {ak($k);} LBRACKET pm_Expr RBRACKET
        ;

//[141]
p_CompCommentConstructor
        :   k=COMMENT {ak($k);} LBRACKET pm_Expr RBRACKET
        ;

//[142]
p_CompPIConstructor 
        :   k=PROCESSING_INSTRUCTION {ak($k);} (p_NCName | (LBRACKET pm_Expr RBRACKET)) LBRACKET pm_Expr? RBRACKET
        ;

//[143]
p_SingleType
        :   p_AtomicType QUESTION?
        ;

//[144]
p_TypeDeclaration
        :   k=AS {ak($k);} st=p_SequenceType
                -> ^(TypeDeclaration $st)
        ;

//[145]
p_SequenceType
        :   k=EMPTY_SEQUENCE {ak($k);} l=LPAREN r=RPAREN
                -> ^(SequenceType ^(EmptySequenceTest $k $l $r))
        | it=p_ItemType ((p_OccurrenceIndicator) => oi=p_OccurrenceIndicator)?
                -> ^(SequenceType $it $oi?)
        ;

//[146] /* xgs: occurrence-indicators */ - resolved in the p_SequenceType production
p_OccurrenceIndicator   
        : QUESTION | STAR | PLUS
        ;
        
//[147]
p_ItemType
        : p_KindTest
                -> ^(KindTest p_KindTest)
        | {lc(MLS)}?=> (BINARY LPAREN RPAREN)
                -> ^(BinaryTest BINARY LPAREN RPAREN)
        | (ITEM LPAREN RPAREN)
                -> ^(ItemTest ITEM LPAREN RPAREN)
        | p_AtomicType
        ;

//[148]
p_AtomicType
        : p_QName
                -> ^(AtomicType p_QName)
        ;

//[149]
p_KindTest
        : p_DocumentTest
        | p_ElementTest
        | p_AttributeTest
        | p_SchemaElementTest
        | p_SchemaAttributeTest
        | p_NamespaceNodeTest
        | p_PITest
        | p_CommentTest
        | p_TextTest
        | p_AnyKindTest
        ;

//[150]
p_AnyKindTest
        :   NODE LPAREN RPAREN
        ;

//[151]
p_DocumentTest
        :   DOCUMENT_NODE LPAREN (p_ElementTest | p_SchemaElementTest)? RPAREN ;

//[152]
p_TextTest
        :   TEXT LPAREN RPAREN
        ;

//[153]
p_CommentTest
        :   COMMENT LPAREN RPAREN
        ;

//[154]
p_NamespaceNodeTest
        : NAMESPACE_NODE LPAREN RPAREN
        ;

//[155]
p_PITest
        :   PROCESSING_INSTRUCTION LPAREN (p_NCName | p_StringLiteral)? RPAREN
        ;

//[156]
p_AttributeTest
        :   ATTRIBUTE LPAREN (p_AttribNameOrWildcard (COMMA p_TypeName)?)? RPAREN
        ;

//[157]
p_AttribNameOrWildcard  
        :   p_QName | STAR
        ;

//[158]
p_SchemaAttributeTest
        :   SCHEMA_ATTRIBUTE LPAREN p_AttributeDeclaration RPAREN
        ;

//[159]
p_AttributeDeclaration
        : p_AttributeName
        ;

//[160]
p_ElementTest
        :   ELEMENT LPAREN (p_ElementNameOrWildcard (COMMA p_TypeName QUESTION?)?)? RPAREN
        ;

//[161]
p_ElementNameOrWildcard
        :   p_QName | STAR ;

//[162]
p_SchemaElementTest
        :   SCHEMA_ELEMENT LPAREN p_ElementDeclaration RPAREN
        ;

//[163]
p_ElementDeclaration
        : p_ElementName
        ;

//[164]
p_AttributeName
        :   p_QName
        ;

//[165]
p_ElementName
        :   p_QName
        ;

//[166]
p_TypeName
        :   p_QName
        ;

//[167]
//URILiteral       ::=      StringLiteral

//[168]
//Prefix       ::=      NCName

//[169]
p_TryCatchExpr
        : p_TryClause p_CatchClause+
        ;

//[170]
p_TryClause
        : kc=TRY {ak($kc);} LBRACKET pm_Expr RBRACKET
        ;

//[171]
//TryTargetExpr

//[172]
p_CatchClause
        : kc=CATCH {ak($kc);} p_CatchErrorList p_CatchVars? LBRACKET pm_Expr RBRACKET
        ;

//[173]
p_CatchErrorList
        : p_NameTest (VBAR p_NameTest)*
        | {lc(MLS)}?=> (/* nothing */)
        ;

//[174]
 p_CatchVars
        : LPAREN DOLLAR p_VarName (COMMA DOLLAR p_VarName (COMMA DOLLAR p_VarName)?)? RPAREN
        ;
 
//[175]
//CatchErrorCode       ::=      "$" VarName

//[176]
//CatchErrorDesc       ::=      "$" VarName

//[177]
//CatchErrorVal        ::=      "$" VarName

// ****************
// Terminal Symbols
// ****************

//[141] /* ws: explicit */
//L_IntegerLiteral

//[142] /* ws: explicit */
//L_DecimalLiteral

//[143] /* ws: explicit */
//L_DoubleLiteral

//[144] /* ws: explicit */
p_StringLiteral
        : QUOT {pushStringLexer(false);} p_QuotStringLiteralContent QUOT { popLexer(); }
                -> ^(StringLiteral p_QuotStringLiteralContent*)
        |   APOS {pushStringLexer(true);} p_AposStringLiteralContent APOS { popLexer(); }
                -> ^(StringLiteral p_AposStringLiteralContent*)
        ;

// *************************************************
// This is not in the EBNF grammar.
// A special node is needed to keep track of the prolog
// declarations for which the order is important.
p_QuotStringLiteralContent
        : (ESCAPE_QUOT | L_CharRef | L_PredefinedEntityRef | ~(QUOT | AMP))*
        ;
// *************************************************

// *************************************************
// This is not in the EBNF grammar.
// A special node is needed to keep track of the prolog
// declarations for which the order is important.
p_AposStringLiteralContent
        : (ESCAPE_QUOT | L_CharRef | L_PredefinedEntityRef | ~(APOS | AMP))*
        ;
// *************************************************

//[145]
//L_PredefinedEntityRef

//[146]
//ESCAPE_QUOT

//[147]
//ESCAPE_APOS

//[148]
//L_ElementContentChar

//[149]
//L_QuotAttrContentChar

//[150]
//L_AposAttrContentChar

//[151] /* gn: comments */
//L_Comment
        
//TODO
//[152]
//PITarget     ::=      [http://www.w3.org/TR/REC-xml#NT-PITarget] XML  /* xgs: xml-version */

//[153]
//L_CharRef

//[154] /* ws: explicit */ - resolved through the additional productions
p_QName @init {setWsExplicit(true);}
        : p_NCName p_LocalNCName
                -> ^(QName p_NCName p_LocalNCName?)
        ;

// rule needed in order to catch the missing
// COLON and restore to non-explicit mode
p_LocalNCName
        : (COLON p_NCName)?
        ;
        finally {setWsExplicit(false);}


//[154] /* ws: explicit */ - solved through the additional productions
// additional production used to resolve the function name exceptions
p_FQName @init {setWsExplicit(true);}
        : p_FNCName p_LocalFNCName
        ;

// rule needed in order to catch the missing
// COLON and restore to non-explicit mode
p_LocalFNCName
        : (COLON p_NCName)?
        ;
        finally {setWsExplicit(false);}


//[155]
p_NCName
        : L_NCName
        // XQuery 1.0 keywords
        | ANCESTOR | ANCESTOR_OR_SELF | AND | AS | ASCENDING | AT | ATTRIBUTE | BASE_URI | BOUNDARY_SPACE | BY | CASE | CAST | CASTABLE | CHILD | COLLATION | COMMENT | CONSTRUCTION | COPY_NAMESPACES | DECLARE | DEFAULT | DESCENDANT | DESCENDANT_OR_SELF | DESCENDING | DIV | DOCUMENT | DOCUMENT_NODE | ELEMENT | ELSE | EMPTY | EMPTY_SEQUENCE | ENCODING | EQ | EVERY | EXCEPT | EXTERNAL | FOLLOWING | FOLLOWING_SIBLING | FOR | FUNCTION | GE | GREATEST | GT | IDIV | IF | IMPORT | IN | INHERIT | INSTANCE | INTERSECT | IS | ITEM | LAX | LE | LEAST | LET | LT | MOD | MODULE | NAMESPACE | NE | NO_INHERIT | NO_PRESERVE | NODE | OF | OPTION | OR | ORDER | ORDERED | ORDERING | PARENT | PRECEDING | PRECEDING_SIBLING | PRESERVE | PROCESSING_INSTRUCTION | RETURN | SATISFIES | SCHEMA | SCHEMA_ATTRIBUTE | SCHEMA_ELEMENT | SELF | SOME | STABLE | STRICT | STRIP | TEXT | THEN | TO | TREAT | TYPESWITCH | UNION | UNORDERED | VALIDATE | VARIABLE | VERSION | WHERE | XQUERY
        // XQuery 1.1 keywords
        | CATCH | CONTEXT | COUNT   | DECIMAL_FORMAT | DECIMAL_SEPARATOR | DIGIT | END | GROUP | GROUPING_SEPARATOR | INFINITY | MINUS_SIGN | NAMESPACE_NODE | NAN | NEXT | ONLY | OUTER | PATTERN_SEPARATOR | PERCENT | PER_MILLE | PREVIOUS | SLIDING | START | TRY | TUMBLING | WHEN | WINDOW | ZERO_DIGIT
        // XQuery Update 1.0 keywords
        | AFTER | BEFORE | COPY | DELETE | FIRST |INSERT | INTO | LAST | MODIFY | NODES | RENAME | REPLACE | REVALIDATION | SKIP | UPDATING | VALUE | WITH
        // XQuery Scripting 1.0 keywords
        | BLOCK | CONSTANT | EXIT | SEQUENTIAL | SET | SIMPLE | WHILE
        // Zorba keywords
        | EVAL | USING
        // Zorba DDL keywords
        | APPEND_ONLY | AUTOMATICALLY | CHECK | COLLECTION | CONSTRAINT | CONST | EQUALITY | EXPLICITLY | FOREACH | FOREIGN | FROM | INDEX | INTEGRITY | KEY | MAINTAINED | MUTABLE | NON | ON | QUEUE | RANGE | READ_ONLY | UNIQUE
        // Mark Logic keywords
        | BINARY | PRIVATE
        // entity references
        | AMP_ER | APOS_ER | QUOT_ER
        ;
p_FNCName
        : L_NCName
        // XQuery 1.0 keywords
        | ANCESTOR | ANCESTOR_OR_SELF | AND | AS | ASCENDING | AT | BASE_URI | BOUNDARY_SPACE | BY | CASE | CAST | CASTABLE | CHILD | COLLATION | CONSTRUCTION | COPY_NAMESPACES | DECLARE | DEFAULT | DESCENDANT | DESCENDANT_OR_SELF | DESCENDING | DIV | DOCUMENT | ELSE | EMPTY | ENCODING | EQ | EVERY | EXCEPT | EXTERNAL | FOLLOWING | FOLLOWING_SIBLING | FOR | FUNCTION | GE | GREATEST | GT | IDIV | IMPORT | IN | INHERIT | INSTANCE | INTERSECT | IS | LAX | LE | LEAST | LET | LT | MOD | MODULE | NAMESPACE | NE | NO_INHERIT | NO_PRESERVE | OF | OPTION | OR | ORDER | ORDERED | ORDERING | PARENT | PRECEDING | PRECEDING_SIBLING | PRESERVE | RETURN | SATISFIES | SCHEMA | SELF | SOME | STABLE | STRICT | STRIP | THEN | TO | TREAT | UNION | UNORDERED | VALIDATE | VARIABLE | VERSION | WHERE | XQUERY
        // XQuery 1.1 keywords
        | CATCH | CONTEXT | COUNT   | DECIMAL_FORMAT | DECIMAL_SEPARATOR | DIGIT | END | GROUP | GROUPING_SEPARATOR | INFINITY | MINUS_SIGN | NAN | NEXT | ONLY | OUTER | PATTERN_SEPARATOR | PERCENT | PER_MILLE | PREVIOUS | SLIDING | START | TRY | TUMBLING | WHEN | WINDOW | ZERO_DIGIT
        // XQuery Update 1.0 keywords
        | AFTER | BEFORE | COPY | DELETE | FIRST |INSERT | INTO | LAST | MODIFY | NODES | RENAME | REPLACE | REVALIDATION | SKIP | UPDATING | VALUE | WITH
        // XQuery Scripting 1.0 keywords
        | BLOCK | CONSTANT | EXIT | SEQUENTIAL | SET | SIMPLE
        // Zorba keywords
        | EVAL | USING
        // Zorba DDL keywords
        | APPEND_ONLY | AUTOMATICALLY | CHECK | COLLECTION | CONSTRAINT | CONST | EQUALITY | EXPLICITLY | FOREACH | FOREIGN | FROM | INDEX | INTEGRITY | KEY | MAINTAINED | MUTABLE | NON | ON | QUEUE | RANGE | READ_ONLY | UNIQUE
        // Mark Logic keywords
        | BINARY | PRIVATE
        // entity references
        | AMP_ER | APOS_ER | QUOT_ER
        ;


//[156]
//S

//[157]
//Char


// **************************************
// XQuery Update 1.0 Productions
// http://www.w3.org/TR/xquery-update-10/
// **************************************

//[141]
pm_RevalidationDecl
        : k+=DECLARE k+=REVALIDATION (k+=STRICT | k+=LAX | k+=SKIP) {ak($k);}
        ;

//[142]
p_InsertExprTargetChoice
        : ((k+=AS (k+=FIRST | k+=LAST))? k+=INTO) {ak($k);}
        | ka=AFTER {ak($ka);}
        | kb=BEFORE {ak($kb);}
        ;

//[143]
p_InsertExpr
        : k+=INSERT (k+=NODE | k+=NODES) p_SourceExpr p_InsertExprTargetChoice p_TargetExpr {ak($k);}
        ;

//[144]
p_DeleteExpr
        : k+=DELETE (k+=NODE | k+=NODES) p_TargetExpr {ak($k);}
        ;

//[145]
p_ReplaceExpr
        : k+=REPLACE (k+=VALUE k+=OF)? k+=NODE p_ExprSingle k+=WITH p_ExprSingle {ak($k);}
        ;

//[146]
p_RenameExpr
        : k+=RENAME k+=NODE p_TargetExpr AS p_NewNameExpr {ak($k);}
        ;

//[147]
p_SourceExpr
        : p_ExprSingle
        ;

//[148]
p_TargetExpr
        : p_ExprSingle
        ;

//[149]
p_NewNameExpr
        : p_ExprSingle
        ;

//[150]
p_TransformExpr
        : k+=COPY DOLLAR p_VarName BIND p_ExprSingle (COMMA DOLLAR p_VarName BIND p_ExprSingle)* k+=MODIFY p_ExprSingle k+=RETURN p_ExprSingle {ak($k);} 
        ;


// **************************************
// XQuery Scripting Extension 1.0 Productions
// http://www.w3.org/TR/xquery-sx-10/
// **************************************

//[32]
pm_ApplyExpr
        : p_ConcatExpr SEMICOLON
        ;

//[33]
p_ConcatExpr
        : p_ExprSingle (COMMA p_ExprSingle)*
        ;

//[153]
p_BlockExpr
        : kb=BLOCK {ak($kb);} p_Block
        ;

//[154]
p_Block
        : LBRACKET bd=pm_BlockDecls bb=p_BlockBody RBRACKET
                -> ^(Block $bd $bb)
        ;

//[155]
pm_BlockDecls
        : bvd+=pm_BlockVarDecl*
                -> ^(BlockDecls $bvd*)
        ;

//[156]
pm_BlockVarDecl
        : kd=DECLARE {ak($kd);} DOLLAR qn=p_VarName td=p_TypeDeclaration? (BIND es=p_ExprSingle)? (COMMA DOLLAR p_VarName p_TypeDeclaration? (BIND p_ExprSingle)?)* SEMICOLON
                ->  ^(BlockVarDecl $qn $td? $es?)
        ;

//[157]
p_BlockBody
        : pm_Expr
        ;

//[158]
p_AssignmentExpr
        : ks=SET {ak($ks);} DOLLAR p_VarName BIND p_ExprSingle
        ;

//[159]
p_ExitExpr
        : ke=EXIT {ak($ke);} kr=RETURNING {ak($kr);} p_ExprSingle
        ;

//[160]
p_WhileExpr
        : kw=WHILE {ak($kw);} LPAREN p_ExprSingle RPAREN p_WhileBody
        ;

//[161]
p_WhileBody
        : p_Block
        ;


// **************************************
// Zorba XQuery Extensions
// http://www.zorba-xquery.com/doc/zorba-latest/zorba/html/eval.html
// **************************************
p_EvalExpr
        : p_UsingClause? ke=EVAL {ak($ke);} LBRACKET p_ExprSingle RBRACKET
        ;

p_UsingClause
        : ku=USING {ak($ku);} DOLLAR p_VarName (COMMA DOLLAR p_VarName)*
        ;
// *************************************************

// *************************************************
// XQDDL
// p_CollectionDecl, p_IndexDecl (zorba/doc/zorba/xqddf.dox)
// p_ICDecl (http://www.zorba-xquery.com/ddl/xqddf.pdf)
// *************************************************
p_CollectionDecl
        : k+=DECLARE p_CollProperties k+=COLLECTION p_QName p_CollectionTypeDecl? (k+=WITH p_NodeModifier k+=NODES)? SEMICOLON {ak($k);}
        ;

p_CollProperties
        : ((k+=CONST | k+=MUTABLE
        | k+=APPEND_ONLY | k+=QUEUE
        | k+=ORDERED | k+=UNORDERED)*) {ak($k);}
        ;

p_CollectionTypeDecl
        : (ka=AS {ak($ka);} p_KindTest ((p_OccurrenceIndicator) => p_OccurrenceIndicator)?)
        ;

p_NodeModifier
        : (k+=READ_ONLY | k+=MUTABLE) {ak($k);}
        ;

p_IndexDecl
        : k+=DECLARE p_IndexProperties k+=INDEX p_IndexName k+=ON k+=NODES p_IndexDomainExpr k+=BY p_IndexKeySpec (COMMA p_IndexKeySpec)* SEMICOLON {ak($k);}
        ;

p_IndexName
        : p_QName
        ;

p_IndexProperties
        : ((k+=UNIQUE | k+=NON k+=UNIQUE
        | k+=VALUE k+=RANGE | k+=VALUE k+=EQUALITY
        | k+=AUTOMATICALLY k+=MAINTAINED | k+=MANUALLY k+=MAINTAINED)*) {ak($k);}
        ;

p_IndexDomainExpr
        : p_PathExpr
        ;

p_IndexKeySpec
        : p_IndexKeyExpr p_IndexKeyTypeDecl p_IndexKeyCollation?
        ;

p_IndexKeyExpr
        : p_PathExpr
        ;

p_IndexKeyTypeDecl
        : ka=AS {ak($ka);} p_AtomicType
        ;

p_IndexKeyCollation
        : kc=COLLATION {ak($kc);} p_StringLiteral
        ;

p_ICDecl
        : k+=DECLARE k+=INTEGRITY k+=CONSTRAINT {ak($k);} p_QName (p_ICCollection | p_ICForeignKey) SEMICOLON
        ;

p_ICCollection
        : k+=ON k+=COLLECTION {ak($k);} p_QName (p_ICCollSequence | p_ICCollSequenceUnique | p_ICCollNode)
        ;

p_ICCollSequence
        : DOLLAR p_QName kc=CHECK {ak($kc);} p_ExprSingle
        ;

p_ICCollSequenceUnique
        : k+=NODE k+=DOLLAR p_QName k+=CHECK k+=UNIQUE k+=KEY p_PathExpr {ak($k);}
        ;

p_ICCollNode
        : k+=FOREACH k+=NODE DOLLAR p_QName k+=CHECK p_ExprSingle {ak($k);}
        ;

p_ICForeignKey
        : k+=FOREIGN k+=KEY p_ICForeignKeySource p_ICForeignKeyTarget {ak($k);}
        ;

p_ICForeignKeySource
        : kf=FROM {ak($kf);} p_ICForeignKeyValues
        ;

p_ICForeignKeyTarget
        : kt=TO {ak($kt);} p_ICForeignKeyValues
        ;

p_ICForeignKeyValues
        : k+=COLLECTION p_QName k+=NODE DOLLAR p_QName k+=KEY p_PathExpr {ak($k);}
        ;
// *************************************************
