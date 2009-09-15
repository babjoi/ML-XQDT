lexer grammar InternalRelaxng;
@header {
package org.oasisopen.parser.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.parser.antlr.Lexer;
}

T11 : 'namespace' ;
T12 : '=' ;
T13 : 'default' ;
T14 : 'datatypes' ;
T15 : 'list' ;
T16 : '{' ;
T17 : '}' ;
T18 : 'mixed' ;
T19 : 'parent' ;
T20 : 'empty' ;
T21 : 'text' ;
T22 : '|' ;
T23 : 'notAllowed' ;
T24 : 'external' ;
T25 : 'grammar' ;
T26 : '(' ;
T27 : ')' ;
T28 : ',' ;
T29 : 'element' ;
T30 : '?' ;
T31 : '+' ;
T32 : '*' ;
T33 : '&' ;
T34 : 'attribute' ;
T35 : '-' ;
T36 : 'div' ;
T37 : 'include' ;
T38 : 'start' ;
T39 : '|=' ;
T40 : '&=' ;
T41 : 'string' ;
T42 : 'token' ;
T43 : 'inherit' ;
T44 : '\\' ;
T45 : ':' ;
T46 : '~' ;

// $ANTLR src "../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g" 2093
RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

// $ANTLR src "../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g" 2095
RULE_INT : ('0'..'9')+;

// $ANTLR src "../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g" 2097
RULE_STRING : ('"' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'"')))* '"'|'\'' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'\'')))* '\'');

// $ANTLR src "../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g" 2099
RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

// $ANTLR src "../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g" 2101
RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

// $ANTLR src "../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g" 2103
RULE_WS : (' '|'\t'|'\r'|'\n')+;

// $ANTLR src "../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g" 2105
RULE_ANY_OTHER : .;


