// $ANTLR 3.1b1 XQuery.g 2008-04-23 19:38:05

  package com.martinprobst.xqpretty;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import org.antlr.runtime.debug.*;
import java.io.IOException;

import org.antlr.runtime.tree.*;

public class XQueryParser extends DebugParserBase {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "CLOSE_TAG", "CLOSE_ANGLE", "OPEN_ANGLE", "EMPTY_CLOSE_TAG", "ElementContentChar", "ESCAPE_CURLY_OPEN", "ESCAPE_CURLY_CLOSE", "PredefinedEntityRef", "QuotAttrContentChar", "AposAttrContentChar", "ESCAPE_APOS", "ESCAPE_QUOT", "QUOT", "APOS", "XQUERY", "VERSION", "StringLiteral", "ENCODING", "MODULE", "NAMESPACE", "Lit_EQ", "SEMICOLON", "DECLARE", "BOUNDARY_SPACE", "PRESERVE", "STRIP", "DEFAULT", "ELEMENT", "FUNCTION", "OPTION", "ORDERING", "ORDERED", "UNORDERED", "ORDER", "EMPTY", "GREATEST", "LEAST", "COPY_NAMESPACES", "COMMA", "NO_PRESERVE", "INHERIT", "NO_INHERIT", "COLLATION", "BASE_URI", "IMPORT", "SCHEMA", "AT", "VARIABLE", "EXTERNAL", "CONSTRUCTION", "RPAREN", "AS", "LPAREN", "LCURLY", "RCURLY", "RETURN", "FOR", "IN", "LET", "WHERE", "BY", "STABLE", "ASCENDING", "DESCENDING", "SOME", "EVERY", "SATISFIES", "TYPESWITCH", "CASE", "IF", "THEN", "ELSE", "OR", "AND", "TO", "DIV", "IDIV", "MOD", "UNION", "INTERSECT", "EXCEPT", "INSTANCE", "OF", "TREAT", "CASTABLE", "CAST", "EQ", "NE", "LT", "LE", "GT", "GE", "IS", "VALIDATE", "LAX", "STRICT", "Pragma", "SLASH", "SLASH_SLASH", "CHILD", "DESCENDANT", "ATTRIBUTE", "SELF", "DESCENDANT_OR_SELF", "FOLLOWING_SIBLING", "FOLLOWING", "PARENT", "ANCESTOR", "PRECEDING_SIBLING", "PRECEDING", "ANCESTOR_OR_SELF", "COLON", "LBRACKET", "RBRACKET", "IntegerLiteral", "DecimalLiteral", "DoubleLiteral", "DirCommentConstructor", "DirPIConstructor", "S", "CDataSection", "CharRef", "DOCUMENT", "TEXT", "COMMENT", "PROCESSING_INSTRUCTION", "EMPTY_SEQUENCE", "ITEM", "NODE", "DOCUMENT_NODE", "SCHEMA_ATTRIBUTE", "SCHEMA_ELEMENT", "NCName", "NCNameStartChar", "NCNameChar", "Letter", "XMLDigit", "SUnprotected", "XQ_COMMENT", "Digits", "Char", "':='", "'$'", "'+'", "'-'", "'*'", "'|'", "'!='", "'<='", "'>='", "'<<'", "'>>'", "'::'", "'@'", "'..'", "'.'", "'?'"
    };
    public static final int COMMA=42;
    public static final int Lit_EQ=24;
    public static final int ELEMENT=31;
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
    public static final int NCNameStartChar=137;
    public static final int AposAttrContentChar=13;
    public static final int Letter=139;
    public static final int CASTABLE=88;
    public static final int ORDER=37;
    public static final int TO=78;
    public static final int AND=77;
    public static final int BASE_URI=47;
    public static final int LCURLY=57;
    public static final int FUNCTION=32;
    public static final int EMPTY_CLOSE_TAG=7;
    public static final int TYPESWITCH=71;
    public static final int RBRACKET=117;
    public static final int LE=93;
    public static final int MODULE=22;
    public static final int VERSION=19;
    public static final int RPAREN=54;
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
    public static final int LBRACKET=116;
    public static final int SATISFIES=70;
    public static final int MOD=81;
    public static final int EXCEPT=84;
    public static final int DESCENDANT=104;
    public static final int STRICT=99;
    public static final int OR=76;
    public static final int S=123;
    public static final int RCURLY=58;
    public static final int FOLLOWING_SIBLING=108;
    public static final int BY=64;
    public static final int STABLE=65;
    public static final int SCHEMA_ELEMENT=135;
    public static final int DoubleLiteral=120;
    public static final int VALIDATE=97;
    public static final int NO_INHERIT=45;
    public static final int PRECEDING=113;
    public static final int LPAREN=56;
    public static final int PRECEDING_SIBLING=112;
    public static final int ORDERING=34;
    public static final int COPY_NAMESPACES=41;
    public static final int NO_PRESERVE=43;
    public static final int UNORDERED=36;
    public static final int APOS=17;
    public static final int PredefinedEntityRef=11;
    public static final int EMPTY=38;
    public static final int T__153=153;
    public static final int INHERIT=44;
    public static final int ASCENDING=66;
    public static final int SLASH=101;
    public static final int ESCAPE_QUOT=15;
    public static final int IntegerLiteral=118;
    public static final int IS=96;
    public static final int GT=94;
    public static final int NE=91;
    public static final int COMMENT=128;
    public static final int StringLiteral=20;
    public static final int ESCAPE_APOS=14;
    public static final int DirCommentConstructor=121;
    public static final int ITEM=131;
    public static final int NCNameChar=138;
    public static final int ORDERED=35;
    public static final int T__152=152;
    public static final int PROCESSING_INSTRUCTION=129;
    public static final int IN=61;
    public static final int SOME=68;
    public static final int CharRef=125;
    public static final int COLLATION=46;
    public static final int SLASH_SLASH=102;
    public static final int ANCESTOR=111;
    public static final int T__147=147;
    public static final int RETURN=59;
    public static final int ESCAPE_CURLY_CLOSE=10;
    public static final int LET=62;
    public static final int IF=73;
    public static final int EOF=-1;
    public static final int VARIABLE=51;
    public static final int T__154=154;
    public static final int NODE=132;
    public static final int FOR=60;
    public static final int T__155=155;
    public static final int PRESERVE=28;
    public static final int DEFAULT=30;
    public static final int XMLDigit=140;
    public static final int ATTRIBUTE=105;
    public static final int CHILD=103;
    public static final int Digits=143;
    public static final int T__146=146;
    public static final int OPTION=33;

    // delegates
    // delegators

    public static final String[] ruleNames = new String[] {
        "invalidRule", "aposAttrValueContent", "elementName", "reverseStep", 
        "attributeName", "functionDeclPre", "nameTest", "typeswitchExpr", 
        "intersectExceptExpr", "boundarySpaceDecl", "orderingModeDecl", 
        "caseClause", "elseClause", "typeName", "relativePathExpr", "dirElemConstructor", 
        "schemaAttributeTest", "atomicType", "attributeDeclaration", "piTarget", 
        "ifExpr", "computedConstructor", "quotAttrValueContent", "declFuncName", 
        "castExpr", "castableExpr", "queryBody", "schemaPrefix", "attribNameOrWildcard", 
        "commonContent", "occurrenceIndicator", "ncName", "stepExpr", "numericLiteral", 
        "libraryModule", "attributeTest", "variable", "axisStep", "rangeExpr", 
        "compElemConstructor", "forClauseExt", "inheritMode", "versionDecl", 
        "orderSpec", "andExpr", "filterExpr", "dirAttributeValue", "pragma", 
        "defaultNamespaceDecl", "sequenceType", "primaryExpr", "generalComp", 
        "validateExpr", "compDocConstructor", "exprSingle", "module", "additiveExpr", 
        "unorderedExpr", "commentTest", "extensionExpr", "returnClause", 
        "paramClause", "schemaElementTest", "orderByClause", "comparisonExpr", 
        "defaultCollationDecl", "unionExpr", "abbrevReverseStep", "constructionDecl", 
        "elemEnclosedExpr", "baseURIDecl", "letClauseExt", "pITest", "parenthesizedExpr", 
        "forClause", "forwardAxis", "contentExpr", "synpred2_XQuery", "functionCall", 
        "dirAttributeList", "expr", "constructor", "valueExpr", "varName", 
        "orderedExpr", "contextItemExpr", "synpred1_XQuery", "itemType", 
        "positionalVar", "kindTest", "thenClause", "treatExpr", "reverseAxis", 
        "copyNamespacesDecl", "letClause", "abbrevForwardStep", "textTest", 
        "functionCallPre", "param", "funcName", "moduleImport", "enclosedExpr", 
        "directConstructor", "quantifiedExprExt", "varDecl", "compPIConstructor", 
        "prolog", "uRILiteral", "importDecl", "singleType", "paramList", 
        "valueComp", "fLWORExpr", "emptyOrderDecl", "unaryExpr", "compCommentConstructor", 
        "optionDecl", "mainModule", "schemaImport", "orderSpecList", "multiplicativeExpr", 
        "nodeComp", "elementDeclaration", "whereClause", "documentTest", 
        "separator", "varRef", "validationMode", "compAttrConstructor", 
        "literal", "predicateList", "orExpr", "forwardStep", "predicate", 
        "orderModifier", "namespaceDecl", "instanceofExpr", "pathExpr", 
        "qNameOrIdent", "anyKindTest", "compTextConstructor", "wildcard", 
        "funcKeyword", "elementTest", "typeDeclaration", "elementNameOrWildcard", 
        "quantifiedExpr", "functionDecl", "satisfiesClause", "defaultClause", 
        "preserveMode", "nodeTest", "setter", "dirElemContent", "moduleDecl"
    };
     
        public int ruleLevel = 0;
        public int getRuleLevel() { return ruleLevel; }
        public void incRuleLevel() { ruleLevel++; }
        public void decRuleLevel() { ruleLevel--; }
        public XQueryParser(TokenStream input) {
            this(input, DebugEventSocketProxy.DEFAULT_DEBUGGER_PORT, new RecognizerSharedState());
        }
        public XQueryParser(TokenStream input, int port, RecognizerSharedState state) {
            super(input, state);
            adaptor.setDebugListener(dbg);
            DebugEventSocketProxy proxy =
                new DebugEventSocketProxy(this,port,adaptor);
            adaptor.setDebugListener(proxy);
            setDebugListener(proxy);
            setTokenStream(new DebugTokenStream(input,proxy));
            try {
                proxy.handshake();
            }
            catch (IOException ioe) {
                reportError(ioe);
            }


        }
    public XQueryParser(TokenStream input, DebugEventListener dbg) {
        super(input, dbg);
        adaptor.setDebugListener(dbg);
    }
    protected boolean evalPredicate(boolean result, String predicate) {
        dbg.semanticPredicate(result, predicate);
        return result;
    }

    protected DebugTreeAdaptor adaptor =
        new DebugTreeAdaptor(null,new CommonTreeAdaptor());
    public void setTreeAdaptor(TreeAdaptor adaptor) {
        this.adaptor = new DebugTreeAdaptor(dbg,adaptor);
    }
    public TreeAdaptor getTreeAdaptor() {
        return adaptor;
    }


    public String[] getTokenNames() { return XQueryParser.tokenNames; }
    public String getGrammarFileName() { return "XQuery.g"; }


    public static class module_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start module
    // XQuery.g:31:1: module : ( versionDecl )? ( libraryModule | mainModule ) EOF ;
    public final XQueryParser.module_return module() throws RecognitionException {
        XQueryParser.module_return retval = new XQueryParser.module_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token EOF4=null;
        XQueryParser.versionDecl_return versionDecl1 = null;

        XQueryParser.libraryModule_return libraryModule2 = null;

        XQueryParser.mainModule_return mainModule3 = null;


        Object EOF4_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "module");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(31, 1);

        try {
            // XQuery.g:31:9: ( ( versionDecl )? ( libraryModule | mainModule ) EOF )
            dbg.enterAlt(1);

            // XQuery.g:31:11: ( versionDecl )? ( libraryModule | mainModule ) EOF
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(31,11);
            // XQuery.g:31:11: ( versionDecl )?
            int alt1=2;
            try { dbg.enterSubRule(1);
            try { dbg.enterDecision(1);

            int LA1_0 = input.LA(1);

            if ( (LA1_0==XQUERY) ) {
                int LA1_1 = input.LA(2);

                if ( (LA1_1==VERSION) ) {
                    alt1=1;
                }
            }
            } finally {dbg.exitDecision(1);}

            switch (alt1) {
                case 1 :
                    dbg.enterAlt(1);

                    // XQuery.g:31:11: versionDecl
                    {
                    dbg.location(31,11);
                    pushFollow(FOLLOW_versionDecl_in_module118);
                    versionDecl1=versionDecl();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, versionDecl1.getTree());
                    dbg.location(31,11);


                    }
                    break;

            }
            } finally {dbg.exitSubRule(1);}

            dbg.location(31,24);
            // XQuery.g:31:24: ( libraryModule | mainModule )
            int alt2=2;
            try { dbg.enterSubRule(2);
            try { dbg.enterDecision(2);

            int LA2_0 = input.LA(1);

            if ( (LA2_0==MODULE) ) {
                int LA2_1 = input.LA(2);

                if ( (LA2_1==NAMESPACE) ) {
                    alt2=1;
                }
                else if ( (LA2_1==EOF||(LA2_1>=CLOSE_ANGLE && LA2_1<=OPEN_ANGLE)||LA2_1==Lit_EQ||LA2_1==COMMA||LA2_1==LPAREN||(LA2_1>=OR && LA2_1<=INSTANCE)||(LA2_1>=TREAT && LA2_1<=IS)||(LA2_1>=SLASH && LA2_1<=SLASH_SLASH)||(LA2_1>=COLON && LA2_1<=LBRACKET)||(LA2_1>=147 && LA2_1<=155)) ) {
                    alt2=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 2, 1, input);

                    dbg.recognitionException(nvae);
                    throw nvae;
                }
            }
            else if ( (LA2_0==OPEN_ANGLE||(LA2_0>=XQUERY && LA2_0<=ENCODING)||LA2_0==NAMESPACE||(LA2_0>=DECLARE && LA2_0<=COPY_NAMESPACES)||(LA2_0>=NO_PRESERVE && LA2_0<=CONSTRUCTION)||(LA2_0>=AS && LA2_0<=LPAREN)||(LA2_0>=RETURN && LA2_0<=ANCESTOR_OR_SELF)||(LA2_0>=IntegerLiteral && LA2_0<=DirPIConstructor)||(LA2_0>=DOCUMENT && LA2_0<=NCName)||(LA2_0>=146 && LA2_0<=149)||(LA2_0>=157 && LA2_0<=159)) ) {
                alt2=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;
            }
            } finally {dbg.exitDecision(2);}

            switch (alt2) {
                case 1 :
                    dbg.enterAlt(1);

                    // XQuery.g:31:25: libraryModule
                    {
                    dbg.location(31,25);
                    pushFollow(FOLLOW_libraryModule_in_module122);
                    libraryModule2=libraryModule();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, libraryModule2.getTree());
                    dbg.location(31,41);


                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // XQuery.g:31:41: mainModule
                    {
                    dbg.location(31,41);
                    pushFollow(FOLLOW_mainModule_in_module126);
                    mainModule3=mainModule();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, mainModule3.getTree());
                    dbg.location(31,51);


                    }
                    break;

            }
            } finally {dbg.exitSubRule(2);}

            dbg.location(31,53);
            EOF4=(Token)input.LT(1);
            match(input,EOF,FOLLOW_EOF_in_module129); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            EOF4_tree = (Object)adaptor.create(EOF4);
            adaptor.addChild(root_0, EOF4_tree);
            }
            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(31, 56);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "module");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end module

    public static class versionDecl_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start versionDecl
    // XQuery.g:32:1: versionDecl : XQUERY VERSION StringLiteral ( ENCODING StringLiteral )? separator ;
    public final XQueryParser.versionDecl_return versionDecl() throws RecognitionException {
        XQueryParser.versionDecl_return retval = new XQueryParser.versionDecl_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token XQUERY5=null;
        Token VERSION6=null;
        Token StringLiteral7=null;
        Token ENCODING8=null;
        Token StringLiteral9=null;
        XQueryParser.separator_return separator10 = null;


        Object XQUERY5_tree=null;
        Object VERSION6_tree=null;
        Object StringLiteral7_tree=null;
        Object ENCODING8_tree=null;
        Object StringLiteral9_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "versionDecl");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(32, 1);

        try {
            // XQuery.g:32:13: ( XQUERY VERSION StringLiteral ( ENCODING StringLiteral )? separator )
            dbg.enterAlt(1);

            // XQuery.g:32:15: XQUERY VERSION StringLiteral ( ENCODING StringLiteral )? separator
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(32,15);
            XQUERY5=(Token)input.LT(1);
            match(input,XQUERY,FOLLOW_XQUERY_in_versionDecl136); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            XQUERY5_tree = (Object)adaptor.create(XQUERY5);
            adaptor.addChild(root_0, XQUERY5_tree);
            }
            dbg.location(32,22);
            VERSION6=(Token)input.LT(1);
            match(input,VERSION,FOLLOW_VERSION_in_versionDecl138); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            VERSION6_tree = (Object)adaptor.create(VERSION6);
            adaptor.addChild(root_0, VERSION6_tree);
            }
            dbg.location(32,30);
            StringLiteral7=(Token)input.LT(1);
            match(input,StringLiteral,FOLLOW_StringLiteral_in_versionDecl140); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            StringLiteral7_tree = (Object)adaptor.create(StringLiteral7);
            adaptor.addChild(root_0, StringLiteral7_tree);
            }
            dbg.location(32,44);
            // XQuery.g:32:44: ( ENCODING StringLiteral )?
            int alt3=2;
            try { dbg.enterSubRule(3);
            try { dbg.enterDecision(3);

            int LA3_0 = input.LA(1);

            if ( (LA3_0==ENCODING) ) {
                alt3=1;
            }
            } finally {dbg.exitDecision(3);}

            switch (alt3) {
                case 1 :
                    dbg.enterAlt(1);

                    // XQuery.g:32:45: ENCODING StringLiteral
                    {
                    dbg.location(32,45);
                    ENCODING8=(Token)input.LT(1);
                    match(input,ENCODING,FOLLOW_ENCODING_in_versionDecl143); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    ENCODING8_tree = (Object)adaptor.create(ENCODING8);
                    adaptor.addChild(root_0, ENCODING8_tree);
                    }
                    dbg.location(32,54);
                    StringLiteral9=(Token)input.LT(1);
                    match(input,StringLiteral,FOLLOW_StringLiteral_in_versionDecl145); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    StringLiteral9_tree = (Object)adaptor.create(StringLiteral9);
                    adaptor.addChild(root_0, StringLiteral9_tree);
                    }
                    dbg.location(32,67);


                    }
                    break;

            }
            } finally {dbg.exitSubRule(3);}

            dbg.location(32,70);
            pushFollow(FOLLOW_separator_in_versionDecl149);
            separator10=separator();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, separator10.getTree());
            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(32, 80);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "versionDecl");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end versionDecl

    public static class mainModule_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start mainModule
    // XQuery.g:33:1: mainModule : prolog queryBody ;
    public final XQueryParser.mainModule_return mainModule() throws RecognitionException {
        XQueryParser.mainModule_return retval = new XQueryParser.mainModule_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        XQueryParser.prolog_return prolog11 = null;

        XQueryParser.queryBody_return queryBody12 = null;



        try { dbg.enterRule(getGrammarFileName(), "mainModule");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(33, 1);

        try {
            // XQuery.g:33:12: ( prolog queryBody )
            dbg.enterAlt(1);

            // XQuery.g:33:14: prolog queryBody
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(33,14);
            pushFollow(FOLLOW_prolog_in_mainModule157);
            prolog11=prolog();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, prolog11.getTree());
            dbg.location(33,21);
            pushFollow(FOLLOW_queryBody_in_mainModule159);
            queryBody12=queryBody();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, queryBody12.getTree());
            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(33, 31);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "mainModule");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end mainModule

    public static class libraryModule_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start libraryModule
    // XQuery.g:34:1: libraryModule : moduleDecl prolog ;
    public final XQueryParser.libraryModule_return libraryModule() throws RecognitionException {
        XQueryParser.libraryModule_return retval = new XQueryParser.libraryModule_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        XQueryParser.moduleDecl_return moduleDecl13 = null;

        XQueryParser.prolog_return prolog14 = null;



        try { dbg.enterRule(getGrammarFileName(), "libraryModule");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(34, 1);

        try {
            // XQuery.g:34:15: ( moduleDecl prolog )
            dbg.enterAlt(1);

            // XQuery.g:34:17: moduleDecl prolog
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(34,17);
            pushFollow(FOLLOW_moduleDecl_in_libraryModule167);
            moduleDecl13=moduleDecl();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, moduleDecl13.getTree());
            dbg.location(34,28);
            pushFollow(FOLLOW_prolog_in_libraryModule169);
            prolog14=prolog();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, prolog14.getTree());
            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(34, 35);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "libraryModule");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end libraryModule

    public static class moduleDecl_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start moduleDecl
    // XQuery.g:35:1: moduleDecl : MODULE NAMESPACE ncName Lit_EQ uRILiteral separator ;
    public final XQueryParser.moduleDecl_return moduleDecl() throws RecognitionException {
        XQueryParser.moduleDecl_return retval = new XQueryParser.moduleDecl_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token MODULE15=null;
        Token NAMESPACE16=null;
        Token Lit_EQ18=null;
        XQueryParser.ncName_return ncName17 = null;

        XQueryParser.uRILiteral_return uRILiteral19 = null;

        XQueryParser.separator_return separator20 = null;


        Object MODULE15_tree=null;
        Object NAMESPACE16_tree=null;
        Object Lit_EQ18_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "moduleDecl");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(35, 1);

        try {
            // XQuery.g:35:12: ( MODULE NAMESPACE ncName Lit_EQ uRILiteral separator )
            dbg.enterAlt(1);

            // XQuery.g:35:14: MODULE NAMESPACE ncName Lit_EQ uRILiteral separator
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(35,14);
            MODULE15=(Token)input.LT(1);
            match(input,MODULE,FOLLOW_MODULE_in_moduleDecl177); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            MODULE15_tree = (Object)adaptor.create(MODULE15);
            adaptor.addChild(root_0, MODULE15_tree);
            }
            dbg.location(35,21);
            NAMESPACE16=(Token)input.LT(1);
            match(input,NAMESPACE,FOLLOW_NAMESPACE_in_moduleDecl179); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            NAMESPACE16_tree = (Object)adaptor.create(NAMESPACE16);
            adaptor.addChild(root_0, NAMESPACE16_tree);
            }
            dbg.location(35,31);
            pushFollow(FOLLOW_ncName_in_moduleDecl181);
            ncName17=ncName();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, ncName17.getTree());
            dbg.location(35,38);
            Lit_EQ18=(Token)input.LT(1);
            match(input,Lit_EQ,FOLLOW_Lit_EQ_in_moduleDecl183); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            Lit_EQ18_tree = (Object)adaptor.create(Lit_EQ18);
            adaptor.addChild(root_0, Lit_EQ18_tree);
            }
            dbg.location(35,45);
            pushFollow(FOLLOW_uRILiteral_in_moduleDecl185);
            uRILiteral19=uRILiteral();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, uRILiteral19.getTree());
            dbg.location(35,56);
            pushFollow(FOLLOW_separator_in_moduleDecl187);
            separator20=separator();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, separator20.getTree());
            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(35, 66);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "moduleDecl");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end moduleDecl

    public static class prolog_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start prolog
    // XQuery.g:36:1: prolog : ( ( defaultNamespaceDecl | setter | namespaceDecl | importDecl ) separator )* ( ( varDecl | functionDecl | optionDecl ) separator )* ;
    public final XQueryParser.prolog_return prolog() throws RecognitionException {
        XQueryParser.prolog_return retval = new XQueryParser.prolog_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        XQueryParser.defaultNamespaceDecl_return defaultNamespaceDecl21 = null;

        XQueryParser.setter_return setter22 = null;

        XQueryParser.namespaceDecl_return namespaceDecl23 = null;

        XQueryParser.importDecl_return importDecl24 = null;

        XQueryParser.separator_return separator25 = null;

        XQueryParser.varDecl_return varDecl26 = null;

        XQueryParser.functionDecl_return functionDecl27 = null;

        XQueryParser.optionDecl_return optionDecl28 = null;

        XQueryParser.separator_return separator29 = null;



        try { dbg.enterRule(getGrammarFileName(), "prolog");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(36, 1);

        try {
            // XQuery.g:36:9: ( ( ( defaultNamespaceDecl | setter | namespaceDecl | importDecl ) separator )* ( ( varDecl | functionDecl | optionDecl ) separator )* )
            dbg.enterAlt(1);

            // XQuery.g:36:11: ( ( defaultNamespaceDecl | setter | namespaceDecl | importDecl ) separator )* ( ( varDecl | functionDecl | optionDecl ) separator )*
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(36,11);
            // XQuery.g:36:11: ( ( defaultNamespaceDecl | setter | namespaceDecl | importDecl ) separator )*
            try { dbg.enterSubRule(5);

            loop5:
            do {
                int alt5=2;
                try { dbg.enterDecision(5);

                int LA5_0 = input.LA(1);

                if ( (LA5_0==DECLARE) ) {
                    int LA5_1 = input.LA(2);

                    if ( (LA5_1==NAMESPACE||LA5_1==BOUNDARY_SPACE||LA5_1==DEFAULT||LA5_1==ORDERING||LA5_1==COPY_NAMESPACES||LA5_1==BASE_URI||LA5_1==CONSTRUCTION) ) {
                        alt5=1;
                    }


                }
                else if ( (LA5_0==IMPORT) ) {
                    int LA5_3 = input.LA(2);

                    if ( (LA5_3==MODULE||LA5_3==SCHEMA) ) {
                        alt5=1;
                    }


                }


                } finally {dbg.exitDecision(5);}

                switch (alt5) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // XQuery.g:36:12: ( defaultNamespaceDecl | setter | namespaceDecl | importDecl ) separator
            	    {
            	    dbg.location(36,12);
            	    // XQuery.g:36:12: ( defaultNamespaceDecl | setter | namespaceDecl | importDecl )
            	    int alt4=4;
            	    try { dbg.enterSubRule(4);
            	    try { dbg.enterDecision(4);

            	    int LA4_0 = input.LA(1);

            	    if ( (LA4_0==DECLARE) ) {
            	        switch ( input.LA(2) ) {
            	        case DEFAULT:
            	            {
            	            int LA4_3 = input.LA(3);

            	            if ( ((LA4_3>=ELEMENT && LA4_3<=FUNCTION)) ) {
            	                alt4=1;
            	            }
            	            else if ( (LA4_3==ORDER||LA4_3==COLLATION) ) {
            	                alt4=2;
            	            }
            	            else {
            	                if (state.backtracking>0) {state.failed=true; return retval;}
            	                NoViableAltException nvae =
            	                    new NoViableAltException("", 4, 3, input);

            	                dbg.recognitionException(nvae);
            	                throw nvae;
            	            }
            	            }
            	            break;
            	        case BOUNDARY_SPACE:
            	        case ORDERING:
            	        case COPY_NAMESPACES:
            	        case BASE_URI:
            	        case CONSTRUCTION:
            	            {
            	            alt4=2;
            	            }
            	            break;
            	        case NAMESPACE:
            	            {
            	            alt4=3;
            	            }
            	            break;
            	        default:
            	            if (state.backtracking>0) {state.failed=true; return retval;}
            	            NoViableAltException nvae =
            	                new NoViableAltException("", 4, 1, input);

            	            dbg.recognitionException(nvae);
            	            throw nvae;
            	        }

            	    }
            	    else if ( (LA4_0==IMPORT) ) {
            	        alt4=4;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 4, 0, input);

            	        dbg.recognitionException(nvae);
            	        throw nvae;
            	    }
            	    } finally {dbg.exitDecision(4);}

            	    switch (alt4) {
            	        case 1 :
            	            dbg.enterAlt(1);

            	            // XQuery.g:36:13: defaultNamespaceDecl
            	            {
            	            dbg.location(36,13);
            	            pushFollow(FOLLOW_defaultNamespaceDecl_in_prolog198);
            	            defaultNamespaceDecl21=defaultNamespaceDecl();

            	            state._fsp--;
            	            if (state.failed) return retval;
            	            if ( state.backtracking==0 ) adaptor.addChild(root_0, defaultNamespaceDecl21.getTree());
            	            dbg.location(36,36);


            	            }
            	            break;
            	        case 2 :
            	            dbg.enterAlt(2);

            	            // XQuery.g:36:36: setter
            	            {
            	            dbg.location(36,36);
            	            pushFollow(FOLLOW_setter_in_prolog202);
            	            setter22=setter();

            	            state._fsp--;
            	            if (state.failed) return retval;
            	            if ( state.backtracking==0 ) adaptor.addChild(root_0, setter22.getTree());
            	            dbg.location(36,45);


            	            }
            	            break;
            	        case 3 :
            	            dbg.enterAlt(3);

            	            // XQuery.g:36:45: namespaceDecl
            	            {
            	            dbg.location(36,45);
            	            pushFollow(FOLLOW_namespaceDecl_in_prolog206);
            	            namespaceDecl23=namespaceDecl();

            	            state._fsp--;
            	            if (state.failed) return retval;
            	            if ( state.backtracking==0 ) adaptor.addChild(root_0, namespaceDecl23.getTree());
            	            dbg.location(36,61);


            	            }
            	            break;
            	        case 4 :
            	            dbg.enterAlt(4);

            	            // XQuery.g:36:61: importDecl
            	            {
            	            dbg.location(36,61);
            	            pushFollow(FOLLOW_importDecl_in_prolog210);
            	            importDecl24=importDecl();

            	            state._fsp--;
            	            if (state.failed) return retval;
            	            if ( state.backtracking==0 ) adaptor.addChild(root_0, importDecl24.getTree());
            	            dbg.location(36,71);


            	            }
            	            break;

            	    }
            	    } finally {dbg.exitSubRule(4);}

            	    dbg.location(36,73);
            	    pushFollow(FOLLOW_separator_in_prolog213);
            	    separator25=separator();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, separator25.getTree());
            	    dbg.location(36,82);


            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);
            } finally {dbg.exitSubRule(5);}

            dbg.location(36,85);
            // XQuery.g:36:85: ( ( varDecl | functionDecl | optionDecl ) separator )*
            try { dbg.enterSubRule(7);

            loop7:
            do {
                int alt7=2;
                try { dbg.enterDecision(7);

                int LA7_0 = input.LA(1);

                if ( (LA7_0==DECLARE) ) {
                    int LA7_2 = input.LA(2);

                    if ( ((LA7_2>=FUNCTION && LA7_2<=OPTION)||LA7_2==VARIABLE) ) {
                        alt7=1;
                    }


                }


                } finally {dbg.exitDecision(7);}

                switch (alt7) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // XQuery.g:36:86: ( varDecl | functionDecl | optionDecl ) separator
            	    {
            	    dbg.location(36,86);
            	    // XQuery.g:36:86: ( varDecl | functionDecl | optionDecl )
            	    int alt6=3;
            	    try { dbg.enterSubRule(6);
            	    try { dbg.enterDecision(6);

            	    int LA6_0 = input.LA(1);

            	    if ( (LA6_0==DECLARE) ) {
            	        switch ( input.LA(2) ) {
            	        case VARIABLE:
            	            {
            	            alt6=1;
            	            }
            	            break;
            	        case FUNCTION:
            	            {
            	            alt6=2;
            	            }
            	            break;
            	        case OPTION:
            	            {
            	            alt6=3;
            	            }
            	            break;
            	        default:
            	            if (state.backtracking>0) {state.failed=true; return retval;}
            	            NoViableAltException nvae =
            	                new NoViableAltException("", 6, 1, input);

            	            dbg.recognitionException(nvae);
            	            throw nvae;
            	        }

            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 6, 0, input);

            	        dbg.recognitionException(nvae);
            	        throw nvae;
            	    }
            	    } finally {dbg.exitDecision(6);}

            	    switch (alt6) {
            	        case 1 :
            	            dbg.enterAlt(1);

            	            // XQuery.g:36:87: varDecl
            	            {
            	            dbg.location(36,87);
            	            pushFollow(FOLLOW_varDecl_in_prolog219);
            	            varDecl26=varDecl();

            	            state._fsp--;
            	            if (state.failed) return retval;
            	            if ( state.backtracking==0 ) adaptor.addChild(root_0, varDecl26.getTree());
            	            dbg.location(36,97);


            	            }
            	            break;
            	        case 2 :
            	            dbg.enterAlt(2);

            	            // XQuery.g:36:97: functionDecl
            	            {
            	            dbg.location(36,97);
            	            pushFollow(FOLLOW_functionDecl_in_prolog223);
            	            functionDecl27=functionDecl();

            	            state._fsp--;
            	            if (state.failed) return retval;
            	            if ( state.backtracking==0 ) adaptor.addChild(root_0, functionDecl27.getTree());
            	            dbg.location(36,112);


            	            }
            	            break;
            	        case 3 :
            	            dbg.enterAlt(3);

            	            // XQuery.g:36:112: optionDecl
            	            {
            	            dbg.location(36,112);
            	            pushFollow(FOLLOW_optionDecl_in_prolog227);
            	            optionDecl28=optionDecl();

            	            state._fsp--;
            	            if (state.failed) return retval;
            	            if ( state.backtracking==0 ) adaptor.addChild(root_0, optionDecl28.getTree());
            	            dbg.location(36,122);


            	            }
            	            break;

            	    }
            	    } finally {dbg.exitSubRule(6);}

            	    dbg.location(36,124);
            	    pushFollow(FOLLOW_separator_in_prolog230);
            	    separator29=separator();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, separator29.getTree());
            	    dbg.location(36,133);


            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);
            } finally {dbg.exitSubRule(7);}

            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(36, 136);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "prolog");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end prolog

    public static class setter_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start setter
    // XQuery.g:37:1: setter : ( boundarySpaceDecl | defaultCollationDecl | baseURIDecl | constructionDecl | orderingModeDecl | emptyOrderDecl | copyNamespacesDecl );
    public final XQueryParser.setter_return setter() throws RecognitionException {
        XQueryParser.setter_return retval = new XQueryParser.setter_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        XQueryParser.boundarySpaceDecl_return boundarySpaceDecl30 = null;

        XQueryParser.defaultCollationDecl_return defaultCollationDecl31 = null;

        XQueryParser.baseURIDecl_return baseURIDecl32 = null;

        XQueryParser.constructionDecl_return constructionDecl33 = null;

        XQueryParser.orderingModeDecl_return orderingModeDecl34 = null;

        XQueryParser.emptyOrderDecl_return emptyOrderDecl35 = null;

        XQueryParser.copyNamespacesDecl_return copyNamespacesDecl36 = null;



        try { dbg.enterRule(getGrammarFileName(), "setter");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(37, 1);

        try {
            // XQuery.g:37:9: ( boundarySpaceDecl | defaultCollationDecl | baseURIDecl | constructionDecl | orderingModeDecl | emptyOrderDecl | copyNamespacesDecl )
            int alt8=7;
            try { dbg.enterDecision(8);

            try {
                isCyclicDecision = true;
                alt8 = dfa8.predict(input);
            }
            catch (NoViableAltException nvae) {
                dbg.recognitionException(nvae);
                throw nvae;
            }
            } finally {dbg.exitDecision(8);}

            switch (alt8) {
                case 1 :
                    dbg.enterAlt(1);

                    // XQuery.g:37:11: boundarySpaceDecl
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(37,11);
                    pushFollow(FOLLOW_boundarySpaceDecl_in_setter241);
                    boundarySpaceDecl30=boundarySpaceDecl();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, boundarySpaceDecl30.getTree());
                    dbg.location(37,31);


                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // XQuery.g:37:31: defaultCollationDecl
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(37,31);
                    pushFollow(FOLLOW_defaultCollationDecl_in_setter245);
                    defaultCollationDecl31=defaultCollationDecl();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, defaultCollationDecl31.getTree());
                    dbg.location(37,54);


                    }
                    break;
                case 3 :
                    dbg.enterAlt(3);

                    // XQuery.g:37:54: baseURIDecl
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(37,54);
                    pushFollow(FOLLOW_baseURIDecl_in_setter249);
                    baseURIDecl32=baseURIDecl();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, baseURIDecl32.getTree());
                    dbg.location(37,68);


                    }
                    break;
                case 4 :
                    dbg.enterAlt(4);

                    // XQuery.g:37:68: constructionDecl
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(37,68);
                    pushFollow(FOLLOW_constructionDecl_in_setter253);
                    constructionDecl33=constructionDecl();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, constructionDecl33.getTree());
                    dbg.location(37,87);


                    }
                    break;
                case 5 :
                    dbg.enterAlt(5);

                    // XQuery.g:37:87: orderingModeDecl
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(37,87);
                    pushFollow(FOLLOW_orderingModeDecl_in_setter257);
                    orderingModeDecl34=orderingModeDecl();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, orderingModeDecl34.getTree());
                    dbg.location(37,106);


                    }
                    break;
                case 6 :
                    dbg.enterAlt(6);

                    // XQuery.g:37:106: emptyOrderDecl
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(37,106);
                    pushFollow(FOLLOW_emptyOrderDecl_in_setter261);
                    emptyOrderDecl35=emptyOrderDecl();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, emptyOrderDecl35.getTree());
                    dbg.location(37,123);


                    }
                    break;
                case 7 :
                    dbg.enterAlt(7);

                    // XQuery.g:37:123: copyNamespacesDecl
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(37,123);
                    pushFollow(FOLLOW_copyNamespacesDecl_in_setter265);
                    copyNamespacesDecl36=copyNamespacesDecl();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, copyNamespacesDecl36.getTree());
                    dbg.location(0,0);


                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(37, 142);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "setter");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end setter

    public static class importDecl_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start importDecl
    // XQuery.g:38:1: importDecl : ( schemaImport | moduleImport );
    public final XQueryParser.importDecl_return importDecl() throws RecognitionException {
        XQueryParser.importDecl_return retval = new XQueryParser.importDecl_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        XQueryParser.schemaImport_return schemaImport37 = null;

        XQueryParser.moduleImport_return moduleImport38 = null;



        try { dbg.enterRule(getGrammarFileName(), "importDecl");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(38, 1);

        try {
            // XQuery.g:38:12: ( schemaImport | moduleImport )
            int alt9=2;
            try { dbg.enterDecision(9);

            int LA9_0 = input.LA(1);

            if ( (LA9_0==IMPORT) ) {
                int LA9_1 = input.LA(2);

                if ( (LA9_1==SCHEMA) ) {
                    alt9=1;
                }
                else if ( (LA9_1==MODULE) ) {
                    alt9=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 9, 1, input);

                    dbg.recognitionException(nvae);
                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;
            }
            } finally {dbg.exitDecision(9);}

            switch (alt9) {
                case 1 :
                    dbg.enterAlt(1);

                    // XQuery.g:38:14: schemaImport
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(38,14);
                    pushFollow(FOLLOW_schemaImport_in_importDecl273);
                    schemaImport37=schemaImport();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, schemaImport37.getTree());
                    dbg.location(38,29);


                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // XQuery.g:38:29: moduleImport
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(38,29);
                    pushFollow(FOLLOW_moduleImport_in_importDecl277);
                    moduleImport38=moduleImport();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, moduleImport38.getTree());
                    dbg.location(0,0);


                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(38, 42);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "importDecl");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end importDecl

    public static class separator_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start separator
    // XQuery.g:39:1: separator : SEMICOLON ;
    public final XQueryParser.separator_return separator() throws RecognitionException {
        XQueryParser.separator_return retval = new XQueryParser.separator_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token SEMICOLON39=null;

        Object SEMICOLON39_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "separator");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(39, 1);

        try {
            // XQuery.g:39:11: ( SEMICOLON )
            dbg.enterAlt(1);

            // XQuery.g:39:13: SEMICOLON
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(39,13);
            SEMICOLON39=(Token)input.LT(1);
            match(input,SEMICOLON,FOLLOW_SEMICOLON_in_separator285); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            SEMICOLON39_tree = (Object)adaptor.create(SEMICOLON39);
            adaptor.addChild(root_0, SEMICOLON39_tree);
            }
            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(39, 23);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "separator");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end separator

    public static class namespaceDecl_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start namespaceDecl
    // XQuery.g:40:1: namespaceDecl : DECLARE NAMESPACE ncName Lit_EQ uRILiteral ;
    public final XQueryParser.namespaceDecl_return namespaceDecl() throws RecognitionException {
        XQueryParser.namespaceDecl_return retval = new XQueryParser.namespaceDecl_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token DECLARE40=null;
        Token NAMESPACE41=null;
        Token Lit_EQ43=null;
        XQueryParser.ncName_return ncName42 = null;

        XQueryParser.uRILiteral_return uRILiteral44 = null;


        Object DECLARE40_tree=null;
        Object NAMESPACE41_tree=null;
        Object Lit_EQ43_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "namespaceDecl");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(40, 1);

        try {
            // XQuery.g:40:15: ( DECLARE NAMESPACE ncName Lit_EQ uRILiteral )
            dbg.enterAlt(1);

            // XQuery.g:40:17: DECLARE NAMESPACE ncName Lit_EQ uRILiteral
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(40,17);
            DECLARE40=(Token)input.LT(1);
            match(input,DECLARE,FOLLOW_DECLARE_in_namespaceDecl293); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            DECLARE40_tree = (Object)adaptor.create(DECLARE40);
            adaptor.addChild(root_0, DECLARE40_tree);
            }
            dbg.location(40,25);
            NAMESPACE41=(Token)input.LT(1);
            match(input,NAMESPACE,FOLLOW_NAMESPACE_in_namespaceDecl295); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            NAMESPACE41_tree = (Object)adaptor.create(NAMESPACE41);
            adaptor.addChild(root_0, NAMESPACE41_tree);
            }
            dbg.location(40,35);
            pushFollow(FOLLOW_ncName_in_namespaceDecl297);
            ncName42=ncName();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, ncName42.getTree());
            dbg.location(40,42);
            Lit_EQ43=(Token)input.LT(1);
            match(input,Lit_EQ,FOLLOW_Lit_EQ_in_namespaceDecl299); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            Lit_EQ43_tree = (Object)adaptor.create(Lit_EQ43);
            adaptor.addChild(root_0, Lit_EQ43_tree);
            }
            dbg.location(40,49);
            pushFollow(FOLLOW_uRILiteral_in_namespaceDecl301);
            uRILiteral44=uRILiteral();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, uRILiteral44.getTree());
            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(40, 60);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "namespaceDecl");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end namespaceDecl

    public static class boundarySpaceDecl_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start boundarySpaceDecl
    // XQuery.g:41:1: boundarySpaceDecl : DECLARE BOUNDARY_SPACE ( PRESERVE | STRIP ) ;
    public final XQueryParser.boundarySpaceDecl_return boundarySpaceDecl() throws RecognitionException {
        XQueryParser.boundarySpaceDecl_return retval = new XQueryParser.boundarySpaceDecl_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token DECLARE45=null;
        Token BOUNDARY_SPACE46=null;
        Token set47=null;

        Object DECLARE45_tree=null;
        Object BOUNDARY_SPACE46_tree=null;
        Object set47_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "boundarySpaceDecl");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(41, 1);

        try {
            // XQuery.g:42:3: ( DECLARE BOUNDARY_SPACE ( PRESERVE | STRIP ) )
            dbg.enterAlt(1);

            // XQuery.g:42:5: DECLARE BOUNDARY_SPACE ( PRESERVE | STRIP )
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(42,5);
            DECLARE45=(Token)input.LT(1);
            match(input,DECLARE,FOLLOW_DECLARE_in_boundarySpaceDecl312); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            DECLARE45_tree = (Object)adaptor.create(DECLARE45);
            adaptor.addChild(root_0, DECLARE45_tree);
            }
            dbg.location(42,13);
            BOUNDARY_SPACE46=(Token)input.LT(1);
            match(input,BOUNDARY_SPACE,FOLLOW_BOUNDARY_SPACE_in_boundarySpaceDecl314); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            BOUNDARY_SPACE46_tree = (Object)adaptor.create(BOUNDARY_SPACE46);
            adaptor.addChild(root_0, BOUNDARY_SPACE46_tree);
            }
            dbg.location(42,28);
            set47=(Token)input.LT(1);
            if ( (input.LA(1)>=PRESERVE && input.LA(1)<=STRIP) ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set47));
                state.errorRecovery=false;state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                dbg.recognitionException(mse);
                throw mse;
            }

            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(42, 47);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "boundarySpaceDecl");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end boundarySpaceDecl

    public static class defaultNamespaceDecl_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start defaultNamespaceDecl
    // XQuery.g:43:1: defaultNamespaceDecl : DECLARE DEFAULT ( ELEMENT | FUNCTION ) NAMESPACE uRILiteral ;
    public final XQueryParser.defaultNamespaceDecl_return defaultNamespaceDecl() throws RecognitionException {
        XQueryParser.defaultNamespaceDecl_return retval = new XQueryParser.defaultNamespaceDecl_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token DECLARE48=null;
        Token DEFAULT49=null;
        Token set50=null;
        Token NAMESPACE51=null;
        XQueryParser.uRILiteral_return uRILiteral52 = null;


        Object DECLARE48_tree=null;
        Object DEFAULT49_tree=null;
        Object set50_tree=null;
        Object NAMESPACE51_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "defaultNamespaceDecl");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(43, 1);

        try {
            // XQuery.g:44:3: ( DECLARE DEFAULT ( ELEMENT | FUNCTION ) NAMESPACE uRILiteral )
            dbg.enterAlt(1);

            // XQuery.g:44:5: DECLARE DEFAULT ( ELEMENT | FUNCTION ) NAMESPACE uRILiteral
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(44,5);
            DECLARE48=(Token)input.LT(1);
            match(input,DECLARE,FOLLOW_DECLARE_in_defaultNamespaceDecl332); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            DECLARE48_tree = (Object)adaptor.create(DECLARE48);
            adaptor.addChild(root_0, DECLARE48_tree);
            }
            dbg.location(44,13);
            DEFAULT49=(Token)input.LT(1);
            match(input,DEFAULT,FOLLOW_DEFAULT_in_defaultNamespaceDecl334); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            DEFAULT49_tree = (Object)adaptor.create(DEFAULT49);
            adaptor.addChild(root_0, DEFAULT49_tree);
            }
            dbg.location(44,21);
            set50=(Token)input.LT(1);
            if ( (input.LA(1)>=ELEMENT && input.LA(1)<=FUNCTION) ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set50));
                state.errorRecovery=false;state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                dbg.recognitionException(mse);
                throw mse;
            }

            dbg.location(44,42);
            NAMESPACE51=(Token)input.LT(1);
            match(input,NAMESPACE,FOLLOW_NAMESPACE_in_defaultNamespaceDecl344); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            NAMESPACE51_tree = (Object)adaptor.create(NAMESPACE51);
            adaptor.addChild(root_0, NAMESPACE51_tree);
            }
            dbg.location(44,52);
            pushFollow(FOLLOW_uRILiteral_in_defaultNamespaceDecl346);
            uRILiteral52=uRILiteral();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, uRILiteral52.getTree());
            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(44, 63);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "defaultNamespaceDecl");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end defaultNamespaceDecl

    public static class optionDecl_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start optionDecl
    // XQuery.g:45:1: optionDecl : DECLARE OPTION qNameOrIdent StringLiteral ;
    public final XQueryParser.optionDecl_return optionDecl() throws RecognitionException {
        XQueryParser.optionDecl_return retval = new XQueryParser.optionDecl_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token DECLARE53=null;
        Token OPTION54=null;
        Token StringLiteral56=null;
        XQueryParser.qNameOrIdent_return qNameOrIdent55 = null;


        Object DECLARE53_tree=null;
        Object OPTION54_tree=null;
        Object StringLiteral56_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "optionDecl");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(45, 1);

        try {
            // XQuery.g:45:12: ( DECLARE OPTION qNameOrIdent StringLiteral )
            dbg.enterAlt(1);

            // XQuery.g:45:14: DECLARE OPTION qNameOrIdent StringLiteral
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(45,14);
            DECLARE53=(Token)input.LT(1);
            match(input,DECLARE,FOLLOW_DECLARE_in_optionDecl354); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            DECLARE53_tree = (Object)adaptor.create(DECLARE53);
            adaptor.addChild(root_0, DECLARE53_tree);
            }
            dbg.location(45,22);
            OPTION54=(Token)input.LT(1);
            match(input,OPTION,FOLLOW_OPTION_in_optionDecl356); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            OPTION54_tree = (Object)adaptor.create(OPTION54);
            adaptor.addChild(root_0, OPTION54_tree);
            }
            dbg.location(45,29);
            pushFollow(FOLLOW_qNameOrIdent_in_optionDecl358);
            qNameOrIdent55=qNameOrIdent();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, qNameOrIdent55.getTree());
            dbg.location(45,42);
            StringLiteral56=(Token)input.LT(1);
            match(input,StringLiteral,FOLLOW_StringLiteral_in_optionDecl360); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            StringLiteral56_tree = (Object)adaptor.create(StringLiteral56);
            adaptor.addChild(root_0, StringLiteral56_tree);
            }
            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(45, 56);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "optionDecl");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end optionDecl

    public static class orderingModeDecl_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start orderingModeDecl
    // XQuery.g:46:1: orderingModeDecl : DECLARE ORDERING ( ORDERED | UNORDERED ) ;
    public final XQueryParser.orderingModeDecl_return orderingModeDecl() throws RecognitionException {
        XQueryParser.orderingModeDecl_return retval = new XQueryParser.orderingModeDecl_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token DECLARE57=null;
        Token ORDERING58=null;
        Token set59=null;

        Object DECLARE57_tree=null;
        Object ORDERING58_tree=null;
        Object set59_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "orderingModeDecl");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(46, 1);

        try {
            // XQuery.g:47:3: ( DECLARE ORDERING ( ORDERED | UNORDERED ) )
            dbg.enterAlt(1);

            // XQuery.g:47:5: DECLARE ORDERING ( ORDERED | UNORDERED )
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(47,5);
            DECLARE57=(Token)input.LT(1);
            match(input,DECLARE,FOLLOW_DECLARE_in_orderingModeDecl370); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            DECLARE57_tree = (Object)adaptor.create(DECLARE57);
            adaptor.addChild(root_0, DECLARE57_tree);
            }
            dbg.location(47,13);
            ORDERING58=(Token)input.LT(1);
            match(input,ORDERING,FOLLOW_ORDERING_in_orderingModeDecl372); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            ORDERING58_tree = (Object)adaptor.create(ORDERING58);
            adaptor.addChild(root_0, ORDERING58_tree);
            }
            dbg.location(47,22);
            set59=(Token)input.LT(1);
            if ( (input.LA(1)>=ORDERED && input.LA(1)<=UNORDERED) ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set59));
                state.errorRecovery=false;state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                dbg.recognitionException(mse);
                throw mse;
            }

            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(47, 44);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "orderingModeDecl");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end orderingModeDecl

    public static class emptyOrderDecl_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start emptyOrderDecl
    // XQuery.g:48:1: emptyOrderDecl : DECLARE DEFAULT ORDER EMPTY ( GREATEST | LEAST ) ;
    public final XQueryParser.emptyOrderDecl_return emptyOrderDecl() throws RecognitionException {
        XQueryParser.emptyOrderDecl_return retval = new XQueryParser.emptyOrderDecl_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token DECLARE60=null;
        Token DEFAULT61=null;
        Token ORDER62=null;
        Token EMPTY63=null;
        Token set64=null;

        Object DECLARE60_tree=null;
        Object DEFAULT61_tree=null;
        Object ORDER62_tree=null;
        Object EMPTY63_tree=null;
        Object set64_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "emptyOrderDecl");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(48, 1);

        try {
            // XQuery.g:48:16: ( DECLARE DEFAULT ORDER EMPTY ( GREATEST | LEAST ) )
            dbg.enterAlt(1);

            // XQuery.g:48:18: DECLARE DEFAULT ORDER EMPTY ( GREATEST | LEAST )
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(48,18);
            DECLARE60=(Token)input.LT(1);
            match(input,DECLARE,FOLLOW_DECLARE_in_emptyOrderDecl388); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            DECLARE60_tree = (Object)adaptor.create(DECLARE60);
            adaptor.addChild(root_0, DECLARE60_tree);
            }
            dbg.location(48,26);
            DEFAULT61=(Token)input.LT(1);
            match(input,DEFAULT,FOLLOW_DEFAULT_in_emptyOrderDecl390); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            DEFAULT61_tree = (Object)adaptor.create(DEFAULT61);
            adaptor.addChild(root_0, DEFAULT61_tree);
            }
            dbg.location(48,34);
            ORDER62=(Token)input.LT(1);
            match(input,ORDER,FOLLOW_ORDER_in_emptyOrderDecl392); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            ORDER62_tree = (Object)adaptor.create(ORDER62);
            adaptor.addChild(root_0, ORDER62_tree);
            }
            dbg.location(48,40);
            EMPTY63=(Token)input.LT(1);
            match(input,EMPTY,FOLLOW_EMPTY_in_emptyOrderDecl394); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            EMPTY63_tree = (Object)adaptor.create(EMPTY63);
            adaptor.addChild(root_0, EMPTY63_tree);
            }
            dbg.location(48,46);
            set64=(Token)input.LT(1);
            if ( (input.LA(1)>=GREATEST && input.LA(1)<=LEAST) ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set64));
                state.errorRecovery=false;state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                dbg.recognitionException(mse);
                throw mse;
            }

            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(48, 65);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "emptyOrderDecl");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end emptyOrderDecl

    public static class copyNamespacesDecl_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start copyNamespacesDecl
    // XQuery.g:49:1: copyNamespacesDecl : DECLARE COPY_NAMESPACES preserveMode COMMA inheritMode ;
    public final XQueryParser.copyNamespacesDecl_return copyNamespacesDecl() throws RecognitionException {
        XQueryParser.copyNamespacesDecl_return retval = new XQueryParser.copyNamespacesDecl_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token DECLARE65=null;
        Token COPY_NAMESPACES66=null;
        Token COMMA68=null;
        XQueryParser.preserveMode_return preserveMode67 = null;

        XQueryParser.inheritMode_return inheritMode69 = null;


        Object DECLARE65_tree=null;
        Object COPY_NAMESPACES66_tree=null;
        Object COMMA68_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "copyNamespacesDecl");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(49, 1);

        try {
            // XQuery.g:50:3: ( DECLARE COPY_NAMESPACES preserveMode COMMA inheritMode )
            dbg.enterAlt(1);

            // XQuery.g:50:5: DECLARE COPY_NAMESPACES preserveMode COMMA inheritMode
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(50,5);
            DECLARE65=(Token)input.LT(1);
            match(input,DECLARE,FOLLOW_DECLARE_in_copyNamespacesDecl412); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            DECLARE65_tree = (Object)adaptor.create(DECLARE65);
            adaptor.addChild(root_0, DECLARE65_tree);
            }
            dbg.location(50,13);
            COPY_NAMESPACES66=(Token)input.LT(1);
            match(input,COPY_NAMESPACES,FOLLOW_COPY_NAMESPACES_in_copyNamespacesDecl414); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            COPY_NAMESPACES66_tree = (Object)adaptor.create(COPY_NAMESPACES66);
            adaptor.addChild(root_0, COPY_NAMESPACES66_tree);
            }
            dbg.location(50,29);
            pushFollow(FOLLOW_preserveMode_in_copyNamespacesDecl416);
            preserveMode67=preserveMode();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, preserveMode67.getTree());
            dbg.location(50,42);
            COMMA68=(Token)input.LT(1);
            match(input,COMMA,FOLLOW_COMMA_in_copyNamespacesDecl418); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            COMMA68_tree = (Object)adaptor.create(COMMA68);
            adaptor.addChild(root_0, COMMA68_tree);
            }
            dbg.location(50,48);
            pushFollow(FOLLOW_inheritMode_in_copyNamespacesDecl420);
            inheritMode69=inheritMode();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, inheritMode69.getTree());
            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(50, 60);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "copyNamespacesDecl");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end copyNamespacesDecl

    public static class preserveMode_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start preserveMode
    // XQuery.g:51:1: preserveMode : ( PRESERVE | NO_PRESERVE );
    public final XQueryParser.preserveMode_return preserveMode() throws RecognitionException {
        XQueryParser.preserveMode_return retval = new XQueryParser.preserveMode_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set70=null;

        Object set70_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "preserveMode");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(51, 1);

        try {
            // XQuery.g:51:14: ( PRESERVE | NO_PRESERVE )
            dbg.enterAlt(1);

            // XQuery.g:
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(51,14);
            set70=(Token)input.LT(1);
            if ( input.LA(1)==PRESERVE||input.LA(1)==NO_PRESERVE ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set70));
                state.errorRecovery=false;state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                dbg.recognitionException(mse);
                throw mse;
            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(51, 39);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "preserveMode");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end preserveMode

    public static class inheritMode_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start inheritMode
    // XQuery.g:52:1: inheritMode : ( INHERIT | NO_INHERIT );
    public final XQueryParser.inheritMode_return inheritMode() throws RecognitionException {
        XQueryParser.inheritMode_return retval = new XQueryParser.inheritMode_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set71=null;

        Object set71_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "inheritMode");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(52, 1);

        try {
            // XQuery.g:52:13: ( INHERIT | NO_INHERIT )
            dbg.enterAlt(1);

            // XQuery.g:
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(52,13);
            set71=(Token)input.LT(1);
            if ( (input.LA(1)>=INHERIT && input.LA(1)<=NO_INHERIT) ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set71));
                state.errorRecovery=false;state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                dbg.recognitionException(mse);
                throw mse;
            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(52, 36);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "inheritMode");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end inheritMode

    public static class defaultCollationDecl_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start defaultCollationDecl
    // XQuery.g:53:1: defaultCollationDecl : DECLARE DEFAULT COLLATION uRILiteral ;
    public final XQueryParser.defaultCollationDecl_return defaultCollationDecl() throws RecognitionException {
        XQueryParser.defaultCollationDecl_return retval = new XQueryParser.defaultCollationDecl_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token DECLARE72=null;
        Token DEFAULT73=null;
        Token COLLATION74=null;
        XQueryParser.uRILiteral_return uRILiteral75 = null;


        Object DECLARE72_tree=null;
        Object DEFAULT73_tree=null;
        Object COLLATION74_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "defaultCollationDecl");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(53, 1);

        try {
            // XQuery.g:54:3: ( DECLARE DEFAULT COLLATION uRILiteral )
            dbg.enterAlt(1);

            // XQuery.g:54:5: DECLARE DEFAULT COLLATION uRILiteral
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(54,5);
            DECLARE72=(Token)input.LT(1);
            match(input,DECLARE,FOLLOW_DECLARE_in_defaultCollationDecl455); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            DECLARE72_tree = (Object)adaptor.create(DECLARE72);
            adaptor.addChild(root_0, DECLARE72_tree);
            }
            dbg.location(54,13);
            DEFAULT73=(Token)input.LT(1);
            match(input,DEFAULT,FOLLOW_DEFAULT_in_defaultCollationDecl457); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            DEFAULT73_tree = (Object)adaptor.create(DEFAULT73);
            adaptor.addChild(root_0, DEFAULT73_tree);
            }
            dbg.location(54,21);
            COLLATION74=(Token)input.LT(1);
            match(input,COLLATION,FOLLOW_COLLATION_in_defaultCollationDecl459); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            COLLATION74_tree = (Object)adaptor.create(COLLATION74);
            adaptor.addChild(root_0, COLLATION74_tree);
            }
            dbg.location(54,31);
            pushFollow(FOLLOW_uRILiteral_in_defaultCollationDecl461);
            uRILiteral75=uRILiteral();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, uRILiteral75.getTree());
            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(54, 42);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "defaultCollationDecl");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end defaultCollationDecl

    public static class baseURIDecl_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start baseURIDecl
    // XQuery.g:55:1: baseURIDecl : DECLARE BASE_URI uRILiteral ;
    public final XQueryParser.baseURIDecl_return baseURIDecl() throws RecognitionException {
        XQueryParser.baseURIDecl_return retval = new XQueryParser.baseURIDecl_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token DECLARE76=null;
        Token BASE_URI77=null;
        XQueryParser.uRILiteral_return uRILiteral78 = null;


        Object DECLARE76_tree=null;
        Object BASE_URI77_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "baseURIDecl");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(55, 1);

        try {
            // XQuery.g:55:13: ( DECLARE BASE_URI uRILiteral )
            dbg.enterAlt(1);

            // XQuery.g:55:15: DECLARE BASE_URI uRILiteral
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(55,15);
            DECLARE76=(Token)input.LT(1);
            match(input,DECLARE,FOLLOW_DECLARE_in_baseURIDecl469); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            DECLARE76_tree = (Object)adaptor.create(DECLARE76);
            adaptor.addChild(root_0, DECLARE76_tree);
            }
            dbg.location(55,23);
            BASE_URI77=(Token)input.LT(1);
            match(input,BASE_URI,FOLLOW_BASE_URI_in_baseURIDecl471); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            BASE_URI77_tree = (Object)adaptor.create(BASE_URI77);
            adaptor.addChild(root_0, BASE_URI77_tree);
            }
            dbg.location(55,32);
            pushFollow(FOLLOW_uRILiteral_in_baseURIDecl473);
            uRILiteral78=uRILiteral();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, uRILiteral78.getTree());
            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(55, 43);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "baseURIDecl");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end baseURIDecl

    public static class schemaImport_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start schemaImport
    // XQuery.g:56:1: schemaImport : IMPORT SCHEMA ( schemaPrefix )? uRILiteral ( AT uRILiteral ( COMMA uRILiteral )* )? ;
    public final XQueryParser.schemaImport_return schemaImport() throws RecognitionException {
        XQueryParser.schemaImport_return retval = new XQueryParser.schemaImport_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token IMPORT79=null;
        Token SCHEMA80=null;
        Token AT83=null;
        Token COMMA85=null;
        XQueryParser.schemaPrefix_return schemaPrefix81 = null;

        XQueryParser.uRILiteral_return uRILiteral82 = null;

        XQueryParser.uRILiteral_return uRILiteral84 = null;

        XQueryParser.uRILiteral_return uRILiteral86 = null;


        Object IMPORT79_tree=null;
        Object SCHEMA80_tree=null;
        Object AT83_tree=null;
        Object COMMA85_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "schemaImport");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(56, 1);

        try {
            // XQuery.g:56:14: ( IMPORT SCHEMA ( schemaPrefix )? uRILiteral ( AT uRILiteral ( COMMA uRILiteral )* )? )
            dbg.enterAlt(1);

            // XQuery.g:56:16: IMPORT SCHEMA ( schemaPrefix )? uRILiteral ( AT uRILiteral ( COMMA uRILiteral )* )?
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(56,16);
            IMPORT79=(Token)input.LT(1);
            match(input,IMPORT,FOLLOW_IMPORT_in_schemaImport481); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            IMPORT79_tree = (Object)adaptor.create(IMPORT79);
            adaptor.addChild(root_0, IMPORT79_tree);
            }
            dbg.location(56,23);
            SCHEMA80=(Token)input.LT(1);
            match(input,SCHEMA,FOLLOW_SCHEMA_in_schemaImport483); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            SCHEMA80_tree = (Object)adaptor.create(SCHEMA80);
            adaptor.addChild(root_0, SCHEMA80_tree);
            }
            dbg.location(56,30);
            // XQuery.g:56:30: ( schemaPrefix )?
            int alt10=2;
            try { dbg.enterSubRule(10);
            try { dbg.enterDecision(10);

            int LA10_0 = input.LA(1);

            if ( (LA10_0==NAMESPACE||LA10_0==DEFAULT) ) {
                alt10=1;
            }
            } finally {dbg.exitDecision(10);}

            switch (alt10) {
                case 1 :
                    dbg.enterAlt(1);

                    // XQuery.g:56:30: schemaPrefix
                    {
                    dbg.location(56,30);
                    pushFollow(FOLLOW_schemaPrefix_in_schemaImport485);
                    schemaPrefix81=schemaPrefix();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, schemaPrefix81.getTree());
                    dbg.location(56,30);


                    }
                    break;

            }
            } finally {dbg.exitSubRule(10);}

            dbg.location(56,44);
            pushFollow(FOLLOW_uRILiteral_in_schemaImport488);
            uRILiteral82=uRILiteral();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, uRILiteral82.getTree());
            dbg.location(56,55);
            // XQuery.g:56:55: ( AT uRILiteral ( COMMA uRILiteral )* )?
            int alt12=2;
            try { dbg.enterSubRule(12);
            try { dbg.enterDecision(12);

            int LA12_0 = input.LA(1);

            if ( (LA12_0==AT) ) {
                alt12=1;
            }
            } finally {dbg.exitDecision(12);}

            switch (alt12) {
                case 1 :
                    dbg.enterAlt(1);

                    // XQuery.g:56:56: AT uRILiteral ( COMMA uRILiteral )*
                    {
                    dbg.location(56,56);
                    AT83=(Token)input.LT(1);
                    match(input,AT,FOLLOW_AT_in_schemaImport491); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    AT83_tree = (Object)adaptor.create(AT83);
                    adaptor.addChild(root_0, AT83_tree);
                    }
                    dbg.location(56,59);
                    pushFollow(FOLLOW_uRILiteral_in_schemaImport493);
                    uRILiteral84=uRILiteral();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, uRILiteral84.getTree());
                    dbg.location(56,70);
                    // XQuery.g:56:70: ( COMMA uRILiteral )*
                    try { dbg.enterSubRule(11);

                    loop11:
                    do {
                        int alt11=2;
                        try { dbg.enterDecision(11);

                        int LA11_0 = input.LA(1);

                        if ( (LA11_0==COMMA) ) {
                            alt11=1;
                        }


                        } finally {dbg.exitDecision(11);}

                        switch (alt11) {
                    	case 1 :
                    	    dbg.enterAlt(1);

                    	    // XQuery.g:56:71: COMMA uRILiteral
                    	    {
                    	    dbg.location(56,71);
                    	    COMMA85=(Token)input.LT(1);
                    	    match(input,COMMA,FOLLOW_COMMA_in_schemaImport496); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    COMMA85_tree = (Object)adaptor.create(COMMA85);
                    	    adaptor.addChild(root_0, COMMA85_tree);
                    	    }
                    	    dbg.location(56,77);
                    	    pushFollow(FOLLOW_uRILiteral_in_schemaImport498);
                    	    uRILiteral86=uRILiteral();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) adaptor.addChild(root_0, uRILiteral86.getTree());
                    	    dbg.location(56,87);


                    	    }
                    	    break;

                    	default :
                    	    break loop11;
                        }
                    } while (true);
                    } finally {dbg.exitSubRule(11);}

                    dbg.location(56,89);


                    }
                    break;

            }
            } finally {dbg.exitSubRule(12);}

            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(56, 92);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "schemaImport");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end schemaImport

    public static class schemaPrefix_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start schemaPrefix
    // XQuery.g:57:1: schemaPrefix : ( ( NAMESPACE ncName Lit_EQ ) | ( DEFAULT ELEMENT NAMESPACE ) );
    public final XQueryParser.schemaPrefix_return schemaPrefix() throws RecognitionException {
        XQueryParser.schemaPrefix_return retval = new XQueryParser.schemaPrefix_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token NAMESPACE87=null;
        Token Lit_EQ89=null;
        Token DEFAULT90=null;
        Token ELEMENT91=null;
        Token NAMESPACE92=null;
        XQueryParser.ncName_return ncName88 = null;


        Object NAMESPACE87_tree=null;
        Object Lit_EQ89_tree=null;
        Object DEFAULT90_tree=null;
        Object ELEMENT91_tree=null;
        Object NAMESPACE92_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "schemaPrefix");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(57, 1);

        try {
            // XQuery.g:57:14: ( ( NAMESPACE ncName Lit_EQ ) | ( DEFAULT ELEMENT NAMESPACE ) )
            int alt13=2;
            try { dbg.enterDecision(13);

            int LA13_0 = input.LA(1);

            if ( (LA13_0==NAMESPACE) ) {
                alt13=1;
            }
            else if ( (LA13_0==DEFAULT) ) {
                alt13=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;
            }
            } finally {dbg.exitDecision(13);}

            switch (alt13) {
                case 1 :
                    dbg.enterAlt(1);

                    // XQuery.g:57:16: ( NAMESPACE ncName Lit_EQ )
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(57,16);
                    // XQuery.g:57:16: ( NAMESPACE ncName Lit_EQ )
                    dbg.enterAlt(1);

                    // XQuery.g:57:17: NAMESPACE ncName Lit_EQ
                    {
                    dbg.location(57,17);
                    NAMESPACE87=(Token)input.LT(1);
                    match(input,NAMESPACE,FOLLOW_NAMESPACE_in_schemaPrefix511); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    NAMESPACE87_tree = (Object)adaptor.create(NAMESPACE87);
                    adaptor.addChild(root_0, NAMESPACE87_tree);
                    }
                    dbg.location(57,27);
                    pushFollow(FOLLOW_ncName_in_schemaPrefix513);
                    ncName88=ncName();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, ncName88.getTree());
                    dbg.location(57,34);
                    Lit_EQ89=(Token)input.LT(1);
                    match(input,Lit_EQ,FOLLOW_Lit_EQ_in_schemaPrefix515); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    Lit_EQ89_tree = (Object)adaptor.create(Lit_EQ89);
                    adaptor.addChild(root_0, Lit_EQ89_tree);
                    }
                    dbg.location(57,40);


                    }

                    dbg.location(57,44);


                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // XQuery.g:57:44: ( DEFAULT ELEMENT NAMESPACE )
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(57,44);
                    // XQuery.g:57:44: ( DEFAULT ELEMENT NAMESPACE )
                    dbg.enterAlt(1);

                    // XQuery.g:57:45: DEFAULT ELEMENT NAMESPACE
                    {
                    dbg.location(57,45);
                    DEFAULT90=(Token)input.LT(1);
                    match(input,DEFAULT,FOLLOW_DEFAULT_in_schemaPrefix521); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    DEFAULT90_tree = (Object)adaptor.create(DEFAULT90);
                    adaptor.addChild(root_0, DEFAULT90_tree);
                    }
                    dbg.location(57,53);
                    ELEMENT91=(Token)input.LT(1);
                    match(input,ELEMENT,FOLLOW_ELEMENT_in_schemaPrefix523); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    ELEMENT91_tree = (Object)adaptor.create(ELEMENT91);
                    adaptor.addChild(root_0, ELEMENT91_tree);
                    }
                    dbg.location(57,61);
                    NAMESPACE92=(Token)input.LT(1);
                    match(input,NAMESPACE,FOLLOW_NAMESPACE_in_schemaPrefix525); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    NAMESPACE92_tree = (Object)adaptor.create(NAMESPACE92);
                    adaptor.addChild(root_0, NAMESPACE92_tree);
                    }
                    dbg.location(57,70);


                    }

                    dbg.location(0,0);


                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(57, 72);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "schemaPrefix");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end schemaPrefix

    public static class moduleImport_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start moduleImport
    // XQuery.g:58:1: moduleImport : IMPORT MODULE ( NAMESPACE ncName Lit_EQ )? uRILiteral ( AT uRILiteral ( COMMA uRILiteral )* )? ;
    public final XQueryParser.moduleImport_return moduleImport() throws RecognitionException {
        XQueryParser.moduleImport_return retval = new XQueryParser.moduleImport_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token IMPORT93=null;
        Token MODULE94=null;
        Token NAMESPACE95=null;
        Token Lit_EQ97=null;
        Token AT99=null;
        Token COMMA101=null;
        XQueryParser.ncName_return ncName96 = null;

        XQueryParser.uRILiteral_return uRILiteral98 = null;

        XQueryParser.uRILiteral_return uRILiteral100 = null;

        XQueryParser.uRILiteral_return uRILiteral102 = null;


        Object IMPORT93_tree=null;
        Object MODULE94_tree=null;
        Object NAMESPACE95_tree=null;
        Object Lit_EQ97_tree=null;
        Object AT99_tree=null;
        Object COMMA101_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "moduleImport");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(58, 1);

        try {
            // XQuery.g:58:14: ( IMPORT MODULE ( NAMESPACE ncName Lit_EQ )? uRILiteral ( AT uRILiteral ( COMMA uRILiteral )* )? )
            dbg.enterAlt(1);

            // XQuery.g:58:16: IMPORT MODULE ( NAMESPACE ncName Lit_EQ )? uRILiteral ( AT uRILiteral ( COMMA uRILiteral )* )?
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(58,16);
            IMPORT93=(Token)input.LT(1);
            match(input,IMPORT,FOLLOW_IMPORT_in_moduleImport534); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            IMPORT93_tree = (Object)adaptor.create(IMPORT93);
            adaptor.addChild(root_0, IMPORT93_tree);
            }
            dbg.location(58,23);
            MODULE94=(Token)input.LT(1);
            match(input,MODULE,FOLLOW_MODULE_in_moduleImport536); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            MODULE94_tree = (Object)adaptor.create(MODULE94);
            adaptor.addChild(root_0, MODULE94_tree);
            }
            dbg.location(58,30);
            // XQuery.g:58:30: ( NAMESPACE ncName Lit_EQ )?
            int alt14=2;
            try { dbg.enterSubRule(14);
            try { dbg.enterDecision(14);

            int LA14_0 = input.LA(1);

            if ( (LA14_0==NAMESPACE) ) {
                alt14=1;
            }
            } finally {dbg.exitDecision(14);}

            switch (alt14) {
                case 1 :
                    dbg.enterAlt(1);

                    // XQuery.g:58:31: NAMESPACE ncName Lit_EQ
                    {
                    dbg.location(58,31);
                    NAMESPACE95=(Token)input.LT(1);
                    match(input,NAMESPACE,FOLLOW_NAMESPACE_in_moduleImport539); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    NAMESPACE95_tree = (Object)adaptor.create(NAMESPACE95);
                    adaptor.addChild(root_0, NAMESPACE95_tree);
                    }
                    dbg.location(58,41);
                    pushFollow(FOLLOW_ncName_in_moduleImport541);
                    ncName96=ncName();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, ncName96.getTree());
                    dbg.location(58,48);
                    Lit_EQ97=(Token)input.LT(1);
                    match(input,Lit_EQ,FOLLOW_Lit_EQ_in_moduleImport543); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    Lit_EQ97_tree = (Object)adaptor.create(Lit_EQ97);
                    adaptor.addChild(root_0, Lit_EQ97_tree);
                    }
                    dbg.location(58,54);


                    }
                    break;

            }
            } finally {dbg.exitSubRule(14);}

            dbg.location(58,57);
            pushFollow(FOLLOW_uRILiteral_in_moduleImport547);
            uRILiteral98=uRILiteral();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, uRILiteral98.getTree());
            dbg.location(58,68);
            // XQuery.g:58:68: ( AT uRILiteral ( COMMA uRILiteral )* )?
            int alt16=2;
            try { dbg.enterSubRule(16);
            try { dbg.enterDecision(16);

            int LA16_0 = input.LA(1);

            if ( (LA16_0==AT) ) {
                alt16=1;
            }
            } finally {dbg.exitDecision(16);}

            switch (alt16) {
                case 1 :
                    dbg.enterAlt(1);

                    // XQuery.g:58:69: AT uRILiteral ( COMMA uRILiteral )*
                    {
                    dbg.location(58,69);
                    AT99=(Token)input.LT(1);
                    match(input,AT,FOLLOW_AT_in_moduleImport550); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    AT99_tree = (Object)adaptor.create(AT99);
                    adaptor.addChild(root_0, AT99_tree);
                    }
                    dbg.location(58,72);
                    pushFollow(FOLLOW_uRILiteral_in_moduleImport552);
                    uRILiteral100=uRILiteral();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, uRILiteral100.getTree());
                    dbg.location(58,83);
                    // XQuery.g:58:83: ( COMMA uRILiteral )*
                    try { dbg.enterSubRule(15);

                    loop15:
                    do {
                        int alt15=2;
                        try { dbg.enterDecision(15);

                        int LA15_0 = input.LA(1);

                        if ( (LA15_0==COMMA) ) {
                            alt15=1;
                        }


                        } finally {dbg.exitDecision(15);}

                        switch (alt15) {
                    	case 1 :
                    	    dbg.enterAlt(1);

                    	    // XQuery.g:58:84: COMMA uRILiteral
                    	    {
                    	    dbg.location(58,84);
                    	    COMMA101=(Token)input.LT(1);
                    	    match(input,COMMA,FOLLOW_COMMA_in_moduleImport555); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    COMMA101_tree = (Object)adaptor.create(COMMA101);
                    	    adaptor.addChild(root_0, COMMA101_tree);
                    	    }
                    	    dbg.location(58,90);
                    	    pushFollow(FOLLOW_uRILiteral_in_moduleImport557);
                    	    uRILiteral102=uRILiteral();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) adaptor.addChild(root_0, uRILiteral102.getTree());
                    	    dbg.location(58,100);


                    	    }
                    	    break;

                    	default :
                    	    break loop15;
                        }
                    } while (true);
                    } finally {dbg.exitSubRule(15);}

                    dbg.location(58,102);


                    }
                    break;

            }
            } finally {dbg.exitSubRule(16);}

            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(58, 105);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "moduleImport");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end moduleImport

    public static class varDecl_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start varDecl
    // XQuery.g:59:1: varDecl : DECLARE VARIABLE variable ( typeDeclaration )? ( ( ':=' exprSingle ) | EXTERNAL ) ;
    public final XQueryParser.varDecl_return varDecl() throws RecognitionException {
        XQueryParser.varDecl_return retval = new XQueryParser.varDecl_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token DECLARE103=null;
        Token VARIABLE104=null;
        Token string_literal107=null;
        Token EXTERNAL109=null;
        XQueryParser.variable_return variable105 = null;

        XQueryParser.typeDeclaration_return typeDeclaration106 = null;

        XQueryParser.exprSingle_return exprSingle108 = null;


        Object DECLARE103_tree=null;
        Object VARIABLE104_tree=null;
        Object string_literal107_tree=null;
        Object EXTERNAL109_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "varDecl");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(59, 1);

        try {
            // XQuery.g:59:10: ( DECLARE VARIABLE variable ( typeDeclaration )? ( ( ':=' exprSingle ) | EXTERNAL ) )
            dbg.enterAlt(1);

            // XQuery.g:59:12: DECLARE VARIABLE variable ( typeDeclaration )? ( ( ':=' exprSingle ) | EXTERNAL )
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(59,12);
            DECLARE103=(Token)input.LT(1);
            match(input,DECLARE,FOLLOW_DECLARE_in_varDecl570); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            DECLARE103_tree = (Object)adaptor.create(DECLARE103);
            adaptor.addChild(root_0, DECLARE103_tree);
            }
            dbg.location(59,20);
            VARIABLE104=(Token)input.LT(1);
            match(input,VARIABLE,FOLLOW_VARIABLE_in_varDecl572); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            VARIABLE104_tree = (Object)adaptor.create(VARIABLE104);
            adaptor.addChild(root_0, VARIABLE104_tree);
            }
            dbg.location(59,29);
            pushFollow(FOLLOW_variable_in_varDecl574);
            variable105=variable();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, variable105.getTree());
            dbg.location(59,38);
            // XQuery.g:59:38: ( typeDeclaration )?
            int alt17=2;
            try { dbg.enterSubRule(17);
            try { dbg.enterDecision(17);

            int LA17_0 = input.LA(1);

            if ( (LA17_0==AS) ) {
                alt17=1;
            }
            } finally {dbg.exitDecision(17);}

            switch (alt17) {
                case 1 :
                    dbg.enterAlt(1);

                    // XQuery.g:59:38: typeDeclaration
                    {
                    dbg.location(59,38);
                    pushFollow(FOLLOW_typeDeclaration_in_varDecl576);
                    typeDeclaration106=typeDeclaration();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, typeDeclaration106.getTree());
                    dbg.location(59,38);


                    }
                    break;

            }
            } finally {dbg.exitSubRule(17);}

            dbg.location(59,55);
            // XQuery.g:59:55: ( ( ':=' exprSingle ) | EXTERNAL )
            int alt18=2;
            try { dbg.enterSubRule(18);
            try { dbg.enterDecision(18);

            int LA18_0 = input.LA(1);

            if ( (LA18_0==145) ) {
                alt18=1;
            }
            else if ( (LA18_0==EXTERNAL) ) {
                alt18=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 18, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;
            }
            } finally {dbg.exitDecision(18);}

            switch (alt18) {
                case 1 :
                    dbg.enterAlt(1);

                    // XQuery.g:59:56: ( ':=' exprSingle )
                    {
                    dbg.location(59,56);
                    // XQuery.g:59:56: ( ':=' exprSingle )
                    dbg.enterAlt(1);

                    // XQuery.g:59:57: ':=' exprSingle
                    {
                    dbg.location(59,57);
                    string_literal107=(Token)input.LT(1);
                    match(input,145,FOLLOW_145_in_varDecl581); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal107_tree = (Object)adaptor.create(string_literal107);
                    adaptor.addChild(root_0, string_literal107_tree);
                    }
                    dbg.location(59,62);
                    pushFollow(FOLLOW_exprSingle_in_varDecl583);
                    exprSingle108=exprSingle();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, exprSingle108.getTree());
                    dbg.location(59,72);


                    }

                    dbg.location(59,76);


                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // XQuery.g:59:76: EXTERNAL
                    {
                    dbg.location(59,76);
                    EXTERNAL109=(Token)input.LT(1);
                    match(input,EXTERNAL,FOLLOW_EXTERNAL_in_varDecl588); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    EXTERNAL109_tree = (Object)adaptor.create(EXTERNAL109);
                    adaptor.addChild(root_0, EXTERNAL109_tree);
                    }
                    dbg.location(59,84);


                    }
                    break;

            }
            } finally {dbg.exitSubRule(18);}

            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(59, 86);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "varDecl");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end varDecl

    public static class constructionDecl_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start constructionDecl
    // XQuery.g:60:1: constructionDecl : DECLARE CONSTRUCTION ( STRIP | PRESERVE ) ;
    public final XQueryParser.constructionDecl_return constructionDecl() throws RecognitionException {
        XQueryParser.constructionDecl_return retval = new XQueryParser.constructionDecl_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token DECLARE110=null;
        Token CONSTRUCTION111=null;
        Token set112=null;

        Object DECLARE110_tree=null;
        Object CONSTRUCTION111_tree=null;
        Object set112_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "constructionDecl");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(60, 1);

        try {
            // XQuery.g:61:3: ( DECLARE CONSTRUCTION ( STRIP | PRESERVE ) )
            dbg.enterAlt(1);

            // XQuery.g:61:5: DECLARE CONSTRUCTION ( STRIP | PRESERVE )
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(61,5);
            DECLARE110=(Token)input.LT(1);
            match(input,DECLARE,FOLLOW_DECLARE_in_constructionDecl600); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            DECLARE110_tree = (Object)adaptor.create(DECLARE110);
            adaptor.addChild(root_0, DECLARE110_tree);
            }
            dbg.location(61,13);
            CONSTRUCTION111=(Token)input.LT(1);
            match(input,CONSTRUCTION,FOLLOW_CONSTRUCTION_in_constructionDecl602); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            CONSTRUCTION111_tree = (Object)adaptor.create(CONSTRUCTION111);
            adaptor.addChild(root_0, CONSTRUCTION111_tree);
            }
            dbg.location(61,26);
            set112=(Token)input.LT(1);
            if ( (input.LA(1)>=PRESERVE && input.LA(1)<=STRIP) ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set112));
                state.errorRecovery=false;state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                dbg.recognitionException(mse);
                throw mse;
            }

            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(61, 45);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "constructionDecl");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end constructionDecl

    public static class functionDecl_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start functionDecl
    // XQuery.g:62:1: functionDecl : DECLARE FUNCTION functionDeclPre paramClause RPAREN ( AS sequenceType )? ( enclosedExpr | EXTERNAL ) ;
    public final XQueryParser.functionDecl_return functionDecl() throws RecognitionException {
        XQueryParser.functionDecl_return retval = new XQueryParser.functionDecl_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token DECLARE113=null;
        Token FUNCTION114=null;
        Token RPAREN117=null;
        Token AS118=null;
        Token EXTERNAL121=null;
        XQueryParser.functionDeclPre_return functionDeclPre115 = null;

        XQueryParser.paramClause_return paramClause116 = null;

        XQueryParser.sequenceType_return sequenceType119 = null;

        XQueryParser.enclosedExpr_return enclosedExpr120 = null;


        Object DECLARE113_tree=null;
        Object FUNCTION114_tree=null;
        Object RPAREN117_tree=null;
        Object AS118_tree=null;
        Object EXTERNAL121_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "functionDecl");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(62, 1);

        try {
            // XQuery.g:62:14: ( DECLARE FUNCTION functionDeclPre paramClause RPAREN ( AS sequenceType )? ( enclosedExpr | EXTERNAL ) )
            dbg.enterAlt(1);

            // XQuery.g:62:16: DECLARE FUNCTION functionDeclPre paramClause RPAREN ( AS sequenceType )? ( enclosedExpr | EXTERNAL )
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(62,16);
            DECLARE113=(Token)input.LT(1);
            match(input,DECLARE,FOLLOW_DECLARE_in_functionDecl618); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            DECLARE113_tree = (Object)adaptor.create(DECLARE113);
            adaptor.addChild(root_0, DECLARE113_tree);
            }
            dbg.location(62,24);
            FUNCTION114=(Token)input.LT(1);
            match(input,FUNCTION,FOLLOW_FUNCTION_in_functionDecl620); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            FUNCTION114_tree = (Object)adaptor.create(FUNCTION114);
            adaptor.addChild(root_0, FUNCTION114_tree);
            }
            dbg.location(62,33);
            pushFollow(FOLLOW_functionDeclPre_in_functionDecl622);
            functionDeclPre115=functionDeclPre();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, functionDeclPre115.getTree());
            dbg.location(62,49);
            pushFollow(FOLLOW_paramClause_in_functionDecl624);
            paramClause116=paramClause();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, paramClause116.getTree());
            dbg.location(62,61);
            RPAREN117=(Token)input.LT(1);
            match(input,RPAREN,FOLLOW_RPAREN_in_functionDecl626); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            RPAREN117_tree = (Object)adaptor.create(RPAREN117);
            adaptor.addChild(root_0, RPAREN117_tree);
            }
            dbg.location(62,68);
            // XQuery.g:62:68: ( AS sequenceType )?
            int alt19=2;
            try { dbg.enterSubRule(19);
            try { dbg.enterDecision(19);

            int LA19_0 = input.LA(1);

            if ( (LA19_0==AS) ) {
                alt19=1;
            }
            } finally {dbg.exitDecision(19);}

            switch (alt19) {
                case 1 :
                    dbg.enterAlt(1);

                    // XQuery.g:62:69: AS sequenceType
                    {
                    dbg.location(62,69);
                    AS118=(Token)input.LT(1);
                    match(input,AS,FOLLOW_AS_in_functionDecl629); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    AS118_tree = (Object)adaptor.create(AS118);
                    adaptor.addChild(root_0, AS118_tree);
                    }
                    dbg.location(62,72);
                    pushFollow(FOLLOW_sequenceType_in_functionDecl631);
                    sequenceType119=sequenceType();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, sequenceType119.getTree());
                    dbg.location(62,84);


                    }
                    break;

            }
            } finally {dbg.exitSubRule(19);}

            dbg.location(62,87);
            // XQuery.g:62:87: ( enclosedExpr | EXTERNAL )
            int alt20=2;
            try { dbg.enterSubRule(20);
            try { dbg.enterDecision(20);

            int LA20_0 = input.LA(1);

            if ( (LA20_0==LCURLY) ) {
                alt20=1;
            }
            else if ( (LA20_0==EXTERNAL) ) {
                alt20=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 20, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;
            }
            } finally {dbg.exitDecision(20);}

            switch (alt20) {
                case 1 :
                    dbg.enterAlt(1);

                    // XQuery.g:62:88: enclosedExpr
                    {
                    dbg.location(62,88);
                    pushFollow(FOLLOW_enclosedExpr_in_functionDecl636);
                    enclosedExpr120=enclosedExpr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, enclosedExpr120.getTree());
                    dbg.location(62,103);


                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // XQuery.g:62:103: EXTERNAL
                    {
                    dbg.location(62,103);
                    EXTERNAL121=(Token)input.LT(1);
                    match(input,EXTERNAL,FOLLOW_EXTERNAL_in_functionDecl640); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    EXTERNAL121_tree = (Object)adaptor.create(EXTERNAL121);
                    adaptor.addChild(root_0, EXTERNAL121_tree);
                    }
                    dbg.location(62,111);


                    }
                    break;

            }
            } finally {dbg.exitSubRule(20);}

            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(62, 113);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "functionDecl");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end functionDecl

    public static class functionDeclPre_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start functionDeclPre
    // XQuery.g:63:1: functionDeclPre : declFuncName LPAREN ;
    public final XQueryParser.functionDeclPre_return functionDeclPre() throws RecognitionException {
        XQueryParser.functionDeclPre_return retval = new XQueryParser.functionDeclPre_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token LPAREN123=null;
        XQueryParser.declFuncName_return declFuncName122 = null;


        Object LPAREN123_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "functionDeclPre");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(63, 1);

        try {
            // XQuery.g:63:17: ( declFuncName LPAREN )
            dbg.enterAlt(1);

            // XQuery.g:63:19: declFuncName LPAREN
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(63,19);
            pushFollow(FOLLOW_declFuncName_in_functionDeclPre649);
            declFuncName122=declFuncName();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, declFuncName122.getTree());
            dbg.location(63,32);
            LPAREN123=(Token)input.LT(1);
            match(input,LPAREN,FOLLOW_LPAREN_in_functionDeclPre651); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            LPAREN123_tree = (Object)adaptor.create(LPAREN123);
            adaptor.addChild(root_0, LPAREN123_tree);
            }
            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(63, 38);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "functionDeclPre");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end functionDeclPre

    public static class declFuncName_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start declFuncName
    // XQuery.g:64:1: declFuncName : qNameOrIdent ;
    public final XQueryParser.declFuncName_return declFuncName() throws RecognitionException {
        XQueryParser.declFuncName_return retval = new XQueryParser.declFuncName_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        XQueryParser.qNameOrIdent_return qNameOrIdent124 = null;



        try { dbg.enterRule(getGrammarFileName(), "declFuncName");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(64, 1);

        try {
            // XQuery.g:64:14: ( qNameOrIdent )
            dbg.enterAlt(1);

            // XQuery.g:64:16: qNameOrIdent
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(64,16);
            pushFollow(FOLLOW_qNameOrIdent_in_declFuncName658);
            qNameOrIdent124=qNameOrIdent();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, qNameOrIdent124.getTree());
            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(64, 28);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "declFuncName");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end declFuncName

    public static class paramClause_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start paramClause
    // XQuery.g:65:1: paramClause : ( paramList )? ;
    public final XQueryParser.paramClause_return paramClause() throws RecognitionException {
        XQueryParser.paramClause_return retval = new XQueryParser.paramClause_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        XQueryParser.paramList_return paramList125 = null;



        try { dbg.enterRule(getGrammarFileName(), "paramClause");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(65, 1);

        try {
            // XQuery.g:65:13: ( ( paramList )? )
            dbg.enterAlt(1);

            // XQuery.g:65:15: ( paramList )?
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(65,15);
            // XQuery.g:65:15: ( paramList )?
            int alt21=2;
            try { dbg.enterSubRule(21);
            try { dbg.enterDecision(21);

            int LA21_0 = input.LA(1);

            if ( (LA21_0==146) ) {
                alt21=1;
            }
            } finally {dbg.exitDecision(21);}

            switch (alt21) {
                case 1 :
                    dbg.enterAlt(1);

                    // XQuery.g:65:15: paramList
                    {
                    dbg.location(65,15);
                    pushFollow(FOLLOW_paramList_in_paramClause665);
                    paramList125=paramList();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, paramList125.getTree());
                    dbg.location(65,15);


                    }
                    break;

            }
            } finally {dbg.exitSubRule(21);}

            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(65, 25);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "paramClause");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end paramClause

    public static class paramList_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start paramList
    // XQuery.g:66:1: paramList : param ( COMMA param )* ;
    public final XQueryParser.paramList_return paramList() throws RecognitionException {
        XQueryParser.paramList_return retval = new XQueryParser.paramList_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token COMMA127=null;
        XQueryParser.param_return param126 = null;

        XQueryParser.param_return param128 = null;


        Object COMMA127_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "paramList");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(66, 1);

        try {
            // XQuery.g:66:11: ( param ( COMMA param )* )
            dbg.enterAlt(1);

            // XQuery.g:66:13: param ( COMMA param )*
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(66,13);
            pushFollow(FOLLOW_param_in_paramList673);
            param126=param();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, param126.getTree());
            dbg.location(66,19);
            // XQuery.g:66:19: ( COMMA param )*
            try { dbg.enterSubRule(22);

            loop22:
            do {
                int alt22=2;
                try { dbg.enterDecision(22);

                int LA22_0 = input.LA(1);

                if ( (LA22_0==COMMA) ) {
                    alt22=1;
                }


                } finally {dbg.exitDecision(22);}

                switch (alt22) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // XQuery.g:66:20: COMMA param
            	    {
            	    dbg.location(66,20);
            	    COMMA127=(Token)input.LT(1);
            	    match(input,COMMA,FOLLOW_COMMA_in_paramList676); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    COMMA127_tree = (Object)adaptor.create(COMMA127);
            	    adaptor.addChild(root_0, COMMA127_tree);
            	    }
            	    dbg.location(66,26);
            	    pushFollow(FOLLOW_param_in_paramList678);
            	    param128=param();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, param128.getTree());
            	    dbg.location(66,31);


            	    }
            	    break;

            	default :
            	    break loop22;
                }
            } while (true);
            } finally {dbg.exitSubRule(22);}

            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(66, 34);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "paramList");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end paramList

    public static class param_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start param
    // XQuery.g:67:1: param : variable ( typeDeclaration )? ;
    public final XQueryParser.param_return param() throws RecognitionException {
        XQueryParser.param_return retval = new XQueryParser.param_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        XQueryParser.variable_return variable129 = null;

        XQueryParser.typeDeclaration_return typeDeclaration130 = null;



        try { dbg.enterRule(getGrammarFileName(), "param");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(67, 1);

        try {
            // XQuery.g:67:8: ( variable ( typeDeclaration )? )
            dbg.enterAlt(1);

            // XQuery.g:67:10: variable ( typeDeclaration )?
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(67,10);
            pushFollow(FOLLOW_variable_in_param689);
            variable129=variable();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, variable129.getTree());
            dbg.location(67,19);
            // XQuery.g:67:19: ( typeDeclaration )?
            int alt23=2;
            try { dbg.enterSubRule(23);
            try { dbg.enterDecision(23);

            int LA23_0 = input.LA(1);

            if ( (LA23_0==AS) ) {
                alt23=1;
            }
            } finally {dbg.exitDecision(23);}

            switch (alt23) {
                case 1 :
                    dbg.enterAlt(1);

                    // XQuery.g:67:19: typeDeclaration
                    {
                    dbg.location(67,19);
                    pushFollow(FOLLOW_typeDeclaration_in_param691);
                    typeDeclaration130=typeDeclaration();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, typeDeclaration130.getTree());
                    dbg.location(67,19);


                    }
                    break;

            }
            } finally {dbg.exitSubRule(23);}

            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(67, 36);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "param");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end param

    public static class enclosedExpr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start enclosedExpr
    // XQuery.g:68:1: enclosedExpr : LCURLY expr RCURLY ;
    public final XQueryParser.enclosedExpr_return enclosedExpr() throws RecognitionException {
        XQueryParser.enclosedExpr_return retval = new XQueryParser.enclosedExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token LCURLY131=null;
        Token RCURLY133=null;
        XQueryParser.expr_return expr132 = null;


        Object LCURLY131_tree=null;
        Object RCURLY133_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "enclosedExpr");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(68, 1);

        try {
            // XQuery.g:68:14: ( LCURLY expr RCURLY )
            dbg.enterAlt(1);

            // XQuery.g:68:16: LCURLY expr RCURLY
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(68,16);
            LCURLY131=(Token)input.LT(1);
            match(input,LCURLY,FOLLOW_LCURLY_in_enclosedExpr700); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            LCURLY131_tree = (Object)adaptor.create(LCURLY131);
            adaptor.addChild(root_0, LCURLY131_tree);
            }
            dbg.location(68,23);
            pushFollow(FOLLOW_expr_in_enclosedExpr702);
            expr132=expr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, expr132.getTree());
            dbg.location(68,28);
            RCURLY133=(Token)input.LT(1);
            match(input,RCURLY,FOLLOW_RCURLY_in_enclosedExpr704); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            RCURLY133_tree = (Object)adaptor.create(RCURLY133);
            adaptor.addChild(root_0, RCURLY133_tree);
            }
            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(68, 35);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "enclosedExpr");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end enclosedExpr

    public static class queryBody_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start queryBody
    // XQuery.g:69:1: queryBody : expr ;
    public final XQueryParser.queryBody_return queryBody() throws RecognitionException {
        XQueryParser.queryBody_return retval = new XQueryParser.queryBody_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        XQueryParser.expr_return expr134 = null;



        try { dbg.enterRule(getGrammarFileName(), "queryBody");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(69, 1);

        try {
            // XQuery.g:69:11: ( expr )
            dbg.enterAlt(1);

            // XQuery.g:69:13: expr
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(69,13);
            pushFollow(FOLLOW_expr_in_queryBody712);
            expr134=expr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, expr134.getTree());
            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(69, 18);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "queryBody");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end queryBody

    public static class expr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start expr
    // XQuery.g:70:1: expr : exprSingle ( COMMA exprSingle )* ;
    public final XQueryParser.expr_return expr() throws RecognitionException {
        XQueryParser.expr_return retval = new XQueryParser.expr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token COMMA136=null;
        XQueryParser.exprSingle_return exprSingle135 = null;

        XQueryParser.exprSingle_return exprSingle137 = null;


        Object COMMA136_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "expr");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(70, 1);

        try {
            // XQuery.g:70:7: ( exprSingle ( COMMA exprSingle )* )
            dbg.enterAlt(1);

            // XQuery.g:70:9: exprSingle ( COMMA exprSingle )*
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(70,9);
            pushFollow(FOLLOW_exprSingle_in_expr721);
            exprSingle135=exprSingle();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, exprSingle135.getTree());
            dbg.location(70,20);
            // XQuery.g:70:20: ( COMMA exprSingle )*
            try { dbg.enterSubRule(24);

            loop24:
            do {
                int alt24=2;
                try { dbg.enterDecision(24);

                int LA24_0 = input.LA(1);

                if ( (LA24_0==COMMA) ) {
                    alt24=1;
                }


                } finally {dbg.exitDecision(24);}

                switch (alt24) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // XQuery.g:70:21: COMMA exprSingle
            	    {
            	    dbg.location(70,21);
            	    COMMA136=(Token)input.LT(1);
            	    match(input,COMMA,FOLLOW_COMMA_in_expr724); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    COMMA136_tree = (Object)adaptor.create(COMMA136);
            	    adaptor.addChild(root_0, COMMA136_tree);
            	    }
            	    dbg.location(70,27);
            	    pushFollow(FOLLOW_exprSingle_in_expr726);
            	    exprSingle137=exprSingle();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, exprSingle137.getTree());
            	    dbg.location(70,37);


            	    }
            	    break;

            	default :
            	    break loop24;
                }
            } while (true);
            } finally {dbg.exitSubRule(24);}

            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(70, 40);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "expr");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end expr

    public static class exprSingle_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start exprSingle
    // XQuery.g:71:1: exprSingle : ( fLWORExpr | quantifiedExpr | typeswitchExpr | ifExpr | orExpr );
    public final XQueryParser.exprSingle_return exprSingle() throws RecognitionException {
        XQueryParser.exprSingle_return retval = new XQueryParser.exprSingle_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        XQueryParser.fLWORExpr_return fLWORExpr138 = null;

        XQueryParser.quantifiedExpr_return quantifiedExpr139 = null;

        XQueryParser.typeswitchExpr_return typeswitchExpr140 = null;

        XQueryParser.ifExpr_return ifExpr141 = null;

        XQueryParser.orExpr_return orExpr142 = null;



        try { dbg.enterRule(getGrammarFileName(), "exprSingle");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(71, 1);

        try {
            // XQuery.g:71:12: ( fLWORExpr | quantifiedExpr | typeswitchExpr | ifExpr | orExpr )
            int alt25=5;
            try { dbg.enterDecision(25);

            try {
                isCyclicDecision = true;
                alt25 = dfa25.predict(input);
            }
            catch (NoViableAltException nvae) {
                dbg.recognitionException(nvae);
                throw nvae;
            }
            } finally {dbg.exitDecision(25);}

            switch (alt25) {
                case 1 :
                    dbg.enterAlt(1);

                    // XQuery.g:71:14: fLWORExpr
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(71,14);
                    pushFollow(FOLLOW_fLWORExpr_in_exprSingle736);
                    fLWORExpr138=fLWORExpr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, fLWORExpr138.getTree());
                    dbg.location(71,27);


                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // XQuery.g:71:27: quantifiedExpr
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(71,27);
                    pushFollow(FOLLOW_quantifiedExpr_in_exprSingle741);
                    quantifiedExpr139=quantifiedExpr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, quantifiedExpr139.getTree());
                    dbg.location(71,45);


                    }
                    break;
                case 3 :
                    dbg.enterAlt(3);

                    // XQuery.g:71:45: typeswitchExpr
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(71,45);
                    pushFollow(FOLLOW_typeswitchExpr_in_exprSingle746);
                    typeswitchExpr140=typeswitchExpr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, typeswitchExpr140.getTree());
                    dbg.location(71,63);


                    }
                    break;
                case 4 :
                    dbg.enterAlt(4);

                    // XQuery.g:71:63: ifExpr
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(71,63);
                    pushFollow(FOLLOW_ifExpr_in_exprSingle751);
                    ifExpr141=ifExpr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, ifExpr141.getTree());
                    dbg.location(71,73);


                    }
                    break;
                case 5 :
                    dbg.enterAlt(5);

                    // XQuery.g:71:73: orExpr
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(71,73);
                    pushFollow(FOLLOW_orExpr_in_exprSingle756);
                    orExpr142=orExpr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, orExpr142.getTree());
                    dbg.location(0,0);


                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(71, 80);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "exprSingle");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end exprSingle

    public static class fLWORExpr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start fLWORExpr
    // XQuery.g:72:1: fLWORExpr : ( forClause | letClause )+ ( whereClause )? ( orderByClause )? returnClause ;
    public final XQueryParser.fLWORExpr_return fLWORExpr() throws RecognitionException {
        XQueryParser.fLWORExpr_return retval = new XQueryParser.fLWORExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        XQueryParser.forClause_return forClause143 = null;

        XQueryParser.letClause_return letClause144 = null;

        XQueryParser.whereClause_return whereClause145 = null;

        XQueryParser.orderByClause_return orderByClause146 = null;

        XQueryParser.returnClause_return returnClause147 = null;



        try { dbg.enterRule(getGrammarFileName(), "fLWORExpr");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(72, 1);

        try {
            // XQuery.g:72:11: ( ( forClause | letClause )+ ( whereClause )? ( orderByClause )? returnClause )
            dbg.enterAlt(1);

            // XQuery.g:72:13: ( forClause | letClause )+ ( whereClause )? ( orderByClause )? returnClause
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(72,13);
            // XQuery.g:72:13: ( forClause | letClause )+
            int cnt26=0;
            try { dbg.enterSubRule(26);

            loop26:
            do {
                int alt26=3;
                try { dbg.enterDecision(26);

                int LA26_0 = input.LA(1);

                if ( (LA26_0==FOR) ) {
                    alt26=1;
                }
                else if ( (LA26_0==LET) ) {
                    alt26=2;
                }


                } finally {dbg.exitDecision(26);}

                switch (alt26) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // XQuery.g:72:14: forClause
            	    {
            	    dbg.location(72,14);
            	    pushFollow(FOLLOW_forClause_in_fLWORExpr765);
            	    forClause143=forClause();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, forClause143.getTree());
            	    dbg.location(72,26);


            	    }
            	    break;
            	case 2 :
            	    dbg.enterAlt(2);

            	    // XQuery.g:72:26: letClause
            	    {
            	    dbg.location(72,26);
            	    pushFollow(FOLLOW_letClause_in_fLWORExpr769);
            	    letClause144=letClause();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, letClause144.getTree());
            	    dbg.location(72,35);


            	    }
            	    break;

            	default :
            	    if ( cnt26 >= 1 ) break loop26;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(26, input);
                        dbg.recognitionException(eee);

                        throw eee;
                }
                cnt26++;
            } while (true);
            } finally {dbg.exitSubRule(26);}

            dbg.location(72,38);
            // XQuery.g:72:38: ( whereClause )?
            int alt27=2;
            try { dbg.enterSubRule(27);
            try { dbg.enterDecision(27);

            int LA27_0 = input.LA(1);

            if ( (LA27_0==WHERE) ) {
                alt27=1;
            }
            } finally {dbg.exitDecision(27);}

            switch (alt27) {
                case 1 :
                    dbg.enterAlt(1);

                    // XQuery.g:72:38: whereClause
                    {
                    dbg.location(72,38);
                    pushFollow(FOLLOW_whereClause_in_fLWORExpr773);
                    whereClause145=whereClause();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, whereClause145.getTree());
                    dbg.location(72,38);


                    }
                    break;

            }
            } finally {dbg.exitSubRule(27);}

            dbg.location(72,51);
            // XQuery.g:72:51: ( orderByClause )?
            int alt28=2;
            try { dbg.enterSubRule(28);
            try { dbg.enterDecision(28);

            int LA28_0 = input.LA(1);

            if ( (LA28_0==ORDER||LA28_0==STABLE) ) {
                alt28=1;
            }
            } finally {dbg.exitDecision(28);}

            switch (alt28) {
                case 1 :
                    dbg.enterAlt(1);

                    // XQuery.g:72:51: orderByClause
                    {
                    dbg.location(72,51);
                    pushFollow(FOLLOW_orderByClause_in_fLWORExpr776);
                    orderByClause146=orderByClause();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, orderByClause146.getTree());
                    dbg.location(72,51);


                    }
                    break;

            }
            } finally {dbg.exitSubRule(28);}

            dbg.location(72,66);
            pushFollow(FOLLOW_returnClause_in_fLWORExpr779);
            returnClause147=returnClause();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, returnClause147.getTree());
            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(72, 78);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "fLWORExpr");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end fLWORExpr

    public static class returnClause_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start returnClause
    // XQuery.g:73:1: returnClause : RETURN exprSingle ;
    public final XQueryParser.returnClause_return returnClause() throws RecognitionException {
        XQueryParser.returnClause_return retval = new XQueryParser.returnClause_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token RETURN148=null;
        XQueryParser.exprSingle_return exprSingle149 = null;


        Object RETURN148_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "returnClause");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(73, 1);

        try {
            // XQuery.g:73:14: ( RETURN exprSingle )
            dbg.enterAlt(1);

            // XQuery.g:73:16: RETURN exprSingle
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(73,16);
            RETURN148=(Token)input.LT(1);
            match(input,RETURN,FOLLOW_RETURN_in_returnClause786); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            RETURN148_tree = (Object)adaptor.create(RETURN148);
            adaptor.addChild(root_0, RETURN148_tree);
            }
            dbg.location(73,23);
            pushFollow(FOLLOW_exprSingle_in_returnClause788);
            exprSingle149=exprSingle();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, exprSingle149.getTree());
            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(73, 34);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "returnClause");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end returnClause

    public static class forClause_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start forClause
    // XQuery.g:74:1: forClause : FOR variable ( typeDeclaration )? ( positionalVar )? IN exprSingle ( forClauseExt )* ;
    public final XQueryParser.forClause_return forClause() throws RecognitionException {
        XQueryParser.forClause_return retval = new XQueryParser.forClause_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token FOR150=null;
        Token IN154=null;
        XQueryParser.variable_return variable151 = null;

        XQueryParser.typeDeclaration_return typeDeclaration152 = null;

        XQueryParser.positionalVar_return positionalVar153 = null;

        XQueryParser.exprSingle_return exprSingle155 = null;

        XQueryParser.forClauseExt_return forClauseExt156 = null;


        Object FOR150_tree=null;
        Object IN154_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "forClause");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(74, 1);

        try {
            // XQuery.g:74:11: ( FOR variable ( typeDeclaration )? ( positionalVar )? IN exprSingle ( forClauseExt )* )
            dbg.enterAlt(1);

            // XQuery.g:74:13: FOR variable ( typeDeclaration )? ( positionalVar )? IN exprSingle ( forClauseExt )*
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(74,13);
            FOR150=(Token)input.LT(1);
            match(input,FOR,FOLLOW_FOR_in_forClause796); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            FOR150_tree = (Object)adaptor.create(FOR150);
            adaptor.addChild(root_0, FOR150_tree);
            }
            dbg.location(74,17);
            pushFollow(FOLLOW_variable_in_forClause798);
            variable151=variable();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, variable151.getTree());
            dbg.location(74,26);
            // XQuery.g:74:26: ( typeDeclaration )?
            int alt29=2;
            try { dbg.enterSubRule(29);
            try { dbg.enterDecision(29);

            int LA29_0 = input.LA(1);

            if ( (LA29_0==AS) ) {
                alt29=1;
            }
            } finally {dbg.exitDecision(29);}

            switch (alt29) {
                case 1 :
                    dbg.enterAlt(1);

                    // XQuery.g:74:26: typeDeclaration
                    {
                    dbg.location(74,26);
                    pushFollow(FOLLOW_typeDeclaration_in_forClause800);
                    typeDeclaration152=typeDeclaration();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, typeDeclaration152.getTree());
                    dbg.location(74,26);


                    }
                    break;

            }
            } finally {dbg.exitSubRule(29);}

            dbg.location(74,43);
            // XQuery.g:74:43: ( positionalVar )?
            int alt30=2;
            try { dbg.enterSubRule(30);
            try { dbg.enterDecision(30);

            int LA30_0 = input.LA(1);

            if ( (LA30_0==AT) ) {
                alt30=1;
            }
            } finally {dbg.exitDecision(30);}

            switch (alt30) {
                case 1 :
                    dbg.enterAlt(1);

                    // XQuery.g:74:43: positionalVar
                    {
                    dbg.location(74,43);
                    pushFollow(FOLLOW_positionalVar_in_forClause803);
                    positionalVar153=positionalVar();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, positionalVar153.getTree());
                    dbg.location(74,43);


                    }
                    break;

            }
            } finally {dbg.exitSubRule(30);}

            dbg.location(74,58);
            IN154=(Token)input.LT(1);
            match(input,IN,FOLLOW_IN_in_forClause806); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            IN154_tree = (Object)adaptor.create(IN154);
            adaptor.addChild(root_0, IN154_tree);
            }
            dbg.location(74,61);
            pushFollow(FOLLOW_exprSingle_in_forClause808);
            exprSingle155=exprSingle();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, exprSingle155.getTree());
            dbg.location(74,72);
            // XQuery.g:74:72: ( forClauseExt )*
            try { dbg.enterSubRule(31);

            loop31:
            do {
                int alt31=2;
                try { dbg.enterDecision(31);

                int LA31_0 = input.LA(1);

                if ( (LA31_0==COMMA) ) {
                    alt31=1;
                }


                } finally {dbg.exitDecision(31);}

                switch (alt31) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // XQuery.g:74:72: forClauseExt
            	    {
            	    dbg.location(74,72);
            	    pushFollow(FOLLOW_forClauseExt_in_forClause810);
            	    forClauseExt156=forClauseExt();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, forClauseExt156.getTree());
            	    dbg.location(74,72);


            	    }
            	    break;

            	default :
            	    break loop31;
                }
            } while (true);
            } finally {dbg.exitSubRule(31);}

            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(74, 86);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "forClause");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end forClause

    public static class variable_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start variable
    // XQuery.g:75:1: variable : '$' varName ;
    public final XQueryParser.variable_return variable() throws RecognitionException {
        XQueryParser.variable_return retval = new XQueryParser.variable_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal157=null;
        XQueryParser.varName_return varName158 = null;


        Object char_literal157_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "variable");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(75, 1);

        try {
            // XQuery.g:75:10: ( '$' varName )
            dbg.enterAlt(1);

            // XQuery.g:75:12: '$' varName
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(75,12);
            char_literal157=(Token)input.LT(1);
            match(input,146,FOLLOW_146_in_variable819); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal157_tree = (Object)adaptor.create(char_literal157);
            adaptor.addChild(root_0, char_literal157_tree);
            }
            dbg.location(75,16);
            pushFollow(FOLLOW_varName_in_variable821);
            varName158=varName();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, varName158.getTree());
            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(75, 23);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "variable");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end variable

    public static class forClauseExt_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start forClauseExt
    // XQuery.g:76:1: forClauseExt : ( COMMA variable ( typeDeclaration )? ( positionalVar )? IN exprSingle ) ;
    public final XQueryParser.forClauseExt_return forClauseExt() throws RecognitionException {
        XQueryParser.forClauseExt_return retval = new XQueryParser.forClauseExt_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token COMMA159=null;
        Token IN163=null;
        XQueryParser.variable_return variable160 = null;

        XQueryParser.typeDeclaration_return typeDeclaration161 = null;

        XQueryParser.positionalVar_return positionalVar162 = null;

        XQueryParser.exprSingle_return exprSingle164 = null;


        Object COMMA159_tree=null;
        Object IN163_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "forClauseExt");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(76, 1);

        try {
            // XQuery.g:76:14: ( ( COMMA variable ( typeDeclaration )? ( positionalVar )? IN exprSingle ) )
            dbg.enterAlt(1);

            // XQuery.g:76:16: ( COMMA variable ( typeDeclaration )? ( positionalVar )? IN exprSingle )
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(76,16);
            // XQuery.g:76:16: ( COMMA variable ( typeDeclaration )? ( positionalVar )? IN exprSingle )
            dbg.enterAlt(1);

            // XQuery.g:76:17: COMMA variable ( typeDeclaration )? ( positionalVar )? IN exprSingle
            {
            dbg.location(76,17);
            COMMA159=(Token)input.LT(1);
            match(input,COMMA,FOLLOW_COMMA_in_forClauseExt829); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            COMMA159_tree = (Object)adaptor.create(COMMA159);
            adaptor.addChild(root_0, COMMA159_tree);
            }
            dbg.location(76,23);
            pushFollow(FOLLOW_variable_in_forClauseExt831);
            variable160=variable();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, variable160.getTree());
            dbg.location(76,32);
            // XQuery.g:76:32: ( typeDeclaration )?
            int alt32=2;
            try { dbg.enterSubRule(32);
            try { dbg.enterDecision(32);

            int LA32_0 = input.LA(1);

            if ( (LA32_0==AS) ) {
                alt32=1;
            }
            } finally {dbg.exitDecision(32);}

            switch (alt32) {
                case 1 :
                    dbg.enterAlt(1);

                    // XQuery.g:76:32: typeDeclaration
                    {
                    dbg.location(76,32);
                    pushFollow(FOLLOW_typeDeclaration_in_forClauseExt833);
                    typeDeclaration161=typeDeclaration();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, typeDeclaration161.getTree());
                    dbg.location(76,32);


                    }
                    break;

            }
            } finally {dbg.exitSubRule(32);}

            dbg.location(76,49);
            // XQuery.g:76:49: ( positionalVar )?
            int alt33=2;
            try { dbg.enterSubRule(33);
            try { dbg.enterDecision(33);

            int LA33_0 = input.LA(1);

            if ( (LA33_0==AT) ) {
                alt33=1;
            }
            } finally {dbg.exitDecision(33);}

            switch (alt33) {
                case 1 :
                    dbg.enterAlt(1);

                    // XQuery.g:76:49: positionalVar
                    {
                    dbg.location(76,49);
                    pushFollow(FOLLOW_positionalVar_in_forClauseExt836);
                    positionalVar162=positionalVar();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, positionalVar162.getTree());
                    dbg.location(76,49);


                    }
                    break;

            }
            } finally {dbg.exitSubRule(33);}

            dbg.location(76,64);
            IN163=(Token)input.LT(1);
            match(input,IN,FOLLOW_IN_in_forClauseExt839); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            IN163_tree = (Object)adaptor.create(IN163);
            adaptor.addChild(root_0, IN163_tree);
            }
            dbg.location(76,67);
            pushFollow(FOLLOW_exprSingle_in_forClauseExt841);
            exprSingle164=exprSingle();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, exprSingle164.getTree());
            dbg.location(76,77);


            }

            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(76, 78);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "forClauseExt");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end forClauseExt

    public static class positionalVar_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start positionalVar
    // XQuery.g:77:1: positionalVar : AT variable ;
    public final XQueryParser.positionalVar_return positionalVar() throws RecognitionException {
        XQueryParser.positionalVar_return retval = new XQueryParser.positionalVar_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token AT165=null;
        XQueryParser.variable_return variable166 = null;


        Object AT165_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "positionalVar");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(77, 1);

        try {
            // XQuery.g:77:15: ( AT variable )
            dbg.enterAlt(1);

            // XQuery.g:77:17: AT variable
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(77,17);
            AT165=(Token)input.LT(1);
            match(input,AT,FOLLOW_AT_in_positionalVar849); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            AT165_tree = (Object)adaptor.create(AT165);
            adaptor.addChild(root_0, AT165_tree);
            }
            dbg.location(77,20);
            pushFollow(FOLLOW_variable_in_positionalVar851);
            variable166=variable();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, variable166.getTree());
            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(77, 29);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "positionalVar");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end positionalVar

    public static class letClause_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start letClause
    // XQuery.g:78:1: letClause : LET variable ( typeDeclaration )? ':=' exprSingle ( letClauseExt )* ;
    public final XQueryParser.letClause_return letClause() throws RecognitionException {
        XQueryParser.letClause_return retval = new XQueryParser.letClause_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token LET167=null;
        Token string_literal170=null;
        XQueryParser.variable_return variable168 = null;

        XQueryParser.typeDeclaration_return typeDeclaration169 = null;

        XQueryParser.exprSingle_return exprSingle171 = null;

        XQueryParser.letClauseExt_return letClauseExt172 = null;


        Object LET167_tree=null;
        Object string_literal170_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "letClause");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(78, 1);

        try {
            // XQuery.g:78:11: ( LET variable ( typeDeclaration )? ':=' exprSingle ( letClauseExt )* )
            dbg.enterAlt(1);

            // XQuery.g:78:13: LET variable ( typeDeclaration )? ':=' exprSingle ( letClauseExt )*
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(78,13);
            LET167=(Token)input.LT(1);
            match(input,LET,FOLLOW_LET_in_letClause859); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            LET167_tree = (Object)adaptor.create(LET167);
            adaptor.addChild(root_0, LET167_tree);
            }
            dbg.location(78,17);
            pushFollow(FOLLOW_variable_in_letClause861);
            variable168=variable();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, variable168.getTree());
            dbg.location(78,26);
            // XQuery.g:78:26: ( typeDeclaration )?
            int alt34=2;
            try { dbg.enterSubRule(34);
            try { dbg.enterDecision(34);

            int LA34_0 = input.LA(1);

            if ( (LA34_0==AS) ) {
                alt34=1;
            }
            } finally {dbg.exitDecision(34);}

            switch (alt34) {
                case 1 :
                    dbg.enterAlt(1);

                    // XQuery.g:78:26: typeDeclaration
                    {
                    dbg.location(78,26);
                    pushFollow(FOLLOW_typeDeclaration_in_letClause863);
                    typeDeclaration169=typeDeclaration();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, typeDeclaration169.getTree());
                    dbg.location(78,26);


                    }
                    break;

            }
            } finally {dbg.exitSubRule(34);}

            dbg.location(78,43);
            string_literal170=(Token)input.LT(1);
            match(input,145,FOLLOW_145_in_letClause866); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            string_literal170_tree = (Object)adaptor.create(string_literal170);
            adaptor.addChild(root_0, string_literal170_tree);
            }
            dbg.location(78,48);
            pushFollow(FOLLOW_exprSingle_in_letClause868);
            exprSingle171=exprSingle();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, exprSingle171.getTree());
            dbg.location(78,59);
            // XQuery.g:78:59: ( letClauseExt )*
            try { dbg.enterSubRule(35);

            loop35:
            do {
                int alt35=2;
                try { dbg.enterDecision(35);

                int LA35_0 = input.LA(1);

                if ( (LA35_0==COMMA) ) {
                    alt35=1;
                }


                } finally {dbg.exitDecision(35);}

                switch (alt35) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // XQuery.g:78:59: letClauseExt
            	    {
            	    dbg.location(78,59);
            	    pushFollow(FOLLOW_letClauseExt_in_letClause870);
            	    letClauseExt172=letClauseExt();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, letClauseExt172.getTree());
            	    dbg.location(78,59);


            	    }
            	    break;

            	default :
            	    break loop35;
                }
            } while (true);
            } finally {dbg.exitSubRule(35);}

            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(78, 73);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "letClause");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end letClause

    public static class letClauseExt_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start letClauseExt
    // XQuery.g:79:1: letClauseExt : ( COMMA variable ( typeDeclaration )? ':=' exprSingle ) ;
    public final XQueryParser.letClauseExt_return letClauseExt() throws RecognitionException {
        XQueryParser.letClauseExt_return retval = new XQueryParser.letClauseExt_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token COMMA173=null;
        Token string_literal176=null;
        XQueryParser.variable_return variable174 = null;

        XQueryParser.typeDeclaration_return typeDeclaration175 = null;

        XQueryParser.exprSingle_return exprSingle177 = null;


        Object COMMA173_tree=null;
        Object string_literal176_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "letClauseExt");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(79, 1);

        try {
            // XQuery.g:79:14: ( ( COMMA variable ( typeDeclaration )? ':=' exprSingle ) )
            dbg.enterAlt(1);

            // XQuery.g:79:16: ( COMMA variable ( typeDeclaration )? ':=' exprSingle )
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(79,16);
            // XQuery.g:79:16: ( COMMA variable ( typeDeclaration )? ':=' exprSingle )
            dbg.enterAlt(1);

            // XQuery.g:79:17: COMMA variable ( typeDeclaration )? ':=' exprSingle
            {
            dbg.location(79,17);
            COMMA173=(Token)input.LT(1);
            match(input,COMMA,FOLLOW_COMMA_in_letClauseExt880); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            COMMA173_tree = (Object)adaptor.create(COMMA173);
            adaptor.addChild(root_0, COMMA173_tree);
            }
            dbg.location(79,23);
            pushFollow(FOLLOW_variable_in_letClauseExt882);
            variable174=variable();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, variable174.getTree());
            dbg.location(79,32);
            // XQuery.g:79:32: ( typeDeclaration )?
            int alt36=2;
            try { dbg.enterSubRule(36);
            try { dbg.enterDecision(36);

            int LA36_0 = input.LA(1);

            if ( (LA36_0==AS) ) {
                alt36=1;
            }
            } finally {dbg.exitDecision(36);}

            switch (alt36) {
                case 1 :
                    dbg.enterAlt(1);

                    // XQuery.g:79:32: typeDeclaration
                    {
                    dbg.location(79,32);
                    pushFollow(FOLLOW_typeDeclaration_in_letClauseExt884);
                    typeDeclaration175=typeDeclaration();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, typeDeclaration175.getTree());
                    dbg.location(79,32);


                    }
                    break;

            }
            } finally {dbg.exitSubRule(36);}

            dbg.location(79,49);
            string_literal176=(Token)input.LT(1);
            match(input,145,FOLLOW_145_in_letClauseExt887); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            string_literal176_tree = (Object)adaptor.create(string_literal176);
            adaptor.addChild(root_0, string_literal176_tree);
            }
            dbg.location(79,54);
            pushFollow(FOLLOW_exprSingle_in_letClauseExt889);
            exprSingle177=exprSingle();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, exprSingle177.getTree());
            dbg.location(79,64);


            }

            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(79, 65);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "letClauseExt");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end letClauseExt

    public static class whereClause_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start whereClause
    // XQuery.g:80:1: whereClause : WHERE exprSingle ;
    public final XQueryParser.whereClause_return whereClause() throws RecognitionException {
        XQueryParser.whereClause_return retval = new XQueryParser.whereClause_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token WHERE178=null;
        XQueryParser.exprSingle_return exprSingle179 = null;


        Object WHERE178_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "whereClause");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(80, 1);

        try {
            // XQuery.g:80:13: ( WHERE exprSingle )
            dbg.enterAlt(1);

            // XQuery.g:80:15: WHERE exprSingle
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(80,15);
            WHERE178=(Token)input.LT(1);
            match(input,WHERE,FOLLOW_WHERE_in_whereClause897); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            WHERE178_tree = (Object)adaptor.create(WHERE178);
            adaptor.addChild(root_0, WHERE178_tree);
            }
            dbg.location(80,21);
            pushFollow(FOLLOW_exprSingle_in_whereClause899);
            exprSingle179=exprSingle();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, exprSingle179.getTree());
            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(80, 32);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "whereClause");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end whereClause

    public static class orderByClause_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start orderByClause
    // XQuery.g:81:1: orderByClause : ( ( ORDER BY ) | ( STABLE ORDER BY ) ) orderSpecList ;
    public final XQueryParser.orderByClause_return orderByClause() throws RecognitionException {
        XQueryParser.orderByClause_return retval = new XQueryParser.orderByClause_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token ORDER180=null;
        Token BY181=null;
        Token STABLE182=null;
        Token ORDER183=null;
        Token BY184=null;
        XQueryParser.orderSpecList_return orderSpecList185 = null;


        Object ORDER180_tree=null;
        Object BY181_tree=null;
        Object STABLE182_tree=null;
        Object ORDER183_tree=null;
        Object BY184_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "orderByClause");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(81, 1);

        try {
            // XQuery.g:81:15: ( ( ( ORDER BY ) | ( STABLE ORDER BY ) ) orderSpecList )
            dbg.enterAlt(1);

            // XQuery.g:81:17: ( ( ORDER BY ) | ( STABLE ORDER BY ) ) orderSpecList
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(81,17);
            // XQuery.g:81:17: ( ( ORDER BY ) | ( STABLE ORDER BY ) )
            int alt37=2;
            try { dbg.enterSubRule(37);
            try { dbg.enterDecision(37);

            int LA37_0 = input.LA(1);

            if ( (LA37_0==ORDER) ) {
                alt37=1;
            }
            else if ( (LA37_0==STABLE) ) {
                alt37=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 37, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;
            }
            } finally {dbg.exitDecision(37);}

            switch (alt37) {
                case 1 :
                    dbg.enterAlt(1);

                    // XQuery.g:81:18: ( ORDER BY )
                    {
                    dbg.location(81,18);
                    // XQuery.g:81:18: ( ORDER BY )
                    dbg.enterAlt(1);

                    // XQuery.g:81:19: ORDER BY
                    {
                    dbg.location(81,19);
                    ORDER180=(Token)input.LT(1);
                    match(input,ORDER,FOLLOW_ORDER_in_orderByClause909); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    ORDER180_tree = (Object)adaptor.create(ORDER180);
                    adaptor.addChild(root_0, ORDER180_tree);
                    }
                    dbg.location(81,25);
                    BY181=(Token)input.LT(1);
                    match(input,BY,FOLLOW_BY_in_orderByClause911); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    BY181_tree = (Object)adaptor.create(BY181);
                    adaptor.addChild(root_0, BY181_tree);
                    }
                    dbg.location(81,27);


                    }

                    dbg.location(81,31);


                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // XQuery.g:81:31: ( STABLE ORDER BY )
                    {
                    dbg.location(81,31);
                    // XQuery.g:81:31: ( STABLE ORDER BY )
                    dbg.enterAlt(1);

                    // XQuery.g:81:32: STABLE ORDER BY
                    {
                    dbg.location(81,32);
                    STABLE182=(Token)input.LT(1);
                    match(input,STABLE,FOLLOW_STABLE_in_orderByClause917); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    STABLE182_tree = (Object)adaptor.create(STABLE182);
                    adaptor.addChild(root_0, STABLE182_tree);
                    }
                    dbg.location(81,39);
                    ORDER183=(Token)input.LT(1);
                    match(input,ORDER,FOLLOW_ORDER_in_orderByClause919); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    ORDER183_tree = (Object)adaptor.create(ORDER183);
                    adaptor.addChild(root_0, ORDER183_tree);
                    }
                    dbg.location(81,45);
                    BY184=(Token)input.LT(1);
                    match(input,BY,FOLLOW_BY_in_orderByClause921); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    BY184_tree = (Object)adaptor.create(BY184);
                    adaptor.addChild(root_0, BY184_tree);
                    }
                    dbg.location(81,47);


                    }

                    dbg.location(81,48);


                    }
                    break;

            }
            } finally {dbg.exitSubRule(37);}

            dbg.location(81,50);
            pushFollow(FOLLOW_orderSpecList_in_orderByClause925);
            orderSpecList185=orderSpecList();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, orderSpecList185.getTree());
            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(81, 64);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "orderByClause");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end orderByClause

    public static class orderSpecList_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start orderSpecList
    // XQuery.g:82:1: orderSpecList : orderSpec ( COMMA orderSpec )* ;
    public final XQueryParser.orderSpecList_return orderSpecList() throws RecognitionException {
        XQueryParser.orderSpecList_return retval = new XQueryParser.orderSpecList_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token COMMA187=null;
        XQueryParser.orderSpec_return orderSpec186 = null;

        XQueryParser.orderSpec_return orderSpec188 = null;


        Object COMMA187_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "orderSpecList");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(82, 1);

        try {
            // XQuery.g:82:15: ( orderSpec ( COMMA orderSpec )* )
            dbg.enterAlt(1);

            // XQuery.g:82:17: orderSpec ( COMMA orderSpec )*
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(82,17);
            pushFollow(FOLLOW_orderSpec_in_orderSpecList933);
            orderSpec186=orderSpec();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, orderSpec186.getTree());
            dbg.location(82,27);
            // XQuery.g:82:27: ( COMMA orderSpec )*
            try { dbg.enterSubRule(38);

            loop38:
            do {
                int alt38=2;
                try { dbg.enterDecision(38);

                int LA38_0 = input.LA(1);

                if ( (LA38_0==COMMA) ) {
                    alt38=1;
                }


                } finally {dbg.exitDecision(38);}

                switch (alt38) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // XQuery.g:82:28: COMMA orderSpec
            	    {
            	    dbg.location(82,28);
            	    COMMA187=(Token)input.LT(1);
            	    match(input,COMMA,FOLLOW_COMMA_in_orderSpecList936); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    COMMA187_tree = (Object)adaptor.create(COMMA187);
            	    adaptor.addChild(root_0, COMMA187_tree);
            	    }
            	    dbg.location(82,34);
            	    pushFollow(FOLLOW_orderSpec_in_orderSpecList938);
            	    orderSpec188=orderSpec();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, orderSpec188.getTree());
            	    dbg.location(82,43);


            	    }
            	    break;

            	default :
            	    break loop38;
                }
            } while (true);
            } finally {dbg.exitSubRule(38);}

            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(82, 46);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "orderSpecList");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end orderSpecList

    public static class orderSpec_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start orderSpec
    // XQuery.g:83:1: orderSpec : exprSingle orderModifier ;
    public final XQueryParser.orderSpec_return orderSpec() throws RecognitionException {
        XQueryParser.orderSpec_return retval = new XQueryParser.orderSpec_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        XQueryParser.exprSingle_return exprSingle189 = null;

        XQueryParser.orderModifier_return orderModifier190 = null;



        try { dbg.enterRule(getGrammarFileName(), "orderSpec");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(83, 1);

        try {
            // XQuery.g:83:11: ( exprSingle orderModifier )
            dbg.enterAlt(1);

            // XQuery.g:83:13: exprSingle orderModifier
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(83,13);
            pushFollow(FOLLOW_exprSingle_in_orderSpec948);
            exprSingle189=exprSingle();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, exprSingle189.getTree());
            dbg.location(83,24);
            pushFollow(FOLLOW_orderModifier_in_orderSpec950);
            orderModifier190=orderModifier();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, orderModifier190.getTree());
            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(83, 38);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "orderSpec");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end orderSpec

    public static class orderModifier_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start orderModifier
    // XQuery.g:84:1: orderModifier : ( ASCENDING | DESCENDING )? ( EMPTY ( GREATEST | LEAST ) )? ( COLLATION uRILiteral )? ;
    public final XQueryParser.orderModifier_return orderModifier() throws RecognitionException {
        XQueryParser.orderModifier_return retval = new XQueryParser.orderModifier_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set191=null;
        Token EMPTY192=null;
        Token set193=null;
        Token COLLATION194=null;
        XQueryParser.uRILiteral_return uRILiteral195 = null;


        Object set191_tree=null;
        Object EMPTY192_tree=null;
        Object set193_tree=null;
        Object COLLATION194_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "orderModifier");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(84, 1);

        try {
            // XQuery.g:84:15: ( ( ASCENDING | DESCENDING )? ( EMPTY ( GREATEST | LEAST ) )? ( COLLATION uRILiteral )? )
            dbg.enterAlt(1);

            // XQuery.g:84:17: ( ASCENDING | DESCENDING )? ( EMPTY ( GREATEST | LEAST ) )? ( COLLATION uRILiteral )?
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(84,17);
            // XQuery.g:84:17: ( ASCENDING | DESCENDING )?
            int alt39=2;
            try { dbg.enterSubRule(39);
            try { dbg.enterDecision(39);

            int LA39_0 = input.LA(1);

            if ( ((LA39_0>=ASCENDING && LA39_0<=DESCENDING)) ) {
                alt39=1;
            }
            } finally {dbg.exitDecision(39);}

            switch (alt39) {
                case 1 :
                    dbg.enterAlt(1);

                    // XQuery.g:
                    {
                    dbg.location(84,17);
                    set191=(Token)input.LT(1);
                    if ( (input.LA(1)>=ASCENDING && input.LA(1)<=DESCENDING) ) {
                        input.consume();
                        if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set191));
                        state.errorRecovery=false;state.failed=false;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        dbg.recognitionException(mse);
                        throw mse;
                    }


                    }
                    break;

            }
            } finally {dbg.exitSubRule(39);}

            dbg.location(84,43);
            // XQuery.g:84:43: ( EMPTY ( GREATEST | LEAST ) )?
            int alt40=2;
            try { dbg.enterSubRule(40);
            try { dbg.enterDecision(40);

            int LA40_0 = input.LA(1);

            if ( (LA40_0==EMPTY) ) {
                alt40=1;
            }
            } finally {dbg.exitDecision(40);}

            switch (alt40) {
                case 1 :
                    dbg.enterAlt(1);

                    // XQuery.g:84:44: EMPTY ( GREATEST | LEAST )
                    {
                    dbg.location(84,44);
                    EMPTY192=(Token)input.LT(1);
                    match(input,EMPTY,FOLLOW_EMPTY_in_orderModifier968); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    EMPTY192_tree = (Object)adaptor.create(EMPTY192);
                    adaptor.addChild(root_0, EMPTY192_tree);
                    }
                    dbg.location(84,50);
                    set193=(Token)input.LT(1);
                    if ( (input.LA(1)>=GREATEST && input.LA(1)<=LEAST) ) {
                        input.consume();
                        if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set193));
                        state.errorRecovery=false;state.failed=false;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        dbg.recognitionException(mse);
                        throw mse;
                    }

                    dbg.location(84,68);


                    }
                    break;

            }
            } finally {dbg.exitSubRule(40);}

            dbg.location(84,71);
            // XQuery.g:84:71: ( COLLATION uRILiteral )?
            int alt41=2;
            try { dbg.enterSubRule(41);
            try { dbg.enterDecision(41);

            int LA41_0 = input.LA(1);

            if ( (LA41_0==COLLATION) ) {
                alt41=1;
            }
            } finally {dbg.exitDecision(41);}

            switch (alt41) {
                case 1 :
                    dbg.enterAlt(1);

                    // XQuery.g:84:72: COLLATION uRILiteral
                    {
                    dbg.location(84,72);
                    COLLATION194=(Token)input.LT(1);
                    match(input,COLLATION,FOLLOW_COLLATION_in_orderModifier981); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    COLLATION194_tree = (Object)adaptor.create(COLLATION194);
                    adaptor.addChild(root_0, COLLATION194_tree);
                    }
                    dbg.location(84,82);
                    pushFollow(FOLLOW_uRILiteral_in_orderModifier983);
                    uRILiteral195=uRILiteral();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, uRILiteral195.getTree());
                    dbg.location(84,92);


                    }
                    break;

            }
            } finally {dbg.exitSubRule(41);}

            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(84, 95);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "orderModifier");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end orderModifier

    public static class quantifiedExpr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start quantifiedExpr
    // XQuery.g:85:1: quantifiedExpr : ( SOME | EVERY ) variable ( typeDeclaration )? IN exprSingle ( quantifiedExprExt )* satisfiesClause ;
    public final XQueryParser.quantifiedExpr_return quantifiedExpr() throws RecognitionException {
        XQueryParser.quantifiedExpr_return retval = new XQueryParser.quantifiedExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set196=null;
        Token IN199=null;
        XQueryParser.variable_return variable197 = null;

        XQueryParser.typeDeclaration_return typeDeclaration198 = null;

        XQueryParser.exprSingle_return exprSingle200 = null;

        XQueryParser.quantifiedExprExt_return quantifiedExprExt201 = null;

        XQueryParser.satisfiesClause_return satisfiesClause202 = null;


        Object set196_tree=null;
        Object IN199_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "quantifiedExpr");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(85, 1);

        try {
            // XQuery.g:85:16: ( ( SOME | EVERY ) variable ( typeDeclaration )? IN exprSingle ( quantifiedExprExt )* satisfiesClause )
            dbg.enterAlt(1);

            // XQuery.g:85:18: ( SOME | EVERY ) variable ( typeDeclaration )? IN exprSingle ( quantifiedExprExt )* satisfiesClause
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(85,18);
            set196=(Token)input.LT(1);
            if ( (input.LA(1)>=SOME && input.LA(1)<=EVERY) ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set196));
                state.errorRecovery=false;state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                dbg.recognitionException(mse);
                throw mse;
            }

            dbg.location(85,33);
            pushFollow(FOLLOW_variable_in_quantifiedExpr1001);
            variable197=variable();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, variable197.getTree());
            dbg.location(85,42);
            // XQuery.g:85:42: ( typeDeclaration )?
            int alt42=2;
            try { dbg.enterSubRule(42);
            try { dbg.enterDecision(42);

            int LA42_0 = input.LA(1);

            if ( (LA42_0==AS) ) {
                alt42=1;
            }
            } finally {dbg.exitDecision(42);}

            switch (alt42) {
                case 1 :
                    dbg.enterAlt(1);

                    // XQuery.g:85:42: typeDeclaration
                    {
                    dbg.location(85,42);
                    pushFollow(FOLLOW_typeDeclaration_in_quantifiedExpr1003);
                    typeDeclaration198=typeDeclaration();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, typeDeclaration198.getTree());
                    dbg.location(85,42);


                    }
                    break;

            }
            } finally {dbg.exitSubRule(42);}

            dbg.location(85,59);
            IN199=(Token)input.LT(1);
            match(input,IN,FOLLOW_IN_in_quantifiedExpr1006); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            IN199_tree = (Object)adaptor.create(IN199);
            adaptor.addChild(root_0, IN199_tree);
            }
            dbg.location(85,62);
            pushFollow(FOLLOW_exprSingle_in_quantifiedExpr1008);
            exprSingle200=exprSingle();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, exprSingle200.getTree());
            dbg.location(85,73);
            // XQuery.g:85:73: ( quantifiedExprExt )*
            try { dbg.enterSubRule(43);

            loop43:
            do {
                int alt43=2;
                try { dbg.enterDecision(43);

                int LA43_0 = input.LA(1);

                if ( (LA43_0==COMMA) ) {
                    alt43=1;
                }


                } finally {dbg.exitDecision(43);}

                switch (alt43) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // XQuery.g:85:73: quantifiedExprExt
            	    {
            	    dbg.location(85,73);
            	    pushFollow(FOLLOW_quantifiedExprExt_in_quantifiedExpr1010);
            	    quantifiedExprExt201=quantifiedExprExt();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, quantifiedExprExt201.getTree());
            	    dbg.location(85,73);


            	    }
            	    break;

            	default :
            	    break loop43;
                }
            } while (true);
            } finally {dbg.exitSubRule(43);}

            dbg.location(85,92);
            pushFollow(FOLLOW_satisfiesClause_in_quantifiedExpr1013);
            satisfiesClause202=satisfiesClause();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, satisfiesClause202.getTree());
            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(85, 107);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "quantifiedExpr");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end quantifiedExpr

    public static class quantifiedExprExt_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start quantifiedExprExt
    // XQuery.g:86:1: quantifiedExprExt : COMMA variable ( typeDeclaration )? IN exprSingle ;
    public final XQueryParser.quantifiedExprExt_return quantifiedExprExt() throws RecognitionException {
        XQueryParser.quantifiedExprExt_return retval = new XQueryParser.quantifiedExprExt_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token COMMA203=null;
        Token IN206=null;
        XQueryParser.variable_return variable204 = null;

        XQueryParser.typeDeclaration_return typeDeclaration205 = null;

        XQueryParser.exprSingle_return exprSingle207 = null;


        Object COMMA203_tree=null;
        Object IN206_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "quantifiedExprExt");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(86, 1);

        try {
            // XQuery.g:87:3: ( COMMA variable ( typeDeclaration )? IN exprSingle )
            dbg.enterAlt(1);

            // XQuery.g:87:5: COMMA variable ( typeDeclaration )? IN exprSingle
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(87,5);
            COMMA203=(Token)input.LT(1);
            match(input,COMMA,FOLLOW_COMMA_in_quantifiedExprExt1022); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            COMMA203_tree = (Object)adaptor.create(COMMA203);
            adaptor.addChild(root_0, COMMA203_tree);
            }
            dbg.location(87,11);
            pushFollow(FOLLOW_variable_in_quantifiedExprExt1024);
            variable204=variable();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, variable204.getTree());
            dbg.location(87,20);
            // XQuery.g:87:20: ( typeDeclaration )?
            int alt44=2;
            try { dbg.enterSubRule(44);
            try { dbg.enterDecision(44);

            int LA44_0 = input.LA(1);

            if ( (LA44_0==AS) ) {
                alt44=1;
            }
            } finally {dbg.exitDecision(44);}

            switch (alt44) {
                case 1 :
                    dbg.enterAlt(1);

                    // XQuery.g:87:20: typeDeclaration
                    {
                    dbg.location(87,20);
                    pushFollow(FOLLOW_typeDeclaration_in_quantifiedExprExt1026);
                    typeDeclaration205=typeDeclaration();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, typeDeclaration205.getTree());
                    dbg.location(87,20);


                    }
                    break;

            }
            } finally {dbg.exitSubRule(44);}

            dbg.location(87,37);
            IN206=(Token)input.LT(1);
            match(input,IN,FOLLOW_IN_in_quantifiedExprExt1029); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            IN206_tree = (Object)adaptor.create(IN206);
            adaptor.addChild(root_0, IN206_tree);
            }
            dbg.location(87,40);
            pushFollow(FOLLOW_exprSingle_in_quantifiedExprExt1031);
            exprSingle207=exprSingle();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, exprSingle207.getTree());
            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(87, 50);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "quantifiedExprExt");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end quantifiedExprExt

    public static class satisfiesClause_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start satisfiesClause
    // XQuery.g:88:1: satisfiesClause : SATISFIES exprSingle ;
    public final XQueryParser.satisfiesClause_return satisfiesClause() throws RecognitionException {
        XQueryParser.satisfiesClause_return retval = new XQueryParser.satisfiesClause_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token SATISFIES208=null;
        XQueryParser.exprSingle_return exprSingle209 = null;


        Object SATISFIES208_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "satisfiesClause");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(88, 1);

        try {
            // XQuery.g:88:17: ( SATISFIES exprSingle )
            dbg.enterAlt(1);

            // XQuery.g:88:19: SATISFIES exprSingle
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(88,19);
            SATISFIES208=(Token)input.LT(1);
            match(input,SATISFIES,FOLLOW_SATISFIES_in_satisfiesClause1038); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            SATISFIES208_tree = (Object)adaptor.create(SATISFIES208);
            adaptor.addChild(root_0, SATISFIES208_tree);
            }
            dbg.location(88,29);
            pushFollow(FOLLOW_exprSingle_in_satisfiesClause1040);
            exprSingle209=exprSingle();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, exprSingle209.getTree());
            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(88, 39);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "satisfiesClause");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end satisfiesClause

    public static class typeswitchExpr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start typeswitchExpr
    // XQuery.g:89:1: typeswitchExpr : TYPESWITCH LPAREN expr RPAREN ( caseClause )+ defaultClause ;
    public final XQueryParser.typeswitchExpr_return typeswitchExpr() throws RecognitionException {
        XQueryParser.typeswitchExpr_return retval = new XQueryParser.typeswitchExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token TYPESWITCH210=null;
        Token LPAREN211=null;
        Token RPAREN213=null;
        XQueryParser.expr_return expr212 = null;

        XQueryParser.caseClause_return caseClause214 = null;

        XQueryParser.defaultClause_return defaultClause215 = null;


        Object TYPESWITCH210_tree=null;
        Object LPAREN211_tree=null;
        Object RPAREN213_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "typeswitchExpr");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(89, 1);

        try {
            // XQuery.g:89:16: ( TYPESWITCH LPAREN expr RPAREN ( caseClause )+ defaultClause )
            dbg.enterAlt(1);

            // XQuery.g:89:18: TYPESWITCH LPAREN expr RPAREN ( caseClause )+ defaultClause
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(89,18);
            TYPESWITCH210=(Token)input.LT(1);
            match(input,TYPESWITCH,FOLLOW_TYPESWITCH_in_typeswitchExpr1047); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            TYPESWITCH210_tree = (Object)adaptor.create(TYPESWITCH210);
            adaptor.addChild(root_0, TYPESWITCH210_tree);
            }
            dbg.location(89,29);
            LPAREN211=(Token)input.LT(1);
            match(input,LPAREN,FOLLOW_LPAREN_in_typeswitchExpr1049); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            LPAREN211_tree = (Object)adaptor.create(LPAREN211);
            adaptor.addChild(root_0, LPAREN211_tree);
            }
            dbg.location(89,36);
            pushFollow(FOLLOW_expr_in_typeswitchExpr1051);
            expr212=expr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, expr212.getTree());
            dbg.location(89,41);
            RPAREN213=(Token)input.LT(1);
            match(input,RPAREN,FOLLOW_RPAREN_in_typeswitchExpr1053); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            RPAREN213_tree = (Object)adaptor.create(RPAREN213);
            adaptor.addChild(root_0, RPAREN213_tree);
            }
            dbg.location(89,48);
            // XQuery.g:89:48: ( caseClause )+
            int cnt45=0;
            try { dbg.enterSubRule(45);

            loop45:
            do {
                int alt45=2;
                try { dbg.enterDecision(45);

                int LA45_0 = input.LA(1);

                if ( (LA45_0==CASE) ) {
                    alt45=1;
                }


                } finally {dbg.exitDecision(45);}

                switch (alt45) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // XQuery.g:89:48: caseClause
            	    {
            	    dbg.location(89,48);
            	    pushFollow(FOLLOW_caseClause_in_typeswitchExpr1055);
            	    caseClause214=caseClause();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, caseClause214.getTree());
            	    dbg.location(89,48);


            	    }
            	    break;

            	default :
            	    if ( cnt45 >= 1 ) break loop45;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(45, input);
                        dbg.recognitionException(eee);

                        throw eee;
                }
                cnt45++;
            } while (true);
            } finally {dbg.exitSubRule(45);}

            dbg.location(89,60);
            pushFollow(FOLLOW_defaultClause_in_typeswitchExpr1058);
            defaultClause215=defaultClause();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, defaultClause215.getTree());
            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(89, 73);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "typeswitchExpr");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end typeswitchExpr

    public static class defaultClause_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start defaultClause
    // XQuery.g:90:1: defaultClause : DEFAULT ( variable )? RETURN exprSingle ;
    public final XQueryParser.defaultClause_return defaultClause() throws RecognitionException {
        XQueryParser.defaultClause_return retval = new XQueryParser.defaultClause_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token DEFAULT216=null;
        Token RETURN218=null;
        XQueryParser.variable_return variable217 = null;

        XQueryParser.exprSingle_return exprSingle219 = null;


        Object DEFAULT216_tree=null;
        Object RETURN218_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "defaultClause");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(90, 1);

        try {
            // XQuery.g:90:15: ( DEFAULT ( variable )? RETURN exprSingle )
            dbg.enterAlt(1);

            // XQuery.g:90:17: DEFAULT ( variable )? RETURN exprSingle
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(90,17);
            DEFAULT216=(Token)input.LT(1);
            match(input,DEFAULT,FOLLOW_DEFAULT_in_defaultClause1065); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            DEFAULT216_tree = (Object)adaptor.create(DEFAULT216);
            adaptor.addChild(root_0, DEFAULT216_tree);
            }
            dbg.location(90,25);
            // XQuery.g:90:25: ( variable )?
            int alt46=2;
            try { dbg.enterSubRule(46);
            try { dbg.enterDecision(46);

            int LA46_0 = input.LA(1);

            if ( (LA46_0==146) ) {
                alt46=1;
            }
            } finally {dbg.exitDecision(46);}

            switch (alt46) {
                case 1 :
                    dbg.enterAlt(1);

                    // XQuery.g:90:26: variable
                    {
                    dbg.location(90,26);
                    pushFollow(FOLLOW_variable_in_defaultClause1068);
                    variable217=variable();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, variable217.getTree());
                    dbg.location(90,34);


                    }
                    break;

            }
            } finally {dbg.exitSubRule(46);}

            dbg.location(90,37);
            RETURN218=(Token)input.LT(1);
            match(input,RETURN,FOLLOW_RETURN_in_defaultClause1072); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            RETURN218_tree = (Object)adaptor.create(RETURN218);
            adaptor.addChild(root_0, RETURN218_tree);
            }
            dbg.location(90,44);
            pushFollow(FOLLOW_exprSingle_in_defaultClause1074);
            exprSingle219=exprSingle();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, exprSingle219.getTree());
            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(90, 54);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "defaultClause");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end defaultClause

    public static class caseClause_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start caseClause
    // XQuery.g:91:1: caseClause : CASE ( variable AS )? sequenceType RETURN exprSingle ;
    public final XQueryParser.caseClause_return caseClause() throws RecognitionException {
        XQueryParser.caseClause_return retval = new XQueryParser.caseClause_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token CASE220=null;
        Token AS222=null;
        Token RETURN224=null;
        XQueryParser.variable_return variable221 = null;

        XQueryParser.sequenceType_return sequenceType223 = null;

        XQueryParser.exprSingle_return exprSingle225 = null;


        Object CASE220_tree=null;
        Object AS222_tree=null;
        Object RETURN224_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "caseClause");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(91, 1);

        try {
            // XQuery.g:91:12: ( CASE ( variable AS )? sequenceType RETURN exprSingle )
            dbg.enterAlt(1);

            // XQuery.g:91:14: CASE ( variable AS )? sequenceType RETURN exprSingle
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(91,14);
            CASE220=(Token)input.LT(1);
            match(input,CASE,FOLLOW_CASE_in_caseClause1081); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            CASE220_tree = (Object)adaptor.create(CASE220);
            adaptor.addChild(root_0, CASE220_tree);
            }
            dbg.location(91,19);
            // XQuery.g:91:19: ( variable AS )?
            int alt47=2;
            try { dbg.enterSubRule(47);
            try { dbg.enterDecision(47);

            int LA47_0 = input.LA(1);

            if ( (LA47_0==146) ) {
                alt47=1;
            }
            } finally {dbg.exitDecision(47);}

            switch (alt47) {
                case 1 :
                    dbg.enterAlt(1);

                    // XQuery.g:91:20: variable AS
                    {
                    dbg.location(91,20);
                    pushFollow(FOLLOW_variable_in_caseClause1084);
                    variable221=variable();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, variable221.getTree());
                    dbg.location(91,29);
                    AS222=(Token)input.LT(1);
                    match(input,AS,FOLLOW_AS_in_caseClause1086); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    AS222_tree = (Object)adaptor.create(AS222);
                    adaptor.addChild(root_0, AS222_tree);
                    }
                    dbg.location(91,31);


                    }
                    break;

            }
            } finally {dbg.exitSubRule(47);}

            dbg.location(91,34);
            pushFollow(FOLLOW_sequenceType_in_caseClause1090);
            sequenceType223=sequenceType();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, sequenceType223.getTree());
            dbg.location(91,47);
            RETURN224=(Token)input.LT(1);
            match(input,RETURN,FOLLOW_RETURN_in_caseClause1092); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            RETURN224_tree = (Object)adaptor.create(RETURN224);
            adaptor.addChild(root_0, RETURN224_tree);
            }
            dbg.location(91,54);
            pushFollow(FOLLOW_exprSingle_in_caseClause1094);
            exprSingle225=exprSingle();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, exprSingle225.getTree());
            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(91, 64);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "caseClause");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end caseClause

    public static class ifExpr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start ifExpr
    // XQuery.g:92:1: ifExpr : IF LPAREN expr RPAREN thenClause elseClause ;
    public final XQueryParser.ifExpr_return ifExpr() throws RecognitionException {
        XQueryParser.ifExpr_return retval = new XQueryParser.ifExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token IF226=null;
        Token LPAREN227=null;
        Token RPAREN229=null;
        XQueryParser.expr_return expr228 = null;

        XQueryParser.thenClause_return thenClause230 = null;

        XQueryParser.elseClause_return elseClause231 = null;


        Object IF226_tree=null;
        Object LPAREN227_tree=null;
        Object RPAREN229_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "ifExpr");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(92, 1);

        try {
            // XQuery.g:92:9: ( IF LPAREN expr RPAREN thenClause elseClause )
            dbg.enterAlt(1);

            // XQuery.g:92:11: IF LPAREN expr RPAREN thenClause elseClause
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(92,11);
            IF226=(Token)input.LT(1);
            match(input,IF,FOLLOW_IF_in_ifExpr1102); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            IF226_tree = (Object)adaptor.create(IF226);
            adaptor.addChild(root_0, IF226_tree);
            }
            dbg.location(92,14);
            LPAREN227=(Token)input.LT(1);
            match(input,LPAREN,FOLLOW_LPAREN_in_ifExpr1104); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            LPAREN227_tree = (Object)adaptor.create(LPAREN227);
            adaptor.addChild(root_0, LPAREN227_tree);
            }
            dbg.location(92,21);
            pushFollow(FOLLOW_expr_in_ifExpr1106);
            expr228=expr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, expr228.getTree());
            dbg.location(92,26);
            RPAREN229=(Token)input.LT(1);
            match(input,RPAREN,FOLLOW_RPAREN_in_ifExpr1108); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            RPAREN229_tree = (Object)adaptor.create(RPAREN229);
            adaptor.addChild(root_0, RPAREN229_tree);
            }
            dbg.location(92,33);
            pushFollow(FOLLOW_thenClause_in_ifExpr1110);
            thenClause230=thenClause();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, thenClause230.getTree());
            dbg.location(92,44);
            pushFollow(FOLLOW_elseClause_in_ifExpr1112);
            elseClause231=elseClause();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, elseClause231.getTree());
            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(92, 54);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "ifExpr");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end ifExpr

    public static class thenClause_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start thenClause
    // XQuery.g:93:1: thenClause : THEN exprSingle ;
    public final XQueryParser.thenClause_return thenClause() throws RecognitionException {
        XQueryParser.thenClause_return retval = new XQueryParser.thenClause_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token THEN232=null;
        XQueryParser.exprSingle_return exprSingle233 = null;


        Object THEN232_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "thenClause");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(93, 1);

        try {
            // XQuery.g:93:12: ( THEN exprSingle )
            dbg.enterAlt(1);

            // XQuery.g:93:14: THEN exprSingle
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(93,14);
            THEN232=(Token)input.LT(1);
            match(input,THEN,FOLLOW_THEN_in_thenClause1119); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            THEN232_tree = (Object)adaptor.create(THEN232);
            adaptor.addChild(root_0, THEN232_tree);
            }
            dbg.location(93,19);
            pushFollow(FOLLOW_exprSingle_in_thenClause1121);
            exprSingle233=exprSingle();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, exprSingle233.getTree());
            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(93, 29);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "thenClause");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end thenClause

    public static class elseClause_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start elseClause
    // XQuery.g:94:1: elseClause : ELSE exprSingle ;
    public final XQueryParser.elseClause_return elseClause() throws RecognitionException {
        XQueryParser.elseClause_return retval = new XQueryParser.elseClause_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token ELSE234=null;
        XQueryParser.exprSingle_return exprSingle235 = null;


        Object ELSE234_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "elseClause");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(94, 1);

        try {
            // XQuery.g:94:12: ( ELSE exprSingle )
            dbg.enterAlt(1);

            // XQuery.g:94:15: ELSE exprSingle
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(94,15);
            ELSE234=(Token)input.LT(1);
            match(input,ELSE,FOLLOW_ELSE_in_elseClause1129); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            ELSE234_tree = (Object)adaptor.create(ELSE234);
            adaptor.addChild(root_0, ELSE234_tree);
            }
            dbg.location(94,20);
            pushFollow(FOLLOW_exprSingle_in_elseClause1131);
            exprSingle235=exprSingle();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, exprSingle235.getTree());
            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(94, 30);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "elseClause");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end elseClause

    public static class orExpr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start orExpr
    // XQuery.g:95:1: orExpr : andExpr ( OR andExpr )* ;
    public final XQueryParser.orExpr_return orExpr() throws RecognitionException {
        XQueryParser.orExpr_return retval = new XQueryParser.orExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token OR237=null;
        XQueryParser.andExpr_return andExpr236 = null;

        XQueryParser.andExpr_return andExpr238 = null;


        Object OR237_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "orExpr");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(95, 1);

        try {
            // XQuery.g:95:9: ( andExpr ( OR andExpr )* )
            dbg.enterAlt(1);

            // XQuery.g:95:11: andExpr ( OR andExpr )*
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(95,11);
            pushFollow(FOLLOW_andExpr_in_orExpr1139);
            andExpr236=andExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, andExpr236.getTree());
            dbg.location(95,19);
            // XQuery.g:95:19: ( OR andExpr )*
            try { dbg.enterSubRule(48);

            loop48:
            do {
                int alt48=2;
                try { dbg.enterDecision(48);

                int LA48_0 = input.LA(1);

                if ( (LA48_0==OR) ) {
                    alt48=1;
                }


                } finally {dbg.exitDecision(48);}

                switch (alt48) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // XQuery.g:95:21: OR andExpr
            	    {
            	    dbg.location(95,21);
            	    OR237=(Token)input.LT(1);
            	    match(input,OR,FOLLOW_OR_in_orExpr1143); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    OR237_tree = (Object)adaptor.create(OR237);
            	    adaptor.addChild(root_0, OR237_tree);
            	    }
            	    dbg.location(95,24);
            	    pushFollow(FOLLOW_andExpr_in_orExpr1145);
            	    andExpr238=andExpr();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, andExpr238.getTree());
            	    dbg.location(95,32);


            	    }
            	    break;

            	default :
            	    break loop48;
                }
            } while (true);
            } finally {dbg.exitSubRule(48);}

            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(95, 35);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "orExpr");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end orExpr

    public static class andExpr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start andExpr
    // XQuery.g:96:1: andExpr : comparisonExpr ( AND comparisonExpr )* ;
    public final XQueryParser.andExpr_return andExpr() throws RecognitionException {
        XQueryParser.andExpr_return retval = new XQueryParser.andExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token AND240=null;
        XQueryParser.comparisonExpr_return comparisonExpr239 = null;

        XQueryParser.comparisonExpr_return comparisonExpr241 = null;


        Object AND240_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "andExpr");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(96, 1);

        try {
            // XQuery.g:96:10: ( comparisonExpr ( AND comparisonExpr )* )
            dbg.enterAlt(1);

            // XQuery.g:96:12: comparisonExpr ( AND comparisonExpr )*
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(96,12);
            pushFollow(FOLLOW_comparisonExpr_in_andExpr1157);
            comparisonExpr239=comparisonExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, comparisonExpr239.getTree());
            dbg.location(96,27);
            // XQuery.g:96:27: ( AND comparisonExpr )*
            try { dbg.enterSubRule(49);

            loop49:
            do {
                int alt49=2;
                try { dbg.enterDecision(49);

                int LA49_0 = input.LA(1);

                if ( (LA49_0==AND) ) {
                    alt49=1;
                }


                } finally {dbg.exitDecision(49);}

                switch (alt49) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // XQuery.g:96:29: AND comparisonExpr
            	    {
            	    dbg.location(96,29);
            	    AND240=(Token)input.LT(1);
            	    match(input,AND,FOLLOW_AND_in_andExpr1161); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    AND240_tree = (Object)adaptor.create(AND240);
            	    adaptor.addChild(root_0, AND240_tree);
            	    }
            	    dbg.location(96,33);
            	    pushFollow(FOLLOW_comparisonExpr_in_andExpr1163);
            	    comparisonExpr241=comparisonExpr();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, comparisonExpr241.getTree());
            	    dbg.location(96,48);


            	    }
            	    break;

            	default :
            	    break loop49;
                }
            } while (true);
            } finally {dbg.exitSubRule(49);}

            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(96, 51);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "andExpr");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end andExpr

    public static class comparisonExpr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start comparisonExpr
    // XQuery.g:97:1: comparisonExpr : rangeExpr ( ( valueComp | generalComp | nodeComp ) rangeExpr )? ;
    public final XQueryParser.comparisonExpr_return comparisonExpr() throws RecognitionException {
        XQueryParser.comparisonExpr_return retval = new XQueryParser.comparisonExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        XQueryParser.rangeExpr_return rangeExpr242 = null;

        XQueryParser.valueComp_return valueComp243 = null;

        XQueryParser.generalComp_return generalComp244 = null;

        XQueryParser.nodeComp_return nodeComp245 = null;

        XQueryParser.rangeExpr_return rangeExpr246 = null;



        try { dbg.enterRule(getGrammarFileName(), "comparisonExpr");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(97, 1);

        try {
            // XQuery.g:97:16: ( rangeExpr ( ( valueComp | generalComp | nodeComp ) rangeExpr )? )
            dbg.enterAlt(1);

            // XQuery.g:97:18: rangeExpr ( ( valueComp | generalComp | nodeComp ) rangeExpr )?
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(97,18);
            pushFollow(FOLLOW_rangeExpr_in_comparisonExpr1174);
            rangeExpr242=rangeExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, rangeExpr242.getTree());
            dbg.location(97,28);
            // XQuery.g:97:28: ( ( valueComp | generalComp | nodeComp ) rangeExpr )?
            int alt51=2;
            try { dbg.enterSubRule(51);
            try { dbg.enterDecision(51);

            int LA51_0 = input.LA(1);

            if ( ((LA51_0>=CLOSE_ANGLE && LA51_0<=OPEN_ANGLE)||LA51_0==Lit_EQ||(LA51_0>=EQ && LA51_0<=IS)||(LA51_0>=151 && LA51_0<=155)) ) {
                alt51=1;
            }
            } finally {dbg.exitDecision(51);}

            switch (alt51) {
                case 1 :
                    dbg.enterAlt(1);

                    // XQuery.g:97:30: ( valueComp | generalComp | nodeComp ) rangeExpr
                    {
                    dbg.location(97,30);
                    // XQuery.g:97:30: ( valueComp | generalComp | nodeComp )
                    int alt50=3;
                    try { dbg.enterSubRule(50);
                    try { dbg.enterDecision(50);

                    switch ( input.LA(1) ) {
                    case EQ:
                    case NE:
                    case LT:
                    case LE:
                    case GT:
                    case GE:
                        {
                        alt50=1;
                        }
                        break;
                    case CLOSE_ANGLE:
                    case OPEN_ANGLE:
                    case Lit_EQ:
                    case 151:
                    case 152:
                    case 153:
                        {
                        alt50=2;
                        }
                        break;
                    case IS:
                    case 154:
                    case 155:
                        {
                        alt50=3;
                        }
                        break;
                    default:
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 50, 0, input);

                        dbg.recognitionException(nvae);
                        throw nvae;
                    }

                    } finally {dbg.exitDecision(50);}

                    switch (alt50) {
                        case 1 :
                            dbg.enterAlt(1);

                            // XQuery.g:97:31: valueComp
                            {
                            dbg.location(97,31);
                            pushFollow(FOLLOW_valueComp_in_comparisonExpr1179);
                            valueComp243=valueComp();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, valueComp243.getTree());
                            dbg.location(97,44);


                            }
                            break;
                        case 2 :
                            dbg.enterAlt(2);

                            // XQuery.g:97:44: generalComp
                            {
                            dbg.location(97,44);
                            pushFollow(FOLLOW_generalComp_in_comparisonExpr1184);
                            generalComp244=generalComp();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, generalComp244.getTree());
                            dbg.location(97,59);


                            }
                            break;
                        case 3 :
                            dbg.enterAlt(3);

                            // XQuery.g:97:59: nodeComp
                            {
                            dbg.location(97,59);
                            pushFollow(FOLLOW_nodeComp_in_comparisonExpr1189);
                            nodeComp245=nodeComp();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, nodeComp245.getTree());
                            dbg.location(97,67);


                            }
                            break;

                    }
                    } finally {dbg.exitSubRule(50);}

                    dbg.location(97,69);
                    pushFollow(FOLLOW_rangeExpr_in_comparisonExpr1192);
                    rangeExpr246=rangeExpr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, rangeExpr246.getTree());
                    dbg.location(97,79);


                    }
                    break;

            }
            } finally {dbg.exitSubRule(51);}

            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(97, 82);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "comparisonExpr");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end comparisonExpr

    public static class rangeExpr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start rangeExpr
    // XQuery.g:98:1: rangeExpr : additiveExpr ( TO additiveExpr )? ;
    public final XQueryParser.rangeExpr_return rangeExpr() throws RecognitionException {
        XQueryParser.rangeExpr_return retval = new XQueryParser.rangeExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token TO248=null;
        XQueryParser.additiveExpr_return additiveExpr247 = null;

        XQueryParser.additiveExpr_return additiveExpr249 = null;


        Object TO248_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "rangeExpr");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(98, 1);

        try {
            // XQuery.g:98:11: ( additiveExpr ( TO additiveExpr )? )
            dbg.enterAlt(1);

            // XQuery.g:98:13: additiveExpr ( TO additiveExpr )?
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(98,13);
            pushFollow(FOLLOW_additiveExpr_in_rangeExpr1203);
            additiveExpr247=additiveExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, additiveExpr247.getTree());
            dbg.location(98,26);
            // XQuery.g:98:26: ( TO additiveExpr )?
            int alt52=2;
            try { dbg.enterSubRule(52);
            try { dbg.enterDecision(52);

            int LA52_0 = input.LA(1);

            if ( (LA52_0==TO) ) {
                alt52=1;
            }
            } finally {dbg.exitDecision(52);}

            switch (alt52) {
                case 1 :
                    dbg.enterAlt(1);

                    // XQuery.g:98:28: TO additiveExpr
                    {
                    dbg.location(98,28);
                    TO248=(Token)input.LT(1);
                    match(input,TO,FOLLOW_TO_in_rangeExpr1207); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    TO248_tree = (Object)adaptor.create(TO248);
                    adaptor.addChild(root_0, TO248_tree);
                    }
                    dbg.location(98,31);
                    pushFollow(FOLLOW_additiveExpr_in_rangeExpr1209);
                    additiveExpr249=additiveExpr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, additiveExpr249.getTree());
                    dbg.location(98,44);


                    }
                    break;

            }
            } finally {dbg.exitSubRule(52);}

            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(98, 47);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "rangeExpr");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end rangeExpr

    public static class additiveExpr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start additiveExpr
    // XQuery.g:99:1: additiveExpr : multiplicativeExpr ( ( '+' | '-' ) multiplicativeExpr )* ;
    public final XQueryParser.additiveExpr_return additiveExpr() throws RecognitionException {
        XQueryParser.additiveExpr_return retval = new XQueryParser.additiveExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set251=null;
        XQueryParser.multiplicativeExpr_return multiplicativeExpr250 = null;

        XQueryParser.multiplicativeExpr_return multiplicativeExpr252 = null;


        Object set251_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "additiveExpr");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(99, 1);

        try {
            // XQuery.g:99:14: ( multiplicativeExpr ( ( '+' | '-' ) multiplicativeExpr )* )
            dbg.enterAlt(1);

            // XQuery.g:99:16: multiplicativeExpr ( ( '+' | '-' ) multiplicativeExpr )*
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(99,16);
            pushFollow(FOLLOW_multiplicativeExpr_in_additiveExpr1220);
            multiplicativeExpr250=multiplicativeExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, multiplicativeExpr250.getTree());
            dbg.location(99,35);
            // XQuery.g:99:35: ( ( '+' | '-' ) multiplicativeExpr )*
            try { dbg.enterSubRule(53);

            loop53:
            do {
                int alt53=2;
                try { dbg.enterDecision(53);

                int LA53_0 = input.LA(1);

                if ( ((LA53_0>=147 && LA53_0<=148)) ) {
                    alt53=1;
                }


                } finally {dbg.exitDecision(53);}

                switch (alt53) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // XQuery.g:99:37: ( '+' | '-' ) multiplicativeExpr
            	    {
            	    dbg.location(99,37);
            	    set251=(Token)input.LT(1);
            	    if ( (input.LA(1)>=147 && input.LA(1)<=148) ) {
            	        input.consume();
            	        if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set251));
            	        state.errorRecovery=false;state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        dbg.recognitionException(mse);
            	        throw mse;
            	    }

            	    dbg.location(99,49);
            	    pushFollow(FOLLOW_multiplicativeExpr_in_additiveExpr1232);
            	    multiplicativeExpr252=multiplicativeExpr();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, multiplicativeExpr252.getTree());
            	    dbg.location(99,68);


            	    }
            	    break;

            	default :
            	    break loop53;
                }
            } while (true);
            } finally {dbg.exitSubRule(53);}

            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(99, 71);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "additiveExpr");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end additiveExpr

    public static class multiplicativeExpr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start multiplicativeExpr
    // XQuery.g:100:1: multiplicativeExpr : unionExpr ( ( '*' | DIV | IDIV | MOD ) unionExpr )* ;
    public final XQueryParser.multiplicativeExpr_return multiplicativeExpr() throws RecognitionException {
        XQueryParser.multiplicativeExpr_return retval = new XQueryParser.multiplicativeExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set254=null;
        XQueryParser.unionExpr_return unionExpr253 = null;

        XQueryParser.unionExpr_return unionExpr255 = null;


        Object set254_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "multiplicativeExpr");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(100, 1);

        try {
            // XQuery.g:101:3: ( unionExpr ( ( '*' | DIV | IDIV | MOD ) unionExpr )* )
            dbg.enterAlt(1);

            // XQuery.g:101:5: unionExpr ( ( '*' | DIV | IDIV | MOD ) unionExpr )*
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(101,5);
            pushFollow(FOLLOW_unionExpr_in_multiplicativeExpr1246);
            unionExpr253=unionExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, unionExpr253.getTree());
            dbg.location(101,15);
            // XQuery.g:101:15: ( ( '*' | DIV | IDIV | MOD ) unionExpr )*
            try { dbg.enterSubRule(54);

            loop54:
            do {
                int alt54=2;
                try { dbg.enterDecision(54);

                int LA54_0 = input.LA(1);

                if ( ((LA54_0>=DIV && LA54_0<=MOD)||LA54_0==149) ) {
                    alt54=1;
                }


                } finally {dbg.exitDecision(54);}

                switch (alt54) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // XQuery.g:101:17: ( '*' | DIV | IDIV | MOD ) unionExpr
            	    {
            	    dbg.location(101,17);
            	    set254=(Token)input.LT(1);
            	    if ( (input.LA(1)>=DIV && input.LA(1)<=MOD)||input.LA(1)==149 ) {
            	        input.consume();
            	        if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set254));
            	        state.errorRecovery=false;state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        dbg.recognitionException(mse);
            	        throw mse;
            	    }

            	    dbg.location(101,42);
            	    pushFollow(FOLLOW_unionExpr_in_multiplicativeExpr1266);
            	    unionExpr255=unionExpr();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, unionExpr255.getTree());
            	    dbg.location(101,52);


            	    }
            	    break;

            	default :
            	    break loop54;
                }
            } while (true);
            } finally {dbg.exitSubRule(54);}

            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(101, 55);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "multiplicativeExpr");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end multiplicativeExpr

    public static class unionExpr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start unionExpr
    // XQuery.g:102:1: unionExpr : intersectExceptExpr ( ( UNION | '|' ) intersectExceptExpr )* ;
    public final XQueryParser.unionExpr_return unionExpr() throws RecognitionException {
        XQueryParser.unionExpr_return retval = new XQueryParser.unionExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set257=null;
        XQueryParser.intersectExceptExpr_return intersectExceptExpr256 = null;

        XQueryParser.intersectExceptExpr_return intersectExceptExpr258 = null;


        Object set257_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "unionExpr");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(102, 1);

        try {
            // XQuery.g:102:11: ( intersectExceptExpr ( ( UNION | '|' ) intersectExceptExpr )* )
            dbg.enterAlt(1);

            // XQuery.g:102:13: intersectExceptExpr ( ( UNION | '|' ) intersectExceptExpr )*
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(102,13);
            pushFollow(FOLLOW_intersectExceptExpr_in_unionExpr1277);
            intersectExceptExpr256=intersectExceptExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, intersectExceptExpr256.getTree());
            dbg.location(102,33);
            // XQuery.g:102:33: ( ( UNION | '|' ) intersectExceptExpr )*
            try { dbg.enterSubRule(55);

            loop55:
            do {
                int alt55=2;
                try { dbg.enterDecision(55);

                int LA55_0 = input.LA(1);

                if ( (LA55_0==UNION||LA55_0==150) ) {
                    alt55=1;
                }


                } finally {dbg.exitDecision(55);}

                switch (alt55) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // XQuery.g:102:35: ( UNION | '|' ) intersectExceptExpr
            	    {
            	    dbg.location(102,35);
            	    set257=(Token)input.LT(1);
            	    if ( input.LA(1)==UNION||input.LA(1)==150 ) {
            	        input.consume();
            	        if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set257));
            	        state.errorRecovery=false;state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        dbg.recognitionException(mse);
            	        throw mse;
            	    }

            	    dbg.location(102,49);
            	    pushFollow(FOLLOW_intersectExceptExpr_in_unionExpr1289);
            	    intersectExceptExpr258=intersectExceptExpr();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, intersectExceptExpr258.getTree());
            	    dbg.location(102,69);


            	    }
            	    break;

            	default :
            	    break loop55;
                }
            } while (true);
            } finally {dbg.exitSubRule(55);}

            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(102, 72);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "unionExpr");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end unionExpr

    public static class intersectExceptExpr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start intersectExceptExpr
    // XQuery.g:103:1: intersectExceptExpr : instanceofExpr ( ( INTERSECT | EXCEPT ) instanceofExpr )* ;
    public final XQueryParser.intersectExceptExpr_return intersectExceptExpr() throws RecognitionException {
        XQueryParser.intersectExceptExpr_return retval = new XQueryParser.intersectExceptExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set260=null;
        XQueryParser.instanceofExpr_return instanceofExpr259 = null;

        XQueryParser.instanceofExpr_return instanceofExpr261 = null;


        Object set260_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "intersectExceptExpr");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(103, 1);

        try {
            // XQuery.g:104:3: ( instanceofExpr ( ( INTERSECT | EXCEPT ) instanceofExpr )* )
            dbg.enterAlt(1);

            // XQuery.g:104:5: instanceofExpr ( ( INTERSECT | EXCEPT ) instanceofExpr )*
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(104,5);
            pushFollow(FOLLOW_instanceofExpr_in_intersectExceptExpr1303);
            instanceofExpr259=instanceofExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, instanceofExpr259.getTree());
            dbg.location(104,20);
            // XQuery.g:104:20: ( ( INTERSECT | EXCEPT ) instanceofExpr )*
            try { dbg.enterSubRule(56);

            loop56:
            do {
                int alt56=2;
                try { dbg.enterDecision(56);

                int LA56_0 = input.LA(1);

                if ( ((LA56_0>=INTERSECT && LA56_0<=EXCEPT)) ) {
                    alt56=1;
                }


                } finally {dbg.exitDecision(56);}

                switch (alt56) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // XQuery.g:104:22: ( INTERSECT | EXCEPT ) instanceofExpr
            	    {
            	    dbg.location(104,22);
            	    set260=(Token)input.LT(1);
            	    if ( (input.LA(1)>=INTERSECT && input.LA(1)<=EXCEPT) ) {
            	        input.consume();
            	        if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set260));
            	        state.errorRecovery=false;state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        dbg.recognitionException(mse);
            	        throw mse;
            	    }

            	    dbg.location(104,43);
            	    pushFollow(FOLLOW_instanceofExpr_in_intersectExceptExpr1315);
            	    instanceofExpr261=instanceofExpr();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, instanceofExpr261.getTree());
            	    dbg.location(104,58);


            	    }
            	    break;

            	default :
            	    break loop56;
                }
            } while (true);
            } finally {dbg.exitSubRule(56);}

            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(104, 61);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "intersectExceptExpr");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end intersectExceptExpr

    public static class instanceofExpr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start instanceofExpr
    // XQuery.g:105:1: instanceofExpr : treatExpr ( INSTANCE OF sequenceType )? ;
    public final XQueryParser.instanceofExpr_return instanceofExpr() throws RecognitionException {
        XQueryParser.instanceofExpr_return retval = new XQueryParser.instanceofExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token INSTANCE263=null;
        Token OF264=null;
        XQueryParser.treatExpr_return treatExpr262 = null;

        XQueryParser.sequenceType_return sequenceType265 = null;


        Object INSTANCE263_tree=null;
        Object OF264_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "instanceofExpr");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(105, 1);

        try {
            // XQuery.g:105:16: ( treatExpr ( INSTANCE OF sequenceType )? )
            dbg.enterAlt(1);

            // XQuery.g:105:18: treatExpr ( INSTANCE OF sequenceType )?
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(105,18);
            pushFollow(FOLLOW_treatExpr_in_instanceofExpr1326);
            treatExpr262=treatExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, treatExpr262.getTree());
            dbg.location(105,28);
            // XQuery.g:105:28: ( INSTANCE OF sequenceType )?
            int alt57=2;
            try { dbg.enterSubRule(57);
            try { dbg.enterDecision(57);

            int LA57_0 = input.LA(1);

            if ( (LA57_0==INSTANCE) ) {
                alt57=1;
            }
            } finally {dbg.exitDecision(57);}

            switch (alt57) {
                case 1 :
                    dbg.enterAlt(1);

                    // XQuery.g:105:30: INSTANCE OF sequenceType
                    {
                    dbg.location(105,30);
                    INSTANCE263=(Token)input.LT(1);
                    match(input,INSTANCE,FOLLOW_INSTANCE_in_instanceofExpr1330); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    INSTANCE263_tree = (Object)adaptor.create(INSTANCE263);
                    adaptor.addChild(root_0, INSTANCE263_tree);
                    }
                    dbg.location(105,39);
                    OF264=(Token)input.LT(1);
                    match(input,OF,FOLLOW_OF_in_instanceofExpr1332); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    OF264_tree = (Object)adaptor.create(OF264);
                    adaptor.addChild(root_0, OF264_tree);
                    }
                    dbg.location(105,42);
                    pushFollow(FOLLOW_sequenceType_in_instanceofExpr1334);
                    sequenceType265=sequenceType();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, sequenceType265.getTree());
                    dbg.location(105,55);


                    }
                    break;

            }
            } finally {dbg.exitSubRule(57);}

            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(105, 58);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "instanceofExpr");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end instanceofExpr

    public static class treatExpr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start treatExpr
    // XQuery.g:106:1: treatExpr : castableExpr ( TREAT AS sequenceType )? ;
    public final XQueryParser.treatExpr_return treatExpr() throws RecognitionException {
        XQueryParser.treatExpr_return retval = new XQueryParser.treatExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token TREAT267=null;
        Token AS268=null;
        XQueryParser.castableExpr_return castableExpr266 = null;

        XQueryParser.sequenceType_return sequenceType269 = null;


        Object TREAT267_tree=null;
        Object AS268_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "treatExpr");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(106, 1);

        try {
            // XQuery.g:106:11: ( castableExpr ( TREAT AS sequenceType )? )
            dbg.enterAlt(1);

            // XQuery.g:106:13: castableExpr ( TREAT AS sequenceType )?
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(106,13);
            pushFollow(FOLLOW_castableExpr_in_treatExpr1345);
            castableExpr266=castableExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, castableExpr266.getTree());
            dbg.location(106,26);
            // XQuery.g:106:26: ( TREAT AS sequenceType )?
            int alt58=2;
            try { dbg.enterSubRule(58);
            try { dbg.enterDecision(58);

            int LA58_0 = input.LA(1);

            if ( (LA58_0==TREAT) ) {
                alt58=1;
            }
            } finally {dbg.exitDecision(58);}

            switch (alt58) {
                case 1 :
                    dbg.enterAlt(1);

                    // XQuery.g:106:28: TREAT AS sequenceType
                    {
                    dbg.location(106,28);
                    TREAT267=(Token)input.LT(1);
                    match(input,TREAT,FOLLOW_TREAT_in_treatExpr1349); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    TREAT267_tree = (Object)adaptor.create(TREAT267);
                    adaptor.addChild(root_0, TREAT267_tree);
                    }
                    dbg.location(106,34);
                    AS268=(Token)input.LT(1);
                    match(input,AS,FOLLOW_AS_in_treatExpr1351); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    AS268_tree = (Object)adaptor.create(AS268);
                    adaptor.addChild(root_0, AS268_tree);
                    }
                    dbg.location(106,37);
                    pushFollow(FOLLOW_sequenceType_in_treatExpr1353);
                    sequenceType269=sequenceType();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, sequenceType269.getTree());
                    dbg.location(106,50);


                    }
                    break;

            }
            } finally {dbg.exitSubRule(58);}

            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(106, 53);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "treatExpr");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end treatExpr

    public static class castableExpr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start castableExpr
    // XQuery.g:107:1: castableExpr : castExpr ( CASTABLE AS singleType )? ;
    public final XQueryParser.castableExpr_return castableExpr() throws RecognitionException {
        XQueryParser.castableExpr_return retval = new XQueryParser.castableExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token CASTABLE271=null;
        Token AS272=null;
        XQueryParser.castExpr_return castExpr270 = null;

        XQueryParser.singleType_return singleType273 = null;


        Object CASTABLE271_tree=null;
        Object AS272_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "castableExpr");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(107, 1);

        try {
            // XQuery.g:107:14: ( castExpr ( CASTABLE AS singleType )? )
            dbg.enterAlt(1);

            // XQuery.g:107:16: castExpr ( CASTABLE AS singleType )?
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(107,16);
            pushFollow(FOLLOW_castExpr_in_castableExpr1364);
            castExpr270=castExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, castExpr270.getTree());
            dbg.location(107,25);
            // XQuery.g:107:25: ( CASTABLE AS singleType )?
            int alt59=2;
            try { dbg.enterSubRule(59);
            try { dbg.enterDecision(59);

            int LA59_0 = input.LA(1);

            if ( (LA59_0==CASTABLE) ) {
                alt59=1;
            }
            } finally {dbg.exitDecision(59);}

            switch (alt59) {
                case 1 :
                    dbg.enterAlt(1);

                    // XQuery.g:107:27: CASTABLE AS singleType
                    {
                    dbg.location(107,27);
                    CASTABLE271=(Token)input.LT(1);
                    match(input,CASTABLE,FOLLOW_CASTABLE_in_castableExpr1368); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    CASTABLE271_tree = (Object)adaptor.create(CASTABLE271);
                    adaptor.addChild(root_0, CASTABLE271_tree);
                    }
                    dbg.location(107,36);
                    AS272=(Token)input.LT(1);
                    match(input,AS,FOLLOW_AS_in_castableExpr1370); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    AS272_tree = (Object)adaptor.create(AS272);
                    adaptor.addChild(root_0, AS272_tree);
                    }
                    dbg.location(107,39);
                    pushFollow(FOLLOW_singleType_in_castableExpr1372);
                    singleType273=singleType();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, singleType273.getTree());
                    dbg.location(107,50);


                    }
                    break;

            }
            } finally {dbg.exitSubRule(59);}

            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(107, 53);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "castableExpr");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end castableExpr

    public static class castExpr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start castExpr
    // XQuery.g:108:1: castExpr : unaryExpr ( CAST AS singleType )? ;
    public final XQueryParser.castExpr_return castExpr() throws RecognitionException {
        XQueryParser.castExpr_return retval = new XQueryParser.castExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token CAST275=null;
        Token AS276=null;
        XQueryParser.unaryExpr_return unaryExpr274 = null;

        XQueryParser.singleType_return singleType277 = null;


        Object CAST275_tree=null;
        Object AS276_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "castExpr");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(108, 1);

        try {
            // XQuery.g:108:10: ( unaryExpr ( CAST AS singleType )? )
            dbg.enterAlt(1);

            // XQuery.g:108:12: unaryExpr ( CAST AS singleType )?
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(108,12);
            pushFollow(FOLLOW_unaryExpr_in_castExpr1383);
            unaryExpr274=unaryExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, unaryExpr274.getTree());
            dbg.location(108,22);
            // XQuery.g:108:22: ( CAST AS singleType )?
            int alt60=2;
            try { dbg.enterSubRule(60);
            try { dbg.enterDecision(60);

            int LA60_0 = input.LA(1);

            if ( (LA60_0==CAST) ) {
                alt60=1;
            }
            } finally {dbg.exitDecision(60);}

            switch (alt60) {
                case 1 :
                    dbg.enterAlt(1);

                    // XQuery.g:108:24: CAST AS singleType
                    {
                    dbg.location(108,24);
                    CAST275=(Token)input.LT(1);
                    match(input,CAST,FOLLOW_CAST_in_castExpr1387); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    CAST275_tree = (Object)adaptor.create(CAST275);
                    adaptor.addChild(root_0, CAST275_tree);
                    }
                    dbg.location(108,29);
                    AS276=(Token)input.LT(1);
                    match(input,AS,FOLLOW_AS_in_castExpr1389); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    AS276_tree = (Object)adaptor.create(AS276);
                    adaptor.addChild(root_0, AS276_tree);
                    }
                    dbg.location(108,32);
                    pushFollow(FOLLOW_singleType_in_castExpr1391);
                    singleType277=singleType();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, singleType277.getTree());
                    dbg.location(108,43);


                    }
                    break;

            }
            } finally {dbg.exitSubRule(60);}

            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(108, 46);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "castExpr");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end castExpr

    public static class unaryExpr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start unaryExpr
    // XQuery.g:109:1: unaryExpr : ( '-' | '+' )* valueExpr ;
    public final XQueryParser.unaryExpr_return unaryExpr() throws RecognitionException {
        XQueryParser.unaryExpr_return retval = new XQueryParser.unaryExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set278=null;
        XQueryParser.valueExpr_return valueExpr279 = null;


        Object set278_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "unaryExpr");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(109, 1);

        try {
            // XQuery.g:109:11: ( ( '-' | '+' )* valueExpr )
            dbg.enterAlt(1);

            // XQuery.g:109:13: ( '-' | '+' )* valueExpr
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(109,13);
            // XQuery.g:109:13: ( '-' | '+' )*
            try { dbg.enterSubRule(61);

            loop61:
            do {
                int alt61=2;
                try { dbg.enterDecision(61);

                int LA61_0 = input.LA(1);

                if ( ((LA61_0>=147 && LA61_0<=148)) ) {
                    alt61=1;
                }


                } finally {dbg.exitDecision(61);}

                switch (alt61) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // XQuery.g:
            	    {
            	    dbg.location(109,13);
            	    set278=(Token)input.LT(1);
            	    if ( (input.LA(1)>=147 && input.LA(1)<=148) ) {
            	        input.consume();
            	        if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set278));
            	        state.errorRecovery=false;state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        dbg.recognitionException(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop61;
                }
            } while (true);
            } finally {dbg.exitSubRule(61);}

            dbg.location(109,26);
            pushFollow(FOLLOW_valueExpr_in_unaryExpr1411);
            valueExpr279=valueExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, valueExpr279.getTree());
            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(109, 36);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "unaryExpr");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end unaryExpr

    public static class valueExpr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start valueExpr
    // XQuery.g:110:1: valueExpr : ( validateExpr | pathExpr | extensionExpr );
    public final XQueryParser.valueExpr_return valueExpr() throws RecognitionException {
        XQueryParser.valueExpr_return retval = new XQueryParser.valueExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        XQueryParser.validateExpr_return validateExpr280 = null;

        XQueryParser.pathExpr_return pathExpr281 = null;

        XQueryParser.extensionExpr_return extensionExpr282 = null;



        try { dbg.enterRule(getGrammarFileName(), "valueExpr");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(110, 1);

        try {
            // XQuery.g:110:11: ( validateExpr | pathExpr | extensionExpr )
            int alt62=3;
            try { dbg.enterDecision(62);

            switch ( input.LA(1) ) {
            case VALIDATE:
                {
                int LA62_1 = input.LA(2);

                if ( (LA62_1==LCURLY||(LA62_1>=LAX && LA62_1<=STRICT)) ) {
                    alt62=1;
                }
                else if ( (LA62_1==EOF||(LA62_1>=CLOSE_ANGLE && LA62_1<=OPEN_ANGLE)||(LA62_1>=Lit_EQ && LA62_1<=SEMICOLON)||LA62_1==DEFAULT||(LA62_1>=ORDER && LA62_1<=EMPTY)||LA62_1==COMMA||LA62_1==COLLATION||LA62_1==RPAREN||LA62_1==LPAREN||(LA62_1>=RCURLY && LA62_1<=FOR)||(LA62_1>=LET && LA62_1<=WHERE)||(LA62_1>=STABLE && LA62_1<=DESCENDING)||LA62_1==SATISFIES||LA62_1==CASE||(LA62_1>=ELSE && LA62_1<=INSTANCE)||(LA62_1>=TREAT && LA62_1<=IS)||(LA62_1>=SLASH && LA62_1<=SLASH_SLASH)||(LA62_1>=COLON && LA62_1<=RBRACKET)||(LA62_1>=147 && LA62_1<=155)) ) {
                    alt62=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 62, 1, input);

                    dbg.recognitionException(nvae);
                    throw nvae;
                }
                }
                break;
            case OPEN_ANGLE:
            case XQUERY:
            case VERSION:
            case StringLiteral:
            case ENCODING:
            case MODULE:
            case NAMESPACE:
            case DECLARE:
            case BOUNDARY_SPACE:
            case PRESERVE:
            case STRIP:
            case DEFAULT:
            case ELEMENT:
            case FUNCTION:
            case OPTION:
            case ORDERING:
            case ORDERED:
            case UNORDERED:
            case ORDER:
            case EMPTY:
            case GREATEST:
            case LEAST:
            case COPY_NAMESPACES:
            case NO_PRESERVE:
            case INHERIT:
            case NO_INHERIT:
            case COLLATION:
            case BASE_URI:
            case IMPORT:
            case SCHEMA:
            case AT:
            case VARIABLE:
            case EXTERNAL:
            case CONSTRUCTION:
            case AS:
            case LPAREN:
            case RETURN:
            case FOR:
            case IN:
            case LET:
            case WHERE:
            case BY:
            case STABLE:
            case ASCENDING:
            case DESCENDING:
            case SOME:
            case EVERY:
            case SATISFIES:
            case TYPESWITCH:
            case CASE:
            case IF:
            case THEN:
            case ELSE:
            case OR:
            case AND:
            case TO:
            case DIV:
            case IDIV:
            case MOD:
            case UNION:
            case INTERSECT:
            case EXCEPT:
            case INSTANCE:
            case OF:
            case TREAT:
            case CASTABLE:
            case CAST:
            case EQ:
            case NE:
            case LT:
            case LE:
            case GT:
            case GE:
            case IS:
            case LAX:
            case STRICT:
            case SLASH:
            case SLASH_SLASH:
            case CHILD:
            case DESCENDANT:
            case ATTRIBUTE:
            case SELF:
            case DESCENDANT_OR_SELF:
            case FOLLOWING_SIBLING:
            case FOLLOWING:
            case PARENT:
            case ANCESTOR:
            case PRECEDING_SIBLING:
            case PRECEDING:
            case ANCESTOR_OR_SELF:
            case IntegerLiteral:
            case DecimalLiteral:
            case DoubleLiteral:
            case DirCommentConstructor:
            case DirPIConstructor:
            case DOCUMENT:
            case TEXT:
            case COMMENT:
            case PROCESSING_INSTRUCTION:
            case EMPTY_SEQUENCE:
            case ITEM:
            case NODE:
            case DOCUMENT_NODE:
            case SCHEMA_ATTRIBUTE:
            case SCHEMA_ELEMENT:
            case NCName:
            case 146:
            case 149:
            case 157:
            case 158:
            case 159:
                {
                alt62=2;
                }
                break;
            case Pragma:
                {
                alt62=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 62, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;
            }

            } finally {dbg.exitDecision(62);}

            switch (alt62) {
                case 1 :
                    dbg.enterAlt(1);

                    // XQuery.g:110:13: validateExpr
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(110,13);
                    pushFollow(FOLLOW_validateExpr_in_valueExpr1419);
                    validateExpr280=validateExpr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, validateExpr280.getTree());
                    dbg.location(110,28);


                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // XQuery.g:110:28: pathExpr
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(110,28);
                    pushFollow(FOLLOW_pathExpr_in_valueExpr1423);
                    pathExpr281=pathExpr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, pathExpr281.getTree());
                    dbg.location(110,39);


                    }
                    break;
                case 3 :
                    dbg.enterAlt(3);

                    // XQuery.g:110:39: extensionExpr
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(110,39);
                    pushFollow(FOLLOW_extensionExpr_in_valueExpr1427);
                    extensionExpr282=extensionExpr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, extensionExpr282.getTree());
                    dbg.location(0,0);


                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(110, 53);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "valueExpr");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end valueExpr

    public static class generalComp_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start generalComp
    // XQuery.g:111:1: generalComp : ( Lit_EQ | '!=' | OPEN_ANGLE | '<=' | CLOSE_ANGLE | '>=' );
    public final XQueryParser.generalComp_return generalComp() throws RecognitionException {
        XQueryParser.generalComp_return retval = new XQueryParser.generalComp_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set283=null;

        Object set283_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "generalComp");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(111, 1);

        try {
            // XQuery.g:111:13: ( Lit_EQ | '!=' | OPEN_ANGLE | '<=' | CLOSE_ANGLE | '>=' )
            dbg.enterAlt(1);

            // XQuery.g:
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(111,13);
            set283=(Token)input.LT(1);
            if ( (input.LA(1)>=CLOSE_ANGLE && input.LA(1)<=OPEN_ANGLE)||input.LA(1)==Lit_EQ||(input.LA(1)>=151 && input.LA(1)<=153) ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set283));
                state.errorRecovery=false;state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                dbg.recognitionException(mse);
                throw mse;
            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(111, 70);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "generalComp");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end generalComp

    public static class valueComp_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start valueComp
    // XQuery.g:112:1: valueComp : ( EQ | NE | LT | LE | GT | GE );
    public final XQueryParser.valueComp_return valueComp() throws RecognitionException {
        XQueryParser.valueComp_return retval = new XQueryParser.valueComp_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set284=null;

        Object set284_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "valueComp");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(112, 1);

        try {
            // XQuery.g:112:11: ( EQ | NE | LT | LE | GT | GE )
            dbg.enterAlt(1);

            // XQuery.g:
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(112,11);
            set284=(Token)input.LT(1);
            if ( (input.LA(1)>=EQ && input.LA(1)<=GE) ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set284));
                state.errorRecovery=false;state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                dbg.recognitionException(mse);
                throw mse;
            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(112, 41);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "valueComp");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end valueComp

    public static class nodeComp_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start nodeComp
    // XQuery.g:113:1: nodeComp : ( IS | '<<' | '>>' );
    public final XQueryParser.nodeComp_return nodeComp() throws RecognitionException {
        XQueryParser.nodeComp_return retval = new XQueryParser.nodeComp_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set285=null;

        Object set285_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "nodeComp");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(113, 1);

        try {
            // XQuery.g:113:10: ( IS | '<<' | '>>' )
            dbg.enterAlt(1);

            // XQuery.g:
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(113,10);
            set285=(Token)input.LT(1);
            if ( input.LA(1)==IS||(input.LA(1)>=154 && input.LA(1)<=155) ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set285));
                state.errorRecovery=false;state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                dbg.recognitionException(mse);
                throw mse;
            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(113, 29);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "nodeComp");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end nodeComp

    public static class validateExpr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start validateExpr
    // XQuery.g:114:1: validateExpr : VALIDATE ( validationMode )? LCURLY expr RCURLY ;
    public final XQueryParser.validateExpr_return validateExpr() throws RecognitionException {
        XQueryParser.validateExpr_return retval = new XQueryParser.validateExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token VALIDATE286=null;
        Token LCURLY288=null;
        Token RCURLY290=null;
        XQueryParser.validationMode_return validationMode287 = null;

        XQueryParser.expr_return expr289 = null;


        Object VALIDATE286_tree=null;
        Object LCURLY288_tree=null;
        Object RCURLY290_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "validateExpr");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(114, 1);

        try {
            // XQuery.g:114:14: ( VALIDATE ( validationMode )? LCURLY expr RCURLY )
            dbg.enterAlt(1);

            // XQuery.g:114:16: VALIDATE ( validationMode )? LCURLY expr RCURLY
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(114,16);
            VALIDATE286=(Token)input.LT(1);
            match(input,VALIDATE,FOLLOW_VALIDATE_in_validateExpr1507); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            VALIDATE286_tree = (Object)adaptor.create(VALIDATE286);
            adaptor.addChild(root_0, VALIDATE286_tree);
            }
            dbg.location(114,25);
            // XQuery.g:114:25: ( validationMode )?
            int alt63=2;
            try { dbg.enterSubRule(63);
            try { dbg.enterDecision(63);

            int LA63_0 = input.LA(1);

            if ( ((LA63_0>=LAX && LA63_0<=STRICT)) ) {
                alt63=1;
            }
            } finally {dbg.exitDecision(63);}

            switch (alt63) {
                case 1 :
                    dbg.enterAlt(1);

                    // XQuery.g:114:25: validationMode
                    {
                    dbg.location(114,25);
                    pushFollow(FOLLOW_validationMode_in_validateExpr1509);
                    validationMode287=validationMode();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, validationMode287.getTree());
                    dbg.location(114,25);


                    }
                    break;

            }
            } finally {dbg.exitSubRule(63);}

            dbg.location(114,41);
            LCURLY288=(Token)input.LT(1);
            match(input,LCURLY,FOLLOW_LCURLY_in_validateExpr1512); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            LCURLY288_tree = (Object)adaptor.create(LCURLY288);
            adaptor.addChild(root_0, LCURLY288_tree);
            }
            dbg.location(114,48);
            pushFollow(FOLLOW_expr_in_validateExpr1514);
            expr289=expr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, expr289.getTree());
            dbg.location(114,53);
            RCURLY290=(Token)input.LT(1);
            match(input,RCURLY,FOLLOW_RCURLY_in_validateExpr1516); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            RCURLY290_tree = (Object)adaptor.create(RCURLY290);
            adaptor.addChild(root_0, RCURLY290_tree);
            }
            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(114, 60);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "validateExpr");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end validateExpr

    public static class validationMode_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start validationMode
    // XQuery.g:115:1: validationMode : ( LAX | STRICT );
    public final XQueryParser.validationMode_return validationMode() throws RecognitionException {
        XQueryParser.validationMode_return retval = new XQueryParser.validationMode_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set291=null;

        Object set291_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "validationMode");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(115, 1);

        try {
            // XQuery.g:115:16: ( LAX | STRICT )
            dbg.enterAlt(1);

            // XQuery.g:
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(115,16);
            set291=(Token)input.LT(1);
            if ( (input.LA(1)>=LAX && input.LA(1)<=STRICT) ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set291));
                state.errorRecovery=false;state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                dbg.recognitionException(mse);
                throw mse;
            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(115, 31);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "validationMode");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end validationMode

    public static class extensionExpr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start extensionExpr
    // XQuery.g:116:1: extensionExpr : ( pragma )+ LCURLY ( expr )? RCURLY ;
    public final XQueryParser.extensionExpr_return extensionExpr() throws RecognitionException {
        XQueryParser.extensionExpr_return retval = new XQueryParser.extensionExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token LCURLY293=null;
        Token RCURLY295=null;
        XQueryParser.pragma_return pragma292 = null;

        XQueryParser.expr_return expr294 = null;


        Object LCURLY293_tree=null;
        Object RCURLY295_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "extensionExpr");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(116, 1);

        try {
            // XQuery.g:116:15: ( ( pragma )+ LCURLY ( expr )? RCURLY )
            dbg.enterAlt(1);

            // XQuery.g:116:17: ( pragma )+ LCURLY ( expr )? RCURLY
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(116,17);
            // XQuery.g:116:17: ( pragma )+
            int cnt64=0;
            try { dbg.enterSubRule(64);

            loop64:
            do {
                int alt64=2;
                try { dbg.enterDecision(64);

                int LA64_0 = input.LA(1);

                if ( (LA64_0==Pragma) ) {
                    alt64=1;
                }


                } finally {dbg.exitDecision(64);}

                switch (alt64) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // XQuery.g:116:17: pragma
            	    {
            	    dbg.location(116,17);
            	    pushFollow(FOLLOW_pragma_in_extensionExpr1536);
            	    pragma292=pragma();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, pragma292.getTree());
            	    dbg.location(116,17);


            	    }
            	    break;

            	default :
            	    if ( cnt64 >= 1 ) break loop64;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(64, input);
                        dbg.recognitionException(eee);

                        throw eee;
                }
                cnt64++;
            } while (true);
            } finally {dbg.exitSubRule(64);}

            dbg.location(116,25);
            LCURLY293=(Token)input.LT(1);
            match(input,LCURLY,FOLLOW_LCURLY_in_extensionExpr1539); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            LCURLY293_tree = (Object)adaptor.create(LCURLY293);
            adaptor.addChild(root_0, LCURLY293_tree);
            }
            dbg.location(116,32);
            // XQuery.g:116:32: ( expr )?
            int alt65=2;
            try { dbg.enterSubRule(65);
            try { dbg.enterDecision(65);

            int LA65_0 = input.LA(1);

            if ( (LA65_0==OPEN_ANGLE||(LA65_0>=XQUERY && LA65_0<=NAMESPACE)||(LA65_0>=DECLARE && LA65_0<=COPY_NAMESPACES)||(LA65_0>=NO_PRESERVE && LA65_0<=CONSTRUCTION)||(LA65_0>=AS && LA65_0<=LPAREN)||(LA65_0>=RETURN && LA65_0<=ANCESTOR_OR_SELF)||(LA65_0>=IntegerLiteral && LA65_0<=DirPIConstructor)||(LA65_0>=DOCUMENT && LA65_0<=NCName)||(LA65_0>=146 && LA65_0<=149)||(LA65_0>=157 && LA65_0<=159)) ) {
                alt65=1;
            }
            } finally {dbg.exitDecision(65);}

            switch (alt65) {
                case 1 :
                    dbg.enterAlt(1);

                    // XQuery.g:116:32: expr
                    {
                    dbg.location(116,32);
                    pushFollow(FOLLOW_expr_in_extensionExpr1541);
                    expr294=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr294.getTree());
                    dbg.location(116,32);


                    }
                    break;

            }
            } finally {dbg.exitSubRule(65);}

            dbg.location(116,38);
            RCURLY295=(Token)input.LT(1);
            match(input,RCURLY,FOLLOW_RCURLY_in_extensionExpr1544); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            RCURLY295_tree = (Object)adaptor.create(RCURLY295);
            adaptor.addChild(root_0, RCURLY295_tree);
            }
            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(116, 45);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "extensionExpr");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end extensionExpr

    public static class pragma_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start pragma
    // XQuery.g:117:1: pragma : Pragma ;
    public final XQueryParser.pragma_return pragma() throws RecognitionException {
        XQueryParser.pragma_return retval = new XQueryParser.pragma_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token Pragma296=null;

        Object Pragma296_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "pragma");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(117, 1);

        try {
            // XQuery.g:117:9: ( Pragma )
            dbg.enterAlt(1);

            // XQuery.g:117:11: Pragma
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(117,11);
            Pragma296=(Token)input.LT(1);
            match(input,Pragma,FOLLOW_Pragma_in_pragma1553); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            Pragma296_tree = (Object)adaptor.create(Pragma296);
            adaptor.addChild(root_0, Pragma296_tree);
            }
            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(117, 17);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "pragma");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end pragma

    public static class pathExpr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start pathExpr
    // XQuery.g:118:1: pathExpr : ( ( SLASH relativePathExpr )=> ( SLASH relativePathExpr ) | SLASH | ( SLASH_SLASH relativePathExpr ) | relativePathExpr );
    public final XQueryParser.pathExpr_return pathExpr() throws RecognitionException {
        XQueryParser.pathExpr_return retval = new XQueryParser.pathExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token SLASH297=null;
        Token SLASH299=null;
        Token SLASH_SLASH300=null;
        XQueryParser.relativePathExpr_return relativePathExpr298 = null;

        XQueryParser.relativePathExpr_return relativePathExpr301 = null;

        XQueryParser.relativePathExpr_return relativePathExpr302 = null;


        Object SLASH297_tree=null;
        Object SLASH299_tree=null;
        Object SLASH_SLASH300_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "pathExpr");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(118, 1);

        try {
            // XQuery.g:118:10: ( ( SLASH relativePathExpr )=> ( SLASH relativePathExpr ) | SLASH | ( SLASH_SLASH relativePathExpr ) | relativePathExpr )
            int alt66=4;
            try { dbg.enterDecision(66);

            try {
                isCyclicDecision = true;
                alt66 = dfa66.predict(input);
            }
            catch (NoViableAltException nvae) {
                dbg.recognitionException(nvae);
                throw nvae;
            }
            } finally {dbg.exitDecision(66);}

            switch (alt66) {
                case 1 :
                    dbg.enterAlt(1);

                    // XQuery.g:118:12: ( SLASH relativePathExpr )=> ( SLASH relativePathExpr )
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(118,40);
                    // XQuery.g:118:40: ( SLASH relativePathExpr )
                    dbg.enterAlt(1);

                    // XQuery.g:118:41: SLASH relativePathExpr
                    {
                    dbg.location(118,41);
                    SLASH297=(Token)input.LT(1);
                    match(input,SLASH,FOLLOW_SLASH_in_pathExpr1569); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    SLASH297_tree = (Object)adaptor.create(SLASH297);
                    adaptor.addChild(root_0, SLASH297_tree);
                    }
                    dbg.location(118,47);
                    pushFollow(FOLLOW_relativePathExpr_in_pathExpr1571);
                    relativePathExpr298=relativePathExpr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, relativePathExpr298.getTree());
                    dbg.location(118,63);


                    }

                    dbg.location(119,6);


                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // XQuery.g:119:6: SLASH
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(119,6);
                    SLASH299=(Token)input.LT(1);
                    match(input,SLASH,FOLLOW_SLASH_in_pathExpr1579); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    SLASH299_tree = (Object)adaptor.create(SLASH299);
                    adaptor.addChild(root_0, SLASH299_tree);
                    }
                    dbg.location(119,14);


                    }
                    break;
                case 3 :
                    dbg.enterAlt(3);

                    // XQuery.g:119:14: ( SLASH_SLASH relativePathExpr )
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(119,14);
                    // XQuery.g:119:14: ( SLASH_SLASH relativePathExpr )
                    dbg.enterAlt(1);

                    // XQuery.g:119:15: SLASH_SLASH relativePathExpr
                    {
                    dbg.location(119,15);
                    SLASH_SLASH300=(Token)input.LT(1);
                    match(input,SLASH_SLASH,FOLLOW_SLASH_SLASH_in_pathExpr1584); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    SLASH_SLASH300_tree = (Object)adaptor.create(SLASH_SLASH300);
                    adaptor.addChild(root_0, SLASH_SLASH300_tree);
                    }
                    dbg.location(119,27);
                    pushFollow(FOLLOW_relativePathExpr_in_pathExpr1586);
                    relativePathExpr301=relativePathExpr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, relativePathExpr301.getTree());
                    dbg.location(119,43);


                    }

                    dbg.location(119,48);


                    }
                    break;
                case 4 :
                    dbg.enterAlt(4);

                    // XQuery.g:119:48: relativePathExpr
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(119,48);
                    pushFollow(FOLLOW_relativePathExpr_in_pathExpr1592);
                    relativePathExpr302=relativePathExpr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, relativePathExpr302.getTree());
                    dbg.location(0,0);


                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(119, 95);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "pathExpr");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end pathExpr

    public static class relativePathExpr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start relativePathExpr
    // XQuery.g:120:1: relativePathExpr : stepExpr ( ( SLASH | SLASH_SLASH ) stepExpr )* ;
    public final XQueryParser.relativePathExpr_return relativePathExpr() throws RecognitionException {
        XQueryParser.relativePathExpr_return retval = new XQueryParser.relativePathExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set304=null;
        XQueryParser.stepExpr_return stepExpr303 = null;

        XQueryParser.stepExpr_return stepExpr305 = null;


        Object set304_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "relativePathExpr");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(120, 1);

        try {
            // XQuery.g:121:3: ( stepExpr ( ( SLASH | SLASH_SLASH ) stepExpr )* )
            dbg.enterAlt(1);

            // XQuery.g:121:5: stepExpr ( ( SLASH | SLASH_SLASH ) stepExpr )*
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(121,5);
            pushFollow(FOLLOW_stepExpr_in_relativePathExpr1605);
            stepExpr303=stepExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, stepExpr303.getTree());
            dbg.location(121,14);
            // XQuery.g:121:14: ( ( SLASH | SLASH_SLASH ) stepExpr )*
            try { dbg.enterSubRule(67);

            loop67:
            do {
                int alt67=2;
                try { dbg.enterDecision(67);

                int LA67_0 = input.LA(1);

                if ( ((LA67_0>=SLASH && LA67_0<=SLASH_SLASH)) ) {
                    alt67=1;
                }


                } finally {dbg.exitDecision(67);}

                switch (alt67) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // XQuery.g:121:15: ( SLASH | SLASH_SLASH ) stepExpr
            	    {
            	    dbg.location(121,15);
            	    set304=(Token)input.LT(1);
            	    if ( (input.LA(1)>=SLASH && input.LA(1)<=SLASH_SLASH) ) {
            	        input.consume();
            	        if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set304));
            	        state.errorRecovery=false;state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        dbg.recognitionException(mse);
            	        throw mse;
            	    }

            	    dbg.location(121,37);
            	    pushFollow(FOLLOW_stepExpr_in_relativePathExpr1616);
            	    stepExpr305=stepExpr();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, stepExpr305.getTree());
            	    dbg.location(121,45);


            	    }
            	    break;

            	default :
            	    break loop67;
                }
            } while (true);
            } finally {dbg.exitSubRule(67);}

            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(121, 48);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "relativePathExpr");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end relativePathExpr

    public static class stepExpr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start stepExpr
    // XQuery.g:122:1: stepExpr : ( filterExpr | axisStep );
    public final XQueryParser.stepExpr_return stepExpr() throws RecognitionException {
        XQueryParser.stepExpr_return retval = new XQueryParser.stepExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        XQueryParser.filterExpr_return filterExpr306 = null;

        XQueryParser.axisStep_return axisStep307 = null;



        try { dbg.enterRule(getGrammarFileName(), "stepExpr");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(122, 1);

        try {
            // XQuery.g:122:10: ( filterExpr | axisStep )
            int alt68=2;
            try { dbg.enterDecision(68);

            try {
                isCyclicDecision = true;
                alt68 = dfa68.predict(input);
            }
            catch (NoViableAltException nvae) {
                dbg.recognitionException(nvae);
                throw nvae;
            }
            } finally {dbg.exitDecision(68);}

            switch (alt68) {
                case 1 :
                    dbg.enterAlt(1);

                    // XQuery.g:122:12: filterExpr
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(122,12);
                    pushFollow(FOLLOW_filterExpr_in_stepExpr1626);
                    filterExpr306=filterExpr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, filterExpr306.getTree());
                    dbg.location(122,25);


                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // XQuery.g:122:25: axisStep
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(122,25);
                    pushFollow(FOLLOW_axisStep_in_stepExpr1630);
                    axisStep307=axisStep();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, axisStep307.getTree());
                    dbg.location(0,0);


                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(122, 34);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "stepExpr");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end stepExpr

    public static class axisStep_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start axisStep
    // XQuery.g:123:1: axisStep : ( reverseStep | forwardStep ) predicateList ;
    public final XQueryParser.axisStep_return axisStep() throws RecognitionException {
        XQueryParser.axisStep_return retval = new XQueryParser.axisStep_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        XQueryParser.reverseStep_return reverseStep308 = null;

        XQueryParser.forwardStep_return forwardStep309 = null;

        XQueryParser.predicateList_return predicateList310 = null;



        try { dbg.enterRule(getGrammarFileName(), "axisStep");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(123, 1);

        try {
            // XQuery.g:123:10: ( ( reverseStep | forwardStep ) predicateList )
            dbg.enterAlt(1);

            // XQuery.g:123:12: ( reverseStep | forwardStep ) predicateList
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(123,12);
            // XQuery.g:123:12: ( reverseStep | forwardStep )
            int alt69=2;
            try { dbg.enterSubRule(69);
            try { dbg.enterDecision(69);

            switch ( input.LA(1) ) {
            case PARENT:
                {
                int LA69_1 = input.LA(2);

                if ( (LA69_1==156) ) {
                    alt69=1;
                }
                else if ( (LA69_1==EOF||(LA69_1>=CLOSE_ANGLE && LA69_1<=OPEN_ANGLE)||(LA69_1>=Lit_EQ && LA69_1<=SEMICOLON)||LA69_1==DEFAULT||(LA69_1>=ORDER && LA69_1<=EMPTY)||LA69_1==COMMA||LA69_1==COLLATION||LA69_1==RPAREN||(LA69_1>=RCURLY && LA69_1<=FOR)||(LA69_1>=LET && LA69_1<=WHERE)||(LA69_1>=STABLE && LA69_1<=DESCENDING)||LA69_1==SATISFIES||LA69_1==CASE||(LA69_1>=ELSE && LA69_1<=INSTANCE)||(LA69_1>=TREAT && LA69_1<=IS)||(LA69_1>=SLASH && LA69_1<=SLASH_SLASH)||(LA69_1>=COLON && LA69_1<=RBRACKET)||(LA69_1>=147 && LA69_1<=155)) ) {
                    alt69=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 69, 1, input);

                    dbg.recognitionException(nvae);
                    throw nvae;
                }
                }
                break;
            case ANCESTOR:
                {
                int LA69_2 = input.LA(2);

                if ( (LA69_2==156) ) {
                    alt69=1;
                }
                else if ( (LA69_2==EOF||(LA69_2>=CLOSE_ANGLE && LA69_2<=OPEN_ANGLE)||(LA69_2>=Lit_EQ && LA69_2<=SEMICOLON)||LA69_2==DEFAULT||(LA69_2>=ORDER && LA69_2<=EMPTY)||LA69_2==COMMA||LA69_2==COLLATION||LA69_2==RPAREN||(LA69_2>=RCURLY && LA69_2<=FOR)||(LA69_2>=LET && LA69_2<=WHERE)||(LA69_2>=STABLE && LA69_2<=DESCENDING)||LA69_2==SATISFIES||LA69_2==CASE||(LA69_2>=ELSE && LA69_2<=INSTANCE)||(LA69_2>=TREAT && LA69_2<=IS)||(LA69_2>=SLASH && LA69_2<=SLASH_SLASH)||(LA69_2>=COLON && LA69_2<=RBRACKET)||(LA69_2>=147 && LA69_2<=155)) ) {
                    alt69=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 69, 2, input);

                    dbg.recognitionException(nvae);
                    throw nvae;
                }
                }
                break;
            case PRECEDING_SIBLING:
                {
                int LA69_3 = input.LA(2);

                if ( (LA69_3==156) ) {
                    alt69=1;
                }
                else if ( (LA69_3==EOF||(LA69_3>=CLOSE_ANGLE && LA69_3<=OPEN_ANGLE)||(LA69_3>=Lit_EQ && LA69_3<=SEMICOLON)||LA69_3==DEFAULT||(LA69_3>=ORDER && LA69_3<=EMPTY)||LA69_3==COMMA||LA69_3==COLLATION||LA69_3==RPAREN||(LA69_3>=RCURLY && LA69_3<=FOR)||(LA69_3>=LET && LA69_3<=WHERE)||(LA69_3>=STABLE && LA69_3<=DESCENDING)||LA69_3==SATISFIES||LA69_3==CASE||(LA69_3>=ELSE && LA69_3<=INSTANCE)||(LA69_3>=TREAT && LA69_3<=IS)||(LA69_3>=SLASH && LA69_3<=SLASH_SLASH)||(LA69_3>=COLON && LA69_3<=RBRACKET)||(LA69_3>=147 && LA69_3<=155)) ) {
                    alt69=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 69, 3, input);

                    dbg.recognitionException(nvae);
                    throw nvae;
                }
                }
                break;
            case PRECEDING:
                {
                int LA69_4 = input.LA(2);

                if ( (LA69_4==156) ) {
                    alt69=1;
                }
                else if ( (LA69_4==EOF||(LA69_4>=CLOSE_ANGLE && LA69_4<=OPEN_ANGLE)||(LA69_4>=Lit_EQ && LA69_4<=SEMICOLON)||LA69_4==DEFAULT||(LA69_4>=ORDER && LA69_4<=EMPTY)||LA69_4==COMMA||LA69_4==COLLATION||LA69_4==RPAREN||(LA69_4>=RCURLY && LA69_4<=FOR)||(LA69_4>=LET && LA69_4<=WHERE)||(LA69_4>=STABLE && LA69_4<=DESCENDING)||LA69_4==SATISFIES||LA69_4==CASE||(LA69_4>=ELSE && LA69_4<=INSTANCE)||(LA69_4>=TREAT && LA69_4<=IS)||(LA69_4>=SLASH && LA69_4<=SLASH_SLASH)||(LA69_4>=COLON && LA69_4<=RBRACKET)||(LA69_4>=147 && LA69_4<=155)) ) {
                    alt69=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 69, 4, input);

                    dbg.recognitionException(nvae);
                    throw nvae;
                }
                }
                break;
            case ANCESTOR_OR_SELF:
                {
                int LA69_5 = input.LA(2);

                if ( (LA69_5==156) ) {
                    alt69=1;
                }
                else if ( (LA69_5==EOF||(LA69_5>=CLOSE_ANGLE && LA69_5<=OPEN_ANGLE)||(LA69_5>=Lit_EQ && LA69_5<=SEMICOLON)||LA69_5==DEFAULT||(LA69_5>=ORDER && LA69_5<=EMPTY)||LA69_5==COMMA||LA69_5==COLLATION||LA69_5==RPAREN||(LA69_5>=RCURLY && LA69_5<=FOR)||(LA69_5>=LET && LA69_5<=WHERE)||(LA69_5>=STABLE && LA69_5<=DESCENDING)||LA69_5==SATISFIES||LA69_5==CASE||(LA69_5>=ELSE && LA69_5<=INSTANCE)||(LA69_5>=TREAT && LA69_5<=IS)||(LA69_5>=SLASH && LA69_5<=SLASH_SLASH)||(LA69_5>=COLON && LA69_5<=RBRACKET)||(LA69_5>=147 && LA69_5<=155)) ) {
                    alt69=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 69, 5, input);

                    dbg.recognitionException(nvae);
                    throw nvae;
                }
                }
                break;
            case 158:
                {
                alt69=1;
                }
                break;
            case XQUERY:
            case VERSION:
            case ENCODING:
            case MODULE:
            case NAMESPACE:
            case DECLARE:
            case BOUNDARY_SPACE:
            case PRESERVE:
            case STRIP:
            case DEFAULT:
            case ELEMENT:
            case FUNCTION:
            case OPTION:
            case ORDERING:
            case ORDERED:
            case UNORDERED:
            case ORDER:
            case EMPTY:
            case GREATEST:
            case LEAST:
            case COPY_NAMESPACES:
            case NO_PRESERVE:
            case INHERIT:
            case NO_INHERIT:
            case COLLATION:
            case BASE_URI:
            case IMPORT:
            case SCHEMA:
            case AT:
            case VARIABLE:
            case EXTERNAL:
            case CONSTRUCTION:
            case AS:
            case RETURN:
            case FOR:
            case IN:
            case LET:
            case WHERE:
            case BY:
            case STABLE:
            case ASCENDING:
            case DESCENDING:
            case SOME:
            case EVERY:
            case SATISFIES:
            case TYPESWITCH:
            case CASE:
            case IF:
            case THEN:
            case ELSE:
            case OR:
            case AND:
            case TO:
            case DIV:
            case IDIV:
            case MOD:
            case UNION:
            case INTERSECT:
            case EXCEPT:
            case INSTANCE:
            case OF:
            case TREAT:
            case CASTABLE:
            case CAST:
            case EQ:
            case NE:
            case LT:
            case LE:
            case GT:
            case GE:
            case IS:
            case VALIDATE:
            case LAX:
            case STRICT:
            case CHILD:
            case DESCENDANT:
            case ATTRIBUTE:
            case SELF:
            case DESCENDANT_OR_SELF:
            case FOLLOWING_SIBLING:
            case FOLLOWING:
            case DOCUMENT:
            case TEXT:
            case COMMENT:
            case PROCESSING_INSTRUCTION:
            case EMPTY_SEQUENCE:
            case ITEM:
            case NODE:
            case DOCUMENT_NODE:
            case SCHEMA_ATTRIBUTE:
            case SCHEMA_ELEMENT:
            case NCName:
            case 149:
            case 157:
                {
                alt69=2;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 69, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;
            }

            } finally {dbg.exitDecision(69);}

            switch (alt69) {
                case 1 :
                    dbg.enterAlt(1);

                    // XQuery.g:123:13: reverseStep
                    {
                    dbg.location(123,13);
                    pushFollow(FOLLOW_reverseStep_in_axisStep1639);
                    reverseStep308=reverseStep();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, reverseStep308.getTree());
                    dbg.location(123,27);


                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // XQuery.g:123:27: forwardStep
                    {
                    dbg.location(123,27);
                    pushFollow(FOLLOW_forwardStep_in_axisStep1643);
                    forwardStep309=forwardStep();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, forwardStep309.getTree());
                    dbg.location(123,38);


                    }
                    break;

            }
            } finally {dbg.exitSubRule(69);}

            dbg.location(123,40);
            pushFollow(FOLLOW_predicateList_in_axisStep1646);
            predicateList310=predicateList();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, predicateList310.getTree());
            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(123, 54);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "axisStep");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end axisStep

    public static class forwardStep_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start forwardStep
    // XQuery.g:124:1: forwardStep : ( ( forwardAxis nodeTest ) | abbrevForwardStep );
    public final XQueryParser.forwardStep_return forwardStep() throws RecognitionException {
        XQueryParser.forwardStep_return retval = new XQueryParser.forwardStep_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        XQueryParser.forwardAxis_return forwardAxis311 = null;

        XQueryParser.nodeTest_return nodeTest312 = null;

        XQueryParser.abbrevForwardStep_return abbrevForwardStep313 = null;



        try { dbg.enterRule(getGrammarFileName(), "forwardStep");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(124, 1);

        try {
            // XQuery.g:124:13: ( ( forwardAxis nodeTest ) | abbrevForwardStep )
            int alt70=2;
            try { dbg.enterDecision(70);

            try {
                isCyclicDecision = true;
                alt70 = dfa70.predict(input);
            }
            catch (NoViableAltException nvae) {
                dbg.recognitionException(nvae);
                throw nvae;
            }
            } finally {dbg.exitDecision(70);}

            switch (alt70) {
                case 1 :
                    dbg.enterAlt(1);

                    // XQuery.g:124:15: ( forwardAxis nodeTest )
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(124,15);
                    // XQuery.g:124:15: ( forwardAxis nodeTest )
                    dbg.enterAlt(1);

                    // XQuery.g:124:16: forwardAxis nodeTest
                    {
                    dbg.location(124,16);
                    pushFollow(FOLLOW_forwardAxis_in_forwardStep1655);
                    forwardAxis311=forwardAxis();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, forwardAxis311.getTree());
                    dbg.location(124,28);
                    pushFollow(FOLLOW_nodeTest_in_forwardStep1657);
                    nodeTest312=nodeTest();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, nodeTest312.getTree());
                    dbg.location(124,36);


                    }

                    dbg.location(124,40);


                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // XQuery.g:124:40: abbrevForwardStep
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(124,40);
                    pushFollow(FOLLOW_abbrevForwardStep_in_forwardStep1662);
                    abbrevForwardStep313=abbrevForwardStep();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, abbrevForwardStep313.getTree());
                    dbg.location(0,0);


                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(124, 58);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "forwardStep");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end forwardStep

    public static class forwardAxis_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start forwardAxis
    // XQuery.g:125:1: forwardAxis : ( ( CHILD '::' ) | ( DESCENDANT '::' ) | ( ATTRIBUTE '::' ) | ( SELF '::' ) | ( DESCENDANT_OR_SELF '::' ) | ( FOLLOWING_SIBLING '::' ) | ( FOLLOWING '::' ) );
    public final XQueryParser.forwardAxis_return forwardAxis() throws RecognitionException {
        XQueryParser.forwardAxis_return retval = new XQueryParser.forwardAxis_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token CHILD314=null;
        Token string_literal315=null;
        Token DESCENDANT316=null;
        Token string_literal317=null;
        Token ATTRIBUTE318=null;
        Token string_literal319=null;
        Token SELF320=null;
        Token string_literal321=null;
        Token DESCENDANT_OR_SELF322=null;
        Token string_literal323=null;
        Token FOLLOWING_SIBLING324=null;
        Token string_literal325=null;
        Token FOLLOWING326=null;
        Token string_literal327=null;

        Object CHILD314_tree=null;
        Object string_literal315_tree=null;
        Object DESCENDANT316_tree=null;
        Object string_literal317_tree=null;
        Object ATTRIBUTE318_tree=null;
        Object string_literal319_tree=null;
        Object SELF320_tree=null;
        Object string_literal321_tree=null;
        Object DESCENDANT_OR_SELF322_tree=null;
        Object string_literal323_tree=null;
        Object FOLLOWING_SIBLING324_tree=null;
        Object string_literal325_tree=null;
        Object FOLLOWING326_tree=null;
        Object string_literal327_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "forwardAxis");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(125, 1);

        try {
            // XQuery.g:125:13: ( ( CHILD '::' ) | ( DESCENDANT '::' ) | ( ATTRIBUTE '::' ) | ( SELF '::' ) | ( DESCENDANT_OR_SELF '::' ) | ( FOLLOWING_SIBLING '::' ) | ( FOLLOWING '::' ) )
            int alt71=7;
            try { dbg.enterDecision(71);

            switch ( input.LA(1) ) {
            case CHILD:
                {
                alt71=1;
                }
                break;
            case DESCENDANT:
                {
                alt71=2;
                }
                break;
            case ATTRIBUTE:
                {
                alt71=3;
                }
                break;
            case SELF:
                {
                alt71=4;
                }
                break;
            case DESCENDANT_OR_SELF:
                {
                alt71=5;
                }
                break;
            case FOLLOWING_SIBLING:
                {
                alt71=6;
                }
                break;
            case FOLLOWING:
                {
                alt71=7;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 71, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;
            }

            } finally {dbg.exitDecision(71);}

            switch (alt71) {
                case 1 :
                    dbg.enterAlt(1);

                    // XQuery.g:125:15: ( CHILD '::' )
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(125,15);
                    // XQuery.g:125:15: ( CHILD '::' )
                    dbg.enterAlt(1);

                    // XQuery.g:125:16: CHILD '::'
                    {
                    dbg.location(125,16);
                    CHILD314=(Token)input.LT(1);
                    match(input,CHILD,FOLLOW_CHILD_in_forwardAxis1671); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    CHILD314_tree = (Object)adaptor.create(CHILD314);
                    adaptor.addChild(root_0, CHILD314_tree);
                    }
                    dbg.location(125,22);
                    string_literal315=(Token)input.LT(1);
                    match(input,156,FOLLOW_156_in_forwardAxis1673); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal315_tree = (Object)adaptor.create(string_literal315);
                    adaptor.addChild(root_0, string_literal315_tree);
                    }
                    dbg.location(125,26);


                    }

                    dbg.location(125,31);


                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // XQuery.g:125:31: ( DESCENDANT '::' )
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(125,31);
                    // XQuery.g:125:31: ( DESCENDANT '::' )
                    dbg.enterAlt(1);

                    // XQuery.g:125:32: DESCENDANT '::'
                    {
                    dbg.location(125,32);
                    DESCENDANT316=(Token)input.LT(1);
                    match(input,DESCENDANT,FOLLOW_DESCENDANT_in_forwardAxis1680); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    DESCENDANT316_tree = (Object)adaptor.create(DESCENDANT316);
                    adaptor.addChild(root_0, DESCENDANT316_tree);
                    }
                    dbg.location(125,43);
                    string_literal317=(Token)input.LT(1);
                    match(input,156,FOLLOW_156_in_forwardAxis1682); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal317_tree = (Object)adaptor.create(string_literal317);
                    adaptor.addChild(root_0, string_literal317_tree);
                    }
                    dbg.location(125,47);


                    }

                    dbg.location(125,52);


                    }
                    break;
                case 3 :
                    dbg.enterAlt(3);

                    // XQuery.g:125:52: ( ATTRIBUTE '::' )
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(125,52);
                    // XQuery.g:125:52: ( ATTRIBUTE '::' )
                    dbg.enterAlt(1);

                    // XQuery.g:125:53: ATTRIBUTE '::'
                    {
                    dbg.location(125,53);
                    ATTRIBUTE318=(Token)input.LT(1);
                    match(input,ATTRIBUTE,FOLLOW_ATTRIBUTE_in_forwardAxis1689); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    ATTRIBUTE318_tree = (Object)adaptor.create(ATTRIBUTE318);
                    adaptor.addChild(root_0, ATTRIBUTE318_tree);
                    }
                    dbg.location(125,63);
                    string_literal319=(Token)input.LT(1);
                    match(input,156,FOLLOW_156_in_forwardAxis1691); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal319_tree = (Object)adaptor.create(string_literal319);
                    adaptor.addChild(root_0, string_literal319_tree);
                    }
                    dbg.location(125,67);


                    }

                    dbg.location(125,72);


                    }
                    break;
                case 4 :
                    dbg.enterAlt(4);

                    // XQuery.g:125:72: ( SELF '::' )
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(125,72);
                    // XQuery.g:125:72: ( SELF '::' )
                    dbg.enterAlt(1);

                    // XQuery.g:125:73: SELF '::'
                    {
                    dbg.location(125,73);
                    SELF320=(Token)input.LT(1);
                    match(input,SELF,FOLLOW_SELF_in_forwardAxis1698); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    SELF320_tree = (Object)adaptor.create(SELF320);
                    adaptor.addChild(root_0, SELF320_tree);
                    }
                    dbg.location(125,78);
                    string_literal321=(Token)input.LT(1);
                    match(input,156,FOLLOW_156_in_forwardAxis1700); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal321_tree = (Object)adaptor.create(string_literal321);
                    adaptor.addChild(root_0, string_literal321_tree);
                    }
                    dbg.location(125,82);


                    }

                    dbg.location(125,87);


                    }
                    break;
                case 5 :
                    dbg.enterAlt(5);

                    // XQuery.g:125:87: ( DESCENDANT_OR_SELF '::' )
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(125,87);
                    // XQuery.g:125:87: ( DESCENDANT_OR_SELF '::' )
                    dbg.enterAlt(1);

                    // XQuery.g:125:88: DESCENDANT_OR_SELF '::'
                    {
                    dbg.location(125,88);
                    DESCENDANT_OR_SELF322=(Token)input.LT(1);
                    match(input,DESCENDANT_OR_SELF,FOLLOW_DESCENDANT_OR_SELF_in_forwardAxis1707); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    DESCENDANT_OR_SELF322_tree = (Object)adaptor.create(DESCENDANT_OR_SELF322);
                    adaptor.addChild(root_0, DESCENDANT_OR_SELF322_tree);
                    }
                    dbg.location(125,107);
                    string_literal323=(Token)input.LT(1);
                    match(input,156,FOLLOW_156_in_forwardAxis1709); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal323_tree = (Object)adaptor.create(string_literal323);
                    adaptor.addChild(root_0, string_literal323_tree);
                    }
                    dbg.location(125,111);


                    }

                    dbg.location(125,116);


                    }
                    break;
                case 6 :
                    dbg.enterAlt(6);

                    // XQuery.g:125:116: ( FOLLOWING_SIBLING '::' )
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(125,116);
                    // XQuery.g:125:116: ( FOLLOWING_SIBLING '::' )
                    dbg.enterAlt(1);

                    // XQuery.g:125:117: FOLLOWING_SIBLING '::'
                    {
                    dbg.location(125,117);
                    FOLLOWING_SIBLING324=(Token)input.LT(1);
                    match(input,FOLLOWING_SIBLING,FOLLOW_FOLLOWING_SIBLING_in_forwardAxis1716); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    FOLLOWING_SIBLING324_tree = (Object)adaptor.create(FOLLOWING_SIBLING324);
                    adaptor.addChild(root_0, FOLLOWING_SIBLING324_tree);
                    }
                    dbg.location(125,135);
                    string_literal325=(Token)input.LT(1);
                    match(input,156,FOLLOW_156_in_forwardAxis1718); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal325_tree = (Object)adaptor.create(string_literal325);
                    adaptor.addChild(root_0, string_literal325_tree);
                    }
                    dbg.location(125,139);


                    }

                    dbg.location(125,144);


                    }
                    break;
                case 7 :
                    dbg.enterAlt(7);

                    // XQuery.g:125:144: ( FOLLOWING '::' )
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(125,144);
                    // XQuery.g:125:144: ( FOLLOWING '::' )
                    dbg.enterAlt(1);

                    // XQuery.g:125:145: FOLLOWING '::'
                    {
                    dbg.location(125,145);
                    FOLLOWING326=(Token)input.LT(1);
                    match(input,FOLLOWING,FOLLOW_FOLLOWING_in_forwardAxis1725); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    FOLLOWING326_tree = (Object)adaptor.create(FOLLOWING326);
                    adaptor.addChild(root_0, FOLLOWING326_tree);
                    }
                    dbg.location(125,155);
                    string_literal327=(Token)input.LT(1);
                    match(input,156,FOLLOW_156_in_forwardAxis1727); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal327_tree = (Object)adaptor.create(string_literal327);
                    adaptor.addChild(root_0, string_literal327_tree);
                    }
                    dbg.location(125,159);


                    }

                    dbg.location(0,0);


                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(125, 161);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "forwardAxis");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end forwardAxis

    public static class abbrevForwardStep_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start abbrevForwardStep
    // XQuery.g:126:1: abbrevForwardStep : ( '@' )? nodeTest ;
    public final XQueryParser.abbrevForwardStep_return abbrevForwardStep() throws RecognitionException {
        XQueryParser.abbrevForwardStep_return retval = new XQueryParser.abbrevForwardStep_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal328=null;
        XQueryParser.nodeTest_return nodeTest329 = null;


        Object char_literal328_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "abbrevForwardStep");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(126, 1);

        try {
            // XQuery.g:127:3: ( ( '@' )? nodeTest )
            dbg.enterAlt(1);

            // XQuery.g:127:5: ( '@' )? nodeTest
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(127,5);
            // XQuery.g:127:5: ( '@' )?
            int alt72=2;
            try { dbg.enterSubRule(72);
            try { dbg.enterDecision(72);

            int LA72_0 = input.LA(1);

            if ( (LA72_0==157) ) {
                alt72=1;
            }
            } finally {dbg.exitDecision(72);}

            switch (alt72) {
                case 1 :
                    dbg.enterAlt(1);

                    // XQuery.g:127:5: '@'
                    {
                    dbg.location(127,5);
                    char_literal328=(Token)input.LT(1);
                    match(input,157,FOLLOW_157_in_abbrevForwardStep1739); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal328_tree = (Object)adaptor.create(char_literal328);
                    adaptor.addChild(root_0, char_literal328_tree);
                    }
                    dbg.location(127,5);


                    }
                    break;

            }
            } finally {dbg.exitSubRule(72);}

            dbg.location(127,10);
            pushFollow(FOLLOW_nodeTest_in_abbrevForwardStep1742);
            nodeTest329=nodeTest();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, nodeTest329.getTree());
            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(127, 19);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "abbrevForwardStep");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end abbrevForwardStep

    public static class reverseStep_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start reverseStep
    // XQuery.g:128:1: reverseStep : ( ( reverseAxis nodeTest ) | abbrevReverseStep );
    public final XQueryParser.reverseStep_return reverseStep() throws RecognitionException {
        XQueryParser.reverseStep_return retval = new XQueryParser.reverseStep_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        XQueryParser.reverseAxis_return reverseAxis330 = null;

        XQueryParser.nodeTest_return nodeTest331 = null;

        XQueryParser.abbrevReverseStep_return abbrevReverseStep332 = null;



        try { dbg.enterRule(getGrammarFileName(), "reverseStep");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(128, 1);

        try {
            // XQuery.g:128:13: ( ( reverseAxis nodeTest ) | abbrevReverseStep )
            int alt73=2;
            try { dbg.enterDecision(73);

            int LA73_0 = input.LA(1);

            if ( ((LA73_0>=PARENT && LA73_0<=ANCESTOR_OR_SELF)) ) {
                alt73=1;
            }
            else if ( (LA73_0==158) ) {
                alt73=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 73, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;
            }
            } finally {dbg.exitDecision(73);}

            switch (alt73) {
                case 1 :
                    dbg.enterAlt(1);

                    // XQuery.g:128:15: ( reverseAxis nodeTest )
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(128,15);
                    // XQuery.g:128:15: ( reverseAxis nodeTest )
                    dbg.enterAlt(1);

                    // XQuery.g:128:16: reverseAxis nodeTest
                    {
                    dbg.location(128,16);
                    pushFollow(FOLLOW_reverseAxis_in_reverseStep1751);
                    reverseAxis330=reverseAxis();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, reverseAxis330.getTree());
                    dbg.location(128,28);
                    pushFollow(FOLLOW_nodeTest_in_reverseStep1753);
                    nodeTest331=nodeTest();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, nodeTest331.getTree());
                    dbg.location(128,36);


                    }

                    dbg.location(128,40);


                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // XQuery.g:128:40: abbrevReverseStep
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(128,40);
                    pushFollow(FOLLOW_abbrevReverseStep_in_reverseStep1758);
                    abbrevReverseStep332=abbrevReverseStep();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, abbrevReverseStep332.getTree());
                    dbg.location(0,0);


                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(128, 58);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "reverseStep");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end reverseStep

    public static class reverseAxis_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start reverseAxis
    // XQuery.g:129:1: reverseAxis : ( ( PARENT '::' ) | ( ANCESTOR '::' ) | ( PRECEDING_SIBLING '::' ) | ( PRECEDING '::' ) | ( ANCESTOR_OR_SELF '::' ) );
    public final XQueryParser.reverseAxis_return reverseAxis() throws RecognitionException {
        XQueryParser.reverseAxis_return retval = new XQueryParser.reverseAxis_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token PARENT333=null;
        Token string_literal334=null;
        Token ANCESTOR335=null;
        Token string_literal336=null;
        Token PRECEDING_SIBLING337=null;
        Token string_literal338=null;
        Token PRECEDING339=null;
        Token string_literal340=null;
        Token ANCESTOR_OR_SELF341=null;
        Token string_literal342=null;

        Object PARENT333_tree=null;
        Object string_literal334_tree=null;
        Object ANCESTOR335_tree=null;
        Object string_literal336_tree=null;
        Object PRECEDING_SIBLING337_tree=null;
        Object string_literal338_tree=null;
        Object PRECEDING339_tree=null;
        Object string_literal340_tree=null;
        Object ANCESTOR_OR_SELF341_tree=null;
        Object string_literal342_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "reverseAxis");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(129, 1);

        try {
            // XQuery.g:129:13: ( ( PARENT '::' ) | ( ANCESTOR '::' ) | ( PRECEDING_SIBLING '::' ) | ( PRECEDING '::' ) | ( ANCESTOR_OR_SELF '::' ) )
            int alt74=5;
            try { dbg.enterDecision(74);

            switch ( input.LA(1) ) {
            case PARENT:
                {
                alt74=1;
                }
                break;
            case ANCESTOR:
                {
                alt74=2;
                }
                break;
            case PRECEDING_SIBLING:
                {
                alt74=3;
                }
                break;
            case PRECEDING:
                {
                alt74=4;
                }
                break;
            case ANCESTOR_OR_SELF:
                {
                alt74=5;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 74, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;
            }

            } finally {dbg.exitDecision(74);}

            switch (alt74) {
                case 1 :
                    dbg.enterAlt(1);

                    // XQuery.g:129:15: ( PARENT '::' )
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(129,15);
                    // XQuery.g:129:15: ( PARENT '::' )
                    dbg.enterAlt(1);

                    // XQuery.g:129:16: PARENT '::'
                    {
                    dbg.location(129,16);
                    PARENT333=(Token)input.LT(1);
                    match(input,PARENT,FOLLOW_PARENT_in_reverseAxis1767); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    PARENT333_tree = (Object)adaptor.create(PARENT333);
                    adaptor.addChild(root_0, PARENT333_tree);
                    }
                    dbg.location(129,23);
                    string_literal334=(Token)input.LT(1);
                    match(input,156,FOLLOW_156_in_reverseAxis1769); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal334_tree = (Object)adaptor.create(string_literal334);
                    adaptor.addChild(root_0, string_literal334_tree);
                    }
                    dbg.location(129,27);


                    }

                    dbg.location(129,32);


                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // XQuery.g:129:32: ( ANCESTOR '::' )
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(129,32);
                    // XQuery.g:129:32: ( ANCESTOR '::' )
                    dbg.enterAlt(1);

                    // XQuery.g:129:33: ANCESTOR '::'
                    {
                    dbg.location(129,33);
                    ANCESTOR335=(Token)input.LT(1);
                    match(input,ANCESTOR,FOLLOW_ANCESTOR_in_reverseAxis1776); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    ANCESTOR335_tree = (Object)adaptor.create(ANCESTOR335);
                    adaptor.addChild(root_0, ANCESTOR335_tree);
                    }
                    dbg.location(129,42);
                    string_literal336=(Token)input.LT(1);
                    match(input,156,FOLLOW_156_in_reverseAxis1778); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal336_tree = (Object)adaptor.create(string_literal336);
                    adaptor.addChild(root_0, string_literal336_tree);
                    }
                    dbg.location(129,46);


                    }

                    dbg.location(129,51);


                    }
                    break;
                case 3 :
                    dbg.enterAlt(3);

                    // XQuery.g:129:51: ( PRECEDING_SIBLING '::' )
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(129,51);
                    // XQuery.g:129:51: ( PRECEDING_SIBLING '::' )
                    dbg.enterAlt(1);

                    // XQuery.g:129:52: PRECEDING_SIBLING '::'
                    {
                    dbg.location(129,52);
                    PRECEDING_SIBLING337=(Token)input.LT(1);
                    match(input,PRECEDING_SIBLING,FOLLOW_PRECEDING_SIBLING_in_reverseAxis1785); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    PRECEDING_SIBLING337_tree = (Object)adaptor.create(PRECEDING_SIBLING337);
                    adaptor.addChild(root_0, PRECEDING_SIBLING337_tree);
                    }
                    dbg.location(129,70);
                    string_literal338=(Token)input.LT(1);
                    match(input,156,FOLLOW_156_in_reverseAxis1787); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal338_tree = (Object)adaptor.create(string_literal338);
                    adaptor.addChild(root_0, string_literal338_tree);
                    }
                    dbg.location(129,74);


                    }

                    dbg.location(129,79);


                    }
                    break;
                case 4 :
                    dbg.enterAlt(4);

                    // XQuery.g:129:79: ( PRECEDING '::' )
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(129,79);
                    // XQuery.g:129:79: ( PRECEDING '::' )
                    dbg.enterAlt(1);

                    // XQuery.g:129:80: PRECEDING '::'
                    {
                    dbg.location(129,80);
                    PRECEDING339=(Token)input.LT(1);
                    match(input,PRECEDING,FOLLOW_PRECEDING_in_reverseAxis1794); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    PRECEDING339_tree = (Object)adaptor.create(PRECEDING339);
                    adaptor.addChild(root_0, PRECEDING339_tree);
                    }
                    dbg.location(129,90);
                    string_literal340=(Token)input.LT(1);
                    match(input,156,FOLLOW_156_in_reverseAxis1796); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal340_tree = (Object)adaptor.create(string_literal340);
                    adaptor.addChild(root_0, string_literal340_tree);
                    }
                    dbg.location(129,94);


                    }

                    dbg.location(129,99);


                    }
                    break;
                case 5 :
                    dbg.enterAlt(5);

                    // XQuery.g:129:99: ( ANCESTOR_OR_SELF '::' )
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(129,99);
                    // XQuery.g:129:99: ( ANCESTOR_OR_SELF '::' )
                    dbg.enterAlt(1);

                    // XQuery.g:129:100: ANCESTOR_OR_SELF '::'
                    {
                    dbg.location(129,100);
                    ANCESTOR_OR_SELF341=(Token)input.LT(1);
                    match(input,ANCESTOR_OR_SELF,FOLLOW_ANCESTOR_OR_SELF_in_reverseAxis1803); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    ANCESTOR_OR_SELF341_tree = (Object)adaptor.create(ANCESTOR_OR_SELF341);
                    adaptor.addChild(root_0, ANCESTOR_OR_SELF341_tree);
                    }
                    dbg.location(129,117);
                    string_literal342=(Token)input.LT(1);
                    match(input,156,FOLLOW_156_in_reverseAxis1805); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal342_tree = (Object)adaptor.create(string_literal342);
                    adaptor.addChild(root_0, string_literal342_tree);
                    }
                    dbg.location(129,121);


                    }

                    dbg.location(0,0);


                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(129, 123);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "reverseAxis");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end reverseAxis

    public static class abbrevReverseStep_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start abbrevReverseStep
    // XQuery.g:130:1: abbrevReverseStep : '..' ;
    public final XQueryParser.abbrevReverseStep_return abbrevReverseStep() throws RecognitionException {
        XQueryParser.abbrevReverseStep_return retval = new XQueryParser.abbrevReverseStep_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token string_literal343=null;

        Object string_literal343_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "abbrevReverseStep");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(130, 1);

        try {
            // XQuery.g:130:19: ( '..' )
            dbg.enterAlt(1);

            // XQuery.g:130:21: '..'
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(130,21);
            string_literal343=(Token)input.LT(1);
            match(input,158,FOLLOW_158_in_abbrevReverseStep1814); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            string_literal343_tree = (Object)adaptor.create(string_literal343);
            adaptor.addChild(root_0, string_literal343_tree);
            }
            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(130, 26);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "abbrevReverseStep");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end abbrevReverseStep

    public static class nodeTest_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start nodeTest
    // XQuery.g:131:1: nodeTest : ( kindTest | nameTest );
    public final XQueryParser.nodeTest_return nodeTest() throws RecognitionException {
        XQueryParser.nodeTest_return retval = new XQueryParser.nodeTest_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        XQueryParser.kindTest_return kindTest344 = null;

        XQueryParser.nameTest_return nameTest345 = null;



        try { dbg.enterRule(getGrammarFileName(), "nodeTest");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(131, 1);

        try {
            // XQuery.g:131:10: ( kindTest | nameTest )
            int alt75=2;
            try { dbg.enterDecision(75);

            try {
                isCyclicDecision = true;
                alt75 = dfa75.predict(input);
            }
            catch (NoViableAltException nvae) {
                dbg.recognitionException(nvae);
                throw nvae;
            }
            } finally {dbg.exitDecision(75);}

            switch (alt75) {
                case 1 :
                    dbg.enterAlt(1);

                    // XQuery.g:131:12: kindTest
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(131,12);
                    pushFollow(FOLLOW_kindTest_in_nodeTest1822);
                    kindTest344=kindTest();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, kindTest344.getTree());
                    dbg.location(131,23);


                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // XQuery.g:131:23: nameTest
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(131,23);
                    pushFollow(FOLLOW_nameTest_in_nodeTest1826);
                    nameTest345=nameTest();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, nameTest345.getTree());
                    dbg.location(0,0);


                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(131, 32);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "nodeTest");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end nodeTest

    public static class nameTest_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start nameTest
    // XQuery.g:132:1: nameTest : ( qNameOrIdent | wildcard );
    public final XQueryParser.nameTest_return nameTest() throws RecognitionException {
        XQueryParser.nameTest_return retval = new XQueryParser.nameTest_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        XQueryParser.qNameOrIdent_return qNameOrIdent346 = null;

        XQueryParser.wildcard_return wildcard347 = null;



        try { dbg.enterRule(getGrammarFileName(), "nameTest");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(132, 1);

        try {
            // XQuery.g:132:10: ( qNameOrIdent | wildcard )
            int alt76=2;
            try { dbg.enterDecision(76);

            int LA76_0 = input.LA(1);

            if ( ((LA76_0>=XQUERY && LA76_0<=VERSION)||(LA76_0>=ENCODING && LA76_0<=NAMESPACE)||(LA76_0>=DECLARE && LA76_0<=COPY_NAMESPACES)||(LA76_0>=NO_PRESERVE && LA76_0<=CONSTRUCTION)||LA76_0==AS||(LA76_0>=RETURN && LA76_0<=STRICT)||(LA76_0>=CHILD && LA76_0<=ANCESTOR_OR_SELF)||(LA76_0>=DOCUMENT && LA76_0<=NCName)) ) {
                int LA76_1 = input.LA(2);

                if ( (LA76_1==COLON) ) {
                    int LA76_3 = input.LA(3);

                    if ( (LA76_3==149) ) {
                        alt76=2;
                    }
                    else if ( ((LA76_3>=XQUERY && LA76_3<=VERSION)||(LA76_3>=ENCODING && LA76_3<=NAMESPACE)||(LA76_3>=DECLARE && LA76_3<=COPY_NAMESPACES)||(LA76_3>=NO_PRESERVE && LA76_3<=CONSTRUCTION)||LA76_3==AS||(LA76_3>=RETURN && LA76_3<=STRICT)||(LA76_3>=CHILD && LA76_3<=ANCESTOR_OR_SELF)||(LA76_3>=DOCUMENT && LA76_3<=NCName)) ) {
                        alt76=1;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 76, 3, input);

                        dbg.recognitionException(nvae);
                        throw nvae;
                    }
                }
                else if ( (LA76_1==EOF||(LA76_1>=CLOSE_ANGLE && LA76_1<=OPEN_ANGLE)||(LA76_1>=Lit_EQ && LA76_1<=SEMICOLON)||LA76_1==DEFAULT||(LA76_1>=ORDER && LA76_1<=EMPTY)||LA76_1==COMMA||LA76_1==COLLATION||LA76_1==RPAREN||(LA76_1>=RCURLY && LA76_1<=FOR)||(LA76_1>=LET && LA76_1<=WHERE)||(LA76_1>=STABLE && LA76_1<=DESCENDING)||LA76_1==SATISFIES||LA76_1==CASE||(LA76_1>=ELSE && LA76_1<=INSTANCE)||(LA76_1>=TREAT && LA76_1<=IS)||(LA76_1>=SLASH && LA76_1<=SLASH_SLASH)||(LA76_1>=LBRACKET && LA76_1<=RBRACKET)||(LA76_1>=147 && LA76_1<=155)) ) {
                    alt76=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 76, 1, input);

                    dbg.recognitionException(nvae);
                    throw nvae;
                }
            }
            else if ( (LA76_0==149) ) {
                alt76=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 76, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;
            }
            } finally {dbg.exitDecision(76);}

            switch (alt76) {
                case 1 :
                    dbg.enterAlt(1);

                    // XQuery.g:132:12: qNameOrIdent
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(132,12);
                    pushFollow(FOLLOW_qNameOrIdent_in_nameTest1834);
                    qNameOrIdent346=qNameOrIdent();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, qNameOrIdent346.getTree());
                    dbg.location(132,27);


                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // XQuery.g:132:27: wildcard
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(132,27);
                    pushFollow(FOLLOW_wildcard_in_nameTest1838);
                    wildcard347=wildcard();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, wildcard347.getTree());
                    dbg.location(0,0);


                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(132, 35);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "nameTest");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end nameTest

    public static class wildcard_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start wildcard
    // XQuery.g:133:1: wildcard : ( '*' ( COLON ncName )? | ncName COLON '*' );
    public final XQueryParser.wildcard_return wildcard() throws RecognitionException {
        XQueryParser.wildcard_return retval = new XQueryParser.wildcard_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal348=null;
        Token COLON349=null;
        Token COLON352=null;
        Token char_literal353=null;
        XQueryParser.ncName_return ncName350 = null;

        XQueryParser.ncName_return ncName351 = null;


        Object char_literal348_tree=null;
        Object COLON349_tree=null;
        Object COLON352_tree=null;
        Object char_literal353_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "wildcard");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(133, 1);

        try {
            // XQuery.g:133:10: ( '*' ( COLON ncName )? | ncName COLON '*' )
            int alt78=2;
            try { dbg.enterDecision(78);

            int LA78_0 = input.LA(1);

            if ( (LA78_0==149) ) {
                alt78=1;
            }
            else if ( ((LA78_0>=XQUERY && LA78_0<=VERSION)||(LA78_0>=ENCODING && LA78_0<=NAMESPACE)||(LA78_0>=DECLARE && LA78_0<=COPY_NAMESPACES)||(LA78_0>=NO_PRESERVE && LA78_0<=CONSTRUCTION)||LA78_0==AS||(LA78_0>=RETURN && LA78_0<=STRICT)||(LA78_0>=CHILD && LA78_0<=ANCESTOR_OR_SELF)||(LA78_0>=DOCUMENT && LA78_0<=NCName)) ) {
                alt78=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 78, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;
            }
            } finally {dbg.exitDecision(78);}

            switch (alt78) {
                case 1 :
                    dbg.enterAlt(1);

                    // XQuery.g:133:12: '*' ( COLON ncName )?
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(133,12);
                    char_literal348=(Token)input.LT(1);
                    match(input,149,FOLLOW_149_in_wildcard1845); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal348_tree = (Object)adaptor.create(char_literal348);
                    adaptor.addChild(root_0, char_literal348_tree);
                    }
                    dbg.location(133,16);
                    // XQuery.g:133:16: ( COLON ncName )?
                    int alt77=2;
                    try { dbg.enterSubRule(77);
                    try { dbg.enterDecision(77);

                    int LA77_0 = input.LA(1);

                    if ( (LA77_0==COLON) ) {
                        alt77=1;
                    }
                    } finally {dbg.exitDecision(77);}

                    switch (alt77) {
                        case 1 :
                            dbg.enterAlt(1);

                            // XQuery.g:133:17: COLON ncName
                            {
                            dbg.location(133,17);
                            COLON349=(Token)input.LT(1);
                            match(input,COLON,FOLLOW_COLON_in_wildcard1848); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            COLON349_tree = (Object)adaptor.create(COLON349);
                            adaptor.addChild(root_0, COLON349_tree);
                            }
                            dbg.location(133,23);
                            pushFollow(FOLLOW_ncName_in_wildcard1850);
                            ncName350=ncName();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, ncName350.getTree());
                            dbg.location(133,29);


                            }
                            break;

                    }
                    } finally {dbg.exitSubRule(77);}

                    dbg.location(133,34);


                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // XQuery.g:133:34: ncName COLON '*'
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(133,34);
                    pushFollow(FOLLOW_ncName_in_wildcard1856);
                    ncName351=ncName();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, ncName351.getTree());
                    dbg.location(133,41);
                    COLON352=(Token)input.LT(1);
                    match(input,COLON,FOLLOW_COLON_in_wildcard1858); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    COLON352_tree = (Object)adaptor.create(COLON352);
                    adaptor.addChild(root_0, COLON352_tree);
                    }
                    dbg.location(133,47);
                    char_literal353=(Token)input.LT(1);
                    match(input,149,FOLLOW_149_in_wildcard1860); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal353_tree = (Object)adaptor.create(char_literal353);
                    adaptor.addChild(root_0, char_literal353_tree);
                    }
                    dbg.location(0,0);


                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(133, 50);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "wildcard");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end wildcard

    public static class filterExpr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start filterExpr
    // XQuery.g:134:1: filterExpr : primaryExpr predicateList ;
    public final XQueryParser.filterExpr_return filterExpr() throws RecognitionException {
        XQueryParser.filterExpr_return retval = new XQueryParser.filterExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        XQueryParser.primaryExpr_return primaryExpr354 = null;

        XQueryParser.predicateList_return predicateList355 = null;



        try { dbg.enterRule(getGrammarFileName(), "filterExpr");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(134, 1);

        try {
            // XQuery.g:134:12: ( primaryExpr predicateList )
            dbg.enterAlt(1);

            // XQuery.g:134:14: primaryExpr predicateList
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(134,14);
            pushFollow(FOLLOW_primaryExpr_in_filterExpr1867);
            primaryExpr354=primaryExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, primaryExpr354.getTree());
            dbg.location(134,26);
            pushFollow(FOLLOW_predicateList_in_filterExpr1869);
            predicateList355=predicateList();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, predicateList355.getTree());
            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(134, 40);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "filterExpr");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end filterExpr

    public static class predicateList_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start predicateList
    // XQuery.g:135:1: predicateList : ( predicate )* ;
    public final XQueryParser.predicateList_return predicateList() throws RecognitionException {
        XQueryParser.predicateList_return retval = new XQueryParser.predicateList_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        XQueryParser.predicate_return predicate356 = null;



        try { dbg.enterRule(getGrammarFileName(), "predicateList");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(135, 1);

        try {
            // XQuery.g:135:15: ( ( predicate )* )
            dbg.enterAlt(1);

            // XQuery.g:135:17: ( predicate )*
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(135,17);
            // XQuery.g:135:17: ( predicate )*
            try { dbg.enterSubRule(79);

            loop79:
            do {
                int alt79=2;
                try { dbg.enterDecision(79);

                int LA79_0 = input.LA(1);

                if ( (LA79_0==LBRACKET) ) {
                    alt79=1;
                }


                } finally {dbg.exitDecision(79);}

                switch (alt79) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // XQuery.g:135:17: predicate
            	    {
            	    dbg.location(135,17);
            	    pushFollow(FOLLOW_predicate_in_predicateList1877);
            	    predicate356=predicate();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, predicate356.getTree());
            	    dbg.location(135,17);


            	    }
            	    break;

            	default :
            	    break loop79;
                }
            } while (true);
            } finally {dbg.exitSubRule(79);}

            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(135, 28);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "predicateList");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end predicateList

    public static class predicate_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start predicate
    // XQuery.g:136:1: predicate : LBRACKET expr RBRACKET ;
    public final XQueryParser.predicate_return predicate() throws RecognitionException {
        XQueryParser.predicate_return retval = new XQueryParser.predicate_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token LBRACKET357=null;
        Token RBRACKET359=null;
        XQueryParser.expr_return expr358 = null;


        Object LBRACKET357_tree=null;
        Object RBRACKET359_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "predicate");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(136, 1);

        try {
            // XQuery.g:136:11: ( LBRACKET expr RBRACKET )
            dbg.enterAlt(1);

            // XQuery.g:136:13: LBRACKET expr RBRACKET
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(136,13);
            LBRACKET357=(Token)input.LT(1);
            match(input,LBRACKET,FOLLOW_LBRACKET_in_predicate1886); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            LBRACKET357_tree = (Object)adaptor.create(LBRACKET357);
            adaptor.addChild(root_0, LBRACKET357_tree);
            }
            dbg.location(136,22);
            pushFollow(FOLLOW_expr_in_predicate1888);
            expr358=expr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, expr358.getTree());
            dbg.location(136,27);
            RBRACKET359=(Token)input.LT(1);
            match(input,RBRACKET,FOLLOW_RBRACKET_in_predicate1890); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            RBRACKET359_tree = (Object)adaptor.create(RBRACKET359);
            adaptor.addChild(root_0, RBRACKET359_tree);
            }
            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(136, 36);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "predicate");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end predicate

    public static class primaryExpr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start primaryExpr
    // XQuery.g:137:1: primaryExpr : ( literal | varRef | parenthesizedExpr | contextItemExpr | functionCall | orderedExpr | unorderedExpr | constructor );
    public final XQueryParser.primaryExpr_return primaryExpr() throws RecognitionException {
        XQueryParser.primaryExpr_return retval = new XQueryParser.primaryExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        XQueryParser.literal_return literal360 = null;

        XQueryParser.varRef_return varRef361 = null;

        XQueryParser.parenthesizedExpr_return parenthesizedExpr362 = null;

        XQueryParser.contextItemExpr_return contextItemExpr363 = null;

        XQueryParser.functionCall_return functionCall364 = null;

        XQueryParser.orderedExpr_return orderedExpr365 = null;

        XQueryParser.unorderedExpr_return unorderedExpr366 = null;

        XQueryParser.constructor_return constructor367 = null;



        try { dbg.enterRule(getGrammarFileName(), "primaryExpr");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(137, 1);

        try {
            // XQuery.g:137:13: ( literal | varRef | parenthesizedExpr | contextItemExpr | functionCall | orderedExpr | unorderedExpr | constructor )
            int alt80=8;
            try { dbg.enterDecision(80);

            try {
                isCyclicDecision = true;
                alt80 = dfa80.predict(input);
            }
            catch (NoViableAltException nvae) {
                dbg.recognitionException(nvae);
                throw nvae;
            }
            } finally {dbg.exitDecision(80);}

            switch (alt80) {
                case 1 :
                    dbg.enterAlt(1);

                    // XQuery.g:137:15: literal
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(137,15);
                    pushFollow(FOLLOW_literal_in_primaryExpr1898);
                    literal360=literal();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, literal360.getTree());
                    dbg.location(137,25);


                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // XQuery.g:137:25: varRef
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(137,25);
                    pushFollow(FOLLOW_varRef_in_primaryExpr1902);
                    varRef361=varRef();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, varRef361.getTree());
                    dbg.location(137,34);


                    }
                    break;
                case 3 :
                    dbg.enterAlt(3);

                    // XQuery.g:137:34: parenthesizedExpr
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(137,34);
                    pushFollow(FOLLOW_parenthesizedExpr_in_primaryExpr1906);
                    parenthesizedExpr362=parenthesizedExpr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, parenthesizedExpr362.getTree());
                    dbg.location(137,54);


                    }
                    break;
                case 4 :
                    dbg.enterAlt(4);

                    // XQuery.g:137:54: contextItemExpr
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(137,54);
                    pushFollow(FOLLOW_contextItemExpr_in_primaryExpr1910);
                    contextItemExpr363=contextItemExpr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, contextItemExpr363.getTree());
                    dbg.location(137,72);


                    }
                    break;
                case 5 :
                    dbg.enterAlt(5);

                    // XQuery.g:137:72: functionCall
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(137,72);
                    pushFollow(FOLLOW_functionCall_in_primaryExpr1914);
                    functionCall364=functionCall();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, functionCall364.getTree());
                    dbg.location(137,87);


                    }
                    break;
                case 6 :
                    dbg.enterAlt(6);

                    // XQuery.g:137:87: orderedExpr
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(137,87);
                    pushFollow(FOLLOW_orderedExpr_in_primaryExpr1918);
                    orderedExpr365=orderedExpr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, orderedExpr365.getTree());
                    dbg.location(137,101);


                    }
                    break;
                case 7 :
                    dbg.enterAlt(7);

                    // XQuery.g:137:101: unorderedExpr
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(137,101);
                    pushFollow(FOLLOW_unorderedExpr_in_primaryExpr1922);
                    unorderedExpr366=unorderedExpr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, unorderedExpr366.getTree());
                    dbg.location(137,117);


                    }
                    break;
                case 8 :
                    dbg.enterAlt(8);

                    // XQuery.g:137:117: constructor
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(137,117);
                    pushFollow(FOLLOW_constructor_in_primaryExpr1926);
                    constructor367=constructor();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, constructor367.getTree());
                    dbg.location(0,0);


                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(137, 129);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "primaryExpr");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end primaryExpr

    public static class literal_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start literal
    // XQuery.g:138:1: literal : ( numericLiteral | StringLiteral );
    public final XQueryParser.literal_return literal() throws RecognitionException {
        XQueryParser.literal_return retval = new XQueryParser.literal_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token StringLiteral369=null;
        XQueryParser.numericLiteral_return numericLiteral368 = null;


        Object StringLiteral369_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "literal");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(138, 1);

        try {
            // XQuery.g:138:10: ( numericLiteral | StringLiteral )
            int alt81=2;
            try { dbg.enterDecision(81);

            int LA81_0 = input.LA(1);

            if ( ((LA81_0>=IntegerLiteral && LA81_0<=DoubleLiteral)) ) {
                alt81=1;
            }
            else if ( (LA81_0==StringLiteral) ) {
                alt81=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 81, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;
            }
            } finally {dbg.exitDecision(81);}

            switch (alt81) {
                case 1 :
                    dbg.enterAlt(1);

                    // XQuery.g:138:12: numericLiteral
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(138,12);
                    pushFollow(FOLLOW_numericLiteral_in_literal1935);
                    numericLiteral368=numericLiteral();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, numericLiteral368.getTree());
                    dbg.location(138,29);


                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // XQuery.g:138:29: StringLiteral
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(138,29);
                    StringLiteral369=(Token)input.LT(1);
                    match(input,StringLiteral,FOLLOW_StringLiteral_in_literal1939); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    StringLiteral369_tree = (Object)adaptor.create(StringLiteral369);
                    adaptor.addChild(root_0, StringLiteral369_tree);
                    }
                    dbg.location(0,0);


                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(138, 43);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "literal");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end literal

    public static class numericLiteral_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start numericLiteral
    // XQuery.g:139:1: numericLiteral : ( IntegerLiteral | DecimalLiteral | DoubleLiteral );
    public final XQueryParser.numericLiteral_return numericLiteral() throws RecognitionException {
        XQueryParser.numericLiteral_return retval = new XQueryParser.numericLiteral_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set370=null;

        Object set370_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "numericLiteral");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(139, 1);

        try {
            // XQuery.g:139:16: ( IntegerLiteral | DecimalLiteral | DoubleLiteral )
            dbg.enterAlt(1);

            // XQuery.g:
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(139,16);
            set370=(Token)input.LT(1);
            if ( (input.LA(1)>=IntegerLiteral && input.LA(1)<=DoubleLiteral) ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set370));
                state.errorRecovery=false;state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                dbg.recognitionException(mse);
                throw mse;
            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(139, 66);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "numericLiteral");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end numericLiteral

    public static class varRef_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start varRef
    // XQuery.g:140:1: varRef : variable ;
    public final XQueryParser.varRef_return varRef() throws RecognitionException {
        XQueryParser.varRef_return retval = new XQueryParser.varRef_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        XQueryParser.variable_return variable371 = null;



        try { dbg.enterRule(getGrammarFileName(), "varRef");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(140, 1);

        try {
            // XQuery.g:140:9: ( variable )
            dbg.enterAlt(1);

            // XQuery.g:140:11: variable
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(140,11);
            pushFollow(FOLLOW_variable_in_varRef1964);
            variable371=variable();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, variable371.getTree());
            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(140, 20);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "varRef");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end varRef

    public static class varName_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start varName
    // XQuery.g:141:1: varName : qNameOrIdent ;
    public final XQueryParser.varName_return varName() throws RecognitionException {
        XQueryParser.varName_return retval = new XQueryParser.varName_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        XQueryParser.qNameOrIdent_return qNameOrIdent372 = null;



        try { dbg.enterRule(getGrammarFileName(), "varName");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(141, 1);

        try {
            // XQuery.g:141:10: ( qNameOrIdent )
            dbg.enterAlt(1);

            // XQuery.g:141:12: qNameOrIdent
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(141,12);
            pushFollow(FOLLOW_qNameOrIdent_in_varName1973);
            qNameOrIdent372=qNameOrIdent();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, qNameOrIdent372.getTree());
            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(141, 25);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "varName");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end varName

    public static class parenthesizedExpr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start parenthesizedExpr
    // XQuery.g:142:1: parenthesizedExpr : LPAREN ( expr )? RPAREN ;
    public final XQueryParser.parenthesizedExpr_return parenthesizedExpr() throws RecognitionException {
        XQueryParser.parenthesizedExpr_return retval = new XQueryParser.parenthesizedExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token LPAREN373=null;
        Token RPAREN375=null;
        XQueryParser.expr_return expr374 = null;


        Object LPAREN373_tree=null;
        Object RPAREN375_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "parenthesizedExpr");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(142, 1);

        try {
            // XQuery.g:143:3: ( LPAREN ( expr )? RPAREN )
            dbg.enterAlt(1);

            // XQuery.g:143:5: LPAREN ( expr )? RPAREN
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(143,5);
            LPAREN373=(Token)input.LT(1);
            match(input,LPAREN,FOLLOW_LPAREN_in_parenthesizedExpr1984); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            LPAREN373_tree = (Object)adaptor.create(LPAREN373);
            adaptor.addChild(root_0, LPAREN373_tree);
            }
            dbg.location(143,12);
            // XQuery.g:143:12: ( expr )?
            int alt82=2;
            try { dbg.enterSubRule(82);
            try { dbg.enterDecision(82);

            int LA82_0 = input.LA(1);

            if ( (LA82_0==OPEN_ANGLE||(LA82_0>=XQUERY && LA82_0<=NAMESPACE)||(LA82_0>=DECLARE && LA82_0<=COPY_NAMESPACES)||(LA82_0>=NO_PRESERVE && LA82_0<=CONSTRUCTION)||(LA82_0>=AS && LA82_0<=LPAREN)||(LA82_0>=RETURN && LA82_0<=ANCESTOR_OR_SELF)||(LA82_0>=IntegerLiteral && LA82_0<=DirPIConstructor)||(LA82_0>=DOCUMENT && LA82_0<=NCName)||(LA82_0>=146 && LA82_0<=149)||(LA82_0>=157 && LA82_0<=159)) ) {
                alt82=1;
            }
            } finally {dbg.exitDecision(82);}

            switch (alt82) {
                case 1 :
                    dbg.enterAlt(1);

                    // XQuery.g:143:12: expr
                    {
                    dbg.location(143,12);
                    pushFollow(FOLLOW_expr_in_parenthesizedExpr1986);
                    expr374=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr374.getTree());
                    dbg.location(143,12);


                    }
                    break;

            }
            } finally {dbg.exitSubRule(82);}

            dbg.location(143,18);
            RPAREN375=(Token)input.LT(1);
            match(input,RPAREN,FOLLOW_RPAREN_in_parenthesizedExpr1989); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            RPAREN375_tree = (Object)adaptor.create(RPAREN375);
            adaptor.addChild(root_0, RPAREN375_tree);
            }
            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(143, 25);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "parenthesizedExpr");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end parenthesizedExpr

    public static class contextItemExpr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start contextItemExpr
    // XQuery.g:144:1: contextItemExpr : '.' ;
    public final XQueryParser.contextItemExpr_return contextItemExpr() throws RecognitionException {
        XQueryParser.contextItemExpr_return retval = new XQueryParser.contextItemExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal376=null;

        Object char_literal376_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "contextItemExpr");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(144, 1);

        try {
            // XQuery.g:144:17: ( '.' )
            dbg.enterAlt(1);

            // XQuery.g:144:19: '.'
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(144,19);
            char_literal376=(Token)input.LT(1);
            match(input,159,FOLLOW_159_in_contextItemExpr1997); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal376_tree = (Object)adaptor.create(char_literal376);
            adaptor.addChild(root_0, char_literal376_tree);
            }
            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(144, 23);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "contextItemExpr");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end contextItemExpr

    public static class orderedExpr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start orderedExpr
    // XQuery.g:145:1: orderedExpr : ORDERED LCURLY expr RCURLY ;
    public final XQueryParser.orderedExpr_return orderedExpr() throws RecognitionException {
        XQueryParser.orderedExpr_return retval = new XQueryParser.orderedExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token ORDERED377=null;
        Token LCURLY378=null;
        Token RCURLY380=null;
        XQueryParser.expr_return expr379 = null;


        Object ORDERED377_tree=null;
        Object LCURLY378_tree=null;
        Object RCURLY380_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "orderedExpr");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(145, 1);

        try {
            // XQuery.g:145:13: ( ORDERED LCURLY expr RCURLY )
            dbg.enterAlt(1);

            // XQuery.g:145:15: ORDERED LCURLY expr RCURLY
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(145,15);
            ORDERED377=(Token)input.LT(1);
            match(input,ORDERED,FOLLOW_ORDERED_in_orderedExpr2005); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            ORDERED377_tree = (Object)adaptor.create(ORDERED377);
            adaptor.addChild(root_0, ORDERED377_tree);
            }
            dbg.location(145,23);
            LCURLY378=(Token)input.LT(1);
            match(input,LCURLY,FOLLOW_LCURLY_in_orderedExpr2007); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            LCURLY378_tree = (Object)adaptor.create(LCURLY378);
            adaptor.addChild(root_0, LCURLY378_tree);
            }
            dbg.location(145,30);
            pushFollow(FOLLOW_expr_in_orderedExpr2009);
            expr379=expr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, expr379.getTree());
            dbg.location(145,35);
            RCURLY380=(Token)input.LT(1);
            match(input,RCURLY,FOLLOW_RCURLY_in_orderedExpr2011); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            RCURLY380_tree = (Object)adaptor.create(RCURLY380);
            adaptor.addChild(root_0, RCURLY380_tree);
            }
            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(145, 42);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "orderedExpr");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end orderedExpr

    public static class unorderedExpr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start unorderedExpr
    // XQuery.g:146:1: unorderedExpr : UNORDERED LCURLY expr RCURLY ;
    public final XQueryParser.unorderedExpr_return unorderedExpr() throws RecognitionException {
        XQueryParser.unorderedExpr_return retval = new XQueryParser.unorderedExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token UNORDERED381=null;
        Token LCURLY382=null;
        Token RCURLY384=null;
        XQueryParser.expr_return expr383 = null;


        Object UNORDERED381_tree=null;
        Object LCURLY382_tree=null;
        Object RCURLY384_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "unorderedExpr");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(146, 1);

        try {
            // XQuery.g:146:15: ( UNORDERED LCURLY expr RCURLY )
            dbg.enterAlt(1);

            // XQuery.g:146:17: UNORDERED LCURLY expr RCURLY
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(146,17);
            UNORDERED381=(Token)input.LT(1);
            match(input,UNORDERED,FOLLOW_UNORDERED_in_unorderedExpr2019); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            UNORDERED381_tree = (Object)adaptor.create(UNORDERED381);
            adaptor.addChild(root_0, UNORDERED381_tree);
            }
            dbg.location(146,27);
            LCURLY382=(Token)input.LT(1);
            match(input,LCURLY,FOLLOW_LCURLY_in_unorderedExpr2021); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            LCURLY382_tree = (Object)adaptor.create(LCURLY382);
            adaptor.addChild(root_0, LCURLY382_tree);
            }
            dbg.location(146,34);
            pushFollow(FOLLOW_expr_in_unorderedExpr2023);
            expr383=expr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, expr383.getTree());
            dbg.location(146,39);
            RCURLY384=(Token)input.LT(1);
            match(input,RCURLY,FOLLOW_RCURLY_in_unorderedExpr2025); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            RCURLY384_tree = (Object)adaptor.create(RCURLY384);
            adaptor.addChild(root_0, RCURLY384_tree);
            }
            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(146, 46);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "unorderedExpr");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end unorderedExpr

    public static class functionCall_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start functionCall
    // XQuery.g:147:1: functionCall : functionCallPre ( exprSingle ( COMMA exprSingle )* )? RPAREN ;
    public final XQueryParser.functionCall_return functionCall() throws RecognitionException {
        XQueryParser.functionCall_return retval = new XQueryParser.functionCall_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token COMMA387=null;
        Token RPAREN389=null;
        XQueryParser.functionCallPre_return functionCallPre385 = null;

        XQueryParser.exprSingle_return exprSingle386 = null;

        XQueryParser.exprSingle_return exprSingle388 = null;


        Object COMMA387_tree=null;
        Object RPAREN389_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "functionCall");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(147, 1);

        try {
            // XQuery.g:147:14: ( functionCallPre ( exprSingle ( COMMA exprSingle )* )? RPAREN )
            dbg.enterAlt(1);

            // XQuery.g:147:16: functionCallPre ( exprSingle ( COMMA exprSingle )* )? RPAREN
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(147,16);
            pushFollow(FOLLOW_functionCallPre_in_functionCall2033);
            functionCallPre385=functionCallPre();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, functionCallPre385.getTree());
            dbg.location(147,32);
            // XQuery.g:147:32: ( exprSingle ( COMMA exprSingle )* )?
            int alt84=2;
            try { dbg.enterSubRule(84);
            try { dbg.enterDecision(84);

            int LA84_0 = input.LA(1);

            if ( (LA84_0==OPEN_ANGLE||(LA84_0>=XQUERY && LA84_0<=NAMESPACE)||(LA84_0>=DECLARE && LA84_0<=COPY_NAMESPACES)||(LA84_0>=NO_PRESERVE && LA84_0<=CONSTRUCTION)||(LA84_0>=AS && LA84_0<=LPAREN)||(LA84_0>=RETURN && LA84_0<=ANCESTOR_OR_SELF)||(LA84_0>=IntegerLiteral && LA84_0<=DirPIConstructor)||(LA84_0>=DOCUMENT && LA84_0<=NCName)||(LA84_0>=146 && LA84_0<=149)||(LA84_0>=157 && LA84_0<=159)) ) {
                alt84=1;
            }
            } finally {dbg.exitDecision(84);}

            switch (alt84) {
                case 1 :
                    dbg.enterAlt(1);

                    // XQuery.g:147:33: exprSingle ( COMMA exprSingle )*
                    {
                    dbg.location(147,33);
                    pushFollow(FOLLOW_exprSingle_in_functionCall2036);
                    exprSingle386=exprSingle();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, exprSingle386.getTree());
                    dbg.location(147,44);
                    // XQuery.g:147:44: ( COMMA exprSingle )*
                    try { dbg.enterSubRule(83);

                    loop83:
                    do {
                        int alt83=2;
                        try { dbg.enterDecision(83);

                        int LA83_0 = input.LA(1);

                        if ( (LA83_0==COMMA) ) {
                            alt83=1;
                        }


                        } finally {dbg.exitDecision(83);}

                        switch (alt83) {
                    	case 1 :
                    	    dbg.enterAlt(1);

                    	    // XQuery.g:147:45: COMMA exprSingle
                    	    {
                    	    dbg.location(147,45);
                    	    COMMA387=(Token)input.LT(1);
                    	    match(input,COMMA,FOLLOW_COMMA_in_functionCall2039); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    COMMA387_tree = (Object)adaptor.create(COMMA387);
                    	    adaptor.addChild(root_0, COMMA387_tree);
                    	    }
                    	    dbg.location(147,51);
                    	    pushFollow(FOLLOW_exprSingle_in_functionCall2041);
                    	    exprSingle388=exprSingle();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) adaptor.addChild(root_0, exprSingle388.getTree());
                    	    dbg.location(147,61);


                    	    }
                    	    break;

                    	default :
                    	    break loop83;
                        }
                    } while (true);
                    } finally {dbg.exitSubRule(83);}

                    dbg.location(147,63);


                    }
                    break;

            }
            } finally {dbg.exitSubRule(84);}

            dbg.location(147,66);
            RPAREN389=(Token)input.LT(1);
            match(input,RPAREN,FOLLOW_RPAREN_in_functionCall2047); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            RPAREN389_tree = (Object)adaptor.create(RPAREN389);
            adaptor.addChild(root_0, RPAREN389_tree);
            }
            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(147, 125);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "functionCall");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end functionCall

    public static class functionCallPre_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start functionCallPre
    // XQuery.g:148:1: functionCallPre : funcName LPAREN ;
    public final XQueryParser.functionCallPre_return functionCallPre() throws RecognitionException {
        XQueryParser.functionCallPre_return retval = new XQueryParser.functionCallPre_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token LPAREN391=null;
        XQueryParser.funcName_return funcName390 = null;


        Object LPAREN391_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "functionCallPre");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(148, 1);

        try {
            // XQuery.g:148:17: ( funcName LPAREN )
            dbg.enterAlt(1);

            // XQuery.g:148:19: funcName LPAREN
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(148,19);
            pushFollow(FOLLOW_funcName_in_functionCallPre2059);
            funcName390=funcName();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, funcName390.getTree());
            dbg.location(148,28);
            LPAREN391=(Token)input.LT(1);
            match(input,LPAREN,FOLLOW_LPAREN_in_functionCallPre2061); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            LPAREN391_tree = (Object)adaptor.create(LPAREN391);
            adaptor.addChild(root_0, LPAREN391_tree);
            }
            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(148, 34);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "functionCallPre");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end functionCallPre

    public static class constructor_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start constructor
    // XQuery.g:149:1: constructor : ( directConstructor | computedConstructor );
    public final XQueryParser.constructor_return constructor() throws RecognitionException {
        XQueryParser.constructor_return retval = new XQueryParser.constructor_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        XQueryParser.directConstructor_return directConstructor392 = null;

        XQueryParser.computedConstructor_return computedConstructor393 = null;



        try { dbg.enterRule(getGrammarFileName(), "constructor");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(149, 1);

        try {
            // XQuery.g:149:13: ( directConstructor | computedConstructor )
            int alt85=2;
            try { dbg.enterDecision(85);

            int LA85_0 = input.LA(1);

            if ( (LA85_0==OPEN_ANGLE||(LA85_0>=DirCommentConstructor && LA85_0<=DirPIConstructor)) ) {
                alt85=1;
            }
            else if ( (LA85_0==ELEMENT||LA85_0==ATTRIBUTE||(LA85_0>=DOCUMENT && LA85_0<=PROCESSING_INSTRUCTION)) ) {
                alt85=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 85, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;
            }
            } finally {dbg.exitDecision(85);}

            switch (alt85) {
                case 1 :
                    dbg.enterAlt(1);

                    // XQuery.g:149:15: directConstructor
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(149,15);
                    pushFollow(FOLLOW_directConstructor_in_constructor2068);
                    directConstructor392=directConstructor();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, directConstructor392.getTree());
                    dbg.location(149,36);


                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // XQuery.g:149:36: computedConstructor
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(149,36);
                    pushFollow(FOLLOW_computedConstructor_in_constructor2073);
                    computedConstructor393=computedConstructor();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, computedConstructor393.getTree());
                    dbg.location(0,0);


                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(149, 56);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "constructor");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end constructor

    public static class directConstructor_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start directConstructor
    // XQuery.g:150:1: directConstructor : ( dirElemConstructor | DirCommentConstructor | DirPIConstructor );
    public final XQueryParser.directConstructor_return directConstructor() throws RecognitionException {
        XQueryParser.directConstructor_return retval = new XQueryParser.directConstructor_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token DirCommentConstructor395=null;
        Token DirPIConstructor396=null;
        XQueryParser.dirElemConstructor_return dirElemConstructor394 = null;


        Object DirCommentConstructor395_tree=null;
        Object DirPIConstructor396_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "directConstructor");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(150, 1);

        try {
            // XQuery.g:151:3: ( dirElemConstructor | DirCommentConstructor | DirPIConstructor )
            int alt86=3;
            try { dbg.enterDecision(86);

            switch ( input.LA(1) ) {
            case OPEN_ANGLE:
                {
                alt86=1;
                }
                break;
            case DirCommentConstructor:
                {
                alt86=2;
                }
                break;
            case DirPIConstructor:
                {
                alt86=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 86, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;
            }

            } finally {dbg.exitDecision(86);}

            switch (alt86) {
                case 1 :
                    dbg.enterAlt(1);

                    // XQuery.g:151:5: dirElemConstructor
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(151,5);
                    pushFollow(FOLLOW_dirElemConstructor_in_directConstructor2084);
                    dirElemConstructor394=dirElemConstructor();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, dirElemConstructor394.getTree());
                    dbg.location(151,27);


                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // XQuery.g:151:27: DirCommentConstructor
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(151,27);
                    DirCommentConstructor395=(Token)input.LT(1);
                    match(input,DirCommentConstructor,FOLLOW_DirCommentConstructor_in_directConstructor2089); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    DirCommentConstructor395_tree = (Object)adaptor.create(DirCommentConstructor395);
                    adaptor.addChild(root_0, DirCommentConstructor395_tree);
                    }
                    dbg.location(151,52);


                    }
                    break;
                case 3 :
                    dbg.enterAlt(3);

                    // XQuery.g:151:52: DirPIConstructor
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(151,52);
                    DirPIConstructor396=(Token)input.LT(1);
                    match(input,DirPIConstructor,FOLLOW_DirPIConstructor_in_directConstructor2094); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    DirPIConstructor396_tree = (Object)adaptor.create(DirPIConstructor396);
                    adaptor.addChild(root_0, DirPIConstructor396_tree);
                    }
                    dbg.location(0,0);


                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(151, 69);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "directConstructor");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end directConstructor

    public static class dirElemConstructor_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start dirElemConstructor
    // XQuery.g:152:1: dirElemConstructor : OPEN_ANGLE qNameOrIdent dirAttributeList ( EMPTY_CLOSE_TAG | ( CLOSE_ANGLE dirElemContent CLOSE_TAG qNameOrIdent ( S )? CLOSE_ANGLE ) ) ;
    public final XQueryParser.dirElemConstructor_return dirElemConstructor() throws RecognitionException {
        XQueryParser.dirElemConstructor_return retval = new XQueryParser.dirElemConstructor_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token OPEN_ANGLE397=null;
        Token EMPTY_CLOSE_TAG400=null;
        Token CLOSE_ANGLE401=null;
        Token CLOSE_TAG403=null;
        Token S405=null;
        Token CLOSE_ANGLE406=null;
        XQueryParser.qNameOrIdent_return qNameOrIdent398 = null;

        XQueryParser.dirAttributeList_return dirAttributeList399 = null;

        XQueryParser.dirElemContent_return dirElemContent402 = null;

        XQueryParser.qNameOrIdent_return qNameOrIdent404 = null;


        Object OPEN_ANGLE397_tree=null;
        Object EMPTY_CLOSE_TAG400_tree=null;
        Object CLOSE_ANGLE401_tree=null;
        Object CLOSE_TAG403_tree=null;
        Object S405_tree=null;
        Object CLOSE_ANGLE406_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "dirElemConstructor");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(152, 1);

        try {
            // XQuery.g:153:3: ( OPEN_ANGLE qNameOrIdent dirAttributeList ( EMPTY_CLOSE_TAG | ( CLOSE_ANGLE dirElemContent CLOSE_TAG qNameOrIdent ( S )? CLOSE_ANGLE ) ) )
            dbg.enterAlt(1);

            // XQuery.g:153:5: OPEN_ANGLE qNameOrIdent dirAttributeList ( EMPTY_CLOSE_TAG | ( CLOSE_ANGLE dirElemContent CLOSE_TAG qNameOrIdent ( S )? CLOSE_ANGLE ) )
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(153,5);
            OPEN_ANGLE397=(Token)input.LT(1);
            match(input,OPEN_ANGLE,FOLLOW_OPEN_ANGLE_in_dirElemConstructor2105); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            OPEN_ANGLE397_tree = (Object)adaptor.create(OPEN_ANGLE397);
            adaptor.addChild(root_0, OPEN_ANGLE397_tree);
            }
            dbg.location(153,16);
            if ( state.backtracking==0 ) {
               pushXMLLexer(); 
            }
            dbg.location(153,36);
            pushFollow(FOLLOW_qNameOrIdent_in_dirElemConstructor2109);
            qNameOrIdent398=qNameOrIdent();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, qNameOrIdent398.getTree());
            dbg.location(153,49);
            pushFollow(FOLLOW_dirAttributeList_in_dirElemConstructor2111);
            dirAttributeList399=dirAttributeList();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, dirAttributeList399.getTree());
            dbg.location(154,4);
            // XQuery.g:154:4: ( EMPTY_CLOSE_TAG | ( CLOSE_ANGLE dirElemContent CLOSE_TAG qNameOrIdent ( S )? CLOSE_ANGLE ) )
            int alt88=2;
            try { dbg.enterSubRule(88);
            try { dbg.enterDecision(88);

            int LA88_0 = input.LA(1);

            if ( (LA88_0==EMPTY_CLOSE_TAG) ) {
                alt88=1;
            }
            else if ( (LA88_0==CLOSE_ANGLE) ) {
                alt88=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 88, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;
            }
            } finally {dbg.exitDecision(88);}

            switch (alt88) {
                case 1 :
                    dbg.enterAlt(1);

                    // XQuery.g:154:5: EMPTY_CLOSE_TAG
                    {
                    dbg.location(154,5);
                    EMPTY_CLOSE_TAG400=(Token)input.LT(1);
                    match(input,EMPTY_CLOSE_TAG,FOLLOW_EMPTY_CLOSE_TAG_in_dirElemConstructor2118); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    EMPTY_CLOSE_TAG400_tree = (Object)adaptor.create(EMPTY_CLOSE_TAG400);
                    adaptor.addChild(root_0, EMPTY_CLOSE_TAG400_tree);
                    }
                    dbg.location(154,23);


                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // XQuery.g:154:23: ( CLOSE_ANGLE dirElemContent CLOSE_TAG qNameOrIdent ( S )? CLOSE_ANGLE )
                    {
                    dbg.location(154,23);
                    // XQuery.g:154:23: ( CLOSE_ANGLE dirElemContent CLOSE_TAG qNameOrIdent ( S )? CLOSE_ANGLE )
                    dbg.enterAlt(1);

                    // XQuery.g:154:24: CLOSE_ANGLE dirElemContent CLOSE_TAG qNameOrIdent ( S )? CLOSE_ANGLE
                    {
                    dbg.location(154,24);
                    CLOSE_ANGLE401=(Token)input.LT(1);
                    match(input,CLOSE_ANGLE,FOLLOW_CLOSE_ANGLE_in_dirElemConstructor2123); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    CLOSE_ANGLE401_tree = (Object)adaptor.create(CLOSE_ANGLE401);
                    adaptor.addChild(root_0, CLOSE_ANGLE401_tree);
                    }
                    dbg.location(154,36);
                    pushFollow(FOLLOW_dirElemContent_in_dirElemConstructor2125);
                    dirElemContent402=dirElemContent();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, dirElemContent402.getTree());
                    dbg.location(154,51);
                    CLOSE_TAG403=(Token)input.LT(1);
                    match(input,CLOSE_TAG,FOLLOW_CLOSE_TAG_in_dirElemConstructor2127); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    CLOSE_TAG403_tree = (Object)adaptor.create(CLOSE_TAG403);
                    adaptor.addChild(root_0, CLOSE_TAG403_tree);
                    }
                    dbg.location(154,61);
                    pushFollow(FOLLOW_qNameOrIdent_in_dirElemConstructor2129);
                    qNameOrIdent404=qNameOrIdent();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, qNameOrIdent404.getTree());
                    dbg.location(154,74);
                    // XQuery.g:154:74: ( S )?
                    int alt87=2;
                    try { dbg.enterSubRule(87);
                    try { dbg.enterDecision(87);

                    int LA87_0 = input.LA(1);

                    if ( (LA87_0==S) ) {
                        alt87=1;
                    }
                    } finally {dbg.exitDecision(87);}

                    switch (alt87) {
                        case 1 :
                            dbg.enterAlt(1);

                            // XQuery.g:154:74: S
                            {
                            dbg.location(154,74);
                            S405=(Token)input.LT(1);
                            match(input,S,FOLLOW_S_in_dirElemConstructor2131); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            S405_tree = (Object)adaptor.create(S405);
                            adaptor.addChild(root_0, S405_tree);
                            }
                            dbg.location(154,74);


                            }
                            break;

                    }
                    } finally {dbg.exitSubRule(87);}

                    dbg.location(154,77);
                    CLOSE_ANGLE406=(Token)input.LT(1);
                    match(input,CLOSE_ANGLE,FOLLOW_CLOSE_ANGLE_in_dirElemConstructor2134); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    CLOSE_ANGLE406_tree = (Object)adaptor.create(CLOSE_ANGLE406);
                    adaptor.addChild(root_0, CLOSE_ANGLE406_tree);
                    }
                    dbg.location(154,88);


                    }

                    dbg.location(154,89);


                    }
                    break;

            }
            } finally {dbg.exitSubRule(88);}

            dbg.location(155,4);
            if ( state.backtracking==0 ) {
               popXMLLexer(); 
            }
            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(155, 42);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "dirElemConstructor");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end dirElemConstructor

    public static class dirAttributeList_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start dirAttributeList
    // XQuery.g:156:1: dirAttributeList : ( S ( qNameOrIdent ( S )? Lit_EQ ( S )? dirAttributeValue )? )* ;
    public final XQueryParser.dirAttributeList_return dirAttributeList() throws RecognitionException {
        XQueryParser.dirAttributeList_return retval = new XQueryParser.dirAttributeList_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token S407=null;
        Token S409=null;
        Token Lit_EQ410=null;
        Token S411=null;
        XQueryParser.qNameOrIdent_return qNameOrIdent408 = null;

        XQueryParser.dirAttributeValue_return dirAttributeValue412 = null;


        Object S407_tree=null;
        Object S409_tree=null;
        Object Lit_EQ410_tree=null;
        Object S411_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "dirAttributeList");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(156, 1);

        try {
            // XQuery.g:157:3: ( ( S ( qNameOrIdent ( S )? Lit_EQ ( S )? dirAttributeValue )? )* )
            dbg.enterAlt(1);

            // XQuery.g:157:5: ( S ( qNameOrIdent ( S )? Lit_EQ ( S )? dirAttributeValue )? )*
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(157,5);
            // XQuery.g:157:5: ( S ( qNameOrIdent ( S )? Lit_EQ ( S )? dirAttributeValue )? )*
            try { dbg.enterSubRule(92);

            loop92:
            do {
                int alt92=2;
                try { dbg.enterDecision(92);

                int LA92_0 = input.LA(1);

                if ( (LA92_0==S) ) {
                    alt92=1;
                }


                } finally {dbg.exitDecision(92);}

                switch (alt92) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // XQuery.g:157:6: S ( qNameOrIdent ( S )? Lit_EQ ( S )? dirAttributeValue )?
            	    {
            	    dbg.location(157,6);
            	    S407=(Token)input.LT(1);
            	    match(input,S,FOLLOW_S_in_dirAttributeList2154); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    S407_tree = (Object)adaptor.create(S407);
            	    adaptor.addChild(root_0, S407_tree);
            	    }
            	    dbg.location(157,8);
            	    // XQuery.g:157:8: ( qNameOrIdent ( S )? Lit_EQ ( S )? dirAttributeValue )?
            	    int alt91=2;
            	    try { dbg.enterSubRule(91);
            	    try { dbg.enterDecision(91);

            	    int LA91_0 = input.LA(1);

            	    if ( ((LA91_0>=XQUERY && LA91_0<=VERSION)||(LA91_0>=ENCODING && LA91_0<=NAMESPACE)||(LA91_0>=DECLARE && LA91_0<=COPY_NAMESPACES)||(LA91_0>=NO_PRESERVE && LA91_0<=CONSTRUCTION)||LA91_0==AS||(LA91_0>=RETURN && LA91_0<=STRICT)||(LA91_0>=CHILD && LA91_0<=ANCESTOR_OR_SELF)||(LA91_0>=DOCUMENT && LA91_0<=NCName)) ) {
            	        alt91=1;
            	    }
            	    } finally {dbg.exitDecision(91);}

            	    switch (alt91) {
            	        case 1 :
            	            dbg.enterAlt(1);

            	            // XQuery.g:157:9: qNameOrIdent ( S )? Lit_EQ ( S )? dirAttributeValue
            	            {
            	            dbg.location(157,9);
            	            pushFollow(FOLLOW_qNameOrIdent_in_dirAttributeList2157);
            	            qNameOrIdent408=qNameOrIdent();

            	            state._fsp--;
            	            if (state.failed) return retval;
            	            if ( state.backtracking==0 ) adaptor.addChild(root_0, qNameOrIdent408.getTree());
            	            dbg.location(157,22);
            	            // XQuery.g:157:22: ( S )?
            	            int alt89=2;
            	            try { dbg.enterSubRule(89);
            	            try { dbg.enterDecision(89);

            	            int LA89_0 = input.LA(1);

            	            if ( (LA89_0==S) ) {
            	                alt89=1;
            	            }
            	            } finally {dbg.exitDecision(89);}

            	            switch (alt89) {
            	                case 1 :
            	                    dbg.enterAlt(1);

            	                    // XQuery.g:157:22: S
            	                    {
            	                    dbg.location(157,22);
            	                    S409=(Token)input.LT(1);
            	                    match(input,S,FOLLOW_S_in_dirAttributeList2159); if (state.failed) return retval;
            	                    if ( state.backtracking==0 ) {
            	                    S409_tree = (Object)adaptor.create(S409);
            	                    adaptor.addChild(root_0, S409_tree);
            	                    }
            	                    dbg.location(157,22);


            	                    }
            	                    break;

            	            }
            	            } finally {dbg.exitSubRule(89);}

            	            dbg.location(157,25);
            	            Lit_EQ410=(Token)input.LT(1);
            	            match(input,Lit_EQ,FOLLOW_Lit_EQ_in_dirAttributeList2162); if (state.failed) return retval;
            	            if ( state.backtracking==0 ) {
            	            Lit_EQ410_tree = (Object)adaptor.create(Lit_EQ410);
            	            adaptor.addChild(root_0, Lit_EQ410_tree);
            	            }
            	            dbg.location(157,32);
            	            // XQuery.g:157:32: ( S )?
            	            int alt90=2;
            	            try { dbg.enterSubRule(90);
            	            try { dbg.enterDecision(90);

            	            int LA90_0 = input.LA(1);

            	            if ( (LA90_0==S) ) {
            	                alt90=1;
            	            }
            	            } finally {dbg.exitDecision(90);}

            	            switch (alt90) {
            	                case 1 :
            	                    dbg.enterAlt(1);

            	                    // XQuery.g:157:32: S
            	                    {
            	                    dbg.location(157,32);
            	                    S411=(Token)input.LT(1);
            	                    match(input,S,FOLLOW_S_in_dirAttributeList2164); if (state.failed) return retval;
            	                    if ( state.backtracking==0 ) {
            	                    S411_tree = (Object)adaptor.create(S411);
            	                    adaptor.addChild(root_0, S411_tree);
            	                    }
            	                    dbg.location(157,32);


            	                    }
            	                    break;

            	            }
            	            } finally {dbg.exitSubRule(90);}

            	            dbg.location(157,35);
            	            pushFollow(FOLLOW_dirAttributeValue_in_dirAttributeList2167);
            	            dirAttributeValue412=dirAttributeValue();

            	            state._fsp--;
            	            if (state.failed) return retval;
            	            if ( state.backtracking==0 ) adaptor.addChild(root_0, dirAttributeValue412.getTree());
            	            dbg.location(157,52);


            	            }
            	            break;

            	    }
            	    } finally {dbg.exitSubRule(91);}

            	    dbg.location(157,54);


            	    }
            	    break;

            	default :
            	    break loop92;
                }
            } while (true);
            } finally {dbg.exitSubRule(92);}

            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(157, 76);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "dirAttributeList");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end dirAttributeList

    public static class dirAttributeValue_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start dirAttributeValue
    // XQuery.g:158:1: dirAttributeValue : ( ( QUOT ( ESCAPE_QUOT | quotAttrValueContent )* QUOT ) | ( APOS ( ESCAPE_APOS | aposAttrValueContent )* APOS ) );
    public final XQueryParser.dirAttributeValue_return dirAttributeValue() throws RecognitionException {
        XQueryParser.dirAttributeValue_return retval = new XQueryParser.dirAttributeValue_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token QUOT413=null;
        Token ESCAPE_QUOT414=null;
        Token QUOT416=null;
        Token APOS417=null;
        Token ESCAPE_APOS418=null;
        Token APOS420=null;
        XQueryParser.quotAttrValueContent_return quotAttrValueContent415 = null;

        XQueryParser.aposAttrValueContent_return aposAttrValueContent419 = null;


        Object QUOT413_tree=null;
        Object ESCAPE_QUOT414_tree=null;
        Object QUOT416_tree=null;
        Object APOS417_tree=null;
        Object ESCAPE_APOS418_tree=null;
        Object APOS420_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "dirAttributeValue");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(158, 1);

        try {
            // XQuery.g:159:3: ( ( QUOT ( ESCAPE_QUOT | quotAttrValueContent )* QUOT ) | ( APOS ( ESCAPE_APOS | aposAttrValueContent )* APOS ) )
            int alt95=2;
            try { dbg.enterDecision(95);

            int LA95_0 = input.LA(1);

            if ( (LA95_0==QUOT) ) {
                alt95=1;
            }
            else if ( (LA95_0==APOS) ) {
                alt95=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 95, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;
            }
            } finally {dbg.exitDecision(95);}

            switch (alt95) {
                case 1 :
                    dbg.enterAlt(1);

                    // XQuery.g:159:5: ( QUOT ( ESCAPE_QUOT | quotAttrValueContent )* QUOT )
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(159,5);
                    // XQuery.g:159:5: ( QUOT ( ESCAPE_QUOT | quotAttrValueContent )* QUOT )
                    dbg.enterAlt(1);

                    // XQuery.g:159:6: QUOT ( ESCAPE_QUOT | quotAttrValueContent )* QUOT
                    {
                    dbg.location(159,6);
                    QUOT413=(Token)input.LT(1);
                    match(input,QUOT,FOLLOW_QUOT_in_dirAttributeValue2184); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    QUOT413_tree = (Object)adaptor.create(QUOT413);
                    adaptor.addChild(root_0, QUOT413_tree);
                    }
                    dbg.location(159,11);
                    // XQuery.g:159:11: ( ESCAPE_QUOT | quotAttrValueContent )*
                    try { dbg.enterSubRule(93);

                    loop93:
                    do {
                        int alt93=3;
                        try { dbg.enterDecision(93);

                        int LA93_0 = input.LA(1);

                        if ( (LA93_0==ESCAPE_QUOT) ) {
                            alt93=1;
                        }
                        else if ( ((LA93_0>=ESCAPE_CURLY_OPEN && LA93_0<=QuotAttrContentChar)||LA93_0==LCURLY||LA93_0==CharRef) ) {
                            alt93=2;
                        }


                        } finally {dbg.exitDecision(93);}

                        switch (alt93) {
                    	case 1 :
                    	    dbg.enterAlt(1);

                    	    // XQuery.g:159:12: ESCAPE_QUOT
                    	    {
                    	    dbg.location(159,12);
                    	    ESCAPE_QUOT414=(Token)input.LT(1);
                    	    match(input,ESCAPE_QUOT,FOLLOW_ESCAPE_QUOT_in_dirAttributeValue2187); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    ESCAPE_QUOT414_tree = (Object)adaptor.create(ESCAPE_QUOT414);
                    	    adaptor.addChild(root_0, ESCAPE_QUOT414_tree);
                    	    }
                    	    dbg.location(159,26);


                    	    }
                    	    break;
                    	case 2 :
                    	    dbg.enterAlt(2);

                    	    // XQuery.g:159:26: quotAttrValueContent
                    	    {
                    	    dbg.location(159,26);
                    	    pushFollow(FOLLOW_quotAttrValueContent_in_dirAttributeValue2191);
                    	    quotAttrValueContent415=quotAttrValueContent();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) adaptor.addChild(root_0, quotAttrValueContent415.getTree());
                    	    dbg.location(159,46);


                    	    }
                    	    break;

                    	default :
                    	    break loop93;
                        }
                    } while (true);
                    } finally {dbg.exitSubRule(93);}

                    dbg.location(159,49);
                    QUOT416=(Token)input.LT(1);
                    match(input,QUOT,FOLLOW_QUOT_in_dirAttributeValue2195); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    QUOT416_tree = (Object)adaptor.create(QUOT416);
                    adaptor.addChild(root_0, QUOT416_tree);
                    }
                    dbg.location(159,53);


                    }

                    dbg.location(159,58);


                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // XQuery.g:159:58: ( APOS ( ESCAPE_APOS | aposAttrValueContent )* APOS )
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(159,58);
                    // XQuery.g:159:58: ( APOS ( ESCAPE_APOS | aposAttrValueContent )* APOS )
                    dbg.enterAlt(1);

                    // XQuery.g:159:59: APOS ( ESCAPE_APOS | aposAttrValueContent )* APOS
                    {
                    dbg.location(159,59);
                    APOS417=(Token)input.LT(1);
                    match(input,APOS,FOLLOW_APOS_in_dirAttributeValue2202); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    APOS417_tree = (Object)adaptor.create(APOS417);
                    adaptor.addChild(root_0, APOS417_tree);
                    }
                    dbg.location(159,64);
                    // XQuery.g:159:64: ( ESCAPE_APOS | aposAttrValueContent )*
                    try { dbg.enterSubRule(94);

                    loop94:
                    do {
                        int alt94=3;
                        try { dbg.enterDecision(94);

                        int LA94_0 = input.LA(1);

                        if ( (LA94_0==ESCAPE_APOS) ) {
                            alt94=1;
                        }
                        else if ( ((LA94_0>=ESCAPE_CURLY_OPEN && LA94_0<=PredefinedEntityRef)||LA94_0==AposAttrContentChar||LA94_0==LCURLY||LA94_0==CharRef) ) {
                            alt94=2;
                        }


                        } finally {dbg.exitDecision(94);}

                        switch (alt94) {
                    	case 1 :
                    	    dbg.enterAlt(1);

                    	    // XQuery.g:159:65: ESCAPE_APOS
                    	    {
                    	    dbg.location(159,65);
                    	    ESCAPE_APOS418=(Token)input.LT(1);
                    	    match(input,ESCAPE_APOS,FOLLOW_ESCAPE_APOS_in_dirAttributeValue2205); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    ESCAPE_APOS418_tree = (Object)adaptor.create(ESCAPE_APOS418);
                    	    adaptor.addChild(root_0, ESCAPE_APOS418_tree);
                    	    }
                    	    dbg.location(159,79);


                    	    }
                    	    break;
                    	case 2 :
                    	    dbg.enterAlt(2);

                    	    // XQuery.g:159:79: aposAttrValueContent
                    	    {
                    	    dbg.location(159,79);
                    	    pushFollow(FOLLOW_aposAttrValueContent_in_dirAttributeValue2209);
                    	    aposAttrValueContent419=aposAttrValueContent();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) adaptor.addChild(root_0, aposAttrValueContent419.getTree());
                    	    dbg.location(159,99);


                    	    }
                    	    break;

                    	default :
                    	    break loop94;
                        }
                    } while (true);
                    } finally {dbg.exitSubRule(94);}

                    dbg.location(159,102);
                    APOS420=(Token)input.LT(1);
                    match(input,APOS,FOLLOW_APOS_in_dirAttributeValue2213); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    APOS420_tree = (Object)adaptor.create(APOS420);
                    adaptor.addChild(root_0, APOS420_tree);
                    }
                    dbg.location(159,106);


                    }

                    dbg.location(0,0);


                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(159, 127);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "dirAttributeValue");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end dirAttributeValue

    public static class quotAttrValueContent_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start quotAttrValueContent
    // XQuery.g:160:1: quotAttrValueContent : ( QuotAttrContentChar | commonContent );
    public final XQueryParser.quotAttrValueContent_return quotAttrValueContent() throws RecognitionException {
        XQueryParser.quotAttrValueContent_return retval = new XQueryParser.quotAttrValueContent_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token QuotAttrContentChar421=null;
        XQueryParser.commonContent_return commonContent422 = null;


        Object QuotAttrContentChar421_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "quotAttrValueContent");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(160, 1);

        try {
            // XQuery.g:161:3: ( QuotAttrContentChar | commonContent )
            int alt96=2;
            try { dbg.enterDecision(96);

            int LA96_0 = input.LA(1);

            if ( (LA96_0==QuotAttrContentChar) ) {
                alt96=1;
            }
            else if ( ((LA96_0>=ESCAPE_CURLY_OPEN && LA96_0<=PredefinedEntityRef)||LA96_0==LCURLY||LA96_0==CharRef) ) {
                alt96=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 96, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;
            }
            } finally {dbg.exitDecision(96);}

            switch (alt96) {
                case 1 :
                    dbg.enterAlt(1);

                    // XQuery.g:161:5: QuotAttrContentChar
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(161,5);
                    QuotAttrContentChar421=(Token)input.LT(1);
                    match(input,QuotAttrContentChar,FOLLOW_QuotAttrContentChar_in_quotAttrValueContent2226); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    QuotAttrContentChar421_tree = (Object)adaptor.create(QuotAttrContentChar421);
                    adaptor.addChild(root_0, QuotAttrContentChar421_tree);
                    }
                    dbg.location(161,28);


                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // XQuery.g:161:28: commonContent
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(161,28);
                    pushFollow(FOLLOW_commonContent_in_quotAttrValueContent2231);
                    commonContent422=commonContent();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, commonContent422.getTree());
                    dbg.location(0,0);


                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(161, 42);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "quotAttrValueContent");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end quotAttrValueContent

    public static class aposAttrValueContent_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start aposAttrValueContent
    // XQuery.g:162:1: aposAttrValueContent : ( AposAttrContentChar | commonContent );
    public final XQueryParser.aposAttrValueContent_return aposAttrValueContent() throws RecognitionException {
        XQueryParser.aposAttrValueContent_return retval = new XQueryParser.aposAttrValueContent_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token AposAttrContentChar423=null;
        XQueryParser.commonContent_return commonContent424 = null;


        Object AposAttrContentChar423_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "aposAttrValueContent");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(162, 1);

        try {
            // XQuery.g:163:3: ( AposAttrContentChar | commonContent )
            int alt97=2;
            try { dbg.enterDecision(97);

            int LA97_0 = input.LA(1);

            if ( (LA97_0==AposAttrContentChar) ) {
                alt97=1;
            }
            else if ( ((LA97_0>=ESCAPE_CURLY_OPEN && LA97_0<=PredefinedEntityRef)||LA97_0==LCURLY||LA97_0==CharRef) ) {
                alt97=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 97, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;
            }
            } finally {dbg.exitDecision(97);}

            switch (alt97) {
                case 1 :
                    dbg.enterAlt(1);

                    // XQuery.g:163:5: AposAttrContentChar
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(163,5);
                    AposAttrContentChar423=(Token)input.LT(1);
                    match(input,AposAttrContentChar,FOLLOW_AposAttrContentChar_in_aposAttrValueContent2241); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    AposAttrContentChar423_tree = (Object)adaptor.create(AposAttrContentChar423);
                    adaptor.addChild(root_0, AposAttrContentChar423_tree);
                    }
                    dbg.location(163,28);


                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // XQuery.g:163:28: commonContent
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(163,28);
                    pushFollow(FOLLOW_commonContent_in_aposAttrValueContent2246);
                    commonContent424=commonContent();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, commonContent424.getTree());
                    dbg.location(0,0);


                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(163, 42);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "aposAttrValueContent");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end aposAttrValueContent

    public static class dirElemContent_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start dirElemContent
    // XQuery.g:164:1: dirElemContent : ( directConstructor | CDataSection | commonContent | ElementContentChar )* ;
    public final XQueryParser.dirElemContent_return dirElemContent() throws RecognitionException {
        XQueryParser.dirElemContent_return retval = new XQueryParser.dirElemContent_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token CDataSection426=null;
        Token ElementContentChar428=null;
        XQueryParser.directConstructor_return directConstructor425 = null;

        XQueryParser.commonContent_return commonContent427 = null;


        Object CDataSection426_tree=null;
        Object ElementContentChar428_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "dirElemContent");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(164, 1);

        try {
            // XQuery.g:164:16: ( ( directConstructor | CDataSection | commonContent | ElementContentChar )* )
            dbg.enterAlt(1);

            // XQuery.g:164:18: ( directConstructor | CDataSection | commonContent | ElementContentChar )*
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(164,18);
            // XQuery.g:164:18: ( directConstructor | CDataSection | commonContent | ElementContentChar )*
            try { dbg.enterSubRule(98);

            loop98:
            do {
                int alt98=5;
                try { dbg.enterDecision(98);

                switch ( input.LA(1) ) {
                case OPEN_ANGLE:
                case DirCommentConstructor:
                case DirPIConstructor:
                    {
                    alt98=1;
                    }
                    break;
                case CDataSection:
                    {
                    alt98=2;
                    }
                    break;
                case ESCAPE_CURLY_OPEN:
                case ESCAPE_CURLY_CLOSE:
                case PredefinedEntityRef:
                case LCURLY:
                case CharRef:
                    {
                    alt98=3;
                    }
                    break;
                case ElementContentChar:
                    {
                    alt98=4;
                    }
                    break;

                }

                } finally {dbg.exitDecision(98);}

                switch (alt98) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // XQuery.g:164:19: directConstructor
            	    {
            	    dbg.location(164,19);
            	    pushFollow(FOLLOW_directConstructor_in_dirElemContent2255);
            	    directConstructor425=directConstructor();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, directConstructor425.getTree());
            	    dbg.location(164,40);


            	    }
            	    break;
            	case 2 :
            	    dbg.enterAlt(2);

            	    // XQuery.g:164:40: CDataSection
            	    {
            	    dbg.location(164,40);
            	    CDataSection426=(Token)input.LT(1);
            	    match(input,CDataSection,FOLLOW_CDataSection_in_dirElemContent2260); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    CDataSection426_tree = (Object)adaptor.create(CDataSection426);
            	    adaptor.addChild(root_0, CDataSection426_tree);
            	    }
            	    dbg.location(164,56);


            	    }
            	    break;
            	case 3 :
            	    dbg.enterAlt(3);

            	    // XQuery.g:164:56: commonContent
            	    {
            	    dbg.location(164,56);
            	    pushFollow(FOLLOW_commonContent_in_dirElemContent2265);
            	    commonContent427=commonContent();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, commonContent427.getTree());
            	    dbg.location(164,73);


            	    }
            	    break;
            	case 4 :
            	    dbg.enterAlt(4);

            	    // XQuery.g:164:73: ElementContentChar
            	    {
            	    dbg.location(164,73);
            	    ElementContentChar428=(Token)input.LT(1);
            	    match(input,ElementContentChar,FOLLOW_ElementContentChar_in_dirElemContent2270); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    ElementContentChar428_tree = (Object)adaptor.create(ElementContentChar428);
            	    adaptor.addChild(root_0, ElementContentChar428_tree);
            	    }
            	    dbg.location(164,91);


            	    }
            	    break;

            	default :
            	    break loop98;
                }
            } while (true);
            } finally {dbg.exitSubRule(98);}

            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(164, 93);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "dirElemContent");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end dirElemContent

    public static class commonContent_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start commonContent
    // XQuery.g:165:1: commonContent : ( PredefinedEntityRef | CharRef | ESCAPE_CURLY_OPEN | ESCAPE_CURLY_CLOSE | elemEnclosedExpr );
    public final XQueryParser.commonContent_return commonContent() throws RecognitionException {
        XQueryParser.commonContent_return retval = new XQueryParser.commonContent_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token PredefinedEntityRef429=null;
        Token CharRef430=null;
        Token ESCAPE_CURLY_OPEN431=null;
        Token ESCAPE_CURLY_CLOSE432=null;
        XQueryParser.elemEnclosedExpr_return elemEnclosedExpr433 = null;


        Object PredefinedEntityRef429_tree=null;
        Object CharRef430_tree=null;
        Object ESCAPE_CURLY_OPEN431_tree=null;
        Object ESCAPE_CURLY_CLOSE432_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "commonContent");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(165, 1);

        try {
            // XQuery.g:165:15: ( PredefinedEntityRef | CharRef | ESCAPE_CURLY_OPEN | ESCAPE_CURLY_CLOSE | elemEnclosedExpr )
            int alt99=5;
            try { dbg.enterDecision(99);

            switch ( input.LA(1) ) {
            case PredefinedEntityRef:
                {
                alt99=1;
                }
                break;
            case CharRef:
                {
                alt99=2;
                }
                break;
            case ESCAPE_CURLY_OPEN:
                {
                alt99=3;
                }
                break;
            case ESCAPE_CURLY_CLOSE:
                {
                alt99=4;
                }
                break;
            case LCURLY:
                {
                alt99=5;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 99, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;
            }

            } finally {dbg.exitDecision(99);}

            switch (alt99) {
                case 1 :
                    dbg.enterAlt(1);

                    // XQuery.g:165:17: PredefinedEntityRef
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(165,17);
                    PredefinedEntityRef429=(Token)input.LT(1);
                    match(input,PredefinedEntityRef,FOLLOW_PredefinedEntityRef_in_commonContent2279); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    PredefinedEntityRef429_tree = (Object)adaptor.create(PredefinedEntityRef429);
                    adaptor.addChild(root_0, PredefinedEntityRef429_tree);
                    }
                    dbg.location(165,39);


                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // XQuery.g:165:39: CharRef
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(165,39);
                    CharRef430=(Token)input.LT(1);
                    match(input,CharRef,FOLLOW_CharRef_in_commonContent2283); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    CharRef430_tree = (Object)adaptor.create(CharRef430);
                    adaptor.addChild(root_0, CharRef430_tree);
                    }
                    dbg.location(165,49);


                    }
                    break;
                case 3 :
                    dbg.enterAlt(3);

                    // XQuery.g:165:49: ESCAPE_CURLY_OPEN
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(165,49);
                    ESCAPE_CURLY_OPEN431=(Token)input.LT(1);
                    match(input,ESCAPE_CURLY_OPEN,FOLLOW_ESCAPE_CURLY_OPEN_in_commonContent2287); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    ESCAPE_CURLY_OPEN431_tree = (Object)adaptor.create(ESCAPE_CURLY_OPEN431);
                    adaptor.addChild(root_0, ESCAPE_CURLY_OPEN431_tree);
                    }
                    dbg.location(165,69);


                    }
                    break;
                case 4 :
                    dbg.enterAlt(4);

                    // XQuery.g:165:69: ESCAPE_CURLY_CLOSE
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(165,69);
                    ESCAPE_CURLY_CLOSE432=(Token)input.LT(1);
                    match(input,ESCAPE_CURLY_CLOSE,FOLLOW_ESCAPE_CURLY_CLOSE_in_commonContent2291); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    ESCAPE_CURLY_CLOSE432_tree = (Object)adaptor.create(ESCAPE_CURLY_CLOSE432);
                    adaptor.addChild(root_0, ESCAPE_CURLY_CLOSE432_tree);
                    }
                    dbg.location(165,90);


                    }
                    break;
                case 5 :
                    dbg.enterAlt(5);

                    // XQuery.g:165:90: elemEnclosedExpr
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(165,90);
                    pushFollow(FOLLOW_elemEnclosedExpr_in_commonContent2295);
                    elemEnclosedExpr433=elemEnclosedExpr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, elemEnclosedExpr433.getTree());
                    dbg.location(0,0);


                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(165, 107);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "commonContent");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end commonContent

    public static class elemEnclosedExpr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start elemEnclosedExpr
    // XQuery.g:166:1: elemEnclosedExpr : LCURLY expr RCURLY ;
    public final XQueryParser.elemEnclosedExpr_return elemEnclosedExpr() throws RecognitionException {
        XQueryParser.elemEnclosedExpr_return retval = new XQueryParser.elemEnclosedExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token LCURLY434=null;
        Token RCURLY436=null;
        XQueryParser.expr_return expr435 = null;


        Object LCURLY434_tree=null;
        Object RCURLY436_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "elemEnclosedExpr");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(166, 1);

        try {
            // XQuery.g:167:3: ( LCURLY expr RCURLY )
            dbg.enterAlt(1);

            // XQuery.g:167:5: LCURLY expr RCURLY
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(167,5);
            LCURLY434=(Token)input.LT(1);
            match(input,LCURLY,FOLLOW_LCURLY_in_elemEnclosedExpr2305); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            LCURLY434_tree = (Object)adaptor.create(LCURLY434);
            adaptor.addChild(root_0, LCURLY434_tree);
            }
            dbg.location(167,12);
            if ( state.backtracking==0 ) {
               pushXQueryLexer(); 
            }
            dbg.location(167,35);
            pushFollow(FOLLOW_expr_in_elemEnclosedExpr2309);
            expr435=expr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, expr435.getTree());
            dbg.location(167,40);
            RCURLY436=(Token)input.LT(1);
            match(input,RCURLY,FOLLOW_RCURLY_in_elemEnclosedExpr2311); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            RCURLY436_tree = (Object)adaptor.create(RCURLY436);
            adaptor.addChild(root_0, RCURLY436_tree);
            }
            dbg.location(167,47);
            if ( state.backtracking==0 ) {
               popXQueryLexer(); 
            }
            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(167, 68);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "elemEnclosedExpr");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end elemEnclosedExpr

    public static class computedConstructor_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start computedConstructor
    // XQuery.g:168:1: computedConstructor : ( compDocConstructor | compElemConstructor | compAttrConstructor | compTextConstructor | compCommentConstructor | compPIConstructor );
    public final XQueryParser.computedConstructor_return computedConstructor() throws RecognitionException {
        XQueryParser.computedConstructor_return retval = new XQueryParser.computedConstructor_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        XQueryParser.compDocConstructor_return compDocConstructor437 = null;

        XQueryParser.compElemConstructor_return compElemConstructor438 = null;

        XQueryParser.compAttrConstructor_return compAttrConstructor439 = null;

        XQueryParser.compTextConstructor_return compTextConstructor440 = null;

        XQueryParser.compCommentConstructor_return compCommentConstructor441 = null;

        XQueryParser.compPIConstructor_return compPIConstructor442 = null;



        try { dbg.enterRule(getGrammarFileName(), "computedConstructor");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(168, 1);

        try {
            // XQuery.g:169:3: ( compDocConstructor | compElemConstructor | compAttrConstructor | compTextConstructor | compCommentConstructor | compPIConstructor )
            int alt100=6;
            try { dbg.enterDecision(100);

            switch ( input.LA(1) ) {
            case DOCUMENT:
                {
                alt100=1;
                }
                break;
            case ELEMENT:
                {
                alt100=2;
                }
                break;
            case ATTRIBUTE:
                {
                alt100=3;
                }
                break;
            case TEXT:
                {
                alt100=4;
                }
                break;
            case COMMENT:
                {
                alt100=5;
                }
                break;
            case PROCESSING_INSTRUCTION:
                {
                alt100=6;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 100, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;
            }

            } finally {dbg.exitDecision(100);}

            switch (alt100) {
                case 1 :
                    dbg.enterAlt(1);

                    // XQuery.g:169:5: compDocConstructor
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(169,5);
                    pushFollow(FOLLOW_compDocConstructor_in_computedConstructor2323);
                    compDocConstructor437=compDocConstructor();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, compDocConstructor437.getTree());
                    dbg.location(169,27);


                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // XQuery.g:169:27: compElemConstructor
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(169,27);
                    pushFollow(FOLLOW_compElemConstructor_in_computedConstructor2328);
                    compElemConstructor438=compElemConstructor();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, compElemConstructor438.getTree());
                    dbg.location(169,50);


                    }
                    break;
                case 3 :
                    dbg.enterAlt(3);

                    // XQuery.g:169:50: compAttrConstructor
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(169,50);
                    pushFollow(FOLLOW_compAttrConstructor_in_computedConstructor2333);
                    compAttrConstructor439=compAttrConstructor();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, compAttrConstructor439.getTree());
                    dbg.location(169,73);


                    }
                    break;
                case 4 :
                    dbg.enterAlt(4);

                    // XQuery.g:169:73: compTextConstructor
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(169,73);
                    pushFollow(FOLLOW_compTextConstructor_in_computedConstructor2338);
                    compTextConstructor440=compTextConstructor();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, compTextConstructor440.getTree());
                    dbg.location(170,6);


                    }
                    break;
                case 5 :
                    dbg.enterAlt(5);

                    // XQuery.g:170:6: compCommentConstructor
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(170,6);
                    pushFollow(FOLLOW_compCommentConstructor_in_computedConstructor2347);
                    compCommentConstructor441=compCommentConstructor();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, compCommentConstructor441.getTree());
                    dbg.location(170,32);


                    }
                    break;
                case 6 :
                    dbg.enterAlt(6);

                    // XQuery.g:170:32: compPIConstructor
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(170,32);
                    pushFollow(FOLLOW_compPIConstructor_in_computedConstructor2352);
                    compPIConstructor442=compPIConstructor();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, compPIConstructor442.getTree());
                    dbg.location(0,0);


                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(170, 50);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "computedConstructor");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end computedConstructor

    public static class compDocConstructor_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start compDocConstructor
    // XQuery.g:171:1: compDocConstructor : DOCUMENT LCURLY expr RCURLY ;
    public final XQueryParser.compDocConstructor_return compDocConstructor() throws RecognitionException {
        XQueryParser.compDocConstructor_return retval = new XQueryParser.compDocConstructor_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token DOCUMENT443=null;
        Token LCURLY444=null;
        Token RCURLY446=null;
        XQueryParser.expr_return expr445 = null;


        Object DOCUMENT443_tree=null;
        Object LCURLY444_tree=null;
        Object RCURLY446_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "compDocConstructor");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(171, 1);

        try {
            // XQuery.g:172:3: ( DOCUMENT LCURLY expr RCURLY )
            dbg.enterAlt(1);

            // XQuery.g:172:5: DOCUMENT LCURLY expr RCURLY
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(172,5);
            DOCUMENT443=(Token)input.LT(1);
            match(input,DOCUMENT,FOLLOW_DOCUMENT_in_compDocConstructor2363); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            DOCUMENT443_tree = (Object)adaptor.create(DOCUMENT443);
            adaptor.addChild(root_0, DOCUMENT443_tree);
            }
            dbg.location(172,14);
            LCURLY444=(Token)input.LT(1);
            match(input,LCURLY,FOLLOW_LCURLY_in_compDocConstructor2365); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            LCURLY444_tree = (Object)adaptor.create(LCURLY444);
            adaptor.addChild(root_0, LCURLY444_tree);
            }
            dbg.location(172,21);
            pushFollow(FOLLOW_expr_in_compDocConstructor2367);
            expr445=expr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, expr445.getTree());
            dbg.location(172,26);
            RCURLY446=(Token)input.LT(1);
            match(input,RCURLY,FOLLOW_RCURLY_in_compDocConstructor2369); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            RCURLY446_tree = (Object)adaptor.create(RCURLY446);
            adaptor.addChild(root_0, RCURLY446_tree);
            }
            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(172, 33);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "compDocConstructor");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end compDocConstructor

    public static class compElemConstructor_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start compElemConstructor
    // XQuery.g:173:1: compElemConstructor : ELEMENT ( qNameOrIdent | ( LCURLY expr RCURLY ) ) LCURLY ( contentExpr )? RCURLY ;
    public final XQueryParser.compElemConstructor_return compElemConstructor() throws RecognitionException {
        XQueryParser.compElemConstructor_return retval = new XQueryParser.compElemConstructor_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token ELEMENT447=null;
        Token LCURLY449=null;
        Token RCURLY451=null;
        Token LCURLY452=null;
        Token RCURLY454=null;
        XQueryParser.qNameOrIdent_return qNameOrIdent448 = null;

        XQueryParser.expr_return expr450 = null;

        XQueryParser.contentExpr_return contentExpr453 = null;


        Object ELEMENT447_tree=null;
        Object LCURLY449_tree=null;
        Object RCURLY451_tree=null;
        Object LCURLY452_tree=null;
        Object RCURLY454_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "compElemConstructor");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(173, 1);

        try {
            // XQuery.g:174:3: ( ELEMENT ( qNameOrIdent | ( LCURLY expr RCURLY ) ) LCURLY ( contentExpr )? RCURLY )
            dbg.enterAlt(1);

            // XQuery.g:174:5: ELEMENT ( qNameOrIdent | ( LCURLY expr RCURLY ) ) LCURLY ( contentExpr )? RCURLY
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(174,5);
            ELEMENT447=(Token)input.LT(1);
            match(input,ELEMENT,FOLLOW_ELEMENT_in_compElemConstructor2380); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            ELEMENT447_tree = (Object)adaptor.create(ELEMENT447);
            adaptor.addChild(root_0, ELEMENT447_tree);
            }
            dbg.location(174,13);
            // XQuery.g:174:13: ( qNameOrIdent | ( LCURLY expr RCURLY ) )
            int alt101=2;
            try { dbg.enterSubRule(101);
            try { dbg.enterDecision(101);

            int LA101_0 = input.LA(1);

            if ( ((LA101_0>=XQUERY && LA101_0<=VERSION)||(LA101_0>=ENCODING && LA101_0<=NAMESPACE)||(LA101_0>=DECLARE && LA101_0<=COPY_NAMESPACES)||(LA101_0>=NO_PRESERVE && LA101_0<=CONSTRUCTION)||LA101_0==AS||(LA101_0>=RETURN && LA101_0<=STRICT)||(LA101_0>=CHILD && LA101_0<=ANCESTOR_OR_SELF)||(LA101_0>=DOCUMENT && LA101_0<=NCName)) ) {
                alt101=1;
            }
            else if ( (LA101_0==LCURLY) ) {
                alt101=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 101, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;
            }
            } finally {dbg.exitDecision(101);}

            switch (alt101) {
                case 1 :
                    dbg.enterAlt(1);

                    // XQuery.g:174:14: qNameOrIdent
                    {
                    dbg.location(174,14);
                    pushFollow(FOLLOW_qNameOrIdent_in_compElemConstructor2383);
                    qNameOrIdent448=qNameOrIdent();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, qNameOrIdent448.getTree());
                    dbg.location(174,29);


                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // XQuery.g:174:29: ( LCURLY expr RCURLY )
                    {
                    dbg.location(174,29);
                    // XQuery.g:174:29: ( LCURLY expr RCURLY )
                    dbg.enterAlt(1);

                    // XQuery.g:174:30: LCURLY expr RCURLY
                    {
                    dbg.location(174,30);
                    LCURLY449=(Token)input.LT(1);
                    match(input,LCURLY,FOLLOW_LCURLY_in_compElemConstructor2388); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    LCURLY449_tree = (Object)adaptor.create(LCURLY449);
                    adaptor.addChild(root_0, LCURLY449_tree);
                    }
                    dbg.location(174,37);
                    pushFollow(FOLLOW_expr_in_compElemConstructor2390);
                    expr450=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr450.getTree());
                    dbg.location(174,42);
                    RCURLY451=(Token)input.LT(1);
                    match(input,RCURLY,FOLLOW_RCURLY_in_compElemConstructor2392); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    RCURLY451_tree = (Object)adaptor.create(RCURLY451);
                    adaptor.addChild(root_0, RCURLY451_tree);
                    }
                    dbg.location(174,48);


                    }

                    dbg.location(174,49);


                    }
                    break;

            }
            } finally {dbg.exitSubRule(101);}

            dbg.location(174,51);
            LCURLY452=(Token)input.LT(1);
            match(input,LCURLY,FOLLOW_LCURLY_in_compElemConstructor2396); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            LCURLY452_tree = (Object)adaptor.create(LCURLY452);
            adaptor.addChild(root_0, LCURLY452_tree);
            }
            dbg.location(174,58);
            // XQuery.g:174:58: ( contentExpr )?
            int alt102=2;
            try { dbg.enterSubRule(102);
            try { dbg.enterDecision(102);

            int LA102_0 = input.LA(1);

            if ( (LA102_0==OPEN_ANGLE||(LA102_0>=XQUERY && LA102_0<=NAMESPACE)||(LA102_0>=DECLARE && LA102_0<=COPY_NAMESPACES)||(LA102_0>=NO_PRESERVE && LA102_0<=CONSTRUCTION)||(LA102_0>=AS && LA102_0<=LPAREN)||(LA102_0>=RETURN && LA102_0<=ANCESTOR_OR_SELF)||(LA102_0>=IntegerLiteral && LA102_0<=DirPIConstructor)||(LA102_0>=DOCUMENT && LA102_0<=NCName)||(LA102_0>=146 && LA102_0<=149)||(LA102_0>=157 && LA102_0<=159)) ) {
                alt102=1;
            }
            } finally {dbg.exitDecision(102);}

            switch (alt102) {
                case 1 :
                    dbg.enterAlt(1);

                    // XQuery.g:174:58: contentExpr
                    {
                    dbg.location(174,58);
                    pushFollow(FOLLOW_contentExpr_in_compElemConstructor2398);
                    contentExpr453=contentExpr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, contentExpr453.getTree());
                    dbg.location(174,58);


                    }
                    break;

            }
            } finally {dbg.exitSubRule(102);}

            dbg.location(174,71);
            RCURLY454=(Token)input.LT(1);
            match(input,RCURLY,FOLLOW_RCURLY_in_compElemConstructor2401); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            RCURLY454_tree = (Object)adaptor.create(RCURLY454);
            adaptor.addChild(root_0, RCURLY454_tree);
            }
            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(174, 78);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "compElemConstructor");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end compElemConstructor

    public static class contentExpr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start contentExpr
    // XQuery.g:175:1: contentExpr : expr ;
    public final XQueryParser.contentExpr_return contentExpr() throws RecognitionException {
        XQueryParser.contentExpr_return retval = new XQueryParser.contentExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        XQueryParser.expr_return expr455 = null;



        try { dbg.enterRule(getGrammarFileName(), "contentExpr");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(175, 1);

        try {
            // XQuery.g:175:13: ( expr )
            dbg.enterAlt(1);

            // XQuery.g:175:15: expr
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(175,15);
            pushFollow(FOLLOW_expr_in_contentExpr2409);
            expr455=expr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, expr455.getTree());
            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(175, 20);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "contentExpr");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end contentExpr

    public static class compAttrConstructor_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start compAttrConstructor
    // XQuery.g:176:1: compAttrConstructor : ATTRIBUTE ( qNameOrIdent | ( LCURLY expr RCURLY ) ) LCURLY ( expr )? RCURLY ;
    public final XQueryParser.compAttrConstructor_return compAttrConstructor() throws RecognitionException {
        XQueryParser.compAttrConstructor_return retval = new XQueryParser.compAttrConstructor_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token ATTRIBUTE456=null;
        Token LCURLY458=null;
        Token RCURLY460=null;
        Token LCURLY461=null;
        Token RCURLY463=null;
        XQueryParser.qNameOrIdent_return qNameOrIdent457 = null;

        XQueryParser.expr_return expr459 = null;

        XQueryParser.expr_return expr462 = null;


        Object ATTRIBUTE456_tree=null;
        Object LCURLY458_tree=null;
        Object RCURLY460_tree=null;
        Object LCURLY461_tree=null;
        Object RCURLY463_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "compAttrConstructor");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(176, 1);

        try {
            // XQuery.g:177:3: ( ATTRIBUTE ( qNameOrIdent | ( LCURLY expr RCURLY ) ) LCURLY ( expr )? RCURLY )
            dbg.enterAlt(1);

            // XQuery.g:177:5: ATTRIBUTE ( qNameOrIdent | ( LCURLY expr RCURLY ) ) LCURLY ( expr )? RCURLY
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(177,5);
            ATTRIBUTE456=(Token)input.LT(1);
            match(input,ATTRIBUTE,FOLLOW_ATTRIBUTE_in_compAttrConstructor2420); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            ATTRIBUTE456_tree = (Object)adaptor.create(ATTRIBUTE456);
            adaptor.addChild(root_0, ATTRIBUTE456_tree);
            }
            dbg.location(177,15);
            // XQuery.g:177:15: ( qNameOrIdent | ( LCURLY expr RCURLY ) )
            int alt103=2;
            try { dbg.enterSubRule(103);
            try { dbg.enterDecision(103);

            int LA103_0 = input.LA(1);

            if ( ((LA103_0>=XQUERY && LA103_0<=VERSION)||(LA103_0>=ENCODING && LA103_0<=NAMESPACE)||(LA103_0>=DECLARE && LA103_0<=COPY_NAMESPACES)||(LA103_0>=NO_PRESERVE && LA103_0<=CONSTRUCTION)||LA103_0==AS||(LA103_0>=RETURN && LA103_0<=STRICT)||(LA103_0>=CHILD && LA103_0<=ANCESTOR_OR_SELF)||(LA103_0>=DOCUMENT && LA103_0<=NCName)) ) {
                alt103=1;
            }
            else if ( (LA103_0==LCURLY) ) {
                alt103=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 103, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;
            }
            } finally {dbg.exitDecision(103);}

            switch (alt103) {
                case 1 :
                    dbg.enterAlt(1);

                    // XQuery.g:177:16: qNameOrIdent
                    {
                    dbg.location(177,16);
                    pushFollow(FOLLOW_qNameOrIdent_in_compAttrConstructor2423);
                    qNameOrIdent457=qNameOrIdent();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, qNameOrIdent457.getTree());
                    dbg.location(177,31);


                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // XQuery.g:177:31: ( LCURLY expr RCURLY )
                    {
                    dbg.location(177,31);
                    // XQuery.g:177:31: ( LCURLY expr RCURLY )
                    dbg.enterAlt(1);

                    // XQuery.g:177:32: LCURLY expr RCURLY
                    {
                    dbg.location(177,32);
                    LCURLY458=(Token)input.LT(1);
                    match(input,LCURLY,FOLLOW_LCURLY_in_compAttrConstructor2428); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    LCURLY458_tree = (Object)adaptor.create(LCURLY458);
                    adaptor.addChild(root_0, LCURLY458_tree);
                    }
                    dbg.location(177,39);
                    pushFollow(FOLLOW_expr_in_compAttrConstructor2430);
                    expr459=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr459.getTree());
                    dbg.location(177,44);
                    RCURLY460=(Token)input.LT(1);
                    match(input,RCURLY,FOLLOW_RCURLY_in_compAttrConstructor2432); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    RCURLY460_tree = (Object)adaptor.create(RCURLY460);
                    adaptor.addChild(root_0, RCURLY460_tree);
                    }
                    dbg.location(177,50);


                    }

                    dbg.location(177,51);


                    }
                    break;

            }
            } finally {dbg.exitSubRule(103);}

            dbg.location(177,53);
            LCURLY461=(Token)input.LT(1);
            match(input,LCURLY,FOLLOW_LCURLY_in_compAttrConstructor2436); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            LCURLY461_tree = (Object)adaptor.create(LCURLY461);
            adaptor.addChild(root_0, LCURLY461_tree);
            }
            dbg.location(177,60);
            // XQuery.g:177:60: ( expr )?
            int alt104=2;
            try { dbg.enterSubRule(104);
            try { dbg.enterDecision(104);

            int LA104_0 = input.LA(1);

            if ( (LA104_0==OPEN_ANGLE||(LA104_0>=XQUERY && LA104_0<=NAMESPACE)||(LA104_0>=DECLARE && LA104_0<=COPY_NAMESPACES)||(LA104_0>=NO_PRESERVE && LA104_0<=CONSTRUCTION)||(LA104_0>=AS && LA104_0<=LPAREN)||(LA104_0>=RETURN && LA104_0<=ANCESTOR_OR_SELF)||(LA104_0>=IntegerLiteral && LA104_0<=DirPIConstructor)||(LA104_0>=DOCUMENT && LA104_0<=NCName)||(LA104_0>=146 && LA104_0<=149)||(LA104_0>=157 && LA104_0<=159)) ) {
                alt104=1;
            }
            } finally {dbg.exitDecision(104);}

            switch (alt104) {
                case 1 :
                    dbg.enterAlt(1);

                    // XQuery.g:177:60: expr
                    {
                    dbg.location(177,60);
                    pushFollow(FOLLOW_expr_in_compAttrConstructor2438);
                    expr462=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr462.getTree());
                    dbg.location(177,60);


                    }
                    break;

            }
            } finally {dbg.exitSubRule(104);}

            dbg.location(177,66);
            RCURLY463=(Token)input.LT(1);
            match(input,RCURLY,FOLLOW_RCURLY_in_compAttrConstructor2441); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            RCURLY463_tree = (Object)adaptor.create(RCURLY463);
            adaptor.addChild(root_0, RCURLY463_tree);
            }
            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(177, 73);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "compAttrConstructor");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end compAttrConstructor

    public static class compTextConstructor_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start compTextConstructor
    // XQuery.g:178:1: compTextConstructor : TEXT LCURLY expr RCURLY ;
    public final XQueryParser.compTextConstructor_return compTextConstructor() throws RecognitionException {
        XQueryParser.compTextConstructor_return retval = new XQueryParser.compTextConstructor_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token TEXT464=null;
        Token LCURLY465=null;
        Token RCURLY467=null;
        XQueryParser.expr_return expr466 = null;


        Object TEXT464_tree=null;
        Object LCURLY465_tree=null;
        Object RCURLY467_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "compTextConstructor");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(178, 1);

        try {
            // XQuery.g:179:3: ( TEXT LCURLY expr RCURLY )
            dbg.enterAlt(1);

            // XQuery.g:179:5: TEXT LCURLY expr RCURLY
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(179,5);
            TEXT464=(Token)input.LT(1);
            match(input,TEXT,FOLLOW_TEXT_in_compTextConstructor2452); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            TEXT464_tree = (Object)adaptor.create(TEXT464);
            adaptor.addChild(root_0, TEXT464_tree);
            }
            dbg.location(179,10);
            LCURLY465=(Token)input.LT(1);
            match(input,LCURLY,FOLLOW_LCURLY_in_compTextConstructor2454); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            LCURLY465_tree = (Object)adaptor.create(LCURLY465);
            adaptor.addChild(root_0, LCURLY465_tree);
            }
            dbg.location(179,17);
            pushFollow(FOLLOW_expr_in_compTextConstructor2456);
            expr466=expr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, expr466.getTree());
            dbg.location(179,22);
            RCURLY467=(Token)input.LT(1);
            match(input,RCURLY,FOLLOW_RCURLY_in_compTextConstructor2458); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            RCURLY467_tree = (Object)adaptor.create(RCURLY467);
            adaptor.addChild(root_0, RCURLY467_tree);
            }
            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(179, 29);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "compTextConstructor");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end compTextConstructor

    public static class compCommentConstructor_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start compCommentConstructor
    // XQuery.g:180:1: compCommentConstructor : COMMENT LCURLY expr RCURLY ;
    public final XQueryParser.compCommentConstructor_return compCommentConstructor() throws RecognitionException {
        XQueryParser.compCommentConstructor_return retval = new XQueryParser.compCommentConstructor_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token COMMENT468=null;
        Token LCURLY469=null;
        Token RCURLY471=null;
        XQueryParser.expr_return expr470 = null;


        Object COMMENT468_tree=null;
        Object LCURLY469_tree=null;
        Object RCURLY471_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "compCommentConstructor");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(180, 1);

        try {
            // XQuery.g:181:3: ( COMMENT LCURLY expr RCURLY )
            dbg.enterAlt(1);

            // XQuery.g:181:5: COMMENT LCURLY expr RCURLY
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(181,5);
            COMMENT468=(Token)input.LT(1);
            match(input,COMMENT,FOLLOW_COMMENT_in_compCommentConstructor2469); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            COMMENT468_tree = (Object)adaptor.create(COMMENT468);
            adaptor.addChild(root_0, COMMENT468_tree);
            }
            dbg.location(181,13);
            LCURLY469=(Token)input.LT(1);
            match(input,LCURLY,FOLLOW_LCURLY_in_compCommentConstructor2471); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            LCURLY469_tree = (Object)adaptor.create(LCURLY469);
            adaptor.addChild(root_0, LCURLY469_tree);
            }
            dbg.location(181,20);
            pushFollow(FOLLOW_expr_in_compCommentConstructor2473);
            expr470=expr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, expr470.getTree());
            dbg.location(181,25);
            RCURLY471=(Token)input.LT(1);
            match(input,RCURLY,FOLLOW_RCURLY_in_compCommentConstructor2475); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            RCURLY471_tree = (Object)adaptor.create(RCURLY471);
            adaptor.addChild(root_0, RCURLY471_tree);
            }
            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(181, 32);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "compCommentConstructor");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end compCommentConstructor

    public static class compPIConstructor_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start compPIConstructor
    // XQuery.g:182:1: compPIConstructor : PROCESSING_INSTRUCTION ( ncName | ( LCURLY expr RCURLY ) ) LCURLY ( expr )? RCURLY ;
    public final XQueryParser.compPIConstructor_return compPIConstructor() throws RecognitionException {
        XQueryParser.compPIConstructor_return retval = new XQueryParser.compPIConstructor_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token PROCESSING_INSTRUCTION472=null;
        Token LCURLY474=null;
        Token RCURLY476=null;
        Token LCURLY477=null;
        Token RCURLY479=null;
        XQueryParser.ncName_return ncName473 = null;

        XQueryParser.expr_return expr475 = null;

        XQueryParser.expr_return expr478 = null;


        Object PROCESSING_INSTRUCTION472_tree=null;
        Object LCURLY474_tree=null;
        Object RCURLY476_tree=null;
        Object LCURLY477_tree=null;
        Object RCURLY479_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "compPIConstructor");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(182, 1);

        try {
            // XQuery.g:183:3: ( PROCESSING_INSTRUCTION ( ncName | ( LCURLY expr RCURLY ) ) LCURLY ( expr )? RCURLY )
            dbg.enterAlt(1);

            // XQuery.g:183:5: PROCESSING_INSTRUCTION ( ncName | ( LCURLY expr RCURLY ) ) LCURLY ( expr )? RCURLY
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(183,5);
            PROCESSING_INSTRUCTION472=(Token)input.LT(1);
            match(input,PROCESSING_INSTRUCTION,FOLLOW_PROCESSING_INSTRUCTION_in_compPIConstructor2486); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            PROCESSING_INSTRUCTION472_tree = (Object)adaptor.create(PROCESSING_INSTRUCTION472);
            adaptor.addChild(root_0, PROCESSING_INSTRUCTION472_tree);
            }
            dbg.location(183,28);
            // XQuery.g:183:28: ( ncName | ( LCURLY expr RCURLY ) )
            int alt105=2;
            try { dbg.enterSubRule(105);
            try { dbg.enterDecision(105);

            int LA105_0 = input.LA(1);

            if ( ((LA105_0>=XQUERY && LA105_0<=VERSION)||(LA105_0>=ENCODING && LA105_0<=NAMESPACE)||(LA105_0>=DECLARE && LA105_0<=COPY_NAMESPACES)||(LA105_0>=NO_PRESERVE && LA105_0<=CONSTRUCTION)||LA105_0==AS||(LA105_0>=RETURN && LA105_0<=STRICT)||(LA105_0>=CHILD && LA105_0<=ANCESTOR_OR_SELF)||(LA105_0>=DOCUMENT && LA105_0<=NCName)) ) {
                alt105=1;
            }
            else if ( (LA105_0==LCURLY) ) {
                alt105=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 105, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;
            }
            } finally {dbg.exitDecision(105);}

            switch (alt105) {
                case 1 :
                    dbg.enterAlt(1);

                    // XQuery.g:183:29: ncName
                    {
                    dbg.location(183,29);
                    pushFollow(FOLLOW_ncName_in_compPIConstructor2489);
                    ncName473=ncName();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, ncName473.getTree());
                    dbg.location(183,38);


                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // XQuery.g:183:38: ( LCURLY expr RCURLY )
                    {
                    dbg.location(183,38);
                    // XQuery.g:183:38: ( LCURLY expr RCURLY )
                    dbg.enterAlt(1);

                    // XQuery.g:183:39: LCURLY expr RCURLY
                    {
                    dbg.location(183,39);
                    LCURLY474=(Token)input.LT(1);
                    match(input,LCURLY,FOLLOW_LCURLY_in_compPIConstructor2494); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    LCURLY474_tree = (Object)adaptor.create(LCURLY474);
                    adaptor.addChild(root_0, LCURLY474_tree);
                    }
                    dbg.location(183,46);
                    pushFollow(FOLLOW_expr_in_compPIConstructor2496);
                    expr475=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr475.getTree());
                    dbg.location(183,51);
                    RCURLY476=(Token)input.LT(1);
                    match(input,RCURLY,FOLLOW_RCURLY_in_compPIConstructor2498); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    RCURLY476_tree = (Object)adaptor.create(RCURLY476);
                    adaptor.addChild(root_0, RCURLY476_tree);
                    }
                    dbg.location(183,57);


                    }

                    dbg.location(183,58);


                    }
                    break;

            }
            } finally {dbg.exitSubRule(105);}

            dbg.location(183,60);
            LCURLY477=(Token)input.LT(1);
            match(input,LCURLY,FOLLOW_LCURLY_in_compPIConstructor2502); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            LCURLY477_tree = (Object)adaptor.create(LCURLY477);
            adaptor.addChild(root_0, LCURLY477_tree);
            }
            dbg.location(183,67);
            // XQuery.g:183:67: ( expr )?
            int alt106=2;
            try { dbg.enterSubRule(106);
            try { dbg.enterDecision(106);

            int LA106_0 = input.LA(1);

            if ( (LA106_0==OPEN_ANGLE||(LA106_0>=XQUERY && LA106_0<=NAMESPACE)||(LA106_0>=DECLARE && LA106_0<=COPY_NAMESPACES)||(LA106_0>=NO_PRESERVE && LA106_0<=CONSTRUCTION)||(LA106_0>=AS && LA106_0<=LPAREN)||(LA106_0>=RETURN && LA106_0<=ANCESTOR_OR_SELF)||(LA106_0>=IntegerLiteral && LA106_0<=DirPIConstructor)||(LA106_0>=DOCUMENT && LA106_0<=NCName)||(LA106_0>=146 && LA106_0<=149)||(LA106_0>=157 && LA106_0<=159)) ) {
                alt106=1;
            }
            } finally {dbg.exitDecision(106);}

            switch (alt106) {
                case 1 :
                    dbg.enterAlt(1);

                    // XQuery.g:183:67: expr
                    {
                    dbg.location(183,67);
                    pushFollow(FOLLOW_expr_in_compPIConstructor2504);
                    expr478=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr478.getTree());
                    dbg.location(183,67);


                    }
                    break;

            }
            } finally {dbg.exitSubRule(106);}

            dbg.location(183,73);
            RCURLY479=(Token)input.LT(1);
            match(input,RCURLY,FOLLOW_RCURLY_in_compPIConstructor2507); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            RCURLY479_tree = (Object)adaptor.create(RCURLY479);
            adaptor.addChild(root_0, RCURLY479_tree);
            }
            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(183, 80);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "compPIConstructor");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end compPIConstructor

    public static class singleType_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start singleType
    // XQuery.g:184:1: singleType : atomicType ( '?' )? ;
    public final XQueryParser.singleType_return singleType() throws RecognitionException {
        XQueryParser.singleType_return retval = new XQueryParser.singleType_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal481=null;
        XQueryParser.atomicType_return atomicType480 = null;


        Object char_literal481_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "singleType");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(184, 1);

        try {
            // XQuery.g:184:12: ( atomicType ( '?' )? )
            dbg.enterAlt(1);

            // XQuery.g:184:14: atomicType ( '?' )?
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(184,14);
            pushFollow(FOLLOW_atomicType_in_singleType2515);
            atomicType480=atomicType();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, atomicType480.getTree());
            dbg.location(184,25);
            // XQuery.g:184:25: ( '?' )?
            int alt107=2;
            try { dbg.enterSubRule(107);
            try { dbg.enterDecision(107);

            int LA107_0 = input.LA(1);

            if ( (LA107_0==160) ) {
                alt107=1;
            }
            } finally {dbg.exitDecision(107);}

            switch (alt107) {
                case 1 :
                    dbg.enterAlt(1);

                    // XQuery.g:184:25: '?'
                    {
                    dbg.location(184,25);
                    char_literal481=(Token)input.LT(1);
                    match(input,160,FOLLOW_160_in_singleType2517); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal481_tree = (Object)adaptor.create(char_literal481);
                    adaptor.addChild(root_0, char_literal481_tree);
                    }
                    dbg.location(184,25);


                    }
                    break;

            }
            } finally {dbg.exitSubRule(107);}

            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(184, 30);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "singleType");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end singleType

    public static class typeDeclaration_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start typeDeclaration
    // XQuery.g:185:1: typeDeclaration : AS sequenceType ;
    public final XQueryParser.typeDeclaration_return typeDeclaration() throws RecognitionException {
        XQueryParser.typeDeclaration_return retval = new XQueryParser.typeDeclaration_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token AS482=null;
        XQueryParser.sequenceType_return sequenceType483 = null;


        Object AS482_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "typeDeclaration");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(185, 1);

        try {
            // XQuery.g:185:17: ( AS sequenceType )
            dbg.enterAlt(1);

            // XQuery.g:185:19: AS sequenceType
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(185,19);
            AS482=(Token)input.LT(1);
            match(input,AS,FOLLOW_AS_in_typeDeclaration2526); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            AS482_tree = (Object)adaptor.create(AS482);
            adaptor.addChild(root_0, AS482_tree);
            }
            dbg.location(185,22);
            pushFollow(FOLLOW_sequenceType_in_typeDeclaration2528);
            sequenceType483=sequenceType();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, sequenceType483.getTree());
            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(185, 35);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "typeDeclaration");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end typeDeclaration

    public static class sequenceType_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start sequenceType
    // XQuery.g:186:1: sequenceType : ( ( EMPTY_SEQUENCE LPAREN RPAREN ) | itemType ( ( occurrenceIndicator )=> occurrenceIndicator )? );
    public final XQueryParser.sequenceType_return sequenceType() throws RecognitionException {
        XQueryParser.sequenceType_return retval = new XQueryParser.sequenceType_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token EMPTY_SEQUENCE484=null;
        Token LPAREN485=null;
        Token RPAREN486=null;
        XQueryParser.itemType_return itemType487 = null;

        XQueryParser.occurrenceIndicator_return occurrenceIndicator488 = null;


        Object EMPTY_SEQUENCE484_tree=null;
        Object LPAREN485_tree=null;
        Object RPAREN486_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "sequenceType");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(186, 1);

        try {
            // XQuery.g:186:14: ( ( EMPTY_SEQUENCE LPAREN RPAREN ) | itemType ( ( occurrenceIndicator )=> occurrenceIndicator )? )
            int alt109=2;
            try { dbg.enterDecision(109);

            int LA109_0 = input.LA(1);

            if ( (LA109_0==EMPTY_SEQUENCE) ) {
                int LA109_1 = input.LA(2);

                if ( (LA109_1==LPAREN) ) {
                    alt109=1;
                }
                else if ( (LA109_1==EOF||(LA109_1>=CLOSE_ANGLE && LA109_1<=OPEN_ANGLE)||(LA109_1>=Lit_EQ && LA109_1<=SEMICOLON)||LA109_1==DEFAULT||(LA109_1>=ORDER && LA109_1<=EMPTY)||LA109_1==COMMA||LA109_1==COLLATION||LA109_1==AT||LA109_1==EXTERNAL||LA109_1==RPAREN||(LA109_1>=LCURLY && LA109_1<=WHERE)||(LA109_1>=STABLE && LA109_1<=DESCENDING)||LA109_1==SATISFIES||LA109_1==CASE||(LA109_1>=ELSE && LA109_1<=INSTANCE)||(LA109_1>=EQ && LA109_1<=IS)||LA109_1==COLON||LA109_1==RBRACKET||LA109_1==145||(LA109_1>=147 && LA109_1<=155)||LA109_1==160) ) {
                    alt109=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 109, 1, input);

                    dbg.recognitionException(nvae);
                    throw nvae;
                }
            }
            else if ( ((LA109_0>=XQUERY && LA109_0<=VERSION)||(LA109_0>=ENCODING && LA109_0<=NAMESPACE)||(LA109_0>=DECLARE && LA109_0<=COPY_NAMESPACES)||(LA109_0>=NO_PRESERVE && LA109_0<=CONSTRUCTION)||LA109_0==AS||(LA109_0>=RETURN && LA109_0<=STRICT)||(LA109_0>=CHILD && LA109_0<=ANCESTOR_OR_SELF)||(LA109_0>=DOCUMENT && LA109_0<=PROCESSING_INSTRUCTION)||(LA109_0>=ITEM && LA109_0<=NCName)) ) {
                alt109=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 109, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;
            }
            } finally {dbg.exitDecision(109);}

            switch (alt109) {
                case 1 :
                    dbg.enterAlt(1);

                    // XQuery.g:186:16: ( EMPTY_SEQUENCE LPAREN RPAREN )
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(186,16);
                    // XQuery.g:186:16: ( EMPTY_SEQUENCE LPAREN RPAREN )
                    dbg.enterAlt(1);

                    // XQuery.g:186:17: EMPTY_SEQUENCE LPAREN RPAREN
                    {
                    dbg.location(186,17);
                    EMPTY_SEQUENCE484=(Token)input.LT(1);
                    match(input,EMPTY_SEQUENCE,FOLLOW_EMPTY_SEQUENCE_in_sequenceType2537); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    EMPTY_SEQUENCE484_tree = (Object)adaptor.create(EMPTY_SEQUENCE484);
                    adaptor.addChild(root_0, EMPTY_SEQUENCE484_tree);
                    }
                    dbg.location(186,32);
                    LPAREN485=(Token)input.LT(1);
                    match(input,LPAREN,FOLLOW_LPAREN_in_sequenceType2539); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    LPAREN485_tree = (Object)adaptor.create(LPAREN485);
                    adaptor.addChild(root_0, LPAREN485_tree);
                    }
                    dbg.location(186,39);
                    RPAREN486=(Token)input.LT(1);
                    match(input,RPAREN,FOLLOW_RPAREN_in_sequenceType2541); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    RPAREN486_tree = (Object)adaptor.create(RPAREN486);
                    adaptor.addChild(root_0, RPAREN486_tree);
                    }
                    dbg.location(186,45);


                    }

                    dbg.location(187,6);


                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // XQuery.g:187:6: itemType ( ( occurrenceIndicator )=> occurrenceIndicator )?
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(187,6);
                    pushFollow(FOLLOW_itemType_in_sequenceType2551);
                    itemType487=itemType();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, itemType487.getTree());
                    dbg.location(187,15);
                    // XQuery.g:187:15: ( ( occurrenceIndicator )=> occurrenceIndicator )?
                    int alt108=2;
                    try { dbg.enterSubRule(108);
                    try { dbg.enterDecision(108);

                    try {
                        isCyclicDecision = true;
                        alt108 = dfa108.predict(input);
                    }
                    catch (NoViableAltException nvae) {
                        dbg.recognitionException(nvae);
                        throw nvae;
                    }
                    } finally {dbg.exitDecision(108);}

                    switch (alt108) {
                        case 1 :
                            dbg.enterAlt(1);

                            // XQuery.g:187:16: ( occurrenceIndicator )=> occurrenceIndicator
                            {
                            dbg.location(187,41);
                            pushFollow(FOLLOW_occurrenceIndicator_in_sequenceType2560);
                            occurrenceIndicator488=occurrenceIndicator();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, occurrenceIndicator488.getTree());
                            dbg.location(187,60);


                            }
                            break;

                    }
                    } finally {dbg.exitSubRule(108);}

                    dbg.location(0,0);


                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(187, 62);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "sequenceType");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end sequenceType

    public static class occurrenceIndicator_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start occurrenceIndicator
    // XQuery.g:188:1: occurrenceIndicator : ( '?' | '*' | '+' );
    public final XQueryParser.occurrenceIndicator_return occurrenceIndicator() throws RecognitionException {
        XQueryParser.occurrenceIndicator_return retval = new XQueryParser.occurrenceIndicator_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set489=null;

        Object set489_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "occurrenceIndicator");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(188, 1);

        try {
            // XQuery.g:189:3: ( '?' | '*' | '+' )
            dbg.enterAlt(1);

            // XQuery.g:
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(189,3);
            set489=(Token)input.LT(1);
            if ( input.LA(1)==147||input.LA(1)==149||input.LA(1)==160 ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set489));
                state.errorRecovery=false;state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                dbg.recognitionException(mse);
                throw mse;
            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(189, 54);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "occurrenceIndicator");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end occurrenceIndicator

    public static class itemType_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start itemType
    // XQuery.g:190:1: itemType : ( kindTest | ( ITEM LPAREN RPAREN ) | atomicType );
    public final XQueryParser.itemType_return itemType() throws RecognitionException {
        XQueryParser.itemType_return retval = new XQueryParser.itemType_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token ITEM491=null;
        Token LPAREN492=null;
        Token RPAREN493=null;
        XQueryParser.kindTest_return kindTest490 = null;

        XQueryParser.atomicType_return atomicType494 = null;


        Object ITEM491_tree=null;
        Object LPAREN492_tree=null;
        Object RPAREN493_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "itemType");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(190, 1);

        try {
            // XQuery.g:190:10: ( kindTest | ( ITEM LPAREN RPAREN ) | atomicType )
            int alt110=3;
            try { dbg.enterDecision(110);

            try {
                isCyclicDecision = true;
                alt110 = dfa110.predict(input);
            }
            catch (NoViableAltException nvae) {
                dbg.recognitionException(nvae);
                throw nvae;
            }
            } finally {dbg.exitDecision(110);}

            switch (alt110) {
                case 1 :
                    dbg.enterAlt(1);

                    // XQuery.g:190:12: kindTest
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(190,12);
                    pushFollow(FOLLOW_kindTest_in_itemType2590);
                    kindTest490=kindTest();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, kindTest490.getTree());
                    dbg.location(190,23);


                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // XQuery.g:190:23: ( ITEM LPAREN RPAREN )
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(190,23);
                    // XQuery.g:190:23: ( ITEM LPAREN RPAREN )
                    dbg.enterAlt(1);

                    // XQuery.g:190:24: ITEM LPAREN RPAREN
                    {
                    dbg.location(190,24);
                    ITEM491=(Token)input.LT(1);
                    match(input,ITEM,FOLLOW_ITEM_in_itemType2595); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    ITEM491_tree = (Object)adaptor.create(ITEM491);
                    adaptor.addChild(root_0, ITEM491_tree);
                    }
                    dbg.location(190,29);
                    LPAREN492=(Token)input.LT(1);
                    match(input,LPAREN,FOLLOW_LPAREN_in_itemType2597); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    LPAREN492_tree = (Object)adaptor.create(LPAREN492);
                    adaptor.addChild(root_0, LPAREN492_tree);
                    }
                    dbg.location(190,36);
                    RPAREN493=(Token)input.LT(1);
                    match(input,RPAREN,FOLLOW_RPAREN_in_itemType2599); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    RPAREN493_tree = (Object)adaptor.create(RPAREN493);
                    adaptor.addChild(root_0, RPAREN493_tree);
                    }
                    dbg.location(190,42);


                    }

                    dbg.location(190,46);


                    }
                    break;
                case 3 :
                    dbg.enterAlt(3);

                    // XQuery.g:190:46: atomicType
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(190,46);
                    pushFollow(FOLLOW_atomicType_in_itemType2604);
                    atomicType494=atomicType();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, atomicType494.getTree());
                    dbg.location(0,0);


                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(190, 57);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "itemType");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end itemType

    public static class atomicType_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start atomicType
    // XQuery.g:191:1: atomicType : qNameOrIdent ;
    public final XQueryParser.atomicType_return atomicType() throws RecognitionException {
        XQueryParser.atomicType_return retval = new XQueryParser.atomicType_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        XQueryParser.qNameOrIdent_return qNameOrIdent495 = null;



        try { dbg.enterRule(getGrammarFileName(), "atomicType");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(191, 1);

        try {
            // XQuery.g:191:12: ( qNameOrIdent )
            dbg.enterAlt(1);

            // XQuery.g:191:14: qNameOrIdent
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(191,14);
            pushFollow(FOLLOW_qNameOrIdent_in_atomicType2612);
            qNameOrIdent495=qNameOrIdent();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, qNameOrIdent495.getTree());
            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(191, 27);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "atomicType");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end atomicType

    public static class kindTest_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start kindTest
    // XQuery.g:192:1: kindTest : ( documentTest | elementTest | attributeTest | schemaElementTest | schemaAttributeTest | pITest | commentTest | textTest | anyKindTest );
    public final XQueryParser.kindTest_return kindTest() throws RecognitionException {
        XQueryParser.kindTest_return retval = new XQueryParser.kindTest_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        XQueryParser.documentTest_return documentTest496 = null;

        XQueryParser.elementTest_return elementTest497 = null;

        XQueryParser.attributeTest_return attributeTest498 = null;

        XQueryParser.schemaElementTest_return schemaElementTest499 = null;

        XQueryParser.schemaAttributeTest_return schemaAttributeTest500 = null;

        XQueryParser.pITest_return pITest501 = null;

        XQueryParser.commentTest_return commentTest502 = null;

        XQueryParser.textTest_return textTest503 = null;

        XQueryParser.anyKindTest_return anyKindTest504 = null;



        try { dbg.enterRule(getGrammarFileName(), "kindTest");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(192, 1);

        try {
            // XQuery.g:192:10: ( documentTest | elementTest | attributeTest | schemaElementTest | schemaAttributeTest | pITest | commentTest | textTest | anyKindTest )
            int alt111=9;
            try { dbg.enterDecision(111);

            switch ( input.LA(1) ) {
            case DOCUMENT_NODE:
                {
                alt111=1;
                }
                break;
            case ELEMENT:
                {
                alt111=2;
                }
                break;
            case ATTRIBUTE:
                {
                alt111=3;
                }
                break;
            case SCHEMA_ELEMENT:
                {
                alt111=4;
                }
                break;
            case SCHEMA_ATTRIBUTE:
                {
                alt111=5;
                }
                break;
            case PROCESSING_INSTRUCTION:
                {
                alt111=6;
                }
                break;
            case COMMENT:
                {
                alt111=7;
                }
                break;
            case TEXT:
                {
                alt111=8;
                }
                break;
            case NODE:
                {
                alt111=9;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 111, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;
            }

            } finally {dbg.exitDecision(111);}

            switch (alt111) {
                case 1 :
                    dbg.enterAlt(1);

                    // XQuery.g:192:12: documentTest
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(192,12);
                    pushFollow(FOLLOW_documentTest_in_kindTest2620);
                    documentTest496=documentTest();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, documentTest496.getTree());
                    dbg.location(192,28);


                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // XQuery.g:192:28: elementTest
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(192,28);
                    pushFollow(FOLLOW_elementTest_in_kindTest2625);
                    elementTest497=elementTest();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, elementTest497.getTree());
                    dbg.location(192,43);


                    }
                    break;
                case 3 :
                    dbg.enterAlt(3);

                    // XQuery.g:192:43: attributeTest
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(192,43);
                    pushFollow(FOLLOW_attributeTest_in_kindTest2630);
                    attributeTest498=attributeTest();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, attributeTest498.getTree());
                    dbg.location(192,60);


                    }
                    break;
                case 4 :
                    dbg.enterAlt(4);

                    // XQuery.g:192:60: schemaElementTest
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(192,60);
                    pushFollow(FOLLOW_schemaElementTest_in_kindTest2635);
                    schemaElementTest499=schemaElementTest();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, schemaElementTest499.getTree());
                    dbg.location(192,81);


                    }
                    break;
                case 5 :
                    dbg.enterAlt(5);

                    // XQuery.g:192:81: schemaAttributeTest
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(192,81);
                    pushFollow(FOLLOW_schemaAttributeTest_in_kindTest2640);
                    schemaAttributeTest500=schemaAttributeTest();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, schemaAttributeTest500.getTree());
                    dbg.location(193,6);


                    }
                    break;
                case 6 :
                    dbg.enterAlt(6);

                    // XQuery.g:193:6: pITest
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(193,6);
                    pushFollow(FOLLOW_pITest_in_kindTest2649);
                    pITest501=pITest();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, pITest501.getTree());
                    dbg.location(193,16);


                    }
                    break;
                case 7 :
                    dbg.enterAlt(7);

                    // XQuery.g:193:16: commentTest
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(193,16);
                    pushFollow(FOLLOW_commentTest_in_kindTest2654);
                    commentTest502=commentTest();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, commentTest502.getTree());
                    dbg.location(193,31);


                    }
                    break;
                case 8 :
                    dbg.enterAlt(8);

                    // XQuery.g:193:31: textTest
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(193,31);
                    pushFollow(FOLLOW_textTest_in_kindTest2659);
                    textTest503=textTest();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, textTest503.getTree());
                    dbg.location(193,43);


                    }
                    break;
                case 9 :
                    dbg.enterAlt(9);

                    // XQuery.g:193:43: anyKindTest
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(193,43);
                    pushFollow(FOLLOW_anyKindTest_in_kindTest2664);
                    anyKindTest504=anyKindTest();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, anyKindTest504.getTree());
                    dbg.location(0,0);


                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(193, 55);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "kindTest");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end kindTest

    public static class anyKindTest_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start anyKindTest
    // XQuery.g:194:1: anyKindTest : NODE LPAREN RPAREN ;
    public final XQueryParser.anyKindTest_return anyKindTest() throws RecognitionException {
        XQueryParser.anyKindTest_return retval = new XQueryParser.anyKindTest_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token NODE505=null;
        Token LPAREN506=null;
        Token RPAREN507=null;

        Object NODE505_tree=null;
        Object LPAREN506_tree=null;
        Object RPAREN507_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "anyKindTest");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(194, 1);

        try {
            // XQuery.g:194:13: ( NODE LPAREN RPAREN )
            dbg.enterAlt(1);

            // XQuery.g:194:15: NODE LPAREN RPAREN
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(194,15);
            NODE505=(Token)input.LT(1);
            match(input,NODE,FOLLOW_NODE_in_anyKindTest2672); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            NODE505_tree = (Object)adaptor.create(NODE505);
            adaptor.addChild(root_0, NODE505_tree);
            }
            dbg.location(194,20);
            LPAREN506=(Token)input.LT(1);
            match(input,LPAREN,FOLLOW_LPAREN_in_anyKindTest2674); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            LPAREN506_tree = (Object)adaptor.create(LPAREN506);
            adaptor.addChild(root_0, LPAREN506_tree);
            }
            dbg.location(194,27);
            RPAREN507=(Token)input.LT(1);
            match(input,RPAREN,FOLLOW_RPAREN_in_anyKindTest2676); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            RPAREN507_tree = (Object)adaptor.create(RPAREN507);
            adaptor.addChild(root_0, RPAREN507_tree);
            }
            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(194, 34);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "anyKindTest");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end anyKindTest

    public static class documentTest_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start documentTest
    // XQuery.g:195:1: documentTest : DOCUMENT_NODE LPAREN ( elementTest | schemaElementTest )? RPAREN ;
    public final XQueryParser.documentTest_return documentTest() throws RecognitionException {
        XQueryParser.documentTest_return retval = new XQueryParser.documentTest_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token DOCUMENT_NODE508=null;
        Token LPAREN509=null;
        Token RPAREN512=null;
        XQueryParser.elementTest_return elementTest510 = null;

        XQueryParser.schemaElementTest_return schemaElementTest511 = null;


        Object DOCUMENT_NODE508_tree=null;
        Object LPAREN509_tree=null;
        Object RPAREN512_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "documentTest");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(195, 1);

        try {
            // XQuery.g:195:14: ( DOCUMENT_NODE LPAREN ( elementTest | schemaElementTest )? RPAREN )
            dbg.enterAlt(1);

            // XQuery.g:195:16: DOCUMENT_NODE LPAREN ( elementTest | schemaElementTest )? RPAREN
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(195,16);
            DOCUMENT_NODE508=(Token)input.LT(1);
            match(input,DOCUMENT_NODE,FOLLOW_DOCUMENT_NODE_in_documentTest2684); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            DOCUMENT_NODE508_tree = (Object)adaptor.create(DOCUMENT_NODE508);
            adaptor.addChild(root_0, DOCUMENT_NODE508_tree);
            }
            dbg.location(195,30);
            LPAREN509=(Token)input.LT(1);
            match(input,LPAREN,FOLLOW_LPAREN_in_documentTest2686); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            LPAREN509_tree = (Object)adaptor.create(LPAREN509);
            adaptor.addChild(root_0, LPAREN509_tree);
            }
            dbg.location(195,37);
            // XQuery.g:195:37: ( elementTest | schemaElementTest )?
            int alt112=3;
            try { dbg.enterSubRule(112);
            try { dbg.enterDecision(112);

            int LA112_0 = input.LA(1);

            if ( (LA112_0==ELEMENT) ) {
                alt112=1;
            }
            else if ( (LA112_0==SCHEMA_ELEMENT) ) {
                alt112=2;
            }
            } finally {dbg.exitDecision(112);}

            switch (alt112) {
                case 1 :
                    dbg.enterAlt(1);

                    // XQuery.g:195:38: elementTest
                    {
                    dbg.location(195,38);
                    pushFollow(FOLLOW_elementTest_in_documentTest2689);
                    elementTest510=elementTest();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, elementTest510.getTree());
                    dbg.location(195,52);


                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // XQuery.g:195:52: schemaElementTest
                    {
                    dbg.location(195,52);
                    pushFollow(FOLLOW_schemaElementTest_in_documentTest2693);
                    schemaElementTest511=schemaElementTest();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, schemaElementTest511.getTree());
                    dbg.location(195,69);


                    }
                    break;

            }
            } finally {dbg.exitSubRule(112);}

            dbg.location(195,72);
            RPAREN512=(Token)input.LT(1);
            match(input,RPAREN,FOLLOW_RPAREN_in_documentTest2697); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            RPAREN512_tree = (Object)adaptor.create(RPAREN512);
            adaptor.addChild(root_0, RPAREN512_tree);
            }
            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(195, 79);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "documentTest");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end documentTest

    public static class textTest_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start textTest
    // XQuery.g:196:1: textTest : TEXT LPAREN RPAREN ;
    public final XQueryParser.textTest_return textTest() throws RecognitionException {
        XQueryParser.textTest_return retval = new XQueryParser.textTest_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token TEXT513=null;
        Token LPAREN514=null;
        Token RPAREN515=null;

        Object TEXT513_tree=null;
        Object LPAREN514_tree=null;
        Object RPAREN515_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "textTest");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(196, 1);

        try {
            // XQuery.g:196:10: ( TEXT LPAREN RPAREN )
            dbg.enterAlt(1);

            // XQuery.g:196:12: TEXT LPAREN RPAREN
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(196,12);
            TEXT513=(Token)input.LT(1);
            match(input,TEXT,FOLLOW_TEXT_in_textTest2705); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            TEXT513_tree = (Object)adaptor.create(TEXT513);
            adaptor.addChild(root_0, TEXT513_tree);
            }
            dbg.location(196,17);
            LPAREN514=(Token)input.LT(1);
            match(input,LPAREN,FOLLOW_LPAREN_in_textTest2707); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            LPAREN514_tree = (Object)adaptor.create(LPAREN514);
            adaptor.addChild(root_0, LPAREN514_tree);
            }
            dbg.location(196,24);
            RPAREN515=(Token)input.LT(1);
            match(input,RPAREN,FOLLOW_RPAREN_in_textTest2709); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            RPAREN515_tree = (Object)adaptor.create(RPAREN515);
            adaptor.addChild(root_0, RPAREN515_tree);
            }
            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(196, 31);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "textTest");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end textTest

    public static class commentTest_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start commentTest
    // XQuery.g:197:1: commentTest : COMMENT LPAREN RPAREN ;
    public final XQueryParser.commentTest_return commentTest() throws RecognitionException {
        XQueryParser.commentTest_return retval = new XQueryParser.commentTest_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token COMMENT516=null;
        Token LPAREN517=null;
        Token RPAREN518=null;

        Object COMMENT516_tree=null;
        Object LPAREN517_tree=null;
        Object RPAREN518_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "commentTest");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(197, 1);

        try {
            // XQuery.g:197:13: ( COMMENT LPAREN RPAREN )
            dbg.enterAlt(1);

            // XQuery.g:197:15: COMMENT LPAREN RPAREN
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(197,15);
            COMMENT516=(Token)input.LT(1);
            match(input,COMMENT,FOLLOW_COMMENT_in_commentTest2717); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            COMMENT516_tree = (Object)adaptor.create(COMMENT516);
            adaptor.addChild(root_0, COMMENT516_tree);
            }
            dbg.location(197,23);
            LPAREN517=(Token)input.LT(1);
            match(input,LPAREN,FOLLOW_LPAREN_in_commentTest2719); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            LPAREN517_tree = (Object)adaptor.create(LPAREN517);
            adaptor.addChild(root_0, LPAREN517_tree);
            }
            dbg.location(197,30);
            RPAREN518=(Token)input.LT(1);
            match(input,RPAREN,FOLLOW_RPAREN_in_commentTest2721); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            RPAREN518_tree = (Object)adaptor.create(RPAREN518);
            adaptor.addChild(root_0, RPAREN518_tree);
            }
            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(197, 37);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "commentTest");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end commentTest

    public static class pITest_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start pITest
    // XQuery.g:198:1: pITest : PROCESSING_INSTRUCTION LPAREN ( ncName | StringLiteral )? RPAREN ;
    public final XQueryParser.pITest_return pITest() throws RecognitionException {
        XQueryParser.pITest_return retval = new XQueryParser.pITest_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token PROCESSING_INSTRUCTION519=null;
        Token LPAREN520=null;
        Token StringLiteral522=null;
        Token RPAREN523=null;
        XQueryParser.ncName_return ncName521 = null;


        Object PROCESSING_INSTRUCTION519_tree=null;
        Object LPAREN520_tree=null;
        Object StringLiteral522_tree=null;
        Object RPAREN523_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "pITest");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(198, 1);

        try {
            // XQuery.g:198:9: ( PROCESSING_INSTRUCTION LPAREN ( ncName | StringLiteral )? RPAREN )
            dbg.enterAlt(1);

            // XQuery.g:198:11: PROCESSING_INSTRUCTION LPAREN ( ncName | StringLiteral )? RPAREN
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(198,11);
            PROCESSING_INSTRUCTION519=(Token)input.LT(1);
            match(input,PROCESSING_INSTRUCTION,FOLLOW_PROCESSING_INSTRUCTION_in_pITest2730); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            PROCESSING_INSTRUCTION519_tree = (Object)adaptor.create(PROCESSING_INSTRUCTION519);
            adaptor.addChild(root_0, PROCESSING_INSTRUCTION519_tree);
            }
            dbg.location(198,34);
            LPAREN520=(Token)input.LT(1);
            match(input,LPAREN,FOLLOW_LPAREN_in_pITest2732); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            LPAREN520_tree = (Object)adaptor.create(LPAREN520);
            adaptor.addChild(root_0, LPAREN520_tree);
            }
            dbg.location(198,41);
            // XQuery.g:198:41: ( ncName | StringLiteral )?
            int alt113=3;
            try { dbg.enterSubRule(113);
            try { dbg.enterDecision(113);

            int LA113_0 = input.LA(1);

            if ( ((LA113_0>=XQUERY && LA113_0<=VERSION)||(LA113_0>=ENCODING && LA113_0<=NAMESPACE)||(LA113_0>=DECLARE && LA113_0<=COPY_NAMESPACES)||(LA113_0>=NO_PRESERVE && LA113_0<=CONSTRUCTION)||LA113_0==AS||(LA113_0>=RETURN && LA113_0<=STRICT)||(LA113_0>=CHILD && LA113_0<=ANCESTOR_OR_SELF)||(LA113_0>=DOCUMENT && LA113_0<=NCName)) ) {
                alt113=1;
            }
            else if ( (LA113_0==StringLiteral) ) {
                alt113=2;
            }
            } finally {dbg.exitDecision(113);}

            switch (alt113) {
                case 1 :
                    dbg.enterAlt(1);

                    // XQuery.g:198:42: ncName
                    {
                    dbg.location(198,42);
                    pushFollow(FOLLOW_ncName_in_pITest2735);
                    ncName521=ncName();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, ncName521.getTree());
                    dbg.location(198,51);


                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // XQuery.g:198:51: StringLiteral
                    {
                    dbg.location(198,51);
                    StringLiteral522=(Token)input.LT(1);
                    match(input,StringLiteral,FOLLOW_StringLiteral_in_pITest2739); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    StringLiteral522_tree = (Object)adaptor.create(StringLiteral522);
                    adaptor.addChild(root_0, StringLiteral522_tree);
                    }
                    dbg.location(198,64);


                    }
                    break;

            }
            } finally {dbg.exitSubRule(113);}

            dbg.location(198,67);
            RPAREN523=(Token)input.LT(1);
            match(input,RPAREN,FOLLOW_RPAREN_in_pITest2743); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            RPAREN523_tree = (Object)adaptor.create(RPAREN523);
            adaptor.addChild(root_0, RPAREN523_tree);
            }
            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(198, 74);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "pITest");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end pITest

    public static class attributeTest_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start attributeTest
    // XQuery.g:199:1: attributeTest : ATTRIBUTE LPAREN ( attribNameOrWildcard ( COMMA typeName )? )? RPAREN ;
    public final XQueryParser.attributeTest_return attributeTest() throws RecognitionException {
        XQueryParser.attributeTest_return retval = new XQueryParser.attributeTest_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token ATTRIBUTE524=null;
        Token LPAREN525=null;
        Token COMMA527=null;
        Token RPAREN529=null;
        XQueryParser.attribNameOrWildcard_return attribNameOrWildcard526 = null;

        XQueryParser.typeName_return typeName528 = null;


        Object ATTRIBUTE524_tree=null;
        Object LPAREN525_tree=null;
        Object COMMA527_tree=null;
        Object RPAREN529_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "attributeTest");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(199, 1);

        try {
            // XQuery.g:199:15: ( ATTRIBUTE LPAREN ( attribNameOrWildcard ( COMMA typeName )? )? RPAREN )
            dbg.enterAlt(1);

            // XQuery.g:199:17: ATTRIBUTE LPAREN ( attribNameOrWildcard ( COMMA typeName )? )? RPAREN
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(199,17);
            ATTRIBUTE524=(Token)input.LT(1);
            match(input,ATTRIBUTE,FOLLOW_ATTRIBUTE_in_attributeTest2751); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            ATTRIBUTE524_tree = (Object)adaptor.create(ATTRIBUTE524);
            adaptor.addChild(root_0, ATTRIBUTE524_tree);
            }
            dbg.location(199,27);
            LPAREN525=(Token)input.LT(1);
            match(input,LPAREN,FOLLOW_LPAREN_in_attributeTest2753); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            LPAREN525_tree = (Object)adaptor.create(LPAREN525);
            adaptor.addChild(root_0, LPAREN525_tree);
            }
            dbg.location(199,34);
            // XQuery.g:199:34: ( attribNameOrWildcard ( COMMA typeName )? )?
            int alt115=2;
            try { dbg.enterSubRule(115);
            try { dbg.enterDecision(115);

            int LA115_0 = input.LA(1);

            if ( ((LA115_0>=XQUERY && LA115_0<=VERSION)||(LA115_0>=ENCODING && LA115_0<=NAMESPACE)||(LA115_0>=DECLARE && LA115_0<=COPY_NAMESPACES)||(LA115_0>=NO_PRESERVE && LA115_0<=CONSTRUCTION)||LA115_0==AS||(LA115_0>=RETURN && LA115_0<=STRICT)||(LA115_0>=CHILD && LA115_0<=ANCESTOR_OR_SELF)||(LA115_0>=DOCUMENT && LA115_0<=NCName)||LA115_0==149) ) {
                alt115=1;
            }
            } finally {dbg.exitDecision(115);}

            switch (alt115) {
                case 1 :
                    dbg.enterAlt(1);

                    // XQuery.g:199:35: attribNameOrWildcard ( COMMA typeName )?
                    {
                    dbg.location(199,35);
                    pushFollow(FOLLOW_attribNameOrWildcard_in_attributeTest2756);
                    attribNameOrWildcard526=attribNameOrWildcard();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, attribNameOrWildcard526.getTree());
                    dbg.location(199,56);
                    // XQuery.g:199:56: ( COMMA typeName )?
                    int alt114=2;
                    try { dbg.enterSubRule(114);
                    try { dbg.enterDecision(114);

                    int LA114_0 = input.LA(1);

                    if ( (LA114_0==COMMA) ) {
                        alt114=1;
                    }
                    } finally {dbg.exitDecision(114);}

                    switch (alt114) {
                        case 1 :
                            dbg.enterAlt(1);

                            // XQuery.g:199:57: COMMA typeName
                            {
                            dbg.location(199,57);
                            COMMA527=(Token)input.LT(1);
                            match(input,COMMA,FOLLOW_COMMA_in_attributeTest2759); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            COMMA527_tree = (Object)adaptor.create(COMMA527);
                            adaptor.addChild(root_0, COMMA527_tree);
                            }
                            dbg.location(199,63);
                            pushFollow(FOLLOW_typeName_in_attributeTest2761);
                            typeName528=typeName();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, typeName528.getTree());
                            dbg.location(199,71);


                            }
                            break;

                    }
                    } finally {dbg.exitSubRule(114);}

                    dbg.location(199,73);


                    }
                    break;

            }
            } finally {dbg.exitSubRule(115);}

            dbg.location(199,76);
            RPAREN529=(Token)input.LT(1);
            match(input,RPAREN,FOLLOW_RPAREN_in_attributeTest2767); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            RPAREN529_tree = (Object)adaptor.create(RPAREN529);
            adaptor.addChild(root_0, RPAREN529_tree);
            }
            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(199, 83);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "attributeTest");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end attributeTest

    public static class attribNameOrWildcard_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start attribNameOrWildcard
    // XQuery.g:200:1: attribNameOrWildcard : ( attributeName | '*' );
    public final XQueryParser.attribNameOrWildcard_return attribNameOrWildcard() throws RecognitionException {
        XQueryParser.attribNameOrWildcard_return retval = new XQueryParser.attribNameOrWildcard_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal531=null;
        XQueryParser.attributeName_return attributeName530 = null;


        Object char_literal531_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "attribNameOrWildcard");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(200, 1);

        try {
            // XQuery.g:201:3: ( attributeName | '*' )
            int alt116=2;
            try { dbg.enterDecision(116);

            int LA116_0 = input.LA(1);

            if ( ((LA116_0>=XQUERY && LA116_0<=VERSION)||(LA116_0>=ENCODING && LA116_0<=NAMESPACE)||(LA116_0>=DECLARE && LA116_0<=COPY_NAMESPACES)||(LA116_0>=NO_PRESERVE && LA116_0<=CONSTRUCTION)||LA116_0==AS||(LA116_0>=RETURN && LA116_0<=STRICT)||(LA116_0>=CHILD && LA116_0<=ANCESTOR_OR_SELF)||(LA116_0>=DOCUMENT && LA116_0<=NCName)) ) {
                alt116=1;
            }
            else if ( (LA116_0==149) ) {
                alt116=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 116, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;
            }
            } finally {dbg.exitDecision(116);}

            switch (alt116) {
                case 1 :
                    dbg.enterAlt(1);

                    // XQuery.g:201:5: attributeName
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(201,5);
                    pushFollow(FOLLOW_attributeName_in_attribNameOrWildcard2778);
                    attributeName530=attributeName();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, attributeName530.getTree());
                    dbg.location(201,21);


                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // XQuery.g:201:21: '*'
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(201,21);
                    char_literal531=(Token)input.LT(1);
                    match(input,149,FOLLOW_149_in_attribNameOrWildcard2782); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal531_tree = (Object)adaptor.create(char_literal531);
                    adaptor.addChild(root_0, char_literal531_tree);
                    }
                    dbg.location(0,0);


                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(201, 25);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "attribNameOrWildcard");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end attribNameOrWildcard

    public static class schemaAttributeTest_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start schemaAttributeTest
    // XQuery.g:202:1: schemaAttributeTest : SCHEMA_ATTRIBUTE LPAREN attributeDeclaration RPAREN ;
    public final XQueryParser.schemaAttributeTest_return schemaAttributeTest() throws RecognitionException {
        XQueryParser.schemaAttributeTest_return retval = new XQueryParser.schemaAttributeTest_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token SCHEMA_ATTRIBUTE532=null;
        Token LPAREN533=null;
        Token RPAREN535=null;
        XQueryParser.attributeDeclaration_return attributeDeclaration534 = null;


        Object SCHEMA_ATTRIBUTE532_tree=null;
        Object LPAREN533_tree=null;
        Object RPAREN535_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "schemaAttributeTest");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(202, 1);

        try {
            // XQuery.g:203:3: ( SCHEMA_ATTRIBUTE LPAREN attributeDeclaration RPAREN )
            dbg.enterAlt(1);

            // XQuery.g:203:5: SCHEMA_ATTRIBUTE LPAREN attributeDeclaration RPAREN
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(203,5);
            SCHEMA_ATTRIBUTE532=(Token)input.LT(1);
            match(input,SCHEMA_ATTRIBUTE,FOLLOW_SCHEMA_ATTRIBUTE_in_schemaAttributeTest2792); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            SCHEMA_ATTRIBUTE532_tree = (Object)adaptor.create(SCHEMA_ATTRIBUTE532);
            adaptor.addChild(root_0, SCHEMA_ATTRIBUTE532_tree);
            }
            dbg.location(203,22);
            LPAREN533=(Token)input.LT(1);
            match(input,LPAREN,FOLLOW_LPAREN_in_schemaAttributeTest2794); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            LPAREN533_tree = (Object)adaptor.create(LPAREN533);
            adaptor.addChild(root_0, LPAREN533_tree);
            }
            dbg.location(203,29);
            pushFollow(FOLLOW_attributeDeclaration_in_schemaAttributeTest2796);
            attributeDeclaration534=attributeDeclaration();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, attributeDeclaration534.getTree());
            dbg.location(203,50);
            RPAREN535=(Token)input.LT(1);
            match(input,RPAREN,FOLLOW_RPAREN_in_schemaAttributeTest2798); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            RPAREN535_tree = (Object)adaptor.create(RPAREN535);
            adaptor.addChild(root_0, RPAREN535_tree);
            }
            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(203, 57);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "schemaAttributeTest");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end schemaAttributeTest

    public static class attributeDeclaration_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start attributeDeclaration
    // XQuery.g:204:1: attributeDeclaration : attributeName ;
    public final XQueryParser.attributeDeclaration_return attributeDeclaration() throws RecognitionException {
        XQueryParser.attributeDeclaration_return retval = new XQueryParser.attributeDeclaration_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        XQueryParser.attributeName_return attributeName536 = null;



        try { dbg.enterRule(getGrammarFileName(), "attributeDeclaration");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(204, 1);

        try {
            // XQuery.g:205:3: ( attributeName )
            dbg.enterAlt(1);

            // XQuery.g:205:5: attributeName
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(205,5);
            pushFollow(FOLLOW_attributeName_in_attributeDeclaration2808);
            attributeName536=attributeName();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, attributeName536.getTree());
            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(205, 19);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "attributeDeclaration");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end attributeDeclaration

    public static class elementTest_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start elementTest
    // XQuery.g:206:1: elementTest : ELEMENT LPAREN ( elementNameOrWildcard ( COMMA typeName ( '?' )? )? )? RPAREN ;
    public final XQueryParser.elementTest_return elementTest() throws RecognitionException {
        XQueryParser.elementTest_return retval = new XQueryParser.elementTest_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token ELEMENT537=null;
        Token LPAREN538=null;
        Token COMMA540=null;
        Token char_literal542=null;
        Token RPAREN543=null;
        XQueryParser.elementNameOrWildcard_return elementNameOrWildcard539 = null;

        XQueryParser.typeName_return typeName541 = null;


        Object ELEMENT537_tree=null;
        Object LPAREN538_tree=null;
        Object COMMA540_tree=null;
        Object char_literal542_tree=null;
        Object RPAREN543_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "elementTest");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(206, 1);

        try {
            // XQuery.g:206:13: ( ELEMENT LPAREN ( elementNameOrWildcard ( COMMA typeName ( '?' )? )? )? RPAREN )
            dbg.enterAlt(1);

            // XQuery.g:206:15: ELEMENT LPAREN ( elementNameOrWildcard ( COMMA typeName ( '?' )? )? )? RPAREN
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(206,15);
            ELEMENT537=(Token)input.LT(1);
            match(input,ELEMENT,FOLLOW_ELEMENT_in_elementTest2816); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            ELEMENT537_tree = (Object)adaptor.create(ELEMENT537);
            adaptor.addChild(root_0, ELEMENT537_tree);
            }
            dbg.location(206,23);
            LPAREN538=(Token)input.LT(1);
            match(input,LPAREN,FOLLOW_LPAREN_in_elementTest2818); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            LPAREN538_tree = (Object)adaptor.create(LPAREN538);
            adaptor.addChild(root_0, LPAREN538_tree);
            }
            dbg.location(206,30);
            // XQuery.g:206:30: ( elementNameOrWildcard ( COMMA typeName ( '?' )? )? )?
            int alt119=2;
            try { dbg.enterSubRule(119);
            try { dbg.enterDecision(119);

            int LA119_0 = input.LA(1);

            if ( ((LA119_0>=XQUERY && LA119_0<=VERSION)||(LA119_0>=ENCODING && LA119_0<=NAMESPACE)||(LA119_0>=DECLARE && LA119_0<=COPY_NAMESPACES)||(LA119_0>=NO_PRESERVE && LA119_0<=CONSTRUCTION)||LA119_0==AS||(LA119_0>=RETURN && LA119_0<=STRICT)||(LA119_0>=CHILD && LA119_0<=ANCESTOR_OR_SELF)||(LA119_0>=DOCUMENT && LA119_0<=NCName)||LA119_0==149) ) {
                alt119=1;
            }
            } finally {dbg.exitDecision(119);}

            switch (alt119) {
                case 1 :
                    dbg.enterAlt(1);

                    // XQuery.g:206:31: elementNameOrWildcard ( COMMA typeName ( '?' )? )?
                    {
                    dbg.location(206,31);
                    pushFollow(FOLLOW_elementNameOrWildcard_in_elementTest2821);
                    elementNameOrWildcard539=elementNameOrWildcard();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, elementNameOrWildcard539.getTree());
                    dbg.location(206,53);
                    // XQuery.g:206:53: ( COMMA typeName ( '?' )? )?
                    int alt118=2;
                    try { dbg.enterSubRule(118);
                    try { dbg.enterDecision(118);

                    int LA118_0 = input.LA(1);

                    if ( (LA118_0==COMMA) ) {
                        alt118=1;
                    }
                    } finally {dbg.exitDecision(118);}

                    switch (alt118) {
                        case 1 :
                            dbg.enterAlt(1);

                            // XQuery.g:206:54: COMMA typeName ( '?' )?
                            {
                            dbg.location(206,54);
                            COMMA540=(Token)input.LT(1);
                            match(input,COMMA,FOLLOW_COMMA_in_elementTest2824); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            COMMA540_tree = (Object)adaptor.create(COMMA540);
                            adaptor.addChild(root_0, COMMA540_tree);
                            }
                            dbg.location(206,60);
                            pushFollow(FOLLOW_typeName_in_elementTest2826);
                            typeName541=typeName();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, typeName541.getTree());
                            dbg.location(206,69);
                            // XQuery.g:206:69: ( '?' )?
                            int alt117=2;
                            try { dbg.enterSubRule(117);
                            try { dbg.enterDecision(117);

                            int LA117_0 = input.LA(1);

                            if ( (LA117_0==160) ) {
                                alt117=1;
                            }
                            } finally {dbg.exitDecision(117);}

                            switch (alt117) {
                                case 1 :
                                    dbg.enterAlt(1);

                                    // XQuery.g:206:69: '?'
                                    {
                                    dbg.location(206,69);
                                    char_literal542=(Token)input.LT(1);
                                    match(input,160,FOLLOW_160_in_elementTest2828); if (state.failed) return retval;
                                    if ( state.backtracking==0 ) {
                                    char_literal542_tree = (Object)adaptor.create(char_literal542);
                                    adaptor.addChild(root_0, char_literal542_tree);
                                    }
                                    dbg.location(206,69);


                                    }
                                    break;

                            }
                            } finally {dbg.exitSubRule(117);}

                            dbg.location(206,73);


                            }
                            break;

                    }
                    } finally {dbg.exitSubRule(118);}

                    dbg.location(206,75);


                    }
                    break;

            }
            } finally {dbg.exitSubRule(119);}

            dbg.location(206,78);
            RPAREN543=(Token)input.LT(1);
            match(input,RPAREN,FOLLOW_RPAREN_in_elementTest2835); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            RPAREN543_tree = (Object)adaptor.create(RPAREN543);
            adaptor.addChild(root_0, RPAREN543_tree);
            }
            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(206, 85);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "elementTest");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end elementTest

    public static class elementNameOrWildcard_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start elementNameOrWildcard
    // XQuery.g:207:1: elementNameOrWildcard : ( elementName | '*' );
    public final XQueryParser.elementNameOrWildcard_return elementNameOrWildcard() throws RecognitionException {
        XQueryParser.elementNameOrWildcard_return retval = new XQueryParser.elementNameOrWildcard_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal545=null;
        XQueryParser.elementName_return elementName544 = null;


        Object char_literal545_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "elementNameOrWildcard");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(207, 1);

        try {
            // XQuery.g:208:3: ( elementName | '*' )
            int alt120=2;
            try { dbg.enterDecision(120);

            int LA120_0 = input.LA(1);

            if ( ((LA120_0>=XQUERY && LA120_0<=VERSION)||(LA120_0>=ENCODING && LA120_0<=NAMESPACE)||(LA120_0>=DECLARE && LA120_0<=COPY_NAMESPACES)||(LA120_0>=NO_PRESERVE && LA120_0<=CONSTRUCTION)||LA120_0==AS||(LA120_0>=RETURN && LA120_0<=STRICT)||(LA120_0>=CHILD && LA120_0<=ANCESTOR_OR_SELF)||(LA120_0>=DOCUMENT && LA120_0<=NCName)) ) {
                alt120=1;
            }
            else if ( (LA120_0==149) ) {
                alt120=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 120, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;
            }
            } finally {dbg.exitDecision(120);}

            switch (alt120) {
                case 1 :
                    dbg.enterAlt(1);

                    // XQuery.g:208:5: elementName
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(208,5);
                    pushFollow(FOLLOW_elementName_in_elementNameOrWildcard2845);
                    elementName544=elementName();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, elementName544.getTree());
                    dbg.location(208,19);


                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // XQuery.g:208:19: '*'
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(208,19);
                    char_literal545=(Token)input.LT(1);
                    match(input,149,FOLLOW_149_in_elementNameOrWildcard2849); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal545_tree = (Object)adaptor.create(char_literal545);
                    adaptor.addChild(root_0, char_literal545_tree);
                    }
                    dbg.location(0,0);


                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(208, 23);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "elementNameOrWildcard");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end elementNameOrWildcard

    public static class schemaElementTest_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start schemaElementTest
    // XQuery.g:209:1: schemaElementTest : SCHEMA_ELEMENT LPAREN elementDeclaration RPAREN ;
    public final XQueryParser.schemaElementTest_return schemaElementTest() throws RecognitionException {
        XQueryParser.schemaElementTest_return retval = new XQueryParser.schemaElementTest_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token SCHEMA_ELEMENT546=null;
        Token LPAREN547=null;
        Token RPAREN549=null;
        XQueryParser.elementDeclaration_return elementDeclaration548 = null;


        Object SCHEMA_ELEMENT546_tree=null;
        Object LPAREN547_tree=null;
        Object RPAREN549_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "schemaElementTest");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(209, 1);

        try {
            // XQuery.g:210:3: ( SCHEMA_ELEMENT LPAREN elementDeclaration RPAREN )
            dbg.enterAlt(1);

            // XQuery.g:210:5: SCHEMA_ELEMENT LPAREN elementDeclaration RPAREN
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(210,5);
            SCHEMA_ELEMENT546=(Token)input.LT(1);
            match(input,SCHEMA_ELEMENT,FOLLOW_SCHEMA_ELEMENT_in_schemaElementTest2859); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            SCHEMA_ELEMENT546_tree = (Object)adaptor.create(SCHEMA_ELEMENT546);
            adaptor.addChild(root_0, SCHEMA_ELEMENT546_tree);
            }
            dbg.location(210,20);
            LPAREN547=(Token)input.LT(1);
            match(input,LPAREN,FOLLOW_LPAREN_in_schemaElementTest2861); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            LPAREN547_tree = (Object)adaptor.create(LPAREN547);
            adaptor.addChild(root_0, LPAREN547_tree);
            }
            dbg.location(210,27);
            pushFollow(FOLLOW_elementDeclaration_in_schemaElementTest2863);
            elementDeclaration548=elementDeclaration();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, elementDeclaration548.getTree());
            dbg.location(210,46);
            RPAREN549=(Token)input.LT(1);
            match(input,RPAREN,FOLLOW_RPAREN_in_schemaElementTest2865); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            RPAREN549_tree = (Object)adaptor.create(RPAREN549);
            adaptor.addChild(root_0, RPAREN549_tree);
            }
            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(210, 53);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "schemaElementTest");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end schemaElementTest

    public static class elementDeclaration_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start elementDeclaration
    // XQuery.g:211:1: elementDeclaration : elementName ;
    public final XQueryParser.elementDeclaration_return elementDeclaration() throws RecognitionException {
        XQueryParser.elementDeclaration_return retval = new XQueryParser.elementDeclaration_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        XQueryParser.elementName_return elementName550 = null;



        try { dbg.enterRule(getGrammarFileName(), "elementDeclaration");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(211, 1);

        try {
            // XQuery.g:212:3: ( elementName )
            dbg.enterAlt(1);

            // XQuery.g:212:5: elementName
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(212,5);
            pushFollow(FOLLOW_elementName_in_elementDeclaration2875);
            elementName550=elementName();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, elementName550.getTree());
            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(212, 17);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "elementDeclaration");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end elementDeclaration

    public static class attributeName_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start attributeName
    // XQuery.g:213:1: attributeName : qNameOrIdent ;
    public final XQueryParser.attributeName_return attributeName() throws RecognitionException {
        XQueryParser.attributeName_return retval = new XQueryParser.attributeName_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        XQueryParser.qNameOrIdent_return qNameOrIdent551 = null;



        try { dbg.enterRule(getGrammarFileName(), "attributeName");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(213, 1);

        try {
            // XQuery.g:213:15: ( qNameOrIdent )
            dbg.enterAlt(1);

            // XQuery.g:213:17: qNameOrIdent
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(213,17);
            pushFollow(FOLLOW_qNameOrIdent_in_attributeName2883);
            qNameOrIdent551=qNameOrIdent();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, qNameOrIdent551.getTree());
            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(213, 30);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "attributeName");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end attributeName

    public static class elementName_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start elementName
    // XQuery.g:214:1: elementName : qNameOrIdent ;
    public final XQueryParser.elementName_return elementName() throws RecognitionException {
        XQueryParser.elementName_return retval = new XQueryParser.elementName_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        XQueryParser.qNameOrIdent_return qNameOrIdent552 = null;



        try { dbg.enterRule(getGrammarFileName(), "elementName");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(214, 1);

        try {
            // XQuery.g:214:13: ( qNameOrIdent )
            dbg.enterAlt(1);

            // XQuery.g:214:15: qNameOrIdent
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(214,15);
            pushFollow(FOLLOW_qNameOrIdent_in_elementName2891);
            qNameOrIdent552=qNameOrIdent();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, qNameOrIdent552.getTree());
            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(214, 28);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "elementName");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end elementName

    public static class typeName_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start typeName
    // XQuery.g:215:1: typeName : qNameOrIdent ;
    public final XQueryParser.typeName_return typeName() throws RecognitionException {
        XQueryParser.typeName_return retval = new XQueryParser.typeName_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        XQueryParser.qNameOrIdent_return qNameOrIdent553 = null;



        try { dbg.enterRule(getGrammarFileName(), "typeName");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(215, 1);

        try {
            // XQuery.g:215:10: ( qNameOrIdent )
            dbg.enterAlt(1);

            // XQuery.g:215:12: qNameOrIdent
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(215,12);
            pushFollow(FOLLOW_qNameOrIdent_in_typeName2899);
            qNameOrIdent553=qNameOrIdent();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, qNameOrIdent553.getTree());
            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(215, 25);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "typeName");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end typeName

    public static class uRILiteral_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start uRILiteral
    // XQuery.g:216:1: uRILiteral : StringLiteral ;
    public final XQueryParser.uRILiteral_return uRILiteral() throws RecognitionException {
        XQueryParser.uRILiteral_return retval = new XQueryParser.uRILiteral_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token StringLiteral554=null;

        Object StringLiteral554_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "uRILiteral");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(216, 1);

        try {
            // XQuery.g:216:12: ( StringLiteral )
            dbg.enterAlt(1);

            // XQuery.g:216:14: StringLiteral
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(216,14);
            StringLiteral554=(Token)input.LT(1);
            match(input,StringLiteral,FOLLOW_StringLiteral_in_uRILiteral2907); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            StringLiteral554_tree = (Object)adaptor.create(StringLiteral554);
            adaptor.addChild(root_0, StringLiteral554_tree);
            }
            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(216, 28);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "uRILiteral");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end uRILiteral

    public static class piTarget_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start piTarget
    // XQuery.g:217:1: piTarget : ncName ;
    public final XQueryParser.piTarget_return piTarget() throws RecognitionException {
        XQueryParser.piTarget_return retval = new XQueryParser.piTarget_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        XQueryParser.ncName_return ncName555 = null;



        try { dbg.enterRule(getGrammarFileName(), "piTarget");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(217, 1);

        try {
            // XQuery.g:217:10: ( ncName )
            dbg.enterAlt(1);

            // XQuery.g:217:12: ncName
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(217,12);
            pushFollow(FOLLOW_ncName_in_piTarget2915);
            ncName555=ncName();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, ncName555.getTree());
            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(217, 18);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "piTarget");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end piTarget

    public static class qNameOrIdent_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start qNameOrIdent
    // XQuery.g:218:1: qNameOrIdent : ncName ( COLON ncName )? ;
    public final XQueryParser.qNameOrIdent_return qNameOrIdent() throws RecognitionException {
        XQueryParser.qNameOrIdent_return retval = new XQueryParser.qNameOrIdent_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token COLON557=null;
        XQueryParser.ncName_return ncName556 = null;

        XQueryParser.ncName_return ncName558 = null;


        Object COLON557_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "qNameOrIdent");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(218, 1);

        try {
            // XQuery.g:218:14: ( ncName ( COLON ncName )? )
            dbg.enterAlt(1);

            // XQuery.g:218:16: ncName ( COLON ncName )?
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(218,16);
            pushFollow(FOLLOW_ncName_in_qNameOrIdent2922);
            ncName556=ncName();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, ncName556.getTree());
            dbg.location(218,23);
            // XQuery.g:218:23: ( COLON ncName )?
            int alt121=2;
            try { dbg.enterSubRule(121);
            try { dbg.enterDecision(121);

            int LA121_0 = input.LA(1);

            if ( (LA121_0==COLON) ) {
                alt121=1;
            }
            } finally {dbg.exitDecision(121);}

            switch (alt121) {
                case 1 :
                    dbg.enterAlt(1);

                    // XQuery.g:218:24: COLON ncName
                    {
                    dbg.location(218,24);
                    COLON557=(Token)input.LT(1);
                    match(input,COLON,FOLLOW_COLON_in_qNameOrIdent2925); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    COLON557_tree = (Object)adaptor.create(COLON557);
                    adaptor.addChild(root_0, COLON557_tree);
                    }
                    dbg.location(218,30);
                    pushFollow(FOLLOW_ncName_in_qNameOrIdent2927);
                    ncName558=ncName();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, ncName558.getTree());
                    dbg.location(218,36);


                    }
                    break;

            }
            } finally {dbg.exitSubRule(121);}

            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(218, 38);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "qNameOrIdent");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end qNameOrIdent

    public static class funcName_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start funcName
    // XQuery.g:219:1: funcName : funcKeyword ( COLON funcKeyword )? ;
    public final XQueryParser.funcName_return funcName() throws RecognitionException {
        XQueryParser.funcName_return retval = new XQueryParser.funcName_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token COLON560=null;
        XQueryParser.funcKeyword_return funcKeyword559 = null;

        XQueryParser.funcKeyword_return funcKeyword561 = null;


        Object COLON560_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "funcName");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(219, 1);

        try {
            // XQuery.g:219:10: ( funcKeyword ( COLON funcKeyword )? )
            dbg.enterAlt(1);

            // XQuery.g:219:12: funcKeyword ( COLON funcKeyword )?
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(219,12);
            pushFollow(FOLLOW_funcKeyword_in_funcName2936);
            funcKeyword559=funcKeyword();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, funcKeyword559.getTree());
            dbg.location(219,24);
            // XQuery.g:219:24: ( COLON funcKeyword )?
            int alt122=2;
            try { dbg.enterSubRule(122);
            try { dbg.enterDecision(122);

            int LA122_0 = input.LA(1);

            if ( (LA122_0==COLON) ) {
                alt122=1;
            }
            } finally {dbg.exitDecision(122);}

            switch (alt122) {
                case 1 :
                    dbg.enterAlt(1);

                    // XQuery.g:219:25: COLON funcKeyword
                    {
                    dbg.location(219,25);
                    COLON560=(Token)input.LT(1);
                    match(input,COLON,FOLLOW_COLON_in_funcName2939); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    COLON560_tree = (Object)adaptor.create(COLON560);
                    adaptor.addChild(root_0, COLON560_tree);
                    }
                    dbg.location(219,31);
                    pushFollow(FOLLOW_funcKeyword_in_funcName2941);
                    funcKeyword561=funcKeyword();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, funcKeyword561.getTree());
                    dbg.location(219,42);


                    }
                    break;

            }
            } finally {dbg.exitSubRule(122);}

            dbg.location(0,0);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(219, 44);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "funcName");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end funcName

    public static class ncName_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start ncName
    // XQuery.g:220:1: ncName : ( NCName | ANCESTOR | ANCESTOR_OR_SELF | AND | AS | ASCENDING | AT | ATTRIBUTE | BASE_URI | BOUNDARY_SPACE | BY | CASE | CAST | CASTABLE | CHILD | COLLATION | COMMENT | CONSTRUCTION | COPY_NAMESPACES | DECLARE | DEFAULT | DESCENDANT | DESCENDANT_OR_SELF | DESCENDING | DIV | DOCUMENT | DOCUMENT_NODE | ELEMENT | ELSE | EMPTY | EMPTY_SEQUENCE | ENCODING | EQ | EVERY | EXCEPT | EXTERNAL | FOLLOWING | FOLLOWING_SIBLING | FOR | FUNCTION | GE | GREATEST | GT | IDIV | IF | IMPORT | IN | INHERIT | INSTANCE | INTERSECT | IS | ITEM | LAX | LE | LEAST | LET | LT | MOD | MODULE | NAMESPACE | NE | NO_INHERIT | NO_PRESERVE | NODE | OF | OPTION | OR | ORDER | ORDERED | ORDERING | PARENT | PRECEDING | PRECEDING_SIBLING | PRESERVE | PROCESSING_INSTRUCTION | RETURN | SATISFIES | SCHEMA | SCHEMA_ATTRIBUTE | SCHEMA_ELEMENT | SELF | SOME | STABLE | STRICT | STRIP | TEXT | THEN | TO | TREAT | TYPESWITCH | UNION | UNORDERED | VALIDATE | VARIABLE | VERSION | WHERE | XQUERY );
    public final XQueryParser.ncName_return ncName() throws RecognitionException {
        XQueryParser.ncName_return retval = new XQueryParser.ncName_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set562=null;

        Object set562_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "ncName");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(220, 1);

        try {
            // XQuery.g:220:9: ( NCName | ANCESTOR | ANCESTOR_OR_SELF | AND | AS | ASCENDING | AT | ATTRIBUTE | BASE_URI | BOUNDARY_SPACE | BY | CASE | CAST | CASTABLE | CHILD | COLLATION | COMMENT | CONSTRUCTION | COPY_NAMESPACES | DECLARE | DEFAULT | DESCENDANT | DESCENDANT_OR_SELF | DESCENDING | DIV | DOCUMENT | DOCUMENT_NODE | ELEMENT | ELSE | EMPTY | EMPTY_SEQUENCE | ENCODING | EQ | EVERY | EXCEPT | EXTERNAL | FOLLOWING | FOLLOWING_SIBLING | FOR | FUNCTION | GE | GREATEST | GT | IDIV | IF | IMPORT | IN | INHERIT | INSTANCE | INTERSECT | IS | ITEM | LAX | LE | LEAST | LET | LT | MOD | MODULE | NAMESPACE | NE | NO_INHERIT | NO_PRESERVE | NODE | OF | OPTION | OR | ORDER | ORDERED | ORDERING | PARENT | PRECEDING | PRECEDING_SIBLING | PRESERVE | PROCESSING_INSTRUCTION | RETURN | SATISFIES | SCHEMA | SCHEMA_ATTRIBUTE | SCHEMA_ELEMENT | SELF | SOME | STABLE | STRICT | STRIP | TEXT | THEN | TO | TREAT | TYPESWITCH | UNION | UNORDERED | VALIDATE | VARIABLE | VERSION | WHERE | XQUERY )
            dbg.enterAlt(1);

            // XQuery.g:
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(220,9);
            set562=(Token)input.LT(1);
            if ( (input.LA(1)>=XQUERY && input.LA(1)<=VERSION)||(input.LA(1)>=ENCODING && input.LA(1)<=NAMESPACE)||(input.LA(1)>=DECLARE && input.LA(1)<=COPY_NAMESPACES)||(input.LA(1)>=NO_PRESERVE && input.LA(1)<=CONSTRUCTION)||input.LA(1)==AS||(input.LA(1)>=RETURN && input.LA(1)<=STRICT)||(input.LA(1)>=CHILD && input.LA(1)<=ANCESTOR_OR_SELF)||(input.LA(1)>=DOCUMENT && input.LA(1)<=NCName) ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set562));
                state.errorRecovery=false;state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                dbg.recognitionException(mse);
                throw mse;
            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(220, 971);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "ncName");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end ncName

    public static class funcKeyword_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start funcKeyword
    // XQuery.g:221:1: funcKeyword : ( NCName | ANCESTOR | ANCESTOR_OR_SELF | AND | AS | ASCENDING | AT | BASE_URI | BOUNDARY_SPACE | BY | CASE | CAST | CASTABLE | CHILD | COLLATION | CONSTRUCTION | COPY_NAMESPACES | DECLARE | DEFAULT | DESCENDANT | DESCENDANT_OR_SELF | DESCENDING | DIV | DOCUMENT | ELSE | EMPTY | ENCODING | EQ | EVERY | EXCEPT | EXTERNAL | FOLLOWING | FOLLOWING_SIBLING | FOR | FUNCTION | GE | GREATEST | GT | IDIV | IMPORT | IN | INHERIT | INSTANCE | INTERSECT | IS | LAX | LE | LEAST | LET | LT | MOD | MODULE | NAMESPACE | NE | NO_INHERIT | NO_PRESERVE | OF | OPTION | OR | ORDER | ORDERED | ORDERING | PARENT | PRECEDING | PRECEDING_SIBLING | PRESERVE | RETURN | SATISFIES | SCHEMA | SELF | SOME | STABLE | STRICT | STRIP | THEN | TO | TREAT | UNION | UNORDERED | VALIDATE | VARIABLE | VERSION | WHERE | XQUERY );
    public final XQueryParser.funcKeyword_return funcKeyword() throws RecognitionException {
        XQueryParser.funcKeyword_return retval = new XQueryParser.funcKeyword_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set563=null;

        Object set563_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "funcKeyword");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(221, 1);

        try {
            // XQuery.g:221:13: ( NCName | ANCESTOR | ANCESTOR_OR_SELF | AND | AS | ASCENDING | AT | BASE_URI | BOUNDARY_SPACE | BY | CASE | CAST | CASTABLE | CHILD | COLLATION | CONSTRUCTION | COPY_NAMESPACES | DECLARE | DEFAULT | DESCENDANT | DESCENDANT_OR_SELF | DESCENDING | DIV | DOCUMENT | ELSE | EMPTY | ENCODING | EQ | EVERY | EXCEPT | EXTERNAL | FOLLOWING | FOLLOWING_SIBLING | FOR | FUNCTION | GE | GREATEST | GT | IDIV | IMPORT | IN | INHERIT | INSTANCE | INTERSECT | IS | LAX | LE | LEAST | LET | LT | MOD | MODULE | NAMESPACE | NE | NO_INHERIT | NO_PRESERVE | OF | OPTION | OR | ORDER | ORDERED | ORDERING | PARENT | PRECEDING | PRECEDING_SIBLING | PRESERVE | RETURN | SATISFIES | SCHEMA | SELF | SOME | STABLE | STRICT | STRIP | THEN | TO | TREAT | UNION | UNORDERED | VALIDATE | VARIABLE | VERSION | WHERE | XQUERY )
            dbg.enterAlt(1);

            // XQuery.g:
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(221,13);
            set563=(Token)input.LT(1);
            if ( (input.LA(1)>=XQUERY && input.LA(1)<=VERSION)||(input.LA(1)>=ENCODING && input.LA(1)<=NAMESPACE)||(input.LA(1)>=DECLARE && input.LA(1)<=DEFAULT)||(input.LA(1)>=FUNCTION && input.LA(1)<=COPY_NAMESPACES)||(input.LA(1)>=NO_PRESERVE && input.LA(1)<=CONSTRUCTION)||input.LA(1)==AS||(input.LA(1)>=RETURN && input.LA(1)<=SATISFIES)||input.LA(1)==CASE||(input.LA(1)>=THEN && input.LA(1)<=STRICT)||(input.LA(1)>=CHILD && input.LA(1)<=DESCENDANT)||(input.LA(1)>=SELF && input.LA(1)<=ANCESTOR_OR_SELF)||input.LA(1)==DOCUMENT||input.LA(1)==NCName ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set563));
                state.errorRecovery=false;state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                dbg.recognitionException(mse);
                throw mse;
            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(221, 810);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "funcKeyword");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end funcKeyword

    // $ANTLR start synpred1_XQuery
    public final void synpred1_XQuery_fragment() throws RecognitionException {   
        // XQuery.g:118:12: ( SLASH relativePathExpr )
        dbg.enterAlt(1);

        // XQuery.g:118:13: SLASH relativePathExpr
        {
        dbg.location(118,13);
        match(input,SLASH,FOLLOW_SLASH_in_synpred1_XQuery1561); if (state.failed) return ;
        dbg.location(118,19);
        pushFollow(FOLLOW_relativePathExpr_in_synpred1_XQuery1563);
        relativePathExpr();

        state._fsp--;
        if (state.failed) return ;
        dbg.location(118,35);


        }
    }
    // $ANTLR end synpred1_XQuery

    // $ANTLR start synpred2_XQuery
    public final void synpred2_XQuery_fragment() throws RecognitionException {   
        // XQuery.g:187:16: ( occurrenceIndicator )
        dbg.enterAlt(1);

        // XQuery.g:187:17: occurrenceIndicator
        {
        dbg.location(187,17);
        pushFollow(FOLLOW_occurrenceIndicator_in_synpred2_XQuery2555);
        occurrenceIndicator();

        state._fsp--;
        if (state.failed) return ;
        dbg.location(187,36);


        }
    }
    // $ANTLR end synpred2_XQuery

    // Delegated rules

    public final boolean synpred1_XQuery() {
        state.backtracking++;
        dbg.beginBacktrack(state.backtracking);
        int start = input.mark();
        try {
            synpred1_XQuery_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        dbg.endBacktrack(state.backtracking, success);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred2_XQuery() {
        state.backtracking++;
        dbg.beginBacktrack(state.backtracking);
        int start = input.mark();
        try {
            synpred2_XQuery_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        dbg.endBacktrack(state.backtracking, success);
        state.backtracking--;
        state.failed=false;
        return success;
    }


    protected DFA8 dfa8 = new DFA8(this);
    protected DFA25 dfa25 = new DFA25(this);
    protected DFA66 dfa66 = new DFA66(this);
    protected DFA68 dfa68 = new DFA68(this);
    protected DFA70 dfa70 = new DFA70(this);
    protected DFA75 dfa75 = new DFA75(this);
    protected DFA80 dfa80 = new DFA80(this);
    protected DFA108 dfa108 = new DFA108(this);
    protected DFA110 dfa110 = new DFA110(this);
    static final String DFA8_eotS =
        "\12\uffff";
    static final String DFA8_eofS =
        "\12\uffff";
    static final String DFA8_minS =
        "\1\32\1\33\1\uffff\1\45\6\uffff";
    static final String DFA8_maxS =
        "\1\32\1\65\1\uffff\1\56\6\uffff";
    static final String DFA8_acceptS =
        "\2\uffff\1\1\1\uffff\1\3\1\4\1\5\1\7\1\2\1\6";
    static final String DFA8_specialS =
        "\12\uffff}>";
    static final String[] DFA8_transitionS = {
            "\1\1",
            "\1\2\2\uffff\1\3\3\uffff\1\6\6\uffff\1\7\5\uffff\1\4\5\uffff"+
            "\1\5",
            "",
            "\1\11\10\uffff\1\10",
            "",
            "",
            "",
            "",
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
            return "37:1: setter : ( boundarySpaceDecl | defaultCollationDecl | baseURIDecl | constructionDecl | orderingModeDecl | emptyOrderDecl | copyNamespacesDecl );";
        }
        public void error(NoViableAltException nvae) {
            dbg.recognitionException(nvae);
        }
    }
    static final String DFA25_eotS =
        "\13\uffff";
    static final String DFA25_eofS =
        "\1\uffff\5\6\5\uffff";
    static final String DFA25_minS =
        "\1\6\5\5\5\uffff";
    static final String DFA25_maxS =
        "\1\u009f\5\u009b\5\uffff";
    static final String DFA25_acceptS =
        "\6\uffff\1\5\1\1\1\2\1\3\1\4";
    static final String DFA25_specialS =
        "\13\uffff}>";
    static final String[] DFA25_transitionS = {
            "\1\6\13\uffff\6\6\2\uffff\20\6\1\uffff\13\6\1\uffff\2\6\2\uffff"+
            "\1\6\1\1\1\6\1\2\5\6\2\3\1\6\1\4\1\6\1\5\51\6\3\uffff\5\6\3"+
            "\uffff\13\6\11\uffff\4\6\7\uffff\3\6",
            "\2\6\21\uffff\2\6\4\uffff\1\6\6\uffff\2\6\3\uffff\1\6\3\uffff"+
            "\1\6\7\uffff\1\6\1\uffff\1\6\1\uffff\3\6\1\uffff\2\6\1\uffff"+
            "\3\6\2\uffff\1\6\1\uffff\1\6\2\uffff\13\6\1\uffff\12\6\4\uffff"+
            "\2\6\14\uffff\3\6\34\uffff\1\7\11\6",
            "\2\6\21\uffff\2\6\4\uffff\1\6\6\uffff\2\6\3\uffff\1\6\3\uffff"+
            "\1\6\7\uffff\1\6\1\uffff\1\6\1\uffff\3\6\1\uffff\2\6\1\uffff"+
            "\3\6\2\uffff\1\6\1\uffff\1\6\2\uffff\13\6\1\uffff\12\6\4\uffff"+
            "\2\6\14\uffff\3\6\34\uffff\1\7\11\6",
            "\2\6\21\uffff\2\6\4\uffff\1\6\6\uffff\2\6\3\uffff\1\6\3\uffff"+
            "\1\6\7\uffff\1\6\1\uffff\1\6\1\uffff\3\6\1\uffff\2\6\1\uffff"+
            "\3\6\2\uffff\1\6\1\uffff\1\6\2\uffff\13\6\1\uffff\12\6\4\uffff"+
            "\2\6\14\uffff\3\6\34\uffff\1\10\11\6",
            "\2\6\21\uffff\2\6\4\uffff\1\6\6\uffff\2\6\3\uffff\1\6\3\uffff"+
            "\1\6\7\uffff\1\6\1\uffff\1\11\1\uffff\3\6\1\uffff\2\6\1\uffff"+
            "\3\6\2\uffff\1\6\1\uffff\1\6\2\uffff\13\6\1\uffff\12\6\4\uffff"+
            "\2\6\14\uffff\3\6\35\uffff\11\6",
            "\2\6\21\uffff\2\6\4\uffff\1\6\6\uffff\2\6\3\uffff\1\6\3\uffff"+
            "\1\6\7\uffff\1\6\1\uffff\1\12\1\uffff\3\6\1\uffff\2\6\1\uffff"+
            "\3\6\2\uffff\1\6\1\uffff\1\6\2\uffff\13\6\1\uffff\12\6\4\uffff"+
            "\2\6\14\uffff\3\6\35\uffff\11\6",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA25_eot = DFA.unpackEncodedString(DFA25_eotS);
    static final short[] DFA25_eof = DFA.unpackEncodedString(DFA25_eofS);
    static final char[] DFA25_min = DFA.unpackEncodedStringToUnsignedChars(DFA25_minS);
    static final char[] DFA25_max = DFA.unpackEncodedStringToUnsignedChars(DFA25_maxS);
    static final short[] DFA25_accept = DFA.unpackEncodedString(DFA25_acceptS);
    static final short[] DFA25_special = DFA.unpackEncodedString(DFA25_specialS);
    static final short[][] DFA25_transition;

    static {
        int numStates = DFA25_transitionS.length;
        DFA25_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA25_transition[i] = DFA.unpackEncodedString(DFA25_transitionS[i]);
        }
    }

    class DFA25 extends DFA {

        public DFA25(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 25;
            this.eot = DFA25_eot;
            this.eof = DFA25_eof;
            this.min = DFA25_min;
            this.max = DFA25_max;
            this.accept = DFA25_accept;
            this.special = DFA25_special;
            this.transition = DFA25_transition;
        }
        public String getDescription() {
            return "71:1: exprSingle : ( fLWORExpr | quantifiedExpr | typeswitchExpr | ifExpr | orExpr );";
        }
        public void error(NoViableAltException nvae) {
            dbg.recognitionException(nvae);
        }
    }
    static final String DFA66_eotS =
        "\51\uffff";
    static final String DFA66_eofS =
        "\51\uffff";
    static final String DFA66_minS =
        "\1\6\1\0\47\uffff";
    static final String DFA66_maxS =
        "\1\u009f\1\0\47\uffff";
    static final String DFA66_acceptS =
        "\2\uffff\1\3\1\4\43\uffff\1\1\1\2";
    static final String DFA66_specialS =
        "\1\uffff\1\0\47\uffff}>";
    static final String[] DFA66_transitionS = {
            "\1\3\13\uffff\6\3\2\uffff\20\3\1\uffff\13\3\1\uffff\2\3\2\uffff"+
            "\51\3\1\uffff\1\1\1\2\14\3\3\uffff\5\3\3\uffff\13\3\11\uffff"+
            "\1\3\2\uffff\1\3\7\uffff\3\3",
            "\1\uffff",
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
            ""
    };

    static final short[] DFA66_eot = DFA.unpackEncodedString(DFA66_eotS);
    static final short[] DFA66_eof = DFA.unpackEncodedString(DFA66_eofS);
    static final char[] DFA66_min = DFA.unpackEncodedStringToUnsignedChars(DFA66_minS);
    static final char[] DFA66_max = DFA.unpackEncodedStringToUnsignedChars(DFA66_maxS);
    static final short[] DFA66_accept = DFA.unpackEncodedString(DFA66_acceptS);
    static final short[] DFA66_special = DFA.unpackEncodedString(DFA66_specialS);
    static final short[][] DFA66_transition;

    static {
        int numStates = DFA66_transitionS.length;
        DFA66_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA66_transition[i] = DFA.unpackEncodedString(DFA66_transitionS[i]);
        }
    }

    class DFA66 extends DFA {

        public DFA66(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 66;
            this.eot = DFA66_eot;
            this.eof = DFA66_eof;
            this.min = DFA66_min;
            this.max = DFA66_max;
            this.accept = DFA66_accept;
            this.special = DFA66_special;
            this.transition = DFA66_transition;
        }
        public String getDescription() {
            return "118:1: pathExpr : ( ( SLASH relativePathExpr )=> ( SLASH relativePathExpr ) | SLASH | ( SLASH_SLASH relativePathExpr ) | relativePathExpr );";
        }
        public void error(NoViableAltException nvae) {
            dbg.recognitionException(nvae);
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA66_1 = input.LA(1);

                         
                        int index66_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred1_XQuery()) ) {s = 39;}

                        else if ( (true) ) {s = 40;}

                         
                        input.seek(index66_1);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 66, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA68_eotS =
        "\144\uffff";
    static final String DFA68_eofS =
        "\2\uffff\16\20\1\uffff\6\20\114\uffff\1\20";
    static final String DFA68_minS =
        "\1\6\1\uffff\16\5\1\uffff\6\5\1\22\3\67\1\71\11\6\1\71\1\45\1\6"+
        "\2\71\1\46\1\47\1\24\1\6\1\71\1\22\1\6\3\67\1\71\11\6\1\71\1\45"+
        "\1\6\2\71\1\46\1\47\1\24\1\6\1\71\1\22\1\6\3\67\1\71\11\6\1\71\1"+
        "\45\1\6\2\71\1\46\1\47\1\24\1\6\1\71\1\22\1\6\1\5";
    static final String DFA68_maxS =
        "\1\u009f\1\uffff\3\u009b\1\u009c\1\u009b\1\u009c\3\u009b\5\u009c"+
        "\1\uffff\5\u009c\1\u009b\1\u0095\4\163\11\u009f\2\163\1\u009f\2"+
        "\u0092\3\163\1\u009f\2\u0092\1\u009f\4\163\11\u009f\2\163\1\u009f"+
        "\2\u0092\3\163\1\u009f\2\u0092\1\u009f\3\71\1\126\11\u009f\1\100"+
        "\1\71\1\u009f\2\u0092\1\73\2\71\1\u009f\2\u0092\1\u009f\1\u009b";
    static final String DFA68_acceptS =
        "\1\uffff\1\1\16\uffff\1\2\123\uffff";
    static final String DFA68_specialS =
        "\144\uffff}>";
    static final String[] DFA68_transitionS = {
            "\1\1\13\uffff\2\26\1\1\3\26\2\uffff\5\26\1\6\3\26\1\2\1\3\5"+
            "\26\1\uffff\13\26\1\uffff\1\26\1\1\2\uffff\14\26\1\20\1\26\1"+
            "\20\32\26\3\uffff\1\17\1\21\1\7\1\22\1\23\1\24\1\25\1\5\1\13"+
            "\1\14\1\15\1\16\3\uffff\5\1\3\uffff\1\4\1\10\1\11\1\12\6\20"+
            "\1\26\11\uffff\1\1\2\uffff\1\20\7\uffff\2\20\1\1",
            "",
            "\2\20\21\uffff\2\20\4\uffff\1\20\6\uffff\2\20\3\uffff\1\20\3"+
            "\uffff\1\20\7\uffff\1\20\1\uffff\2\1\3\20\1\uffff\2\20\1\uffff"+
            "\3\20\2\uffff\1\20\1\uffff\1\20\2\uffff\13\20\1\uffff\12\20"+
            "\4\uffff\2\20\14\uffff\1\27\2\20\35\uffff\11\20",
            "\2\20\21\uffff\2\20\4\uffff\1\20\6\uffff\2\20\3\uffff\1\20\3"+
            "\uffff\1\20\7\uffff\1\20\1\uffff\2\1\3\20\1\uffff\2\20\1\uffff"+
            "\3\20\2\uffff\1\20\1\uffff\1\20\2\uffff\13\20\1\uffff\12\20"+
            "\4\uffff\2\20\14\uffff\1\27\2\20\35\uffff\11\20",
            "\2\20\21\uffff\2\20\4\uffff\1\20\6\uffff\2\20\3\uffff\1\20\3"+
            "\uffff\1\20\7\uffff\1\20\1\uffff\2\1\3\20\1\uffff\2\20\1\uffff"+
            "\3\20\2\uffff\1\20\1\uffff\1\20\2\uffff\13\20\1\uffff\12\20"+
            "\4\uffff\2\20\14\uffff\1\27\2\20\35\uffff\11\20",
            "\2\20\21\uffff\2\20\4\uffff\1\20\6\uffff\2\20\3\uffff\1\20\3"+
            "\uffff\1\20\7\uffff\1\20\1\uffff\1\1\1\uffff\3\20\1\uffff\2"+
            "\20\1\uffff\3\20\2\uffff\1\20\1\uffff\1\20\2\uffff\13\20\1\uffff"+
            "\12\20\4\uffff\2\20\14\uffff\1\27\2\20\35\uffff\12\20",
            "\2\20\13\uffff\2\1\1\uffff\3\1\2\20\4\1\1\56\6\1\1\45\1\53\3"+
            "\1\1\20\3\1\1\54\7\1\1\20\1\1\1\20\1\1\1\20\1\47\1\50\1\1\1"+
            "\51\1\44\1\1\1\46\2\52\2\1\1\55\1\1\1\57\2\1\1\60\1\43\1\42"+
            "\1\37\3\36\1\35\2\34\1\33\1\1\1\32\1\31\1\30\6\40\1\41\3\1\1"+
            "\uffff\2\20\14\1\3\20\10\uffff\13\1\12\uffff\11\20",
            "\2\20\13\uffff\2\1\1\uffff\3\1\2\20\4\1\1\107\6\1\1\76\1\104"+
            "\3\1\1\20\3\1\1\105\7\1\1\20\1\1\1\20\1\1\1\20\1\100\1\101\1"+
            "\1\1\102\1\75\1\1\1\77\2\103\2\1\1\106\1\1\1\110\2\1\1\111\1"+
            "\74\1\73\1\70\3\67\1\66\2\65\1\64\1\1\1\63\1\62\1\61\6\71\1"+
            "\72\3\1\1\uffff\2\20\14\1\3\20\10\uffff\13\1\12\uffff\12\20",
            "\2\20\21\uffff\2\20\4\uffff\1\20\6\uffff\2\20\3\uffff\1\20\3"+
            "\uffff\1\20\7\uffff\1\20\1\uffff\1\20\1\1\3\20\1\uffff\2\20"+
            "\1\uffff\3\20\2\uffff\1\20\1\uffff\1\20\2\uffff\13\20\1\uffff"+
            "\12\20\4\uffff\2\20\14\uffff\3\20\35\uffff\11\20",
            "\2\20\21\uffff\2\20\4\uffff\1\20\6\uffff\2\20\3\uffff\1\20\3"+
            "\uffff\1\20\7\uffff\1\20\1\uffff\1\20\1\1\3\20\1\uffff\2\20"+
            "\1\uffff\3\20\2\uffff\1\20\1\uffff\1\20\2\uffff\13\20\1\uffff"+
            "\12\20\4\uffff\2\20\14\uffff\3\20\35\uffff\11\20",
            "\2\20\13\uffff\2\1\1\uffff\3\1\2\20\4\1\1\140\6\1\1\127\1\135"+
            "\3\1\1\20\3\1\1\136\7\1\1\20\1\1\1\20\1\1\1\20\1\131\1\132\1"+
            "\1\1\133\1\126\1\1\1\130\2\134\2\1\1\137\1\1\1\141\2\1\1\142"+
            "\1\125\1\124\1\121\3\120\1\117\2\116\1\115\1\1\1\114\1\113\1"+
            "\112\6\122\1\123\3\1\1\uffff\2\20\14\1\3\20\10\uffff\13\1\12"+
            "\uffff\11\20",
            "\2\20\21\uffff\2\20\4\uffff\1\20\6\uffff\2\20\3\uffff\1\20\3"+
            "\uffff\1\20\7\uffff\1\20\1\uffff\1\1\1\uffff\3\20\1\uffff\2"+
            "\20\1\uffff\3\20\2\uffff\1\20\1\uffff\1\20\2\uffff\13\20\1\uffff"+
            "\12\20\4\uffff\2\20\14\uffff\1\27\2\20\35\uffff\12\20",
            "\2\20\21\uffff\2\20\4\uffff\1\20\6\uffff\2\20\3\uffff\1\20\3"+
            "\uffff\1\20\7\uffff\1\20\1\uffff\1\1\1\uffff\3\20\1\uffff\2"+
            "\20\1\uffff\3\20\2\uffff\1\20\1\uffff\1\20\2\uffff\13\20\1\uffff"+
            "\12\20\4\uffff\2\20\14\uffff\1\27\2\20\35\uffff\12\20",
            "\2\20\21\uffff\2\20\4\uffff\1\20\6\uffff\2\20\3\uffff\1\20\3"+
            "\uffff\1\20\7\uffff\1\20\1\uffff\1\1\1\uffff\3\20\1\uffff\2"+
            "\20\1\uffff\3\20\2\uffff\1\20\1\uffff\1\20\2\uffff\13\20\1\uffff"+
            "\12\20\4\uffff\2\20\14\uffff\1\27\2\20\35\uffff\12\20",
            "\2\20\21\uffff\2\20\4\uffff\1\20\6\uffff\2\20\3\uffff\1\20\3"+
            "\uffff\1\20\7\uffff\1\20\1\uffff\1\1\1\uffff\3\20\1\uffff\2"+
            "\20\1\uffff\3\20\2\uffff\1\20\1\uffff\1\20\2\uffff\13\20\1\uffff"+
            "\12\20\4\uffff\2\20\14\uffff\1\27\2\20\35\uffff\12\20",
            "\2\20\21\uffff\2\20\4\uffff\1\20\6\uffff\2\20\3\uffff\1\20\3"+
            "\uffff\1\20\7\uffff\1\20\1\uffff\1\1\1\uffff\3\20\1\uffff\2"+
            "\20\1\uffff\3\20\2\uffff\1\20\1\uffff\1\20\2\uffff\13\20\1\uffff"+
            "\12\20\4\uffff\2\20\14\uffff\1\27\2\20\35\uffff\12\20",
            "",
            "\2\20\21\uffff\2\20\4\uffff\1\20\6\uffff\2\20\3\uffff\1\20\3"+
            "\uffff\1\20\7\uffff\1\20\1\uffff\1\1\1\uffff\3\20\1\uffff\2"+
            "\20\1\uffff\3\20\2\uffff\1\20\1\uffff\1\20\2\uffff\13\20\1\uffff"+
            "\12\20\4\uffff\2\20\14\uffff\1\27\2\20\35\uffff\12\20",
            "\2\20\21\uffff\2\20\4\uffff\1\20\6\uffff\2\20\3\uffff\1\20\3"+
            "\uffff\1\20\7\uffff\1\20\1\uffff\1\1\1\uffff\3\20\1\uffff\2"+
            "\20\1\uffff\3\20\2\uffff\1\20\1\uffff\1\20\2\uffff\13\20\1\uffff"+
            "\12\20\4\uffff\2\20\14\uffff\1\27\2\20\35\uffff\12\20",
            "\2\20\21\uffff\2\20\4\uffff\1\20\6\uffff\2\20\3\uffff\1\20\3"+
            "\uffff\1\20\7\uffff\1\20\1\uffff\1\1\1\uffff\3\20\1\uffff\2"+
            "\20\1\uffff\3\20\2\uffff\1\20\1\uffff\1\20\2\uffff\13\20\1\uffff"+
            "\12\20\4\uffff\2\20\14\uffff\1\27\2\20\35\uffff\12\20",
            "\2\20\21\uffff\2\20\4\uffff\1\20\6\uffff\2\20\3\uffff\1\20\3"+
            "\uffff\1\20\7\uffff\1\20\1\uffff\1\1\1\uffff\3\20\1\uffff\2"+
            "\20\1\uffff\3\20\2\uffff\1\20\1\uffff\1\20\2\uffff\13\20\1\uffff"+
            "\12\20\4\uffff\2\20\14\uffff\1\27\2\20\35\uffff\12\20",
            "\2\20\21\uffff\2\20\4\uffff\1\20\6\uffff\2\20\3\uffff\1\20\3"+
            "\uffff\1\20\7\uffff\1\20\1\uffff\1\1\1\uffff\3\20\1\uffff\2"+
            "\20\1\uffff\3\20\2\uffff\1\20\1\uffff\1\20\2\uffff\13\20\1\uffff"+
            "\12\20\4\uffff\2\20\14\uffff\1\27\2\20\35\uffff\12\20",
            "\2\20\21\uffff\2\20\4\uffff\1\20\6\uffff\2\20\3\uffff\1\20\3"+
            "\uffff\1\20\7\uffff\1\20\1\uffff\1\1\1\uffff\3\20\1\uffff\2"+
            "\20\1\uffff\3\20\2\uffff\1\20\1\uffff\1\20\2\uffff\13\20\1\uffff"+
            "\12\20\4\uffff\2\20\14\uffff\1\27\2\20\35\uffff\11\20",
            "\2\143\1\uffff\3\143\2\uffff\5\143\1\20\12\143\1\uffff\13\143"+
            "\1\uffff\1\143\3\uffff\14\143\1\20\1\143\1\20\32\143\3\uffff"+
            "\2\143\1\20\11\143\13\uffff\1\143\11\20\1\143\14\uffff\1\20",
            "\1\20\1\uffff\1\1\71\uffff\1\1",
            "\1\20\1\uffff\1\1\71\uffff\1\1",
            "\1\20\1\uffff\1\1\71\uffff\1\1",
            "\1\1\34\uffff\1\20\34\uffff\1\1",
            "\1\20\13\uffff\6\20\2\uffff\20\20\1\uffff\13\20\1\uffff\2\20"+
            "\1\1\1\uffff\70\20\1\1\2\uffff\5\20\3\uffff\13\20\11\uffff\4"+
            "\20\7\uffff\3\20",
            "\1\20\13\uffff\6\20\2\uffff\20\20\1\uffff\13\20\1\uffff\2\20"+
            "\1\1\1\uffff\70\20\1\1\2\uffff\5\20\3\uffff\13\20\11\uffff\4"+
            "\20\7\uffff\3\20",
            "\1\20\13\uffff\6\20\2\uffff\20\20\1\uffff\13\20\1\uffff\2\20"+
            "\1\1\1\uffff\70\20\1\1\2\uffff\5\20\3\uffff\13\20\11\uffff\4"+
            "\20\7\uffff\3\20",
            "\1\20\13\uffff\6\20\2\uffff\20\20\1\uffff\13\20\1\uffff\2\20"+
            "\1\1\1\uffff\70\20\1\1\2\uffff\5\20\3\uffff\13\20\11\uffff\4"+
            "\20\7\uffff\3\20",
            "\1\20\13\uffff\6\20\2\uffff\20\20\1\uffff\13\20\1\uffff\2\20"+
            "\1\1\1\uffff\70\20\1\1\2\uffff\5\20\3\uffff\13\20\11\uffff\4"+
            "\20\7\uffff\3\20",
            "\1\20\13\uffff\6\20\2\uffff\20\20\1\uffff\13\20\1\uffff\2\20"+
            "\1\1\1\uffff\70\20\1\1\2\uffff\5\20\3\uffff\13\20\11\uffff\4"+
            "\20\7\uffff\3\20",
            "\1\20\13\uffff\6\20\2\uffff\20\20\1\uffff\13\20\1\uffff\2\20"+
            "\1\1\1\uffff\70\20\1\1\2\uffff\5\20\3\uffff\13\20\11\uffff\4"+
            "\20\7\uffff\3\20",
            "\1\20\13\uffff\6\20\2\uffff\20\20\1\uffff\13\20\1\uffff\2\20"+
            "\1\1\1\uffff\70\20\1\1\2\uffff\5\20\3\uffff\13\20\11\uffff\4"+
            "\20\7\uffff\3\20",
            "\1\20\13\uffff\6\20\2\uffff\20\20\1\uffff\13\20\1\uffff\2\20"+
            "\1\1\1\uffff\70\20\1\1\2\uffff\5\20\3\uffff\13\20\11\uffff\4"+
            "\20\7\uffff\3\20",
            "\1\1\6\uffff\1\20\62\uffff\1\1",
            "\1\20\23\uffff\1\1\71\uffff\1\1",
            "\1\20\13\uffff\6\20\2\uffff\20\20\1\uffff\13\20\1\uffff\2\20"+
            "\1\1\1\uffff\70\20\1\1\2\uffff\5\20\3\uffff\13\20\11\uffff\4"+
            "\20\7\uffff\3\20",
            "\1\1\71\uffff\1\1\36\uffff\1\20",
            "\1\1\71\uffff\1\1\36\uffff\1\20",
            "\1\20\3\uffff\1\20\3\uffff\1\20\12\uffff\1\1\1\uffff\1\20\67"+
            "\uffff\1\1",
            "\2\20\20\uffff\1\1\71\uffff\1\1",
            "\1\20\44\uffff\1\1\71\uffff\1\1",
            "\1\20\13\uffff\6\20\2\uffff\20\20\1\uffff\13\20\1\uffff\2\20"+
            "\1\1\1\uffff\70\20\1\1\2\uffff\5\20\3\uffff\13\20\11\uffff\4"+
            "\20\7\uffff\3\20",
            "\1\1\1\uffff\1\20\67\uffff\1\1\36\uffff\1\20",
            "\2\20\1\uffff\3\20\2\uffff\20\20\1\uffff\13\20\1\uffff\1\20"+
            "\1\uffff\1\1\1\uffff\51\20\3\uffff\14\20\1\1\12\uffff\13\20"+
            "\11\uffff\1\20",
            "\1\20\13\uffff\6\20\2\uffff\20\20\1\uffff\13\20\1\uffff\2\20"+
            "\1\1\1\uffff\70\20\1\1\2\uffff\5\20\3\uffff\13\20\11\uffff\4"+
            "\20\7\uffff\3\20",
            "\1\20\1\uffff\1\1\71\uffff\1\1",
            "\1\20\1\uffff\1\1\71\uffff\1\1",
            "\1\20\1\uffff\1\1\71\uffff\1\1",
            "\1\1\34\uffff\1\20\34\uffff\1\1",
            "\1\20\13\uffff\6\20\2\uffff\20\20\1\uffff\13\20\1\uffff\2\20"+
            "\1\1\1\uffff\70\20\1\1\2\uffff\5\20\3\uffff\13\20\11\uffff\4"+
            "\20\7\uffff\3\20",
            "\1\20\13\uffff\6\20\2\uffff\20\20\1\uffff\13\20\1\uffff\2\20"+
            "\1\1\1\uffff\70\20\1\1\2\uffff\5\20\3\uffff\13\20\11\uffff\4"+
            "\20\7\uffff\3\20",
            "\1\20\13\uffff\6\20\2\uffff\20\20\1\uffff\13\20\1\uffff\2\20"+
            "\1\1\1\uffff\70\20\1\1\2\uffff\5\20\3\uffff\13\20\11\uffff\4"+
            "\20\7\uffff\3\20",
            "\1\20\13\uffff\6\20\2\uffff\20\20\1\uffff\13\20\1\uffff\2\20"+
            "\1\1\1\uffff\70\20\1\1\2\uffff\5\20\3\uffff\13\20\11\uffff\4"+
            "\20\7\uffff\3\20",
            "\1\20\13\uffff\6\20\2\uffff\20\20\1\uffff\13\20\1\uffff\2\20"+
            "\1\1\1\uffff\70\20\1\1\2\uffff\5\20\3\uffff\13\20\11\uffff\4"+
            "\20\7\uffff\3\20",
            "\1\20\13\uffff\6\20\2\uffff\20\20\1\uffff\13\20\1\uffff\2\20"+
            "\1\1\1\uffff\70\20\1\1\2\uffff\5\20\3\uffff\13\20\11\uffff\4"+
            "\20\7\uffff\3\20",
            "\1\20\13\uffff\6\20\2\uffff\20\20\1\uffff\13\20\1\uffff\2\20"+
            "\1\1\1\uffff\70\20\1\1\2\uffff\5\20\3\uffff\13\20\11\uffff\4"+
            "\20\7\uffff\3\20",
            "\1\20\13\uffff\6\20\2\uffff\20\20\1\uffff\13\20\1\uffff\2\20"+
            "\1\1\1\uffff\70\20\1\1\2\uffff\5\20\3\uffff\13\20\11\uffff\4"+
            "\20\7\uffff\3\20",
            "\1\20\13\uffff\6\20\2\uffff\20\20\1\uffff\13\20\1\uffff\2\20"+
            "\1\1\1\uffff\70\20\1\1\2\uffff\5\20\3\uffff\13\20\11\uffff\4"+
            "\20\7\uffff\3\20",
            "\1\1\6\uffff\1\20\62\uffff\1\1",
            "\1\20\23\uffff\1\1\71\uffff\1\1",
            "\1\20\13\uffff\6\20\2\uffff\20\20\1\uffff\13\20\1\uffff\2\20"+
            "\1\1\1\uffff\70\20\1\1\2\uffff\5\20\3\uffff\13\20\11\uffff\4"+
            "\20\7\uffff\3\20",
            "\1\1\71\uffff\1\1\36\uffff\1\20",
            "\1\1\71\uffff\1\1\36\uffff\1\20",
            "\1\20\3\uffff\1\20\3\uffff\1\20\12\uffff\1\1\1\uffff\1\20\67"+
            "\uffff\1\1",
            "\2\20\20\uffff\1\1\71\uffff\1\1",
            "\1\20\44\uffff\1\1\71\uffff\1\1",
            "\1\20\13\uffff\6\20\2\uffff\20\20\1\uffff\13\20\1\uffff\2\20"+
            "\1\1\1\uffff\70\20\1\1\2\uffff\5\20\3\uffff\13\20\11\uffff\4"+
            "\20\7\uffff\3\20",
            "\1\1\1\uffff\1\20\67\uffff\1\1\36\uffff\1\20",
            "\2\20\1\uffff\3\20\2\uffff\20\20\1\uffff\13\20\1\uffff\1\20"+
            "\1\uffff\1\1\1\uffff\51\20\3\uffff\14\20\1\1\12\uffff\13\20"+
            "\11\uffff\1\20",
            "\1\20\13\uffff\6\20\2\uffff\20\20\1\uffff\13\20\1\uffff\2\20"+
            "\1\1\1\uffff\70\20\1\1\2\uffff\5\20\3\uffff\13\20\11\uffff\4"+
            "\20\7\uffff\3\20",
            "\1\20\1\uffff\1\1",
            "\1\20\1\uffff\1\1",
            "\1\20\1\uffff\1\1",
            "\1\1\34\uffff\1\20",
            "\1\20\13\uffff\6\20\2\uffff\20\20\1\uffff\13\20\1\uffff\2\20"+
            "\1\1\1\uffff\70\20\3\uffff\5\20\3\uffff\13\20\11\uffff\4\20"+
            "\7\uffff\3\20",
            "\1\20\13\uffff\6\20\2\uffff\20\20\1\uffff\13\20\1\uffff\2\20"+
            "\1\1\1\uffff\70\20\3\uffff\5\20\3\uffff\13\20\11\uffff\4\20"+
            "\7\uffff\3\20",
            "\1\20\13\uffff\6\20\2\uffff\20\20\1\uffff\13\20\1\uffff\2\20"+
            "\1\1\1\uffff\70\20\3\uffff\5\20\3\uffff\13\20\11\uffff\4\20"+
            "\7\uffff\3\20",
            "\1\20\13\uffff\6\20\2\uffff\20\20\1\uffff\13\20\1\uffff\2\20"+
            "\1\1\1\uffff\70\20\3\uffff\5\20\3\uffff\13\20\11\uffff\4\20"+
            "\7\uffff\3\20",
            "\1\20\13\uffff\6\20\2\uffff\20\20\1\uffff\13\20\1\uffff\2\20"+
            "\1\1\1\uffff\70\20\3\uffff\5\20\3\uffff\13\20\11\uffff\4\20"+
            "\7\uffff\3\20",
            "\1\20\13\uffff\6\20\2\uffff\20\20\1\uffff\13\20\1\uffff\2\20"+
            "\1\1\1\uffff\70\20\3\uffff\5\20\3\uffff\13\20\11\uffff\4\20"+
            "\7\uffff\3\20",
            "\1\20\13\uffff\6\20\2\uffff\20\20\1\uffff\13\20\1\uffff\2\20"+
            "\1\1\1\uffff\70\20\3\uffff\5\20\3\uffff\13\20\11\uffff\4\20"+
            "\7\uffff\3\20",
            "\1\20\13\uffff\6\20\2\uffff\20\20\1\uffff\13\20\1\uffff\2\20"+
            "\1\1\1\uffff\70\20\3\uffff\5\20\3\uffff\13\20\11\uffff\4\20"+
            "\7\uffff\3\20",
            "\1\20\13\uffff\6\20\2\uffff\20\20\1\uffff\13\20\1\uffff\2\20"+
            "\1\1\1\uffff\70\20\3\uffff\5\20\3\uffff\13\20\11\uffff\4\20"+
            "\7\uffff\3\20",
            "\1\1\6\uffff\1\20",
            "\1\20\23\uffff\1\1",
            "\1\20\13\uffff\6\20\2\uffff\20\20\1\uffff\13\20\1\uffff\2\20"+
            "\1\1\1\uffff\70\20\3\uffff\5\20\3\uffff\13\20\11\uffff\4\20"+
            "\7\uffff\3\20",
            "\1\1\130\uffff\1\20",
            "\1\1\130\uffff\1\20",
            "\1\20\3\uffff\1\20\3\uffff\1\20\12\uffff\1\1\1\uffff\1\20",
            "\2\20\20\uffff\1\1",
            "\1\20\44\uffff\1\1",
            "\1\20\13\uffff\6\20\2\uffff\20\20\1\uffff\13\20\1\uffff\2\20"+
            "\1\1\1\uffff\70\20\3\uffff\5\20\3\uffff\13\20\11\uffff\4\20"+
            "\7\uffff\3\20",
            "\1\1\1\uffff\1\20\126\uffff\1\20",
            "\2\20\1\uffff\3\20\2\uffff\20\20\1\uffff\13\20\1\uffff\1\20"+
            "\1\uffff\1\1\1\uffff\51\20\3\uffff\14\20\13\uffff\13\20\11\uffff"+
            "\1\20",
            "\1\20\13\uffff\6\20\2\uffff\20\20\1\uffff\13\20\1\uffff\2\20"+
            "\1\1\1\uffff\70\20\3\uffff\5\20\3\uffff\13\20\11\uffff\4\20"+
            "\7\uffff\3\20",
            "\2\20\21\uffff\2\20\4\uffff\1\20\6\uffff\2\20\3\uffff\1\20\3"+
            "\uffff\1\20\7\uffff\1\20\1\uffff\1\1\1\uffff\3\20\1\uffff\2"+
            "\20\1\uffff\3\20\2\uffff\1\20\1\uffff\1\20\2\uffff\13\20\1\uffff"+
            "\12\20\4\uffff\2\20\15\uffff\2\20\35\uffff\11\20"
    };

    static final short[] DFA68_eot = DFA.unpackEncodedString(DFA68_eotS);
    static final short[] DFA68_eof = DFA.unpackEncodedString(DFA68_eofS);
    static final char[] DFA68_min = DFA.unpackEncodedStringToUnsignedChars(DFA68_minS);
    static final char[] DFA68_max = DFA.unpackEncodedStringToUnsignedChars(DFA68_maxS);
    static final short[] DFA68_accept = DFA.unpackEncodedString(DFA68_acceptS);
    static final short[] DFA68_special = DFA.unpackEncodedString(DFA68_specialS);
    static final short[][] DFA68_transition;

    static {
        int numStates = DFA68_transitionS.length;
        DFA68_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA68_transition[i] = DFA.unpackEncodedString(DFA68_transitionS[i]);
        }
    }

    class DFA68 extends DFA {

        public DFA68(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 68;
            this.eot = DFA68_eot;
            this.eof = DFA68_eof;
            this.min = DFA68_min;
            this.max = DFA68_max;
            this.accept = DFA68_accept;
            this.special = DFA68_special;
            this.transition = DFA68_transition;
        }
        public String getDescription() {
            return "122:1: stepExpr : ( filterExpr | axisStep );";
        }
        public void error(NoViableAltException nvae) {
            dbg.recognitionException(nvae);
        }
    }
    static final String DFA70_eotS =
        "\12\uffff";
    static final String DFA70_eofS =
        "\1\uffff\7\10\2\uffff";
    static final String DFA70_minS =
        "\1\22\7\5\2\uffff";
    static final String DFA70_maxS =
        "\1\u009d\7\u009c\2\uffff";
    static final String DFA70_acceptS =
        "\10\uffff\1\2\1\1";
    static final String DFA70_specialS =
        "\12\uffff}>";
    static final String[] DFA70_transitionS = {
            "\2\10\1\uffff\3\10\2\uffff\20\10\1\uffff\13\10\1\uffff\1\10"+
            "\3\uffff\51\10\3\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\5\10\13\uffff"+
            "\13\10\14\uffff\1\10\7\uffff\1\10",
            "\2\10\21\uffff\2\10\4\uffff\1\10\6\uffff\2\10\3\uffff\1\10\3"+
            "\uffff\1\10\7\uffff\1\10\3\uffff\3\10\1\uffff\2\10\1\uffff\3"+
            "\10\2\uffff\1\10\1\uffff\1\10\2\uffff\13\10\1\uffff\12\10\4"+
            "\uffff\2\10\14\uffff\3\10\35\uffff\11\10\1\11",
            "\2\10\21\uffff\2\10\4\uffff\1\10\6\uffff\2\10\3\uffff\1\10\3"+
            "\uffff\1\10\7\uffff\1\10\3\uffff\3\10\1\uffff\2\10\1\uffff\3"+
            "\10\2\uffff\1\10\1\uffff\1\10\2\uffff\13\10\1\uffff\12\10\4"+
            "\uffff\2\10\14\uffff\3\10\35\uffff\11\10\1\11",
            "\2\10\21\uffff\2\10\4\uffff\1\10\6\uffff\2\10\3\uffff\1\10\3"+
            "\uffff\1\10\7\uffff\1\10\1\uffff\1\10\1\uffff\3\10\1\uffff\2"+
            "\10\1\uffff\3\10\2\uffff\1\10\1\uffff\1\10\2\uffff\13\10\1\uffff"+
            "\12\10\4\uffff\2\10\14\uffff\3\10\35\uffff\11\10\1\11",
            "\2\10\21\uffff\2\10\4\uffff\1\10\6\uffff\2\10\3\uffff\1\10\3"+
            "\uffff\1\10\7\uffff\1\10\3\uffff\3\10\1\uffff\2\10\1\uffff\3"+
            "\10\2\uffff\1\10\1\uffff\1\10\2\uffff\13\10\1\uffff\12\10\4"+
            "\uffff\2\10\14\uffff\3\10\35\uffff\11\10\1\11",
            "\2\10\21\uffff\2\10\4\uffff\1\10\6\uffff\2\10\3\uffff\1\10\3"+
            "\uffff\1\10\7\uffff\1\10\3\uffff\3\10\1\uffff\2\10\1\uffff\3"+
            "\10\2\uffff\1\10\1\uffff\1\10\2\uffff\13\10\1\uffff\12\10\4"+
            "\uffff\2\10\14\uffff\3\10\35\uffff\11\10\1\11",
            "\2\10\21\uffff\2\10\4\uffff\1\10\6\uffff\2\10\3\uffff\1\10\3"+
            "\uffff\1\10\7\uffff\1\10\3\uffff\3\10\1\uffff\2\10\1\uffff\3"+
            "\10\2\uffff\1\10\1\uffff\1\10\2\uffff\13\10\1\uffff\12\10\4"+
            "\uffff\2\10\14\uffff\3\10\35\uffff\11\10\1\11",
            "\2\10\21\uffff\2\10\4\uffff\1\10\6\uffff\2\10\3\uffff\1\10\3"+
            "\uffff\1\10\7\uffff\1\10\3\uffff\3\10\1\uffff\2\10\1\uffff\3"+
            "\10\2\uffff\1\10\1\uffff\1\10\2\uffff\13\10\1\uffff\12\10\4"+
            "\uffff\2\10\14\uffff\3\10\35\uffff\11\10\1\11",
            "",
            ""
    };

    static final short[] DFA70_eot = DFA.unpackEncodedString(DFA70_eotS);
    static final short[] DFA70_eof = DFA.unpackEncodedString(DFA70_eofS);
    static final char[] DFA70_min = DFA.unpackEncodedStringToUnsignedChars(DFA70_minS);
    static final char[] DFA70_max = DFA.unpackEncodedStringToUnsignedChars(DFA70_maxS);
    static final short[] DFA70_accept = DFA.unpackEncodedString(DFA70_acceptS);
    static final short[] DFA70_special = DFA.unpackEncodedString(DFA70_specialS);
    static final short[][] DFA70_transition;

    static {
        int numStates = DFA70_transitionS.length;
        DFA70_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA70_transition[i] = DFA.unpackEncodedString(DFA70_transitionS[i]);
        }
    }

    class DFA70 extends DFA {

        public DFA70(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 70;
            this.eot = DFA70_eot;
            this.eof = DFA70_eof;
            this.min = DFA70_min;
            this.max = DFA70_max;
            this.accept = DFA70_accept;
            this.special = DFA70_special;
            this.transition = DFA70_transition;
        }
        public String getDescription() {
            return "124:1: forwardStep : ( ( forwardAxis nodeTest ) | abbrevForwardStep );";
        }
        public void error(NoViableAltException nvae) {
            dbg.recognitionException(nvae);
        }
    }
    static final String DFA75_eotS =
        "\14\uffff";
    static final String DFA75_eofS =
        "\1\uffff\11\12\2\uffff";
    static final String DFA75_minS =
        "\1\22\11\5\2\uffff";
    static final String DFA75_maxS =
        "\1\u0095\11\u009b\2\uffff";
    static final String DFA75_acceptS =
        "\12\uffff\1\2\1\1";
    static final String DFA75_specialS =
        "\14\uffff}>";
    static final String[] DFA75_transitionS = {
            "\2\12\1\uffff\3\12\2\uffff\5\12\1\2\12\12\1\uffff\13\12\1\uffff"+
            "\1\12\3\uffff\51\12\3\uffff\2\12\1\3\11\12\13\uffff\1\12\1\10"+
            "\1\7\1\6\2\12\1\11\1\1\1\5\1\4\1\12\14\uffff\1\12",
            "\2\12\21\uffff\2\12\4\uffff\1\12\6\uffff\2\12\3\uffff\1\12\3"+
            "\uffff\1\12\7\uffff\1\12\1\uffff\1\13\1\uffff\3\12\1\uffff\2"+
            "\12\1\uffff\3\12\2\uffff\1\12\1\uffff\1\12\2\uffff\13\12\1\uffff"+
            "\12\12\4\uffff\2\12\14\uffff\3\12\35\uffff\11\12",
            "\2\12\21\uffff\2\12\4\uffff\1\12\6\uffff\2\12\3\uffff\1\12\3"+
            "\uffff\1\12\7\uffff\1\12\1\uffff\1\13\1\uffff\3\12\1\uffff\2"+
            "\12\1\uffff\3\12\2\uffff\1\12\1\uffff\1\12\2\uffff\13\12\1\uffff"+
            "\12\12\4\uffff\2\12\14\uffff\3\12\35\uffff\11\12",
            "\2\12\21\uffff\2\12\4\uffff\1\12\6\uffff\2\12\3\uffff\1\12\3"+
            "\uffff\1\12\7\uffff\1\12\1\uffff\1\13\1\uffff\3\12\1\uffff\2"+
            "\12\1\uffff\3\12\2\uffff\1\12\1\uffff\1\12\2\uffff\13\12\1\uffff"+
            "\12\12\4\uffff\2\12\14\uffff\3\12\35\uffff\11\12",
            "\2\12\21\uffff\2\12\4\uffff\1\12\6\uffff\2\12\3\uffff\1\12\3"+
            "\uffff\1\12\7\uffff\1\12\1\uffff\1\13\1\uffff\3\12\1\uffff\2"+
            "\12\1\uffff\3\12\2\uffff\1\12\1\uffff\1\12\2\uffff\13\12\1\uffff"+
            "\12\12\4\uffff\2\12\14\uffff\3\12\35\uffff\11\12",
            "\2\12\21\uffff\2\12\4\uffff\1\12\6\uffff\2\12\3\uffff\1\12\3"+
            "\uffff\1\12\7\uffff\1\12\1\uffff\1\13\1\uffff\3\12\1\uffff\2"+
            "\12\1\uffff\3\12\2\uffff\1\12\1\uffff\1\12\2\uffff\13\12\1\uffff"+
            "\12\12\4\uffff\2\12\14\uffff\3\12\35\uffff\11\12",
            "\2\12\21\uffff\2\12\4\uffff\1\12\6\uffff\2\12\3\uffff\1\12\3"+
            "\uffff\1\12\7\uffff\1\12\1\uffff\1\13\1\uffff\3\12\1\uffff\2"+
            "\12\1\uffff\3\12\2\uffff\1\12\1\uffff\1\12\2\uffff\13\12\1\uffff"+
            "\12\12\4\uffff\2\12\14\uffff\3\12\35\uffff\11\12",
            "\2\12\21\uffff\2\12\4\uffff\1\12\6\uffff\2\12\3\uffff\1\12\3"+
            "\uffff\1\12\7\uffff\1\12\1\uffff\1\13\1\uffff\3\12\1\uffff\2"+
            "\12\1\uffff\3\12\2\uffff\1\12\1\uffff\1\12\2\uffff\13\12\1\uffff"+
            "\12\12\4\uffff\2\12\14\uffff\3\12\35\uffff\11\12",
            "\2\12\21\uffff\2\12\4\uffff\1\12\6\uffff\2\12\3\uffff\1\12\3"+
            "\uffff\1\12\7\uffff\1\12\1\uffff\1\13\1\uffff\3\12\1\uffff\2"+
            "\12\1\uffff\3\12\2\uffff\1\12\1\uffff\1\12\2\uffff\13\12\1\uffff"+
            "\12\12\4\uffff\2\12\14\uffff\3\12\35\uffff\11\12",
            "\2\12\21\uffff\2\12\4\uffff\1\12\6\uffff\2\12\3\uffff\1\12\3"+
            "\uffff\1\12\7\uffff\1\12\1\uffff\1\13\1\uffff\3\12\1\uffff\2"+
            "\12\1\uffff\3\12\2\uffff\1\12\1\uffff\1\12\2\uffff\13\12\1\uffff"+
            "\12\12\4\uffff\2\12\14\uffff\3\12\35\uffff\11\12",
            "",
            ""
    };

    static final short[] DFA75_eot = DFA.unpackEncodedString(DFA75_eotS);
    static final short[] DFA75_eof = DFA.unpackEncodedString(DFA75_eofS);
    static final char[] DFA75_min = DFA.unpackEncodedStringToUnsignedChars(DFA75_minS);
    static final char[] DFA75_max = DFA.unpackEncodedStringToUnsignedChars(DFA75_maxS);
    static final short[] DFA75_accept = DFA.unpackEncodedString(DFA75_acceptS);
    static final short[] DFA75_special = DFA.unpackEncodedString(DFA75_specialS);
    static final short[][] DFA75_transition;

    static {
        int numStates = DFA75_transitionS.length;
        DFA75_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA75_transition[i] = DFA.unpackEncodedString(DFA75_transitionS[i]);
        }
    }

    class DFA75 extends DFA {

        public DFA75(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 75;
            this.eot = DFA75_eot;
            this.eof = DFA75_eof;
            this.min = DFA75_min;
            this.max = DFA75_max;
            this.accept = DFA75_accept;
            this.special = DFA75_special;
            this.transition = DFA75_transition;
        }
        public String getDescription() {
            return "131:1: nodeTest : ( kindTest | nameTest );";
        }
        public void error(NoViableAltException nvae) {
            dbg.recognitionException(nvae);
        }
    }
    static final String DFA80_eotS =
        "\14\uffff";
    static final String DFA80_eofS =
        "\14\uffff";
    static final String DFA80_minS =
        "\1\6\4\uffff\3\70\4\uffff";
    static final String DFA80_maxS =
        "\1\u009f\4\uffff\3\163\4\uffff";
    static final String DFA80_acceptS =
        "\1\uffff\1\1\1\2\1\3\1\4\3\uffff\1\10\1\5\1\6\1\7";
    static final String DFA80_specialS =
        "\14\uffff}>";
    static final String[] DFA80_transitionS = {
            "\1\10\13\uffff\2\11\1\1\3\11\2\uffff\5\11\1\10\3\11\1\5\1\6"+
            "\5\11\1\uffff\13\11\1\uffff\1\11\1\3\2\uffff\14\11\1\uffff\1"+
            "\11\1\uffff\32\11\3\uffff\2\11\1\10\11\11\3\uffff\3\1\2\10\3"+
            "\uffff\1\7\3\10\6\uffff\1\11\11\uffff\1\2\14\uffff\1\4",
            "",
            "",
            "",
            "",
            "\1\11\1\12\71\uffff\1\11",
            "\1\11\1\13\71\uffff\1\11",
            "\1\11\1\10\71\uffff\1\11",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA80_eot = DFA.unpackEncodedString(DFA80_eotS);
    static final short[] DFA80_eof = DFA.unpackEncodedString(DFA80_eofS);
    static final char[] DFA80_min = DFA.unpackEncodedStringToUnsignedChars(DFA80_minS);
    static final char[] DFA80_max = DFA.unpackEncodedStringToUnsignedChars(DFA80_maxS);
    static final short[] DFA80_accept = DFA.unpackEncodedString(DFA80_acceptS);
    static final short[] DFA80_special = DFA.unpackEncodedString(DFA80_specialS);
    static final short[][] DFA80_transition;

    static {
        int numStates = DFA80_transitionS.length;
        DFA80_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA80_transition[i] = DFA.unpackEncodedString(DFA80_transitionS[i]);
        }
    }

    class DFA80 extends DFA {

        public DFA80(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 80;
            this.eot = DFA80_eot;
            this.eof = DFA80_eof;
            this.min = DFA80_min;
            this.max = DFA80_max;
            this.accept = DFA80_accept;
            this.special = DFA80_special;
            this.transition = DFA80_transition;
        }
        public String getDescription() {
            return "137:1: primaryExpr : ( literal | varRef | parenthesizedExpr | contextItemExpr | functionCall | orderedExpr | unorderedExpr | constructor );";
        }
        public void error(NoViableAltException nvae) {
            dbg.recognitionException(nvae);
        }
    }
    static final String DFA108_eotS =
        "\47\uffff";
    static final String DFA108_eofS =
        "\1\2\46\uffff";
    static final String DFA108_minS =
        "\1\5\1\0\5\uffff\1\0\37\uffff";
    static final String DFA108_maxS =
        "\1\u00a0\1\0\5\uffff\1\0\37\uffff";
    static final String DFA108_acceptS =
        "\2\uffff\1\2\6\uffff\1\1\35\uffff";
    static final String DFA108_specialS =
        "\1\0\1\1\5\uffff\1\2\37\uffff}>";
    static final String[] DFA108_transitionS = {
            "\2\2\21\uffff\2\2\4\uffff\1\2\6\uffff\2\2\3\uffff\1\2\3\uffff"+
            "\1\2\3\uffff\1\2\1\uffff\1\2\1\uffff\1\2\2\uffff\7\2\1\uffff"+
            "\3\2\2\uffff\1\2\1\uffff\1\2\2\uffff\13\2\4\uffff\7\2\24\uffff"+
            "\1\2\33\uffff\1\2\1\uffff\1\7\1\2\1\1\6\2\4\uffff\1\11",
            "\1\uffff",
            "",
            "",
            "",
            "",
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
            "",
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

    static final short[] DFA108_eot = DFA.unpackEncodedString(DFA108_eotS);
    static final short[] DFA108_eof = DFA.unpackEncodedString(DFA108_eofS);
    static final char[] DFA108_min = DFA.unpackEncodedStringToUnsignedChars(DFA108_minS);
    static final char[] DFA108_max = DFA.unpackEncodedStringToUnsignedChars(DFA108_maxS);
    static final short[] DFA108_accept = DFA.unpackEncodedString(DFA108_acceptS);
    static final short[] DFA108_special = DFA.unpackEncodedString(DFA108_specialS);
    static final short[][] DFA108_transition;

    static {
        int numStates = DFA108_transitionS.length;
        DFA108_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA108_transition[i] = DFA.unpackEncodedString(DFA108_transitionS[i]);
        }
    }

    class DFA108 extends DFA {

        public DFA108(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 108;
            this.eot = DFA108_eot;
            this.eof = DFA108_eof;
            this.min = DFA108_min;
            this.max = DFA108_max;
            this.accept = DFA108_accept;
            this.special = DFA108_special;
            this.transition = DFA108_transition;
        }
        public String getDescription() {
            return "187:15: ( ( occurrenceIndicator )=> occurrenceIndicator )?";
        }
        public void error(NoViableAltException nvae) {
            dbg.recognitionException(nvae);
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA108_0 = input.LA(1);

                         
                        int index108_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA108_0==149) ) {s = 1;}

                        else if ( (LA108_0==EOF||(LA108_0>=CLOSE_ANGLE && LA108_0<=OPEN_ANGLE)||(LA108_0>=Lit_EQ && LA108_0<=SEMICOLON)||LA108_0==DEFAULT||(LA108_0>=ORDER && LA108_0<=EMPTY)||LA108_0==COMMA||LA108_0==COLLATION||LA108_0==AT||LA108_0==EXTERNAL||LA108_0==RPAREN||(LA108_0>=LCURLY && LA108_0<=WHERE)||(LA108_0>=STABLE && LA108_0<=DESCENDING)||LA108_0==SATISFIES||LA108_0==CASE||(LA108_0>=ELSE && LA108_0<=INSTANCE)||(LA108_0>=EQ && LA108_0<=IS)||LA108_0==RBRACKET||LA108_0==145||LA108_0==148||(LA108_0>=150 && LA108_0<=155)) ) {s = 2;}

                        else if ( (LA108_0==147) ) {s = 7;}

                        else if ( (LA108_0==160) && (synpred2_XQuery())) {s = 9;}

                         
                        input.seek(index108_0);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA108_1 = input.LA(1);

                         
                        int index108_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred2_XQuery()) ) {s = 9;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index108_1);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA108_7 = input.LA(1);

                         
                        int index108_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred2_XQuery()) ) {s = 9;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index108_7);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 108, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA110_eotS =
        "\16\uffff";
    static final String DFA110_eofS =
        "\1\uffff\12\13\3\uffff";
    static final String DFA110_minS =
        "\1\22\12\5\3\uffff";
    static final String DFA110_maxS =
        "\1\u0088\12\u00a0\3\uffff";
    static final String DFA110_acceptS =
        "\13\uffff\1\3\1\1\1\2";
    static final String DFA110_specialS =
        "\16\uffff}>";
    static final String[] DFA110_transitionS = {
            "\2\13\1\uffff\3\13\2\uffff\5\13\1\2\12\13\1\uffff\13\13\1\uffff"+
            "\1\13\3\uffff\51\13\3\uffff\2\13\1\3\11\13\13\uffff\1\13\1\10"+
            "\1\7\1\6\1\13\1\12\1\11\1\1\1\5\1\4\1\13",
            "\2\13\21\uffff\2\13\4\uffff\1\13\6\uffff\2\13\3\uffff\1\13\3"+
            "\uffff\1\13\3\uffff\1\13\1\uffff\1\13\1\uffff\1\13\1\uffff\1"+
            "\14\7\13\1\uffff\3\13\2\uffff\1\13\1\uffff\1\13\2\uffff\13\13"+
            "\4\uffff\7\13\22\uffff\1\13\1\uffff\1\13\33\uffff\1\13\1\uffff"+
            "\11\13\4\uffff\1\13",
            "\2\13\21\uffff\2\13\4\uffff\1\13\6\uffff\2\13\3\uffff\1\13\3"+
            "\uffff\1\13\3\uffff\1\13\1\uffff\1\13\1\uffff\1\13\1\uffff\1"+
            "\14\7\13\1\uffff\3\13\2\uffff\1\13\1\uffff\1\13\2\uffff\13\13"+
            "\4\uffff\7\13\22\uffff\1\13\1\uffff\1\13\33\uffff\1\13\1\uffff"+
            "\11\13\4\uffff\1\13",
            "\2\13\21\uffff\2\13\4\uffff\1\13\6\uffff\2\13\3\uffff\1\13\3"+
            "\uffff\1\13\3\uffff\1\13\1\uffff\1\13\1\uffff\1\13\1\uffff\1"+
            "\14\7\13\1\uffff\3\13\2\uffff\1\13\1\uffff\1\13\2\uffff\13\13"+
            "\4\uffff\7\13\22\uffff\1\13\1\uffff\1\13\33\uffff\1\13\1\uffff"+
            "\11\13\4\uffff\1\13",
            "\2\13\21\uffff\2\13\4\uffff\1\13\6\uffff\2\13\3\uffff\1\13\3"+
            "\uffff\1\13\3\uffff\1\13\1\uffff\1\13\1\uffff\1\13\1\uffff\1"+
            "\14\7\13\1\uffff\3\13\2\uffff\1\13\1\uffff\1\13\2\uffff\13\13"+
            "\4\uffff\7\13\22\uffff\1\13\1\uffff\1\13\33\uffff\1\13\1\uffff"+
            "\11\13\4\uffff\1\13",
            "\2\13\21\uffff\2\13\4\uffff\1\13\6\uffff\2\13\3\uffff\1\13\3"+
            "\uffff\1\13\3\uffff\1\13\1\uffff\1\13\1\uffff\1\13\1\uffff\1"+
            "\14\7\13\1\uffff\3\13\2\uffff\1\13\1\uffff\1\13\2\uffff\13\13"+
            "\4\uffff\7\13\22\uffff\1\13\1\uffff\1\13\33\uffff\1\13\1\uffff"+
            "\11\13\4\uffff\1\13",
            "\2\13\21\uffff\2\13\4\uffff\1\13\6\uffff\2\13\3\uffff\1\13\3"+
            "\uffff\1\13\3\uffff\1\13\1\uffff\1\13\1\uffff\1\13\1\uffff\1"+
            "\14\7\13\1\uffff\3\13\2\uffff\1\13\1\uffff\1\13\2\uffff\13\13"+
            "\4\uffff\7\13\22\uffff\1\13\1\uffff\1\13\33\uffff\1\13\1\uffff"+
            "\11\13\4\uffff\1\13",
            "\2\13\21\uffff\2\13\4\uffff\1\13\6\uffff\2\13\3\uffff\1\13\3"+
            "\uffff\1\13\3\uffff\1\13\1\uffff\1\13\1\uffff\1\13\1\uffff\1"+
            "\14\7\13\1\uffff\3\13\2\uffff\1\13\1\uffff\1\13\2\uffff\13\13"+
            "\4\uffff\7\13\22\uffff\1\13\1\uffff\1\13\33\uffff\1\13\1\uffff"+
            "\11\13\4\uffff\1\13",
            "\2\13\21\uffff\2\13\4\uffff\1\13\6\uffff\2\13\3\uffff\1\13\3"+
            "\uffff\1\13\3\uffff\1\13\1\uffff\1\13\1\uffff\1\13\1\uffff\1"+
            "\14\7\13\1\uffff\3\13\2\uffff\1\13\1\uffff\1\13\2\uffff\13\13"+
            "\4\uffff\7\13\22\uffff\1\13\1\uffff\1\13\33\uffff\1\13\1\uffff"+
            "\11\13\4\uffff\1\13",
            "\2\13\21\uffff\2\13\4\uffff\1\13\6\uffff\2\13\3\uffff\1\13\3"+
            "\uffff\1\13\3\uffff\1\13\1\uffff\1\13\1\uffff\1\13\1\uffff\1"+
            "\14\7\13\1\uffff\3\13\2\uffff\1\13\1\uffff\1\13\2\uffff\13\13"+
            "\4\uffff\7\13\22\uffff\1\13\1\uffff\1\13\33\uffff\1\13\1\uffff"+
            "\11\13\4\uffff\1\13",
            "\2\13\21\uffff\2\13\4\uffff\1\13\6\uffff\2\13\3\uffff\1\13\3"+
            "\uffff\1\13\3\uffff\1\13\1\uffff\1\13\1\uffff\1\13\1\uffff\1"+
            "\15\7\13\1\uffff\3\13\2\uffff\1\13\1\uffff\1\13\2\uffff\13\13"+
            "\4\uffff\7\13\22\uffff\1\13\1\uffff\1\13\33\uffff\1\13\1\uffff"+
            "\11\13\4\uffff\1\13",
            "",
            "",
            ""
    };

    static final short[] DFA110_eot = DFA.unpackEncodedString(DFA110_eotS);
    static final short[] DFA110_eof = DFA.unpackEncodedString(DFA110_eofS);
    static final char[] DFA110_min = DFA.unpackEncodedStringToUnsignedChars(DFA110_minS);
    static final char[] DFA110_max = DFA.unpackEncodedStringToUnsignedChars(DFA110_maxS);
    static final short[] DFA110_accept = DFA.unpackEncodedString(DFA110_acceptS);
    static final short[] DFA110_special = DFA.unpackEncodedString(DFA110_specialS);
    static final short[][] DFA110_transition;

    static {
        int numStates = DFA110_transitionS.length;
        DFA110_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA110_transition[i] = DFA.unpackEncodedString(DFA110_transitionS[i]);
        }
    }

    class DFA110 extends DFA {

        public DFA110(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 110;
            this.eot = DFA110_eot;
            this.eof = DFA110_eof;
            this.min = DFA110_min;
            this.max = DFA110_max;
            this.accept = DFA110_accept;
            this.special = DFA110_special;
            this.transition = DFA110_transition;
        }
        public String getDescription() {
            return "190:1: itemType : ( kindTest | ( ITEM LPAREN RPAREN ) | atomicType );";
        }
        public void error(NoViableAltException nvae) {
            dbg.recognitionException(nvae);
        }
    }
 

    public static final BitSet FOLLOW_versionDecl_in_module118 = new BitSet(new long[]{0xF9BFFBFFFCFC0040L,0xC7C7FFFFFFFFFFFFL,0x00000000E03C01FFL});
    public static final BitSet FOLLOW_libraryModule_in_module122 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_mainModule_in_module126 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_module129 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_XQUERY_in_versionDecl136 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_VERSION_in_versionDecl138 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_StringLiteral_in_versionDecl140 = new BitSet(new long[]{0x0000000002200000L});
    public static final BitSet FOLLOW_ENCODING_in_versionDecl143 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_StringLiteral_in_versionDecl145 = new BitSet(new long[]{0x0000000002200000L});
    public static final BitSet FOLLOW_separator_in_versionDecl149 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_prolog_in_mainModule157 = new BitSet(new long[]{0xF9BFFBFFFCFC0040L,0xC7C7FFFFFFFFFFFFL,0x00000000E03C01FFL});
    public static final BitSet FOLLOW_queryBody_in_mainModule159 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_moduleDecl_in_libraryModule167 = new BitSet(new long[]{0x0001000004000000L});
    public static final BitSet FOLLOW_prolog_in_libraryModule169 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MODULE_in_moduleDecl177 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_NAMESPACE_in_moduleDecl179 = new BitSet(new long[]{0xF9BFFBFFFCFC0040L,0xC7C7FFEFFFFFFFFFL,0x00000000E02401FFL});
    public static final BitSet FOLLOW_ncName_in_moduleDecl181 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_Lit_EQ_in_moduleDecl183 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_uRILiteral_in_moduleDecl185 = new BitSet(new long[]{0x0000000002200000L});
    public static final BitSet FOLLOW_separator_in_moduleDecl187 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_defaultNamespaceDecl_in_prolog198 = new BitSet(new long[]{0x0000000002200000L});
    public static final BitSet FOLLOW_setter_in_prolog202 = new BitSet(new long[]{0x0000000002200000L});
    public static final BitSet FOLLOW_namespaceDecl_in_prolog206 = new BitSet(new long[]{0x0000000002200000L});
    public static final BitSet FOLLOW_importDecl_in_prolog210 = new BitSet(new long[]{0x0000000002200000L});
    public static final BitSet FOLLOW_separator_in_prolog213 = new BitSet(new long[]{0x0001000004000002L});
    public static final BitSet FOLLOW_varDecl_in_prolog219 = new BitSet(new long[]{0x0000000002200000L});
    public static final BitSet FOLLOW_functionDecl_in_prolog223 = new BitSet(new long[]{0x0000000002200000L});
    public static final BitSet FOLLOW_optionDecl_in_prolog227 = new BitSet(new long[]{0x0000000002200000L});
    public static final BitSet FOLLOW_separator_in_prolog230 = new BitSet(new long[]{0x0000000004000002L});
    public static final BitSet FOLLOW_boundarySpaceDecl_in_setter241 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_defaultCollationDecl_in_setter245 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_baseURIDecl_in_setter249 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_constructionDecl_in_setter253 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_orderingModeDecl_in_setter257 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_emptyOrderDecl_in_setter261 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_copyNamespacesDecl_in_setter265 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_schemaImport_in_importDecl273 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_moduleImport_in_importDecl277 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SEMICOLON_in_separator285 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DECLARE_in_namespaceDecl293 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_NAMESPACE_in_namespaceDecl295 = new BitSet(new long[]{0xF9BFFBFFFCFC0040L,0xC7C7FFEFFFFFFFFFL,0x00000000E02401FFL});
    public static final BitSet FOLLOW_ncName_in_namespaceDecl297 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_Lit_EQ_in_namespaceDecl299 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_uRILiteral_in_namespaceDecl301 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DECLARE_in_boundarySpaceDecl312 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_BOUNDARY_SPACE_in_boundarySpaceDecl314 = new BitSet(new long[]{0x0000000030000000L});
    public static final BitSet FOLLOW_set_in_boundarySpaceDecl316 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DECLARE_in_defaultNamespaceDecl332 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_DEFAULT_in_defaultNamespaceDecl334 = new BitSet(new long[]{0x0000000180000000L});
    public static final BitSet FOLLOW_set_in_defaultNamespaceDecl336 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_NAMESPACE_in_defaultNamespaceDecl344 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_uRILiteral_in_defaultNamespaceDecl346 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DECLARE_in_optionDecl354 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_OPTION_in_optionDecl356 = new BitSet(new long[]{0xF9BFFBFFFCFC0040L,0xC7C7FFEFFFFFFFFFL,0x00000000E02401FFL});
    public static final BitSet FOLLOW_qNameOrIdent_in_optionDecl358 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_StringLiteral_in_optionDecl360 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DECLARE_in_orderingModeDecl370 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_ORDERING_in_orderingModeDecl372 = new BitSet(new long[]{0x0000001800000000L});
    public static final BitSet FOLLOW_set_in_orderingModeDecl374 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DECLARE_in_emptyOrderDecl388 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_DEFAULT_in_emptyOrderDecl390 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_ORDER_in_emptyOrderDecl392 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_EMPTY_in_emptyOrderDecl394 = new BitSet(new long[]{0x0000018000000000L});
    public static final BitSet FOLLOW_set_in_emptyOrderDecl396 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DECLARE_in_copyNamespacesDecl412 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_COPY_NAMESPACES_in_copyNamespacesDecl414 = new BitSet(new long[]{0x0000080010000000L});
    public static final BitSet FOLLOW_preserveMode_in_copyNamespacesDecl416 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_COMMA_in_copyNamespacesDecl418 = new BitSet(new long[]{0x0000300000000000L});
    public static final BitSet FOLLOW_inheritMode_in_copyNamespacesDecl420 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_preserveMode0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_inheritMode0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DECLARE_in_defaultCollationDecl455 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_DEFAULT_in_defaultCollationDecl457 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_COLLATION_in_defaultCollationDecl459 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_uRILiteral_in_defaultCollationDecl461 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DECLARE_in_baseURIDecl469 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_BASE_URI_in_baseURIDecl471 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_uRILiteral_in_baseURIDecl473 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IMPORT_in_schemaImport481 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_SCHEMA_in_schemaImport483 = new BitSet(new long[]{0x0000000040900000L});
    public static final BitSet FOLLOW_schemaPrefix_in_schemaImport485 = new BitSet(new long[]{0x0000000040900000L});
    public static final BitSet FOLLOW_uRILiteral_in_schemaImport488 = new BitSet(new long[]{0x0004000000000002L});
    public static final BitSet FOLLOW_AT_in_schemaImport491 = new BitSet(new long[]{0x0000000040900000L});
    public static final BitSet FOLLOW_uRILiteral_in_schemaImport493 = new BitSet(new long[]{0x0000040000000002L});
    public static final BitSet FOLLOW_COMMA_in_schemaImport496 = new BitSet(new long[]{0x0000000040900000L});
    public static final BitSet FOLLOW_uRILiteral_in_schemaImport498 = new BitSet(new long[]{0x0000040000000002L});
    public static final BitSet FOLLOW_NAMESPACE_in_schemaPrefix511 = new BitSet(new long[]{0xF9BFFBFFFCFC0040L,0xC7C7FFEFFFFFFFFFL,0x00000000E02401FFL});
    public static final BitSet FOLLOW_ncName_in_schemaPrefix513 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_Lit_EQ_in_schemaPrefix515 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DEFAULT_in_schemaPrefix521 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_ELEMENT_in_schemaPrefix523 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_NAMESPACE_in_schemaPrefix525 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IMPORT_in_moduleImport534 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_MODULE_in_moduleImport536 = new BitSet(new long[]{0x0000000040900000L});
    public static final BitSet FOLLOW_NAMESPACE_in_moduleImport539 = new BitSet(new long[]{0xF9BFFBFFFCFC0040L,0xC7C7FFEFFFFFFFFFL,0x00000000E02401FFL});
    public static final BitSet FOLLOW_ncName_in_moduleImport541 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_Lit_EQ_in_moduleImport543 = new BitSet(new long[]{0x0000000040900000L});
    public static final BitSet FOLLOW_uRILiteral_in_moduleImport547 = new BitSet(new long[]{0x0004000000000002L});
    public static final BitSet FOLLOW_AT_in_moduleImport550 = new BitSet(new long[]{0x0000000040900000L});
    public static final BitSet FOLLOW_uRILiteral_in_moduleImport552 = new BitSet(new long[]{0x0000040000000002L});
    public static final BitSet FOLLOW_COMMA_in_moduleImport555 = new BitSet(new long[]{0x0000000040900000L});
    public static final BitSet FOLLOW_uRILiteral_in_moduleImport557 = new BitSet(new long[]{0x0000040000000002L});
    public static final BitSet FOLLOW_DECLARE_in_varDecl570 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_VARIABLE_in_varDecl572 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_variable_in_varDecl574 = new BitSet(new long[]{0x0090000000000000L,0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_typeDeclaration_in_varDecl576 = new BitSet(new long[]{0x0010000000000000L,0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_145_in_varDecl581 = new BitSet(new long[]{0xF9BFFBFFFCFC0040L,0xC7C7FFFFFFFFFFFFL,0x00000000E03C01FFL});
    public static final BitSet FOLLOW_exprSingle_in_varDecl583 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EXTERNAL_in_varDecl588 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DECLARE_in_constructionDecl600 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_CONSTRUCTION_in_constructionDecl602 = new BitSet(new long[]{0x0000000030000000L});
    public static final BitSet FOLLOW_set_in_constructionDecl604 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DECLARE_in_functionDecl618 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FUNCTION_in_functionDecl620 = new BitSet(new long[]{0xF9BFFBFFFCFC0040L,0xC7C7FFEFFFFFFFFFL,0x00000000E02401FFL});
    public static final BitSet FOLLOW_functionDeclPre_in_functionDecl622 = new BitSet(new long[]{0x0040000000000000L,0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_paramClause_in_functionDecl624 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_functionDecl626 = new BitSet(new long[]{0x0290000000000000L});
    public static final BitSet FOLLOW_AS_in_functionDecl629 = new BitSet(new long[]{0xF9BFFBFFFCFC0040L,0xC7C7FFEFFFFFFFFFL,0x00000000E02401FFL});
    public static final BitSet FOLLOW_sequenceType_in_functionDecl631 = new BitSet(new long[]{0x0210000000000000L});
    public static final BitSet FOLLOW_enclosedExpr_in_functionDecl636 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EXTERNAL_in_functionDecl640 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_declFuncName_in_functionDeclPre649 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_functionDeclPre651 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_qNameOrIdent_in_declFuncName658 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_paramList_in_paramClause665 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_param_in_paramList673 = new BitSet(new long[]{0x0000040000000002L});
    public static final BitSet FOLLOW_COMMA_in_paramList676 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_param_in_paramList678 = new BitSet(new long[]{0x0000040000000002L});
    public static final BitSet FOLLOW_variable_in_param689 = new BitSet(new long[]{0x0080000000000002L});
    public static final BitSet FOLLOW_typeDeclaration_in_param691 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LCURLY_in_enclosedExpr700 = new BitSet(new long[]{0xF9BFFBFFFCFC0040L,0xC7C7FFFFFFFFFFFFL,0x00000000E03C01FFL});
    public static final BitSet FOLLOW_expr_in_enclosedExpr702 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_RCURLY_in_enclosedExpr704 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_in_queryBody712 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_exprSingle_in_expr721 = new BitSet(new long[]{0x0000040000000002L});
    public static final BitSet FOLLOW_COMMA_in_expr724 = new BitSet(new long[]{0xF9BFFBFFFCFC0040L,0xC7C7FFFFFFFFFFFFL,0x00000000E03C01FFL});
    public static final BitSet FOLLOW_exprSingle_in_expr726 = new BitSet(new long[]{0x0000040000000002L});
    public static final BitSet FOLLOW_fLWORExpr_in_exprSingle736 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_quantifiedExpr_in_exprSingle741 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeswitchExpr_in_exprSingle746 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ifExpr_in_exprSingle751 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_orExpr_in_exprSingle756 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_forClause_in_fLWORExpr765 = new BitSet(new long[]{0xD800002000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_letClause_in_fLWORExpr769 = new BitSet(new long[]{0xD800002000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_whereClause_in_fLWORExpr773 = new BitSet(new long[]{0x8800002000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_orderByClause_in_fLWORExpr776 = new BitSet(new long[]{0x8800002000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_returnClause_in_fLWORExpr779 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RETURN_in_returnClause786 = new BitSet(new long[]{0xF9BFFBFFFCFC0040L,0xC7C7FFFFFFFFFFFFL,0x00000000E03C01FFL});
    public static final BitSet FOLLOW_exprSingle_in_returnClause788 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FOR_in_forClause796 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_variable_in_forClause798 = new BitSet(new long[]{0x2084000000000000L});
    public static final BitSet FOLLOW_typeDeclaration_in_forClause800 = new BitSet(new long[]{0x2004000000000000L});
    public static final BitSet FOLLOW_positionalVar_in_forClause803 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_IN_in_forClause806 = new BitSet(new long[]{0xF9BFFBFFFCFC0040L,0xC7C7FFFFFFFFFFFFL,0x00000000E03C01FFL});
    public static final BitSet FOLLOW_exprSingle_in_forClause808 = new BitSet(new long[]{0x0000040000000002L});
    public static final BitSet FOLLOW_forClauseExt_in_forClause810 = new BitSet(new long[]{0x0000040000000002L});
    public static final BitSet FOLLOW_146_in_variable819 = new BitSet(new long[]{0xF9BFFBFFFCFC0040L,0xC7C7FFEFFFFFFFFFL,0x00000000E02401FFL});
    public static final BitSet FOLLOW_varName_in_variable821 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COMMA_in_forClauseExt829 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_variable_in_forClauseExt831 = new BitSet(new long[]{0x2084000000000000L});
    public static final BitSet FOLLOW_typeDeclaration_in_forClauseExt833 = new BitSet(new long[]{0x2004000000000000L});
    public static final BitSet FOLLOW_positionalVar_in_forClauseExt836 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_IN_in_forClauseExt839 = new BitSet(new long[]{0xF9BFFBFFFCFC0040L,0xC7C7FFFFFFFFFFFFL,0x00000000E03C01FFL});
    public static final BitSet FOLLOW_exprSingle_in_forClauseExt841 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AT_in_positionalVar849 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_variable_in_positionalVar851 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LET_in_letClause859 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_variable_in_letClause861 = new BitSet(new long[]{0x0080000000000000L,0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_typeDeclaration_in_letClause863 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_145_in_letClause866 = new BitSet(new long[]{0xF9BFFBFFFCFC0040L,0xC7C7FFFFFFFFFFFFL,0x00000000E03C01FFL});
    public static final BitSet FOLLOW_exprSingle_in_letClause868 = new BitSet(new long[]{0x0000040000000002L});
    public static final BitSet FOLLOW_letClauseExt_in_letClause870 = new BitSet(new long[]{0x0000040000000002L});
    public static final BitSet FOLLOW_COMMA_in_letClauseExt880 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_variable_in_letClauseExt882 = new BitSet(new long[]{0x0080000000000000L,0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_typeDeclaration_in_letClauseExt884 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_145_in_letClauseExt887 = new BitSet(new long[]{0xF9BFFBFFFCFC0040L,0xC7C7FFFFFFFFFFFFL,0x00000000E03C01FFL});
    public static final BitSet FOLLOW_exprSingle_in_letClauseExt889 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WHERE_in_whereClause897 = new BitSet(new long[]{0xF9BFFBFFFCFC0040L,0xC7C7FFFFFFFFFFFFL,0x00000000E03C01FFL});
    public static final BitSet FOLLOW_exprSingle_in_whereClause899 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ORDER_in_orderByClause909 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_BY_in_orderByClause911 = new BitSet(new long[]{0xF9BFFBFFFCFC0040L,0xC7C7FFFFFFFFFFFFL,0x00000000E03C01FFL});
    public static final BitSet FOLLOW_STABLE_in_orderByClause917 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_ORDER_in_orderByClause919 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_BY_in_orderByClause921 = new BitSet(new long[]{0xF9BFFBFFFCFC0040L,0xC7C7FFFFFFFFFFFFL,0x00000000E03C01FFL});
    public static final BitSet FOLLOW_orderSpecList_in_orderByClause925 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_orderSpec_in_orderSpecList933 = new BitSet(new long[]{0x0000040000000002L});
    public static final BitSet FOLLOW_COMMA_in_orderSpecList936 = new BitSet(new long[]{0xF9BFFBFFFCFC0040L,0xC7C7FFFFFFFFFFFFL,0x00000000E03C01FFL});
    public static final BitSet FOLLOW_orderSpec_in_orderSpecList938 = new BitSet(new long[]{0x0000040000000002L});
    public static final BitSet FOLLOW_exprSingle_in_orderSpec948 = new BitSet(new long[]{0x0000404000000000L,0x000000000000000CL});
    public static final BitSet FOLLOW_orderModifier_in_orderSpec950 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_orderModifier958 = new BitSet(new long[]{0x0000404000000002L});
    public static final BitSet FOLLOW_EMPTY_in_orderModifier968 = new BitSet(new long[]{0x0000018000000000L});
    public static final BitSet FOLLOW_set_in_orderModifier970 = new BitSet(new long[]{0x0000400000000002L});
    public static final BitSet FOLLOW_COLLATION_in_orderModifier981 = new BitSet(new long[]{0x0000000040900000L});
    public static final BitSet FOLLOW_uRILiteral_in_orderModifier983 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_quantifiedExpr993 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_variable_in_quantifiedExpr1001 = new BitSet(new long[]{0x2080000000000000L});
    public static final BitSet FOLLOW_typeDeclaration_in_quantifiedExpr1003 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_IN_in_quantifiedExpr1006 = new BitSet(new long[]{0xF9BFFBFFFCFC0040L,0xC7C7FFFFFFFFFFFFL,0x00000000E03C01FFL});
    public static final BitSet FOLLOW_exprSingle_in_quantifiedExpr1008 = new BitSet(new long[]{0x0000040000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_quantifiedExprExt_in_quantifiedExpr1010 = new BitSet(new long[]{0x0000040000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_satisfiesClause_in_quantifiedExpr1013 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COMMA_in_quantifiedExprExt1022 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_variable_in_quantifiedExprExt1024 = new BitSet(new long[]{0x2080000000000000L});
    public static final BitSet FOLLOW_typeDeclaration_in_quantifiedExprExt1026 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_IN_in_quantifiedExprExt1029 = new BitSet(new long[]{0xF9BFFBFFFCFC0040L,0xC7C7FFFFFFFFFFFFL,0x00000000E03C01FFL});
    public static final BitSet FOLLOW_exprSingle_in_quantifiedExprExt1031 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SATISFIES_in_satisfiesClause1038 = new BitSet(new long[]{0xF9BFFBFFFCFC0040L,0xC7C7FFFFFFFFFFFFL,0x00000000E03C01FFL});
    public static final BitSet FOLLOW_exprSingle_in_satisfiesClause1040 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TYPESWITCH_in_typeswitchExpr1047 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_typeswitchExpr1049 = new BitSet(new long[]{0xF9BFFBFFFCFC0040L,0xC7C7FFFFFFFFFFFFL,0x00000000E03C01FFL});
    public static final BitSet FOLLOW_expr_in_typeswitchExpr1051 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_typeswitchExpr1053 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_caseClause_in_typeswitchExpr1055 = new BitSet(new long[]{0x0000000040000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_defaultClause_in_typeswitchExpr1058 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DEFAULT_in_defaultClause1065 = new BitSet(new long[]{0x0800000000000000L,0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_variable_in_defaultClause1068 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_RETURN_in_defaultClause1072 = new BitSet(new long[]{0xF9BFFBFFFCFC0040L,0xC7C7FFFFFFFFFFFFL,0x00000000E03C01FFL});
    public static final BitSet FOLLOW_exprSingle_in_defaultClause1074 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CASE_in_caseClause1081 = new BitSet(new long[]{0xF9BFFBFFFCFC0040L,0xC7C7FFEFFFFFFFFFL,0x00000000E02401FFL});
    public static final BitSet FOLLOW_variable_in_caseClause1084 = new BitSet(new long[]{0x0080000000000000L});
    public static final BitSet FOLLOW_AS_in_caseClause1086 = new BitSet(new long[]{0xF9BFFBFFFCFC0040L,0xC7C7FFEFFFFFFFFFL,0x00000000E02401FFL});
    public static final BitSet FOLLOW_sequenceType_in_caseClause1090 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_RETURN_in_caseClause1092 = new BitSet(new long[]{0xF9BFFBFFFCFC0040L,0xC7C7FFFFFFFFFFFFL,0x00000000E03C01FFL});
    public static final BitSet FOLLOW_exprSingle_in_caseClause1094 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IF_in_ifExpr1102 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_ifExpr1104 = new BitSet(new long[]{0xF9BFFBFFFCFC0040L,0xC7C7FFFFFFFFFFFFL,0x00000000E03C01FFL});
    public static final BitSet FOLLOW_expr_in_ifExpr1106 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_ifExpr1108 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_thenClause_in_ifExpr1110 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_elseClause_in_ifExpr1112 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_THEN_in_thenClause1119 = new BitSet(new long[]{0xF9BFFBFFFCFC0040L,0xC7C7FFFFFFFFFFFFL,0x00000000E03C01FFL});
    public static final BitSet FOLLOW_exprSingle_in_thenClause1121 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ELSE_in_elseClause1129 = new BitSet(new long[]{0xF9BFFBFFFCFC0040L,0xC7C7FFFFFFFFFFFFL,0x00000000E03C01FFL});
    public static final BitSet FOLLOW_exprSingle_in_elseClause1131 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_andExpr_in_orExpr1139 = new BitSet(new long[]{0x0000000000000002L,0x0000000000001000L});
    public static final BitSet FOLLOW_OR_in_orExpr1143 = new BitSet(new long[]{0xF9BFFBFFFCFC0040L,0xC7C7FFFFFFFFFFFFL,0x00000000E03C01FFL});
    public static final BitSet FOLLOW_andExpr_in_orExpr1145 = new BitSet(new long[]{0x0000000000000002L,0x0000000000001000L});
    public static final BitSet FOLLOW_comparisonExpr_in_andExpr1157 = new BitSet(new long[]{0x0000000000000002L,0x0000000000002000L});
    public static final BitSet FOLLOW_AND_in_andExpr1161 = new BitSet(new long[]{0xF9BFFBFFFCFC0040L,0xC7C7FFFFFFFFFFFFL,0x00000000E03C01FFL});
    public static final BitSet FOLLOW_comparisonExpr_in_andExpr1163 = new BitSet(new long[]{0x0000000000000002L,0x0000000000002000L});
    public static final BitSet FOLLOW_rangeExpr_in_comparisonExpr1174 = new BitSet(new long[]{0x0000000001000062L,0x00000001FC000000L,0x000000000F800000L});
    public static final BitSet FOLLOW_valueComp_in_comparisonExpr1179 = new BitSet(new long[]{0xF9BFFBFFFCFC0040L,0xC7C7FFFFFFFFFFFFL,0x00000000E03C01FFL});
    public static final BitSet FOLLOW_generalComp_in_comparisonExpr1184 = new BitSet(new long[]{0xF9BFFBFFFCFC0040L,0xC7C7FFFFFFFFFFFFL,0x00000000E03C01FFL});
    public static final BitSet FOLLOW_nodeComp_in_comparisonExpr1189 = new BitSet(new long[]{0xF9BFFBFFFCFC0040L,0xC7C7FFFFFFFFFFFFL,0x00000000E03C01FFL});
    public static final BitSet FOLLOW_rangeExpr_in_comparisonExpr1192 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_additiveExpr_in_rangeExpr1203 = new BitSet(new long[]{0x0000000000000002L,0x0000000000004000L});
    public static final BitSet FOLLOW_TO_in_rangeExpr1207 = new BitSet(new long[]{0xF9BFFBFFFCFC0040L,0xC7C7FFFFFFFFFFFFL,0x00000000E03C01FFL});
    public static final BitSet FOLLOW_additiveExpr_in_rangeExpr1209 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_multiplicativeExpr_in_additiveExpr1220 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000180000L});
    public static final BitSet FOLLOW_set_in_additiveExpr1224 = new BitSet(new long[]{0xF9BFFBFFFCFC0040L,0xC7C7FFFFFFFFFFFFL,0x00000000E03C01FFL});
    public static final BitSet FOLLOW_multiplicativeExpr_in_additiveExpr1232 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000180000L});
    public static final BitSet FOLLOW_unionExpr_in_multiplicativeExpr1246 = new BitSet(new long[]{0x0000000000000002L,0x0000000000038000L,0x0000000000200000L});
    public static final BitSet FOLLOW_set_in_multiplicativeExpr1250 = new BitSet(new long[]{0xF9BFFBFFFCFC0040L,0xC7C7FFFFFFFFFFFFL,0x00000000E03C01FFL});
    public static final BitSet FOLLOW_unionExpr_in_multiplicativeExpr1266 = new BitSet(new long[]{0x0000000000000002L,0x0000000000038000L,0x0000000000200000L});
    public static final BitSet FOLLOW_intersectExceptExpr_in_unionExpr1277 = new BitSet(new long[]{0x0000000000000002L,0x0000000000040000L,0x0000000000400000L});
    public static final BitSet FOLLOW_set_in_unionExpr1281 = new BitSet(new long[]{0xF9BFFBFFFCFC0040L,0xC7C7FFFFFFFFFFFFL,0x00000000E03C01FFL});
    public static final BitSet FOLLOW_intersectExceptExpr_in_unionExpr1289 = new BitSet(new long[]{0x0000000000000002L,0x0000000000040000L,0x0000000000400000L});
    public static final BitSet FOLLOW_instanceofExpr_in_intersectExceptExpr1303 = new BitSet(new long[]{0x0000000000000002L,0x0000000000180000L});
    public static final BitSet FOLLOW_set_in_intersectExceptExpr1307 = new BitSet(new long[]{0xF9BFFBFFFCFC0040L,0xC7C7FFFFFFFFFFFFL,0x00000000E03C01FFL});
    public static final BitSet FOLLOW_instanceofExpr_in_intersectExceptExpr1315 = new BitSet(new long[]{0x0000000000000002L,0x0000000000180000L});
    public static final BitSet FOLLOW_treatExpr_in_instanceofExpr1326 = new BitSet(new long[]{0x0000000000000002L,0x0000000000200000L});
    public static final BitSet FOLLOW_INSTANCE_in_instanceofExpr1330 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_OF_in_instanceofExpr1332 = new BitSet(new long[]{0xF9BFFBFFFCFC0040L,0xC7C7FFEFFFFFFFFFL,0x00000000E02401FFL});
    public static final BitSet FOLLOW_sequenceType_in_instanceofExpr1334 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_castableExpr_in_treatExpr1345 = new BitSet(new long[]{0x0000000000000002L,0x0000000000800000L});
    public static final BitSet FOLLOW_TREAT_in_treatExpr1349 = new BitSet(new long[]{0x0080000000000000L});
    public static final BitSet FOLLOW_AS_in_treatExpr1351 = new BitSet(new long[]{0xF9BFFBFFFCFC0040L,0xC7C7FFEFFFFFFFFFL,0x00000000E02401FFL});
    public static final BitSet FOLLOW_sequenceType_in_treatExpr1353 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_castExpr_in_castableExpr1364 = new BitSet(new long[]{0x0000000000000002L,0x0000000001000000L});
    public static final BitSet FOLLOW_CASTABLE_in_castableExpr1368 = new BitSet(new long[]{0x0080000000000000L});
    public static final BitSet FOLLOW_AS_in_castableExpr1370 = new BitSet(new long[]{0xF9BFFBFFFCFC0040L,0xC7C7FFEFFFFFFFFFL,0x00000000E02401FFL});
    public static final BitSet FOLLOW_singleType_in_castableExpr1372 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_unaryExpr_in_castExpr1383 = new BitSet(new long[]{0x0000000000000002L,0x0000000002000000L});
    public static final BitSet FOLLOW_CAST_in_castExpr1387 = new BitSet(new long[]{0x0080000000000000L});
    public static final BitSet FOLLOW_AS_in_castExpr1389 = new BitSet(new long[]{0xF9BFFBFFFCFC0040L,0xC7C7FFEFFFFFFFFFL,0x00000000E02401FFL});
    public static final BitSet FOLLOW_singleType_in_castExpr1391 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_unaryExpr1402 = new BitSet(new long[]{0xF9BFFBFFFCFC0040L,0xC7C7FFFFFFFFFFFFL,0x00000000E03C01FFL});
    public static final BitSet FOLLOW_valueExpr_in_unaryExpr1411 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_validateExpr_in_valueExpr1419 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pathExpr_in_valueExpr1423 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_extensionExpr_in_valueExpr1427 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_generalComp0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_valueComp0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_nodeComp0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALIDATE_in_validateExpr1507 = new BitSet(new long[]{0x0200000000000000L,0x0000000C00000000L});
    public static final BitSet FOLLOW_validationMode_in_validateExpr1509 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_LCURLY_in_validateExpr1512 = new BitSet(new long[]{0xF9BFFBFFFCFC0040L,0xC7C7FFFFFFFFFFFFL,0x00000000E03C01FFL});
    public static final BitSet FOLLOW_expr_in_validateExpr1514 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_RCURLY_in_validateExpr1516 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_validationMode0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pragma_in_extensionExpr1536 = new BitSet(new long[]{0xFBBFFBFFFCFC0040L,0xC7C7FFFFFFFFFFFFL,0x00000000E03C01FFL});
    public static final BitSet FOLLOW_LCURLY_in_extensionExpr1539 = new BitSet(new long[]{0xFFBFFBFFFCFC0040L,0xC7C7FFFFFFFFFFFFL,0x00000000E03C01FFL});
    public static final BitSet FOLLOW_expr_in_extensionExpr1541 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_RCURLY_in_extensionExpr1544 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Pragma_in_pragma1553 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SLASH_in_pathExpr1569 = new BitSet(new long[]{0xF9BFFBFFFCFC0040L,0xC7C7FFEFFFFFFFFFL,0x00000000E02401FFL});
    public static final BitSet FOLLOW_relativePathExpr_in_pathExpr1571 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SLASH_in_pathExpr1579 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SLASH_SLASH_in_pathExpr1584 = new BitSet(new long[]{0xF9BFFBFFFCFC0040L,0xC7C7FFEFFFFFFFFFL,0x00000000E02401FFL});
    public static final BitSet FOLLOW_relativePathExpr_in_pathExpr1586 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_relativePathExpr_in_pathExpr1592 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_stepExpr_in_relativePathExpr1605 = new BitSet(new long[]{0x0000000000000002L,0x0000006000000000L});
    public static final BitSet FOLLOW_set_in_relativePathExpr1608 = new BitSet(new long[]{0xF9BFFBFFFCFC0040L,0xC7C7FFEFFFFFFFFFL,0x00000000E02401FFL});
    public static final BitSet FOLLOW_stepExpr_in_relativePathExpr1616 = new BitSet(new long[]{0x0000000000000002L,0x0000006000000000L});
    public static final BitSet FOLLOW_filterExpr_in_stepExpr1626 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_axisStep_in_stepExpr1630 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_reverseStep_in_axisStep1639 = new BitSet(new long[]{0x0000000000000000L,0x0010000000000000L});
    public static final BitSet FOLLOW_forwardStep_in_axisStep1643 = new BitSet(new long[]{0x0000000000000000L,0x0010000000000000L});
    public static final BitSet FOLLOW_predicateList_in_axisStep1646 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_forwardAxis_in_forwardStep1655 = new BitSet(new long[]{0xF9BFFBFFFCFC0040L,0xC7C7FFEFFFFFFFFFL,0x00000000E02401FFL});
    public static final BitSet FOLLOW_nodeTest_in_forwardStep1657 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_abbrevForwardStep_in_forwardStep1662 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CHILD_in_forwardAxis1671 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000010000000L});
    public static final BitSet FOLLOW_156_in_forwardAxis1673 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DESCENDANT_in_forwardAxis1680 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000010000000L});
    public static final BitSet FOLLOW_156_in_forwardAxis1682 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ATTRIBUTE_in_forwardAxis1689 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000010000000L});
    public static final BitSet FOLLOW_156_in_forwardAxis1691 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SELF_in_forwardAxis1698 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000010000000L});
    public static final BitSet FOLLOW_156_in_forwardAxis1700 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DESCENDANT_OR_SELF_in_forwardAxis1707 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000010000000L});
    public static final BitSet FOLLOW_156_in_forwardAxis1709 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FOLLOWING_SIBLING_in_forwardAxis1716 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000010000000L});
    public static final BitSet FOLLOW_156_in_forwardAxis1718 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FOLLOWING_in_forwardAxis1725 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000010000000L});
    public static final BitSet FOLLOW_156_in_forwardAxis1727 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_157_in_abbrevForwardStep1739 = new BitSet(new long[]{0xF9BFFBFFFCFC0040L,0xC7C7FFEFFFFFFFFFL,0x00000000E02401FFL});
    public static final BitSet FOLLOW_nodeTest_in_abbrevForwardStep1742 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_reverseAxis_in_reverseStep1751 = new BitSet(new long[]{0xF9BFFBFFFCFC0040L,0xC7C7FFEFFFFFFFFFL,0x00000000E02401FFL});
    public static final BitSet FOLLOW_nodeTest_in_reverseStep1753 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_abbrevReverseStep_in_reverseStep1758 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PARENT_in_reverseAxis1767 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000010000000L});
    public static final BitSet FOLLOW_156_in_reverseAxis1769 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ANCESTOR_in_reverseAxis1776 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000010000000L});
    public static final BitSet FOLLOW_156_in_reverseAxis1778 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PRECEDING_SIBLING_in_reverseAxis1785 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000010000000L});
    public static final BitSet FOLLOW_156_in_reverseAxis1787 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PRECEDING_in_reverseAxis1794 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000010000000L});
    public static final BitSet FOLLOW_156_in_reverseAxis1796 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ANCESTOR_OR_SELF_in_reverseAxis1803 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000010000000L});
    public static final BitSet FOLLOW_156_in_reverseAxis1805 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_158_in_abbrevReverseStep1814 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_kindTest_in_nodeTest1822 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_nameTest_in_nodeTest1826 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_qNameOrIdent_in_nameTest1834 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_wildcard_in_nameTest1838 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_149_in_wildcard1845 = new BitSet(new long[]{0x0000000000000002L,0x0008000000000000L});
    public static final BitSet FOLLOW_COLON_in_wildcard1848 = new BitSet(new long[]{0xF9BFFBFFFCFC0040L,0xC7C7FFEFFFFFFFFFL,0x00000000E02401FFL});
    public static final BitSet FOLLOW_ncName_in_wildcard1850 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ncName_in_wildcard1856 = new BitSet(new long[]{0x0000000000000000L,0x0008000000000000L});
    public static final BitSet FOLLOW_COLON_in_wildcard1858 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_149_in_wildcard1860 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_primaryExpr_in_filterExpr1867 = new BitSet(new long[]{0x0000000000000000L,0x0010000000000000L});
    public static final BitSet FOLLOW_predicateList_in_filterExpr1869 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_predicate_in_predicateList1877 = new BitSet(new long[]{0x0000000000000002L,0x0010000000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_predicate1886 = new BitSet(new long[]{0xFBBFFBFFFCFC0040L,0xC7C7FFFFFFFFFFFFL,0x00000000E03C01FFL});
    public static final BitSet FOLLOW_expr_in_predicate1888 = new BitSet(new long[]{0x0000000000000000L,0x0020000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_predicate1890 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_literal_in_primaryExpr1898 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_varRef_in_primaryExpr1902 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parenthesizedExpr_in_primaryExpr1906 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_contextItemExpr_in_primaryExpr1910 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_functionCall_in_primaryExpr1914 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_orderedExpr_in_primaryExpr1918 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_unorderedExpr_in_primaryExpr1922 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_constructor_in_primaryExpr1926 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_numericLiteral_in_literal1935 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_StringLiteral_in_literal1939 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_numericLiteral0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variable_in_varRef1964 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_qNameOrIdent_in_varName1973 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_parenthesizedExpr1984 = new BitSet(new long[]{0xFBFFFBFFFCFC0040L,0xC7C7FFFFFFFFFFFFL,0x00000000E03C01FFL});
    public static final BitSet FOLLOW_expr_in_parenthesizedExpr1986 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_parenthesizedExpr1989 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_159_in_contextItemExpr1997 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ORDERED_in_orderedExpr2005 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_LCURLY_in_orderedExpr2007 = new BitSet(new long[]{0xFBBFFBFFFCFC0040L,0xC7C7FFFFFFFFFFFFL,0x00000000E03C01FFL});
    public static final BitSet FOLLOW_expr_in_orderedExpr2009 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_RCURLY_in_orderedExpr2011 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_UNORDERED_in_unorderedExpr2019 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_LCURLY_in_unorderedExpr2021 = new BitSet(new long[]{0xFBBFFBFFFCFC0040L,0xC7C7FFFFFFFFFFFFL,0x00000000E03C01FFL});
    public static final BitSet FOLLOW_expr_in_unorderedExpr2023 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_RCURLY_in_unorderedExpr2025 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_functionCallPre_in_functionCall2033 = new BitSet(new long[]{0xFBFFFBFFFCFC0040L,0xC7C7FFFFFFFFFFFFL,0x00000000E03C01FFL});
    public static final BitSet FOLLOW_exprSingle_in_functionCall2036 = new BitSet(new long[]{0x0040040000000000L});
    public static final BitSet FOLLOW_COMMA_in_functionCall2039 = new BitSet(new long[]{0xFBBFFBFFFCFC0040L,0xC7C7FFFFFFFFFFFFL,0x00000000E03C01FFL});
    public static final BitSet FOLLOW_exprSingle_in_functionCall2041 = new BitSet(new long[]{0x0040040000000000L});
    public static final BitSet FOLLOW_RPAREN_in_functionCall2047 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_funcName_in_functionCallPre2059 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_functionCallPre2061 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_directConstructor_in_constructor2068 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_computedConstructor_in_constructor2073 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_dirElemConstructor_in_directConstructor2084 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DirCommentConstructor_in_directConstructor2089 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DirPIConstructor_in_directConstructor2094 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OPEN_ANGLE_in_dirElemConstructor2105 = new BitSet(new long[]{0xF9BFFBFFFCFC0040L,0xC7C7FFEFFFFFFFFFL,0x00000000E02401FFL});
    public static final BitSet FOLLOW_qNameOrIdent_in_dirElemConstructor2109 = new BitSet(new long[]{0x00000000000000A0L,0x0800000000000000L});
    public static final BitSet FOLLOW_dirAttributeList_in_dirElemConstructor2111 = new BitSet(new long[]{0x00000000000000A0L});
    public static final BitSet FOLLOW_EMPTY_CLOSE_TAG_in_dirElemConstructor2118 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CLOSE_ANGLE_in_dirElemConstructor2123 = new BitSet(new long[]{0x0200000000000F50L,0x3600000000000000L});
    public static final BitSet FOLLOW_dirElemContent_in_dirElemConstructor2125 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CLOSE_TAG_in_dirElemConstructor2127 = new BitSet(new long[]{0xF9BFFBFFFCFC0040L,0xC7C7FFEFFFFFFFFFL,0x00000000E02401FFL});
    public static final BitSet FOLLOW_qNameOrIdent_in_dirElemConstructor2129 = new BitSet(new long[]{0x0000000000000020L,0x0800000000000000L});
    public static final BitSet FOLLOW_S_in_dirElemConstructor2131 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_CLOSE_ANGLE_in_dirElemConstructor2134 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_S_in_dirAttributeList2154 = new BitSet(new long[]{0xF9BFFBFFFCFC0042L,0xCFC7FFEFFFFFFFFFL,0x00000000E02401FFL});
    public static final BitSet FOLLOW_qNameOrIdent_in_dirAttributeList2157 = new BitSet(new long[]{0x0000000001000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_S_in_dirAttributeList2159 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_Lit_EQ_in_dirAttributeList2162 = new BitSet(new long[]{0x0000000000030000L,0x0800000000000000L});
    public static final BitSet FOLLOW_S_in_dirAttributeList2164 = new BitSet(new long[]{0x0000000000030000L,0x0800000000000000L});
    public static final BitSet FOLLOW_dirAttributeValue_in_dirAttributeList2167 = new BitSet(new long[]{0x0000000000000002L,0x0800000000000000L});
    public static final BitSet FOLLOW_QUOT_in_dirAttributeValue2184 = new BitSet(new long[]{0x0200000000019E00L,0x2000000000000000L});
    public static final BitSet FOLLOW_ESCAPE_QUOT_in_dirAttributeValue2187 = new BitSet(new long[]{0x0200000000019E00L,0x2000000000000000L});
    public static final BitSet FOLLOW_quotAttrValueContent_in_dirAttributeValue2191 = new BitSet(new long[]{0x0200000000019E00L,0x2000000000000000L});
    public static final BitSet FOLLOW_QUOT_in_dirAttributeValue2195 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_APOS_in_dirAttributeValue2202 = new BitSet(new long[]{0x020000000003FE00L,0x2000000000000000L});
    public static final BitSet FOLLOW_ESCAPE_APOS_in_dirAttributeValue2205 = new BitSet(new long[]{0x020000000003FE00L,0x2000000000000000L});
    public static final BitSet FOLLOW_aposAttrValueContent_in_dirAttributeValue2209 = new BitSet(new long[]{0x020000000003FE00L,0x2000000000000000L});
    public static final BitSet FOLLOW_APOS_in_dirAttributeValue2213 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_QuotAttrContentChar_in_quotAttrValueContent2226 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_commonContent_in_quotAttrValueContent2231 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AposAttrContentChar_in_aposAttrValueContent2241 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_commonContent_in_aposAttrValueContent2246 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_directConstructor_in_dirElemContent2255 = new BitSet(new long[]{0x020000000003FF42L,0x3600000000000000L});
    public static final BitSet FOLLOW_CDataSection_in_dirElemContent2260 = new BitSet(new long[]{0x020000000003FF42L,0x3600000000000000L});
    public static final BitSet FOLLOW_commonContent_in_dirElemContent2265 = new BitSet(new long[]{0x020000000003FF42L,0x3600000000000000L});
    public static final BitSet FOLLOW_ElementContentChar_in_dirElemContent2270 = new BitSet(new long[]{0x020000000003FF42L,0x3600000000000000L});
    public static final BitSet FOLLOW_PredefinedEntityRef_in_commonContent2279 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CharRef_in_commonContent2283 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ESCAPE_CURLY_OPEN_in_commonContent2287 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ESCAPE_CURLY_CLOSE_in_commonContent2291 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_elemEnclosedExpr_in_commonContent2295 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LCURLY_in_elemEnclosedExpr2305 = new BitSet(new long[]{0xFBBFFBFFFCFC0040L,0xC7C7FFFFFFFFFFFFL,0x00000000E03C01FFL});
    public static final BitSet FOLLOW_expr_in_elemEnclosedExpr2309 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_RCURLY_in_elemEnclosedExpr2311 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_compDocConstructor_in_computedConstructor2323 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_compElemConstructor_in_computedConstructor2328 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_compAttrConstructor_in_computedConstructor2333 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_compTextConstructor_in_computedConstructor2338 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_compCommentConstructor_in_computedConstructor2347 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_compPIConstructor_in_computedConstructor2352 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOCUMENT_in_compDocConstructor2363 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_LCURLY_in_compDocConstructor2365 = new BitSet(new long[]{0xFBBFFBFFFCFC0040L,0xC7C7FFFFFFFFFFFFL,0x00000000E03C01FFL});
    public static final BitSet FOLLOW_expr_in_compDocConstructor2367 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_RCURLY_in_compDocConstructor2369 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ELEMENT_in_compElemConstructor2380 = new BitSet(new long[]{0xFBBFFBFFFCFC0040L,0xC7C7FFEFFFFFFFFFL,0x00000000E02401FFL});
    public static final BitSet FOLLOW_qNameOrIdent_in_compElemConstructor2383 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_LCURLY_in_compElemConstructor2388 = new BitSet(new long[]{0xFBBFFBFFFCFC0040L,0xC7C7FFFFFFFFFFFFL,0x00000000E03C01FFL});
    public static final BitSet FOLLOW_expr_in_compElemConstructor2390 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_RCURLY_in_compElemConstructor2392 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_LCURLY_in_compElemConstructor2396 = new BitSet(new long[]{0xFFBFFBFFFCFC0040L,0xC7C7FFFFFFFFFFFFL,0x00000000E03C01FFL});
    public static final BitSet FOLLOW_contentExpr_in_compElemConstructor2398 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_RCURLY_in_compElemConstructor2401 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_in_contentExpr2409 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ATTRIBUTE_in_compAttrConstructor2420 = new BitSet(new long[]{0xFBBFFBFFFCFC0040L,0xC7C7FFEFFFFFFFFFL,0x00000000E02401FFL});
    public static final BitSet FOLLOW_qNameOrIdent_in_compAttrConstructor2423 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_LCURLY_in_compAttrConstructor2428 = new BitSet(new long[]{0xFBBFFBFFFCFC0040L,0xC7C7FFFFFFFFFFFFL,0x00000000E03C01FFL});
    public static final BitSet FOLLOW_expr_in_compAttrConstructor2430 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_RCURLY_in_compAttrConstructor2432 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_LCURLY_in_compAttrConstructor2436 = new BitSet(new long[]{0xFFBFFBFFFCFC0040L,0xC7C7FFFFFFFFFFFFL,0x00000000E03C01FFL});
    public static final BitSet FOLLOW_expr_in_compAttrConstructor2438 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_RCURLY_in_compAttrConstructor2441 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TEXT_in_compTextConstructor2452 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_LCURLY_in_compTextConstructor2454 = new BitSet(new long[]{0xFBBFFBFFFCFC0040L,0xC7C7FFFFFFFFFFFFL,0x00000000E03C01FFL});
    public static final BitSet FOLLOW_expr_in_compTextConstructor2456 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_RCURLY_in_compTextConstructor2458 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COMMENT_in_compCommentConstructor2469 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_LCURLY_in_compCommentConstructor2471 = new BitSet(new long[]{0xFBBFFBFFFCFC0040L,0xC7C7FFFFFFFFFFFFL,0x00000000E03C01FFL});
    public static final BitSet FOLLOW_expr_in_compCommentConstructor2473 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_RCURLY_in_compCommentConstructor2475 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PROCESSING_INSTRUCTION_in_compPIConstructor2486 = new BitSet(new long[]{0xFBBFFBFFFCFC0040L,0xC7C7FFEFFFFFFFFFL,0x00000000E02401FFL});
    public static final BitSet FOLLOW_ncName_in_compPIConstructor2489 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_LCURLY_in_compPIConstructor2494 = new BitSet(new long[]{0xFBBFFBFFFCFC0040L,0xC7C7FFFFFFFFFFFFL,0x00000000E03C01FFL});
    public static final BitSet FOLLOW_expr_in_compPIConstructor2496 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_RCURLY_in_compPIConstructor2498 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_LCURLY_in_compPIConstructor2502 = new BitSet(new long[]{0xFFBFFBFFFCFC0040L,0xC7C7FFFFFFFFFFFFL,0x00000000E03C01FFL});
    public static final BitSet FOLLOW_expr_in_compPIConstructor2504 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_RCURLY_in_compPIConstructor2507 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_atomicType_in_singleType2515 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000100000000L});
    public static final BitSet FOLLOW_160_in_singleType2517 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AS_in_typeDeclaration2526 = new BitSet(new long[]{0xF9BFFBFFFCFC0040L,0xC7C7FFEFFFFFFFFFL,0x00000000E02401FFL});
    public static final BitSet FOLLOW_sequenceType_in_typeDeclaration2528 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EMPTY_SEQUENCE_in_sequenceType2537 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_sequenceType2539 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_sequenceType2541 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_itemType_in_sequenceType2551 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000100280000L});
    public static final BitSet FOLLOW_occurrenceIndicator_in_sequenceType2560 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_occurrenceIndicator0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_kindTest_in_itemType2590 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ITEM_in_itemType2595 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_itemType2597 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_itemType2599 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_atomicType_in_itemType2604 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_qNameOrIdent_in_atomicType2612 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_documentTest_in_kindTest2620 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_elementTest_in_kindTest2625 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_attributeTest_in_kindTest2630 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_schemaElementTest_in_kindTest2635 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_schemaAttributeTest_in_kindTest2640 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pITest_in_kindTest2649 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_commentTest_in_kindTest2654 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_textTest_in_kindTest2659 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_anyKindTest_in_kindTest2664 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NODE_in_anyKindTest2672 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_anyKindTest2674 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_anyKindTest2676 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOCUMENT_NODE_in_documentTest2684 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_documentTest2686 = new BitSet(new long[]{0x0040000080000000L,0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_elementTest_in_documentTest2689 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_schemaElementTest_in_documentTest2693 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_documentTest2697 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TEXT_in_textTest2705 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_textTest2707 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_textTest2709 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COMMENT_in_commentTest2717 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_commentTest2719 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_commentTest2721 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PROCESSING_INSTRUCTION_in_pITest2730 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_pITest2732 = new BitSet(new long[]{0xF9FFFBFFFCFC0040L,0xC7C7FFEFFFFFFFFFL,0x00000000E02401FFL});
    public static final BitSet FOLLOW_ncName_in_pITest2735 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_StringLiteral_in_pITest2739 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_pITest2743 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ATTRIBUTE_in_attributeTest2751 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_attributeTest2753 = new BitSet(new long[]{0xF9FFFBFFFCFC0040L,0xC7C7FFEFFFFFFFFFL,0x00000000E02401FFL});
    public static final BitSet FOLLOW_attribNameOrWildcard_in_attributeTest2756 = new BitSet(new long[]{0x0040040000000000L});
    public static final BitSet FOLLOW_COMMA_in_attributeTest2759 = new BitSet(new long[]{0xF9BFFBFFFCFC0040L,0xC7C7FFEFFFFFFFFFL,0x00000000E02401FFL});
    public static final BitSet FOLLOW_typeName_in_attributeTest2761 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_attributeTest2767 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_attributeName_in_attribNameOrWildcard2778 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_149_in_attribNameOrWildcard2782 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SCHEMA_ATTRIBUTE_in_schemaAttributeTest2792 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_schemaAttributeTest2794 = new BitSet(new long[]{0xF9BFFBFFFCFC0040L,0xC7C7FFEFFFFFFFFFL,0x00000000E02401FFL});
    public static final BitSet FOLLOW_attributeDeclaration_in_schemaAttributeTest2796 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_schemaAttributeTest2798 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_attributeName_in_attributeDeclaration2808 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ELEMENT_in_elementTest2816 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_elementTest2818 = new BitSet(new long[]{0xF9FFFBFFFCFC0040L,0xC7C7FFEFFFFFFFFFL,0x00000000E02401FFL});
    public static final BitSet FOLLOW_elementNameOrWildcard_in_elementTest2821 = new BitSet(new long[]{0x0040040000000000L});
    public static final BitSet FOLLOW_COMMA_in_elementTest2824 = new BitSet(new long[]{0xF9BFFBFFFCFC0040L,0xC7C7FFEFFFFFFFFFL,0x00000000E02401FFL});
    public static final BitSet FOLLOW_typeName_in_elementTest2826 = new BitSet(new long[]{0x0040000000000000L,0x0000000000000000L,0x0000000100000000L});
    public static final BitSet FOLLOW_160_in_elementTest2828 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_elementTest2835 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_elementName_in_elementNameOrWildcard2845 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_149_in_elementNameOrWildcard2849 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SCHEMA_ELEMENT_in_schemaElementTest2859 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_schemaElementTest2861 = new BitSet(new long[]{0xF9BFFBFFFCFC0040L,0xC7C7FFEFFFFFFFFFL,0x00000000E02401FFL});
    public static final BitSet FOLLOW_elementDeclaration_in_schemaElementTest2863 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_schemaElementTest2865 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_elementName_in_elementDeclaration2875 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_qNameOrIdent_in_attributeName2883 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_qNameOrIdent_in_elementName2891 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_qNameOrIdent_in_typeName2899 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_StringLiteral_in_uRILiteral2907 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ncName_in_piTarget2915 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ncName_in_qNameOrIdent2922 = new BitSet(new long[]{0x0000000000000002L,0x0008000000000000L});
    public static final BitSet FOLLOW_COLON_in_qNameOrIdent2925 = new BitSet(new long[]{0xF9BFFBFFFCFC0040L,0xC7C7FFEFFFFFFFFFL,0x00000000E02401FFL});
    public static final BitSet FOLLOW_ncName_in_qNameOrIdent2927 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_funcKeyword_in_funcName2936 = new BitSet(new long[]{0x0000000000000002L,0x0008000000000000L});
    public static final BitSet FOLLOW_COLON_in_funcName2939 = new BitSet(new long[]{0xF8BFFBFF7CEC0000L,0x4007FD8FFFFFFD7FL,0x0000000000000100L});
    public static final BitSet FOLLOW_funcKeyword_in_funcName2941 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_ncName0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_funcKeyword0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SLASH_in_synpred1_XQuery1561 = new BitSet(new long[]{0xF9BFFBFFFCFC0040L,0xC7C7FFEFFFFFFFFFL,0x00000000E02401FFL});
    public static final BitSet FOLLOW_relativePathExpr_in_synpred1_XQuery1563 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_occurrenceIndicator_in_synpred2_XQuery2555 = new BitSet(new long[]{0x0000000000000002L});

}