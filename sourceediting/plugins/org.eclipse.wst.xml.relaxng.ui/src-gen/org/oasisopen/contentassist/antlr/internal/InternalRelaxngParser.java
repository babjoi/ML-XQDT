package org.oasisopen.contentassist.antlr.internal; 

import java.io.InputStream;
import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.xtext.parsetree.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.ui.common.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;
import org.oasisopen.services.RelaxngGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class InternalRelaxngParser extends AbstractInternalContentAssistParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_STRING", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'*'", "'empty'", "'text'", "'|'", "'notAllowed'", "'?'", "'+'", "','", "'&'", "'='", "'|='", "'&='", "'string'", "'token'", "'attribute'", "'default'", "'datatypes'", "'div'", "'element'", "'external'", "'grammar'", "'include'", "'inherit'", "'list'", "'mixed'", "'namespace'", "'parent'", "'start'", "'{'", "'}'", "'('", "')'", "'-'", "'\\\\'", "':'", "'~'"
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
    public String getGrammarFileName() { return "../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g"; }


     
     	private RelaxngGrammarAccess grammarAccess;
     	
        public void setGrammarAccess(RelaxngGrammarAccess grammarAccess) {
        	this.grammarAccess = grammarAccess;
        }
        
        @Override
        protected Grammar getGrammar() {
        	return grammarAccess.getGrammar();
        }




    // $ANTLR start entryRuleTopLevel
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:55:1: entryRuleTopLevel : ruleTopLevel EOF ;
    public final void entryRuleTopLevel() throws RecognitionException {
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:55:19: ( ruleTopLevel EOF )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:56:1: ruleTopLevel EOF
            {
             before(grammarAccess.getTopLevelRule()); 
            pushFollow(FOLLOW_ruleTopLevel_in_entryRuleTopLevel60);
            ruleTopLevel();
            _fsp--;

             after(grammarAccess.getTopLevelRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleTopLevel67); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end entryRuleTopLevel


    // $ANTLR start ruleTopLevel
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:63:1: ruleTopLevel : ( ( rule__TopLevel__Group ) ) ;
    public final void ruleTopLevel() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:67:2: ( ( ( rule__TopLevel__Group ) ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:68:1: ( ( rule__TopLevel__Group ) )
            {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:68:1: ( ( rule__TopLevel__Group ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:69:1: ( rule__TopLevel__Group )
            {
             before(grammarAccess.getTopLevelAccess().getGroup()); 
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:70:1: ( rule__TopLevel__Group )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:70:2: rule__TopLevel__Group
            {
            pushFollow(FOLLOW_rule__TopLevel__Group_in_ruleTopLevel94);
            rule__TopLevel__Group();
            _fsp--;


            }

             after(grammarAccess.getTopLevelAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end ruleTopLevel


    // $ANTLR start entryRuleDecl
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:81:1: entryRuleDecl : ruleDecl EOF ;
    public final void entryRuleDecl() throws RecognitionException {
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:81:15: ( ruleDecl EOF )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:82:1: ruleDecl EOF
            {
             before(grammarAccess.getDeclRule()); 
            pushFollow(FOLLOW_ruleDecl_in_entryRuleDecl119);
            ruleDecl();
            _fsp--;

             after(grammarAccess.getDeclRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleDecl126); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end entryRuleDecl


    // $ANTLR start ruleDecl
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:89:1: ruleDecl : ( ( rule__Decl__Alternatives ) ) ;
    public final void ruleDecl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:93:2: ( ( ( rule__Decl__Alternatives ) ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:94:1: ( ( rule__Decl__Alternatives ) )
            {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:94:1: ( ( rule__Decl__Alternatives ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:95:1: ( rule__Decl__Alternatives )
            {
             before(grammarAccess.getDeclAccess().getAlternatives()); 
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:96:1: ( rule__Decl__Alternatives )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:96:2: rule__Decl__Alternatives
            {
            pushFollow(FOLLOW_rule__Decl__Alternatives_in_ruleDecl153);
            rule__Decl__Alternatives();
            _fsp--;


            }

             after(grammarAccess.getDeclAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end ruleDecl


    // $ANTLR start entryRulePattern
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:107:1: entryRulePattern : rulePattern EOF ;
    public final void entryRulePattern() throws RecognitionException {
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:107:18: ( rulePattern EOF )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:108:1: rulePattern EOF
            {
             before(grammarAccess.getPatternRule()); 
            pushFollow(FOLLOW_rulePattern_in_entryRulePattern178);
            rulePattern();
            _fsp--;

             after(grammarAccess.getPatternRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRulePattern185); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end entryRulePattern


    // $ANTLR start rulePattern
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:115:1: rulePattern : ( ( rule__Pattern__Alternatives ) ) ;
    public final void rulePattern() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:119:2: ( ( ( rule__Pattern__Alternatives ) ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:120:1: ( ( rule__Pattern__Alternatives ) )
            {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:120:1: ( ( rule__Pattern__Alternatives ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:121:1: ( rule__Pattern__Alternatives )
            {
             before(grammarAccess.getPatternAccess().getAlternatives()); 
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:122:1: ( rule__Pattern__Alternatives )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:122:2: rule__Pattern__Alternatives
            {
            pushFollow(FOLLOW_rule__Pattern__Alternatives_in_rulePattern212);
            rule__Pattern__Alternatives();
            _fsp--;


            }

             after(grammarAccess.getPatternAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rulePattern


    // $ANTLR start entryRuleElement
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:133:1: entryRuleElement : ruleElement EOF ;
    public final void entryRuleElement() throws RecognitionException {
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:133:18: ( ruleElement EOF )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:134:1: ruleElement EOF
            {
             before(grammarAccess.getElementRule()); 
            pushFollow(FOLLOW_ruleElement_in_entryRuleElement237);
            ruleElement();
            _fsp--;

             after(grammarAccess.getElementRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleElement244); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end entryRuleElement


    // $ANTLR start ruleElement
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:141:1: ruleElement : ( ( rule__Element__Group ) ) ;
    public final void ruleElement() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:145:2: ( ( ( rule__Element__Group ) ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:146:1: ( ( rule__Element__Group ) )
            {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:146:1: ( ( rule__Element__Group ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:147:1: ( rule__Element__Group )
            {
             before(grammarAccess.getElementAccess().getGroup()); 
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:148:1: ( rule__Element__Group )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:148:2: rule__Element__Group
            {
            pushFollow(FOLLOW_rule__Element__Group_in_ruleElement271);
            rule__Element__Group();
            _fsp--;


            }

             after(grammarAccess.getElementAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end ruleElement


    // $ANTLR start entryRuleAttribute
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:159:1: entryRuleAttribute : ruleAttribute EOF ;
    public final void entryRuleAttribute() throws RecognitionException {
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:159:20: ( ruleAttribute EOF )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:160:1: ruleAttribute EOF
            {
             before(grammarAccess.getAttributeRule()); 
            pushFollow(FOLLOW_ruleAttribute_in_entryRuleAttribute296);
            ruleAttribute();
            _fsp--;

             after(grammarAccess.getAttributeRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAttribute303); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end entryRuleAttribute


    // $ANTLR start ruleAttribute
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:167:1: ruleAttribute : ( ( rule__Attribute__Group ) ) ;
    public final void ruleAttribute() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:171:2: ( ( ( rule__Attribute__Group ) ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:172:1: ( ( rule__Attribute__Group ) )
            {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:172:1: ( ( rule__Attribute__Group ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:173:1: ( rule__Attribute__Group )
            {
             before(grammarAccess.getAttributeAccess().getGroup()); 
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:174:1: ( rule__Attribute__Group )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:174:2: rule__Attribute__Group
            {
            pushFollow(FOLLOW_rule__Attribute__Group_in_ruleAttribute330);
            rule__Attribute__Group();
            _fsp--;


            }

             after(grammarAccess.getAttributeAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end ruleAttribute


    // $ANTLR start entryRuleParam
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:185:1: entryRuleParam : ruleParam EOF ;
    public final void entryRuleParam() throws RecognitionException {
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:185:16: ( ruleParam EOF )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:186:1: ruleParam EOF
            {
             before(grammarAccess.getParamRule()); 
            pushFollow(FOLLOW_ruleParam_in_entryRuleParam355);
            ruleParam();
            _fsp--;

             after(grammarAccess.getParamRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleParam362); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end entryRuleParam


    // $ANTLR start ruleParam
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:193:1: ruleParam : ( ( rule__Param__Group ) ) ;
    public final void ruleParam() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:197:2: ( ( ( rule__Param__Group ) ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:198:1: ( ( rule__Param__Group ) )
            {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:198:1: ( ( rule__Param__Group ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:199:1: ( rule__Param__Group )
            {
             before(grammarAccess.getParamAccess().getGroup()); 
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:200:1: ( rule__Param__Group )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:200:2: rule__Param__Group
            {
            pushFollow(FOLLOW_rule__Param__Group_in_ruleParam389);
            rule__Param__Group();
            _fsp--;


            }

             after(grammarAccess.getParamAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end ruleParam


    // $ANTLR start entryRuleExceptPattern
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:211:1: entryRuleExceptPattern : ruleExceptPattern EOF ;
    public final void entryRuleExceptPattern() throws RecognitionException {
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:211:24: ( ruleExceptPattern EOF )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:212:1: ruleExceptPattern EOF
            {
             before(grammarAccess.getExceptPatternRule()); 
            pushFollow(FOLLOW_ruleExceptPattern_in_entryRuleExceptPattern414);
            ruleExceptPattern();
            _fsp--;

             after(grammarAccess.getExceptPatternRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExceptPattern421); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end entryRuleExceptPattern


    // $ANTLR start ruleExceptPattern
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:219:1: ruleExceptPattern : ( ( rule__ExceptPattern__Group ) ) ;
    public final void ruleExceptPattern() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:223:2: ( ( ( rule__ExceptPattern__Group ) ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:224:1: ( ( rule__ExceptPattern__Group ) )
            {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:224:1: ( ( rule__ExceptPattern__Group ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:225:1: ( rule__ExceptPattern__Group )
            {
             before(grammarAccess.getExceptPatternAccess().getGroup()); 
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:226:1: ( rule__ExceptPattern__Group )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:226:2: rule__ExceptPattern__Group
            {
            pushFollow(FOLLOW_rule__ExceptPattern__Group_in_ruleExceptPattern448);
            rule__ExceptPattern__Group();
            _fsp--;


            }

             after(grammarAccess.getExceptPatternAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end ruleExceptPattern


    // $ANTLR start entryRuleGrammarContent
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:237:1: entryRuleGrammarContent : ruleGrammarContent EOF ;
    public final void entryRuleGrammarContent() throws RecognitionException {
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:237:25: ( ruleGrammarContent EOF )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:238:1: ruleGrammarContent EOF
            {
             before(grammarAccess.getGrammarContentRule()); 
            pushFollow(FOLLOW_ruleGrammarContent_in_entryRuleGrammarContent473);
            ruleGrammarContent();
            _fsp--;

             after(grammarAccess.getGrammarContentRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleGrammarContent480); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end entryRuleGrammarContent


    // $ANTLR start ruleGrammarContent
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:245:1: ruleGrammarContent : ( ( rule__GrammarContent__Alternatives ) ) ;
    public final void ruleGrammarContent() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:249:2: ( ( ( rule__GrammarContent__Alternatives ) ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:250:1: ( ( rule__GrammarContent__Alternatives ) )
            {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:250:1: ( ( rule__GrammarContent__Alternatives ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:251:1: ( rule__GrammarContent__Alternatives )
            {
             before(grammarAccess.getGrammarContentAccess().getAlternatives()); 
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:252:1: ( rule__GrammarContent__Alternatives )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:252:2: rule__GrammarContent__Alternatives
            {
            pushFollow(FOLLOW_rule__GrammarContent__Alternatives_in_ruleGrammarContent507);
            rule__GrammarContent__Alternatives();
            _fsp--;


            }

             after(grammarAccess.getGrammarContentAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end ruleGrammarContent


    // $ANTLR start entryRuleIncludeContent
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:263:1: entryRuleIncludeContent : ruleIncludeContent EOF ;
    public final void entryRuleIncludeContent() throws RecognitionException {
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:263:25: ( ruleIncludeContent EOF )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:264:1: ruleIncludeContent EOF
            {
             before(grammarAccess.getIncludeContentRule()); 
            pushFollow(FOLLOW_ruleIncludeContent_in_entryRuleIncludeContent532);
            ruleIncludeContent();
            _fsp--;

             after(grammarAccess.getIncludeContentRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleIncludeContent539); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end entryRuleIncludeContent


    // $ANTLR start ruleIncludeContent
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:271:1: ruleIncludeContent : ( ( rule__IncludeContent__Alternatives ) ) ;
    public final void ruleIncludeContent() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:275:2: ( ( ( rule__IncludeContent__Alternatives ) ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:276:1: ( ( rule__IncludeContent__Alternatives ) )
            {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:276:1: ( ( rule__IncludeContent__Alternatives ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:277:1: ( rule__IncludeContent__Alternatives )
            {
             before(grammarAccess.getIncludeContentAccess().getAlternatives()); 
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:278:1: ( rule__IncludeContent__Alternatives )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:278:2: rule__IncludeContent__Alternatives
            {
            pushFollow(FOLLOW_rule__IncludeContent__Alternatives_in_ruleIncludeContent566);
            rule__IncludeContent__Alternatives();
            _fsp--;


            }

             after(grammarAccess.getIncludeContentAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end ruleIncludeContent


    // $ANTLR start entryRuleStart
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:289:1: entryRuleStart : ruleStart EOF ;
    public final void entryRuleStart() throws RecognitionException {
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:289:16: ( ruleStart EOF )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:290:1: ruleStart EOF
            {
             before(grammarAccess.getStartRule()); 
            pushFollow(FOLLOW_ruleStart_in_entryRuleStart591);
            ruleStart();
            _fsp--;

             after(grammarAccess.getStartRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleStart598); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end entryRuleStart


    // $ANTLR start ruleStart
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:297:1: ruleStart : ( ( rule__Start__Group ) ) ;
    public final void ruleStart() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:301:2: ( ( ( rule__Start__Group ) ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:302:1: ( ( rule__Start__Group ) )
            {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:302:1: ( ( rule__Start__Group ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:303:1: ( rule__Start__Group )
            {
             before(grammarAccess.getStartAccess().getGroup()); 
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:304:1: ( rule__Start__Group )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:304:2: rule__Start__Group
            {
            pushFollow(FOLLOW_rule__Start__Group_in_ruleStart625);
            rule__Start__Group();
            _fsp--;


            }

             after(grammarAccess.getStartAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end ruleStart


    // $ANTLR start entryRuleDefine
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:315:1: entryRuleDefine : ruleDefine EOF ;
    public final void entryRuleDefine() throws RecognitionException {
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:315:17: ( ruleDefine EOF )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:316:1: ruleDefine EOF
            {
             before(grammarAccess.getDefineRule()); 
            pushFollow(FOLLOW_ruleDefine_in_entryRuleDefine650);
            ruleDefine();
            _fsp--;

             after(grammarAccess.getDefineRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleDefine657); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end entryRuleDefine


    // $ANTLR start ruleDefine
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:323:1: ruleDefine : ( ( rule__Define__Group ) ) ;
    public final void ruleDefine() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:327:2: ( ( ( rule__Define__Group ) ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:328:1: ( ( rule__Define__Group ) )
            {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:328:1: ( ( rule__Define__Group ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:329:1: ( rule__Define__Group )
            {
             before(grammarAccess.getDefineAccess().getGroup()); 
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:330:1: ( rule__Define__Group )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:330:2: rule__Define__Group
            {
            pushFollow(FOLLOW_rule__Define__Group_in_ruleDefine684);
            rule__Define__Group();
            _fsp--;


            }

             after(grammarAccess.getDefineAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end ruleDefine


    // $ANTLR start entryRuleName
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:341:1: entryRuleName : ruleName EOF ;
    public final void entryRuleName() throws RecognitionException {
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:341:15: ( ruleName EOF )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:342:1: ruleName EOF
            {
             before(grammarAccess.getNameRule()); 
            pushFollow(FOLLOW_ruleName_in_entryRuleName709);
            ruleName();
            _fsp--;

             after(grammarAccess.getNameRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleName716); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end entryRuleName


    // $ANTLR start ruleName
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:349:1: ruleName : ( ( rule__Name__Alternatives ) ) ;
    public final void ruleName() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:353:2: ( ( ( rule__Name__Alternatives ) ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:354:1: ( ( rule__Name__Alternatives ) )
            {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:354:1: ( ( rule__Name__Alternatives ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:355:1: ( rule__Name__Alternatives )
            {
             before(grammarAccess.getNameAccess().getAlternatives()); 
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:356:1: ( rule__Name__Alternatives )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:356:2: rule__Name__Alternatives
            {
            pushFollow(FOLLOW_rule__Name__Alternatives_in_ruleName743);
            rule__Name__Alternatives();
            _fsp--;


            }

             after(grammarAccess.getNameAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end ruleName


    // $ANTLR start entryRuleExceptNameClass
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:367:1: entryRuleExceptNameClass : ruleExceptNameClass EOF ;
    public final void entryRuleExceptNameClass() throws RecognitionException {
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:367:26: ( ruleExceptNameClass EOF )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:368:1: ruleExceptNameClass EOF
            {
             before(grammarAccess.getExceptNameClassRule()); 
            pushFollow(FOLLOW_ruleExceptNameClass_in_entryRuleExceptNameClass768);
            ruleExceptNameClass();
            _fsp--;

             after(grammarAccess.getExceptNameClassRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExceptNameClass775); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end entryRuleExceptNameClass


    // $ANTLR start ruleExceptNameClass
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:375:1: ruleExceptNameClass : ( ( rule__ExceptNameClass__Group ) ) ;
    public final void ruleExceptNameClass() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:379:2: ( ( ( rule__ExceptNameClass__Group ) ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:380:1: ( ( rule__ExceptNameClass__Group ) )
            {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:380:1: ( ( rule__ExceptNameClass__Group ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:381:1: ( rule__ExceptNameClass__Group )
            {
             before(grammarAccess.getExceptNameClassAccess().getGroup()); 
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:382:1: ( rule__ExceptNameClass__Group )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:382:2: rule__ExceptNameClass__Group
            {
            pushFollow(FOLLOW_rule__ExceptNameClass__Group_in_ruleExceptNameClass802);
            rule__ExceptNameClass__Group();
            _fsp--;


            }

             after(grammarAccess.getExceptNameClassAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end ruleExceptNameClass


    // $ANTLR start entryRuleNameClass
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:393:1: entryRuleNameClass : ruleNameClass EOF ;
    public final void entryRuleNameClass() throws RecognitionException {
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:393:20: ( ruleNameClass EOF )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:394:1: ruleNameClass EOF
            {
             before(grammarAccess.getNameClassRule()); 
            pushFollow(FOLLOW_ruleNameClass_in_entryRuleNameClass827);
            ruleNameClass();
            _fsp--;

             after(grammarAccess.getNameClassRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleNameClass834); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end entryRuleNameClass


    // $ANTLR start ruleNameClass
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:401:1: ruleNameClass : ( ( rule__NameClass__Alternatives ) ) ;
    public final void ruleNameClass() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:405:2: ( ( ( rule__NameClass__Alternatives ) ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:406:1: ( ( rule__NameClass__Alternatives ) )
            {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:406:1: ( ( rule__NameClass__Alternatives ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:407:1: ( rule__NameClass__Alternatives )
            {
             before(grammarAccess.getNameClassAccess().getAlternatives()); 
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:408:1: ( rule__NameClass__Alternatives )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:408:2: rule__NameClass__Alternatives
            {
            pushFollow(FOLLOW_rule__NameClass__Alternatives_in_ruleNameClass861);
            rule__NameClass__Alternatives();
            _fsp--;


            }

             after(grammarAccess.getNameClassAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end ruleNameClass


    // $ANTLR start entryRuleDataTypeName
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:419:1: entryRuleDataTypeName : ruleDataTypeName EOF ;
    public final void entryRuleDataTypeName() throws RecognitionException {
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:419:23: ( ruleDataTypeName EOF )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:420:1: ruleDataTypeName EOF
            {
             before(grammarAccess.getDataTypeNameRule()); 
            pushFollow(FOLLOW_ruleDataTypeName_in_entryRuleDataTypeName886);
            ruleDataTypeName();
            _fsp--;

             after(grammarAccess.getDataTypeNameRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleDataTypeName893); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end entryRuleDataTypeName


    // $ANTLR start ruleDataTypeName
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:427:1: ruleDataTypeName : ( ( rule__DataTypeName__Alternatives ) ) ;
    public final void ruleDataTypeName() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:431:2: ( ( ( rule__DataTypeName__Alternatives ) ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:432:1: ( ( rule__DataTypeName__Alternatives ) )
            {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:432:1: ( ( rule__DataTypeName__Alternatives ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:433:1: ( rule__DataTypeName__Alternatives )
            {
             before(grammarAccess.getDataTypeNameAccess().getAlternatives()); 
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:434:1: ( rule__DataTypeName__Alternatives )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:434:2: rule__DataTypeName__Alternatives
            {
            pushFollow(FOLLOW_rule__DataTypeName__Alternatives_in_ruleDataTypeName920);
            rule__DataTypeName__Alternatives();
            _fsp--;


            }

             after(grammarAccess.getDataTypeNameAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end ruleDataTypeName


    // $ANTLR start entryRuleDataTypeValue
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:445:1: entryRuleDataTypeValue : ruleDataTypeValue EOF ;
    public final void entryRuleDataTypeValue() throws RecognitionException {
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:445:24: ( ruleDataTypeValue EOF )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:446:1: ruleDataTypeValue EOF
            {
             before(grammarAccess.getDataTypeValueRule()); 
            pushFollow(FOLLOW_ruleDataTypeValue_in_entryRuleDataTypeValue945);
            ruleDataTypeValue();
            _fsp--;

             after(grammarAccess.getDataTypeValueRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleDataTypeValue952); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end entryRuleDataTypeValue


    // $ANTLR start ruleDataTypeValue
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:453:1: ruleDataTypeValue : ( ruleLiteral ) ;
    public final void ruleDataTypeValue() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:457:2: ( ( ruleLiteral ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:458:1: ( ruleLiteral )
            {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:458:1: ( ruleLiteral )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:459:1: ruleLiteral
            {
             before(grammarAccess.getDataTypeValueAccess().getLiteralParserRuleCall()); 
            pushFollow(FOLLOW_ruleLiteral_in_ruleDataTypeValue979);
            ruleLiteral();
            _fsp--;

             after(grammarAccess.getDataTypeValueAccess().getLiteralParserRuleCall()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end ruleDataTypeValue


    // $ANTLR start entryRuleAnyURILiteral
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:471:1: entryRuleAnyURILiteral : ruleAnyURILiteral EOF ;
    public final void entryRuleAnyURILiteral() throws RecognitionException {
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:471:24: ( ruleAnyURILiteral EOF )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:472:1: ruleAnyURILiteral EOF
            {
             before(grammarAccess.getAnyURILiteralRule()); 
            pushFollow(FOLLOW_ruleAnyURILiteral_in_entryRuleAnyURILiteral1003);
            ruleAnyURILiteral();
            _fsp--;

             after(grammarAccess.getAnyURILiteralRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAnyURILiteral1010); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end entryRuleAnyURILiteral


    // $ANTLR start ruleAnyURILiteral
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:479:1: ruleAnyURILiteral : ( ruleLiteral ) ;
    public final void ruleAnyURILiteral() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:483:2: ( ( ruleLiteral ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:484:1: ( ruleLiteral )
            {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:484:1: ( ruleLiteral )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:485:1: ruleLiteral
            {
             before(grammarAccess.getAnyURILiteralAccess().getLiteralParserRuleCall()); 
            pushFollow(FOLLOW_ruleLiteral_in_ruleAnyURILiteral1037);
            ruleLiteral();
            _fsp--;

             after(grammarAccess.getAnyURILiteralAccess().getLiteralParserRuleCall()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end ruleAnyURILiteral


    // $ANTLR start entryRuleInherit
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:499:1: entryRuleInherit : ruleInherit EOF ;
    public final void entryRuleInherit() throws RecognitionException {
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:499:18: ( ruleInherit EOF )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:500:1: ruleInherit EOF
            {
             before(grammarAccess.getInheritRule()); 
            pushFollow(FOLLOW_ruleInherit_in_entryRuleInherit1063);
            ruleInherit();
            _fsp--;

             after(grammarAccess.getInheritRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleInherit1070); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end entryRuleInherit


    // $ANTLR start ruleInherit
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:507:1: ruleInherit : ( ( rule__Inherit__Group ) ) ;
    public final void ruleInherit() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:511:2: ( ( ( rule__Inherit__Group ) ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:512:1: ( ( rule__Inherit__Group ) )
            {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:512:1: ( ( rule__Inherit__Group ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:513:1: ( rule__Inherit__Group )
            {
             before(grammarAccess.getInheritAccess().getGroup()); 
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:514:1: ( rule__Inherit__Group )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:514:2: rule__Inherit__Group
            {
            pushFollow(FOLLOW_rule__Inherit__Group_in_ruleInherit1097);
            rule__Inherit__Group();
            _fsp--;


            }

             after(grammarAccess.getInheritAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end ruleInherit


    // $ANTLR start entryRuleIdentifierOrKeyWord
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:525:1: entryRuleIdentifierOrKeyWord : ruleIdentifierOrKeyWord EOF ;
    public final void entryRuleIdentifierOrKeyWord() throws RecognitionException {
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:525:30: ( ruleIdentifierOrKeyWord EOF )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:526:1: ruleIdentifierOrKeyWord EOF
            {
             before(grammarAccess.getIdentifierOrKeyWordRule()); 
            pushFollow(FOLLOW_ruleIdentifierOrKeyWord_in_entryRuleIdentifierOrKeyWord1122);
            ruleIdentifierOrKeyWord();
            _fsp--;

             after(grammarAccess.getIdentifierOrKeyWordRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleIdentifierOrKeyWord1129); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end entryRuleIdentifierOrKeyWord


    // $ANTLR start ruleIdentifierOrKeyWord
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:533:1: ruleIdentifierOrKeyWord : ( ( rule__IdentifierOrKeyWord__Alternatives ) ) ;
    public final void ruleIdentifierOrKeyWord() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:537:2: ( ( ( rule__IdentifierOrKeyWord__Alternatives ) ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:538:1: ( ( rule__IdentifierOrKeyWord__Alternatives ) )
            {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:538:1: ( ( rule__IdentifierOrKeyWord__Alternatives ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:539:1: ( rule__IdentifierOrKeyWord__Alternatives )
            {
             before(grammarAccess.getIdentifierOrKeyWordAccess().getAlternatives()); 
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:540:1: ( rule__IdentifierOrKeyWord__Alternatives )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:540:2: rule__IdentifierOrKeyWord__Alternatives
            {
            pushFollow(FOLLOW_rule__IdentifierOrKeyWord__Alternatives_in_ruleIdentifierOrKeyWord1156);
            rule__IdentifierOrKeyWord__Alternatives();
            _fsp--;


            }

             after(grammarAccess.getIdentifierOrKeyWordAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end ruleIdentifierOrKeyWord


    // $ANTLR start entryRuleIdentifier
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:551:1: entryRuleIdentifier : ruleIdentifier EOF ;
    public final void entryRuleIdentifier() throws RecognitionException {
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:551:21: ( ruleIdentifier EOF )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:552:1: ruleIdentifier EOF
            {
             before(grammarAccess.getIdentifierRule()); 
            pushFollow(FOLLOW_ruleIdentifier_in_entryRuleIdentifier1181);
            ruleIdentifier();
            _fsp--;

             after(grammarAccess.getIdentifierRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleIdentifier1188); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end entryRuleIdentifier


    // $ANTLR start ruleIdentifier
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:559:1: ruleIdentifier : ( ( rule__Identifier__Alternatives ) ) ;
    public final void ruleIdentifier() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:563:2: ( ( ( rule__Identifier__Alternatives ) ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:564:1: ( ( rule__Identifier__Alternatives ) )
            {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:564:1: ( ( rule__Identifier__Alternatives ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:565:1: ( rule__Identifier__Alternatives )
            {
             before(grammarAccess.getIdentifierAccess().getAlternatives()); 
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:566:1: ( rule__Identifier__Alternatives )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:566:2: rule__Identifier__Alternatives
            {
            pushFollow(FOLLOW_rule__Identifier__Alternatives_in_ruleIdentifier1215);
            rule__Identifier__Alternatives();
            _fsp--;


            }

             after(grammarAccess.getIdentifierAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end ruleIdentifier


    // $ANTLR start entryRuleQuotedIdentifier
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:577:1: entryRuleQuotedIdentifier : ruleQuotedIdentifier EOF ;
    public final void entryRuleQuotedIdentifier() throws RecognitionException {
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:577:27: ( ruleQuotedIdentifier EOF )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:578:1: ruleQuotedIdentifier EOF
            {
             before(grammarAccess.getQuotedIdentifierRule()); 
            pushFollow(FOLLOW_ruleQuotedIdentifier_in_entryRuleQuotedIdentifier1240);
            ruleQuotedIdentifier();
            _fsp--;

             after(grammarAccess.getQuotedIdentifierRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleQuotedIdentifier1247); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end entryRuleQuotedIdentifier


    // $ANTLR start ruleQuotedIdentifier
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:585:1: ruleQuotedIdentifier : ( ( rule__QuotedIdentifier__Group ) ) ;
    public final void ruleQuotedIdentifier() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:589:2: ( ( ( rule__QuotedIdentifier__Group ) ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:590:1: ( ( rule__QuotedIdentifier__Group ) )
            {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:590:1: ( ( rule__QuotedIdentifier__Group ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:591:1: ( rule__QuotedIdentifier__Group )
            {
             before(grammarAccess.getQuotedIdentifierAccess().getGroup()); 
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:592:1: ( rule__QuotedIdentifier__Group )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:592:2: rule__QuotedIdentifier__Group
            {
            pushFollow(FOLLOW_rule__QuotedIdentifier__Group_in_ruleQuotedIdentifier1274);
            rule__QuotedIdentifier__Group();
            _fsp--;


            }

             after(grammarAccess.getQuotedIdentifierAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end ruleQuotedIdentifier


    // $ANTLR start entryRuleCName
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:603:1: entryRuleCName : ruleCName EOF ;
    public final void entryRuleCName() throws RecognitionException {
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:603:16: ( ruleCName EOF )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:604:1: ruleCName EOF
            {
             before(grammarAccess.getCNameRule()); 
            pushFollow(FOLLOW_ruleCName_in_entryRuleCName1299);
            ruleCName();
            _fsp--;

             after(grammarAccess.getCNameRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleCName1306); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end entryRuleCName


    // $ANTLR start ruleCName
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:611:1: ruleCName : ( ( rule__CName__Group ) ) ;
    public final void ruleCName() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:615:2: ( ( ( rule__CName__Group ) ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:616:1: ( ( rule__CName__Group ) )
            {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:616:1: ( ( rule__CName__Group ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:617:1: ( rule__CName__Group )
            {
             before(grammarAccess.getCNameAccess().getGroup()); 
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:618:1: ( rule__CName__Group )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:618:2: rule__CName__Group
            {
            pushFollow(FOLLOW_rule__CName__Group_in_ruleCName1333);
            rule__CName__Group();
            _fsp--;


            }

             after(grammarAccess.getCNameAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end ruleCName


    // $ANTLR start entryRuleAnyName
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:629:1: entryRuleAnyName : ruleAnyName EOF ;
    public final void entryRuleAnyName() throws RecognitionException {
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:629:18: ( ruleAnyName EOF )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:630:1: ruleAnyName EOF
            {
             before(grammarAccess.getAnyNameRule()); 
            pushFollow(FOLLOW_ruleAnyName_in_entryRuleAnyName1358);
            ruleAnyName();
            _fsp--;

             after(grammarAccess.getAnyNameRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAnyName1365); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end entryRuleAnyName


    // $ANTLR start ruleAnyName
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:637:1: ruleAnyName : ( '*' ) ;
    public final void ruleAnyName() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:641:2: ( ( '*' ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:642:1: ( '*' )
            {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:642:1: ( '*' )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:643:1: '*'
            {
             before(grammarAccess.getAnyNameAccess().getAsteriskKeyword()); 
            match(input,11,FOLLOW_11_in_ruleAnyName1393); 
             after(grammarAccess.getAnyNameAccess().getAsteriskKeyword()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end ruleAnyName


    // $ANTLR start entryRuleLiteral
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:657:1: entryRuleLiteral : ruleLiteral EOF ;
    public final void entryRuleLiteral() throws RecognitionException {
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:657:18: ( ruleLiteral EOF )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:658:1: ruleLiteral EOF
            {
             before(grammarAccess.getLiteralRule()); 
            pushFollow(FOLLOW_ruleLiteral_in_entryRuleLiteral1419);
            ruleLiteral();
            _fsp--;

             after(grammarAccess.getLiteralRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleLiteral1426); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end entryRuleLiteral


    // $ANTLR start ruleLiteral
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:665:1: ruleLiteral : ( ( rule__Literal__Group ) ) ;
    public final void ruleLiteral() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:669:2: ( ( ( rule__Literal__Group ) ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:670:1: ( ( rule__Literal__Group ) )
            {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:670:1: ( ( rule__Literal__Group ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:671:1: ( rule__Literal__Group )
            {
             before(grammarAccess.getLiteralAccess().getGroup()); 
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:672:1: ( rule__Literal__Group )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:672:2: rule__Literal__Group
            {
            pushFollow(FOLLOW_rule__Literal__Group_in_ruleLiteral1453);
            rule__Literal__Group();
            _fsp--;


            }

             after(grammarAccess.getLiteralAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end ruleLiteral


    // $ANTLR start entryRuleKeyWord
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:683:1: entryRuleKeyWord : ruleKeyWord EOF ;
    public final void entryRuleKeyWord() throws RecognitionException {
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:683:18: ( ruleKeyWord EOF )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:684:1: ruleKeyWord EOF
            {
             before(grammarAccess.getKeyWordRule()); 
            pushFollow(FOLLOW_ruleKeyWord_in_entryRuleKeyWord1478);
            ruleKeyWord();
            _fsp--;

             after(grammarAccess.getKeyWordRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleKeyWord1485); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end entryRuleKeyWord


    // $ANTLR start ruleKeyWord
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:691:1: ruleKeyWord : ( ( rule__KeyWord__Alternatives ) ) ;
    public final void ruleKeyWord() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:695:2: ( ( ( rule__KeyWord__Alternatives ) ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:696:1: ( ( rule__KeyWord__Alternatives ) )
            {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:696:1: ( ( rule__KeyWord__Alternatives ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:697:1: ( rule__KeyWord__Alternatives )
            {
             before(grammarAccess.getKeyWordAccess().getAlternatives()); 
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:698:1: ( rule__KeyWord__Alternatives )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:698:2: rule__KeyWord__Alternatives
            {
            pushFollow(FOLLOW_rule__KeyWord__Alternatives_in_ruleKeyWord1512);
            rule__KeyWord__Alternatives();
            _fsp--;


            }

             after(grammarAccess.getKeyWordAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end ruleKeyWord


    // $ANTLR start rule__TopLevel__Alternatives_1
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:709:1: rule__TopLevel__Alternatives_1 : ( ( ( ( rulePattern ) ) ) | ( ( ( ruleGrammarContent ) )* ) );
    public final void rule__TopLevel__Alternatives_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:713:1: ( ( ( ( rulePattern ) ) ) | ( ( ( ruleGrammarContent ) )* ) )
            int alt2=2;
            switch ( input.LA(1) ) {
            case RULE_STRING:
            case 12:
            case 13:
            case 14:
            case 15:
            case 23:
            case 24:
            case 25:
            case 29:
            case 30:
            case 31:
            case 34:
            case 35:
            case 37:
            case 41:
                {
                alt2=1;
                }
                break;
            case RULE_ID:
                {
                switch ( input.LA(2) ) {
                case EOF:
                case 45:
                    {
                    alt2=1;
                    }
                    break;
                case 20:
                    {
                    int LA2_4 = input.LA(3);

                    if ( ((LA2_4>=RULE_ID && LA2_4<=RULE_STRING)||(LA2_4>=12 && LA2_4<=15)||(LA2_4>=23 && LA2_4<=25)||(LA2_4>=29 && LA2_4<=31)||(LA2_4>=34 && LA2_4<=35)||LA2_4==37||LA2_4==41) ) {
                        alt2=2;
                    }
                    else if ( (LA2_4==EOF) ) {
                        alt2=1;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("709:1: rule__TopLevel__Alternatives_1 : ( ( ( ( rulePattern ) ) ) | ( ( ( ruleGrammarContent ) )* ) );", 2, 4, input);

                        throw nvae;
                    }
                    }
                    break;
                case 21:
                case 22:
                    {
                    alt2=2;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("709:1: rule__TopLevel__Alternatives_1 : ( ( ( ( rulePattern ) ) ) | ( ( ( ruleGrammarContent ) )* ) );", 2, 2, input);

                    throw nvae;
                }

                }
                break;
            case EOF:
            case 28:
            case 32:
            case 38:
                {
                alt2=2;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("709:1: rule__TopLevel__Alternatives_1 : ( ( ( ( rulePattern ) ) ) | ( ( ( ruleGrammarContent ) )* ) );", 2, 0, input);

                throw nvae;
            }

            switch (alt2) {
                case 1 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:714:1: ( ( ( rulePattern ) ) )
                    {
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:714:1: ( ( ( rulePattern ) ) )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:715:1: ( ( rulePattern ) )
                    {
                     before(grammarAccess.getTopLevelAccess().getPatternAssignment_1_0()); 
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:716:1: ( ( rulePattern ) )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:717:1: ( rulePattern )
                    {
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:717:1: ( rulePattern )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:718:1: rulePattern
                    {
                     before(grammarAccess.getTopLevelAccess().getPatternPatternParserRuleCall_1_0_0()); 
                    pushFollow(FOLLOW_rulePattern_in_rule__TopLevel__Alternatives_11554);
                    rulePattern();
                    _fsp--;

                     after(grammarAccess.getTopLevelAccess().getPatternPatternParserRuleCall_1_0_0()); 

                    }


                    }

                     after(grammarAccess.getTopLevelAccess().getPatternAssignment_1_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:725:6: ( ( ( ruleGrammarContent ) )* )
                    {
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:725:6: ( ( ( ruleGrammarContent ) )* )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:726:1: ( ( ruleGrammarContent ) )*
                    {
                     before(grammarAccess.getTopLevelAccess().getGrammarContentAssignment_1_1()); 
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:727:1: ( ( ruleGrammarContent ) )*
                    loop1:
                    do {
                        int alt1=2;
                        int LA1_0 = input.LA(1);

                        if ( (LA1_0==RULE_ID||LA1_0==28||LA1_0==32||LA1_0==38) ) {
                            alt1=1;
                        }


                        switch (alt1) {
                    	case 1 :
                    	    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:728:1: ( ruleGrammarContent )
                    	    {
                    	    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:728:1: ( ruleGrammarContent )
                    	    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:729:1: ruleGrammarContent
                    	    {
                    	     before(grammarAccess.getTopLevelAccess().getGrammarContentGrammarContentParserRuleCall_1_1_0()); 
                    	    pushFollow(FOLLOW_ruleGrammarContent_in_rule__TopLevel__Alternatives_11584);
                    	    ruleGrammarContent();
                    	    _fsp--;

                    	     after(grammarAccess.getTopLevelAccess().getGrammarContentGrammarContentParserRuleCall_1_1_0()); 

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop1;
                        }
                    } while (true);

                     after(grammarAccess.getTopLevelAccess().getGrammarContentAssignment_1_1()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__TopLevel__Alternatives_1


    // $ANTLR start rule__Decl__Alternatives
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:741:1: rule__Decl__Alternatives : ( ( ( rule__Decl__Group_0 ) ) | ( ( rule__Decl__Group_1 ) ) | ( ( rule__Decl__Group_2 ) ) );
    public final void rule__Decl__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:745:1: ( ( ( rule__Decl__Group_0 ) ) | ( ( rule__Decl__Group_1 ) ) | ( ( rule__Decl__Group_2 ) ) )
            int alt3=3;
            switch ( input.LA(1) ) {
            case 36:
                {
                alt3=1;
                }
                break;
            case 26:
                {
                alt3=2;
                }
                break;
            case 27:
                {
                alt3=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("741:1: rule__Decl__Alternatives : ( ( ( rule__Decl__Group_0 ) ) | ( ( rule__Decl__Group_1 ) ) | ( ( rule__Decl__Group_2 ) ) );", 3, 0, input);

                throw nvae;
            }

            switch (alt3) {
                case 1 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:746:1: ( ( rule__Decl__Group_0 ) )
                    {
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:746:1: ( ( rule__Decl__Group_0 ) )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:747:1: ( rule__Decl__Group_0 )
                    {
                     before(grammarAccess.getDeclAccess().getGroup_0()); 
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:748:1: ( rule__Decl__Group_0 )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:748:2: rule__Decl__Group_0
                    {
                    pushFollow(FOLLOW_rule__Decl__Group_0_in_rule__Decl__Alternatives1623);
                    rule__Decl__Group_0();
                    _fsp--;


                    }

                     after(grammarAccess.getDeclAccess().getGroup_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:751:6: ( ( rule__Decl__Group_1 ) )
                    {
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:751:6: ( ( rule__Decl__Group_1 ) )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:752:1: ( rule__Decl__Group_1 )
                    {
                     before(grammarAccess.getDeclAccess().getGroup_1()); 
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:753:1: ( rule__Decl__Group_1 )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:753:2: rule__Decl__Group_1
                    {
                    pushFollow(FOLLOW_rule__Decl__Group_1_in_rule__Decl__Alternatives1640);
                    rule__Decl__Group_1();
                    _fsp--;


                    }

                     after(grammarAccess.getDeclAccess().getGroup_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:756:6: ( ( rule__Decl__Group_2 ) )
                    {
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:756:6: ( ( rule__Decl__Group_2 ) )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:757:1: ( rule__Decl__Group_2 )
                    {
                     before(grammarAccess.getDeclAccess().getGroup_2()); 
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:758:1: ( rule__Decl__Group_2 )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:758:2: rule__Decl__Group_2
                    {
                    pushFollow(FOLLOW_rule__Decl__Group_2_in_rule__Decl__Alternatives1657);
                    rule__Decl__Group_2();
                    _fsp--;


                    }

                     after(grammarAccess.getDeclAccess().getGroup_2()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Decl__Alternatives


    // $ANTLR start rule__Pattern__Alternatives
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:766:1: rule__Pattern__Alternatives : ( ( ( ( ( rule__Pattern__ValAlternatives_0_0 ) ) )+ ) | ( ( rule__Pattern__Group_1 ) ) | ( ( rule__Pattern__Group_2 ) ) | ( ( rule__Pattern__Group_3 ) ) | ( ( rule__Pattern__Group_4 ) ) | ( 'empty' ) | ( 'text' ) | ( '|' ) | ( ( ( ruleDataTypeValue ) )+ ) | ( ( rule__Pattern__Group_9 ) ) | ( 'notAllowed' ) | ( ( rule__Pattern__Group_11 ) ) | ( ( rule__Pattern__Group_12 ) ) | ( ( rule__Pattern__Group_13 ) ) );
    public final void rule__Pattern__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:770:1: ( ( ( ( ( rule__Pattern__ValAlternatives_0_0 ) ) )+ ) | ( ( rule__Pattern__Group_1 ) ) | ( ( rule__Pattern__Group_2 ) ) | ( ( rule__Pattern__Group_3 ) ) | ( ( rule__Pattern__Group_4 ) ) | ( 'empty' ) | ( 'text' ) | ( '|' ) | ( ( ( ruleDataTypeValue ) )+ ) | ( ( rule__Pattern__Group_9 ) ) | ( 'notAllowed' ) | ( ( rule__Pattern__Group_11 ) ) | ( ( rule__Pattern__Group_12 ) ) | ( ( rule__Pattern__Group_13 ) ) )
            int alt6=14;
            switch ( input.LA(1) ) {
            case 25:
            case 29:
                {
                alt6=1;
                }
                break;
            case 34:
                {
                alt6=2;
                }
                break;
            case 35:
                {
                alt6=3;
                }
                break;
            case RULE_ID:
                {
                int LA6_4 = input.LA(2);

                if ( (LA6_4==EOF||(LA6_4>=RULE_ID && LA6_4<=RULE_STRING)||(LA6_4>=12 && LA6_4<=15)||LA6_4==20||(LA6_4>=23 && LA6_4<=25)||(LA6_4>=28 && LA6_4<=32)||(LA6_4>=34 && LA6_4<=35)||(LA6_4>=37 && LA6_4<=38)||(LA6_4>=40 && LA6_4<=43)) ) {
                    alt6=4;
                }
                else if ( (LA6_4==45) ) {
                    alt6=10;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("766:1: rule__Pattern__Alternatives : ( ( ( ( ( rule__Pattern__ValAlternatives_0_0 ) ) )+ ) | ( ( rule__Pattern__Group_1 ) ) | ( ( rule__Pattern__Group_2 ) ) | ( ( rule__Pattern__Group_3 ) ) | ( ( rule__Pattern__Group_4 ) ) | ( 'empty' ) | ( 'text' ) | ( '|' ) | ( ( ( ruleDataTypeValue ) )+ ) | ( ( rule__Pattern__Group_9 ) ) | ( 'notAllowed' ) | ( ( rule__Pattern__Group_11 ) ) | ( ( rule__Pattern__Group_12 ) ) | ( ( rule__Pattern__Group_13 ) ) );", 6, 4, input);

                    throw nvae;
                }
                }
                break;
            case 37:
                {
                alt6=5;
                }
                break;
            case 12:
                {
                alt6=6;
                }
                break;
            case 13:
                {
                alt6=7;
                }
                break;
            case 14:
                {
                alt6=8;
                }
                break;
            case RULE_STRING:
                {
                alt6=9;
                }
                break;
            case 23:
            case 24:
                {
                alt6=10;
                }
                break;
            case 15:
                {
                alt6=11;
                }
                break;
            case 30:
                {
                alt6=12;
                }
                break;
            case 31:
                {
                alt6=13;
                }
                break;
            case 41:
                {
                alt6=14;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("766:1: rule__Pattern__Alternatives : ( ( ( ( ( rule__Pattern__ValAlternatives_0_0 ) ) )+ ) | ( ( rule__Pattern__Group_1 ) ) | ( ( rule__Pattern__Group_2 ) ) | ( ( rule__Pattern__Group_3 ) ) | ( ( rule__Pattern__Group_4 ) ) | ( 'empty' ) | ( 'text' ) | ( '|' ) | ( ( ( ruleDataTypeValue ) )+ ) | ( ( rule__Pattern__Group_9 ) ) | ( 'notAllowed' ) | ( ( rule__Pattern__Group_11 ) ) | ( ( rule__Pattern__Group_12 ) ) | ( ( rule__Pattern__Group_13 ) ) );", 6, 0, input);

                throw nvae;
            }

            switch (alt6) {
                case 1 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:771:1: ( ( ( ( rule__Pattern__ValAlternatives_0_0 ) ) )+ )
                    {
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:771:1: ( ( ( ( rule__Pattern__ValAlternatives_0_0 ) ) )+ )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:772:1: ( ( ( rule__Pattern__ValAlternatives_0_0 ) ) )+
                    {
                     before(grammarAccess.getPatternAccess().getValAssignment_0()); 
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:773:1: ( ( ( rule__Pattern__ValAlternatives_0_0 ) ) )+
                    int cnt4=0;
                    loop4:
                    do {
                        int alt4=2;
                        int LA4_0 = input.LA(1);

                        if ( (LA4_0==29) ) {
                            alt4=1;
                        }
                        else if ( (LA4_0==25) ) {
                            alt4=1;
                        }


                        switch (alt4) {
                    	case 1 :
                    	    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:774:1: ( ( rule__Pattern__ValAlternatives_0_0 ) )
                    	    {
                    	    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:774:1: ( ( rule__Pattern__ValAlternatives_0_0 ) )
                    	    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:775:1: ( rule__Pattern__ValAlternatives_0_0 )
                    	    {
                    	     before(grammarAccess.getPatternAccess().getValAlternatives_0_0()); 
                    	    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:776:1: ( rule__Pattern__ValAlternatives_0_0 )
                    	    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:776:2: rule__Pattern__ValAlternatives_0_0
                    	    {
                    	    pushFollow(FOLLOW_rule__Pattern__ValAlternatives_0_0_in_rule__Pattern__Alternatives1696);
                    	    rule__Pattern__ValAlternatives_0_0();
                    	    _fsp--;


                    	    }

                    	     after(grammarAccess.getPatternAccess().getValAlternatives_0_0()); 

                    	    }


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

                     after(grammarAccess.getPatternAccess().getValAssignment_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:783:6: ( ( rule__Pattern__Group_1 ) )
                    {
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:783:6: ( ( rule__Pattern__Group_1 ) )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:784:1: ( rule__Pattern__Group_1 )
                    {
                     before(grammarAccess.getPatternAccess().getGroup_1()); 
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:785:1: ( rule__Pattern__Group_1 )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:785:2: rule__Pattern__Group_1
                    {
                    pushFollow(FOLLOW_rule__Pattern__Group_1_in_rule__Pattern__Alternatives1722);
                    rule__Pattern__Group_1();
                    _fsp--;


                    }

                     after(grammarAccess.getPatternAccess().getGroup_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:788:6: ( ( rule__Pattern__Group_2 ) )
                    {
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:788:6: ( ( rule__Pattern__Group_2 ) )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:789:1: ( rule__Pattern__Group_2 )
                    {
                     before(grammarAccess.getPatternAccess().getGroup_2()); 
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:790:1: ( rule__Pattern__Group_2 )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:790:2: rule__Pattern__Group_2
                    {
                    pushFollow(FOLLOW_rule__Pattern__Group_2_in_rule__Pattern__Alternatives1739);
                    rule__Pattern__Group_2();
                    _fsp--;


                    }

                     after(grammarAccess.getPatternAccess().getGroup_2()); 

                    }


                    }
                    break;
                case 4 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:793:6: ( ( rule__Pattern__Group_3 ) )
                    {
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:793:6: ( ( rule__Pattern__Group_3 ) )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:794:1: ( rule__Pattern__Group_3 )
                    {
                     before(grammarAccess.getPatternAccess().getGroup_3()); 
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:795:1: ( rule__Pattern__Group_3 )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:795:2: rule__Pattern__Group_3
                    {
                    pushFollow(FOLLOW_rule__Pattern__Group_3_in_rule__Pattern__Alternatives1756);
                    rule__Pattern__Group_3();
                    _fsp--;


                    }

                     after(grammarAccess.getPatternAccess().getGroup_3()); 

                    }


                    }
                    break;
                case 5 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:798:6: ( ( rule__Pattern__Group_4 ) )
                    {
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:798:6: ( ( rule__Pattern__Group_4 ) )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:799:1: ( rule__Pattern__Group_4 )
                    {
                     before(grammarAccess.getPatternAccess().getGroup_4()); 
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:800:1: ( rule__Pattern__Group_4 )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:800:2: rule__Pattern__Group_4
                    {
                    pushFollow(FOLLOW_rule__Pattern__Group_4_in_rule__Pattern__Alternatives1773);
                    rule__Pattern__Group_4();
                    _fsp--;


                    }

                     after(grammarAccess.getPatternAccess().getGroup_4()); 

                    }


                    }
                    break;
                case 6 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:803:6: ( 'empty' )
                    {
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:803:6: ( 'empty' )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:804:1: 'empty'
                    {
                     before(grammarAccess.getPatternAccess().getEmptyKeyword_5()); 
                    match(input,12,FOLLOW_12_in_rule__Pattern__Alternatives1791); 
                     after(grammarAccess.getPatternAccess().getEmptyKeyword_5()); 

                    }


                    }
                    break;
                case 7 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:810:6: ( 'text' )
                    {
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:810:6: ( 'text' )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:811:1: 'text'
                    {
                     before(grammarAccess.getPatternAccess().getTextKeyword_6()); 
                    match(input,13,FOLLOW_13_in_rule__Pattern__Alternatives1810); 
                     after(grammarAccess.getPatternAccess().getTextKeyword_6()); 

                    }


                    }
                    break;
                case 8 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:817:6: ( '|' )
                    {
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:817:6: ( '|' )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:818:1: '|'
                    {
                     before(grammarAccess.getPatternAccess().getVerticalLineKeyword_7()); 
                    match(input,14,FOLLOW_14_in_rule__Pattern__Alternatives1829); 
                     after(grammarAccess.getPatternAccess().getVerticalLineKeyword_7()); 

                    }


                    }
                    break;
                case 9 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:824:6: ( ( ( ruleDataTypeValue ) )+ )
                    {
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:824:6: ( ( ( ruleDataTypeValue ) )+ )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:825:1: ( ( ruleDataTypeValue ) )+
                    {
                     before(grammarAccess.getPatternAccess().getValueAssignment_8()); 
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:826:1: ( ( ruleDataTypeValue ) )+
                    int cnt5=0;
                    loop5:
                    do {
                        int alt5=2;
                        int LA5_0 = input.LA(1);

                        if ( (LA5_0==RULE_STRING) ) {
                            alt5=1;
                        }


                        switch (alt5) {
                    	case 1 :
                    	    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:827:1: ( ruleDataTypeValue )
                    	    {
                    	    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:827:1: ( ruleDataTypeValue )
                    	    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:828:1: ruleDataTypeValue
                    	    {
                    	     before(grammarAccess.getPatternAccess().getValueDataTypeValueParserRuleCall_8_0()); 
                    	    pushFollow(FOLLOW_ruleDataTypeValue_in_rule__Pattern__Alternatives1854);
                    	    ruleDataTypeValue();
                    	    _fsp--;

                    	     after(grammarAccess.getPatternAccess().getValueDataTypeValueParserRuleCall_8_0()); 

                    	    }


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

                     after(grammarAccess.getPatternAccess().getValueAssignment_8()); 

                    }


                    }
                    break;
                case 10 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:835:6: ( ( rule__Pattern__Group_9 ) )
                    {
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:835:6: ( ( rule__Pattern__Group_9 ) )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:836:1: ( rule__Pattern__Group_9 )
                    {
                     before(grammarAccess.getPatternAccess().getGroup_9()); 
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:837:1: ( rule__Pattern__Group_9 )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:837:2: rule__Pattern__Group_9
                    {
                    pushFollow(FOLLOW_rule__Pattern__Group_9_in_rule__Pattern__Alternatives1878);
                    rule__Pattern__Group_9();
                    _fsp--;


                    }

                     after(grammarAccess.getPatternAccess().getGroup_9()); 

                    }


                    }
                    break;
                case 11 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:840:6: ( 'notAllowed' )
                    {
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:840:6: ( 'notAllowed' )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:841:1: 'notAllowed'
                    {
                     before(grammarAccess.getPatternAccess().getNotAllowedKeyword_10()); 
                    match(input,15,FOLLOW_15_in_rule__Pattern__Alternatives1896); 
                     after(grammarAccess.getPatternAccess().getNotAllowedKeyword_10()); 

                    }


                    }
                    break;
                case 12 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:847:6: ( ( rule__Pattern__Group_11 ) )
                    {
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:847:6: ( ( rule__Pattern__Group_11 ) )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:848:1: ( rule__Pattern__Group_11 )
                    {
                     before(grammarAccess.getPatternAccess().getGroup_11()); 
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:849:1: ( rule__Pattern__Group_11 )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:849:2: rule__Pattern__Group_11
                    {
                    pushFollow(FOLLOW_rule__Pattern__Group_11_in_rule__Pattern__Alternatives1914);
                    rule__Pattern__Group_11();
                    _fsp--;


                    }

                     after(grammarAccess.getPatternAccess().getGroup_11()); 

                    }


                    }
                    break;
                case 13 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:852:6: ( ( rule__Pattern__Group_12 ) )
                    {
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:852:6: ( ( rule__Pattern__Group_12 ) )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:853:1: ( rule__Pattern__Group_12 )
                    {
                     before(grammarAccess.getPatternAccess().getGroup_12()); 
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:854:1: ( rule__Pattern__Group_12 )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:854:2: rule__Pattern__Group_12
                    {
                    pushFollow(FOLLOW_rule__Pattern__Group_12_in_rule__Pattern__Alternatives1931);
                    rule__Pattern__Group_12();
                    _fsp--;


                    }

                     after(grammarAccess.getPatternAccess().getGroup_12()); 

                    }


                    }
                    break;
                case 14 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:857:6: ( ( rule__Pattern__Group_13 ) )
                    {
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:857:6: ( ( rule__Pattern__Group_13 ) )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:858:1: ( rule__Pattern__Group_13 )
                    {
                     before(grammarAccess.getPatternAccess().getGroup_13()); 
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:859:1: ( rule__Pattern__Group_13 )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:859:2: rule__Pattern__Group_13
                    {
                    pushFollow(FOLLOW_rule__Pattern__Group_13_in_rule__Pattern__Alternatives1948);
                    rule__Pattern__Group_13();
                    _fsp--;


                    }

                     after(grammarAccess.getPatternAccess().getGroup_13()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Pattern__Alternatives


    // $ANTLR start rule__Pattern__ValAlternatives_0_0
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:867:1: rule__Pattern__ValAlternatives_0_0 : ( ( ruleElement ) | ( ruleAttribute ) );
    public final void rule__Pattern__ValAlternatives_0_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:871:1: ( ( ruleElement ) | ( ruleAttribute ) )
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==29) ) {
                alt7=1;
            }
            else if ( (LA7_0==25) ) {
                alt7=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("867:1: rule__Pattern__ValAlternatives_0_0 : ( ( ruleElement ) | ( ruleAttribute ) );", 7, 0, input);

                throw nvae;
            }
            switch (alt7) {
                case 1 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:872:1: ( ruleElement )
                    {
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:872:1: ( ruleElement )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:873:1: ruleElement
                    {
                     before(grammarAccess.getPatternAccess().getValElementParserRuleCall_0_0_0()); 
                    pushFollow(FOLLOW_ruleElement_in_rule__Pattern__ValAlternatives_0_01980);
                    ruleElement();
                    _fsp--;

                     after(grammarAccess.getPatternAccess().getValElementParserRuleCall_0_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:877:6: ( ruleAttribute )
                    {
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:877:6: ( ruleAttribute )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:878:1: ruleAttribute
                    {
                     before(grammarAccess.getPatternAccess().getValAttributeParserRuleCall_0_0_1()); 
                    pushFollow(FOLLOW_ruleAttribute_in_rule__Pattern__ValAlternatives_0_01996);
                    ruleAttribute();
                    _fsp--;

                     after(grammarAccess.getPatternAccess().getValAttributeParserRuleCall_0_0_1()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Pattern__ValAlternatives_0_0


    // $ANTLR start rule__Element__CardinalityAlternatives_5_0
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:887:1: rule__Element__CardinalityAlternatives_5_0 : ( ( '?' ) | ( '+' ) | ( '*' ) );
    public final void rule__Element__CardinalityAlternatives_5_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:891:1: ( ( '?' ) | ( '+' ) | ( '*' ) )
            int alt8=3;
            switch ( input.LA(1) ) {
            case 16:
                {
                alt8=1;
                }
                break;
            case 17:
                {
                alt8=2;
                }
                break;
            case 11:
                {
                alt8=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("887:1: rule__Element__CardinalityAlternatives_5_0 : ( ( '?' ) | ( '+' ) | ( '*' ) );", 8, 0, input);

                throw nvae;
            }

            switch (alt8) {
                case 1 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:892:1: ( '?' )
                    {
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:892:1: ( '?' )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:893:1: '?'
                    {
                     before(grammarAccess.getElementAccess().getCardinalityQuestionMarkKeyword_5_0_0()); 
                    match(input,16,FOLLOW_16_in_rule__Element__CardinalityAlternatives_5_02028); 
                     after(grammarAccess.getElementAccess().getCardinalityQuestionMarkKeyword_5_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:899:6: ( '+' )
                    {
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:899:6: ( '+' )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:900:1: '+'
                    {
                     before(grammarAccess.getElementAccess().getCardinalityPlusSignKeyword_5_0_1()); 
                    match(input,17,FOLLOW_17_in_rule__Element__CardinalityAlternatives_5_02047); 
                     after(grammarAccess.getElementAccess().getCardinalityPlusSignKeyword_5_0_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:906:6: ( '*' )
                    {
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:906:6: ( '*' )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:907:1: '*'
                    {
                     before(grammarAccess.getElementAccess().getCardinalityAsteriskKeyword_5_0_2()); 
                    match(input,11,FOLLOW_11_in_rule__Element__CardinalityAlternatives_5_02066); 
                     after(grammarAccess.getElementAccess().getCardinalityAsteriskKeyword_5_0_2()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Element__CardinalityAlternatives_5_0


    // $ANTLR start rule__Element__ContinueAlternatives_6_0
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:918:1: rule__Element__ContinueAlternatives_6_0 : ( ( ',' ) | ( '&' ) | ( '|' ) );
    public final void rule__Element__ContinueAlternatives_6_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:922:1: ( ( ',' ) | ( '&' ) | ( '|' ) )
            int alt9=3;
            switch ( input.LA(1) ) {
            case 18:
                {
                alt9=1;
                }
                break;
            case 19:
                {
                alt9=2;
                }
                break;
            case 14:
                {
                alt9=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("918:1: rule__Element__ContinueAlternatives_6_0 : ( ( ',' ) | ( '&' ) | ( '|' ) );", 9, 0, input);

                throw nvae;
            }

            switch (alt9) {
                case 1 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:923:1: ( ',' )
                    {
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:923:1: ( ',' )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:924:1: ','
                    {
                     before(grammarAccess.getElementAccess().getContinueCommaKeyword_6_0_0()); 
                    match(input,18,FOLLOW_18_in_rule__Element__ContinueAlternatives_6_02100); 
                     after(grammarAccess.getElementAccess().getContinueCommaKeyword_6_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:930:6: ( '&' )
                    {
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:930:6: ( '&' )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:931:1: '&'
                    {
                     before(grammarAccess.getElementAccess().getContinueAmpersandKeyword_6_0_1()); 
                    match(input,19,FOLLOW_19_in_rule__Element__ContinueAlternatives_6_02119); 
                     after(grammarAccess.getElementAccess().getContinueAmpersandKeyword_6_0_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:937:6: ( '|' )
                    {
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:937:6: ( '|' )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:938:1: '|'
                    {
                     before(grammarAccess.getElementAccess().getContinueVerticalLineKeyword_6_0_2()); 
                    match(input,14,FOLLOW_14_in_rule__Element__ContinueAlternatives_6_02138); 
                     after(grammarAccess.getElementAccess().getContinueVerticalLineKeyword_6_0_2()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Element__ContinueAlternatives_6_0


    // $ANTLR start rule__Attribute__ContinueAlternatives_6_0
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:949:1: rule__Attribute__ContinueAlternatives_6_0 : ( ( ',' ) | ( '|' ) );
    public final void rule__Attribute__ContinueAlternatives_6_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:953:1: ( ( ',' ) | ( '|' ) )
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==18) ) {
                alt10=1;
            }
            else if ( (LA10_0==14) ) {
                alt10=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("949:1: rule__Attribute__ContinueAlternatives_6_0 : ( ( ',' ) | ( '|' ) );", 10, 0, input);

                throw nvae;
            }
            switch (alt10) {
                case 1 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:954:1: ( ',' )
                    {
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:954:1: ( ',' )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:955:1: ','
                    {
                     before(grammarAccess.getAttributeAccess().getContinueCommaKeyword_6_0_0()); 
                    match(input,18,FOLLOW_18_in_rule__Attribute__ContinueAlternatives_6_02172); 
                     after(grammarAccess.getAttributeAccess().getContinueCommaKeyword_6_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:961:6: ( '|' )
                    {
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:961:6: ( '|' )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:962:1: '|'
                    {
                     before(grammarAccess.getAttributeAccess().getContinueVerticalLineKeyword_6_0_1()); 
                    match(input,14,FOLLOW_14_in_rule__Attribute__ContinueAlternatives_6_02191); 
                     after(grammarAccess.getAttributeAccess().getContinueVerticalLineKeyword_6_0_1()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Attribute__ContinueAlternatives_6_0


    // $ANTLR start rule__GrammarContent__Alternatives
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:973:1: rule__GrammarContent__Alternatives : ( ( ruleStart ) | ( ruleDefine ) | ( ( rule__GrammarContent__Group_2 ) ) | ( ( rule__GrammarContent__Group_3 ) ) );
    public final void rule__GrammarContent__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:977:1: ( ( ruleStart ) | ( ruleDefine ) | ( ( rule__GrammarContent__Group_2 ) ) | ( ( rule__GrammarContent__Group_3 ) ) )
            int alt11=4;
            switch ( input.LA(1) ) {
            case 38:
                {
                alt11=1;
                }
                break;
            case RULE_ID:
                {
                alt11=2;
                }
                break;
            case 28:
                {
                alt11=3;
                }
                break;
            case 32:
                {
                alt11=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("973:1: rule__GrammarContent__Alternatives : ( ( ruleStart ) | ( ruleDefine ) | ( ( rule__GrammarContent__Group_2 ) ) | ( ( rule__GrammarContent__Group_3 ) ) );", 11, 0, input);

                throw nvae;
            }

            switch (alt11) {
                case 1 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:978:1: ( ruleStart )
                    {
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:978:1: ( ruleStart )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:979:1: ruleStart
                    {
                     before(grammarAccess.getGrammarContentAccess().getStartParserRuleCall_0()); 
                    pushFollow(FOLLOW_ruleStart_in_rule__GrammarContent__Alternatives2224);
                    ruleStart();
                    _fsp--;

                     after(grammarAccess.getGrammarContentAccess().getStartParserRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:983:6: ( ruleDefine )
                    {
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:983:6: ( ruleDefine )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:984:1: ruleDefine
                    {
                     before(grammarAccess.getGrammarContentAccess().getDefineParserRuleCall_1()); 
                    pushFollow(FOLLOW_ruleDefine_in_rule__GrammarContent__Alternatives2240);
                    ruleDefine();
                    _fsp--;

                     after(grammarAccess.getGrammarContentAccess().getDefineParserRuleCall_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:988:6: ( ( rule__GrammarContent__Group_2 ) )
                    {
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:988:6: ( ( rule__GrammarContent__Group_2 ) )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:989:1: ( rule__GrammarContent__Group_2 )
                    {
                     before(grammarAccess.getGrammarContentAccess().getGroup_2()); 
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:990:1: ( rule__GrammarContent__Group_2 )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:990:2: rule__GrammarContent__Group_2
                    {
                    pushFollow(FOLLOW_rule__GrammarContent__Group_2_in_rule__GrammarContent__Alternatives2256);
                    rule__GrammarContent__Group_2();
                    _fsp--;


                    }

                     after(grammarAccess.getGrammarContentAccess().getGroup_2()); 

                    }


                    }
                    break;
                case 4 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:993:6: ( ( rule__GrammarContent__Group_3 ) )
                    {
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:993:6: ( ( rule__GrammarContent__Group_3 ) )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:994:1: ( rule__GrammarContent__Group_3 )
                    {
                     before(grammarAccess.getGrammarContentAccess().getGroup_3()); 
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:995:1: ( rule__GrammarContent__Group_3 )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:995:2: rule__GrammarContent__Group_3
                    {
                    pushFollow(FOLLOW_rule__GrammarContent__Group_3_in_rule__GrammarContent__Alternatives2273);
                    rule__GrammarContent__Group_3();
                    _fsp--;


                    }

                     after(grammarAccess.getGrammarContentAccess().getGroup_3()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__GrammarContent__Alternatives


    // $ANTLR start rule__IncludeContent__Alternatives
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1003:1: rule__IncludeContent__Alternatives : ( ( ruleDefine ) | ( ruleStart ) | ( ( rule__IncludeContent__Group_2 ) ) );
    public final void rule__IncludeContent__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1007:1: ( ( ruleDefine ) | ( ruleStart ) | ( ( rule__IncludeContent__Group_2 ) ) )
            int alt12=3;
            switch ( input.LA(1) ) {
            case RULE_ID:
                {
                alt12=1;
                }
                break;
            case 38:
                {
                alt12=2;
                }
                break;
            case 28:
                {
                alt12=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("1003:1: rule__IncludeContent__Alternatives : ( ( ruleDefine ) | ( ruleStart ) | ( ( rule__IncludeContent__Group_2 ) ) );", 12, 0, input);

                throw nvae;
            }

            switch (alt12) {
                case 1 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1008:1: ( ruleDefine )
                    {
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1008:1: ( ruleDefine )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1009:1: ruleDefine
                    {
                     before(grammarAccess.getIncludeContentAccess().getDefineParserRuleCall_0()); 
                    pushFollow(FOLLOW_ruleDefine_in_rule__IncludeContent__Alternatives2305);
                    ruleDefine();
                    _fsp--;

                     after(grammarAccess.getIncludeContentAccess().getDefineParserRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1013:6: ( ruleStart )
                    {
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1013:6: ( ruleStart )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1014:1: ruleStart
                    {
                     before(grammarAccess.getIncludeContentAccess().getStartParserRuleCall_1()); 
                    pushFollow(FOLLOW_ruleStart_in_rule__IncludeContent__Alternatives2321);
                    ruleStart();
                    _fsp--;

                     after(grammarAccess.getIncludeContentAccess().getStartParserRuleCall_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1018:6: ( ( rule__IncludeContent__Group_2 ) )
                    {
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1018:6: ( ( rule__IncludeContent__Group_2 ) )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1019:1: ( rule__IncludeContent__Group_2 )
                    {
                     before(grammarAccess.getIncludeContentAccess().getGroup_2()); 
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1020:1: ( rule__IncludeContent__Group_2 )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1020:2: rule__IncludeContent__Group_2
                    {
                    pushFollow(FOLLOW_rule__IncludeContent__Group_2_in_rule__IncludeContent__Alternatives2337);
                    rule__IncludeContent__Group_2();
                    _fsp--;


                    }

                     after(grammarAccess.getIncludeContentAccess().getGroup_2()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__IncludeContent__Alternatives


    // $ANTLR start rule__Start__Alternatives_1
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1028:1: rule__Start__Alternatives_1 : ( ( '=' ) | ( '|=' ) | ( '&=' ) );
    public final void rule__Start__Alternatives_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1032:1: ( ( '=' ) | ( '|=' ) | ( '&=' ) )
            int alt13=3;
            switch ( input.LA(1) ) {
            case 20:
                {
                alt13=1;
                }
                break;
            case 21:
                {
                alt13=2;
                }
                break;
            case 22:
                {
                alt13=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("1028:1: rule__Start__Alternatives_1 : ( ( '=' ) | ( '|=' ) | ( '&=' ) );", 13, 0, input);

                throw nvae;
            }

            switch (alt13) {
                case 1 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1033:1: ( '=' )
                    {
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1033:1: ( '=' )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1034:1: '='
                    {
                     before(grammarAccess.getStartAccess().getEqualsSignKeyword_1_0()); 
                    match(input,20,FOLLOW_20_in_rule__Start__Alternatives_12370); 
                     after(grammarAccess.getStartAccess().getEqualsSignKeyword_1_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1040:6: ( '|=' )
                    {
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1040:6: ( '|=' )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1041:1: '|='
                    {
                     before(grammarAccess.getStartAccess().getVerticalLineEqualsSignKeyword_1_1()); 
                    match(input,21,FOLLOW_21_in_rule__Start__Alternatives_12389); 
                     after(grammarAccess.getStartAccess().getVerticalLineEqualsSignKeyword_1_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1047:6: ( '&=' )
                    {
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1047:6: ( '&=' )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1048:1: '&='
                    {
                     before(grammarAccess.getStartAccess().getAmpersandEqualsSignKeyword_1_2()); 
                    match(input,22,FOLLOW_22_in_rule__Start__Alternatives_12408); 
                     after(grammarAccess.getStartAccess().getAmpersandEqualsSignKeyword_1_2()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Start__Alternatives_1


    // $ANTLR start rule__Start__Alternatives_2
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1059:1: rule__Start__Alternatives_2 : ( ( RULE_ID ) | ( ( ( rulePattern ) ) ) );
    public final void rule__Start__Alternatives_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1063:1: ( ( RULE_ID ) | ( ( ( rulePattern ) ) ) )
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==RULE_ID) ) {
                alt14=1;
            }
            else if ( (LA14_0==RULE_STRING||(LA14_0>=12 && LA14_0<=15)||(LA14_0>=23 && LA14_0<=25)||(LA14_0>=29 && LA14_0<=31)||(LA14_0>=34 && LA14_0<=35)||LA14_0==37||LA14_0==41) ) {
                alt14=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("1059:1: rule__Start__Alternatives_2 : ( ( RULE_ID ) | ( ( ( rulePattern ) ) ) );", 14, 0, input);

                throw nvae;
            }
            switch (alt14) {
                case 1 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1064:1: ( RULE_ID )
                    {
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1064:1: ( RULE_ID )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1065:1: RULE_ID
                    {
                     before(grammarAccess.getStartAccess().getIDTerminalRuleCall_2_0()); 
                    match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__Start__Alternatives_22441); 
                     after(grammarAccess.getStartAccess().getIDTerminalRuleCall_2_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1069:6: ( ( ( rulePattern ) ) )
                    {
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1069:6: ( ( ( rulePattern ) ) )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1070:1: ( ( rulePattern ) )
                    {
                     before(grammarAccess.getStartAccess().getPatternAssignment_2_1()); 
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1071:1: ( ( rulePattern ) )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1072:1: ( rulePattern )
                    {
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1072:1: ( rulePattern )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1073:1: rulePattern
                    {
                     before(grammarAccess.getStartAccess().getPatternPatternParserRuleCall_2_1_0()); 
                    pushFollow(FOLLOW_rulePattern_in_rule__Start__Alternatives_22464);
                    rulePattern();
                    _fsp--;

                     after(grammarAccess.getStartAccess().getPatternPatternParserRuleCall_2_1_0()); 

                    }


                    }

                     after(grammarAccess.getStartAccess().getPatternAssignment_2_1()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Start__Alternatives_2


    // $ANTLR start rule__Define__Alternatives_1
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1085:1: rule__Define__Alternatives_1 : ( ( '=' ) | ( '|=' ) | ( '&=' ) );
    public final void rule__Define__Alternatives_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1089:1: ( ( '=' ) | ( '|=' ) | ( '&=' ) )
            int alt15=3;
            switch ( input.LA(1) ) {
            case 20:
                {
                alt15=1;
                }
                break;
            case 21:
                {
                alt15=2;
                }
                break;
            case 22:
                {
                alt15=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("1085:1: rule__Define__Alternatives_1 : ( ( '=' ) | ( '|=' ) | ( '&=' ) );", 15, 0, input);

                throw nvae;
            }

            switch (alt15) {
                case 1 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1090:1: ( '=' )
                    {
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1090:1: ( '=' )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1091:1: '='
                    {
                     before(grammarAccess.getDefineAccess().getEqualsSignKeyword_1_0()); 
                    match(input,20,FOLLOW_20_in_rule__Define__Alternatives_12503); 
                     after(grammarAccess.getDefineAccess().getEqualsSignKeyword_1_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1097:6: ( '|=' )
                    {
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1097:6: ( '|=' )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1098:1: '|='
                    {
                     before(grammarAccess.getDefineAccess().getVerticalLineEqualsSignKeyword_1_1()); 
                    match(input,21,FOLLOW_21_in_rule__Define__Alternatives_12522); 
                     after(grammarAccess.getDefineAccess().getVerticalLineEqualsSignKeyword_1_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1104:6: ( '&=' )
                    {
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1104:6: ( '&=' )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1105:1: '&='
                    {
                     before(grammarAccess.getDefineAccess().getAmpersandEqualsSignKeyword_1_2()); 
                    match(input,22,FOLLOW_22_in_rule__Define__Alternatives_12541); 
                     after(grammarAccess.getDefineAccess().getAmpersandEqualsSignKeyword_1_2()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Define__Alternatives_1


    // $ANTLR start rule__Name__Alternatives
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1116:1: rule__Name__Alternatives : ( ( ruleIdentifierOrKeyWord ) | ( ruleCName ) );
    public final void rule__Name__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1120:1: ( ( ruleIdentifierOrKeyWord ) | ( ruleCName ) )
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==RULE_ID) ) {
                int LA16_1 = input.LA(2);

                if ( (LA16_1==45) ) {
                    alt16=2;
                }
                else if ( ((LA16_1>=12 && LA16_1<=13)||LA16_1==15||(LA16_1>=23 && LA16_1<=38)) ) {
                    alt16=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("1116:1: rule__Name__Alternatives : ( ( ruleIdentifierOrKeyWord ) | ( ruleCName ) );", 16, 1, input);

                    throw nvae;
                }
            }
            else if ( ((LA16_0>=12 && LA16_0<=13)||LA16_0==15||(LA16_0>=23 && LA16_0<=38)||LA16_0==44) ) {
                alt16=1;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("1116:1: rule__Name__Alternatives : ( ( ruleIdentifierOrKeyWord ) | ( ruleCName ) );", 16, 0, input);

                throw nvae;
            }
            switch (alt16) {
                case 1 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1121:1: ( ruleIdentifierOrKeyWord )
                    {
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1121:1: ( ruleIdentifierOrKeyWord )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1122:1: ruleIdentifierOrKeyWord
                    {
                     before(grammarAccess.getNameAccess().getIdentifierOrKeyWordParserRuleCall_0()); 
                    pushFollow(FOLLOW_ruleIdentifierOrKeyWord_in_rule__Name__Alternatives2574);
                    ruleIdentifierOrKeyWord();
                    _fsp--;

                     after(grammarAccess.getNameAccess().getIdentifierOrKeyWordParserRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1126:6: ( ruleCName )
                    {
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1126:6: ( ruleCName )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1127:1: ruleCName
                    {
                     before(grammarAccess.getNameAccess().getCNameParserRuleCall_1()); 
                    pushFollow(FOLLOW_ruleCName_in_rule__Name__Alternatives2590);
                    ruleCName();
                    _fsp--;

                     after(grammarAccess.getNameAccess().getCNameParserRuleCall_1()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Name__Alternatives


    // $ANTLR start rule__NameClass__Alternatives
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1136:1: rule__NameClass__Alternatives : ( ( ruleName ) | ( ( rule__NameClass__Group_1 ) ) | ( ( rule__NameClass__Group_2 ) ) | ( ( rule__NameClass__Group_3 ) ) | ( ( rule__NameClass__Group_4 ) ) );
    public final void rule__NameClass__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1140:1: ( ( ruleName ) | ( ( rule__NameClass__Group_1 ) ) | ( ( rule__NameClass__Group_2 ) ) | ( ( rule__NameClass__Group_3 ) ) | ( ( rule__NameClass__Group_4 ) ) )
            int alt17=5;
            switch ( input.LA(1) ) {
            case RULE_ID:
                {
                int LA17_1 = input.LA(2);

                if ( ((LA17_1>=12 && LA17_1<=13)||LA17_1==15||(LA17_1>=23 && LA17_1<=38)||LA17_1==45) ) {
                    alt17=1;
                }
                else if ( (LA17_1==EOF||LA17_1==39||(LA17_1>=42 && LA17_1<=43)) ) {
                    alt17=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("1136:1: rule__NameClass__Alternatives : ( ( ruleName ) | ( ( rule__NameClass__Group_1 ) ) | ( ( rule__NameClass__Group_2 ) ) | ( ( rule__NameClass__Group_3 ) ) | ( ( rule__NameClass__Group_4 ) ) );", 17, 1, input);

                    throw nvae;
                }
                }
                break;
            case 12:
            case 13:
            case 15:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 44:
                {
                alt17=1;
                }
                break;
            case 11:
                {
                alt17=3;
                }
                break;
            case 14:
                {
                alt17=4;
                }
                break;
            case 41:
                {
                alt17=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("1136:1: rule__NameClass__Alternatives : ( ( ruleName ) | ( ( rule__NameClass__Group_1 ) ) | ( ( rule__NameClass__Group_2 ) ) | ( ( rule__NameClass__Group_3 ) ) | ( ( rule__NameClass__Group_4 ) ) );", 17, 0, input);

                throw nvae;
            }

            switch (alt17) {
                case 1 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1141:1: ( ruleName )
                    {
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1141:1: ( ruleName )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1142:1: ruleName
                    {
                     before(grammarAccess.getNameClassAccess().getNameParserRuleCall_0()); 
                    pushFollow(FOLLOW_ruleName_in_rule__NameClass__Alternatives2621);
                    ruleName();
                    _fsp--;

                     after(grammarAccess.getNameClassAccess().getNameParserRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1146:6: ( ( rule__NameClass__Group_1 ) )
                    {
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1146:6: ( ( rule__NameClass__Group_1 ) )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1147:1: ( rule__NameClass__Group_1 )
                    {
                     before(grammarAccess.getNameClassAccess().getGroup_1()); 
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1148:1: ( rule__NameClass__Group_1 )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1148:2: rule__NameClass__Group_1
                    {
                    pushFollow(FOLLOW_rule__NameClass__Group_1_in_rule__NameClass__Alternatives2637);
                    rule__NameClass__Group_1();
                    _fsp--;


                    }

                     after(grammarAccess.getNameClassAccess().getGroup_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1151:6: ( ( rule__NameClass__Group_2 ) )
                    {
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1151:6: ( ( rule__NameClass__Group_2 ) )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1152:1: ( rule__NameClass__Group_2 )
                    {
                     before(grammarAccess.getNameClassAccess().getGroup_2()); 
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1153:1: ( rule__NameClass__Group_2 )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1153:2: rule__NameClass__Group_2
                    {
                    pushFollow(FOLLOW_rule__NameClass__Group_2_in_rule__NameClass__Alternatives2654);
                    rule__NameClass__Group_2();
                    _fsp--;


                    }

                     after(grammarAccess.getNameClassAccess().getGroup_2()); 

                    }


                    }
                    break;
                case 4 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1156:6: ( ( rule__NameClass__Group_3 ) )
                    {
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1156:6: ( ( rule__NameClass__Group_3 ) )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1157:1: ( rule__NameClass__Group_3 )
                    {
                     before(grammarAccess.getNameClassAccess().getGroup_3()); 
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1158:1: ( rule__NameClass__Group_3 )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1158:2: rule__NameClass__Group_3
                    {
                    pushFollow(FOLLOW_rule__NameClass__Group_3_in_rule__NameClass__Alternatives2671);
                    rule__NameClass__Group_3();
                    _fsp--;


                    }

                     after(grammarAccess.getNameClassAccess().getGroup_3()); 

                    }


                    }
                    break;
                case 5 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1161:6: ( ( rule__NameClass__Group_4 ) )
                    {
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1161:6: ( ( rule__NameClass__Group_4 ) )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1162:1: ( rule__NameClass__Group_4 )
                    {
                     before(grammarAccess.getNameClassAccess().getGroup_4()); 
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1163:1: ( rule__NameClass__Group_4 )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1163:2: rule__NameClass__Group_4
                    {
                    pushFollow(FOLLOW_rule__NameClass__Group_4_in_rule__NameClass__Alternatives2688);
                    rule__NameClass__Group_4();
                    _fsp--;


                    }

                     after(grammarAccess.getNameClassAccess().getGroup_4()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__NameClass__Alternatives


    // $ANTLR start rule__DataTypeName__Alternatives
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1171:1: rule__DataTypeName__Alternatives : ( ( ruleCName ) | ( 'string' ) | ( 'token' ) );
    public final void rule__DataTypeName__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1175:1: ( ( ruleCName ) | ( 'string' ) | ( 'token' ) )
            int alt18=3;
            switch ( input.LA(1) ) {
            case RULE_ID:
                {
                alt18=1;
                }
                break;
            case 23:
                {
                alt18=2;
                }
                break;
            case 24:
                {
                alt18=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("1171:1: rule__DataTypeName__Alternatives : ( ( ruleCName ) | ( 'string' ) | ( 'token' ) );", 18, 0, input);

                throw nvae;
            }

            switch (alt18) {
                case 1 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1176:1: ( ruleCName )
                    {
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1176:1: ( ruleCName )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1177:1: ruleCName
                    {
                     before(grammarAccess.getDataTypeNameAccess().getCNameParserRuleCall_0()); 
                    pushFollow(FOLLOW_ruleCName_in_rule__DataTypeName__Alternatives2720);
                    ruleCName();
                    _fsp--;

                     after(grammarAccess.getDataTypeNameAccess().getCNameParserRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1181:6: ( 'string' )
                    {
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1181:6: ( 'string' )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1182:1: 'string'
                    {
                     before(grammarAccess.getDataTypeNameAccess().getStringKeyword_1()); 
                    match(input,23,FOLLOW_23_in_rule__DataTypeName__Alternatives2737); 
                     after(grammarAccess.getDataTypeNameAccess().getStringKeyword_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1188:6: ( 'token' )
                    {
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1188:6: ( 'token' )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1189:1: 'token'
                    {
                     before(grammarAccess.getDataTypeNameAccess().getTokenKeyword_2()); 
                    match(input,24,FOLLOW_24_in_rule__DataTypeName__Alternatives2756); 
                     after(grammarAccess.getDataTypeNameAccess().getTokenKeyword_2()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__DataTypeName__Alternatives


    // $ANTLR start rule__IdentifierOrKeyWord__Alternatives
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1201:1: rule__IdentifierOrKeyWord__Alternatives : ( ( ruleIdentifier ) | ( ruleKeyWord ) );
    public final void rule__IdentifierOrKeyWord__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1205:1: ( ( ruleIdentifier ) | ( ruleKeyWord ) )
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==RULE_ID||LA19_0==44) ) {
                alt19=1;
            }
            else if ( ((LA19_0>=12 && LA19_0<=13)||LA19_0==15||(LA19_0>=23 && LA19_0<=38)) ) {
                alt19=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("1201:1: rule__IdentifierOrKeyWord__Alternatives : ( ( ruleIdentifier ) | ( ruleKeyWord ) );", 19, 0, input);

                throw nvae;
            }
            switch (alt19) {
                case 1 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1206:1: ( ruleIdentifier )
                    {
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1206:1: ( ruleIdentifier )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1207:1: ruleIdentifier
                    {
                     before(grammarAccess.getIdentifierOrKeyWordAccess().getIdentifierParserRuleCall_0()); 
                    pushFollow(FOLLOW_ruleIdentifier_in_rule__IdentifierOrKeyWord__Alternatives2790);
                    ruleIdentifier();
                    _fsp--;

                     after(grammarAccess.getIdentifierOrKeyWordAccess().getIdentifierParserRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1211:6: ( ruleKeyWord )
                    {
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1211:6: ( ruleKeyWord )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1212:1: ruleKeyWord
                    {
                     before(grammarAccess.getIdentifierOrKeyWordAccess().getKeyWordParserRuleCall_1()); 
                    pushFollow(FOLLOW_ruleKeyWord_in_rule__IdentifierOrKeyWord__Alternatives2806);
                    ruleKeyWord();
                    _fsp--;

                     after(grammarAccess.getIdentifierOrKeyWordAccess().getKeyWordParserRuleCall_1()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__IdentifierOrKeyWord__Alternatives


    // $ANTLR start rule__Identifier__Alternatives
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1221:1: rule__Identifier__Alternatives : ( ( ( rule__Identifier__Group_0 ) ) | ( ruleQuotedIdentifier ) );
    public final void rule__Identifier__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1225:1: ( ( ( rule__Identifier__Group_0 ) ) | ( ruleQuotedIdentifier ) )
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==RULE_ID) ) {
                alt20=1;
            }
            else if ( (LA20_0==44) ) {
                alt20=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("1221:1: rule__Identifier__Alternatives : ( ( ( rule__Identifier__Group_0 ) ) | ( ruleQuotedIdentifier ) );", 20, 0, input);

                throw nvae;
            }
            switch (alt20) {
                case 1 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1226:1: ( ( rule__Identifier__Group_0 ) )
                    {
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1226:1: ( ( rule__Identifier__Group_0 ) )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1227:1: ( rule__Identifier__Group_0 )
                    {
                     before(grammarAccess.getIdentifierAccess().getGroup_0()); 
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1228:1: ( rule__Identifier__Group_0 )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1228:2: rule__Identifier__Group_0
                    {
                    pushFollow(FOLLOW_rule__Identifier__Group_0_in_rule__Identifier__Alternatives2837);
                    rule__Identifier__Group_0();
                    _fsp--;


                    }

                     after(grammarAccess.getIdentifierAccess().getGroup_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1231:6: ( ruleQuotedIdentifier )
                    {
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1231:6: ( ruleQuotedIdentifier )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1232:1: ruleQuotedIdentifier
                    {
                     before(grammarAccess.getIdentifierAccess().getQuotedIdentifierParserRuleCall_1()); 
                    pushFollow(FOLLOW_ruleQuotedIdentifier_in_rule__Identifier__Alternatives2854);
                    ruleQuotedIdentifier();
                    _fsp--;

                     after(grammarAccess.getIdentifierAccess().getQuotedIdentifierParserRuleCall_1()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Identifier__Alternatives


    // $ANTLR start rule__KeyWord__Alternatives
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1241:1: rule__KeyWord__Alternatives : ( ( 'attribute' ) | ( 'default' ) | ( 'datatypes' ) | ( 'div' ) | ( 'element' ) | ( 'empty' ) | ( 'external' ) | ( 'grammar' ) | ( 'include' ) | ( 'inherit' ) | ( 'list' ) | ( 'mixed' ) | ( 'namespace' ) | ( 'notAllowed' ) | ( 'parent' ) | ( 'start' ) | ( 'string' ) | ( 'text' ) | ( 'token' ) );
    public final void rule__KeyWord__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1245:1: ( ( 'attribute' ) | ( 'default' ) | ( 'datatypes' ) | ( 'div' ) | ( 'element' ) | ( 'empty' ) | ( 'external' ) | ( 'grammar' ) | ( 'include' ) | ( 'inherit' ) | ( 'list' ) | ( 'mixed' ) | ( 'namespace' ) | ( 'notAllowed' ) | ( 'parent' ) | ( 'start' ) | ( 'string' ) | ( 'text' ) | ( 'token' ) )
            int alt21=19;
            switch ( input.LA(1) ) {
            case 25:
                {
                alt21=1;
                }
                break;
            case 26:
                {
                alt21=2;
                }
                break;
            case 27:
                {
                alt21=3;
                }
                break;
            case 28:
                {
                alt21=4;
                }
                break;
            case 29:
                {
                alt21=5;
                }
                break;
            case 12:
                {
                alt21=6;
                }
                break;
            case 30:
                {
                alt21=7;
                }
                break;
            case 31:
                {
                alt21=8;
                }
                break;
            case 32:
                {
                alt21=9;
                }
                break;
            case 33:
                {
                alt21=10;
                }
                break;
            case 34:
                {
                alt21=11;
                }
                break;
            case 35:
                {
                alt21=12;
                }
                break;
            case 36:
                {
                alt21=13;
                }
                break;
            case 15:
                {
                alt21=14;
                }
                break;
            case 37:
                {
                alt21=15;
                }
                break;
            case 38:
                {
                alt21=16;
                }
                break;
            case 23:
                {
                alt21=17;
                }
                break;
            case 13:
                {
                alt21=18;
                }
                break;
            case 24:
                {
                alt21=19;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("1241:1: rule__KeyWord__Alternatives : ( ( 'attribute' ) | ( 'default' ) | ( 'datatypes' ) | ( 'div' ) | ( 'element' ) | ( 'empty' ) | ( 'external' ) | ( 'grammar' ) | ( 'include' ) | ( 'inherit' ) | ( 'list' ) | ( 'mixed' ) | ( 'namespace' ) | ( 'notAllowed' ) | ( 'parent' ) | ( 'start' ) | ( 'string' ) | ( 'text' ) | ( 'token' ) );", 21, 0, input);

                throw nvae;
            }

            switch (alt21) {
                case 1 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1246:1: ( 'attribute' )
                    {
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1246:1: ( 'attribute' )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1247:1: 'attribute'
                    {
                     before(grammarAccess.getKeyWordAccess().getAttributeKeyword_0()); 
                    match(input,25,FOLLOW_25_in_rule__KeyWord__Alternatives2886); 
                     after(grammarAccess.getKeyWordAccess().getAttributeKeyword_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1253:6: ( 'default' )
                    {
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1253:6: ( 'default' )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1254:1: 'default'
                    {
                     before(grammarAccess.getKeyWordAccess().getDefaultKeyword_1()); 
                    match(input,26,FOLLOW_26_in_rule__KeyWord__Alternatives2905); 
                     after(grammarAccess.getKeyWordAccess().getDefaultKeyword_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1260:6: ( 'datatypes' )
                    {
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1260:6: ( 'datatypes' )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1261:1: 'datatypes'
                    {
                     before(grammarAccess.getKeyWordAccess().getDatatypesKeyword_2()); 
                    match(input,27,FOLLOW_27_in_rule__KeyWord__Alternatives2924); 
                     after(grammarAccess.getKeyWordAccess().getDatatypesKeyword_2()); 

                    }


                    }
                    break;
                case 4 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1267:6: ( 'div' )
                    {
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1267:6: ( 'div' )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1268:1: 'div'
                    {
                     before(grammarAccess.getKeyWordAccess().getDivKeyword_3()); 
                    match(input,28,FOLLOW_28_in_rule__KeyWord__Alternatives2943); 
                     after(grammarAccess.getKeyWordAccess().getDivKeyword_3()); 

                    }


                    }
                    break;
                case 5 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1274:6: ( 'element' )
                    {
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1274:6: ( 'element' )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1275:1: 'element'
                    {
                     before(grammarAccess.getKeyWordAccess().getElementKeyword_4()); 
                    match(input,29,FOLLOW_29_in_rule__KeyWord__Alternatives2962); 
                     after(grammarAccess.getKeyWordAccess().getElementKeyword_4()); 

                    }


                    }
                    break;
                case 6 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1281:6: ( 'empty' )
                    {
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1281:6: ( 'empty' )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1282:1: 'empty'
                    {
                     before(grammarAccess.getKeyWordAccess().getEmptyKeyword_5()); 
                    match(input,12,FOLLOW_12_in_rule__KeyWord__Alternatives2981); 
                     after(grammarAccess.getKeyWordAccess().getEmptyKeyword_5()); 

                    }


                    }
                    break;
                case 7 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1288:6: ( 'external' )
                    {
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1288:6: ( 'external' )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1289:1: 'external'
                    {
                     before(grammarAccess.getKeyWordAccess().getExternalKeyword_6()); 
                    match(input,30,FOLLOW_30_in_rule__KeyWord__Alternatives3000); 
                     after(grammarAccess.getKeyWordAccess().getExternalKeyword_6()); 

                    }


                    }
                    break;
                case 8 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1295:6: ( 'grammar' )
                    {
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1295:6: ( 'grammar' )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1296:1: 'grammar'
                    {
                     before(grammarAccess.getKeyWordAccess().getGrammarKeyword_7()); 
                    match(input,31,FOLLOW_31_in_rule__KeyWord__Alternatives3019); 
                     after(grammarAccess.getKeyWordAccess().getGrammarKeyword_7()); 

                    }


                    }
                    break;
                case 9 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1302:6: ( 'include' )
                    {
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1302:6: ( 'include' )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1303:1: 'include'
                    {
                     before(grammarAccess.getKeyWordAccess().getIncludeKeyword_8()); 
                    match(input,32,FOLLOW_32_in_rule__KeyWord__Alternatives3038); 
                     after(grammarAccess.getKeyWordAccess().getIncludeKeyword_8()); 

                    }


                    }
                    break;
                case 10 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1309:6: ( 'inherit' )
                    {
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1309:6: ( 'inherit' )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1310:1: 'inherit'
                    {
                     before(grammarAccess.getKeyWordAccess().getInheritKeyword_9()); 
                    match(input,33,FOLLOW_33_in_rule__KeyWord__Alternatives3057); 
                     after(grammarAccess.getKeyWordAccess().getInheritKeyword_9()); 

                    }


                    }
                    break;
                case 11 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1316:6: ( 'list' )
                    {
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1316:6: ( 'list' )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1317:1: 'list'
                    {
                     before(grammarAccess.getKeyWordAccess().getListKeyword_10()); 
                    match(input,34,FOLLOW_34_in_rule__KeyWord__Alternatives3076); 
                     after(grammarAccess.getKeyWordAccess().getListKeyword_10()); 

                    }


                    }
                    break;
                case 12 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1323:6: ( 'mixed' )
                    {
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1323:6: ( 'mixed' )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1324:1: 'mixed'
                    {
                     before(grammarAccess.getKeyWordAccess().getMixedKeyword_11()); 
                    match(input,35,FOLLOW_35_in_rule__KeyWord__Alternatives3095); 
                     after(grammarAccess.getKeyWordAccess().getMixedKeyword_11()); 

                    }


                    }
                    break;
                case 13 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1330:6: ( 'namespace' )
                    {
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1330:6: ( 'namespace' )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1331:1: 'namespace'
                    {
                     before(grammarAccess.getKeyWordAccess().getNamespaceKeyword_12()); 
                    match(input,36,FOLLOW_36_in_rule__KeyWord__Alternatives3114); 
                     after(grammarAccess.getKeyWordAccess().getNamespaceKeyword_12()); 

                    }


                    }
                    break;
                case 14 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1337:6: ( 'notAllowed' )
                    {
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1337:6: ( 'notAllowed' )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1338:1: 'notAllowed'
                    {
                     before(grammarAccess.getKeyWordAccess().getNotAllowedKeyword_13()); 
                    match(input,15,FOLLOW_15_in_rule__KeyWord__Alternatives3133); 
                     after(grammarAccess.getKeyWordAccess().getNotAllowedKeyword_13()); 

                    }


                    }
                    break;
                case 15 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1344:6: ( 'parent' )
                    {
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1344:6: ( 'parent' )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1345:1: 'parent'
                    {
                     before(grammarAccess.getKeyWordAccess().getParentKeyword_14()); 
                    match(input,37,FOLLOW_37_in_rule__KeyWord__Alternatives3152); 
                     after(grammarAccess.getKeyWordAccess().getParentKeyword_14()); 

                    }


                    }
                    break;
                case 16 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1351:6: ( 'start' )
                    {
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1351:6: ( 'start' )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1352:1: 'start'
                    {
                     before(grammarAccess.getKeyWordAccess().getStartKeyword_15()); 
                    match(input,38,FOLLOW_38_in_rule__KeyWord__Alternatives3171); 
                     after(grammarAccess.getKeyWordAccess().getStartKeyword_15()); 

                    }


                    }
                    break;
                case 17 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1358:6: ( 'string' )
                    {
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1358:6: ( 'string' )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1359:1: 'string'
                    {
                     before(grammarAccess.getKeyWordAccess().getStringKeyword_16()); 
                    match(input,23,FOLLOW_23_in_rule__KeyWord__Alternatives3190); 
                     after(grammarAccess.getKeyWordAccess().getStringKeyword_16()); 

                    }


                    }
                    break;
                case 18 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1365:6: ( 'text' )
                    {
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1365:6: ( 'text' )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1366:1: 'text'
                    {
                     before(grammarAccess.getKeyWordAccess().getTextKeyword_17()); 
                    match(input,13,FOLLOW_13_in_rule__KeyWord__Alternatives3209); 
                     after(grammarAccess.getKeyWordAccess().getTextKeyword_17()); 

                    }


                    }
                    break;
                case 19 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1372:6: ( 'token' )
                    {
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1372:6: ( 'token' )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1373:1: 'token'
                    {
                     before(grammarAccess.getKeyWordAccess().getTokenKeyword_18()); 
                    match(input,24,FOLLOW_24_in_rule__KeyWord__Alternatives3228); 
                     after(grammarAccess.getKeyWordAccess().getTokenKeyword_18()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__KeyWord__Alternatives


    // $ANTLR start rule__TopLevel__Group
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1385:1: rule__TopLevel__Group : ( ( ( ruleDecl ) )* ) ( ( rule__TopLevel__Alternatives_1 ) ) ;
    public final void rule__TopLevel__Group() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1389:1: ( ( ( ( ruleDecl ) )* ) ( ( rule__TopLevel__Alternatives_1 ) ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1390:1: ( ( ( ruleDecl ) )* ) ( ( rule__TopLevel__Alternatives_1 ) )
            {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1390:1: ( ( ( ruleDecl ) )* )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1391:1: ( ( ruleDecl ) )*
            {
             before(grammarAccess.getTopLevelAccess().getDeclsAssignment_0()); 
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1392:1: ( ( ruleDecl ) )*
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( ((LA22_0>=26 && LA22_0<=27)||LA22_0==36) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1393:1: ( ruleDecl )
            	    {
            	    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1393:1: ( ruleDecl )
            	    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1394:1: ruleDecl
            	    {
            	     before(grammarAccess.getTopLevelAccess().getDeclsDeclParserRuleCall_0_0()); 
            	    pushFollow(FOLLOW_ruleDecl_in_rule__TopLevel__Group3269);
            	    ruleDecl();
            	    _fsp--;

            	     after(grammarAccess.getTopLevelAccess().getDeclsDeclParserRuleCall_0_0()); 

            	    }


            	    }
            	    break;

            	default :
            	    break loop22;
                }
            } while (true);

             after(grammarAccess.getTopLevelAccess().getDeclsAssignment_0()); 

            }

            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1400:1: ( ( rule__TopLevel__Alternatives_1 ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1401:1: ( rule__TopLevel__Alternatives_1 )
            {
             before(grammarAccess.getTopLevelAccess().getAlternatives_1()); 
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1402:1: ( rule__TopLevel__Alternatives_1 )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1402:2: rule__TopLevel__Alternatives_1
            {
            pushFollow(FOLLOW_rule__TopLevel__Alternatives_1_in_rule__TopLevel__Group3287);
            rule__TopLevel__Alternatives_1();
            _fsp--;


            }

             after(grammarAccess.getTopLevelAccess().getAlternatives_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__TopLevel__Group


    // $ANTLR start rule__Decl__Group_0
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1410:1: rule__Decl__Group_0 : ( 'namespace' ) ( ( ( RULE_ID ) ) ) ( '=' ) ( ( ( RULE_STRING ) ) ) ;
    public final void rule__Decl__Group_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1414:1: ( ( 'namespace' ) ( ( ( RULE_ID ) ) ) ( '=' ) ( ( ( RULE_STRING ) ) ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1415:1: ( 'namespace' ) ( ( ( RULE_ID ) ) ) ( '=' ) ( ( ( RULE_STRING ) ) )
            {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1415:1: ( 'namespace' )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1416:1: 'namespace'
            {
             before(grammarAccess.getDeclAccess().getNamespaceKeyword_0_0()); 
            match(input,36,FOLLOW_36_in_rule__Decl__Group_03320); 
             after(grammarAccess.getDeclAccess().getNamespaceKeyword_0_0()); 

            }

            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1421:1: ( ( ( RULE_ID ) ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1422:1: ( ( RULE_ID ) )
            {
             before(grammarAccess.getDeclAccess().getPrefixAssignment_0_1()); 
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1423:1: ( ( RULE_ID ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1424:1: ( RULE_ID )
            {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1424:1: ( RULE_ID )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1425:1: RULE_ID
            {
             before(grammarAccess.getDeclAccess().getPrefixIDTerminalRuleCall_0_1_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__Decl__Group_03339); 
             after(grammarAccess.getDeclAccess().getPrefixIDTerminalRuleCall_0_1_0()); 

            }


            }

             after(grammarAccess.getDeclAccess().getPrefixAssignment_0_1()); 

            }

            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1431:1: ( '=' )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1432:1: '='
            {
             before(grammarAccess.getDeclAccess().getEqualsSignKeyword_0_2()); 
            match(input,20,FOLLOW_20_in_rule__Decl__Group_03357); 
             after(grammarAccess.getDeclAccess().getEqualsSignKeyword_0_2()); 

            }

            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1437:1: ( ( ( RULE_STRING ) ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1438:1: ( ( RULE_STRING ) )
            {
             before(grammarAccess.getDeclAccess().getUriAssignment_0_3()); 
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1439:1: ( ( RULE_STRING ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1440:1: ( RULE_STRING )
            {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1440:1: ( RULE_STRING )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1441:1: RULE_STRING
            {
             before(grammarAccess.getDeclAccess().getUriSTRINGTerminalRuleCall_0_3_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__Decl__Group_03376); 
             after(grammarAccess.getDeclAccess().getUriSTRINGTerminalRuleCall_0_3_0()); 

            }


            }

             after(grammarAccess.getDeclAccess().getUriAssignment_0_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Decl__Group_0


    // $ANTLR start rule__Decl__Group_1
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1453:1: rule__Decl__Group_1 : ( 'default' ) ( 'namespace' ) ( ( ( RULE_ID ) ) ) ( '=' ) ( ( ( RULE_STRING ) ) ) ;
    public final void rule__Decl__Group_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1457:1: ( ( 'default' ) ( 'namespace' ) ( ( ( RULE_ID ) ) ) ( '=' ) ( ( ( RULE_STRING ) ) ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1458:1: ( 'default' ) ( 'namespace' ) ( ( ( RULE_ID ) ) ) ( '=' ) ( ( ( RULE_STRING ) ) )
            {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1458:1: ( 'default' )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1459:1: 'default'
            {
             before(grammarAccess.getDeclAccess().getDefaultKeyword_1_0()); 
            match(input,26,FOLLOW_26_in_rule__Decl__Group_13415); 
             after(grammarAccess.getDeclAccess().getDefaultKeyword_1_0()); 

            }

            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1464:1: ( 'namespace' )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1465:1: 'namespace'
            {
             before(grammarAccess.getDeclAccess().getNamespaceKeyword_1_1()); 
            match(input,36,FOLLOW_36_in_rule__Decl__Group_13428); 
             after(grammarAccess.getDeclAccess().getNamespaceKeyword_1_1()); 

            }

            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1470:1: ( ( ( RULE_ID ) ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1471:1: ( ( RULE_ID ) )
            {
             before(grammarAccess.getDeclAccess().getPrefixAssignment_1_2()); 
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1472:1: ( ( RULE_ID ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1473:1: ( RULE_ID )
            {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1473:1: ( RULE_ID )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1474:1: RULE_ID
            {
             before(grammarAccess.getDeclAccess().getPrefixIDTerminalRuleCall_1_2_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__Decl__Group_13447); 
             after(grammarAccess.getDeclAccess().getPrefixIDTerminalRuleCall_1_2_0()); 

            }


            }

             after(grammarAccess.getDeclAccess().getPrefixAssignment_1_2()); 

            }

            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1480:1: ( '=' )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1481:1: '='
            {
             before(grammarAccess.getDeclAccess().getEqualsSignKeyword_1_3()); 
            match(input,20,FOLLOW_20_in_rule__Decl__Group_13465); 
             after(grammarAccess.getDeclAccess().getEqualsSignKeyword_1_3()); 

            }

            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1486:1: ( ( ( RULE_STRING ) ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1487:1: ( ( RULE_STRING ) )
            {
             before(grammarAccess.getDeclAccess().getUriAssignment_1_4()); 
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1488:1: ( ( RULE_STRING ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1489:1: ( RULE_STRING )
            {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1489:1: ( RULE_STRING )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1490:1: RULE_STRING
            {
             before(grammarAccess.getDeclAccess().getUriSTRINGTerminalRuleCall_1_4_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__Decl__Group_13484); 
             after(grammarAccess.getDeclAccess().getUriSTRINGTerminalRuleCall_1_4_0()); 

            }


            }

             after(grammarAccess.getDeclAccess().getUriAssignment_1_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Decl__Group_1


    // $ANTLR start rule__Decl__Group_2
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1502:1: rule__Decl__Group_2 : ( 'datatypes' ) ( ( ( RULE_ID ) ) ) ( '=' ) ( ( ( RULE_STRING ) ) ) ;
    public final void rule__Decl__Group_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1506:1: ( ( 'datatypes' ) ( ( ( RULE_ID ) ) ) ( '=' ) ( ( ( RULE_STRING ) ) ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1507:1: ( 'datatypes' ) ( ( ( RULE_ID ) ) ) ( '=' ) ( ( ( RULE_STRING ) ) )
            {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1507:1: ( 'datatypes' )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1508:1: 'datatypes'
            {
             before(grammarAccess.getDeclAccess().getDatatypesKeyword_2_0()); 
            match(input,27,FOLLOW_27_in_rule__Decl__Group_23523); 
             after(grammarAccess.getDeclAccess().getDatatypesKeyword_2_0()); 

            }

            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1513:1: ( ( ( RULE_ID ) ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1514:1: ( ( RULE_ID ) )
            {
             before(grammarAccess.getDeclAccess().getDatatypeIdAssignment_2_1()); 
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1515:1: ( ( RULE_ID ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1516:1: ( RULE_ID )
            {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1516:1: ( RULE_ID )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1517:1: RULE_ID
            {
             before(grammarAccess.getDeclAccess().getDatatypeIdIDTerminalRuleCall_2_1_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__Decl__Group_23542); 
             after(grammarAccess.getDeclAccess().getDatatypeIdIDTerminalRuleCall_2_1_0()); 

            }


            }

             after(grammarAccess.getDeclAccess().getDatatypeIdAssignment_2_1()); 

            }

            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1523:1: ( '=' )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1524:1: '='
            {
             before(grammarAccess.getDeclAccess().getEqualsSignKeyword_2_2()); 
            match(input,20,FOLLOW_20_in_rule__Decl__Group_23560); 
             after(grammarAccess.getDeclAccess().getEqualsSignKeyword_2_2()); 

            }

            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1529:1: ( ( ( RULE_STRING ) ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1530:1: ( ( RULE_STRING ) )
            {
             before(grammarAccess.getDeclAccess().getValueAssignment_2_3()); 
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1531:1: ( ( RULE_STRING ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1532:1: ( RULE_STRING )
            {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1532:1: ( RULE_STRING )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1533:1: RULE_STRING
            {
             before(grammarAccess.getDeclAccess().getValueSTRINGTerminalRuleCall_2_3_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__Decl__Group_23579); 
             after(grammarAccess.getDeclAccess().getValueSTRINGTerminalRuleCall_2_3_0()); 

            }


            }

             after(grammarAccess.getDeclAccess().getValueAssignment_2_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Decl__Group_2


    // $ANTLR start rule__Pattern__Group_1
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1545:1: rule__Pattern__Group_1 : ( 'list' ) ( '{' ) ( ( ( rulePattern ) )* ) ( '}' ) ;
    public final void rule__Pattern__Group_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1549:1: ( ( 'list' ) ( '{' ) ( ( ( rulePattern ) )* ) ( '}' ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1550:1: ( 'list' ) ( '{' ) ( ( ( rulePattern ) )* ) ( '}' )
            {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1550:1: ( 'list' )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1551:1: 'list'
            {
             before(grammarAccess.getPatternAccess().getListKeyword_1_0()); 
            match(input,34,FOLLOW_34_in_rule__Pattern__Group_13618); 
             after(grammarAccess.getPatternAccess().getListKeyword_1_0()); 

            }

            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1556:1: ( '{' )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1557:1: '{'
            {
             before(grammarAccess.getPatternAccess().getLeftCurlyBracketKeyword_1_1()); 
            match(input,39,FOLLOW_39_in_rule__Pattern__Group_13631); 
             after(grammarAccess.getPatternAccess().getLeftCurlyBracketKeyword_1_1()); 

            }

            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1562:1: ( ( ( rulePattern ) )* )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1563:1: ( ( rulePattern ) )*
            {
             before(grammarAccess.getPatternAccess().getPatternAssignment_1_2()); 
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1564:1: ( ( rulePattern ) )*
            loop23:
            do {
                int alt23=2;
                int LA23_0 = input.LA(1);

                if ( ((LA23_0>=RULE_ID && LA23_0<=RULE_STRING)||(LA23_0>=12 && LA23_0<=15)||(LA23_0>=23 && LA23_0<=25)||(LA23_0>=29 && LA23_0<=31)||(LA23_0>=34 && LA23_0<=35)||LA23_0==37||LA23_0==41) ) {
                    alt23=1;
                }


                switch (alt23) {
            	case 1 :
            	    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1565:1: ( rulePattern )
            	    {
            	    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1565:1: ( rulePattern )
            	    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1566:1: rulePattern
            	    {
            	     before(grammarAccess.getPatternAccess().getPatternPatternParserRuleCall_1_2_0()); 
            	    pushFollow(FOLLOW_rulePattern_in_rule__Pattern__Group_13650);
            	    rulePattern();
            	    _fsp--;

            	     after(grammarAccess.getPatternAccess().getPatternPatternParserRuleCall_1_2_0()); 

            	    }


            	    }
            	    break;

            	default :
            	    break loop23;
                }
            } while (true);

             after(grammarAccess.getPatternAccess().getPatternAssignment_1_2()); 

            }

            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1572:1: ( '}' )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1573:1: '}'
            {
             before(grammarAccess.getPatternAccess().getRightCurlyBracketKeyword_1_3()); 
            match(input,40,FOLLOW_40_in_rule__Pattern__Group_13669); 
             after(grammarAccess.getPatternAccess().getRightCurlyBracketKeyword_1_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Pattern__Group_1


    // $ANTLR start rule__Pattern__Group_2
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1584:1: rule__Pattern__Group_2 : ( 'mixed' ) ( '{' ) ( ( ( rulePattern ) )* ) ( '}' ) ;
    public final void rule__Pattern__Group_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1588:1: ( ( 'mixed' ) ( '{' ) ( ( ( rulePattern ) )* ) ( '}' ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1589:1: ( 'mixed' ) ( '{' ) ( ( ( rulePattern ) )* ) ( '}' )
            {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1589:1: ( 'mixed' )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1590:1: 'mixed'
            {
             before(grammarAccess.getPatternAccess().getMixedKeyword_2_0()); 
            match(input,35,FOLLOW_35_in_rule__Pattern__Group_23703); 
             after(grammarAccess.getPatternAccess().getMixedKeyword_2_0()); 

            }

            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1595:1: ( '{' )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1596:1: '{'
            {
             before(grammarAccess.getPatternAccess().getLeftCurlyBracketKeyword_2_1()); 
            match(input,39,FOLLOW_39_in_rule__Pattern__Group_23716); 
             after(grammarAccess.getPatternAccess().getLeftCurlyBracketKeyword_2_1()); 

            }

            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1601:1: ( ( ( rulePattern ) )* )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1602:1: ( ( rulePattern ) )*
            {
             before(grammarAccess.getPatternAccess().getPatternAssignment_2_2()); 
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1603:1: ( ( rulePattern ) )*
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( ((LA24_0>=RULE_ID && LA24_0<=RULE_STRING)||(LA24_0>=12 && LA24_0<=15)||(LA24_0>=23 && LA24_0<=25)||(LA24_0>=29 && LA24_0<=31)||(LA24_0>=34 && LA24_0<=35)||LA24_0==37||LA24_0==41) ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1604:1: ( rulePattern )
            	    {
            	    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1604:1: ( rulePattern )
            	    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1605:1: rulePattern
            	    {
            	     before(grammarAccess.getPatternAccess().getPatternPatternParserRuleCall_2_2_0()); 
            	    pushFollow(FOLLOW_rulePattern_in_rule__Pattern__Group_23735);
            	    rulePattern();
            	    _fsp--;

            	     after(grammarAccess.getPatternAccess().getPatternPatternParserRuleCall_2_2_0()); 

            	    }


            	    }
            	    break;

            	default :
            	    break loop24;
                }
            } while (true);

             after(grammarAccess.getPatternAccess().getPatternAssignment_2_2()); 

            }

            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1611:1: ( '}' )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1612:1: '}'
            {
             before(grammarAccess.getPatternAccess().getRightCurlyBracketKeyword_2_3()); 
            match(input,40,FOLLOW_40_in_rule__Pattern__Group_23754); 
             after(grammarAccess.getPatternAccess().getRightCurlyBracketKeyword_2_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Pattern__Group_2


    // $ANTLR start rule__Pattern__Group_3
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1623:1: rule__Pattern__Group_3 : ( RULE_ID ) ( ( '=' )? ) ;
    public final void rule__Pattern__Group_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1627:1: ( ( RULE_ID ) ( ( '=' )? ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1628:1: ( RULE_ID ) ( ( '=' )? )
            {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1628:1: ( RULE_ID )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1629:1: RULE_ID
            {
             before(grammarAccess.getPatternAccess().getIDTerminalRuleCall_3_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__Pattern__Group_33787); 
             after(grammarAccess.getPatternAccess().getIDTerminalRuleCall_3_0()); 

            }

            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1632:1: ( ( '=' )? )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1633:1: ( '=' )?
            {
             before(grammarAccess.getPatternAccess().getEqualsSignKeyword_3_1()); 
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1634:1: ( '=' )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==20) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1635:2: '='
                    {
                    match(input,20,FOLLOW_20_in_rule__Pattern__Group_33799); 

                    }
                    break;

            }

             after(grammarAccess.getPatternAccess().getEqualsSignKeyword_3_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Pattern__Group_3


    // $ANTLR start rule__Pattern__Group_4
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1644:1: rule__Pattern__Group_4 : ( 'parent' ) ( RULE_ID ) ;
    public final void rule__Pattern__Group_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1648:1: ( ( 'parent' ) ( RULE_ID ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1649:1: ( 'parent' ) ( RULE_ID )
            {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1649:1: ( 'parent' )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1650:1: 'parent'
            {
             before(grammarAccess.getPatternAccess().getParentKeyword_4_0()); 
            match(input,37,FOLLOW_37_in_rule__Pattern__Group_43835); 
             after(grammarAccess.getPatternAccess().getParentKeyword_4_0()); 

            }

            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1655:1: ( RULE_ID )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1656:1: RULE_ID
            {
             before(grammarAccess.getPatternAccess().getIDTerminalRuleCall_4_1()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__Pattern__Group_43847); 
             after(grammarAccess.getPatternAccess().getIDTerminalRuleCall_4_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Pattern__Group_4


    // $ANTLR start rule__Pattern__Group_9
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1665:1: rule__Pattern__Group_9 : ( ruleDataTypeName ) ( ( rule__Pattern__Group_9_1 )* ) ( ( ( ruleExceptPattern ) )* ) ;
    public final void rule__Pattern__Group_9() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1669:1: ( ( ruleDataTypeName ) ( ( rule__Pattern__Group_9_1 )* ) ( ( ( ruleExceptPattern ) )* ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1670:1: ( ruleDataTypeName ) ( ( rule__Pattern__Group_9_1 )* ) ( ( ( ruleExceptPattern ) )* )
            {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1670:1: ( ruleDataTypeName )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1671:1: ruleDataTypeName
            {
             before(grammarAccess.getPatternAccess().getDataTypeNameParserRuleCall_9_0()); 
            pushFollow(FOLLOW_ruleDataTypeName_in_rule__Pattern__Group_93878);
            ruleDataTypeName();
            _fsp--;

             after(grammarAccess.getPatternAccess().getDataTypeNameParserRuleCall_9_0()); 

            }

            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1674:1: ( ( rule__Pattern__Group_9_1 )* )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1675:1: ( rule__Pattern__Group_9_1 )*
            {
             before(grammarAccess.getPatternAccess().getGroup_9_1()); 
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1676:1: ( rule__Pattern__Group_9_1 )*
            loop26:
            do {
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( (LA26_0==39) ) {
                    alt26=1;
                }


                switch (alt26) {
            	case 1 :
            	    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1676:2: rule__Pattern__Group_9_1
            	    {
            	    pushFollow(FOLLOW_rule__Pattern__Group_9_1_in_rule__Pattern__Group_93888);
            	    rule__Pattern__Group_9_1();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    break loop26;
                }
            } while (true);

             after(grammarAccess.getPatternAccess().getGroup_9_1()); 

            }

            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1678:1: ( ( ( ruleExceptPattern ) )* )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1679:1: ( ( ruleExceptPattern ) )*
            {
             before(grammarAccess.getPatternAccess().getExceptPatternAssignment_9_2()); 
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1680:1: ( ( ruleExceptPattern ) )*
            loop27:
            do {
                int alt27=2;
                int LA27_0 = input.LA(1);

                if ( (LA27_0==43) ) {
                    alt27=1;
                }


                switch (alt27) {
            	case 1 :
            	    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1681:1: ( ruleExceptPattern )
            	    {
            	    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1681:1: ( ruleExceptPattern )
            	    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1682:1: ruleExceptPattern
            	    {
            	     before(grammarAccess.getPatternAccess().getExceptPatternExceptPatternParserRuleCall_9_2_0()); 
            	    pushFollow(FOLLOW_ruleExceptPattern_in_rule__Pattern__Group_93907);
            	    ruleExceptPattern();
            	    _fsp--;

            	     after(grammarAccess.getPatternAccess().getExceptPatternExceptPatternParserRuleCall_9_2_0()); 

            	    }


            	    }
            	    break;

            	default :
            	    break loop27;
                }
            } while (true);

             after(grammarAccess.getPatternAccess().getExceptPatternAssignment_9_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Pattern__Group_9


    // $ANTLR start rule__Pattern__Group_9_1
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1694:1: rule__Pattern__Group_9_1 : ( '{' ) ( ( ( ruleParam ) )* ) ( '}' ) ;
    public final void rule__Pattern__Group_9_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1698:1: ( ( '{' ) ( ( ( ruleParam ) )* ) ( '}' ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1699:1: ( '{' ) ( ( ( ruleParam ) )* ) ( '}' )
            {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1699:1: ( '{' )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1700:1: '{'
            {
             before(grammarAccess.getPatternAccess().getLeftCurlyBracketKeyword_9_1_0()); 
            match(input,39,FOLLOW_39_in_rule__Pattern__Group_9_13947); 
             after(grammarAccess.getPatternAccess().getLeftCurlyBracketKeyword_9_1_0()); 

            }

            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1705:1: ( ( ( ruleParam ) )* )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1706:1: ( ( ruleParam ) )*
            {
             before(grammarAccess.getPatternAccess().getParamAssignment_9_1_1()); 
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1707:1: ( ( ruleParam ) )*
            loop28:
            do {
                int alt28=2;
                int LA28_0 = input.LA(1);

                if ( (LA28_0==RULE_ID||(LA28_0>=12 && LA28_0<=13)||LA28_0==15||(LA28_0>=23 && LA28_0<=38)||LA28_0==44) ) {
                    alt28=1;
                }


                switch (alt28) {
            	case 1 :
            	    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1708:1: ( ruleParam )
            	    {
            	    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1708:1: ( ruleParam )
            	    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1709:1: ruleParam
            	    {
            	     before(grammarAccess.getPatternAccess().getParamParamParserRuleCall_9_1_1_0()); 
            	    pushFollow(FOLLOW_ruleParam_in_rule__Pattern__Group_9_13966);
            	    ruleParam();
            	    _fsp--;

            	     after(grammarAccess.getPatternAccess().getParamParamParserRuleCall_9_1_1_0()); 

            	    }


            	    }
            	    break;

            	default :
            	    break loop28;
                }
            } while (true);

             after(grammarAccess.getPatternAccess().getParamAssignment_9_1_1()); 

            }

            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1715:1: ( '}' )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1716:1: '}'
            {
             before(grammarAccess.getPatternAccess().getRightCurlyBracketKeyword_9_1_2()); 
            match(input,40,FOLLOW_40_in_rule__Pattern__Group_9_13985); 
             after(grammarAccess.getPatternAccess().getRightCurlyBracketKeyword_9_1_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Pattern__Group_9_1


    // $ANTLR start rule__Pattern__Group_11
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1727:1: rule__Pattern__Group_11 : ( 'external' ) ( ( ( ruleAnyURILiteral ) ) ) ( ( ( ruleInherit ) )* ) ;
    public final void rule__Pattern__Group_11() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1731:1: ( ( 'external' ) ( ( ( ruleAnyURILiteral ) ) ) ( ( ( ruleInherit ) )* ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1732:1: ( 'external' ) ( ( ( ruleAnyURILiteral ) ) ) ( ( ( ruleInherit ) )* )
            {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1732:1: ( 'external' )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1733:1: 'external'
            {
             before(grammarAccess.getPatternAccess().getExternalKeyword_11_0()); 
            match(input,30,FOLLOW_30_in_rule__Pattern__Group_114019); 
             after(grammarAccess.getPatternAccess().getExternalKeyword_11_0()); 

            }

            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1738:1: ( ( ( ruleAnyURILiteral ) ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1739:1: ( ( ruleAnyURILiteral ) )
            {
             before(grammarAccess.getPatternAccess().getUriAssignment_11_1()); 
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1740:1: ( ( ruleAnyURILiteral ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1741:1: ( ruleAnyURILiteral )
            {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1741:1: ( ruleAnyURILiteral )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1742:1: ruleAnyURILiteral
            {
             before(grammarAccess.getPatternAccess().getUriAnyURILiteralParserRuleCall_11_1_0()); 
            pushFollow(FOLLOW_ruleAnyURILiteral_in_rule__Pattern__Group_114038);
            ruleAnyURILiteral();
            _fsp--;

             after(grammarAccess.getPatternAccess().getUriAnyURILiteralParserRuleCall_11_1_0()); 

            }


            }

             after(grammarAccess.getPatternAccess().getUriAssignment_11_1()); 

            }

            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1748:1: ( ( ( ruleInherit ) )* )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1749:1: ( ( ruleInherit ) )*
            {
             before(grammarAccess.getPatternAccess().getInheritAssignment_11_2()); 
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1750:1: ( ( ruleInherit ) )*
            loop29:
            do {
                int alt29=2;
                int LA29_0 = input.LA(1);

                if ( (LA29_0==33) ) {
                    alt29=1;
                }


                switch (alt29) {
            	case 1 :
            	    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1751:1: ( ruleInherit )
            	    {
            	    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1751:1: ( ruleInherit )
            	    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1752:1: ruleInherit
            	    {
            	     before(grammarAccess.getPatternAccess().getInheritInheritParserRuleCall_11_2_0()); 
            	    pushFollow(FOLLOW_ruleInherit_in_rule__Pattern__Group_114062);
            	    ruleInherit();
            	    _fsp--;

            	     after(grammarAccess.getPatternAccess().getInheritInheritParserRuleCall_11_2_0()); 

            	    }


            	    }
            	    break;

            	default :
            	    break loop29;
                }
            } while (true);

             after(grammarAccess.getPatternAccess().getInheritAssignment_11_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Pattern__Group_11


    // $ANTLR start rule__Pattern__Group_12
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1764:1: rule__Pattern__Group_12 : ( 'grammar' ) ( '{' ) ( ( ( ruleGrammarContent ) )* ) ( '}' ) ;
    public final void rule__Pattern__Group_12() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1768:1: ( ( 'grammar' ) ( '{' ) ( ( ( ruleGrammarContent ) )* ) ( '}' ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1769:1: ( 'grammar' ) ( '{' ) ( ( ( ruleGrammarContent ) )* ) ( '}' )
            {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1769:1: ( 'grammar' )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1770:1: 'grammar'
            {
             before(grammarAccess.getPatternAccess().getGrammarKeyword_12_0()); 
            match(input,31,FOLLOW_31_in_rule__Pattern__Group_124102); 
             after(grammarAccess.getPatternAccess().getGrammarKeyword_12_0()); 

            }

            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1775:1: ( '{' )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1776:1: '{'
            {
             before(grammarAccess.getPatternAccess().getLeftCurlyBracketKeyword_12_1()); 
            match(input,39,FOLLOW_39_in_rule__Pattern__Group_124115); 
             after(grammarAccess.getPatternAccess().getLeftCurlyBracketKeyword_12_1()); 

            }

            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1781:1: ( ( ( ruleGrammarContent ) )* )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1782:1: ( ( ruleGrammarContent ) )*
            {
             before(grammarAccess.getPatternAccess().getGrammarContentAssignment_12_2()); 
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1783:1: ( ( ruleGrammarContent ) )*
            loop30:
            do {
                int alt30=2;
                int LA30_0 = input.LA(1);

                if ( (LA30_0==RULE_ID||LA30_0==28||LA30_0==32||LA30_0==38) ) {
                    alt30=1;
                }


                switch (alt30) {
            	case 1 :
            	    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1784:1: ( ruleGrammarContent )
            	    {
            	    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1784:1: ( ruleGrammarContent )
            	    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1785:1: ruleGrammarContent
            	    {
            	     before(grammarAccess.getPatternAccess().getGrammarContentGrammarContentParserRuleCall_12_2_0()); 
            	    pushFollow(FOLLOW_ruleGrammarContent_in_rule__Pattern__Group_124134);
            	    ruleGrammarContent();
            	    _fsp--;

            	     after(grammarAccess.getPatternAccess().getGrammarContentGrammarContentParserRuleCall_12_2_0()); 

            	    }


            	    }
            	    break;

            	default :
            	    break loop30;
                }
            } while (true);

             after(grammarAccess.getPatternAccess().getGrammarContentAssignment_12_2()); 

            }

            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1791:1: ( '}' )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1792:1: '}'
            {
             before(grammarAccess.getPatternAccess().getRightCurlyBracketKeyword_12_3()); 
            match(input,40,FOLLOW_40_in_rule__Pattern__Group_124153); 
             after(grammarAccess.getPatternAccess().getRightCurlyBracketKeyword_12_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Pattern__Group_12


    // $ANTLR start rule__Pattern__Group_13
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1803:1: rule__Pattern__Group_13 : ( '(' ) ( ( ( rulePattern ) )* ) ( ')' ) ( ( ( ( ',' ) ) )? ) ;
    public final void rule__Pattern__Group_13() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1807:1: ( ( '(' ) ( ( ( rulePattern ) )* ) ( ')' ) ( ( ( ( ',' ) ) )? ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1808:1: ( '(' ) ( ( ( rulePattern ) )* ) ( ')' ) ( ( ( ( ',' ) ) )? )
            {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1808:1: ( '(' )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1809:1: '('
            {
             before(grammarAccess.getPatternAccess().getLeftParenthesisKeyword_13_0()); 
            match(input,41,FOLLOW_41_in_rule__Pattern__Group_134187); 
             after(grammarAccess.getPatternAccess().getLeftParenthesisKeyword_13_0()); 

            }

            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1814:1: ( ( ( rulePattern ) )* )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1815:1: ( ( rulePattern ) )*
            {
             before(grammarAccess.getPatternAccess().getGroupAssignment_13_1()); 
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1816:1: ( ( rulePattern ) )*
            loop31:
            do {
                int alt31=2;
                int LA31_0 = input.LA(1);

                if ( ((LA31_0>=RULE_ID && LA31_0<=RULE_STRING)||(LA31_0>=12 && LA31_0<=15)||(LA31_0>=23 && LA31_0<=25)||(LA31_0>=29 && LA31_0<=31)||(LA31_0>=34 && LA31_0<=35)||LA31_0==37||LA31_0==41) ) {
                    alt31=1;
                }


                switch (alt31) {
            	case 1 :
            	    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1817:1: ( rulePattern )
            	    {
            	    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1817:1: ( rulePattern )
            	    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1818:1: rulePattern
            	    {
            	     before(grammarAccess.getPatternAccess().getGroupPatternParserRuleCall_13_1_0()); 
            	    pushFollow(FOLLOW_rulePattern_in_rule__Pattern__Group_134206);
            	    rulePattern();
            	    _fsp--;

            	     after(grammarAccess.getPatternAccess().getGroupPatternParserRuleCall_13_1_0()); 

            	    }


            	    }
            	    break;

            	default :
            	    break loop31;
                }
            } while (true);

             after(grammarAccess.getPatternAccess().getGroupAssignment_13_1()); 

            }

            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1824:1: ( ')' )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1825:1: ')'
            {
             before(grammarAccess.getPatternAccess().getRightParenthesisKeyword_13_2()); 
            match(input,42,FOLLOW_42_in_rule__Pattern__Group_134225); 
             after(grammarAccess.getPatternAccess().getRightParenthesisKeyword_13_2()); 

            }

            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1830:1: ( ( ( ( ',' ) ) )? )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1831:1: ( ( ( ',' ) ) )?
            {
             before(grammarAccess.getPatternAccess().getContinueAssignment_13_3()); 
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1832:1: ( ( ( ',' ) ) )?
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( (LA32_0==18) ) {
                alt32=1;
            }
            switch (alt32) {
                case 1 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1833:1: ( ( ',' ) )
                    {
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1833:1: ( ( ',' ) )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1834:1: ( ',' )
                    {
                     before(grammarAccess.getPatternAccess().getContinueCommaKeyword_13_3_0()); 
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1835:1: ( ',' )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1836:1: ','
                    {
                     before(grammarAccess.getPatternAccess().getContinueCommaKeyword_13_3_0()); 
                    match(input,18,FOLLOW_18_in_rule__Pattern__Group_134249); 
                     after(grammarAccess.getPatternAccess().getContinueCommaKeyword_13_3_0()); 

                    }

                     after(grammarAccess.getPatternAccess().getContinueCommaKeyword_13_3_0()); 

                    }


                    }
                    break;

            }

             after(grammarAccess.getPatternAccess().getContinueAssignment_13_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Pattern__Group_13


    // $ANTLR start rule__Element__Group
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1853:1: rule__Element__Group : ( 'element' ) ( ( ( ruleNameClass ) ) ) ( '{' ) ( ( ( rulePattern ) )* ) ( '}' ) ( ( ( ( rule__Element__CardinalityAlternatives_5_0 ) ) )? ) ( ( ( ( rule__Element__ContinueAlternatives_6_0 ) ) )? ) ;
    public final void rule__Element__Group() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1857:1: ( ( 'element' ) ( ( ( ruleNameClass ) ) ) ( '{' ) ( ( ( rulePattern ) )* ) ( '}' ) ( ( ( ( rule__Element__CardinalityAlternatives_5_0 ) ) )? ) ( ( ( ( rule__Element__ContinueAlternatives_6_0 ) ) )? ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1858:1: ( 'element' ) ( ( ( ruleNameClass ) ) ) ( '{' ) ( ( ( rulePattern ) )* ) ( '}' ) ( ( ( ( rule__Element__CardinalityAlternatives_5_0 ) ) )? ) ( ( ( ( rule__Element__ContinueAlternatives_6_0 ) ) )? )
            {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1858:1: ( 'element' )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1859:1: 'element'
            {
             before(grammarAccess.getElementAccess().getElementKeyword_0()); 
            match(input,29,FOLLOW_29_in_rule__Element__Group4296); 
             after(grammarAccess.getElementAccess().getElementKeyword_0()); 

            }

            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1864:1: ( ( ( ruleNameClass ) ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1865:1: ( ( ruleNameClass ) )
            {
             before(grammarAccess.getElementAccess().getNameAssignment_1()); 
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1866:1: ( ( ruleNameClass ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1867:1: ( ruleNameClass )
            {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1867:1: ( ruleNameClass )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1868:1: ruleNameClass
            {
             before(grammarAccess.getElementAccess().getNameNameClassParserRuleCall_1_0()); 
            pushFollow(FOLLOW_ruleNameClass_in_rule__Element__Group4315);
            ruleNameClass();
            _fsp--;

             after(grammarAccess.getElementAccess().getNameNameClassParserRuleCall_1_0()); 

            }


            }

             after(grammarAccess.getElementAccess().getNameAssignment_1()); 

            }

            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1874:1: ( '{' )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1875:1: '{'
            {
             before(grammarAccess.getElementAccess().getLeftCurlyBracketKeyword_2()); 
            match(input,39,FOLLOW_39_in_rule__Element__Group4333); 
             after(grammarAccess.getElementAccess().getLeftCurlyBracketKeyword_2()); 

            }

            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1880:1: ( ( ( rulePattern ) )* )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1881:1: ( ( rulePattern ) )*
            {
             before(grammarAccess.getElementAccess().getPatternAssignment_3()); 
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1882:1: ( ( rulePattern ) )*
            loop33:
            do {
                int alt33=2;
                int LA33_0 = input.LA(1);

                if ( ((LA33_0>=RULE_ID && LA33_0<=RULE_STRING)||(LA33_0>=12 && LA33_0<=15)||(LA33_0>=23 && LA33_0<=25)||(LA33_0>=29 && LA33_0<=31)||(LA33_0>=34 && LA33_0<=35)||LA33_0==37||LA33_0==41) ) {
                    alt33=1;
                }


                switch (alt33) {
            	case 1 :
            	    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1883:1: ( rulePattern )
            	    {
            	    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1883:1: ( rulePattern )
            	    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1884:1: rulePattern
            	    {
            	     before(grammarAccess.getElementAccess().getPatternPatternParserRuleCall_3_0()); 
            	    pushFollow(FOLLOW_rulePattern_in_rule__Element__Group4352);
            	    rulePattern();
            	    _fsp--;

            	     after(grammarAccess.getElementAccess().getPatternPatternParserRuleCall_3_0()); 

            	    }


            	    }
            	    break;

            	default :
            	    break loop33;
                }
            } while (true);

             after(grammarAccess.getElementAccess().getPatternAssignment_3()); 

            }

            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1890:1: ( '}' )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1891:1: '}'
            {
             before(grammarAccess.getElementAccess().getRightCurlyBracketKeyword_4()); 
            match(input,40,FOLLOW_40_in_rule__Element__Group4371); 
             after(grammarAccess.getElementAccess().getRightCurlyBracketKeyword_4()); 

            }

            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1896:1: ( ( ( ( rule__Element__CardinalityAlternatives_5_0 ) ) )? )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1897:1: ( ( ( rule__Element__CardinalityAlternatives_5_0 ) ) )?
            {
             before(grammarAccess.getElementAccess().getCardinalityAssignment_5()); 
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1898:1: ( ( ( rule__Element__CardinalityAlternatives_5_0 ) ) )?
            int alt34=2;
            int LA34_0 = input.LA(1);

            if ( (LA34_0==11||(LA34_0>=16 && LA34_0<=17)) ) {
                alt34=1;
            }
            switch (alt34) {
                case 1 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1899:1: ( ( rule__Element__CardinalityAlternatives_5_0 ) )
                    {
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1899:1: ( ( rule__Element__CardinalityAlternatives_5_0 ) )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1900:1: ( rule__Element__CardinalityAlternatives_5_0 )
                    {
                     before(grammarAccess.getElementAccess().getCardinalityAlternatives_5_0()); 
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1901:1: ( rule__Element__CardinalityAlternatives_5_0 )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1901:2: rule__Element__CardinalityAlternatives_5_0
                    {
                    pushFollow(FOLLOW_rule__Element__CardinalityAlternatives_5_0_in_rule__Element__Group4390);
                    rule__Element__CardinalityAlternatives_5_0();
                    _fsp--;


                    }

                     after(grammarAccess.getElementAccess().getCardinalityAlternatives_5_0()); 

                    }


                    }
                    break;

            }

             after(grammarAccess.getElementAccess().getCardinalityAssignment_5()); 

            }

            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1907:1: ( ( ( ( rule__Element__ContinueAlternatives_6_0 ) ) )? )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1908:1: ( ( ( rule__Element__ContinueAlternatives_6_0 ) ) )?
            {
             before(grammarAccess.getElementAccess().getContinueAssignment_6()); 
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1909:1: ( ( ( rule__Element__ContinueAlternatives_6_0 ) ) )?
            int alt35=2;
            int LA35_0 = input.LA(1);

            if ( ((LA35_0>=18 && LA35_0<=19)) ) {
                alt35=1;
            }
            else if ( (LA35_0==14) ) {
                alt35=1;
            }
            switch (alt35) {
                case 1 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1910:1: ( ( rule__Element__ContinueAlternatives_6_0 ) )
                    {
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1910:1: ( ( rule__Element__ContinueAlternatives_6_0 ) )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1911:1: ( rule__Element__ContinueAlternatives_6_0 )
                    {
                     before(grammarAccess.getElementAccess().getContinueAlternatives_6_0()); 
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1912:1: ( rule__Element__ContinueAlternatives_6_0 )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1912:2: rule__Element__ContinueAlternatives_6_0
                    {
                    pushFollow(FOLLOW_rule__Element__ContinueAlternatives_6_0_in_rule__Element__Group4417);
                    rule__Element__ContinueAlternatives_6_0();
                    _fsp--;


                    }

                     after(grammarAccess.getElementAccess().getContinueAlternatives_6_0()); 

                    }


                    }
                    break;

            }

             after(grammarAccess.getElementAccess().getContinueAssignment_6()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Element__Group


    // $ANTLR start rule__Attribute__Group
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1924:1: rule__Attribute__Group : ( 'attribute' ) ( ( ( ruleNameClass ) ) ) ( '{' ) ( ( ( rulePattern ) )* ) ( '}' ) ( ( ( ( '?' ) ) )? ) ( ( ( ( rule__Attribute__ContinueAlternatives_6_0 ) ) )? ) ;
    public final void rule__Attribute__Group() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1928:1: ( ( 'attribute' ) ( ( ( ruleNameClass ) ) ) ( '{' ) ( ( ( rulePattern ) )* ) ( '}' ) ( ( ( ( '?' ) ) )? ) ( ( ( ( rule__Attribute__ContinueAlternatives_6_0 ) ) )? ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1929:1: ( 'attribute' ) ( ( ( ruleNameClass ) ) ) ( '{' ) ( ( ( rulePattern ) )* ) ( '}' ) ( ( ( ( '?' ) ) )? ) ( ( ( ( rule__Attribute__ContinueAlternatives_6_0 ) ) )? )
            {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1929:1: ( 'attribute' )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1930:1: 'attribute'
            {
             before(grammarAccess.getAttributeAccess().getAttributeKeyword_0()); 
            match(input,25,FOLLOW_25_in_rule__Attribute__Group4459); 
             after(grammarAccess.getAttributeAccess().getAttributeKeyword_0()); 

            }

            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1935:1: ( ( ( ruleNameClass ) ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1936:1: ( ( ruleNameClass ) )
            {
             before(grammarAccess.getAttributeAccess().getNameAssignment_1()); 
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1937:1: ( ( ruleNameClass ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1938:1: ( ruleNameClass )
            {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1938:1: ( ruleNameClass )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1939:1: ruleNameClass
            {
             before(grammarAccess.getAttributeAccess().getNameNameClassParserRuleCall_1_0()); 
            pushFollow(FOLLOW_ruleNameClass_in_rule__Attribute__Group4478);
            ruleNameClass();
            _fsp--;

             after(grammarAccess.getAttributeAccess().getNameNameClassParserRuleCall_1_0()); 

            }


            }

             after(grammarAccess.getAttributeAccess().getNameAssignment_1()); 

            }

            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1945:1: ( '{' )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1946:1: '{'
            {
             before(grammarAccess.getAttributeAccess().getLeftCurlyBracketKeyword_2()); 
            match(input,39,FOLLOW_39_in_rule__Attribute__Group4496); 
             after(grammarAccess.getAttributeAccess().getLeftCurlyBracketKeyword_2()); 

            }

            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1951:1: ( ( ( rulePattern ) )* )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1952:1: ( ( rulePattern ) )*
            {
             before(grammarAccess.getAttributeAccess().getPatternAssignment_3()); 
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1953:1: ( ( rulePattern ) )*
            loop36:
            do {
                int alt36=2;
                int LA36_0 = input.LA(1);

                if ( ((LA36_0>=RULE_ID && LA36_0<=RULE_STRING)||(LA36_0>=12 && LA36_0<=15)||(LA36_0>=23 && LA36_0<=25)||(LA36_0>=29 && LA36_0<=31)||(LA36_0>=34 && LA36_0<=35)||LA36_0==37||LA36_0==41) ) {
                    alt36=1;
                }


                switch (alt36) {
            	case 1 :
            	    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1954:1: ( rulePattern )
            	    {
            	    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1954:1: ( rulePattern )
            	    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1955:1: rulePattern
            	    {
            	     before(grammarAccess.getAttributeAccess().getPatternPatternParserRuleCall_3_0()); 
            	    pushFollow(FOLLOW_rulePattern_in_rule__Attribute__Group4515);
            	    rulePattern();
            	    _fsp--;

            	     after(grammarAccess.getAttributeAccess().getPatternPatternParserRuleCall_3_0()); 

            	    }


            	    }
            	    break;

            	default :
            	    break loop36;
                }
            } while (true);

             after(grammarAccess.getAttributeAccess().getPatternAssignment_3()); 

            }

            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1961:1: ( '}' )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1962:1: '}'
            {
             before(grammarAccess.getAttributeAccess().getRightCurlyBracketKeyword_4()); 
            match(input,40,FOLLOW_40_in_rule__Attribute__Group4534); 
             after(grammarAccess.getAttributeAccess().getRightCurlyBracketKeyword_4()); 

            }

            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1967:1: ( ( ( ( '?' ) ) )? )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1968:1: ( ( ( '?' ) ) )?
            {
             before(grammarAccess.getAttributeAccess().getCardinalityAssignment_5()); 
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1969:1: ( ( ( '?' ) ) )?
            int alt37=2;
            int LA37_0 = input.LA(1);

            if ( (LA37_0==16) ) {
                alt37=1;
            }
            switch (alt37) {
                case 1 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1970:1: ( ( '?' ) )
                    {
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1970:1: ( ( '?' ) )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1971:1: ( '?' )
                    {
                     before(grammarAccess.getAttributeAccess().getCardinalityQuestionMarkKeyword_5_0()); 
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1972:1: ( '?' )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1973:1: '?'
                    {
                     before(grammarAccess.getAttributeAccess().getCardinalityQuestionMarkKeyword_5_0()); 
                    match(input,16,FOLLOW_16_in_rule__Attribute__Group4558); 
                     after(grammarAccess.getAttributeAccess().getCardinalityQuestionMarkKeyword_5_0()); 

                    }

                     after(grammarAccess.getAttributeAccess().getCardinalityQuestionMarkKeyword_5_0()); 

                    }


                    }
                    break;

            }

             after(grammarAccess.getAttributeAccess().getCardinalityAssignment_5()); 

            }

            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1984:1: ( ( ( ( rule__Attribute__ContinueAlternatives_6_0 ) ) )? )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1985:1: ( ( ( rule__Attribute__ContinueAlternatives_6_0 ) ) )?
            {
             before(grammarAccess.getAttributeAccess().getContinueAssignment_6()); 
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1986:1: ( ( ( rule__Attribute__ContinueAlternatives_6_0 ) ) )?
            int alt38=2;
            int LA38_0 = input.LA(1);

            if ( (LA38_0==18) ) {
                alt38=1;
            }
            else if ( (LA38_0==14) ) {
                alt38=1;
            }
            switch (alt38) {
                case 1 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1987:1: ( ( rule__Attribute__ContinueAlternatives_6_0 ) )
                    {
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1987:1: ( ( rule__Attribute__ContinueAlternatives_6_0 ) )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1988:1: ( rule__Attribute__ContinueAlternatives_6_0 )
                    {
                     before(grammarAccess.getAttributeAccess().getContinueAlternatives_6_0()); 
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1989:1: ( rule__Attribute__ContinueAlternatives_6_0 )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1989:2: rule__Attribute__ContinueAlternatives_6_0
                    {
                    pushFollow(FOLLOW_rule__Attribute__ContinueAlternatives_6_0_in_rule__Attribute__Group4590);
                    rule__Attribute__ContinueAlternatives_6_0();
                    _fsp--;


                    }

                     after(grammarAccess.getAttributeAccess().getContinueAlternatives_6_0()); 

                    }


                    }
                    break;

            }

             after(grammarAccess.getAttributeAccess().getContinueAssignment_6()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Attribute__Group


    // $ANTLR start rule__Param__Group
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2001:1: rule__Param__Group : ( ruleIdentifierOrKeyWord ) ( '=' ) ( ( ( ruleLiteral ) ) ) ;
    public final void rule__Param__Group() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2005:1: ( ( ruleIdentifierOrKeyWord ) ( '=' ) ( ( ( ruleLiteral ) ) ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2006:1: ( ruleIdentifierOrKeyWord ) ( '=' ) ( ( ( ruleLiteral ) ) )
            {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2006:1: ( ruleIdentifierOrKeyWord )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2007:1: ruleIdentifierOrKeyWord
            {
             before(grammarAccess.getParamAccess().getIdentifierOrKeyWordParserRuleCall_0()); 
            pushFollow(FOLLOW_ruleIdentifierOrKeyWord_in_rule__Param__Group4631);
            ruleIdentifierOrKeyWord();
            _fsp--;

             after(grammarAccess.getParamAccess().getIdentifierOrKeyWordParserRuleCall_0()); 

            }

            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2010:1: ( '=' )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2011:1: '='
            {
             before(grammarAccess.getParamAccess().getEqualsSignKeyword_1()); 
            match(input,20,FOLLOW_20_in_rule__Param__Group4642); 
             after(grammarAccess.getParamAccess().getEqualsSignKeyword_1()); 

            }

            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2016:1: ( ( ( ruleLiteral ) ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2017:1: ( ( ruleLiteral ) )
            {
             before(grammarAccess.getParamAccess().getParmValueAssignment_2()); 
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2018:1: ( ( ruleLiteral ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2019:1: ( ruleLiteral )
            {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2019:1: ( ruleLiteral )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2020:1: ruleLiteral
            {
             before(grammarAccess.getParamAccess().getParmValueLiteralParserRuleCall_2_0()); 
            pushFollow(FOLLOW_ruleLiteral_in_rule__Param__Group4661);
            ruleLiteral();
            _fsp--;

             after(grammarAccess.getParamAccess().getParmValueLiteralParserRuleCall_2_0()); 

            }


            }

             after(grammarAccess.getParamAccess().getParmValueAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Param__Group


    // $ANTLR start rule__ExceptPattern__Group
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2032:1: rule__ExceptPattern__Group : ( '-' ) ( rulePattern ) ;
    public final void rule__ExceptPattern__Group() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2036:1: ( ( '-' ) ( rulePattern ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2037:1: ( '-' ) ( rulePattern )
            {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2037:1: ( '-' )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2038:1: '-'
            {
             before(grammarAccess.getExceptPatternAccess().getHyphenMinusKeyword_0()); 
            match(input,43,FOLLOW_43_in_rule__ExceptPattern__Group4700); 
             after(grammarAccess.getExceptPatternAccess().getHyphenMinusKeyword_0()); 

            }

            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2043:1: ( rulePattern )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2044:1: rulePattern
            {
             before(grammarAccess.getExceptPatternAccess().getPatternParserRuleCall_1()); 
            pushFollow(FOLLOW_rulePattern_in_rule__ExceptPattern__Group4712);
            rulePattern();
            _fsp--;

             after(grammarAccess.getExceptPatternAccess().getPatternParserRuleCall_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__ExceptPattern__Group


    // $ANTLR start rule__GrammarContent__Group_2
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2053:1: rule__GrammarContent__Group_2 : ( 'div' ) ( '{' ) ( ( ( ruleGrammarContent ) )* ) ( '}' ) ;
    public final void rule__GrammarContent__Group_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2057:1: ( ( 'div' ) ( '{' ) ( ( ( ruleGrammarContent ) )* ) ( '}' ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2058:1: ( 'div' ) ( '{' ) ( ( ( ruleGrammarContent ) )* ) ( '}' )
            {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2058:1: ( 'div' )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2059:1: 'div'
            {
             before(grammarAccess.getGrammarContentAccess().getDivKeyword_2_0()); 
            match(input,28,FOLLOW_28_in_rule__GrammarContent__Group_24744); 
             after(grammarAccess.getGrammarContentAccess().getDivKeyword_2_0()); 

            }

            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2064:1: ( '{' )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2065:1: '{'
            {
             before(grammarAccess.getGrammarContentAccess().getLeftCurlyBracketKeyword_2_1()); 
            match(input,39,FOLLOW_39_in_rule__GrammarContent__Group_24757); 
             after(grammarAccess.getGrammarContentAccess().getLeftCurlyBracketKeyword_2_1()); 

            }

            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2070:1: ( ( ( ruleGrammarContent ) )* )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2071:1: ( ( ruleGrammarContent ) )*
            {
             before(grammarAccess.getGrammarContentAccess().getGrammarContenGrammarContentAssignment_2_2()); 
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2072:1: ( ( ruleGrammarContent ) )*
            loop39:
            do {
                int alt39=2;
                int LA39_0 = input.LA(1);

                if ( (LA39_0==RULE_ID||LA39_0==28||LA39_0==32||LA39_0==38) ) {
                    alt39=1;
                }


                switch (alt39) {
            	case 1 :
            	    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2073:1: ( ruleGrammarContent )
            	    {
            	    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2073:1: ( ruleGrammarContent )
            	    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2074:1: ruleGrammarContent
            	    {
            	     before(grammarAccess.getGrammarContentAccess().getGrammarContenGrammarContentGrammarContentParserRuleCall_2_2_0()); 
            	    pushFollow(FOLLOW_ruleGrammarContent_in_rule__GrammarContent__Group_24776);
            	    ruleGrammarContent();
            	    _fsp--;

            	     after(grammarAccess.getGrammarContentAccess().getGrammarContenGrammarContentGrammarContentParserRuleCall_2_2_0()); 

            	    }


            	    }
            	    break;

            	default :
            	    break loop39;
                }
            } while (true);

             after(grammarAccess.getGrammarContentAccess().getGrammarContenGrammarContentAssignment_2_2()); 

            }

            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2080:1: ( '}' )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2081:1: '}'
            {
             before(grammarAccess.getGrammarContentAccess().getRightCurlyBracketKeyword_2_3()); 
            match(input,40,FOLLOW_40_in_rule__GrammarContent__Group_24795); 
             after(grammarAccess.getGrammarContentAccess().getRightCurlyBracketKeyword_2_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__GrammarContent__Group_2


    // $ANTLR start rule__GrammarContent__Group_3
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2092:1: rule__GrammarContent__Group_3 : ( 'include' ) ( ruleAnyURILiteral ) ( ( ( ruleInherit ) ) ) ( ( rule__GrammarContent__Group_3_3 ) ) ;
    public final void rule__GrammarContent__Group_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2096:1: ( ( 'include' ) ( ruleAnyURILiteral ) ( ( ( ruleInherit ) ) ) ( ( rule__GrammarContent__Group_3_3 ) ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2097:1: ( 'include' ) ( ruleAnyURILiteral ) ( ( ( ruleInherit ) ) ) ( ( rule__GrammarContent__Group_3_3 ) )
            {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2097:1: ( 'include' )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2098:1: 'include'
            {
             before(grammarAccess.getGrammarContentAccess().getIncludeKeyword_3_0()); 
            match(input,32,FOLLOW_32_in_rule__GrammarContent__Group_34829); 
             after(grammarAccess.getGrammarContentAccess().getIncludeKeyword_3_0()); 

            }

            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2103:1: ( ruleAnyURILiteral )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2104:1: ruleAnyURILiteral
            {
             before(grammarAccess.getGrammarContentAccess().getAnyURILiteralParserRuleCall_3_1()); 
            pushFollow(FOLLOW_ruleAnyURILiteral_in_rule__GrammarContent__Group_34841);
            ruleAnyURILiteral();
            _fsp--;

             after(grammarAccess.getGrammarContentAccess().getAnyURILiteralParserRuleCall_3_1()); 

            }

            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2107:1: ( ( ( ruleInherit ) ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2108:1: ( ( ruleInherit ) )
            {
             before(grammarAccess.getGrammarContentAccess().getInheritAssignment_3_2()); 
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2109:1: ( ( ruleInherit ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2110:1: ( ruleInherit )
            {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2110:1: ( ruleInherit )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2111:1: ruleInherit
            {
             before(grammarAccess.getGrammarContentAccess().getInheritInheritParserRuleCall_3_2_0()); 
            pushFollow(FOLLOW_ruleInherit_in_rule__GrammarContent__Group_34858);
            ruleInherit();
            _fsp--;

             after(grammarAccess.getGrammarContentAccess().getInheritInheritParserRuleCall_3_2_0()); 

            }


            }

             after(grammarAccess.getGrammarContentAccess().getInheritAssignment_3_2()); 

            }

            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2117:1: ( ( rule__GrammarContent__Group_3_3 ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2118:1: ( rule__GrammarContent__Group_3_3 )
            {
             before(grammarAccess.getGrammarContentAccess().getGroup_3_3()); 
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2119:1: ( rule__GrammarContent__Group_3_3 )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2119:2: rule__GrammarContent__Group_3_3
            {
            pushFollow(FOLLOW_rule__GrammarContent__Group_3_3_in_rule__GrammarContent__Group_34875);
            rule__GrammarContent__Group_3_3();
            _fsp--;


            }

             after(grammarAccess.getGrammarContentAccess().getGroup_3_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__GrammarContent__Group_3


    // $ANTLR start rule__GrammarContent__Group_3_3
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2127:1: rule__GrammarContent__Group_3_3 : ( '{' ) ( ( ( ruleIncludeContent ) )* ) ( '}' ) ;
    public final void rule__GrammarContent__Group_3_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2131:1: ( ( '{' ) ( ( ( ruleIncludeContent ) )* ) ( '}' ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2132:1: ( '{' ) ( ( ( ruleIncludeContent ) )* ) ( '}' )
            {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2132:1: ( '{' )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2133:1: '{'
            {
             before(grammarAccess.getGrammarContentAccess().getLeftCurlyBracketKeyword_3_3_0()); 
            match(input,39,FOLLOW_39_in_rule__GrammarContent__Group_3_34908); 
             after(grammarAccess.getGrammarContentAccess().getLeftCurlyBracketKeyword_3_3_0()); 

            }

            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2138:1: ( ( ( ruleIncludeContent ) )* )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2139:1: ( ( ruleIncludeContent ) )*
            {
             before(grammarAccess.getGrammarContentAccess().getIncludeContentAssignment_3_3_1()); 
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2140:1: ( ( ruleIncludeContent ) )*
            loop40:
            do {
                int alt40=2;
                int LA40_0 = input.LA(1);

                if ( (LA40_0==RULE_ID||LA40_0==28||LA40_0==38) ) {
                    alt40=1;
                }


                switch (alt40) {
            	case 1 :
            	    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2141:1: ( ruleIncludeContent )
            	    {
            	    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2141:1: ( ruleIncludeContent )
            	    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2142:1: ruleIncludeContent
            	    {
            	     before(grammarAccess.getGrammarContentAccess().getIncludeContentIncludeContentParserRuleCall_3_3_1_0()); 
            	    pushFollow(FOLLOW_ruleIncludeContent_in_rule__GrammarContent__Group_3_34927);
            	    ruleIncludeContent();
            	    _fsp--;

            	     after(grammarAccess.getGrammarContentAccess().getIncludeContentIncludeContentParserRuleCall_3_3_1_0()); 

            	    }


            	    }
            	    break;

            	default :
            	    break loop40;
                }
            } while (true);

             after(grammarAccess.getGrammarContentAccess().getIncludeContentAssignment_3_3_1()); 

            }

            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2148:1: ( '}' )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2149:1: '}'
            {
             before(grammarAccess.getGrammarContentAccess().getRightCurlyBracketKeyword_3_3_2()); 
            match(input,40,FOLLOW_40_in_rule__GrammarContent__Group_3_34946); 
             after(grammarAccess.getGrammarContentAccess().getRightCurlyBracketKeyword_3_3_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__GrammarContent__Group_3_3


    // $ANTLR start rule__IncludeContent__Group_2
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2160:1: rule__IncludeContent__Group_2 : ( 'div' ) ( '{' ) ( ( ( ruleGrammarContent ) )* ) ( '}' ) ;
    public final void rule__IncludeContent__Group_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2164:1: ( ( 'div' ) ( '{' ) ( ( ( ruleGrammarContent ) )* ) ( '}' ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2165:1: ( 'div' ) ( '{' ) ( ( ( ruleGrammarContent ) )* ) ( '}' )
            {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2165:1: ( 'div' )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2166:1: 'div'
            {
             before(grammarAccess.getIncludeContentAccess().getDivKeyword_2_0()); 
            match(input,28,FOLLOW_28_in_rule__IncludeContent__Group_24980); 
             after(grammarAccess.getIncludeContentAccess().getDivKeyword_2_0()); 

            }

            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2171:1: ( '{' )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2172:1: '{'
            {
             before(grammarAccess.getIncludeContentAccess().getLeftCurlyBracketKeyword_2_1()); 
            match(input,39,FOLLOW_39_in_rule__IncludeContent__Group_24993); 
             after(grammarAccess.getIncludeContentAccess().getLeftCurlyBracketKeyword_2_1()); 

            }

            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2177:1: ( ( ( ruleGrammarContent ) )* )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2178:1: ( ( ruleGrammarContent ) )*
            {
             before(grammarAccess.getIncludeContentAccess().getIncludeGrammarContentAssignment_2_2()); 
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2179:1: ( ( ruleGrammarContent ) )*
            loop41:
            do {
                int alt41=2;
                int LA41_0 = input.LA(1);

                if ( (LA41_0==RULE_ID||LA41_0==28||LA41_0==32||LA41_0==38) ) {
                    alt41=1;
                }


                switch (alt41) {
            	case 1 :
            	    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2180:1: ( ruleGrammarContent )
            	    {
            	    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2180:1: ( ruleGrammarContent )
            	    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2181:1: ruleGrammarContent
            	    {
            	     before(grammarAccess.getIncludeContentAccess().getIncludeGrammarContentGrammarContentParserRuleCall_2_2_0()); 
            	    pushFollow(FOLLOW_ruleGrammarContent_in_rule__IncludeContent__Group_25012);
            	    ruleGrammarContent();
            	    _fsp--;

            	     after(grammarAccess.getIncludeContentAccess().getIncludeGrammarContentGrammarContentParserRuleCall_2_2_0()); 

            	    }


            	    }
            	    break;

            	default :
            	    break loop41;
                }
            } while (true);

             after(grammarAccess.getIncludeContentAccess().getIncludeGrammarContentAssignment_2_2()); 

            }

            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2187:1: ( '}' )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2188:1: '}'
            {
             before(grammarAccess.getIncludeContentAccess().getRightCurlyBracketKeyword_2_3()); 
            match(input,40,FOLLOW_40_in_rule__IncludeContent__Group_25031); 
             after(grammarAccess.getIncludeContentAccess().getRightCurlyBracketKeyword_2_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__IncludeContent__Group_2


    // $ANTLR start rule__Start__Group
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2199:1: rule__Start__Group : ( 'start' ) ( ( rule__Start__Alternatives_1 ) ) ( ( rule__Start__Alternatives_2 ) ) ;
    public final void rule__Start__Group() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2203:1: ( ( 'start' ) ( ( rule__Start__Alternatives_1 ) ) ( ( rule__Start__Alternatives_2 ) ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2204:1: ( 'start' ) ( ( rule__Start__Alternatives_1 ) ) ( ( rule__Start__Alternatives_2 ) )
            {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2204:1: ( 'start' )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2205:1: 'start'
            {
             before(grammarAccess.getStartAccess().getStartKeyword_0()); 
            match(input,38,FOLLOW_38_in_rule__Start__Group5065); 
             after(grammarAccess.getStartAccess().getStartKeyword_0()); 

            }

            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2210:1: ( ( rule__Start__Alternatives_1 ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2211:1: ( rule__Start__Alternatives_1 )
            {
             before(grammarAccess.getStartAccess().getAlternatives_1()); 
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2212:1: ( rule__Start__Alternatives_1 )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2212:2: rule__Start__Alternatives_1
            {
            pushFollow(FOLLOW_rule__Start__Alternatives_1_in_rule__Start__Group5077);
            rule__Start__Alternatives_1();
            _fsp--;


            }

             after(grammarAccess.getStartAccess().getAlternatives_1()); 

            }

            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2214:1: ( ( rule__Start__Alternatives_2 ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2215:1: ( rule__Start__Alternatives_2 )
            {
             before(grammarAccess.getStartAccess().getAlternatives_2()); 
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2216:1: ( rule__Start__Alternatives_2 )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2216:2: rule__Start__Alternatives_2
            {
            pushFollow(FOLLOW_rule__Start__Alternatives_2_in_rule__Start__Group5088);
            rule__Start__Alternatives_2();
            _fsp--;


            }

             after(grammarAccess.getStartAccess().getAlternatives_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Start__Group


    // $ANTLR start rule__Define__Group
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2224:1: rule__Define__Group : ( RULE_ID ) ( ( rule__Define__Alternatives_1 ) ) ( ( ( rulePattern ) ) ) ;
    public final void rule__Define__Group() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2228:1: ( ( RULE_ID ) ( ( rule__Define__Alternatives_1 ) ) ( ( ( rulePattern ) ) ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2229:1: ( RULE_ID ) ( ( rule__Define__Alternatives_1 ) ) ( ( ( rulePattern ) ) )
            {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2229:1: ( RULE_ID )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2230:1: RULE_ID
            {
             before(grammarAccess.getDefineAccess().getIDTerminalRuleCall_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__Define__Group5120); 
             after(grammarAccess.getDefineAccess().getIDTerminalRuleCall_0()); 

            }

            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2233:1: ( ( rule__Define__Alternatives_1 ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2234:1: ( rule__Define__Alternatives_1 )
            {
             before(grammarAccess.getDefineAccess().getAlternatives_1()); 
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2235:1: ( rule__Define__Alternatives_1 )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2235:2: rule__Define__Alternatives_1
            {
            pushFollow(FOLLOW_rule__Define__Alternatives_1_in_rule__Define__Group5130);
            rule__Define__Alternatives_1();
            _fsp--;


            }

             after(grammarAccess.getDefineAccess().getAlternatives_1()); 

            }

            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2237:1: ( ( ( rulePattern ) ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2238:1: ( ( rulePattern ) )
            {
             before(grammarAccess.getDefineAccess().getPatternAssignment_2()); 
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2239:1: ( ( rulePattern ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2240:1: ( rulePattern )
            {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2240:1: ( rulePattern )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2241:1: rulePattern
            {
             before(grammarAccess.getDefineAccess().getPatternPatternParserRuleCall_2_0()); 
            pushFollow(FOLLOW_rulePattern_in_rule__Define__Group5148);
            rulePattern();
            _fsp--;

             after(grammarAccess.getDefineAccess().getPatternPatternParserRuleCall_2_0()); 

            }


            }

             after(grammarAccess.getDefineAccess().getPatternAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Define__Group


    // $ANTLR start rule__ExceptNameClass__Group
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2253:1: rule__ExceptNameClass__Group : ( '-' ) ( ruleNameClass ) ;
    public final void rule__ExceptNameClass__Group() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2257:1: ( ( '-' ) ( ruleNameClass ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2258:1: ( '-' ) ( ruleNameClass )
            {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2258:1: ( '-' )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2259:1: '-'
            {
             before(grammarAccess.getExceptNameClassAccess().getHyphenMinusKeyword_0()); 
            match(input,43,FOLLOW_43_in_rule__ExceptNameClass__Group5187); 
             after(grammarAccess.getExceptNameClassAccess().getHyphenMinusKeyword_0()); 

            }

            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2264:1: ( ruleNameClass )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2265:1: ruleNameClass
            {
             before(grammarAccess.getExceptNameClassAccess().getNameClassParserRuleCall_1()); 
            pushFollow(FOLLOW_ruleNameClass_in_rule__ExceptNameClass__Group5199);
            ruleNameClass();
            _fsp--;

             after(grammarAccess.getExceptNameClassAccess().getNameClassParserRuleCall_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__ExceptNameClass__Group


    // $ANTLR start rule__NameClass__Group_1
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2274:1: rule__NameClass__Group_1 : ( RULE_ID ) ( ( ( ruleExceptNameClass ) )? ) ;
    public final void rule__NameClass__Group_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2278:1: ( ( RULE_ID ) ( ( ( ruleExceptNameClass ) )? ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2279:1: ( RULE_ID ) ( ( ( ruleExceptNameClass ) )? )
            {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2279:1: ( RULE_ID )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2280:1: RULE_ID
            {
             before(grammarAccess.getNameClassAccess().getIDTerminalRuleCall_1_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__NameClass__Group_15230); 
             after(grammarAccess.getNameClassAccess().getIDTerminalRuleCall_1_0()); 

            }

            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2283:1: ( ( ( ruleExceptNameClass ) )? )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2284:1: ( ( ruleExceptNameClass ) )?
            {
             before(grammarAccess.getNameClassAccess().getExceptClassNameAssignment_1_1()); 
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2285:1: ( ( ruleExceptNameClass ) )?
            int alt42=2;
            int LA42_0 = input.LA(1);

            if ( (LA42_0==43) ) {
                alt42=1;
            }
            switch (alt42) {
                case 1 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2286:1: ( ruleExceptNameClass )
                    {
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2286:1: ( ruleExceptNameClass )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2287:1: ruleExceptNameClass
                    {
                     before(grammarAccess.getNameClassAccess().getExceptClassNameExceptNameClassParserRuleCall_1_1_0()); 
                    pushFollow(FOLLOW_ruleExceptNameClass_in_rule__NameClass__Group_15247);
                    ruleExceptNameClass();
                    _fsp--;

                     after(grammarAccess.getNameClassAccess().getExceptClassNameExceptNameClassParserRuleCall_1_1_0()); 

                    }


                    }
                    break;

            }

             after(grammarAccess.getNameClassAccess().getExceptClassNameAssignment_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__NameClass__Group_1


    // $ANTLR start rule__NameClass__Group_2
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2299:1: rule__NameClass__Group_2 : ( ruleAnyName ) ( ( ( ruleExceptNameClass ) )? ) ;
    public final void rule__NameClass__Group_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2303:1: ( ( ruleAnyName ) ( ( ( ruleExceptNameClass ) )? ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2304:1: ( ruleAnyName ) ( ( ( ruleExceptNameClass ) )? )
            {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2304:1: ( ruleAnyName )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2305:1: ruleAnyName
            {
             before(grammarAccess.getNameClassAccess().getAnyNameParserRuleCall_2_0()); 
            pushFollow(FOLLOW_ruleAnyName_in_rule__NameClass__Group_25286);
            ruleAnyName();
            _fsp--;

             after(grammarAccess.getNameClassAccess().getAnyNameParserRuleCall_2_0()); 

            }

            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2308:1: ( ( ( ruleExceptNameClass ) )? )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2309:1: ( ( ruleExceptNameClass ) )?
            {
             before(grammarAccess.getNameClassAccess().getExceptClassNameAssignment_2_1()); 
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2310:1: ( ( ruleExceptNameClass ) )?
            int alt43=2;
            int LA43_0 = input.LA(1);

            if ( (LA43_0==43) ) {
                alt43=1;
            }
            switch (alt43) {
                case 1 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2311:1: ( ruleExceptNameClass )
                    {
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2311:1: ( ruleExceptNameClass )
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2312:1: ruleExceptNameClass
                    {
                     before(grammarAccess.getNameClassAccess().getExceptClassNameExceptNameClassParserRuleCall_2_1_0()); 
                    pushFollow(FOLLOW_ruleExceptNameClass_in_rule__NameClass__Group_25303);
                    ruleExceptNameClass();
                    _fsp--;

                     after(grammarAccess.getNameClassAccess().getExceptClassNameExceptNameClassParserRuleCall_2_1_0()); 

                    }


                    }
                    break;

            }

             after(grammarAccess.getNameClassAccess().getExceptClassNameAssignment_2_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__NameClass__Group_2


    // $ANTLR start rule__NameClass__Group_3
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2324:1: rule__NameClass__Group_3 : ( '|' ) ( ( ( ruleNameClass ) ) ) ;
    public final void rule__NameClass__Group_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2328:1: ( ( '|' ) ( ( ( ruleNameClass ) ) ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2329:1: ( '|' ) ( ( ( ruleNameClass ) ) )
            {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2329:1: ( '|' )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2330:1: '|'
            {
             before(grammarAccess.getNameClassAccess().getVerticalLineKeyword_3_0()); 
            match(input,14,FOLLOW_14_in_rule__NameClass__Group_35343); 
             after(grammarAccess.getNameClassAccess().getVerticalLineKeyword_3_0()); 

            }

            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2335:1: ( ( ( ruleNameClass ) ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2336:1: ( ( ruleNameClass ) )
            {
             before(grammarAccess.getNameClassAccess().getNcAssignment_3_1()); 
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2337:1: ( ( ruleNameClass ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2338:1: ( ruleNameClass )
            {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2338:1: ( ruleNameClass )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2339:1: ruleNameClass
            {
             before(grammarAccess.getNameClassAccess().getNcNameClassParserRuleCall_3_1_0()); 
            pushFollow(FOLLOW_ruleNameClass_in_rule__NameClass__Group_35362);
            ruleNameClass();
            _fsp--;

             after(grammarAccess.getNameClassAccess().getNcNameClassParserRuleCall_3_1_0()); 

            }


            }

             after(grammarAccess.getNameClassAccess().getNcAssignment_3_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__NameClass__Group_3


    // $ANTLR start rule__NameClass__Group_4
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2351:1: rule__NameClass__Group_4 : ( '(' ) ( ( ( ruleNameClass ) ) ) ( ')' ) ;
    public final void rule__NameClass__Group_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2355:1: ( ( '(' ) ( ( ( ruleNameClass ) ) ) ( ')' ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2356:1: ( '(' ) ( ( ( ruleNameClass ) ) ) ( ')' )
            {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2356:1: ( '(' )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2357:1: '('
            {
             before(grammarAccess.getNameClassAccess().getLeftParenthesisKeyword_4_0()); 
            match(input,41,FOLLOW_41_in_rule__NameClass__Group_45401); 
             after(grammarAccess.getNameClassAccess().getLeftParenthesisKeyword_4_0()); 

            }

            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2362:1: ( ( ( ruleNameClass ) ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2363:1: ( ( ruleNameClass ) )
            {
             before(grammarAccess.getNameClassAccess().getNcAssignment_4_1()); 
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2364:1: ( ( ruleNameClass ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2365:1: ( ruleNameClass )
            {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2365:1: ( ruleNameClass )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2366:1: ruleNameClass
            {
             before(grammarAccess.getNameClassAccess().getNcNameClassParserRuleCall_4_1_0()); 
            pushFollow(FOLLOW_ruleNameClass_in_rule__NameClass__Group_45420);
            ruleNameClass();
            _fsp--;

             after(grammarAccess.getNameClassAccess().getNcNameClassParserRuleCall_4_1_0()); 

            }


            }

             after(grammarAccess.getNameClassAccess().getNcAssignment_4_1()); 

            }

            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2372:1: ( ')' )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2373:1: ')'
            {
             before(grammarAccess.getNameClassAccess().getRightParenthesisKeyword_4_2()); 
            match(input,42,FOLLOW_42_in_rule__NameClass__Group_45438); 
             after(grammarAccess.getNameClassAccess().getRightParenthesisKeyword_4_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__NameClass__Group_4


    // $ANTLR start rule__Inherit__Group
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2384:1: rule__Inherit__Group : ( 'inherit' ) ( '=' ) ( ruleIdentifierOrKeyWord ) ;
    public final void rule__Inherit__Group() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2388:1: ( ( 'inherit' ) ( '=' ) ( ruleIdentifierOrKeyWord ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2389:1: ( 'inherit' ) ( '=' ) ( ruleIdentifierOrKeyWord )
            {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2389:1: ( 'inherit' )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2390:1: 'inherit'
            {
             before(grammarAccess.getInheritAccess().getInheritKeyword_0()); 
            match(input,33,FOLLOW_33_in_rule__Inherit__Group5472); 
             after(grammarAccess.getInheritAccess().getInheritKeyword_0()); 

            }

            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2395:1: ( '=' )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2396:1: '='
            {
             before(grammarAccess.getInheritAccess().getEqualsSignKeyword_1()); 
            match(input,20,FOLLOW_20_in_rule__Inherit__Group5485); 
             after(grammarAccess.getInheritAccess().getEqualsSignKeyword_1()); 

            }

            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2401:1: ( ruleIdentifierOrKeyWord )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2402:1: ruleIdentifierOrKeyWord
            {
             before(grammarAccess.getInheritAccess().getIdentifierOrKeyWordParserRuleCall_2()); 
            pushFollow(FOLLOW_ruleIdentifierOrKeyWord_in_rule__Inherit__Group5497);
            ruleIdentifierOrKeyWord();
            _fsp--;

             after(grammarAccess.getInheritAccess().getIdentifierOrKeyWordParserRuleCall_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Inherit__Group


    // $ANTLR start rule__Identifier__Group_0
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2411:1: rule__Identifier__Group_0 : ( RULE_ID ) ( ( ( ruleKeyWord ) ) ) ;
    public final void rule__Identifier__Group_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2415:1: ( ( RULE_ID ) ( ( ( ruleKeyWord ) ) ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2416:1: ( RULE_ID ) ( ( ( ruleKeyWord ) ) )
            {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2416:1: ( RULE_ID )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2417:1: RULE_ID
            {
             before(grammarAccess.getIdentifierAccess().getIDTerminalRuleCall_0_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__Identifier__Group_05528); 
             after(grammarAccess.getIdentifierAccess().getIDTerminalRuleCall_0_0()); 

            }

            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2420:1: ( ( ( ruleKeyWord ) ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2421:1: ( ( ruleKeyWord ) )
            {
             before(grammarAccess.getIdentifierAccess().getKeyWordAssignment_0_1()); 
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2422:1: ( ( ruleKeyWord ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2423:1: ( ruleKeyWord )
            {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2423:1: ( ruleKeyWord )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2424:1: ruleKeyWord
            {
             before(grammarAccess.getIdentifierAccess().getKeyWordKeyWordParserRuleCall_0_1_0()); 
            pushFollow(FOLLOW_ruleKeyWord_in_rule__Identifier__Group_05545);
            ruleKeyWord();
            _fsp--;

             after(grammarAccess.getIdentifierAccess().getKeyWordKeyWordParserRuleCall_0_1_0()); 

            }


            }

             after(grammarAccess.getIdentifierAccess().getKeyWordAssignment_0_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Identifier__Group_0


    // $ANTLR start rule__QuotedIdentifier__Group
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2436:1: rule__QuotedIdentifier__Group : ( '\\\\' ) ( RULE_ID ) ;
    public final void rule__QuotedIdentifier__Group() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2440:1: ( ( '\\\\' ) ( RULE_ID ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2441:1: ( '\\\\' ) ( RULE_ID )
            {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2441:1: ( '\\\\' )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2442:1: '\\\\'
            {
             before(grammarAccess.getQuotedIdentifierAccess().getReverseSolidusKeyword_0()); 
            match(input,44,FOLLOW_44_in_rule__QuotedIdentifier__Group5584); 
             after(grammarAccess.getQuotedIdentifierAccess().getReverseSolidusKeyword_0()); 

            }

            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2447:1: ( RULE_ID )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2448:1: RULE_ID
            {
             before(grammarAccess.getQuotedIdentifierAccess().getIDTerminalRuleCall_1()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__QuotedIdentifier__Group5596); 
             after(grammarAccess.getQuotedIdentifierAccess().getIDTerminalRuleCall_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__QuotedIdentifier__Group


    // $ANTLR start rule__CName__Group
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2457:1: rule__CName__Group : ( RULE_ID ) ( ':' ) ( RULE_ID ) ;
    public final void rule__CName__Group() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2461:1: ( ( RULE_ID ) ( ':' ) ( RULE_ID ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2462:1: ( RULE_ID ) ( ':' ) ( RULE_ID )
            {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2462:1: ( RULE_ID )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2463:1: RULE_ID
            {
             before(grammarAccess.getCNameAccess().getIDTerminalRuleCall_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__CName__Group5627); 
             after(grammarAccess.getCNameAccess().getIDTerminalRuleCall_0()); 

            }

            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2466:1: ( ':' )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2467:1: ':'
            {
             before(grammarAccess.getCNameAccess().getColonKeyword_1()); 
            match(input,45,FOLLOW_45_in_rule__CName__Group5638); 
             after(grammarAccess.getCNameAccess().getColonKeyword_1()); 

            }

            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2472:1: ( RULE_ID )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2473:1: RULE_ID
            {
             before(grammarAccess.getCNameAccess().getIDTerminalRuleCall_2()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__CName__Group5650); 
             after(grammarAccess.getCNameAccess().getIDTerminalRuleCall_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__CName__Group


    // $ANTLR start rule__Literal__Group
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2482:1: rule__Literal__Group : ( RULE_STRING ) ( ( rule__Literal__Group_1 )+ ) ;
    public final void rule__Literal__Group() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2486:1: ( ( RULE_STRING ) ( ( rule__Literal__Group_1 )+ ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2487:1: ( RULE_STRING ) ( ( rule__Literal__Group_1 )+ )
            {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2487:1: ( RULE_STRING )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2488:1: RULE_STRING
            {
             before(grammarAccess.getLiteralAccess().getSTRINGTerminalRuleCall_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__Literal__Group5681); 
             after(grammarAccess.getLiteralAccess().getSTRINGTerminalRuleCall_0()); 

            }

            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2491:1: ( ( rule__Literal__Group_1 )+ )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2492:1: ( rule__Literal__Group_1 )+
            {
             before(grammarAccess.getLiteralAccess().getGroup_1()); 
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2493:1: ( rule__Literal__Group_1 )+
            int cnt44=0;
            loop44:
            do {
                int alt44=2;
                int LA44_0 = input.LA(1);

                if ( (LA44_0==46) ) {
                    alt44=1;
                }


                switch (alt44) {
            	case 1 :
            	    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2493:2: rule__Literal__Group_1
            	    {
            	    pushFollow(FOLLOW_rule__Literal__Group_1_in_rule__Literal__Group5691);
            	    rule__Literal__Group_1();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    if ( cnt44 >= 1 ) break loop44;
                        EarlyExitException eee =
                            new EarlyExitException(44, input);
                        throw eee;
                }
                cnt44++;
            } while (true);

             after(grammarAccess.getLiteralAccess().getGroup_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Literal__Group


    // $ANTLR start rule__Literal__Group_1
    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2501:1: rule__Literal__Group_1 : ( '~' ) ( ( ( RULE_STRING ) ) ) ;
    public final void rule__Literal__Group_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2505:1: ( ( '~' ) ( ( ( RULE_STRING ) ) ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2506:1: ( '~' ) ( ( ( RULE_STRING ) ) )
            {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2506:1: ( '~' )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2507:1: '~'
            {
             before(grammarAccess.getLiteralAccess().getTildeKeyword_1_0()); 
            match(input,46,FOLLOW_46_in_rule__Literal__Group_15725); 
             after(grammarAccess.getLiteralAccess().getTildeKeyword_1_0()); 

            }

            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2512:1: ( ( ( RULE_STRING ) ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2513:1: ( ( RULE_STRING ) )
            {
             before(grammarAccess.getLiteralAccess().getLiteralSegmentAssignment_1_1()); 
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2514:1: ( ( RULE_STRING ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2515:1: ( RULE_STRING )
            {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2515:1: ( RULE_STRING )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2516:1: RULE_STRING
            {
             before(grammarAccess.getLiteralAccess().getLiteralSegmentSTRINGTerminalRuleCall_1_1_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__Literal__Group_15744); 
             after(grammarAccess.getLiteralAccess().getLiteralSegmentSTRINGTerminalRuleCall_1_1_0()); 

            }


            }

             after(grammarAccess.getLiteralAccess().getLiteralSegmentAssignment_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Literal__Group_1


 

    public static final BitSet FOLLOW_ruleTopLevel_in_entryRuleTopLevel60 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleTopLevel67 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TopLevel__Group_in_ruleTopLevel94 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDecl_in_entryRuleDecl119 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDecl126 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Decl__Alternatives_in_ruleDecl153 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePattern_in_entryRulePattern178 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePattern185 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Pattern__Alternatives_in_rulePattern212 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleElement_in_entryRuleElement237 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleElement244 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Element__Group_in_ruleElement271 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAttribute_in_entryRuleAttribute296 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAttribute303 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Attribute__Group_in_ruleAttribute330 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleParam_in_entryRuleParam355 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleParam362 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Param__Group_in_ruleParam389 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExceptPattern_in_entryRuleExceptPattern414 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExceptPattern421 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ExceptPattern__Group_in_ruleExceptPattern448 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleGrammarContent_in_entryRuleGrammarContent473 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleGrammarContent480 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__GrammarContent__Alternatives_in_ruleGrammarContent507 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIncludeContent_in_entryRuleIncludeContent532 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleIncludeContent539 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__IncludeContent__Alternatives_in_ruleIncludeContent566 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStart_in_entryRuleStart591 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleStart598 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Start__Group_in_ruleStart625 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDefine_in_entryRuleDefine650 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDefine657 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Define__Group_in_ruleDefine684 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleName_in_entryRuleName709 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleName716 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Name__Alternatives_in_ruleName743 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExceptNameClass_in_entryRuleExceptNameClass768 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExceptNameClass775 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ExceptNameClass__Group_in_ruleExceptNameClass802 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNameClass_in_entryRuleNameClass827 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleNameClass834 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NameClass__Alternatives_in_ruleNameClass861 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDataTypeName_in_entryRuleDataTypeName886 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDataTypeName893 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DataTypeName__Alternatives_in_ruleDataTypeName920 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDataTypeValue_in_entryRuleDataTypeValue945 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDataTypeValue952 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLiteral_in_ruleDataTypeValue979 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAnyURILiteral_in_entryRuleAnyURILiteral1003 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAnyURILiteral1010 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLiteral_in_ruleAnyURILiteral1037 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInherit_in_entryRuleInherit1063 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleInherit1070 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Inherit__Group_in_ruleInherit1097 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIdentifierOrKeyWord_in_entryRuleIdentifierOrKeyWord1122 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleIdentifierOrKeyWord1129 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__IdentifierOrKeyWord__Alternatives_in_ruleIdentifierOrKeyWord1156 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIdentifier_in_entryRuleIdentifier1181 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleIdentifier1188 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Identifier__Alternatives_in_ruleIdentifier1215 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQuotedIdentifier_in_entryRuleQuotedIdentifier1240 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleQuotedIdentifier1247 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__QuotedIdentifier__Group_in_ruleQuotedIdentifier1274 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCName_in_entryRuleCName1299 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleCName1306 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__CName__Group_in_ruleCName1333 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAnyName_in_entryRuleAnyName1358 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAnyName1365 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_11_in_ruleAnyName1393 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLiteral_in_entryRuleLiteral1419 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleLiteral1426 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Literal__Group_in_ruleLiteral1453 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleKeyWord_in_entryRuleKeyWord1478 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleKeyWord1485 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__KeyWord__Alternatives_in_ruleKeyWord1512 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePattern_in_rule__TopLevel__Alternatives_11554 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleGrammarContent_in_rule__TopLevel__Alternatives_11584 = new BitSet(new long[]{0x0000004110000012L});
    public static final BitSet FOLLOW_rule__Decl__Group_0_in_rule__Decl__Alternatives1623 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Decl__Group_1_in_rule__Decl__Alternatives1640 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Decl__Group_2_in_rule__Decl__Alternatives1657 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Pattern__ValAlternatives_0_0_in_rule__Pattern__Alternatives1696 = new BitSet(new long[]{0x0000000022000002L});
    public static final BitSet FOLLOW_rule__Pattern__Group_1_in_rule__Pattern__Alternatives1722 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Pattern__Group_2_in_rule__Pattern__Alternatives1739 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Pattern__Group_3_in_rule__Pattern__Alternatives1756 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Pattern__Group_4_in_rule__Pattern__Alternatives1773 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_rule__Pattern__Alternatives1791 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_rule__Pattern__Alternatives1810 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_rule__Pattern__Alternatives1829 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDataTypeValue_in_rule__Pattern__Alternatives1854 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_rule__Pattern__Group_9_in_rule__Pattern__Alternatives1878 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_rule__Pattern__Alternatives1896 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Pattern__Group_11_in_rule__Pattern__Alternatives1914 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Pattern__Group_12_in_rule__Pattern__Alternatives1931 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Pattern__Group_13_in_rule__Pattern__Alternatives1948 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleElement_in_rule__Pattern__ValAlternatives_0_01980 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAttribute_in_rule__Pattern__ValAlternatives_0_01996 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_rule__Element__CardinalityAlternatives_5_02028 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_rule__Element__CardinalityAlternatives_5_02047 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_11_in_rule__Element__CardinalityAlternatives_5_02066 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_rule__Element__ContinueAlternatives_6_02100 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_rule__Element__ContinueAlternatives_6_02119 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_rule__Element__ContinueAlternatives_6_02138 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_rule__Attribute__ContinueAlternatives_6_02172 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_rule__Attribute__ContinueAlternatives_6_02191 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStart_in_rule__GrammarContent__Alternatives2224 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDefine_in_rule__GrammarContent__Alternatives2240 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__GrammarContent__Group_2_in_rule__GrammarContent__Alternatives2256 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__GrammarContent__Group_3_in_rule__GrammarContent__Alternatives2273 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDefine_in_rule__IncludeContent__Alternatives2305 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStart_in_rule__IncludeContent__Alternatives2321 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__IncludeContent__Group_2_in_rule__IncludeContent__Alternatives2337 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_rule__Start__Alternatives_12370 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_rule__Start__Alternatives_12389 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_rule__Start__Alternatives_12408 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__Start__Alternatives_22441 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePattern_in_rule__Start__Alternatives_22464 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_rule__Define__Alternatives_12503 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_rule__Define__Alternatives_12522 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_rule__Define__Alternatives_12541 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIdentifierOrKeyWord_in_rule__Name__Alternatives2574 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCName_in_rule__Name__Alternatives2590 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleName_in_rule__NameClass__Alternatives2621 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NameClass__Group_1_in_rule__NameClass__Alternatives2637 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NameClass__Group_2_in_rule__NameClass__Alternatives2654 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NameClass__Group_3_in_rule__NameClass__Alternatives2671 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__NameClass__Group_4_in_rule__NameClass__Alternatives2688 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCName_in_rule__DataTypeName__Alternatives2720 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_rule__DataTypeName__Alternatives2737 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_rule__DataTypeName__Alternatives2756 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIdentifier_in_rule__IdentifierOrKeyWord__Alternatives2790 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleKeyWord_in_rule__IdentifierOrKeyWord__Alternatives2806 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Identifier__Group_0_in_rule__Identifier__Alternatives2837 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQuotedIdentifier_in_rule__Identifier__Alternatives2854 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_rule__KeyWord__Alternatives2886 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_rule__KeyWord__Alternatives2905 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_rule__KeyWord__Alternatives2924 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_rule__KeyWord__Alternatives2943 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_29_in_rule__KeyWord__Alternatives2962 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_rule__KeyWord__Alternatives2981 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_30_in_rule__KeyWord__Alternatives3000 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_rule__KeyWord__Alternatives3019 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_rule__KeyWord__Alternatives3038 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_rule__KeyWord__Alternatives3057 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_34_in_rule__KeyWord__Alternatives3076 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_35_in_rule__KeyWord__Alternatives3095 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_36_in_rule__KeyWord__Alternatives3114 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_rule__KeyWord__Alternatives3133 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_37_in_rule__KeyWord__Alternatives3152 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_38_in_rule__KeyWord__Alternatives3171 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_rule__KeyWord__Alternatives3190 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_rule__KeyWord__Alternatives3209 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_rule__KeyWord__Alternatives3228 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDecl_in_rule__TopLevel__Group3269 = new BitSet(new long[]{0x0000027DFF80F032L});
    public static final BitSet FOLLOW_rule__TopLevel__Alternatives_1_in_rule__TopLevel__Group3287 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_36_in_rule__Decl__Group_03320 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__Decl__Group_03339 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_rule__Decl__Group_03357 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__Decl__Group_03376 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_rule__Decl__Group_13415 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_36_in_rule__Decl__Group_13428 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__Decl__Group_13447 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_rule__Decl__Group_13465 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__Decl__Group_13484 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_rule__Decl__Group_23523 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__Decl__Group_23542 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_rule__Decl__Group_23560 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__Decl__Group_23579 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_34_in_rule__Pattern__Group_13618 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_39_in_rule__Pattern__Group_13631 = new BitSet(new long[]{0x0000032CE380F030L});
    public static final BitSet FOLLOW_rulePattern_in_rule__Pattern__Group_13650 = new BitSet(new long[]{0x0000032CE380F030L});
    public static final BitSet FOLLOW_40_in_rule__Pattern__Group_13669 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_35_in_rule__Pattern__Group_23703 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_39_in_rule__Pattern__Group_23716 = new BitSet(new long[]{0x0000032CE380F030L});
    public static final BitSet FOLLOW_rulePattern_in_rule__Pattern__Group_23735 = new BitSet(new long[]{0x0000032CE380F030L});
    public static final BitSet FOLLOW_40_in_rule__Pattern__Group_23754 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__Pattern__Group_33787 = new BitSet(new long[]{0x0000000000100002L});
    public static final BitSet FOLLOW_20_in_rule__Pattern__Group_33799 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_37_in_rule__Pattern__Group_43835 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__Pattern__Group_43847 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDataTypeName_in_rule__Pattern__Group_93878 = new BitSet(new long[]{0x0000088000000002L});
    public static final BitSet FOLLOW_rule__Pattern__Group_9_1_in_rule__Pattern__Group_93888 = new BitSet(new long[]{0x0000088000000002L});
    public static final BitSet FOLLOW_ruleExceptPattern_in_rule__Pattern__Group_93907 = new BitSet(new long[]{0x0000080000000002L});
    public static final BitSet FOLLOW_39_in_rule__Pattern__Group_9_13947 = new BitSet(new long[]{0x0000117FFF80B010L});
    public static final BitSet FOLLOW_ruleParam_in_rule__Pattern__Group_9_13966 = new BitSet(new long[]{0x0000117FFF80B010L});
    public static final BitSet FOLLOW_40_in_rule__Pattern__Group_9_13985 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_30_in_rule__Pattern__Group_114019 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ruleAnyURILiteral_in_rule__Pattern__Group_114038 = new BitSet(new long[]{0x0000000200000002L});
    public static final BitSet FOLLOW_ruleInherit_in_rule__Pattern__Group_114062 = new BitSet(new long[]{0x0000000200000002L});
    public static final BitSet FOLLOW_31_in_rule__Pattern__Group_124102 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_39_in_rule__Pattern__Group_124115 = new BitSet(new long[]{0x0000014110000010L});
    public static final BitSet FOLLOW_ruleGrammarContent_in_rule__Pattern__Group_124134 = new BitSet(new long[]{0x0000014110000010L});
    public static final BitSet FOLLOW_40_in_rule__Pattern__Group_124153 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_41_in_rule__Pattern__Group_134187 = new BitSet(new long[]{0x0000062CE380F030L});
    public static final BitSet FOLLOW_rulePattern_in_rule__Pattern__Group_134206 = new BitSet(new long[]{0x0000062CE380F030L});
    public static final BitSet FOLLOW_42_in_rule__Pattern__Group_134225 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_18_in_rule__Pattern__Group_134249 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_29_in_rule__Element__Group4296 = new BitSet(new long[]{0x0000127FFF80F810L});
    public static final BitSet FOLLOW_ruleNameClass_in_rule__Element__Group4315 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_39_in_rule__Element__Group4333 = new BitSet(new long[]{0x0000032CE380F030L});
    public static final BitSet FOLLOW_rulePattern_in_rule__Element__Group4352 = new BitSet(new long[]{0x0000032CE380F030L});
    public static final BitSet FOLLOW_40_in_rule__Element__Group4371 = new BitSet(new long[]{0x00000000000F4802L});
    public static final BitSet FOLLOW_rule__Element__CardinalityAlternatives_5_0_in_rule__Element__Group4390 = new BitSet(new long[]{0x00000000000C4002L});
    public static final BitSet FOLLOW_rule__Element__ContinueAlternatives_6_0_in_rule__Element__Group4417 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_rule__Attribute__Group4459 = new BitSet(new long[]{0x0000127FFF80F810L});
    public static final BitSet FOLLOW_ruleNameClass_in_rule__Attribute__Group4478 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_39_in_rule__Attribute__Group4496 = new BitSet(new long[]{0x0000032CE380F030L});
    public static final BitSet FOLLOW_rulePattern_in_rule__Attribute__Group4515 = new BitSet(new long[]{0x0000032CE380F030L});
    public static final BitSet FOLLOW_40_in_rule__Attribute__Group4534 = new BitSet(new long[]{0x0000000000054002L});
    public static final BitSet FOLLOW_16_in_rule__Attribute__Group4558 = new BitSet(new long[]{0x0000000000044002L});
    public static final BitSet FOLLOW_rule__Attribute__ContinueAlternatives_6_0_in_rule__Attribute__Group4590 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIdentifierOrKeyWord_in_rule__Param__Group4631 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_rule__Param__Group4642 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ruleLiteral_in_rule__Param__Group4661 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_43_in_rule__ExceptPattern__Group4700 = new BitSet(new long[]{0x0000022CE380F030L});
    public static final BitSet FOLLOW_rulePattern_in_rule__ExceptPattern__Group4712 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_rule__GrammarContent__Group_24744 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_39_in_rule__GrammarContent__Group_24757 = new BitSet(new long[]{0x0000014110000010L});
    public static final BitSet FOLLOW_ruleGrammarContent_in_rule__GrammarContent__Group_24776 = new BitSet(new long[]{0x0000014110000010L});
    public static final BitSet FOLLOW_40_in_rule__GrammarContent__Group_24795 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_rule__GrammarContent__Group_34829 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ruleAnyURILiteral_in_rule__GrammarContent__Group_34841 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_ruleInherit_in_rule__GrammarContent__Group_34858 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_rule__GrammarContent__Group_3_3_in_rule__GrammarContent__Group_34875 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_39_in_rule__GrammarContent__Group_3_34908 = new BitSet(new long[]{0x0000014010000010L});
    public static final BitSet FOLLOW_ruleIncludeContent_in_rule__GrammarContent__Group_3_34927 = new BitSet(new long[]{0x0000014010000010L});
    public static final BitSet FOLLOW_40_in_rule__GrammarContent__Group_3_34946 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_rule__IncludeContent__Group_24980 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_39_in_rule__IncludeContent__Group_24993 = new BitSet(new long[]{0x0000014110000010L});
    public static final BitSet FOLLOW_ruleGrammarContent_in_rule__IncludeContent__Group_25012 = new BitSet(new long[]{0x0000014110000010L});
    public static final BitSet FOLLOW_40_in_rule__IncludeContent__Group_25031 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_38_in_rule__Start__Group5065 = new BitSet(new long[]{0x0000000000700000L});
    public static final BitSet FOLLOW_rule__Start__Alternatives_1_in_rule__Start__Group5077 = new BitSet(new long[]{0x0000022CE380F030L});
    public static final BitSet FOLLOW_rule__Start__Alternatives_2_in_rule__Start__Group5088 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__Define__Group5120 = new BitSet(new long[]{0x0000000000700000L});
    public static final BitSet FOLLOW_rule__Define__Alternatives_1_in_rule__Define__Group5130 = new BitSet(new long[]{0x0000022CE380F030L});
    public static final BitSet FOLLOW_rulePattern_in_rule__Define__Group5148 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_43_in_rule__ExceptNameClass__Group5187 = new BitSet(new long[]{0x0000127FFF80F810L});
    public static final BitSet FOLLOW_ruleNameClass_in_rule__ExceptNameClass__Group5199 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__NameClass__Group_15230 = new BitSet(new long[]{0x0000080000000002L});
    public static final BitSet FOLLOW_ruleExceptNameClass_in_rule__NameClass__Group_15247 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAnyName_in_rule__NameClass__Group_25286 = new BitSet(new long[]{0x0000080000000002L});
    public static final BitSet FOLLOW_ruleExceptNameClass_in_rule__NameClass__Group_25303 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_rule__NameClass__Group_35343 = new BitSet(new long[]{0x0000127FFF80F810L});
    public static final BitSet FOLLOW_ruleNameClass_in_rule__NameClass__Group_35362 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_41_in_rule__NameClass__Group_45401 = new BitSet(new long[]{0x0000127FFF80F810L});
    public static final BitSet FOLLOW_ruleNameClass_in_rule__NameClass__Group_45420 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_42_in_rule__NameClass__Group_45438 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_rule__Inherit__Group5472 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_rule__Inherit__Group5485 = new BitSet(new long[]{0x0000107FFF80B010L});
    public static final BitSet FOLLOW_ruleIdentifierOrKeyWord_in_rule__Inherit__Group5497 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__Identifier__Group_05528 = new BitSet(new long[]{0x0000007FFF80B000L});
    public static final BitSet FOLLOW_ruleKeyWord_in_rule__Identifier__Group_05545 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_44_in_rule__QuotedIdentifier__Group5584 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__QuotedIdentifier__Group5596 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__CName__Group5627 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_45_in_rule__CName__Group5638 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__CName__Group5650 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__Literal__Group5681 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_rule__Literal__Group_1_in_rule__Literal__Group5691 = new BitSet(new long[]{0x0000400000000002L});
    public static final BitSet FOLLOW_46_in_rule__Literal__Group_15725 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__Literal__Group_15744 = new BitSet(new long[]{0x0000000000000002L});

}