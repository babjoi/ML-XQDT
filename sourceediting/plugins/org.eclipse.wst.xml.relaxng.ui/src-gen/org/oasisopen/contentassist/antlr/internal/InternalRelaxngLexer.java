package org.oasisopen.contentassist.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.ui.common.editor.contentassist.antlr.internal.Lexer;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class InternalRelaxngLexer extends Lexer {
    public static final int RULE_ID=4;
    public static final int RULE_ANY_OTHER=10;
    public static final int T29=29;
    public static final int T28=28;
    public static final int T27=27;
    public static final int T26=26;
    public static final int T25=25;
    public static final int EOF=-1;
    public static final int T24=24;
    public static final int T23=23;
    public static final int T22=22;
    public static final int T21=21;
    public static final int T20=20;
    public static final int RULE_INT=6;
    public static final int T38=38;
    public static final int T37=37;
    public static final int T39=39;
    public static final int T34=34;
    public static final int T33=33;
    public static final int T36=36;
    public static final int T35=35;
    public static final int T30=30;
    public static final int T32=32;
    public static final int T31=31;
    public static final int T43=43;
    public static final int Tokens=47;
    public static final int RULE_SL_COMMENT=8;
    public static final int T42=42;
    public static final int T41=41;
    public static final int T40=40;
    public static final int T46=46;
    public static final int T45=45;
    public static final int RULE_ML_COMMENT=7;
    public static final int T44=44;
    public static final int RULE_STRING=5;
    public static final int T11=11;
    public static final int T12=12;
    public static final int T13=13;
    public static final int T14=14;
    public static final int T15=15;
    public static final int RULE_WS=9;
    public static final int T16=16;
    public static final int T17=17;
    public static final int T18=18;
    public static final int T19=19;
    public InternalRelaxngLexer() {;} 
    public InternalRelaxngLexer(CharStream input) {
        super(input);
    }
    public String getGrammarFileName() { return "../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g"; }

    // $ANTLR start T11
    public final void mT11() throws RecognitionException {
        try {
            int _type = T11;
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:10:5: ( '*' )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:10:7: '*'
            {
            match('*'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T11

    // $ANTLR start T12
    public final void mT12() throws RecognitionException {
        try {
            int _type = T12;
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:11:5: ( 'empty' )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:11:7: 'empty'
            {
            match("empty"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T12

    // $ANTLR start T13
    public final void mT13() throws RecognitionException {
        try {
            int _type = T13;
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:12:5: ( 'text' )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:12:7: 'text'
            {
            match("text"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T13

    // $ANTLR start T14
    public final void mT14() throws RecognitionException {
        try {
            int _type = T14;
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:13:5: ( '|' )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:13:7: '|'
            {
            match('|'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T14

    // $ANTLR start T15
    public final void mT15() throws RecognitionException {
        try {
            int _type = T15;
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:14:5: ( 'notAllowed' )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:14:7: 'notAllowed'
            {
            match("notAllowed"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T15

    // $ANTLR start T16
    public final void mT16() throws RecognitionException {
        try {
            int _type = T16;
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:15:5: ( '?' )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:15:7: '?'
            {
            match('?'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T16

    // $ANTLR start T17
    public final void mT17() throws RecognitionException {
        try {
            int _type = T17;
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:16:5: ( '+' )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:16:7: '+'
            {
            match('+'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T17

    // $ANTLR start T18
    public final void mT18() throws RecognitionException {
        try {
            int _type = T18;
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:17:5: ( ',' )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:17:7: ','
            {
            match(','); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T18

    // $ANTLR start T19
    public final void mT19() throws RecognitionException {
        try {
            int _type = T19;
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:18:5: ( '&' )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:18:7: '&'
            {
            match('&'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T19

    // $ANTLR start T20
    public final void mT20() throws RecognitionException {
        try {
            int _type = T20;
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:19:5: ( '=' )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:19:7: '='
            {
            match('='); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T20

    // $ANTLR start T21
    public final void mT21() throws RecognitionException {
        try {
            int _type = T21;
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:20:5: ( '|=' )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:20:7: '|='
            {
            match("|="); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T21

    // $ANTLR start T22
    public final void mT22() throws RecognitionException {
        try {
            int _type = T22;
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:21:5: ( '&=' )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:21:7: '&='
            {
            match("&="); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T22

    // $ANTLR start T23
    public final void mT23() throws RecognitionException {
        try {
            int _type = T23;
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:22:5: ( 'string' )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:22:7: 'string'
            {
            match("string"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T23

    // $ANTLR start T24
    public final void mT24() throws RecognitionException {
        try {
            int _type = T24;
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:23:5: ( 'token' )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:23:7: 'token'
            {
            match("token"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T24

    // $ANTLR start T25
    public final void mT25() throws RecognitionException {
        try {
            int _type = T25;
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:24:5: ( 'attribute' )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:24:7: 'attribute'
            {
            match("attribute"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T25

    // $ANTLR start T26
    public final void mT26() throws RecognitionException {
        try {
            int _type = T26;
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:25:5: ( 'default' )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:25:7: 'default'
            {
            match("default"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T26

    // $ANTLR start T27
    public final void mT27() throws RecognitionException {
        try {
            int _type = T27;
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:26:5: ( 'datatypes' )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:26:7: 'datatypes'
            {
            match("datatypes"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T27

    // $ANTLR start T28
    public final void mT28() throws RecognitionException {
        try {
            int _type = T28;
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:27:5: ( 'div' )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:27:7: 'div'
            {
            match("div"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T28

    // $ANTLR start T29
    public final void mT29() throws RecognitionException {
        try {
            int _type = T29;
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:28:5: ( 'element' )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:28:7: 'element'
            {
            match("element"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T29

    // $ANTLR start T30
    public final void mT30() throws RecognitionException {
        try {
            int _type = T30;
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:29:5: ( 'external' )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:29:7: 'external'
            {
            match("external"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T30

    // $ANTLR start T31
    public final void mT31() throws RecognitionException {
        try {
            int _type = T31;
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:30:5: ( 'grammar' )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:30:7: 'grammar'
            {
            match("grammar"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T31

    // $ANTLR start T32
    public final void mT32() throws RecognitionException {
        try {
            int _type = T32;
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:31:5: ( 'include' )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:31:7: 'include'
            {
            match("include"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T32

    // $ANTLR start T33
    public final void mT33() throws RecognitionException {
        try {
            int _type = T33;
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:32:5: ( 'inherit' )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:32:7: 'inherit'
            {
            match("inherit"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T33

    // $ANTLR start T34
    public final void mT34() throws RecognitionException {
        try {
            int _type = T34;
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:33:5: ( 'list' )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:33:7: 'list'
            {
            match("list"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T34

    // $ANTLR start T35
    public final void mT35() throws RecognitionException {
        try {
            int _type = T35;
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:34:5: ( 'mixed' )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:34:7: 'mixed'
            {
            match("mixed"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T35

    // $ANTLR start T36
    public final void mT36() throws RecognitionException {
        try {
            int _type = T36;
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:35:5: ( 'namespace' )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:35:7: 'namespace'
            {
            match("namespace"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T36

    // $ANTLR start T37
    public final void mT37() throws RecognitionException {
        try {
            int _type = T37;
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:36:5: ( 'parent' )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:36:7: 'parent'
            {
            match("parent"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T37

    // $ANTLR start T38
    public final void mT38() throws RecognitionException {
        try {
            int _type = T38;
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:37:5: ( 'start' )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:37:7: 'start'
            {
            match("start"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T38

    // $ANTLR start T39
    public final void mT39() throws RecognitionException {
        try {
            int _type = T39;
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:38:5: ( '{' )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:38:7: '{'
            {
            match('{'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T39

    // $ANTLR start T40
    public final void mT40() throws RecognitionException {
        try {
            int _type = T40;
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:39:5: ( '}' )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:39:7: '}'
            {
            match('}'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T40

    // $ANTLR start T41
    public final void mT41() throws RecognitionException {
        try {
            int _type = T41;
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:40:5: ( '(' )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:40:7: '('
            {
            match('('); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T41

    // $ANTLR start T42
    public final void mT42() throws RecognitionException {
        try {
            int _type = T42;
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:41:5: ( ')' )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:41:7: ')'
            {
            match(')'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T42

    // $ANTLR start T43
    public final void mT43() throws RecognitionException {
        try {
            int _type = T43;
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:42:5: ( '-' )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:42:7: '-'
            {
            match('-'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T43

    // $ANTLR start T44
    public final void mT44() throws RecognitionException {
        try {
            int _type = T44;
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:43:5: ( '\\\\' )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:43:7: '\\\\'
            {
            match('\\'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T44

    // $ANTLR start T45
    public final void mT45() throws RecognitionException {
        try {
            int _type = T45;
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:44:5: ( ':' )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:44:7: ':'
            {
            match(':'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T45

    // $ANTLR start T46
    public final void mT46() throws RecognitionException {
        try {
            int _type = T46;
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:45:5: ( '~' )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:45:7: '~'
            {
            match('~'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T46

    // $ANTLR start RULE_ID
    public final void mRULE_ID() throws RecognitionException {
        try {
            int _type = RULE_ID;
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2529:9: ( ( '^' )? ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )* )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2529:11: ( '^' )? ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2529:11: ( '^' )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0=='^') ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2529:11: '^'
                    {
                    match('^'); 

                    }
                    break;

            }

            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recover(mse);    throw mse;
            }

            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2529:40: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>='0' && LA2_0<='9')||(LA2_0>='A' && LA2_0<='Z')||LA2_0=='_'||(LA2_0>='a' && LA2_0<='z')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:
            	    {
            	    if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse =
            	            new MismatchedSetException(null,input);
            	        recover(mse);    throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RULE_ID

    // $ANTLR start RULE_INT
    public final void mRULE_INT() throws RecognitionException {
        try {
            int _type = RULE_INT;
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2531:10: ( ( '0' .. '9' )+ )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2531:12: ( '0' .. '9' )+
            {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2531:12: ( '0' .. '9' )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0>='0' && LA3_0<='9')) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2531:13: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

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

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RULE_INT

    // $ANTLR start RULE_STRING
    public final void mRULE_STRING() throws RecognitionException {
        try {
            int _type = RULE_STRING;
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2533:13: ( ( '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' ) )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2533:15: ( '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' )
            {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2533:15: ( '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0=='\"') ) {
                alt6=1;
            }
            else if ( (LA6_0=='\'') ) {
                alt6=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("2533:15: ( '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' )", 6, 0, input);

                throw nvae;
            }
            switch (alt6) {
                case 1 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2533:16: '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )* '\"'
                    {
                    match('\"'); 
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2533:20: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )*
                    loop4:
                    do {
                        int alt4=3;
                        int LA4_0 = input.LA(1);

                        if ( (LA4_0=='\\') ) {
                            alt4=1;
                        }
                        else if ( ((LA4_0>='\u0000' && LA4_0<='!')||(LA4_0>='#' && LA4_0<='[')||(LA4_0>=']' && LA4_0<='\uFFFE')) ) {
                            alt4=2;
                        }


                        switch (alt4) {
                    	case 1 :
                    	    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2533:21: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' )
                    	    {
                    	    match('\\'); 
                    	    if ( input.LA(1)=='\"'||input.LA(1)=='\''||input.LA(1)=='\\'||input.LA(1)=='b'||input.LA(1)=='f'||input.LA(1)=='n'||input.LA(1)=='r'||input.LA(1)=='t' ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse =
                    	            new MismatchedSetException(null,input);
                    	        recover(mse);    throw mse;
                    	    }


                    	    }
                    	    break;
                    	case 2 :
                    	    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2533:62: ~ ( ( '\\\\' | '\"' ) )
                    	    {
                    	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='!')||(input.LA(1)>='#' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFE') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse =
                    	            new MismatchedSetException(null,input);
                    	        recover(mse);    throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop4;
                        }
                    } while (true);

                    match('\"'); 

                    }
                    break;
                case 2 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2533:82: '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )* '\\''
                    {
                    match('\''); 
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2533:87: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )*
                    loop5:
                    do {
                        int alt5=3;
                        int LA5_0 = input.LA(1);

                        if ( (LA5_0=='\\') ) {
                            alt5=1;
                        }
                        else if ( ((LA5_0>='\u0000' && LA5_0<='&')||(LA5_0>='(' && LA5_0<='[')||(LA5_0>=']' && LA5_0<='\uFFFE')) ) {
                            alt5=2;
                        }


                        switch (alt5) {
                    	case 1 :
                    	    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2533:88: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' )
                    	    {
                    	    match('\\'); 
                    	    if ( input.LA(1)=='\"'||input.LA(1)=='\''||input.LA(1)=='\\'||input.LA(1)=='b'||input.LA(1)=='f'||input.LA(1)=='n'||input.LA(1)=='r'||input.LA(1)=='t' ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse =
                    	            new MismatchedSetException(null,input);
                    	        recover(mse);    throw mse;
                    	    }


                    	    }
                    	    break;
                    	case 2 :
                    	    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2533:129: ~ ( ( '\\\\' | '\\'' ) )
                    	    {
                    	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='&')||(input.LA(1)>='(' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFE') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse =
                    	            new MismatchedSetException(null,input);
                    	        recover(mse);    throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop5;
                        }
                    } while (true);

                    match('\''); 

                    }
                    break;

            }


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RULE_STRING

    // $ANTLR start RULE_ML_COMMENT
    public final void mRULE_ML_COMMENT() throws RecognitionException {
        try {
            int _type = RULE_ML_COMMENT;
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2535:17: ( '/*' ( options {greedy=false; } : . )* '*/' )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2535:19: '/*' ( options {greedy=false; } : . )* '*/'
            {
            match("/*"); 

            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2535:24: ( options {greedy=false; } : . )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0=='*') ) {
                    int LA7_1 = input.LA(2);

                    if ( (LA7_1=='/') ) {
                        alt7=2;
                    }
                    else if ( ((LA7_1>='\u0000' && LA7_1<='.')||(LA7_1>='0' && LA7_1<='\uFFFE')) ) {
                        alt7=1;
                    }


                }
                else if ( ((LA7_0>='\u0000' && LA7_0<=')')||(LA7_0>='+' && LA7_0<='\uFFFE')) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2535:52: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);

            match("*/"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RULE_ML_COMMENT

    // $ANTLR start RULE_SL_COMMENT
    public final void mRULE_SL_COMMENT() throws RecognitionException {
        try {
            int _type = RULE_SL_COMMENT;
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2537:17: ( '//' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )? )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2537:19: '//' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )?
            {
            match("//"); 

            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2537:24: (~ ( ( '\\n' | '\\r' ) ) )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( ((LA8_0>='\u0000' && LA8_0<='\t')||(LA8_0>='\u000B' && LA8_0<='\f')||(LA8_0>='\u000E' && LA8_0<='\uFFFE')) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2537:24: ~ ( ( '\\n' | '\\r' ) )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='\uFFFE') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse =
            	            new MismatchedSetException(null,input);
            	        recover(mse);    throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);

            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2537:40: ( ( '\\r' )? '\\n' )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0=='\n'||LA10_0=='\r') ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2537:41: ( '\\r' )? '\\n'
                    {
                    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2537:41: ( '\\r' )?
                    int alt9=2;
                    int LA9_0 = input.LA(1);

                    if ( (LA9_0=='\r') ) {
                        alt9=1;
                    }
                    switch (alt9) {
                        case 1 :
                            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2537:41: '\\r'
                            {
                            match('\r'); 

                            }
                            break;

                    }

                    match('\n'); 

                    }
                    break;

            }


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RULE_SL_COMMENT

    // $ANTLR start RULE_WS
    public final void mRULE_WS() throws RecognitionException {
        try {
            int _type = RULE_WS;
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2539:9: ( ( ' ' | '\\t' | '\\r' | '\\n' )+ )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2539:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
            {
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2539:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
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
            	    // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:
            	    {
            	    if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse =
            	            new MismatchedSetException(null,input);
            	        recover(mse);    throw mse;
            	    }


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


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RULE_WS

    // $ANTLR start RULE_ANY_OTHER
    public final void mRULE_ANY_OTHER() throws RecognitionException {
        try {
            int _type = RULE_ANY_OTHER;
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2541:16: ( . )
            // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:2541:18: .
            {
            matchAny(); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RULE_ANY_OTHER

    public void mTokens() throws RecognitionException {
        // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1:8: ( T11 | T12 | T13 | T14 | T15 | T16 | T17 | T18 | T19 | T20 | T21 | T22 | T23 | T24 | T25 | T26 | T27 | T28 | T29 | T30 | T31 | T32 | T33 | T34 | T35 | T36 | T37 | T38 | T39 | T40 | T41 | T42 | T43 | T44 | T45 | T46 | RULE_ID | RULE_INT | RULE_STRING | RULE_ML_COMMENT | RULE_SL_COMMENT | RULE_WS | RULE_ANY_OTHER )
        int alt12=43;
        int LA12_0 = input.LA(1);

        if ( (LA12_0=='*') ) {
            alt12=1;
        }
        else if ( (LA12_0=='e') ) {
            switch ( input.LA(2) ) {
            case 'x':
                {
                int LA12_36 = input.LA(3);

                if ( (LA12_36=='t') ) {
                    int LA12_75 = input.LA(4);

                    if ( (LA12_75=='e') ) {
                        int LA12_94 = input.LA(5);

                        if ( (LA12_94=='r') ) {
                            int LA12_113 = input.LA(6);

                            if ( (LA12_113=='n') ) {
                                int LA12_131 = input.LA(7);

                                if ( (LA12_131=='a') ) {
                                    int LA12_147 = input.LA(8);

                                    if ( (LA12_147=='l') ) {
                                        int LA12_159 = input.LA(9);

                                        if ( ((LA12_159>='0' && LA12_159<='9')||(LA12_159>='A' && LA12_159<='Z')||LA12_159=='_'||(LA12_159>='a' && LA12_159<='z')) ) {
                                            alt12=37;
                                        }
                                        else {
                                            alt12=20;}
                                    }
                                    else {
                                        alt12=37;}
                                }
                                else {
                                    alt12=37;}
                            }
                            else {
                                alt12=37;}
                        }
                        else {
                            alt12=37;}
                    }
                    else {
                        alt12=37;}
                }
                else {
                    alt12=37;}
                }
                break;
            case 'l':
                {
                int LA12_37 = input.LA(3);

                if ( (LA12_37=='e') ) {
                    int LA12_76 = input.LA(4);

                    if ( (LA12_76=='m') ) {
                        int LA12_95 = input.LA(5);

                        if ( (LA12_95=='e') ) {
                            int LA12_114 = input.LA(6);

                            if ( (LA12_114=='n') ) {
                                int LA12_132 = input.LA(7);

                                if ( (LA12_132=='t') ) {
                                    int LA12_148 = input.LA(8);

                                    if ( ((LA12_148>='0' && LA12_148<='9')||(LA12_148>='A' && LA12_148<='Z')||LA12_148=='_'||(LA12_148>='a' && LA12_148<='z')) ) {
                                        alt12=37;
                                    }
                                    else {
                                        alt12=19;}
                                }
                                else {
                                    alt12=37;}
                            }
                            else {
                                alt12=37;}
                        }
                        else {
                            alt12=37;}
                    }
                    else {
                        alt12=37;}
                }
                else {
                    alt12=37;}
                }
                break;
            case 'm':
                {
                int LA12_38 = input.LA(3);

                if ( (LA12_38=='p') ) {
                    int LA12_77 = input.LA(4);

                    if ( (LA12_77=='t') ) {
                        int LA12_96 = input.LA(5);

                        if ( (LA12_96=='y') ) {
                            int LA12_115 = input.LA(6);

                            if ( ((LA12_115>='0' && LA12_115<='9')||(LA12_115>='A' && LA12_115<='Z')||LA12_115=='_'||(LA12_115>='a' && LA12_115<='z')) ) {
                                alt12=37;
                            }
                            else {
                                alt12=2;}
                        }
                        else {
                            alt12=37;}
                    }
                    else {
                        alt12=37;}
                }
                else {
                    alt12=37;}
                }
                break;
            default:
                alt12=37;}

        }
        else if ( (LA12_0=='t') ) {
            switch ( input.LA(2) ) {
            case 'e':
                {
                int LA12_40 = input.LA(3);

                if ( (LA12_40=='x') ) {
                    int LA12_78 = input.LA(4);

                    if ( (LA12_78=='t') ) {
                        int LA12_97 = input.LA(5);

                        if ( ((LA12_97>='0' && LA12_97<='9')||(LA12_97>='A' && LA12_97<='Z')||LA12_97=='_'||(LA12_97>='a' && LA12_97<='z')) ) {
                            alt12=37;
                        }
                        else {
                            alt12=3;}
                    }
                    else {
                        alt12=37;}
                }
                else {
                    alt12=37;}
                }
                break;
            case 'o':
                {
                int LA12_41 = input.LA(3);

                if ( (LA12_41=='k') ) {
                    int LA12_79 = input.LA(4);

                    if ( (LA12_79=='e') ) {
                        int LA12_98 = input.LA(5);

                        if ( (LA12_98=='n') ) {
                            int LA12_117 = input.LA(6);

                            if ( ((LA12_117>='0' && LA12_117<='9')||(LA12_117>='A' && LA12_117<='Z')||LA12_117=='_'||(LA12_117>='a' && LA12_117<='z')) ) {
                                alt12=37;
                            }
                            else {
                                alt12=14;}
                        }
                        else {
                            alt12=37;}
                    }
                    else {
                        alt12=37;}
                }
                else {
                    alt12=37;}
                }
                break;
            default:
                alt12=37;}

        }
        else if ( (LA12_0=='|') ) {
            int LA12_4 = input.LA(2);

            if ( (LA12_4=='=') ) {
                alt12=11;
            }
            else {
                alt12=4;}
        }
        else if ( (LA12_0=='n') ) {
            switch ( input.LA(2) ) {
            case 'o':
                {
                int LA12_44 = input.LA(3);

                if ( (LA12_44=='t') ) {
                    int LA12_80 = input.LA(4);

                    if ( (LA12_80=='A') ) {
                        int LA12_99 = input.LA(5);

                        if ( (LA12_99=='l') ) {
                            int LA12_118 = input.LA(6);

                            if ( (LA12_118=='l') ) {
                                int LA12_135 = input.LA(7);

                                if ( (LA12_135=='o') ) {
                                    int LA12_149 = input.LA(8);

                                    if ( (LA12_149=='w') ) {
                                        int LA12_161 = input.LA(9);

                                        if ( (LA12_161=='e') ) {
                                            int LA12_170 = input.LA(10);

                                            if ( (LA12_170=='d') ) {
                                                int LA12_174 = input.LA(11);

                                                if ( ((LA12_174>='0' && LA12_174<='9')||(LA12_174>='A' && LA12_174<='Z')||LA12_174=='_'||(LA12_174>='a' && LA12_174<='z')) ) {
                                                    alt12=37;
                                                }
                                                else {
                                                    alt12=5;}
                                            }
                                            else {
                                                alt12=37;}
                                        }
                                        else {
                                            alt12=37;}
                                    }
                                    else {
                                        alt12=37;}
                                }
                                else {
                                    alt12=37;}
                            }
                            else {
                                alt12=37;}
                        }
                        else {
                            alt12=37;}
                    }
                    else {
                        alt12=37;}
                }
                else {
                    alt12=37;}
                }
                break;
            case 'a':
                {
                int LA12_45 = input.LA(3);

                if ( (LA12_45=='m') ) {
                    int LA12_81 = input.LA(4);

                    if ( (LA12_81=='e') ) {
                        int LA12_100 = input.LA(5);

                        if ( (LA12_100=='s') ) {
                            int LA12_119 = input.LA(6);

                            if ( (LA12_119=='p') ) {
                                int LA12_136 = input.LA(7);

                                if ( (LA12_136=='a') ) {
                                    int LA12_150 = input.LA(8);

                                    if ( (LA12_150=='c') ) {
                                        int LA12_162 = input.LA(9);

                                        if ( (LA12_162=='e') ) {
                                            int LA12_171 = input.LA(10);

                                            if ( ((LA12_171>='0' && LA12_171<='9')||(LA12_171>='A' && LA12_171<='Z')||LA12_171=='_'||(LA12_171>='a' && LA12_171<='z')) ) {
                                                alt12=37;
                                            }
                                            else {
                                                alt12=26;}
                                        }
                                        else {
                                            alt12=37;}
                                    }
                                    else {
                                        alt12=37;}
                                }
                                else {
                                    alt12=37;}
                            }
                            else {
                                alt12=37;}
                        }
                        else {
                            alt12=37;}
                    }
                    else {
                        alt12=37;}
                }
                else {
                    alt12=37;}
                }
                break;
            default:
                alt12=37;}

        }
        else if ( (LA12_0=='?') ) {
            alt12=6;
        }
        else if ( (LA12_0=='+') ) {
            alt12=7;
        }
        else if ( (LA12_0==',') ) {
            alt12=8;
        }
        else if ( (LA12_0=='&') ) {
            int LA12_9 = input.LA(2);

            if ( (LA12_9=='=') ) {
                alt12=12;
            }
            else {
                alt12=9;}
        }
        else if ( (LA12_0=='=') ) {
            alt12=10;
        }
        else if ( (LA12_0=='s') ) {
            int LA12_11 = input.LA(2);

            if ( (LA12_11=='t') ) {
                switch ( input.LA(3) ) {
                case 'r':
                    {
                    int LA12_82 = input.LA(4);

                    if ( (LA12_82=='i') ) {
                        int LA12_101 = input.LA(5);

                        if ( (LA12_101=='n') ) {
                            int LA12_120 = input.LA(6);

                            if ( (LA12_120=='g') ) {
                                int LA12_137 = input.LA(7);

                                if ( ((LA12_137>='0' && LA12_137<='9')||(LA12_137>='A' && LA12_137<='Z')||LA12_137=='_'||(LA12_137>='a' && LA12_137<='z')) ) {
                                    alt12=37;
                                }
                                else {
                                    alt12=13;}
                            }
                            else {
                                alt12=37;}
                        }
                        else {
                            alt12=37;}
                    }
                    else {
                        alt12=37;}
                    }
                    break;
                case 'a':
                    {
                    int LA12_83 = input.LA(4);

                    if ( (LA12_83=='r') ) {
                        int LA12_102 = input.LA(5);

                        if ( (LA12_102=='t') ) {
                            int LA12_121 = input.LA(6);

                            if ( ((LA12_121>='0' && LA12_121<='9')||(LA12_121>='A' && LA12_121<='Z')||LA12_121=='_'||(LA12_121>='a' && LA12_121<='z')) ) {
                                alt12=37;
                            }
                            else {
                                alt12=28;}
                        }
                        else {
                            alt12=37;}
                    }
                    else {
                        alt12=37;}
                    }
                    break;
                default:
                    alt12=37;}

            }
            else {
                alt12=37;}
        }
        else if ( (LA12_0=='a') ) {
            int LA12_12 = input.LA(2);

            if ( (LA12_12=='t') ) {
                int LA12_53 = input.LA(3);

                if ( (LA12_53=='t') ) {
                    int LA12_84 = input.LA(4);

                    if ( (LA12_84=='r') ) {
                        int LA12_103 = input.LA(5);

                        if ( (LA12_103=='i') ) {
                            int LA12_122 = input.LA(6);

                            if ( (LA12_122=='b') ) {
                                int LA12_139 = input.LA(7);

                                if ( (LA12_139=='u') ) {
                                    int LA12_152 = input.LA(8);

                                    if ( (LA12_152=='t') ) {
                                        int LA12_163 = input.LA(9);

                                        if ( (LA12_163=='e') ) {
                                            int LA12_172 = input.LA(10);

                                            if ( ((LA12_172>='0' && LA12_172<='9')||(LA12_172>='A' && LA12_172<='Z')||LA12_172=='_'||(LA12_172>='a' && LA12_172<='z')) ) {
                                                alt12=37;
                                            }
                                            else {
                                                alt12=15;}
                                        }
                                        else {
                                            alt12=37;}
                                    }
                                    else {
                                        alt12=37;}
                                }
                                else {
                                    alt12=37;}
                            }
                            else {
                                alt12=37;}
                        }
                        else {
                            alt12=37;}
                    }
                    else {
                        alt12=37;}
                }
                else {
                    alt12=37;}
            }
            else {
                alt12=37;}
        }
        else if ( (LA12_0=='d') ) {
            switch ( input.LA(2) ) {
            case 'e':
                {
                int LA12_54 = input.LA(3);

                if ( (LA12_54=='f') ) {
                    int LA12_85 = input.LA(4);

                    if ( (LA12_85=='a') ) {
                        int LA12_104 = input.LA(5);

                        if ( (LA12_104=='u') ) {
                            int LA12_123 = input.LA(6);

                            if ( (LA12_123=='l') ) {
                                int LA12_140 = input.LA(7);

                                if ( (LA12_140=='t') ) {
                                    int LA12_153 = input.LA(8);

                                    if ( ((LA12_153>='0' && LA12_153<='9')||(LA12_153>='A' && LA12_153<='Z')||LA12_153=='_'||(LA12_153>='a' && LA12_153<='z')) ) {
                                        alt12=37;
                                    }
                                    else {
                                        alt12=16;}
                                }
                                else {
                                    alt12=37;}
                            }
                            else {
                                alt12=37;}
                        }
                        else {
                            alt12=37;}
                    }
                    else {
                        alt12=37;}
                }
                else {
                    alt12=37;}
                }
                break;
            case 'a':
                {
                int LA12_55 = input.LA(3);

                if ( (LA12_55=='t') ) {
                    int LA12_86 = input.LA(4);

                    if ( (LA12_86=='a') ) {
                        int LA12_105 = input.LA(5);

                        if ( (LA12_105=='t') ) {
                            int LA12_124 = input.LA(6);

                            if ( (LA12_124=='y') ) {
                                int LA12_141 = input.LA(7);

                                if ( (LA12_141=='p') ) {
                                    int LA12_154 = input.LA(8);

                                    if ( (LA12_154=='e') ) {
                                        int LA12_165 = input.LA(9);

                                        if ( (LA12_165=='s') ) {
                                            int LA12_173 = input.LA(10);

                                            if ( ((LA12_173>='0' && LA12_173<='9')||(LA12_173>='A' && LA12_173<='Z')||LA12_173=='_'||(LA12_173>='a' && LA12_173<='z')) ) {
                                                alt12=37;
                                            }
                                            else {
                                                alt12=17;}
                                        }
                                        else {
                                            alt12=37;}
                                    }
                                    else {
                                        alt12=37;}
                                }
                                else {
                                    alt12=37;}
                            }
                            else {
                                alt12=37;}
                        }
                        else {
                            alt12=37;}
                    }
                    else {
                        alt12=37;}
                }
                else {
                    alt12=37;}
                }
                break;
            case 'i':
                {
                int LA12_56 = input.LA(3);

                if ( (LA12_56=='v') ) {
                    int LA12_87 = input.LA(4);

                    if ( ((LA12_87>='0' && LA12_87<='9')||(LA12_87>='A' && LA12_87<='Z')||LA12_87=='_'||(LA12_87>='a' && LA12_87<='z')) ) {
                        alt12=37;
                    }
                    else {
                        alt12=18;}
                }
                else {
                    alt12=37;}
                }
                break;
            default:
                alt12=37;}

        }
        else if ( (LA12_0=='g') ) {
            int LA12_14 = input.LA(2);

            if ( (LA12_14=='r') ) {
                int LA12_57 = input.LA(3);

                if ( (LA12_57=='a') ) {
                    int LA12_88 = input.LA(4);

                    if ( (LA12_88=='m') ) {
                        int LA12_107 = input.LA(5);

                        if ( (LA12_107=='m') ) {
                            int LA12_125 = input.LA(6);

                            if ( (LA12_125=='a') ) {
                                int LA12_142 = input.LA(7);

                                if ( (LA12_142=='r') ) {
                                    int LA12_155 = input.LA(8);

                                    if ( ((LA12_155>='0' && LA12_155<='9')||(LA12_155>='A' && LA12_155<='Z')||LA12_155=='_'||(LA12_155>='a' && LA12_155<='z')) ) {
                                        alt12=37;
                                    }
                                    else {
                                        alt12=21;}
                                }
                                else {
                                    alt12=37;}
                            }
                            else {
                                alt12=37;}
                        }
                        else {
                            alt12=37;}
                    }
                    else {
                        alt12=37;}
                }
                else {
                    alt12=37;}
            }
            else {
                alt12=37;}
        }
        else if ( (LA12_0=='i') ) {
            int LA12_15 = input.LA(2);

            if ( (LA12_15=='n') ) {
                switch ( input.LA(3) ) {
                case 'h':
                    {
                    int LA12_89 = input.LA(4);

                    if ( (LA12_89=='e') ) {
                        int LA12_108 = input.LA(5);

                        if ( (LA12_108=='r') ) {
                            int LA12_126 = input.LA(6);

                            if ( (LA12_126=='i') ) {
                                int LA12_143 = input.LA(7);

                                if ( (LA12_143=='t') ) {
                                    int LA12_156 = input.LA(8);

                                    if ( ((LA12_156>='0' && LA12_156<='9')||(LA12_156>='A' && LA12_156<='Z')||LA12_156=='_'||(LA12_156>='a' && LA12_156<='z')) ) {
                                        alt12=37;
                                    }
                                    else {
                                        alt12=23;}
                                }
                                else {
                                    alt12=37;}
                            }
                            else {
                                alt12=37;}
                        }
                        else {
                            alt12=37;}
                    }
                    else {
                        alt12=37;}
                    }
                    break;
                case 'c':
                    {
                    int LA12_90 = input.LA(4);

                    if ( (LA12_90=='l') ) {
                        int LA12_109 = input.LA(5);

                        if ( (LA12_109=='u') ) {
                            int LA12_127 = input.LA(6);

                            if ( (LA12_127=='d') ) {
                                int LA12_144 = input.LA(7);

                                if ( (LA12_144=='e') ) {
                                    int LA12_157 = input.LA(8);

                                    if ( ((LA12_157>='0' && LA12_157<='9')||(LA12_157>='A' && LA12_157<='Z')||LA12_157=='_'||(LA12_157>='a' && LA12_157<='z')) ) {
                                        alt12=37;
                                    }
                                    else {
                                        alt12=22;}
                                }
                                else {
                                    alt12=37;}
                            }
                            else {
                                alt12=37;}
                        }
                        else {
                            alt12=37;}
                    }
                    else {
                        alt12=37;}
                    }
                    break;
                default:
                    alt12=37;}

            }
            else {
                alt12=37;}
        }
        else if ( (LA12_0=='l') ) {
            int LA12_16 = input.LA(2);

            if ( (LA12_16=='i') ) {
                int LA12_59 = input.LA(3);

                if ( (LA12_59=='s') ) {
                    int LA12_91 = input.LA(4);

                    if ( (LA12_91=='t') ) {
                        int LA12_110 = input.LA(5);

                        if ( ((LA12_110>='0' && LA12_110<='9')||(LA12_110>='A' && LA12_110<='Z')||LA12_110=='_'||(LA12_110>='a' && LA12_110<='z')) ) {
                            alt12=37;
                        }
                        else {
                            alt12=24;}
                    }
                    else {
                        alt12=37;}
                }
                else {
                    alt12=37;}
            }
            else {
                alt12=37;}
        }
        else if ( (LA12_0=='m') ) {
            int LA12_17 = input.LA(2);

            if ( (LA12_17=='i') ) {
                int LA12_60 = input.LA(3);

                if ( (LA12_60=='x') ) {
                    int LA12_92 = input.LA(4);

                    if ( (LA12_92=='e') ) {
                        int LA12_111 = input.LA(5);

                        if ( (LA12_111=='d') ) {
                            int LA12_129 = input.LA(6);

                            if ( ((LA12_129>='0' && LA12_129<='9')||(LA12_129>='A' && LA12_129<='Z')||LA12_129=='_'||(LA12_129>='a' && LA12_129<='z')) ) {
                                alt12=37;
                            }
                            else {
                                alt12=25;}
                        }
                        else {
                            alt12=37;}
                    }
                    else {
                        alt12=37;}
                }
                else {
                    alt12=37;}
            }
            else {
                alt12=37;}
        }
        else if ( (LA12_0=='p') ) {
            int LA12_18 = input.LA(2);

            if ( (LA12_18=='a') ) {
                int LA12_61 = input.LA(3);

                if ( (LA12_61=='r') ) {
                    int LA12_93 = input.LA(4);

                    if ( (LA12_93=='e') ) {
                        int LA12_112 = input.LA(5);

                        if ( (LA12_112=='n') ) {
                            int LA12_130 = input.LA(6);

                            if ( (LA12_130=='t') ) {
                                int LA12_146 = input.LA(7);

                                if ( ((LA12_146>='0' && LA12_146<='9')||(LA12_146>='A' && LA12_146<='Z')||LA12_146=='_'||(LA12_146>='a' && LA12_146<='z')) ) {
                                    alt12=37;
                                }
                                else {
                                    alt12=27;}
                            }
                            else {
                                alt12=37;}
                        }
                        else {
                            alt12=37;}
                    }
                    else {
                        alt12=37;}
                }
                else {
                    alt12=37;}
            }
            else {
                alt12=37;}
        }
        else if ( (LA12_0=='{') ) {
            alt12=29;
        }
        else if ( (LA12_0=='}') ) {
            alt12=30;
        }
        else if ( (LA12_0=='(') ) {
            alt12=31;
        }
        else if ( (LA12_0==')') ) {
            alt12=32;
        }
        else if ( (LA12_0=='-') ) {
            alt12=33;
        }
        else if ( (LA12_0=='\\') ) {
            alt12=34;
        }
        else if ( (LA12_0==':') ) {
            alt12=35;
        }
        else if ( (LA12_0=='~') ) {
            alt12=36;
        }
        else if ( (LA12_0=='^') ) {
            int LA12_27 = input.LA(2);

            if ( ((LA12_27>='A' && LA12_27<='Z')||LA12_27=='_'||(LA12_27>='a' && LA12_27<='z')) ) {
                alt12=37;
            }
            else {
                alt12=43;}
        }
        else if ( ((LA12_0>='A' && LA12_0<='Z')||LA12_0=='_'||(LA12_0>='b' && LA12_0<='c')||LA12_0=='f'||LA12_0=='h'||(LA12_0>='j' && LA12_0<='k')||LA12_0=='o'||(LA12_0>='q' && LA12_0<='r')||(LA12_0>='u' && LA12_0<='z')) ) {
            alt12=37;
        }
        else if ( ((LA12_0>='0' && LA12_0<='9')) ) {
            alt12=38;
        }
        else if ( (LA12_0=='\"') ) {
            int LA12_30 = input.LA(2);

            if ( ((LA12_30>='\u0000' && LA12_30<='\uFFFE')) ) {
                alt12=39;
            }
            else {
                alt12=43;}
        }
        else if ( (LA12_0=='\'') ) {
            int LA12_31 = input.LA(2);

            if ( ((LA12_31>='\u0000' && LA12_31<='\uFFFE')) ) {
                alt12=39;
            }
            else {
                alt12=43;}
        }
        else if ( (LA12_0=='/') ) {
            switch ( input.LA(2) ) {
            case '/':
                {
                alt12=41;
                }
                break;
            case '*':
                {
                alt12=40;
                }
                break;
            default:
                alt12=43;}

        }
        else if ( ((LA12_0>='\t' && LA12_0<='\n')||LA12_0=='\r'||LA12_0==' ') ) {
            alt12=42;
        }
        else if ( ((LA12_0>='\u0000' && LA12_0<='\b')||(LA12_0>='\u000B' && LA12_0<='\f')||(LA12_0>='\u000E' && LA12_0<='\u001F')||LA12_0=='!'||(LA12_0>='#' && LA12_0<='%')||LA12_0=='.'||(LA12_0>=';' && LA12_0<='<')||LA12_0=='>'||LA12_0=='@'||LA12_0=='['||LA12_0==']'||LA12_0=='`'||(LA12_0>='\u007F' && LA12_0<='\uFFFE')) ) {
            alt12=43;
        }
        else {
            NoViableAltException nvae =
                new NoViableAltException("1:1: Tokens : ( T11 | T12 | T13 | T14 | T15 | T16 | T17 | T18 | T19 | T20 | T21 | T22 | T23 | T24 | T25 | T26 | T27 | T28 | T29 | T30 | T31 | T32 | T33 | T34 | T35 | T36 | T37 | T38 | T39 | T40 | T41 | T42 | T43 | T44 | T45 | T46 | RULE_ID | RULE_INT | RULE_STRING | RULE_ML_COMMENT | RULE_SL_COMMENT | RULE_WS | RULE_ANY_OTHER );", 12, 0, input);

            throw nvae;
        }
        switch (alt12) {
            case 1 :
                // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1:10: T11
                {
                mT11(); 

                }
                break;
            case 2 :
                // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1:14: T12
                {
                mT12(); 

                }
                break;
            case 3 :
                // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1:18: T13
                {
                mT13(); 

                }
                break;
            case 4 :
                // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1:22: T14
                {
                mT14(); 

                }
                break;
            case 5 :
                // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1:26: T15
                {
                mT15(); 

                }
                break;
            case 6 :
                // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1:30: T16
                {
                mT16(); 

                }
                break;
            case 7 :
                // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1:34: T17
                {
                mT17(); 

                }
                break;
            case 8 :
                // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1:38: T18
                {
                mT18(); 

                }
                break;
            case 9 :
                // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1:42: T19
                {
                mT19(); 

                }
                break;
            case 10 :
                // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1:46: T20
                {
                mT20(); 

                }
                break;
            case 11 :
                // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1:50: T21
                {
                mT21(); 

                }
                break;
            case 12 :
                // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1:54: T22
                {
                mT22(); 

                }
                break;
            case 13 :
                // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1:58: T23
                {
                mT23(); 

                }
                break;
            case 14 :
                // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1:62: T24
                {
                mT24(); 

                }
                break;
            case 15 :
                // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1:66: T25
                {
                mT25(); 

                }
                break;
            case 16 :
                // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1:70: T26
                {
                mT26(); 

                }
                break;
            case 17 :
                // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1:74: T27
                {
                mT27(); 

                }
                break;
            case 18 :
                // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1:78: T28
                {
                mT28(); 

                }
                break;
            case 19 :
                // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1:82: T29
                {
                mT29(); 

                }
                break;
            case 20 :
                // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1:86: T30
                {
                mT30(); 

                }
                break;
            case 21 :
                // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1:90: T31
                {
                mT31(); 

                }
                break;
            case 22 :
                // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1:94: T32
                {
                mT32(); 

                }
                break;
            case 23 :
                // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1:98: T33
                {
                mT33(); 

                }
                break;
            case 24 :
                // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1:102: T34
                {
                mT34(); 

                }
                break;
            case 25 :
                // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1:106: T35
                {
                mT35(); 

                }
                break;
            case 26 :
                // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1:110: T36
                {
                mT36(); 

                }
                break;
            case 27 :
                // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1:114: T37
                {
                mT37(); 

                }
                break;
            case 28 :
                // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1:118: T38
                {
                mT38(); 

                }
                break;
            case 29 :
                // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1:122: T39
                {
                mT39(); 

                }
                break;
            case 30 :
                // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1:126: T40
                {
                mT40(); 

                }
                break;
            case 31 :
                // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1:130: T41
                {
                mT41(); 

                }
                break;
            case 32 :
                // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1:134: T42
                {
                mT42(); 

                }
                break;
            case 33 :
                // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1:138: T43
                {
                mT43(); 

                }
                break;
            case 34 :
                // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1:142: T44
                {
                mT44(); 

                }
                break;
            case 35 :
                // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1:146: T45
                {
                mT45(); 

                }
                break;
            case 36 :
                // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1:150: T46
                {
                mT46(); 

                }
                break;
            case 37 :
                // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1:154: RULE_ID
                {
                mRULE_ID(); 

                }
                break;
            case 38 :
                // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1:162: RULE_INT
                {
                mRULE_INT(); 

                }
                break;
            case 39 :
                // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1:171: RULE_STRING
                {
                mRULE_STRING(); 

                }
                break;
            case 40 :
                // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1:183: RULE_ML_COMMENT
                {
                mRULE_ML_COMMENT(); 

                }
                break;
            case 41 :
                // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1:199: RULE_SL_COMMENT
                {
                mRULE_SL_COMMENT(); 

                }
                break;
            case 42 :
                // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1:215: RULE_WS
                {
                mRULE_WS(); 

                }
                break;
            case 43 :
                // ../org.eclipse.wst.xml.relaxng.ui/src-gen/org/oasisopen/contentassist/antlr/internal/InternalRelaxng.g:1:223: RULE_ANY_OTHER
                {
                mRULE_ANY_OTHER(); 

                }
                break;

        }

    }


 

}