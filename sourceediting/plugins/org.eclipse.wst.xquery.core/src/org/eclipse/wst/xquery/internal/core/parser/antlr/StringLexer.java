// $ANTLR 3.2 Sep 23, 2009 12:02:23 StringLexer.g 2011-07-24 13:08:42

/*******************************************************************************
 * Copyright (c) 2008, 2009 28msec Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Gabriel Petrovay (28msec) - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.xquery.internal.core.parser.antlr;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class StringLexer extends XQDTLexer {
    public static final int INDEX=221;
    public static final int L_CDataSection=7;
    public static final int MINUS=252;
    public static final int NAMESPACE_NODE=124;
    public static final int END=119;
    public static final int INTO=150;
    public static final int PATTERN_SEPARATOR=130;
    public static final int GREATER_GREATER=258;
    public static final int RENAME=154;
    public static final int INSTANCE=63;
    public static final int CONTENT=164;
    public static final int LAX=67;
    public static final int STOP=193;
    public static final int IMPORT=60;
    public static final int BINARY=233;
    public static final int ON=228;
    public static final int DOT=261;
    public static final int CONSTRAINT=216;
    public static final int Letter=283;
    public static final int ORDER=82;
    public static final int CASTABLE=28;
    public static final int DIACRITICS=165;
    public static final int CONSTANT=204;
    public static final int DETERMINISTIC=117;
    public static final int EMPTY_CLOSE_TAG=265;
    public static final int TYPESWITCH=104;
    public static final int MODULE=73;
    public static final int ESCAPE_RBRACKET=11;
    public static final int AMP=247;
    public static final int RPAREN=238;
    public static final int VERSION=109;
    public static final int XML_COMMENT_START=271;
    public static final int EXACTLY=169;
    public static final int FTNOT=173;
    public static final int DECLARE=34;
    public static final int BOUNDARY_SPACE=24;
    public static final int CLOSE_TAG=266;
    public static final int NONDETERMINISTIC=127;
    public static final int OCCURS=182;
    public static final int UNION=105;
    public static final int STRIP=99;
    public static final int GROUPING_SEPARATOR=121;
    public static final int RANGE=230;
    public static final int HexLetter=284;
    public static final int SAME=187;
    public static final int WHEN=141;
    public static final int DESCENDING=38;
    public static final int DOCUMENT_NODE=41;
    public static final int ANCESTOR_OR_SELF=17;
    public static final int MUTABLE=226;
    public static final int SMALLER_SMALLER=257;
    public static final int RELATIONSHIP=186;
    public static final int ANY=162;
    public static final int CATCH=112;
    public static final int GE=55;
    public static final int ATTR_SIGN=275;
    public static final int ELSE=43;
    public static final int WORD=201;
    public static final int SU=288;
    public static final int PRAGMA_START=269;
    public static final int L_ElementContentChar=6;
    public static final int SENSITIVE=189;
    public static final int EVAL=211;
    public static final int SELF=95;
    public static final int DISTANCE=167;
    public static final int COUNT=114;
    public static final int TEXT=100;
    public static final int COLON=263;
    public static final int PARENT=85;
    public static final int SET=208;
    public static final int FTOR=174;
    public static final int SCORE=188;
    public static final int UNIQUE=232;
    public static final int FOREACH=219;
    public static final int PUBLIC=134;
    public static final int PERCENT=131;
    public static final int EMPTY_SEQUENCE=45;
    public static final int EXTERNAL=50;
    public static final int Digit=285;
    public static final int LAST=151;
    public static final int COLLECTION=215;
    public static final int DOT_DOT=262;
    public static final int DECIMAL_SEPARATOR=116;
    public static final int CAST=27;
    public static final int L_CharRef=9;
    public static final int AUTOMATICALLY=213;
    public static final int LBRACKET=240;
    public static final int MOD=72;
    public static final int EXCEPT=49;
    public static final int QUESTION=249;
    public static final int OR=81;
    public static final int AFTER=144;
    public static final int S=287;
    public static final int SMALLEREQ=255;
    public static final int BLOCK=203;
    public static final int BY=25;
    public static final int SCHEMA_ELEMENT=94;
    public static final int INFINITY=122;
    public static final int WEIGHT=198;
    public static final int TUMBLING=140;
    public static final int NO_INHERIT=76;
    public static final int LPAREN=237;
    public static final int UPPERCASE=196;
    public static final int PRECEDING_SIBLING=87;
    public static final int L_DecimalLiteral=293;
    public static final int EXIT=205;
    public static final int PI_START=273;
    public static final int APOS=278;
    public static final int SKIP=157;
    public static final int STEMMING=192;
    public static final int FROM=170;
    public static final int DELETE=147;
    public static final int TIMES=195;
    public static final int EMPTY=44;
    public static final int ASCENDING=20;
    public static final int QUEUE=229;
    public static final int WHILE=210;
    public static final int ESCAPE_QUOT=13;
    public static final int ONLY=128;
    public static final int SENTENCES=191;
    public static final int APOS_ER=235;
    public static final int NE=75;
    public static final int COMMENT=31;
    public static final int RETURNING=206;
    public static final int L_AnyChar=296;
    public static final int RSQUARE=243;
    public static final int ESCAPE_APOS=12;
    public static final int NCNameChar=281;
    public static final int EQUALITY=218;
    public static final int ENTIRE=168;
    public static final int WITH=160;
    public static final int IN=61;
    public static final int SOME=96;
    public static final int MOST=179;
    public static final int NEXT=126;
    public static final int RETURN=90;
    public static final int LET=70;
    public static final int IF=59;
    public static final int NODE=78;
    public static final int PER_MILLE=132;
    public static final int FOR=53;
    public static final int CHARREF_HEX=277;
    public static final int PRESERVE=88;
    public static final int DEFAULT=35;
    public static final int L_AposAttrContentChar=5;
    public static final int LEVELS=177;
    public static final int BEFORE=145;
    public static final int ATTRIBUTE=22;
    public static final int CHILD=29;
    public static final int Digits=286;
    public static final int CDATA_START=14;
    public static final int TRY=139;
    public static final int NOT=181;
    public static final int OPTION=80;
    public static final int L_DirCommentConstructor=290;
    public static final int COMMA=248;
    public static final int CONST=217;
    public static final int ELEMENT=42;
    public static final int INSENSITIVE=175;
    public static final int AS=19;
    public static final int DOCUMENT=40;
    public static final int ENCODING=46;
    public static final int NAN=125;
    public static final int TREAT=103;
    public static final int NAMESPACE=74;
    public static final int LEAST=69;
    public static final int THEN=101;
    public static final int PI_END=274;
    public static final int GREATEREQ=256;
    public static final int WORDS=202;
    public static final int FOREIGN=220;
    public static final int PARAGRAPHS=184;
    public static final int NCNameStartChar=280;
    public static final int PRIVATE=133;
    public static final int AND=18;
    public static final int BASE_URI=23;
    public static final int TO=102;
    public static final int FUNCTION=54;
    public static final int L_Pragma=289;
    public static final int READ_ONLY=231;
    public static final int LANGUAGE=176;
    public static final int RBRACKET=241;
    public static final int LE=68;
    public static final int LOWERCASE=178;
    public static final int SCHEMA=92;
    public static final int CONSTRUCTION=32;
    public static final int WILDCARDS=199;
    public static final int PLUS=251;
    public static final int DIFFERENT=166;
    public static final int L_DoubleLiteral=294;
    public static final int NON=227;
    public static final int AT=21;
    public static final int INTERSECT=64;
    public static final int L_QuotAttrContentChar=4;
    public static final int LSQUARE=242;
    public static final int GREATEST=56;
    public static final int APPEND_ONLY=212;
    public static final int MAINTAINED=224;
    public static final int EQ=47;
    public static final int L_AposStringLiteralChar=298;
    public static final int ESCAPE_LBRACKET=10;
    public static final int LT=71;
    public static final int OF=79;
    public static final int DOLLAR=239;
    public static final int WINDOW=142;
    public static final int FOLLOWING=51;
    public static final int CASE=26;
    public static final int CDATA_END=15;
    public static final int DESCENDANT_OR_SELF=37;
    public static final int EQUAL=244;
    public static final int SEMICOLON=267;
    public static final int THESAURUS=194;
    public static final int CHECK=214;
    public static final int KEY=223;
    public static final int FTAND=172;
    public static final int FIRST=148;
    public static final int SIMPLE=209;
    public static final int PARAGRAPH=183;
    public static final int DIV=39;
    public static final int ALL=161;
    public static final int FT_OPTION=171;
    public static final int REVALIDATION=156;
    public static final int INSERT=149;
    public static final int QUOT=279;
    public static final int WHERE=110;
    public static final int COPY=146;
    public static final int PREVIOUS=135;
    public static final int WITHOUT=200;
    public static final int AMP_ER=234;
    public static final int USING=197;
    public static final int SCHEMA_ATTRIBUTE=93;
    public static final int EVERY=48;
    public static final int CONTEXT=113;
    public static final int XQUERY=111;
    public static final int INTEGRITY=222;
    public static final int SLIDING=136;
    public static final int UPDATING=158;
    public static final int IDIV=58;
    public static final int PRAGMA_END=270;
    public static final int SATISFIES=91;
    public static final int VALUE=159;
    public static final int DESCENDANT=36;
    public static final int STRICT=98;
    public static final int NOTEQUAL=246;
    public static final int COLON_COLON=264;
    public static final int L_PredefinedEntityRef=8;
    public static final int L_IntegerLiteral=292;
    public static final int FOLLOWING_SIBLING=52;
    public static final int STABLE=97;
    public static final int CHARREF_DEC=276;
    public static final int DECIMAL_FORMAT=115;
    public static final int START=137;
    public static final int GROUP=120;
    public static final int VALIDATE=107;
    public static final int CONTAINS=163;
    public static final int PRECEDING=86;
    public static final int ZERO_DIGIT=143;
    public static final int MINUS_SIGN=123;
    public static final int GREATER=254;
    public static final int VBAR=268;
    public static final int DIGIT=118;
    public static final int COPY_NAMESPACES=33;
    public static final int ORDERING=84;
    public static final int NO_PRESERVE=77;
    public static final int UNORDERED=106;
    public static final int OUTER=129;
    public static final int SENTENCE=190;
    public static final int L_NCName=282;
    public static final int INHERIT=62;
    public static final int SLASH=259;
    public static final int SWITCH=138;
    public static final int L_DirPIConstructor=291;
    public static final int IS=65;
    public static final int REPLACE=155;
    public static final int NO=180;
    public static final int GT=57;
    public static final int PHRASE=185;
    public static final int L_QuotStringLiteralChar=297;
    public static final int ITEM=66;
    public static final int ORDERED=83;
    public static final int PROCESSING_INSTRUCTION=89;
    public static final int COLLATION=30;
    public static final int SLASH_SLASH=260;
    public static final int ANCESTOR=16;
    public static final int SEQUENTIAL=207;
    public static final int SMALLER=253;
    public static final int NODES=153;
    public static final int MANUALLY=225;
    public static final int VARIABLE=108;
    public static final int EOF=-1;
    public static final int MODIFY=152;
    public static final int QUOT_ER=236;
    public static final int STAR=250;
    public static final int BIND=245;
    public static final int L_Comment=295;
    public static final int XML_COMMENT_END=272;

    // dummy list for warning elimination
    List<Stack<Object>> dummy = new ArrayList<Stack<Object>>();

    boolean inQuotStr = false;
    boolean inAposStr = false;

    public StringLexer(CharStream input, boolean isAposStr) {
    	this(input, new RecognizerSharedState());
    	this.inAposStr = isAposStr;
    	this.inQuotStr = !isAposStr;
    	setIsWsExplicit(true);
    }


    // delegates
    // delegators

    public StringLexer() {;} 
    public StringLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public StringLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "StringLexer.g"; }

    // $ANTLR start "QUOT"
    public final void mQUOT() throws RecognitionException {
        try {
            int _type = QUOT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // StringLexer.g:38:6: ({...}? => '\"' )
            // StringLexer.g:38:8: {...}? => '\"'
            {
            if ( !(( inQuotStr )) ) {
                throw new FailedPredicateException(input, "QUOT", " inQuotStr ");
            }
            match('\"'); 
             inQuotStr = !inQuotStr; 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "QUOT"

    // $ANTLR start "APOS"
    public final void mAPOS() throws RecognitionException {
        try {
            int _type = APOS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // StringLexer.g:39:6: ({...}? => '\\'' )
            // StringLexer.g:39:8: {...}? => '\\''
            {
            if ( !(( inAposStr )) ) {
                throw new FailedPredicateException(input, "APOS", " inAposStr ");
            }
            match('\''); 
             inAposStr = !inAposStr; 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "APOS"

    // $ANTLR start "ESCAPE_QUOT"
    public final void mESCAPE_QUOT() throws RecognitionException {
        try {
            int _type = ESCAPE_QUOT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // StringLexer.g:40:13: ({...}? => '\"\"' )
            // StringLexer.g:40:15: {...}? => '\"\"'
            {
            if ( !(( inQuotStr )) ) {
                throw new FailedPredicateException(input, "ESCAPE_QUOT", " inQuotStr ");
            }
            match("\"\""); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ESCAPE_QUOT"

    // $ANTLR start "ESCAPE_APOS"
    public final void mESCAPE_APOS() throws RecognitionException {
        try {
            int _type = ESCAPE_APOS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // StringLexer.g:41:13: ({...}? => '\\'\\'' )
            // StringLexer.g:41:15: {...}? => '\\'\\''
            {
            if ( !(( inAposStr )) ) {
                throw new FailedPredicateException(input, "ESCAPE_APOS", " inAposStr ");
            }
            match("''"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ESCAPE_APOS"

    // $ANTLR start "L_PredefinedEntityRef"
    public final void mL_PredefinedEntityRef() throws RecognitionException {
        try {
            int _type = L_PredefinedEntityRef;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // StringLexer.g:45:2: ({...}? => '&' ( 'lt' | 'gt' | 'apos' | 'quot' | 'amp' ) ';' )
            // StringLexer.g:45:4: {...}? => '&' ( 'lt' | 'gt' | 'apos' | 'quot' | 'amp' ) ';'
            {
            if ( !(( inQuotStr | inAposStr )) ) {
                throw new FailedPredicateException(input, "L_PredefinedEntityRef", " inQuotStr | inAposStr ");
            }
            match('&'); 
            // StringLexer.g:45:38: ( 'lt' | 'gt' | 'apos' | 'quot' | 'amp' )
            int alt1=5;
            switch ( input.LA(1) ) {
            case 'l':
                {
                alt1=1;
                }
                break;
            case 'g':
                {
                alt1=2;
                }
                break;
            case 'a':
                {
                int LA1_3 = input.LA(2);

                if ( (LA1_3=='p') ) {
                    alt1=3;
                }
                else if ( (LA1_3=='m') ) {
                    alt1=5;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 1, 3, input);

                    throw nvae;
                }
                }
                break;
            case 'q':
                {
                alt1=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }

            switch (alt1) {
                case 1 :
                    // StringLexer.g:45:39: 'lt'
                    {
                    match("lt"); 


                    }
                    break;
                case 2 :
                    // StringLexer.g:45:46: 'gt'
                    {
                    match("gt"); 


                    }
                    break;
                case 3 :
                    // StringLexer.g:45:53: 'apos'
                    {
                    match("apos"); 


                    }
                    break;
                case 4 :
                    // StringLexer.g:45:62: 'quot'
                    {
                    match("quot"); 


                    }
                    break;
                case 5 :
                    // StringLexer.g:45:71: 'amp'
                    {
                    match("amp"); 


                    }
                    break;

            }

            match(';'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "L_PredefinedEntityRef"

    // $ANTLR start "L_CharRef"
    public final void mL_CharRef() throws RecognitionException {
        try {
            int _type = L_CharRef;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // StringLexer.g:50:2: ({...}? => '&#' ( '0' .. '9' )+ ';' | '&#x' ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' )+ ';' )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0=='&') ) {
                int LA4_1 = input.LA(2);

                if ( (LA4_1=='#') ) {
                    int LA4_2 = input.LA(3);

                    if ( (LA4_2=='x') ) {
                        alt4=2;
                    }
                    else if ( ((LA4_2>='0' && LA4_2<='9')) && (( inQuotStr | inAposStr ))) {
                        alt4=1;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 4, 2, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 4, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
                case 1 :
                    // StringLexer.g:50:4: {...}? => '&#' ( '0' .. '9' )+ ';'
                    {
                    if ( !(( inQuotStr | inAposStr )) ) {
                        throw new FailedPredicateException(input, "L_CharRef", " inQuotStr | inAposStr ");
                    }
                    match("&#"); 

                    // StringLexer.g:50:39: ( '0' .. '9' )+
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
                    	    // StringLexer.g:50:39: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

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

                    match(';'); 

                    }
                    break;
                case 2 :
                    // StringLexer.g:50:55: '&#x' ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' )+ ';'
                    {
                    match("&#x"); 

                    // StringLexer.g:50:61: ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' )+
                    int cnt3=0;
                    loop3:
                    do {
                        int alt3=2;
                        int LA3_0 = input.LA(1);

                        if ( ((LA3_0>='0' && LA3_0<='9')||(LA3_0>='A' && LA3_0<='F')||(LA3_0>='a' && LA3_0<='f')) ) {
                            alt3=1;
                        }


                        switch (alt3) {
                    	case 1 :
                    	    // StringLexer.g:
                    	    {
                    	    if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='F')||(input.LA(1)>='a' && input.LA(1)<='f') ) {
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

                    match(';'); 

                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "L_CharRef"

    // $ANTLR start "L_QuotStringLiteralChar"
    public final void mL_QuotStringLiteralChar() throws RecognitionException {
        try {
            int _type = L_QuotStringLiteralChar;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // StringLexer.g:54:2: ({...}? => ( '\\u0009' | '\\u000A' | '\\u000D' | '\\u0020' .. '\\u0021' | '\\u0023' .. '\\u0025' | '\\u0027' .. '\\uD7FF' | '\\uE000' .. '\\uFFFD' )+ )
            // StringLexer.g:54:4: {...}? => ( '\\u0009' | '\\u000A' | '\\u000D' | '\\u0020' .. '\\u0021' | '\\u0023' .. '\\u0025' | '\\u0027' .. '\\uD7FF' | '\\uE000' .. '\\uFFFD' )+
            {
            if ( !(( inQuotStr )) ) {
                throw new FailedPredicateException(input, "L_QuotStringLiteralChar", " inQuotStr ");
            }
            // StringLexer.g:55:3: ( '\\u0009' | '\\u000A' | '\\u000D' | '\\u0020' .. '\\u0021' | '\\u0023' .. '\\u0025' | '\\u0027' .. '\\uD7FF' | '\\uE000' .. '\\uFFFD' )+
            int cnt5=0;
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( ((LA5_0>='\t' && LA5_0<='\n')||LA5_0=='\r'||(LA5_0>=' ' && LA5_0<='!')||(LA5_0>='#' && LA5_0<='%')||(LA5_0>='\'' && LA5_0<='\uD7FF')||(LA5_0>='\uE000' && LA5_0<='\uFFFD')) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // StringLexer.g:
            	    {
            	    if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||input.LA(1)=='\r'||(input.LA(1)>=' ' && input.LA(1)<='!')||(input.LA(1)>='#' && input.LA(1)<='%')||(input.LA(1)>='\'' && input.LA(1)<='\uD7FF')||(input.LA(1)>='\uE000' && input.LA(1)<='\uFFFD') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    if ( cnt5 >= 1 ) break loop5;
                        EarlyExitException eee =
                            new EarlyExitException(5, input);
                        throw eee;
                }
                cnt5++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "L_QuotStringLiteralChar"

    // $ANTLR start "L_AposStringLiteralChar"
    public final void mL_AposStringLiteralChar() throws RecognitionException {
        try {
            int _type = L_AposStringLiteralChar;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // StringLexer.g:60:2: ({...}? => ( '\\u0009' | '\\u000A' | '\\u000D' | '\\u0020' .. '\\u0025' | '\\u0028' .. '\\uD7FF' | '\\uE000' .. '\\uFFFD' )+ )
            // StringLexer.g:60:4: {...}? => ( '\\u0009' | '\\u000A' | '\\u000D' | '\\u0020' .. '\\u0025' | '\\u0028' .. '\\uD7FF' | '\\uE000' .. '\\uFFFD' )+
            {
            if ( !(( inAposStr )) ) {
                throw new FailedPredicateException(input, "L_AposStringLiteralChar", " inAposStr ");
            }
            // StringLexer.g:61:3: ( '\\u0009' | '\\u000A' | '\\u000D' | '\\u0020' .. '\\u0025' | '\\u0028' .. '\\uD7FF' | '\\uE000' .. '\\uFFFD' )+
            int cnt6=0;
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( ((LA6_0>='\t' && LA6_0<='\n')||LA6_0=='\r'||(LA6_0>=' ' && LA6_0<='%')||(LA6_0>='(' && LA6_0<='\uD7FF')||(LA6_0>='\uE000' && LA6_0<='\uFFFD')) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // StringLexer.g:
            	    {
            	    if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||input.LA(1)=='\r'||(input.LA(1)>=' ' && input.LA(1)<='%')||(input.LA(1)>='(' && input.LA(1)<='\uD7FF')||(input.LA(1)>='\uE000' && input.LA(1)<='\uFFFD') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    if ( cnt6 >= 1 ) break loop6;
                        EarlyExitException eee =
                            new EarlyExitException(6, input);
                        throw eee;
                }
                cnt6++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "L_AposStringLiteralChar"

    // $ANTLR start "L_AnyChar"
    public final void mL_AnyChar() throws RecognitionException {
        try {
            int _type = L_AnyChar;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // StringLexer.g:70:5: ({...}? => ( '\\u0009' | '\\u000A' | '\\u000D' | '\\u0020' .. '\\u0025' | '\\u0027' .. '\\u003B' | '\\u003D' .. '\\u007A' | '\\u007C' | '\\u007E' .. '\\uD7FF' | '\\uE000' .. '\\uFFFD' )+ )
            // StringLexer.g:70:9: {...}? => ( '\\u0009' | '\\u000A' | '\\u000D' | '\\u0020' .. '\\u0025' | '\\u0027' .. '\\u003B' | '\\u003D' .. '\\u007A' | '\\u007C' | '\\u007E' .. '\\uD7FF' | '\\uE000' .. '\\uFFFD' )+
            {
            if ( !(( !inQuotStr && !inAposStr )) ) {
                throw new FailedPredicateException(input, "L_AnyChar", " !inQuotStr && !inAposStr ");
            }
            // StringLexer.g:71:9: ( '\\u0009' | '\\u000A' | '\\u000D' | '\\u0020' .. '\\u0025' | '\\u0027' .. '\\u003B' | '\\u003D' .. '\\u007A' | '\\u007C' | '\\u007E' .. '\\uD7FF' | '\\uE000' .. '\\uFFFD' )+
            int cnt7=0;
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( ((LA7_0>='\t' && LA7_0<='\n')||LA7_0=='\r'||(LA7_0>=' ' && LA7_0<='%')||(LA7_0>='\'' && LA7_0<=';')||(LA7_0>='=' && LA7_0<='z')||LA7_0=='|'||(LA7_0>='~' && LA7_0<='\uD7FF')||(LA7_0>='\uE000' && LA7_0<='\uFFFD')) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // StringLexer.g:
            	    {
            	    if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||input.LA(1)=='\r'||(input.LA(1)>=' ' && input.LA(1)<='%')||(input.LA(1)>='\'' && input.LA(1)<=';')||(input.LA(1)>='=' && input.LA(1)<='z')||input.LA(1)=='|'||(input.LA(1)>='~' && input.LA(1)<='\uD7FF')||(input.LA(1)>='\uE000' && input.LA(1)<='\uFFFD') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    if ( cnt7 >= 1 ) break loop7;
                        EarlyExitException eee =
                            new EarlyExitException(7, input);
                        throw eee;
                }
                cnt7++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "L_AnyChar"

    public void mTokens() throws RecognitionException {
        // StringLexer.g:1:8: ( QUOT | APOS | ESCAPE_QUOT | ESCAPE_APOS | L_PredefinedEntityRef | L_CharRef | L_QuotStringLiteralChar | L_AposStringLiteralChar | L_AnyChar )
        int alt8=9;
        alt8 = dfa8.predict(input);
        switch (alt8) {
            case 1 :
                // StringLexer.g:1:10: QUOT
                {
                mQUOT(); 

                }
                break;
            case 2 :
                // StringLexer.g:1:15: APOS
                {
                mAPOS(); 

                }
                break;
            case 3 :
                // StringLexer.g:1:20: ESCAPE_QUOT
                {
                mESCAPE_QUOT(); 

                }
                break;
            case 4 :
                // StringLexer.g:1:32: ESCAPE_APOS
                {
                mESCAPE_APOS(); 

                }
                break;
            case 5 :
                // StringLexer.g:1:44: L_PredefinedEntityRef
                {
                mL_PredefinedEntityRef(); 

                }
                break;
            case 6 :
                // StringLexer.g:1:66: L_CharRef
                {
                mL_CharRef(); 

                }
                break;
            case 7 :
                // StringLexer.g:1:76: L_QuotStringLiteralChar
                {
                mL_QuotStringLiteralChar(); 

                }
                break;
            case 8 :
                // StringLexer.g:1:100: L_AposStringLiteralChar
                {
                mL_AposStringLiteralChar(); 

                }
                break;
            case 9 :
                // StringLexer.g:1:124: L_AnyChar
                {
                mL_AnyChar(); 

                }
                break;

        }

    }


    protected DFA8 dfa8 = new DFA8(this);
    static final String DFA8_eotS =
        "\1\uffff\1\7\1\14\1\uffff\1\21\1\22\1\23\1\uffff\1\25\2\uffff\1"+
        "\26\1\uffff\1\30\15\uffff";
    static final String DFA8_eofS =
        "\33\uffff";
    static final String DFA8_minS =
        "\3\11\1\43\3\11\1\0\1\11\2\uffff\1\11\1\0\1\11\3\uffff\3\0\1\uffff"+
        "\2\0\1\uffff\1\0\2\uffff";
    static final String DFA8_maxS =
        "\3\ufffd\1\161\3\ufffd\1\0\1\ufffd\2\uffff\1\ufffd\1\0\1\ufffd"+
        "\3\uffff\3\0\1\uffff\2\0\1\uffff\1\0\2\uffff";
    static final String DFA8_acceptS =
        "\11\uffff\1\11\1\10\3\uffff\1\7\1\6\1\5\3\uffff\1\1\2\uffff\1\2"+
        "\1\uffff\1\3\1\4";
    static final String DFA8_specialS =
        "\1\16\1\4\1\12\1\21\1\2\1\14\1\6\1\1\1\20\2\uffff\1\3\1\0\1\13"+
        "\3\uffff\1\15\1\7\1\10\1\uffff\1\5\1\11\1\uffff\1\17\2\uffff}>";
    static final String[] DFA8_transitionS = {
            "\2\4\2\uffff\1\4\22\uffff\2\4\1\1\3\4\1\3\1\2\24\4\1\5\76\4"+
            "\1\5\1\4\1\5\ud782\4\u0800\uffff\u1ffe\4",
            "\2\10\2\uffff\1\10\22\uffff\2\10\1\6\3\10\1\uffff\1\11\24"+
            "\10\1\12\76\10\1\12\1\10\1\12\ud782\10\u0800\uffff\u1ffe\10",
            "\2\15\2\uffff\1\15\22\uffff\2\15\1\11\3\15\1\uffff\1\13\24"+
            "\15\1\16\76\15\1\16\1\15\1\16\ud782\15\u0800\uffff\u1ffe\15",
            "\1\17\75\uffff\1\20\5\uffff\1\20\4\uffff\1\20\4\uffff\1\20",
            "\2\4\2\uffff\1\4\22\uffff\2\4\1\10\3\4\1\uffff\1\15\24\4\1"+
            "\5\76\4\1\5\1\4\1\5\ud782\4\u0800\uffff\u1ffe\4",
            "\2\5\2\uffff\1\5\22\uffff\2\5\1\12\3\5\1\uffff\1\16\ud7d8"+
            "\5\u0800\uffff\u1ffe\5",
            "\2\10\2\uffff\1\10\22\uffff\6\10\1\uffff\1\11\24\10\1\12\76"+
            "\10\1\12\1\10\1\12\ud782\10\u0800\uffff\u1ffe\10",
            "\1\uffff",
            "\2\10\2\uffff\1\10\22\uffff\6\10\1\uffff\1\11\24\10\1\12\76"+
            "\10\1\12\1\10\1\12\ud782\10\u0800\uffff\u1ffe\10",
            "",
            "",
            "\2\15\2\uffff\1\15\22\uffff\2\15\1\11\3\15\1\uffff\25\15\1"+
            "\16\76\15\1\16\1\15\1\16\ud782\15\u0800\uffff\u1ffe\15",
            "\1\uffff",
            "\2\15\2\uffff\1\15\22\uffff\2\15\1\11\3\15\1\uffff\25\15\1"+
            "\16\76\15\1\16\1\15\1\16\ud782\15\u0800\uffff\u1ffe\15",
            "",
            "",
            "",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "",
            "\1\uffff",
            "\1\uffff",
            "",
            "\1\uffff",
            "",
            ""
    };

    static final short[] DFA8_eot = DFA.unpackEncodedString(DFA8_eotS);
    static final short[] DFA8_eof = DFA.unpackEncodedString(DFA8_eofS);
    static final char[] DFA8_min = DFA.unpackEncodedStringToUnsignedChars(DFA8_minS);
    static final char[] DFA8_max = DFA.unpackEncodedStringToUnsignedChars(DFA8_maxS);
    static final short[] DFA8_accept = DFA.unpackEncodedString(DFA8_acceptS);
    static final short[] DFA8_special = DFA.unpackEncodedString(DFA8_specialS);
    static final short[][] DFA8_transition;

    static {
        int numStates = DFA8_transitionS.length;
        DFA8_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA8_transition[i] = DFA.unpackEncodedString(DFA8_transitionS[i]);
        }
    }

    class DFA8 extends DFA {

        public DFA8(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 8;
            this.eot = DFA8_eot;
            this.eof = DFA8_eof;
            this.min = DFA8_min;
            this.max = DFA8_max;
            this.accept = DFA8_accept;
            this.special = DFA8_special;
            this.transition = DFA8_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( QUOT | APOS | ESCAPE_QUOT | ESCAPE_APOS | L_PredefinedEntityRef | L_CharRef | L_QuotStringLiteralChar | L_AposStringLiteralChar | L_AnyChar );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA8_12 = input.LA(1);

                         
                        int index8_12 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (( inAposStr )) ) {s = 23;}

                        else if ( (( inQuotStr )) ) {s = 14;}

                        else if ( (( !inQuotStr && !inAposStr )) ) {s = 9;}

                         
                        input.seek(index8_12);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA8_7 = input.LA(1);

                         
                        int index8_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (( inQuotStr )) ) {s = 20;}

                        else if ( (( inAposStr )) ) {s = 10;}

                        else if ( (( !inQuotStr && !inAposStr )) ) {s = 9;}

                         
                        input.seek(index8_7);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA8_4 = input.LA(1);

                         
                        int index8_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ((LA8_4>='\t' && LA8_4<='\n')||LA8_4=='\r'||(LA8_4>=' ' && LA8_4<='!')||(LA8_4>='#' && LA8_4<='%')||(LA8_4>='(' && LA8_4<=';')||(LA8_4>='=' && LA8_4<='z')||LA8_4=='|'||(LA8_4>='~' && LA8_4<='\uD7FF')||(LA8_4>='\uE000' && LA8_4<='\uFFFD')) && ((( !inQuotStr && !inAposStr )||( inQuotStr )||( inAposStr )))) {s = 4;}

                        else if ( (LA8_4=='<'||LA8_4=='{'||LA8_4=='}') && ((( inQuotStr )||( inAposStr )))) {s = 5;}

                        else if ( (LA8_4=='\"') && ((( !inQuotStr && !inAposStr )||( inAposStr )))) {s = 8;}

                        else if ( (LA8_4=='\'') && ((( !inQuotStr && !inAposStr )||( inQuotStr )))) {s = 13;}

                        else s = 17;

                         
                        input.seek(index8_4);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA8_11 = input.LA(1);

                         
                        int index8_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ((LA8_11>='\t' && LA8_11<='\n')||LA8_11=='\r'||(LA8_11>=' ' && LA8_11<='!')||(LA8_11>='#' && LA8_11<='%')||(LA8_11>='\'' && LA8_11<=';')||(LA8_11>='=' && LA8_11<='z')||LA8_11=='|'||(LA8_11>='~' && LA8_11<='\uD7FF')||(LA8_11>='\uE000' && LA8_11<='\uFFFD')) && ((( !inQuotStr && !inAposStr )||( inQuotStr )))) {s = 13;}

                        else if ( (LA8_11=='<'||LA8_11=='{'||LA8_11=='}') && (( inQuotStr ))) {s = 14;}

                        else if ( (LA8_11=='\"') && (( !inQuotStr && !inAposStr ))) {s = 9;}

                        else s = 22;

                         
                        input.seek(index8_11);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA8_1 = input.LA(1);

                         
                        int index8_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA8_1=='\"') && ((( !inQuotStr && !inAposStr )||( inQuotStr )||( inAposStr )))) {s = 6;}

                        else if ( ((LA8_1>='\t' && LA8_1<='\n')||LA8_1=='\r'||(LA8_1>=' ' && LA8_1<='!')||(LA8_1>='#' && LA8_1<='%')||(LA8_1>='(' && LA8_1<=';')||(LA8_1>='=' && LA8_1<='z')||LA8_1=='|'||(LA8_1>='~' && LA8_1<='\uD7FF')||(LA8_1>='\uE000' && LA8_1<='\uFFFD')) && ((( !inQuotStr && !inAposStr )||( inAposStr )))) {s = 8;}

                        else if ( (LA8_1=='\'') && (( !inQuotStr && !inAposStr ))) {s = 9;}

                        else if ( (LA8_1=='<'||LA8_1=='{'||LA8_1=='}') && (( inAposStr ))) {s = 10;}

                        else s = 7;

                         
                        input.seek(index8_1);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA8_21 = input.LA(1);

                         
                        int index8_21 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (( inAposStr )) ) {s = 10;}

                        else if ( (( !inQuotStr && !inAposStr )) ) {s = 9;}

                         
                        input.seek(index8_21);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA8_6 = input.LA(1);

                         
                        int index8_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ((LA8_6>='\t' && LA8_6<='\n')||LA8_6=='\r'||(LA8_6>=' ' && LA8_6<='%')||(LA8_6>='(' && LA8_6<=';')||(LA8_6>='=' && LA8_6<='z')||LA8_6=='|'||(LA8_6>='~' && LA8_6<='\uD7FF')||(LA8_6>='\uE000' && LA8_6<='\uFFFD')) && ((( !inQuotStr && !inAposStr )||( inAposStr )))) {s = 8;}

                        else if ( (LA8_6=='<'||LA8_6=='{'||LA8_6=='}') && (( inAposStr ))) {s = 10;}

                        else if ( (LA8_6=='\'') && (( !inQuotStr && !inAposStr ))) {s = 9;}

                        else s = 19;

                         
                        input.seek(index8_6);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA8_18 = input.LA(1);

                         
                        int index8_18 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (( inQuotStr )) ) {s = 14;}

                        else if ( (( inAposStr )) ) {s = 10;}

                         
                        input.seek(index8_18);
                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA8_19 = input.LA(1);

                         
                        int index8_19 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (( inQuotStr )) ) {s = 25;}

                        else if ( (( inAposStr )) ) {s = 10;}

                        else if ( (( !inQuotStr && !inAposStr )) ) {s = 9;}

                         
                        input.seek(index8_19);
                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA8_22 = input.LA(1);

                         
                        int index8_22 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (( inAposStr )) ) {s = 26;}

                        else if ( (( inQuotStr )) ) {s = 14;}

                        else if ( (( !inQuotStr && !inAposStr )) ) {s = 9;}

                         
                        input.seek(index8_22);
                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA8_2 = input.LA(1);

                         
                        int index8_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA8_2=='\'') && ((( !inQuotStr && !inAposStr )||( inQuotStr )||( inAposStr )))) {s = 11;}

                        else if ( ((LA8_2>='\t' && LA8_2<='\n')||LA8_2=='\r'||(LA8_2>=' ' && LA8_2<='!')||(LA8_2>='#' && LA8_2<='%')||(LA8_2>='(' && LA8_2<=';')||(LA8_2>='=' && LA8_2<='z')||LA8_2=='|'||(LA8_2>='~' && LA8_2<='\uD7FF')||(LA8_2>='\uE000' && LA8_2<='\uFFFD')) && ((( !inQuotStr && !inAposStr )||( inQuotStr )))) {s = 13;}

                        else if ( (LA8_2=='<'||LA8_2=='{'||LA8_2=='}') && (( inQuotStr ))) {s = 14;}

                        else if ( (LA8_2=='\"') && (( !inQuotStr && !inAposStr ))) {s = 9;}

                        else s = 12;

                         
                        input.seek(index8_2);
                        if ( s>=0 ) return s;
                        break;
                    case 11 : 
                        int LA8_13 = input.LA(1);

                         
                        int index8_13 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ((LA8_13>='\t' && LA8_13<='\n')||LA8_13=='\r'||(LA8_13>=' ' && LA8_13<='!')||(LA8_13>='#' && LA8_13<='%')||(LA8_13>='\'' && LA8_13<=';')||(LA8_13>='=' && LA8_13<='z')||LA8_13=='|'||(LA8_13>='~' && LA8_13<='\uD7FF')||(LA8_13>='\uE000' && LA8_13<='\uFFFD')) && ((( !inQuotStr && !inAposStr )||( inQuotStr )))) {s = 13;}

                        else if ( (LA8_13=='<'||LA8_13=='{'||LA8_13=='}') && (( inQuotStr ))) {s = 14;}

                        else if ( (LA8_13=='\"') && (( !inQuotStr && !inAposStr ))) {s = 9;}

                        else s = 24;

                         
                        input.seek(index8_13);
                        if ( s>=0 ) return s;
                        break;
                    case 12 : 
                        int LA8_5 = input.LA(1);

                         
                        int index8_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ((LA8_5>='\t' && LA8_5<='\n')||LA8_5=='\r'||(LA8_5>=' ' && LA8_5<='!')||(LA8_5>='#' && LA8_5<='%')||(LA8_5>='(' && LA8_5<='\uD7FF')||(LA8_5>='\uE000' && LA8_5<='\uFFFD')) && ((( inQuotStr )||( inAposStr )))) {s = 5;}

                        else if ( (LA8_5=='\'') && (( inQuotStr ))) {s = 14;}

                        else if ( (LA8_5=='\"') && (( inAposStr ))) {s = 10;}

                        else s = 18;

                         
                        input.seek(index8_5);
                        if ( s>=0 ) return s;
                        break;
                    case 13 : 
                        int LA8_17 = input.LA(1);

                         
                        int index8_17 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (( inQuotStr )) ) {s = 14;}

                        else if ( (( inAposStr )) ) {s = 10;}

                        else if ( (( !inQuotStr && !inAposStr )) ) {s = 9;}

                         
                        input.seek(index8_17);
                        if ( s>=0 ) return s;
                        break;
                    case 14 : 
                        int LA8_0 = input.LA(1);

                         
                        int index8_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA8_0=='\"') && ((( !inQuotStr && !inAposStr )||( inQuotStr )||( inAposStr )))) {s = 1;}

                        else if ( (LA8_0=='\'') && ((( !inQuotStr && !inAposStr )||( inQuotStr )||( inAposStr )))) {s = 2;}

                        else if ( (LA8_0=='&') ) {s = 3;}

                        else if ( ((LA8_0>='\t' && LA8_0<='\n')||LA8_0=='\r'||(LA8_0>=' ' && LA8_0<='!')||(LA8_0>='#' && LA8_0<='%')||(LA8_0>='(' && LA8_0<=';')||(LA8_0>='=' && LA8_0<='z')||LA8_0=='|'||(LA8_0>='~' && LA8_0<='\uD7FF')||(LA8_0>='\uE000' && LA8_0<='\uFFFD')) && ((( !inQuotStr && !inAposStr )||( inQuotStr )||( inAposStr )))) {s = 4;}

                        else if ( (LA8_0=='<'||LA8_0=='{'||LA8_0=='}') && ((( inQuotStr )||( inAposStr )))) {s = 5;}

                         
                        input.seek(index8_0);
                        if ( s>=0 ) return s;
                        break;
                    case 15 : 
                        int LA8_24 = input.LA(1);

                         
                        int index8_24 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (( inQuotStr )) ) {s = 14;}

                        else if ( (( !inQuotStr && !inAposStr )) ) {s = 9;}

                         
                        input.seek(index8_24);
                        if ( s>=0 ) return s;
                        break;
                    case 16 : 
                        int LA8_8 = input.LA(1);

                         
                        int index8_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ((LA8_8>='\t' && LA8_8<='\n')||LA8_8=='\r'||(LA8_8>=' ' && LA8_8<='%')||(LA8_8>='(' && LA8_8<=';')||(LA8_8>='=' && LA8_8<='z')||LA8_8=='|'||(LA8_8>='~' && LA8_8<='\uD7FF')||(LA8_8>='\uE000' && LA8_8<='\uFFFD')) && ((( !inQuotStr && !inAposStr )||( inAposStr )))) {s = 8;}

                        else if ( (LA8_8=='<'||LA8_8=='{'||LA8_8=='}') && (( inAposStr ))) {s = 10;}

                        else if ( (LA8_8=='\'') && (( !inQuotStr && !inAposStr ))) {s = 9;}

                        else s = 21;

                         
                        input.seek(index8_8);
                        if ( s>=0 ) return s;
                        break;
                    case 17 : 
                        int LA8_3 = input.LA(1);

                         
                        int index8_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA8_3=='#') ) {s = 15;}

                        else if ( (LA8_3=='a'||LA8_3=='g'||LA8_3=='l'||LA8_3=='q') && (( inQuotStr | inAposStr ))) {s = 16;}

                         
                        input.seek(index8_3);
                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 8, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

}