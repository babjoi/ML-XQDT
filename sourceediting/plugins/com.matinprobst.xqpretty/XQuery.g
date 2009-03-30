grammar XQuery;
options {
  output=AST;
  superClass='ParserBase';
}

tokens {
  CLOSE_TAG;
  CLOSE_ANGLE;
  OPEN_ANGLE;
  EMPTY_CLOSE_TAG;
  ElementContentChar;
  ESCAPE_CURLY_OPEN;
  ESCAPE_CURLY_CLOSE;
  PredefinedEntityRef;
  QuotAttrContentChar;
  AposAttrContentChar;
  ESCAPE_APOS;
  ESCAPE_QUOT;
  QUOT;
  APOS;
}

@header {
/*
 * Copyright 2008 Martin Probst
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.martinprobst.xqpretty;
}
@lexer::header {
/*
 * Copyright 2008 Martin Probst
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.martinprobst.xqpretty;
}

module		:	versionDecl? (libraryModule | mainModule) EOF;
versionDecl	:	XQUERY VERSION StringLiteral (ENCODING StringLiteral)? separator ;
mainModule	:	prolog queryBody ;
libraryModule	:	moduleDecl prolog ;
moduleDecl	:	MODULE NAMESPACE ncName Lit_EQ uRILiteral separator ;
prolog		:	((defaultNamespaceDecl | setter | namespaceDecl | importDecl) separator)* ((varDecl | functionDecl | optionDecl) separator)* ;
setter		:	boundarySpaceDecl | defaultCollationDecl | baseURIDecl | constructionDecl | orderingModeDecl | emptyOrderDecl | copyNamespacesDecl ;
importDecl	:	schemaImport | moduleImport ;
separator	:	SEMICOLON ;
namespaceDecl	:	DECLARE NAMESPACE ncName Lit_EQ uRILiteral ;
boundarySpaceDecl	
		:	DECLARE BOUNDARY_SPACE (PRESERVE | STRIP) ;
defaultNamespaceDecl
		:	DECLARE DEFAULT (ELEMENT | FUNCTION) NAMESPACE uRILiteral ;
optionDecl	:	DECLARE OPTION qNameOrIdent StringLiteral ;
orderingModeDecl
		:	DECLARE ORDERING (ORDERED | UNORDERED) ;
emptyOrderDecl	:	DECLARE DEFAULT ORDER EMPTY (GREATEST | LEAST) ;
copyNamespacesDecl
		:	DECLARE COPY_NAMESPACES preserveMode COMMA inheritMode ;
preserveMode	:	PRESERVE | NO_PRESERVE ;
inheritMode	:	INHERIT | NO_INHERIT ;
defaultCollationDecl	
		:	DECLARE DEFAULT COLLATION uRILiteral ;
baseURIDecl	:	DECLARE BASE_URI uRILiteral ;
schemaImport	:	IMPORT SCHEMA schemaPrefix? uRILiteral (AT uRILiteral (COMMA uRILiteral)*)? ;
schemaPrefix	:	(NAMESPACE ncName Lit_EQ) | (DEFAULT ELEMENT NAMESPACE) ;
moduleImport	:	IMPORT MODULE (NAMESPACE ncName Lit_EQ)? uRILiteral (AT uRILiteral (COMMA uRILiteral)*)? ;
varDecl		:	DECLARE VARIABLE variable typeDeclaration? ((':=' exprSingle) | EXTERNAL) ;
constructionDecl	
		:	DECLARE CONSTRUCTION (STRIP | PRESERVE) ;
functionDecl	:	DECLARE FUNCTION functionDeclPre paramClause RPAREN (AS sequenceType)? (enclosedExpr | EXTERNAL) ;
functionDeclPre	:	declFuncName LPAREN;
declFuncName	:	qNameOrIdent;
paramClause	:	paramList?;
paramList	:	param (COMMA param)* ;
param		:	variable typeDeclaration? ;
enclosedExpr	:	LCURLY expr RCURLY ;
queryBody	:	expr ;
expr		:	exprSingle (COMMA exprSingle)* ;
exprSingle	:	fLWORExpr  | quantifiedExpr  | typeswitchExpr  | ifExpr  | orExpr ;
fLWORExpr	:	(forClause | letClause)+ whereClause? orderByClause? returnClause;
returnClause	:	RETURN exprSingle ;
forClause	:	FOR variable typeDeclaration? positionalVar? IN exprSingle forClauseExt* ;
variable	:	'$' varName;
forClauseExt	:	(COMMA variable typeDeclaration? positionalVar? IN exprSingle);
positionalVar	:	AT variable ;
letClause	:	LET variable typeDeclaration? ':=' exprSingle letClauseExt* ;
letClauseExt	:	(COMMA variable typeDeclaration? ':=' exprSingle);
whereClause	:	WHERE exprSingle ;
orderByClause	:	((ORDER BY) | (STABLE ORDER BY)) orderSpecList ;
orderSpecList	:	orderSpec (COMMA orderSpec)* ;
orderSpec	:	exprSingle orderModifier ;
orderModifier	:	(ASCENDING | DESCENDING)? (EMPTY (GREATEST | LEAST))? (COLLATION uRILiteral)? ;
quantifiedExpr	:	(SOME | EVERY) variable typeDeclaration? IN exprSingle quantifiedExprExt* satisfiesClause;
quantifiedExprExt
		:	COMMA variable typeDeclaration? IN exprSingle;
satisfiesClause	:	SATISFIES exprSingle;
typeswitchExpr	:	TYPESWITCH LPAREN expr RPAREN caseClause+ defaultClause;
defaultClause	:	DEFAULT (variable)? RETURN exprSingle;
caseClause	:	CASE (variable AS)? sequenceType RETURN exprSingle;
ifExpr		:	IF LPAREN expr RPAREN thenClause elseClause;
thenClause	:	THEN exprSingle;
elseClause	: 	ELSE exprSingle;
orExpr		:	andExpr ( OR andExpr )* ;
andExpr		:	comparisonExpr ( AND comparisonExpr )* ;
comparisonExpr	:	rangeExpr ( (valueComp  | generalComp  | nodeComp) rangeExpr )? ;
rangeExpr	:	additiveExpr ( TO additiveExpr )? ;
additiveExpr	:	multiplicativeExpr ( ('+' | '-') multiplicativeExpr )* ;
multiplicativeExpr	
		:	unionExpr ( ('*' | DIV | IDIV | MOD) unionExpr )* ;
unionExpr	:	intersectExceptExpr ( (UNION | '|') intersectExceptExpr )* ;
intersectExceptExpr	
		:	instanceofExpr ( (INTERSECT | EXCEPT) instanceofExpr )* ;
instanceofExpr	:	treatExpr ( INSTANCE OF sequenceType )? ;
treatExpr	:	castableExpr ( TREAT AS sequenceType )? ;
castableExpr	:	castExpr ( CASTABLE AS singleType )? ;
castExpr	:	unaryExpr ( CAST AS singleType )? ;
unaryExpr	:	('-' | '+')* valueExpr ;
valueExpr	:	validateExpr | pathExpr | extensionExpr ;
generalComp	:	Lit_EQ | '!=' | OPEN_ANGLE | '<=' | CLOSE_ANGLE | '>=' ;
valueComp	:	EQ | NE | LT | LE | GT | GE ;
nodeComp	:	IS | '<<' | '>>' ;
validateExpr	:	VALIDATE validationMode? LCURLY expr RCURLY ;
validationMode	:	LAX | STRICT ;
extensionExpr	:	pragma+ LCURLY expr? RCURLY ;
pragma		:	Pragma;
pathExpr	:	(SLASH relativePathExpr) => (SLASH relativePathExpr)
			| SLASH | (SLASH_SLASH relativePathExpr)  | relativePathExpr	/* xgs: leading-lone-slash */ ;
relativePathExpr	
		:	stepExpr ((SLASH | SLASH_SLASH) stepExpr)* ;
stepExpr	:	filterExpr | axisStep ;
axisStep	:	(reverseStep | forwardStep) predicateList ;
forwardStep	:	(forwardAxis nodeTest) | abbrevForwardStep ;
forwardAxis	:	(CHILD '::')  | (DESCENDANT '::')  | (ATTRIBUTE '::')  | (SELF '::')  | (DESCENDANT_OR_SELF '::')  | (FOLLOWING_SIBLING '::')  | (FOLLOWING '::') ;
abbrevForwardStep	
		:	'@'? nodeTest ;
reverseStep	:	(reverseAxis nodeTest) | abbrevReverseStep ;
reverseAxis	:	(PARENT '::')  | (ANCESTOR '::')  | (PRECEDING_SIBLING '::')  | (PRECEDING '::')  | (ANCESTOR_OR_SELF '::') ;
abbrevReverseStep	:	'..' ;
nodeTest	:	kindTest | nameTest ;
nameTest	:	qNameOrIdent | wildcard;
wildcard	:	'*' (COLON ncName)? | ncName COLON '*';
filterExpr	:	primaryExpr predicateList ;
predicateList	:	predicate* ;
predicate	:	LBRACKET expr RBRACKET ;
primaryExpr	:	literal | varRef | parenthesizedExpr | contextItemExpr | functionCall | orderedExpr | unorderedExpr | constructor ;
literal		:	numericLiteral | StringLiteral ;
numericLiteral	:	IntegerLiteral | DecimalLiteral | DoubleLiteral ;
varRef		:	variable ;
varName		:	qNameOrIdent ;
parenthesizedExpr	
		:	LPAREN expr? RPAREN ;
contextItemExpr	:	'.' ;
orderedExpr	:	ORDERED LCURLY expr RCURLY ;
unorderedExpr	:	UNORDERED LCURLY expr RCURLY ;
functionCall	:	functionCallPre (exprSingle (COMMA exprSingle)*)? RPAREN	/* xgs: reserved-function-names */ /* gn: parens */ ;
functionCallPre	:	funcName LPAREN;
constructor	:	directConstructor  | computedConstructor ;
directConstructor	
		:	dirElemConstructor  | DirCommentConstructor  | DirPIConstructor ;
dirElemConstructor	
		:	OPEN_ANGLE { pushXMLLexer(); } qNameOrIdent dirAttributeList 
			(EMPTY_CLOSE_TAG | (CLOSE_ANGLE dirElemContent CLOSE_TAG qNameOrIdent S? CLOSE_ANGLE))
			{ popXMLLexer(); }	/* ws: explicit */ ;
dirAttributeList
		:	(S (qNameOrIdent S? Lit_EQ S? dirAttributeValue)?)*	/* ws: explicit */ ;
dirAttributeValue
		:	(QUOT (ESCAPE_QUOT | quotAttrValueContent)* QUOT)  | (APOS (ESCAPE_APOS | aposAttrValueContent)* APOS)	/* ws: explicit */ ;
quotAttrValueContent
		:	QuotAttrContentChar  | commonContent ;
aposAttrValueContent
		:	AposAttrContentChar  | commonContent ;
dirElemContent	:	(directConstructor  | CDataSection  | commonContent  | ElementContentChar)*;
commonContent	:	PredefinedEntityRef | CharRef | ESCAPE_CURLY_OPEN | ESCAPE_CURLY_CLOSE | elemEnclosedExpr ;
elemEnclosedExpr
		:	LCURLY { pushXQueryLexer(); } expr RCURLY { popXQueryLexer(); };
computedConstructor	
		:	compDocConstructor  | compElemConstructor  | compAttrConstructor  | compTextConstructor  
			| compCommentConstructor  | compPIConstructor ;
compDocConstructor	
		:	DOCUMENT LCURLY expr RCURLY ;
compElemConstructor	
		:	ELEMENT (qNameOrIdent | (LCURLY expr RCURLY)) LCURLY contentExpr? RCURLY ;
contentExpr	:	expr ;
compAttrConstructor	
		:	ATTRIBUTE (qNameOrIdent | (LCURLY expr RCURLY)) LCURLY expr? RCURLY ;
compTextConstructor	
		:	TEXT LCURLY expr RCURLY ;
compCommentConstructor	
		:	COMMENT LCURLY expr RCURLY ;
compPIConstructor	
		:	PROCESSING_INSTRUCTION (ncName | (LCURLY expr RCURLY)) LCURLY expr? RCURLY ;
singleType	:	atomicType '?'? ;
typeDeclaration	:	AS sequenceType ;
sequenceType	:	(EMPTY_SEQUENCE LPAREN RPAREN)  
			| itemType ((occurrenceIndicator) => occurrenceIndicator)?;
occurrenceIndicator	
		:	'?' | '*' | '+'	/* xgs: occurrence-indicators */ ;
itemType	:	kindTest | (ITEM LPAREN RPAREN) | atomicType ;
atomicType	:	qNameOrIdent ;
kindTest	:	documentTest  | elementTest  | attributeTest  | schemaElementTest  | schemaAttributeTest  
			| pITest  | commentTest  | textTest  | anyKindTest ;
anyKindTest	:	NODE LPAREN RPAREN ;
documentTest	:	DOCUMENT_NODE LPAREN (elementTest | schemaElementTest)? RPAREN ;
textTest	:	TEXT LPAREN RPAREN ;
commentTest	:	COMMENT LPAREN RPAREN ;
pITest		:	PROCESSING_INSTRUCTION LPAREN (ncName | StringLiteral)? RPAREN ;
attributeTest	:	ATTRIBUTE LPAREN (attribNameOrWildcard (COMMA typeName)?)? RPAREN ;
attribNameOrWildcard	
		:	attributeName | '*' ;
schemaAttributeTest
		:	SCHEMA_ATTRIBUTE LPAREN attributeDeclaration RPAREN ;
attributeDeclaration
		:	attributeName ;
elementTest	:	ELEMENT LPAREN (elementNameOrWildcard (COMMA typeName '?'?)?)? RPAREN ;
elementNameOrWildcard
		:	elementName | '*' ;
schemaElementTest
		:	SCHEMA_ELEMENT LPAREN elementDeclaration RPAREN ;
elementDeclaration
		:	elementName ;
attributeName	:	qNameOrIdent ;
elementName	:	qNameOrIdent ;
typeName	:	qNameOrIdent ;
uRILiteral	:	StringLiteral ;
piTarget	:	ncName;
qNameOrIdent	:	ncName (COLON ncName)?;
funcName	:	funcKeyword (COLON funcKeyword)?;
ncName		:	NCName | ANCESTOR | ANCESTOR_OR_SELF | AND | AS | ASCENDING | AT | ATTRIBUTE | BASE_URI | BOUNDARY_SPACE | BY | CASE | CAST | CASTABLE | CHILD | COLLATION | COMMENT | CONSTRUCTION | COPY_NAMESPACES | DECLARE | DEFAULT | DESCENDANT | DESCENDANT_OR_SELF | DESCENDING | DIV | DOCUMENT | DOCUMENT_NODE | ELEMENT | ELSE | EMPTY | EMPTY_SEQUENCE | ENCODING | EQ | EVERY | EXCEPT | EXTERNAL | FOLLOWING | FOLLOWING_SIBLING | FOR | FUNCTION | GE | GREATEST | GT | IDIV | IF | IMPORT | IN | INHERIT | INSTANCE | INTERSECT | IS | ITEM | LAX | LE | LEAST | LET | LT | MOD | MODULE | NAMESPACE | NE | NO_INHERIT | NO_PRESERVE | NODE | OF | OPTION | OR | ORDER | ORDERED | ORDERING | PARENT | PRECEDING | PRECEDING_SIBLING | PRESERVE | PROCESSING_INSTRUCTION | RETURN | SATISFIES | SCHEMA | SCHEMA_ATTRIBUTE | SCHEMA_ELEMENT | SELF | SOME | STABLE | STRICT | STRIP | TEXT | THEN | TO | TREAT | TYPESWITCH | UNION | UNORDERED | VALIDATE | VARIABLE | VERSION | WHERE | XQUERY;
funcKeyword	:	NCName | ANCESTOR | ANCESTOR_OR_SELF | AND | AS | ASCENDING | AT | BASE_URI | BOUNDARY_SPACE | BY | CASE | CAST | CASTABLE | CHILD | COLLATION | CONSTRUCTION | COPY_NAMESPACES | DECLARE | DEFAULT | DESCENDANT | DESCENDANT_OR_SELF | DESCENDING | DIV | DOCUMENT | ELSE | EMPTY | ENCODING | EQ | EVERY | EXCEPT | EXTERNAL | FOLLOWING | FOLLOWING_SIBLING | FOR | FUNCTION | GE | GREATEST | GT | IDIV | IMPORT | IN | INHERIT | INSTANCE | INTERSECT | IS | LAX | LE | LEAST | LET | LT | MOD | MODULE | NAMESPACE | NE | NO_INHERIT | NO_PRESERVE | OF | OPTION | OR | ORDER | ORDERED | ORDERING | PARENT | PRECEDING | PRECEDING_SIBLING | PRESERVE | RETURN | SATISFIES | SCHEMA | SELF | SOME | STABLE | STRICT | STRIP | THEN | TO | TREAT | UNION | UNORDERED | VALIDATE | VARIABLE | VERSION | WHERE | XQUERY;


ANCESTOR	:	'ancestor';
ANCESTOR_OR_SELF	
		:	'ancestor-or-self';
AND		:	'and';
AS		:	'as';
ASCENDING	:	'ascending';
AT		:	'at';
ATTRIBUTE	:	'attribute';
BASE_URI	:	'base-uri';
BOUNDARY_SPACE	:	'boundary-space';
BY		:	'by';
CASE		:	'case';
CAST		:	'cast';
CASTABLE	:	'castable';
CHILD		:	'child';
COLLATION	:	'collation';
COMMENT		:	'comment';
CONSTRUCTION	:	'construction';
COPY_NAMESPACES	:	'copy-namespaces';
DECLARE		:	'declare';
DEFAULT		:	'default';
DESCENDANT	:	'descendant';
DESCENDANT_OR_SELF	
		:	'descendant-or-self';
DESCENDING	:	'descending';
DIV		:	'div';
DOCUMENT	:	'document';
DOCUMENT_NODE	:	'document-node';
ELEMENT		:	'element';
ELSE		:	'else';
EMPTY		:	'empty';
EMPTY_SEQUENCE	:	'empty-sequence';
ENCODING	:	'encoding';
EQ		:	'eq';
EVERY		:	'every';
EXCEPT		:	'except';
EXTERNAL	:	'external';
FOLLOWING	:	'following';
FOLLOWING_SIBLING	
		:	'following-sibling';
FOR		:	'for';
FUNCTION	:	'function';
GE		:	'ge';
GREATEST	:	'greatest';
GT		:	'gt';
IDIV		:	'idiv';
IF		:	'if';
IMPORT		:	'import';
IN		:	'in';
INHERIT		:	'inherit';
INSTANCE	:	'instance';
INTERSECT	:	'intersect';
IS		:	'is';
ITEM		:	'item';
LAX		:	'lax';
LE		:	'le';
LEAST		:	'least';
LET		:	'let';
LT		:	'lt';
MOD		:	'mod';
MODULE		:	'module';
NAMESPACE	:	'namespace';
NE		:	'ne';
NO_INHERIT	:	'no-inherit';
NO_PRESERVE	:	'no-preserve';
NODE		:	'node';
OF		:	'of';
OPTION		:	'option';
OR		:	'or';
ORDER		:	'order';
ORDERED		:	'ordered';
ORDERING	:	'ordering';
PARENT		:	'parent';
PRECEDING	:	'preceding';
PRECEDING_SIBLING
		:	'preceding-sibling';
PRESERVE	:	'preserve';
PROCESSING_INSTRUCTION
		:	'processing-instruction';
RETURN		:	'return';
SATISFIES	:	'satisfies';
SCHEMA		:	'schema';
SCHEMA_ATTRIBUTE
		:	'schema-attribute';
SCHEMA_ELEMENT	:	'schema-element';
SELF		:	'self';
SOME		:	'some';
STABLE		:	'stable';
STRICT		:	'strict';
STRIP		:	'strip';
TEXT		:	'text';
THEN		:	'then';
TO		:	'to';
TREAT		:	'treat';
TYPESWITCH	:	'typeswitch';
UNION		:	'union';
UNORDERED	:	'unordered';
VALIDATE	:	'validate';
VARIABLE	:	'variable';
VERSION		:	'version';
WHERE		:	'where';
XQUERY		:	'xquery';
//QName		:	NCName (':' NCName)?;
//Wildcard	:	(NCName ':' '*')  | ('*' ':' NCName)	/* ws: explicit */ ;
NCName		:	NCNameStartChar NCNameChar*;

fragment NCNameStartChar
		:	Letter | '_';
fragment NCNameChar
		:	Letter | XMLDigit | '.' | '-' | '_'; //| CombiningChar | Extender;
fragment Letter
//	:	'\u0041'..'\u005A' | '\u0061'..'\u007A' | '\u00C0'..'\u00D6' | '\u00D8'..'\u00F6' | '\u00F8'..'\u00FF' | '\u0100'..'\u0131' | '\u0134'..'\u013E' | '\u0141'..'\u0148' | '\u014A'..'\u017E' | '\u0180'..'\u01C3' | '\u01CD'..'\u01F0' | '\u01F4'..'\u01F5' | '\u01FA'..'\u0217' | '\u0250'..'\u02A8' | '\u02BB'..'\u02C1' | '\u0386' | '\u0388'..'\u038A' | '\u038C' | '\u038E'..'\u03A1' | '\u03A3'..'\u03CE' | '\u03D0'..'\u03D6' | '\u03DA' | '\u03DC' | '\u03DE' | '\u03E0' | '\u03E2'..'\u03F3' | '\u0401'..'\u040C' | '\u040E'..'\u044F' | '\u0451'..'\u045C' | '\u045E'..'\u0481' | '\u0490'..'\u04C4' | '\u04C7'..'\u04C8' | '\u04CB'..'\u04CC' | '\u04D0'..'\u04EB' | '\u04EE'..'\u04F5' | '\u04F8'..'\u04F9' | '\u0531'..'\u0556' | '\u0559' | '\u0561'..'\u0586' | '\u05D0'..'\u05EA' | '\u05F0'..'\u05F2' | '\u0621'..'\u063A' | '\u0641'..'\u064A' | '\u0671'..'\u06B7' | '\u06BA'..'\u06BE' | '\u06C0'..'\u06CE' | '\u06D0'..'\u06D3' | '\u06D5' | '\u06E5'..'\u06E6' | '\u0905'..'\u0939' | '\u093D' | '\u0958'..'\u0961' | '\u0985'..'\u098C' | '\u098F'..'\u0990' | '\u0993'..'\u09A8' | '\u09AA'..'\u09B0' | '\u09B2' | '\u09B6'..'\u09B9' | '\u09DC'..'\u09DD' | '\u09DF'..'\u09E1' | '\u09F0'..'\u09F1' | '\u0A05'..'\u0A0A' | '\u0A0F'..'\u0A10' | '\u0A13'..'\u0A28' | '\u0A2A'..'\u0A30' | '\u0A32'..'\u0A33' | '\u0A35'..'\u0A36' | '\u0A38'..'\u0A39' | '\u0A59'..'\u0A5C' | '\u0A5E' | '\u0A72'..'\u0A74' | '\u0A85'..'\u0A8B' | '\u0A8D' | '\u0A8F'..'\u0A91' | '\u0A93'..'\u0AA8' | '\u0AAA'..'\u0AB0' | '\u0AB2'..'\u0AB3' | '\u0AB5'..'\u0AB9' | '\u0ABD' | '\u0AE0' | '\u0B05'..'\u0B0C' | '\u0B0F'..'\u0B10' | '\u0B13'..'\u0B28' | '\u0B2A'..'\u0B30' | '\u0B32'..'\u0B33' | '\u0B36'..'\u0B39' | '\u0B3D' | '\u0B5C'..'\u0B5D' | '\u0B5F'..'\u0B61' | '\u0B85'..'\u0B8A' | '\u0B8E'..'\u0B90' | '\u0B92'..'\u0B95' | '\u0B99'..'\u0B9A' | '\u0B9C' | '\u0B9E'..'\u0B9F' | '\u0BA3'..'\u0BA4' | '\u0BA8'..'\u0BAA' | '\u0BAE'..'\u0BB5' | '\u0BB7'..'\u0BB9' | '\u0C05'..'\u0C0C' | '\u0C0E'..'\u0C10' | '\u0C12'..'\u0C28' | '\u0C2A'..'\u0C33' | '\u0C35'..'\u0C39' | '\u0C60'..'\u0C61' | '\u0C85'..'\u0C8C' | '\u0C8E'..'\u0C90' | '\u0C92'..'\u0CA8' | '\u0CAA'..'\u0CB3' | '\u0CB5'..'\u0CB9' | '\u0CDE' | '\u0CE0'..'\u0CE1' | '\u0D05'..'\u0D0C' | '\u0D0E'..'\u0D10' | '\u0D12'..'\u0D28' | '\u0D2A'..'\u0D39' | '\u0D60'..'\u0D61' | '\u0E01'..'\u0E2E' | '\u0E30' | '\u0E32'..'\u0E33' | '\u0E40'..'\u0E45' | '\u0E81'..'\u0E82' | '\u0E84' | '\u0E87'..'\u0E88' | '\u0E8A' | '\u0E8D' | '\u0E94'..'\u0E97' | '\u0E99'..'\u0E9F' | '\u0EA1'..'\u0EA3' | '\u0EA5' | '\u0EA7' | '\u0EAA'..'\u0EAB' | '\u0EAD'..'\u0EAE' | '\u0EB0' | '\u0EB2'..'\u0EB3' | '\u0EBD' | '\u0EC0'..'\u0EC4' | '\u0F40'..'\u0F47' | '\u0F49'..'\u0F69' | '\u10A0'..'\u10C5' | '\u10D0'..'\u10F6' | '\u1100' | '\u1102'..'\u1103' | '\u1105'..'\u1107' | '\u1109' | '\u110B'..'\u110C' | '\u110E'..'\u1112' | '\u113C' | '\u113E' | '\u1140' | '\u114C' | '\u114E' | '\u1150' | '\u1154'..'\u1155' | '\u1159' | '\u115F'..'\u1161' | '\u1163' | '\u1165' | '\u1167' | '\u1169' | '\u116D'..'\u116E' | '\u1172'..'\u1173' | '\u1175' | '\u119E' | '\u11A8' | '\u11AB' | '\u11AE'..'\u11AF' | '\u11B7'..'\u11B8' | '\u11BA' | '\u11BC'..'\u11C2' | '\u11EB' | '\u11F0' | '\u11F9' | '\u1E00'..'\u1E9B' | '\u1EA0'..'\u1EF9' | '\u1F00'..'\u1F15' | '\u1F18'..'\u1F1D' | '\u1F20'..'\u1F45' | '\u1F48'..'\u1F4D' | '\u1F50'..'\u1F57' | '\u1F59' | '\u1F5B' | '\u1F5D' | '\u1F5F'..'\u1F7D' | '\u1F80'..'\u1FB4' | '\u1FB6'..'\u1FBC' | '\u1FBE' | '\u1FC2'..'\u1FC4' | '\u1FC6'..'\u1FCC' | '\u1FD0'..'\u1FD3' | '\u1FD6'..'\u1FDB' | '\u1FE0'..'\u1FEC' | '\u1FF2'..'\u1FF4' | '\u1FF6'..'\u1FFC' | '\u2126' | '\u212A'..'\u212B' | '\u212E' | '\u2180'..'\u2182' | '\u3041'..'\u3094' | '\u30A1'..'\u30FA' | '\u3105'..'\u312C' | '\uAC00'..'\uD7A3';
		:	'a'..'z' | 'A'..'Z';
fragment XMLDigit
		:	'0'..'9';
//fragment Letter	:	{ CharHelper.isLetter(LA(1) }? =>  .;
//fragment BaseChar
//		:	{ CharHelper.isBaseChar(LA(1) }? =>  .;
//fragment Ideographic	
//		:	{ CharHelper.isIdeographic(LA(1)) }? =>  .;
//fragment XMLDigit
//		:	{ CharHelper.isXMLDigit(LA(1)) }? =>  .;
//fragment CombiningChar
//		:	{ CharHelper.isCombiningChar(LA(1)) }? =>  .;
//fragment Extender
//		:	{ CharHelper.isExtender(LA(1)) }? =>  .;

DirCommentConstructor	
		:	'<!--' (options {greedy=false;} : .* ) '-->'	/* ws: explicit */ ;
DirPIConstructor	
		:	'<?' SUnprotected? NCName (SUnprotected (options {greedy=false;} : .*))? '?>'	/* ws: explicit */ ;
CDataSection	:	'<![CDATA[' (options {greedy=false;} : .*) ']]>'	/* ws: explicit */ ;
Pragma		:	'(#' SUnprotected? NCName (':' NCName)? (SUnprotected (options {greedy=false;} : .)*)? '#)';	

LCURLY		:	'{';
RCURLY		:	'}';
OPEN_ANGLE	:	'<';
CLOSE_ANGLE	:	'>';
Lit_EQ		:	'=';
fragment QUOT	:	'"';
fragment APOS	:	'\'';

SLASH_SLASH	:	'//';
SLASH		:	'/';

LBRACKET	:	'[';
RBRACKET	:	']';
LPAREN		:	'(';
RPAREN		:	')';
COLON		:	':';
COMMA		:	',';
SEMICOLON	:	';';

S		:	('\t' | ' ' | '\n' | '\r')+ { $channel = HIDDEN; };
fragment SUnprotected
		:	('\t' | ' ' | '\n' | '\r')+;
XQ_COMMENT
		:	'(:' (options {greedy=false;}: XQ_COMMENT |  . )* ':)' { $channel = HIDDEN; };
//XQ_COMMENT
//		:	'(:' (options {greedy=false;}: ( XQ_COMMENT | '(' ~':' | ':' ~')' | . )*) ':)' { $channel = HIDDEN; };
StringLiteral	:	QUOT (options {greedy=false;}: (~('"' | '&') | ESCAPE_QUOT | CharRef | PredefinedEntityRef) *) QUOT
			| APOS (options {greedy=false;}: (~('\'' | '&') | ESCAPE_APOS | CharRef | PredefinedEntityRef) *) APOS;
fragment ESCAPE_QUOT
		:	'""';
fragment ESCAPE_APOS
		:	'\'\'';
fragment PredefinedEntityRef
		:	'&' ('lt' | 'gt' | 'apos' | 'quot' | 'amp' ) ';';
fragment CharRef:	'&#' Digits ';' | '&#x' ('0'..'9'|'a'..'f'|'A'..'F')+ ';';

fragment Char	:	'\u0009' | '\u000A' | '\u000D' | '\u0020'..'\uD7FF' | '\uE000'..'\uFFFD' ;//| '\u10000'..'\u10FFFF';
IntegerLiteral	:	Digits;
DoubleLiteral	:	(('.' Digits) | (Digits ('.' '0'..'9'*)?)) ('e' | 'E') ('+'|'-')? Digits;
DecimalLiteral	:   	('.' Digits) | (Digits '.' '0'..'9'*);
fragment Digits	:	'0'..'9'+;
