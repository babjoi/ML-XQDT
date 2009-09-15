package org.oasisopen.parser.antlr.internal; 

import java.io.InputStream;
import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.xtext.parsetree.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import org.eclipse.xtext.conversion.ValueConverterException;
import org.oasisopen.services.RelaxngGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class InternalRelaxngParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_STRING", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'namespace'", "'='", "'default'", "'datatypes'", "'list'", "'{'", "'}'", "'mixed'", "'parent'", "'empty'", "'text'", "'|'", "'notAllowed'", "'external'", "'grammar'", "'('", "')'", "','", "'element'", "'?'", "'+'", "'*'", "'&'", "'attribute'", "'-'", "'div'", "'include'", "'start'", "'|='", "'&='", "'string'", "'token'", "'inherit'", "'\\\\'", "':'", "'~'"
    };
    public static final int RULE_ID=4;
    public static final int RULE_STRING=5;
    public static final int RULE_ANY_OTHER=10;
    public static final int RULE_INT=6;
    public static final int RULE_WS=9;
    public static final int RULE_SL_COMMENT=8;
    public static final int EOF=-1;
    public static final int RULE_ML_COMMENT=7;

        public InternalRelaxngParser(TokenStream input) {
            super(input);
        }
        

    public String[] getTokenNames() { return tokenNames; }
    public String getGrammarFileName() { return "../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g"; }


     
     	private RelaxngGrammarAccess grammarAccess;
     	
        public InternalRelaxngParser(TokenStream input, IAstFactory factory, RelaxngGrammarAccess grammarAccess) {
            this(input);
            this.factory = factory;
            registerRules(grammarAccess.getGrammar());
            this.grammarAccess = grammarAccess;
        }
        
        @Override
        protected InputStream getTokenFile() {
        	ClassLoader classLoader = getClass().getClassLoader();
        	return classLoader.getResourceAsStream("org/oasisopen/parser/antlr/internal/InternalRelaxng.tokens");
        }
        
        @Override
        protected String getFirstRuleName() {
        	return "TopLevel";	
       	} 



    // $ANTLR start entryRuleTopLevel
    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:72:1: entryRuleTopLevel returns [EObject current=null] : iv_ruleTopLevel= ruleTopLevel EOF ;
    public final EObject entryRuleTopLevel() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTopLevel = null;


        try {
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:72:50: (iv_ruleTopLevel= ruleTopLevel EOF )
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:73:2: iv_ruleTopLevel= ruleTopLevel EOF
            {
             currentNode = createCompositeNode(grammarAccess.getTopLevelRule(), currentNode); 
            pushFollow(FOLLOW_ruleTopLevel_in_entryRuleTopLevel73);
            iv_ruleTopLevel=ruleTopLevel();
            _fsp--;

             current =iv_ruleTopLevel; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleTopLevel83); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleTopLevel


    // $ANTLR start ruleTopLevel
    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:80:1: ruleTopLevel returns [EObject current=null] : ( (lv_decls_0= ruleDecl )* ( (lv_pattern_1= rulePattern ) | (lv_grammarContent_2= ruleGrammarContent )* ) ) ;
    public final EObject ruleTopLevel() throws RecognitionException {
        EObject current = null;

        EObject lv_decls_0 = null;

        EObject lv_pattern_1 = null;

        EObject lv_grammarContent_2 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:85:6: ( ( (lv_decls_0= ruleDecl )* ( (lv_pattern_1= rulePattern ) | (lv_grammarContent_2= ruleGrammarContent )* ) ) )
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:86:1: ( (lv_decls_0= ruleDecl )* ( (lv_pattern_1= rulePattern ) | (lv_grammarContent_2= ruleGrammarContent )* ) )
            {
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:86:1: ( (lv_decls_0= ruleDecl )* ( (lv_pattern_1= rulePattern ) | (lv_grammarContent_2= ruleGrammarContent )* ) )
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:86:2: (lv_decls_0= ruleDecl )* ( (lv_pattern_1= rulePattern ) | (lv_grammarContent_2= ruleGrammarContent )* )
            {
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:86:2: (lv_decls_0= ruleDecl )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==11||(LA1_0>=13 && LA1_0<=14)) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:89:6: lv_decls_0= ruleDecl
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getTopLevelAccess().getDeclsDeclParserRuleCall_0_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleDecl_in_ruleTopLevel142);
            	    lv_decls_0=ruleDecl();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getTopLevelRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        
            	    	        try {
            	    	       		add(current, "decls", lv_decls_0, "Decl", currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:107:3: ( (lv_pattern_1= rulePattern ) | (lv_grammarContent_2= ruleGrammarContent )* )
            int alt3=2;
            switch ( input.LA(1) ) {
            case RULE_STRING:
            case 15:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 29:
            case 34:
            case 41:
            case 42:
                {
                alt3=1;
                }
                break;
            case RULE_ID:
                {
                switch ( input.LA(2) ) {
                case EOF:
                case 45:
                    {
                    alt3=1;
                    }
                    break;
                case 12:
                    {
                    int LA3_4 = input.LA(3);

                    if ( ((LA3_4>=RULE_ID && LA3_4<=RULE_STRING)||LA3_4==15||(LA3_4>=18 && LA3_4<=26)||LA3_4==29||LA3_4==34||(LA3_4>=41 && LA3_4<=42)) ) {
                        alt3=2;
                    }
                    else if ( (LA3_4==EOF) ) {
                        alt3=1;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("107:3: ( (lv_pattern_1= rulePattern ) | (lv_grammarContent_2= ruleGrammarContent )* )", 3, 4, input);

                        throw nvae;
                    }
                    }
                    break;
                case 39:
                case 40:
                    {
                    alt3=2;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("107:3: ( (lv_pattern_1= rulePattern ) | (lv_grammarContent_2= ruleGrammarContent )* )", 3, 2, input);

                    throw nvae;
                }

                }
                break;
            case EOF:
            case 36:
            case 37:
            case 38:
                {
                alt3=2;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("107:3: ( (lv_pattern_1= rulePattern ) | (lv_grammarContent_2= ruleGrammarContent )* )", 3, 0, input);

                throw nvae;
            }

            switch (alt3) {
                case 1 :
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:107:4: (lv_pattern_1= rulePattern )
                    {
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:107:4: (lv_pattern_1= rulePattern )
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:110:6: lv_pattern_1= rulePattern
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getTopLevelAccess().getPatternPatternParserRuleCall_1_0_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_rulePattern_in_ruleTopLevel182);
                    lv_pattern_1=rulePattern();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getTopLevelRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "pattern", lv_pattern_1, "Pattern", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }
                    break;
                case 2 :
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:129:6: (lv_grammarContent_2= ruleGrammarContent )*
                    {
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:129:6: (lv_grammarContent_2= ruleGrammarContent )*
                    loop2:
                    do {
                        int alt2=2;
                        int LA2_0 = input.LA(1);

                        if ( (LA2_0==RULE_ID||(LA2_0>=36 && LA2_0<=38)) ) {
                            alt2=1;
                        }


                        switch (alt2) {
                    	case 1 :
                    	    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:132:6: lv_grammarContent_2= ruleGrammarContent
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getTopLevelAccess().getGrammarContentGrammarContentParserRuleCall_1_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleGrammarContent_in_ruleTopLevel226);
                    	    lv_grammarContent_2=ruleGrammarContent();
                    	    _fsp--;


                    	    	        if (current==null) {
                    	    	            current = factory.create(grammarAccess.getTopLevelRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	    	        }
                    	    	        
                    	    	        try {
                    	    	       		add(current, "grammarContent", lv_grammarContent_2, "GrammarContent", currentNode);
                    	    	        } catch (ValueConverterException vce) {
                    	    				handleValueConverterException(vce);
                    	    	        }
                    	    	        currentNode = currentNode.getParent();
                    	    	    

                    	    }
                    	    break;

                    	default :
                    	    break loop2;
                        }
                    } while (true);


                    }
                    break;

            }


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleTopLevel


    // $ANTLR start entryRuleDecl
    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:157:1: entryRuleDecl returns [EObject current=null] : iv_ruleDecl= ruleDecl EOF ;
    public final EObject entryRuleDecl() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDecl = null;


        try {
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:157:46: (iv_ruleDecl= ruleDecl EOF )
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:158:2: iv_ruleDecl= ruleDecl EOF
            {
             currentNode = createCompositeNode(grammarAccess.getDeclRule(), currentNode); 
            pushFollow(FOLLOW_ruleDecl_in_entryRuleDecl265);
            iv_ruleDecl=ruleDecl();
            _fsp--;

             current =iv_ruleDecl; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleDecl275); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleDecl


    // $ANTLR start ruleDecl
    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:165:1: ruleDecl returns [EObject current=null] : ( ( 'namespace' (lv_prefix_1= RULE_ID ) '=' (lv_uri_3= RULE_STRING ) ) | ( 'default' 'namespace' (lv_prefix_6= RULE_ID ) '=' (lv_uri_8= RULE_STRING ) ) | ( 'datatypes' (lv_datatypeId_10= RULE_ID ) '=' (lv_value_12= RULE_STRING ) ) ) ;
    public final EObject ruleDecl() throws RecognitionException {
        EObject current = null;

        Token lv_prefix_1=null;
        Token lv_uri_3=null;
        Token lv_prefix_6=null;
        Token lv_uri_8=null;
        Token lv_datatypeId_10=null;
        Token lv_value_12=null;

         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:170:6: ( ( ( 'namespace' (lv_prefix_1= RULE_ID ) '=' (lv_uri_3= RULE_STRING ) ) | ( 'default' 'namespace' (lv_prefix_6= RULE_ID ) '=' (lv_uri_8= RULE_STRING ) ) | ( 'datatypes' (lv_datatypeId_10= RULE_ID ) '=' (lv_value_12= RULE_STRING ) ) ) )
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:171:1: ( ( 'namespace' (lv_prefix_1= RULE_ID ) '=' (lv_uri_3= RULE_STRING ) ) | ( 'default' 'namespace' (lv_prefix_6= RULE_ID ) '=' (lv_uri_8= RULE_STRING ) ) | ( 'datatypes' (lv_datatypeId_10= RULE_ID ) '=' (lv_value_12= RULE_STRING ) ) )
            {
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:171:1: ( ( 'namespace' (lv_prefix_1= RULE_ID ) '=' (lv_uri_3= RULE_STRING ) ) | ( 'default' 'namespace' (lv_prefix_6= RULE_ID ) '=' (lv_uri_8= RULE_STRING ) ) | ( 'datatypes' (lv_datatypeId_10= RULE_ID ) '=' (lv_value_12= RULE_STRING ) ) )
            int alt4=3;
            switch ( input.LA(1) ) {
            case 11:
                {
                alt4=1;
                }
                break;
            case 13:
                {
                alt4=2;
                }
                break;
            case 14:
                {
                alt4=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("171:1: ( ( 'namespace' (lv_prefix_1= RULE_ID ) '=' (lv_uri_3= RULE_STRING ) ) | ( 'default' 'namespace' (lv_prefix_6= RULE_ID ) '=' (lv_uri_8= RULE_STRING ) ) | ( 'datatypes' (lv_datatypeId_10= RULE_ID ) '=' (lv_value_12= RULE_STRING ) ) )", 4, 0, input);

                throw nvae;
            }

            switch (alt4) {
                case 1 :
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:171:2: ( 'namespace' (lv_prefix_1= RULE_ID ) '=' (lv_uri_3= RULE_STRING ) )
                    {
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:171:2: ( 'namespace' (lv_prefix_1= RULE_ID ) '=' (lv_uri_3= RULE_STRING ) )
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:171:3: 'namespace' (lv_prefix_1= RULE_ID ) '=' (lv_uri_3= RULE_STRING )
                    {
                    match(input,11,FOLLOW_11_in_ruleDecl310); 

                            createLeafNode(grammarAccess.getDeclAccess().getNamespaceKeyword_0_0(), null); 
                        
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:175:1: (lv_prefix_1= RULE_ID )
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:177:6: lv_prefix_1= RULE_ID
                    {
                    lv_prefix_1=(Token)input.LT(1);
                    match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleDecl332); 

                    		createLeafNode(grammarAccess.getDeclAccess().getPrefixIDTerminalRuleCall_0_1_0(), "prefix"); 
                    	

                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getDeclRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "prefix", lv_prefix_1, "ID", lastConsumedNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	    

                    }

                    match(input,12,FOLLOW_12_in_ruleDecl349); 

                            createLeafNode(grammarAccess.getDeclAccess().getEqualsSignKeyword_0_2(), null); 
                        
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:199:1: (lv_uri_3= RULE_STRING )
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:201:6: lv_uri_3= RULE_STRING
                    {
                    lv_uri_3=(Token)input.LT(1);
                    match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleDecl371); 

                    		createLeafNode(grammarAccess.getDeclAccess().getUriSTRINGTerminalRuleCall_0_3_0(), "uri"); 
                    	

                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getDeclRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "uri", lv_uri_3, "STRING", lastConsumedNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	    

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:220:6: ( 'default' 'namespace' (lv_prefix_6= RULE_ID ) '=' (lv_uri_8= RULE_STRING ) )
                    {
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:220:6: ( 'default' 'namespace' (lv_prefix_6= RULE_ID ) '=' (lv_uri_8= RULE_STRING ) )
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:220:7: 'default' 'namespace' (lv_prefix_6= RULE_ID ) '=' (lv_uri_8= RULE_STRING )
                    {
                    match(input,13,FOLLOW_13_in_ruleDecl396); 

                            createLeafNode(grammarAccess.getDeclAccess().getDefaultKeyword_1_0(), null); 
                        
                    match(input,11,FOLLOW_11_in_ruleDecl405); 

                            createLeafNode(grammarAccess.getDeclAccess().getNamespaceKeyword_1_1(), null); 
                        
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:228:1: (lv_prefix_6= RULE_ID )
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:230:6: lv_prefix_6= RULE_ID
                    {
                    lv_prefix_6=(Token)input.LT(1);
                    match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleDecl427); 

                    		createLeafNode(grammarAccess.getDeclAccess().getPrefixIDTerminalRuleCall_1_2_0(), "prefix"); 
                    	

                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getDeclRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "prefix", lv_prefix_6, "ID", lastConsumedNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	    

                    }

                    match(input,12,FOLLOW_12_in_ruleDecl444); 

                            createLeafNode(grammarAccess.getDeclAccess().getEqualsSignKeyword_1_3(), null); 
                        
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:252:1: (lv_uri_8= RULE_STRING )
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:254:6: lv_uri_8= RULE_STRING
                    {
                    lv_uri_8=(Token)input.LT(1);
                    match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleDecl466); 

                    		createLeafNode(grammarAccess.getDeclAccess().getUriSTRINGTerminalRuleCall_1_4_0(), "uri"); 
                    	

                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getDeclRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "uri", lv_uri_8, "STRING", lastConsumedNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	    

                    }


                    }


                    }
                    break;
                case 3 :
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:273:6: ( 'datatypes' (lv_datatypeId_10= RULE_ID ) '=' (lv_value_12= RULE_STRING ) )
                    {
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:273:6: ( 'datatypes' (lv_datatypeId_10= RULE_ID ) '=' (lv_value_12= RULE_STRING ) )
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:273:7: 'datatypes' (lv_datatypeId_10= RULE_ID ) '=' (lv_value_12= RULE_STRING )
                    {
                    match(input,14,FOLLOW_14_in_ruleDecl491); 

                            createLeafNode(grammarAccess.getDeclAccess().getDatatypesKeyword_2_0(), null); 
                        
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:277:1: (lv_datatypeId_10= RULE_ID )
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:279:6: lv_datatypeId_10= RULE_ID
                    {
                    lv_datatypeId_10=(Token)input.LT(1);
                    match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleDecl513); 

                    		createLeafNode(grammarAccess.getDeclAccess().getDatatypeIdIDTerminalRuleCall_2_1_0(), "datatypeId"); 
                    	

                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getDeclRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "datatypeId", lv_datatypeId_10, "ID", lastConsumedNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	    

                    }

                    match(input,12,FOLLOW_12_in_ruleDecl530); 

                            createLeafNode(grammarAccess.getDeclAccess().getEqualsSignKeyword_2_2(), null); 
                        
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:301:1: (lv_value_12= RULE_STRING )
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:303:6: lv_value_12= RULE_STRING
                    {
                    lv_value_12=(Token)input.LT(1);
                    match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleDecl552); 

                    		createLeafNode(grammarAccess.getDeclAccess().getValueSTRINGTerminalRuleCall_2_3_0(), "value"); 
                    	

                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getDeclRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "value", lv_value_12, "STRING", lastConsumedNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	    

                    }


                    }


                    }
                    break;

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleDecl


    // $ANTLR start entryRulePattern
    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:328:1: entryRulePattern returns [EObject current=null] : iv_rulePattern= rulePattern EOF ;
    public final EObject entryRulePattern() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePattern = null;


        try {
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:328:49: (iv_rulePattern= rulePattern EOF )
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:329:2: iv_rulePattern= rulePattern EOF
            {
             currentNode = createCompositeNode(grammarAccess.getPatternRule(), currentNode); 
            pushFollow(FOLLOW_rulePattern_in_entryRulePattern594);
            iv_rulePattern=rulePattern();
            _fsp--;

             current =iv_rulePattern; 
            match(input,EOF,FOLLOW_EOF_in_entryRulePattern604); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRulePattern


    // $ANTLR start rulePattern
    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:336:1: rulePattern returns [EObject current=null] : ( (lv_val_0= ( ruleElement | ruleAttribute ) )+ | ( 'list' '{' (lv_pattern_3= rulePattern )* '}' ) | ( 'mixed' '{' (lv_pattern_7= rulePattern )* '}' ) | ( RULE_ID ( '=' )? ) | ( 'parent' RULE_ID ) | 'empty' | 'text' | '|' | (lv_value_16= ruleDataTypeValue )+ | ( ruleDataTypeName ( '{' (lv_param_19= ruleParam )* '}' )* (lv_exceptPattern_21= ruleExceptPattern )* ) | 'notAllowed' | ( 'external' (lv_uri_24= ruleAnyURILiteral ) (lv_inherit_25= ruleInherit )* ) | ( 'grammar' '{' (lv_grammarContent_28= ruleGrammarContent )* '}' ) | ( '(' (lv_group_31= rulePattern )* ')' (lv_continue_33= ',' )? ) ) ;
    public final EObject rulePattern() throws RecognitionException {
        EObject current = null;

        Token lv_val_0=null;
        Token lv_continue_33=null;
        EObject lv_pattern_3 = null;

        EObject lv_pattern_7 = null;

        EObject lv_value_16 = null;

        EObject lv_param_19 = null;

        EObject lv_exceptPattern_21 = null;

        EObject lv_uri_24 = null;

        EObject lv_inherit_25 = null;

        EObject lv_grammarContent_28 = null;

        EObject lv_group_31 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:341:6: ( ( (lv_val_0= ( ruleElement | ruleAttribute ) )+ | ( 'list' '{' (lv_pattern_3= rulePattern )* '}' ) | ( 'mixed' '{' (lv_pattern_7= rulePattern )* '}' ) | ( RULE_ID ( '=' )? ) | ( 'parent' RULE_ID ) | 'empty' | 'text' | '|' | (lv_value_16= ruleDataTypeValue )+ | ( ruleDataTypeName ( '{' (lv_param_19= ruleParam )* '}' )* (lv_exceptPattern_21= ruleExceptPattern )* ) | 'notAllowed' | ( 'external' (lv_uri_24= ruleAnyURILiteral ) (lv_inherit_25= ruleInherit )* ) | ( 'grammar' '{' (lv_grammarContent_28= ruleGrammarContent )* '}' ) | ( '(' (lv_group_31= rulePattern )* ')' (lv_continue_33= ',' )? ) ) )
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:342:1: ( (lv_val_0= ( ruleElement | ruleAttribute ) )+ | ( 'list' '{' (lv_pattern_3= rulePattern )* '}' ) | ( 'mixed' '{' (lv_pattern_7= rulePattern )* '}' ) | ( RULE_ID ( '=' )? ) | ( 'parent' RULE_ID ) | 'empty' | 'text' | '|' | (lv_value_16= ruleDataTypeValue )+ | ( ruleDataTypeName ( '{' (lv_param_19= ruleParam )* '}' )* (lv_exceptPattern_21= ruleExceptPattern )* ) | 'notAllowed' | ( 'external' (lv_uri_24= ruleAnyURILiteral ) (lv_inherit_25= ruleInherit )* ) | ( 'grammar' '{' (lv_grammarContent_28= ruleGrammarContent )* '}' ) | ( '(' (lv_group_31= rulePattern )* ')' (lv_continue_33= ',' )? ) )
            {
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:342:1: ( (lv_val_0= ( ruleElement | ruleAttribute ) )+ | ( 'list' '{' (lv_pattern_3= rulePattern )* '}' ) | ( 'mixed' '{' (lv_pattern_7= rulePattern )* '}' ) | ( RULE_ID ( '=' )? ) | ( 'parent' RULE_ID ) | 'empty' | 'text' | '|' | (lv_value_16= ruleDataTypeValue )+ | ( ruleDataTypeName ( '{' (lv_param_19= ruleParam )* '}' )* (lv_exceptPattern_21= ruleExceptPattern )* ) | 'notAllowed' | ( 'external' (lv_uri_24= ruleAnyURILiteral ) (lv_inherit_25= ruleInherit )* ) | ( 'grammar' '{' (lv_grammarContent_28= ruleGrammarContent )* '}' ) | ( '(' (lv_group_31= rulePattern )* ')' (lv_continue_33= ',' )? ) )
            int alt18=14;
            switch ( input.LA(1) ) {
            case 29:
            case 34:
                {
                alt18=1;
                }
                break;
            case 15:
                {
                alt18=2;
                }
                break;
            case 18:
                {
                alt18=3;
                }
                break;
            case RULE_ID:
                {
                int LA18_4 = input.LA(2);

                if ( (LA18_4==45) ) {
                    alt18=10;
                }
                else if ( (LA18_4==EOF||(LA18_4>=RULE_ID && LA18_4<=RULE_STRING)||LA18_4==12||LA18_4==15||(LA18_4>=17 && LA18_4<=27)||LA18_4==29||(LA18_4>=34 && LA18_4<=38)||(LA18_4>=41 && LA18_4<=42)) ) {
                    alt18=4;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("342:1: ( (lv_val_0= ( ruleElement | ruleAttribute ) )+ | ( 'list' '{' (lv_pattern_3= rulePattern )* '}' ) | ( 'mixed' '{' (lv_pattern_7= rulePattern )* '}' ) | ( RULE_ID ( '=' )? ) | ( 'parent' RULE_ID ) | 'empty' | 'text' | '|' | (lv_value_16= ruleDataTypeValue )+ | ( ruleDataTypeName ( '{' (lv_param_19= ruleParam )* '}' )* (lv_exceptPattern_21= ruleExceptPattern )* ) | 'notAllowed' | ( 'external' (lv_uri_24= ruleAnyURILiteral ) (lv_inherit_25= ruleInherit )* ) | ( 'grammar' '{' (lv_grammarContent_28= ruleGrammarContent )* '}' ) | ( '(' (lv_group_31= rulePattern )* ')' (lv_continue_33= ',' )? ) )", 18, 4, input);

                    throw nvae;
                }
                }
                break;
            case 19:
                {
                alt18=5;
                }
                break;
            case 20:
                {
                alt18=6;
                }
                break;
            case 21:
                {
                alt18=7;
                }
                break;
            case 22:
                {
                alt18=8;
                }
                break;
            case RULE_STRING:
                {
                alt18=9;
                }
                break;
            case 41:
            case 42:
                {
                alt18=10;
                }
                break;
            case 23:
                {
                alt18=11;
                }
                break;
            case 24:
                {
                alt18=12;
                }
                break;
            case 25:
                {
                alt18=13;
                }
                break;
            case 26:
                {
                alt18=14;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("342:1: ( (lv_val_0= ( ruleElement | ruleAttribute ) )+ | ( 'list' '{' (lv_pattern_3= rulePattern )* '}' ) | ( 'mixed' '{' (lv_pattern_7= rulePattern )* '}' ) | ( RULE_ID ( '=' )? ) | ( 'parent' RULE_ID ) | 'empty' | 'text' | '|' | (lv_value_16= ruleDataTypeValue )+ | ( ruleDataTypeName ( '{' (lv_param_19= ruleParam )* '}' )* (lv_exceptPattern_21= ruleExceptPattern )* ) | 'notAllowed' | ( 'external' (lv_uri_24= ruleAnyURILiteral ) (lv_inherit_25= ruleInherit )* ) | ( 'grammar' '{' (lv_grammarContent_28= ruleGrammarContent )* '}' ) | ( '(' (lv_group_31= rulePattern )* ')' (lv_continue_33= ',' )? ) )", 18, 0, input);

                throw nvae;
            }

            switch (alt18) {
                case 1 :
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:342:2: (lv_val_0= ( ruleElement | ruleAttribute ) )+
                    {
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:342:2: (lv_val_0= ( ruleElement | ruleAttribute ) )+
                    int cnt6=0;
                    loop6:
                    do {
                        int alt6=2;
                        int LA6_0 = input.LA(1);

                        if ( (LA6_0==29) ) {
                            alt6=1;
                        }
                        else if ( (LA6_0==34) ) {
                            alt6=1;
                        }


                        switch (alt6) {
                    	case 1 :
                    	    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:344:6: lv_val_0= ( ruleElement | ruleAttribute )
                    	    {
                    	    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:344:15: ( ruleElement | ruleAttribute )
                    	    int alt5=2;
                    	    int LA5_0 = input.LA(1);

                    	    if ( (LA5_0==29) ) {
                    	        alt5=1;
                    	    }
                    	    else if ( (LA5_0==34) ) {
                    	        alt5=2;
                    	    }
                    	    else {
                    	        NoViableAltException nvae =
                    	            new NoViableAltException("344:15: ( ruleElement | ruleAttribute )", 5, 0, input);

                    	        throw nvae;
                    	    }
                    	    switch (alt5) {
                    	        case 1 :
                    	            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:344:17: ruleElement
                    	            {
                    	             
                    	                    currentNode=createCompositeNode(grammarAccess.getPatternAccess().getValElementParserRuleCall_0_0_0(), currentNode); 
                    	                
                    	            pushFollow(FOLLOW_ruleElement_in_rulePattern655);
                    	            ruleElement();
                    	            _fsp--;

                    	             
                    	                    currentNode = currentNode.getParent();
                    	                

                    	            }
                    	            break;
                    	        case 2 :
                    	            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:351:7: ruleAttribute
                    	            {
                    	             
                    	                    currentNode=createCompositeNode(grammarAccess.getPatternAccess().getValAttributeParserRuleCall_0_0_1(), currentNode); 
                    	                
                    	            pushFollow(FOLLOW_ruleAttribute_in_rulePattern669);
                    	            ruleAttribute();
                    	            _fsp--;

                    	             
                    	                    currentNode = currentNode.getParent();
                    	                

                    	            }
                    	            break;

                    	    }


                    	    	        if (current==null) {
                    	    	            current = factory.create(grammarAccess.getPatternRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode, current);
                    	    	        }
                    	    	        
                    	    	        try {
                    	    	       		set(current, "val", lv_val_0, null, lastConsumedNode);
                    	    	        } catch (ValueConverterException vce) {
                    	    				handleValueConverterException(vce);
                    	    	        }
                    	    	    

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
                    break;
                case 2 :
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:373:6: ( 'list' '{' (lv_pattern_3= rulePattern )* '}' )
                    {
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:373:6: ( 'list' '{' (lv_pattern_3= rulePattern )* '}' )
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:373:7: 'list' '{' (lv_pattern_3= rulePattern )* '}'
                    {
                    match(input,15,FOLLOW_15_in_rulePattern695); 

                            createLeafNode(grammarAccess.getPatternAccess().getListKeyword_1_0(), null); 
                        
                    match(input,16,FOLLOW_16_in_rulePattern704); 

                            createLeafNode(grammarAccess.getPatternAccess().getLeftCurlyBracketKeyword_1_1(), null); 
                        
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:381:1: (lv_pattern_3= rulePattern )*
                    loop7:
                    do {
                        int alt7=2;
                        int LA7_0 = input.LA(1);

                        if ( ((LA7_0>=RULE_ID && LA7_0<=RULE_STRING)||LA7_0==15||(LA7_0>=18 && LA7_0<=26)||LA7_0==29||LA7_0==34||(LA7_0>=41 && LA7_0<=42)) ) {
                            alt7=1;
                        }


                        switch (alt7) {
                    	case 1 :
                    	    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:384:6: lv_pattern_3= rulePattern
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getPatternAccess().getPatternPatternParserRuleCall_1_2_0(), currentNode); 
                    	    	    
                    	    pushFollow(FOLLOW_rulePattern_in_rulePattern738);
                    	    lv_pattern_3=rulePattern();
                    	    _fsp--;


                    	    	        if (current==null) {
                    	    	            current = factory.create(grammarAccess.getPatternRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	    	        }
                    	    	        
                    	    	        try {
                    	    	       		add(current, "pattern", lv_pattern_3, "Pattern", currentNode);
                    	    	        } catch (ValueConverterException vce) {
                    	    				handleValueConverterException(vce);
                    	    	        }
                    	    	        currentNode = currentNode.getParent();
                    	    	    

                    	    }
                    	    break;

                    	default :
                    	    break loop7;
                        }
                    } while (true);

                    match(input,17,FOLLOW_17_in_rulePattern752); 

                            createLeafNode(grammarAccess.getPatternAccess().getRightCurlyBracketKeyword_1_3(), null); 
                        

                    }


                    }
                    break;
                case 3 :
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:407:6: ( 'mixed' '{' (lv_pattern_7= rulePattern )* '}' )
                    {
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:407:6: ( 'mixed' '{' (lv_pattern_7= rulePattern )* '}' )
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:407:7: 'mixed' '{' (lv_pattern_7= rulePattern )* '}'
                    {
                    match(input,18,FOLLOW_18_in_rulePattern769); 

                            createLeafNode(grammarAccess.getPatternAccess().getMixedKeyword_2_0(), null); 
                        
                    match(input,16,FOLLOW_16_in_rulePattern778); 

                            createLeafNode(grammarAccess.getPatternAccess().getLeftCurlyBracketKeyword_2_1(), null); 
                        
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:415:1: (lv_pattern_7= rulePattern )*
                    loop8:
                    do {
                        int alt8=2;
                        int LA8_0 = input.LA(1);

                        if ( ((LA8_0>=RULE_ID && LA8_0<=RULE_STRING)||LA8_0==15||(LA8_0>=18 && LA8_0<=26)||LA8_0==29||LA8_0==34||(LA8_0>=41 && LA8_0<=42)) ) {
                            alt8=1;
                        }


                        switch (alt8) {
                    	case 1 :
                    	    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:418:6: lv_pattern_7= rulePattern
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getPatternAccess().getPatternPatternParserRuleCall_2_2_0(), currentNode); 
                    	    	    
                    	    pushFollow(FOLLOW_rulePattern_in_rulePattern812);
                    	    lv_pattern_7=rulePattern();
                    	    _fsp--;


                    	    	        if (current==null) {
                    	    	            current = factory.create(grammarAccess.getPatternRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	    	        }
                    	    	        
                    	    	        try {
                    	    	       		add(current, "pattern", lv_pattern_7, "Pattern", currentNode);
                    	    	        } catch (ValueConverterException vce) {
                    	    				handleValueConverterException(vce);
                    	    	        }
                    	    	        currentNode = currentNode.getParent();
                    	    	    

                    	    }
                    	    break;

                    	default :
                    	    break loop8;
                        }
                    } while (true);

                    match(input,17,FOLLOW_17_in_rulePattern826); 

                            createLeafNode(grammarAccess.getPatternAccess().getRightCurlyBracketKeyword_2_3(), null); 
                        

                    }


                    }
                    break;
                case 4 :
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:441:6: ( RULE_ID ( '=' )? )
                    {
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:441:6: ( RULE_ID ( '=' )? )
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:441:7: RULE_ID ( '=' )?
                    {
                    match(input,RULE_ID,FOLLOW_RULE_ID_in_rulePattern843); 
                     
                        createLeafNode(grammarAccess.getPatternAccess().getIDTerminalRuleCall_3_0(), null); 
                        
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:445:1: ( '=' )?
                    int alt9=2;
                    int LA9_0 = input.LA(1);

                    if ( (LA9_0==12) ) {
                        alt9=1;
                    }
                    switch (alt9) {
                        case 1 :
                            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:445:2: '='
                            {
                            match(input,12,FOLLOW_12_in_rulePattern852); 

                                    createLeafNode(grammarAccess.getPatternAccess().getEqualsSignKeyword_3_1(), null); 
                                

                            }
                            break;

                    }


                    }


                    }
                    break;
                case 5 :
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:450:6: ( 'parent' RULE_ID )
                    {
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:450:6: ( 'parent' RULE_ID )
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:450:7: 'parent' RULE_ID
                    {
                    match(input,19,FOLLOW_19_in_rulePattern871); 

                            createLeafNode(grammarAccess.getPatternAccess().getParentKeyword_4_0(), null); 
                        
                    match(input,RULE_ID,FOLLOW_RULE_ID_in_rulePattern880); 
                     
                        createLeafNode(grammarAccess.getPatternAccess().getIDTerminalRuleCall_4_1(), null); 
                        

                    }


                    }
                    break;
                case 6 :
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:459:6: 'empty'
                    {
                    match(input,20,FOLLOW_20_in_rulePattern895); 

                            createLeafNode(grammarAccess.getPatternAccess().getEmptyKeyword_5(), null); 
                        

                    }
                    break;
                case 7 :
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:464:6: 'text'
                    {
                    match(input,21,FOLLOW_21_in_rulePattern910); 

                            createLeafNode(grammarAccess.getPatternAccess().getTextKeyword_6(), null); 
                        

                    }
                    break;
                case 8 :
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:469:6: '|'
                    {
                    match(input,22,FOLLOW_22_in_rulePattern925); 

                            createLeafNode(grammarAccess.getPatternAccess().getVerticalLineKeyword_7(), null); 
                        

                    }
                    break;
                case 9 :
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:474:6: (lv_value_16= ruleDataTypeValue )+
                    {
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:474:6: (lv_value_16= ruleDataTypeValue )+
                    int cnt10=0;
                    loop10:
                    do {
                        int alt10=2;
                        int LA10_0 = input.LA(1);

                        if ( (LA10_0==RULE_STRING) ) {
                            alt10=1;
                        }


                        switch (alt10) {
                    	case 1 :
                    	    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:477:6: lv_value_16= ruleDataTypeValue
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getPatternAccess().getValueDataTypeValueParserRuleCall_8_0(), currentNode); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleDataTypeValue_in_rulePattern965);
                    	    lv_value_16=ruleDataTypeValue();
                    	    _fsp--;


                    	    	        if (current==null) {
                    	    	            current = factory.create(grammarAccess.getPatternRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	    	        }
                    	    	        
                    	    	        try {
                    	    	       		set(current, "value", lv_value_16, "DataTypeValue", currentNode);
                    	    	        } catch (ValueConverterException vce) {
                    	    				handleValueConverterException(vce);
                    	    	        }
                    	    	        currentNode = currentNode.getParent();
                    	    	    

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt10 >= 1 ) break loop10;
                                EarlyExitException eee =
                                    new EarlyExitException(10, input);
                                throw eee;
                        }
                        cnt10++;
                    } while (true);


                    }
                    break;
                case 10 :
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:496:6: ( ruleDataTypeName ( '{' (lv_param_19= ruleParam )* '}' )* (lv_exceptPattern_21= ruleExceptPattern )* )
                    {
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:496:6: ( ruleDataTypeName ( '{' (lv_param_19= ruleParam )* '}' )* (lv_exceptPattern_21= ruleExceptPattern )* )
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:496:7: ruleDataTypeName ( '{' (lv_param_19= ruleParam )* '}' )* (lv_exceptPattern_21= ruleExceptPattern )*
                    {
                    pushFollow(FOLLOW_ruleDataTypeName_in_rulePattern986);
                    ruleDataTypeName();
                    _fsp--;

                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:496:23: ( '{' (lv_param_19= ruleParam )* '}' )*
                    loop12:
                    do {
                        int alt12=2;
                        int LA12_0 = input.LA(1);

                        if ( (LA12_0==16) ) {
                            alt12=1;
                        }


                        switch (alt12) {
                    	case 1 :
                    	    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:496:24: '{' (lv_param_19= ruleParam )* '}'
                    	    {
                    	    match(input,16,FOLLOW_16_in_rulePattern988); 

                    	            createLeafNode(grammarAccess.getPatternAccess().getLeftCurlyBracketKeyword_9_1_0(), null); 
                    	        
                    	    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:500:1: (lv_param_19= ruleParam )*
                    	    loop11:
                    	    do {
                    	        int alt11=2;
                    	        int LA11_0 = input.LA(1);

                    	        if ( (LA11_0==RULE_ID||LA11_0==11||(LA11_0>=13 && LA11_0<=15)||(LA11_0>=18 && LA11_0<=21)||(LA11_0>=23 && LA11_0<=25)||LA11_0==29||LA11_0==34||(LA11_0>=36 && LA11_0<=38)||(LA11_0>=41 && LA11_0<=44)) ) {
                    	            alt11=1;
                    	        }


                    	        switch (alt11) {
                    	    	case 1 :
                    	    	    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:503:6: lv_param_19= ruleParam
                    	    	    {
                    	    	     
                    	    	    	        currentNode=createCompositeNode(grammarAccess.getPatternAccess().getParamParamParserRuleCall_9_1_1_0(), currentNode); 
                    	    	    	    
                    	    	    pushFollow(FOLLOW_ruleParam_in_rulePattern1022);
                    	    	    lv_param_19=ruleParam();
                    	    	    _fsp--;


                    	    	    	        if (current==null) {
                    	    	    	            current = factory.create(grammarAccess.getPatternRule().getType().getClassifier());
                    	    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	    	    	        }
                    	    	    	        
                    	    	    	        try {
                    	    	    	       		add(current, "param", lv_param_19, "Param", currentNode);
                    	    	    	        } catch (ValueConverterException vce) {
                    	    	    				handleValueConverterException(vce);
                    	    	    	        }
                    	    	    	        currentNode = currentNode.getParent();
                    	    	    	    

                    	    	    }
                    	    	    break;

                    	    	default :
                    	    	    break loop11;
                    	        }
                    	    } while (true);

                    	    match(input,17,FOLLOW_17_in_rulePattern1036); 

                    	            createLeafNode(grammarAccess.getPatternAccess().getRightCurlyBracketKeyword_9_1_2(), null); 
                    	        

                    	    }
                    	    break;

                    	default :
                    	    break loop12;
                        }
                    } while (true);

                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:525:3: (lv_exceptPattern_21= ruleExceptPattern )*
                    loop13:
                    do {
                        int alt13=2;
                        int LA13_0 = input.LA(1);

                        if ( (LA13_0==35) ) {
                            alt13=1;
                        }


                        switch (alt13) {
                    	case 1 :
                    	    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:528:6: lv_exceptPattern_21= ruleExceptPattern
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getPatternAccess().getExceptPatternExceptPatternParserRuleCall_9_2_0(), currentNode); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleExceptPattern_in_rulePattern1072);
                    	    lv_exceptPattern_21=ruleExceptPattern();
                    	    _fsp--;


                    	    	        if (current==null) {
                    	    	            current = factory.create(grammarAccess.getPatternRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	    	        }
                    	    	        
                    	    	        try {
                    	    	       		set(current, "exceptPattern", lv_exceptPattern_21, "ExceptPattern", currentNode);
                    	    	        } catch (ValueConverterException vce) {
                    	    				handleValueConverterException(vce);
                    	    	        }
                    	    	        currentNode = currentNode.getParent();
                    	    	    

                    	    }
                    	    break;

                    	default :
                    	    break loop13;
                        }
                    } while (true);


                    }


                    }
                    break;
                case 11 :
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:547:6: 'notAllowed'
                    {
                    match(input,23,FOLLOW_23_in_rulePattern1093); 

                            createLeafNode(grammarAccess.getPatternAccess().getNotAllowedKeyword_10(), null); 
                        

                    }
                    break;
                case 12 :
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:552:6: ( 'external' (lv_uri_24= ruleAnyURILiteral ) (lv_inherit_25= ruleInherit )* )
                    {
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:552:6: ( 'external' (lv_uri_24= ruleAnyURILiteral ) (lv_inherit_25= ruleInherit )* )
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:552:7: 'external' (lv_uri_24= ruleAnyURILiteral ) (lv_inherit_25= ruleInherit )*
                    {
                    match(input,24,FOLLOW_24_in_rulePattern1109); 

                            createLeafNode(grammarAccess.getPatternAccess().getExternalKeyword_11_0(), null); 
                        
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:556:1: (lv_uri_24= ruleAnyURILiteral )
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:559:6: lv_uri_24= ruleAnyURILiteral
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getPatternAccess().getUriAnyURILiteralParserRuleCall_11_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleAnyURILiteral_in_rulePattern1143);
                    lv_uri_24=ruleAnyURILiteral();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getPatternRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "uri", lv_uri_24, "AnyURILiteral", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }

                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:577:2: (lv_inherit_25= ruleInherit )*
                    loop14:
                    do {
                        int alt14=2;
                        int LA14_0 = input.LA(1);

                        if ( (LA14_0==43) ) {
                            alt14=1;
                        }


                        switch (alt14) {
                    	case 1 :
                    	    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:580:6: lv_inherit_25= ruleInherit
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getPatternAccess().getInheritInheritParserRuleCall_11_2_0(), currentNode); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleInherit_in_rulePattern1181);
                    	    lv_inherit_25=ruleInherit();
                    	    _fsp--;


                    	    	        if (current==null) {
                    	    	            current = factory.create(grammarAccess.getPatternRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	    	        }
                    	    	        
                    	    	        try {
                    	    	       		set(current, "inherit", lv_inherit_25, "Inherit", currentNode);
                    	    	        } catch (ValueConverterException vce) {
                    	    				handleValueConverterException(vce);
                    	    	        }
                    	    	        currentNode = currentNode.getParent();
                    	    	    

                    	    }
                    	    break;

                    	default :
                    	    break loop14;
                        }
                    } while (true);


                    }


                    }
                    break;
                case 13 :
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:599:6: ( 'grammar' '{' (lv_grammarContent_28= ruleGrammarContent )* '}' )
                    {
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:599:6: ( 'grammar' '{' (lv_grammarContent_28= ruleGrammarContent )* '}' )
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:599:7: 'grammar' '{' (lv_grammarContent_28= ruleGrammarContent )* '}'
                    {
                    match(input,25,FOLLOW_25_in_rulePattern1203); 

                            createLeafNode(grammarAccess.getPatternAccess().getGrammarKeyword_12_0(), null); 
                        
                    match(input,16,FOLLOW_16_in_rulePattern1212); 

                            createLeafNode(grammarAccess.getPatternAccess().getLeftCurlyBracketKeyword_12_1(), null); 
                        
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:607:1: (lv_grammarContent_28= ruleGrammarContent )*
                    loop15:
                    do {
                        int alt15=2;
                        int LA15_0 = input.LA(1);

                        if ( (LA15_0==RULE_ID||(LA15_0>=36 && LA15_0<=38)) ) {
                            alt15=1;
                        }


                        switch (alt15) {
                    	case 1 :
                    	    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:610:6: lv_grammarContent_28= ruleGrammarContent
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getPatternAccess().getGrammarContentGrammarContentParserRuleCall_12_2_0(), currentNode); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleGrammarContent_in_rulePattern1246);
                    	    lv_grammarContent_28=ruleGrammarContent();
                    	    _fsp--;


                    	    	        if (current==null) {
                    	    	            current = factory.create(grammarAccess.getPatternRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	    	        }
                    	    	        
                    	    	        try {
                    	    	       		add(current, "grammarContent", lv_grammarContent_28, "GrammarContent", currentNode);
                    	    	        } catch (ValueConverterException vce) {
                    	    				handleValueConverterException(vce);
                    	    	        }
                    	    	        currentNode = currentNode.getParent();
                    	    	    

                    	    }
                    	    break;

                    	default :
                    	    break loop15;
                        }
                    } while (true);

                    match(input,17,FOLLOW_17_in_rulePattern1260); 

                            createLeafNode(grammarAccess.getPatternAccess().getRightCurlyBracketKeyword_12_3(), null); 
                        

                    }


                    }
                    break;
                case 14 :
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:633:6: ( '(' (lv_group_31= rulePattern )* ')' (lv_continue_33= ',' )? )
                    {
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:633:6: ( '(' (lv_group_31= rulePattern )* ')' (lv_continue_33= ',' )? )
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:633:7: '(' (lv_group_31= rulePattern )* ')' (lv_continue_33= ',' )?
                    {
                    match(input,26,FOLLOW_26_in_rulePattern1277); 

                            createLeafNode(grammarAccess.getPatternAccess().getLeftParenthesisKeyword_13_0(), null); 
                        
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:637:1: (lv_group_31= rulePattern )*
                    loop16:
                    do {
                        int alt16=2;
                        int LA16_0 = input.LA(1);

                        if ( ((LA16_0>=RULE_ID && LA16_0<=RULE_STRING)||LA16_0==15||(LA16_0>=18 && LA16_0<=26)||LA16_0==29||LA16_0==34||(LA16_0>=41 && LA16_0<=42)) ) {
                            alt16=1;
                        }


                        switch (alt16) {
                    	case 1 :
                    	    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:640:6: lv_group_31= rulePattern
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getPatternAccess().getGroupPatternParserRuleCall_13_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FOLLOW_rulePattern_in_rulePattern1311);
                    	    lv_group_31=rulePattern();
                    	    _fsp--;


                    	    	        if (current==null) {
                    	    	            current = factory.create(grammarAccess.getPatternRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	    	        }
                    	    	        
                    	    	        try {
                    	    	       		add(current, "group", lv_group_31, "Pattern", currentNode);
                    	    	        } catch (ValueConverterException vce) {
                    	    				handleValueConverterException(vce);
                    	    	        }
                    	    	        currentNode = currentNode.getParent();
                    	    	    

                    	    }
                    	    break;

                    	default :
                    	    break loop16;
                        }
                    } while (true);

                    match(input,27,FOLLOW_27_in_rulePattern1325); 

                            createLeafNode(grammarAccess.getPatternAccess().getRightParenthesisKeyword_13_2(), null); 
                        
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:662:1: (lv_continue_33= ',' )?
                    int alt17=2;
                    int LA17_0 = input.LA(1);

                    if ( (LA17_0==28) ) {
                        alt17=1;
                    }
                    switch (alt17) {
                        case 1 :
                            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:664:6: lv_continue_33= ','
                            {
                            lv_continue_33=(Token)input.LT(1);
                            match(input,28,FOLLOW_28_in_rulePattern1346); 

                                    createLeafNode(grammarAccess.getPatternAccess().getContinueCommaKeyword_13_3_0(), "continue"); 
                                

                            	        if (current==null) {
                            	            current = factory.create(grammarAccess.getPatternRule().getType().getClassifier());
                            	            associateNodeWithAstElement(currentNode, current);
                            	        }
                            	        
                            	        try {
                            	       		set(current, "continue", /* lv_continue_33 */ input.LT(-1), ",", lastConsumedNode);
                            	        } catch (ValueConverterException vce) {
                            				handleValueConverterException(vce);
                            	        }
                            	    

                            }
                            break;

                    }


                    }


                    }
                    break;

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end rulePattern


    // $ANTLR start entryRuleElement
    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:690:1: entryRuleElement returns [EObject current=null] : iv_ruleElement= ruleElement EOF ;
    public final EObject entryRuleElement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleElement = null;


        try {
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:690:49: (iv_ruleElement= ruleElement EOF )
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:691:2: iv_ruleElement= ruleElement EOF
            {
             currentNode = createCompositeNode(grammarAccess.getElementRule(), currentNode); 
            pushFollow(FOLLOW_ruleElement_in_entryRuleElement1394);
            iv_ruleElement=ruleElement();
            _fsp--;

             current =iv_ruleElement; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleElement1404); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleElement


    // $ANTLR start ruleElement
    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:698:1: ruleElement returns [EObject current=null] : ( 'element' (lv_name_1= ruleNameClass ) '{' (lv_pattern_3= rulePattern )* '}' (lv_cardinality_5= ( '?' | '+' | '*' ) )? (lv_continue_6= ( ',' | '&' | '|' ) )? ) ;
    public final EObject ruleElement() throws RecognitionException {
        EObject current = null;

        Token lv_cardinality_5=null;
        Token lv_continue_6=null;
        EObject lv_name_1 = null;

        EObject lv_pattern_3 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:703:6: ( ( 'element' (lv_name_1= ruleNameClass ) '{' (lv_pattern_3= rulePattern )* '}' (lv_cardinality_5= ( '?' | '+' | '*' ) )? (lv_continue_6= ( ',' | '&' | '|' ) )? ) )
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:704:1: ( 'element' (lv_name_1= ruleNameClass ) '{' (lv_pattern_3= rulePattern )* '}' (lv_cardinality_5= ( '?' | '+' | '*' ) )? (lv_continue_6= ( ',' | '&' | '|' ) )? )
            {
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:704:1: ( 'element' (lv_name_1= ruleNameClass ) '{' (lv_pattern_3= rulePattern )* '}' (lv_cardinality_5= ( '?' | '+' | '*' ) )? (lv_continue_6= ( ',' | '&' | '|' ) )? )
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:704:2: 'element' (lv_name_1= ruleNameClass ) '{' (lv_pattern_3= rulePattern )* '}' (lv_cardinality_5= ( '?' | '+' | '*' ) )? (lv_continue_6= ( ',' | '&' | '|' ) )?
            {
            match(input,29,FOLLOW_29_in_ruleElement1438); 

                    createLeafNode(grammarAccess.getElementAccess().getElementKeyword_0(), null); 
                
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:708:1: (lv_name_1= ruleNameClass )
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:711:6: lv_name_1= ruleNameClass
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getElementAccess().getNameNameClassParserRuleCall_1_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleNameClass_in_ruleElement1472);
            lv_name_1=ruleNameClass();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getElementRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        
            	        try {
            	       		set(current, "name", lv_name_1, "NameClass", currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }

            match(input,16,FOLLOW_16_in_ruleElement1485); 

                    createLeafNode(grammarAccess.getElementAccess().getLeftCurlyBracketKeyword_2(), null); 
                
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:733:1: (lv_pattern_3= rulePattern )*
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( ((LA19_0>=RULE_ID && LA19_0<=RULE_STRING)||LA19_0==15||(LA19_0>=18 && LA19_0<=26)||LA19_0==29||LA19_0==34||(LA19_0>=41 && LA19_0<=42)) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:736:6: lv_pattern_3= rulePattern
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getElementAccess().getPatternPatternParserRuleCall_3_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_rulePattern_in_ruleElement1519);
            	    lv_pattern_3=rulePattern();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getElementRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        
            	    	        try {
            	    	       		add(current, "pattern", lv_pattern_3, "Pattern", currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }
            	    break;

            	default :
            	    break loop19;
                }
            } while (true);

            match(input,17,FOLLOW_17_in_ruleElement1533); 

                    createLeafNode(grammarAccess.getElementAccess().getRightCurlyBracketKeyword_4(), null); 
                
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:758:1: (lv_cardinality_5= ( '?' | '+' | '*' ) )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( ((LA21_0>=30 && LA21_0<=32)) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:760:6: lv_cardinality_5= ( '?' | '+' | '*' )
                    {
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:760:23: ( '?' | '+' | '*' )
                    int alt20=3;
                    switch ( input.LA(1) ) {
                    case 30:
                        {
                        alt20=1;
                        }
                        break;
                    case 31:
                        {
                        alt20=2;
                        }
                        break;
                    case 32:
                        {
                        alt20=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("760:23: ( '?' | '+' | '*' )", 20, 0, input);

                        throw nvae;
                    }

                    switch (alt20) {
                        case 1 :
                            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:760:24: '?'
                            {
                            match(input,30,FOLLOW_30_in_ruleElement1555); 

                                    createLeafNode(grammarAccess.getElementAccess().getCardinalityQuestionMarkKeyword_5_0_0(), "cardinality"); 
                                

                            }
                            break;
                        case 2 :
                            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:766:6: '+'
                            {
                            match(input,31,FOLLOW_31_in_ruleElement1571); 

                                    createLeafNode(grammarAccess.getElementAccess().getCardinalityPlusSignKeyword_5_0_1(), "cardinality"); 
                                

                            }
                            break;
                        case 3 :
                            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:772:6: '*'
                            {
                            match(input,32,FOLLOW_32_in_ruleElement1587); 

                                    createLeafNode(grammarAccess.getElementAccess().getCardinalityAsteriskKeyword_5_0_2(), "cardinality"); 
                                

                            }
                            break;

                    }


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getElementRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "cardinality", /* lv_cardinality_5 */ input.LT(-1), null, lastConsumedNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	    

                    }
                    break;

            }

            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:792:3: (lv_continue_6= ( ',' | '&' | '|' ) )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==28||LA23_0==33) ) {
                alt23=1;
            }
            else if ( (LA23_0==22) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:794:6: lv_continue_6= ( ',' | '&' | '|' )
                    {
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:794:20: ( ',' | '&' | '|' )
                    int alt22=3;
                    switch ( input.LA(1) ) {
                    case 28:
                        {
                        alt22=1;
                        }
                        break;
                    case 33:
                        {
                        alt22=2;
                        }
                        break;
                    case 22:
                        {
                        alt22=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("794:20: ( ',' | '&' | '|' )", 22, 0, input);

                        throw nvae;
                    }

                    switch (alt22) {
                        case 1 :
                            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:794:21: ','
                            {
                            match(input,28,FOLLOW_28_in_ruleElement1625); 

                                    createLeafNode(grammarAccess.getElementAccess().getContinueCommaKeyword_6_0_0(), "continue"); 
                                

                            }
                            break;
                        case 2 :
                            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:800:6: '&'
                            {
                            match(input,33,FOLLOW_33_in_ruleElement1641); 

                                    createLeafNode(grammarAccess.getElementAccess().getContinueAmpersandKeyword_6_0_1(), "continue"); 
                                

                            }
                            break;
                        case 3 :
                            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:806:6: '|'
                            {
                            match(input,22,FOLLOW_22_in_ruleElement1657); 

                                    createLeafNode(grammarAccess.getElementAccess().getContinueVerticalLineKeyword_6_0_2(), "continue"); 
                                

                            }
                            break;

                    }


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getElementRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "continue", /* lv_continue_6 */ input.LT(-1), null, lastConsumedNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	    

                    }
                    break;

            }


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleElement


    // $ANTLR start entryRuleAttribute
    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:833:1: entryRuleAttribute returns [EObject current=null] : iv_ruleAttribute= ruleAttribute EOF ;
    public final EObject entryRuleAttribute() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAttribute = null;


        try {
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:833:51: (iv_ruleAttribute= ruleAttribute EOF )
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:834:2: iv_ruleAttribute= ruleAttribute EOF
            {
             currentNode = createCompositeNode(grammarAccess.getAttributeRule(), currentNode); 
            pushFollow(FOLLOW_ruleAttribute_in_entryRuleAttribute1706);
            iv_ruleAttribute=ruleAttribute();
            _fsp--;

             current =iv_ruleAttribute; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAttribute1716); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleAttribute


    // $ANTLR start ruleAttribute
    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:841:1: ruleAttribute returns [EObject current=null] : ( 'attribute' (lv_name_1= ruleNameClass ) '{' (lv_pattern_3= rulePattern )* '}' (lv_cardinality_5= '?' )? (lv_continue_6= ( ',' | '|' ) )? ) ;
    public final EObject ruleAttribute() throws RecognitionException {
        EObject current = null;

        Token lv_cardinality_5=null;
        Token lv_continue_6=null;
        EObject lv_name_1 = null;

        EObject lv_pattern_3 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:846:6: ( ( 'attribute' (lv_name_1= ruleNameClass ) '{' (lv_pattern_3= rulePattern )* '}' (lv_cardinality_5= '?' )? (lv_continue_6= ( ',' | '|' ) )? ) )
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:847:1: ( 'attribute' (lv_name_1= ruleNameClass ) '{' (lv_pattern_3= rulePattern )* '}' (lv_cardinality_5= '?' )? (lv_continue_6= ( ',' | '|' ) )? )
            {
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:847:1: ( 'attribute' (lv_name_1= ruleNameClass ) '{' (lv_pattern_3= rulePattern )* '}' (lv_cardinality_5= '?' )? (lv_continue_6= ( ',' | '|' ) )? )
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:847:2: 'attribute' (lv_name_1= ruleNameClass ) '{' (lv_pattern_3= rulePattern )* '}' (lv_cardinality_5= '?' )? (lv_continue_6= ( ',' | '|' ) )?
            {
            match(input,34,FOLLOW_34_in_ruleAttribute1750); 

                    createLeafNode(grammarAccess.getAttributeAccess().getAttributeKeyword_0(), null); 
                
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:851:1: (lv_name_1= ruleNameClass )
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:854:6: lv_name_1= ruleNameClass
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getAttributeAccess().getNameNameClassParserRuleCall_1_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleNameClass_in_ruleAttribute1784);
            lv_name_1=ruleNameClass();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getAttributeRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        
            	        try {
            	       		set(current, "name", lv_name_1, "NameClass", currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }

            match(input,16,FOLLOW_16_in_ruleAttribute1797); 

                    createLeafNode(grammarAccess.getAttributeAccess().getLeftCurlyBracketKeyword_2(), null); 
                
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:876:1: (lv_pattern_3= rulePattern )*
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( ((LA24_0>=RULE_ID && LA24_0<=RULE_STRING)||LA24_0==15||(LA24_0>=18 && LA24_0<=26)||LA24_0==29||LA24_0==34||(LA24_0>=41 && LA24_0<=42)) ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:879:6: lv_pattern_3= rulePattern
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getAttributeAccess().getPatternPatternParserRuleCall_3_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_rulePattern_in_ruleAttribute1831);
            	    lv_pattern_3=rulePattern();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getAttributeRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        
            	    	        try {
            	    	       		add(current, "pattern", lv_pattern_3, "Pattern", currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }
            	    break;

            	default :
            	    break loop24;
                }
            } while (true);

            match(input,17,FOLLOW_17_in_ruleAttribute1845); 

                    createLeafNode(grammarAccess.getAttributeAccess().getRightCurlyBracketKeyword_4(), null); 
                
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:901:1: (lv_cardinality_5= '?' )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==30) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:903:6: lv_cardinality_5= '?'
                    {
                    lv_cardinality_5=(Token)input.LT(1);
                    match(input,30,FOLLOW_30_in_ruleAttribute1866); 

                            createLeafNode(grammarAccess.getAttributeAccess().getCardinalityQuestionMarkKeyword_5_0(), "cardinality"); 
                        

                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getAttributeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "cardinality", /* lv_cardinality_5 */ input.LT(-1), "?", lastConsumedNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	    

                    }
                    break;

            }

            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:922:3: (lv_continue_6= ( ',' | '|' ) )?
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==28) ) {
                alt27=1;
            }
            else if ( (LA27_0==22) ) {
                alt27=1;
            }
            switch (alt27) {
                case 1 :
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:924:6: lv_continue_6= ( ',' | '|' )
                    {
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:924:20: ( ',' | '|' )
                    int alt26=2;
                    int LA26_0 = input.LA(1);

                    if ( (LA26_0==28) ) {
                        alt26=1;
                    }
                    else if ( (LA26_0==22) ) {
                        alt26=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("924:20: ( ',' | '|' )", 26, 0, input);

                        throw nvae;
                    }
                    switch (alt26) {
                        case 1 :
                            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:924:21: ','
                            {
                            match(input,28,FOLLOW_28_in_ruleAttribute1902); 

                                    createLeafNode(grammarAccess.getAttributeAccess().getContinueCommaKeyword_6_0_0(), "continue"); 
                                

                            }
                            break;
                        case 2 :
                            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:930:6: '|'
                            {
                            match(input,22,FOLLOW_22_in_ruleAttribute1918); 

                                    createLeafNode(grammarAccess.getAttributeAccess().getContinueVerticalLineKeyword_6_0_1(), "continue"); 
                                

                            }
                            break;

                    }


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getAttributeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "continue", /* lv_continue_6 */ input.LT(-1), null, lastConsumedNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	    

                    }
                    break;

            }


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleAttribute


    // $ANTLR start entryRuleParam
    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:957:1: entryRuleParam returns [EObject current=null] : iv_ruleParam= ruleParam EOF ;
    public final EObject entryRuleParam() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleParam = null;


        try {
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:957:47: (iv_ruleParam= ruleParam EOF )
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:958:2: iv_ruleParam= ruleParam EOF
            {
             currentNode = createCompositeNode(grammarAccess.getParamRule(), currentNode); 
            pushFollow(FOLLOW_ruleParam_in_entryRuleParam1967);
            iv_ruleParam=ruleParam();
            _fsp--;

             current =iv_ruleParam; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleParam1977); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleParam


    // $ANTLR start ruleParam
    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:965:1: ruleParam returns [EObject current=null] : (this_IdentifierOrKeyWord_0= ruleIdentifierOrKeyWord '=' (lv_parmValue_2= ruleLiteral ) ) ;
    public final EObject ruleParam() throws RecognitionException {
        EObject current = null;

        EObject this_IdentifierOrKeyWord_0 = null;

        EObject lv_parmValue_2 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:970:6: ( (this_IdentifierOrKeyWord_0= ruleIdentifierOrKeyWord '=' (lv_parmValue_2= ruleLiteral ) ) )
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:971:1: (this_IdentifierOrKeyWord_0= ruleIdentifierOrKeyWord '=' (lv_parmValue_2= ruleLiteral ) )
            {
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:971:1: (this_IdentifierOrKeyWord_0= ruleIdentifierOrKeyWord '=' (lv_parmValue_2= ruleLiteral ) )
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:972:5: this_IdentifierOrKeyWord_0= ruleIdentifierOrKeyWord '=' (lv_parmValue_2= ruleLiteral )
            {
             
                    currentNode=createCompositeNode(grammarAccess.getParamAccess().getIdentifierOrKeyWordParserRuleCall_0(), currentNode); 
                
            pushFollow(FOLLOW_ruleIdentifierOrKeyWord_in_ruleParam2024);
            this_IdentifierOrKeyWord_0=ruleIdentifierOrKeyWord();
            _fsp--;

             
                    current = this_IdentifierOrKeyWord_0; 
                    currentNode = currentNode.getParent();
                
            match(input,12,FOLLOW_12_in_ruleParam2032); 

                    createLeafNode(grammarAccess.getParamAccess().getEqualsSignKeyword_1(), null); 
                
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:984:1: (lv_parmValue_2= ruleLiteral )
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:987:6: lv_parmValue_2= ruleLiteral
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getParamAccess().getParmValueLiteralParserRuleCall_2_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleLiteral_in_ruleParam2066);
            lv_parmValue_2=ruleLiteral();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getParamRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        
            	        try {
            	       		set(current, "parmValue", lv_parmValue_2, "Literal", currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleParam


    // $ANTLR start entryRuleExceptPattern
    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1012:1: entryRuleExceptPattern returns [EObject current=null] : iv_ruleExceptPattern= ruleExceptPattern EOF ;
    public final EObject entryRuleExceptPattern() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExceptPattern = null;


        try {
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1012:55: (iv_ruleExceptPattern= ruleExceptPattern EOF )
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1013:2: iv_ruleExceptPattern= ruleExceptPattern EOF
            {
             currentNode = createCompositeNode(grammarAccess.getExceptPatternRule(), currentNode); 
            pushFollow(FOLLOW_ruleExceptPattern_in_entryRuleExceptPattern2103);
            iv_ruleExceptPattern=ruleExceptPattern();
            _fsp--;

             current =iv_ruleExceptPattern; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExceptPattern2113); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleExceptPattern


    // $ANTLR start ruleExceptPattern
    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1020:1: ruleExceptPattern returns [EObject current=null] : ( '-' this_Pattern_1= rulePattern ) ;
    public final EObject ruleExceptPattern() throws RecognitionException {
        EObject current = null;

        EObject this_Pattern_1 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1025:6: ( ( '-' this_Pattern_1= rulePattern ) )
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1026:1: ( '-' this_Pattern_1= rulePattern )
            {
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1026:1: ( '-' this_Pattern_1= rulePattern )
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1026:2: '-' this_Pattern_1= rulePattern
            {
            match(input,35,FOLLOW_35_in_ruleExceptPattern2147); 

                    createLeafNode(grammarAccess.getExceptPatternAccess().getHyphenMinusKeyword_0(), null); 
                
             
                    currentNode=createCompositeNode(grammarAccess.getExceptPatternAccess().getPatternParserRuleCall_1(), currentNode); 
                
            pushFollow(FOLLOW_rulePattern_in_ruleExceptPattern2169);
            this_Pattern_1=rulePattern();
            _fsp--;

             
                    current = this_Pattern_1; 
                    currentNode = currentNode.getParent();
                

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleExceptPattern


    // $ANTLR start entryRuleGrammarContent
    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1046:1: entryRuleGrammarContent returns [EObject current=null] : iv_ruleGrammarContent= ruleGrammarContent EOF ;
    public final EObject entryRuleGrammarContent() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGrammarContent = null;


        try {
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1046:56: (iv_ruleGrammarContent= ruleGrammarContent EOF )
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1047:2: iv_ruleGrammarContent= ruleGrammarContent EOF
            {
             currentNode = createCompositeNode(grammarAccess.getGrammarContentRule(), currentNode); 
            pushFollow(FOLLOW_ruleGrammarContent_in_entryRuleGrammarContent2201);
            iv_ruleGrammarContent=ruleGrammarContent();
            _fsp--;

             current =iv_ruleGrammarContent; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleGrammarContent2211); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleGrammarContent


    // $ANTLR start ruleGrammarContent
    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1054:1: ruleGrammarContent returns [EObject current=null] : (this_Start_0= ruleStart | this_Define_1= ruleDefine | ( 'div' '{' (lv_grammarContenGrammarContent_4= ruleGrammarContent )* '}' ) | ( 'include' this_AnyURILiteral_7= ruleAnyURILiteral (lv_inherit_8= ruleInherit ) ( '{' (lv_includeContent_10= ruleIncludeContent )* '}' ) ) ) ;
    public final EObject ruleGrammarContent() throws RecognitionException {
        EObject current = null;

        EObject this_Start_0 = null;

        EObject this_Define_1 = null;

        EObject lv_grammarContenGrammarContent_4 = null;

        EObject this_AnyURILiteral_7 = null;

        EObject lv_inherit_8 = null;

        EObject lv_includeContent_10 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1059:6: ( (this_Start_0= ruleStart | this_Define_1= ruleDefine | ( 'div' '{' (lv_grammarContenGrammarContent_4= ruleGrammarContent )* '}' ) | ( 'include' this_AnyURILiteral_7= ruleAnyURILiteral (lv_inherit_8= ruleInherit ) ( '{' (lv_includeContent_10= ruleIncludeContent )* '}' ) ) ) )
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1060:1: (this_Start_0= ruleStart | this_Define_1= ruleDefine | ( 'div' '{' (lv_grammarContenGrammarContent_4= ruleGrammarContent )* '}' ) | ( 'include' this_AnyURILiteral_7= ruleAnyURILiteral (lv_inherit_8= ruleInherit ) ( '{' (lv_includeContent_10= ruleIncludeContent )* '}' ) ) )
            {
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1060:1: (this_Start_0= ruleStart | this_Define_1= ruleDefine | ( 'div' '{' (lv_grammarContenGrammarContent_4= ruleGrammarContent )* '}' ) | ( 'include' this_AnyURILiteral_7= ruleAnyURILiteral (lv_inherit_8= ruleInherit ) ( '{' (lv_includeContent_10= ruleIncludeContent )* '}' ) ) )
            int alt30=4;
            switch ( input.LA(1) ) {
            case 38:
                {
                alt30=1;
                }
                break;
            case RULE_ID:
                {
                alt30=2;
                }
                break;
            case 36:
                {
                alt30=3;
                }
                break;
            case 37:
                {
                alt30=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("1060:1: (this_Start_0= ruleStart | this_Define_1= ruleDefine | ( 'div' '{' (lv_grammarContenGrammarContent_4= ruleGrammarContent )* '}' ) | ( 'include' this_AnyURILiteral_7= ruleAnyURILiteral (lv_inherit_8= ruleInherit ) ( '{' (lv_includeContent_10= ruleIncludeContent )* '}' ) ) )", 30, 0, input);

                throw nvae;
            }

            switch (alt30) {
                case 1 :
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1061:5: this_Start_0= ruleStart
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getGrammarContentAccess().getStartParserRuleCall_0(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleStart_in_ruleGrammarContent2258);
                    this_Start_0=ruleStart();
                    _fsp--;

                     
                            current = this_Start_0; 
                            currentNode = currentNode.getParent();
                        

                    }
                    break;
                case 2 :
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1071:5: this_Define_1= ruleDefine
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getGrammarContentAccess().getDefineParserRuleCall_1(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleDefine_in_ruleGrammarContent2285);
                    this_Define_1=ruleDefine();
                    _fsp--;

                     
                            current = this_Define_1; 
                            currentNode = currentNode.getParent();
                        

                    }
                    break;
                case 3 :
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1080:6: ( 'div' '{' (lv_grammarContenGrammarContent_4= ruleGrammarContent )* '}' )
                    {
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1080:6: ( 'div' '{' (lv_grammarContenGrammarContent_4= ruleGrammarContent )* '}' )
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1080:7: 'div' '{' (lv_grammarContenGrammarContent_4= ruleGrammarContent )* '}'
                    {
                    match(input,36,FOLLOW_36_in_ruleGrammarContent2300); 

                            createLeafNode(grammarAccess.getGrammarContentAccess().getDivKeyword_2_0(), null); 
                        
                    match(input,16,FOLLOW_16_in_ruleGrammarContent2309); 

                            createLeafNode(grammarAccess.getGrammarContentAccess().getLeftCurlyBracketKeyword_2_1(), null); 
                        
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1088:1: (lv_grammarContenGrammarContent_4= ruleGrammarContent )*
                    loop28:
                    do {
                        int alt28=2;
                        int LA28_0 = input.LA(1);

                        if ( (LA28_0==RULE_ID||(LA28_0>=36 && LA28_0<=38)) ) {
                            alt28=1;
                        }


                        switch (alt28) {
                    	case 1 :
                    	    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1091:6: lv_grammarContenGrammarContent_4= ruleGrammarContent
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getGrammarContentAccess().getGrammarContenGrammarContentGrammarContentParserRuleCall_2_2_0(), currentNode); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleGrammarContent_in_ruleGrammarContent2343);
                    	    lv_grammarContenGrammarContent_4=ruleGrammarContent();
                    	    _fsp--;


                    	    	        if (current==null) {
                    	    	            current = factory.create(grammarAccess.getGrammarContentRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	    	        }
                    	    	        
                    	    	        try {
                    	    	       		add(current, "grammarContenGrammarContent", lv_grammarContenGrammarContent_4, "GrammarContent", currentNode);
                    	    	        } catch (ValueConverterException vce) {
                    	    				handleValueConverterException(vce);
                    	    	        }
                    	    	        currentNode = currentNode.getParent();
                    	    	    

                    	    }
                    	    break;

                    	default :
                    	    break loop28;
                        }
                    } while (true);

                    match(input,17,FOLLOW_17_in_ruleGrammarContent2357); 

                            createLeafNode(grammarAccess.getGrammarContentAccess().getRightCurlyBracketKeyword_2_3(), null); 
                        

                    }


                    }
                    break;
                case 4 :
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1114:6: ( 'include' this_AnyURILiteral_7= ruleAnyURILiteral (lv_inherit_8= ruleInherit ) ( '{' (lv_includeContent_10= ruleIncludeContent )* '}' ) )
                    {
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1114:6: ( 'include' this_AnyURILiteral_7= ruleAnyURILiteral (lv_inherit_8= ruleInherit ) ( '{' (lv_includeContent_10= ruleIncludeContent )* '}' ) )
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1114:7: 'include' this_AnyURILiteral_7= ruleAnyURILiteral (lv_inherit_8= ruleInherit ) ( '{' (lv_includeContent_10= ruleIncludeContent )* '}' )
                    {
                    match(input,37,FOLLOW_37_in_ruleGrammarContent2374); 

                            createLeafNode(grammarAccess.getGrammarContentAccess().getIncludeKeyword_3_0(), null); 
                        
                     
                            currentNode=createCompositeNode(grammarAccess.getGrammarContentAccess().getAnyURILiteralParserRuleCall_3_1(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleAnyURILiteral_in_ruleGrammarContent2396);
                    this_AnyURILiteral_7=ruleAnyURILiteral();
                    _fsp--;

                     
                            current = this_AnyURILiteral_7; 
                            currentNode = currentNode.getParent();
                        
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1127:1: (lv_inherit_8= ruleInherit )
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1130:6: lv_inherit_8= ruleInherit
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getGrammarContentAccess().getInheritInheritParserRuleCall_3_2_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleInherit_in_ruleGrammarContent2429);
                    lv_inherit_8=ruleInherit();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getGrammarContentRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "inherit", lv_inherit_8, "Inherit", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }

                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1148:2: ( '{' (lv_includeContent_10= ruleIncludeContent )* '}' )
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1148:3: '{' (lv_includeContent_10= ruleIncludeContent )* '}'
                    {
                    match(input,16,FOLLOW_16_in_ruleGrammarContent2443); 

                            createLeafNode(grammarAccess.getGrammarContentAccess().getLeftCurlyBracketKeyword_3_3_0(), null); 
                        
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1152:1: (lv_includeContent_10= ruleIncludeContent )*
                    loop29:
                    do {
                        int alt29=2;
                        int LA29_0 = input.LA(1);

                        if ( (LA29_0==RULE_ID||LA29_0==36||LA29_0==38) ) {
                            alt29=1;
                        }


                        switch (alt29) {
                    	case 1 :
                    	    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1155:6: lv_includeContent_10= ruleIncludeContent
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getGrammarContentAccess().getIncludeContentIncludeContentParserRuleCall_3_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleIncludeContent_in_ruleGrammarContent2477);
                    	    lv_includeContent_10=ruleIncludeContent();
                    	    _fsp--;


                    	    	        if (current==null) {
                    	    	            current = factory.create(grammarAccess.getGrammarContentRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	    	        }
                    	    	        
                    	    	        try {
                    	    	       		add(current, "includeContent", lv_includeContent_10, "IncludeContent", currentNode);
                    	    	        } catch (ValueConverterException vce) {
                    	    				handleValueConverterException(vce);
                    	    	        }
                    	    	        currentNode = currentNode.getParent();
                    	    	    

                    	    }
                    	    break;

                    	default :
                    	    break loop29;
                        }
                    } while (true);

                    match(input,17,FOLLOW_17_in_ruleGrammarContent2491); 

                            createLeafNode(grammarAccess.getGrammarContentAccess().getRightCurlyBracketKeyword_3_3_2(), null); 
                        

                    }


                    }


                    }
                    break;

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleGrammarContent


    // $ANTLR start entryRuleIncludeContent
    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1184:1: entryRuleIncludeContent returns [EObject current=null] : iv_ruleIncludeContent= ruleIncludeContent EOF ;
    public final EObject entryRuleIncludeContent() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIncludeContent = null;


        try {
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1184:56: (iv_ruleIncludeContent= ruleIncludeContent EOF )
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1185:2: iv_ruleIncludeContent= ruleIncludeContent EOF
            {
             currentNode = createCompositeNode(grammarAccess.getIncludeContentRule(), currentNode); 
            pushFollow(FOLLOW_ruleIncludeContent_in_entryRuleIncludeContent2526);
            iv_ruleIncludeContent=ruleIncludeContent();
            _fsp--;

             current =iv_ruleIncludeContent; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleIncludeContent2536); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleIncludeContent


    // $ANTLR start ruleIncludeContent
    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1192:1: ruleIncludeContent returns [EObject current=null] : (this_Define_0= ruleDefine | this_Start_1= ruleStart | ( 'div' '{' (lv_includeGrammarContent_4= ruleGrammarContent )* '}' ) ) ;
    public final EObject ruleIncludeContent() throws RecognitionException {
        EObject current = null;

        EObject this_Define_0 = null;

        EObject this_Start_1 = null;

        EObject lv_includeGrammarContent_4 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1197:6: ( (this_Define_0= ruleDefine | this_Start_1= ruleStart | ( 'div' '{' (lv_includeGrammarContent_4= ruleGrammarContent )* '}' ) ) )
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1198:1: (this_Define_0= ruleDefine | this_Start_1= ruleStart | ( 'div' '{' (lv_includeGrammarContent_4= ruleGrammarContent )* '}' ) )
            {
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1198:1: (this_Define_0= ruleDefine | this_Start_1= ruleStart | ( 'div' '{' (lv_includeGrammarContent_4= ruleGrammarContent )* '}' ) )
            int alt32=3;
            switch ( input.LA(1) ) {
            case RULE_ID:
                {
                alt32=1;
                }
                break;
            case 38:
                {
                alt32=2;
                }
                break;
            case 36:
                {
                alt32=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("1198:1: (this_Define_0= ruleDefine | this_Start_1= ruleStart | ( 'div' '{' (lv_includeGrammarContent_4= ruleGrammarContent )* '}' ) )", 32, 0, input);

                throw nvae;
            }

            switch (alt32) {
                case 1 :
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1199:5: this_Define_0= ruleDefine
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getIncludeContentAccess().getDefineParserRuleCall_0(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleDefine_in_ruleIncludeContent2583);
                    this_Define_0=ruleDefine();
                    _fsp--;

                     
                            current = this_Define_0; 
                            currentNode = currentNode.getParent();
                        

                    }
                    break;
                case 2 :
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1209:5: this_Start_1= ruleStart
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getIncludeContentAccess().getStartParserRuleCall_1(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleStart_in_ruleIncludeContent2610);
                    this_Start_1=ruleStart();
                    _fsp--;

                     
                            current = this_Start_1; 
                            currentNode = currentNode.getParent();
                        

                    }
                    break;
                case 3 :
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1218:6: ( 'div' '{' (lv_includeGrammarContent_4= ruleGrammarContent )* '}' )
                    {
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1218:6: ( 'div' '{' (lv_includeGrammarContent_4= ruleGrammarContent )* '}' )
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1218:7: 'div' '{' (lv_includeGrammarContent_4= ruleGrammarContent )* '}'
                    {
                    match(input,36,FOLLOW_36_in_ruleIncludeContent2625); 

                            createLeafNode(grammarAccess.getIncludeContentAccess().getDivKeyword_2_0(), null); 
                        
                    match(input,16,FOLLOW_16_in_ruleIncludeContent2634); 

                            createLeafNode(grammarAccess.getIncludeContentAccess().getLeftCurlyBracketKeyword_2_1(), null); 
                        
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1226:1: (lv_includeGrammarContent_4= ruleGrammarContent )*
                    loop31:
                    do {
                        int alt31=2;
                        int LA31_0 = input.LA(1);

                        if ( (LA31_0==RULE_ID||(LA31_0>=36 && LA31_0<=38)) ) {
                            alt31=1;
                        }


                        switch (alt31) {
                    	case 1 :
                    	    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1229:6: lv_includeGrammarContent_4= ruleGrammarContent
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getIncludeContentAccess().getIncludeGrammarContentGrammarContentParserRuleCall_2_2_0(), currentNode); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleGrammarContent_in_ruleIncludeContent2668);
                    	    lv_includeGrammarContent_4=ruleGrammarContent();
                    	    _fsp--;


                    	    	        if (current==null) {
                    	    	            current = factory.create(grammarAccess.getIncludeContentRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	    	        }
                    	    	        
                    	    	        try {
                    	    	       		add(current, "includeGrammarContent", lv_includeGrammarContent_4, "GrammarContent", currentNode);
                    	    	        } catch (ValueConverterException vce) {
                    	    				handleValueConverterException(vce);
                    	    	        }
                    	    	        currentNode = currentNode.getParent();
                    	    	    

                    	    }
                    	    break;

                    	default :
                    	    break loop31;
                        }
                    } while (true);

                    match(input,17,FOLLOW_17_in_ruleIncludeContent2682); 

                            createLeafNode(grammarAccess.getIncludeContentAccess().getRightCurlyBracketKeyword_2_3(), null); 
                        

                    }


                    }
                    break;

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleIncludeContent


    // $ANTLR start entryRuleStart
    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1258:1: entryRuleStart returns [EObject current=null] : iv_ruleStart= ruleStart EOF ;
    public final EObject entryRuleStart() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleStart = null;


        try {
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1258:47: (iv_ruleStart= ruleStart EOF )
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1259:2: iv_ruleStart= ruleStart EOF
            {
             currentNode = createCompositeNode(grammarAccess.getStartRule(), currentNode); 
            pushFollow(FOLLOW_ruleStart_in_entryRuleStart2716);
            iv_ruleStart=ruleStart();
            _fsp--;

             current =iv_ruleStart; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleStart2726); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleStart


    // $ANTLR start ruleStart
    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1266:1: ruleStart returns [EObject current=null] : ( 'start' ( '=' | '|=' | '&=' ) ( RULE_ID | (lv_pattern_5= rulePattern ) ) ) ;
    public final EObject ruleStart() throws RecognitionException {
        EObject current = null;

        EObject lv_pattern_5 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1271:6: ( ( 'start' ( '=' | '|=' | '&=' ) ( RULE_ID | (lv_pattern_5= rulePattern ) ) ) )
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1272:1: ( 'start' ( '=' | '|=' | '&=' ) ( RULE_ID | (lv_pattern_5= rulePattern ) ) )
            {
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1272:1: ( 'start' ( '=' | '|=' | '&=' ) ( RULE_ID | (lv_pattern_5= rulePattern ) ) )
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1272:2: 'start' ( '=' | '|=' | '&=' ) ( RULE_ID | (lv_pattern_5= rulePattern ) )
            {
            match(input,38,FOLLOW_38_in_ruleStart2760); 

                    createLeafNode(grammarAccess.getStartAccess().getStartKeyword_0(), null); 
                
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1276:1: ( '=' | '|=' | '&=' )
            int alt33=3;
            switch ( input.LA(1) ) {
            case 12:
                {
                alt33=1;
                }
                break;
            case 39:
                {
                alt33=2;
                }
                break;
            case 40:
                {
                alt33=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("1276:1: ( '=' | '|=' | '&=' )", 33, 0, input);

                throw nvae;
            }

            switch (alt33) {
                case 1 :
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1276:2: '='
                    {
                    match(input,12,FOLLOW_12_in_ruleStart2770); 

                            createLeafNode(grammarAccess.getStartAccess().getEqualsSignKeyword_1_0(), null); 
                        

                    }
                    break;
                case 2 :
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1281:6: '|='
                    {
                    match(input,39,FOLLOW_39_in_ruleStart2785); 

                            createLeafNode(grammarAccess.getStartAccess().getVerticalLineEqualsSignKeyword_1_1(), null); 
                        

                    }
                    break;
                case 3 :
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1286:6: '&='
                    {
                    match(input,40,FOLLOW_40_in_ruleStart2800); 

                            createLeafNode(grammarAccess.getStartAccess().getAmpersandEqualsSignKeyword_1_2(), null); 
                        

                    }
                    break;

            }

            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1290:2: ( RULE_ID | (lv_pattern_5= rulePattern ) )
            int alt34=2;
            int LA34_0 = input.LA(1);

            if ( (LA34_0==RULE_ID) ) {
                alt34=1;
            }
            else if ( (LA34_0==RULE_STRING||LA34_0==15||(LA34_0>=18 && LA34_0<=26)||LA34_0==29||LA34_0==34||(LA34_0>=41 && LA34_0<=42)) ) {
                alt34=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("1290:2: ( RULE_ID | (lv_pattern_5= rulePattern ) )", 34, 0, input);

                throw nvae;
            }
            switch (alt34) {
                case 1 :
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1290:3: RULE_ID
                    {
                    match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleStart2811); 
                     
                        createLeafNode(grammarAccess.getStartAccess().getIDTerminalRuleCall_2_0(), null); 
                        

                    }
                    break;
                case 2 :
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1295:6: (lv_pattern_5= rulePattern )
                    {
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1295:6: (lv_pattern_5= rulePattern )
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1298:6: lv_pattern_5= rulePattern
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getStartAccess().getPatternPatternParserRuleCall_2_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_rulePattern_in_ruleStart2850);
                    lv_pattern_5=rulePattern();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getStartRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "pattern", lv_pattern_5, "Pattern", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }
                    break;

            }


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleStart


    // $ANTLR start entryRuleDefine
    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1323:1: entryRuleDefine returns [EObject current=null] : iv_ruleDefine= ruleDefine EOF ;
    public final EObject entryRuleDefine() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDefine = null;


        try {
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1323:48: (iv_ruleDefine= ruleDefine EOF )
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1324:2: iv_ruleDefine= ruleDefine EOF
            {
             currentNode = createCompositeNode(grammarAccess.getDefineRule(), currentNode); 
            pushFollow(FOLLOW_ruleDefine_in_entryRuleDefine2888);
            iv_ruleDefine=ruleDefine();
            _fsp--;

             current =iv_ruleDefine; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleDefine2898); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleDefine


    // $ANTLR start ruleDefine
    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1331:1: ruleDefine returns [EObject current=null] : ( RULE_ID ( '=' | '|=' | '&=' ) (lv_pattern_4= rulePattern ) ) ;
    public final EObject ruleDefine() throws RecognitionException {
        EObject current = null;

        EObject lv_pattern_4 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1336:6: ( ( RULE_ID ( '=' | '|=' | '&=' ) (lv_pattern_4= rulePattern ) ) )
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1337:1: ( RULE_ID ( '=' | '|=' | '&=' ) (lv_pattern_4= rulePattern ) )
            {
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1337:1: ( RULE_ID ( '=' | '|=' | '&=' ) (lv_pattern_4= rulePattern ) )
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1337:2: RULE_ID ( '=' | '|=' | '&=' ) (lv_pattern_4= rulePattern )
            {
            match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleDefine2932); 
             
                createLeafNode(grammarAccess.getDefineAccess().getIDTerminalRuleCall_0(), null); 
                
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1341:1: ( '=' | '|=' | '&=' )
            int alt35=3;
            switch ( input.LA(1) ) {
            case 12:
                {
                alt35=1;
                }
                break;
            case 39:
                {
                alt35=2;
                }
                break;
            case 40:
                {
                alt35=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("1341:1: ( '=' | '|=' | '&=' )", 35, 0, input);

                throw nvae;
            }

            switch (alt35) {
                case 1 :
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1341:2: '='
                    {
                    match(input,12,FOLLOW_12_in_ruleDefine2941); 

                            createLeafNode(grammarAccess.getDefineAccess().getEqualsSignKeyword_1_0(), null); 
                        

                    }
                    break;
                case 2 :
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1346:6: '|='
                    {
                    match(input,39,FOLLOW_39_in_ruleDefine2956); 

                            createLeafNode(grammarAccess.getDefineAccess().getVerticalLineEqualsSignKeyword_1_1(), null); 
                        

                    }
                    break;
                case 3 :
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1351:6: '&='
                    {
                    match(input,40,FOLLOW_40_in_ruleDefine2971); 

                            createLeafNode(grammarAccess.getDefineAccess().getAmpersandEqualsSignKeyword_1_2(), null); 
                        

                    }
                    break;

            }

            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1355:2: (lv_pattern_4= rulePattern )
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1358:6: lv_pattern_4= rulePattern
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getDefineAccess().getPatternPatternParserRuleCall_2_0(), currentNode); 
            	    
            pushFollow(FOLLOW_rulePattern_in_ruleDefine3006);
            lv_pattern_4=rulePattern();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getDefineRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        
            	        try {
            	       		set(current, "pattern", lv_pattern_4, "Pattern", currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleDefine


    // $ANTLR start entryRuleName
    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1383:1: entryRuleName returns [EObject current=null] : iv_ruleName= ruleName EOF ;
    public final EObject entryRuleName() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleName = null;


        try {
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1383:46: (iv_ruleName= ruleName EOF )
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1384:2: iv_ruleName= ruleName EOF
            {
             currentNode = createCompositeNode(grammarAccess.getNameRule(), currentNode); 
            pushFollow(FOLLOW_ruleName_in_entryRuleName3043);
            iv_ruleName=ruleName();
            _fsp--;

             current =iv_ruleName; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleName3053); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleName


    // $ANTLR start ruleName
    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1391:1: ruleName returns [EObject current=null] : (this_IdentifierOrKeyWord_0= ruleIdentifierOrKeyWord | ruleCName ) ;
    public final EObject ruleName() throws RecognitionException {
        EObject current = null;

        EObject this_IdentifierOrKeyWord_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1396:6: ( (this_IdentifierOrKeyWord_0= ruleIdentifierOrKeyWord | ruleCName ) )
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1397:1: (this_IdentifierOrKeyWord_0= ruleIdentifierOrKeyWord | ruleCName )
            {
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1397:1: (this_IdentifierOrKeyWord_0= ruleIdentifierOrKeyWord | ruleCName )
            int alt36=2;
            int LA36_0 = input.LA(1);

            if ( (LA36_0==RULE_ID) ) {
                int LA36_1 = input.LA(2);

                if ( (LA36_1==45) ) {
                    alt36=2;
                }
                else if ( (LA36_1==11||(LA36_1>=13 && LA36_1<=15)||(LA36_1>=18 && LA36_1<=21)||(LA36_1>=23 && LA36_1<=25)||LA36_1==29||LA36_1==34||(LA36_1>=36 && LA36_1<=38)||(LA36_1>=41 && LA36_1<=43)) ) {
                    alt36=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("1397:1: (this_IdentifierOrKeyWord_0= ruleIdentifierOrKeyWord | ruleCName )", 36, 1, input);

                    throw nvae;
                }
            }
            else if ( (LA36_0==11||(LA36_0>=13 && LA36_0<=15)||(LA36_0>=18 && LA36_0<=21)||(LA36_0>=23 && LA36_0<=25)||LA36_0==29||LA36_0==34||(LA36_0>=36 && LA36_0<=38)||(LA36_0>=41 && LA36_0<=44)) ) {
                alt36=1;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("1397:1: (this_IdentifierOrKeyWord_0= ruleIdentifierOrKeyWord | ruleCName )", 36, 0, input);

                throw nvae;
            }
            switch (alt36) {
                case 1 :
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1398:5: this_IdentifierOrKeyWord_0= ruleIdentifierOrKeyWord
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getNameAccess().getIdentifierOrKeyWordParserRuleCall_0(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleIdentifierOrKeyWord_in_ruleName3100);
                    this_IdentifierOrKeyWord_0=ruleIdentifierOrKeyWord();
                    _fsp--;

                     
                            current = this_IdentifierOrKeyWord_0; 
                            currentNode = currentNode.getParent();
                        

                    }
                    break;
                case 2 :
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1407:6: ruleCName
                    {
                    pushFollow(FOLLOW_ruleCName_in_ruleName3114);
                    ruleCName();
                    _fsp--;


                    }
                    break;

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleName


    // $ANTLR start entryRuleExceptNameClass
    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1414:1: entryRuleExceptNameClass returns [EObject current=null] : iv_ruleExceptNameClass= ruleExceptNameClass EOF ;
    public final EObject entryRuleExceptNameClass() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExceptNameClass = null;


        try {
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1414:57: (iv_ruleExceptNameClass= ruleExceptNameClass EOF )
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1415:2: iv_ruleExceptNameClass= ruleExceptNameClass EOF
            {
             currentNode = createCompositeNode(grammarAccess.getExceptNameClassRule(), currentNode); 
            pushFollow(FOLLOW_ruleExceptNameClass_in_entryRuleExceptNameClass3139);
            iv_ruleExceptNameClass=ruleExceptNameClass();
            _fsp--;

             current =iv_ruleExceptNameClass; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExceptNameClass3149); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleExceptNameClass


    // $ANTLR start ruleExceptNameClass
    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1422:1: ruleExceptNameClass returns [EObject current=null] : ( '-' this_NameClass_1= ruleNameClass ) ;
    public final EObject ruleExceptNameClass() throws RecognitionException {
        EObject current = null;

        EObject this_NameClass_1 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1427:6: ( ( '-' this_NameClass_1= ruleNameClass ) )
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1428:1: ( '-' this_NameClass_1= ruleNameClass )
            {
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1428:1: ( '-' this_NameClass_1= ruleNameClass )
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1428:2: '-' this_NameClass_1= ruleNameClass
            {
            match(input,35,FOLLOW_35_in_ruleExceptNameClass3183); 

                    createLeafNode(grammarAccess.getExceptNameClassAccess().getHyphenMinusKeyword_0(), null); 
                
             
                    currentNode=createCompositeNode(grammarAccess.getExceptNameClassAccess().getNameClassParserRuleCall_1(), currentNode); 
                
            pushFollow(FOLLOW_ruleNameClass_in_ruleExceptNameClass3205);
            this_NameClass_1=ruleNameClass();
            _fsp--;

             
                    current = this_NameClass_1; 
                    currentNode = currentNode.getParent();
                

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleExceptNameClass


    // $ANTLR start entryRuleNameClass
    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1448:1: entryRuleNameClass returns [EObject current=null] : iv_ruleNameClass= ruleNameClass EOF ;
    public final EObject entryRuleNameClass() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNameClass = null;


        try {
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1448:51: (iv_ruleNameClass= ruleNameClass EOF )
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1449:2: iv_ruleNameClass= ruleNameClass EOF
            {
             currentNode = createCompositeNode(grammarAccess.getNameClassRule(), currentNode); 
            pushFollow(FOLLOW_ruleNameClass_in_entryRuleNameClass3237);
            iv_ruleNameClass=ruleNameClass();
            _fsp--;

             current =iv_ruleNameClass; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleNameClass3247); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleNameClass


    // $ANTLR start ruleNameClass
    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1456:1: ruleNameClass returns [EObject current=null] : (this_Name_0= ruleName | ( RULE_ID (lv_exceptClassName_2= ruleExceptNameClass )? ) | (this_AnyName_3= ruleAnyName (lv_exceptClassName_4= ruleExceptNameClass )? ) | ( '|' (lv_nc_6= ruleNameClass ) ) | ( '(' (lv_nc_8= ruleNameClass ) ')' ) ) ;
    public final EObject ruleNameClass() throws RecognitionException {
        EObject current = null;

        EObject this_Name_0 = null;

        EObject lv_exceptClassName_2 = null;

        EObject this_AnyName_3 = null;

        EObject lv_exceptClassName_4 = null;

        EObject lv_nc_6 = null;

        EObject lv_nc_8 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1461:6: ( (this_Name_0= ruleName | ( RULE_ID (lv_exceptClassName_2= ruleExceptNameClass )? ) | (this_AnyName_3= ruleAnyName (lv_exceptClassName_4= ruleExceptNameClass )? ) | ( '|' (lv_nc_6= ruleNameClass ) ) | ( '(' (lv_nc_8= ruleNameClass ) ')' ) ) )
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1462:1: (this_Name_0= ruleName | ( RULE_ID (lv_exceptClassName_2= ruleExceptNameClass )? ) | (this_AnyName_3= ruleAnyName (lv_exceptClassName_4= ruleExceptNameClass )? ) | ( '|' (lv_nc_6= ruleNameClass ) ) | ( '(' (lv_nc_8= ruleNameClass ) ')' ) )
            {
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1462:1: (this_Name_0= ruleName | ( RULE_ID (lv_exceptClassName_2= ruleExceptNameClass )? ) | (this_AnyName_3= ruleAnyName (lv_exceptClassName_4= ruleExceptNameClass )? ) | ( '|' (lv_nc_6= ruleNameClass ) ) | ( '(' (lv_nc_8= ruleNameClass ) ')' ) )
            int alt39=5;
            switch ( input.LA(1) ) {
            case RULE_ID:
                {
                int LA39_1 = input.LA(2);

                if ( (LA39_1==11||(LA39_1>=13 && LA39_1<=15)||(LA39_1>=18 && LA39_1<=21)||(LA39_1>=23 && LA39_1<=25)||LA39_1==29||LA39_1==34||(LA39_1>=36 && LA39_1<=38)||(LA39_1>=41 && LA39_1<=43)||LA39_1==45) ) {
                    alt39=1;
                }
                else if ( (LA39_1==EOF||LA39_1==16||LA39_1==27||LA39_1==35) ) {
                    alt39=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("1462:1: (this_Name_0= ruleName | ( RULE_ID (lv_exceptClassName_2= ruleExceptNameClass )? ) | (this_AnyName_3= ruleAnyName (lv_exceptClassName_4= ruleExceptNameClass )? ) | ( '|' (lv_nc_6= ruleNameClass ) ) | ( '(' (lv_nc_8= ruleNameClass ) ')' ) )", 39, 1, input);

                    throw nvae;
                }
                }
                break;
            case 11:
            case 13:
            case 14:
            case 15:
            case 18:
            case 19:
            case 20:
            case 21:
            case 23:
            case 24:
            case 25:
            case 29:
            case 34:
            case 36:
            case 37:
            case 38:
            case 41:
            case 42:
            case 43:
            case 44:
                {
                alt39=1;
                }
                break;
            case 32:
                {
                alt39=3;
                }
                break;
            case 22:
                {
                alt39=4;
                }
                break;
            case 26:
                {
                alt39=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("1462:1: (this_Name_0= ruleName | ( RULE_ID (lv_exceptClassName_2= ruleExceptNameClass )? ) | (this_AnyName_3= ruleAnyName (lv_exceptClassName_4= ruleExceptNameClass )? ) | ( '|' (lv_nc_6= ruleNameClass ) ) | ( '(' (lv_nc_8= ruleNameClass ) ')' ) )", 39, 0, input);

                throw nvae;
            }

            switch (alt39) {
                case 1 :
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1463:5: this_Name_0= ruleName
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getNameClassAccess().getNameParserRuleCall_0(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleName_in_ruleNameClass3294);
                    this_Name_0=ruleName();
                    _fsp--;

                     
                            current = this_Name_0; 
                            currentNode = currentNode.getParent();
                        

                    }
                    break;
                case 2 :
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1472:6: ( RULE_ID (lv_exceptClassName_2= ruleExceptNameClass )? )
                    {
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1472:6: ( RULE_ID (lv_exceptClassName_2= ruleExceptNameClass )? )
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1472:7: RULE_ID (lv_exceptClassName_2= ruleExceptNameClass )?
                    {
                    match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleNameClass3309); 
                     
                        createLeafNode(grammarAccess.getNameClassAccess().getIDTerminalRuleCall_1_0(), null); 
                        
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1476:1: (lv_exceptClassName_2= ruleExceptNameClass )?
                    int alt37=2;
                    int LA37_0 = input.LA(1);

                    if ( (LA37_0==35) ) {
                        alt37=1;
                    }
                    switch (alt37) {
                        case 1 :
                            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1479:6: lv_exceptClassName_2= ruleExceptNameClass
                            {
                             
                            	        currentNode=createCompositeNode(grammarAccess.getNameClassAccess().getExceptClassNameExceptNameClassParserRuleCall_1_1_0(), currentNode); 
                            	    
                            pushFollow(FOLLOW_ruleExceptNameClass_in_ruleNameClass3342);
                            lv_exceptClassName_2=ruleExceptNameClass();
                            _fsp--;


                            	        if (current==null) {
                            	            current = factory.create(grammarAccess.getNameClassRule().getType().getClassifier());
                            	            associateNodeWithAstElement(currentNode.getParent(), current);
                            	        }
                            	        
                            	        try {
                            	       		set(current, "exceptClassName", lv_exceptClassName_2, "ExceptNameClass", currentNode);
                            	        } catch (ValueConverterException vce) {
                            				handleValueConverterException(vce);
                            	        }
                            	        currentNode = currentNode.getParent();
                            	    

                            }
                            break;

                    }


                    }


                    }
                    break;
                case 3 :
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1498:6: (this_AnyName_3= ruleAnyName (lv_exceptClassName_4= ruleExceptNameClass )? )
                    {
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1498:6: (this_AnyName_3= ruleAnyName (lv_exceptClassName_4= ruleExceptNameClass )? )
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1499:5: this_AnyName_3= ruleAnyName (lv_exceptClassName_4= ruleExceptNameClass )?
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getNameClassAccess().getAnyNameParserRuleCall_2_0(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleAnyName_in_ruleNameClass3377);
                    this_AnyName_3=ruleAnyName();
                    _fsp--;

                     
                            current = this_AnyName_3; 
                            currentNode = currentNode.getParent();
                        
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1507:1: (lv_exceptClassName_4= ruleExceptNameClass )?
                    int alt38=2;
                    int LA38_0 = input.LA(1);

                    if ( (LA38_0==35) ) {
                        alt38=1;
                    }
                    switch (alt38) {
                        case 1 :
                            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1510:6: lv_exceptClassName_4= ruleExceptNameClass
                            {
                             
                            	        currentNode=createCompositeNode(grammarAccess.getNameClassAccess().getExceptClassNameExceptNameClassParserRuleCall_2_1_0(), currentNode); 
                            	    
                            pushFollow(FOLLOW_ruleExceptNameClass_in_ruleNameClass3410);
                            lv_exceptClassName_4=ruleExceptNameClass();
                            _fsp--;


                            	        if (current==null) {
                            	            current = factory.create(grammarAccess.getNameClassRule().getType().getClassifier());
                            	            associateNodeWithAstElement(currentNode.getParent(), current);
                            	        }
                            	        
                            	        try {
                            	       		set(current, "exceptClassName", lv_exceptClassName_4, "ExceptNameClass", currentNode);
                            	        } catch (ValueConverterException vce) {
                            				handleValueConverterException(vce);
                            	        }
                            	        currentNode = currentNode.getParent();
                            	    

                            }
                            break;

                    }


                    }


                    }
                    break;
                case 4 :
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1529:6: ( '|' (lv_nc_6= ruleNameClass ) )
                    {
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1529:6: ( '|' (lv_nc_6= ruleNameClass ) )
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1529:7: '|' (lv_nc_6= ruleNameClass )
                    {
                    match(input,22,FOLLOW_22_in_ruleNameClass3432); 

                            createLeafNode(grammarAccess.getNameClassAccess().getVerticalLineKeyword_3_0(), null); 
                        
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1533:1: (lv_nc_6= ruleNameClass )
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1536:6: lv_nc_6= ruleNameClass
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getNameClassAccess().getNcNameClassParserRuleCall_3_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleNameClass_in_ruleNameClass3466);
                    lv_nc_6=ruleNameClass();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getNameClassRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "nc", lv_nc_6, "NameClass", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;
                case 5 :
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1555:6: ( '(' (lv_nc_8= ruleNameClass ) ')' )
                    {
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1555:6: ( '(' (lv_nc_8= ruleNameClass ) ')' )
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1555:7: '(' (lv_nc_8= ruleNameClass ) ')'
                    {
                    match(input,26,FOLLOW_26_in_ruleNameClass3487); 

                            createLeafNode(grammarAccess.getNameClassAccess().getLeftParenthesisKeyword_4_0(), null); 
                        
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1559:1: (lv_nc_8= ruleNameClass )
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1562:6: lv_nc_8= ruleNameClass
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getNameClassAccess().getNcNameClassParserRuleCall_4_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleNameClass_in_ruleNameClass3521);
                    lv_nc_8=ruleNameClass();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getNameClassRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "nc", lv_nc_8, "NameClass", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }

                    match(input,27,FOLLOW_27_in_ruleNameClass3534); 

                            createLeafNode(grammarAccess.getNameClassAccess().getRightParenthesisKeyword_4_2(), null); 
                        

                    }


                    }
                    break;

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleNameClass


    // $ANTLR start entryRuleDataTypeName
    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1591:1: entryRuleDataTypeName returns [String current=null] : iv_ruleDataTypeName= ruleDataTypeName EOF ;
    public final String entryRuleDataTypeName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleDataTypeName = null;


        try {
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1591:53: (iv_ruleDataTypeName= ruleDataTypeName EOF )
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1592:2: iv_ruleDataTypeName= ruleDataTypeName EOF
            {
             currentNode = createCompositeNode(grammarAccess.getDataTypeNameRule(), currentNode); 
            pushFollow(FOLLOW_ruleDataTypeName_in_entryRuleDataTypeName3569);
            iv_ruleDataTypeName=ruleDataTypeName();
            _fsp--;

             current =iv_ruleDataTypeName.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleDataTypeName3580); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleDataTypeName


    // $ANTLR start ruleDataTypeName
    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1599:1: ruleDataTypeName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_CName_0= ruleCName | kw= 'string' | kw= 'token' ) ;
    public final AntlrDatatypeRuleToken ruleDataTypeName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        AntlrDatatypeRuleToken this_CName_0 = null;


         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1605:6: ( (this_CName_0= ruleCName | kw= 'string' | kw= 'token' ) )
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1606:1: (this_CName_0= ruleCName | kw= 'string' | kw= 'token' )
            {
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1606:1: (this_CName_0= ruleCName | kw= 'string' | kw= 'token' )
            int alt40=3;
            switch ( input.LA(1) ) {
            case RULE_ID:
                {
                alt40=1;
                }
                break;
            case 41:
                {
                alt40=2;
                }
                break;
            case 42:
                {
                alt40=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("1606:1: (this_CName_0= ruleCName | kw= 'string' | kw= 'token' )", 40, 0, input);

                throw nvae;
            }

            switch (alt40) {
                case 1 :
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1607:5: this_CName_0= ruleCName
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getDataTypeNameAccess().getCNameParserRuleCall_0(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleCName_in_ruleDataTypeName3627);
                    this_CName_0=ruleCName();
                    _fsp--;


                    		current.merge(this_CName_0);
                        
                     
                            currentNode = currentNode.getParent();
                        

                    }
                    break;
                case 2 :
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1619:2: kw= 'string'
                    {
                    kw=(Token)input.LT(1);
                    match(input,41,FOLLOW_41_in_ruleDataTypeName3651); 

                            current.merge(kw);
                            createLeafNode(grammarAccess.getDataTypeNameAccess().getStringKeyword_1(), null); 
                        

                    }
                    break;
                case 3 :
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1626:2: kw= 'token'
                    {
                    kw=(Token)input.LT(1);
                    match(input,42,FOLLOW_42_in_ruleDataTypeName3670); 

                            current.merge(kw);
                            createLeafNode(grammarAccess.getDataTypeNameAccess().getTokenKeyword_2(), null); 
                        

                    }
                    break;

            }


            }

             resetLookahead(); 
            	    lastConsumedNode = currentNode;
            	    lastConsumedDatatypeToken = current;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleDataTypeName


    // $ANTLR start entryRuleDataTypeValue
    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1639:1: entryRuleDataTypeValue returns [EObject current=null] : iv_ruleDataTypeValue= ruleDataTypeValue EOF ;
    public final EObject entryRuleDataTypeValue() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDataTypeValue = null;


        try {
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1639:55: (iv_ruleDataTypeValue= ruleDataTypeValue EOF )
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1640:2: iv_ruleDataTypeValue= ruleDataTypeValue EOF
            {
             currentNode = createCompositeNode(grammarAccess.getDataTypeValueRule(), currentNode); 
            pushFollow(FOLLOW_ruleDataTypeValue_in_entryRuleDataTypeValue3708);
            iv_ruleDataTypeValue=ruleDataTypeValue();
            _fsp--;

             current =iv_ruleDataTypeValue; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleDataTypeValue3718); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleDataTypeValue


    // $ANTLR start ruleDataTypeValue
    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1647:1: ruleDataTypeValue returns [EObject current=null] : this_Literal_0= ruleLiteral ;
    public final EObject ruleDataTypeValue() throws RecognitionException {
        EObject current = null;

        EObject this_Literal_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1652:6: (this_Literal_0= ruleLiteral )
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1654:5: this_Literal_0= ruleLiteral
            {
             
                    currentNode=createCompositeNode(grammarAccess.getDataTypeValueAccess().getLiteralParserRuleCall(), currentNode); 
                
            pushFollow(FOLLOW_ruleLiteral_in_ruleDataTypeValue3764);
            this_Literal_0=ruleLiteral();
            _fsp--;

             
                    current = this_Literal_0; 
                    currentNode = currentNode.getParent();
                

            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleDataTypeValue


    // $ANTLR start entryRuleAnyURILiteral
    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1669:1: entryRuleAnyURILiteral returns [EObject current=null] : iv_ruleAnyURILiteral= ruleAnyURILiteral EOF ;
    public final EObject entryRuleAnyURILiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAnyURILiteral = null;


        try {
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1669:55: (iv_ruleAnyURILiteral= ruleAnyURILiteral EOF )
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1670:2: iv_ruleAnyURILiteral= ruleAnyURILiteral EOF
            {
             currentNode = createCompositeNode(grammarAccess.getAnyURILiteralRule(), currentNode); 
            pushFollow(FOLLOW_ruleAnyURILiteral_in_entryRuleAnyURILiteral3795);
            iv_ruleAnyURILiteral=ruleAnyURILiteral();
            _fsp--;

             current =iv_ruleAnyURILiteral; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAnyURILiteral3805); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleAnyURILiteral


    // $ANTLR start ruleAnyURILiteral
    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1677:1: ruleAnyURILiteral returns [EObject current=null] : this_Literal_0= ruleLiteral ;
    public final EObject ruleAnyURILiteral() throws RecognitionException {
        EObject current = null;

        EObject this_Literal_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1682:6: (this_Literal_0= ruleLiteral )
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1684:5: this_Literal_0= ruleLiteral
            {
             
                    currentNode=createCompositeNode(grammarAccess.getAnyURILiteralAccess().getLiteralParserRuleCall(), currentNode); 
                
            pushFollow(FOLLOW_ruleLiteral_in_ruleAnyURILiteral3851);
            this_Literal_0=ruleLiteral();
            _fsp--;

             
                    current = this_Literal_0; 
                    currentNode = currentNode.getParent();
                

            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleAnyURILiteral


    // $ANTLR start entryRuleInherit
    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1701:1: entryRuleInherit returns [EObject current=null] : iv_ruleInherit= ruleInherit EOF ;
    public final EObject entryRuleInherit() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInherit = null;


        try {
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1701:49: (iv_ruleInherit= ruleInherit EOF )
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1702:2: iv_ruleInherit= ruleInherit EOF
            {
             currentNode = createCompositeNode(grammarAccess.getInheritRule(), currentNode); 
            pushFollow(FOLLOW_ruleInherit_in_entryRuleInherit3884);
            iv_ruleInherit=ruleInherit();
            _fsp--;

             current =iv_ruleInherit; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleInherit3894); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleInherit


    // $ANTLR start ruleInherit
    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1709:1: ruleInherit returns [EObject current=null] : ( 'inherit' '=' this_IdentifierOrKeyWord_2= ruleIdentifierOrKeyWord ) ;
    public final EObject ruleInherit() throws RecognitionException {
        EObject current = null;

        EObject this_IdentifierOrKeyWord_2 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1714:6: ( ( 'inherit' '=' this_IdentifierOrKeyWord_2= ruleIdentifierOrKeyWord ) )
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1715:1: ( 'inherit' '=' this_IdentifierOrKeyWord_2= ruleIdentifierOrKeyWord )
            {
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1715:1: ( 'inherit' '=' this_IdentifierOrKeyWord_2= ruleIdentifierOrKeyWord )
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1715:2: 'inherit' '=' this_IdentifierOrKeyWord_2= ruleIdentifierOrKeyWord
            {
            match(input,43,FOLLOW_43_in_ruleInherit3928); 

                    createLeafNode(grammarAccess.getInheritAccess().getInheritKeyword_0(), null); 
                
            match(input,12,FOLLOW_12_in_ruleInherit3937); 

                    createLeafNode(grammarAccess.getInheritAccess().getEqualsSignKeyword_1(), null); 
                
             
                    currentNode=createCompositeNode(grammarAccess.getInheritAccess().getIdentifierOrKeyWordParserRuleCall_2(), currentNode); 
                
            pushFollow(FOLLOW_ruleIdentifierOrKeyWord_in_ruleInherit3959);
            this_IdentifierOrKeyWord_2=ruleIdentifierOrKeyWord();
            _fsp--;

             
                    current = this_IdentifierOrKeyWord_2; 
                    currentNode = currentNode.getParent();
                

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleInherit


    // $ANTLR start entryRuleIdentifierOrKeyWord
    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1739:1: entryRuleIdentifierOrKeyWord returns [EObject current=null] : iv_ruleIdentifierOrKeyWord= ruleIdentifierOrKeyWord EOF ;
    public final EObject entryRuleIdentifierOrKeyWord() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIdentifierOrKeyWord = null;


        try {
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1739:61: (iv_ruleIdentifierOrKeyWord= ruleIdentifierOrKeyWord EOF )
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1740:2: iv_ruleIdentifierOrKeyWord= ruleIdentifierOrKeyWord EOF
            {
             currentNode = createCompositeNode(grammarAccess.getIdentifierOrKeyWordRule(), currentNode); 
            pushFollow(FOLLOW_ruleIdentifierOrKeyWord_in_entryRuleIdentifierOrKeyWord3991);
            iv_ruleIdentifierOrKeyWord=ruleIdentifierOrKeyWord();
            _fsp--;

             current =iv_ruleIdentifierOrKeyWord; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleIdentifierOrKeyWord4001); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleIdentifierOrKeyWord


    // $ANTLR start ruleIdentifierOrKeyWord
    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1747:1: ruleIdentifierOrKeyWord returns [EObject current=null] : (this_Identifier_0= ruleIdentifier | this_KeyWord_1= ruleKeyWord ) ;
    public final EObject ruleIdentifierOrKeyWord() throws RecognitionException {
        EObject current = null;

        EObject this_Identifier_0 = null;

        EObject this_KeyWord_1 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1752:6: ( (this_Identifier_0= ruleIdentifier | this_KeyWord_1= ruleKeyWord ) )
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1753:1: (this_Identifier_0= ruleIdentifier | this_KeyWord_1= ruleKeyWord )
            {
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1753:1: (this_Identifier_0= ruleIdentifier | this_KeyWord_1= ruleKeyWord )
            int alt41=2;
            int LA41_0 = input.LA(1);

            if ( (LA41_0==RULE_ID||LA41_0==44) ) {
                alt41=1;
            }
            else if ( (LA41_0==11||(LA41_0>=13 && LA41_0<=15)||(LA41_0>=18 && LA41_0<=21)||(LA41_0>=23 && LA41_0<=25)||LA41_0==29||LA41_0==34||(LA41_0>=36 && LA41_0<=38)||(LA41_0>=41 && LA41_0<=43)) ) {
                alt41=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("1753:1: (this_Identifier_0= ruleIdentifier | this_KeyWord_1= ruleKeyWord )", 41, 0, input);

                throw nvae;
            }
            switch (alt41) {
                case 1 :
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1754:5: this_Identifier_0= ruleIdentifier
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getIdentifierOrKeyWordAccess().getIdentifierParserRuleCall_0(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleIdentifier_in_ruleIdentifierOrKeyWord4048);
                    this_Identifier_0=ruleIdentifier();
                    _fsp--;

                     
                            current = this_Identifier_0; 
                            currentNode = currentNode.getParent();
                        

                    }
                    break;
                case 2 :
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1764:5: this_KeyWord_1= ruleKeyWord
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getIdentifierOrKeyWordAccess().getKeyWordParserRuleCall_1(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleKeyWord_in_ruleIdentifierOrKeyWord4075);
                    this_KeyWord_1=ruleKeyWord();
                    _fsp--;

                     
                            current = this_KeyWord_1; 
                            currentNode = currentNode.getParent();
                        

                    }
                    break;

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleIdentifierOrKeyWord


    // $ANTLR start entryRuleIdentifier
    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1779:1: entryRuleIdentifier returns [EObject current=null] : iv_ruleIdentifier= ruleIdentifier EOF ;
    public final EObject entryRuleIdentifier() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIdentifier = null;


        try {
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1779:52: (iv_ruleIdentifier= ruleIdentifier EOF )
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1780:2: iv_ruleIdentifier= ruleIdentifier EOF
            {
             currentNode = createCompositeNode(grammarAccess.getIdentifierRule(), currentNode); 
            pushFollow(FOLLOW_ruleIdentifier_in_entryRuleIdentifier4107);
            iv_ruleIdentifier=ruleIdentifier();
            _fsp--;

             current =iv_ruleIdentifier; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleIdentifier4117); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleIdentifier


    // $ANTLR start ruleIdentifier
    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1787:1: ruleIdentifier returns [EObject current=null] : ( ( RULE_ID (lv_keyWord_1= ruleKeyWord ) ) | ruleQuotedIdentifier ) ;
    public final EObject ruleIdentifier() throws RecognitionException {
        EObject current = null;

        EObject lv_keyWord_1 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1792:6: ( ( ( RULE_ID (lv_keyWord_1= ruleKeyWord ) ) | ruleQuotedIdentifier ) )
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1793:1: ( ( RULE_ID (lv_keyWord_1= ruleKeyWord ) ) | ruleQuotedIdentifier )
            {
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1793:1: ( ( RULE_ID (lv_keyWord_1= ruleKeyWord ) ) | ruleQuotedIdentifier )
            int alt42=2;
            int LA42_0 = input.LA(1);

            if ( (LA42_0==RULE_ID) ) {
                alt42=1;
            }
            else if ( (LA42_0==44) ) {
                alt42=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("1793:1: ( ( RULE_ID (lv_keyWord_1= ruleKeyWord ) ) | ruleQuotedIdentifier )", 42, 0, input);

                throw nvae;
            }
            switch (alt42) {
                case 1 :
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1793:2: ( RULE_ID (lv_keyWord_1= ruleKeyWord ) )
                    {
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1793:2: ( RULE_ID (lv_keyWord_1= ruleKeyWord ) )
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1793:3: RULE_ID (lv_keyWord_1= ruleKeyWord )
                    {
                    match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleIdentifier4152); 
                     
                        createLeafNode(grammarAccess.getIdentifierAccess().getIDTerminalRuleCall_0_0(), null); 
                        
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1797:1: (lv_keyWord_1= ruleKeyWord )
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1800:6: lv_keyWord_1= ruleKeyWord
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getIdentifierAccess().getKeyWordKeyWordParserRuleCall_0_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleKeyWord_in_ruleIdentifier4185);
                    lv_keyWord_1=ruleKeyWord();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getIdentifierRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "keyWord", lv_keyWord_1, "KeyWord", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1819:6: ruleQuotedIdentifier
                    {
                    pushFollow(FOLLOW_ruleQuotedIdentifier_in_ruleIdentifier4205);
                    ruleQuotedIdentifier();
                    _fsp--;


                    }
                    break;

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleIdentifier


    // $ANTLR start entryRuleQuotedIdentifier
    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1826:1: entryRuleQuotedIdentifier returns [String current=null] : iv_ruleQuotedIdentifier= ruleQuotedIdentifier EOF ;
    public final String entryRuleQuotedIdentifier() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleQuotedIdentifier = null;


        try {
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1826:57: (iv_ruleQuotedIdentifier= ruleQuotedIdentifier EOF )
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1827:2: iv_ruleQuotedIdentifier= ruleQuotedIdentifier EOF
            {
             currentNode = createCompositeNode(grammarAccess.getQuotedIdentifierRule(), currentNode); 
            pushFollow(FOLLOW_ruleQuotedIdentifier_in_entryRuleQuotedIdentifier4231);
            iv_ruleQuotedIdentifier=ruleQuotedIdentifier();
            _fsp--;

             current =iv_ruleQuotedIdentifier.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleQuotedIdentifier4242); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleQuotedIdentifier


    // $ANTLR start ruleQuotedIdentifier
    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1834:1: ruleQuotedIdentifier returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= '\\\\' this_ID_1= RULE_ID ) ;
    public final AntlrDatatypeRuleToken ruleQuotedIdentifier() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        Token this_ID_1=null;

         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1840:6: ( (kw= '\\\\' this_ID_1= RULE_ID ) )
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1841:1: (kw= '\\\\' this_ID_1= RULE_ID )
            {
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1841:1: (kw= '\\\\' this_ID_1= RULE_ID )
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1842:2: kw= '\\\\' this_ID_1= RULE_ID
            {
            kw=(Token)input.LT(1);
            match(input,44,FOLLOW_44_in_ruleQuotedIdentifier4280); 

                    current.merge(kw);
                    createLeafNode(grammarAccess.getQuotedIdentifierAccess().getReverseSolidusKeyword_0(), null); 
                
            this_ID_1=(Token)input.LT(1);
            match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleQuotedIdentifier4295); 

            		current.merge(this_ID_1);
                
             
                createLeafNode(grammarAccess.getQuotedIdentifierAccess().getIDTerminalRuleCall_1(), null); 
                

            }


            }

             resetLookahead(); 
            	    lastConsumedNode = currentNode;
            	    lastConsumedDatatypeToken = current;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleQuotedIdentifier


    // $ANTLR start entryRuleCName
    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1862:1: entryRuleCName returns [String current=null] : iv_ruleCName= ruleCName EOF ;
    public final String entryRuleCName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleCName = null;


        try {
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1862:46: (iv_ruleCName= ruleCName EOF )
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1863:2: iv_ruleCName= ruleCName EOF
            {
             currentNode = createCompositeNode(grammarAccess.getCNameRule(), currentNode); 
            pushFollow(FOLLOW_ruleCName_in_entryRuleCName4339);
            iv_ruleCName=ruleCName();
            _fsp--;

             current =iv_ruleCName.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleCName4350); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleCName


    // $ANTLR start ruleCName
    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1870:1: ruleCName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_ID_0= RULE_ID kw= ':' this_ID_2= RULE_ID ) ;
    public final AntlrDatatypeRuleToken ruleCName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;
        Token kw=null;
        Token this_ID_2=null;

         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1876:6: ( (this_ID_0= RULE_ID kw= ':' this_ID_2= RULE_ID ) )
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1877:1: (this_ID_0= RULE_ID kw= ':' this_ID_2= RULE_ID )
            {
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1877:1: (this_ID_0= RULE_ID kw= ':' this_ID_2= RULE_ID )
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1877:6: this_ID_0= RULE_ID kw= ':' this_ID_2= RULE_ID
            {
            this_ID_0=(Token)input.LT(1);
            match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleCName4390); 

            		current.merge(this_ID_0);
                
             
                createLeafNode(grammarAccess.getCNameAccess().getIDTerminalRuleCall_0(), null); 
                
            kw=(Token)input.LT(1);
            match(input,45,FOLLOW_45_in_ruleCName4408); 

                    current.merge(kw);
                    createLeafNode(grammarAccess.getCNameAccess().getColonKeyword_1(), null); 
                
            this_ID_2=(Token)input.LT(1);
            match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleCName4423); 

            		current.merge(this_ID_2);
                
             
                createLeafNode(grammarAccess.getCNameAccess().getIDTerminalRuleCall_2(), null); 
                

            }


            }

             resetLookahead(); 
            	    lastConsumedNode = currentNode;
            	    lastConsumedDatatypeToken = current;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleCName


    // $ANTLR start entryRuleAnyName
    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1905:1: entryRuleAnyName returns [EObject current=null] : iv_ruleAnyName= ruleAnyName EOF ;
    public final EObject entryRuleAnyName() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAnyName = null;


        try {
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1905:49: (iv_ruleAnyName= ruleAnyName EOF )
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1906:2: iv_ruleAnyName= ruleAnyName EOF
            {
             currentNode = createCompositeNode(grammarAccess.getAnyNameRule(), currentNode); 
            pushFollow(FOLLOW_ruleAnyName_in_entryRuleAnyName4466);
            iv_ruleAnyName=ruleAnyName();
            _fsp--;

             current =iv_ruleAnyName; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAnyName4476); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleAnyName


    // $ANTLR start ruleAnyName
    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1913:1: ruleAnyName returns [EObject current=null] : '*' ;
    public final EObject ruleAnyName() throws RecognitionException {
        EObject current = null;

         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1918:6: ( '*' )
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1919:1: '*'
            {
            match(input,32,FOLLOW_32_in_ruleAnyName4509); 

                    createLeafNode(grammarAccess.getAnyNameAccess().getAsteriskKeyword(), null); 
                

            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleAnyName


    // $ANTLR start entryRuleLiteral
    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1930:1: entryRuleLiteral returns [EObject current=null] : iv_ruleLiteral= ruleLiteral EOF ;
    public final EObject entryRuleLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLiteral = null;


        try {
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1930:49: (iv_ruleLiteral= ruleLiteral EOF )
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1931:2: iv_ruleLiteral= ruleLiteral EOF
            {
             currentNode = createCompositeNode(grammarAccess.getLiteralRule(), currentNode); 
            pushFollow(FOLLOW_ruleLiteral_in_entryRuleLiteral4541);
            iv_ruleLiteral=ruleLiteral();
            _fsp--;

             current =iv_ruleLiteral; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleLiteral4551); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleLiteral


    // $ANTLR start ruleLiteral
    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1938:1: ruleLiteral returns [EObject current=null] : ( RULE_STRING ( '~' (lv_literalSegment_2= RULE_STRING ) )+ ) ;
    public final EObject ruleLiteral() throws RecognitionException {
        EObject current = null;

        Token lv_literalSegment_2=null;

         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1943:6: ( ( RULE_STRING ( '~' (lv_literalSegment_2= RULE_STRING ) )+ ) )
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1944:1: ( RULE_STRING ( '~' (lv_literalSegment_2= RULE_STRING ) )+ )
            {
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1944:1: ( RULE_STRING ( '~' (lv_literalSegment_2= RULE_STRING ) )+ )
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1944:2: RULE_STRING ( '~' (lv_literalSegment_2= RULE_STRING ) )+
            {
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleLiteral4585); 
             
                createLeafNode(grammarAccess.getLiteralAccess().getSTRINGTerminalRuleCall_0(), null); 
                
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1948:1: ( '~' (lv_literalSegment_2= RULE_STRING ) )+
            int cnt43=0;
            loop43:
            do {
                int alt43=2;
                int LA43_0 = input.LA(1);

                if ( (LA43_0==46) ) {
                    alt43=1;
                }


                switch (alt43) {
            	case 1 :
            	    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1948:2: '~' (lv_literalSegment_2= RULE_STRING )
            	    {
            	    match(input,46,FOLLOW_46_in_ruleLiteral4594); 

            	            createLeafNode(grammarAccess.getLiteralAccess().getTildeKeyword_1_0(), null); 
            	        
            	    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1952:1: (lv_literalSegment_2= RULE_STRING )
            	    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1954:6: lv_literalSegment_2= RULE_STRING
            	    {
            	    lv_literalSegment_2=(Token)input.LT(1);
            	    match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleLiteral4616); 

            	    		createLeafNode(grammarAccess.getLiteralAccess().getLiteralSegmentSTRINGTerminalRuleCall_1_1_0(), "literalSegment"); 
            	    	

            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getLiteralRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode, current);
            	    	        }
            	    	        
            	    	        try {
            	    	       		set(current, "literalSegment", lv_literalSegment_2, "STRING", lastConsumedNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt43 >= 1 ) break loop43;
                        EarlyExitException eee =
                            new EarlyExitException(43, input);
                        throw eee;
                }
                cnt43++;
            } while (true);


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleLiteral


    // $ANTLR start entryRuleKeyWord
    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1979:1: entryRuleKeyWord returns [EObject current=null] : iv_ruleKeyWord= ruleKeyWord EOF ;
    public final EObject entryRuleKeyWord() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKeyWord = null;


        try {
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1979:49: (iv_ruleKeyWord= ruleKeyWord EOF )
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1980:2: iv_ruleKeyWord= ruleKeyWord EOF
            {
             currentNode = createCompositeNode(grammarAccess.getKeyWordRule(), currentNode); 
            pushFollow(FOLLOW_ruleKeyWord_in_entryRuleKeyWord4659);
            iv_ruleKeyWord=ruleKeyWord();
            _fsp--;

             current =iv_ruleKeyWord; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleKeyWord4669); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleKeyWord


    // $ANTLR start ruleKeyWord
    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1987:1: ruleKeyWord returns [EObject current=null] : ( 'attribute' | 'default' | 'datatypes' | 'div' | 'element' | 'empty' | 'external' | 'grammar' | 'include' | 'inherit' | 'list' | 'mixed' | 'namespace' | 'notAllowed' | 'parent' | 'start' | 'string' | 'text' | 'token' ) ;
    public final EObject ruleKeyWord() throws RecognitionException {
        EObject current = null;

         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1992:6: ( ( 'attribute' | 'default' | 'datatypes' | 'div' | 'element' | 'empty' | 'external' | 'grammar' | 'include' | 'inherit' | 'list' | 'mixed' | 'namespace' | 'notAllowed' | 'parent' | 'start' | 'string' | 'text' | 'token' ) )
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1993:1: ( 'attribute' | 'default' | 'datatypes' | 'div' | 'element' | 'empty' | 'external' | 'grammar' | 'include' | 'inherit' | 'list' | 'mixed' | 'namespace' | 'notAllowed' | 'parent' | 'start' | 'string' | 'text' | 'token' )
            {
            // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1993:1: ( 'attribute' | 'default' | 'datatypes' | 'div' | 'element' | 'empty' | 'external' | 'grammar' | 'include' | 'inherit' | 'list' | 'mixed' | 'namespace' | 'notAllowed' | 'parent' | 'start' | 'string' | 'text' | 'token' )
            int alt44=19;
            switch ( input.LA(1) ) {
            case 34:
                {
                alt44=1;
                }
                break;
            case 13:
                {
                alt44=2;
                }
                break;
            case 14:
                {
                alt44=3;
                }
                break;
            case 36:
                {
                alt44=4;
                }
                break;
            case 29:
                {
                alt44=5;
                }
                break;
            case 20:
                {
                alt44=6;
                }
                break;
            case 24:
                {
                alt44=7;
                }
                break;
            case 25:
                {
                alt44=8;
                }
                break;
            case 37:
                {
                alt44=9;
                }
                break;
            case 43:
                {
                alt44=10;
                }
                break;
            case 15:
                {
                alt44=11;
                }
                break;
            case 18:
                {
                alt44=12;
                }
                break;
            case 11:
                {
                alt44=13;
                }
                break;
            case 23:
                {
                alt44=14;
                }
                break;
            case 19:
                {
                alt44=15;
                }
                break;
            case 38:
                {
                alt44=16;
                }
                break;
            case 41:
                {
                alt44=17;
                }
                break;
            case 21:
                {
                alt44=18;
                }
                break;
            case 42:
                {
                alt44=19;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("1993:1: ( 'attribute' | 'default' | 'datatypes' | 'div' | 'element' | 'empty' | 'external' | 'grammar' | 'include' | 'inherit' | 'list' | 'mixed' | 'namespace' | 'notAllowed' | 'parent' | 'start' | 'string' | 'text' | 'token' )", 44, 0, input);

                throw nvae;
            }

            switch (alt44) {
                case 1 :
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1993:2: 'attribute'
                    {
                    match(input,34,FOLLOW_34_in_ruleKeyWord4703); 

                            createLeafNode(grammarAccess.getKeyWordAccess().getAttributeKeyword_0(), null); 
                        

                    }
                    break;
                case 2 :
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:1998:6: 'default'
                    {
                    match(input,13,FOLLOW_13_in_ruleKeyWord4718); 

                            createLeafNode(grammarAccess.getKeyWordAccess().getDefaultKeyword_1(), null); 
                        

                    }
                    break;
                case 3 :
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:2003:6: 'datatypes'
                    {
                    match(input,14,FOLLOW_14_in_ruleKeyWord4733); 

                            createLeafNode(grammarAccess.getKeyWordAccess().getDatatypesKeyword_2(), null); 
                        

                    }
                    break;
                case 4 :
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:2008:6: 'div'
                    {
                    match(input,36,FOLLOW_36_in_ruleKeyWord4748); 

                            createLeafNode(grammarAccess.getKeyWordAccess().getDivKeyword_3(), null); 
                        

                    }
                    break;
                case 5 :
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:2013:6: 'element'
                    {
                    match(input,29,FOLLOW_29_in_ruleKeyWord4763); 

                            createLeafNode(grammarAccess.getKeyWordAccess().getElementKeyword_4(), null); 
                        

                    }
                    break;
                case 6 :
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:2018:6: 'empty'
                    {
                    match(input,20,FOLLOW_20_in_ruleKeyWord4778); 

                            createLeafNode(grammarAccess.getKeyWordAccess().getEmptyKeyword_5(), null); 
                        

                    }
                    break;
                case 7 :
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:2023:6: 'external'
                    {
                    match(input,24,FOLLOW_24_in_ruleKeyWord4793); 

                            createLeafNode(grammarAccess.getKeyWordAccess().getExternalKeyword_6(), null); 
                        

                    }
                    break;
                case 8 :
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:2028:6: 'grammar'
                    {
                    match(input,25,FOLLOW_25_in_ruleKeyWord4808); 

                            createLeafNode(grammarAccess.getKeyWordAccess().getGrammarKeyword_7(), null); 
                        

                    }
                    break;
                case 9 :
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:2033:6: 'include'
                    {
                    match(input,37,FOLLOW_37_in_ruleKeyWord4823); 

                            createLeafNode(grammarAccess.getKeyWordAccess().getIncludeKeyword_8(), null); 
                        

                    }
                    break;
                case 10 :
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:2038:6: 'inherit'
                    {
                    match(input,43,FOLLOW_43_in_ruleKeyWord4838); 

                            createLeafNode(grammarAccess.getKeyWordAccess().getInheritKeyword_9(), null); 
                        

                    }
                    break;
                case 11 :
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:2043:6: 'list'
                    {
                    match(input,15,FOLLOW_15_in_ruleKeyWord4853); 

                            createLeafNode(grammarAccess.getKeyWordAccess().getListKeyword_10(), null); 
                        

                    }
                    break;
                case 12 :
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:2048:6: 'mixed'
                    {
                    match(input,18,FOLLOW_18_in_ruleKeyWord4868); 

                            createLeafNode(grammarAccess.getKeyWordAccess().getMixedKeyword_11(), null); 
                        

                    }
                    break;
                case 13 :
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:2053:6: 'namespace'
                    {
                    match(input,11,FOLLOW_11_in_ruleKeyWord4883); 

                            createLeafNode(grammarAccess.getKeyWordAccess().getNamespaceKeyword_12(), null); 
                        

                    }
                    break;
                case 14 :
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:2058:6: 'notAllowed'
                    {
                    match(input,23,FOLLOW_23_in_ruleKeyWord4898); 

                            createLeafNode(grammarAccess.getKeyWordAccess().getNotAllowedKeyword_13(), null); 
                        

                    }
                    break;
                case 15 :
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:2063:6: 'parent'
                    {
                    match(input,19,FOLLOW_19_in_ruleKeyWord4913); 

                            createLeafNode(grammarAccess.getKeyWordAccess().getParentKeyword_14(), null); 
                        

                    }
                    break;
                case 16 :
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:2068:6: 'start'
                    {
                    match(input,38,FOLLOW_38_in_ruleKeyWord4928); 

                            createLeafNode(grammarAccess.getKeyWordAccess().getStartKeyword_15(), null); 
                        

                    }
                    break;
                case 17 :
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:2073:6: 'string'
                    {
                    match(input,41,FOLLOW_41_in_ruleKeyWord4943); 

                            createLeafNode(grammarAccess.getKeyWordAccess().getStringKeyword_16(), null); 
                        

                    }
                    break;
                case 18 :
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:2078:6: 'text'
                    {
                    match(input,21,FOLLOW_21_in_ruleKeyWord4958); 

                            createLeafNode(grammarAccess.getKeyWordAccess().getTextKeyword_17(), null); 
                        

                    }
                    break;
                case 19 :
                    // ../org.eclipse.wst.xml.relaxng/src-gen/org/oasisopen/parser/antlr/internal/InternalRelaxng.g:2083:6: 'token'
                    {
                    match(input,42,FOLLOW_42_in_ruleKeyWord4973); 

                            createLeafNode(grammarAccess.getKeyWordAccess().getTokenKeyword_18(), null); 
                        

                    }
                    break;

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleKeyWord


 

    public static final BitSet FOLLOW_ruleTopLevel_in_entryRuleTopLevel73 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleTopLevel83 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDecl_in_ruleTopLevel142 = new BitSet(new long[]{0x0000067427FCE832L});
    public static final BitSet FOLLOW_rulePattern_in_ruleTopLevel182 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleGrammarContent_in_ruleTopLevel226 = new BitSet(new long[]{0x0000007000000012L});
    public static final BitSet FOLLOW_ruleDecl_in_entryRuleDecl265 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDecl275 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_11_in_ruleDecl310 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleDecl332 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleDecl349 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleDecl371 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_ruleDecl396 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_ruleDecl405 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleDecl427 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleDecl444 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleDecl466 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_ruleDecl491 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleDecl513 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleDecl530 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleDecl552 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePattern_in_entryRulePattern594 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePattern604 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleElement_in_rulePattern655 = new BitSet(new long[]{0x0000000420000002L});
    public static final BitSet FOLLOW_ruleAttribute_in_rulePattern669 = new BitSet(new long[]{0x0000000420000002L});
    public static final BitSet FOLLOW_15_in_rulePattern695 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_rulePattern704 = new BitSet(new long[]{0x0000060427FE8030L});
    public static final BitSet FOLLOW_rulePattern_in_rulePattern738 = new BitSet(new long[]{0x0000060427FE8030L});
    public static final BitSet FOLLOW_17_in_rulePattern752 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_rulePattern769 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_rulePattern778 = new BitSet(new long[]{0x0000060427FE8030L});
    public static final BitSet FOLLOW_rulePattern_in_rulePattern812 = new BitSet(new long[]{0x0000060427FE8030L});
    public static final BitSet FOLLOW_17_in_rulePattern826 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rulePattern843 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_12_in_rulePattern852 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_rulePattern871 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_rulePattern880 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_rulePattern895 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_rulePattern910 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_rulePattern925 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDataTypeValue_in_rulePattern965 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_ruleDataTypeName_in_rulePattern986 = new BitSet(new long[]{0x0000000800010002L});
    public static final BitSet FOLLOW_16_in_rulePattern988 = new BitSet(new long[]{0x00001E7423BEE810L});
    public static final BitSet FOLLOW_ruleParam_in_rulePattern1022 = new BitSet(new long[]{0x00001E7423BEE810L});
    public static final BitSet FOLLOW_17_in_rulePattern1036 = new BitSet(new long[]{0x0000000800010002L});
    public static final BitSet FOLLOW_ruleExceptPattern_in_rulePattern1072 = new BitSet(new long[]{0x0000000800000002L});
    public static final BitSet FOLLOW_23_in_rulePattern1093 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_rulePattern1109 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ruleAnyURILiteral_in_rulePattern1143 = new BitSet(new long[]{0x0000080000000002L});
    public static final BitSet FOLLOW_ruleInherit_in_rulePattern1181 = new BitSet(new long[]{0x0000080000000002L});
    public static final BitSet FOLLOW_25_in_rulePattern1203 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_rulePattern1212 = new BitSet(new long[]{0x0000007000020010L});
    public static final BitSet FOLLOW_ruleGrammarContent_in_rulePattern1246 = new BitSet(new long[]{0x0000007000020010L});
    public static final BitSet FOLLOW_17_in_rulePattern1260 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_rulePattern1277 = new BitSet(new long[]{0x000006042FFC8030L});
    public static final BitSet FOLLOW_rulePattern_in_rulePattern1311 = new BitSet(new long[]{0x000006042FFC8030L});
    public static final BitSet FOLLOW_27_in_rulePattern1325 = new BitSet(new long[]{0x0000000010000002L});
    public static final BitSet FOLLOW_28_in_rulePattern1346 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleElement_in_entryRuleElement1394 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleElement1404 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_29_in_ruleElement1438 = new BitSet(new long[]{0x00001E7527FCE810L});
    public static final BitSet FOLLOW_ruleNameClass_in_ruleElement1472 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_ruleElement1485 = new BitSet(new long[]{0x0000060427FE8030L});
    public static final BitSet FOLLOW_rulePattern_in_ruleElement1519 = new BitSet(new long[]{0x0000060427FE8030L});
    public static final BitSet FOLLOW_17_in_ruleElement1533 = new BitSet(new long[]{0x00000003D0400002L});
    public static final BitSet FOLLOW_30_in_ruleElement1555 = new BitSet(new long[]{0x0000000210400002L});
    public static final BitSet FOLLOW_31_in_ruleElement1571 = new BitSet(new long[]{0x0000000210400002L});
    public static final BitSet FOLLOW_32_in_ruleElement1587 = new BitSet(new long[]{0x0000000210400002L});
    public static final BitSet FOLLOW_28_in_ruleElement1625 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_ruleElement1641 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_ruleElement1657 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAttribute_in_entryRuleAttribute1706 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAttribute1716 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_34_in_ruleAttribute1750 = new BitSet(new long[]{0x00001E7527FCE810L});
    public static final BitSet FOLLOW_ruleNameClass_in_ruleAttribute1784 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_ruleAttribute1797 = new BitSet(new long[]{0x0000060427FE8030L});
    public static final BitSet FOLLOW_rulePattern_in_ruleAttribute1831 = new BitSet(new long[]{0x0000060427FE8030L});
    public static final BitSet FOLLOW_17_in_ruleAttribute1845 = new BitSet(new long[]{0x0000000050400002L});
    public static final BitSet FOLLOW_30_in_ruleAttribute1866 = new BitSet(new long[]{0x0000000010400002L});
    public static final BitSet FOLLOW_28_in_ruleAttribute1902 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_ruleAttribute1918 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleParam_in_entryRuleParam1967 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleParam1977 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIdentifierOrKeyWord_in_ruleParam2024 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleParam2032 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ruleLiteral_in_ruleParam2066 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExceptPattern_in_entryRuleExceptPattern2103 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExceptPattern2113 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_35_in_ruleExceptPattern2147 = new BitSet(new long[]{0x0000060427FC8030L});
    public static final BitSet FOLLOW_rulePattern_in_ruleExceptPattern2169 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleGrammarContent_in_entryRuleGrammarContent2201 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleGrammarContent2211 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStart_in_ruleGrammarContent2258 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDefine_in_ruleGrammarContent2285 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_36_in_ruleGrammarContent2300 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_ruleGrammarContent2309 = new BitSet(new long[]{0x0000007000020010L});
    public static final BitSet FOLLOW_ruleGrammarContent_in_ruleGrammarContent2343 = new BitSet(new long[]{0x0000007000020010L});
    public static final BitSet FOLLOW_17_in_ruleGrammarContent2357 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_37_in_ruleGrammarContent2374 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ruleAnyURILiteral_in_ruleGrammarContent2396 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_ruleInherit_in_ruleGrammarContent2429 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_ruleGrammarContent2443 = new BitSet(new long[]{0x0000005000020010L});
    public static final BitSet FOLLOW_ruleIncludeContent_in_ruleGrammarContent2477 = new BitSet(new long[]{0x0000005000020010L});
    public static final BitSet FOLLOW_17_in_ruleGrammarContent2491 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIncludeContent_in_entryRuleIncludeContent2526 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleIncludeContent2536 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDefine_in_ruleIncludeContent2583 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStart_in_ruleIncludeContent2610 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_36_in_ruleIncludeContent2625 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_ruleIncludeContent2634 = new BitSet(new long[]{0x0000007000020010L});
    public static final BitSet FOLLOW_ruleGrammarContent_in_ruleIncludeContent2668 = new BitSet(new long[]{0x0000007000020010L});
    public static final BitSet FOLLOW_17_in_ruleIncludeContent2682 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStart_in_entryRuleStart2716 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleStart2726 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_38_in_ruleStart2760 = new BitSet(new long[]{0x0000018000001000L});
    public static final BitSet FOLLOW_12_in_ruleStart2770 = new BitSet(new long[]{0x0000060427FC8030L});
    public static final BitSet FOLLOW_39_in_ruleStart2785 = new BitSet(new long[]{0x0000060427FC8030L});
    public static final BitSet FOLLOW_40_in_ruleStart2800 = new BitSet(new long[]{0x0000060427FC8030L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleStart2811 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePattern_in_ruleStart2850 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDefine_in_entryRuleDefine2888 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDefine2898 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleDefine2932 = new BitSet(new long[]{0x0000018000001000L});
    public static final BitSet FOLLOW_12_in_ruleDefine2941 = new BitSet(new long[]{0x0000060427FC8030L});
    public static final BitSet FOLLOW_39_in_ruleDefine2956 = new BitSet(new long[]{0x0000060427FC8030L});
    public static final BitSet FOLLOW_40_in_ruleDefine2971 = new BitSet(new long[]{0x0000060427FC8030L});
    public static final BitSet FOLLOW_rulePattern_in_ruleDefine3006 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleName_in_entryRuleName3043 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleName3053 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIdentifierOrKeyWord_in_ruleName3100 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCName_in_ruleName3114 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExceptNameClass_in_entryRuleExceptNameClass3139 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExceptNameClass3149 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_35_in_ruleExceptNameClass3183 = new BitSet(new long[]{0x00001E7527FCE810L});
    public static final BitSet FOLLOW_ruleNameClass_in_ruleExceptNameClass3205 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNameClass_in_entryRuleNameClass3237 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleNameClass3247 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleName_in_ruleNameClass3294 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleNameClass3309 = new BitSet(new long[]{0x0000000800000002L});
    public static final BitSet FOLLOW_ruleExceptNameClass_in_ruleNameClass3342 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAnyName_in_ruleNameClass3377 = new BitSet(new long[]{0x0000000800000002L});
    public static final BitSet FOLLOW_ruleExceptNameClass_in_ruleNameClass3410 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_ruleNameClass3432 = new BitSet(new long[]{0x00001E7527FCE810L});
    public static final BitSet FOLLOW_ruleNameClass_in_ruleNameClass3466 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_ruleNameClass3487 = new BitSet(new long[]{0x00001E7527FCE810L});
    public static final BitSet FOLLOW_ruleNameClass_in_ruleNameClass3521 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_27_in_ruleNameClass3534 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDataTypeName_in_entryRuleDataTypeName3569 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDataTypeName3580 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCName_in_ruleDataTypeName3627 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_41_in_ruleDataTypeName3651 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_42_in_ruleDataTypeName3670 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDataTypeValue_in_entryRuleDataTypeValue3708 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDataTypeValue3718 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLiteral_in_ruleDataTypeValue3764 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAnyURILiteral_in_entryRuleAnyURILiteral3795 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAnyURILiteral3805 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLiteral_in_ruleAnyURILiteral3851 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInherit_in_entryRuleInherit3884 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleInherit3894 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_43_in_ruleInherit3928 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleInherit3937 = new BitSet(new long[]{0x00001E7423BCE810L});
    public static final BitSet FOLLOW_ruleIdentifierOrKeyWord_in_ruleInherit3959 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIdentifierOrKeyWord_in_entryRuleIdentifierOrKeyWord3991 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleIdentifierOrKeyWord4001 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIdentifier_in_ruleIdentifierOrKeyWord4048 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleKeyWord_in_ruleIdentifierOrKeyWord4075 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIdentifier_in_entryRuleIdentifier4107 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleIdentifier4117 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleIdentifier4152 = new BitSet(new long[]{0x00000E7423BCE800L});
    public static final BitSet FOLLOW_ruleKeyWord_in_ruleIdentifier4185 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQuotedIdentifier_in_ruleIdentifier4205 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQuotedIdentifier_in_entryRuleQuotedIdentifier4231 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleQuotedIdentifier4242 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_44_in_ruleQuotedIdentifier4280 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleQuotedIdentifier4295 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCName_in_entryRuleCName4339 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleCName4350 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleCName4390 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_45_in_ruleCName4408 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleCName4423 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAnyName_in_entryRuleAnyName4466 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAnyName4476 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_ruleAnyName4509 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLiteral_in_entryRuleLiteral4541 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleLiteral4551 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleLiteral4585 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_46_in_ruleLiteral4594 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleLiteral4616 = new BitSet(new long[]{0x0000400000000002L});
    public static final BitSet FOLLOW_ruleKeyWord_in_entryRuleKeyWord4659 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleKeyWord4669 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_34_in_ruleKeyWord4703 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_ruleKeyWord4718 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_ruleKeyWord4733 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_36_in_ruleKeyWord4748 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_29_in_ruleKeyWord4763 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_ruleKeyWord4778 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_ruleKeyWord4793 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_ruleKeyWord4808 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_37_in_ruleKeyWord4823 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_43_in_ruleKeyWord4838 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_ruleKeyWord4853 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_ruleKeyWord4868 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_11_in_ruleKeyWord4883 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_ruleKeyWord4898 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_ruleKeyWord4913 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_38_in_ruleKeyWord4928 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_41_in_ruleKeyWord4943 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_ruleKeyWord4958 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_42_in_ruleKeyWord4973 = new BitSet(new long[]{0x0000000000000002L});

}