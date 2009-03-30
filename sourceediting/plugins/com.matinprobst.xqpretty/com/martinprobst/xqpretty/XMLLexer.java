// $ANTLR 3.1b1 XMLLexer.g 2008-04-23 19:38:35

package com.martinprobst.xqpretty;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class XMLLexer extends Lexer {
    public static final int COMMA=42;
    public static final int ELEMENT=31;
    public static final int Lit_EQ=24;
    public static final int DOCUMENT=126;
    public static final int AS=55;
    public static final int CLOSE_ANGLE=5;
    public static final int ENCODING=21;
    public static final int TREAT=87;
    public static final int NAMESPACE=23;
    public static final int XQ_COMMENT=142;
    public static final int LEAST=40;
    public static final int THEN=74;
    public static final int T__160=160;
    public static final int INSTANCE=85;
    public static final int T__145=145;
    public static final int CDataSection=124;
    public static final int NCNameUnprotected=161;
    public static final int LAX=98;
    public static final int IMPORT=48;
    public static final int AposAttrContentChar=13;
    public static final int NCNameStartChar=137;
    public static final int ORDER=37;
    public static final int CASTABLE=88;
    public static final int Letter=139;
    public static final int BASE_URI=47;
    public static final int AND=77;
    public static final int TO=78;
    public static final int LCURLY=57;
    public static final int FUNCTION=32;
    public static final int EMPTY_CLOSE_TAG=7;
    public static final int TYPESWITCH=71;
    public static final int MODULE=22;
    public static final int LE=93;
    public static final int RBRACKET=117;
    public static final int RPAREN=54;
    public static final int VERSION=19;
    public static final int SCHEMA=49;
    public static final int CONSTRUCTION=53;
    public static final int ESCAPE_CURLY_OPEN=9;
    public static final int DECLARE=26;
    public static final int BOUNDARY_SPACE=27;
    public static final int CLOSE_TAG=4;
    public static final int INTERSECT=83;
    public static final int OPEN_ANGLE=6;
    public static final int AT=50;
    public static final int ElementContentChar=8;
    public static final int UNION=82;
    public static final int STRIP=29;
    public static final int T__150=150;
    public static final int GREATEST=39;
    public static final int T__156=156;
    public static final int QuotAttrContentChar=12;
    public static final int DESCENDING=67;
    public static final int Char=144;
    public static final int EQ=90;
    public static final int T__159=159;
    public static final int LT=92;
    public static final int DOCUMENT_NODE=133;
    public static final int OF=86;
    public static final int T__151=151;
    public static final int ANCESTOR_OR_SELF=114;
    public static final int T__158=158;
    public static final int FOLLOWING=109;
    public static final int GE=95;
    public static final int CASE=72;
    public static final int DESCENDANT_OR_SELF=107;
    public static final int T__157=157;
    public static final int ELSE=75;
    public static final int SEMICOLON=25;
    public static final int SELF=106;
    public static final int Pragma=100;
    public static final int SUnprotected=141;
    public static final int T__149=149;
    public static final int DecimalLiteral=119;
    public static final int TEXT=127;
    public static final int COLON=115;
    public static final int DIV=79;
    public static final int PARENT=110;
    public static final int NCName=136;
    public static final int QUOT=16;
    public static final int DirPIConstructor=122;
    public static final int WHERE=63;
    public static final int EMPTY_SEQUENCE=130;
    public static final int EXTERNAL=52;
    public static final int SCHEMA_ATTRIBUTE=134;
    public static final int EVERY=69;
    public static final int XQUERY=18;
    public static final int CAST=89;
    public static final int T__148=148;
    public static final int IDIV=80;
    public static final int SATISFIES=70;
    public static final int LBRACKET=116;
    public static final int MOD=81;
    public static final int EXCEPT=84;
    public static final int DESCENDANT=104;
    public static final int OR=76;
    public static final int STRICT=99;
    public static final int S=123;
    public static final int RCURLY=58;
    public static final int BY=64;
    public static final int FOLLOWING_SIBLING=108;
    public static final int STABLE=65;
    public static final int DoubleLiteral=120;
    public static final int SCHEMA_ELEMENT=135;
    public static final int VALIDATE=97;
    public static final int NO_INHERIT=45;
    public static final int PRECEDING=113;
    public static final int LPAREN=56;
    public static final int PRECEDING_SIBLING=112;
    public static final int COPY_NAMESPACES=41;
    public static final int ORDERING=34;
    public static final int NO_PRESERVE=43;
    public static final int UNORDERED=36;
    public static final int APOS=17;
    public static final int PredefinedEntityRef=11;
    public static final int EMPTY=38;
    public static final int INHERIT=44;
    public static final int T__153=153;
    public static final int ASCENDING=66;
    public static final int SLASH=101;
    public static final int ESCAPE_QUOT=15;
    public static final int IS=96;
    public static final int IntegerLiteral=118;
    public static final int NE=91;
    public static final int GT=94;
    public static final int COMMENT=128;
    public static final int StringLiteral=20;
    public static final int ESCAPE_APOS=14;
    public static final int DirCommentConstructor=121;
    public static final int ITEM=131;
    public static final int ORDERED=35;
    public static final int NCNameChar=138;
    public static final int T__152=152;
    public static final int IN=61;
    public static final int PROCESSING_INSTRUCTION=129;
    public static final int SOME=68;
    public static final int COLLATION=46;
    public static final int CharRef=125;
    public static final int SLASH_SLASH=102;
    public static final int T__147=147;
    public static final int ANCESTOR=111;
    public static final int RETURN=59;
    public static final int ESCAPE_CURLY_CLOSE=10;
    public static final int IF=73;
    public static final int LET=62;
    public static final int VARIABLE=51;
    public static final int EOF=-1;
    public static final int T__154=154;
    public static final int NODE=132;
    public static final int FOR=60;
    public static final int DEFAULT=30;
    public static final int PRESERVE=28;
    public static final int T__155=155;
    public static final int XMLDigit=140;
    public static final int ATTRIBUTE=105;
    public static final int CHILD=103;
    public static final int Digits=143;
    public static final int OPTION=33;
    public static final int T__146=146;

      // when we start, the '<' has already been eaten by the other lexer
      boolean inElem = true;
      boolean inAposAttr = false;
      boolean inQuotAttr = false;


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

    // $ANTLR start QuotAttrContentChar
    public final void mQuotAttrContentChar() throws RecognitionException {
        try {
            int _type = QuotAttrContentChar;
            // XMLLexer.g:18:2: ({...}? => ( '\\u0009' | '\\u000A' | '\\u000D' | '\\u0020' .. '\\u0021' | '\\u0023' .. '\\u0025' | '\\u0027' .. '\\u003B' | '\\u003D' .. '\\u007A' | '\\u007C' .. '\\u007C' | '\\u007E' .. '\\uD7FF' | '\\uE000' .. '\\uFFFD' )+ )
            // XMLLexer.g:18:4: {...}? => ( '\\u0009' | '\\u000A' | '\\u000D' | '\\u0020' .. '\\u0021' | '\\u0023' .. '\\u0025' | '\\u0027' .. '\\u003B' | '\\u003D' .. '\\u007A' | '\\u007C' .. '\\u007C' | '\\u007E' .. '\\uD7FF' | '\\uE000' .. '\\uFFFD' )+
            {
            if ( !( inQuotAttr ) ) {
                throw new FailedPredicateException(input, "QuotAttrContentChar", " inQuotAttr ");
            }
            // XMLLexer.g:19:3: ( '\\u0009' | '\\u000A' | '\\u000D' | '\\u0020' .. '\\u0021' | '\\u0023' .. '\\u0025' | '\\u0027' .. '\\u003B' | '\\u003D' .. '\\u007A' | '\\u007C' .. '\\u007C' | '\\u007E' .. '\\uD7FF' | '\\uE000' .. '\\uFFFD' )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>='\t' && LA1_0<='\n')||LA1_0=='\r'||(LA1_0>=' ' && LA1_0<='!')||(LA1_0>='#' && LA1_0<='%')||(LA1_0>='\'' && LA1_0<=';')||(LA1_0>='=' && LA1_0<='z')||LA1_0=='|'||(LA1_0>='~' && LA1_0<='\uD7FF')||(LA1_0>='\uE000' && LA1_0<='\uFFFD')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // XMLLexer.g:
            	    {
            	    if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||input.LA(1)=='\r'||(input.LA(1)>=' ' && input.LA(1)<='!')||(input.LA(1)>='#' && input.LA(1)<='%')||(input.LA(1)>='\'' && input.LA(1)<=';')||(input.LA(1)>='=' && input.LA(1)<='z')||input.LA(1)=='|'||(input.LA(1)>='~' && input.LA(1)<='\uD7FF')||(input.LA(1)>='\uE000' && input.LA(1)<='\uFFFD') ) {
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
        }
        finally {
        }
    }
    // $ANTLR end QuotAttrContentChar

    // $ANTLR start AposAttrContentChar
    public final void mAposAttrContentChar() throws RecognitionException {
        try {
            int _type = AposAttrContentChar;
            // XMLLexer.g:23:2: ({...}? => ( '\\u0009' | '\\u000A' | '\\u000D' | '\\u0020' .. '\\u0025' | '\\u0027' .. '\\u0026' | '\\u0028' .. '\\u003B' | '\\u003D' .. '\\u007A' | '\\u007C' .. '\\u007C' | '\\u007E' .. '\\uD7FF' | '\\uE000' .. '\\uFFFD' )+ )
            // XMLLexer.g:23:4: {...}? => ( '\\u0009' | '\\u000A' | '\\u000D' | '\\u0020' .. '\\u0025' | '\\u0027' .. '\\u0026' | '\\u0028' .. '\\u003B' | '\\u003D' .. '\\u007A' | '\\u007C' .. '\\u007C' | '\\u007E' .. '\\uD7FF' | '\\uE000' .. '\\uFFFD' )+
            {
            if ( !( inAposAttr ) ) {
                throw new FailedPredicateException(input, "AposAttrContentChar", " inAposAttr ");
            }
            // XMLLexer.g:24:3: ( '\\u0009' | '\\u000A' | '\\u000D' | '\\u0020' .. '\\u0025' | '\\u0027' .. '\\u0026' | '\\u0028' .. '\\u003B' | '\\u003D' .. '\\u007A' | '\\u007C' .. '\\u007C' | '\\u007E' .. '\\uD7FF' | '\\uE000' .. '\\uFFFD' )+
            int cnt2=0;
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>='\t' && LA2_0<='\n')||LA2_0=='\r'||(LA2_0>=' ' && LA2_0<='%')||(LA2_0>='(' && LA2_0<=';')||(LA2_0>='=' && LA2_0<='z')||LA2_0=='|'||(LA2_0>='~' && LA2_0<='\uD7FF')||(LA2_0>='\uE000' && LA2_0<='\uFFFD')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // XMLLexer.g:
            	    {
            	    if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||input.LA(1)=='\r'||(input.LA(1)>=' ' && input.LA(1)<='%')||(input.LA(1)>='(' && input.LA(1)<=';')||(input.LA(1)>='=' && input.LA(1)<='z')||input.LA(1)=='|'||(input.LA(1)>='~' && input.LA(1)<='\uD7FF')||(input.LA(1)>='\uE000' && input.LA(1)<='\uFFFD') ) {
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
        }
        finally {
        }
    }
    // $ANTLR end AposAttrContentChar

    // $ANTLR start CLOSE_ANGLE
    public final void mCLOSE_ANGLE() throws RecognitionException {
        try {
            int _type = CLOSE_ANGLE;
            // XMLLexer.g:28:2: ({...}? => '>' )
            // XMLLexer.g:28:4: {...}? => '>'
            {
            if ( !( inElem  ) ) {
                throw new FailedPredicateException(input, "CLOSE_ANGLE", " inElem  ");
            }
            match('>'); 
             inElem = false; 


            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end CLOSE_ANGLE

    // $ANTLR start EMPTY_CLOSE_TAG
    public final void mEMPTY_CLOSE_TAG() throws RecognitionException {
        try {
            int _type = EMPTY_CLOSE_TAG;
            // XMLLexer.g:30:2: ({...}? => '/>' )
            // XMLLexer.g:30:4: {...}? => '/>'
            {
            if ( !( inElem  ) ) {
                throw new FailedPredicateException(input, "EMPTY_CLOSE_TAG", " inElem  ");
            }
            match("/>"); 

             inElem = false; 


            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end EMPTY_CLOSE_TAG

    // $ANTLR start S
    public final void mS() throws RecognitionException {
        try {
            int _type = S;
            // XMLLexer.g:31:3: ({...}? => ( ' ' | '\\t' | '\\r' | '\\n' )+ )
            // XMLLexer.g:31:5: {...}? => ( ' ' | '\\t' | '\\r' | '\\n' )+
            {
            if ( !( inElem  ) ) {
                throw new FailedPredicateException(input, "S", " inElem  ");
            }
            // XMLLexer.g:31:21: ( ' ' | '\\t' | '\\r' | '\\n' )+
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
            	    if ( cnt3 >= 1 ) break loop3;
                        EarlyExitException eee =
                            new EarlyExitException(3, input);
                        throw eee;
                }
                cnt3++;
            } while (true);



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end S

    // $ANTLR start NCName
    public final void mNCName() throws RecognitionException {
        try {
            int _type = NCName;
            // XMLLexer.g:33:8: ({...}? => NCNameUnprotected )
            // XMLLexer.g:33:10: {...}? => NCNameUnprotected
            {
            if ( !( inElem  ) ) {
                throw new FailedPredicateException(input, "NCName", " inElem  ");
            }
            mNCNameUnprotected(); 


            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end NCName

    // $ANTLR start COLON
    public final void mCOLON() throws RecognitionException {
        try {
            int _type = COLON;
            // XMLLexer.g:34:7: ( ':' )
            // XMLLexer.g:34:9: ':'
            {
            match(':'); 


            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end COLON

    // $ANTLR start NCNameUnprotected
    public final void mNCNameUnprotected() throws RecognitionException {
        try {
            // XMLLexer.g:37:3: ( NCNameStartChar ( NCNameChar )* )
            // XMLLexer.g:37:5: NCNameStartChar ( NCNameChar )*
            {
            mNCNameStartChar(); 
            // XMLLexer.g:37:21: ( NCNameChar )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( ((LA4_0>='-' && LA4_0<='.')||(LA4_0>='0' && LA4_0<='9')||(LA4_0>='A' && LA4_0<='Z')||LA4_0=='_'||(LA4_0>='a' && LA4_0<='z')) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // XMLLexer.g:37:21: NCNameChar
            	    {
            	    mNCNameChar(); 


            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);



            }

        }
        finally {
        }
    }
    // $ANTLR end NCNameUnprotected

    // $ANTLR start NCNameStartChar
    public final void mNCNameStartChar() throws RecognitionException {
        try {
            // XMLLexer.g:39:3: ( Letter | '_' )
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
    // $ANTLR end NCNameStartChar

    // $ANTLR start NCNameChar
    public final void mNCNameChar() throws RecognitionException {
        try {
            // XMLLexer.g:41:3: ( Letter | XMLDigit | '.' | '-' | '_' )
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
    // $ANTLR end NCNameChar

    // $ANTLR start Letter
    public final void mLetter() throws RecognitionException {
        try {
            // XMLLexer.g:43:2: ( 'a' .. 'z' | 'A' .. 'Z' )
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
    // $ANTLR end Letter

    // $ANTLR start XMLDigit
    public final void mXMLDigit() throws RecognitionException {
        try {
            // XMLLexer.g:45:2: ( '0' .. '9' )
            // XMLLexer.g:45:4: '0' .. '9'
            {
            matchRange('0','9'); 


            }

        }
        finally {
        }
    }
    // $ANTLR end XMLDigit

    // $ANTLR start Lit_EQ
    public final void mLit_EQ() throws RecognitionException {
        try {
            int _type = Lit_EQ;
            // XMLLexer.g:57:8: ({...}? => '=' )
            // XMLLexer.g:57:10: {...}? => '='
            {
            if ( !( inElem  ) ) {
                throw new FailedPredicateException(input, "Lit_EQ", " inElem  ");
            }
            match('='); 


            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end Lit_EQ

    // $ANTLR start QUOT
    public final void mQUOT() throws RecognitionException {
        try {
            int _type = QUOT;
            // XMLLexer.g:58:6: ({...}? => '\"' )
            // XMLLexer.g:58:8: {...}? => '\"'
            {
            if ( !( inElem || inAposAttr ) ) {
                throw new FailedPredicateException(input, "QUOT", " inElem || inAposAttr ");
            }
            match('\"'); 
             inQuotAttr = !inQuotAttr; 


            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end QUOT

    // $ANTLR start APOS
    public final void mAPOS() throws RecognitionException {
        try {
            int _type = APOS;
            // XMLLexer.g:59:6: ({...}? => '\\'' )
            // XMLLexer.g:59:8: {...}? => '\\''
            {
            if ( !( inElem || inQuotAttr ) ) {
                throw new FailedPredicateException(input, "APOS", " inElem || inQuotAttr ");
            }
            match('\''); 
             inAposAttr = !inAposAttr; 


            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end APOS

    // $ANTLR start ESCAPE_APOS
    public final void mESCAPE_APOS() throws RecognitionException {
        try {
            int _type = ESCAPE_APOS;
            // XMLLexer.g:61:2: ({...}? => '\\'\\'' )
            // XMLLexer.g:61:4: {...}? => '\\'\\''
            {
            if ( !( inAposAttr ) ) {
                throw new FailedPredicateException(input, "ESCAPE_APOS", " inAposAttr ");
            }
            match("\'\'"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end ESCAPE_APOS

    // $ANTLR start ESCAPE_QUOT
    public final void mESCAPE_QUOT() throws RecognitionException {
        try {
            int _type = ESCAPE_QUOT;
            // XMLLexer.g:64:2: ({...}? => '\"\"' )
            // XMLLexer.g:64:4: {...}? => '\"\"'
            {
            if ( !( inQuotAttr ) ) {
                throw new FailedPredicateException(input, "ESCAPE_QUOT", " inQuotAttr ");
            }
            match("\"\""); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end ESCAPE_QUOT

    // $ANTLR start ElementContentChar
    public final void mElementContentChar() throws RecognitionException {
        try {
            int _type = ElementContentChar;
            // XMLLexer.g:69:2: ({...}? => ( '\\u0009' | '\\u000A' | '\\u000D' | '\\u0020' .. '\\u0025' | '\\u0027' .. '\\u003B' | '\\u003D' .. '\\u007A' | '\\u007C' | '\\u007E' .. '\\uD7FF' | '\\uE000' .. '\\uFFFD' )+ )
            // XMLLexer.g:69:4: {...}? => ( '\\u0009' | '\\u000A' | '\\u000D' | '\\u0020' .. '\\u0025' | '\\u0027' .. '\\u003B' | '\\u003D' .. '\\u007A' | '\\u007C' | '\\u007E' .. '\\uD7FF' | '\\uE000' .. '\\uFFFD' )+
            {
            if ( !( !inElem ) ) {
                throw new FailedPredicateException(input, "ElementContentChar", " !inElem ");
            }
            // XMLLexer.g:70:3: ( '\\u0009' | '\\u000A' | '\\u000D' | '\\u0020' .. '\\u0025' | '\\u0027' .. '\\u003B' | '\\u003D' .. '\\u007A' | '\\u007C' | '\\u007E' .. '\\uD7FF' | '\\uE000' .. '\\uFFFD' )+
            int cnt5=0;
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( ((LA5_0>='\t' && LA5_0<='\n')||LA5_0=='\r'||(LA5_0>=' ' && LA5_0<='%')||(LA5_0>='\'' && LA5_0<=';')||(LA5_0>='=' && LA5_0<='z')||LA5_0=='|'||(LA5_0>='~' && LA5_0<='\uD7FF')||(LA5_0>='\uE000' && LA5_0<='\uFFFD')) ) {
                    alt5=1;
                }


                switch (alt5) {
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
            	    if ( cnt5 >= 1 ) break loop5;
                        EarlyExitException eee =
                            new EarlyExitException(5, input);
                        throw eee;
                }
                cnt5++;
            } while (true);



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end ElementContentChar

    // $ANTLR start ESCAPE_CURLY_OPEN
    public final void mESCAPE_CURLY_OPEN() throws RecognitionException {
        try {
            int _type = ESCAPE_CURLY_OPEN;
            // XMLLexer.g:74:2: ({...}? => '{{' )
            // XMLLexer.g:74:4: {...}? => '{{'
            {
            if ( !( !inElem || inAposAttr || inQuotAttr ) ) {
                throw new FailedPredicateException(input, "ESCAPE_CURLY_OPEN", " !inElem || inAposAttr || inQuotAttr ");
            }
            match("{{"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end ESCAPE_CURLY_OPEN

    // $ANTLR start ESCAPE_CURLY_CLOSE
    public final void mESCAPE_CURLY_CLOSE() throws RecognitionException {
        try {
            int _type = ESCAPE_CURLY_CLOSE;
            // XMLLexer.g:76:2: ({...}? => '}}' )
            // XMLLexer.g:76:4: {...}? => '}}'
            {
            if ( !( !inElem || inAposAttr || inQuotAttr ) ) {
                throw new FailedPredicateException(input, "ESCAPE_CURLY_CLOSE", " !inElem || inAposAttr || inQuotAttr ");
            }
            match("}}"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end ESCAPE_CURLY_CLOSE

    // $ANTLR start LCURLY
    public final void mLCURLY() throws RecognitionException {
        try {
            int _type = LCURLY;
            // XMLLexer.g:77:8: ({...}? => '{' )
            // XMLLexer.g:77:10: {...}? => '{'
            {
            if ( !( !inElem || inAposAttr || inQuotAttr ) ) {
                throw new FailedPredicateException(input, "LCURLY", " !inElem || inAposAttr || inQuotAttr ");
            }
            match('{'); 


            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end LCURLY

    // $ANTLR start RCURLY
    public final void mRCURLY() throws RecognitionException {
        try {
            int _type = RCURLY;
            // XMLLexer.g:78:8: ({...}? => '}' )
            // XMLLexer.g:78:10: {...}? => '}'
            {
            if ( !( !inElem || inAposAttr || inQuotAttr ) ) {
                throw new FailedPredicateException(input, "RCURLY", " !inElem || inAposAttr || inQuotAttr ");
            }
            match('}'); 


            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RCURLY

    // $ANTLR start OPEN_ANGLE
    public final void mOPEN_ANGLE() throws RecognitionException {
        try {
            int _type = OPEN_ANGLE;
            // XMLLexer.g:81:2: ({...}? => '<' )
            // XMLLexer.g:81:4: {...}? => '<'
            {
            if ( !( !inElem ) ) {
                throw new FailedPredicateException(input, "OPEN_ANGLE", " !inElem ");
            }
            match('<'); 
             inElem = true; 


            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end OPEN_ANGLE

    // $ANTLR start CLOSE_TAG
    public final void mCLOSE_TAG() throws RecognitionException {
        try {
            int _type = CLOSE_TAG;
            // XMLLexer.g:83:2: ({...}? => '</' )
            // XMLLexer.g:83:4: {...}? => '</'
            {
            if ( !( !inElem ) ) {
                throw new FailedPredicateException(input, "CLOSE_TAG", " !inElem ");
            }
            match("</"); 

             inElem = true; 


            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end CLOSE_TAG

    // $ANTLR start PredefinedEntityRef
    public final void mPredefinedEntityRef() throws RecognitionException {
        try {
            int _type = PredefinedEntityRef;
            // XMLLexer.g:85:2: ({...}? => '&' ( 'lt' | 'gt' | 'apos' | 'quot' | 'amp' ) ';' )
            // XMLLexer.g:85:4: {...}? => '&' ( 'lt' | 'gt' | 'apos' | 'quot' | 'amp' ) ';'
            {
            if ( !( !inElem || inAposAttr || inQuotAttr ) ) {
                throw new FailedPredicateException(input, "PredefinedEntityRef", " !inElem || inAposAttr || inQuotAttr ");
            }
            match('&'); 
            // XMLLexer.g:86:7: ( 'lt' | 'gt' | 'apos' | 'quot' | 'amp' )
            int alt6=5;
            switch ( input.LA(1) ) {
            case 'l':
                {
                alt6=1;
                }
                break;
            case 'g':
                {
                alt6=2;
                }
                break;
            case 'a':
                {
                int LA6_3 = input.LA(2);

                if ( (LA6_3=='p') ) {
                    alt6=3;
                }
                else if ( (LA6_3=='m') ) {
                    alt6=5;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 6, 3, input);

                    throw nvae;
                }
                }
                break;
            case 'q':
                {
                alt6=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }

            switch (alt6) {
                case 1 :
                    // XMLLexer.g:86:8: 'lt'
                    {
                    match("lt"); 



                    }
                    break;
                case 2 :
                    // XMLLexer.g:86:15: 'gt'
                    {
                    match("gt"); 



                    }
                    break;
                case 3 :
                    // XMLLexer.g:86:22: 'apos'
                    {
                    match("apos"); 



                    }
                    break;
                case 4 :
                    // XMLLexer.g:86:31: 'quot'
                    {
                    match("quot"); 



                    }
                    break;
                case 5 :
                    // XMLLexer.g:86:40: 'amp'
                    {
                    match("amp"); 



                    }
                    break;

            }

            match(';'); 


            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end PredefinedEntityRef

    // $ANTLR start CharRef
    public final void mCharRef() throws RecognitionException {
        try {
            int _type = CharRef;
            // XMLLexer.g:87:9: ({...}? => '&#' ( '0' .. '9' )+ ';' | '&#x' ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' )+ ';' )
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0=='&') ) {
                int LA9_1 = input.LA(2);

                if ( (LA9_1=='#') ) {
                    int LA9_2 = input.LA(3);

                    if ( (LA9_2=='x') ) {
                        alt9=2;
                    }
                    else if ( ((LA9_2>='0' && LA9_2<='9')) && ( !inElem || inAposAttr || inQuotAttr )) {
                        alt9=1;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 9, 2, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 9, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;
            }
            switch (alt9) {
                case 1 :
                    // XMLLexer.g:87:11: {...}? => '&#' ( '0' .. '9' )+ ';'
                    {
                    if ( !( !inElem || inAposAttr || inQuotAttr ) ) {
                        throw new FailedPredicateException(input, "CharRef", " !inElem || inAposAttr || inQuotAttr ");
                    }
                    match("&#"); 

                    // XMLLexer.g:88:8: ( '0' .. '9' )+
                    int cnt7=0;
                    loop7:
                    do {
                        int alt7=2;
                        int LA7_0 = input.LA(1);

                        if ( ((LA7_0>='0' && LA7_0<='9')) ) {
                            alt7=1;
                        }


                        switch (alt7) {
                    	case 1 :
                    	    // XMLLexer.g:88:9: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 


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

                    match(';'); 


                    }
                    break;
                case 2 :
                    // XMLLexer.g:88:26: '&#x' ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' )+ ';'
                    {
                    match("&#x"); 

                    // XMLLexer.g:88:32: ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' )+
                    int cnt8=0;
                    loop8:
                    do {
                        int alt8=2;
                        int LA8_0 = input.LA(1);

                        if ( ((LA8_0>='0' && LA8_0<='9')||(LA8_0>='A' && LA8_0<='F')||(LA8_0>='a' && LA8_0<='f')) ) {
                            alt8=1;
                        }


                        switch (alt8) {
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

            }
            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end CharRef

    // $ANTLR start DirCommentConstructor
    public final void mDirCommentConstructor() throws RecognitionException {
        try {
            int _type = DirCommentConstructor;
            // XMLLexer.g:91:2: ({...}? => '<!--' ( options {greedy=false; } : ( . )* ) '-->' )
            // XMLLexer.g:91:4: {...}? => '<!--' ( options {greedy=false; } : ( . )* ) '-->'
            {
            if ( !( !inElem ) ) {
                throw new FailedPredicateException(input, "DirCommentConstructor", " !inElem ");
            }
            match("<!--"); 

            // XMLLexer.g:91:27: ( options {greedy=false; } : ( . )* )
            // XMLLexer.g:91:54: ( . )*
            {
            // XMLLexer.g:91:54: ( . )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0=='-') ) {
                    int LA10_1 = input.LA(2);

                    if ( (LA10_1=='-') ) {
                        int LA10_3 = input.LA(3);

                        if ( (LA10_3=='>') ) {
                            alt10=2;
                        }
                        else if ( ((LA10_3>='\u0000' && LA10_3<='=')||(LA10_3>='?' && LA10_3<='\uFFFE')) ) {
                            alt10=1;
                        }


                    }
                    else if ( ((LA10_1>='\u0000' && LA10_1<=',')||(LA10_1>='.' && LA10_1<='\uFFFE')) ) {
                        alt10=1;
                    }


                }
                else if ( ((LA10_0>='\u0000' && LA10_0<=',')||(LA10_0>='.' && LA10_0<='\uFFFE')) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // XMLLexer.g:91:54: .
            	    {
            	    matchAny(); 


            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);



            }

            match("-->"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end DirCommentConstructor

    // $ANTLR start DirPIConstructor
    public final void mDirPIConstructor() throws RecognitionException {
        try {
            int _type = DirPIConstructor;
            // XMLLexer.g:93:2: ({...}? => '<?' ( SUnprotected )? NCNameUnprotected ( SUnprotected ( options {greedy=false; } : ( . )* ) )? '?>' )
            // XMLLexer.g:93:4: {...}? => '<?' ( SUnprotected )? NCNameUnprotected ( SUnprotected ( options {greedy=false; } : ( . )* ) )? '?>'
            {
            if ( !( !inElem ) ) {
                throw new FailedPredicateException(input, "DirPIConstructor", " !inElem ");
            }
            match("<?"); 

            // XMLLexer.g:94:8: ( SUnprotected )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( ((LA11_0>='\t' && LA11_0<='\n')||LA11_0=='\r'||LA11_0==' ') ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // XMLLexer.g:94:8: SUnprotected
                    {
                    mSUnprotected(); 


                    }
                    break;

            }

            mNCNameUnprotected(); 
            // XMLLexer.g:94:40: ( SUnprotected ( options {greedy=false; } : ( . )* ) )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( ((LA13_0>='\t' && LA13_0<='\n')||LA13_0=='\r'||LA13_0==' ') ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // XMLLexer.g:94:41: SUnprotected ( options {greedy=false; } : ( . )* )
                    {
                    mSUnprotected(); 
                    // XMLLexer.g:94:54: ( options {greedy=false; } : ( . )* )
                    // XMLLexer.g:94:81: ( . )*
                    {
                    // XMLLexer.g:94:81: ( . )*
                    loop12:
                    do {
                        int alt12=2;
                        int LA12_0 = input.LA(1);

                        if ( (LA12_0=='?') ) {
                            int LA12_1 = input.LA(2);

                            if ( (LA12_1=='>') ) {
                                alt12=2;
                            }
                            else if ( ((LA12_1>='\u0000' && LA12_1<='=')||(LA12_1>='?' && LA12_1<='\uFFFE')) ) {
                                alt12=1;
                            }


                        }
                        else if ( ((LA12_0>='\u0000' && LA12_0<='>')||(LA12_0>='@' && LA12_0<='\uFFFE')) ) {
                            alt12=1;
                        }


                        switch (alt12) {
                    	case 1 :
                    	    // XMLLexer.g:94:81: .
                    	    {
                    	    matchAny(); 


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

            match("?>"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end DirPIConstructor

    // $ANTLR start SUnprotected
    public final void mSUnprotected() throws RecognitionException {
        try {
            // XMLLexer.g:96:2: ( ( ' ' | '\\t' | '\\n' | '\\r' )+ )
            // XMLLexer.g:96:4: ( ' ' | '\\t' | '\\n' | '\\r' )+
            {
            // XMLLexer.g:96:4: ( ' ' | '\\t' | '\\n' | '\\r' )+
            int cnt14=0;
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( ((LA14_0>='\t' && LA14_0<='\n')||LA14_0=='\r'||LA14_0==' ') ) {
                    alt14=1;
                }


                switch (alt14) {
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
            	    if ( cnt14 >= 1 ) break loop14;
                        EarlyExitException eee =
                            new EarlyExitException(14, input);
                        throw eee;
                }
                cnt14++;
            } while (true);



            }

        }
        finally {
        }
    }
    // $ANTLR end SUnprotected

    // $ANTLR start CDataSection
    public final void mCDataSection() throws RecognitionException {
        try {
            int _type = CDataSection;
            // XMLLexer.g:98:2: ({...}? => '<![CDATA[' ( options {greedy=false; } : ( . )* ) ']]>' )
            // XMLLexer.g:98:4: {...}? => '<![CDATA[' ( options {greedy=false; } : ( . )* ) ']]>'
            {
            if ( !( !inElem ) ) {
                throw new FailedPredicateException(input, "CDataSection", " !inElem ");
            }
            match("<![CDATA["); 

            // XMLLexer.g:98:32: ( options {greedy=false; } : ( . )* )
            // XMLLexer.g:98:59: ( . )*
            {
            // XMLLexer.g:98:59: ( . )*
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( (LA15_0==']') ) {
                    int LA15_1 = input.LA(2);

                    if ( (LA15_1==']') ) {
                        int LA15_3 = input.LA(3);

                        if ( (LA15_3=='>') ) {
                            alt15=2;
                        }
                        else if ( ((LA15_3>='\u0000' && LA15_3<='=')||(LA15_3>='?' && LA15_3<='\uFFFE')) ) {
                            alt15=1;
                        }


                    }
                    else if ( ((LA15_1>='\u0000' && LA15_1<='\\')||(LA15_1>='^' && LA15_1<='\uFFFE')) ) {
                        alt15=1;
                    }


                }
                else if ( ((LA15_0>='\u0000' && LA15_0<='\\')||(LA15_0>='^' && LA15_0<='\uFFFE')) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // XMLLexer.g:98:59: .
            	    {
            	    matchAny(); 


            	    }
            	    break;

            	default :
            	    break loop15;
                }
            } while (true);



            }

            match("]]>"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end CDataSection

    public void mTokens() throws RecognitionException {
        // XMLLexer.g:1:8: ( QuotAttrContentChar | AposAttrContentChar | CLOSE_ANGLE | EMPTY_CLOSE_TAG | S | NCName | COLON | Lit_EQ | QUOT | APOS | ESCAPE_APOS | ESCAPE_QUOT | ElementContentChar | ESCAPE_CURLY_OPEN | ESCAPE_CURLY_CLOSE | LCURLY | RCURLY | OPEN_ANGLE | CLOSE_TAG | PredefinedEntityRef | CharRef | DirCommentConstructor | DirPIConstructor | CDataSection )
        int alt16=24;
        alt16 = dfa16.predict(input);
        switch (alt16) {
            case 1 :
                // XMLLexer.g:1:10: QuotAttrContentChar
                {
                mQuotAttrContentChar(); 


                }
                break;
            case 2 :
                // XMLLexer.g:1:30: AposAttrContentChar
                {
                mAposAttrContentChar(); 


                }
                break;
            case 3 :
                // XMLLexer.g:1:50: CLOSE_ANGLE
                {
                mCLOSE_ANGLE(); 


                }
                break;
            case 4 :
                // XMLLexer.g:1:62: EMPTY_CLOSE_TAG
                {
                mEMPTY_CLOSE_TAG(); 


                }
                break;
            case 5 :
                // XMLLexer.g:1:78: S
                {
                mS(); 


                }
                break;
            case 6 :
                // XMLLexer.g:1:80: NCName
                {
                mNCName(); 


                }
                break;
            case 7 :
                // XMLLexer.g:1:87: COLON
                {
                mCOLON(); 


                }
                break;
            case 8 :
                // XMLLexer.g:1:93: Lit_EQ
                {
                mLit_EQ(); 


                }
                break;
            case 9 :
                // XMLLexer.g:1:100: QUOT
                {
                mQUOT(); 


                }
                break;
            case 10 :
                // XMLLexer.g:1:105: APOS
                {
                mAPOS(); 


                }
                break;
            case 11 :
                // XMLLexer.g:1:110: ESCAPE_APOS
                {
                mESCAPE_APOS(); 


                }
                break;
            case 12 :
                // XMLLexer.g:1:122: ESCAPE_QUOT
                {
                mESCAPE_QUOT(); 


                }
                break;
            case 13 :
                // XMLLexer.g:1:134: ElementContentChar
                {
                mElementContentChar(); 


                }
                break;
            case 14 :
                // XMLLexer.g:1:153: ESCAPE_CURLY_OPEN
                {
                mESCAPE_CURLY_OPEN(); 


                }
                break;
            case 15 :
                // XMLLexer.g:1:171: ESCAPE_CURLY_CLOSE
                {
                mESCAPE_CURLY_CLOSE(); 


                }
                break;
            case 16 :
                // XMLLexer.g:1:190: LCURLY
                {
                mLCURLY(); 


                }
                break;
            case 17 :
                // XMLLexer.g:1:197: RCURLY
                {
                mRCURLY(); 


                }
                break;
            case 18 :
                // XMLLexer.g:1:204: OPEN_ANGLE
                {
                mOPEN_ANGLE(); 


                }
                break;
            case 19 :
                // XMLLexer.g:1:215: CLOSE_TAG
                {
                mCLOSE_TAG(); 


                }
                break;
            case 20 :
                // XMLLexer.g:1:225: PredefinedEntityRef
                {
                mPredefinedEntityRef(); 


                }
                break;
            case 21 :
                // XMLLexer.g:1:245: CharRef
                {
                mCharRef(); 


                }
                break;
            case 22 :
                // XMLLexer.g:1:253: DirCommentConstructor
                {
                mDirCommentConstructor(); 


                }
                break;
            case 23 :
                // XMLLexer.g:1:275: DirPIConstructor
                {
                mDirPIConstructor(); 


                }
                break;
            case 24 :
                // XMLLexer.g:1:292: CDataSection
                {
                mCDataSection(); 


                }
                break;

        }

    }


    protected DFA16 dfa16 = new DFA16(this);
    static final String DFA16_eotS =
        "\1\uffff\1\16\1\22\1\25\1\27\1\30\1\31\1\33\1\34\1\27\1\36\1\40"+
        "\1\44\2\uffff\1\52\1\53\1\54\2\uffff\1\56\1\uffff\1\60\3\uffff\1"+
        "\31\37\uffff";
    static final String DFA16_eofS =
        "\72\uffff";
    static final String DFA16_minS =
        "\12\11\1\173\1\175\1\41\1\43\1\0\3\11\1\0\1\uffff\1\11\1\0\1\11"+
        "\3\0\1\11\2\0\5\uffff\1\55\7\uffff\3\0\1\uffff\1\0\1\uffff\1\0\11"+
        "\uffff";
    static final String DFA16_maxS =
        "\12\ufffd\1\173\1\175\1\77\1\161\1\0\3\ufffd\1\0\1\uffff\1\ufffd"+
        "\1\0\1\ufffd\3\0\1\ufffd\2\0\5\uffff\1\133\7\uffff\3\0\1\uffff\1"+
        "\0\1\uffff\1\0\11\uffff";
    static final String DFA16_acceptS =
        "\23\uffff\1\15\11\uffff\1\16\1\20\1\17\1\21\1\23\1\uffff\1\27\1"+
        "\22\1\25\1\24\1\1\1\2\1\3\3\uffff\1\12\1\uffff\1\11\1\uffff\1\5"+
        "\1\6\1\7\1\10\1\26\1\30\1\13\1\14\1\4";
    static final String DFA16_specialS =
        "\1\24\1\10\1\36\1\15\1\20\1\0\1\2\1\35\1\11\1\31\1\40\1\26\1\14"+
        "\1\3\1\37\1\21\1\17\1\22\1\7\1\uffff\1\27\1\6\1\30\1\32\1\23\1\34"+
        "\1\16\1\4\1\41\5\uffff\1\13\7\uffff\1\5\1\33\1\25\1\uffff\1\12\1"+
        "\uffff\1\1\11\uffff}>";
    static final String[] DFA16_transitionS = {
            "\2\5\2\uffff\1\5\22\uffff\1\5\1\11\1\3\3\11\1\15\1\2\7\11\1"+
            "\4\12\11\1\7\1\11\1\14\1\10\1\1\2\11\32\6\4\11\1\6\1\11\32\6"+
            "\1\12\1\11\1\13\ud782\11\u0800\uffff\u1ffe\11",
            "\2\11\2\uffff\1\11\22\uffff\2\11\1\17\3\11\1\uffff\1\20\24\11"+
            "\1\uffff\76\11\1\uffff\1\11\1\uffff\ud782\11\u0800\uffff\u1ffe"+
            "\11",
            "\2\20\2\uffff\1\20\22\uffff\2\20\1\23\3\20\1\uffff\1\21\24\20"+
            "\1\uffff\76\20\1\uffff\1\20\1\uffff\ud782\20\u0800\uffff\u1ffe"+
            "\20",
            "\2\17\2\uffff\1\17\22\uffff\2\17\1\24\3\17\1\uffff\1\23\24\17"+
            "\1\uffff\76\17\1\uffff\1\17\1\uffff\ud782\17\u0800\uffff\u1ffe"+
            "\17",
            "\2\11\2\uffff\1\11\22\uffff\2\11\1\17\3\11\1\uffff\1\20\24\11"+
            "\1\uffff\1\11\1\26\74\11\1\uffff\1\11\1\uffff\ud782\11\u0800"+
            "\uffff\u1ffe\11",
            "\2\5\2\uffff\1\5\22\uffff\1\5\1\11\1\17\3\11\1\uffff\1\20\24"+
            "\11\1\uffff\76\11\1\uffff\1\11\1\uffff\ud782\11\u0800\uffff"+
            "\u1ffe\11",
            "\2\11\2\uffff\1\11\22\uffff\2\11\1\17\3\11\1\uffff\1\20\5\11"+
            "\2\32\1\11\12\32\2\11\1\uffff\4\11\32\32\4\11\1\32\1\11\32\32"+
            "\1\uffff\1\11\1\uffff\ud782\11\u0800\uffff\u1ffe\11",
            "\2\11\2\uffff\1\11\22\uffff\2\11\1\17\3\11\1\uffff\1\20\24\11"+
            "\1\uffff\76\11\1\uffff\1\11\1\uffff\ud782\11\u0800\uffff\u1ffe"+
            "\11",
            "\2\11\2\uffff\1\11\22\uffff\2\11\1\17\3\11\1\uffff\1\20\24\11"+
            "\1\uffff\76\11\1\uffff\1\11\1\uffff\ud782\11\u0800\uffff\u1ffe"+
            "\11",
            "\2\11\2\uffff\1\11\22\uffff\2\11\1\17\3\11\1\uffff\1\20\24\11"+
            "\1\uffff\76\11\1\uffff\1\11\1\uffff\ud782\11\u0800\uffff\u1ffe"+
            "\11",
            "\1\35",
            "\1\37",
            "\1\42\15\uffff\1\41\17\uffff\1\43",
            "\1\45\75\uffff\1\46\5\uffff\1\46\4\uffff\1\46\4\uffff\1\46",
            "\1\uffff",
            "\2\17\2\uffff\1\17\22\uffff\6\17\1\uffff\1\23\24\17\1\uffff"+
            "\76\17\1\uffff\1\17\1\uffff\ud782\17\u0800\uffff\u1ffe\17",
            "\2\20\2\uffff\1\20\22\uffff\2\20\1\23\3\20\1\uffff\25\20\1\uffff"+
            "\76\20\1\uffff\1\20\1\uffff\ud782\20\u0800\uffff\u1ffe\20",
            "\2\20\2\uffff\1\20\22\uffff\2\20\1\23\3\20\1\uffff\25\20\1\uffff"+
            "\76\20\1\uffff\1\20\1\uffff\ud782\20\u0800\uffff\u1ffe\20",
            "\1\uffff",
            "",
            "\2\17\2\uffff\1\17\22\uffff\6\17\1\uffff\1\23\24\17\1\uffff"+
            "\76\17\1\uffff\1\17\1\uffff\ud782\17\u0800\uffff\u1ffe\17",
            "\1\uffff",
            "\2\11\2\uffff\1\11\22\uffff\2\11\1\17\3\11\1\uffff\1\20\24\11"+
            "\1\uffff\76\11\1\uffff\1\11\1\uffff\ud782\11\u0800\uffff\u1ffe"+
            "\11",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\2\11\2\uffff\1\11\22\uffff\2\11\1\17\3\11\1\uffff\1\20\5\11"+
            "\2\32\1\11\12\32\2\11\1\uffff\4\11\32\32\4\11\1\32\1\11\32\32"+
            "\1\uffff\1\11\1\uffff\ud782\11\u0800\uffff\u1ffe\11",
            "\1\uffff",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "\1\65\55\uffff\1\66",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "",
            "\1\uffff",
            "",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
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
            return "1:1: Tokens : ( QuotAttrContentChar | AposAttrContentChar | CLOSE_ANGLE | EMPTY_CLOSE_TAG | S | NCName | COLON | Lit_EQ | QUOT | APOS | ESCAPE_APOS | ESCAPE_QUOT | ElementContentChar | ESCAPE_CURLY_OPEN | ESCAPE_CURLY_CLOSE | LCURLY | RCURLY | OPEN_ANGLE | CLOSE_TAG | PredefinedEntityRef | CharRef | DirCommentConstructor | DirPIConstructor | CDataSection );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA16_5 = input.LA(1);

                         
                        int index16_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ((LA16_5>='\t' && LA16_5<='\n')||LA16_5=='\r'||LA16_5==' ') && (( !inElem || inQuotAttr || inElem  || inAposAttr ))) {s = 5;}

                        else if ( (LA16_5=='!'||(LA16_5>='#' && LA16_5<='%')||(LA16_5>='(' && LA16_5<=';')||(LA16_5>='=' && LA16_5<='z')||LA16_5=='|'||(LA16_5>='~' && LA16_5<='\uD7FF')||(LA16_5>='\uE000' && LA16_5<='\uFFFD')) && (( !inElem || inQuotAttr || inAposAttr ))) {s = 9;}

                        else if ( (LA16_5=='\"') && (( !inElem || inAposAttr ))) {s = 15;}

                        else if ( (LA16_5=='\'') && (( !inElem || inQuotAttr ))) {s = 16;}

                        else s = 24;

                         
                        input.seek(index16_5);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA16_48 = input.LA(1);

                         
                        int index16_48 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ( inQuotAttr ) ) {s = 39;}

                        else if ( ( inAposAttr ) ) {s = 40;}

                        else if ( ( inElem  ) ) {s = 57;}

                        else if ( ( !inElem ) ) {s = 19;}

                         
                        input.seek(index16_48);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA16_6 = input.LA(1);

                         
                        int index16_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ((LA16_6>='-' && LA16_6<='.')||(LA16_6>='0' && LA16_6<='9')||(LA16_6>='A' && LA16_6<='Z')||LA16_6=='_'||(LA16_6>='a' && LA16_6<='z')) && (( !inElem || inQuotAttr || inElem  || inAposAttr ))) {s = 26;}

                        else if ( ((LA16_6>='\t' && LA16_6<='\n')||LA16_6=='\r'||(LA16_6>=' ' && LA16_6<='!')||(LA16_6>='#' && LA16_6<='%')||(LA16_6>='(' && LA16_6<=',')||LA16_6=='/'||(LA16_6>=':' && LA16_6<=';')||(LA16_6>='=' && LA16_6<='@')||(LA16_6>='[' && LA16_6<='^')||LA16_6=='`'||LA16_6=='|'||(LA16_6>='~' && LA16_6<='\uD7FF')||(LA16_6>='\uE000' && LA16_6<='\uFFFD')) && (( !inElem || inQuotAttr || inAposAttr ))) {s = 9;}

                        else if ( (LA16_6=='\"') && (( !inElem || inAposAttr ))) {s = 15;}

                        else if ( (LA16_6=='\'') && (( !inElem || inQuotAttr ))) {s = 16;}

                        else s = 25;

                         
                        input.seek(index16_6);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA16_13 = input.LA(1);

                         
                        int index16_13 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA16_13=='#') ) {s = 37;}

                        else if ( (LA16_13=='a'||LA16_13=='g'||LA16_13=='l'||LA16_13=='q') && ( !inElem || inAposAttr || inQuotAttr )) {s = 38;}

                         
                        input.seek(index16_13);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA16_27 = input.LA(1);

                         
                        int index16_27 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ( inQuotAttr ) ) {s = 39;}

                        else if ( ( inAposAttr ) ) {s = 40;}

                        else if ( (!(( !inElem || inQuotAttr || inAposAttr ))) ) {s = 51;}

                        else if ( ( !inElem ) ) {s = 19;}

                         
                        input.seek(index16_27);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA16_42 = input.LA(1);

                         
                        int index16_42 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ( inAposAttr ) ) {s = 40;}

                        else if ( ( !inElem ) ) {s = 19;}

                         
                        input.seek(index16_42);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA16_21 = input.LA(1);

                         
                        int index16_21 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ( inAposAttr ) ) {s = 40;}

                        else if ( ( inElem || inAposAttr ) ) {s = 47;}

                        else if ( ( !inElem ) ) {s = 19;}

                         
                        input.seek(index16_21);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA16_18 = input.LA(1);

                         
                        int index16_18 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ( inQuotAttr ) ) {s = 39;}

                        else if ( ( inElem || inQuotAttr ) ) {s = 45;}

                        else if ( ( !inElem ) ) {s = 19;}

                         
                        input.seek(index16_18);
                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA16_1 = input.LA(1);

                         
                        int index16_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ((LA16_1>='\t' && LA16_1<='\n')||LA16_1=='\r'||(LA16_1>=' ' && LA16_1<='!')||(LA16_1>='#' && LA16_1<='%')||(LA16_1>='(' && LA16_1<=';')||(LA16_1>='=' && LA16_1<='z')||LA16_1=='|'||(LA16_1>='~' && LA16_1<='\uD7FF')||(LA16_1>='\uE000' && LA16_1<='\uFFFD')) && (( !inElem || inQuotAttr || inAposAttr ))) {s = 9;}

                        else if ( (LA16_1=='\"') && (( !inElem || inAposAttr ))) {s = 15;}

                        else if ( (LA16_1=='\'') && (( !inElem || inQuotAttr ))) {s = 16;}

                        else s = 14;

                         
                        input.seek(index16_1);
                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA16_8 = input.LA(1);

                         
                        int index16_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ((LA16_8>='\t' && LA16_8<='\n')||LA16_8=='\r'||(LA16_8>=' ' && LA16_8<='!')||(LA16_8>='#' && LA16_8<='%')||(LA16_8>='(' && LA16_8<=';')||(LA16_8>='=' && LA16_8<='z')||LA16_8=='|'||(LA16_8>='~' && LA16_8<='\uD7FF')||(LA16_8>='\uE000' && LA16_8<='\uFFFD')) && (( !inElem || inQuotAttr || inAposAttr ))) {s = 9;}

                        else if ( (LA16_8=='\"') && (( !inElem || inAposAttr ))) {s = 15;}

                        else if ( (LA16_8=='\'') && (( !inElem || inQuotAttr ))) {s = 16;}

                        else s = 28;

                         
                        input.seek(index16_8);
                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA16_46 = input.LA(1);

                         
                        int index16_46 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ( inAposAttr ) ) {s = 40;}

                        else if ( ( inQuotAttr ) ) {s = 56;}

                        else if ( ( !inElem ) ) {s = 19;}

                         
                        input.seek(index16_46);
                        if ( s>=0 ) return s;
                        break;
                    case 11 : 
                        int LA16_34 = input.LA(1);

                         
                        int index16_34 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA16_34=='-') && ( !inElem )) {s = 53;}

                        else if ( (LA16_34=='[') && ( !inElem )) {s = 54;}

                         
                        input.seek(index16_34);
                        if ( s>=0 ) return s;
                        break;
                    case 12 : 
                        int LA16_12 = input.LA(1);

                         
                        int index16_12 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA16_12=='/') && ( !inElem )) {s = 33;}

                        else if ( (LA16_12=='!') && ( !inElem )) {s = 34;}

                        else if ( (LA16_12=='?') && ( !inElem )) {s = 35;}

                        else s = 36;

                         
                        input.seek(index16_12);
                        if ( s>=0 ) return s;
                        break;
                    case 13 : 
                        int LA16_3 = input.LA(1);

                         
                        int index16_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA16_3=='\"') && (( !inElem || inQuotAttr || inAposAttr ))) {s = 20;}

                        else if ( ((LA16_3>='\t' && LA16_3<='\n')||LA16_3=='\r'||(LA16_3>=' ' && LA16_3<='!')||(LA16_3>='#' && LA16_3<='%')||(LA16_3>='(' && LA16_3<=';')||(LA16_3>='=' && LA16_3<='z')||LA16_3=='|'||(LA16_3>='~' && LA16_3<='\uD7FF')||(LA16_3>='\uE000' && LA16_3<='\uFFFD')) && (( !inElem || inAposAttr ))) {s = 15;}

                        else if ( (LA16_3=='\'') && ( !inElem )) {s = 19;}

                        else s = 21;

                         
                        input.seek(index16_3);
                        if ( s>=0 ) return s;
                        break;
                    case 14 : 
                        int LA16_26 = input.LA(1);

                         
                        int index16_26 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ((LA16_26>='-' && LA16_26<='.')||(LA16_26>='0' && LA16_26<='9')||(LA16_26>='A' && LA16_26<='Z')||LA16_26=='_'||(LA16_26>='a' && LA16_26<='z')) && (( !inElem || inQuotAttr || inElem  || inAposAttr ))) {s = 26;}

                        else if ( ((LA16_26>='\t' && LA16_26<='\n')||LA16_26=='\r'||(LA16_26>=' ' && LA16_26<='!')||(LA16_26>='#' && LA16_26<='%')||(LA16_26>='(' && LA16_26<=',')||LA16_26=='/'||(LA16_26>=':' && LA16_26<=';')||(LA16_26>='=' && LA16_26<='@')||(LA16_26>='[' && LA16_26<='^')||LA16_26=='`'||LA16_26=='|'||(LA16_26>='~' && LA16_26<='\uD7FF')||(LA16_26>='\uE000' && LA16_26<='\uFFFD')) && (( !inElem || inQuotAttr || inAposAttr ))) {s = 9;}

                        else if ( (LA16_26=='\"') && (( !inElem || inAposAttr ))) {s = 15;}

                        else if ( (LA16_26=='\'') && (( !inElem || inQuotAttr ))) {s = 16;}

                        else s = 25;

                         
                        input.seek(index16_26);
                        if ( s>=0 ) return s;
                        break;
                    case 15 : 
                        int LA16_16 = input.LA(1);

                         
                        int index16_16 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ((LA16_16>='\t' && LA16_16<='\n')||LA16_16=='\r'||(LA16_16>=' ' && LA16_16<='!')||(LA16_16>='#' && LA16_16<='%')||(LA16_16>='\'' && LA16_16<=';')||(LA16_16>='=' && LA16_16<='z')||LA16_16=='|'||(LA16_16>='~' && LA16_16<='\uD7FF')||(LA16_16>='\uE000' && LA16_16<='\uFFFD')) && (( !inElem || inQuotAttr ))) {s = 16;}

                        else if ( (LA16_16=='\"') && ( !inElem )) {s = 19;}

                        else s = 43;

                         
                        input.seek(index16_16);
                        if ( s>=0 ) return s;
                        break;
                    case 16 : 
                        int LA16_4 = input.LA(1);

                         
                        int index16_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA16_4=='>') && (( !inElem || inQuotAttr || inElem  || inAposAttr ))) {s = 22;}

                        else if ( ((LA16_4>='\t' && LA16_4<='\n')||LA16_4=='\r'||(LA16_4>=' ' && LA16_4<='!')||(LA16_4>='#' && LA16_4<='%')||(LA16_4>='(' && LA16_4<=';')||LA16_4=='='||(LA16_4>='?' && LA16_4<='z')||LA16_4=='|'||(LA16_4>='~' && LA16_4<='\uD7FF')||(LA16_4>='\uE000' && LA16_4<='\uFFFD')) && (( !inElem || inQuotAttr || inAposAttr ))) {s = 9;}

                        else if ( (LA16_4=='\"') && (( !inElem || inAposAttr ))) {s = 15;}

                        else if ( (LA16_4=='\'') && (( !inElem || inQuotAttr ))) {s = 16;}

                        else s = 23;

                         
                        input.seek(index16_4);
                        if ( s>=0 ) return s;
                        break;
                    case 17 : 
                        int LA16_15 = input.LA(1);

                         
                        int index16_15 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ((LA16_15>='\t' && LA16_15<='\n')||LA16_15=='\r'||(LA16_15>=' ' && LA16_15<='%')||(LA16_15>='(' && LA16_15<=';')||(LA16_15>='=' && LA16_15<='z')||LA16_15=='|'||(LA16_15>='~' && LA16_15<='\uD7FF')||(LA16_15>='\uE000' && LA16_15<='\uFFFD')) && (( !inElem || inAposAttr ))) {s = 15;}

                        else if ( (LA16_15=='\'') && ( !inElem )) {s = 19;}

                        else s = 42;

                         
                        input.seek(index16_15);
                        if ( s>=0 ) return s;
                        break;
                    case 18 : 
                        int LA16_17 = input.LA(1);

                         
                        int index16_17 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ((LA16_17>='\t' && LA16_17<='\n')||LA16_17=='\r'||(LA16_17>=' ' && LA16_17<='!')||(LA16_17>='#' && LA16_17<='%')||(LA16_17>='\'' && LA16_17<=';')||(LA16_17>='=' && LA16_17<='z')||LA16_17=='|'||(LA16_17>='~' && LA16_17<='\uD7FF')||(LA16_17>='\uE000' && LA16_17<='\uFFFD')) && (( !inElem || inQuotAttr ))) {s = 16;}

                        else if ( (LA16_17=='\"') && ( !inElem )) {s = 19;}

                        else s = 44;

                         
                        input.seek(index16_17);
                        if ( s>=0 ) return s;
                        break;
                    case 19 : 
                        int LA16_24 = input.LA(1);

                         
                        int index16_24 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ( inQuotAttr ) ) {s = 39;}

                        else if ( ( inAposAttr ) ) {s = 40;}

                        else if ( ( inElem  ) ) {s = 49;}

                        else if ( ( !inElem ) ) {s = 19;}

                         
                        input.seek(index16_24);
                        if ( s>=0 ) return s;
                        break;
                    case 20 : 
                        int LA16_0 = input.LA(1);

                         
                        int index16_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA16_0=='>') && (( !inElem || inElem  || inQuotAttr || inAposAttr ))) {s = 1;}

                        else if ( (LA16_0=='\'') && (( !inElem || inQuotAttr || inElem || inQuotAttr || inAposAttr ))) {s = 2;}

                        else if ( (LA16_0=='\"') && (( !inElem || inQuotAttr || inElem || inAposAttr || inAposAttr ))) {s = 3;}

                        else if ( (LA16_0=='/') && (( !inElem || inElem  || inQuotAttr || inAposAttr ))) {s = 4;}

                        else if ( ((LA16_0>='\t' && LA16_0<='\n')||LA16_0=='\r'||LA16_0==' ') && (( !inElem || inQuotAttr || inElem  || inAposAttr ))) {s = 5;}

                        else if ( ((LA16_0>='A' && LA16_0<='Z')||LA16_0=='_'||(LA16_0>='a' && LA16_0<='z')) && (( !inElem || inElem  || inQuotAttr || inAposAttr ))) {s = 6;}

                        else if ( (LA16_0==':') ) {s = 7;}

                        else if ( (LA16_0=='=') && (( !inElem || inElem  || inQuotAttr || inAposAttr ))) {s = 8;}

                        else if ( (LA16_0=='!'||(LA16_0>='#' && LA16_0<='%')||(LA16_0>='(' && LA16_0<='.')||(LA16_0>='0' && LA16_0<='9')||LA16_0==';'||(LA16_0>='?' && LA16_0<='@')||(LA16_0>='[' && LA16_0<='^')||LA16_0=='`'||LA16_0=='|'||(LA16_0>='~' && LA16_0<='\uD7FF')||(LA16_0>='\uE000' && LA16_0<='\uFFFD')) && (( !inElem || inQuotAttr || inAposAttr ))) {s = 9;}

                        else if ( (LA16_0=='{') && ( !inElem || inAposAttr || inQuotAttr )) {s = 10;}

                        else if ( (LA16_0=='}') && ( !inElem || inAposAttr || inQuotAttr )) {s = 11;}

                        else if ( (LA16_0=='<') && ( !inElem )) {s = 12;}

                        else if ( (LA16_0=='&') ) {s = 13;}

                         
                        input.seek(index16_0);
                        if ( s>=0 ) return s;
                        break;
                    case 21 : 
                        int LA16_44 = input.LA(1);

                         
                        int index16_44 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ( inQuotAttr ) ) {s = 39;}

                        else if ( ( inAposAttr ) ) {s = 55;}

                        else if ( ( !inElem ) ) {s = 19;}

                         
                        input.seek(index16_44);
                        if ( s>=0 ) return s;
                        break;
                    case 22 : 
                        int LA16_11 = input.LA(1);

                         
                        int index16_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA16_11=='}') && ( !inElem || inAposAttr || inQuotAttr )) {s = 31;}

                        else s = 32;

                         
                        input.seek(index16_11);
                        if ( s>=0 ) return s;
                        break;
                    case 23 : 
                        int LA16_20 = input.LA(1);

                         
                        int index16_20 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ((LA16_20>='\t' && LA16_20<='\n')||LA16_20=='\r'||(LA16_20>=' ' && LA16_20<='%')||(LA16_20>='(' && LA16_20<=';')||(LA16_20>='=' && LA16_20<='z')||LA16_20=='|'||(LA16_20>='~' && LA16_20<='\uD7FF')||(LA16_20>='\uE000' && LA16_20<='\uFFFD')) && (( !inElem || inAposAttr ))) {s = 15;}

                        else if ( (LA16_20=='\'') && ( !inElem )) {s = 19;}

                        else s = 46;

                         
                        input.seek(index16_20);
                        if ( s>=0 ) return s;
                        break;
                    case 24 : 
                        int LA16_22 = input.LA(1);

                         
                        int index16_22 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ((LA16_22>='\t' && LA16_22<='\n')||LA16_22=='\r'||(LA16_22>=' ' && LA16_22<='!')||(LA16_22>='#' && LA16_22<='%')||(LA16_22>='(' && LA16_22<=';')||(LA16_22>='=' && LA16_22<='z')||LA16_22=='|'||(LA16_22>='~' && LA16_22<='\uD7FF')||(LA16_22>='\uE000' && LA16_22<='\uFFFD')) && (( !inElem || inQuotAttr || inAposAttr ))) {s = 9;}

                        else if ( (LA16_22=='\"') && (( !inElem || inAposAttr ))) {s = 15;}

                        else if ( (LA16_22=='\'') && (( !inElem || inQuotAttr ))) {s = 16;}

                        else s = 48;

                         
                        input.seek(index16_22);
                        if ( s>=0 ) return s;
                        break;
                    case 25 : 
                        int LA16_9 = input.LA(1);

                         
                        int index16_9 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ((LA16_9>='\t' && LA16_9<='\n')||LA16_9=='\r'||(LA16_9>=' ' && LA16_9<='!')||(LA16_9>='#' && LA16_9<='%')||(LA16_9>='(' && LA16_9<=';')||(LA16_9>='=' && LA16_9<='z')||LA16_9=='|'||(LA16_9>='~' && LA16_9<='\uD7FF')||(LA16_9>='\uE000' && LA16_9<='\uFFFD')) && (( !inElem || inQuotAttr || inAposAttr ))) {s = 9;}

                        else if ( (LA16_9=='\"') && (( !inElem || inAposAttr ))) {s = 15;}

                        else if ( (LA16_9=='\'') && (( !inElem || inQuotAttr ))) {s = 16;}

                        else s = 23;

                         
                        input.seek(index16_9);
                        if ( s>=0 ) return s;
                        break;
                    case 26 : 
                        int LA16_23 = input.LA(1);

                         
                        int index16_23 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ( inQuotAttr ) ) {s = 39;}

                        else if ( ( inAposAttr ) ) {s = 40;}

                        else if ( ( !inElem ) ) {s = 19;}

                         
                        input.seek(index16_23);
                        if ( s>=0 ) return s;
                        break;
                    case 27 : 
                        int LA16_43 = input.LA(1);

                         
                        int index16_43 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ( inQuotAttr ) ) {s = 39;}

                        else if ( ( !inElem ) ) {s = 19;}

                         
                        input.seek(index16_43);
                        if ( s>=0 ) return s;
                        break;
                    case 28 : 
                        int LA16_25 = input.LA(1);

                         
                        int index16_25 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ( inQuotAttr ) ) {s = 39;}

                        else if ( ( inAposAttr ) ) {s = 40;}

                        else if ( ( inElem  ) ) {s = 50;}

                        else if ( ( !inElem ) ) {s = 19;}

                         
                        input.seek(index16_25);
                        if ( s>=0 ) return s;
                        break;
                    case 29 : 
                        int LA16_7 = input.LA(1);

                         
                        int index16_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ((LA16_7>='\t' && LA16_7<='\n')||LA16_7=='\r'||(LA16_7>=' ' && LA16_7<='!')||(LA16_7>='#' && LA16_7<='%')||(LA16_7>='(' && LA16_7<=';')||(LA16_7>='=' && LA16_7<='z')||LA16_7=='|'||(LA16_7>='~' && LA16_7<='\uD7FF')||(LA16_7>='\uE000' && LA16_7<='\uFFFD')) && (( !inElem || inQuotAttr || inAposAttr ))) {s = 9;}

                        else if ( (LA16_7=='\"') && (( !inElem || inAposAttr ))) {s = 15;}

                        else if ( (LA16_7=='\'') && (( !inElem || inQuotAttr ))) {s = 16;}

                        else s = 27;

                         
                        input.seek(index16_7);
                        if ( s>=0 ) return s;
                        break;
                    case 30 : 
                        int LA16_2 = input.LA(1);

                         
                        int index16_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA16_2=='\'') && (( !inElem || inQuotAttr || inAposAttr ))) {s = 17;}

                        else if ( ((LA16_2>='\t' && LA16_2<='\n')||LA16_2=='\r'||(LA16_2>=' ' && LA16_2<='!')||(LA16_2>='#' && LA16_2<='%')||(LA16_2>='(' && LA16_2<=';')||(LA16_2>='=' && LA16_2<='z')||LA16_2=='|'||(LA16_2>='~' && LA16_2<='\uD7FF')||(LA16_2>='\uE000' && LA16_2<='\uFFFD')) && (( !inElem || inQuotAttr ))) {s = 16;}

                        else if ( (LA16_2=='\"') && ( !inElem )) {s = 19;}

                        else s = 18;

                         
                        input.seek(index16_2);
                        if ( s>=0 ) return s;
                        break;
                    case 31 : 
                        int LA16_14 = input.LA(1);

                         
                        int index16_14 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ( inQuotAttr ) ) {s = 39;}

                        else if ( ( inAposAttr ) ) {s = 40;}

                        else if ( ( inElem  ) ) {s = 41;}

                        else if ( ( !inElem ) ) {s = 19;}

                         
                        input.seek(index16_14);
                        if ( s>=0 ) return s;
                        break;
                    case 32 : 
                        int LA16_10 = input.LA(1);

                         
                        int index16_10 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA16_10=='{') && ( !inElem || inAposAttr || inQuotAttr )) {s = 29;}

                        else s = 30;

                         
                        input.seek(index16_10);
                        if ( s>=0 ) return s;
                        break;
                    case 33 : 
                        int LA16_28 = input.LA(1);

                         
                        int index16_28 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ( inQuotAttr ) ) {s = 39;}

                        else if ( ( inAposAttr ) ) {s = 40;}

                        else if ( ( inElem  ) ) {s = 52;}

                        else if ( ( !inElem ) ) {s = 19;}

                         
                        input.seek(index16_28);
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