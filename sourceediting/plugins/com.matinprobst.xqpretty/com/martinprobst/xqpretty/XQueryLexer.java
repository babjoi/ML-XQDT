// $ANTLR 3.1b1 XQuery.g 2008-04-23 19:38:07

  package com.martinprobst.xqpretty;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class XQueryLexer extends Lexer {
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

    // delegates
    // delegators

    public XQueryLexer() {;} 
    public XQueryLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public XQueryLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "XQuery.g"; }

    // $ANTLR start T__145
    public final void mT__145() throws RecognitionException {
        try {
            int _type = T__145;
            // XQuery.g:7:8: ( ':=' )
            // XQuery.g:7:10: ':='
            {
            match(":="); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T__145

    // $ANTLR start T__146
    public final void mT__146() throws RecognitionException {
        try {
            int _type = T__146;
            // XQuery.g:8:8: ( '$' )
            // XQuery.g:8:10: '$'
            {
            match('$'); 


            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T__146

    // $ANTLR start T__147
    public final void mT__147() throws RecognitionException {
        try {
            int _type = T__147;
            // XQuery.g:9:8: ( '+' )
            // XQuery.g:9:10: '+'
            {
            match('+'); 


            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T__147

    // $ANTLR start T__148
    public final void mT__148() throws RecognitionException {
        try {
            int _type = T__148;
            // XQuery.g:10:8: ( '-' )
            // XQuery.g:10:10: '-'
            {
            match('-'); 


            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T__148

    // $ANTLR start T__149
    public final void mT__149() throws RecognitionException {
        try {
            int _type = T__149;
            // XQuery.g:11:8: ( '*' )
            // XQuery.g:11:10: '*'
            {
            match('*'); 


            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T__149

    // $ANTLR start T__150
    public final void mT__150() throws RecognitionException {
        try {
            int _type = T__150;
            // XQuery.g:12:8: ( '|' )
            // XQuery.g:12:10: '|'
            {
            match('|'); 


            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T__150

    // $ANTLR start T__151
    public final void mT__151() throws RecognitionException {
        try {
            int _type = T__151;
            // XQuery.g:13:8: ( '!=' )
            // XQuery.g:13:10: '!='
            {
            match("!="); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T__151

    // $ANTLR start T__152
    public final void mT__152() throws RecognitionException {
        try {
            int _type = T__152;
            // XQuery.g:14:8: ( '<=' )
            // XQuery.g:14:10: '<='
            {
            match("<="); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T__152

    // $ANTLR start T__153
    public final void mT__153() throws RecognitionException {
        try {
            int _type = T__153;
            // XQuery.g:15:8: ( '>=' )
            // XQuery.g:15:10: '>='
            {
            match(">="); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T__153

    // $ANTLR start T__154
    public final void mT__154() throws RecognitionException {
        try {
            int _type = T__154;
            // XQuery.g:16:8: ( '<<' )
            // XQuery.g:16:10: '<<'
            {
            match("<<"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T__154

    // $ANTLR start T__155
    public final void mT__155() throws RecognitionException {
        try {
            int _type = T__155;
            // XQuery.g:17:8: ( '>>' )
            // XQuery.g:17:10: '>>'
            {
            match(">>"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T__155

    // $ANTLR start T__156
    public final void mT__156() throws RecognitionException {
        try {
            int _type = T__156;
            // XQuery.g:18:8: ( '::' )
            // XQuery.g:18:10: '::'
            {
            match("::"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T__156

    // $ANTLR start T__157
    public final void mT__157() throws RecognitionException {
        try {
            int _type = T__157;
            // XQuery.g:19:8: ( '@' )
            // XQuery.g:19:10: '@'
            {
            match('@'); 


            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T__157

    // $ANTLR start T__158
    public final void mT__158() throws RecognitionException {
        try {
            int _type = T__158;
            // XQuery.g:20:8: ( '..' )
            // XQuery.g:20:10: '..'
            {
            match(".."); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T__158

    // $ANTLR start T__159
    public final void mT__159() throws RecognitionException {
        try {
            int _type = T__159;
            // XQuery.g:21:8: ( '.' )
            // XQuery.g:21:10: '.'
            {
            match('.'); 


            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T__159

    // $ANTLR start T__160
    public final void mT__160() throws RecognitionException {
        try {
            int _type = T__160;
            // XQuery.g:22:8: ( '?' )
            // XQuery.g:22:10: '?'
            {
            match('?'); 


            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T__160

    // $ANTLR start ANCESTOR
    public final void mANCESTOR() throws RecognitionException {
        try {
            int _type = ANCESTOR;
            // XQuery.g:224:10: ( 'ancestor' )
            // XQuery.g:224:12: 'ancestor'
            {
            match("ancestor"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end ANCESTOR

    // $ANTLR start ANCESTOR_OR_SELF
    public final void mANCESTOR_OR_SELF() throws RecognitionException {
        try {
            int _type = ANCESTOR_OR_SELF;
            // XQuery.g:226:3: ( 'ancestor-or-self' )
            // XQuery.g:226:5: 'ancestor-or-self'
            {
            match("ancestor-or-self"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end ANCESTOR_OR_SELF

    // $ANTLR start AND
    public final void mAND() throws RecognitionException {
        try {
            int _type = AND;
            // XQuery.g:227:6: ( 'and' )
            // XQuery.g:227:8: 'and'
            {
            match("and"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end AND

    // $ANTLR start AS
    public final void mAS() throws RecognitionException {
        try {
            int _type = AS;
            // XQuery.g:228:5: ( 'as' )
            // XQuery.g:228:7: 'as'
            {
            match("as"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end AS

    // $ANTLR start ASCENDING
    public final void mASCENDING() throws RecognitionException {
        try {
            int _type = ASCENDING;
            // XQuery.g:229:11: ( 'ascending' )
            // XQuery.g:229:13: 'ascending'
            {
            match("ascending"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end ASCENDING

    // $ANTLR start AT
    public final void mAT() throws RecognitionException {
        try {
            int _type = AT;
            // XQuery.g:230:5: ( 'at' )
            // XQuery.g:230:7: 'at'
            {
            match("at"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end AT

    // $ANTLR start ATTRIBUTE
    public final void mATTRIBUTE() throws RecognitionException {
        try {
            int _type = ATTRIBUTE;
            // XQuery.g:231:11: ( 'attribute' )
            // XQuery.g:231:13: 'attribute'
            {
            match("attribute"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end ATTRIBUTE

    // $ANTLR start BASE_URI
    public final void mBASE_URI() throws RecognitionException {
        try {
            int _type = BASE_URI;
            // XQuery.g:232:10: ( 'base-uri' )
            // XQuery.g:232:12: 'base-uri'
            {
            match("base-uri"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end BASE_URI

    // $ANTLR start BOUNDARY_SPACE
    public final void mBOUNDARY_SPACE() throws RecognitionException {
        try {
            int _type = BOUNDARY_SPACE;
            // XQuery.g:233:16: ( 'boundary-space' )
            // XQuery.g:233:18: 'boundary-space'
            {
            match("boundary-space"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end BOUNDARY_SPACE

    // $ANTLR start BY
    public final void mBY() throws RecognitionException {
        try {
            int _type = BY;
            // XQuery.g:234:5: ( 'by' )
            // XQuery.g:234:7: 'by'
            {
            match("by"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end BY

    // $ANTLR start CASE
    public final void mCASE() throws RecognitionException {
        try {
            int _type = CASE;
            // XQuery.g:235:7: ( 'case' )
            // XQuery.g:235:9: 'case'
            {
            match("case"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end CASE

    // $ANTLR start CAST
    public final void mCAST() throws RecognitionException {
        try {
            int _type = CAST;
            // XQuery.g:236:7: ( 'cast' )
            // XQuery.g:236:9: 'cast'
            {
            match("cast"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end CAST

    // $ANTLR start CASTABLE
    public final void mCASTABLE() throws RecognitionException {
        try {
            int _type = CASTABLE;
            // XQuery.g:237:10: ( 'castable' )
            // XQuery.g:237:12: 'castable'
            {
            match("castable"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end CASTABLE

    // $ANTLR start CHILD
    public final void mCHILD() throws RecognitionException {
        try {
            int _type = CHILD;
            // XQuery.g:238:8: ( 'child' )
            // XQuery.g:238:10: 'child'
            {
            match("child"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end CHILD

    // $ANTLR start COLLATION
    public final void mCOLLATION() throws RecognitionException {
        try {
            int _type = COLLATION;
            // XQuery.g:239:11: ( 'collation' )
            // XQuery.g:239:13: 'collation'
            {
            match("collation"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end COLLATION

    // $ANTLR start COMMENT
    public final void mCOMMENT() throws RecognitionException {
        try {
            int _type = COMMENT;
            // XQuery.g:240:10: ( 'comment' )
            // XQuery.g:240:12: 'comment'
            {
            match("comment"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end COMMENT

    // $ANTLR start CONSTRUCTION
    public final void mCONSTRUCTION() throws RecognitionException {
        try {
            int _type = CONSTRUCTION;
            // XQuery.g:241:14: ( 'construction' )
            // XQuery.g:241:16: 'construction'
            {
            match("construction"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end CONSTRUCTION

    // $ANTLR start COPY_NAMESPACES
    public final void mCOPY_NAMESPACES() throws RecognitionException {
        try {
            int _type = COPY_NAMESPACES;
            // XQuery.g:242:17: ( 'copy-namespaces' )
            // XQuery.g:242:19: 'copy-namespaces'
            {
            match("copy-namespaces"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end COPY_NAMESPACES

    // $ANTLR start DECLARE
    public final void mDECLARE() throws RecognitionException {
        try {
            int _type = DECLARE;
            // XQuery.g:243:10: ( 'declare' )
            // XQuery.g:243:12: 'declare'
            {
            match("declare"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end DECLARE

    // $ANTLR start DEFAULT
    public final void mDEFAULT() throws RecognitionException {
        try {
            int _type = DEFAULT;
            // XQuery.g:244:10: ( 'default' )
            // XQuery.g:244:12: 'default'
            {
            match("default"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end DEFAULT

    // $ANTLR start DESCENDANT
    public final void mDESCENDANT() throws RecognitionException {
        try {
            int _type = DESCENDANT;
            // XQuery.g:245:12: ( 'descendant' )
            // XQuery.g:245:14: 'descendant'
            {
            match("descendant"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end DESCENDANT

    // $ANTLR start DESCENDANT_OR_SELF
    public final void mDESCENDANT_OR_SELF() throws RecognitionException {
        try {
            int _type = DESCENDANT_OR_SELF;
            // XQuery.g:247:3: ( 'descendant-or-self' )
            // XQuery.g:247:5: 'descendant-or-self'
            {
            match("descendant-or-self"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end DESCENDANT_OR_SELF

    // $ANTLR start DESCENDING
    public final void mDESCENDING() throws RecognitionException {
        try {
            int _type = DESCENDING;
            // XQuery.g:248:12: ( 'descending' )
            // XQuery.g:248:14: 'descending'
            {
            match("descending"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end DESCENDING

    // $ANTLR start DIV
    public final void mDIV() throws RecognitionException {
        try {
            int _type = DIV;
            // XQuery.g:249:6: ( 'div' )
            // XQuery.g:249:8: 'div'
            {
            match("div"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end DIV

    // $ANTLR start DOCUMENT
    public final void mDOCUMENT() throws RecognitionException {
        try {
            int _type = DOCUMENT;
            // XQuery.g:250:10: ( 'document' )
            // XQuery.g:250:12: 'document'
            {
            match("document"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end DOCUMENT

    // $ANTLR start DOCUMENT_NODE
    public final void mDOCUMENT_NODE() throws RecognitionException {
        try {
            int _type = DOCUMENT_NODE;
            // XQuery.g:251:15: ( 'document-node' )
            // XQuery.g:251:17: 'document-node'
            {
            match("document-node"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end DOCUMENT_NODE

    // $ANTLR start ELEMENT
    public final void mELEMENT() throws RecognitionException {
        try {
            int _type = ELEMENT;
            // XQuery.g:252:10: ( 'element' )
            // XQuery.g:252:12: 'element'
            {
            match("element"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end ELEMENT

    // $ANTLR start ELSE
    public final void mELSE() throws RecognitionException {
        try {
            int _type = ELSE;
            // XQuery.g:253:7: ( 'else' )
            // XQuery.g:253:9: 'else'
            {
            match("else"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end ELSE

    // $ANTLR start EMPTY
    public final void mEMPTY() throws RecognitionException {
        try {
            int _type = EMPTY;
            // XQuery.g:254:8: ( 'empty' )
            // XQuery.g:254:10: 'empty'
            {
            match("empty"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end EMPTY

    // $ANTLR start EMPTY_SEQUENCE
    public final void mEMPTY_SEQUENCE() throws RecognitionException {
        try {
            int _type = EMPTY_SEQUENCE;
            // XQuery.g:255:16: ( 'empty-sequence' )
            // XQuery.g:255:18: 'empty-sequence'
            {
            match("empty-sequence"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end EMPTY_SEQUENCE

    // $ANTLR start ENCODING
    public final void mENCODING() throws RecognitionException {
        try {
            int _type = ENCODING;
            // XQuery.g:256:10: ( 'encoding' )
            // XQuery.g:256:12: 'encoding'
            {
            match("encoding"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end ENCODING

    // $ANTLR start EQ
    public final void mEQ() throws RecognitionException {
        try {
            int _type = EQ;
            // XQuery.g:257:5: ( 'eq' )
            // XQuery.g:257:7: 'eq'
            {
            match("eq"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end EQ

    // $ANTLR start EVERY
    public final void mEVERY() throws RecognitionException {
        try {
            int _type = EVERY;
            // XQuery.g:258:8: ( 'every' )
            // XQuery.g:258:10: 'every'
            {
            match("every"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end EVERY

    // $ANTLR start EXCEPT
    public final void mEXCEPT() throws RecognitionException {
        try {
            int _type = EXCEPT;
            // XQuery.g:259:9: ( 'except' )
            // XQuery.g:259:11: 'except'
            {
            match("except"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end EXCEPT

    // $ANTLR start EXTERNAL
    public final void mEXTERNAL() throws RecognitionException {
        try {
            int _type = EXTERNAL;
            // XQuery.g:260:10: ( 'external' )
            // XQuery.g:260:12: 'external'
            {
            match("external"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end EXTERNAL

    // $ANTLR start FOLLOWING
    public final void mFOLLOWING() throws RecognitionException {
        try {
            int _type = FOLLOWING;
            // XQuery.g:261:11: ( 'following' )
            // XQuery.g:261:13: 'following'
            {
            match("following"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end FOLLOWING

    // $ANTLR start FOLLOWING_SIBLING
    public final void mFOLLOWING_SIBLING() throws RecognitionException {
        try {
            int _type = FOLLOWING_SIBLING;
            // XQuery.g:263:3: ( 'following-sibling' )
            // XQuery.g:263:5: 'following-sibling'
            {
            match("following-sibling"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end FOLLOWING_SIBLING

    // $ANTLR start FOR
    public final void mFOR() throws RecognitionException {
        try {
            int _type = FOR;
            // XQuery.g:264:6: ( 'for' )
            // XQuery.g:264:8: 'for'
            {
            match("for"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end FOR

    // $ANTLR start FUNCTION
    public final void mFUNCTION() throws RecognitionException {
        try {
            int _type = FUNCTION;
            // XQuery.g:265:10: ( 'function' )
            // XQuery.g:265:12: 'function'
            {
            match("function"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end FUNCTION

    // $ANTLR start GE
    public final void mGE() throws RecognitionException {
        try {
            int _type = GE;
            // XQuery.g:266:5: ( 'ge' )
            // XQuery.g:266:7: 'ge'
            {
            match("ge"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end GE

    // $ANTLR start GREATEST
    public final void mGREATEST() throws RecognitionException {
        try {
            int _type = GREATEST;
            // XQuery.g:267:10: ( 'greatest' )
            // XQuery.g:267:12: 'greatest'
            {
            match("greatest"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end GREATEST

    // $ANTLR start GT
    public final void mGT() throws RecognitionException {
        try {
            int _type = GT;
            // XQuery.g:268:5: ( 'gt' )
            // XQuery.g:268:7: 'gt'
            {
            match("gt"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end GT

    // $ANTLR start IDIV
    public final void mIDIV() throws RecognitionException {
        try {
            int _type = IDIV;
            // XQuery.g:269:7: ( 'idiv' )
            // XQuery.g:269:9: 'idiv'
            {
            match("idiv"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end IDIV

    // $ANTLR start IF
    public final void mIF() throws RecognitionException {
        try {
            int _type = IF;
            // XQuery.g:270:5: ( 'if' )
            // XQuery.g:270:7: 'if'
            {
            match("if"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end IF

    // $ANTLR start IMPORT
    public final void mIMPORT() throws RecognitionException {
        try {
            int _type = IMPORT;
            // XQuery.g:271:9: ( 'import' )
            // XQuery.g:271:11: 'import'
            {
            match("import"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end IMPORT

    // $ANTLR start IN
    public final void mIN() throws RecognitionException {
        try {
            int _type = IN;
            // XQuery.g:272:5: ( 'in' )
            // XQuery.g:272:7: 'in'
            {
            match("in"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end IN

    // $ANTLR start INHERIT
    public final void mINHERIT() throws RecognitionException {
        try {
            int _type = INHERIT;
            // XQuery.g:273:10: ( 'inherit' )
            // XQuery.g:273:12: 'inherit'
            {
            match("inherit"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end INHERIT

    // $ANTLR start INSTANCE
    public final void mINSTANCE() throws RecognitionException {
        try {
            int _type = INSTANCE;
            // XQuery.g:274:10: ( 'instance' )
            // XQuery.g:274:12: 'instance'
            {
            match("instance"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end INSTANCE

    // $ANTLR start INTERSECT
    public final void mINTERSECT() throws RecognitionException {
        try {
            int _type = INTERSECT;
            // XQuery.g:275:11: ( 'intersect' )
            // XQuery.g:275:13: 'intersect'
            {
            match("intersect"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end INTERSECT

    // $ANTLR start IS
    public final void mIS() throws RecognitionException {
        try {
            int _type = IS;
            // XQuery.g:276:5: ( 'is' )
            // XQuery.g:276:7: 'is'
            {
            match("is"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end IS

    // $ANTLR start ITEM
    public final void mITEM() throws RecognitionException {
        try {
            int _type = ITEM;
            // XQuery.g:277:7: ( 'item' )
            // XQuery.g:277:9: 'item'
            {
            match("item"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end ITEM

    // $ANTLR start LAX
    public final void mLAX() throws RecognitionException {
        try {
            int _type = LAX;
            // XQuery.g:278:6: ( 'lax' )
            // XQuery.g:278:8: 'lax'
            {
            match("lax"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end LAX

    // $ANTLR start LE
    public final void mLE() throws RecognitionException {
        try {
            int _type = LE;
            // XQuery.g:279:5: ( 'le' )
            // XQuery.g:279:7: 'le'
            {
            match("le"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end LE

    // $ANTLR start LEAST
    public final void mLEAST() throws RecognitionException {
        try {
            int _type = LEAST;
            // XQuery.g:280:8: ( 'least' )
            // XQuery.g:280:10: 'least'
            {
            match("least"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end LEAST

    // $ANTLR start LET
    public final void mLET() throws RecognitionException {
        try {
            int _type = LET;
            // XQuery.g:281:6: ( 'let' )
            // XQuery.g:281:8: 'let'
            {
            match("let"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end LET

    // $ANTLR start LT
    public final void mLT() throws RecognitionException {
        try {
            int _type = LT;
            // XQuery.g:282:5: ( 'lt' )
            // XQuery.g:282:7: 'lt'
            {
            match("lt"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end LT

    // $ANTLR start MOD
    public final void mMOD() throws RecognitionException {
        try {
            int _type = MOD;
            // XQuery.g:283:6: ( 'mod' )
            // XQuery.g:283:8: 'mod'
            {
            match("mod"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end MOD

    // $ANTLR start MODULE
    public final void mMODULE() throws RecognitionException {
        try {
            int _type = MODULE;
            // XQuery.g:284:9: ( 'module' )
            // XQuery.g:284:11: 'module'
            {
            match("module"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end MODULE

    // $ANTLR start NAMESPACE
    public final void mNAMESPACE() throws RecognitionException {
        try {
            int _type = NAMESPACE;
            // XQuery.g:285:11: ( 'namespace' )
            // XQuery.g:285:13: 'namespace'
            {
            match("namespace"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end NAMESPACE

    // $ANTLR start NE
    public final void mNE() throws RecognitionException {
        try {
            int _type = NE;
            // XQuery.g:286:5: ( 'ne' )
            // XQuery.g:286:7: 'ne'
            {
            match("ne"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end NE

    // $ANTLR start NO_INHERIT
    public final void mNO_INHERIT() throws RecognitionException {
        try {
            int _type = NO_INHERIT;
            // XQuery.g:287:12: ( 'no-inherit' )
            // XQuery.g:287:14: 'no-inherit'
            {
            match("no-inherit"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end NO_INHERIT

    // $ANTLR start NO_PRESERVE
    public final void mNO_PRESERVE() throws RecognitionException {
        try {
            int _type = NO_PRESERVE;
            // XQuery.g:288:13: ( 'no-preserve' )
            // XQuery.g:288:15: 'no-preserve'
            {
            match("no-preserve"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end NO_PRESERVE

    // $ANTLR start NODE
    public final void mNODE() throws RecognitionException {
        try {
            int _type = NODE;
            // XQuery.g:289:7: ( 'node' )
            // XQuery.g:289:9: 'node'
            {
            match("node"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end NODE

    // $ANTLR start OF
    public final void mOF() throws RecognitionException {
        try {
            int _type = OF;
            // XQuery.g:290:5: ( 'of' )
            // XQuery.g:290:7: 'of'
            {
            match("of"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end OF

    // $ANTLR start OPTION
    public final void mOPTION() throws RecognitionException {
        try {
            int _type = OPTION;
            // XQuery.g:291:9: ( 'option' )
            // XQuery.g:291:11: 'option'
            {
            match("option"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end OPTION

    // $ANTLR start OR
    public final void mOR() throws RecognitionException {
        try {
            int _type = OR;
            // XQuery.g:292:5: ( 'or' )
            // XQuery.g:292:7: 'or'
            {
            match("or"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end OR

    // $ANTLR start ORDER
    public final void mORDER() throws RecognitionException {
        try {
            int _type = ORDER;
            // XQuery.g:293:8: ( 'order' )
            // XQuery.g:293:10: 'order'
            {
            match("order"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end ORDER

    // $ANTLR start ORDERED
    public final void mORDERED() throws RecognitionException {
        try {
            int _type = ORDERED;
            // XQuery.g:294:10: ( 'ordered' )
            // XQuery.g:294:12: 'ordered'
            {
            match("ordered"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end ORDERED

    // $ANTLR start ORDERING
    public final void mORDERING() throws RecognitionException {
        try {
            int _type = ORDERING;
            // XQuery.g:295:10: ( 'ordering' )
            // XQuery.g:295:12: 'ordering'
            {
            match("ordering"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end ORDERING

    // $ANTLR start PARENT
    public final void mPARENT() throws RecognitionException {
        try {
            int _type = PARENT;
            // XQuery.g:296:9: ( 'parent' )
            // XQuery.g:296:11: 'parent'
            {
            match("parent"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end PARENT

    // $ANTLR start PRECEDING
    public final void mPRECEDING() throws RecognitionException {
        try {
            int _type = PRECEDING;
            // XQuery.g:297:11: ( 'preceding' )
            // XQuery.g:297:13: 'preceding'
            {
            match("preceding"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end PRECEDING

    // $ANTLR start PRECEDING_SIBLING
    public final void mPRECEDING_SIBLING() throws RecognitionException {
        try {
            int _type = PRECEDING_SIBLING;
            // XQuery.g:299:3: ( 'preceding-sibling' )
            // XQuery.g:299:5: 'preceding-sibling'
            {
            match("preceding-sibling"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end PRECEDING_SIBLING

    // $ANTLR start PRESERVE
    public final void mPRESERVE() throws RecognitionException {
        try {
            int _type = PRESERVE;
            // XQuery.g:300:10: ( 'preserve' )
            // XQuery.g:300:12: 'preserve'
            {
            match("preserve"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end PRESERVE

    // $ANTLR start PROCESSING_INSTRUCTION
    public final void mPROCESSING_INSTRUCTION() throws RecognitionException {
        try {
            int _type = PROCESSING_INSTRUCTION;
            // XQuery.g:302:3: ( 'processing-instruction' )
            // XQuery.g:302:5: 'processing-instruction'
            {
            match("processing-instruction"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end PROCESSING_INSTRUCTION

    // $ANTLR start RETURN
    public final void mRETURN() throws RecognitionException {
        try {
            int _type = RETURN;
            // XQuery.g:303:9: ( 'return' )
            // XQuery.g:303:11: 'return'
            {
            match("return"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RETURN

    // $ANTLR start SATISFIES
    public final void mSATISFIES() throws RecognitionException {
        try {
            int _type = SATISFIES;
            // XQuery.g:304:11: ( 'satisfies' )
            // XQuery.g:304:13: 'satisfies'
            {
            match("satisfies"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end SATISFIES

    // $ANTLR start SCHEMA
    public final void mSCHEMA() throws RecognitionException {
        try {
            int _type = SCHEMA;
            // XQuery.g:305:9: ( 'schema' )
            // XQuery.g:305:11: 'schema'
            {
            match("schema"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end SCHEMA

    // $ANTLR start SCHEMA_ATTRIBUTE
    public final void mSCHEMA_ATTRIBUTE() throws RecognitionException {
        try {
            int _type = SCHEMA_ATTRIBUTE;
            // XQuery.g:307:3: ( 'schema-attribute' )
            // XQuery.g:307:5: 'schema-attribute'
            {
            match("schema-attribute"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end SCHEMA_ATTRIBUTE

    // $ANTLR start SCHEMA_ELEMENT
    public final void mSCHEMA_ELEMENT() throws RecognitionException {
        try {
            int _type = SCHEMA_ELEMENT;
            // XQuery.g:308:16: ( 'schema-element' )
            // XQuery.g:308:18: 'schema-element'
            {
            match("schema-element"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end SCHEMA_ELEMENT

    // $ANTLR start SELF
    public final void mSELF() throws RecognitionException {
        try {
            int _type = SELF;
            // XQuery.g:309:7: ( 'self' )
            // XQuery.g:309:9: 'self'
            {
            match("self"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end SELF

    // $ANTLR start SOME
    public final void mSOME() throws RecognitionException {
        try {
            int _type = SOME;
            // XQuery.g:310:7: ( 'some' )
            // XQuery.g:310:9: 'some'
            {
            match("some"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end SOME

    // $ANTLR start STABLE
    public final void mSTABLE() throws RecognitionException {
        try {
            int _type = STABLE;
            // XQuery.g:311:9: ( 'stable' )
            // XQuery.g:311:11: 'stable'
            {
            match("stable"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end STABLE

    // $ANTLR start STRICT
    public final void mSTRICT() throws RecognitionException {
        try {
            int _type = STRICT;
            // XQuery.g:312:9: ( 'strict' )
            // XQuery.g:312:11: 'strict'
            {
            match("strict"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end STRICT

    // $ANTLR start STRIP
    public final void mSTRIP() throws RecognitionException {
        try {
            int _type = STRIP;
            // XQuery.g:313:8: ( 'strip' )
            // XQuery.g:313:10: 'strip'
            {
            match("strip"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end STRIP

    // $ANTLR start TEXT
    public final void mTEXT() throws RecognitionException {
        try {
            int _type = TEXT;
            // XQuery.g:314:7: ( 'text' )
            // XQuery.g:314:9: 'text'
            {
            match("text"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end TEXT

    // $ANTLR start THEN
    public final void mTHEN() throws RecognitionException {
        try {
            int _type = THEN;
            // XQuery.g:315:7: ( 'then' )
            // XQuery.g:315:9: 'then'
            {
            match("then"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end THEN

    // $ANTLR start TO
    public final void mTO() throws RecognitionException {
        try {
            int _type = TO;
            // XQuery.g:316:5: ( 'to' )
            // XQuery.g:316:7: 'to'
            {
            match("to"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end TO

    // $ANTLR start TREAT
    public final void mTREAT() throws RecognitionException {
        try {
            int _type = TREAT;
            // XQuery.g:317:8: ( 'treat' )
            // XQuery.g:317:10: 'treat'
            {
            match("treat"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end TREAT

    // $ANTLR start TYPESWITCH
    public final void mTYPESWITCH() throws RecognitionException {
        try {
            int _type = TYPESWITCH;
            // XQuery.g:318:12: ( 'typeswitch' )
            // XQuery.g:318:14: 'typeswitch'
            {
            match("typeswitch"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end TYPESWITCH

    // $ANTLR start UNION
    public final void mUNION() throws RecognitionException {
        try {
            int _type = UNION;
            // XQuery.g:319:8: ( 'union' )
            // XQuery.g:319:10: 'union'
            {
            match("union"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end UNION

    // $ANTLR start UNORDERED
    public final void mUNORDERED() throws RecognitionException {
        try {
            int _type = UNORDERED;
            // XQuery.g:320:11: ( 'unordered' )
            // XQuery.g:320:13: 'unordered'
            {
            match("unordered"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end UNORDERED

    // $ANTLR start VALIDATE
    public final void mVALIDATE() throws RecognitionException {
        try {
            int _type = VALIDATE;
            // XQuery.g:321:10: ( 'validate' )
            // XQuery.g:321:12: 'validate'
            {
            match("validate"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end VALIDATE

    // $ANTLR start VARIABLE
    public final void mVARIABLE() throws RecognitionException {
        try {
            int _type = VARIABLE;
            // XQuery.g:322:10: ( 'variable' )
            // XQuery.g:322:12: 'variable'
            {
            match("variable"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end VARIABLE

    // $ANTLR start VERSION
    public final void mVERSION() throws RecognitionException {
        try {
            int _type = VERSION;
            // XQuery.g:323:10: ( 'version' )
            // XQuery.g:323:12: 'version'
            {
            match("version"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end VERSION

    // $ANTLR start WHERE
    public final void mWHERE() throws RecognitionException {
        try {
            int _type = WHERE;
            // XQuery.g:324:8: ( 'where' )
            // XQuery.g:324:10: 'where'
            {
            match("where"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end WHERE

    // $ANTLR start XQUERY
    public final void mXQUERY() throws RecognitionException {
        try {
            int _type = XQUERY;
            // XQuery.g:325:9: ( 'xquery' )
            // XQuery.g:325:11: 'xquery'
            {
            match("xquery"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end XQUERY

    // $ANTLR start NCName
    public final void mNCName() throws RecognitionException {
        try {
            int _type = NCName;
            // XQuery.g:328:9: ( NCNameStartChar ( NCNameChar )* )
            // XQuery.g:328:11: NCNameStartChar ( NCNameChar )*
            {
            mNCNameStartChar(); 
            // XQuery.g:328:27: ( NCNameChar )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>='-' && LA1_0<='.')||(LA1_0>='0' && LA1_0<='9')||(LA1_0>='A' && LA1_0<='Z')||LA1_0=='_'||(LA1_0>='a' && LA1_0<='z')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // XQuery.g:328:27: NCNameChar
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
        }
        finally {
        }
    }
    // $ANTLR end NCName

    // $ANTLR start NCNameStartChar
    public final void mNCNameStartChar() throws RecognitionException {
        try {
            // XQuery.g:331:3: ( Letter | '_' )
            // XQuery.g:
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
            // XQuery.g:333:3: ( Letter | XMLDigit | '.' | '-' | '_' )
            // XQuery.g:
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
            // XQuery.g:336:3: ( 'a' .. 'z' | 'A' .. 'Z' )
            // XQuery.g:
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
            // XQuery.g:338:3: ( '0' .. '9' )
            // XQuery.g:338:5: '0' .. '9'
            {
            matchRange('0','9'); 


            }

        }
        finally {
        }
    }
    // $ANTLR end XMLDigit

    // $ANTLR start DirCommentConstructor
    public final void mDirCommentConstructor() throws RecognitionException {
        try {
            int _type = DirCommentConstructor;
            // XQuery.g:352:3: ( '<!--' ( options {greedy=false; } : ( . )* ) '-->' )
            // XQuery.g:352:5: '<!--' ( options {greedy=false; } : ( . )* ) '-->'
            {
            match("<!--"); 

            // XQuery.g:352:12: ( options {greedy=false; } : ( . )* )
            // XQuery.g:352:39: ( . )*
            {
            // XQuery.g:352:39: ( . )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0=='-') ) {
                    int LA2_1 = input.LA(2);

                    if ( (LA2_1=='-') ) {
                        int LA2_3 = input.LA(3);

                        if ( (LA2_3=='>') ) {
                            alt2=2;
                        }
                        else if ( ((LA2_3>='\u0000' && LA2_3<='=')||(LA2_3>='?' && LA2_3<='\uFFFE')) ) {
                            alt2=1;
                        }


                    }
                    else if ( ((LA2_1>='\u0000' && LA2_1<=',')||(LA2_1>='.' && LA2_1<='\uFFFE')) ) {
                        alt2=1;
                    }


                }
                else if ( ((LA2_0>='\u0000' && LA2_0<=',')||(LA2_0>='.' && LA2_0<='\uFFFE')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // XQuery.g:352:39: .
            	    {
            	    matchAny(); 


            	    }
            	    break;

            	default :
            	    break loop2;
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
            // XQuery.g:354:3: ( '<?' ( SUnprotected )? NCName ( SUnprotected ( options {greedy=false; } : ( . )* ) )? '?>' )
            // XQuery.g:354:5: '<?' ( SUnprotected )? NCName ( SUnprotected ( options {greedy=false; } : ( . )* ) )? '?>'
            {
            match("<?"); 

            // XQuery.g:354:10: ( SUnprotected )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( ((LA3_0>='\t' && LA3_0<='\n')||LA3_0=='\r'||LA3_0==' ') ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // XQuery.g:354:10: SUnprotected
                    {
                    mSUnprotected(); 


                    }
                    break;

            }

            mNCName(); 
            // XQuery.g:354:31: ( SUnprotected ( options {greedy=false; } : ( . )* ) )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( ((LA5_0>='\t' && LA5_0<='\n')||LA5_0=='\r'||LA5_0==' ') ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // XQuery.g:354:32: SUnprotected ( options {greedy=false; } : ( . )* )
                    {
                    mSUnprotected(); 
                    // XQuery.g:354:45: ( options {greedy=false; } : ( . )* )
                    // XQuery.g:354:72: ( . )*
                    {
                    // XQuery.g:354:72: ( . )*
                    loop4:
                    do {
                        int alt4=2;
                        int LA4_0 = input.LA(1);

                        if ( (LA4_0=='?') ) {
                            int LA4_1 = input.LA(2);

                            if ( (LA4_1=='>') ) {
                                alt4=2;
                            }
                            else if ( ((LA4_1>='\u0000' && LA4_1<='=')||(LA4_1>='?' && LA4_1<='\uFFFE')) ) {
                                alt4=1;
                            }


                        }
                        else if ( ((LA4_0>='\u0000' && LA4_0<='>')||(LA4_0>='@' && LA4_0<='\uFFFE')) ) {
                            alt4=1;
                        }


                        switch (alt4) {
                    	case 1 :
                    	    // XQuery.g:354:72: .
                    	    {
                    	    matchAny(); 


                    	    }
                    	    break;

                    	default :
                    	    break loop4;
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

    // $ANTLR start CDataSection
    public final void mCDataSection() throws RecognitionException {
        try {
            int _type = CDataSection;
            // XQuery.g:355:14: ( '<![CDATA[' ( options {greedy=false; } : ( . )* ) ']]>' )
            // XQuery.g:355:16: '<![CDATA[' ( options {greedy=false; } : ( . )* ) ']]>'
            {
            match("<![CDATA["); 

            // XQuery.g:355:28: ( options {greedy=false; } : ( . )* )
            // XQuery.g:355:55: ( . )*
            {
            // XQuery.g:355:55: ( . )*
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
                        else if ( ((LA6_3>='\u0000' && LA6_3<='=')||(LA6_3>='?' && LA6_3<='\uFFFE')) ) {
                            alt6=1;
                        }


                    }
                    else if ( ((LA6_1>='\u0000' && LA6_1<='\\')||(LA6_1>='^' && LA6_1<='\uFFFE')) ) {
                        alt6=1;
                    }


                }
                else if ( ((LA6_0>='\u0000' && LA6_0<='\\')||(LA6_0>='^' && LA6_0<='\uFFFE')) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // XQuery.g:355:55: .
            	    {
            	    matchAny(); 


            	    }
            	    break;

            	default :
            	    break loop6;
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

    // $ANTLR start Pragma
    public final void mPragma() throws RecognitionException {
        try {
            int _type = Pragma;
            // XQuery.g:356:9: ( '(#' ( SUnprotected )? NCName ( ':' NCName )? ( SUnprotected ( options {greedy=false; } : . )* )? '#)' )
            // XQuery.g:356:11: '(#' ( SUnprotected )? NCName ( ':' NCName )? ( SUnprotected ( options {greedy=false; } : . )* )? '#)'
            {
            match("(#"); 

            // XQuery.g:356:16: ( SUnprotected )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( ((LA7_0>='\t' && LA7_0<='\n')||LA7_0=='\r'||LA7_0==' ') ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // XQuery.g:356:16: SUnprotected
                    {
                    mSUnprotected(); 


                    }
                    break;

            }

            mNCName(); 
            // XQuery.g:356:37: ( ':' NCName )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==':') ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // XQuery.g:356:38: ':' NCName
                    {
                    match(':'); 
                    mNCName(); 


                    }
                    break;

            }

            // XQuery.g:356:51: ( SUnprotected ( options {greedy=false; } : . )* )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( ((LA10_0>='\t' && LA10_0<='\n')||LA10_0=='\r'||LA10_0==' ') ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // XQuery.g:356:52: SUnprotected ( options {greedy=false; } : . )*
                    {
                    mSUnprotected(); 
                    // XQuery.g:356:65: ( options {greedy=false; } : . )*
                    loop9:
                    do {
                        int alt9=2;
                        int LA9_0 = input.LA(1);

                        if ( (LA9_0=='#') ) {
                            int LA9_1 = input.LA(2);

                            if ( (LA9_1==')') ) {
                                alt9=2;
                            }
                            else if ( ((LA9_1>='\u0000' && LA9_1<='(')||(LA9_1>='*' && LA9_1<='\uFFFE')) ) {
                                alt9=1;
                            }


                        }
                        else if ( ((LA9_0>='\u0000' && LA9_0<='\"')||(LA9_0>='$' && LA9_0<='\uFFFE')) ) {
                            alt9=1;
                        }


                        switch (alt9) {
                    	case 1 :
                    	    // XQuery.g:356:92: .
                    	    {
                    	    matchAny(); 


                    	    }
                    	    break;

                    	default :
                    	    break loop9;
                        }
                    } while (true);



                    }
                    break;

            }

            match("#)"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end Pragma

    // $ANTLR start LCURLY
    public final void mLCURLY() throws RecognitionException {
        try {
            int _type = LCURLY;
            // XQuery.g:358:9: ( '{' )
            // XQuery.g:358:11: '{'
            {
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
            // XQuery.g:359:9: ( '}' )
            // XQuery.g:359:11: '}'
            {
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
            // XQuery.g:360:12: ( '<' )
            // XQuery.g:360:14: '<'
            {
            match('<'); 


            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end OPEN_ANGLE

    // $ANTLR start CLOSE_ANGLE
    public final void mCLOSE_ANGLE() throws RecognitionException {
        try {
            int _type = CLOSE_ANGLE;
            // XQuery.g:361:13: ( '>' )
            // XQuery.g:361:15: '>'
            {
            match('>'); 


            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end CLOSE_ANGLE

    // $ANTLR start Lit_EQ
    public final void mLit_EQ() throws RecognitionException {
        try {
            int _type = Lit_EQ;
            // XQuery.g:362:9: ( '=' )
            // XQuery.g:362:11: '='
            {
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
            // XQuery.g:363:15: ( '\"' )
            // XQuery.g:363:17: '\"'
            {
            match('\"'); 


            }

        }
        finally {
        }
    }
    // $ANTLR end QUOT

    // $ANTLR start APOS
    public final void mAPOS() throws RecognitionException {
        try {
            // XQuery.g:364:15: ( '\\'' )
            // XQuery.g:364:17: '\\''
            {
            match('\''); 


            }

        }
        finally {
        }
    }
    // $ANTLR end APOS

    // $ANTLR start SLASH_SLASH
    public final void mSLASH_SLASH() throws RecognitionException {
        try {
            int _type = SLASH_SLASH;
            // XQuery.g:366:13: ( '//' )
            // XQuery.g:366:15: '//'
            {
            match("//"); 



            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end SLASH_SLASH

    // $ANTLR start SLASH
    public final void mSLASH() throws RecognitionException {
        try {
            int _type = SLASH;
            // XQuery.g:367:8: ( '/' )
            // XQuery.g:367:10: '/'
            {
            match('/'); 


            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end SLASH

    // $ANTLR start LBRACKET
    public final void mLBRACKET() throws RecognitionException {
        try {
            int _type = LBRACKET;
            // XQuery.g:369:10: ( '[' )
            // XQuery.g:369:12: '['
            {
            match('['); 


            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end LBRACKET

    // $ANTLR start RBRACKET
    public final void mRBRACKET() throws RecognitionException {
        try {
            int _type = RBRACKET;
            // XQuery.g:370:10: ( ']' )
            // XQuery.g:370:12: ']'
            {
            match(']'); 


            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RBRACKET

    // $ANTLR start LPAREN
    public final void mLPAREN() throws RecognitionException {
        try {
            int _type = LPAREN;
            // XQuery.g:371:9: ( '(' )
            // XQuery.g:371:11: '('
            {
            match('('); 


            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end LPAREN

    // $ANTLR start RPAREN
    public final void mRPAREN() throws RecognitionException {
        try {
            int _type = RPAREN;
            // XQuery.g:372:9: ( ')' )
            // XQuery.g:372:11: ')'
            {
            match(')'); 


            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RPAREN

    // $ANTLR start COLON
    public final void mCOLON() throws RecognitionException {
        try {
            int _type = COLON;
            // XQuery.g:373:8: ( ':' )
            // XQuery.g:373:10: ':'
            {
            match(':'); 


            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end COLON

    // $ANTLR start COMMA
    public final void mCOMMA() throws RecognitionException {
        try {
            int _type = COMMA;
            // XQuery.g:374:8: ( ',' )
            // XQuery.g:374:10: ','
            {
            match(','); 


            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end COMMA

    // $ANTLR start SEMICOLON
    public final void mSEMICOLON() throws RecognitionException {
        try {
            int _type = SEMICOLON;
            // XQuery.g:375:11: ( ';' )
            // XQuery.g:375:13: ';'
            {
            match(';'); 


            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end SEMICOLON

    // $ANTLR start S
    public final void mS() throws RecognitionException {
        try {
            int _type = S;
            // XQuery.g:377:4: ( ( '\\t' | ' ' | '\\n' | '\\r' )+ )
            // XQuery.g:377:6: ( '\\t' | ' ' | '\\n' | '\\r' )+
            {
            // XQuery.g:377:6: ( '\\t' | ' ' | '\\n' | '\\r' )+
            int cnt11=0;
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( ((LA11_0>='\t' && LA11_0<='\n')||LA11_0=='\r'||LA11_0==' ') ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // XQuery.g:
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
            	    if ( cnt11 >= 1 ) break loop11;
                        EarlyExitException eee =
                            new EarlyExitException(11, input);
                        throw eee;
                }
                cnt11++;
            } while (true);

             state.channel = HIDDEN; 


            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end S

    // $ANTLR start SUnprotected
    public final void mSUnprotected() throws RecognitionException {
        try {
            // XQuery.g:379:3: ( ( '\\t' | ' ' | '\\n' | '\\r' )+ )
            // XQuery.g:379:5: ( '\\t' | ' ' | '\\n' | '\\r' )+
            {
            // XQuery.g:379:5: ( '\\t' | ' ' | '\\n' | '\\r' )+
            int cnt12=0;
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( ((LA12_0>='\t' && LA12_0<='\n')||LA12_0=='\r'||LA12_0==' ') ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // XQuery.g:
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
            	    if ( cnt12 >= 1 ) break loop12;
                        EarlyExitException eee =
                            new EarlyExitException(12, input);
                        throw eee;
                }
                cnt12++;
            } while (true);



            }

        }
        finally {
        }
    }
    // $ANTLR end SUnprotected

    // $ANTLR start XQ_COMMENT
    public final void mXQ_COMMENT() throws RecognitionException {
        try {
            int _type = XQ_COMMENT;
            // XQuery.g:381:3: ( '(:' ( options {greedy=false; } : XQ_COMMENT | . )* ':)' )
            // XQuery.g:381:5: '(:' ( options {greedy=false; } : XQ_COMMENT | . )* ':)'
            {
            match("(:"); 

            // XQuery.g:381:10: ( options {greedy=false; } : XQ_COMMENT | . )*
            loop13:
            do {
                int alt13=3;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==':') ) {
                    int LA13_1 = input.LA(2);

                    if ( (LA13_1==')') ) {
                        alt13=3;
                    }
                    else if ( ((LA13_1>='\u0000' && LA13_1<='(')||(LA13_1>='*' && LA13_1<='\uFFFE')) ) {
                        alt13=2;
                    }


                }
                else if ( (LA13_0=='(') ) {
                    int LA13_2 = input.LA(2);

                    if ( (LA13_2==':') ) {
                        alt13=1;
                    }
                    else if ( ((LA13_2>='\u0000' && LA13_2<='9')||(LA13_2>=';' && LA13_2<='\uFFFE')) ) {
                        alt13=2;
                    }


                }
                else if ( ((LA13_0>='\u0000' && LA13_0<='\'')||(LA13_0>=')' && LA13_0<='9')||(LA13_0>=';' && LA13_0<='\uFFFE')) ) {
                    alt13=2;
                }


                switch (alt13) {
            	case 1 :
            	    // XQuery.g:381:36: XQ_COMMENT
            	    {
            	    mXQ_COMMENT(); 


            	    }
            	    break;
            	case 2 :
            	    // XQuery.g:381:50: .
            	    {
            	    matchAny(); 


            	    }
            	    break;

            	default :
            	    break loop13;
                }
            } while (true);

            match(":)"); 

             state.channel = HIDDEN; 


            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end XQ_COMMENT

    // $ANTLR start StringLiteral
    public final void mStringLiteral() throws RecognitionException {
        try {
            int _type = StringLiteral;
            // XQuery.g:384:15: ( QUOT ( options {greedy=false; } : (~ ( '\"' | '&' ) | ESCAPE_QUOT | CharRef | PredefinedEntityRef )* ) QUOT | APOS ( options {greedy=false; } : (~ ( '\\'' | '&' ) | ESCAPE_APOS | CharRef | PredefinedEntityRef )* ) APOS )
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0=='\"') ) {
                alt16=1;
            }
            else if ( (LA16_0=='\'') ) {
                alt16=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 16, 0, input);

                throw nvae;
            }
            switch (alt16) {
                case 1 :
                    // XQuery.g:384:17: QUOT ( options {greedy=false; } : (~ ( '\"' | '&' ) | ESCAPE_QUOT | CharRef | PredefinedEntityRef )* ) QUOT
                    {
                    mQUOT(); 
                    // XQuery.g:384:22: ( options {greedy=false; } : (~ ( '\"' | '&' ) | ESCAPE_QUOT | CharRef | PredefinedEntityRef )* )
                    // XQuery.g:384:48: (~ ( '\"' | '&' ) | ESCAPE_QUOT | CharRef | PredefinedEntityRef )*
                    {
                    // XQuery.g:384:48: (~ ( '\"' | '&' ) | ESCAPE_QUOT | CharRef | PredefinedEntityRef )*
                    loop14:
                    do {
                        int alt14=5;
                        int LA14_0 = input.LA(1);

                        if ( (LA14_0=='\"') ) {
                            int LA14_1 = input.LA(2);

                            if ( (LA14_1=='\"') ) {
                                alt14=2;
                            }


                        }
                        else if ( ((LA14_0>='\u0000' && LA14_0<='!')||(LA14_0>='#' && LA14_0<='%')||(LA14_0>='\'' && LA14_0<='\uFFFE')) ) {
                            alt14=1;
                        }
                        else if ( (LA14_0=='&') ) {
                            int LA14_3 = input.LA(2);

                            if ( (LA14_3=='#') ) {
                                alt14=3;
                            }
                            else if ( (LA14_3=='a'||LA14_3=='g'||LA14_3=='l'||LA14_3=='q') ) {
                                alt14=4;
                            }


                        }


                        switch (alt14) {
                    	case 1 :
                    	    // XQuery.g:384:49: ~ ( '\"' | '&' )
                    	    {
                    	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='!')||(input.LA(1)>='#' && input.LA(1)<='%')||(input.LA(1)>='\'' && input.LA(1)<='\uFFFE') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;}



                    	    }
                    	    break;
                    	case 2 :
                    	    // XQuery.g:384:64: ESCAPE_QUOT
                    	    {
                    	    mESCAPE_QUOT(); 


                    	    }
                    	    break;
                    	case 3 :
                    	    // XQuery.g:384:78: CharRef
                    	    {
                    	    mCharRef(); 


                    	    }
                    	    break;
                    	case 4 :
                    	    // XQuery.g:384:88: PredefinedEntityRef
                    	    {
                    	    mPredefinedEntityRef(); 


                    	    }
                    	    break;

                    	default :
                    	    break loop14;
                        }
                    } while (true);



                    }

                    mQUOT(); 


                    }
                    break;
                case 2 :
                    // XQuery.g:385:6: APOS ( options {greedy=false; } : (~ ( '\\'' | '&' ) | ESCAPE_APOS | CharRef | PredefinedEntityRef )* ) APOS
                    {
                    mAPOS(); 
                    // XQuery.g:385:11: ( options {greedy=false; } : (~ ( '\\'' | '&' ) | ESCAPE_APOS | CharRef | PredefinedEntityRef )* )
                    // XQuery.g:385:37: (~ ( '\\'' | '&' ) | ESCAPE_APOS | CharRef | PredefinedEntityRef )*
                    {
                    // XQuery.g:385:37: (~ ( '\\'' | '&' ) | ESCAPE_APOS | CharRef | PredefinedEntityRef )*
                    loop15:
                    do {
                        int alt15=5;
                        int LA15_0 = input.LA(1);

                        if ( (LA15_0=='\'') ) {
                            int LA15_1 = input.LA(2);

                            if ( (LA15_1=='\'') ) {
                                alt15=2;
                            }


                        }
                        else if ( ((LA15_0>='\u0000' && LA15_0<='%')||(LA15_0>='(' && LA15_0<='\uFFFE')) ) {
                            alt15=1;
                        }
                        else if ( (LA15_0=='&') ) {
                            int LA15_3 = input.LA(2);

                            if ( (LA15_3=='#') ) {
                                alt15=3;
                            }
                            else if ( (LA15_3=='a'||LA15_3=='g'||LA15_3=='l'||LA15_3=='q') ) {
                                alt15=4;
                            }


                        }


                        switch (alt15) {
                    	case 1 :
                    	    // XQuery.g:385:38: ~ ( '\\'' | '&' )
                    	    {
                    	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='%')||(input.LA(1)>='(' && input.LA(1)<='\uFFFE') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;}



                    	    }
                    	    break;
                    	case 2 :
                    	    // XQuery.g:385:54: ESCAPE_APOS
                    	    {
                    	    mESCAPE_APOS(); 


                    	    }
                    	    break;
                    	case 3 :
                    	    // XQuery.g:385:68: CharRef
                    	    {
                    	    mCharRef(); 


                    	    }
                    	    break;
                    	case 4 :
                    	    // XQuery.g:385:78: PredefinedEntityRef
                    	    {
                    	    mPredefinedEntityRef(); 


                    	    }
                    	    break;

                    	default :
                    	    break loop15;
                        }
                    } while (true);



                    }

                    mAPOS(); 


                    }
                    break;

            }
            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end StringLiteral

    // $ANTLR start ESCAPE_QUOT
    public final void mESCAPE_QUOT() throws RecognitionException {
        try {
            // XQuery.g:387:3: ( '\"\"' )
            // XQuery.g:387:5: '\"\"'
            {
            match("\"\""); 



            }

        }
        finally {
        }
    }
    // $ANTLR end ESCAPE_QUOT

    // $ANTLR start ESCAPE_APOS
    public final void mESCAPE_APOS() throws RecognitionException {
        try {
            // XQuery.g:389:3: ( '\\'\\'' )
            // XQuery.g:389:5: '\\'\\''
            {
            match("\'\'"); 



            }

        }
        finally {
        }
    }
    // $ANTLR end ESCAPE_APOS

    // $ANTLR start PredefinedEntityRef
    public final void mPredefinedEntityRef() throws RecognitionException {
        try {
            // XQuery.g:391:3: ( '&' ( 'lt' | 'gt' | 'apos' | 'quot' | 'amp' ) ';' )
            // XQuery.g:391:5: '&' ( 'lt' | 'gt' | 'apos' | 'quot' | 'amp' ) ';'
            {
            match('&'); 
            // XQuery.g:391:9: ( 'lt' | 'gt' | 'apos' | 'quot' | 'amp' )
            int alt17=5;
            switch ( input.LA(1) ) {
            case 'l':
                {
                alt17=1;
                }
                break;
            case 'g':
                {
                alt17=2;
                }
                break;
            case 'a':
                {
                int LA17_3 = input.LA(2);

                if ( (LA17_3=='p') ) {
                    alt17=3;
                }
                else if ( (LA17_3=='m') ) {
                    alt17=5;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 17, 3, input);

                    throw nvae;
                }
                }
                break;
            case 'q':
                {
                alt17=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 17, 0, input);

                throw nvae;
            }

            switch (alt17) {
                case 1 :
                    // XQuery.g:391:10: 'lt'
                    {
                    match("lt"); 



                    }
                    break;
                case 2 :
                    // XQuery.g:391:17: 'gt'
                    {
                    match("gt"); 



                    }
                    break;
                case 3 :
                    // XQuery.g:391:24: 'apos'
                    {
                    match("apos"); 



                    }
                    break;
                case 4 :
                    // XQuery.g:391:33: 'quot'
                    {
                    match("quot"); 



                    }
                    break;
                case 5 :
                    // XQuery.g:391:42: 'amp'
                    {
                    match("amp"); 



                    }
                    break;

            }

            match(';'); 


            }

        }
        finally {
        }
    }
    // $ANTLR end PredefinedEntityRef

    // $ANTLR start CharRef
    public final void mCharRef() throws RecognitionException {
        try {
            // XQuery.g:392:17: ( '&#' Digits ';' | '&#x' ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' )+ ';' )
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0=='&') ) {
                int LA19_1 = input.LA(2);

                if ( (LA19_1=='#') ) {
                    int LA19_2 = input.LA(3);

                    if ( (LA19_2=='x') ) {
                        alt19=2;
                    }
                    else if ( ((LA19_2>='0' && LA19_2<='9')) ) {
                        alt19=1;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 19, 2, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 19, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 19, 0, input);

                throw nvae;
            }
            switch (alt19) {
                case 1 :
                    // XQuery.g:392:19: '&#' Digits ';'
                    {
                    match("&#"); 

                    mDigits(); 
                    match(';'); 


                    }
                    break;
                case 2 :
                    // XQuery.g:392:37: '&#x' ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' )+ ';'
                    {
                    match("&#x"); 

                    // XQuery.g:392:43: ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' )+
                    int cnt18=0;
                    loop18:
                    do {
                        int alt18=2;
                        int LA18_0 = input.LA(1);

                        if ( ((LA18_0>='0' && LA18_0<='9')||(LA18_0>='A' && LA18_0<='F')||(LA18_0>='a' && LA18_0<='f')) ) {
                            alt18=1;
                        }


                        switch (alt18) {
                    	case 1 :
                    	    // XQuery.g:
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
                    	    if ( cnt18 >= 1 ) break loop18;
                                EarlyExitException eee =
                                    new EarlyExitException(18, input);
                                throw eee;
                        }
                        cnt18++;
                    } while (true);

                    match(';'); 


                    }
                    break;

            }
        }
        finally {
        }
    }
    // $ANTLR end CharRef

    // $ANTLR start Char
    public final void mChar() throws RecognitionException {
        try {
            // XQuery.g:394:15: ( '\\u0009' | '\\u000A' | '\\u000D' | '\\u0020' .. '\\uD7FF' | '\\uE000' .. '\\uFFFD' )
            // XQuery.g:
            {
            if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||input.LA(1)=='\r'||(input.LA(1)>=' ' && input.LA(1)<='\uD7FF')||(input.LA(1)>='\uE000' && input.LA(1)<='\uFFFD') ) {
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
    // $ANTLR end Char

    // $ANTLR start IntegerLiteral
    public final void mIntegerLiteral() throws RecognitionException {
        try {
            int _type = IntegerLiteral;
            // XQuery.g:395:16: ( Digits )
            // XQuery.g:395:18: Digits
            {
            mDigits(); 


            }

            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end IntegerLiteral

    // $ANTLR start DoubleLiteral
    public final void mDoubleLiteral() throws RecognitionException {
        try {
            int _type = DoubleLiteral;
            // XQuery.g:396:15: ( ( ( '.' Digits ) | ( Digits ( '.' ( '0' .. '9' )* )? ) ) ( 'e' | 'E' ) ( '+' | '-' )? Digits )
            // XQuery.g:396:17: ( ( '.' Digits ) | ( Digits ( '.' ( '0' .. '9' )* )? ) ) ( 'e' | 'E' ) ( '+' | '-' )? Digits
            {
            // XQuery.g:396:17: ( ( '.' Digits ) | ( Digits ( '.' ( '0' .. '9' )* )? ) )
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0=='.') ) {
                alt22=1;
            }
            else if ( ((LA22_0>='0' && LA22_0<='9')) ) {
                alt22=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 22, 0, input);

                throw nvae;
            }
            switch (alt22) {
                case 1 :
                    // XQuery.g:396:18: ( '.' Digits )
                    {
                    // XQuery.g:396:18: ( '.' Digits )
                    // XQuery.g:396:19: '.' Digits
                    {
                    match('.'); 
                    mDigits(); 


                    }



                    }
                    break;
                case 2 :
                    // XQuery.g:396:33: ( Digits ( '.' ( '0' .. '9' )* )? )
                    {
                    // XQuery.g:396:33: ( Digits ( '.' ( '0' .. '9' )* )? )
                    // XQuery.g:396:34: Digits ( '.' ( '0' .. '9' )* )?
                    {
                    mDigits(); 
                    // XQuery.g:396:41: ( '.' ( '0' .. '9' )* )?
                    int alt21=2;
                    int LA21_0 = input.LA(1);

                    if ( (LA21_0=='.') ) {
                        alt21=1;
                    }
                    switch (alt21) {
                        case 1 :
                            // XQuery.g:396:42: '.' ( '0' .. '9' )*
                            {
                            match('.'); 
                            // XQuery.g:396:46: ( '0' .. '9' )*
                            loop20:
                            do {
                                int alt20=2;
                                int LA20_0 = input.LA(1);

                                if ( ((LA20_0>='0' && LA20_0<='9')) ) {
                                    alt20=1;
                                }


                                switch (alt20) {
                            	case 1 :
                            	    // XQuery.g:396:46: '0' .. '9'
                            	    {
                            	    matchRange('0','9'); 


                            	    }
                            	    break;

                            	default :
                            	    break loop20;
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

            // XQuery.g:396:72: ( '+' | '-' )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0=='+'||LA23_0=='-') ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // XQuery.g:
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
        }
        finally {
        }
    }
    // $ANTLR end DoubleLiteral

    // $ANTLR start DecimalLiteral
    public final void mDecimalLiteral() throws RecognitionException {
        try {
            int _type = DecimalLiteral;
            // XQuery.g:397:16: ( ( '.' Digits ) | ( Digits '.' ( '0' .. '9' )* ) )
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0=='.') ) {
                alt25=1;
            }
            else if ( ((LA25_0>='0' && LA25_0<='9')) ) {
                alt25=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 25, 0, input);

                throw nvae;
            }
            switch (alt25) {
                case 1 :
                    // XQuery.g:397:21: ( '.' Digits )
                    {
                    // XQuery.g:397:21: ( '.' Digits )
                    // XQuery.g:397:22: '.' Digits
                    {
                    match('.'); 
                    mDigits(); 


                    }



                    }
                    break;
                case 2 :
                    // XQuery.g:397:36: ( Digits '.' ( '0' .. '9' )* )
                    {
                    // XQuery.g:397:36: ( Digits '.' ( '0' .. '9' )* )
                    // XQuery.g:397:37: Digits '.' ( '0' .. '9' )*
                    {
                    mDigits(); 
                    match('.'); 
                    // XQuery.g:397:48: ( '0' .. '9' )*
                    loop24:
                    do {
                        int alt24=2;
                        int LA24_0 = input.LA(1);

                        if ( ((LA24_0>='0' && LA24_0<='9')) ) {
                            alt24=1;
                        }


                        switch (alt24) {
                    	case 1 :
                    	    // XQuery.g:397:48: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 


                    	    }
                    	    break;

                    	default :
                    	    break loop24;
                        }
                    } while (true);



                    }



                    }
                    break;

            }
            state.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end DecimalLiteral

    // $ANTLR start Digits
    public final void mDigits() throws RecognitionException {
        try {
            // XQuery.g:398:17: ( ( '0' .. '9' )+ )
            // XQuery.g:398:19: ( '0' .. '9' )+
            {
            // XQuery.g:398:19: ( '0' .. '9' )+
            int cnt26=0;
            loop26:
            do {
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( ((LA26_0>='0' && LA26_0<='9')) ) {
                    alt26=1;
                }


                switch (alt26) {
            	case 1 :
            	    // XQuery.g:398:19: '0' .. '9'
            	    {
            	    matchRange('0','9'); 


            	    }
            	    break;

            	default :
            	    if ( cnt26 >= 1 ) break loop26;
                        EarlyExitException eee =
                            new EarlyExitException(26, input);
                        throw eee;
                }
                cnt26++;
            } while (true);



            }

        }
        finally {
        }
    }
    // $ANTLR end Digits

    public void mTokens() throws RecognitionException {
        // XQuery.g:1:8: ( T__145 | T__146 | T__147 | T__148 | T__149 | T__150 | T__151 | T__152 | T__153 | T__154 | T__155 | T__156 | T__157 | T__158 | T__159 | T__160 | ANCESTOR | ANCESTOR_OR_SELF | AND | AS | ASCENDING | AT | ATTRIBUTE | BASE_URI | BOUNDARY_SPACE | BY | CASE | CAST | CASTABLE | CHILD | COLLATION | COMMENT | CONSTRUCTION | COPY_NAMESPACES | DECLARE | DEFAULT | DESCENDANT | DESCENDANT_OR_SELF | DESCENDING | DIV | DOCUMENT | DOCUMENT_NODE | ELEMENT | ELSE | EMPTY | EMPTY_SEQUENCE | ENCODING | EQ | EVERY | EXCEPT | EXTERNAL | FOLLOWING | FOLLOWING_SIBLING | FOR | FUNCTION | GE | GREATEST | GT | IDIV | IF | IMPORT | IN | INHERIT | INSTANCE | INTERSECT | IS | ITEM | LAX | LE | LEAST | LET | LT | MOD | MODULE | NAMESPACE | NE | NO_INHERIT | NO_PRESERVE | NODE | OF | OPTION | OR | ORDER | ORDERED | ORDERING | PARENT | PRECEDING | PRECEDING_SIBLING | PRESERVE | PROCESSING_INSTRUCTION | RETURN | SATISFIES | SCHEMA | SCHEMA_ATTRIBUTE | SCHEMA_ELEMENT | SELF | SOME | STABLE | STRICT | STRIP | TEXT | THEN | TO | TREAT | TYPESWITCH | UNION | UNORDERED | VALIDATE | VARIABLE | VERSION | WHERE | XQUERY | NCName | DirCommentConstructor | DirPIConstructor | CDataSection | Pragma | LCURLY | RCURLY | OPEN_ANGLE | CLOSE_ANGLE | Lit_EQ | SLASH_SLASH | SLASH | LBRACKET | RBRACKET | LPAREN | RPAREN | COLON | COMMA | SEMICOLON | S | XQ_COMMENT | StringLiteral | IntegerLiteral | DoubleLiteral | DecimalLiteral )
        int alt27=137;
        alt27 = dfa27.predict(input);
        switch (alt27) {
            case 1 :
                // XQuery.g:1:10: T__145
                {
                mT__145(); 


                }
                break;
            case 2 :
                // XQuery.g:1:17: T__146
                {
                mT__146(); 


                }
                break;
            case 3 :
                // XQuery.g:1:24: T__147
                {
                mT__147(); 


                }
                break;
            case 4 :
                // XQuery.g:1:31: T__148
                {
                mT__148(); 


                }
                break;
            case 5 :
                // XQuery.g:1:38: T__149
                {
                mT__149(); 


                }
                break;
            case 6 :
                // XQuery.g:1:45: T__150
                {
                mT__150(); 


                }
                break;
            case 7 :
                // XQuery.g:1:52: T__151
                {
                mT__151(); 


                }
                break;
            case 8 :
                // XQuery.g:1:59: T__152
                {
                mT__152(); 


                }
                break;
            case 9 :
                // XQuery.g:1:66: T__153
                {
                mT__153(); 


                }
                break;
            case 10 :
                // XQuery.g:1:73: T__154
                {
                mT__154(); 


                }
                break;
            case 11 :
                // XQuery.g:1:80: T__155
                {
                mT__155(); 


                }
                break;
            case 12 :
                // XQuery.g:1:87: T__156
                {
                mT__156(); 


                }
                break;
            case 13 :
                // XQuery.g:1:94: T__157
                {
                mT__157(); 


                }
                break;
            case 14 :
                // XQuery.g:1:101: T__158
                {
                mT__158(); 


                }
                break;
            case 15 :
                // XQuery.g:1:108: T__159
                {
                mT__159(); 


                }
                break;
            case 16 :
                // XQuery.g:1:115: T__160
                {
                mT__160(); 


                }
                break;
            case 17 :
                // XQuery.g:1:122: ANCESTOR
                {
                mANCESTOR(); 


                }
                break;
            case 18 :
                // XQuery.g:1:131: ANCESTOR_OR_SELF
                {
                mANCESTOR_OR_SELF(); 


                }
                break;
            case 19 :
                // XQuery.g:1:148: AND
                {
                mAND(); 


                }
                break;
            case 20 :
                // XQuery.g:1:152: AS
                {
                mAS(); 


                }
                break;
            case 21 :
                // XQuery.g:1:155: ASCENDING
                {
                mASCENDING(); 


                }
                break;
            case 22 :
                // XQuery.g:1:165: AT
                {
                mAT(); 


                }
                break;
            case 23 :
                // XQuery.g:1:168: ATTRIBUTE
                {
                mATTRIBUTE(); 


                }
                break;
            case 24 :
                // XQuery.g:1:178: BASE_URI
                {
                mBASE_URI(); 


                }
                break;
            case 25 :
                // XQuery.g:1:187: BOUNDARY_SPACE
                {
                mBOUNDARY_SPACE(); 


                }
                break;
            case 26 :
                // XQuery.g:1:202: BY
                {
                mBY(); 


                }
                break;
            case 27 :
                // XQuery.g:1:205: CASE
                {
                mCASE(); 


                }
                break;
            case 28 :
                // XQuery.g:1:210: CAST
                {
                mCAST(); 


                }
                break;
            case 29 :
                // XQuery.g:1:215: CASTABLE
                {
                mCASTABLE(); 


                }
                break;
            case 30 :
                // XQuery.g:1:224: CHILD
                {
                mCHILD(); 


                }
                break;
            case 31 :
                // XQuery.g:1:230: COLLATION
                {
                mCOLLATION(); 


                }
                break;
            case 32 :
                // XQuery.g:1:240: COMMENT
                {
                mCOMMENT(); 


                }
                break;
            case 33 :
                // XQuery.g:1:248: CONSTRUCTION
                {
                mCONSTRUCTION(); 


                }
                break;
            case 34 :
                // XQuery.g:1:261: COPY_NAMESPACES
                {
                mCOPY_NAMESPACES(); 


                }
                break;
            case 35 :
                // XQuery.g:1:277: DECLARE
                {
                mDECLARE(); 


                }
                break;
            case 36 :
                // XQuery.g:1:285: DEFAULT
                {
                mDEFAULT(); 


                }
                break;
            case 37 :
                // XQuery.g:1:293: DESCENDANT
                {
                mDESCENDANT(); 


                }
                break;
            case 38 :
                // XQuery.g:1:304: DESCENDANT_OR_SELF
                {
                mDESCENDANT_OR_SELF(); 


                }
                break;
            case 39 :
                // XQuery.g:1:323: DESCENDING
                {
                mDESCENDING(); 


                }
                break;
            case 40 :
                // XQuery.g:1:334: DIV
                {
                mDIV(); 


                }
                break;
            case 41 :
                // XQuery.g:1:338: DOCUMENT
                {
                mDOCUMENT(); 


                }
                break;
            case 42 :
                // XQuery.g:1:347: DOCUMENT_NODE
                {
                mDOCUMENT_NODE(); 


                }
                break;
            case 43 :
                // XQuery.g:1:361: ELEMENT
                {
                mELEMENT(); 


                }
                break;
            case 44 :
                // XQuery.g:1:369: ELSE
                {
                mELSE(); 


                }
                break;
            case 45 :
                // XQuery.g:1:374: EMPTY
                {
                mEMPTY(); 


                }
                break;
            case 46 :
                // XQuery.g:1:380: EMPTY_SEQUENCE
                {
                mEMPTY_SEQUENCE(); 


                }
                break;
            case 47 :
                // XQuery.g:1:395: ENCODING
                {
                mENCODING(); 


                }
                break;
            case 48 :
                // XQuery.g:1:404: EQ
                {
                mEQ(); 


                }
                break;
            case 49 :
                // XQuery.g:1:407: EVERY
                {
                mEVERY(); 


                }
                break;
            case 50 :
                // XQuery.g:1:413: EXCEPT
                {
                mEXCEPT(); 


                }
                break;
            case 51 :
                // XQuery.g:1:420: EXTERNAL
                {
                mEXTERNAL(); 


                }
                break;
            case 52 :
                // XQuery.g:1:429: FOLLOWING
                {
                mFOLLOWING(); 


                }
                break;
            case 53 :
                // XQuery.g:1:439: FOLLOWING_SIBLING
                {
                mFOLLOWING_SIBLING(); 


                }
                break;
            case 54 :
                // XQuery.g:1:457: FOR
                {
                mFOR(); 


                }
                break;
            case 55 :
                // XQuery.g:1:461: FUNCTION
                {
                mFUNCTION(); 


                }
                break;
            case 56 :
                // XQuery.g:1:470: GE
                {
                mGE(); 


                }
                break;
            case 57 :
                // XQuery.g:1:473: GREATEST
                {
                mGREATEST(); 


                }
                break;
            case 58 :
                // XQuery.g:1:482: GT
                {
                mGT(); 


                }
                break;
            case 59 :
                // XQuery.g:1:485: IDIV
                {
                mIDIV(); 


                }
                break;
            case 60 :
                // XQuery.g:1:490: IF
                {
                mIF(); 


                }
                break;
            case 61 :
                // XQuery.g:1:493: IMPORT
                {
                mIMPORT(); 


                }
                break;
            case 62 :
                // XQuery.g:1:500: IN
                {
                mIN(); 


                }
                break;
            case 63 :
                // XQuery.g:1:503: INHERIT
                {
                mINHERIT(); 


                }
                break;
            case 64 :
                // XQuery.g:1:511: INSTANCE
                {
                mINSTANCE(); 


                }
                break;
            case 65 :
                // XQuery.g:1:520: INTERSECT
                {
                mINTERSECT(); 


                }
                break;
            case 66 :
                // XQuery.g:1:530: IS
                {
                mIS(); 


                }
                break;
            case 67 :
                // XQuery.g:1:533: ITEM
                {
                mITEM(); 


                }
                break;
            case 68 :
                // XQuery.g:1:538: LAX
                {
                mLAX(); 


                }
                break;
            case 69 :
                // XQuery.g:1:542: LE
                {
                mLE(); 


                }
                break;
            case 70 :
                // XQuery.g:1:545: LEAST
                {
                mLEAST(); 


                }
                break;
            case 71 :
                // XQuery.g:1:551: LET
                {
                mLET(); 


                }
                break;
            case 72 :
                // XQuery.g:1:555: LT
                {
                mLT(); 


                }
                break;
            case 73 :
                // XQuery.g:1:558: MOD
                {
                mMOD(); 


                }
                break;
            case 74 :
                // XQuery.g:1:562: MODULE
                {
                mMODULE(); 


                }
                break;
            case 75 :
                // XQuery.g:1:569: NAMESPACE
                {
                mNAMESPACE(); 


                }
                break;
            case 76 :
                // XQuery.g:1:579: NE
                {
                mNE(); 


                }
                break;
            case 77 :
                // XQuery.g:1:582: NO_INHERIT
                {
                mNO_INHERIT(); 


                }
                break;
            case 78 :
                // XQuery.g:1:593: NO_PRESERVE
                {
                mNO_PRESERVE(); 


                }
                break;
            case 79 :
                // XQuery.g:1:605: NODE
                {
                mNODE(); 


                }
                break;
            case 80 :
                // XQuery.g:1:610: OF
                {
                mOF(); 


                }
                break;
            case 81 :
                // XQuery.g:1:613: OPTION
                {
                mOPTION(); 


                }
                break;
            case 82 :
                // XQuery.g:1:620: OR
                {
                mOR(); 


                }
                break;
            case 83 :
                // XQuery.g:1:623: ORDER
                {
                mORDER(); 


                }
                break;
            case 84 :
                // XQuery.g:1:629: ORDERED
                {
                mORDERED(); 


                }
                break;
            case 85 :
                // XQuery.g:1:637: ORDERING
                {
                mORDERING(); 


                }
                break;
            case 86 :
                // XQuery.g:1:646: PARENT
                {
                mPARENT(); 


                }
                break;
            case 87 :
                // XQuery.g:1:653: PRECEDING
                {
                mPRECEDING(); 


                }
                break;
            case 88 :
                // XQuery.g:1:663: PRECEDING_SIBLING
                {
                mPRECEDING_SIBLING(); 


                }
                break;
            case 89 :
                // XQuery.g:1:681: PRESERVE
                {
                mPRESERVE(); 


                }
                break;
            case 90 :
                // XQuery.g:1:690: PROCESSING_INSTRUCTION
                {
                mPROCESSING_INSTRUCTION(); 


                }
                break;
            case 91 :
                // XQuery.g:1:713: RETURN
                {
                mRETURN(); 


                }
                break;
            case 92 :
                // XQuery.g:1:720: SATISFIES
                {
                mSATISFIES(); 


                }
                break;
            case 93 :
                // XQuery.g:1:730: SCHEMA
                {
                mSCHEMA(); 


                }
                break;
            case 94 :
                // XQuery.g:1:737: SCHEMA_ATTRIBUTE
                {
                mSCHEMA_ATTRIBUTE(); 


                }
                break;
            case 95 :
                // XQuery.g:1:754: SCHEMA_ELEMENT
                {
                mSCHEMA_ELEMENT(); 


                }
                break;
            case 96 :
                // XQuery.g:1:769: SELF
                {
                mSELF(); 


                }
                break;
            case 97 :
                // XQuery.g:1:774: SOME
                {
                mSOME(); 


                }
                break;
            case 98 :
                // XQuery.g:1:779: STABLE
                {
                mSTABLE(); 


                }
                break;
            case 99 :
                // XQuery.g:1:786: STRICT
                {
                mSTRICT(); 


                }
                break;
            case 100 :
                // XQuery.g:1:793: STRIP
                {
                mSTRIP(); 


                }
                break;
            case 101 :
                // XQuery.g:1:799: TEXT
                {
                mTEXT(); 


                }
                break;
            case 102 :
                // XQuery.g:1:804: THEN
                {
                mTHEN(); 


                }
                break;
            case 103 :
                // XQuery.g:1:809: TO
                {
                mTO(); 


                }
                break;
            case 104 :
                // XQuery.g:1:812: TREAT
                {
                mTREAT(); 


                }
                break;
            case 105 :
                // XQuery.g:1:818: TYPESWITCH
                {
                mTYPESWITCH(); 


                }
                break;
            case 106 :
                // XQuery.g:1:829: UNION
                {
                mUNION(); 


                }
                break;
            case 107 :
                // XQuery.g:1:835: UNORDERED
                {
                mUNORDERED(); 


                }
                break;
            case 108 :
                // XQuery.g:1:845: VALIDATE
                {
                mVALIDATE(); 


                }
                break;
            case 109 :
                // XQuery.g:1:854: VARIABLE
                {
                mVARIABLE(); 


                }
                break;
            case 110 :
                // XQuery.g:1:863: VERSION
                {
                mVERSION(); 


                }
                break;
            case 111 :
                // XQuery.g:1:871: WHERE
                {
                mWHERE(); 


                }
                break;
            case 112 :
                // XQuery.g:1:877: XQUERY
                {
                mXQUERY(); 


                }
                break;
            case 113 :
                // XQuery.g:1:884: NCName
                {
                mNCName(); 


                }
                break;
            case 114 :
                // XQuery.g:1:891: DirCommentConstructor
                {
                mDirCommentConstructor(); 


                }
                break;
            case 115 :
                // XQuery.g:1:913: DirPIConstructor
                {
                mDirPIConstructor(); 


                }
                break;
            case 116 :
                // XQuery.g:1:930: CDataSection
                {
                mCDataSection(); 


                }
                break;
            case 117 :
                // XQuery.g:1:943: Pragma
                {
                mPragma(); 


                }
                break;
            case 118 :
                // XQuery.g:1:950: LCURLY
                {
                mLCURLY(); 


                }
                break;
            case 119 :
                // XQuery.g:1:957: RCURLY
                {
                mRCURLY(); 


                }
                break;
            case 120 :
                // XQuery.g:1:964: OPEN_ANGLE
                {
                mOPEN_ANGLE(); 


                }
                break;
            case 121 :
                // XQuery.g:1:975: CLOSE_ANGLE
                {
                mCLOSE_ANGLE(); 


                }
                break;
            case 122 :
                // XQuery.g:1:987: Lit_EQ
                {
                mLit_EQ(); 


                }
                break;
            case 123 :
                // XQuery.g:1:994: SLASH_SLASH
                {
                mSLASH_SLASH(); 


                }
                break;
            case 124 :
                // XQuery.g:1:1006: SLASH
                {
                mSLASH(); 


                }
                break;
            case 125 :
                // XQuery.g:1:1012: LBRACKET
                {
                mLBRACKET(); 


                }
                break;
            case 126 :
                // XQuery.g:1:1021: RBRACKET
                {
                mRBRACKET(); 


                }
                break;
            case 127 :
                // XQuery.g:1:1030: LPAREN
                {
                mLPAREN(); 


                }
                break;
            case 128 :
                // XQuery.g:1:1037: RPAREN
                {
                mRPAREN(); 


                }
                break;
            case 129 :
                // XQuery.g:1:1044: COLON
                {
                mCOLON(); 


                }
                break;
            case 130 :
                // XQuery.g:1:1050: COMMA
                {
                mCOMMA(); 


                }
                break;
            case 131 :
                // XQuery.g:1:1056: SEMICOLON
                {
                mSEMICOLON(); 


                }
                break;
            case 132 :
                // XQuery.g:1:1066: S
                {
                mS(); 


                }
                break;
            case 133 :
                // XQuery.g:1:1068: XQ_COMMENT
                {
                mXQ_COMMENT(); 


                }
                break;
            case 134 :
                // XQuery.g:1:1079: StringLiteral
                {
                mStringLiteral(); 


                }
                break;
            case 135 :
                // XQuery.g:1:1093: IntegerLiteral
                {
                mIntegerLiteral(); 


                }
                break;
            case 136 :
                // XQuery.g:1:1108: DoubleLiteral
                {
                mDoubleLiteral(); 


                }
                break;
            case 137 :
                // XQuery.g:1:1122: DecimalLiteral
                {
                mDecimalLiteral(); 


                }
                break;

        }

    }


    protected DFA27 dfa27 = new DFA27(this);
    static final String DFA27_eotS =
        "\1\uffff\1\61\6\uffff\1\66\1\71\1\uffff\1\74\1\uffff\24\41\1\uffff"+
        "\1\170\3\uffff\1\172\7\uffff\1\173\14\uffff\1\u0080\1\uffff\1\41"+
        "\1\u0084\1\u0086\2\41\1\u0089\11\41\1\u0099\4\41\1\u00a0\1\41\1"+
        "\u00a2\1\41\1\u00a4\1\41\1\u00a9\1\u00aa\2\41\1\u00af\1\u00b0\2"+
        "\41\1\u00b3\1\41\1\u00b6\1\41\1\u00b9\12\41\1\u00c6\7\41\6\uffff"+
        "\1\u0080\4\uffff\1\41\1\u00d2\1\41\1\uffff\1\41\1\uffff\2\41\1\uffff"+
        "\11\41\1\u00e1\5\41\1\uffff\4\41\1\u00eb\1\41\1\uffff\1\41\1\uffff"+
        "\1\41\1\uffff\4\41\2\uffff\1\41\1\u00f4\1\41\1\u00f6\2\uffff\1\u00f8"+
        "\1\41\1\uffff\2\41\1\uffff\2\41\1\uffff\14\41\1\uffff\11\41\1\u0080"+
        "\1\41\1\uffff\4\41\1\u011a\1\u011c\10\41\1\uffff\2\41\1\u0127\6"+
        "\41\1\uffff\2\41\1\u0130\4\41\1\u0135\1\uffff\1\41\1\uffff\1\41"+
        "\1\uffff\3\41\1\u013b\11\41\1\u0145\1\u0146\2\41\1\u014a\1\u014b"+
        "\16\41\1\uffff\1\41\1\uffff\1\u015b\11\41\1\uffff\1\u0166\1\41\1"+
        "\u0168\5\41\1\uffff\4\41\1\uffff\1\u0172\4\41\1\uffff\1\41\1\u017a"+
        "\7\41\2\uffff\2\41\1\u0184\2\uffff\1\u0185\1\41\1\u0187\4\41\1\u018c"+
        "\7\41\1\uffff\12\41\1\uffff\1\41\1\uffff\1\u019f\4\41\1\u01a4\3"+
        "\41\1\uffff\1\u01a8\3\41\1\u01ac\2\41\1\uffff\1\u01af\3\41\1\u01b3"+
        "\1\41\1\u01b6\1\u01b7\1\u01b8\2\uffff\1\41\1\uffff\4\41\1\uffff"+
        "\1\u01be\7\41\1\u01c6\2\41\1\u01c9\1\u01ca\2\41\1\u01ce\2\41\1\uffff"+
        "\4\41\1\uffff\1\u01d5\2\41\1\uffff\3\41\1\uffff\1\u01db\1\41\1\uffff"+
        "\3\41\1\uffff\2\41\3\uffff\4\41\1\u01e7\1\uffff\1\u01e9\2\41\1\u01ec"+
        "\1\41\1\u01ee\1\41\1\uffff\2\41\2\uffff\2\41\1\u01f5\1\uffff\1\41"+
        "\1\u01f7\1\u01f8\1\41\1\u01fa\1\u01fb\1\uffff\1\u01fc\4\41\1\uffff"+
        "\1\u0201\1\41\1\u0203\6\41\1\u020a\1\u020b\1\uffff\1\41\1\uffff"+
        "\1\u020d\1\u020e\1\uffff\1\41\1\uffff\1\u0210\5\41\1\uffff\1\41"+
        "\2\uffff\1\u0218\3\uffff\1\u0219\1\u021a\2\41\1\uffff\1\u021e\1"+
        "\uffff\1\41\1\u0220\3\41\1\u0224\2\uffff\1\41\2\uffff\1\41\1\uffff"+
        "\2\41\1\u022a\1\u022b\3\41\3\uffff\1\u022f\2\41\1\uffff\1\41\1\uffff"+
        "\2\41\1\u0235\1\uffff\5\41\2\uffff\3\41\1\uffff\1\u023e\4\41\1\uffff"+
        "\2\41\1\u0245\5\41\1\uffff\6\41\1\uffff\2\41\1\u0253\7\41\1\u025b"+
        "\2\41\1\uffff\1\u025e\4\41\1\u0263\1\41\1\uffff\1\u0265\1\41\1\uffff"+
        "\4\41\1\uffff\1\u026b\1\uffff\4\41\1\u0270\1\uffff\1\41\1\u0272"+
        "\1\u0273\1\41\1\uffff\1\u0275\2\uffff\1\41\1\uffff\3\41\1\u027a"+
        "\1\uffff";
    static final String DFA27_eofS =
        "\u027b\uffff";
    static final String DFA27_minS =
        "\1\11\1\72\6\uffff\1\41\1\75\1\uffff\1\56\1\uffff\1\156\2\141\1"+
        "\145\1\154\1\157\1\145\1\144\1\141\1\157\1\141\1\146\1\141\1\145"+
        "\1\141\1\145\1\156\1\141\1\150\1\161\1\uffff\1\43\3\uffff\1\57\7"+
        "\uffff\1\56\5\uffff\1\55\6\uffff\1\60\1\uffff\1\143\2\55\1\163\1"+
        "\165\1\55\1\163\1\151\1\154\1\143\1\166\1\143\1\145\1\160\1\143"+
        "\1\55\1\145\1\143\1\154\1\156\1\55\1\145\1\55\1\151\1\55\1\160\2"+
        "\55\1\145\1\170\2\55\1\144\1\155\3\55\1\164\1\55\1\162\1\145\2\164"+
        "\1\150\1\154\1\155\1\141\1\170\1\145\1\55\1\145\1\160\1\151\1\154"+
        "\1\162\1\145\1\165\6\uffff\1\60\4\uffff\1\145\1\55\1\145\1\uffff"+
        "\1\162\1\uffff\1\145\1\156\1\uffff\1\145\2\154\1\155\1\163\1\171"+
        "\1\154\1\141\1\143\1\55\1\165\1\155\1\145\1\164\1\157\1\uffff\1"+
        "\162\2\145\1\154\1\55\1\143\1\uffff\1\141\1\uffff\1\166\1\uffff"+
        "\1\157\1\145\1\164\1\145\2\uffff\1\155\1\55\1\163\1\55\2\uffff\1"+
        "\55\1\145\1\uffff\1\151\1\145\1\uffff\1\151\1\145\1\uffff\1\145"+
        "\2\143\1\165\1\151\1\145\1\146\1\145\1\142\1\151\1\164\1\156\1\uffff"+
        "\1\141\1\145\1\157\1\162\2\151\1\163\1\162\1\145\1\60\1\163\1\uffff"+
        "\1\156\1\151\1\55\1\144\2\55\1\144\1\141\1\145\1\164\1\55\1\141"+
        "\1\165\1\145\1\uffff\1\155\1\145\1\55\1\171\1\144\1\171\1\160\1"+
        "\162\1\157\1\uffff\2\164\1\55\2\162\1\141\1\162\1\55\1\uffff\1\164"+
        "\1\uffff\1\154\1\uffff\1\163\1\156\1\162\1\55\1\157\1\162\1\156"+
        "\3\145\1\162\1\163\1\155\2\55\1\154\1\143\2\55\1\164\1\163\1\156"+
        "\2\144\1\141\1\151\1\145\1\162\1\164\1\144\1\142\1\165\1\141\1\uffff"+
        "\1\142\1\uffff\1\55\1\164\1\156\1\162\1\156\1\162\1\154\1\156\1"+
        "\145\1\156\1\uffff\1\55\1\151\1\55\1\164\1\156\1\167\1\151\1\145"+
        "\1\uffff\1\164\1\151\1\156\1\163\1\uffff\1\55\1\145\1\160\1\150"+
        "\1\145\1\uffff\1\156\1\55\1\164\1\144\1\162\1\163\1\156\1\146\1"+
        "\141\2\uffff\1\145\1\164\1\55\2\uffff\1\55\1\167\1\55\1\145\1\141"+
        "\1\142\1\157\1\55\1\171\1\157\1\151\1\165\2\162\1\154\1\uffff\1"+
        "\151\1\164\1\165\1\141\1\145\1\164\1\144\1\156\1\164\1\163\1\uffff"+
        "\1\156\1\uffff\1\55\1\141\1\151\1\157\1\163\1\55\1\164\1\143\1\145"+
        "\1\uffff\1\55\1\141\1\145\1\163\1\55\1\144\1\156\1\uffff\1\55\1"+
        "\151\1\166\1\163\1\55\1\151\3\55\2\uffff\1\151\1\uffff\1\162\1\164"+
        "\1\154\1\156\1\uffff\1\55\1\162\1\156\1\164\1\151\1\171\1\145\1"+
        "\157\1\55\1\143\1\155\2\55\1\141\1\164\1\55\1\145\1\147\1\uffff"+
        "\1\154\2\156\1\164\1\uffff\1\55\1\145\1\143\1\uffff\1\143\1\162"+
        "\1\145\1\uffff\1\55\1\147\1\uffff\1\156\1\145\1\151\1\uffff\1\145"+
        "\1\141\3\uffff\1\164\3\145\1\55\1\uffff\1\55\1\147\1\145\3\55\1"+
        "\156\1\uffff\1\164\1\145\2\uffff\2\156\1\55\1\uffff\1\161\2\55\1"+
        "\147\2\55\1\uffff\1\55\1\164\1\145\1\151\1\162\1\uffff\1\55\1\147"+
        "\1\55\1\156\1\163\1\164\1\154\1\143\1\144\2\55\1\uffff\1\157\1\uffff"+
        "\2\55\1\uffff\1\163\1\uffff\1\55\1\151\1\163\1\164\1\147\1\156\1"+
        "\uffff\1\165\2\uffff\1\55\3\uffff\2\55\1\164\1\166\1\uffff\1\55"+
        "\1\uffff\1\147\1\55\1\164\1\145\1\150\1\55\2\uffff\1\162\2\uffff"+
        "\1\160\1\uffff\1\157\1\160\2\55\1\157\1\145\1\163\3\uffff\1\55\1"+
        "\145\1\163\1\uffff\1\55\1\uffff\1\162\1\155\1\55\1\uffff\1\55\1"+
        "\141\1\156\1\141\1\157\2\uffff\1\144\1\156\1\151\1\uffff\1\55\3"+
        "\151\1\145\1\uffff\1\163\1\143\1\55\1\143\1\162\1\145\1\143\1\142"+
        "\1\uffff\1\142\1\156\1\142\1\156\2\145\1\uffff\1\145\2\55\1\145"+
        "\2\154\1\163\1\165\1\164\1\154\1\55\2\163\1\uffff\1\55\2\151\2\164"+
        "\1\55\1\146\1\uffff\1\55\1\145\1\uffff\2\156\1\162\1\145\1\uffff"+
        "\1\55\1\uffff\1\154\2\147\1\165\1\55\1\uffff\1\146\2\55\1\143\1"+
        "\uffff\1\55\2\uffff\1\164\1\uffff\1\151\1\157\1\156\1\55\1\uffff";
    static final String DFA27_maxS =
        "\1\175\1\75\6\uffff\1\77\1\76\1\uffff\1\71\1\uffff\1\164\1\171\2"+
        "\157\1\170\1\165\3\164\2\157\2\162\1\145\1\164\1\171\1\156\1\145"+
        "\1\150\1\161\1\uffff\1\72\3\uffff\1\57\7\uffff\1\145\5\uffff\1\133"+
        "\6\uffff\1\145\1\uffff\1\144\2\172\1\163\1\165\1\172\1\163\1\151"+
        "\1\160\1\163\1\166\1\143\1\163\1\160\1\143\1\172\1\145\1\164\1\162"+
        "\1\156\1\172\1\145\1\172\1\151\1\172\1\160\2\172\1\145\1\170\2\172"+
        "\1\144\1\155\1\172\1\144\1\172\1\164\1\172\1\162\1\157\2\164\1\150"+
        "\1\154\1\155\1\162\1\170\1\145\1\172\1\145\1\160\1\157\2\162\1\145"+
        "\1\165\6\uffff\1\145\4\uffff\1\145\1\172\1\145\1\uffff\1\162\1\uffff"+
        "\1\145\1\156\1\uffff\1\164\2\154\1\155\1\163\1\171\1\154\1\141\1"+
        "\143\1\172\1\165\1\155\1\145\1\164\1\157\1\uffff\1\162\2\145\1\154"+
        "\1\172\1\143\1\uffff\1\141\1\uffff\1\166\1\uffff\1\157\1\145\1\164"+
        "\1\145\2\uffff\1\155\1\172\1\163\1\172\2\uffff\1\172\1\145\1\uffff"+
        "\1\160\1\145\1\uffff\1\151\1\145\1\uffff\1\145\1\163\1\143\1\165"+
        "\1\151\1\145\1\146\1\145\1\142\1\151\1\164\1\156\1\uffff\1\141\1"+
        "\145\1\157\1\162\2\151\1\163\1\162\2\145\1\163\1\uffff\1\156\1\151"+
        "\1\55\1\144\2\172\1\144\1\141\1\145\1\164\1\55\1\141\1\165\1\145"+
        "\1\uffff\1\155\1\145\1\172\1\171\1\144\1\171\1\160\1\162\1\157\1"+
        "\uffff\2\164\1\172\2\162\1\141\1\162\1\172\1\uffff\1\164\1\uffff"+
        "\1\154\1\uffff\1\163\1\156\1\162\1\172\1\157\1\162\1\156\3\145\1"+
        "\162\1\163\1\155\2\172\1\154\1\160\2\172\1\164\1\163\1\156\2\144"+
        "\1\141\1\151\1\145\1\162\1\164\1\144\1\142\1\165\1\141\1\uffff\1"+
        "\142\1\uffff\1\172\1\164\1\156\1\162\1\156\1\162\1\154\1\156\1\145"+
        "\1\156\1\uffff\1\172\1\151\1\172\1\164\1\156\1\167\1\151\1\145\1"+
        "\uffff\1\164\1\151\1\156\1\163\1\uffff\1\172\1\145\1\160\1\150\1"+
        "\145\1\uffff\1\156\1\172\1\164\1\144\1\162\1\163\1\156\1\146\1\141"+
        "\2\uffff\1\145\1\164\1\172\2\uffff\1\172\1\167\1\172\1\145\1\141"+
        "\1\142\1\157\1\172\1\171\1\157\1\151\1\165\2\162\1\154\1\uffff\1"+
        "\151\1\164\1\165\1\141\1\145\1\164\1\144\1\156\1\164\1\163\1\uffff"+
        "\1\156\1\uffff\1\172\1\141\1\151\1\157\1\163\1\172\1\164\1\143\1"+
        "\145\1\uffff\1\172\1\141\1\145\1\163\1\172\1\144\1\156\1\uffff\1"+
        "\172\1\151\1\166\1\163\1\172\1\151\3\172\2\uffff\1\151\1\uffff\1"+
        "\162\1\164\1\154\1\156\1\uffff\1\172\1\162\1\156\1\164\1\151\1\171"+
        "\1\145\1\157\1\172\1\143\1\155\2\172\1\151\1\164\1\172\1\145\1\147"+
        "\1\uffff\1\154\2\156\1\164\1\uffff\1\172\1\145\1\143\1\uffff\1\143"+
        "\1\162\1\145\1\uffff\1\172\1\147\1\uffff\1\156\1\145\1\151\1\uffff"+
        "\2\145\3\uffff\1\164\3\145\1\172\1\uffff\1\172\1\147\1\145\1\172"+
        "\1\55\1\172\1\156\1\uffff\1\164\1\145\2\uffff\2\156\1\172\1\uffff"+
        "\1\161\2\172\1\147\2\172\1\uffff\1\172\1\164\1\145\1\151\1\162\1"+
        "\uffff\1\172\1\147\1\172\1\156\1\163\1\164\1\154\1\143\1\144\2\172"+
        "\1\uffff\1\157\1\uffff\2\172\1\uffff\1\163\1\uffff\1\172\1\151\1"+
        "\163\1\164\1\147\1\156\1\uffff\1\165\2\uffff\1\172\3\uffff\2\172"+
        "\1\164\1\166\1\uffff\1\172\1\uffff\1\147\1\172\1\164\1\145\1\150"+
        "\1\172\2\uffff\1\162\2\uffff\1\160\1\uffff\1\157\1\160\2\172\1\157"+
        "\1\145\1\163\3\uffff\1\172\1\145\1\163\1\uffff\1\55\1\uffff\1\162"+
        "\1\155\1\172\1\uffff\1\55\1\141\1\156\1\141\1\157\2\uffff\1\144"+
        "\1\156\1\151\1\uffff\1\172\3\151\1\145\1\uffff\1\163\1\143\1\172"+
        "\1\143\1\162\1\145\1\143\1\142\1\uffff\1\142\1\156\1\142\1\156\2"+
        "\145\1\uffff\1\145\1\55\1\172\1\145\2\154\1\163\1\165\1\164\1\154"+
        "\1\172\2\163\1\uffff\1\172\2\151\2\164\1\172\1\146\1\uffff\1\172"+
        "\1\145\1\uffff\2\156\1\162\1\145\1\uffff\1\172\1\uffff\1\154\2\147"+
        "\1\165\1\172\1\uffff\1\146\2\172\1\143\1\uffff\1\172\2\uffff\1\164"+
        "\1\uffff\1\151\1\157\1\156\1\172\1\uffff";
    static final String DFA27_acceptS =
        "\2\uffff\1\2\1\3\1\4\1\5\1\6\1\7\2\uffff\1\15\1\uffff\1\20\24\uffff"+
        "\1\161\1\uffff\1\166\1\167\1\172\1\uffff\1\175\1\176\1\u0080\1\u0082"+
        "\1\u0083\1\u0084\1\u0086\1\uffff\1\1\1\14\1\u0081\1\10\1\12\1\uffff"+
        "\1\163\1\170\1\11\1\13\1\171\1\16\1\uffff\1\17\71\uffff\1\165\1"+
        "\u0085\1\177\1\173\1\174\1\u0087\1\uffff\1\u0088\1\162\1\164\1\u0089"+
        "\3\uffff\1\24\1\uffff\1\26\2\uffff\1\32\17\uffff\1\60\6\uffff\1"+
        "\70\1\uffff\1\72\1\uffff\1\74\4\uffff\1\76\1\102\4\uffff\1\105\1"+
        "\110\2\uffff\1\114\2\uffff\1\120\2\uffff\1\122\14\uffff\1\147\13"+
        "\uffff\1\23\16\uffff\1\50\11\uffff\1\66\10\uffff\1\104\1\uffff\1"+
        "\107\1\uffff\1\111\41\uffff\1\33\1\uffff\1\34\12\uffff\1\54\10\uffff"+
        "\1\73\4\uffff\1\103\5\uffff\1\117\11\uffff\1\140\1\141\3\uffff\1"+
        "\145\1\146\17\uffff\1\36\12\uffff\1\55\1\uffff\1\61\11\uffff\1\106"+
        "\7\uffff\1\123\11\uffff\1\144\1\150\1\uffff\1\152\4\uffff\1\157"+
        "\22\uffff\1\62\4\uffff\1\75\3\uffff\1\112\3\uffff\1\121\2\uffff"+
        "\1\126\3\uffff\1\133\2\uffff\1\135\1\142\1\143\5\uffff\1\160\7\uffff"+
        "\1\40\2\uffff\1\43\1\44\3\uffff\1\53\6\uffff\1\77\5\uffff\1\124"+
        "\13\uffff\1\156\1\uffff\1\21\2\uffff\1\30\1\uffff\1\35\6\uffff\1"+
        "\51\1\uffff\1\57\1\63\1\uffff\1\67\1\71\1\100\4\uffff\1\125\1\uffff"+
        "\1\131\6\uffff\1\154\1\155\1\uffff\1\25\1\27\1\uffff\1\37\7\uffff"+
        "\1\64\1\101\1\113\3\uffff\1\127\1\uffff\1\134\3\uffff\1\153\5\uffff"+
        "\1\45\1\47\3\uffff\1\115\5\uffff\1\151\10\uffff\1\116\6\uffff\1"+
        "\41\15\uffff\1\52\7\uffff\1\31\2\uffff\1\56\4\uffff\1\137\1\uffff"+
        "\1\42\5\uffff\1\22\4\uffff\1\136\1\uffff\1\65\1\130\1\uffff\1\46"+
        "\4\uffff\1\132";
    static final String DFA27_specialS =
        "\u027b\uffff}>";
    static final String[] DFA27_transitionS = {
            "\2\54\2\uffff\1\54\22\uffff\1\54\1\7\1\55\1\uffff\1\2\2\uffff"+
            "\1\55\1\42\1\51\1\5\1\3\1\52\1\4\1\13\1\46\12\56\1\1\1\53\1"+
            "\10\1\45\1\11\1\14\1\12\32\41\1\47\1\uffff\1\50\1\uffff\1\41"+
            "\1\uffff\1\15\1\16\1\17\1\20\1\21\1\22\1\23\1\41\1\24\2\41\1"+
            "\25\1\26\1\27\1\30\1\31\1\41\1\32\1\33\1\34\1\35\1\36\1\37\1"+
            "\40\2\41\1\43\1\6\1\44",
            "\1\60\2\uffff\1\57",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\64\32\uffff\1\63\1\62\1\uffff\1\65",
            "\1\67\1\70",
            "",
            "\1\72\1\uffff\12\73",
            "",
            "\1\75\4\uffff\1\76\1\77",
            "\1\100\15\uffff\1\101\11\uffff\1\102",
            "\1\103\6\uffff\1\104\6\uffff\1\105",
            "\1\106\3\uffff\1\107\5\uffff\1\110",
            "\1\111\1\112\1\113\2\uffff\1\114\4\uffff\1\115\1\uffff\1\116",
            "\1\117\5\uffff\1\120",
            "\1\121\14\uffff\1\122\1\uffff\1\123",
            "\1\124\1\uffff\1\125\6\uffff\1\126\1\127\4\uffff\1\130\1\131",
            "\1\132\3\uffff\1\133\16\uffff\1\134",
            "\1\135",
            "\1\136\3\uffff\1\137\11\uffff\1\140",
            "\1\141\11\uffff\1\142\1\uffff\1\143",
            "\1\144\20\uffff\1\145",
            "\1\146",
            "\1\147\1\uffff\1\150\1\uffff\1\151\11\uffff\1\152\4\uffff\1"+
            "\153",
            "\1\154\2\uffff\1\155\6\uffff\1\156\2\uffff\1\157\6\uffff\1\160",
            "\1\161",
            "\1\162\3\uffff\1\163",
            "\1\164",
            "\1\165",
            "",
            "\1\166\26\uffff\1\167",
            "",
            "",
            "",
            "\1\171",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\174\1\uffff\12\56\13\uffff\1\175\37\uffff\1\175",
            "",
            "",
            "",
            "",
            "",
            "\1\176\55\uffff\1\177",
            "",
            "",
            "",
            "",
            "",
            "",
            "\12\73\13\uffff\1\175\37\uffff\1\175",
            "",
            "\1\u0081\1\u0082",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\2\41"+
            "\1\u0083\27\41",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\23\41"+
            "\1\u0085\6\41",
            "\1\u0087",
            "\1\u0088",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u008a",
            "\1\u008b",
            "\1\u008c\1\u008d\1\u008e\1\uffff\1\u008f",
            "\1\u0090\2\uffff\1\u0091\14\uffff\1\u0092",
            "\1\u0093",
            "\1\u0094",
            "\1\u0095\15\uffff\1\u0096",
            "\1\u0097",
            "\1\u0098",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u009a",
            "\1\u009b\20\uffff\1\u009c",
            "\1\u009d\5\uffff\1\u009e",
            "\1\u009f",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u00a1",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u00a3",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u00a5",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\7\41"+
            "\1\u00a6\12\41\1\u00a7\1\u00a8\6\41",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u00ab",
            "\1\u00ac",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\1\u00ad"+
            "\22\41\1\u00ae\6\41",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u00b1",
            "\1\u00b2",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u00b4\66\uffff\1\u00b5",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u00b7",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\3\41"+
            "\1\u00b8\26\41",
            "\1\u00ba",
            "\1\u00bb\11\uffff\1\u00bc",
            "\1\u00bd",
            "\1\u00be",
            "\1\u00bf",
            "\1\u00c0",
            "\1\u00c1",
            "\1\u00c2\20\uffff\1\u00c3",
            "\1\u00c4",
            "\1\u00c5",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u00c7",
            "\1\u00c8",
            "\1\u00c9\5\uffff\1\u00ca",
            "\1\u00cb\5\uffff\1\u00cc",
            "\1\u00cd",
            "\1\u00ce",
            "\1\u00cf",
            "",
            "",
            "",
            "",
            "",
            "",
            "\12\u00d0\13\uffff\1\175\37\uffff\1\175",
            "",
            "",
            "",
            "",
            "\1\u00d1",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u00d3",
            "",
            "\1\u00d4",
            "",
            "\1\u00d5",
            "\1\u00d6",
            "",
            "\1\u00d7\16\uffff\1\u00d8",
            "\1\u00d9",
            "\1\u00da",
            "\1\u00db",
            "\1\u00dc",
            "\1\u00dd",
            "\1\u00de",
            "\1\u00df",
            "\1\u00e0",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u00e2",
            "\1\u00e3",
            "\1\u00e4",
            "\1\u00e5",
            "\1\u00e6",
            "",
            "\1\u00e7",
            "\1\u00e8",
            "\1\u00e9",
            "\1\u00ea",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u00ec",
            "",
            "\1\u00ed",
            "",
            "\1\u00ee",
            "",
            "\1\u00ef",
            "\1\u00f0",
            "\1\u00f1",
            "\1\u00f2",
            "",
            "",
            "\1\u00f3",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u00f5",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "",
            "",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\24\41"+
            "\1\u00f7\5\41",
            "\1\u00f9",
            "",
            "\1\u00fa\6\uffff\1\u00fb",
            "\1\u00fc",
            "",
            "\1\u00fd",
            "\1\u00fe",
            "",
            "\1\u00ff",
            "\1\u0100\17\uffff\1\u0101",
            "\1\u0102",
            "\1\u0103",
            "\1\u0104",
            "\1\u0105",
            "\1\u0106",
            "\1\u0107",
            "\1\u0108",
            "\1\u0109",
            "\1\u010a",
            "\1\u010b",
            "",
            "\1\u010c",
            "\1\u010d",
            "\1\u010e",
            "\1\u010f",
            "\1\u0110",
            "\1\u0111",
            "\1\u0112",
            "\1\u0113",
            "\1\u0114",
            "\12\u00d0\13\uffff\1\175\37\uffff\1\175",
            "\1\u0115",
            "",
            "\1\u0116",
            "\1\u0117",
            "\1\u0118",
            "\1\u0119",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\1\u011b"+
            "\31\41",
            "\1\u011d",
            "\1\u011e",
            "\1\u011f",
            "\1\u0120",
            "\1\u0121",
            "\1\u0122",
            "\1\u0123",
            "\1\u0124",
            "",
            "\1\u0125",
            "\1\u0126",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u0128",
            "\1\u0129",
            "\1\u012a",
            "\1\u012b",
            "\1\u012c",
            "\1\u012d",
            "",
            "\1\u012e",
            "\1\u012f",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u0131",
            "\1\u0132",
            "\1\u0133",
            "\1\u0134",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "",
            "\1\u0136",
            "",
            "\1\u0137",
            "",
            "\1\u0138",
            "\1\u0139",
            "\1\u013a",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u013c",
            "\1\u013d",
            "\1\u013e",
            "\1\u013f",
            "\1\u0140",
            "\1\u0141",
            "\1\u0142",
            "\1\u0143",
            "\1\u0144",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u0147",
            "\1\u0148\14\uffff\1\u0149",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u014c",
            "\1\u014d",
            "\1\u014e",
            "\1\u014f",
            "\1\u0150",
            "\1\u0151",
            "\1\u0152",
            "\1\u0153",
            "\1\u0154",
            "\1\u0155",
            "\1\u0156",
            "\1\u0157",
            "\1\u0158",
            "\1\u0159",
            "",
            "\1\u015a",
            "",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u015c",
            "\1\u015d",
            "\1\u015e",
            "\1\u015f",
            "\1\u0160",
            "\1\u0161",
            "\1\u0162",
            "\1\u0163",
            "\1\u0164",
            "",
            "\1\u0165\1\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff"+
            "\32\41",
            "\1\u0167",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u0169",
            "\1\u016a",
            "\1\u016b",
            "\1\u016c",
            "\1\u016d",
            "",
            "\1\u016e",
            "\1\u016f",
            "\1\u0170",
            "\1\u0171",
            "",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u0173",
            "\1\u0174",
            "\1\u0175",
            "\1\u0176",
            "",
            "\1\u0177",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\4\41"+
            "\1\u0178\3\41\1\u0179\21\41",
            "\1\u017b",
            "\1\u017c",
            "\1\u017d",
            "\1\u017e",
            "\1\u017f",
            "\1\u0180",
            "\1\u0181",
            "",
            "",
            "\1\u0182",
            "\1\u0183",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "",
            "",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u0186",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u0188",
            "\1\u0189",
            "\1\u018a",
            "\1\u018b",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u018d",
            "\1\u018e",
            "\1\u018f",
            "\1\u0190",
            "\1\u0191",
            "\1\u0192",
            "\1\u0193",
            "",
            "\1\u0194",
            "\1\u0195",
            "\1\u0196",
            "\1\u0197",
            "\1\u0198",
            "\1\u0199",
            "\1\u019a",
            "\1\u019b",
            "\1\u019c",
            "\1\u019d",
            "",
            "\1\u019e",
            "",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u01a0",
            "\1\u01a1",
            "\1\u01a2",
            "\1\u01a3",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u01a5",
            "\1\u01a6",
            "\1\u01a7",
            "",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u01a9",
            "\1\u01aa",
            "\1\u01ab",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u01ad",
            "\1\u01ae",
            "",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u01b0",
            "\1\u01b1",
            "\1\u01b2",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u01b4",
            "\1\u01b5\1\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff"+
            "\32\41",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "",
            "",
            "\1\u01b9",
            "",
            "\1\u01ba",
            "\1\u01bb",
            "\1\u01bc",
            "\1\u01bd",
            "",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u01bf",
            "\1\u01c0",
            "\1\u01c1",
            "\1\u01c2",
            "\1\u01c3",
            "\1\u01c4",
            "\1\u01c5",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u01c7",
            "\1\u01c8",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u01cb\7\uffff\1\u01cc",
            "\1\u01cd",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u01cf",
            "\1\u01d0",
            "",
            "\1\u01d1",
            "\1\u01d2",
            "\1\u01d3",
            "\1\u01d4",
            "",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u01d6",
            "\1\u01d7",
            "",
            "\1\u01d8",
            "\1\u01d9",
            "\1\u01da",
            "",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u01dc",
            "",
            "\1\u01dd",
            "\1\u01de",
            "\1\u01df",
            "",
            "\1\u01e0",
            "\1\u01e1\3\uffff\1\u01e2",
            "",
            "",
            "",
            "\1\u01e3",
            "\1\u01e4",
            "\1\u01e5",
            "\1\u01e6",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "",
            "\1\u01e8\1\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff"+
            "\32\41",
            "\1\u01ea",
            "\1\u01eb",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u01ed",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u01ef",
            "",
            "\1\u01f0",
            "\1\u01f1",
            "",
            "",
            "\1\u01f2",
            "\1\u01f3",
            "\1\u01f4\1\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff"+
            "\32\41",
            "",
            "\1\u01f6",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u01f9",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u01fd",
            "\1\u01fe",
            "\1\u01ff",
            "\1\u0200",
            "",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u0202",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u0204",
            "\1\u0205",
            "\1\u0206",
            "\1\u0207",
            "\1\u0208",
            "\1\u0209",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "",
            "\1\u020c",
            "",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "",
            "\1\u020f",
            "",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u0211",
            "\1\u0212",
            "\1\u0213",
            "\1\u0214",
            "\1\u0215",
            "",
            "\1\u0216",
            "",
            "",
            "\1\u0217\1\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff"+
            "\32\41",
            "",
            "",
            "",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u021b",
            "\1\u021c",
            "",
            "\1\u021d\1\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff"+
            "\32\41",
            "",
            "\1\u021f",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u0221",
            "\1\u0222",
            "\1\u0223",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "",
            "",
            "\1\u0225",
            "",
            "",
            "\1\u0226",
            "",
            "\1\u0227",
            "\1\u0228",
            "\1\u0229\1\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff"+
            "\32\41",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u022c",
            "\1\u022d",
            "\1\u022e",
            "",
            "",
            "",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u0230",
            "\1\u0231",
            "",
            "\1\u0232",
            "",
            "\1\u0233",
            "\1\u0234",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "",
            "\1\u0236",
            "\1\u0237",
            "\1\u0238",
            "\1\u0239",
            "\1\u023a",
            "",
            "",
            "\1\u023b",
            "\1\u023c",
            "\1\u023d",
            "",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u023f",
            "\1\u0240",
            "\1\u0241",
            "\1\u0242",
            "",
            "\1\u0243",
            "\1\u0244",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u0246",
            "\1\u0247",
            "\1\u0248",
            "\1\u0249",
            "\1\u024a",
            "",
            "\1\u024b",
            "\1\u024c",
            "\1\u024d",
            "\1\u024e",
            "\1\u024f",
            "\1\u0250",
            "",
            "\1\u0251",
            "\1\u0252",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u0254",
            "\1\u0255",
            "\1\u0256",
            "\1\u0257",
            "\1\u0258",
            "\1\u0259",
            "\1\u025a",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u025c",
            "\1\u025d",
            "",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u025f",
            "\1\u0260",
            "\1\u0261",
            "\1\u0262",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u0264",
            "",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u0266",
            "",
            "\1\u0267",
            "\1\u0268",
            "\1\u0269",
            "\1\u026a",
            "",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "",
            "\1\u026c",
            "\1\u026d",
            "\1\u026e",
            "\1\u026f",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "",
            "\1\u0271",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u0274",
            "",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "",
            "",
            "\1\u0276",
            "",
            "\1\u0277",
            "\1\u0278",
            "\1\u0279",
            "\2\41\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            ""
    };

    static final short[] DFA27_eot = DFA.unpackEncodedString(DFA27_eotS);
    static final short[] DFA27_eof = DFA.unpackEncodedString(DFA27_eofS);
    static final char[] DFA27_min = DFA.unpackEncodedStringToUnsignedChars(DFA27_minS);
    static final char[] DFA27_max = DFA.unpackEncodedStringToUnsignedChars(DFA27_maxS);
    static final short[] DFA27_accept = DFA.unpackEncodedString(DFA27_acceptS);
    static final short[] DFA27_special = DFA.unpackEncodedString(DFA27_specialS);
    static final short[][] DFA27_transition;

    static {
        int numStates = DFA27_transitionS.length;
        DFA27_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA27_transition[i] = DFA.unpackEncodedString(DFA27_transitionS[i]);
        }
    }

    class DFA27 extends DFA {

        public DFA27(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 27;
            this.eot = DFA27_eot;
            this.eof = DFA27_eof;
            this.min = DFA27_min;
            this.max = DFA27_max;
            this.accept = DFA27_accept;
            this.special = DFA27_special;
            this.transition = DFA27_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__145 | T__146 | T__147 | T__148 | T__149 | T__150 | T__151 | T__152 | T__153 | T__154 | T__155 | T__156 | T__157 | T__158 | T__159 | T__160 | ANCESTOR | ANCESTOR_OR_SELF | AND | AS | ASCENDING | AT | ATTRIBUTE | BASE_URI | BOUNDARY_SPACE | BY | CASE | CAST | CASTABLE | CHILD | COLLATION | COMMENT | CONSTRUCTION | COPY_NAMESPACES | DECLARE | DEFAULT | DESCENDANT | DESCENDANT_OR_SELF | DESCENDING | DIV | DOCUMENT | DOCUMENT_NODE | ELEMENT | ELSE | EMPTY | EMPTY_SEQUENCE | ENCODING | EQ | EVERY | EXCEPT | EXTERNAL | FOLLOWING | FOLLOWING_SIBLING | FOR | FUNCTION | GE | GREATEST | GT | IDIV | IF | IMPORT | IN | INHERIT | INSTANCE | INTERSECT | IS | ITEM | LAX | LE | LEAST | LET | LT | MOD | MODULE | NAMESPACE | NE | NO_INHERIT | NO_PRESERVE | NODE | OF | OPTION | OR | ORDER | ORDERED | ORDERING | PARENT | PRECEDING | PRECEDING_SIBLING | PRESERVE | PROCESSING_INSTRUCTION | RETURN | SATISFIES | SCHEMA | SCHEMA_ATTRIBUTE | SCHEMA_ELEMENT | SELF | SOME | STABLE | STRICT | STRIP | TEXT | THEN | TO | TREAT | TYPESWITCH | UNION | UNORDERED | VALIDATE | VARIABLE | VERSION | WHERE | XQUERY | NCName | DirCommentConstructor | DirPIConstructor | CDataSection | Pragma | LCURLY | RCURLY | OPEN_ANGLE | CLOSE_ANGLE | Lit_EQ | SLASH_SLASH | SLASH | LBRACKET | RBRACKET | LPAREN | RPAREN | COLON | COMMA | SEMICOLON | S | XQ_COMMENT | StringLiteral | IntegerLiteral | DoubleLiteral | DecimalLiteral );";
        }
    }
 

}