/*******************************************************************************
 * Copyright (c) 2008 by Buddhika Laknath <blaknath@gmail.com>  
 * This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0, and is available at
 * http://www.eclipse.org/legal/epl-v10.html 
 *******************************************************************************/

package org.eclipse.wst.xquery.core.internal.parser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.Token;
import org.eclipse.wst.xquery.core.internal.regions.XQueryRegionContext;

import com.martinprobst.xqpretty.XQueryLexer;

public class XQueryTextParser implements XQueryRegionContext{

	public static final int MODE_NORMAL = 0;
	private XQueryLexer fTokenizer = null;
	private int mode ; 

	public XQueryTextParser(int mode) {
		this.mode = mode ;
	}

	/*
	 * Returns the token list
	 */	
	public List getTokenList(String content) {
		List tokens; 
		try{
			tokens = new ArrayList();
			XQueryLexer lexer = getTokenizer(content) ;
			Token t = lexer.nextToken();
			while (t.getType() != Token.EOF) {
				tokens.add(t);
				t = lexer.nextToken();
			}
		}catch(Exception e){
			e.printStackTrace();
			tokens = Collections.EMPTY_LIST;
		}
	    
		return tokens; 
	}
	
	/*
	 * Returns the generalized token type
	 */
	public String getXQueryTokenType(Token token, Token prevToken){
				
		if (prevToken != null && prevToken.getType() == 146)
			return XQUERY_VARIABLE;
		
		switch(token.getType()){
			case 42 :
				return XQUERY_SPECIAL;//COMMA
			case 24 :
				return XQUERY_SPECIAL;//Lit_EQ				
			case 31 :
				return XQUERY_KEYWORDS;//ELEMENT
			case 126 : 
				return XQUERY_KEYWORDS;//DOCUMENT
			case 55 :
				return XQUERY_KEYWORDS;//AS
			case 5 :
				return XQUERY_SPECIAL;//CLOSE_ANGLE			
			case 21 :
				return XQUERY_KEYWORDS;//ENCODING
			case 87 : 
				return XQUERY_KEYWORDS;//TREAT
			case 23 :
				return XQUERY_KEYWORDS;//NAMESPACE
			case 142 :
				return XQUERY_COMMENT;//XQ_COMMENT				
			case 40 :
				return XQUERY_KEYWORDS;//LEAST
			case 74 : 
				return XQUERY_KEYWORDS;//THEN
			case 85 :
				return XQUERY_SPECIAL;//INSTANCE				
			case 124 : 
				return XQUERY_TEXT;//CDataSection
			case 98 :
				return XQUERY_KEYWORDS;//LAX
			case 48 :
				return XQUERY_KEYWORDS;//IMPORT				
			case 137 :
				return XQUERY_TEXT;//NCNameStartChar
			case 13 : 
				return XQUERY_KEYWORDS;//AposAttrContentChar
			case 139 :
				return XQUERY_TEXT;//Letter
			case 88 :
				return XQUERY_KEYWORDS;//CASTABLE				
			case 37 :
				return XQUERY_KEYWORDS;//ORDER
			case 78 : 
				return XQUERY_KEYWORDS;//TO		
			case 77 :
				return XQUERY_KEYWORDS;//AND
			case 47 :
				return XQUERY_KEYWORDS;//BASE_URI				
			case 57 :
				return XQUERY_LEFT_CUR_BRACKET;//LCURLY
			case 32 : 
				return XQUERY_KEYWORDS;//FUNCTION
			case 7 :
				return XQUERY_SPECIAL;//EMPTY_CLOSE_TAG
			case 71 :
				return XQUERY_KEYWORDS;//TYPESWITCH				
			case 117 :
				return XQUERY_SPECIAL;//RBRACKET
			case 93 : 
				return XQUERY_KEYWORDS;//LE
			case 22 :
				return XQUERY_KEYWORDS;//MODULE
			case 19 :
				return XQUERY_KEYWORDS;//VERSION				
			case 54 :
				return XQUERY_RIGHT_BRACKET;//RPAREN
			case 49 : 
				return XQUERY_KEYWORDS;//SCHEMA
			case 53 :
				return XQUERY_KEYWORDS;//CONSTRUCTION
			case 9 :
				return XQUERY_SPECIAL;//ESCAPE_CURLY_OPEN				
			case 26 :
				return XQUERY_KEYWORDS;//DECLARE
			case 27 : 
				return XQUERY_KEYWORDS;//BOUNDARY_SPACE
			case 4 :
				return XQUERY_SPECIAL;//CLOSE_TAG
			case 83 :
				return XQUERY_KEYWORDS;//INTERSECT				
			case 6 :
				return XQUERY_SPECIAL;//OPEN_ANGLE
			case 50 : 
				return XQUERY_KEYWORDS;//AT
			case 8 :
				return XQUERY_SPECIAL;//ElementContentChar
			case 82 :
				return XQUERY_KEYWORDS;//UNION				
			case 29 :
				return XQUERY_KEYWORDS;//STRIP		
			case 39 :
				return XQUERY_KEYWORDS;//GREATEST			
			case 12 :
				return XQUERY_SPECIAL;//QuotAttrContentChar
			case 67 : 
				return XQUERY_KEYWORDS;//DESCENDING
			case 144 :
				return XQUERY_TEXT;//Char
			case 90 :
				return XQUERY_KEYWORDS;//EQ				
			case 92 : 
				return XQUERY_KEYWORDS;//LT
			case 133 :
				return XQUERY_KEYWORDS;//DOCUMENT_NODE
			case 86 :
				return XQUERY_KEYWORDS;//OF				
			case 114 : 
				return XQUERY_KEYWORDS;//ANCESTOR_OR_SELF
			case 109 :
				return XQUERY_KEYWORDS;//FOLLOWING				
			case 95 :
				return XQUERY_KEYWORDS;//GE
			case 72 : 
				return XQUERY_KEYWORDS;//CASE
			case 107 :
				return XQUERY_KEYWORDS;//DESCENDANT_OR_SELF		
			case 75 :
				return XQUERY_KEYWORDS;//ELSE
			case 25 : 
				return XQUERY_SPECIAL;//SEMICOLON
			case 106 :
				return XQUERY_KEYWORDS;//SELF
			case 100 :
				return XQUERY_TEXT;//Pragma				
			case 141 :
				return XQUERY_TEXT;//SUnprotected
			case 119 :
				return XQUERY_TEXT;//DecimalLiteral
			case 127 :
				return XQUERY_KEYWORDS;//TEXT				
			case 115 :
				return XQUERY_SPECIAL;//COLON
			case 79 : 
				return XQUERY_UNDEFINED;//DIV
			case 110 :
				return XQUERY_KEYWORDS;//PARENT
			case 136 :
				return XQUERY_TEXT;//NCName				
			case 16 :
				return XQUERY_SPECIAL;//QUOT
			case 122 : 
				return XQUERY_TEXT;//DirPIConstructor
			case 63 :
				return XQUERY_KEYWORDS;//WHERE
			case 130 :
				return XQUERY_KEYWORDS;//EMPTY_SEQUENCE				
			case 52 :
				return XQUERY_KEYWORDS;//EXTERNAL
			case 134 : 
				return XQUERY_KEYWORDS;//SCHEMA_ATTRIBUTE
			case 69 :
				return XQUERY_KEYWORDS;//EVERY
			case 18 :
				return XQUERY_KEYWORDS;//XQUERY				
			case 89 :
				return XQUERY_KEYWORDS;//CAST
			case 80 :
				return XQUERY_UNDEFINED;//IDIV
			case 116 :
				return XQUERY_SPECIAL;//LBRACKET				
			case 70 :
				return XQUERY_KEYWORDS;//SATISFIES
			case 81 : 
				return XQUERY_KEYWORDS;//MOD
			case 84 :
				return XQUERY_KEYWORDS;//EXCEPT
			case 104 :
				return XQUERY_KEYWORDS;//DESCENDANT				
			case 99 :
				return XQUERY_KEYWORDS;//STRICT
			case 76 : 
				return XQUERY_KEYWORDS;//OR				
			case 123 :
				return XQUERY_TEXT;//S
			case 58 :
				return XQUERY_RIGHT_CUR_BRACKET;//RCURLY				
			case 108 :
				return XQUERY_KEYWORDS;//FOLLOWING_SIBLING
			case 64 : 
				return XQUERY_KEYWORDS;//BY
			case 65 :
				return XQUERY_KEYWORDS;//STABLE
			case 135 :
				return XQUERY_KEYWORDS;//SCHEMA_ELEMENT				
			case 120 :
				return XQUERY_TEXT;//DoubleLiteral
			case 97 : 
				return XQUERY_KEYWORDS;//VALIDATE
			case 45 :
				return XQUERY_KEYWORDS;//NO_INHERIT
			case 113 :
				return XQUERY_KEYWORDS;//PRECEDING				
			case 56 :
				return XQUERY_LEFT_BRACKET;//LPAREN
			case 112 : 
				return XQUERY_KEYWORDS;//PRECEDING_SIBLING
			case 34 :
				return XQUERY_KEYWORDS;//ORDERING
			case 41 :
				return XQUERY_KEYWORDS;//COPY_NAMESPACES				
			case 43 :
				return XQUERY_KEYWORDS;//NO_PRESERVE
			case 36 : 
				return XQUERY_KEYWORDS;//UNORDERED
			case 17 :
				return XQUERY_SPECIAL;//APOS
			case 11 :
				return XQUERY_TEXT;//PredefinedEntityRef				
			case 38 :
				return XQUERY_KEYWORDS;//EMPTY
			case 44 :
				return XQUERY_KEYWORDS;//INHERIT
			case 66 :
				return XQUERY_KEYWORDS;//ASCENDING				
			case 101 :
				return XQUERY_SPECIAL;//SLASH
			case 15 : 
				return XQUERY_SPECIAL;//ESCAPE_QUOT		
			case 118 :
				return XQUERY_TEXT;//IntegerLiteral
			case 96 :
				return XQUERY_KEYWORDS;//IS				
			case 94 :
				return XQUERY_KEYWORDS;//GT
			case 91 : 
				return XQUERY_KEYWORDS;//NE
			case 128 :
				return XQUERY_COMMENT;//COMMENT
			case 20 :
				return XQUERY_TEXT;//StringLiteral				
			case 14 :
				return XQUERY_SPECIAL;//ESCAPE_APOS
			case 121 : 
				return XQUERY_TEXT;//DirCommentConstructor
			case 131 :
				return XQUERY_KEYWORDS;//ITEM
			case 138 :
				return XQUERY_TEXT;//NCNameChar				
			case 35 :
				return XQUERY_KEYWORDS;//ORDERED
			case 129 : 
				return XQUERY_KEYWORDS;//PROCESSING_INSTRUCTION
			case 61 :
				return XQUERY_KEYWORDS;//IN
			case 68 :
				return XQUERY_KEYWORDS;//SOME				
			case 125 :
				return XQUERY_TEXT;//CharRef
			case 46 : 
				return XQUERY_KEYWORDS;//COLLATION
			case 102 :
				return XQUERY_SPECIAL;//SLASH_SLASH
			case 111 :
				return XQUERY_KEYWORDS;//ANCESTOR		
			case 59 :
				return XQUERY_KEYWORDS;//RETURN					
			case 10 : 
				return XQUERY_SPECIAL;//ESCAPE_CURLY_CLOSE
			case 62 :
				return XQUERY_KEYWORDS;//LET
			case 73 :
				return XQUERY_KEYWORDS;//IF				
			case 51 :
				return XQUERY_VARIABLE;//VARIABLE				
			case 132 : 
				return XQUERY_KEYWORDS;//NODE				
			case 60 :
				return XQUERY_KEYWORDS;//FOR
			case 28 :
				return XQUERY_KEYWORDS;//PRESERVE
			case 30 : 
				return XQUERY_KEYWORDS;//DEFAULT
			case 140 :
				return XQUERY_TEXT;//XMLDigit
			case 105 :
				return XQUERY_KEYWORDS;//ATTRIBUTE				
			case 103 :
				return XQUERY_KEYWORDS;//CHILD
			case 143 : 
				return XQUERY_TEXT;//Digits
			case 33 :
				return XQUERY_KEYWORDS;//OPTION				
			case 158 :
				return XQUERY_SPECIAL; //'..'
			case 156 : 
				return XQUERY_SPECIAL;//'::'
			case 150 :
				return XQUERY_SPECIAL;//'|'
			case 146 :
				return XQUERY_VARIABLE_$;//'$'				
			case 157 :
				return XQUERY_SPECIAL;//'@'
			case 148 : 
				return XQUERY_SPECIAL;//'-'
			case 160 :
				return XQUERY_SPECIAL;//'?'
			case 151 :
				return XQUERY_SPECIAL;//'!'				
			case 145 :
				return XQUERY_SPECIAL;//':='
			case 153 : 
				return XQUERY_SPECIAL;//'>='
			case 154 :
				return XQUERY_SPECIAL;//'<<'
			case 155 :
				return XQUERY_SPECIAL;//'>>'				
			case 159 :
				return XQUERY_SPECIAL;//'.'
			case 149 : 
				return XQUERY_SPECIAL;//'*'		
			case 152 :
				return XQUERY_SPECIAL;//'<='
			case 147 :
				return XQUERY_SPECIAL;//'+'		
		}
		
		return null;
	}
	
	private XQueryLexer getTokenizer(String content) {
		if (fTokenizer == null) {
			ANTLRStringStream source = new ANTLRStringStream(content);
			fTokenizer = new XQueryLexer(source);
		}
		return fTokenizer;
	}	

}
