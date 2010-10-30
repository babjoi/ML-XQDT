// $ANTLR 3.1.2 XQueryLexer.g 2010-10-30 22:09:16

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


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class XQueryLexer extends XQDTLexer {
    public static final int L_CDataSection=7;
    public static final int INDEX=180;
    public static final int MINUS=211;
    public static final int NAMESPACE_NODE=124;
    public static final int END=119;
    public static final int INTO=149;
    public static final int PATTERN_SEPARATOR=130;
    public static final int GREATER_GREATER=217;
    public static final int RENAME=153;
    public static final int INSTANCE=63;
    public static final int LAX=67;
    public static final int IMPORT=60;
    public static final int BINARY=192;
    public static final int ON=187;
    public static final int DOT=220;
    public static final int CONSTRAINT=174;
    public static final int Letter=242;
    public static final int ORDER=82;
    public static final int CASTABLE=28;
    public static final int DETERMINISTIC=117;
    public static final int CONSTANT=161;
    public static final int EMPTY_CLOSE_TAG=224;
    public static final int TYPESWITCH=104;
    public static final int MODULE=73;
    public static final int ESCAPE_RBRACKET=11;
    public static final int AMP=206;
    public static final int RPAREN=197;
    public static final int VERSION=109;
    public static final int XML_COMMENT_START=230;
    public static final int DECLARE=34;
    public static final int BOUNDARY_SPACE=24;
    public static final int CLOSE_TAG=225;
    public static final int NONDETERMINISTIC=127;
    public static final int UNION=105;
    public static final int STRIP=99;
    public static final int RANGE=189;
    public static final int GROUPING_SEPARATOR=121;
    public static final int HexLetter=243;
    public static final int WHEN=140;
    public static final int DESCENDING=38;
    public static final int DOCUMENT_NODE=41;
    public static final int ANCESTOR_OR_SELF=17;
    public static final int MUTABLE=185;
    public static final int SMALLER_SMALLER=216;
    public static final int CATCH=112;
    public static final int ATTR_SIGN=234;
    public static final int GE=55;
    public static final int SU=247;
    public static final int ELSE=43;
    public static final int PRAGMA_START=228;
    public static final int L_ElementContentChar=6;
    public static final int EVAL=168;
    public static final int SELF=95;
    public static final int COUNT=114;
    public static final int TEXT=100;
    public static final int COLON=222;
    public static final int PARENT=85;
    public static final int SET=165;
    public static final int UNIQUE=191;
    public static final int FOREACH=177;
    public static final int PUBLIC=134;
    public static final int PERCENT=131;
    public static final int Digit=244;
    public static final int EXTERNAL=50;
    public static final int EMPTY_SEQUENCE=45;
    public static final int LAST=150;
    public static final int COLLECTION=173;
    public static final int DOT_DOT=221;
    public static final int L_CharRef=9;
    public static final int CAST=27;
    public static final int DECIMAL_SEPARATOR=116;
    public static final int AUTOMATICALLY=171;
    public static final int LBRACKET=199;
    public static final int MOD=72;
    public static final int EXCEPT=49;
    public static final int QUESTION=208;
    public static final int OR=81;
    public static final int AFTER=143;
    public static final int S=246;
    public static final int SMALLEREQ=214;
    public static final int BLOCK=160;
    public static final int BY=25;
    public static final int SCHEMA_ELEMENT=94;
    public static final int INFINITY=122;
    public static final int TUMBLING=139;
    public static final int NO_INHERIT=76;
    public static final int LPAREN=196;
    public static final int PRECEDING_SIBLING=87;
    public static final int L_DecimalLiteral=252;
    public static final int EXIT=162;
    public static final int PI_START=232;
    public static final int APOS=237;
    public static final int SKIP=156;
    public static final int FROM=179;
    public static final int DELETE=146;
    public static final int EMPTY=44;
    public static final int ASCENDING=20;
    public static final int QUEUE=188;
    public static final int WHILE=167;
    public static final int ESCAPE_QUOT=13;
    public static final int ONLY=128;
    public static final int APOS_ER=194;
    public static final int NE=75;
    public static final int COMMENT=31;
    public static final int RETURNING=163;
    public static final int L_AnyChar=255;
    public static final int RSQUARE=202;
    public static final int ESCAPE_APOS=12;
    public static final int NCNameChar=240;
    public static final int EQUALITY=176;
    public static final int WITH=159;
    public static final int IN=61;
    public static final int SOME=96;
    public static final int NEXT=126;
    public static final int RETURN=90;
    public static final int LET=70;
    public static final int IF=59;
    public static final int NODE=78;
    public static final int PER_MILLE=132;
    public static final int FOR=53;
    public static final int CHARREF_HEX=236;
    public static final int PRESERVE=88;
    public static final int DEFAULT=35;
    public static final int L_AposAttrContentChar=5;
    public static final int BEFORE=144;
    public static final int ATTRIBUTE=22;
    public static final int CHILD=29;
    public static final int Digits=245;
    public static final int CDATA_START=14;
    public static final int TRY=138;
    public static final int OPTION=80;
    public static final int L_DirCommentConstructor=249;
    public static final int COMMA=207;
    public static final int CONST=175;
    public static final int ELEMENT=42;
    public static final int AS=19;
    public static final int DOCUMENT=40;
    public static final int ENCODING=46;
    public static final int NAN=125;
    public static final int TREAT=103;
    public static final int NAMESPACE=74;
    public static final int LEAST=69;
    public static final int THEN=101;
    public static final int PI_END=233;
    public static final int GREATEREQ=215;
    public static final int FOREIGN=178;
    public static final int NCNameStartChar=239;
    public static final int PRIVATE=133;
    public static final int AND=18;
    public static final int BASE_URI=23;
    public static final int TO=102;
    public static final int FUNCTION=54;
    public static final int L_Pragma=248;
    public static final int READ_ONLY=190;
    public static final int RBRACKET=200;
    public static final int LE=68;
    public static final int SCHEMA=92;
    public static final int CONSTRUCTION=32;
    public static final int PLUS=210;
    public static final int L_DoubleLiteral=253;
    public static final int NON=186;
    public static final int INTERSECT=64;
    public static final int AT=21;
    public static final int L_QuotAttrContentChar=4;
    public static final int LSQUARE=201;
    public static final int GREATEST=56;
    public static final int APPEND_ONLY=170;
    public static final int MAINTAINED=183;
    public static final int EQ=47;
    public static final int LT=71;
    public static final int ESCAPE_LBRACKET=10;
    public static final int OF=79;
    public static final int DOLLAR=198;
    public static final int WINDOW=141;
    public static final int FOLLOWING=51;
    public static final int CASE=26;
    public static final int CDATA_END=15;
    public static final int DESCENDANT_OR_SELF=37;
    public static final int EQUAL=203;
    public static final int SEMICOLON=226;
    public static final int CHECK=172;
    public static final int KEY=182;
    public static final int FIRST=147;
    public static final int SIMPLE=166;
    public static final int DIV=39;
    public static final int INSERT=148;
    public static final int REVALIDATION=155;
    public static final int QUOT=238;
    public static final int WHERE=110;
    public static final int PREVIOUS=135;
    public static final int COPY=145;
    public static final int AMP_ER=193;
    public static final int USING=169;
    public static final int EVERY=48;
    public static final int SCHEMA_ATTRIBUTE=93;
    public static final int XQUERY=111;
    public static final int CONTEXT=113;
    public static final int INTEGRITY=181;
    public static final int SLIDING=136;
    public static final int PRAGMA_END=229;
    public static final int IDIV=58;
    public static final int UPDATING=157;
    public static final int SATISFIES=91;
    public static final int DESCENDANT=36;
    public static final int VALUE=158;
    public static final int NOTEQUAL=205;
    public static final int STRICT=98;
    public static final int COLON_COLON=223;
    public static final int L_PredefinedEntityRef=8;
    public static final int L_IntegerLiteral=251;
    public static final int FOLLOWING_SIBLING=52;
    public static final int CHARREF_DEC=235;
    public static final int STABLE=97;
    public static final int DECIMAL_FORMAT=115;
    public static final int START=137;
    public static final int GROUP=120;
    public static final int VALIDATE=107;
    public static final int PRECEDING=86;
    public static final int VBAR=227;
    public static final int GREATER=213;
    public static final int MINUS_SIGN=123;
    public static final int ZERO_DIGIT=142;
    public static final int ORDERING=84;
    public static final int COPY_NAMESPACES=33;
    public static final int DIGIT=118;
    public static final int NO_PRESERVE=77;
    public static final int UNORDERED=106;
    public static final int OUTER=129;
    public static final int L_NCName=241;
    public static final int INHERIT=62;
    public static final int SLASH=218;
    public static final int L_DirPIConstructor=250;
    public static final int IS=65;
    public static final int REPLACE=154;
    public static final int GT=57;
    public static final int ITEM=66;
    public static final int ORDERED=83;
    public static final int PROCESSING_INSTRUCTION=89;
    public static final int COLLATION=30;
    public static final int SLASH_SLASH=219;
    public static final int ANCESTOR=16;
    public static final int SEQUENTIAL=164;
    public static final int SMALLER=212;
    public static final int NODES=152;
    public static final int MANUALLY=184;
    public static final int EOF=-1;
    public static final int VARIABLE=108;
    public static final int MODIFY=151;
    public static final int STAR=209;
    public static final int QUOT_ER=195;
    public static final int L_Comment=254;
    public static final int BIND=204;
    public static final int XML_COMMENT_END=231;

    // dummy list for warning elimination
    List<Stack<Object>> dummy = new ArrayList<Stack<Object>>();


    // delegates
    // delegators

    public XQueryLexer() {;} 
    public XQueryLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public XQueryLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "XQueryLexer.g"; }

    // $ANTLR start "ANCESTOR"
    public final void mANCESTOR() throws RecognitionException {
        try {
            int _type = ANCESTOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:48:29: ( 'ancestor' )
            // XQueryLexer.g:48:31: 'ancestor'
            {
            match("ancestor"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ANCESTOR"

    // $ANTLR start "ANCESTOR_OR_SELF"
    public final void mANCESTOR_OR_SELF() throws RecognitionException {
        try {
            int _type = ANCESTOR_OR_SELF;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:49:29: ( 'ancestor-or-self' )
            // XQueryLexer.g:49:31: 'ancestor-or-self'
            {
            match("ancestor-or-self"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ANCESTOR_OR_SELF"

    // $ANTLR start "AND"
    public final void mAND() throws RecognitionException {
        try {
            int _type = AND;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:50:29: ( 'and' )
            // XQueryLexer.g:50:31: 'and'
            {
            match("and"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "AND"

    // $ANTLR start "AS"
    public final void mAS() throws RecognitionException {
        try {
            int _type = AS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:51:29: ( 'as' )
            // XQueryLexer.g:51:31: 'as'
            {
            match("as"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "AS"

    // $ANTLR start "ASCENDING"
    public final void mASCENDING() throws RecognitionException {
        try {
            int _type = ASCENDING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:52:29: ( 'ascending' )
            // XQueryLexer.g:52:31: 'ascending'
            {
            match("ascending"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ASCENDING"

    // $ANTLR start "AT"
    public final void mAT() throws RecognitionException {
        try {
            int _type = AT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:53:29: ( 'at' )
            // XQueryLexer.g:53:31: 'at'
            {
            match("at"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "AT"

    // $ANTLR start "ATTRIBUTE"
    public final void mATTRIBUTE() throws RecognitionException {
        try {
            int _type = ATTRIBUTE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:54:29: ( 'attribute' )
            // XQueryLexer.g:54:31: 'attribute'
            {
            match("attribute"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ATTRIBUTE"

    // $ANTLR start "BASE_URI"
    public final void mBASE_URI() throws RecognitionException {
        try {
            int _type = BASE_URI;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:55:29: ( 'base-uri' )
            // XQueryLexer.g:55:31: 'base-uri'
            {
            match("base-uri"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "BASE_URI"

    // $ANTLR start "BOUNDARY_SPACE"
    public final void mBOUNDARY_SPACE() throws RecognitionException {
        try {
            int _type = BOUNDARY_SPACE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:56:29: ( 'boundary-space' )
            // XQueryLexer.g:56:31: 'boundary-space'
            {
            match("boundary-space"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "BOUNDARY_SPACE"

    // $ANTLR start "BY"
    public final void mBY() throws RecognitionException {
        try {
            int _type = BY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:57:29: ( 'by' )
            // XQueryLexer.g:57:31: 'by'
            {
            match("by"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "BY"

    // $ANTLR start "CASE"
    public final void mCASE() throws RecognitionException {
        try {
            int _type = CASE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:58:29: ( 'case' )
            // XQueryLexer.g:58:31: 'case'
            {
            match("case"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CASE"

    // $ANTLR start "CAST"
    public final void mCAST() throws RecognitionException {
        try {
            int _type = CAST;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:59:29: ( 'cast' )
            // XQueryLexer.g:59:31: 'cast'
            {
            match("cast"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CAST"

    // $ANTLR start "CASTABLE"
    public final void mCASTABLE() throws RecognitionException {
        try {
            int _type = CASTABLE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:60:29: ( 'castable' )
            // XQueryLexer.g:60:31: 'castable'
            {
            match("castable"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CASTABLE"

    // $ANTLR start "CHILD"
    public final void mCHILD() throws RecognitionException {
        try {
            int _type = CHILD;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:61:29: ( 'child' )
            // XQueryLexer.g:61:31: 'child'
            {
            match("child"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CHILD"

    // $ANTLR start "COLLATION"
    public final void mCOLLATION() throws RecognitionException {
        try {
            int _type = COLLATION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:62:29: ( 'collation' )
            // XQueryLexer.g:62:31: 'collation'
            {
            match("collation"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "COLLATION"

    // $ANTLR start "COMMENT"
    public final void mCOMMENT() throws RecognitionException {
        try {
            int _type = COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:63:29: ( 'comment' )
            // XQueryLexer.g:63:31: 'comment'
            {
            match("comment"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "COMMENT"

    // $ANTLR start "CONSTRUCTION"
    public final void mCONSTRUCTION() throws RecognitionException {
        try {
            int _type = CONSTRUCTION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:64:29: ( 'construction' )
            // XQueryLexer.g:64:31: 'construction'
            {
            match("construction"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CONSTRUCTION"

    // $ANTLR start "COPY_NAMESPACES"
    public final void mCOPY_NAMESPACES() throws RecognitionException {
        try {
            int _type = COPY_NAMESPACES;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:65:29: ( 'copy-namespaces' )
            // XQueryLexer.g:65:31: 'copy-namespaces'
            {
            match("copy-namespaces"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "COPY_NAMESPACES"

    // $ANTLR start "DECLARE"
    public final void mDECLARE() throws RecognitionException {
        try {
            int _type = DECLARE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:66:29: ( 'declare' )
            // XQueryLexer.g:66:31: 'declare'
            {
            match("declare"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DECLARE"

    // $ANTLR start "DEFAULT"
    public final void mDEFAULT() throws RecognitionException {
        try {
            int _type = DEFAULT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:67:29: ( 'default' )
            // XQueryLexer.g:67:31: 'default'
            {
            match("default"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DEFAULT"

    // $ANTLR start "DESCENDANT"
    public final void mDESCENDANT() throws RecognitionException {
        try {
            int _type = DESCENDANT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:68:29: ( 'descendant' )
            // XQueryLexer.g:68:31: 'descendant'
            {
            match("descendant"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DESCENDANT"

    // $ANTLR start "DESCENDANT_OR_SELF"
    public final void mDESCENDANT_OR_SELF() throws RecognitionException {
        try {
            int _type = DESCENDANT_OR_SELF;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:69:29: ( 'descendant-or-self' )
            // XQueryLexer.g:69:31: 'descendant-or-self'
            {
            match("descendant-or-self"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DESCENDANT_OR_SELF"

    // $ANTLR start "DESCENDING"
    public final void mDESCENDING() throws RecognitionException {
        try {
            int _type = DESCENDING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:70:29: ( 'descending' )
            // XQueryLexer.g:70:31: 'descending'
            {
            match("descending"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DESCENDING"

    // $ANTLR start "DIV"
    public final void mDIV() throws RecognitionException {
        try {
            int _type = DIV;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:71:29: ( 'div' )
            // XQueryLexer.g:71:31: 'div'
            {
            match("div"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DIV"

    // $ANTLR start "DOCUMENT"
    public final void mDOCUMENT() throws RecognitionException {
        try {
            int _type = DOCUMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:72:29: ( 'document' )
            // XQueryLexer.g:72:31: 'document'
            {
            match("document"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DOCUMENT"

    // $ANTLR start "DOCUMENT_NODE"
    public final void mDOCUMENT_NODE() throws RecognitionException {
        try {
            int _type = DOCUMENT_NODE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:73:29: ( 'document-node' )
            // XQueryLexer.g:73:31: 'document-node'
            {
            match("document-node"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DOCUMENT_NODE"

    // $ANTLR start "ELEMENT"
    public final void mELEMENT() throws RecognitionException {
        try {
            int _type = ELEMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:74:29: ( 'element' )
            // XQueryLexer.g:74:31: 'element'
            {
            match("element"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ELEMENT"

    // $ANTLR start "ELSE"
    public final void mELSE() throws RecognitionException {
        try {
            int _type = ELSE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:75:29: ( 'else' )
            // XQueryLexer.g:75:31: 'else'
            {
            match("else"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ELSE"

    // $ANTLR start "EMPTY"
    public final void mEMPTY() throws RecognitionException {
        try {
            int _type = EMPTY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:76:29: ( 'empty' )
            // XQueryLexer.g:76:31: 'empty'
            {
            match("empty"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "EMPTY"

    // $ANTLR start "EMPTY_SEQUENCE"
    public final void mEMPTY_SEQUENCE() throws RecognitionException {
        try {
            int _type = EMPTY_SEQUENCE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:77:29: ( 'empty-sequence' )
            // XQueryLexer.g:77:31: 'empty-sequence'
            {
            match("empty-sequence"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "EMPTY_SEQUENCE"

    // $ANTLR start "ENCODING"
    public final void mENCODING() throws RecognitionException {
        try {
            int _type = ENCODING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:78:29: ( 'encoding' )
            // XQueryLexer.g:78:31: 'encoding'
            {
            match("encoding"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ENCODING"

    // $ANTLR start "EQ"
    public final void mEQ() throws RecognitionException {
        try {
            int _type = EQ;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:79:29: ( 'eq' )
            // XQueryLexer.g:79:31: 'eq'
            {
            match("eq"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "EQ"

    // $ANTLR start "EVERY"
    public final void mEVERY() throws RecognitionException {
        try {
            int _type = EVERY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:80:29: ( 'every' )
            // XQueryLexer.g:80:31: 'every'
            {
            match("every"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "EVERY"

    // $ANTLR start "EXCEPT"
    public final void mEXCEPT() throws RecognitionException {
        try {
            int _type = EXCEPT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:81:29: ( 'except' )
            // XQueryLexer.g:81:31: 'except'
            {
            match("except"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "EXCEPT"

    // $ANTLR start "EXTERNAL"
    public final void mEXTERNAL() throws RecognitionException {
        try {
            int _type = EXTERNAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:82:29: ( 'external' )
            // XQueryLexer.g:82:31: 'external'
            {
            match("external"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "EXTERNAL"

    // $ANTLR start "FOLLOWING"
    public final void mFOLLOWING() throws RecognitionException {
        try {
            int _type = FOLLOWING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:83:29: ( 'following' )
            // XQueryLexer.g:83:31: 'following'
            {
            match("following"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "FOLLOWING"

    // $ANTLR start "FOLLOWING_SIBLING"
    public final void mFOLLOWING_SIBLING() throws RecognitionException {
        try {
            int _type = FOLLOWING_SIBLING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:84:29: ( 'following-sibling' )
            // XQueryLexer.g:84:31: 'following-sibling'
            {
            match("following-sibling"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "FOLLOWING_SIBLING"

    // $ANTLR start "FOR"
    public final void mFOR() throws RecognitionException {
        try {
            int _type = FOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:85:29: ( 'for' )
            // XQueryLexer.g:85:31: 'for'
            {
            match("for"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "FOR"

    // $ANTLR start "FUNCTION"
    public final void mFUNCTION() throws RecognitionException {
        try {
            int _type = FUNCTION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:86:29: ( 'function' )
            // XQueryLexer.g:86:31: 'function'
            {
            match("function"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "FUNCTION"

    // $ANTLR start "GE"
    public final void mGE() throws RecognitionException {
        try {
            int _type = GE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:87:29: ( 'ge' )
            // XQueryLexer.g:87:31: 'ge'
            {
            match("ge"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "GE"

    // $ANTLR start "GREATEST"
    public final void mGREATEST() throws RecognitionException {
        try {
            int _type = GREATEST;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:88:29: ( 'greatest' )
            // XQueryLexer.g:88:31: 'greatest'
            {
            match("greatest"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "GREATEST"

    // $ANTLR start "GT"
    public final void mGT() throws RecognitionException {
        try {
            int _type = GT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:89:29: ( 'gt' )
            // XQueryLexer.g:89:31: 'gt'
            {
            match("gt"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "GT"

    // $ANTLR start "IDIV"
    public final void mIDIV() throws RecognitionException {
        try {
            int _type = IDIV;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:90:29: ( 'idiv' )
            // XQueryLexer.g:90:31: 'idiv'
            {
            match("idiv"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "IDIV"

    // $ANTLR start "IF"
    public final void mIF() throws RecognitionException {
        try {
            int _type = IF;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:91:29: ( 'if' )
            // XQueryLexer.g:91:31: 'if'
            {
            match("if"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "IF"

    // $ANTLR start "IMPORT"
    public final void mIMPORT() throws RecognitionException {
        try {
            int _type = IMPORT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:92:29: ( 'import' )
            // XQueryLexer.g:92:31: 'import'
            {
            match("import"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "IMPORT"

    // $ANTLR start "IN"
    public final void mIN() throws RecognitionException {
        try {
            int _type = IN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:93:29: ( 'in' )
            // XQueryLexer.g:93:31: 'in'
            {
            match("in"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "IN"

    // $ANTLR start "INHERIT"
    public final void mINHERIT() throws RecognitionException {
        try {
            int _type = INHERIT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:94:29: ( 'inherit' )
            // XQueryLexer.g:94:31: 'inherit'
            {
            match("inherit"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "INHERIT"

    // $ANTLR start "INSTANCE"
    public final void mINSTANCE() throws RecognitionException {
        try {
            int _type = INSTANCE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:95:29: ( 'instance' )
            // XQueryLexer.g:95:31: 'instance'
            {
            match("instance"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "INSTANCE"

    // $ANTLR start "INTERSECT"
    public final void mINTERSECT() throws RecognitionException {
        try {
            int _type = INTERSECT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:96:29: ( 'intersect' )
            // XQueryLexer.g:96:31: 'intersect'
            {
            match("intersect"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "INTERSECT"

    // $ANTLR start "IS"
    public final void mIS() throws RecognitionException {
        try {
            int _type = IS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:97:29: ( 'is' )
            // XQueryLexer.g:97:31: 'is'
            {
            match("is"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "IS"

    // $ANTLR start "ITEM"
    public final void mITEM() throws RecognitionException {
        try {
            int _type = ITEM;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:98:29: ( 'item' )
            // XQueryLexer.g:98:31: 'item'
            {
            match("item"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ITEM"

    // $ANTLR start "LAX"
    public final void mLAX() throws RecognitionException {
        try {
            int _type = LAX;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:99:29: ( 'lax' )
            // XQueryLexer.g:99:31: 'lax'
            {
            match("lax"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LAX"

    // $ANTLR start "LE"
    public final void mLE() throws RecognitionException {
        try {
            int _type = LE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:100:29: ( 'le' )
            // XQueryLexer.g:100:31: 'le'
            {
            match("le"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LE"

    // $ANTLR start "LEAST"
    public final void mLEAST() throws RecognitionException {
        try {
            int _type = LEAST;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:101:29: ( 'least' )
            // XQueryLexer.g:101:31: 'least'
            {
            match("least"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LEAST"

    // $ANTLR start "LET"
    public final void mLET() throws RecognitionException {
        try {
            int _type = LET;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:102:29: ( 'let' )
            // XQueryLexer.g:102:31: 'let'
            {
            match("let"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LET"

    // $ANTLR start "LT"
    public final void mLT() throws RecognitionException {
        try {
            int _type = LT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:103:29: ( 'lt' )
            // XQueryLexer.g:103:31: 'lt'
            {
            match("lt"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LT"

    // $ANTLR start "MOD"
    public final void mMOD() throws RecognitionException {
        try {
            int _type = MOD;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:104:29: ( 'mod' )
            // XQueryLexer.g:104:31: 'mod'
            {
            match("mod"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "MOD"

    // $ANTLR start "MODULE"
    public final void mMODULE() throws RecognitionException {
        try {
            int _type = MODULE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:105:29: ( 'module' )
            // XQueryLexer.g:105:31: 'module'
            {
            match("module"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "MODULE"

    // $ANTLR start "NAMESPACE"
    public final void mNAMESPACE() throws RecognitionException {
        try {
            int _type = NAMESPACE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:106:29: ( 'namespace' )
            // XQueryLexer.g:106:31: 'namespace'
            {
            match("namespace"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NAMESPACE"

    // $ANTLR start "NE"
    public final void mNE() throws RecognitionException {
        try {
            int _type = NE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:107:29: ( 'ne' )
            // XQueryLexer.g:107:31: 'ne'
            {
            match("ne"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NE"

    // $ANTLR start "NO_INHERIT"
    public final void mNO_INHERIT() throws RecognitionException {
        try {
            int _type = NO_INHERIT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:108:29: ( 'no-inherit' )
            // XQueryLexer.g:108:31: 'no-inherit'
            {
            match("no-inherit"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NO_INHERIT"

    // $ANTLR start "NO_PRESERVE"
    public final void mNO_PRESERVE() throws RecognitionException {
        try {
            int _type = NO_PRESERVE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:109:29: ( 'no-preserve' )
            // XQueryLexer.g:109:31: 'no-preserve'
            {
            match("no-preserve"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NO_PRESERVE"

    // $ANTLR start "NODE"
    public final void mNODE() throws RecognitionException {
        try {
            int _type = NODE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:110:29: ( 'node' )
            // XQueryLexer.g:110:31: 'node'
            {
            match("node"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NODE"

    // $ANTLR start "OF"
    public final void mOF() throws RecognitionException {
        try {
            int _type = OF;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:111:29: ( 'of' )
            // XQueryLexer.g:111:31: 'of'
            {
            match("of"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "OF"

    // $ANTLR start "OPTION"
    public final void mOPTION() throws RecognitionException {
        try {
            int _type = OPTION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:112:29: ( 'option' )
            // XQueryLexer.g:112:31: 'option'
            {
            match("option"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "OPTION"

    // $ANTLR start "OR"
    public final void mOR() throws RecognitionException {
        try {
            int _type = OR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:113:29: ( 'or' )
            // XQueryLexer.g:113:31: 'or'
            {
            match("or"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "OR"

    // $ANTLR start "ORDER"
    public final void mORDER() throws RecognitionException {
        try {
            int _type = ORDER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:114:29: ( 'order' )
            // XQueryLexer.g:114:31: 'order'
            {
            match("order"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ORDER"

    // $ANTLR start "ORDERED"
    public final void mORDERED() throws RecognitionException {
        try {
            int _type = ORDERED;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:115:29: ( 'ordered' )
            // XQueryLexer.g:115:31: 'ordered'
            {
            match("ordered"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ORDERED"

    // $ANTLR start "ORDERING"
    public final void mORDERING() throws RecognitionException {
        try {
            int _type = ORDERING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:116:29: ( 'ordering' )
            // XQueryLexer.g:116:31: 'ordering'
            {
            match("ordering"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ORDERING"

    // $ANTLR start "PARENT"
    public final void mPARENT() throws RecognitionException {
        try {
            int _type = PARENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:117:29: ( 'parent' )
            // XQueryLexer.g:117:31: 'parent'
            {
            match("parent"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PARENT"

    // $ANTLR start "PRECEDING"
    public final void mPRECEDING() throws RecognitionException {
        try {
            int _type = PRECEDING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:118:29: ( 'preceding' )
            // XQueryLexer.g:118:31: 'preceding'
            {
            match("preceding"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PRECEDING"

    // $ANTLR start "PRECEDING_SIBLING"
    public final void mPRECEDING_SIBLING() throws RecognitionException {
        try {
            int _type = PRECEDING_SIBLING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:119:29: ( 'preceding-sibling' )
            // XQueryLexer.g:119:31: 'preceding-sibling'
            {
            match("preceding-sibling"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PRECEDING_SIBLING"

    // $ANTLR start "PRESERVE"
    public final void mPRESERVE() throws RecognitionException {
        try {
            int _type = PRESERVE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:120:29: ( 'preserve' )
            // XQueryLexer.g:120:31: 'preserve'
            {
            match("preserve"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PRESERVE"

    // $ANTLR start "PROCESSING_INSTRUCTION"
    public final void mPROCESSING_INSTRUCTION() throws RecognitionException {
        try {
            int _type = PROCESSING_INSTRUCTION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:121:29: ( 'processing-instruction' )
            // XQueryLexer.g:121:31: 'processing-instruction'
            {
            match("processing-instruction"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PROCESSING_INSTRUCTION"

    // $ANTLR start "RETURN"
    public final void mRETURN() throws RecognitionException {
        try {
            int _type = RETURN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:122:29: ( 'return' )
            // XQueryLexer.g:122:31: 'return'
            {
            match("return"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RETURN"

    // $ANTLR start "SATISFIES"
    public final void mSATISFIES() throws RecognitionException {
        try {
            int _type = SATISFIES;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:123:29: ( 'satisfies' )
            // XQueryLexer.g:123:31: 'satisfies'
            {
            match("satisfies"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SATISFIES"

    // $ANTLR start "SCHEMA"
    public final void mSCHEMA() throws RecognitionException {
        try {
            int _type = SCHEMA;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:124:29: ( 'schema' )
            // XQueryLexer.g:124:31: 'schema'
            {
            match("schema"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SCHEMA"

    // $ANTLR start "SCHEMA_ATTRIBUTE"
    public final void mSCHEMA_ATTRIBUTE() throws RecognitionException {
        try {
            int _type = SCHEMA_ATTRIBUTE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:125:29: ( 'schema-attribute' )
            // XQueryLexer.g:125:31: 'schema-attribute'
            {
            match("schema-attribute"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SCHEMA_ATTRIBUTE"

    // $ANTLR start "SCHEMA_ELEMENT"
    public final void mSCHEMA_ELEMENT() throws RecognitionException {
        try {
            int _type = SCHEMA_ELEMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:126:29: ( 'schema-element' )
            // XQueryLexer.g:126:31: 'schema-element'
            {
            match("schema-element"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SCHEMA_ELEMENT"

    // $ANTLR start "SELF"
    public final void mSELF() throws RecognitionException {
        try {
            int _type = SELF;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:127:29: ( 'self' )
            // XQueryLexer.g:127:31: 'self'
            {
            match("self"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SELF"

    // $ANTLR start "SOME"
    public final void mSOME() throws RecognitionException {
        try {
            int _type = SOME;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:128:29: ( 'some' )
            // XQueryLexer.g:128:31: 'some'
            {
            match("some"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SOME"

    // $ANTLR start "STABLE"
    public final void mSTABLE() throws RecognitionException {
        try {
            int _type = STABLE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:129:29: ( 'stable' )
            // XQueryLexer.g:129:31: 'stable'
            {
            match("stable"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "STABLE"

    // $ANTLR start "STRICT"
    public final void mSTRICT() throws RecognitionException {
        try {
            int _type = STRICT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:130:29: ( 'strict' )
            // XQueryLexer.g:130:31: 'strict'
            {
            match("strict"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "STRICT"

    // $ANTLR start "STRIP"
    public final void mSTRIP() throws RecognitionException {
        try {
            int _type = STRIP;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:131:29: ( 'strip' )
            // XQueryLexer.g:131:31: 'strip'
            {
            match("strip"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "STRIP"

    // $ANTLR start "TEXT"
    public final void mTEXT() throws RecognitionException {
        try {
            int _type = TEXT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:132:29: ( 'text' )
            // XQueryLexer.g:132:31: 'text'
            {
            match("text"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "TEXT"

    // $ANTLR start "THEN"
    public final void mTHEN() throws RecognitionException {
        try {
            int _type = THEN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:133:29: ( 'then' )
            // XQueryLexer.g:133:31: 'then'
            {
            match("then"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "THEN"

    // $ANTLR start "TO"
    public final void mTO() throws RecognitionException {
        try {
            int _type = TO;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:134:29: ( 'to' )
            // XQueryLexer.g:134:31: 'to'
            {
            match("to"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "TO"

    // $ANTLR start "TREAT"
    public final void mTREAT() throws RecognitionException {
        try {
            int _type = TREAT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:135:29: ( 'treat' )
            // XQueryLexer.g:135:31: 'treat'
            {
            match("treat"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "TREAT"

    // $ANTLR start "TYPESWITCH"
    public final void mTYPESWITCH() throws RecognitionException {
        try {
            int _type = TYPESWITCH;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:136:29: ( 'typeswitch' )
            // XQueryLexer.g:136:31: 'typeswitch'
            {
            match("typeswitch"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "TYPESWITCH"

    // $ANTLR start "UNION"
    public final void mUNION() throws RecognitionException {
        try {
            int _type = UNION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:137:29: ( 'union' )
            // XQueryLexer.g:137:31: 'union'
            {
            match("union"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "UNION"

    // $ANTLR start "UNORDERED"
    public final void mUNORDERED() throws RecognitionException {
        try {
            int _type = UNORDERED;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:138:29: ( 'unordered' )
            // XQueryLexer.g:138:31: 'unordered'
            {
            match("unordered"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "UNORDERED"

    // $ANTLR start "VALIDATE"
    public final void mVALIDATE() throws RecognitionException {
        try {
            int _type = VALIDATE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:139:29: ( 'validate' )
            // XQueryLexer.g:139:31: 'validate'
            {
            match("validate"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "VALIDATE"

    // $ANTLR start "VARIABLE"
    public final void mVARIABLE() throws RecognitionException {
        try {
            int _type = VARIABLE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:140:29: ( 'variable' )
            // XQueryLexer.g:140:31: 'variable'
            {
            match("variable"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "VARIABLE"

    // $ANTLR start "VERSION"
    public final void mVERSION() throws RecognitionException {
        try {
            int _type = VERSION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:141:29: ( 'version' )
            // XQueryLexer.g:141:31: 'version'
            {
            match("version"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "VERSION"

    // $ANTLR start "WHERE"
    public final void mWHERE() throws RecognitionException {
        try {
            int _type = WHERE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:142:29: ( 'where' )
            // XQueryLexer.g:142:31: 'where'
            {
            match("where"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "WHERE"

    // $ANTLR start "XQUERY"
    public final void mXQUERY() throws RecognitionException {
        try {
            int _type = XQUERY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:143:29: ( 'xquery' )
            // XQueryLexer.g:143:31: 'xquery'
            {
            match("xquery"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "XQUERY"

    // $ANTLR start "CATCH"
    public final void mCATCH() throws RecognitionException {
        try {
            int _type = CATCH;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:145:29: ( 'catch' )
            // XQueryLexer.g:145:31: 'catch'
            {
            match("catch"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CATCH"

    // $ANTLR start "CONTEXT"
    public final void mCONTEXT() throws RecognitionException {
        try {
            int _type = CONTEXT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:146:29: ( 'context' )
            // XQueryLexer.g:146:31: 'context'
            {
            match("context"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CONTEXT"

    // $ANTLR start "COUNT"
    public final void mCOUNT() throws RecognitionException {
        try {
            int _type = COUNT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:147:29: ( 'count' )
            // XQueryLexer.g:147:31: 'count'
            {
            match("count"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "COUNT"

    // $ANTLR start "DECIMAL_FORMAT"
    public final void mDECIMAL_FORMAT() throws RecognitionException {
        try {
            int _type = DECIMAL_FORMAT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:148:29: ( 'decimal-format' )
            // XQueryLexer.g:148:31: 'decimal-format'
            {
            match("decimal-format"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DECIMAL_FORMAT"

    // $ANTLR start "DECIMAL_SEPARATOR"
    public final void mDECIMAL_SEPARATOR() throws RecognitionException {
        try {
            int _type = DECIMAL_SEPARATOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:149:29: ( 'decimal-separator' )
            // XQueryLexer.g:149:31: 'decimal-separator'
            {
            match("decimal-separator"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DECIMAL_SEPARATOR"

    // $ANTLR start "DETERMINISTIC"
    public final void mDETERMINISTIC() throws RecognitionException {
        try {
            int _type = DETERMINISTIC;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:150:29: ( 'deterministic' )
            // XQueryLexer.g:150:31: 'deterministic'
            {
            match("deterministic"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DETERMINISTIC"

    // $ANTLR start "DIGIT"
    public final void mDIGIT() throws RecognitionException {
        try {
            int _type = DIGIT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:151:29: ( 'digit' )
            // XQueryLexer.g:151:31: 'digit'
            {
            match("digit"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DIGIT"

    // $ANTLR start "END"
    public final void mEND() throws RecognitionException {
        try {
            int _type = END;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:152:29: ( 'end' )
            // XQueryLexer.g:152:31: 'end'
            {
            match("end"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "END"

    // $ANTLR start "GROUP"
    public final void mGROUP() throws RecognitionException {
        try {
            int _type = GROUP;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:153:29: ( 'group' )
            // XQueryLexer.g:153:31: 'group'
            {
            match("group"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "GROUP"

    // $ANTLR start "GROUPING_SEPARATOR"
    public final void mGROUPING_SEPARATOR() throws RecognitionException {
        try {
            int _type = GROUPING_SEPARATOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:154:29: ( 'grouping-separator' )
            // XQueryLexer.g:154:31: 'grouping-separator'
            {
            match("grouping-separator"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "GROUPING_SEPARATOR"

    // $ANTLR start "INFINITY"
    public final void mINFINITY() throws RecognitionException {
        try {
            int _type = INFINITY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:155:29: ( 'infinity' )
            // XQueryLexer.g:155:31: 'infinity'
            {
            match("infinity"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "INFINITY"

    // $ANTLR start "MINUS_SIGN"
    public final void mMINUS_SIGN() throws RecognitionException {
        try {
            int _type = MINUS_SIGN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:156:29: ( 'minus-sign' )
            // XQueryLexer.g:156:31: 'minus-sign'
            {
            match("minus-sign"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "MINUS_SIGN"

    // $ANTLR start "NAMESPACE_NODE"
    public final void mNAMESPACE_NODE() throws RecognitionException {
        try {
            int _type = NAMESPACE_NODE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:157:29: ( 'namespace-node' )
            // XQueryLexer.g:157:31: 'namespace-node'
            {
            match("namespace-node"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NAMESPACE_NODE"

    // $ANTLR start "NAN"
    public final void mNAN() throws RecognitionException {
        try {
            int _type = NAN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:158:29: ( 'NaN' )
            // XQueryLexer.g:158:31: 'NaN'
            {
            match("NaN"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NAN"

    // $ANTLR start "NEXT"
    public final void mNEXT() throws RecognitionException {
        try {
            int _type = NEXT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:159:29: ( 'next' )
            // XQueryLexer.g:159:31: 'next'
            {
            match("next"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NEXT"

    // $ANTLR start "NONDETERMINISTIC"
    public final void mNONDETERMINISTIC() throws RecognitionException {
        try {
            int _type = NONDETERMINISTIC;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:160:29: ( 'nondeterministic' )
            // XQueryLexer.g:160:31: 'nondeterministic'
            {
            match("nondeterministic"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NONDETERMINISTIC"

    // $ANTLR start "ONLY"
    public final void mONLY() throws RecognitionException {
        try {
            int _type = ONLY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:161:29: ( 'only' )
            // XQueryLexer.g:161:31: 'only'
            {
            match("only"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ONLY"

    // $ANTLR start "OUTER"
    public final void mOUTER() throws RecognitionException {
        try {
            int _type = OUTER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:162:29: ( 'outer' )
            // XQueryLexer.g:162:31: 'outer'
            {
            match("outer"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "OUTER"

    // $ANTLR start "PATTERN_SEPARATOR"
    public final void mPATTERN_SEPARATOR() throws RecognitionException {
        try {
            int _type = PATTERN_SEPARATOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:163:29: ( 'pattern-separator' )
            // XQueryLexer.g:163:31: 'pattern-separator'
            {
            match("pattern-separator"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PATTERN_SEPARATOR"

    // $ANTLR start "PERCENT"
    public final void mPERCENT() throws RecognitionException {
        try {
            int _type = PERCENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:164:29: ( 'percent' )
            // XQueryLexer.g:164:31: 'percent'
            {
            match("percent"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PERCENT"

    // $ANTLR start "PER_MILLE"
    public final void mPER_MILLE() throws RecognitionException {
        try {
            int _type = PER_MILLE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:165:29: ( 'per-mille' )
            // XQueryLexer.g:165:31: 'per-mille'
            {
            match("per-mille"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PER_MILLE"

    // $ANTLR start "PRIVATE"
    public final void mPRIVATE() throws RecognitionException {
        try {
            int _type = PRIVATE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:166:29: ( 'private' )
            // XQueryLexer.g:166:31: 'private'
            {
            match("private"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PRIVATE"

    // $ANTLR start "PUBLIC"
    public final void mPUBLIC() throws RecognitionException {
        try {
            int _type = PUBLIC;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:167:29: ( 'public' )
            // XQueryLexer.g:167:31: 'public'
            {
            match("public"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PUBLIC"

    // $ANTLR start "PREVIOUS"
    public final void mPREVIOUS() throws RecognitionException {
        try {
            int _type = PREVIOUS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:168:29: ( 'previous' )
            // XQueryLexer.g:168:31: 'previous'
            {
            match("previous"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PREVIOUS"

    // $ANTLR start "SLIDING"
    public final void mSLIDING() throws RecognitionException {
        try {
            int _type = SLIDING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:169:29: ( 'sliding' )
            // XQueryLexer.g:169:31: 'sliding'
            {
            match("sliding"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SLIDING"

    // $ANTLR start "START"
    public final void mSTART() throws RecognitionException {
        try {
            int _type = START;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:170:29: ( 'start' )
            // XQueryLexer.g:170:31: 'start'
            {
            match("start"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "START"

    // $ANTLR start "TRY"
    public final void mTRY() throws RecognitionException {
        try {
            int _type = TRY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:171:29: ( 'try' )
            // XQueryLexer.g:171:31: 'try'
            {
            match("try"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "TRY"

    // $ANTLR start "TUMBLING"
    public final void mTUMBLING() throws RecognitionException {
        try {
            int _type = TUMBLING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:172:29: ( 'tumbling' )
            // XQueryLexer.g:172:31: 'tumbling'
            {
            match("tumbling"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "TUMBLING"

    // $ANTLR start "WHEN"
    public final void mWHEN() throws RecognitionException {
        try {
            int _type = WHEN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:173:29: ( 'when' )
            // XQueryLexer.g:173:31: 'when'
            {
            match("when"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "WHEN"

    // $ANTLR start "WINDOW"
    public final void mWINDOW() throws RecognitionException {
        try {
            int _type = WINDOW;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:174:29: ( 'window' )
            // XQueryLexer.g:174:31: 'window'
            {
            match("window"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "WINDOW"

    // $ANTLR start "ZERO_DIGIT"
    public final void mZERO_DIGIT() throws RecognitionException {
        try {
            int _type = ZERO_DIGIT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:175:29: ( 'zero-digit' )
            // XQueryLexer.g:175:31: 'zero-digit'
            {
            match("zero-digit"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ZERO_DIGIT"

    // $ANTLR start "AFTER"
    public final void mAFTER() throws RecognitionException {
        try {
            int _type = AFTER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:177:29: ( 'after' )
            // XQueryLexer.g:177:31: 'after'
            {
            match("after"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "AFTER"

    // $ANTLR start "BEFORE"
    public final void mBEFORE() throws RecognitionException {
        try {
            int _type = BEFORE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:178:29: ( 'before' )
            // XQueryLexer.g:178:31: 'before'
            {
            match("before"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "BEFORE"

    // $ANTLR start "COPY"
    public final void mCOPY() throws RecognitionException {
        try {
            int _type = COPY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:179:29: ( 'copy' )
            // XQueryLexer.g:179:31: 'copy'
            {
            match("copy"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "COPY"

    // $ANTLR start "DELETE"
    public final void mDELETE() throws RecognitionException {
        try {
            int _type = DELETE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:180:29: ( 'delete' )
            // XQueryLexer.g:180:31: 'delete'
            {
            match("delete"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DELETE"

    // $ANTLR start "FIRST"
    public final void mFIRST() throws RecognitionException {
        try {
            int _type = FIRST;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:181:29: ( 'first' )
            // XQueryLexer.g:181:31: 'first'
            {
            match("first"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "FIRST"

    // $ANTLR start "INSERT"
    public final void mINSERT() throws RecognitionException {
        try {
            int _type = INSERT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:182:29: ( 'insert' )
            // XQueryLexer.g:182:31: 'insert'
            {
            match("insert"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "INSERT"

    // $ANTLR start "INTO"
    public final void mINTO() throws RecognitionException {
        try {
            int _type = INTO;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:183:29: ( 'into' )
            // XQueryLexer.g:183:31: 'into'
            {
            match("into"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "INTO"

    // $ANTLR start "LAST"
    public final void mLAST() throws RecognitionException {
        try {
            int _type = LAST;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:184:29: ( 'last' )
            // XQueryLexer.g:184:31: 'last'
            {
            match("last"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LAST"

    // $ANTLR start "MODIFY"
    public final void mMODIFY() throws RecognitionException {
        try {
            int _type = MODIFY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:185:29: ( 'modify' )
            // XQueryLexer.g:185:31: 'modify'
            {
            match("modify"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "MODIFY"

    // $ANTLR start "NODES"
    public final void mNODES() throws RecognitionException {
        try {
            int _type = NODES;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:186:29: ( 'nodes' )
            // XQueryLexer.g:186:31: 'nodes'
            {
            match("nodes"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NODES"

    // $ANTLR start "RENAME"
    public final void mRENAME() throws RecognitionException {
        try {
            int _type = RENAME;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:187:29: ( 'rename' )
            // XQueryLexer.g:187:31: 'rename'
            {
            match("rename"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RENAME"

    // $ANTLR start "REPLACE"
    public final void mREPLACE() throws RecognitionException {
        try {
            int _type = REPLACE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:188:29: ( 'replace' )
            // XQueryLexer.g:188:31: 'replace'
            {
            match("replace"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "REPLACE"

    // $ANTLR start "REVALIDATION"
    public final void mREVALIDATION() throws RecognitionException {
        try {
            int _type = REVALIDATION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:189:29: ( 'revalidation' )
            // XQueryLexer.g:189:31: 'revalidation'
            {
            match("revalidation"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "REVALIDATION"

    // $ANTLR start "SKIP"
    public final void mSKIP() throws RecognitionException {
        try {
            int _type = SKIP;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:190:29: ( 'skip' )
            // XQueryLexer.g:190:31: 'skip'
            {
            match("skip"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SKIP"

    // $ANTLR start "UPDATING"
    public final void mUPDATING() throws RecognitionException {
        try {
            int _type = UPDATING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:191:29: ( 'updating' )
            // XQueryLexer.g:191:31: 'updating'
            {
            match("updating"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "UPDATING"

    // $ANTLR start "VALUE"
    public final void mVALUE() throws RecognitionException {
        try {
            int _type = VALUE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:192:29: ( 'value' )
            // XQueryLexer.g:192:31: 'value'
            {
            match("value"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "VALUE"

    // $ANTLR start "WITH"
    public final void mWITH() throws RecognitionException {
        try {
            int _type = WITH;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:193:29: ( 'with' )
            // XQueryLexer.g:193:31: 'with'
            {
            match("with"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "WITH"

    // $ANTLR start "BLOCK"
    public final void mBLOCK() throws RecognitionException {
        try {
            int _type = BLOCK;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:195:29: ( 'block' )
            // XQueryLexer.g:195:31: 'block'
            {
            match("block"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "BLOCK"

    // $ANTLR start "CONSTANT"
    public final void mCONSTANT() throws RecognitionException {
        try {
            int _type = CONSTANT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:196:29: ( 'constant' )
            // XQueryLexer.g:196:31: 'constant'
            {
            match("constant"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CONSTANT"

    // $ANTLR start "EXIT"
    public final void mEXIT() throws RecognitionException {
        try {
            int _type = EXIT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:197:29: ( 'exit' )
            // XQueryLexer.g:197:31: 'exit'
            {
            match("exit"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "EXIT"

    // $ANTLR start "RETURNING"
    public final void mRETURNING() throws RecognitionException {
        try {
            int _type = RETURNING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:198:29: ( 'returning' )
            // XQueryLexer.g:198:31: 'returning'
            {
            match("returning"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RETURNING"

    // $ANTLR start "SEQUENTIAL"
    public final void mSEQUENTIAL() throws RecognitionException {
        try {
            int _type = SEQUENTIAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:199:29: ( 'sequential' )
            // XQueryLexer.g:199:31: 'sequential'
            {
            match("sequential"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SEQUENTIAL"

    // $ANTLR start "SET"
    public final void mSET() throws RecognitionException {
        try {
            int _type = SET;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:200:29: ( 'set' )
            // XQueryLexer.g:200:31: 'set'
            {
            match("set"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SET"

    // $ANTLR start "SIMPLE"
    public final void mSIMPLE() throws RecognitionException {
        try {
            int _type = SIMPLE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:201:29: ( 'simple' )
            // XQueryLexer.g:201:31: 'simple'
            {
            match("simple"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SIMPLE"

    // $ANTLR start "WHILE"
    public final void mWHILE() throws RecognitionException {
        try {
            int _type = WHILE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:202:29: ( 'while' )
            // XQueryLexer.g:202:31: 'while'
            {
            match("while"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "WHILE"

    // $ANTLR start "EVAL"
    public final void mEVAL() throws RecognitionException {
        try {
            int _type = EVAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:204:29: ( 'eval' )
            // XQueryLexer.g:204:31: 'eval'
            {
            match("eval"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "EVAL"

    // $ANTLR start "USING"
    public final void mUSING() throws RecognitionException {
        try {
            int _type = USING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:205:29: ( 'using' )
            // XQueryLexer.g:205:31: 'using'
            {
            match("using"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "USING"

    // $ANTLR start "APPEND_ONLY"
    public final void mAPPEND_ONLY() throws RecognitionException {
        try {
            int _type = APPEND_ONLY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:207:29: ( 'append-only' )
            // XQueryLexer.g:207:31: 'append-only'
            {
            match("append-only"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "APPEND_ONLY"

    // $ANTLR start "AUTOMATICALLY"
    public final void mAUTOMATICALLY() throws RecognitionException {
        try {
            int _type = AUTOMATICALLY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:208:29: ( 'automatically' )
            // XQueryLexer.g:208:31: 'automatically'
            {
            match("automatically"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "AUTOMATICALLY"

    // $ANTLR start "CHECK"
    public final void mCHECK() throws RecognitionException {
        try {
            int _type = CHECK;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:209:29: ( 'check' )
            // XQueryLexer.g:209:31: 'check'
            {
            match("check"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CHECK"

    // $ANTLR start "COLLECTION"
    public final void mCOLLECTION() throws RecognitionException {
        try {
            int _type = COLLECTION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:210:29: ( 'collection' )
            // XQueryLexer.g:210:31: 'collection'
            {
            match("collection"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "COLLECTION"

    // $ANTLR start "CONSTRAINT"
    public final void mCONSTRAINT() throws RecognitionException {
        try {
            int _type = CONSTRAINT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:211:29: ( 'constraint' )
            // XQueryLexer.g:211:31: 'constraint'
            {
            match("constraint"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CONSTRAINT"

    // $ANTLR start "CONST"
    public final void mCONST() throws RecognitionException {
        try {
            int _type = CONST;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:212:29: ( 'const' )
            // XQueryLexer.g:212:31: 'const'
            {
            match("const"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CONST"

    // $ANTLR start "EQUALITY"
    public final void mEQUALITY() throws RecognitionException {
        try {
            int _type = EQUALITY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:213:29: ( 'equality' )
            // XQueryLexer.g:213:31: 'equality'
            {
            match("equality"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "EQUALITY"

    // $ANTLR start "FOREACH"
    public final void mFOREACH() throws RecognitionException {
        try {
            int _type = FOREACH;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:214:29: ( 'foreach' )
            // XQueryLexer.g:214:31: 'foreach'
            {
            match("foreach"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "FOREACH"

    // $ANTLR start "FOREIGN"
    public final void mFOREIGN() throws RecognitionException {
        try {
            int _type = FOREIGN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:215:29: ( 'foreign' )
            // XQueryLexer.g:215:31: 'foreign'
            {
            match("foreign"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "FOREIGN"

    // $ANTLR start "FROM"
    public final void mFROM() throws RecognitionException {
        try {
            int _type = FROM;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:216:29: ( 'from' )
            // XQueryLexer.g:216:31: 'from'
            {
            match("from"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "FROM"

    // $ANTLR start "INDEX"
    public final void mINDEX() throws RecognitionException {
        try {
            int _type = INDEX;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:217:29: ( 'index' )
            // XQueryLexer.g:217:31: 'index'
            {
            match("index"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "INDEX"

    // $ANTLR start "INTEGRITY"
    public final void mINTEGRITY() throws RecognitionException {
        try {
            int _type = INTEGRITY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:218:29: ( 'integrity' )
            // XQueryLexer.g:218:31: 'integrity'
            {
            match("integrity"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "INTEGRITY"

    // $ANTLR start "KEY"
    public final void mKEY() throws RecognitionException {
        try {
            int _type = KEY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:219:29: ( 'key' )
            // XQueryLexer.g:219:31: 'key'
            {
            match("key"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "KEY"

    // $ANTLR start "MAINTAINED"
    public final void mMAINTAINED() throws RecognitionException {
        try {
            int _type = MAINTAINED;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:220:29: ( 'maintained' )
            // XQueryLexer.g:220:31: 'maintained'
            {
            match("maintained"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "MAINTAINED"

    // $ANTLR start "MANUALLY"
    public final void mMANUALLY() throws RecognitionException {
        try {
            int _type = MANUALLY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:221:29: ( 'manually' )
            // XQueryLexer.g:221:31: 'manually'
            {
            match("manually"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "MANUALLY"

    // $ANTLR start "MUTABLE"
    public final void mMUTABLE() throws RecognitionException {
        try {
            int _type = MUTABLE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:222:29: ( 'mutable' )
            // XQueryLexer.g:222:31: 'mutable'
            {
            match("mutable"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "MUTABLE"

    // $ANTLR start "NON"
    public final void mNON() throws RecognitionException {
        try {
            int _type = NON;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:223:29: ( 'non' )
            // XQueryLexer.g:223:31: 'non'
            {
            match("non"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NON"

    // $ANTLR start "ON"
    public final void mON() throws RecognitionException {
        try {
            int _type = ON;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:224:29: ( 'on' )
            // XQueryLexer.g:224:31: 'on'
            {
            match("on"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ON"

    // $ANTLR start "QUEUE"
    public final void mQUEUE() throws RecognitionException {
        try {
            int _type = QUEUE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:225:29: ( 'queue' )
            // XQueryLexer.g:225:31: 'queue'
            {
            match("queue"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "QUEUE"

    // $ANTLR start "RANGE"
    public final void mRANGE() throws RecognitionException {
        try {
            int _type = RANGE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:226:29: ( 'range' )
            // XQueryLexer.g:226:31: 'range'
            {
            match("range"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RANGE"

    // $ANTLR start "READ_ONLY"
    public final void mREAD_ONLY() throws RecognitionException {
        try {
            int _type = READ_ONLY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:227:29: ( 'read-only' )
            // XQueryLexer.g:227:31: 'read-only'
            {
            match("read-only"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "READ_ONLY"

    // $ANTLR start "UNIQUE"
    public final void mUNIQUE() throws RecognitionException {
        try {
            int _type = UNIQUE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:228:29: ( 'unique' )
            // XQueryLexer.g:228:31: 'unique'
            {
            match("unique"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "UNIQUE"

    // $ANTLR start "BINARY"
    public final void mBINARY() throws RecognitionException {
        try {
            int _type = BINARY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:230:29: ( 'binary' )
            // XQueryLexer.g:230:31: 'binary'
            {
            match("binary"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "BINARY"

    // $ANTLR start "AMP_ER"
    public final void mAMP_ER() throws RecognitionException {
        try {
            int _type = AMP_ER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:233:9: ( 'amp' )
            // XQueryLexer.g:233:11: 'amp'
            {
            match("amp"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "AMP_ER"

    // $ANTLR start "APOS_ER"
    public final void mAPOS_ER() throws RecognitionException {
        try {
            int _type = APOS_ER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:234:9: ( 'apos' )
            // XQueryLexer.g:234:11: 'apos'
            {
            match("apos"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "APOS_ER"

    // $ANTLR start "QUOT_ER"
    public final void mQUOT_ER() throws RecognitionException {
        try {
            int _type = QUOT_ER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:235:9: ( 'quot' )
            // XQueryLexer.g:235:11: 'quot'
            {
            match("quot"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "QUOT_ER"

    // $ANTLR start "LPAREN"
    public final void mLPAREN() throws RecognitionException {
        try {
            int _type = LPAREN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:242:25: ( '(' )
            // XQueryLexer.g:242:27: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LPAREN"

    // $ANTLR start "RPAREN"
    public final void mRPAREN() throws RecognitionException {
        try {
            int _type = RPAREN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:243:25: ( ')' )
            // XQueryLexer.g:243:27: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RPAREN"

    // $ANTLR start "DOLLAR"
    public final void mDOLLAR() throws RecognitionException {
        try {
            int _type = DOLLAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:244:25: ( '$' )
            // XQueryLexer.g:244:27: '$'
            {
            match('$'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DOLLAR"

    // $ANTLR start "LBRACKET"
    public final void mLBRACKET() throws RecognitionException {
        try {
            int _type = LBRACKET;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:245:25: ( '{' )
            // XQueryLexer.g:245:27: '{'
            {
            match('{'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LBRACKET"

    // $ANTLR start "RBRACKET"
    public final void mRBRACKET() throws RecognitionException {
        try {
            int _type = RBRACKET;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:246:25: ( '}' )
            // XQueryLexer.g:246:27: '}'
            {
            match('}'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RBRACKET"

    // $ANTLR start "LSQUARE"
    public final void mLSQUARE() throws RecognitionException {
        try {
            int _type = LSQUARE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:247:25: ( '[' )
            // XQueryLexer.g:247:27: '['
            {
            match('['); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LSQUARE"

    // $ANTLR start "RSQUARE"
    public final void mRSQUARE() throws RecognitionException {
        try {
            int _type = RSQUARE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:248:25: ( ']' )
            // XQueryLexer.g:248:27: ']'
            {
            match(']'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RSQUARE"

    // $ANTLR start "EQUAL"
    public final void mEQUAL() throws RecognitionException {
        try {
            int _type = EQUAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:249:25: ( '=' )
            // XQueryLexer.g:249:27: '='
            {
            match('='); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "EQUAL"

    // $ANTLR start "BIND"
    public final void mBIND() throws RecognitionException {
        try {
            int _type = BIND;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:250:25: ( ':=' )
            // XQueryLexer.g:250:27: ':='
            {
            match(":="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "BIND"

    // $ANTLR start "NOTEQUAL"
    public final void mNOTEQUAL() throws RecognitionException {
        try {
            int _type = NOTEQUAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:251:25: ( '!=' )
            // XQueryLexer.g:251:27: '!='
            {
            match("!="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NOTEQUAL"

    // $ANTLR start "AMP"
    public final void mAMP() throws RecognitionException {
        try {
            int _type = AMP;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:252:25: ( '&' )
            // XQueryLexer.g:252:27: '&'
            {
            match('&'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "AMP"

    // $ANTLR start "COMMA"
    public final void mCOMMA() throws RecognitionException {
        try {
            int _type = COMMA;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:253:25: ( ',' )
            // XQueryLexer.g:253:27: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "COMMA"

    // $ANTLR start "QUESTION"
    public final void mQUESTION() throws RecognitionException {
        try {
            int _type = QUESTION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:254:25: ( '?' )
            // XQueryLexer.g:254:27: '?'
            {
            match('?'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "QUESTION"

    // $ANTLR start "STAR"
    public final void mSTAR() throws RecognitionException {
        try {
            int _type = STAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:255:25: ( '*' )
            // XQueryLexer.g:255:27: '*'
            {
            match('*'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "STAR"

    // $ANTLR start "PLUS"
    public final void mPLUS() throws RecognitionException {
        try {
            int _type = PLUS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:256:25: ( '+' )
            // XQueryLexer.g:256:27: '+'
            {
            match('+'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PLUS"

    // $ANTLR start "MINUS"
    public final void mMINUS() throws RecognitionException {
        try {
            int _type = MINUS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:257:25: ( '-' )
            // XQueryLexer.g:257:27: '-'
            {
            match('-'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "MINUS"

    // $ANTLR start "SMALLER"
    public final void mSMALLER() throws RecognitionException {
        try {
            int _type = SMALLER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:258:25: ( '<' )
            // XQueryLexer.g:258:27: '<'
            {
            match('<'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SMALLER"

    // $ANTLR start "GREATER"
    public final void mGREATER() throws RecognitionException {
        try {
            int _type = GREATER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:259:25: ( '>' )
            // XQueryLexer.g:259:27: '>'
            {
            match('>'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "GREATER"

    // $ANTLR start "SMALLEREQ"
    public final void mSMALLEREQ() throws RecognitionException {
        try {
            int _type = SMALLEREQ;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:260:25: ( '<=' )
            // XQueryLexer.g:260:27: '<='
            {
            match("<="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SMALLEREQ"

    // $ANTLR start "GREATEREQ"
    public final void mGREATEREQ() throws RecognitionException {
        try {
            int _type = GREATEREQ;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:261:25: ( '>=' )
            // XQueryLexer.g:261:27: '>='
            {
            match(">="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "GREATEREQ"

    // $ANTLR start "SMALLER_SMALLER"
    public final void mSMALLER_SMALLER() throws RecognitionException {
        try {
            int _type = SMALLER_SMALLER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:262:25: ( '<<' )
            // XQueryLexer.g:262:27: '<<'
            {
            match("<<"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SMALLER_SMALLER"

    // $ANTLR start "GREATER_GREATER"
    public final void mGREATER_GREATER() throws RecognitionException {
        try {
            int _type = GREATER_GREATER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:263:25: ( '>>' )
            // XQueryLexer.g:263:27: '>>'
            {
            match(">>"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "GREATER_GREATER"

    // $ANTLR start "SLASH"
    public final void mSLASH() throws RecognitionException {
        try {
            int _type = SLASH;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:264:25: ( '/' )
            // XQueryLexer.g:264:27: '/'
            {
            match('/'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SLASH"

    // $ANTLR start "SLASH_SLASH"
    public final void mSLASH_SLASH() throws RecognitionException {
        try {
            int _type = SLASH_SLASH;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:265:25: ( '//' )
            // XQueryLexer.g:265:27: '//'
            {
            match("//"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SLASH_SLASH"

    // $ANTLR start "DOT"
    public final void mDOT() throws RecognitionException {
        try {
            int _type = DOT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:266:25: ( '.' )
            // XQueryLexer.g:266:27: '.'
            {
            match('.'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DOT"

    // $ANTLR start "DOT_DOT"
    public final void mDOT_DOT() throws RecognitionException {
        try {
            int _type = DOT_DOT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:267:25: ( '..' )
            // XQueryLexer.g:267:27: '..'
            {
            match(".."); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DOT_DOT"

    // $ANTLR start "COLON"
    public final void mCOLON() throws RecognitionException {
        try {
            int _type = COLON;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:268:25: ( ':' )
            // XQueryLexer.g:268:27: ':'
            {
            match(':'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "COLON"

    // $ANTLR start "COLON_COLON"
    public final void mCOLON_COLON() throws RecognitionException {
        try {
            int _type = COLON_COLON;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:269:25: ( '::' )
            // XQueryLexer.g:269:27: '::'
            {
            match("::"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "COLON_COLON"

    // $ANTLR start "EMPTY_CLOSE_TAG"
    public final void mEMPTY_CLOSE_TAG() throws RecognitionException {
        try {
            int _type = EMPTY_CLOSE_TAG;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:270:25: ( '/>' )
            // XQueryLexer.g:270:27: '/>'
            {
            match("/>"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "EMPTY_CLOSE_TAG"

    // $ANTLR start "CLOSE_TAG"
    public final void mCLOSE_TAG() throws RecognitionException {
        try {
            int _type = CLOSE_TAG;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:271:25: ( '</' )
            // XQueryLexer.g:271:27: '</'
            {
            match("</"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CLOSE_TAG"

    // $ANTLR start "SEMICOLON"
    public final void mSEMICOLON() throws RecognitionException {
        try {
            int _type = SEMICOLON;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:272:25: ( ';' )
            // XQueryLexer.g:272:27: ';'
            {
            match(';'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SEMICOLON"

    // $ANTLR start "VBAR"
    public final void mVBAR() throws RecognitionException {
        try {
            int _type = VBAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:273:25: ( '|' )
            // XQueryLexer.g:273:27: '|'
            {
            match('|'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "VBAR"

    // $ANTLR start "PRAGMA_START"
    public final void mPRAGMA_START() throws RecognitionException {
        try {
            int _type = PRAGMA_START;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:274:25: ( '(#' )
            // XQueryLexer.g:274:27: '(#'
            {
            match("(#"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PRAGMA_START"

    // $ANTLR start "PRAGMA_END"
    public final void mPRAGMA_END() throws RecognitionException {
        try {
            int _type = PRAGMA_END;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:275:25: ( '#)' )
            // XQueryLexer.g:275:27: '#)'
            {
            match("#)"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PRAGMA_END"

    // $ANTLR start "XML_COMMENT_START"
    public final void mXML_COMMENT_START() throws RecognitionException {
        try {
            int _type = XML_COMMENT_START;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:276:25: ( '<!--' )
            // XQueryLexer.g:276:27: '<!--'
            {
            match("<!--"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "XML_COMMENT_START"

    // $ANTLR start "XML_COMMENT_END"
    public final void mXML_COMMENT_END() throws RecognitionException {
        try {
            int _type = XML_COMMENT_END;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:277:25: ( '-->' )
            // XQueryLexer.g:277:27: '-->'
            {
            match("-->"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "XML_COMMENT_END"

    // $ANTLR start "PI_START"
    public final void mPI_START() throws RecognitionException {
        try {
            int _type = PI_START;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:278:25: ( '<?' )
            // XQueryLexer.g:278:27: '<?'
            {
            match("<?"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PI_START"

    // $ANTLR start "PI_END"
    public final void mPI_END() throws RecognitionException {
        try {
            int _type = PI_END;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:279:25: ( '?>' )
            // XQueryLexer.g:279:27: '?>'
            {
            match("?>"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PI_END"

    // $ANTLR start "ATTR_SIGN"
    public final void mATTR_SIGN() throws RecognitionException {
        try {
            int _type = ATTR_SIGN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:280:25: ( '@' )
            // XQueryLexer.g:280:27: '@'
            {
            match('@'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ATTR_SIGN"

    // $ANTLR start "CHARREF_DEC"
    public final void mCHARREF_DEC() throws RecognitionException {
        try {
            int _type = CHARREF_DEC;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:281:25: ( '&#' )
            // XQueryLexer.g:281:27: '&#'
            {
            match("&#"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CHARREF_DEC"

    // $ANTLR start "CHARREF_HEX"
    public final void mCHARREF_HEX() throws RecognitionException {
        try {
            int _type = CHARREF_HEX;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:282:25: ( '&#x' )
            // XQueryLexer.g:282:27: '&#x'
            {
            match("&#x"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CHARREF_HEX"

    // $ANTLR start "APOS"
    public final void mAPOS() throws RecognitionException {
        try {
            int _type = APOS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:283:25: ( '\\'' )
            // XQueryLexer.g:283:27: '\\''
            {
            match('\''); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "APOS"

    // $ANTLR start "QUOT"
    public final void mQUOT() throws RecognitionException {
        try {
            int _type = QUOT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:284:25: ( '\"' )
            // XQueryLexer.g:284:27: '\"'
            {
            match('\"'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "QUOT"

    // $ANTLR start "L_NCName"
    public final void mL_NCName() throws RecognitionException {
        try {
            int _type = L_NCName;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:288:9: ( NCNameStartChar ( NCNameChar )* )
            // XQueryLexer.g:288:13: NCNameStartChar ( NCNameChar )*
            {
            mNCNameStartChar(); 
            // XQueryLexer.g:288:29: ( NCNameChar )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>='-' && LA1_0<='.')||(LA1_0>='0' && LA1_0<='9')||(LA1_0>='A' && LA1_0<='Z')||LA1_0=='_'||(LA1_0>='a' && LA1_0<='z')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // XQueryLexer.g:288:29: NCNameChar
            	    {
            	    mNCNameChar(); 

            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "L_NCName"

    // $ANTLR start "Letter"
    public final void mLetter() throws RecognitionException {
        try {
            // XQueryLexer.g:291:29: ( 'a' .. 'z' | 'A' .. 'Z' )
            // XQueryLexer.g:
            {
            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "Letter"

    // $ANTLR start "HexLetter"
    public final void mHexLetter() throws RecognitionException {
        try {
            // XQueryLexer.g:292:29: ( 'a' .. 'f' | 'A' .. 'F' )
            // XQueryLexer.g:
            {
            if ( (input.LA(1)>='A' && input.LA(1)<='F')||(input.LA(1)>='a' && input.LA(1)<='f') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "HexLetter"

    // $ANTLR start "Digit"
    public final void mDigit() throws RecognitionException {
        try {
            // XQueryLexer.g:293:29: ( '0' .. '9' )
            // XQueryLexer.g:293:31: '0' .. '9'
            {
            matchRange('0','9'); 

            }

        }
        finally {
        }
    }
    // $ANTLR end "Digit"

    // $ANTLR start "Digits"
    public final void mDigits() throws RecognitionException {
        try {
            // XQueryLexer.g:294:29: ( ( Digit )+ )
            // XQueryLexer.g:294:31: ( Digit )+
            {
            // XQueryLexer.g:294:31: ( Digit )+
            int cnt2=0;
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>='0' && LA2_0<='9')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // XQueryLexer.g:294:31: Digit
            	    {
            	    mDigit(); 

            	    }
            	    break;

            	default :
            	    if ( cnt2 >= 1 ) break loop2;
                        EarlyExitException eee =
                            new EarlyExitException(2, input);
                        throw eee;
                }
                cnt2++;
            } while (true);


            }

        }
        finally {
        }
    }
    // $ANTLR end "Digits"

    // $ANTLR start "NCNameStartChar"
    public final void mNCNameStartChar() throws RecognitionException {
        try {
            // XQueryLexer.g:296:29: ( Letter | '_' )
            // XQueryLexer.g:
            {
            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "NCNameStartChar"

    // $ANTLR start "NCNameChar"
    public final void mNCNameChar() throws RecognitionException {
        try {
            // XQueryLexer.g:297:29: ( Letter | Digit | '.' | '-' | '_' )
            // XQueryLexer.g:
            {
            if ( (input.LA(1)>='-' && input.LA(1)<='.')||(input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "NCNameChar"

    // $ANTLR start "S"
    public final void mS() throws RecognitionException {
        try {
            int _type = S;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:300:9: ( ( '\\t' | ' ' | '\\n' | '\\r' )+ )
            // XQueryLexer.g:300:11: ( '\\t' | ' ' | '\\n' | '\\r' )+
            {
            // XQueryLexer.g:300:11: ( '\\t' | ' ' | '\\n' | '\\r' )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0>='\t' && LA3_0<='\n')||LA3_0=='\r'||LA3_0==' ') ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // XQueryLexer.g:
            	    {
            	    if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    if ( cnt3 >= 1 ) break loop3;
                        EarlyExitException eee =
                            new EarlyExitException(3, input);
                        throw eee;
                }
                cnt3++;
            } while (true);

             _channel = HIDDEN; 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "S"

    // $ANTLR start "SU"
    public final void mSU() throws RecognitionException {
        try {
            // XQueryLexer.g:303:9: ( ( '\\t' | ' ' | '\\n' | '\\r' )+ )
            // XQueryLexer.g:303:11: ( '\\t' | ' ' | '\\n' | '\\r' )+
            {
            // XQueryLexer.g:303:11: ( '\\t' | ' ' | '\\n' | '\\r' )+
            int cnt4=0;
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( ((LA4_0>='\t' && LA4_0<='\n')||LA4_0=='\r'||LA4_0==' ') ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // XQueryLexer.g:
            	    {
            	    if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    if ( cnt4 >= 1 ) break loop4;
                        EarlyExitException eee =
                            new EarlyExitException(4, input);
                        throw eee;
                }
                cnt4++;
            } while (true);


            }

        }
        finally {
        }
    }
    // $ANTLR end "SU"

    // $ANTLR start "L_Pragma"
    public final void mL_Pragma() throws RecognitionException {
        try {
            int _type = L_Pragma;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:314:9: ( PRAGMA_START ( SU )? L_NCName COLON L_NCName ( SU ( options {greedy=false; } : . )* )? PRAGMA_END )
            // XQueryLexer.g:314:11: PRAGMA_START ( SU )? L_NCName COLON L_NCName ( SU ( options {greedy=false; } : . )* )? PRAGMA_END
            {
            mPRAGMA_START(); 
            // XQueryLexer.g:314:24: ( SU )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( ((LA5_0>='\t' && LA5_0<='\n')||LA5_0=='\r'||LA5_0==' ') ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // XQueryLexer.g:314:24: SU
                    {
                    mSU(); 

                    }
                    break;

            }

            mL_NCName(); 
            mCOLON(); 
            mL_NCName(); 
            // XQueryLexer.g:314:52: ( SU ( options {greedy=false; } : . )* )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( ((LA7_0>='\t' && LA7_0<='\n')||LA7_0=='\r'||LA7_0==' ') ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // XQueryLexer.g:314:53: SU ( options {greedy=false; } : . )*
                    {
                    mSU(); 
                    // XQueryLexer.g:314:56: ( options {greedy=false; } : . )*
                    loop6:
                    do {
                        int alt6=2;
                        int LA6_0 = input.LA(1);

                        if ( (LA6_0=='#') ) {
                            int LA6_1 = input.LA(2);

                            if ( (LA6_1==')') ) {
                                alt6=2;
                            }
                            else if ( ((LA6_1>='\u0000' && LA6_1<='(')||(LA6_1>='*' && LA6_1<='\uFFFF')) ) {
                                alt6=1;
                            }


                        }
                        else if ( ((LA6_0>='\u0000' && LA6_0<='\"')||(LA6_0>='$' && LA6_0<='\uFFFF')) ) {
                            alt6=1;
                        }


                        switch (alt6) {
                    	case 1 :
                    	    // XQueryLexer.g:314:83: .
                    	    {
                    	    matchAny(); 

                    	    }
                    	    break;

                    	default :
                    	    break loop6;
                        }
                    } while (true);


                    }
                    break;

            }

            mPRAGMA_END(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "L_Pragma"

    // $ANTLR start "L_DirCommentConstructor"
    public final void mL_DirCommentConstructor() throws RecognitionException {
        try {
            int _type = L_DirCommentConstructor;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:324:9: ( XML_COMMENT_START ( options {greedy=false; } : ( . )* ) XML_COMMENT_END )
            // XQueryLexer.g:324:11: XML_COMMENT_START ( options {greedy=false; } : ( . )* ) XML_COMMENT_END
            {
            mXML_COMMENT_START(); 
            // XQueryLexer.g:324:29: ( options {greedy=false; } : ( . )* )
            // XQueryLexer.g:324:56: ( . )*
            {
            // XQueryLexer.g:324:56: ( . )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0=='-') ) {
                    int LA8_1 = input.LA(2);

                    if ( (LA8_1=='-') ) {
                        int LA8_3 = input.LA(3);

                        if ( (LA8_3=='>') ) {
                            alt8=2;
                        }
                        else if ( ((LA8_3>='\u0000' && LA8_3<='=')||(LA8_3>='?' && LA8_3<='\uFFFF')) ) {
                            alt8=1;
                        }


                    }
                    else if ( ((LA8_1>='\u0000' && LA8_1<=',')||(LA8_1>='.' && LA8_1<='\uFFFF')) ) {
                        alt8=1;
                    }


                }
                else if ( ((LA8_0>='\u0000' && LA8_0<=',')||(LA8_0>='.' && LA8_0<='\uFFFF')) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // XQueryLexer.g:324:56: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);


            }

            mXML_COMMENT_END(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "L_DirCommentConstructor"

    // $ANTLR start "L_DirPIConstructor"
    public final void mL_DirPIConstructor() throws RecognitionException {
        try {
            int _type = L_DirPIConstructor;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:333:9: ( PI_START ( SU )? L_NCName ( SU ( options {greedy=false; } : ( . )* ) )? PI_END )
            // XQueryLexer.g:333:11: PI_START ( SU )? L_NCName ( SU ( options {greedy=false; } : ( . )* ) )? PI_END
            {
            mPI_START(); 
            // XQueryLexer.g:333:20: ( SU )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( ((LA9_0>='\t' && LA9_0<='\n')||LA9_0=='\r'||LA9_0==' ') ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // XQueryLexer.g:333:20: SU
                    {
                    mSU(); 

                    }
                    break;

            }

            mL_NCName(); 
            // XQueryLexer.g:333:33: ( SU ( options {greedy=false; } : ( . )* ) )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( ((LA11_0>='\t' && LA11_0<='\n')||LA11_0=='\r'||LA11_0==' ') ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // XQueryLexer.g:333:34: SU ( options {greedy=false; } : ( . )* )
                    {
                    mSU(); 
                    // XQueryLexer.g:333:36: ( options {greedy=false; } : ( . )* )
                    // XQueryLexer.g:333:63: ( . )*
                    {
                    // XQueryLexer.g:333:63: ( . )*
                    loop10:
                    do {
                        int alt10=2;
                        int LA10_0 = input.LA(1);

                        if ( (LA10_0=='?') ) {
                            int LA10_1 = input.LA(2);

                            if ( (LA10_1=='>') ) {
                                alt10=2;
                            }
                            else if ( ((LA10_1>='\u0000' && LA10_1<='=')||(LA10_1>='?' && LA10_1<='\uFFFF')) ) {
                                alt10=1;
                            }


                        }
                        else if ( ((LA10_0>='\u0000' && LA10_0<='>')||(LA10_0>='@' && LA10_0<='\uFFFF')) ) {
                            alt10=1;
                        }


                        switch (alt10) {
                    	case 1 :
                    	    // XQueryLexer.g:333:63: .
                    	    {
                    	    matchAny(); 

                    	    }
                    	    break;

                    	default :
                    	    break loop10;
                        }
                    } while (true);


                    }


                    }
                    break;

            }

            mPI_END(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "L_DirPIConstructor"

    // $ANTLR start "L_IntegerLiteral"
    public final void mL_IntegerLiteral() throws RecognitionException {
        try {
            int _type = L_IntegerLiteral;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:341:9: ( Digits )
            // XQueryLexer.g:341:13: Digits
            {
            mDigits(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "L_IntegerLiteral"

    // $ANTLR start "L_DecimalLiteral"
    public final void mL_DecimalLiteral() throws RecognitionException {
        try {
            int _type = L_DecimalLiteral;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:346:9: ( ( '.' Digits ) | ( Digits '.' ( Digit )* ) )
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0=='.') ) {
                alt13=1;
            }
            else if ( ((LA13_0>='0' && LA13_0<='9')) ) {
                alt13=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;
            }
            switch (alt13) {
                case 1 :
                    // XQueryLexer.g:346:11: ( '.' Digits )
                    {
                    // XQueryLexer.g:346:11: ( '.' Digits )
                    // XQueryLexer.g:346:12: '.' Digits
                    {
                    match('.'); 
                    mDigits(); 

                    }


                    }
                    break;
                case 2 :
                    // XQueryLexer.g:346:26: ( Digits '.' ( Digit )* )
                    {
                    // XQueryLexer.g:346:26: ( Digits '.' ( Digit )* )
                    // XQueryLexer.g:346:27: Digits '.' ( Digit )*
                    {
                    mDigits(); 
                    match('.'); 
                    // XQueryLexer.g:346:38: ( Digit )*
                    loop12:
                    do {
                        int alt12=2;
                        int LA12_0 = input.LA(1);

                        if ( ((LA12_0>='0' && LA12_0<='9')) ) {
                            alt12=1;
                        }


                        switch (alt12) {
                    	case 1 :
                    	    // XQueryLexer.g:346:38: Digit
                    	    {
                    	    mDigit(); 

                    	    }
                    	    break;

                    	default :
                    	    break loop12;
                        }
                    } while (true);


                    }


                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "L_DecimalLiteral"

    // $ANTLR start "L_DoubleLiteral"
    public final void mL_DoubleLiteral() throws RecognitionException {
        try {
            int _type = L_DoubleLiteral;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:351:9: ( ( ( '.' Digits ) | ( Digits ( '.' ( Digit )* )? ) ) ( 'e' | 'E' ) ( '+' | '-' )? Digits )
            // XQueryLexer.g:351:11: ( ( '.' Digits ) | ( Digits ( '.' ( Digit )* )? ) ) ( 'e' | 'E' ) ( '+' | '-' )? Digits
            {
            // XQueryLexer.g:351:11: ( ( '.' Digits ) | ( Digits ( '.' ( Digit )* )? ) )
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0=='.') ) {
                alt16=1;
            }
            else if ( ((LA16_0>='0' && LA16_0<='9')) ) {
                alt16=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 16, 0, input);

                throw nvae;
            }
            switch (alt16) {
                case 1 :
                    // XQueryLexer.g:351:12: ( '.' Digits )
                    {
                    // XQueryLexer.g:351:12: ( '.' Digits )
                    // XQueryLexer.g:351:13: '.' Digits
                    {
                    match('.'); 
                    mDigits(); 

                    }


                    }
                    break;
                case 2 :
                    // XQueryLexer.g:351:27: ( Digits ( '.' ( Digit )* )? )
                    {
                    // XQueryLexer.g:351:27: ( Digits ( '.' ( Digit )* )? )
                    // XQueryLexer.g:351:28: Digits ( '.' ( Digit )* )?
                    {
                    mDigits(); 
                    // XQueryLexer.g:351:35: ( '.' ( Digit )* )?
                    int alt15=2;
                    int LA15_0 = input.LA(1);

                    if ( (LA15_0=='.') ) {
                        alt15=1;
                    }
                    switch (alt15) {
                        case 1 :
                            // XQueryLexer.g:351:36: '.' ( Digit )*
                            {
                            match('.'); 
                            // XQueryLexer.g:351:40: ( Digit )*
                            loop14:
                            do {
                                int alt14=2;
                                int LA14_0 = input.LA(1);

                                if ( ((LA14_0>='0' && LA14_0<='9')) ) {
                                    alt14=1;
                                }


                                switch (alt14) {
                            	case 1 :
                            	    // XQueryLexer.g:351:40: Digit
                            	    {
                            	    mDigit(); 

                            	    }
                            	    break;

                            	default :
                            	    break loop14;
                                }
                            } while (true);


                            }
                            break;

                    }


                    }


                    }
                    break;

            }

            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // XQueryLexer.g:351:63: ( '+' | '-' )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0=='+'||LA17_0=='-') ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // XQueryLexer.g:
                    {
                    if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
                        input.consume();

                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;}


                    }
                    break;

            }

            mDigits(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "L_DoubleLiteral"

    // $ANTLR start "L_Comment"
    public final void mL_Comment() throws RecognitionException {
        try {
            int _type = L_Comment;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:356:9: ( '(:' ( options {greedy=false; } : L_Comment | . )* ':)' )
            // XQueryLexer.g:356:11: '(:' ( options {greedy=false; } : L_Comment | . )* ':)'
            {
            match("(:"); 

            // XQueryLexer.g:356:16: ( options {greedy=false; } : L_Comment | . )*
            loop18:
            do {
                int alt18=3;
                int LA18_0 = input.LA(1);

                if ( (LA18_0==':') ) {
                    int LA18_1 = input.LA(2);

                    if ( (LA18_1==')') ) {
                        alt18=3;
                    }
                    else if ( ((LA18_1>='\u0000' && LA18_1<='(')||(LA18_1>='*' && LA18_1<='\uFFFF')) ) {
                        alt18=2;
                    }


                }
                else if ( (LA18_0=='(') ) {
                    int LA18_2 = input.LA(2);

                    if ( (LA18_2==':') ) {
                        alt18=1;
                    }
                    else if ( ((LA18_2>='\u0000' && LA18_2<='9')||(LA18_2>=';' && LA18_2<='\uFFFF')) ) {
                        alt18=2;
                    }


                }
                else if ( ((LA18_0>='\u0000' && LA18_0<='\'')||(LA18_0>=')' && LA18_0<='9')||(LA18_0>=';' && LA18_0<='\uFFFF')) ) {
                    alt18=2;
                }


                switch (alt18) {
            	case 1 :
            	    // XQueryLexer.g:356:42: L_Comment
            	    {
            	    mL_Comment(); 

            	    }
            	    break;
            	case 2 :
            	    // XQueryLexer.g:356:54: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop18;
                }
            } while (true);

            match(":)"); 

             _channel = HIDDEN; 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "L_Comment"

    // $ANTLR start "L_AnyChar"
    public final void mL_AnyChar() throws RecognitionException {
        try {
            int _type = L_AnyChar;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XQueryLexer.g:359:11: ( . )
            // XQueryLexer.g:359:13: .
            {
            matchAny(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "L_AnyChar"

    public void mTokens() throws RecognitionException {
        // XQueryLexer.g:1:8: ( ANCESTOR | ANCESTOR_OR_SELF | AND | AS | ASCENDING | AT | ATTRIBUTE | BASE_URI | BOUNDARY_SPACE | BY | CASE | CAST | CASTABLE | CHILD | COLLATION | COMMENT | CONSTRUCTION | COPY_NAMESPACES | DECLARE | DEFAULT | DESCENDANT | DESCENDANT_OR_SELF | DESCENDING | DIV | DOCUMENT | DOCUMENT_NODE | ELEMENT | ELSE | EMPTY | EMPTY_SEQUENCE | ENCODING | EQ | EVERY | EXCEPT | EXTERNAL | FOLLOWING | FOLLOWING_SIBLING | FOR | FUNCTION | GE | GREATEST | GT | IDIV | IF | IMPORT | IN | INHERIT | INSTANCE | INTERSECT | IS | ITEM | LAX | LE | LEAST | LET | LT | MOD | MODULE | NAMESPACE | NE | NO_INHERIT | NO_PRESERVE | NODE | OF | OPTION | OR | ORDER | ORDERED | ORDERING | PARENT | PRECEDING | PRECEDING_SIBLING | PRESERVE | PROCESSING_INSTRUCTION | RETURN | SATISFIES | SCHEMA | SCHEMA_ATTRIBUTE | SCHEMA_ELEMENT | SELF | SOME | STABLE | STRICT | STRIP | TEXT | THEN | TO | TREAT | TYPESWITCH | UNION | UNORDERED | VALIDATE | VARIABLE | VERSION | WHERE | XQUERY | CATCH | CONTEXT | COUNT | DECIMAL_FORMAT | DECIMAL_SEPARATOR | DETERMINISTIC | DIGIT | END | GROUP | GROUPING_SEPARATOR | INFINITY | MINUS_SIGN | NAMESPACE_NODE | NAN | NEXT | NONDETERMINISTIC | ONLY | OUTER | PATTERN_SEPARATOR | PERCENT | PER_MILLE | PRIVATE | PUBLIC | PREVIOUS | SLIDING | START | TRY | TUMBLING | WHEN | WINDOW | ZERO_DIGIT | AFTER | BEFORE | COPY | DELETE | FIRST | INSERT | INTO | LAST | MODIFY | NODES | RENAME | REPLACE | REVALIDATION | SKIP | UPDATING | VALUE | WITH | BLOCK | CONSTANT | EXIT | RETURNING | SEQUENTIAL | SET | SIMPLE | WHILE | EVAL | USING | APPEND_ONLY | AUTOMATICALLY | CHECK | COLLECTION | CONSTRAINT | CONST | EQUALITY | FOREACH | FOREIGN | FROM | INDEX | INTEGRITY | KEY | MAINTAINED | MANUALLY | MUTABLE | NON | ON | QUEUE | RANGE | READ_ONLY | UNIQUE | BINARY | AMP_ER | APOS_ER | QUOT_ER | LPAREN | RPAREN | DOLLAR | LBRACKET | RBRACKET | LSQUARE | RSQUARE | EQUAL | BIND | NOTEQUAL | AMP | COMMA | QUESTION | STAR | PLUS | MINUS | SMALLER | GREATER | SMALLEREQ | GREATEREQ | SMALLER_SMALLER | GREATER_GREATER | SLASH | SLASH_SLASH | DOT | DOT_DOT | COLON | COLON_COLON | EMPTY_CLOSE_TAG | CLOSE_TAG | SEMICOLON | VBAR | PRAGMA_START | PRAGMA_END | XML_COMMENT_START | XML_COMMENT_END | PI_START | PI_END | ATTR_SIGN | CHARREF_DEC | CHARREF_HEX | APOS | QUOT | L_NCName | S | L_Pragma | L_DirCommentConstructor | L_DirPIConstructor | L_IntegerLiteral | L_DecimalLiteral | L_DoubleLiteral | L_Comment | L_AnyChar )
        int alt19=233;
        alt19 = dfa19.predict(input);
        switch (alt19) {
            case 1 :
                // XQueryLexer.g:1:10: ANCESTOR
                {
                mANCESTOR(); 

                }
                break;
            case 2 :
                // XQueryLexer.g:1:19: ANCESTOR_OR_SELF
                {
                mANCESTOR_OR_SELF(); 

                }
                break;
            case 3 :
                // XQueryLexer.g:1:36: AND
                {
                mAND(); 

                }
                break;
            case 4 :
                // XQueryLexer.g:1:40: AS
                {
                mAS(); 

                }
                break;
            case 5 :
                // XQueryLexer.g:1:43: ASCENDING
                {
                mASCENDING(); 

                }
                break;
            case 6 :
                // XQueryLexer.g:1:53: AT
                {
                mAT(); 

                }
                break;
            case 7 :
                // XQueryLexer.g:1:56: ATTRIBUTE
                {
                mATTRIBUTE(); 

                }
                break;
            case 8 :
                // XQueryLexer.g:1:66: BASE_URI
                {
                mBASE_URI(); 

                }
                break;
            case 9 :
                // XQueryLexer.g:1:75: BOUNDARY_SPACE
                {
                mBOUNDARY_SPACE(); 

                }
                break;
            case 10 :
                // XQueryLexer.g:1:90: BY
                {
                mBY(); 

                }
                break;
            case 11 :
                // XQueryLexer.g:1:93: CASE
                {
                mCASE(); 

                }
                break;
            case 12 :
                // XQueryLexer.g:1:98: CAST
                {
                mCAST(); 

                }
                break;
            case 13 :
                // XQueryLexer.g:1:103: CASTABLE
                {
                mCASTABLE(); 

                }
                break;
            case 14 :
                // XQueryLexer.g:1:112: CHILD
                {
                mCHILD(); 

                }
                break;
            case 15 :
                // XQueryLexer.g:1:118: COLLATION
                {
                mCOLLATION(); 

                }
                break;
            case 16 :
                // XQueryLexer.g:1:128: COMMENT
                {
                mCOMMENT(); 

                }
                break;
            case 17 :
                // XQueryLexer.g:1:136: CONSTRUCTION
                {
                mCONSTRUCTION(); 

                }
                break;
            case 18 :
                // XQueryLexer.g:1:149: COPY_NAMESPACES
                {
                mCOPY_NAMESPACES(); 

                }
                break;
            case 19 :
                // XQueryLexer.g:1:165: DECLARE
                {
                mDECLARE(); 

                }
                break;
            case 20 :
                // XQueryLexer.g:1:173: DEFAULT
                {
                mDEFAULT(); 

                }
                break;
            case 21 :
                // XQueryLexer.g:1:181: DESCENDANT
                {
                mDESCENDANT(); 

                }
                break;
            case 22 :
                // XQueryLexer.g:1:192: DESCENDANT_OR_SELF
                {
                mDESCENDANT_OR_SELF(); 

                }
                break;
            case 23 :
                // XQueryLexer.g:1:211: DESCENDING
                {
                mDESCENDING(); 

                }
                break;
            case 24 :
                // XQueryLexer.g:1:222: DIV
                {
                mDIV(); 

                }
                break;
            case 25 :
                // XQueryLexer.g:1:226: DOCUMENT
                {
                mDOCUMENT(); 

                }
                break;
            case 26 :
                // XQueryLexer.g:1:235: DOCUMENT_NODE
                {
                mDOCUMENT_NODE(); 

                }
                break;
            case 27 :
                // XQueryLexer.g:1:249: ELEMENT
                {
                mELEMENT(); 

                }
                break;
            case 28 :
                // XQueryLexer.g:1:257: ELSE
                {
                mELSE(); 

                }
                break;
            case 29 :
                // XQueryLexer.g:1:262: EMPTY
                {
                mEMPTY(); 

                }
                break;
            case 30 :
                // XQueryLexer.g:1:268: EMPTY_SEQUENCE
                {
                mEMPTY_SEQUENCE(); 

                }
                break;
            case 31 :
                // XQueryLexer.g:1:283: ENCODING
                {
                mENCODING(); 

                }
                break;
            case 32 :
                // XQueryLexer.g:1:292: EQ
                {
                mEQ(); 

                }
                break;
            case 33 :
                // XQueryLexer.g:1:295: EVERY
                {
                mEVERY(); 

                }
                break;
            case 34 :
                // XQueryLexer.g:1:301: EXCEPT
                {
                mEXCEPT(); 

                }
                break;
            case 35 :
                // XQueryLexer.g:1:308: EXTERNAL
                {
                mEXTERNAL(); 

                }
                break;
            case 36 :
                // XQueryLexer.g:1:317: FOLLOWING
                {
                mFOLLOWING(); 

                }
                break;
            case 37 :
                // XQueryLexer.g:1:327: FOLLOWING_SIBLING
                {
                mFOLLOWING_SIBLING(); 

                }
                break;
            case 38 :
                // XQueryLexer.g:1:345: FOR
                {
                mFOR(); 

                }
                break;
            case 39 :
                // XQueryLexer.g:1:349: FUNCTION
                {
                mFUNCTION(); 

                }
                break;
            case 40 :
                // XQueryLexer.g:1:358: GE
                {
                mGE(); 

                }
                break;
            case 41 :
                // XQueryLexer.g:1:361: GREATEST
                {
                mGREATEST(); 

                }
                break;
            case 42 :
                // XQueryLexer.g:1:370: GT
                {
                mGT(); 

                }
                break;
            case 43 :
                // XQueryLexer.g:1:373: IDIV
                {
                mIDIV(); 

                }
                break;
            case 44 :
                // XQueryLexer.g:1:378: IF
                {
                mIF(); 

                }
                break;
            case 45 :
                // XQueryLexer.g:1:381: IMPORT
                {
                mIMPORT(); 

                }
                break;
            case 46 :
                // XQueryLexer.g:1:388: IN
                {
                mIN(); 

                }
                break;
            case 47 :
                // XQueryLexer.g:1:391: INHERIT
                {
                mINHERIT(); 

                }
                break;
            case 48 :
                // XQueryLexer.g:1:399: INSTANCE
                {
                mINSTANCE(); 

                }
                break;
            case 49 :
                // XQueryLexer.g:1:408: INTERSECT
                {
                mINTERSECT(); 

                }
                break;
            case 50 :
                // XQueryLexer.g:1:418: IS
                {
                mIS(); 

                }
                break;
            case 51 :
                // XQueryLexer.g:1:421: ITEM
                {
                mITEM(); 

                }
                break;
            case 52 :
                // XQueryLexer.g:1:426: LAX
                {
                mLAX(); 

                }
                break;
            case 53 :
                // XQueryLexer.g:1:430: LE
                {
                mLE(); 

                }
                break;
            case 54 :
                // XQueryLexer.g:1:433: LEAST
                {
                mLEAST(); 

                }
                break;
            case 55 :
                // XQueryLexer.g:1:439: LET
                {
                mLET(); 

                }
                break;
            case 56 :
                // XQueryLexer.g:1:443: LT
                {
                mLT(); 

                }
                break;
            case 57 :
                // XQueryLexer.g:1:446: MOD
                {
                mMOD(); 

                }
                break;
            case 58 :
                // XQueryLexer.g:1:450: MODULE
                {
                mMODULE(); 

                }
                break;
            case 59 :
                // XQueryLexer.g:1:457: NAMESPACE
                {
                mNAMESPACE(); 

                }
                break;
            case 60 :
                // XQueryLexer.g:1:467: NE
                {
                mNE(); 

                }
                break;
            case 61 :
                // XQueryLexer.g:1:470: NO_INHERIT
                {
                mNO_INHERIT(); 

                }
                break;
            case 62 :
                // XQueryLexer.g:1:481: NO_PRESERVE
                {
                mNO_PRESERVE(); 

                }
                break;
            case 63 :
                // XQueryLexer.g:1:493: NODE
                {
                mNODE(); 

                }
                break;
            case 64 :
                // XQueryLexer.g:1:498: OF
                {
                mOF(); 

                }
                break;
            case 65 :
                // XQueryLexer.g:1:501: OPTION
                {
                mOPTION(); 

                }
                break;
            case 66 :
                // XQueryLexer.g:1:508: OR
                {
                mOR(); 

                }
                break;
            case 67 :
                // XQueryLexer.g:1:511: ORDER
                {
                mORDER(); 

                }
                break;
            case 68 :
                // XQueryLexer.g:1:517: ORDERED
                {
                mORDERED(); 

                }
                break;
            case 69 :
                // XQueryLexer.g:1:525: ORDERING
                {
                mORDERING(); 

                }
                break;
            case 70 :
                // XQueryLexer.g:1:534: PARENT
                {
                mPARENT(); 

                }
                break;
            case 71 :
                // XQueryLexer.g:1:541: PRECEDING
                {
                mPRECEDING(); 

                }
                break;
            case 72 :
                // XQueryLexer.g:1:551: PRECEDING_SIBLING
                {
                mPRECEDING_SIBLING(); 

                }
                break;
            case 73 :
                // XQueryLexer.g:1:569: PRESERVE
                {
                mPRESERVE(); 

                }
                break;
            case 74 :
                // XQueryLexer.g:1:578: PROCESSING_INSTRUCTION
                {
                mPROCESSING_INSTRUCTION(); 

                }
                break;
            case 75 :
                // XQueryLexer.g:1:601: RETURN
                {
                mRETURN(); 

                }
                break;
            case 76 :
                // XQueryLexer.g:1:608: SATISFIES
                {
                mSATISFIES(); 

                }
                break;
            case 77 :
                // XQueryLexer.g:1:618: SCHEMA
                {
                mSCHEMA(); 

                }
                break;
            case 78 :
                // XQueryLexer.g:1:625: SCHEMA_ATTRIBUTE
                {
                mSCHEMA_ATTRIBUTE(); 

                }
                break;
            case 79 :
                // XQueryLexer.g:1:642: SCHEMA_ELEMENT
                {
                mSCHEMA_ELEMENT(); 

                }
                break;
            case 80 :
                // XQueryLexer.g:1:657: SELF
                {
                mSELF(); 

                }
                break;
            case 81 :
                // XQueryLexer.g:1:662: SOME
                {
                mSOME(); 

                }
                break;
            case 82 :
                // XQueryLexer.g:1:667: STABLE
                {
                mSTABLE(); 

                }
                break;
            case 83 :
                // XQueryLexer.g:1:674: STRICT
                {
                mSTRICT(); 

                }
                break;
            case 84 :
                // XQueryLexer.g:1:681: STRIP
                {
                mSTRIP(); 

                }
                break;
            case 85 :
                // XQueryLexer.g:1:687: TEXT
                {
                mTEXT(); 

                }
                break;
            case 86 :
                // XQueryLexer.g:1:692: THEN
                {
                mTHEN(); 

                }
                break;
            case 87 :
                // XQueryLexer.g:1:697: TO
                {
                mTO(); 

                }
                break;
            case 88 :
                // XQueryLexer.g:1:700: TREAT
                {
                mTREAT(); 

                }
                break;
            case 89 :
                // XQueryLexer.g:1:706: TYPESWITCH
                {
                mTYPESWITCH(); 

                }
                break;
            case 90 :
                // XQueryLexer.g:1:717: UNION
                {
                mUNION(); 

                }
                break;
            case 91 :
                // XQueryLexer.g:1:723: UNORDERED
                {
                mUNORDERED(); 

                }
                break;
            case 92 :
                // XQueryLexer.g:1:733: VALIDATE
                {
                mVALIDATE(); 

                }
                break;
            case 93 :
                // XQueryLexer.g:1:742: VARIABLE
                {
                mVARIABLE(); 

                }
                break;
            case 94 :
                // XQueryLexer.g:1:751: VERSION
                {
                mVERSION(); 

                }
                break;
            case 95 :
                // XQueryLexer.g:1:759: WHERE
                {
                mWHERE(); 

                }
                break;
            case 96 :
                // XQueryLexer.g:1:765: XQUERY
                {
                mXQUERY(); 

                }
                break;
            case 97 :
                // XQueryLexer.g:1:772: CATCH
                {
                mCATCH(); 

                }
                break;
            case 98 :
                // XQueryLexer.g:1:778: CONTEXT
                {
                mCONTEXT(); 

                }
                break;
            case 99 :
                // XQueryLexer.g:1:786: COUNT
                {
                mCOUNT(); 

                }
                break;
            case 100 :
                // XQueryLexer.g:1:792: DECIMAL_FORMAT
                {
                mDECIMAL_FORMAT(); 

                }
                break;
            case 101 :
                // XQueryLexer.g:1:807: DECIMAL_SEPARATOR
                {
                mDECIMAL_SEPARATOR(); 

                }
                break;
            case 102 :
                // XQueryLexer.g:1:825: DETERMINISTIC
                {
                mDETERMINISTIC(); 

                }
                break;
            case 103 :
                // XQueryLexer.g:1:839: DIGIT
                {
                mDIGIT(); 

                }
                break;
            case 104 :
                // XQueryLexer.g:1:845: END
                {
                mEND(); 

                }
                break;
            case 105 :
                // XQueryLexer.g:1:849: GROUP
                {
                mGROUP(); 

                }
                break;
            case 106 :
                // XQueryLexer.g:1:855: GROUPING_SEPARATOR
                {
                mGROUPING_SEPARATOR(); 

                }
                break;
            case 107 :
                // XQueryLexer.g:1:874: INFINITY
                {
                mINFINITY(); 

                }
                break;
            case 108 :
                // XQueryLexer.g:1:883: MINUS_SIGN
                {
                mMINUS_SIGN(); 

                }
                break;
            case 109 :
                // XQueryLexer.g:1:894: NAMESPACE_NODE
                {
                mNAMESPACE_NODE(); 

                }
                break;
            case 110 :
                // XQueryLexer.g:1:909: NAN
                {
                mNAN(); 

                }
                break;
            case 111 :
                // XQueryLexer.g:1:913: NEXT
                {
                mNEXT(); 

                }
                break;
            case 112 :
                // XQueryLexer.g:1:918: NONDETERMINISTIC
                {
                mNONDETERMINISTIC(); 

                }
                break;
            case 113 :
                // XQueryLexer.g:1:935: ONLY
                {
                mONLY(); 

                }
                break;
            case 114 :
                // XQueryLexer.g:1:940: OUTER
                {
                mOUTER(); 

                }
                break;
            case 115 :
                // XQueryLexer.g:1:946: PATTERN_SEPARATOR
                {
                mPATTERN_SEPARATOR(); 

                }
                break;
            case 116 :
                // XQueryLexer.g:1:964: PERCENT
                {
                mPERCENT(); 

                }
                break;
            case 117 :
                // XQueryLexer.g:1:972: PER_MILLE
                {
                mPER_MILLE(); 

                }
                break;
            case 118 :
                // XQueryLexer.g:1:982: PRIVATE
                {
                mPRIVATE(); 

                }
                break;
            case 119 :
                // XQueryLexer.g:1:990: PUBLIC
                {
                mPUBLIC(); 

                }
                break;
            case 120 :
                // XQueryLexer.g:1:997: PREVIOUS
                {
                mPREVIOUS(); 

                }
                break;
            case 121 :
                // XQueryLexer.g:1:1006: SLIDING
                {
                mSLIDING(); 

                }
                break;
            case 122 :
                // XQueryLexer.g:1:1014: START
                {
                mSTART(); 

                }
                break;
            case 123 :
                // XQueryLexer.g:1:1020: TRY
                {
                mTRY(); 

                }
                break;
            case 124 :
                // XQueryLexer.g:1:1024: TUMBLING
                {
                mTUMBLING(); 

                }
                break;
            case 125 :
                // XQueryLexer.g:1:1033: WHEN
                {
                mWHEN(); 

                }
                break;
            case 126 :
                // XQueryLexer.g:1:1038: WINDOW
                {
                mWINDOW(); 

                }
                break;
            case 127 :
                // XQueryLexer.g:1:1045: ZERO_DIGIT
                {
                mZERO_DIGIT(); 

                }
                break;
            case 128 :
                // XQueryLexer.g:1:1056: AFTER
                {
                mAFTER(); 

                }
                break;
            case 129 :
                // XQueryLexer.g:1:1062: BEFORE
                {
                mBEFORE(); 

                }
                break;
            case 130 :
                // XQueryLexer.g:1:1069: COPY
                {
                mCOPY(); 

                }
                break;
            case 131 :
                // XQueryLexer.g:1:1074: DELETE
                {
                mDELETE(); 

                }
                break;
            case 132 :
                // XQueryLexer.g:1:1081: FIRST
                {
                mFIRST(); 

                }
                break;
            case 133 :
                // XQueryLexer.g:1:1087: INSERT
                {
                mINSERT(); 

                }
                break;
            case 134 :
                // XQueryLexer.g:1:1094: INTO
                {
                mINTO(); 

                }
                break;
            case 135 :
                // XQueryLexer.g:1:1099: LAST
                {
                mLAST(); 

                }
                break;
            case 136 :
                // XQueryLexer.g:1:1104: MODIFY
                {
                mMODIFY(); 

                }
                break;
            case 137 :
                // XQueryLexer.g:1:1111: NODES
                {
                mNODES(); 

                }
                break;
            case 138 :
                // XQueryLexer.g:1:1117: RENAME
                {
                mRENAME(); 

                }
                break;
            case 139 :
                // XQueryLexer.g:1:1124: REPLACE
                {
                mREPLACE(); 

                }
                break;
            case 140 :
                // XQueryLexer.g:1:1132: REVALIDATION
                {
                mREVALIDATION(); 

                }
                break;
            case 141 :
                // XQueryLexer.g:1:1145: SKIP
                {
                mSKIP(); 

                }
                break;
            case 142 :
                // XQueryLexer.g:1:1150: UPDATING
                {
                mUPDATING(); 

                }
                break;
            case 143 :
                // XQueryLexer.g:1:1159: VALUE
                {
                mVALUE(); 

                }
                break;
            case 144 :
                // XQueryLexer.g:1:1165: WITH
                {
                mWITH(); 

                }
                break;
            case 145 :
                // XQueryLexer.g:1:1170: BLOCK
                {
                mBLOCK(); 

                }
                break;
            case 146 :
                // XQueryLexer.g:1:1176: CONSTANT
                {
                mCONSTANT(); 

                }
                break;
            case 147 :
                // XQueryLexer.g:1:1185: EXIT
                {
                mEXIT(); 

                }
                break;
            case 148 :
                // XQueryLexer.g:1:1190: RETURNING
                {
                mRETURNING(); 

                }
                break;
            case 149 :
                // XQueryLexer.g:1:1200: SEQUENTIAL
                {
                mSEQUENTIAL(); 

                }
                break;
            case 150 :
                // XQueryLexer.g:1:1211: SET
                {
                mSET(); 

                }
                break;
            case 151 :
                // XQueryLexer.g:1:1215: SIMPLE
                {
                mSIMPLE(); 

                }
                break;
            case 152 :
                // XQueryLexer.g:1:1222: WHILE
                {
                mWHILE(); 

                }
                break;
            case 153 :
                // XQueryLexer.g:1:1228: EVAL
                {
                mEVAL(); 

                }
                break;
            case 154 :
                // XQueryLexer.g:1:1233: USING
                {
                mUSING(); 

                }
                break;
            case 155 :
                // XQueryLexer.g:1:1239: APPEND_ONLY
                {
                mAPPEND_ONLY(); 

                }
                break;
            case 156 :
                // XQueryLexer.g:1:1251: AUTOMATICALLY
                {
                mAUTOMATICALLY(); 

                }
                break;
            case 157 :
                // XQueryLexer.g:1:1265: CHECK
                {
                mCHECK(); 

                }
                break;
            case 158 :
                // XQueryLexer.g:1:1271: COLLECTION
                {
                mCOLLECTION(); 

                }
                break;
            case 159 :
                // XQueryLexer.g:1:1282: CONSTRAINT
                {
                mCONSTRAINT(); 

                }
                break;
            case 160 :
                // XQueryLexer.g:1:1293: CONST
                {
                mCONST(); 

                }
                break;
            case 161 :
                // XQueryLexer.g:1:1299: EQUALITY
                {
                mEQUALITY(); 

                }
                break;
            case 162 :
                // XQueryLexer.g:1:1308: FOREACH
                {
                mFOREACH(); 

                }
                break;
            case 163 :
                // XQueryLexer.g:1:1316: FOREIGN
                {
                mFOREIGN(); 

                }
                break;
            case 164 :
                // XQueryLexer.g:1:1324: FROM
                {
                mFROM(); 

                }
                break;
            case 165 :
                // XQueryLexer.g:1:1329: INDEX
                {
                mINDEX(); 

                }
                break;
            case 166 :
                // XQueryLexer.g:1:1335: INTEGRITY
                {
                mINTEGRITY(); 

                }
                break;
            case 167 :
                // XQueryLexer.g:1:1345: KEY
                {
                mKEY(); 

                }
                break;
            case 168 :
                // XQueryLexer.g:1:1349: MAINTAINED
                {
                mMAINTAINED(); 

                }
                break;
            case 169 :
                // XQueryLexer.g:1:1360: MANUALLY
                {
                mMANUALLY(); 

                }
                break;
            case 170 :
                // XQueryLexer.g:1:1369: MUTABLE
                {
                mMUTABLE(); 

                }
                break;
            case 171 :
                // XQueryLexer.g:1:1377: NON
                {
                mNON(); 

                }
                break;
            case 172 :
                // XQueryLexer.g:1:1381: ON
                {
                mON(); 

                }
                break;
            case 173 :
                // XQueryLexer.g:1:1384: QUEUE
                {
                mQUEUE(); 

                }
                break;
            case 174 :
                // XQueryLexer.g:1:1390: RANGE
                {
                mRANGE(); 

                }
                break;
            case 175 :
                // XQueryLexer.g:1:1396: READ_ONLY
                {
                mREAD_ONLY(); 

                }
                break;
            case 176 :
                // XQueryLexer.g:1:1406: UNIQUE
                {
                mUNIQUE(); 

                }
                break;
            case 177 :
                // XQueryLexer.g:1:1413: BINARY
                {
                mBINARY(); 

                }
                break;
            case 178 :
                // XQueryLexer.g:1:1420: AMP_ER
                {
                mAMP_ER(); 

                }
                break;
            case 179 :
                // XQueryLexer.g:1:1427: APOS_ER
                {
                mAPOS_ER(); 

                }
                break;
            case 180 :
                // XQueryLexer.g:1:1435: QUOT_ER
                {
                mQUOT_ER(); 

                }
                break;
            case 181 :
                // XQueryLexer.g:1:1443: LPAREN
                {
                mLPAREN(); 

                }
                break;
            case 182 :
                // XQueryLexer.g:1:1450: RPAREN
                {
                mRPAREN(); 

                }
                break;
            case 183 :
                // XQueryLexer.g:1:1457: DOLLAR
                {
                mDOLLAR(); 

                }
                break;
            case 184 :
                // XQueryLexer.g:1:1464: LBRACKET
                {
                mLBRACKET(); 

                }
                break;
            case 185 :
                // XQueryLexer.g:1:1473: RBRACKET
                {
                mRBRACKET(); 

                }
                break;
            case 186 :
                // XQueryLexer.g:1:1482: LSQUARE
                {
                mLSQUARE(); 

                }
                break;
            case 187 :
                // XQueryLexer.g:1:1490: RSQUARE
                {
                mRSQUARE(); 

                }
                break;
            case 188 :
                // XQueryLexer.g:1:1498: EQUAL
                {
                mEQUAL(); 

                }
                break;
            case 189 :
                // XQueryLexer.g:1:1504: BIND
                {
                mBIND(); 

                }
                break;
            case 190 :
                // XQueryLexer.g:1:1509: NOTEQUAL
                {
                mNOTEQUAL(); 

                }
                break;
            case 191 :
                // XQueryLexer.g:1:1518: AMP
                {
                mAMP(); 

                }
                break;
            case 192 :
                // XQueryLexer.g:1:1522: COMMA
                {
                mCOMMA(); 

                }
                break;
            case 193 :
                // XQueryLexer.g:1:1528: QUESTION
                {
                mQUESTION(); 

                }
                break;
            case 194 :
                // XQueryLexer.g:1:1537: STAR
                {
                mSTAR(); 

                }
                break;
            case 195 :
                // XQueryLexer.g:1:1542: PLUS
                {
                mPLUS(); 

                }
                break;
            case 196 :
                // XQueryLexer.g:1:1547: MINUS
                {
                mMINUS(); 

                }
                break;
            case 197 :
                // XQueryLexer.g:1:1553: SMALLER
                {
                mSMALLER(); 

                }
                break;
            case 198 :
                // XQueryLexer.g:1:1561: GREATER
                {
                mGREATER(); 

                }
                break;
            case 199 :
                // XQueryLexer.g:1:1569: SMALLEREQ
                {
                mSMALLEREQ(); 

                }
                break;
            case 200 :
                // XQueryLexer.g:1:1579: GREATEREQ
                {
                mGREATEREQ(); 

                }
                break;
            case 201 :
                // XQueryLexer.g:1:1589: SMALLER_SMALLER
                {
                mSMALLER_SMALLER(); 

                }
                break;
            case 202 :
                // XQueryLexer.g:1:1605: GREATER_GREATER
                {
                mGREATER_GREATER(); 

                }
                break;
            case 203 :
                // XQueryLexer.g:1:1621: SLASH
                {
                mSLASH(); 

                }
                break;
            case 204 :
                // XQueryLexer.g:1:1627: SLASH_SLASH
                {
                mSLASH_SLASH(); 

                }
                break;
            case 205 :
                // XQueryLexer.g:1:1639: DOT
                {
                mDOT(); 

                }
                break;
            case 206 :
                // XQueryLexer.g:1:1643: DOT_DOT
                {
                mDOT_DOT(); 

                }
                break;
            case 207 :
                // XQueryLexer.g:1:1651: COLON
                {
                mCOLON(); 

                }
                break;
            case 208 :
                // XQueryLexer.g:1:1657: COLON_COLON
                {
                mCOLON_COLON(); 

                }
                break;
            case 209 :
                // XQueryLexer.g:1:1669: EMPTY_CLOSE_TAG
                {
                mEMPTY_CLOSE_TAG(); 

                }
                break;
            case 210 :
                // XQueryLexer.g:1:1685: CLOSE_TAG
                {
                mCLOSE_TAG(); 

                }
                break;
            case 211 :
                // XQueryLexer.g:1:1695: SEMICOLON
                {
                mSEMICOLON(); 

                }
                break;
            case 212 :
                // XQueryLexer.g:1:1705: VBAR
                {
                mVBAR(); 

                }
                break;
            case 213 :
                // XQueryLexer.g:1:1710: PRAGMA_START
                {
                mPRAGMA_START(); 

                }
                break;
            case 214 :
                // XQueryLexer.g:1:1723: PRAGMA_END
                {
                mPRAGMA_END(); 

                }
                break;
            case 215 :
                // XQueryLexer.g:1:1734: XML_COMMENT_START
                {
                mXML_COMMENT_START(); 

                }
                break;
            case 216 :
                // XQueryLexer.g:1:1752: XML_COMMENT_END
                {
                mXML_COMMENT_END(); 

                }
                break;
            case 217 :
                // XQueryLexer.g:1:1768: PI_START
                {
                mPI_START(); 

                }
                break;
            case 218 :
                // XQueryLexer.g:1:1777: PI_END
                {
                mPI_END(); 

                }
                break;
            case 219 :
                // XQueryLexer.g:1:1784: ATTR_SIGN
                {
                mATTR_SIGN(); 

                }
                break;
            case 220 :
                // XQueryLexer.g:1:1794: CHARREF_DEC
                {
                mCHARREF_DEC(); 

                }
                break;
            case 221 :
                // XQueryLexer.g:1:1806: CHARREF_HEX
                {
                mCHARREF_HEX(); 

                }
                break;
            case 222 :
                // XQueryLexer.g:1:1818: APOS
                {
                mAPOS(); 

                }
                break;
            case 223 :
                // XQueryLexer.g:1:1823: QUOT
                {
                mQUOT(); 

                }
                break;
            case 224 :
                // XQueryLexer.g:1:1828: L_NCName
                {
                mL_NCName(); 

                }
                break;
            case 225 :
                // XQueryLexer.g:1:1837: S
                {
                mS(); 

                }
                break;
            case 226 :
                // XQueryLexer.g:1:1839: L_Pragma
                {
                mL_Pragma(); 

                }
                break;
            case 227 :
                // XQueryLexer.g:1:1848: L_DirCommentConstructor
                {
                mL_DirCommentConstructor(); 

                }
                break;
            case 228 :
                // XQueryLexer.g:1:1872: L_DirPIConstructor
                {
                mL_DirPIConstructor(); 

                }
                break;
            case 229 :
                // XQueryLexer.g:1:1891: L_IntegerLiteral
                {
                mL_IntegerLiteral(); 

                }
                break;
            case 230 :
                // XQueryLexer.g:1:1908: L_DecimalLiteral
                {
                mL_DecimalLiteral(); 

                }
                break;
            case 231 :
                // XQueryLexer.g:1:1925: L_DoubleLiteral
                {
                mL_DoubleLiteral(); 

                }
                break;
            case 232 :
                // XQueryLexer.g:1:1941: L_Comment
                {
                mL_Comment(); 

                }
                break;
            case 233 :
                // XQueryLexer.g:1:1951: L_AnyChar
                {
                mL_AnyChar(); 

                }
                break;

        }

    }


    protected DFA19 dfa19 = new DFA19(this);
    static final String DFA19_eotS =
        "\1\uffff\30\76\1\u008f\7\uffff\1\u0099\1\66\1\u009c\1\uffff\1\u009f"+
        "\2\uffff\1\u00a3\1\u00a9\1\u00ac\1\u00af\1\u00b2\2\uffff\1\66\5"+
        "\uffff\1\u00ba\1\uffff\1\76\1\u00c1\1\u00c3\4\76\1\uffff\2\76\1"+
        "\u00cb\14\76\1\u00e6\6\76\1\u00f1\1\76\1\u00f4\1\76\1\u00f6\1\76"+
        "\1\u00fd\1\u00fe\2\76\1\u0104\1\u0105\5\76\1\u010d\1\76\1\u0111"+
        "\1\76\1\u0114\1\u0116\21\76\1\u0132\17\76\1\u0148\15\uffff\1\u014b"+
        "\14\uffff\1\u014d\10\uffff\1\u014f\11\uffff\1\u00ba\1\u014f\1\uffff"+
        "\1\76\1\u0152\1\76\1\uffff\1\76\1\uffff\4\76\1\u0159\2\76\1\uffff"+
        "\21\76\1\u0170\6\76\1\u0177\1\76\1\uffff\6\76\1\u0180\3\76\1\uffff"+
        "\2\76\1\uffff\1\76\1\uffff\6\76\2\uffff\1\76\1\u0190\2\76\1\u0193"+
        "\2\uffff\1\u0196\6\76\1\uffff\2\76\1\u01a1\1\uffff\2\76\1\uffff"+
        "\1\76\1\uffff\22\76\1\u01ba\10\76\1\uffff\1\76\1\u01c5\16\76\1\u01d7"+
        "\1\76\1\u01d9\2\76\10\uffff\1\u014f\1\76\1\uffff\4\76\1\u01e2\1"+
        "\76\1\uffff\5\76\1\u01e9\1\u01eb\7\76\1\u01f5\7\76\1\uffff\3\76"+
        "\1\u0200\2\76\1\uffff\2\76\1\u0205\2\76\1\u0208\2\76\1\uffff\2\76"+
        "\1\u020e\2\76\1\u0211\5\76\1\u0218\2\76\1\u021b\1\uffff\1\u021c"+
        "\1\76\1\uffff\2\76\1\uffff\5\76\1\u0225\2\76\1\u0229\1\76\1\uffff"+
        "\2\76\1\u022d\23\76\1\u0241\1\76\1\uffff\1\u0243\4\76\1\u0249\1"+
        "\76\1\u024b\1\u024c\1\76\1\uffff\14\76\1\u025a\2\76\1\u025d\1\76"+
        "\1\uffff\1\76\1\uffff\1\76\1\u0261\1\u0262\3\76\1\u0267\1\76\1\uffff"+
        "\4\76\1\u026d\1\76\1\uffff\1\76\1\uffff\1\u0270\1\u0271\1\u0272"+
        "\3\76\1\u0278\2\76\1\uffff\1\u027b\6\76\1\u0282\2\76\1\uffff\1\u0286"+
        "\2\76\1\u0289\1\uffff\2\76\1\uffff\4\76\1\u0290\1\uffff\1\76\1\u0293"+
        "\1\uffff\6\76\1\uffff\1\76\1\u029b\2\uffff\1\u029c\7\76\1\uffff"+
        "\2\76\1\u02a6\1\uffff\2\76\1\u02ab\1\uffff\1\u02ac\17\76\1\u02bc"+
        "\2\76\1\uffff\1\76\1\uffff\1\76\1\u02c1\1\76\1\u02c3\1\76\1\uffff"+
        "\1\76\2\uffff\1\u02c6\2\76\1\u02c9\3\76\1\u02cd\1\76\1\u02cf\2\76"+
        "\1\u02d2\1\uffff\1\u02d3\1\76\1\uffff\2\76\1\u02d7\3\uffff\3\76"+
        "\1\uffff\4\76\1\u02df\1\uffff\1\u02e0\1\76\3\uffff\5\76\1\uffff"+
        "\2\76\1\uffff\5\76\1\u02ef\1\uffff\3\76\1\uffff\2\76\1\uffff\1\u02f5"+
        "\5\76\1\uffff\2\76\1\uffff\1\u02fd\2\76\1\u0300\3\76\2\uffff\1\u0304"+
        "\1\u0305\7\76\1\uffff\1\76\1\u030e\2\76\2\uffff\1\u0311\10\76\1"+
        "\u031a\1\u031c\1\u031d\3\76\1\uffff\1\76\1\u0323\1\76\1\u0325\1"+
        "\uffff\1\u0326\1\uffff\1\76\1\u0328\1\uffff\2\76\1\uffff\1\u032b"+
        "\2\76\1\uffff\1\76\1\uffff\2\76\2\uffff\1\u0331\1\u0332\1\76\1\uffff"+
        "\7\76\2\uffff\3\76\1\u033e\3\76\1\u0342\1\76\1\u0344\1\76\1\u0346"+
        "\2\76\1\uffff\1\76\1\u034b\3\76\1\uffff\2\76\1\u0351\1\u0352\3\76"+
        "\1\uffff\1\u0356\1\76\1\uffff\3\76\2\uffff\3\76\1\u035e\4\76\1\uffff"+
        "\1\u0363\1\76\1\uffff\5\76\1\u036a\1\u036b\1\76\1\uffff\1\76\2\uffff"+
        "\1\u036e\4\76\1\uffff\1\76\2\uffff\1\u0375\1\uffff\2\76\1\uffff"+
        "\4\76\1\u037c\2\uffff\1\76\1\u037f\4\76\1\u0384\1\76\1\u0386\2\76"+
        "\1\uffff\2\76\1\u038b\1\uffff\1\76\1\uffff\1\76\1\uffff\3\76\1\u0393"+
        "\1\uffff\1\76\1\u0395\1\u0396\1\u0397\1\76\2\uffff\1\u0399\1\u039a"+
        "\1\76\1\uffff\1\u039c\2\76\1\u039f\2\76\1\u03a2\1\uffff\4\76\1\uffff"+
        "\1\u03a7\2\76\1\u03aa\1\u03ab\1\76\2\uffff\2\76\1\uffff\6\76\1\uffff"+
        "\1\76\1\u03b6\1\76\1\u03b8\1\u03b9\1\u03ba\1\uffff\2\76\1\uffff"+
        "\1\u03bd\1\u03be\2\76\1\uffff\1\76\1\uffff\1\u03c2\3\76\1\uffff"+
        "\7\76\1\uffff\1\76\3\uffff\1\u03cf\2\uffff\1\76\1\uffff\1\u03d1"+
        "\1\u03d2\1\uffff\2\76\1\uffff\1\u03d6\3\76\1\uffff\1\76\1\u03dc"+
        "\2\uffff\1\76\1\u03de\1\u03df\1\76\1\u03e1\1\u03e2\4\76\1\uffff"+
        "\1\u03e7\3\uffff\2\76\2\uffff\3\76\1\uffff\1\u03ed\1\76\1\u03ef"+
        "\3\76\1\u03f4\1\u03f5\4\76\1\uffff\1\76\2\uffff\1\u03fb\1\u03fc"+
        "\1\76\1\uffff\1\u03fe\4\76\1\uffff\1\76\2\uffff\1\76\2\uffff\2\76"+
        "\1\u0407\1\u0408\1\uffff\1\u0409\1\76\1\u040b\2\76\1\uffff\1\76"+
        "\1\uffff\4\76\2\uffff\5\76\2\uffff\1\76\1\uffff\1\u0419\7\76\3\uffff"+
        "\1\76\1\uffff\2\76\1\u0424\12\76\1\uffff\4\76\1\u0433\3\76\1\u0437"+
        "\1\76\1\uffff\4\76\1\u043d\1\u043e\10\76\1\uffff\3\76\1\uffff\1"+
        "\u044a\1\76\1\u044c\2\76\2\uffff\1\u044f\2\76\1\u0452\5\76\1\u0458"+
        "\1\76\1\uffff\1\u045a\1\uffff\2\76\1\uffff\2\76\1\uffff\5\76\1\uffff"+
        "\1\u0464\1\uffff\4\76\1\u0469\3\76\1\u046d\1\uffff\1\u046e\1\76"+
        "\1\u0470\1\76\1\uffff\1\u0472\1\u0473\1\76\2\uffff\1\u0475\1\uffff"+
        "\1\u0476\2\uffff\1\76\2\uffff\3\76\1\u047b\1\uffff";
    static final String DFA19_eofS =
        "\u047c\uffff";
    static final String DFA19_minS =
        "\1\0\1\146\2\141\1\145\1\154\1\151\1\145\1\144\3\141\1\146\3\141"+
        "\1\145\1\156\1\141\1\150\1\161\1\141\2\145\1\165\1\43\7\uffff\1"+
        "\72\1\75\1\43\1\uffff\1\76\2\uffff\1\55\1\41\1\75\1\57\1\56\2\uffff"+
        "\1\51\5\uffff\1\56\1\uffff\1\143\2\55\1\164\1\157\1\164\1\160\1"+
        "\uffff\1\163\1\165\1\55\1\146\1\157\1\156\1\163\1\145\1\154\1\143"+
        "\1\147\1\143\1\145\1\160\1\143\1\55\1\141\1\143\1\154\1\156\1\162"+
        "\1\157\1\55\1\145\1\55\1\151\1\55\1\160\2\55\1\145\1\163\2\55\1"+
        "\144\1\156\1\151\1\164\1\155\3\55\1\164\2\55\1\164\1\162\1\145\1"+
        "\162\1\142\1\141\1\156\1\164\1\150\1\154\1\155\1\141\2\151\1\155"+
        "\1\170\1\145\1\55\1\145\1\160\1\155\1\151\1\144\1\151\1\154\1\162"+
        "\1\145\1\156\1\165\1\116\1\162\1\171\1\145\1\11\15\uffff\1\170\13"+
        "\uffff\1\55\1\11\10\uffff\1\60\11\uffff\1\56\1\60\1\uffff\1\145"+
        "\1\55\1\145\1\uffff\1\162\1\uffff\2\145\1\163\1\157\1\55\1\145\1"+
        "\156\1\uffff\1\157\1\143\1\141\1\145\1\143\1\154\1\143\1\154\1\155"+
        "\1\163\1\171\1\156\1\151\1\141\1\143\2\145\1\55\1\151\1\165\1\155"+
        "\1\145\1\164\1\157\1\55\1\141\1\uffff\1\162\1\154\2\145\1\164\1"+
        "\154\1\55\1\143\1\163\1\155\1\uffff\1\141\1\165\1\uffff\1\166\1"+
        "\uffff\1\157\3\145\1\151\1\145\2\uffff\1\155\1\55\1\164\1\163\1"+
        "\55\2\uffff\1\55\1\165\1\156\1\165\1\141\1\145\1\164\1\uffff\1\151"+
        "\1\145\1\55\1\uffff\1\151\1\145\1\uffff\1\171\1\uffff\2\145\1\164"+
        "\2\143\1\166\1\55\1\154\1\165\1\141\1\154\1\141\1\144\1\147\1\151"+
        "\1\145\1\146\1\165\1\55\1\145\1\142\1\151\1\144\2\160\1\164\1\156"+
        "\1\uffff\1\141\1\55\1\145\1\142\1\157\1\162\1\141\1\156\2\151\1"+
        "\163\1\156\1\154\1\144\1\150\1\145\1\55\1\157\1\55\1\165\1\164\4"+
        "\uffff\1\55\3\uffff\1\60\1\163\1\uffff\1\156\1\151\1\162\1\156\1"+
        "\55\1\155\1\uffff\1\55\1\144\1\162\1\153\1\162\2\55\1\150\1\144"+
        "\1\153\1\141\1\145\1\164\1\145\1\55\1\164\1\141\1\155\1\165\1\145"+
        "\1\162\1\164\1\uffff\1\164\1\155\1\145\1\55\1\171\1\144\1\uffff"+
        "\1\154\1\171\1\55\1\160\1\162\1\55\1\157\1\141\1\uffff\2\164\1\55"+
        "\1\164\1\160\1\55\2\162\1\141\1\162\1\147\1\55\1\156\1\170\1\55"+
        "\1\uffff\1\55\1\164\1\uffff\1\154\1\146\1\uffff\1\163\1\164\1\141"+
        "\1\142\1\163\1\55\1\156\1\162\1\55\1\145\1\uffff\1\157\1\162\1\55"+
        "\1\162\1\156\3\145\1\151\1\145\1\141\1\145\1\155\1\151\1\162\1\155"+
        "\1\141\1\154\1\55\1\145\1\163\1\155\1\55\1\145\1\uffff\1\55\1\154"+
        "\1\164\1\143\1\151\1\55\1\154\2\55\1\164\1\uffff\1\163\1\154\1\156"+
        "\1\165\1\144\1\164\1\147\1\144\1\145\1\141\1\151\1\145\1\55\1\145"+
        "\1\157\1\55\1\162\1\uffff\1\55\1\uffff\1\145\1\55\1\0\1\164\1\144"+
        "\1\142\1\55\1\144\1\uffff\1\141\1\165\1\141\1\145\1\55\1\171\1\uffff"+
        "\1\142\1\uffff\3\55\1\164\1\143\1\156\1\55\1\170\1\156\1\uffff\1"+
        "\55\1\162\1\141\1\154\1\156\1\155\1\145\1\55\1\145\1\156\1\uffff"+
        "\1\55\2\151\1\55\1\uffff\1\164\1\156\1\uffff\1\167\1\143\1\147\1"+
        "\151\1\55\1\uffff\1\145\1\55\1\uffff\1\164\1\151\1\156\1\164\1\163"+
        "\1\162\1\uffff\1\151\1\55\2\uffff\1\55\1\145\1\171\1\55\1\141\2"+
        "\154\1\160\1\uffff\1\150\1\145\1\55\1\uffff\1\164\1\156\1\55\1\uffff"+
        "\1\55\1\164\1\162\1\144\1\162\1\157\1\163\1\164\1\156\1\151\1\143"+
        "\1\156\1\145\1\143\1\151\1\157\1\55\1\146\1\141\1\uffff\1\156\1"+
        "\uffff\1\145\1\55\1\164\1\55\1\156\1\uffff\1\145\2\uffff\1\55\1"+
        "\167\1\151\1\55\2\145\1\151\1\55\1\141\1\55\1\142\1\157\1\55\1\uffff"+
        "\1\55\1\167\1\uffff\1\171\1\144\1\55\3\uffff\1\157\1\151\1\165\1"+
        "\uffff\1\55\1\164\2\162\1\55\1\uffff\1\55\1\154\3\uffff\1\151\2"+
        "\164\1\141\1\156\1\uffff\1\164\1\141\1\uffff\1\145\1\154\1\164\1"+
        "\144\1\151\1\55\1\uffff\1\156\1\164\1\163\1\uffff\1\156\1\164\1"+
        "\uffff\1\55\1\141\1\151\1\150\1\156\1\157\1\uffff\1\163\1\156\1"+
        "\uffff\1\55\1\164\1\143\1\55\1\145\1\151\1\164\2\uffff\2\55\1\163"+
        "\1\151\1\154\1\145\1\141\1\145\1\163\1\uffff\1\145\1\55\1\144\1"+
        "\156\2\uffff\1\55\1\156\1\151\1\166\1\165\1\163\1\145\1\164\1\154"+
        "\3\55\1\145\1\144\1\156\1\uffff\1\151\1\55\1\164\1\55\1\uffff\1"+
        "\55\1\uffff\1\147\1\55\1\uffff\1\151\1\156\1\uffff\1\55\1\162\1"+
        "\156\1\uffff\1\164\1\uffff\1\154\1\156\2\uffff\2\55\1\151\1\uffff"+
        "\1\162\1\156\1\164\1\157\2\151\1\171\2\uffff\1\145\1\157\1\151\1"+
        "\55\1\143\1\151\1\164\1\55\1\155\3\55\1\141\1\156\1\uffff\1\164"+
        "\1\55\1\145\1\147\1\171\1\uffff\1\154\1\156\2\55\1\156\1\164\1\147"+
        "\1\uffff\1\55\1\145\1\uffff\1\143\1\164\1\171\2\uffff\1\151\1\156"+
        "\1\171\1\55\1\143\1\162\1\145\1\162\1\uffff\1\55\1\147\1\uffff\1"+
        "\55\1\156\1\145\1\163\1\151\2\55\1\154\1\uffff\1\156\2\uffff\1\55"+
        "\1\141\1\154\1\145\1\141\1\uffff\1\151\2\uffff\1\55\1\uffff\1\164"+
        "\1\147\1\uffff\1\145\1\147\2\145\1\55\2\uffff\1\147\1\55\1\147\1"+
        "\145\1\156\1\143\3\55\1\156\1\157\1\uffff\1\164\1\156\1\55\1\uffff"+
        "\1\145\1\uffff\1\146\1\uffff\2\156\1\151\1\55\1\uffff\1\161\3\55"+
        "\1\147\2\uffff\3\55\1\uffff\1\55\1\164\1\171\1\55\1\147\1\145\1"+
        "\55\1\uffff\1\145\1\151\1\162\1\155\1\uffff\1\55\1\163\1\147\2\55"+
        "\1\156\2\uffff\1\145\1\147\1\uffff\1\164\1\171\1\163\1\164\1\154"+
        "\1\141\1\uffff\1\143\1\55\1\144\3\55\1\uffff\1\151\1\157\1\uffff"+
        "\2\55\1\154\1\141\1\uffff\1\163\1\uffff\1\55\1\156\1\151\1\164\1"+
        "\uffff\1\163\1\157\1\145\1\164\1\147\1\163\1\156\1\uffff\1\165\3"+
        "\uffff\1\55\2\uffff\1\163\1\uffff\2\55\1\uffff\1\156\1\144\1\uffff"+
        "\1\55\1\164\1\166\1\151\1\uffff\1\145\1\55\2\uffff\1\147\2\55\1"+
        "\151\2\55\1\164\1\145\1\154\1\150\1\uffff\1\55\3\uffff\1\164\1\162"+
        "\2\uffff\1\171\1\154\1\160\1\uffff\1\55\1\157\1\55\1\160\1\162\1"+
        "\160\2\55\1\164\1\157\1\145\1\163\1\uffff\1\145\2\uffff\2\55\1\156"+
        "\1\uffff\1\55\1\145\1\156\1\160\1\163\1\uffff\1\55\2\uffff\1\157"+
        "\2\uffff\1\162\1\155\2\55\1\uffff\3\55\1\154\1\141\1\uffff\1\156"+
        "\1\uffff\1\141\1\155\1\141\1\157\2\uffff\1\151\1\144\1\156\1\151"+
        "\1\160\2\uffff\1\157\1\uffff\1\55\1\151\1\141\2\151\1\156\1\151"+
        "\1\145\3\uffff\1\163\1\uffff\1\171\1\143\1\55\1\143\1\141\2\162"+
        "\1\143\1\145\1\143\1\142\1\141\1\144\1\uffff\1\163\1\162\1\142\1"+
        "\156\1\55\1\142\1\156\1\145\1\55\1\145\1\uffff\1\145\1\164\1\141"+
        "\3\55\1\145\1\154\1\162\1\145\1\164\1\141\1\154\1\163\1\uffff\1"+
        "\165\1\164\1\154\1\uffff\1\55\1\163\1\55\1\164\1\163\2\uffff\1\55"+
        "\1\151\1\141\1\55\1\151\1\164\1\151\2\164\1\55\1\146\1\uffff\1\55"+
        "\1\uffff\1\157\1\145\1\uffff\1\156\1\164\1\uffff\1\143\1\157\1\156"+
        "\1\162\1\145\1\uffff\1\55\1\uffff\1\162\1\154\1\147\1\157\1\55\1"+
        "\162\1\147\1\165\1\55\1\uffff\1\55\1\146\1\55\1\162\1\uffff\2\55"+
        "\1\143\2\uffff\1\55\1\uffff\1\55\2\uffff\1\164\2\uffff\1\151\1\157"+
        "\1\156\1\55\1\uffff";
    static final String DFA19_maxS =
        "\1\uffff\1\165\1\171\2\157\1\170\1\165\3\164\1\165\1\157\2\165"+
        "\1\145\1\164\1\171\1\163\1\145\1\151\1\161\1\141\2\145\1\165\1\72"+
        "\7\uffff\2\75\1\43\1\uffff\1\76\2\uffff\1\55\1\77\2\76\1\71\2\uffff"+
        "\1\51\5\uffff\1\145\1\uffff\1\144\2\172\1\164\1\160\1\164\1\160"+
        "\1\uffff\1\163\1\165\1\172\1\146\1\157\1\156\1\164\1\151\1\165\1"+
        "\164\1\166\1\143\1\163\1\160\1\144\1\172\1\145\1\164\1\162\1\156"+
        "\1\162\1\157\1\172\1\157\1\172\1\151\1\172\1\160\2\172\1\145\1\170"+
        "\2\172\1\144\2\156\1\164\1\155\1\172\1\156\1\172\1\164\2\172\2\164"+
        "\1\157\1\162\1\142\1\166\1\156\1\164\1\150\1\164\1\155\1\162\2\151"+
        "\1\155\1\170\1\145\1\172\1\171\1\160\1\155\1\157\1\144\1\151\2\162"+
        "\1\151\1\164\1\165\1\116\1\162\1\171\1\157\1\172\15\uffff\1\170"+
        "\13\uffff\1\55\1\172\10\uffff\1\145\11\uffff\2\145\1\uffff\1\145"+
        "\1\172\1\145\1\uffff\1\162\1\uffff\2\145\1\163\1\157\1\172\1\145"+
        "\1\156\1\uffff\1\157\1\143\1\141\1\164\1\143\1\154\1\143\1\154\1"+
        "\155\1\164\1\171\1\156\1\154\1\141\1\143\2\145\1\172\1\151\1\165"+
        "\1\155\1\145\1\164\1\157\1\172\1\141\1\uffff\1\162\1\154\2\145\1"+
        "\164\1\154\1\172\1\143\1\163\1\155\1\uffff\1\141\1\165\1\uffff\1"+
        "\166\1\uffff\1\157\1\145\1\164\1\157\1\151\1\145\2\uffff\1\155\1"+
        "\172\1\164\1\163\1\172\2\uffff\1\172\1\165\1\156\1\165\1\141\1\145"+
        "\1\164\1\uffff\1\160\1\145\1\172\1\uffff\1\151\1\145\1\uffff\1\171"+
        "\1\uffff\2\145\1\164\1\166\1\143\1\166\1\143\1\154\1\165\1\141\1"+
        "\154\1\141\1\144\1\147\1\151\1\145\1\146\1\165\1\172\1\145\1\162"+
        "\1\151\1\144\2\160\1\164\1\156\1\uffff\1\141\1\172\1\145\1\142\1"+
        "\161\1\162\1\141\1\156\1\165\1\151\1\163\1\162\1\154\1\144\1\150"+
        "\1\145\1\172\1\157\1\172\1\165\1\164\4\uffff\1\55\3\uffff\1\145"+
        "\1\163\1\uffff\1\156\1\151\1\162\1\156\1\172\1\155\1\uffff\1\55"+
        "\1\144\1\162\1\153\1\162\2\172\1\150\1\144\1\153\2\145\1\164\1\145"+
        "\1\172\1\164\1\141\1\155\1\165\1\145\1\162\1\164\1\uffff\1\164\1"+
        "\155\1\145\1\172\1\171\1\144\1\uffff\1\154\1\171\1\172\1\160\1\162"+
        "\1\172\1\157\1\151\1\uffff\2\164\1\172\1\164\1\160\1\172\2\162\1"+
        "\141\2\162\1\172\1\156\1\170\1\172\1\uffff\1\172\1\164\1\uffff\1"+
        "\154\1\146\1\uffff\1\163\1\164\1\141\1\142\1\163\1\172\1\156\1\162"+
        "\1\172\1\145\1\uffff\1\157\1\162\1\172\1\162\1\156\3\145\1\151\1"+
        "\145\1\141\1\145\1\155\1\151\1\162\1\155\1\141\1\154\1\55\1\145"+
        "\1\163\1\155\1\172\1\145\1\uffff\1\172\1\154\1\164\1\160\1\151\1"+
        "\172\1\154\2\172\1\164\1\uffff\1\163\1\154\1\156\1\165\1\144\1\164"+
        "\1\147\1\144\1\145\1\141\1\151\1\145\1\172\1\145\1\157\1\172\1\162"+
        "\1\uffff\1\55\1\uffff\1\145\1\172\1\uffff\1\164\1\144\1\142\1\172"+
        "\1\144\1\uffff\1\141\1\165\1\141\1\145\1\172\1\171\1\uffff\1\142"+
        "\1\uffff\3\172\1\164\1\143\1\156\1\172\1\170\1\156\1\uffff\1\172"+
        "\1\162\1\141\1\154\1\156\1\155\1\145\1\172\1\145\1\156\1\uffff\1"+
        "\172\2\151\1\172\1\uffff\1\164\1\156\1\uffff\1\167\1\143\1\147\1"+
        "\151\1\172\1\uffff\1\145\1\172\1\uffff\1\164\1\151\1\156\1\164\1"+
        "\163\1\162\1\uffff\1\151\1\172\2\uffff\1\172\1\145\1\171\1\55\1"+
        "\141\2\154\1\160\1\uffff\1\150\1\145\1\172\1\uffff\1\164\1\156\1"+
        "\172\1\uffff\1\172\1\164\1\162\1\144\1\162\1\157\1\163\1\164\1\156"+
        "\1\151\1\143\1\156\1\145\1\143\1\151\1\157\1\172\1\146\1\141\1\uffff"+
        "\1\156\1\uffff\1\145\1\172\1\164\1\172\1\156\1\uffff\1\145\2\uffff"+
        "\1\172\1\167\1\151\1\172\2\145\1\151\1\172\1\141\1\172\1\142\1\157"+
        "\1\172\1\uffff\1\172\1\167\1\uffff\1\171\1\144\1\172\3\uffff\1\157"+
        "\1\151\1\165\1\uffff\1\55\1\164\2\162\1\172\1\uffff\1\172\1\154"+
        "\3\uffff\1\151\2\164\1\165\1\156\1\uffff\1\164\1\141\1\uffff\1\145"+
        "\1\154\1\164\1\144\1\151\1\172\1\uffff\1\156\1\164\1\163\1\uffff"+
        "\1\156\1\164\1\uffff\1\172\1\141\1\151\1\150\1\156\1\157\1\uffff"+
        "\1\163\1\156\1\uffff\1\172\1\164\1\143\1\172\1\145\1\151\1\164\2"+
        "\uffff\2\172\1\163\1\151\1\154\1\145\1\141\1\145\1\163\1\uffff\1"+
        "\145\1\172\1\144\1\156\2\uffff\1\172\1\156\1\151\1\166\1\165\1\163"+
        "\1\145\1\164\1\154\3\172\1\145\1\144\1\156\1\uffff\1\151\1\172\1"+
        "\164\1\172\1\uffff\1\172\1\uffff\1\147\1\172\1\uffff\1\151\1\156"+
        "\1\uffff\1\172\1\162\1\156\1\uffff\1\164\1\uffff\1\154\1\156\2\uffff"+
        "\2\172\1\151\1\uffff\1\162\1\156\1\164\1\157\2\151\1\171\2\uffff"+
        "\1\145\1\157\1\151\1\172\1\143\1\151\1\164\1\172\1\155\1\172\1\55"+
        "\1\172\1\151\1\156\1\uffff\1\164\1\172\1\145\1\147\1\171\1\uffff"+
        "\1\154\1\156\2\172\1\156\1\164\1\147\1\uffff\1\172\1\145\1\uffff"+
        "\1\143\1\164\1\171\2\uffff\1\151\1\156\1\171\1\172\1\143\1\162\1"+
        "\145\1\162\1\uffff\1\172\1\147\1\uffff\1\55\1\156\1\145\1\163\1"+
        "\151\2\172\1\154\1\uffff\1\156\2\uffff\1\172\1\141\1\154\2\145\1"+
        "\uffff\1\151\2\uffff\1\172\1\uffff\1\164\1\147\1\uffff\1\145\1\147"+
        "\2\145\1\172\2\uffff\1\147\1\172\1\147\1\145\1\156\1\143\1\172\1"+
        "\55\1\172\1\156\1\157\1\uffff\1\164\1\156\1\172\1\uffff\1\145\1"+
        "\uffff\1\163\1\uffff\2\156\1\151\1\172\1\uffff\1\161\3\172\1\147"+
        "\2\uffff\2\172\1\55\1\uffff\1\172\1\164\1\171\1\172\1\147\1\145"+
        "\1\172\1\uffff\1\145\1\151\1\162\1\155\1\uffff\1\172\1\163\1\147"+
        "\2\172\1\156\2\uffff\1\145\1\147\1\uffff\1\164\1\171\1\163\1\164"+
        "\1\154\1\141\1\uffff\1\143\1\172\1\144\3\172\1\uffff\1\151\1\157"+
        "\1\uffff\2\172\1\154\1\141\1\uffff\1\163\1\uffff\1\172\1\156\1\151"+
        "\1\164\1\uffff\1\163\1\157\1\145\1\164\1\147\1\163\1\156\1\uffff"+
        "\1\165\3\uffff\1\172\2\uffff\1\163\1\uffff\2\172\1\uffff\1\156\1"+
        "\144\1\uffff\1\172\1\164\1\166\1\151\1\uffff\1\145\1\172\2\uffff"+
        "\1\147\2\172\1\151\2\172\1\164\1\145\1\154\1\150\1\uffff\1\172\3"+
        "\uffff\1\164\1\162\2\uffff\1\171\1\154\1\160\1\uffff\1\172\1\157"+
        "\1\172\1\160\1\162\1\160\2\172\1\164\1\157\1\145\1\163\1\uffff\1"+
        "\145\2\uffff\2\172\1\156\1\uffff\1\172\1\145\1\156\1\160\1\163\1"+
        "\uffff\1\55\2\uffff\1\157\2\uffff\1\162\1\155\2\172\1\uffff\1\172"+
        "\1\55\1\172\1\154\1\141\1\uffff\1\156\1\uffff\1\141\1\155\1\141"+
        "\1\157\2\uffff\1\151\1\144\1\156\1\151\1\160\2\uffff\1\157\1\uffff"+
        "\1\172\1\151\1\141\2\151\1\156\1\151\1\145\3\uffff\1\163\1\uffff"+
        "\1\171\1\143\1\172\1\143\1\141\2\162\1\143\1\145\1\143\1\142\1\141"+
        "\1\144\1\uffff\1\163\1\162\1\142\1\156\1\172\1\142\1\156\1\145\1"+
        "\172\1\145\1\uffff\1\145\1\164\1\141\1\55\2\172\1\145\1\154\1\162"+
        "\1\145\1\164\1\141\1\154\1\163\1\uffff\1\165\1\164\1\154\1\uffff"+
        "\1\172\1\163\1\172\1\164\1\163\2\uffff\1\172\1\151\1\141\1\172\1"+
        "\151\1\164\1\151\2\164\1\172\1\146\1\uffff\1\172\1\uffff\1\157\1"+
        "\145\1\uffff\1\156\1\164\1\uffff\1\143\1\157\1\156\1\162\1\145\1"+
        "\uffff\1\172\1\uffff\1\162\1\154\1\147\1\157\1\172\1\162\1\147\1"+
        "\165\1\172\1\uffff\1\172\1\146\1\172\1\162\1\uffff\2\172\1\143\2"+
        "\uffff\1\172\1\uffff\1\172\2\uffff\1\164\2\uffff\1\151\1\157\1\156"+
        "\1\172\1\uffff";
    static final String DFA19_acceptS =
        "\32\uffff\1\u00b6\1\u00b7\1\u00b8\1\u00b9\1\u00ba\1\u00bb\1\u00bc"+
        "\3\uffff\1\u00c0\1\uffff\1\u00c2\1\u00c3\5\uffff\1\u00d3\1\u00d4"+
        "\1\uffff\1\u00db\1\u00de\1\u00df\1\u00e0\1\u00e1\1\uffff\1\u00e9"+
        "\7\uffff\1\u00e0\117\uffff\1\u00e8\1\u00b5\1\u00b6\1\u00b7\1\u00b8"+
        "\1\u00b9\1\u00ba\1\u00bb\1\u00bc\1\u00bd\1\u00d0\1\u00cf\1\u00be"+
        "\1\uffff\1\u00bf\1\u00c0\1\u00da\1\u00c1\1\u00c2\1\u00c3\1\u00d8"+
        "\1\u00c4\1\u00c7\1\u00c9\1\u00d2\2\uffff\1\u00c5\1\u00c8\1\u00ca"+
        "\1\u00c6\1\u00cc\1\u00d1\1\u00cb\1\u00ce\1\uffff\1\u00cd\1\u00d3"+
        "\1\u00d4\1\u00d6\1\u00db\1\u00de\1\u00df\1\u00e1\1\u00e5\2\uffff"+
        "\1\u00e7\3\uffff\1\4\1\uffff\1\6\7\uffff\1\12\32\uffff\1\40\12\uffff"+
        "\1\50\2\uffff\1\52\1\uffff\1\54\6\uffff\1\56\1\62\5\uffff\1\65\1"+
        "\70\7\uffff\1\74\3\uffff\1\100\2\uffff\1\102\1\uffff\1\u00ac\33"+
        "\uffff\1\127\25\uffff\1\u00d5\1\u00e2\1\u00dd\1\u00dc\1\uffff\1"+
        "\u00d9\1\u00e4\1\u00e6\2\uffff\1\3\6\uffff\1\u00b2\26\uffff\1\30"+
        "\6\uffff\1\150\10\uffff\1\46\17\uffff\1\64\2\uffff\1\67\2\uffff"+
        "\1\71\12\uffff\1\u00ab\30\uffff\1\u0096\12\uffff\1\173\21\uffff"+
        "\1\156\1\uffff\1\u00a7\10\uffff\1\u00b3\6\uffff\1\13\1\uffff\1\14"+
        "\11\uffff\1\u0082\12\uffff\1\34\4\uffff\1\u0099\2\uffff\1\u0093"+
        "\5\uffff\1\u00a4\2\uffff\1\53\6\uffff\1\u0086\2\uffff\1\63\1\u0087"+
        "\10\uffff\1\157\3\uffff\1\77\3\uffff\1\161\23\uffff\1\120\1\uffff"+
        "\1\121\5\uffff\1\u008d\1\uffff\1\125\1\126\15\uffff\1\175\2\uffff"+
        "\1\u0090\3\uffff\1\u00b4\1\u00d7\1\u00e3\3\uffff\1\u0080\5\uffff"+
        "\1\u0091\2\uffff\1\141\1\16\1\u009d\5\uffff\1\u00a0\2\uffff\1\143"+
        "\6\uffff\1\147\3\uffff\1\35\2\uffff\1\41\6\uffff\1\u0084\2\uffff"+
        "\1\151\7\uffff\1\u00a5\1\66\11\uffff\1\u0089\4\uffff\1\103\1\162"+
        "\17\uffff\1\u00ae\4\uffff\1\172\1\uffff\1\124\2\uffff\1\130\2\uffff"+
        "\1\132\3\uffff\1\u009a\1\uffff\1\u008f\2\uffff\1\137\1\u0098\3\uffff"+
        "\1\u00ad\7\uffff\1\u0081\1\u00b1\16\uffff\1\u0083\5\uffff\1\42\7"+
        "\uffff\1\55\2\uffff\1\u0085\3\uffff\1\72\1\u0088\10\uffff\1\101"+
        "\2\uffff\1\106\10\uffff\1\167\1\uffff\1\113\1\u008a\5\uffff\1\115"+
        "\1\uffff\1\122\1\123\1\uffff\1\u0097\2\uffff\1\u00b0\5\uffff\1\176"+
        "\1\140\13\uffff\1\20\3\uffff\1\142\1\uffff\1\23\1\uffff\1\24\4\uffff"+
        "\1\33\5\uffff\1\u00a2\1\u00a3\3\uffff\1\57\7\uffff\1\u00aa\4\uffff"+
        "\1\104\6\uffff\1\166\1\164\2\uffff\1\u008b\6\uffff\1\171\6\uffff"+
        "\1\136\2\uffff\1\1\4\uffff\1\10\1\uffff\1\15\4\uffff\1\u0092\7\uffff"+
        "\1\31\1\uffff\1\37\1\u00a1\1\43\1\uffff\1\47\1\51\1\uffff\1\60\2"+
        "\uffff\1\153\2\uffff\1\u00a9\4\uffff\1\105\2\uffff\1\111\1\170\12"+
        "\uffff\1\174\1\uffff\1\u008e\1\134\1\135\2\uffff\1\5\1\7\3\uffff"+
        "\1\17\14\uffff\1\44\1\uffff\1\61\1\u00a6\3\uffff\1\73\5\uffff\1"+
        "\107\1\uffff\1\165\1\u0094\1\uffff\1\u00af\1\114\4\uffff\1\133\5"+
        "\uffff\1\u009e\1\uffff\1\u009f\4\uffff\1\25\1\27\5\uffff\1\154\1"+
        "\u00a8\1\uffff\1\75\10\uffff\1\u0095\1\131\1\177\1\uffff\1\u009b"+
        "\15\uffff\1\76\12\uffff\1\21\16\uffff\1\u008c\3\uffff\1\u009c\5"+
        "\uffff\1\146\1\32\13\uffff\1\11\1\uffff\1\144\2\uffff\1\36\2\uffff"+
        "\1\155\5\uffff\1\117\1\uffff\1\22\11\uffff\1\2\4\uffff\1\160\3\uffff"+
        "\1\116\1\145\1\uffff\1\45\1\uffff\1\163\1\110\1\uffff\1\26\1\152"+
        "\4\uffff\1\112";
    static final String DFA19_specialS =
        "\1\1\u01db\uffff\1\0\u029f\uffff}>";
    static final String[] DFA19_transitionS = {
            "\11\66\2\64\2\66\1\64\22\66\1\64\1\42\1\62\1\57\1\33\1\66\1"+
            "\43\1\61\1\31\1\32\1\46\1\47\1\44\1\50\1\54\1\53\12\65\1\41"+
            "\1\55\1\51\1\40\1\52\1\45\1\60\15\63\1\25\14\63\1\36\1\66\1"+
            "\37\1\66\1\63\1\66\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\63\1\10\1\63"+
            "\1\27\1\11\1\12\1\13\1\14\1\15\1\30\1\16\1\17\1\20\1\21\1\22"+
            "\1\23\1\24\1\63\1\26\1\34\1\56\1\35\uff82\66",
            "\1\72\6\uffff\1\75\1\67\1\uffff\1\73\2\uffff\1\70\1\71\1\74",
            "\1\77\3\uffff\1\102\3\uffff\1\104\2\uffff\1\103\2\uffff\1"+
            "\100\11\uffff\1\101",
            "\1\105\6\uffff\1\106\6\uffff\1\107",
            "\1\110\3\uffff\1\111\5\uffff\1\112",
            "\1\113\1\114\1\115\2\uffff\1\116\4\uffff\1\117\1\uffff\1\120",
            "\1\123\5\uffff\1\121\2\uffff\1\124\2\uffff\1\122",
            "\1\125\14\uffff\1\126\1\uffff\1\127",
            "\1\130\1\uffff\1\131\6\uffff\1\132\1\133\4\uffff\1\134\1\135",
            "\1\136\3\uffff\1\137\16\uffff\1\140",
            "\1\143\7\uffff\1\142\5\uffff\1\141\5\uffff\1\144",
            "\1\145\3\uffff\1\146\11\uffff\1\147",
            "\1\150\7\uffff\1\153\1\uffff\1\151\1\uffff\1\152\2\uffff\1"+
            "\154",
            "\1\155\3\uffff\1\157\14\uffff\1\156\2\uffff\1\160",
            "\1\162\3\uffff\1\161",
            "\1\163\1\uffff\1\164\1\uffff\1\165\3\uffff\1\172\1\uffff\1"+
            "\171\1\170\2\uffff\1\166\4\uffff\1\167",
            "\1\173\2\uffff\1\174\6\uffff\1\175\2\uffff\1\176\2\uffff\1"+
            "\u0080\3\uffff\1\177",
            "\1\u0081\1\uffff\1\u0082\2\uffff\1\u0083",
            "\1\u0084\3\uffff\1\u0085",
            "\1\u0086\1\u0087",
            "\1\u0088",
            "\1\u0089",
            "\1\u008a",
            "\1\u008b",
            "\1\u008c",
            "\1\u008d\26\uffff\1\u008e",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\u0098\2\uffff\1\u0097",
            "\1\u009a",
            "\1\u009b",
            "",
            "\1\u009e",
            "",
            "",
            "\1\u00a2",
            "\1\u00a7\15\uffff\1\u00a6\14\uffff\1\u00a5\1\u00a4\1\uffff"+
            "\1\u00a8",
            "\1\u00aa\1\u00ab",
            "\1\u00ad\16\uffff\1\u00ae",
            "\1\u00b0\1\uffff\12\u00b1",
            "",
            "",
            "\1\u00b5",
            "",
            "",
            "",
            "",
            "",
            "\1\u00bc\1\uffff\12\u00bb\13\uffff\1\u00bd\37\uffff\1\u00bd",
            "",
            "\1\u00be\1\u00bf",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\2\76"+
            "\1\u00c0\27\76",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\23"+
            "\76\1\u00c2\6\76",
            "\1\u00c4",
            "\1\u00c6\1\u00c5",
            "\1\u00c7",
            "\1\u00c8",
            "",
            "\1\u00c9",
            "\1\u00ca",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u00cc",
            "\1\u00cd",
            "\1\u00ce",
            "\1\u00cf\1\u00d0",
            "\1\u00d2\3\uffff\1\u00d1",
            "\1\u00d3\1\u00d4\1\u00d5\1\uffff\1\u00d6\4\uffff\1\u00d7",
            "\1\u00d8\2\uffff\1\u00d9\5\uffff\1\u00dc\6\uffff\1\u00da\1"+
            "\u00db",
            "\1\u00de\16\uffff\1\u00dd",
            "\1\u00df",
            "\1\u00e0\15\uffff\1\u00e1",
            "\1\u00e2",
            "\1\u00e3\1\u00e4",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\24"+
            "\76\1\u00e5\5\76",
            "\1\u00e8\3\uffff\1\u00e7",
            "\1\u00e9\5\uffff\1\u00eb\12\uffff\1\u00ea",
            "\1\u00ec\5\uffff\1\u00ed",
            "\1\u00ee",
            "\1\u00ef",
            "\1\u00f0",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u00f2\11\uffff\1\u00f3",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u00f5",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u00f7",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\3\76"+
            "\1\u00fc\1\76\1\u00fb\1\76\1\u00f8\12\76\1\u00f9\1\u00fa\6\76",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u00ff",
            "\1\u0101\4\uffff\1\u0100",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\1\u0102"+
            "\22\76\1\u0103\6\76",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u0106",
            "\1\u0107",
            "\1\u0108\4\uffff\1\u0109",
            "\1\u010a",
            "\1\u010b",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\27"+
            "\76\1\u010c\2\76",
            "\1\u010e\66\uffff\1\u010f\11\uffff\1\u0110",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u0112",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\3\76"+
            "\1\u0113\26\76",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\13"+
            "\76\1\u0115\16\76",
            "\1\u0117",
            "\1\u0118\1\uffff\1\u0119",
            "\1\u011a\3\uffff\1\u011c\5\uffff\1\u011b",
            "\1\u011d",
            "\1\u011e",
            "\1\u0123\14\uffff\1\u0120\1\uffff\1\u0121\3\uffff\1\u011f"+
            "\1\uffff\1\u0122",
            "\1\u0124",
            "\1\u0125",
            "\1\u0126",
            "\1\u0127\4\uffff\1\u0128\2\uffff\1\u0129",
            "\1\u012a",
            "\1\u012b\20\uffff\1\u012c",
            "\1\u012d",
            "\1\u012e",
            "\1\u012f",
            "\1\u0130",
            "\1\u0131",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u0133\23\uffff\1\u0134",
            "\1\u0135",
            "\1\u0136",
            "\1\u0137\5\uffff\1\u0138",
            "\1\u0139",
            "\1\u013a",
            "\1\u013b\5\uffff\1\u013c",
            "\1\u013d",
            "\1\u013e\3\uffff\1\u013f",
            "\1\u0140\5\uffff\1\u0141",
            "\1\u0142",
            "\1\u0143",
            "\1\u0144",
            "\1\u0145",
            "\1\u0146\11\uffff\1\u0147",
            "\2\u0149\2\uffff\1\u0149\22\uffff\1\u0149\40\uffff\32\u0149"+
            "\4\uffff\1\u0149\1\uffff\32\u0149",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\u014a",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\u014c",
            "\2\u014e\2\uffff\1\u014e\22\uffff\1\u014e\40\uffff\32\u014e"+
            "\4\uffff\1\u014e\1\uffff\32\u014e",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\12\u00b1\13\uffff\1\u00bd\37\uffff\1\u00bd",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\u00bc\1\uffff\12\u00bb\13\uffff\1\u00bd\37\uffff\1\u00bd",
            "\12\u0150\13\uffff\1\u00bd\37\uffff\1\u00bd",
            "",
            "\1\u0151",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u0153",
            "",
            "\1\u0154",
            "",
            "\1\u0155",
            "\1\u0156",
            "\1\u0157",
            "\1\u0158",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u015a",
            "\1\u015b",
            "",
            "\1\u015c",
            "\1\u015d",
            "\1\u015e",
            "\1\u015f\16\uffff\1\u0160",
            "\1\u0161",
            "\1\u0162",
            "\1\u0163",
            "\1\u0164",
            "\1\u0165",
            "\1\u0166\1\u0167",
            "\1\u0168",
            "\1\u0169",
            "\1\u016b\2\uffff\1\u016a",
            "\1\u016c",
            "\1\u016d",
            "\1\u016e",
            "\1\u016f",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u0171",
            "\1\u0172",
            "\1\u0173",
            "\1\u0174",
            "\1\u0175",
            "\1\u0176",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u0178",
            "",
            "\1\u0179",
            "\1\u017a",
            "\1\u017b",
            "\1\u017c",
            "\1\u017d",
            "\1\u017e",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\4\76"+
            "\1\u017f\25\76",
            "\1\u0181",
            "\1\u0182",
            "\1\u0183",
            "",
            "\1\u0184",
            "\1\u0185",
            "",
            "\1\u0186",
            "",
            "\1\u0187",
            "\1\u0188",
            "\1\u018a\16\uffff\1\u0189",
            "\1\u018b\11\uffff\1\u018c",
            "\1\u018d",
            "\1\u018e",
            "",
            "",
            "\1\u018f",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u0191",
            "\1\u0192",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "",
            "",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\10"+
            "\76\1\u0195\13\76\1\u0194\5\76",
            "\1\u0197",
            "\1\u0198",
            "\1\u0199",
            "\1\u019a",
            "\1\u019b",
            "\1\u019c",
            "",
            "\1\u019d\6\uffff\1\u019e",
            "\1\u019f",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\3\76"+
            "\1\u01a0\26\76",
            "",
            "\1\u01a2",
            "\1\u01a3",
            "",
            "\1\u01a4",
            "",
            "\1\u01a5",
            "\1\u01a6",
            "\1\u01a7",
            "\1\u01a8\17\uffff\1\u01a9\2\uffff\1\u01aa",
            "\1\u01ab",
            "\1\u01ac",
            "\1\u01ae\65\uffff\1\u01ad",
            "\1\u01af",
            "\1\u01b0",
            "\1\u01b1",
            "\1\u01b2",
            "\1\u01b3",
            "\1\u01b4",
            "\1\u01b5",
            "\1\u01b6",
            "\1\u01b7",
            "\1\u01b8",
            "\1\u01b9",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u01bb",
            "\1\u01bc\17\uffff\1\u01bd",
            "\1\u01be",
            "\1\u01bf",
            "\1\u01c0",
            "\1\u01c1",
            "\1\u01c2",
            "\1\u01c3",
            "",
            "\1\u01c4",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u01c6",
            "\1\u01c7",
            "\1\u01c8\1\uffff\1\u01c9",
            "\1\u01ca",
            "\1\u01cb",
            "\1\u01cc",
            "\1\u01cd\13\uffff\1\u01ce",
            "\1\u01cf",
            "\1\u01d0",
            "\1\u01d2\3\uffff\1\u01d1",
            "\1\u01d3",
            "\1\u01d4",
            "\1\u01d5",
            "\1\u01d6",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u01d8",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u01da",
            "\1\u01db",
            "",
            "",
            "",
            "",
            "\1\u01dc",
            "",
            "",
            "",
            "\12\u0150\13\uffff\1\u00bd\37\uffff\1\u00bd",
            "\1\u01dd",
            "",
            "\1\u01de",
            "\1\u01df",
            "\1\u01e0",
            "\1\u01e1",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u01e3",
            "",
            "\1\u01e4",
            "\1\u01e5",
            "\1\u01e6",
            "\1\u01e7",
            "\1\u01e8",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\1\u01ea"+
            "\31\76",
            "\1\u01ec",
            "\1\u01ed",
            "\1\u01ee",
            "\1\u01ef\3\uffff\1\u01f0",
            "\1\u01f1",
            "\1\u01f2",
            "\1\u01f3",
            "\1\u01f4\1\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff"+
            "\32\76",
            "\1\u01f6",
            "\1\u01f7",
            "\1\u01f8",
            "\1\u01f9",
            "\1\u01fa",
            "\1\u01fb",
            "\1\u01fc",
            "",
            "\1\u01fd",
            "\1\u01fe",
            "\1\u01ff",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u0201",
            "\1\u0202",
            "",
            "\1\u0203",
            "\1\u0204",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u0206",
            "\1\u0207",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u0209",
            "\1\u020a\7\uffff\1\u020b",
            "",
            "\1\u020c",
            "\1\u020d",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u020f",
            "\1\u0210",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u0212",
            "\1\u0213",
            "\1\u0214",
            "\1\u0215",
            "\1\u0217\12\uffff\1\u0216",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u0219",
            "\1\u021a",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u021d",
            "",
            "\1\u021e",
            "\1\u021f",
            "",
            "\1\u0220",
            "\1\u0221",
            "\1\u0222",
            "\1\u0223",
            "\1\u0224",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u0226",
            "\1\u0227",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\22"+
            "\76\1\u0228\7\76",
            "\1\u022a",
            "",
            "\1\u022b",
            "\1\u022c",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u022e",
            "\1\u022f",
            "\1\u0230",
            "\1\u0231",
            "\1\u0232",
            "\1\u0233",
            "\1\u0234",
            "\1\u0235",
            "\1\u0236",
            "\1\u0237",
            "\1\u0238",
            "\1\u0239",
            "\1\u023a",
            "\1\u023b",
            "\1\u023c",
            "\1\u023d",
            "\1\u023e",
            "\1\u023f",
            "\1\u0240",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u0242",
            "",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u0244",
            "\1\u0245",
            "\1\u0246\14\uffff\1\u0247",
            "\1\u0248",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u024a",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u024d",
            "",
            "\1\u024e",
            "\1\u024f",
            "\1\u0250",
            "\1\u0251",
            "\1\u0252",
            "\1\u0253",
            "\1\u0254",
            "\1\u0255",
            "\1\u0256",
            "\1\u0257",
            "\1\u0258",
            "\1\u0259",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u025b",
            "\1\u025c",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u025e",
            "",
            "\1\u025f",
            "",
            "\1\u0260",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\0\u0263",
            "\1\u0264",
            "\1\u0265",
            "\1\u0266",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u0268",
            "",
            "\1\u0269",
            "\1\u026a",
            "\1\u026b",
            "\1\u026c",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u026e",
            "",
            "\1\u026f",
            "",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u0273",
            "\1\u0274",
            "\1\u0275",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\1\u0277"+
            "\20\76\1\u0276\10\76",
            "\1\u0279",
            "\1\u027a",
            "",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u027c",
            "\1\u027d",
            "\1\u027e",
            "\1\u027f",
            "\1\u0280",
            "\1\u0281",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u0283",
            "\1\u0284",
            "",
            "\1\u0285\1\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff"+
            "\32\76",
            "\1\u0287",
            "\1\u0288",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "",
            "\1\u028a",
            "\1\u028b",
            "",
            "\1\u028c",
            "\1\u028d",
            "\1\u028e",
            "\1\u028f",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "",
            "\1\u0291",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\10"+
            "\76\1\u0292\21\76",
            "",
            "\1\u0294",
            "\1\u0295",
            "\1\u0296",
            "\1\u0297",
            "\1\u0298",
            "\1\u0299",
            "",
            "\1\u029a",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "",
            "",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u029d",
            "\1\u029e",
            "\1\u029f",
            "\1\u02a0",
            "\1\u02a1",
            "\1\u02a2",
            "\1\u02a3",
            "",
            "\1\u02a4",
            "\1\u02a5",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "",
            "\1\u02a7",
            "\1\u02a8",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\4\76"+
            "\1\u02a9\3\76\1\u02aa\21\76",
            "",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u02ad",
            "\1\u02ae",
            "\1\u02af",
            "\1\u02b0",
            "\1\u02b1",
            "\1\u02b2",
            "\1\u02b3",
            "\1\u02b4",
            "\1\u02b5",
            "\1\u02b6",
            "\1\u02b7",
            "\1\u02b8",
            "\1\u02b9",
            "\1\u02ba",
            "\1\u02bb",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u02bd",
            "\1\u02be",
            "",
            "\1\u02bf",
            "",
            "\1\u02c0",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u02c2",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u02c4",
            "",
            "\1\u02c5",
            "",
            "",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u02c7",
            "\1\u02c8",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u02ca",
            "\1\u02cb",
            "\1\u02cc",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u02ce",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u02d0",
            "\1\u02d1",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u02d4",
            "",
            "\1\u02d5",
            "\1\u02d6",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "",
            "",
            "",
            "\1\u02d8",
            "\1\u02d9",
            "\1\u02da",
            "",
            "\1\u02db",
            "\1\u02dc",
            "\1\u02dd",
            "\1\u02de",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u02e1",
            "",
            "",
            "",
            "\1\u02e2",
            "\1\u02e3",
            "\1\u02e4",
            "\1\u02e6\23\uffff\1\u02e5",
            "\1\u02e7",
            "",
            "\1\u02e8",
            "\1\u02e9",
            "",
            "\1\u02ea",
            "\1\u02eb",
            "\1\u02ec",
            "\1\u02ed",
            "\1\u02ee",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "",
            "\1\u02f0",
            "\1\u02f1",
            "\1\u02f2",
            "",
            "\1\u02f3",
            "\1\u02f4",
            "",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u02f6",
            "\1\u02f7",
            "\1\u02f8",
            "\1\u02f9",
            "\1\u02fa",
            "",
            "\1\u02fb",
            "\1\u02fc",
            "",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u02fe",
            "\1\u02ff",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u0301",
            "\1\u0302",
            "\1\u0303",
            "",
            "",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u0306",
            "\1\u0307",
            "\1\u0308",
            "\1\u0309",
            "\1\u030a",
            "\1\u030b",
            "\1\u030c",
            "",
            "\1\u030d",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u030f",
            "\1\u0310",
            "",
            "",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u0312",
            "\1\u0313",
            "\1\u0314",
            "\1\u0315",
            "\1\u0316",
            "\1\u0317",
            "\1\u0318",
            "\1\u0319",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\10"+
            "\76\1\u031b\21\76",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u031e",
            "\1\u031f",
            "\1\u0320",
            "",
            "\1\u0321",
            "\1\u0322\1\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff"+
            "\32\76",
            "\1\u0324",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "",
            "\1\u0327",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "",
            "\1\u0329",
            "\1\u032a",
            "",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u032c",
            "\1\u032d",
            "",
            "\1\u032e",
            "",
            "\1\u032f",
            "\1\u0330",
            "",
            "",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u0333",
            "",
            "\1\u0334",
            "\1\u0335",
            "\1\u0336",
            "\1\u0337",
            "\1\u0338",
            "\1\u0339",
            "\1\u033a",
            "",
            "",
            "\1\u033b",
            "\1\u033c",
            "\1\u033d",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u033f",
            "\1\u0340",
            "\1\u0341",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u0343",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u0345",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u0347\7\uffff\1\u0348",
            "\1\u0349",
            "",
            "\1\u034a",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u034c",
            "\1\u034d",
            "\1\u034e",
            "",
            "\1\u034f",
            "\1\u0350",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u0353",
            "\1\u0354",
            "\1\u0355",
            "",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u0357",
            "",
            "\1\u0358",
            "\1\u0359",
            "\1\u035a",
            "",
            "",
            "\1\u035b",
            "\1\u035c",
            "\1\u035d",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u035f",
            "\1\u0360",
            "\1\u0361",
            "\1\u0362",
            "",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u0364",
            "",
            "\1\u0365",
            "\1\u0366",
            "\1\u0367",
            "\1\u0368",
            "\1\u0369",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u036c",
            "",
            "\1\u036d",
            "",
            "",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u036f",
            "\1\u0370",
            "\1\u0371",
            "\1\u0372\3\uffff\1\u0373",
            "",
            "\1\u0374",
            "",
            "",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "",
            "\1\u0376",
            "\1\u0377",
            "",
            "\1\u0378",
            "\1\u0379",
            "\1\u037a",
            "\1\u037b",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "",
            "",
            "\1\u037d",
            "\1\u037e\1\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff"+
            "\32\76",
            "\1\u0380",
            "\1\u0381",
            "\1\u0382",
            "\1\u0383",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u0385",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u0387",
            "\1\u0388",
            "",
            "\1\u0389",
            "\1\u038a",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "",
            "\1\u038c",
            "",
            "\1\u038d\14\uffff\1\u038e",
            "",
            "\1\u038f",
            "\1\u0390",
            "\1\u0391",
            "\1\u0392\1\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff"+
            "\32\76",
            "",
            "\1\u0394",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u0398",
            "",
            "",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u039b",
            "",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u039d",
            "\1\u039e",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u03a0",
            "\1\u03a1",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "",
            "\1\u03a3",
            "\1\u03a4",
            "\1\u03a5",
            "\1\u03a6",
            "",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u03a8",
            "\1\u03a9",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u03ac",
            "",
            "",
            "\1\u03ad",
            "\1\u03ae",
            "",
            "\1\u03af",
            "\1\u03b0",
            "\1\u03b1",
            "\1\u03b2",
            "\1\u03b3",
            "\1\u03b4",
            "",
            "\1\u03b5",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u03b7",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "",
            "\1\u03bb",
            "\1\u03bc",
            "",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u03bf",
            "\1\u03c0",
            "",
            "\1\u03c1",
            "",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u03c3",
            "\1\u03c4",
            "\1\u03c5",
            "",
            "\1\u03c6",
            "\1\u03c7",
            "\1\u03c8",
            "\1\u03c9",
            "\1\u03ca",
            "\1\u03cb",
            "\1\u03cc",
            "",
            "\1\u03cd",
            "",
            "",
            "",
            "\1\u03ce\1\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff"+
            "\32\76",
            "",
            "",
            "\1\u03d0",
            "",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "",
            "\1\u03d3",
            "\1\u03d4",
            "",
            "\1\u03d5\1\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff"+
            "\32\76",
            "\1\u03d7",
            "\1\u03d8",
            "\1\u03d9",
            "",
            "\1\u03da",
            "\1\u03db\1\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff"+
            "\32\76",
            "",
            "",
            "\1\u03dd",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u03e0",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u03e3",
            "\1\u03e4",
            "\1\u03e5",
            "\1\u03e6",
            "",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "",
            "",
            "",
            "\1\u03e8",
            "\1\u03e9",
            "",
            "",
            "\1\u03ea",
            "\1\u03eb",
            "\1\u03ec",
            "",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u03ee",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u03f0",
            "\1\u03f1",
            "\1\u03f2",
            "\1\u03f3\1\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff"+
            "\32\76",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u03f6",
            "\1\u03f7",
            "\1\u03f8",
            "\1\u03f9",
            "",
            "\1\u03fa",
            "",
            "",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u03fd",
            "",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u03ff",
            "\1\u0400",
            "\1\u0401",
            "\1\u0402",
            "",
            "\1\u0403",
            "",
            "",
            "\1\u0404",
            "",
            "",
            "\1\u0405",
            "\1\u0406",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u040a",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u040c",
            "\1\u040d",
            "",
            "\1\u040e",
            "",
            "\1\u040f",
            "\1\u0410",
            "\1\u0411",
            "\1\u0412",
            "",
            "",
            "\1\u0413",
            "\1\u0414",
            "\1\u0415",
            "\1\u0416",
            "\1\u0417",
            "",
            "",
            "\1\u0418",
            "",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u041a",
            "\1\u041b",
            "\1\u041c",
            "\1\u041d",
            "\1\u041e",
            "\1\u041f",
            "\1\u0420",
            "",
            "",
            "",
            "\1\u0421",
            "",
            "\1\u0422",
            "\1\u0423",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u0425",
            "\1\u0426",
            "\1\u0427",
            "\1\u0428",
            "\1\u0429",
            "\1\u042a",
            "\1\u042b",
            "\1\u042c",
            "\1\u042d",
            "\1\u042e",
            "",
            "\1\u042f",
            "\1\u0430",
            "\1\u0431",
            "\1\u0432",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u0434",
            "\1\u0435",
            "\1\u0436",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u0438",
            "",
            "\1\u0439",
            "\1\u043a",
            "\1\u043b",
            "\1\u043c",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u043f",
            "\1\u0440",
            "\1\u0441",
            "\1\u0442",
            "\1\u0443",
            "\1\u0444",
            "\1\u0445",
            "\1\u0446",
            "",
            "\1\u0447",
            "\1\u0448",
            "\1\u0449",
            "",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u044b",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u044d",
            "\1\u044e",
            "",
            "",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u0450",
            "\1\u0451",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u0453",
            "\1\u0454",
            "\1\u0455",
            "\1\u0456",
            "\1\u0457",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u0459",
            "",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "",
            "\1\u045b",
            "\1\u045c",
            "",
            "\1\u045d",
            "\1\u045e",
            "",
            "\1\u045f",
            "\1\u0460",
            "\1\u0461",
            "\1\u0462",
            "\1\u0463",
            "",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "",
            "\1\u0465",
            "\1\u0466",
            "\1\u0467",
            "\1\u0468",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u046a",
            "\1\u046b",
            "\1\u046c",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u046f",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u0471",
            "",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "\1\u0474",
            "",
            "",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            "",
            "",
            "\1\u0477",
            "",
            "",
            "\1\u0478",
            "\1\u0479",
            "\1\u047a",
            "\2\76\1\uffff\12\76\7\uffff\32\76\4\uffff\1\76\1\uffff\32"+
            "\76",
            ""
    };

    static final short[] DFA19_eot = DFA.unpackEncodedString(DFA19_eotS);
    static final short[] DFA19_eof = DFA.unpackEncodedString(DFA19_eofS);
    static final char[] DFA19_min = DFA.unpackEncodedStringToUnsignedChars(DFA19_minS);
    static final char[] DFA19_max = DFA.unpackEncodedStringToUnsignedChars(DFA19_maxS);
    static final short[] DFA19_accept = DFA.unpackEncodedString(DFA19_acceptS);
    static final short[] DFA19_special = DFA.unpackEncodedString(DFA19_specialS);
    static final short[][] DFA19_transition;

    static {
        int numStates = DFA19_transitionS.length;
        DFA19_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA19_transition[i] = DFA.unpackEncodedString(DFA19_transitionS[i]);
        }
    }

    class DFA19 extends DFA {

        public DFA19(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 19;
            this.eot = DFA19_eot;
            this.eof = DFA19_eof;
            this.min = DFA19_min;
            this.max = DFA19_max;
            this.accept = DFA19_accept;
            this.special = DFA19_special;
            this.transition = DFA19_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( ANCESTOR | ANCESTOR_OR_SELF | AND | AS | ASCENDING | AT | ATTRIBUTE | BASE_URI | BOUNDARY_SPACE | BY | CASE | CAST | CASTABLE | CHILD | COLLATION | COMMENT | CONSTRUCTION | COPY_NAMESPACES | DECLARE | DEFAULT | DESCENDANT | DESCENDANT_OR_SELF | DESCENDING | DIV | DOCUMENT | DOCUMENT_NODE | ELEMENT | ELSE | EMPTY | EMPTY_SEQUENCE | ENCODING | EQ | EVERY | EXCEPT | EXTERNAL | FOLLOWING | FOLLOWING_SIBLING | FOR | FUNCTION | GE | GREATEST | GT | IDIV | IF | IMPORT | IN | INHERIT | INSTANCE | INTERSECT | IS | ITEM | LAX | LE | LEAST | LET | LT | MOD | MODULE | NAMESPACE | NE | NO_INHERIT | NO_PRESERVE | NODE | OF | OPTION | OR | ORDER | ORDERED | ORDERING | PARENT | PRECEDING | PRECEDING_SIBLING | PRESERVE | PROCESSING_INSTRUCTION | RETURN | SATISFIES | SCHEMA | SCHEMA_ATTRIBUTE | SCHEMA_ELEMENT | SELF | SOME | STABLE | STRICT | STRIP | TEXT | THEN | TO | TREAT | TYPESWITCH | UNION | UNORDERED | VALIDATE | VARIABLE | VERSION | WHERE | XQUERY | CATCH | CONTEXT | COUNT | DECIMAL_FORMAT | DECIMAL_SEPARATOR | DETERMINISTIC | DIGIT | END | GROUP | GROUPING_SEPARATOR | INFINITY | MINUS_SIGN | NAMESPACE_NODE | NAN | NEXT | NONDETERMINISTIC | ONLY | OUTER | PATTERN_SEPARATOR | PERCENT | PER_MILLE | PRIVATE | PUBLIC | PREVIOUS | SLIDING | START | TRY | TUMBLING | WHEN | WINDOW | ZERO_DIGIT | AFTER | BEFORE | COPY | DELETE | FIRST | INSERT | INTO | LAST | MODIFY | NODES | RENAME | REPLACE | REVALIDATION | SKIP | UPDATING | VALUE | WITH | BLOCK | CONSTANT | EXIT | RETURNING | SEQUENTIAL | SET | SIMPLE | WHILE | EVAL | USING | APPEND_ONLY | AUTOMATICALLY | CHECK | COLLECTION | CONSTRAINT | CONST | EQUALITY | FOREACH | FOREIGN | FROM | INDEX | INTEGRITY | KEY | MAINTAINED | MANUALLY | MUTABLE | NON | ON | QUEUE | RANGE | READ_ONLY | UNIQUE | BINARY | AMP_ER | APOS_ER | QUOT_ER | LPAREN | RPAREN | DOLLAR | LBRACKET | RBRACKET | LSQUARE | RSQUARE | EQUAL | BIND | NOTEQUAL | AMP | COMMA | QUESTION | STAR | PLUS | MINUS | SMALLER | GREATER | SMALLEREQ | GREATEREQ | SMALLER_SMALLER | GREATER_GREATER | SLASH | SLASH_SLASH | DOT | DOT_DOT | COLON | COLON_COLON | EMPTY_CLOSE_TAG | CLOSE_TAG | SEMICOLON | VBAR | PRAGMA_START | PRAGMA_END | XML_COMMENT_START | XML_COMMENT_END | PI_START | PI_END | ATTR_SIGN | CHARREF_DEC | CHARREF_HEX | APOS | QUOT | L_NCName | S | L_Pragma | L_DirCommentConstructor | L_DirPIConstructor | L_IntegerLiteral | L_DecimalLiteral | L_DoubleLiteral | L_Comment | L_AnyChar );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA19_476 = input.LA(1);

                        s = -1;
                        if ( ((LA19_476>='\u0000' && LA19_476<='\uFFFF')) ) {s = 611;}

                        else s = 610;

                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA19_0 = input.LA(1);

                        s = -1;
                        if ( (LA19_0=='a') ) {s = 1;}

                        else if ( (LA19_0=='b') ) {s = 2;}

                        else if ( (LA19_0=='c') ) {s = 3;}

                        else if ( (LA19_0=='d') ) {s = 4;}

                        else if ( (LA19_0=='e') ) {s = 5;}

                        else if ( (LA19_0=='f') ) {s = 6;}

                        else if ( (LA19_0=='g') ) {s = 7;}

                        else if ( (LA19_0=='i') ) {s = 8;}

                        else if ( (LA19_0=='l') ) {s = 9;}

                        else if ( (LA19_0=='m') ) {s = 10;}

                        else if ( (LA19_0=='n') ) {s = 11;}

                        else if ( (LA19_0=='o') ) {s = 12;}

                        else if ( (LA19_0=='p') ) {s = 13;}

                        else if ( (LA19_0=='r') ) {s = 14;}

                        else if ( (LA19_0=='s') ) {s = 15;}

                        else if ( (LA19_0=='t') ) {s = 16;}

                        else if ( (LA19_0=='u') ) {s = 17;}

                        else if ( (LA19_0=='v') ) {s = 18;}

                        else if ( (LA19_0=='w') ) {s = 19;}

                        else if ( (LA19_0=='x') ) {s = 20;}

                        else if ( (LA19_0=='N') ) {s = 21;}

                        else if ( (LA19_0=='z') ) {s = 22;}

                        else if ( (LA19_0=='k') ) {s = 23;}

                        else if ( (LA19_0=='q') ) {s = 24;}

                        else if ( (LA19_0=='(') ) {s = 25;}

                        else if ( (LA19_0==')') ) {s = 26;}

                        else if ( (LA19_0=='$') ) {s = 27;}

                        else if ( (LA19_0=='{') ) {s = 28;}

                        else if ( (LA19_0=='}') ) {s = 29;}

                        else if ( (LA19_0=='[') ) {s = 30;}

                        else if ( (LA19_0==']') ) {s = 31;}

                        else if ( (LA19_0=='=') ) {s = 32;}

                        else if ( (LA19_0==':') ) {s = 33;}

                        else if ( (LA19_0=='!') ) {s = 34;}

                        else if ( (LA19_0=='&') ) {s = 35;}

                        else if ( (LA19_0==',') ) {s = 36;}

                        else if ( (LA19_0=='?') ) {s = 37;}

                        else if ( (LA19_0=='*') ) {s = 38;}

                        else if ( (LA19_0=='+') ) {s = 39;}

                        else if ( (LA19_0=='-') ) {s = 40;}

                        else if ( (LA19_0=='<') ) {s = 41;}

                        else if ( (LA19_0=='>') ) {s = 42;}

                        else if ( (LA19_0=='/') ) {s = 43;}

                        else if ( (LA19_0=='.') ) {s = 44;}

                        else if ( (LA19_0==';') ) {s = 45;}

                        else if ( (LA19_0=='|') ) {s = 46;}

                        else if ( (LA19_0=='#') ) {s = 47;}

                        else if ( (LA19_0=='@') ) {s = 48;}

                        else if ( (LA19_0=='\'') ) {s = 49;}

                        else if ( (LA19_0=='\"') ) {s = 50;}

                        else if ( ((LA19_0>='A' && LA19_0<='M')||(LA19_0>='O' && LA19_0<='Z')||LA19_0=='_'||LA19_0=='h'||LA19_0=='j'||LA19_0=='y') ) {s = 51;}

                        else if ( ((LA19_0>='\t' && LA19_0<='\n')||LA19_0=='\r'||LA19_0==' ') ) {s = 52;}

                        else if ( ((LA19_0>='0' && LA19_0<='9')) ) {s = 53;}

                        else if ( ((LA19_0>='\u0000' && LA19_0<='\b')||(LA19_0>='\u000B' && LA19_0<='\f')||(LA19_0>='\u000E' && LA19_0<='\u001F')||LA19_0=='%'||LA19_0=='\\'||LA19_0=='^'||LA19_0=='`'||(LA19_0>='~' && LA19_0<='\uFFFF')) ) {s = 54;}

                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 19, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

}