lexer grammar XMLLexer;
options {
  tokenVocab=XQuery;
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

@lexer::members {
  // when we start, the '<' has already been eaten by the other lexer
  boolean inElem = true;
  boolean inAposAttr = false;
  boolean inQuotAttr = false;
}

QuotAttrContentChar
	:	{ inQuotAttr }? =>
		('\u0009' | '\u000A' | '\u000D' | '\u0020'..'\u0021' | '\u0023'..'\u0025' 
		| '\u0027'..'\u003B' | '\u003D'..'\u007A' | '\u007C'..'\u007C' | '\u007E'..'\uD7FF' |
		'\uE000'..'\uFFFD')+;
AposAttrContentChar
	:	{ inAposAttr }? =>
		('\u0009' | '\u000A' | '\u000D' | '\u0020'..'\u0025' | '\u0027'..'\u0026'
		| '\u0028'..'\u003B' | '\u003D'..'\u007A' | '\u007C'..'\u007C' | '\u007E'..'\uD7FF' |
		'\uE000'..'\uFFFD')+;
CLOSE_ANGLE
	:	{ inElem  }? => '>' { inElem = false; };
EMPTY_CLOSE_TAG
	:	{ inElem  }? => '/>' { inElem = false; };
S	:	{ inElem  }? => (' ' | '\t' | '\r' | '\n')+;
//QName	:	{ inElem  }? => NCName (':' NCName)?;
NCName	:	{ inElem  }? => NCNameUnprotected;
COLON	:	':';

fragment NCNameUnprotected
		:	NCNameStartChar NCNameChar*;
fragment NCNameStartChar
		:	Letter | '_';
fragment NCNameChar
		:	Letter | XMLDigit | '.' | '-' | '_'; //| CombiningChar | Extender;
fragment Letter
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
Lit_EQ	:	{ inElem  }? => '=';
QUOT	:	{ inElem || inAposAttr }? => '"' { inQuotAttr = !inQuotAttr; };
APOS	:	{ inElem || inQuotAttr }? => '\'' { inAposAttr = !inAposAttr; };
ESCAPE_APOS	
	:	{ inAposAttr }? =>
		'\'\'';
ESCAPE_QUOT	
	:	{ inQuotAttr }? =>
		'""';

ElementContentChar
//	:	 '\UFF02';
	:	{ !inElem }? =>
		('\u0009' | '\u000A' | '\u000D' | '\u0020'..'\u0025' | '\u0027'..'\u003B' 
		| '\u003D'..'\u007A' | '\u007C' | '\u007E'..'\uD7FF' | '\uE000'..'\uFFFD')+
	;
ESCAPE_CURLY_OPEN
	:	{ !inElem || inAposAttr || inQuotAttr }? => '{{';
ESCAPE_CURLY_CLOSE
	:	{ !inElem || inAposAttr || inQuotAttr }? => '}}';
LCURLY	:	{ !inElem || inAposAttr || inQuotAttr }? => '{';
RCURLY	:	{ !inElem || inAposAttr || inQuotAttr }? => '}';

OPEN_ANGLE
	:	{ !inElem }? => '<'  { inElem = true; };
CLOSE_TAG
	:	{ !inElem }? => '</' { inElem = true; };
PredefinedEntityRef
	:	{ !inElem || inAposAttr || inQuotAttr }? =>
		'&' ('lt' | 'gt' | 'apos' | 'quot' | 'amp' ) ';';
CharRef	:	{ !inElem || inAposAttr || inQuotAttr }? =>
		'&#' ('0'..'9')+ ';' | '&#x' ('0'..'9'|'a'..'f'|'A'..'F')+ ';';

DirCommentConstructor	
	:	{ !inElem }? => '<!--' (options {greedy=false;} : .* ) '-->'	/* ws: explicit */ ;
DirPIConstructor	
	:	{ !inElem }? => 
		'<?' SUnprotected? NCNameUnprotected (SUnprotected (options {greedy=false;} : .*))? '?>'	/* ws: explicit */ ;
fragment SUnprotected
	:	(' ' | '\t' | '\n' | '\r')+;
CDataSection	
	:	{ !inElem }? => '<![CDATA[' (options {greedy=false;} : .*) ']]>'	/* ws: explicit */ ;

