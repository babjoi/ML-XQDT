lexer grammar InternalRelaxng;
@header {
package org.oasisopen.contentassist.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.ui.common.editor.contentassist.antlr.internal.Lexer;
}

T11 : '*' ;
T12 : 'empty' ;
T13 : 'text' ;
T14 : '|' ;
T15 : 'notAllowed' ;
T16 : '?' ;
T17 : '+' ;
T18 : ',' ;
T19 : '&' ;
T20 : '=' ;
T21 : '|=' ;
T22 : '&=' ;
T23 : 'string' ;
T24 : 'token' ;
T25 : 'attribute' ;
T26 : 'default' ;
T27 : 'datatypes' ;
T28 : 'div' ;
T29 : 'element' ;
T30 : 'external' ;
T31 : 'grammar' ;
T32 : 'include' ;
T33 : 'inherit' ;
T34 : 'list' ;
T35 : 'mixed' ;
T36 : 'namespace' ;
T37 : 'parent' ;
T38 : 'start' ;
T39 : '{' ;
T40 : '}' ;
T41 : '(' ;
T42 : ')' ;
T43 : '-' ;
T44 : '\\' ;
T45 : ':' ;
T46 : '~' ;

// $ANTLR src "../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g" 2529
RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

// $ANTLR src "../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g" 2531
RULE_INT : ('0'..'9')+;

// $ANTLR src "../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g" 2533
RULE_STRING : ('"' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'"')))* '"'|'\'' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'\'')))* '\'');

// $ANTLR src "../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g" 2535
RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

// $ANTLR src "../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g" 2537
RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

// $ANTLR src "../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g" 2539
RULE_WS : (' '|'\t'|'\r'|'\n')+;

// $ANTLR src "../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g" 2541
RULE_ANY_OTHER : .;


