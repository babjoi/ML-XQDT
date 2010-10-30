// $ANTLR 3.1.2 XMLLexer.g 2010-10-30 22:09:17

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

@SuppressWarnings("unused")
public class XMLLexer extends XQDTLexer {
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
    public static final int NCNameUnprotected=256;
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
    public static final int GE=55;
    public static final int ATTR_SIGN=234;
    public static final int ELSE=43;
    public static final int SU=247;
    public static final int PRAGMA_START=228;
    public static final int EVAL=168;
    public static final int L_ElementContentChar=6;
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
    public static final int EMPTY_SEQUENCE=45;
    public static final int EXTERNAL=50;
    public static final int Digit=244;
    public static final int LAST=150;
    public static final int COLLECTION=173;
    public static final int DOT_DOT=221;
    public static final int AUTOMATICALLY=171;
    public static final int DECIMAL_SEPARATOR=116;
    public static final int CAST=27;
    public static final int L_CharRef=9;
    public static final int LBRACKET=199;
    public static final int MOD=72;
    public static final int EXCEPT=49;
    public static final int QUESTION=208;
    public static final int OR=81;
    public static final int AFTER=143;
    public static final int BLOCK=160;
    public static final int SMALLEREQ=214;
    public static final int S=246;
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
    public static final int REVALIDATION=155;
    public static final int INSERT=148;
    public static final int QUOT=238;
    public static final int WHERE=110;
    public static final int COPY=145;
    public static final int PREVIOUS=135;
    public static final int AMP_ER=193;
    public static final int USING=169;
    public static final int SCHEMA_ATTRIBUTE=93;
    public static final int EVERY=48;
    public static final int CONTEXT=113;
    public static final int XQUERY=111;
    public static final int INTEGRITY=181;
    public static final int SLIDING=136;
    public static final int UPDATING=157;
    public static final int IDIV=58;
    public static final int PRAGMA_END=229;
    public static final int SATISFIES=91;
    public static final int VALUE=158;
    public static final int DESCENDANT=36;
    public static final int STRICT=98;
    public static final int NOTEQUAL=205;
    public static final int COLON_COLON=223;
    public static final int L_PredefinedEntityRef=8;
    public static final int L_IntegerLiteral=251;
    public static final int FOLLOWING_SIBLING=52;
    public static final int STABLE=97;
    public static final int CHARREF_DEC=235;
    public static final int DECIMAL_FORMAT=115;
    public static final int START=137;
    public static final int GROUP=120;
    public static final int VALIDATE=107;
    public static final int PRECEDING=86;
    public static final int ZERO_DIGIT=142;
    public static final int MINUS_SIGN=123;
    public static final int GREATER=213;
    public static final int VBAR=227;
    public static final int DIGIT=118;
    public static final int COPY_NAMESPACES=33;
    public static final int ORDERING=84;
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
    public static final int SEQUENTIAL=164;
    public static final int ANCESTOR=16;
    public static final int SMALLER=212;
    public static final int MANUALLY=184;
    public static final int NODES=152;
    public static final int VARIABLE=108;
    public static final int EOF=-1;
    public static final int MODIFY=151;
    public static final int XMLDigit=257;
    public static final int QUOT_ER=195;
    public static final int STAR=209;
    public static final int BIND=204;
    public static final int L_Comment=254;
    public static final int XML_COMMENT_END=231;

    // dummy list for warning elimination
    List<Stack<Object>> dummy = new ArrayList<Stack<Object>>();

    // when we start, the '<' has already been eaten by the other lexer
    boolean inElem = true;
    boolean inAposAttr = false;
    boolean inQuotAttr = false;

    public boolean isInElement()
    {
       return inElem;
    }

    public boolean isInAposAttribute()
    {
       return inAposAttr;
    }

    public boolean isInQuotAttr()
    {
       return inQuotAttr;
    }
        
    @Override
    public void addToStack(List<XQDTLexer> stack) {
    	if (!inAposAttr && !inQuotAttr)
    		inElem = false;
    	stack.add(this);
    } 

    private boolean log() {
    	System.out.println("inApos:\t" + inAposAttr);
    	System.out.println("inQuot:\t" + inQuotAttr);
    	System.out.println("inElem:\t" + inElem);
    	System.out.println("---------------------");
    	return false;
    };


    // delegates
    // delegators

    public XMLLexer() {;} 
    public XMLLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public XMLLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "XMLLexer.g"; }

    // $ANTLR start "QUOT"
    public final void mQUOT() throws RecognitionException {
        try {
            int _type = QUOT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XMLLexer.g:77:6: ({...}? => '\"' )
            // XMLLexer.g:77:8: {...}? => '\"'
            {
            if ( !(( inElem || inQuotAttr )) ) {
                throw new FailedPredicateException(input, "QUOT", " inElem || inQuotAttr ");
            }
            match('\"'); 
             if (!inAposAttr) inQuotAttr = (!inQuotAttr); 

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
            // XMLLexer.g:78:6: ({...}? => '\\'' )
            // XMLLexer.g:78:8: {...}? => '\\''
            {
            if ( !(( inElem || inAposAttr )) ) {
                throw new FailedPredicateException(input, "APOS", " inElem || inAposAttr ");
            }
            match('\''); 
             if (!inQuotAttr) inAposAttr = !inAposAttr; 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "APOS"

    // $ANTLR start "L_QuotAttrContentChar"
    public final void mL_QuotAttrContentChar() throws RecognitionException {
        try {
            int _type = L_QuotAttrContentChar;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XMLLexer.g:81:2: ({...}? => ( '\\u0009' | '\\u000A' | '\\u000D' | '\\u0020' | '\\u0021' | '\\u0023' .. '\\u0025' | '\\u0028' .. '\\u003B' | '\\u003D' .. '\\u007A' | '\\u007C' .. '\\u007C' | '\\u007E' .. '\\uD7FF' | '\\uE000' .. '\\uFFFD' )+ )
            // XMLLexer.g:81:4: {...}? => ( '\\u0009' | '\\u000A' | '\\u000D' | '\\u0020' | '\\u0021' | '\\u0023' .. '\\u0025' | '\\u0028' .. '\\u003B' | '\\u003D' .. '\\u007A' | '\\u007C' .. '\\u007C' | '\\u007E' .. '\\uD7FF' | '\\uE000' .. '\\uFFFD' )+
            {
            if ( !(( inQuotAttr )) ) {
                throw new FailedPredicateException(input, "L_QuotAttrContentChar", " inQuotAttr ");
            }
            // XMLLexer.g:82:3: ( '\\u0009' | '\\u000A' | '\\u000D' | '\\u0020' | '\\u0021' | '\\u0023' .. '\\u0025' | '\\u0028' .. '\\u003B' | '\\u003D' .. '\\u007A' | '\\u007C' .. '\\u007C' | '\\u007E' .. '\\uD7FF' | '\\uE000' .. '\\uFFFD' )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>='\t' && LA1_0<='\n')||LA1_0=='\r'||(LA1_0>=' ' && LA1_0<='!')||(LA1_0>='#' && LA1_0<='%')||(LA1_0>='(' && LA1_0<=';')||(LA1_0>='=' && LA1_0<='z')||LA1_0=='|'||(LA1_0>='~' && LA1_0<='\uD7FF')||(LA1_0>='\uE000' && LA1_0<='\uFFFD')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // XMLLexer.g:
            	    {
            	    if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||input.LA(1)=='\r'||(input.LA(1)>=' ' && input.LA(1)<='!')||(input.LA(1)>='#' && input.LA(1)<='%')||(input.LA(1)>='(' && input.LA(1)<=';')||(input.LA(1)>='=' && input.LA(1)<='z')||input.LA(1)=='|'||(input.LA(1)>='~' && input.LA(1)<='\uD7FF')||(input.LA(1)>='\uE000' && input.LA(1)<='\uFFFD') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    if ( cnt1 >= 1 ) break loop1;
                        EarlyExitException eee =
                            new EarlyExitException(1, input);
                        throw eee;
                }
                cnt1++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "L_QuotAttrContentChar"

    // $ANTLR start "L_AposAttrContentChar"
    public final void mL_AposAttrContentChar() throws RecognitionException {
        try {
            int _type = L_AposAttrContentChar;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XMLLexer.g:88:2: ({...}? => ( '\\u0009' | '\\u000A' | '\\u000D' | '\\u0020' | '\\u0021' | '\\u0023' .. '\\u0025' | '\\u0028' .. '\\u003B' | '\\u003D' .. '\\u007A' | '\\u007C' .. '\\u007C' | '\\u007E' .. '\\uD7FF' | '\\uE000' .. '\\uFFFD' )+ )
            // XMLLexer.g:88:4: {...}? => ( '\\u0009' | '\\u000A' | '\\u000D' | '\\u0020' | '\\u0021' | '\\u0023' .. '\\u0025' | '\\u0028' .. '\\u003B' | '\\u003D' .. '\\u007A' | '\\u007C' .. '\\u007C' | '\\u007E' .. '\\uD7FF' | '\\uE000' .. '\\uFFFD' )+
            {
            if ( !(( inAposAttr )) ) {
                throw new FailedPredicateException(input, "L_AposAttrContentChar", " inAposAttr ");
            }
            // XMLLexer.g:89:3: ( '\\u0009' | '\\u000A' | '\\u000D' | '\\u0020' | '\\u0021' | '\\u0023' .. '\\u0025' | '\\u0028' .. '\\u003B' | '\\u003D' .. '\\u007A' | '\\u007C' .. '\\u007C' | '\\u007E' .. '\\uD7FF' | '\\uE000' .. '\\uFFFD' )+
            int cnt2=0;
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>='\t' && LA2_0<='\n')||LA2_0=='\r'||(LA2_0>=' ' && LA2_0<='!')||(LA2_0>='#' && LA2_0<='%')||(LA2_0>='(' && LA2_0<=';')||(LA2_0>='=' && LA2_0<='z')||LA2_0=='|'||(LA2_0>='~' && LA2_0<='\uD7FF')||(LA2_0>='\uE000' && LA2_0<='\uFFFD')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // XMLLexer.g:
            	    {
            	    if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||input.LA(1)=='\r'||(input.LA(1)>=' ' && input.LA(1)<='!')||(input.LA(1)>='#' && input.LA(1)<='%')||(input.LA(1)>='(' && input.LA(1)<=';')||(input.LA(1)>='=' && input.LA(1)<='z')||input.LA(1)=='|'||(input.LA(1)>='~' && input.LA(1)<='\uD7FF')||(input.LA(1)>='\uE000' && input.LA(1)<='\uFFFD') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


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

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "L_AposAttrContentChar"

    // $ANTLR start "L_ElementContentChar"
    public final void mL_ElementContentChar() throws RecognitionException {
        try {
            int _type = L_ElementContentChar;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XMLLexer.g:96:2: ({...}? => ( '\\u0009' | '\\u000A' | '\\u000D' | '\\u0020' .. '\\u0025' | '\\u0027' .. '\\u003B' | '\\u003D' .. '\\u007A' | '\\u007C' | '\\u007E' .. '\\uD7FF' | '\\uE000' .. '\\uFFFD' )+ )
            // XMLLexer.g:96:4: {...}? => ( '\\u0009' | '\\u000A' | '\\u000D' | '\\u0020' .. '\\u0025' | '\\u0027' .. '\\u003B' | '\\u003D' .. '\\u007A' | '\\u007C' | '\\u007E' .. '\\uD7FF' | '\\uE000' .. '\\uFFFD' )+
            {
            if ( !(( !inElem )) ) {
                throw new FailedPredicateException(input, "L_ElementContentChar", " !inElem ");
            }
            // XMLLexer.g:97:3: ( '\\u0009' | '\\u000A' | '\\u000D' | '\\u0020' .. '\\u0025' | '\\u0027' .. '\\u003B' | '\\u003D' .. '\\u007A' | '\\u007C' | '\\u007E' .. '\\uD7FF' | '\\uE000' .. '\\uFFFD' )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0>='\t' && LA3_0<='\n')||LA3_0=='\r'||(LA3_0>=' ' && LA3_0<='%')||(LA3_0>='\'' && LA3_0<=';')||(LA3_0>='=' && LA3_0<='z')||LA3_0=='|'||(LA3_0>='~' && LA3_0<='\uD7FF')||(LA3_0>='\uE000' && LA3_0<='\uFFFD')) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // XMLLexer.g:
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
            	    if ( cnt3 >= 1 ) break loop3;
                        EarlyExitException eee =
                            new EarlyExitException(3, input);
                        throw eee;
                }
                cnt3++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "L_ElementContentChar"

    // $ANTLR start "GREATER"
    public final void mGREATER() throws RecognitionException {
        try {
            int _type = GREATER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XMLLexer.g:103:2: ({...}? => '>' )
            // XMLLexer.g:103:4: {...}? => '>'
            {
            if ( !(( inElem )) ) {
                throw new FailedPredicateException(input, "GREATER", " inElem ");
            }
            match('>'); 
             inElem = false; 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "GREATER"

    // $ANTLR start "EMPTY_CLOSE_TAG"
    public final void mEMPTY_CLOSE_TAG() throws RecognitionException {
        try {
            int _type = EMPTY_CLOSE_TAG;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XMLLexer.g:107:2: ({...}? => '/>' )
            // XMLLexer.g:107:4: {...}? => '/>'
            {
            if ( !(( inElem )) ) {
                throw new FailedPredicateException(input, "EMPTY_CLOSE_TAG", " inElem ");
            }
            match("/>"); 

             inElem = false; 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "EMPTY_CLOSE_TAG"

    // $ANTLR start "S"
    public final void mS() throws RecognitionException {
        try {
            int _type = S;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XMLLexer.g:111:2: ({...}? => ( ' ' | '\\t' | '\\r' | '\\n' )+ )
            // XMLLexer.g:111:4: {...}? => ( ' ' | '\\t' | '\\r' | '\\n' )+
            {
            if ( !(( inElem )) ) {
                throw new FailedPredicateException(input, "S", " inElem ");
            }
            // XMLLexer.g:111:19: ( ' ' | '\\t' | '\\r' | '\\n' )+
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
            	    // XMLLexer.g:
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

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "S"

    // $ANTLR start "L_NCName"
    public final void mL_NCName() throws RecognitionException {
        try {
            int _type = L_NCName;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XMLLexer.g:117:2: ({...}? => NCNameUnprotected )
            // XMLLexer.g:117:4: {...}? => NCNameUnprotected
            {
            if ( !(( inElem )) ) {
                throw new FailedPredicateException(input, "L_NCName", " inElem ");
            }
            mNCNameUnprotected(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "L_NCName"

    // $ANTLR start "NCNameUnprotected"
    public final void mNCNameUnprotected() throws RecognitionException {
        try {
            // XMLLexer.g:121:2: ( NCNameStartChar ( NCNameChar )* )
            // XMLLexer.g:121:4: NCNameStartChar ( NCNameChar )*
            {
            mNCNameStartChar(); 
            // XMLLexer.g:121:20: ( NCNameChar )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( ((LA5_0>='-' && LA5_0<='.')||(LA5_0>='0' && LA5_0<='9')||(LA5_0>='A' && LA5_0<='Z')||LA5_0=='_'||(LA5_0>='a' && LA5_0<='z')) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // XMLLexer.g:121:20: NCNameChar
            	    {
            	    mNCNameChar(); 

            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);


            }

        }
        finally {
        }
    }
    // $ANTLR end "NCNameUnprotected"

    // $ANTLR start "NCNameStartChar"
    public final void mNCNameStartChar() throws RecognitionException {
        try {
            // XMLLexer.g:125:2: ( Letter | '_' )
            // XMLLexer.g:
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
            // XMLLexer.g:129:2: ( Letter | XMLDigit | '.' | '-' | '_' )
            // XMLLexer.g:
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

    // $ANTLR start "Letter"
    public final void mLetter() throws RecognitionException {
        try {
            // XMLLexer.g:133:2: ( 'a' .. 'z' | 'A' .. 'Z' )
            // XMLLexer.g:
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

    // $ANTLR start "XMLDigit"
    public final void mXMLDigit() throws RecognitionException {
        try {
            // XMLLexer.g:137:2: ( '0' .. '9' )
            // XMLLexer.g:137:4: '0' .. '9'
            {
            matchRange('0','9'); 

            }

        }
        finally {
        }
    }
    // $ANTLR end "XMLDigit"

    // $ANTLR start "EQUAL"
    public final void mEQUAL() throws RecognitionException {
        try {
            int _type = EQUAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XMLLexer.g:152:7: ({...}? => '=' )
            // XMLLexer.g:152:9: {...}? => '='
            {
            if ( !(( inElem  )) ) {
                throw new FailedPredicateException(input, "EQUAL", " inElem  ");
            }
            match('='); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "EQUAL"

    // $ANTLR start "ESCAPE_APOS"
    public final void mESCAPE_APOS() throws RecognitionException {
        try {
            int _type = ESCAPE_APOS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XMLLexer.g:153:13: ({...}? => '\\'\\'' )
            // XMLLexer.g:153:15: {...}? => '\\'\\''
            {
            if ( !(( inAposAttr )) ) {
                throw new FailedPredicateException(input, "ESCAPE_APOS", " inAposAttr ");
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

    // $ANTLR start "ESCAPE_QUOT"
    public final void mESCAPE_QUOT() throws RecognitionException {
        try {
            int _type = ESCAPE_QUOT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XMLLexer.g:154:13: ({...}? => '\"\"' )
            // XMLLexer.g:154:15: {...}? => '\"\"'
            {
            if ( !(( inQuotAttr )) ) {
                throw new FailedPredicateException(input, "ESCAPE_QUOT", " inQuotAttr ");
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

    // $ANTLR start "ESCAPE_LBRACKET"
    public final void mESCAPE_LBRACKET() throws RecognitionException {
        try {
            int _type = ESCAPE_LBRACKET;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XMLLexer.g:157:2: ({...}? => '{{' )
            // XMLLexer.g:157:4: {...}? => '{{'
            {
            if ( !(( !inElem || inAposAttr || inQuotAttr )) ) {
                throw new FailedPredicateException(input, "ESCAPE_LBRACKET", " !inElem || inAposAttr || inQuotAttr ");
            }
            match("{{"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ESCAPE_LBRACKET"

    // $ANTLR start "ESCAPE_RBRACKET"
    public final void mESCAPE_RBRACKET() throws RecognitionException {
        try {
            int _type = ESCAPE_RBRACKET;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XMLLexer.g:161:2: ({...}? => '}}' )
            // XMLLexer.g:161:4: {...}? => '}}'
            {
            if ( !(( !inElem || inAposAttr || inQuotAttr )) ) {
                throw new FailedPredicateException(input, "ESCAPE_RBRACKET", " !inElem || inAposAttr || inQuotAttr ");
            }
            match("}}"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ESCAPE_RBRACKET"

    // $ANTLR start "LBRACKET"
    public final void mLBRACKET() throws RecognitionException {
        try {
            int _type = LBRACKET;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XMLLexer.g:164:10: ({...}? => '{' )
            // XMLLexer.g:164:12: {...}? => '{'
            {
            if ( !(( !inElem || inAposAttr || inQuotAttr )) ) {
                throw new FailedPredicateException(input, "LBRACKET", " !inElem || inAposAttr || inQuotAttr ");
            }
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
            // XMLLexer.g:165:10: ({...}? => '}' )
            // XMLLexer.g:165:12: {...}? => '}'
            {
            if ( !(( !inElem || inAposAttr || inQuotAttr )) ) {
                throw new FailedPredicateException(input, "RBRACKET", " !inElem || inAposAttr || inQuotAttr ");
            }
            match('}'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RBRACKET"

    // $ANTLR start "SMALLER"
    public final void mSMALLER() throws RecognitionException {
        try {
            int _type = SMALLER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XMLLexer.g:166:9: ( '<' )
            // XMLLexer.g:166:11: '<'
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

    // $ANTLR start "CLOSE_TAG"
    public final void mCLOSE_TAG() throws RecognitionException {
        try {
            int _type = CLOSE_TAG;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XMLLexer.g:167:11: ({...}? => '</' )
            // XMLLexer.g:167:13: {...}? => '</'
            {
            if ( !(( !inElem )) ) {
                throw new FailedPredicateException(input, "CLOSE_TAG", " !inElem ");
            }
            match("</"); 

             inElem = true; 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CLOSE_TAG"

    // $ANTLR start "CDATA_START"
    public final void mCDATA_START() throws RecognitionException {
        try {
            int _type = CDATA_START;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XMLLexer.g:169:13: ( '<![CDATA[' )
            // XMLLexer.g:169:15: '<![CDATA['
            {
            match("<![CDATA["); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CDATA_START"

    // $ANTLR start "CDATA_END"
    public final void mCDATA_END() throws RecognitionException {
        try {
            int _type = CDATA_END;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XMLLexer.g:170:12: ( ']]>' )
            // XMLLexer.g:170:14: ']]>'
            {
            match("]]>"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CDATA_END"

    // $ANTLR start "L_CDataSection"
    public final void mL_CDataSection() throws RecognitionException {
        try {
            int _type = L_CDataSection;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XMLLexer.g:174:3: ({...}? => CDATA_START ( options {greedy=false; } : ( . )* ) CDATA_END )
            // XMLLexer.g:174:5: {...}? => CDATA_START ( options {greedy=false; } : ( . )* ) CDATA_END
            {
            if ( !(( !inElem )) ) {
                throw new FailedPredicateException(input, "L_CDataSection", " !inElem ");
            }
            mCDATA_START(); 
            // XMLLexer.g:174:33: ( options {greedy=false; } : ( . )* )
            // XMLLexer.g:174:60: ( . )*
            {
            // XMLLexer.g:174:60: ( . )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==']') ) {
                    int LA6_1 = input.LA(2);

                    if ( (LA6_1==']') ) {
                        int LA6_3 = input.LA(3);

                        if ( (LA6_3=='>') ) {
                            alt6=2;
                        }
                        else if ( ((LA6_3>='\u0000' && LA6_3<='=')||(LA6_3>='?' && LA6_3<='\uFFFF')) ) {
                            alt6=1;
                        }


                    }
                    else if ( ((LA6_1>='\u0000' && LA6_1<='\\')||(LA6_1>='^' && LA6_1<='\uFFFF')) ) {
                        alt6=1;
                    }


                }
                else if ( ((LA6_0>='\u0000' && LA6_0<='\\')||(LA6_0>='^' && LA6_0<='\uFFFF')) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // XMLLexer.g:174:60: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);


            }

            mCDATA_END(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "L_CDataSection"

    // $ANTLR start "L_PredefinedEntityRef"
    public final void mL_PredefinedEntityRef() throws RecognitionException {
        try {
            int _type = L_PredefinedEntityRef;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XMLLexer.g:182:2: ({...}? => '&' ( 'lt' | 'gt' | 'apos' | 'quot' | 'amp' ) ';' )
            // XMLLexer.g:182:4: {...}? => '&' ( 'lt' | 'gt' | 'apos' | 'quot' | 'amp' ) ';'
            {
            if ( !(( !inElem || inAposAttr || inQuotAttr )) ) {
                throw new FailedPredicateException(input, "L_PredefinedEntityRef", " !inElem || inAposAttr || inQuotAttr ");
            }
            match('&'); 
            // XMLLexer.g:182:52: ( 'lt' | 'gt' | 'apos' | 'quot' | 'amp' )
            int alt7=5;
            switch ( input.LA(1) ) {
            case 'l':
                {
                alt7=1;
                }
                break;
            case 'g':
                {
                alt7=2;
                }
                break;
            case 'a':
                {
                int LA7_3 = input.LA(2);

                if ( (LA7_3=='p') ) {
                    alt7=3;
                }
                else if ( (LA7_3=='m') ) {
                    alt7=5;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 7, 3, input);

                    throw nvae;
                }
                }
                break;
            case 'q':
                {
                alt7=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }

            switch (alt7) {
                case 1 :
                    // XMLLexer.g:182:53: 'lt'
                    {
                    match("lt"); 


                    }
                    break;
                case 2 :
                    // XMLLexer.g:182:60: 'gt'
                    {
                    match("gt"); 


                    }
                    break;
                case 3 :
                    // XMLLexer.g:182:67: 'apos'
                    {
                    match("apos"); 


                    }
                    break;
                case 4 :
                    // XMLLexer.g:182:76: 'quot'
                    {
                    match("quot"); 


                    }
                    break;
                case 5 :
                    // XMLLexer.g:182:85: 'amp'
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
            // XMLLexer.g:187:2: ({...}? => '&#' ( '0' .. '9' )+ ';' | '&#x' ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' )+ ';' )
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0=='&') ) {
                int LA10_1 = input.LA(2);

                if ( (LA10_1=='#') ) {
                    int LA10_2 = input.LA(3);

                    if ( (LA10_2=='x') ) {
                        alt10=2;
                    }
                    else if ( ((LA10_2>='0' && LA10_2<='9')) && (( !inElem || inAposAttr || inQuotAttr ))) {
                        alt10=1;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 10, 2, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 10, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;
            }
            switch (alt10) {
                case 1 :
                    // XMLLexer.g:187:4: {...}? => '&#' ( '0' .. '9' )+ ';'
                    {
                    if ( !(( !inElem || inAposAttr || inQuotAttr )) ) {
                        throw new FailedPredicateException(input, "L_CharRef", " !inElem || inAposAttr || inQuotAttr ");
                    }
                    match("&#"); 

                    // XMLLexer.g:187:53: ( '0' .. '9' )+
                    int cnt8=0;
                    loop8:
                    do {
                        int alt8=2;
                        int LA8_0 = input.LA(1);

                        if ( ((LA8_0>='0' && LA8_0<='9')) ) {
                            alt8=1;
                        }


                        switch (alt8) {
                    	case 1 :
                    	    // XMLLexer.g:187:54: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt8 >= 1 ) break loop8;
                                EarlyExitException eee =
                                    new EarlyExitException(8, input);
                                throw eee;
                        }
                        cnt8++;
                    } while (true);

                    match(';'); 

                    }
                    break;
                case 2 :
                    // XMLLexer.g:187:71: '&#x' ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' )+ ';'
                    {
                    match("&#x"); 

                    // XMLLexer.g:187:77: ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' )+
                    int cnt9=0;
                    loop9:
                    do {
                        int alt9=2;
                        int LA9_0 = input.LA(1);

                        if ( ((LA9_0>='0' && LA9_0<='9')||(LA9_0>='A' && LA9_0<='F')||(LA9_0>='a' && LA9_0<='f')) ) {
                            alt9=1;
                        }


                        switch (alt9) {
                    	case 1 :
                    	    // XMLLexer.g:
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
                    	    if ( cnt9 >= 1 ) break loop9;
                                EarlyExitException eee =
                                    new EarlyExitException(9, input);
                                throw eee;
                        }
                        cnt9++;
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

    // $ANTLR start "L_DirCommentConstructor"
    public final void mL_DirCommentConstructor() throws RecognitionException {
        try {
            int _type = L_DirCommentConstructor;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XMLLexer.g:191:2: ({...}? => '<!--' ( options {greedy=false; } : ( . )* ) '-->' )
            // XMLLexer.g:191:4: {...}? => '<!--' ( options {greedy=false; } : ( . )* ) '-->'
            {
            if ( !(( !inElem )) ) {
                throw new FailedPredicateException(input, "L_DirCommentConstructor", " !inElem ");
            }
            match("<!--"); 

            // XMLLexer.g:191:27: ( options {greedy=false; } : ( . )* )
            // XMLLexer.g:191:54: ( . )*
            {
            // XMLLexer.g:191:54: ( . )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0=='-') ) {
                    int LA11_1 = input.LA(2);

                    if ( (LA11_1=='-') ) {
                        int LA11_3 = input.LA(3);

                        if ( (LA11_3=='>') ) {
                            alt11=2;
                        }
                        else if ( ((LA11_3>='\u0000' && LA11_3<='=')||(LA11_3>='?' && LA11_3<='\uFFFF')) ) {
                            alt11=1;
                        }


                    }
                    else if ( ((LA11_1>='\u0000' && LA11_1<=',')||(LA11_1>='.' && LA11_1<='\uFFFF')) ) {
                        alt11=1;
                    }


                }
                else if ( ((LA11_0>='\u0000' && LA11_0<=',')||(LA11_0>='.' && LA11_0<='\uFFFF')) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // XMLLexer.g:191:54: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop11;
                }
            } while (true);


            }

            match("-->"); 


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
            // XMLLexer.g:194:2: ({...}? => '<?' ( SU )? NCNameUnprotected ( SU ( options {greedy=false; } : ( . )* ) )? '?>' )
            // XMLLexer.g:194:4: {...}? => '<?' ( SU )? NCNameUnprotected ( SU ( options {greedy=false; } : ( . )* ) )? '?>'
            {
            if ( !(( !inElem )) ) {
                throw new FailedPredicateException(input, "L_DirPIConstructor", " !inElem ");
            }
            match("<?"); 

            // XMLLexer.g:195:8: ( SU )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( ((LA12_0>='\t' && LA12_0<='\n')||LA12_0=='\r'||LA12_0==' ') ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // XMLLexer.g:195:8: SU
                    {
                    mSU(); 

                    }
                    break;

            }

            mNCNameUnprotected(); 
            // XMLLexer.g:195:30: ( SU ( options {greedy=false; } : ( . )* ) )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( ((LA14_0>='\t' && LA14_0<='\n')||LA14_0=='\r'||LA14_0==' ') ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // XMLLexer.g:195:31: SU ( options {greedy=false; } : ( . )* )
                    {
                    mSU(); 
                    // XMLLexer.g:195:34: ( options {greedy=false; } : ( . )* )
                    // XMLLexer.g:195:61: ( . )*
                    {
                    // XMLLexer.g:195:61: ( . )*
                    loop13:
                    do {
                        int alt13=2;
                        int LA13_0 = input.LA(1);

                        if ( (LA13_0=='?') ) {
                            int LA13_1 = input.LA(2);

                            if ( (LA13_1=='>') ) {
                                alt13=2;
                            }
                            else if ( ((LA13_1>='\u0000' && LA13_1<='=')||(LA13_1>='?' && LA13_1<='\uFFFF')) ) {
                                alt13=1;
                            }


                        }
                        else if ( ((LA13_0>='\u0000' && LA13_0<='>')||(LA13_0>='@' && LA13_0<='\uFFFF')) ) {
                            alt13=1;
                        }


                        switch (alt13) {
                    	case 1 :
                    	    // XMLLexer.g:195:61: .
                    	    {
                    	    matchAny(); 

                    	    }
                    	    break;

                    	default :
                    	    break loop13;
                        }
                    } while (true);


                    }


                    }
                    break;

            }

            match("?>"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "L_DirPIConstructor"

    // $ANTLR start "SU"
    public final void mSU() throws RecognitionException {
        try {
            // XMLLexer.g:199:2: ( ( ' ' | '\\t' | '\\n' | '\\r' )+ )
            // XMLLexer.g:199:4: ( ' ' | '\\t' | '\\n' | '\\r' )+
            {
            // XMLLexer.g:199:4: ( ' ' | '\\t' | '\\n' | '\\r' )+
            int cnt15=0;
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( ((LA15_0>='\t' && LA15_0<='\n')||LA15_0=='\r'||LA15_0==' ') ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // XMLLexer.g:
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
            	    if ( cnt15 >= 1 ) break loop15;
                        EarlyExitException eee =
                            new EarlyExitException(15, input);
                        throw eee;
                }
                cnt15++;
            } while (true);


            }

        }
        finally {
        }
    }
    // $ANTLR end "SU"

    // $ANTLR start "COLON"
    public final void mCOLON() throws RecognitionException {
        try {
            int _type = COLON;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // XMLLexer.g:202:7: ( ':' )
            // XMLLexer.g:202:9: ':'
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

    public void mTokens() throws RecognitionException {
        // XMLLexer.g:1:8: ( QUOT | APOS | L_QuotAttrContentChar | L_AposAttrContentChar | L_ElementContentChar | GREATER | EMPTY_CLOSE_TAG | S | L_NCName | EQUAL | ESCAPE_APOS | ESCAPE_QUOT | ESCAPE_LBRACKET | ESCAPE_RBRACKET | LBRACKET | RBRACKET | SMALLER | CLOSE_TAG | CDATA_START | CDATA_END | L_CDataSection | L_PredefinedEntityRef | L_CharRef | L_DirCommentConstructor | L_DirPIConstructor | COLON )
        int alt16=26;
        alt16 = dfa16.predict(input);
        switch (alt16) {
            case 1 :
                // XMLLexer.g:1:10: QUOT
                {
                mQUOT(); 

                }
                break;
            case 2 :
                // XMLLexer.g:1:15: APOS
                {
                mAPOS(); 

                }
                break;
            case 3 :
                // XMLLexer.g:1:20: L_QuotAttrContentChar
                {
                mL_QuotAttrContentChar(); 

                }
                break;
            case 4 :
                // XMLLexer.g:1:42: L_AposAttrContentChar
                {
                mL_AposAttrContentChar(); 

                }
                break;
            case 5 :
                // XMLLexer.g:1:64: L_ElementContentChar
                {
                mL_ElementContentChar(); 

                }
                break;
            case 6 :
                // XMLLexer.g:1:85: GREATER
                {
                mGREATER(); 

                }
                break;
            case 7 :
                // XMLLexer.g:1:93: EMPTY_CLOSE_TAG
                {
                mEMPTY_CLOSE_TAG(); 

                }
                break;
            case 8 :
                // XMLLexer.g:1:109: S
                {
                mS(); 

                }
                break;
            case 9 :
                // XMLLexer.g:1:111: L_NCName
                {
                mL_NCName(); 

                }
                break;
            case 10 :
                // XMLLexer.g:1:120: EQUAL
                {
                mEQUAL(); 

                }
                break;
            case 11 :
                // XMLLexer.g:1:126: ESCAPE_APOS
                {
                mESCAPE_APOS(); 

                }
                break;
            case 12 :
                // XMLLexer.g:1:138: ESCAPE_QUOT
                {
                mESCAPE_QUOT(); 

                }
                break;
            case 13 :
                // XMLLexer.g:1:150: ESCAPE_LBRACKET
                {
                mESCAPE_LBRACKET(); 

                }
                break;
            case 14 :
                // XMLLexer.g:1:166: ESCAPE_RBRACKET
                {
                mESCAPE_RBRACKET(); 

                }
                break;
            case 15 :
                // XMLLexer.g:1:182: LBRACKET
                {
                mLBRACKET(); 

                }
                break;
            case 16 :
                // XMLLexer.g:1:191: RBRACKET
                {
                mRBRACKET(); 

                }
                break;
            case 17 :
                // XMLLexer.g:1:200: SMALLER
                {
                mSMALLER(); 

                }
                break;
            case 18 :
                // XMLLexer.g:1:208: CLOSE_TAG
                {
                mCLOSE_TAG(); 

                }
                break;
            case 19 :
                // XMLLexer.g:1:218: CDATA_START
                {
                mCDATA_START(); 

                }
                break;
            case 20 :
                // XMLLexer.g:1:230: CDATA_END
                {
                mCDATA_END(); 

                }
                break;
            case 21 :
                // XMLLexer.g:1:240: L_CDataSection
                {
                mL_CDataSection(); 

                }
                break;
            case 22 :
                // XMLLexer.g:1:255: L_PredefinedEntityRef
                {
                mL_PredefinedEntityRef(); 

                }
                break;
            case 23 :
                // XMLLexer.g:1:277: L_CharRef
                {
                mL_CharRef(); 

                }
                break;
            case 24 :
                // XMLLexer.g:1:287: L_DirCommentConstructor
                {
                mL_DirCommentConstructor(); 

                }
                break;
            case 25 :
                // XMLLexer.g:1:311: L_DirPIConstructor
                {
                mL_DirPIConstructor(); 

                }
                break;
            case 26 :
                // XMLLexer.g:1:330: COLON
                {
                mCOLON(); 

                }
                break;

        }

    }


    protected DFA16 dfa16 = new DFA16(this);
    static final String DFA16_eotS =
        "\1\uffff\1\20\1\23\1\24\1\26\1\27\1\30\1\32\1\26\1\35\1\37\1\43"+
        "\1\44\1\uffff\1\26\1\47\2\uffff\1\51\2\uffff\1\56\3\uffff\1\30\1"+
        "\uffff\1\26\26\uffff\1\71\15\uffff\1\101\2\uffff";
    static final String DFA16_eofS =
        "\103\uffff";
    static final String DFA16_minS =
        "\11\11\1\173\1\175\1\41\1\11\1\43\2\11\1\0\1\uffff\1\11\2\0\1\11"+
        "\3\0\1\11\1\0\1\11\5\uffff\1\55\2\uffff\1\0\2\uffff\1\0\1\uffff"+
        "\1\0\4\uffff\1\0\3\uffff\1\11\1\103\5\uffff\1\0\1\104\1\uffff\1"+
        "\101\1\124\1\101\1\133\1\0\2\uffff";
    static final String DFA16_maxS =
        "\11\ufffd\1\173\1\175\1\77\1\ufffd\1\161\2\ufffd\1\0\1\uffff\1"+
        "\ufffd\2\0\1\ufffd\3\0\1\ufffd\1\0\1\ufffd\5\uffff\1\133\2\uffff"+
        "\1\0\2\uffff\1\0\1\uffff\1\0\4\uffff\1\0\3\uffff\1\ufffd\1\103\5"+
        "\uffff\1\0\1\104\1\uffff\1\101\1\124\1\101\1\133\1\uffff\2\uffff";
    static final String DFA16_acceptS =
        "\21\uffff\1\5\12\uffff\1\15\1\17\1\16\1\20\1\22\1\uffff\1\31\1"+
        "\21\1\uffff\1\27\1\26\1\uffff\1\1\1\uffff\1\2\1\3\1\4\1\6\1\uffff"+
        "\1\10\1\11\1\12\2\uffff\1\30\1\32\1\14\1\13\1\7\2\uffff\1\24\5\uffff"+
        "\1\23\1\25";
    static final String DFA16_specialS =
        "\1\34\1\2\1\25\1\36\1\33\1\11\1\12\1\35\1\27\1\41\1\13\1\17\1\32"+
        "\1\1\1\30\1\5\1\7\1\uffff\1\16\1\21\1\15\1\14\1\31\1\37\1\3\1\4"+
        "\1\10\1\26\5\uffff\1\22\2\uffff\1\20\2\uffff\1\0\1\uffff\1\24\4"+
        "\uffff\1\42\3\uffff\1\23\6\uffff\1\40\6\uffff\1\6\2\uffff}>";
    static final String[] DFA16_transitionS = {
            "\2\5\2\uffff\1\5\22\uffff\1\5\1\16\1\1\3\16\1\15\1\2\7\16\1"+
            "\4\12\16\1\14\1\16\1\13\1\7\1\3\2\16\32\6\2\16\1\10\1\16\1\6"+
            "\1\16\32\6\1\11\1\16\1\12\ud782\16\u0800\uffff\u1ffe\16",
            "\2\21\2\uffff\1\21\22\uffff\2\21\1\17\3\21\1\uffff\25\21\1"+
            "\uffff\76\21\1\uffff\1\21\1\uffff\ud782\21\u0800\uffff\u1ffe"+
            "\21",
            "\2\21\2\uffff\1\21\22\uffff\6\21\1\uffff\1\22\24\21\1\uffff"+
            "\76\21\1\uffff\1\21\1\uffff\ud782\21\u0800\uffff\u1ffe\21",
            "\2\16\2\uffff\1\16\22\uffff\2\16\1\21\3\16\1\uffff\1\21\24"+
            "\16\1\uffff\76\16\1\uffff\1\16\1\uffff\ud782\16\u0800\uffff"+
            "\u1ffe\16",
            "\2\16\2\uffff\1\16\22\uffff\2\16\1\21\3\16\1\uffff\1\21\24"+
            "\16\1\uffff\1\16\1\25\74\16\1\uffff\1\16\1\uffff\ud782\16\u0800"+
            "\uffff\u1ffe\16",
            "\2\5\2\uffff\1\5\22\uffff\1\5\1\16\1\21\3\16\1\uffff\1\21"+
            "\24\16\1\uffff\76\16\1\uffff\1\16\1\uffff\ud782\16\u0800\uffff"+
            "\u1ffe\16",
            "\2\16\2\uffff\1\16\22\uffff\2\16\1\21\3\16\1\uffff\1\21\5"+
            "\16\2\31\1\16\12\31\2\16\1\uffff\4\16\32\31\4\16\1\31\1\16\32"+
            "\31\1\uffff\1\16\1\uffff\ud782\16\u0800\uffff\u1ffe\16",
            "\2\16\2\uffff\1\16\22\uffff\2\16\1\21\3\16\1\uffff\1\21\24"+
            "\16\1\uffff\76\16\1\uffff\1\16\1\uffff\ud782\16\u0800\uffff"+
            "\u1ffe\16",
            "\2\16\2\uffff\1\16\22\uffff\2\16\1\21\3\16\1\uffff\1\21\24"+
            "\16\1\uffff\40\16\1\33\35\16\1\uffff\1\16\1\uffff\ud782\16\u0800"+
            "\uffff\u1ffe\16",
            "\1\34",
            "\1\36",
            "\1\41\15\uffff\1\40\17\uffff\1\42",
            "\2\16\2\uffff\1\16\22\uffff\2\16\1\21\3\16\1\uffff\1\21\24"+
            "\16\1\uffff\76\16\1\uffff\1\16\1\uffff\ud782\16\u0800\uffff"+
            "\u1ffe\16",
            "\1\45\75\uffff\1\46\5\uffff\1\46\4\uffff\1\46\4\uffff\1\46",
            "\2\16\2\uffff\1\16\22\uffff\2\16\1\21\3\16\1\uffff\1\21\24"+
            "\16\1\uffff\76\16\1\uffff\1\16\1\uffff\ud782\16\u0800\uffff"+
            "\u1ffe\16",
            "\2\21\2\uffff\1\21\22\uffff\6\21\1\uffff\25\21\1\uffff\76"+
            "\21\1\uffff\1\21\1\uffff\ud782\21\u0800\uffff\u1ffe\21",
            "\1\uffff",
            "",
            "\2\21\2\uffff\1\21\22\uffff\6\21\1\uffff\25\21\1\uffff\76"+
            "\21\1\uffff\1\21\1\uffff\ud782\21\u0800\uffff\u1ffe\21",
            "\1\uffff",
            "\1\uffff",
            "\2\16\2\uffff\1\16\22\uffff\2\16\1\21\3\16\1\uffff\1\21\24"+
            "\16\1\uffff\76\16\1\uffff\1\16\1\uffff\ud782\16\u0800\uffff"+
            "\u1ffe\16",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\2\16\2\uffff\1\16\22\uffff\2\16\1\21\3\16\1\uffff\1\21\5"+
            "\16\2\31\1\16\12\31\2\16\1\uffff\4\16\32\31\4\16\1\31\1\16\32"+
            "\31\1\uffff\1\16\1\uffff\ud782\16\u0800\uffff\u1ffe\16",
            "\1\uffff",
            "\2\16\2\uffff\1\16\22\uffff\2\16\1\21\3\16\1\uffff\1\21\24"+
            "\16\1\uffff\1\16\1\62\74\16\1\uffff\1\16\1\uffff\ud782\16\u0800"+
            "\uffff\u1ffe\16",
            "",
            "",
            "",
            "",
            "",
            "\1\64\55\uffff\1\63",
            "",
            "",
            "\1\uffff",
            "",
            "",
            "\1\uffff",
            "",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "\1\uffff",
            "",
            "",
            "",
            "\2\16\2\uffff\1\16\22\uffff\2\16\1\21\3\16\1\uffff\1\21\24"+
            "\16\1\uffff\76\16\1\uffff\1\16\1\uffff\ud782\16\u0800\uffff"+
            "\u1ffe\16",
            "\1\72",
            "",
            "",
            "",
            "",
            "",
            "\1\uffff",
            "\1\74",
            "",
            "\1\75",
            "\1\76",
            "\1\77",
            "\1\100",
            "\0\102",
            "",
            ""
    };

    static final short[] DFA16_eot = DFA.unpackEncodedString(DFA16_eotS);
    static final short[] DFA16_eof = DFA.unpackEncodedString(DFA16_eofS);
    static final char[] DFA16_min = DFA.unpackEncodedStringToUnsignedChars(DFA16_minS);
    static final char[] DFA16_max = DFA.unpackEncodedStringToUnsignedChars(DFA16_maxS);
    static final short[] DFA16_accept = DFA.unpackEncodedString(DFA16_acceptS);
    static final short[] DFA16_special = DFA.unpackEncodedString(DFA16_specialS);
    static final short[][] DFA16_transition;

    static {
        int numStates = DFA16_transitionS.length;
        DFA16_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA16_transition[i] = DFA.unpackEncodedString(DFA16_transitionS[i]);
        }
    }

    class DFA16 extends DFA {

        public DFA16(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 16;
            this.eot = DFA16_eot;
            this.eof = DFA16_eof;
            this.min = DFA16_min;
            this.max = DFA16_max;
            this.accept = DFA16_accept;
            this.special = DFA16_special;
            this.transition = DFA16_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( QUOT | APOS | L_QuotAttrContentChar | L_AposAttrContentChar | L_ElementContentChar | GREATER | EMPTY_CLOSE_TAG | S | L_NCName | EQUAL | ESCAPE_APOS | ESCAPE_QUOT | ESCAPE_LBRACKET | ESCAPE_RBRACKET | LBRACKET | RBRACKET | SMALLER | CLOSE_TAG | CDATA_START | CDATA_END | L_CDataSection | L_PredefinedEntityRef | L_CharRef | L_DirCommentConstructor | L_DirPIConstructor | COLON );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA16_39 = input.LA(1);

                         
                        int index16_39 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (( !inElem )) ) {s = 17;}

                        else if ( (( inQuotAttr )) ) {s = 54;}

                         
                        input.seek(index16_39);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA16_13 = input.LA(1);

                         
                        int index16_13 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA16_13=='#') ) {s = 37;}

                        else if ( (LA16_13=='a'||LA16_13=='g'||LA16_13=='l'||LA16_13=='q') && (( !inElem || inAposAttr || inQuotAttr ))) {s = 38;}

                         
                        input.seek(index16_13);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA16_1 = input.LA(1);

                         
                        int index16_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA16_1=='\"') && ((( !inElem )||( inQuotAttr )))) {s = 15;}

                        else if ( ((LA16_1>='\t' && LA16_1<='\n')||LA16_1=='\r'||(LA16_1>=' ' && LA16_1<='!')||(LA16_1>='#' && LA16_1<='%')||(LA16_1>='\'' && LA16_1<=';')||(LA16_1>='=' && LA16_1<='z')||LA16_1=='|'||(LA16_1>='~' && LA16_1<='\uD7FF')||(LA16_1>='\uE000' && LA16_1<='\uFFFD')) && (( !inElem ))) {s = 17;}

                        else s = 16;

                         
                        input.seek(index16_1);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA16_24 = input.LA(1);

                         
                        int index16_24 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (( inQuotAttr )) ) {s = 43;}

                        else if ( (( inAposAttr )) ) {s = 44;}

                        else if ( (( !inElem )) ) {s = 17;}

                        else if ( (( inElem )) ) {s = 48;}

                         
                        input.seek(index16_24);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA16_25 = input.LA(1);

                         
                        int index16_25 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ((LA16_25>='-' && LA16_25<='.')||(LA16_25>='0' && LA16_25<='9')||(LA16_25>='A' && LA16_25<='Z')||LA16_25=='_'||(LA16_25>='a' && LA16_25<='z')) && ((( inElem )||( !inElem )||( inQuotAttr )||( inAposAttr )))) {s = 25;}

                        else if ( (LA16_25=='\"'||LA16_25=='\'') && (( !inElem ))) {s = 17;}

                        else if ( ((LA16_25>='\t' && LA16_25<='\n')||LA16_25=='\r'||(LA16_25>=' ' && LA16_25<='!')||(LA16_25>='#' && LA16_25<='%')||(LA16_25>='(' && LA16_25<=',')||LA16_25=='/'||(LA16_25>=':' && LA16_25<=';')||(LA16_25>='=' && LA16_25<='@')||(LA16_25>='[' && LA16_25<='^')||LA16_25=='`'||LA16_25=='|'||(LA16_25>='~' && LA16_25<='\uD7FF')||(LA16_25>='\uE000' && LA16_25<='\uFFFD')) && ((( !inElem )||( inQuotAttr )||( inAposAttr )))) {s = 14;}

                        else s = 24;

                         
                        input.seek(index16_25);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA16_15 = input.LA(1);

                         
                        int index16_15 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ((LA16_15>='\t' && LA16_15<='\n')||LA16_15=='\r'||(LA16_15>=' ' && LA16_15<='%')||(LA16_15>='\'' && LA16_15<=';')||(LA16_15>='=' && LA16_15<='z')||LA16_15=='|'||(LA16_15>='~' && LA16_15<='\uD7FF')||(LA16_15>='\uE000' && LA16_15<='\uFFFD')) && (( !inElem ))) {s = 17;}

                        else s = 39;

                         
                        input.seek(index16_15);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA16_64 = input.LA(1);

                         
                        int index16_64 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ((LA16_64>='\u0000' && LA16_64<='\uFFFF')) && (( !inElem ))) {s = 66;}

                        else s = 65;

                         
                        input.seek(index16_64);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA16_16 = input.LA(1);

                         
                        int index16_16 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (( inElem || inQuotAttr )) ) {s = 40;}

                        else if ( (( !inElem )) ) {s = 17;}

                         
                        input.seek(index16_16);
                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA16_26 = input.LA(1);

                         
                        int index16_26 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (( inQuotAttr )) ) {s = 43;}

                        else if ( (( inAposAttr )) ) {s = 44;}

                        else if ( (( !inElem )) ) {s = 17;}

                        else if ( (( inElem  )) ) {s = 49;}

                         
                        input.seek(index16_26);
                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA16_5 = input.LA(1);

                         
                        int index16_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ((LA16_5>='\t' && LA16_5<='\n')||LA16_5=='\r'||LA16_5==' ') && ((( inElem )||( !inElem )||( inQuotAttr )||( inAposAttr )))) {s = 5;}

                        else if ( (LA16_5=='!'||(LA16_5>='#' && LA16_5<='%')||(LA16_5>='(' && LA16_5<=';')||(LA16_5>='=' && LA16_5<='z')||LA16_5=='|'||(LA16_5>='~' && LA16_5<='\uD7FF')||(LA16_5>='\uE000' && LA16_5<='\uFFFD')) && ((( !inElem )||( inQuotAttr )||( inAposAttr )))) {s = 14;}

                        else if ( (LA16_5=='\"'||LA16_5=='\'') && (( !inElem ))) {s = 17;}

                        else s = 23;

                         
                        input.seek(index16_5);
                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA16_6 = input.LA(1);

                         
                        int index16_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ((LA16_6>='-' && LA16_6<='.')||(LA16_6>='0' && LA16_6<='9')||(LA16_6>='A' && LA16_6<='Z')||LA16_6=='_'||(LA16_6>='a' && LA16_6<='z')) && ((( inElem )||( !inElem )||( inQuotAttr )||( inAposAttr )))) {s = 25;}

                        else if ( ((LA16_6>='\t' && LA16_6<='\n')||LA16_6=='\r'||(LA16_6>=' ' && LA16_6<='!')||(LA16_6>='#' && LA16_6<='%')||(LA16_6>='(' && LA16_6<=',')||LA16_6=='/'||(LA16_6>=':' && LA16_6<=';')||(LA16_6>='=' && LA16_6<='@')||(LA16_6>='[' && LA16_6<='^')||LA16_6=='`'||LA16_6=='|'||(LA16_6>='~' && LA16_6<='\uD7FF')||(LA16_6>='\uE000' && LA16_6<='\uFFFD')) && ((( !inElem )||( inQuotAttr )||( inAposAttr )))) {s = 14;}

                        else if ( (LA16_6=='\"'||LA16_6=='\'') && (( !inElem ))) {s = 17;}

                        else s = 24;

                         
                        input.seek(index16_6);
                        if ( s>=0 ) return s;
                        break;
                    case 11 : 
                        int LA16_10 = input.LA(1);

                         
                        int index16_10 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA16_10=='}') && (( !inElem || inAposAttr || inQuotAttr ))) {s = 30;}

                        else s = 31;

                         
                        input.seek(index16_10);
                        if ( s>=0 ) return s;
                        break;
                    case 12 : 
                        int LA16_21 = input.LA(1);

                         
                        int index16_21 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ((LA16_21>='\t' && LA16_21<='\n')||LA16_21=='\r'||(LA16_21>=' ' && LA16_21<='!')||(LA16_21>='#' && LA16_21<='%')||(LA16_21>='(' && LA16_21<=';')||(LA16_21>='=' && LA16_21<='z')||LA16_21=='|'||(LA16_21>='~' && LA16_21<='\uD7FF')||(LA16_21>='\uE000' && LA16_21<='\uFFFD')) && ((( !inElem )||( inQuotAttr )||( inAposAttr )))) {s = 14;}

                        else if ( (LA16_21=='\"'||LA16_21=='\'') && (( !inElem ))) {s = 17;}

                        else s = 46;

                         
                        input.seek(index16_21);
                        if ( s>=0 ) return s;
                        break;
                    case 13 : 
                        int LA16_20 = input.LA(1);

                         
                        int index16_20 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (( inQuotAttr )) ) {s = 43;}

                        else if ( (( inAposAttr )) ) {s = 44;}

                        else if ( (( !inElem )) ) {s = 17;}

                        else if ( (( inElem )) ) {s = 45;}

                         
                        input.seek(index16_20);
                        if ( s>=0 ) return s;
                        break;
                    case 14 : 
                        int LA16_18 = input.LA(1);

                         
                        int index16_18 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ((LA16_18>='\t' && LA16_18<='\n')||LA16_18=='\r'||(LA16_18>=' ' && LA16_18<='%')||(LA16_18>='\'' && LA16_18<=';')||(LA16_18>='=' && LA16_18<='z')||LA16_18=='|'||(LA16_18>='~' && LA16_18<='\uD7FF')||(LA16_18>='\uE000' && LA16_18<='\uFFFD')) && (( !inElem ))) {s = 17;}

                        else s = 41;

                         
                        input.seek(index16_18);
                        if ( s>=0 ) return s;
                        break;
                    case 15 : 
                        int LA16_11 = input.LA(1);

                         
                        int index16_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA16_11=='/') && (( !inElem ))) {s = 32;}

                        else if ( (LA16_11=='!') ) {s = 33;}

                        else if ( (LA16_11=='?') && (( !inElem ))) {s = 34;}

                        else s = 35;

                         
                        input.seek(index16_11);
                        if ( s>=0 ) return s;
                        break;
                    case 16 : 
                        int LA16_36 = input.LA(1);

                         
                        int index16_36 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (( inQuotAttr )) ) {s = 43;}

                        else if ( (( inAposAttr )) ) {s = 44;}

                        else if ( (( !inElem )) ) {s = 17;}

                        else if ( (true) ) {s = 53;}

                         
                        input.seek(index16_36);
                        if ( s>=0 ) return s;
                        break;
                    case 17 : 
                        int LA16_19 = input.LA(1);

                         
                        int index16_19 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (( inElem || inAposAttr )) ) {s = 42;}

                        else if ( (( !inElem )) ) {s = 17;}

                         
                        input.seek(index16_19);
                        if ( s>=0 ) return s;
                        break;
                    case 18 : 
                        int LA16_33 = input.LA(1);

                         
                        int index16_33 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA16_33=='[') ) {s = 51;}

                        else if ( (LA16_33=='-') && (( !inElem ))) {s = 52;}

                         
                        input.seek(index16_33);
                        if ( s>=0 ) return s;
                        break;
                    case 19 : 
                        int LA16_50 = input.LA(1);

                         
                        int index16_50 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ((LA16_50>='\t' && LA16_50<='\n')||LA16_50=='\r'||(LA16_50>=' ' && LA16_50<='!')||(LA16_50>='#' && LA16_50<='%')||(LA16_50>='(' && LA16_50<=';')||(LA16_50>='=' && LA16_50<='z')||LA16_50=='|'||(LA16_50>='~' && LA16_50<='\uD7FF')||(LA16_50>='\uE000' && LA16_50<='\uFFFD')) && ((( !inElem )||( inQuotAttr )||( inAposAttr )))) {s = 14;}

                        else if ( (LA16_50=='\"'||LA16_50=='\'') && (( !inElem ))) {s = 17;}

                        else s = 57;

                         
                        input.seek(index16_50);
                        if ( s>=0 ) return s;
                        break;
                    case 20 : 
                        int LA16_41 = input.LA(1);

                         
                        int index16_41 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (( !inElem )) ) {s = 17;}

                        else if ( (( inAposAttr )) ) {s = 55;}

                         
                        input.seek(index16_41);
                        if ( s>=0 ) return s;
                        break;
                    case 21 : 
                        int LA16_2 = input.LA(1);

                         
                        int index16_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA16_2=='\'') && ((( !inElem )||( inAposAttr )))) {s = 18;}

                        else if ( ((LA16_2>='\t' && LA16_2<='\n')||LA16_2=='\r'||(LA16_2>=' ' && LA16_2<='%')||(LA16_2>='(' && LA16_2<=';')||(LA16_2>='=' && LA16_2<='z')||LA16_2=='|'||(LA16_2>='~' && LA16_2<='\uD7FF')||(LA16_2>='\uE000' && LA16_2<='\uFFFD')) && (( !inElem ))) {s = 17;}

                        else s = 19;

                         
                        input.seek(index16_2);
                        if ( s>=0 ) return s;
                        break;
                    case 22 : 
                        int LA16_27 = input.LA(1);

                         
                        int index16_27 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA16_27=='>') ) {s = 50;}

                        else if ( ((LA16_27>='\t' && LA16_27<='\n')||LA16_27=='\r'||(LA16_27>=' ' && LA16_27<='!')||(LA16_27>='#' && LA16_27<='%')||(LA16_27>='(' && LA16_27<=';')||LA16_27=='='||(LA16_27>='?' && LA16_27<='z')||LA16_27=='|'||(LA16_27>='~' && LA16_27<='\uD7FF')||(LA16_27>='\uE000' && LA16_27<='\uFFFD')) && ((( !inElem )||( inQuotAttr )||( inAposAttr )))) {s = 14;}

                        else if ( (LA16_27=='\"'||LA16_27=='\'') && (( !inElem ))) {s = 17;}

                        else s = 22;

                         
                        input.seek(index16_27);
                        if ( s>=0 ) return s;
                        break;
                    case 23 : 
                        int LA16_8 = input.LA(1);

                         
                        int index16_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA16_8==']') ) {s = 27;}

                        else if ( ((LA16_8>='\t' && LA16_8<='\n')||LA16_8=='\r'||(LA16_8>=' ' && LA16_8<='!')||(LA16_8>='#' && LA16_8<='%')||(LA16_8>='(' && LA16_8<=';')||(LA16_8>='=' && LA16_8<='\\')||(LA16_8>='^' && LA16_8<='z')||LA16_8=='|'||(LA16_8>='~' && LA16_8<='\uD7FF')||(LA16_8>='\uE000' && LA16_8<='\uFFFD')) && ((( !inElem )||( inQuotAttr )||( inAposAttr )))) {s = 14;}

                        else if ( (LA16_8=='\"'||LA16_8=='\'') && (( !inElem ))) {s = 17;}

                        else s = 22;

                         
                        input.seek(index16_8);
                        if ( s>=0 ) return s;
                        break;
                    case 24 : 
                        int LA16_14 = input.LA(1);

                         
                        int index16_14 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ((LA16_14>='\t' && LA16_14<='\n')||LA16_14=='\r'||(LA16_14>=' ' && LA16_14<='!')||(LA16_14>='#' && LA16_14<='%')||(LA16_14>='(' && LA16_14<=';')||(LA16_14>='=' && LA16_14<='z')||LA16_14=='|'||(LA16_14>='~' && LA16_14<='\uD7FF')||(LA16_14>='\uE000' && LA16_14<='\uFFFD')) && ((( !inElem )||( inQuotAttr )||( inAposAttr )))) {s = 14;}

                        else if ( (LA16_14=='\"'||LA16_14=='\'') && (( !inElem ))) {s = 17;}

                        else s = 22;

                         
                        input.seek(index16_14);
                        if ( s>=0 ) return s;
                        break;
                    case 25 : 
                        int LA16_22 = input.LA(1);

                         
                        int index16_22 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (( inQuotAttr )) ) {s = 43;}

                        else if ( (( inAposAttr )) ) {s = 44;}

                        else if ( (( !inElem )) ) {s = 17;}

                         
                        input.seek(index16_22);
                        if ( s>=0 ) return s;
                        break;
                    case 26 : 
                        int LA16_12 = input.LA(1);

                         
                        int index16_12 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ((LA16_12>='\t' && LA16_12<='\n')||LA16_12=='\r'||(LA16_12>=' ' && LA16_12<='!')||(LA16_12>='#' && LA16_12<='%')||(LA16_12>='(' && LA16_12<=';')||(LA16_12>='=' && LA16_12<='z')||LA16_12=='|'||(LA16_12>='~' && LA16_12<='\uD7FF')||(LA16_12>='\uE000' && LA16_12<='\uFFFD')) && ((( !inElem )||( inQuotAttr )||( inAposAttr )))) {s = 14;}

                        else if ( (LA16_12=='\"'||LA16_12=='\'') && (( !inElem ))) {s = 17;}

                        else s = 36;

                         
                        input.seek(index16_12);
                        if ( s>=0 ) return s;
                        break;
                    case 27 : 
                        int LA16_4 = input.LA(1);

                         
                        int index16_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA16_4=='>') && ((( inElem )||( !inElem )||( inQuotAttr )||( inAposAttr )))) {s = 21;}

                        else if ( ((LA16_4>='\t' && LA16_4<='\n')||LA16_4=='\r'||(LA16_4>=' ' && LA16_4<='!')||(LA16_4>='#' && LA16_4<='%')||(LA16_4>='(' && LA16_4<=';')||LA16_4=='='||(LA16_4>='?' && LA16_4<='z')||LA16_4=='|'||(LA16_4>='~' && LA16_4<='\uD7FF')||(LA16_4>='\uE000' && LA16_4<='\uFFFD')) && ((( !inElem )||( inQuotAttr )||( inAposAttr )))) {s = 14;}

                        else if ( (LA16_4=='\"'||LA16_4=='\'') && (( !inElem ))) {s = 17;}

                        else s = 22;

                         
                        input.seek(index16_4);
                        if ( s>=0 ) return s;
                        break;
                    case 28 : 
                        int LA16_0 = input.LA(1);

                         
                        int index16_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA16_0=='\"') && ((( !inElem )||( inQuotAttr )||( inElem || inQuotAttr )))) {s = 1;}

                        else if ( (LA16_0=='\'') && ((( !inElem )||( inElem || inAposAttr )||( inAposAttr )))) {s = 2;}

                        else if ( (LA16_0=='>') && ((( inElem )||( !inElem )||( inQuotAttr )||( inAposAttr )))) {s = 3;}

                        else if ( (LA16_0=='/') && ((( inElem )||( !inElem )||( inQuotAttr )||( inAposAttr )))) {s = 4;}

                        else if ( ((LA16_0>='\t' && LA16_0<='\n')||LA16_0=='\r'||LA16_0==' ') && ((( inElem )||( !inElem )||( inQuotAttr )||( inAposAttr )))) {s = 5;}

                        else if ( ((LA16_0>='A' && LA16_0<='Z')||LA16_0=='_'||(LA16_0>='a' && LA16_0<='z')) && ((( inElem )||( !inElem )||( inQuotAttr )||( inAposAttr )))) {s = 6;}

                        else if ( (LA16_0=='=') && ((( !inElem )||( inQuotAttr )||( inElem  )||( inAposAttr )))) {s = 7;}

                        else if ( (LA16_0==']') ) {s = 8;}

                        else if ( (LA16_0=='{') && (( !inElem || inAposAttr || inQuotAttr ))) {s = 9;}

                        else if ( (LA16_0=='}') && (( !inElem || inAposAttr || inQuotAttr ))) {s = 10;}

                        else if ( (LA16_0=='<') ) {s = 11;}

                        else if ( (LA16_0==':') ) {s = 12;}

                        else if ( (LA16_0=='&') ) {s = 13;}

                        else if ( (LA16_0=='!'||(LA16_0>='#' && LA16_0<='%')||(LA16_0>='(' && LA16_0<='.')||(LA16_0>='0' && LA16_0<='9')||LA16_0==';'||(LA16_0>='?' && LA16_0<='@')||(LA16_0>='[' && LA16_0<='\\')||LA16_0=='^'||LA16_0=='`'||LA16_0=='|'||(LA16_0>='~' && LA16_0<='\uD7FF')||(LA16_0>='\uE000' && LA16_0<='\uFFFD')) && ((( !inElem )||( inQuotAttr )||( inAposAttr )))) {s = 14;}

                         
                        input.seek(index16_0);
                        if ( s>=0 ) return s;
                        break;
                    case 29 : 
                        int LA16_7 = input.LA(1);

                         
                        int index16_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ((LA16_7>='\t' && LA16_7<='\n')||LA16_7=='\r'||(LA16_7>=' ' && LA16_7<='!')||(LA16_7>='#' && LA16_7<='%')||(LA16_7>='(' && LA16_7<=';')||(LA16_7>='=' && LA16_7<='z')||LA16_7=='|'||(LA16_7>='~' && LA16_7<='\uD7FF')||(LA16_7>='\uE000' && LA16_7<='\uFFFD')) && ((( !inElem )||( inQuotAttr )||( inAposAttr )))) {s = 14;}

                        else if ( (LA16_7=='\"'||LA16_7=='\'') && (( !inElem ))) {s = 17;}

                        else s = 26;

                         
                        input.seek(index16_7);
                        if ( s>=0 ) return s;
                        break;
                    case 30 : 
                        int LA16_3 = input.LA(1);

                         
                        int index16_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ((LA16_3>='\t' && LA16_3<='\n')||LA16_3=='\r'||(LA16_3>=' ' && LA16_3<='!')||(LA16_3>='#' && LA16_3<='%')||(LA16_3>='(' && LA16_3<=';')||(LA16_3>='=' && LA16_3<='z')||LA16_3=='|'||(LA16_3>='~' && LA16_3<='\uD7FF')||(LA16_3>='\uE000' && LA16_3<='\uFFFD')) && ((( !inElem )||( inQuotAttr )||( inAposAttr )))) {s = 14;}

                        else if ( (LA16_3=='\"'||LA16_3=='\'') && (( !inElem ))) {s = 17;}

                        else s = 20;

                         
                        input.seek(index16_3);
                        if ( s>=0 ) return s;
                        break;
                    case 31 : 
                        int LA16_23 = input.LA(1);

                         
                        int index16_23 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (( inQuotAttr )) ) {s = 43;}

                        else if ( (( inAposAttr )) ) {s = 44;}

                        else if ( (( !inElem )) ) {s = 17;}

                        else if ( (( inElem )) ) {s = 47;}

                         
                        input.seek(index16_23);
                        if ( s>=0 ) return s;
                        break;
                    case 32 : 
                        int LA16_57 = input.LA(1);

                         
                        int index16_57 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (( inQuotAttr )) ) {s = 43;}

                        else if ( (( inAposAttr )) ) {s = 44;}

                        else if ( (( !inElem )) ) {s = 17;}

                        else if ( (true) ) {s = 59;}

                         
                        input.seek(index16_57);
                        if ( s>=0 ) return s;
                        break;
                    case 33 : 
                        int LA16_9 = input.LA(1);

                         
                        int index16_9 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA16_9=='{') && (( !inElem || inAposAttr || inQuotAttr ))) {s = 28;}

                        else s = 29;

                         
                        input.seek(index16_9);
                        if ( s>=0 ) return s;
                        break;
                    case 34 : 
                        int LA16_46 = input.LA(1);

                         
                        int index16_46 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (( inQuotAttr )) ) {s = 43;}

                        else if ( (( inAposAttr )) ) {s = 44;}

                        else if ( (( !inElem )) ) {s = 17;}

                        else if ( (( inElem )) ) {s = 56;}

                         
                        input.seek(index16_46);
                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 16, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

}