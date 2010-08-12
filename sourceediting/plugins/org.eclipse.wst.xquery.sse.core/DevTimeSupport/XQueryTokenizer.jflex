/*******************************************************************************
 * Copyright (c) 2004, 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.wst.xquery.sse.core.internal.parser;

import java.io.CharArrayReader;
import java.io.IOException; 
import java.util.Stack; 
 
import org.eclipse.wst.sse.core.internal.provisional.text.ITextRegion;
import org.eclipse.wst.sse.core.internal.util.Debug;
import org.eclipse.wst.sse.core.utils.StringUtils;  
import org.eclipse.wst.xquery.sse.core.internal.regions.XQueryRegions;

@SuppressWarnings("restriction")

%%

%public
%class XQueryTokenizer
%implements XQueryRegions
%extends AbstractTokenizer
%function primGetNextToken
%type String
%char
%line
%unicode
%pack
 
%eof{
// do nothing, this is the downstream parser's job
%eof}

%{
	// Expression context constants. Must be negative to avoid conflicting with lexical states
	 
	final private static int EXPR = -1;
	final private static int MODULE = -2; // In a module (top-level) 
	final private static int WHEREEXPR = -3; // In where ExprSingle
	final private static int RETURNEXPR = -4; // In return ExprSingle 
	final private static int ORDEREXPR = -5;  // In OrderSpec ExprSingle
	final private static int FLCLAUSEEXPR = -6; // In in or := ExprSingle
	final private static int FLWORFOR = -7; 
	final private static int FLWORLET = -8; 
	final private static int PREDICATEEXPR = -9; // Within a predicate 
	
	// Within an expression between "(" and ")": Parentherize/Function call/Test condition/Typeswitch operand. 
    // This state is always preceded by the following state when closing this expression  
    final private static int PAREXPR = -10; 
    
    // Within an expression between "{" and "}": ordered/unordered/comp node contruction/direct element content
    // This state is always preceded by the following state when closing this expression  
    final private static int CURLYEXPR = -11; 
    
    final private static int INVARDECLINIT = -12;
    
	final private static int IF = -13;  
	final private static int IFTHENEXPR = -14; // In the if then expr 
	final private static int IFELSEEXPR = -15; // In the if else expr 
	final private static int QUANTIFIED = -16;
	final private static int QUANTIFIEDINEXPR = -17;
	final private static int QUANTIFIEDSATIFIESEXPR = -18;
	final private static int TYPESWITCH = -19;  
	final private static int TYPESWITCHDEFAULT = -20; // in default typeswitch ExprSingle 
	
	// XQuery Update Facility 1.0
	    
	final private static int XUDELETE = -21;  
	final private static int XUREPLACE = -22;
	final private static int XURENAME = -23;
	final private static int XUTRANSFORM = -24;
	final private static int XUTRANSFORMASSIGN = -25; // In copy := assignment
	final private static int XUMODIFYEXPR = -26; // In the modify expression
	final private static int XURETURNEXPR = -27; // In the return expression
	final private static int XUDELETETARGET = -28; 
	final private static int XUREPLACETARGET = -29;
	final private static int XUREPLACESRC = -30;
    final private static int XUNEWNAMEEXPR = -31;
	final private static int XUINSERTTARGET = -32;
	final private static int XUINSERT = -33; 
	final private static int XUSOURCE = -34;
    
    
    // XQuery Scripting Extension 1.0
    final private static int SXBLOCK = -35;
    final private static int INBLOCKVARDECLINIT = -36;
    final private static int SXASSIGN = -37; // in an assign expression
    final private static int SXASSIGNRHS = -38; // In the rhs of the assign expression
    final private static int SXEXIT = -39;  
    final private static int SXEXITEXPRSINGLE = -40; // In the exit expression single
    final private static int SXWHILE = -41; 
    
    // XQuery 1.1 
    final private static int GROUPBY = -42;
    
    
    
    final private static int NULL = -9999;
    
    // State
    
    /** The owner parser */
     private XQueryRegionParser parser;
    
    /** The cached next token */
    private String nextToken;
    
    /** Context stack. Lexical state can also be pushed on this stack (for instance, for continuation) */
    private IntStack states;
    
    /** XML Element depth */
    private IntStack elementDepths;
    
    /** Lexical state before recovery */
    private int recoveryState;
      
    /** Whether to drop the parsed token */
    private boolean drop;
    
    /** State when token has been dropped */
    private int droppedState;
    
    /** Whether parsing a kind test or a sequence type */
    private Stack<Boolean> sequenceType;
     
    // Constructors
    
    public XQueryTokenizer()
    {
      elementDepths = new IntStack(512); 
      states = new IntStack(512);
      sequenceType = new Stack<Boolean>();
    }
    
    // Methods

	public void setParser(XQueryRegionParser parser)
	{
	  this.parser = parser;
	}

	/** Print out string */
	private final void dump(String s) {
		if (Debug.debugTokenizer) {
			System.out.println(s + " (" + yychar + "-" + //$NON-NLS-2$//$NON-NLS-1$
				(yylength() + yychar) + "):\'" +//$NON-NLS-1$
					StringUtils.escape(yytext()) + "\'");//$NON-NLS-1$
		}
	}
	
	/** Push given state */
	private final void pushState(int state) {
	  states.push(state);
	}
	
	/** Push current state */
	private final void pushCurrentState() {
	  states.push(yystate());
	}
	 
	/** Pop state  */
	private final int popState() {
	  return states.empty() ? YYINITIAL : states.pop();
	}
	
	/** Peek at state */
	private final int peekState() {
	  return states.empty() ? YYINITIAL : states.peek();
	}
	
	/** Check state on given state matches one of expected. If not, mark current token with recovery state  */
	private final void check(int top, int... expected) {
	  for (int state : expected)
	    if (state == top)
	      return;
	      
	   recoveryState = top; // Will show an error
	}
	
	
	/** Check state on top of the stack is the expected ones. If not, mark current token with recovery state  */
	private final void checkTop(int... expected) {
	  check(peekState(), expected);
	}
	
	/** Pop state and check that it matches at least one of the given state  */
	private final void popAndCheck(int... states) {
	  check(popState());
	} 
	
	/** Start or continue FLWOR clause */
	final private void flowr(int type)
	{
		// Start FLWOR clause only if not already in such clause
		if (peekState() != FLWORFOR && peekState() != FLWORLET)
		{ 
		  pushState(type);
		}
	}
	
	/** 
	 * Received a token terminating ExprSingle.
	 *
	 * Depending on the context, either :
	 * - recursively terminate outer ExprSingle (for instance when the terminating ExprSingle is within the context of a For Return expression)
	 *   in this case the state stack will be reduced accordingly
	 * - continue parsing the same outer expression single. Only the sub-expression type is popped from the state stack
	 *
	 * yybegin(TS_EXPRSINGLE) should always be called after pushing the context expression on the stack.
	 */
	final private int endExprSingle()
	{
		final int context = popState();
		switch (context)
		{
		  // Cases where the outer expression must also be terminated.
		
		  case RETURNEXPR: 
		  case IFELSEEXPR: 
		  case QUANTIFIEDSATIFIESEXPR: 
		  case TYPESWITCHDEFAULT: 
		  case XUINSERTTARGET: 
		  case XUDELETETARGET: 
		  case XUREPLACESRC: 
		  case XUNEWNAMEEXPR: 
		  case XURETURNEXPR: 
		  case SXASSIGNRHS: 
		  case SXEXITEXPRSINGLE: 
		  case GROUPBY: // treated as an expression single 
		    popState();
		    return endExprSingle();
	
	      case MODULE:
	        // Terminate parsing..
	        yybegin(TS_SINK);
	        return context;
	
		  case WHEREEXPR:
		  case ORDEREXPR:
		  case FLCLAUSEEXPR:
		  case PREDICATEEXPR:
		  case IFTHENEXPR:
		  case XUREPLACE:
		  case XUREPLACETARGET:
		  case INVARDECLINIT:
		  case CURLYEXPR: 
		  case SXWHILE:
		  default:
		    // Continue analyzing the same expression. The caller will be responsible for moving to a new lexical state.
		    return context; 
		} 
	} 
	
	/** Received "," */
	final private String comma()
	{
	  final int context = peekState();
	  switch (context)
	  {
	    case FLCLAUSEEXPR: // Expecting a new variable declaration
	      popState();
	      yybegin(peekState() == FLWORFOR ? TS_FORCLAUSE : TS_LETCLAUSE); 
	      break;
	   
	   case QUANTIFIEDINEXPR:     // Expecting a new variable declaration
	      popState();
	      yybegin(TS_QUANTIFIEDEXPR);
	      break;
	     
	   case XUTRANSFORMASSIGN: 
	     popState();
	     yybegin(TS_TRANSFORMEXPR);
	      break;
	        	    
	   case INBLOCKVARDECLINIT:
	      // Expecting a new block variable declaration
	      popState();
	      yybegin(TS_BLOCKVARNAME);
	      break;
	      
	    case GROUPBY: // Expecting a new grouping spec
	      yybegin(TS_GCVARNAME);
	      break;
	    
	    case EXPR:
	    case CURLYEXPR:
	    case YYINITIAL:
	    default:
	      // Just keep going..
	      yybegin(TS_EXPRSINGLE);
	      
	  }
	  
	  return COMMA;
	} 
	
	/** Received ";" */
	final private String semicolon()
	{
	  final int context = peekState();
	  switch (context)
	  {
	    case INVARDECLINIT:
	      // Terminate global variable initialization
	      popState();
	      yybegin(TS_PROLOG2);
	      break;
	    case INBLOCKVARDECLINIT:
	      // Terminate a block variable initialization
	      popState();
	      yybegin(TS_BLOCKVARDECLOPT);
	      break;
	    default:
	      // Terminate apply expression.. Keep going..
	      yybegin(TS_EXPROPT);
	  }
	    
	  return SEPARATOR;
	}
	
	/** Received "return" */
	final private String returnkw()
	{
	  final int context = endExprSingle();
	  switch (context)
	  {
	    case FLCLAUSEEXPR:
	    case WHEREEXPR:
	    case ORDEREXPR:
	      checkTop(FLWORFOR, FLWORLET);
  		  pushState(RETURNEXPR);  
  		  yybegin(TS_EXPRSINGLE); 
  		  break;
  		case XUMODIFYEXPR:
  		  checkTop(XUTRANSFORM);
  		  pushState(XURETURNEXPR);  
  		  yybegin(TS_EXPRSINGLE); 
  		  break;
  		
	  }
	  
	  return KW_RETURN;
	}
	 
	
	/** Start XML section */
	private void startXML()
	{
	  elementDepths.push(0);
	}
	
	/** End XML section */
	private void endXML()
	{
	  elementDepths.pop();
	}
	
	/** Receive notification of the beginning of an XML element */
	private void startElement()
	{
	  if (!elementDepths.empty())
		elementDepths.setTop(elementDepths.peek() + 1);
	} 
	
	/** Receive notification of the end of an XML element */
	private void endElement()
	{
		if (!elementDepths.empty())
		  elementDepths.setTop(elementDepths.peek() - 1);
	} 
	
	/** Tell whether or not it within an element content */
	private boolean inXMLContent()
	{
		if (!elementDepths.empty())
			return elementDepths.peek() > 0;
			
		return false;
	}
	
	/** Start sequence type */
	private void startSequenceType()
	{
	  sequenceType.push(true);
	}
	
	/** Start kind test */
	private void startKindTest()
	{
	  sequenceType.push(false);
	}
	 
	/** End sequence type or kind test */
	private boolean endSTOrKT()
	{
	  if (!sequenceType.isEmpty())
	     return sequenceType.pop();
	  return false;
	}
	
	/** Whether parsing a sequence type */
	private boolean inSequenceType()
	{
	  return sequenceType.isEmpty() ? false : sequenceType.peek();
	}
	 
	 
	/** Restore tokenizer state with the value stored on top of the stack */
	private void restoreState()
	{
		final int state = popState();
		if (state <= -1)
		{
		 	// The query is not valid... recover by going by to the initial state (for now)
		 	//recoveryState = state; // An error message will be shown
		 	yybegin(YYINITIAL); // TODO: can we do better than that?
		}
		else
		  yybegin(state);
	}
	 
	/** 
	 * Try recovering from an invalid token by parsing again from another lexical state
	 */
	private void retry(int nextState)
	{
		if (nextState != zzLexicalState)
		{
		  drop = true;
		  droppedState = zzLexicalState; 
		  
		  yypushback(yylength()); 
		  yybegin(nextState);
		}
	    
	}
	
	/** 
	 * Recover from an invalid token
	 */
	private void recover(int nextState)
	{
		recoveryState = zzLexicalState;
		yybegin(nextState);
	}
	 
	/** Parse pragma content */
	final private void parsePragmaContent() throws IOException
	{
	  // The current char must be a space ({S}).
	  // Search for #)
	  int c;
	  while (!isEOF())
	  {
	     c = yyadvance();
	     if (isEOF())
	       return;
	     if (c == '#')
	     {
	       c = yyadvance();
	       if (isEOF()) return;
	       
	       if (c == ')') 
	       {
	          // found it. Push 2 characters back. 
	          zzMarkedPos = zzCurrentPos - 2;
	          return;
	       }
	     }
	  }
	  
	}
	
	/** Parse XQuery comment */
	final private void parseXQueryComment() throws IOException {
		// The current char must be '('
		// Search for :), ignoring nesting comments

		yyadvance(); // skip '('
		yyadvance(); // skip ':'
		
		int c;
		int nesting = 0;
		while (!isEOF()) {
			c = yyadvance();

			if (isEOF())
			{
				zzMarkedPos = zzCurrentPos;
				return;
			}

			if (c == ':') {
				c = yyadvance();
				if (isEOF())
				{
					zzMarkedPos = zzCurrentPos;
					return;
				}

				if (c == ')') {
					if (nesting == 0)
					{
						// Found the end
						zzMarkedPos = zzCurrentPos;
						return;
					}

					nesting--;
				}
			} else if (c == '(') {
				c = yyadvance();
				if (isEOF())
				{
					zzMarkedPos = zzCurrentPos;
					return;
				}

				if (c == ':')
					nesting++;
			}
		}
		
		zzMarkedPos = zzCurrentPos;
	}
	
	/**
     * Skip XQuery comments and white spaces and try matching the given token
     * 
     * @throws IOException
     */
    protected boolean skipAndMatch(String token) throws IOException {
        int savedCurrentPos = zzCurrentPos;
        int savedMarkedPos = zzMarkedPos;
        zzCurrentPos = zzMarkedPos;
        
        boolean match = false;
        if (skipWhitespace()) 
       		match = match(token);
           
        zzCurrentPos = savedCurrentPos;
        zzMarkedPos = savedMarkedPos;
        return match;
    }
    
    /** 
     * Skip whitespace and XQuery comments.
     * @return true is the current character is non-whitespace  
     */
    protected boolean skipWhitespace() throws IOException {
        int c;
        while (!isEOF()) {
            c = yyadvance();

            if (c == '(') {
                if (isEOF())
                    return false;
                
                c = yyadvance();
                if (c == ':') { // Comment
                    zzCurrentPos -= 2;
                    parseXQueryComment();
                } else {
                	zzCurrentPos -= 2; // Point to '('
                   return true;
                }
            } else if (isWhitespace(c)) {
                // we're good -> skip
            } else {
                // must be the beginning of the token
                zzCurrentPos--;
                return true;
            }
        }
        
        return false;
    }

    protected boolean isWhitespace(int c) {
        return c == '\n' || c == '\r' || c == ' ' || c == '\t';
    }

    protected boolean match(String token) throws IOException {
        int c;
        for (int i = 0; i < token.length(); i++) {
            if (isEOF()) {
               return false;
            }
            c = yyadvance();

            if (c != token.charAt(i)) {
                return false;
            }
        }

        return true;
    }
    
    /**
     * Go to the given state only if the input matches the given token (after skipping spaces and xquery comments) 
     * 
     * If fails, assumes the current token is a PATH_NAMETEST (and go to TS_ENDAXISSTEP)
     *
     * @throws IOException
     */
    protected String lookahead(String token, int pushState, int nextState, String regionName) throws IOException {
    	if (skipAndMatch(token))
    	{
    	    if (pushState != NULL)
    	      pushState(pushState);
    		yybegin(nextState);
    		return regionName;
    	}
    	
    	yybegin(TS_ENDAXISSTEP);
    	return PATH_NAMETEST;
    }
    
    /** 'for' '$' look ahead */
    protected String lookAheadForClause() throws IOException {
    	if (skipAndMatch("$"))
  		{
  		  flowr(FLWORFOR); 
  		  yybegin(TS_FORCLAUSE); 
  		  return KW_FOR;
  		} 
  		
  		yybegin(TS_ENDAXISSTEP);
    	return PATH_NAMETEST;
    }
    
    /** 'let' '$' look ahead */
    protected String lookAheadLetClause() throws IOException {
    	if (skipAndMatch("$"))
  		{
  		  flowr(FLWORLET); 
  		  yybegin(TS_LETCLAUSE); 
  		  return KW_LET;
  		} 
  		
  		yybegin(TS_ENDAXISSTEP);
    	return PATH_NAMETEST;
    }
    
    /** Look for the non-separator token after 'declare' and set the lexer in the proper state */
    protected String lookaheadDeclare(boolean declare)  throws IOException {
    	int savedCurrentPos = zzCurrentPos;
        int savedMarkedPos = zzMarkedPos;
        zzCurrentPos = zzMarkedPos;
        
        int nextState = NULL; 
        
        String expectedToken = null; 
        if (skipWhitespace()) 
        {
        	// Identify the token to match.
        	
        	int c = yyadvance();
        	switch (c)
        	{
        		case 'a': // assignable
        	  		expectedToken = "ssignable";
        	  		nextState = TS_DECLVARSX;
        	      	break;
        		case 'b': // boundary-space, base-uri
        	  		c = yyadvance();
        	    	switch (c)
        	   	 	{
        	    		case 'a':
        	    			expectedToken = "se-uri";
        	    			nextState = TS_BASEURI;
        	    			break;
        	     		case 'o': 
        	     			expectedToken = "undary-space";
        	     			nextState = TS_BOUNDARYSPACE;
        	      			break;
        	    	}
        	    	break;
        	  	case 'c': // copy-namespaces, construction
        	    	c = yyadvance(); 
        	    	if (c == 'o')
        	    	{
        	    		c = yyadvance();
	        	    	switch (c)
        	    		{
        	    			case 'p':
        	    				expectedToken = "y-namespaces";
        	    				nextState = TS_COPYNAMESPACES;
        	    				break;
        	     			case 'n': 
        	     				expectedToken = "struction";
        	     				nextState = TS_DECLCONST;
        	      				break;
        	   	 		}
        	    		break;
        	    	}
        	    	break;
        	  case 'd': // default
        	  		expectedToken = "efault";
        	  		nextState = TS_DECLAREDEFAULT;
        	      	break;
        	  case 'f': // function
        	  		expectedToken = "unction";
        	  		nextState = TS_DECLFUNCTION;
        	      	break;
        	  case 'n': // namespace
        	  		expectedToken = "amespace";
        	  		nextState = TS_NAMESPACEKEYWORD;
        	      	break;
        	  case 'm': // module
        	  		expectedToken = "odule";
        	  		nextState = TS_IMPORTMODULE;
        	      	break;
        	  case 'o': // option, ordering
        	    	c = yyadvance();
        	    	switch (c)
        	   	 	{
        	    		case 'p':
        	    			expectedToken = "tion";
        	    			nextState = TS_DECLAREDEFAULT;
        	    			break;
        	     		case 'r': 
        	     			expectedToken = "dering";
        	     			nextState = TS_ORDERING;
        	      			break;
        	    	}
        	    	break;
        	  case 'r': // revalidation
        	  		expectedToken = "evalidation";
        	     	nextState = TS_DECLREVAL;
        	      	break;
        	  case 's': // simple/sequential/schema
        	  		c = yyadvance();
        	    	switch (c)
        	   	 	{ 
        	    		case 'c':
        	    			expectedToken = "hema";
        	    			nextState = TS_IMPORTSCHEMA;
        	    			break;
        	    		case 'e':
        	    			expectedToken = "quential";
        	    			nextState = TS_DECLFUNCTION;
        	    			break;
        	     		case 'i': 
        	     			expectedToken = "mple";
        	     			nextState = TS_DECLFUNCTION;
        	      			break;
        	    	}
        	    	break;
        	  case 'v': // variable
        	  		expectedToken = "ariable";
        	  		nextState = TS_DECLVAR;
        	      	break;
        	  case 'u': // unassignable, updating
        	  		c = yyadvance();
        	    	switch (c)
        	   	 	{
        	    		case 'n':
        	    			expectedToken = "assignable";
        	    			nextState = TS_DECLVARSX;
        	    			break;
        	     		case 'p': 
        	     			expectedToken = "dating";
        	     			nextState = TS_DECLFUNCTION;
        	      			break;
        	    	}
        	    	break; 
        	}
        	
        }
        
        
        String regionType = declare ? KW_DECLARE : KW_IMPORT;
        boolean match = false; 
        if (expectedToken != null)
        	match = match(expectedToken);
        
        if (match)
        {
         	if (nextState == TS_NAMESPACEKEYWORD)
        	{
        	   // Special case for declare namespace
        	   pushState(TS_PROLOG1); 
        	   pushState(TS_SEPARATOR);
        	}
        }
        else
        {
         	nextState = TS_ENDAXISSTEP;
        	regionType = PATH_NAMETEST;
        }
        
       
         
        // restore lexer position   
        zzCurrentPos = savedCurrentPos;
        zzMarkedPos = savedMarkedPos;
        
         
        yybegin(nextState);
    	return regionType;
    }
    
    /** Look for an occurrence indicator after sequence type */
    protected String lookaheadOccurrenceIndicator(boolean atomicType)  throws IOException {
    	int savedCurrentPos = zzCurrentPos;
        int savedMarkedPos = zzMarkedPos;
        zzCurrentPos = zzMarkedPos;
        
        boolean isOI = false;
        String regionType;
        
        if (skipWhitespace())
        {
           int c = yyadvance();
           isOI = c == '?' || c == '*' || c == '+';
        }
        
        if (isOI)
        {
        	yybegin(TS_OCCINDICATOR); 
        	if (atomicType)
        	{
        		startSequenceType();
        		regionType = ST_ATOMICTYPE;
        	}
        	else
        	{ 
        		regionType = ST_RPAR; 
        	}
        }
        else
        {
          	endSTOrKT();
            restoreState(); 
            regionType = ST_RPAR; 
        }
        
        
         // restore lexer position   
        zzCurrentPos = savedCurrentPos;
        zzMarkedPos = savedMarkedPos;
        
        return regionType;
    }
    
    /** Sequence type lookahead: fallback to atomic type. */
    protected String lookaheadSeqType(boolean start, int nextState, String regionType) throws IOException {
    	int savedCurrentPos = zzCurrentPos;
        int savedMarkedPos = zzMarkedPos;
        zzCurrentPos = zzMarkedPos;
         
        boolean restore = false;
        if (skipWhitespace())
        {
           int c = yyadvance();
           
           if (c == '(')
           {
           		// This is a sequence type. Nothing to do
           }
           else
           {
               	// This is an atomic type. Is the current character an occurence indicator?
           	   	if (c == '?' || c == '*' || c == '+')
           		{
           			start = true;
           			nextState = TS_OCCINDICATOR;
           			regionType = ST_ATOMICTYPE;
           		}
           		else
           		{
           		  	restore = true; 
           		  	start = false;
           		  	regionType = ST_ATOMICTYPE;
           		}
           	}
        }
         
    	 // restore lexer position   
        zzCurrentPos = savedCurrentPos;
        zzMarkedPos = savedMarkedPos;
    	
    	if (start)
    		startSequenceType();
    	if (restore)
    		restoreState();
    	else
    		yybegin(nextState);
    	return regionType;
    }
    	 
	
	// Overrides 
	
	public final boolean isEOF() {
		return zzAtEOF;
	}
	
	 
    public final int getOffset() {
	  return yychar;
    } 
    
    public void reset(char[] charArray) {
	  reset(new CharArrayReader(charArray), 0);
    }
    
    public void reset(char[] charArray, int newOffset) {
	  reset(new CharArrayReader(charArray), newOffset);
    }
    
    public void reset(java.io.InputStream in) {
	  reset(new java.io.InputStreamReader(in), 0);
    }
    public void reset(java.io.InputStream in, int newOffset) {
      reset(new java.io.InputStreamReader(in), newOffset);
    }

    public void reset(java.io.Reader in) {
	  reset(in, 0);
    }
    
    public void reset(java.io.Reader in, int newOffset) {
	  if (Debug.debugTokenizer) {
		System.out.println("resetting tokenizer");//$NON-NLS-1$
	  }
	  
	  yyreset(in);
	  
	  states.reset(); 
	  elementDepths.reset();
	  zzStartRead = newOffset; 
	  recoveryState = -1;  
	  pushState(MODULE);  
	  
	  try
	  {
	    cacheNextToken();
	  } catch (IOException e)
	  {
	    zzAtEOF = true;
	  }
    }
    
    public String getLookAheadToken()
    {
       return nextToken;
    }
    
	  /**
	   * Gets the next input character.
	   *
	   * @return      the next character of the input stream, EOF if the
	   *              end of the stream is reached.
	   * @exception   IOException  if any I/O-Error occurs
	   */
	   private int yyadvance() throws java.io.IOException {
	
	    /* standard case */
	    if (zzCurrentPos < zzEndRead) return zzBuffer[zzCurrentPos++];
	
	    /* if the eof is reached, we don't need to work hard */ 
	    if (zzAtEOF) return YYEOF;
	
	    /* otherwise: need to refill the buffer */
	    boolean eof = zzRefill();
	     
	    if (eof)
	    {
	       zzAtEOF = true; 
	       return YYEOF;
	    }
	    return zzBuffer[zzCurrentPos++];
	  }
    
    
    /**
     * user method
	 *
	 * Converts the raw context String returned by the primGetNextToken()
	 * method into a full ITextRegion by pulling in values for the
	 * current offset within the scanning text.
	 *
	 * Returns null when EOF is encountered and attaches intermittently
	 * discovered whitespace onto the end of useful regions.
	 *
	 */
	public final ITextRegion getNextToken() throws IOException {
	  if (nextToken == null)
	    return null;
	  
	  final String token = nextToken;
	  final int start = yychar;
	  final int textLength = yylength();
	  int length = textLength;
	  int lstate = recoveryState;
	  
	  recoveryState = -1;
	  drop = false; 
	   
	  // Load next token 
	  cacheNextToken();
	  
	  // Collapse white spaces (can never be dropped)
	  if (nextToken == WHITE_SPACE)
	  {
	    length += yylength();
	   	cacheNextToken();
	  }
	  
	   if (drop)
	  {
	     // Show error on previous token.
	     lstate = droppedState;
	  }
	  
	  return regionFactory.createToken(token, start, textLength, length, lstate);
	}
	
	final private void cacheNextToken() throws IOException {
	  do
	  {
	    nextToken = primGetNextToken(); 
	  } while (yylength() == 0 && !isEOF()); // Keep going if nothing has been consumed due to a "retry"
	}

	
%}

// XQuery Macros 
 
// DirCommentConstructor	   ::=   	"<!--" DirCommentContents "-->"
// DirCommentContents	   ::=   	((Char - '-') | ('-' (Char - '-')))*
XMLComment = "<!--"{Char}*"-->"

// CDataSectionContents	   ::=   	(Char* - (Char* ']]>' Char*))
XMLCDATA =  "<![CDATA["{Char}*"]]>" 
   
// DirPIConstructor	   ::=   	"<?" PITarget (S DirPIContents)? "?>"	/* ws: explicit */
// PITarget	           ::=   	 Name - (('X' | 'x') ('M' | 'm') ('L' | 'l'))
// DirPIContents	   ::=   	(Char* - (Char* '?>' Char*))
XMLPI = "<?"{Name}{S}{Char}*"?>"

// Attribute Value
// DirAttributeValue	   ::=   	('"' (EscapeQuot | QuotAttrValueContent)* '"')
//                                  | ("'" (EscapeApos | AposAttrValueContent)* "'")
//	QuotAttrValueContent	   ::=   	QuotAttrContentChar
//	QuotAttrContentChar	   ::=   	Char - ["{}<&]

EscapeQuot = "\"\""
EscapeApos = "''"
QuotAttrContentChar = [\x09\x0A\x0D\x20-\x21\x23-\x25\x27-\x3B\x3D-\x7A\x7C\x7E-\uD7FF\uE000-\uFFFD] 
AposAttrContentChar = [\x09\x0A\x0D\x20-\x25\x28-\x3B\x3D-\x7A\x7C\x7E-\uD7FF\uE000-\uFFFD]  
ElemContentChar = [\x09\x0A\x0D\x20-\x25\x27-\x3B\x3D-\x7A\x7C\x7E-\uD7FF\uE000-\uFFFD]  

PredefinedEntityRef = "&"("lt" | "gt" | "amp" | "quot" | "apos")";"

// XQuery comments
XQueryCommentStart   = {S}*"(:"  

// Occurrence indicator

OccurrenceIndicator="+"|"?"|"*"
 
// Literals

Literal = {NumericLiteral} | {StringLiteral}
NumericLiteral = {IntegerLiteral} | {DecimalLiteral} | {DoubleLiteral}
IntegerLiteral = {Digits}
DecimalLiteral = (\.{Digits}) | ({Digits}\.[0-9]*)
DoubleLiteral = ((\.{Digits}) | ({Digits} (\.[0-9]*)?)) [eE] [+-]? {Digits}
StringLiteral = ("\"" ({PredefinedEntityRef} | {CharRef} | {EscapeQuot} | [^\"])* "\"") 
              | ("'" ({PredefinedEntityRef} | {CharRef} | {EscapeApos} | [^'])* "'")
PredefinedEntityRef = "&" ("lt" | "gt" | "amp" | "quot" | "apos") ";"
Digits	   = [0-9]+
 
// Characters (XML 1.0)

Char = [\x09\x0A\x0D\x20-\uD7FF\uE000-\uFFFD] 
S = [\x20\x09\x0D\x0A]+

// Character classes (XML 1.0)

Letter = ({BaseChar} | {Ideographic})
BaseChar = [\u0041-\u005A\u0061-\u007A\u00C0-\u00D6\u00D8-\u00F6\u00F8-\u00FF\u0100-\u0131\u0134-\u013E\u0141-\u0148\u014A-\u017E\u0180-\u01C3\u01CD-\u01F0\u01F4-\u01F5\u01FA-\u0217\u0250-\u02A8\u02BB-\u02C1\u0386\u0388-\u038A\u038C\u038E-\u03A1\u03A3-\u03CE\u03D0-\u03D6\u03DA\u03DC\u03DE\u03E0\u03E2-\u03F3\u0401-\u040C\u040E-\u044F\u0451-\u045C\u045E-\u0481\u0490-\u04C4\u04C7-\u04C8\u04CB-\u04CC\u04D0-\u04EB\u04EE-\u04F5\u04F8-\u04F9\u0531-\u0556\u0559\u0561-\u0586\u05D0-\u05EA\u05F0-\u05F2\u0621-\u063A\u0641-\u064A\u0671-\u06B7\u06BA-\u06BE\u06C0-\u06CE\u06D0-\u06D3\u06D5\u06E5-\u06E6\u0905-\u0939\u093D\u0958-\u0961\u0985-\u098C\u098F-\u0990\u0993-\u09A8\u09AA-\u09B0\u09B2\u09B6-\u09B9\u09DC-\u09DD\u09DF-\u09E1\u09F0-\u09F1\u0A05-\u0A0A\u0A0F-\u0A10\u0A13-\u0A28\u0A2A-\u0A30\u0A32-\u0A33\u0A35-\u0A36\u0A38-\u0A39\u0A59-\u0A5C\u0A5E\u0A72-\u0A74\u0A85-\u0A8B\u0A8D\u0A8F-\u0A91\u0A93-\u0AA8\u0AAA-\u0AB0\u0AB2-\u0AB3\u0AB5-\u0AB9\u0ABD\u0AE0\u0B05-\u0B0C\u0B0F-\u0B10\u0B13-\u0B28\u0B2A-\u0B30\u0B32-\u0B33\u0B36-\u0B39\u0B3D\u0B5C-\u0B5D\u0B5F-\u0B61\u0B85-\u0B8A\u0B8E-\u0B90\u0B92-\u0B95\u0B99-\u0B9A\u0B9C\u0B9E-\u0B9F\u0BA3-\u0BA4\u0BA8-\u0BAA\u0BAE-\u0BB5\u0BB7-\u0BB9\u0C05-\u0C0C\u0C0E-\u0C10\u0C12-\u0C28\u0C2A-\u0C33\u0C35-\u0C39\u0C60-\u0C61\u0C85-\u0C8C\u0C8E-\u0C90\u0C92-\u0CA8\u0CAA-\u0CB3\u0CB5-\u0CB9\u0CDE\u0CE0-\u0CE1\u0D05-\u0D0C\u0D0E-\u0D10\u0D12-\u0D28\u0D2A-\u0D39\u0D60-\u0D61\u0E01-\u0E2E\u0E30\u0E32-\u0E33\u0E40-\u0E45\u0E81-\u0E82\u0E84\u0E87-\u0E88\u0E8A\u0E8D\u0E94-\u0E97\u0E99-\u0E9F\u0EA1-\u0EA3\u0EA5\u0EA7\u0EAA-\u0EAB\u0EAD-\u0EAE\u0EB0\u0EB2-\u0EB3\u0EBD\u0EC0-\u0EC4\u0F40-\u0F47\u0F49-\u0F69\u10A0-\u10C5\u10D0-\u10F6\u1100\u1102-\u1103\u1105-\u1107\u1109\u110B-\u110C\u110E-\u1112\u113C\u113E\u1140\u114C\u114E\u1150\u1154-\u1155\u1159\u115F-\u1161\u1163\u1165\u1167\u1169\u116D-\u116E\u1172-\u1173\u1175\u119E\u11A8\u11AB\u11AE-\u11AF\u11B7-\u11B8\u11BA\u11BC-\u11C2\u11EB\u11F0\u11F9\u1E00-\u1E9B\u1EA0-\u1EF9\u1F00-\u1F15\u1F18-\u1F1D\u1F20-\u1F45\u1F48-\u1F4D\u1F50-\u1F57\u1F59\u1F5B\u1F5D\u1F5F-\u1F7D\u1F80-\u1FB4\u1FB6-\u1FBC\u1FBE\u1FC2-\u1FC4\u1FC6-\u1FCC\u1FD0-\u1FD3\u1FD6-\u1FDB\u1FE0-\u1FEC\u1FF2-\u1FF4\u1FF6-\u1FFC\u2126\u212A-\u212B\u212E\u2180-\u2182\u3041-\u3094\u30A1-\u30FA\u3105-\u312C\uAC00-\uD7A3]
Ideographic = [\u4E00-\u9FA5\u3007\u3021-\u3029]
CombiningChar = [\u0300-\u0345\u0360-\u0361\u0483-\u0486\u0591-\u05A1\u05A3-\u05B9\u05BB-\u05BD\u05BF\u05C1-\u05C2\u05C4\u064B-\u0652\u0670\u06D6-\u06DC\u06DD-\u06DF\u06E0-\u06E4\u06E7-\u06E8\u06EA-\u06ED\u0901-\u0903\u093C\u093E-\u094C\u094D\u0951-\u0954\u0962-\u0963\u0981-\u0983\u09BC\u09BE\u09BF\u09C0-\u09C4\u09C7-\u09C8\u09CB-\u09CD\u09D7\u09E2-\u09E3\u0A02\u0A3C\u0A3E\u0A3F\u0A40-\u0A42\u0A47-\u0A48\u0A4B-\u0A4D\u0A70-\u0A71\u0A81-\u0A83\u0ABC\u0ABE-\u0AC5\u0AC7-\u0AC9\u0ACB-\u0ACD\u0B01-\u0B03\u0B3C\u0B3E-\u0B43\u0B47-\u0B48\u0B4B-\u0B4D\u0B56-\u0B57\u0B82-\u0B83\u0BBE-\u0BC2\u0BC6-\u0BC8\u0BCA-\u0BCD\u0BD7\u0C01-\u0C03\u0C3E-\u0C44\u0C46-\u0C48\u0C4A-\u0C4D\u0C55-\u0C56\u0C82-\u0C83\u0CBE-\u0CC4\u0CC6-\u0CC8\u0CCA-\u0CCD\u0CD5-\u0CD6\u0D02-\u0D03\u0D3E-\u0D43\u0D46-\u0D48\u0D4A-\u0D4D\u0D57\u0E31\u0E34-\u0E3A\u0E47-\u0E4E\u0EB1\u0EB4-\u0EB9\u0EBB-\u0EBC\u0EC8-\u0ECD\u0F18-\u0F19\u0F35\u0F37\u0F39\u0F3E\u0F3F\u0F71-\u0F84\u0F86-\u0F8B\u0F90-\u0F95\u0F97\u0F99-\u0FAD\u0FB1-\u0FB7\u0FB9\u20D0-\u20DC\u20E1\u302A-\u302F\u3099\u309A]
Digit =  [\u0030-\u0039\u0660-\u0669\u06F0-\u06F9\u0966-\u096F\u09E6-\u09EF\u0A66-\u0A6F\u0AE6-\u0AEF\u0B66-\u0B6F\u0BE7-\u0BEF\u0C66-\u0C6F\u0CE6-\u0CEF\u0D66-\u0D6F\u0E50-\u0E59\u0ED0-\u0ED9\u0F20-\u0F29]
Extender = [\u00B7\u02D0\u02D1\u0387\u0640\u0E46\u0EC6\u3005\u3031-\u3035\u309D-\u309E\u30FC-\u30FE]

// Character and Entity references

CharRef = (&#[0-9]+;|&#x[0-9a-fA-F]+;)

// Names and tokens

NameChar = ({Letter} | {Digit} | "." | "-" | "_" | ":" | {CombiningChar} | {Extender})
Name = ({Letter} | "_" | ":" ) ({NameChar})*

// Namespaces

QName = {PrefixedName} | {UnprefixedName}
PrefixedName = {Prefix}":"{LocalPart}
UnprefixedName = {LocalPart}
Prefix = {NCName}
LocalPart = {NCName}
NCName = {SimpleName}  //  - ({Char}*":"{Char}*)
 
SimpleNameChar = ({Letter} | {Digit} | "." | "-" | "_" | {CombiningChar} | {Extender})
SimpleName = ({Letter} | "_" ) ({SimpleNameChar})* 
 
// States 

%state TS_XQUERYVERSION 
%state TS_XQUERYVERSIONSTRLITERAL
%state TS_XQUERYENCODING
%state TS_XQUERYSTRLITERAL
%state TS_XQUERYVERSIONSEPARATOR
%state TS_MODULESEP
%state TS_LIBRARYORMAIN
%state TS_SEPARATOR
%state TS_COLONCOLON
%state TS_NAMESPACEKEYWORD
%state TS_PROLOG1
%state TS_PROLOG2
%state TS_NSEQUALS
%state TS_NSURILITERAL
%state TS_BOUNDARYSPACE
%state TS_BSPRESERVESTRIP
%state TS_NSDECLDEFAULT
%state TS_NSDECLSEP
%state TS_NSDECLELEMFUNCTION
%state TS_NSDECLNS
%state TS_EFNSURILITERAL
%state TS_ORDERINGKW
%state TS_DOORDER
%state TS_DOEMPTY
%state TS_DOGREATESTLEAST
%state TS_CODEFAULT
%state TS_COURILITERAL
%state TS_BUURILITERAL
%state TS_CONSSTRIPPRESERVE
%state TS_CNPRESERVEMODE
%state TS_CNENDPRESERVEMODE
%state TS_CNINHERITMODE
%state TS_ISPREFIX
%state TS_ISEQUALS
%state TS_ISELEMENT
%state TS_ISLOCATION
%state TS_ISFIRSTLITERAL
%state TS_ISLITERALS
%state TS_ISURI
%state TS_ISNS
%state TS_IMNS
%state TS_IMEQUAL
%state TS_IMURI
%state TS_IMFIRSTLITERAL
%state TS_IMLOCATION
%state TS_IMLITERALS
%state TS_OPTION
%state TS_OPTIONQNAME
%state TS_OPTIONSL
%state TS_ORDERING
%state TS_DEFAULTORDER
%state TS_COPYNAMESPACES
%state TS_COLLATION
%state TS_BASEURI
%state TS_IMPORTSCHEMA
%state TS_IMPORTMODULE
%state TS_DECLVAR
%state TS_VDVAR
%state TS_VDTYPEDECL
%state TS_VDINIT 
%state TS_EXPRVARREF 
%state TS_TYPEDECL
%state TS_NCNAME
%state TS_DECLCONST
%state TS_DECLFUNCTION
%state TS_FDLPAR
%state TS_FDENDPARAMNAME
%state TS_FUNCTIONNAME
%state TS_FIRSTFUNCTIONPARAM
%state TS_FUNCTIONPARAMS
%state TS_FDENDPARAMS
%state TS_FDENDPARAM
%state TS_FDINIT
%state TS_EXPR 
%state TS_EXPROPT  
%state TS_EXPRSINGLE  
%state TS_ENDEXPR
%state TS_ENDPRIMARY
%state TS_ENDPAREXPR 
%state TS_ENDEXPRSINGLE 
%state TS_OPERAND  
%state TS_FORCLAUSE 
%state TS_ENDFORVARREF
%state TS_ENDLETVARREF
%state TS_ENDLETTYPEDECL
%state TS_ENDFORTYPEDECL
%state TS_FORIN
%state TS_LETCLAUSE 
%state TS_IFEXPR
%state TS_ENDIFTEST
%state TS_TYPESWITCHEXPR
%state TS_ENDTSOPERAND
%state TS_ENDCASEVARREF
%state TS_ENDTSSEQUENCETYPE
%state TS_ENDCASEKW 
%state TS_ENDTSDEFAULTKW
%state TS_ENDTSDEFAULTVARREF
%state TS_ORDERBY
%state TS_BY 
%state TS_STABLEORDER
%state TS_ORDERMODIFIER 
%state TS_ORDERMODIFIER2 
%state TS_COLLATIONURI
%state TS_ENDORDERSPEC
%state TS_OMEMPTY
%state TS_FORPOSVAR
%state TS_RPAR
%state TS_FUNCTIONCALLLPAR 
%state TS_ORDEREDLCURLY
%state TS_IOTYPEDECL
%state TS_TATYPEDECL
%state TS_CATYPEDECL
%state TS_ENDAXISSTEP
%state TS_ENDEXPRSINGLE
%state TS_NODETEST 
%state TS_STEPEXPR
%state TS_OPTSTEPEXPR
%state TS_QUANTIFIEDEXPR
%state TS_ENDQUANTIFIEDVARREF
%state TS_ENDQUANTIFIEDTYPEDECL
%state TS_VALIDATEEXPR
%state TS_VALIDATERCURLY
%state TS_COMPDOCUMENT
%state TS_COMPTEXT
%state TS_COMPCOMMENT
%state TS_COMPELEMENTORATTR
%state TS_COMPELEMENTORATTRLCURLY
%state TS_COMPPI
%state TS_PRAGMA
%state TS_PRAGMAQNAME
%state TS_PRAGMACONTENT
%state TS_PRAGMANEXT
%state TS_XMLTAGNAME 
%state TS_XMLENDTAGNAME
%state TS_XMLENDTAGDELIM
%state TS_XMLATTLIST
%state TS_XMLCONTENT
%state TS_XMLATTREQ
%state TS_XMLATTRVALUE
%state TS_XMLQUOTATTRVALUE
%state TS_XMLAPOSATTRVALUE
%state TS_SINK
%state TS_ENDVARREF
%state TS_SINGLETYPE
%state TS_SINGLETYPEQMOREND
%state TS_DECLAREDEFAULT
%state TS_DD_NSORCOLLATIONORORDER

%state TS_EMPTYSEQUENCE
%state TS_EMPTYSEQUENCELPAR
%state TS_ITEM
%state TS_ITEMLPAR
%state TS_DOCUMENTTEST 
%state TS_ELEMENTTESTORSCHEMAELEMENTTESTOPT
%state TS_ELEMENTTEST 
%state TS_SCHEMAELEMENTTEST
%state TS_ELEMENTNAMEORWILDCARDOPT
%state TS_ATTRIBUTETEST
%state TS_PITEST
%state TS_COMMENTTEST
%state TS_TEXTTEST
%state TS_ANYKINDTEST 
%state TS_OCCINDICATOR
%state TS_DOCTESTENDELEMENTTEST
%state TS_ELEMENTTESTCOMMAORRPAR
%state TS_ELEMENTTESTTYPENAME
%state TS_ELEMENTTESTOPTORRPAR
%state TS_ELEMENTTESTRPAR
%state TS_SET_ELEMENTDECLARATION
%state TS_SET_RPAR
%state TS_PIT_NCNAMEORSTRINGOPT
%state TS_PIT_RPAR
%state TS_AT_NAMEORWILDCARDOPT
%state TS_AT_COMMAORRPAR
%state TS_AT_TYPENAME
%state TS_AT_RPAR
%state TS_SCHEMAATTRIBUTETEST
%state TS_SAT_ATTRIBUTEDECLARATION
%state TS_SAT_RPAR


// XQuery Update Facility 1.0 (Candidate Recommentation)

%state TS_DECLREVAL
%state TS_DECLREVAL2
%state TS_FUNCTIONKW
%state TS_INSERTEXPR
%state TS_FIRSTORLAST
%state TS_INSERTINTO
%state TS_DELETEEXPR
%state TS_REPLACEEXPR
%state TS_REPLACEOF
%state TS_REPLACENODE
%state TS_RENAMEEXPR
%state TS_TRANSFORMEXPR
%state TS_ENDCOPYVARREF
 
// XQuery Scripting (W3C Working Draft 8 April 2010)

%state TS_DECLVARSX
%state TS_BLOCK
%state TS_BLOCKVARDECLOPT
%state TS_BLOCKVARNAME
%state TS_BLOCKVARTYPEDECL
%state TS_BLOCKVARINIT
%state TS_EXIT
%state TS_WHILE


// XQuery 1.1 (W3C Working Draft 13 July 2010)
%state TS_GROUPBY
%state TS_GCAFTERVARNAME
%state TS_GCVARNAME
%state TS_GCCOLLATIONURI
%state TS_GCENDGROUPINGSPEC
  
%%

// Prolog

// "xquery" "version" StringLiteral ("encoding" StringLiteral)? Separator

<YYINITIAL> {
  "xquery" / {S}+"version" 			{ yybegin(TS_XQUERYVERSION); return KW_XQUERY; }
  "xquery" / {XQueryCommentStart} 	{ return lookahead("version", NULL, TS_XQUERYVERSION, KW_XQUERY); }
}
<TS_XQUERYVERSION>  "version" { yybegin(TS_XQUERYVERSIONSTRLITERAL); return KW_VERSION; } 

<TS_XQUERYVERSIONSTRLITERAL> {
  {StringLiteral} 	{ yybegin(TS_XQUERYENCODING); return STRINGLITERAL; }
  ";"				{ recover(TS_LIBRARYORMAIN); return SEPARATOR; }
  
}
<TS_XQUERYENCODING>  "encoding" 	{ yybegin(TS_XQUERYSTRLITERAL); return KW_ENCODING; }

<TS_XQUERYSTRLITERAL> {
  {StringLiteral} 	{ yybegin(TS_XQUERYVERSIONSEPARATOR); return STRINGLITERAL; }
  ";"				{ recover(TS_LIBRARYORMAIN); return SEPARATOR; }
  {S}*				{ return WHITE_SPACE; }
  .					{ retry(TS_XQUERYVERSIONSEPARATOR); return UNDEFINED; }
}

<TS_XQUERYVERSIONSEPARATOR, TS_XQUERYENCODING> {	
  ";" 				{ yybegin(TS_LIBRARYORMAIN); return SEPARATOR; }
  {S}*				{ return WHITE_SPACE; }
  .					{ retry(TS_LIBRARYORMAIN); return UNDEFINED; }
}
 
// Handle separator (Restore stacked state)
<TS_SEPARATOR> { 
	";" 					{ restoreState(); return SEPARATOR; } 
	{S}*					{ return WHITE_SPACE; }
	"(:"					{ parseXQueryComment(); return XQUERY_COMMENT;}
    .						{ recover(popState()); return UNDEFINED; }
}

// Handle "namespace" NCName "=" URILiteral  (Restore stacked state)

<TS_NAMESPACEKEYWORD>  	"namespace"		{ pushState(TS_NSEQUALS); yybegin(TS_NCNAME); return KW_NAMESPACE; }
<TS_NSEQUALS> 			"=" 			{ yybegin(TS_NSURILITERAL); return EQUALS; }
<TS_NSURILITERAL>		{StringLiteral} 	{ restoreState(); return URILITERAL; }

// "module" "namespace" NCName "=" URILiteral Separator

<YYINITIAL, TS_LIBRARYORMAIN> {
  "module" / {S}+"namespace" 		{ pushState(TS_MODULESEP); yybegin(TS_NAMESPACEKEYWORD); return KW_MODULE; }
  "module" / {XQueryCommentStart} 	{ return lookahead("namespace", TS_MODULESEP, TS_NAMESPACEKEYWORD, KW_MODULE); } 
}
<TS_MODULESEP> 	";" 				{ yybegin(TS_PROLOG1); return SEPARATOR; }
 
// declare/import XQueryComment rule

<YYINITIAL, TS_LIBRARYORMAIN, TS_PROLOG1, TS_PROLOG2> {
  "declare" / {XQueryCommentStart} 	{ return lookaheadDeclare(true); }  
  "import" / {XQueryCommentStart} 	{ return lookaheadDeclare(false); }  
}

// "declare" "default" dispatcher. 
 
<YYINITIAL, TS_LIBRARYORMAIN, TS_PROLOG1>  "declare" / {S}+"default" 		{ yybegin(TS_DECLAREDEFAULT); return KW_DECLARE; }  
  

<TS_DECLAREDEFAULT> "default" { yybegin(TS_DD_NSORCOLLATIONORORDER); return KW_DEFAULT; }
<TS_DD_NSORCOLLATIONORORDER> {
  "element"			{ yybegin(TS_NSDECLNS); return KW_ELEMENT; }
  "function" 		{ yybegin(TS_NSDECLNS); return KW_DEFAULTFUNCTION; }
  
  "collation" 		{ yybegin(TS_COURILITERAL); return KW_COLLATION; }
  "order" 			{ yybegin(TS_DOEMPTY); return KW_ORDER; }
}

// "declare" "default" ("element" | "function") "namespace" URILiteral Separator
 
<TS_NSDECLNS>			"namespace" 	{ yybegin(TS_EFNSURILITERAL); return KW_NAMESPACE; }
<TS_EFNSURILITERAL>		{StringLiteral}	{ yybegin(TS_NSDECLSEP); return URILITERAL; }
<TS_NSDECLSEP>			";" 			{ yybegin(TS_PROLOG1); return SEPARATOR; }

// 	"declare" "default" "collation" URILiteral Separator
 
<TS_COURILITERAL>  	{StringLiteral} 		{ pushState(TS_PROLOG1); yybegin(TS_SEPARATOR); return URILITERAL; }

// "declare" "default" "order" "empty" ("greatest" | "least") Separator

<TS_DOEMPTY>			"empty" 		{ yybegin(TS_DOGREATESTLEAST); return KW_EMPTY; }
<TS_DOGREATESTLEAST>    "greatest" 		{ pushState(TS_PROLOG1); yybegin(TS_SEPARATOR); return KW_GREATEST; }
<TS_DOGREATESTLEAST>  	"least"   		{ pushState(TS_PROLOG1); yybegin(TS_SEPARATOR); return KW_LEAST; }

// "declare" "boundary-space" ("preserve" | "strip") Separator

<YYINITIAL, TS_LIBRARYORMAIN, TS_PROLOG1> {
  "declare" / {S}+"boundary-space" { yybegin(TS_BOUNDARYSPACE); return KW_DECLARE; }
}

<TS_BOUNDARYSPACE> "boundary-space" { yybegin(TS_BSPRESERVESTRIP); return KW_BOUNDARY_SPACE; }
<TS_BSPRESERVESTRIP> {
  "preserve" 		{ pushState(TS_PROLOG1); yybegin(TS_SEPARATOR); return KW_PRESERVE; }
  "strip" 			{ pushState(TS_PROLOG1); yybegin(TS_SEPARATOR); return KW_STRIP; }
}


// "declare" "base-uri" URILiteral Separator

<YYINITIAL, TS_LIBRARYORMAIN, TS_PROLOG1> "declare" / {S}+"base-uri" { yybegin(TS_BASEURI); return KW_DECLARE; }

<TS_BASEURI> 		"base-uri"  	{ yybegin(TS_BUURILITERAL); return KW_BASEURI; }
<TS_BUURILITERAL> 	{StringLiteral} 	{ pushState(TS_PROLOG1); yybegin(TS_SEPARATOR); return URILITERAL; }


// "declare" "construction" ("strip" | "preserve") Separator

<YYINITIAL, TS_LIBRARYORMAIN, TS_PROLOG1> "declare" / {S}+"construction" { yybegin(TS_DECLCONST); return KW_DECLARE; }

<TS_DECLCONST> 			"construction"			{ yybegin(TS_CONSSTRIPPRESERVE); return KW_CONSTRUCTION; }
<TS_CONSSTRIPPRESERVE>  "strip"					{ pushState(TS_PROLOG1); yybegin(TS_SEPARATOR); return KW_STRIP; }
<TS_CONSSTRIPPRESERVE>  "preserve"				{ pushState(TS_PROLOG1); yybegin(TS_SEPARATOR); return KW_PRESERVE; }

// "declare" "ordering" ("ordered" | "unordered") Separator

<YYINITIAL, TS_LIBRARYORMAIN, TS_PROLOG1> "declare" / {S}+"ordering" { yybegin(TS_ORDERING);  return KW_DECLARE; }

<TS_ORDERING> 			"ordering" 		{ yybegin(TS_ORDERINGKW); return KW_ORDERING; }
<TS_ORDERINGKW>			"ordered" 		{ pushState(TS_PROLOG1); yybegin(TS_SEPARATOR); return KW_ORDERED; }
<TS_ORDERINGKW> 		"unordered"   	{ pushState(TS_PROLOG1); yybegin(TS_SEPARATOR); return KW_UNORDERED; }
 
 

// "declare" "copy-namespaces" PreserveMode "," InheritMode Separator

<YYINITIAL, TS_LIBRARYORMAIN, TS_PROLOG1> "declare" / {S}+"copy-namespaces" { yybegin(TS_COPYNAMESPACES); return KW_DECLARE; }

<TS_COPYNAMESPACES> 		"copy-namespaces" 			{ yybegin(TS_CNPRESERVEMODE); return KW_COPYNAMESPACES; }
<TS_CNPRESERVEMODE>  		"preserve" 					{ yybegin(TS_CNENDPRESERVEMODE); return KW_PRESERVE; }
<TS_CNPRESERVEMODE>  		"no-preserve" 				{ yybegin(TS_CNENDPRESERVEMODE); return KW_NOPRESERVE; }
<TS_CNENDPRESERVEMODE> 		"," 						{ yybegin(TS_CNINHERITMODE);return COMMA; } 
<TS_CNINHERITMODE>  		"inherit" 					{ pushState(TS_PROLOG1); yybegin(TS_SEPARATOR); return KW_INHERIT; }
<TS_CNINHERITMODE>  		"no-inherit" 				{ pushState(TS_PROLOG1); yybegin(TS_SEPARATOR); return KW_NOINHERIT; } 

// declare" "namespace" NCName "=" URILiteral Separator

<YYINITIAL, TS_LIBRARYORMAIN, TS_PROLOG1> "declare" / {S}+"namespace" { pushState(TS_PROLOG1); pushState(TS_SEPARATOR); yybegin(TS_NAMESPACEKEYWORD); return KW_DECLARE; }

// 	SchemaImport	   ::=   	"import" "schema" SchemaPrefix? URILiteral ("at" URILiteral ("," URILiteral)*)? Separator
// 	SchemaPrefix	   ::=   	("namespace" NCName "=") | ("default" "element" "namespace")

<YYINITIAL, TS_LIBRARYORMAIN, TS_PROLOG1> "import" / {S}+"schema" { yybegin(TS_IMPORTSCHEMA); return KW_IMPORT; }

<TS_IMPORTSCHEMA> 				"schema" 				{ yybegin(TS_ISPREFIX); return KW_SCHEMA; }
<TS_ISPREFIX> {				
  "namespace" 		{ pushState(TS_ISEQUALS); yybegin(TS_NCNAME); return KW_NAMESPACE; }
  "default" 		{ yybegin(TS_ISELEMENT); return KW_DEFAULT; }
  
}
<TS_ISPREFIX, TS_ISURI>		{StringLiteral}			{ yybegin(TS_ISFIRSTLITERAL); return URILITERAL; }
<TS_ISEQUALS>				"="						{ yybegin(TS_ISURI); return EQUALS; } 
<TS_ISELEMENT>				"element"				{ yybegin(TS_ISNS); return KW_ELEMENT; }
<TS_ISNS>					"namespace"				{ yybegin(TS_ISURI); return KW_NAMESPACE; }

<TS_ISFIRSTLITERAL> {
  "at"	{ yybegin(TS_ISLOCATION); return KW_AT; }
}
<TS_ISLOCATION> {StringLiteral}	{ yybegin(TS_ISLITERALS); return URILITERAL; }
<TS_ISFIRSTLITERAL, TS_ISLITERALS> {
  ";"   { yybegin(TS_PROLOG1); return SEPARATOR; }
  ","	{ yybegin(TS_ISLOCATION); return COMMA; }
}

// ModuleImport	   ::=   	"import" "module" ("namespace" NCName "=")? URILiteral ("at" URILiteral ("," URILiteral)*)? Separator

<YYINITIAL, TS_LIBRARYORMAIN, TS_PROLOG1> "import" / {S}+"module" { yybegin(TS_IMPORTMODULE); return KW_IMPORT; }

<TS_IMPORTMODULE> "module" 			{ yybegin(TS_IMNS); return KW_MODULE; }
<TS_IMNS> {
  "namespace" 		{ pushState(TS_IMEQUAL); yybegin(TS_NCNAME); return KW_NAMESPACE; } 
}
<TS_IMEQUAL>		 "="			{ yybegin(TS_IMURI); return EQUALS; }
<TS_IMURI, TS_IMNS>	 {StringLiteral}	{ yybegin(TS_IMFIRSTLITERAL); return URILITERAL; }

<TS_IMFIRSTLITERAL> {
  "at"	{ yybegin(TS_IMLOCATION); return KW_AT; }
}
<TS_IMLOCATION> {StringLiteral}	{ yybegin(TS_IMLITERALS); return URILITERAL; }
<TS_IMFIRSTLITERAL, TS_IMLITERALS> {
  ";"   { yybegin(TS_PROLOG1); return SEPARATOR; }
  ","	{ yybegin(TS_IMLOCATION); return COMMA; }
}

// XQuery Update Facility 1.0

// RevalidationDecl	   ::=   	"declare" "revalidation" ("strict" | "lax" | "skip")

<YYINITIAL, TS_LIBRARYORMAIN, TS_PROLOG1> {
  "declare" / {S}+"revalidation" { yybegin(TS_DECLREVAL); return KW_DECLARE; }
}

<TS_DECLREVAL> "revalidation" { yybegin(TS_DECLREVAL2); return KW_REVALIDATION; }
<TS_DECLREVAL2> {
  "strict"		{  pushState(TS_PROLOG1); yybegin(TS_SEPARATOR); return KW_STRICT; }
  "lax"			{  pushState(TS_PROLOG1); yybegin(TS_SEPARATOR); return KW_STRICT; }
  "skip"		{  pushState(TS_PROLOG1); yybegin(TS_SEPARATOR); return KW_STRICT; }
}

// Start second Prolog part


//*********** Variable Declaration

// VarDecl	   ::=   	"declare" "variable" "$" QName TypeDeclaration? ((":=" ExprSingle) | "external") Separator
// 
// XQuery Scripting: 
// VarDecl	   ::=   	"declare" ("unassignable"? | "assignable") "variable" "$" QName TypeDeclaration? ((":=" ExprSingle) | "external")

<YYINITIAL, TS_LIBRARYORMAIN, TS_PROLOG1, TS_PROLOG2> "declare" / {S}+"variable" { yybegin(TS_DECLVAR); return KW_DECLARE; }
<YYINITIAL, TS_LIBRARYORMAIN, TS_PROLOG1, TS_PROLOG2> "declare" / {S}+("unassignable"|"assignable") { yybegin(TS_DECLVARSX); return KW_DECLARE; }

<TS_DECLVAR> 		"variable"		 			{ yybegin(TS_VDVAR); return KW_VARIABLE; }
<TS_DECLVARSX> {
  "unassignable" { yybegin(TS_DECLVAR); return KW_UNASSIGNABLE; }
  "assignable"   { yybegin(TS_DECLVAR); return KW_ASSIGNABLE; }
}

<TS_VDVAR>			"$"	   						{ pushState(TS_VDTYPEDECL); yybegin(TS_EXPRVARREF); return DOLLAR; }
<TS_VDTYPEDECL> {
  "as"				{ pushState(TS_VDINIT); yybegin(TS_TYPEDECL); return KW_AS; }
}
<TS_VDINIT, TS_VDTYPEDECL> {
  ":="				{ pushState(INVARDECLINIT); yybegin(TS_EXPRSINGLE); return ASSIGN; } 
  "external"		{ pushState(TS_PROLOG2); yybegin(TS_SEPARATOR); return KW_EXTERNAL; }
}
//*********** Function Declaration

// FunctionDecl	   ::=   	"declare" "function" QName "(" ParamList? ")" ("as" SequenceType)? (EnclosedExpr | "external") Separator
// EnclosedExpr	   ::=   	"{" Expr "}"

// XQuery Update
// FunctionDecl	   ::=   	"declare" "updating"? "function" QName "(" ParamList? ")" ("as" SequenceType)? (EnclosedExpr | "external")

// XQuery Scripting
// FunctionDecl	   ::=   	("declare" ("simple"? | "updating") "function" QName "(" ParamList? ")" ("as" SequenceType)? (EnclosedExpr | "external"))
//                        | ("declare" "sequential" "function" QName "(" ParamList? ")" ("as" SequenceType)? (Block | "external"))

<YYINITIAL, TS_LIBRARYORMAIN, TS_PROLOG1, TS_PROLOG2> "declare" / {S}+("function"|"updating"|"sequential"|"simple") {    yybegin(TS_DECLFUNCTION); return KW_DECLARE; }

<TS_DECLFUNCTION> 	{
  "updating"				{ yybegin(TS_FUNCTIONKW); return KW_UPDATING; }
  "simple"					{ yybegin(TS_FUNCTIONKW); return KW_SIMPLE; }
  "sequential"				{ yybegin(TS_FUNCTIONKW); return KW_SEQUENTIAL; }
}	

<TS_DECLFUNCTION, TS_FUNCTIONKW> {
  "function"				{ yybegin(TS_FUNCTIONNAME); return KW_FUNCTION; }
}

<TS_FUNCTIONNAME> 	{QName} 				{ yybegin(TS_FDLPAR); return FUNCTIONNAME; }
<TS_FDLPAR>			"("						{ yybegin(TS_FIRSTFUNCTIONPARAM); return LPAR; }
<TS_FDENDPARAMS> {
  "as"			{ pushState(TS_FDINIT); yybegin(TS_TYPEDECL); return KW_AS; } 
 
}
<TS_FDINIT, TS_FDENDPARAMS> {
  "external"	{ pushState(TS_PROLOG2); yybegin(TS_SEPARATOR); return KW_EXTERNAL; }
  "{"			{ pushState(TS_PROLOG2); pushState(TS_SEPARATOR); pushState(CURLYEXPR); yybegin(TS_EXPR); return LCURLY;}  
  
  // XQuery Scripting
  "block"		{ pushState(SXBLOCK); yybegin(TS_BLOCK); return KW_BLOCK; }
}

// ParamList	   ::=   	Param ("," Param)*
// Param	       ::=   	"$" QName TypeDeclaration?
 
<TS_FIRSTFUNCTIONPARAM, TS_FUNCTIONPARAMS> {
  "$"			{ pushState(TS_FDENDPARAMNAME); yybegin(TS_EXPRVARREF); return DOLLAR; }
}
<TS_FDENDPARAMNAME> {
  "as"			{ pushState(TS_FDENDPARAM); yybegin(TS_TYPEDECL); return KW_AS; } 
} 
<TS_FDENDPARAM, TS_FDENDPARAMNAME> {
  ","			{ yybegin(TS_FUNCTIONPARAMS); return COMMA; } 
}
<TS_FIRSTFUNCTIONPARAM, TS_FDENDPARAM, TS_FDENDPARAMNAME> {
  ")"			{ yybegin(TS_FDENDPARAMS); return RPAR; }
}

// "declare" "option" QName StringLiteral Separator

<YYINITIAL, TS_LIBRARYORMAIN, TS_PROLOG1, TS_PROLOG2> "declare" / {S}+"option" { yybegin(TS_OPTION); return KW_DECLARE; } 

<TS_OPTION> 		"option"  			{ yybegin(TS_OPTIONQNAME); return KW_OPTION; }
<TS_OPTIONQNAME> 	{QName} 			{ yybegin(TS_OPTIONSL); return QNAME; }
<TS_OPTIONSL>	 	{StringLiteral} 	{ pushState(TS_PROLOG2); yybegin(TS_SEPARATOR); return STRINGLITERAL; }

// End Prolog

// Initial state for ExprSingle 
<YYINITIAL, TS_LIBRARYORMAIN, TS_PROLOG1, TS_PROLOG2, TS_EXPR, TS_EXPROPT, TS_EXPRSINGLE, TS_BLOCKVARDECLOPT> {
 
  // Non-operand expressions

  // For clause

  "for" / {S}*"$"					{ flowr(FLWORFOR); yybegin(TS_FORCLAUSE); return KW_FOR; }
  "for" / {XQueryCommentStart}		{ return lookAheadForClause(); }
  "let" / {S}*"$"					{ flowr(FLWORLET); yybegin(TS_LETCLAUSE); return KW_LET; }
  "let" / {XQueryCommentStart}		{ return lookAheadLetClause(); }
   
  // Quantified expressions
  
  "some" / {S}*"$"      			{ pushState(QUANTIFIED); yybegin(TS_QUANTIFIEDEXPR); return KW_SOME; }
  "some" / {XQueryCommentStart}		{ return lookahead("$", QUANTIFIED, TS_QUANTIFIEDEXPR, KW_SOME); }
  "every" / {S}*"$"     			{ pushState(QUANTIFIED); yybegin(TS_QUANTIFIEDEXPR); return KW_EVERY; }
  "every" / {XQueryCommentStart}	{ return lookahead("$", QUANTIFIED, TS_QUANTIFIEDEXPR, KW_EVERY); }
  
  // If expression
  
  "if" / {S}*"("        			{ pushState(IF); yybegin(TS_IFEXPR); return KW_IF; }
  "if" / {XQueryCommentStart}  		{ return lookahead("(", IF, TS_IFEXPR, KW_IF); }
  
  
  // TypeSwitch expression
  
  "typeswitch" / {S}*"("        		{ pushState(TYPESWITCH); yybegin(TS_TYPESWITCHEXPR); return KW_TYPESWITCH; }
  "typeswitch" / {XQueryCommentStart}   { return lookahead("(", TYPESWITCH, TS_TYPESWITCHEXPR, KW_TYPESWITCH); }
  
  // XQuery Update Facility 1.0 expressions
  
  "insert" / {S}+("node"|"nodes")  	{ pushState(XUINSERT); yybegin(TS_INSERTEXPR); return KW_INSERT; }
  "insert" / {XQueryCommentStart}  	{ return lookahead("node", XUINSERT, TS_INSERTEXPR, KW_INSERT);  }
  
  "delete" / {S}+("node"|"nodes")  	{ pushState(XUDELETE); yybegin(TS_DELETEEXPR); return KW_DELETE; }
  "delete" / {XQueryCommentStart}  	{ return lookahead("node", XUDELETE, TS_DELETEEXPR, KW_DELETE);  }
  
  "replace" / {S}+"value" 			{ pushState(XUREPLACE); yybegin(TS_REPLACEEXPR); return KW_REPLACE; }
  "replace" / {XQueryCommentStart}  { return lookahead("value", XUREPLACE, TS_REPLACEEXPR, KW_REPLACE);  }
  
  "rename" / {S}+"node"			   { pushState(XURENAME); yybegin(TS_RENAMEEXPR); return KW_RENAME; }
  "rename" / {XQueryCommentStart}  { return lookahead("node", XURENAME, TS_RENAMEEXPR, KW_RENAME);  }
  
  "copy" / {S}*"$"			   	   { pushState(XUTRANSFORM); yybegin(TS_TRANSFORMEXPR); return KW_COPY; }
  "copy" / {XQueryCommentStart}  	{ return lookahead("$", XUTRANSFORM, TS_TRANSFORMEXPR, KW_COPY);  }
  
  // XQuery Scripting Extension 1.0 expressions
  
  "block" / {S}*"{"				{ pushState(TS_ENDEXPRSINGLE); pushState(SXBLOCK); yybegin(TS_BLOCK); return KW_BLOCK; }
  
  "exit" /  {S}+"returning"     { pushState(SXEXIT); yybegin(TS_EXIT); return KW_EXIT; }
  "exit" / {XQueryCommentStart} { return lookahead("returning", SXEXIT, TS_EXIT, KW_EXIT);  }
  
  "while" / {S}*"("				{ pushState(TS_ENDEXPRSINGLE); pushState(SXWHILE); yybegin(TS_WHILE); return KW_WHILE; }
}  

// Operand (OrExpr)
<YYINITIAL, TS_LIBRARYORMAIN, TS_PROLOG1, TS_PROLOG2, TS_OPERAND, TS_EXPR, TS_EXPROPT, TS_EXPRSINGLE, TS_BLOCKVARDECLOPT> { 
  // UnaryExpr

  "+"							{ return OP_PLUS; }
  "-"							{ return OP_MINUS; }
   
  // PathExpr 
   
  "/"							{ yybegin(TS_OPTSTEPEXPR); return PATH_SLASH; }  
  "//"							{ yybegin(TS_STEPEXPR); return PATH_SLASHSLASH; }  
}

// NodeTest

<YYINITIAL, TS_LIBRARYORMAIN, TS_PROLOG1, TS_PROLOG2, TS_OPERAND, TS_EXPR, TS_EXPROPT, TS_EXPRSINGLE, TS_STEPEXPR, TS_OPTSTEPEXPR, TS_BLOCKVARDECLOPT, TS_NODETEST> {
  // NameTest

  {NCName}":""*"						{ yybegin(TS_ENDAXISSTEP); return PATH_NAMETEST; } /* ws: explicit */
  "*"":"{NCName}                		{ yybegin(TS_ENDAXISSTEP); return PATH_NAMETEST; } /* ws: explicit */
  {QName}   							{ yybegin(TS_ENDAXISSTEP); return PATH_NAMETEST; } /* ws: explicit */
  "*"									{ yybegin(TS_ENDAXISSTEP); return PATH_NAMETEST; } 
  
  // NodeTest
  
  "document-node" / {S}*"("					{ pushState(TS_ENDAXISSTEP); yybegin(TS_DOCUMENTTEST); return KT_DOCUMENTNODE; }  
  "document-node" / {XQueryCommentStart} 	{ return lookahead("(", TS_ENDAXISSTEP, TS_DOCUMENTTEST, KT_DOCUMENTNODE); }
  
  "element"	/ {S}*"("						{ pushState(TS_ENDAXISSTEP); yybegin(TS_ELEMENTTEST); return KT_ELEMENT; }
  "element" / {XQueryCommentStart} 			{ return lookahead("(", TS_ENDAXISSTEP, TS_ELEMENTTEST, KT_ELEMENT); }
  
  "attribute" / {S}*"("						{ pushState(TS_ENDAXISSTEP); yybegin(TS_ATTRIBUTETEST); return KT_ATTRIBUTE; }
  "attribute" / {XQueryCommentStart} 		{ 
  												// TODO: optimize
  	
   												// Attribute test?
  												String regionType = lookahead("(", TS_ENDAXISSTEP, TS_ATTRIBUTETEST, KT_ATTRIBUTE); 
  												if (regionType != KT_ATTRIBUTE)
  												{
  													// Axis name?
  												  	regionType = lookahead("::", NULL, TS_COLONCOLON, PATH_ATTRIBUTE);
  												  	if (regionType != PATH_ATTRIBUTE)
  												  	{
  												  		// Computed attribute?
  												  		regionType = lookahead("{", TS_ENDAXISSTEP, TS_COMPELEMENTORATTR, KW_ATTRIBUTE);
  												  	}
  												}
  												
  												return regionType;
  											}
  
  "processing-instruction" / {S}*"("    			{ pushState(TS_ENDAXISSTEP); yybegin(TS_PITEST); return KT_PI; }
  "processing-instruction" / {XQueryCommentStart}   { return lookahead("(", TS_ENDAXISSTEP, TS_PITEST, KT_PI); }
  
  "schema-element" / {S}*"("    					{ pushState(TS_ENDAXISSTEP); yybegin(TS_SCHEMAELEMENTTEST); return KT_SCHEMAELEMENT; }
  "schema-element" / {XQueryCommentStart}     		{ return lookahead("(", TS_ENDAXISSTEP, TS_SCHEMAELEMENTTEST, KT_SCHEMAELEMENT); }
  
  "schema-attribute" / {S}*"("    					{ pushState(TS_ENDAXISSTEP); yybegin(TS_SCHEMAATTRIBUTETEST); return KT_SCHEMAATTRIBUTE; }
  "schema-attribute" / {XQueryCommentStart}     	{ return lookahead("(", TS_ENDAXISSTEP, TS_SCHEMAATTRIBUTETEST, KT_SCHEMAATTRIBUTE); }
  
  "comment" / {S}*"("    				{ pushState(TS_ENDAXISSTEP); yybegin(TS_ITEM); return KT_COMMENT; } // same as item()
  "comment" / {XQueryCommentStart}  	{ return lookahead("(", TS_ENDAXISSTEP, TS_ITEM, KT_COMMENT);  } // same as item()
  
  "text" / {S}*"("    					{ pushState(TS_ENDAXISSTEP); yybegin(TS_ITEM); return KT_TEXT; }// same as item()
  "text" / {XQueryCommentStart}    		{ return lookahead("(", TS_ENDAXISSTEP, TS_ITEM, KT_TEXT);   }// same as item()
  
  "node" / {S}*"("    					{ pushState(TS_ENDAXISSTEP); yybegin(TS_ITEM); return KT_ANYKIND; }// same as item()
  "node" / {XQueryCommentStart} 		{ return lookahead("(", TS_ENDAXISSTEP, TS_ITEM, KT_ANYKIND); }// same as item()
}
 

<YYINITIAL, TS_LIBRARYORMAIN, TS_PROLOG1, TS_PROLOG2, TS_OPERAND, TS_EXPR, TS_EXPROPT, TS_EXPRSINGLE, TS_STEPEXPR, TS_OPTSTEPEXPR, TS_BLOCKVARDECLOPT> {
  
  // Axis Step

  "child" / {S}*"::"							{ yybegin(TS_COLONCOLON); return PATH_CHILD; }
  "child" / {XQueryCommentStart}				{ return lookahead("::", NULL, TS_COLONCOLON, PATH_CHILD); }
  
  "descendant-or-self" / {S}*"::"				{ yybegin(TS_COLONCOLON); return PATH_DESCENDANT_OR_SELF; }
  "descendant-or-self" / {XQueryCommentStart}	{ return lookahead("::", NULL, TS_COLONCOLON, PATH_DESCENDANT_OR_SELF); }
  
  "descendant" / {S}*"::"						{ yybegin(TS_COLONCOLON); return PATH_DESCENDANT; }
  "descendant" / {XQueryCommentStart}			{ return lookahead("::", NULL, TS_COLONCOLON, PATH_DESCENDANT); }
  
  "attribute" / {S}*"::"						{ yybegin(TS_COLONCOLON); return PATH_ATTRIBUTE; }
 // see above
 // "attribute" / {XQueryCommentStart}			{ return lookahead("::", NULL, TS_COLONCOLON, PATH_ATTRIBUTE); }
  
  "self" / {S}*"::"								{ yybegin(TS_COLONCOLON); return PATH_SELF; }
  "self" / {XQueryCommentStart}					{ return lookahead("::", NULL, TS_COLONCOLON, PATH_SELF); }
  
  "following-sibling" / {S}*"::"				{ yybegin(TS_COLONCOLON); return PATH_FOLLOWING_SIBLING; }
  "following-sibling" / {XQueryCommentStart}	{ return lookahead("::", NULL, TS_COLONCOLON, PATH_FOLLOWING_SIBLING); }
  
  "following" / {S}*"::"						{ yybegin(TS_COLONCOLON); return PATH_FOLLOWING; }
  "following" / {XQueryCommentStart}			{ return lookahead("::", NULL, TS_COLONCOLON, PATH_FOLLOWING); }
  
  "parent" / {S}*"::"							{ yybegin(TS_COLONCOLON); return PATH_PARENT; }
  "parent" / {XQueryCommentStart}				{ return lookahead("::", NULL, TS_COLONCOLON, PATH_PARENT); }
  
  "ancestor-or-self" / {S}*"::"					{ yybegin(TS_COLONCOLON); return PATH_ANCESTOR_OR_SELF; }
  "ancestor-or-self" / {XQueryCommentStart}		{ return lookahead("::", NULL, TS_COLONCOLON, PATH_ANCESTOR_OR_SELF); }
  
  "ancestor" / {S}*"::"							{ yybegin(TS_COLONCOLON); return PATH_ANCESTOR; }
  "ancestor" / {XQueryCommentStart}				{ return lookahead("::", NULL, TS_COLONCOLON, PATH_ANCESTOR); }
  
  "preceding-sibling" / {S}*"::"				{ yybegin(TS_COLONCOLON); return PATH_PRECEDING_SIBLING; }
  "preceding-sibling" / {XQueryCommentStart}	{ return lookahead("::", NULL, TS_COLONCOLON, PATH_PRECEDING_SIBLING); }
  
  "preceding" / {S}*"::"						{ yybegin(TS_COLONCOLON); return PATH_PRECEDING; }
  "preceding" / {XQueryCommentStart}			{ return lookahead("::", NULL, TS_COLONCOLON, PATH_PRECEDING); }
  
  "@"											{  yybegin(TS_NODETEST); return PATH_ABBREVATTRIBUTE; }
  
  
   
  // Primary Expr

  {StringLiteral} 				{ yybegin(TS_ENDPRIMARY); return STRINGLITERAL; } 
  {NumericLiteral}  			{ yybegin(TS_ENDPRIMARY); return NUMERICLITERAL; }
  
  // Variable reference or assignment (XQSX)
  "$"							{ pushState(TS_ENDVARREF); yybegin(TS_EXPRVARREF); return DOLLAR; }
 
  "("							{ pushState(TS_ENDPRIMARY); pushState(PAREXPR); yybegin(TS_EXPROPT); return LPAR; }
  
  "."							{ yybegin(TS_ENDPRIMARY); return PATH_CONTEXTITEM; }
  
 
  "ordered" / {S}*"{"   				{ pushState(TS_ENDPRIMARY); yybegin(TS_ORDEREDLCURLY); return KW_ORDERED; }
  "ordered" / {XQueryCommentStart}		{ return lookahead("{", TS_ENDPRIMARY, TS_ORDEREDLCURLY, KW_ORDERED); }
 
  "unordered" / {S}*"{" 				{ pushState(TS_ENDPRIMARY); yybegin(TS_ORDEREDLCURLY); return KW_UNORDERED; }
  "unordered" / {XQueryCommentStart}	{ return lookahead("{", TS_ENDPRIMARY, TS_ORDEREDLCURLY, KW_UNORDERED); }
 
  "validate" / {S}*("lax"|"strict"|"{") 	{ yybegin(TS_VALIDATEEXPR); return KW_VALIDATE; }
  "validate" / {XQueryCommentStart} 		{ 
  												String regionType = lookahead("{", NULL, TS_VALIDATEEXPR, KW_VALIDATE);
  												if (regionType != KW_VALIDATE)
  												{
  													regionType = lookahead("lax", NULL, TS_VALIDATEEXPR, KW_VALIDATE);
  													if (regionType != KW_VALIDATE)
  														regionType = lookahead("strict", NULL, TS_VALIDATEEXPR, KW_VALIDATE);
  												}
  												return regionType; 	
											} 
 
  "(#"							{ pushState(TS_ENDPRIMARY); yybegin(TS_PRAGMA); return LPRAGMA; }
 
  // Computed constructors
   
  "document" / {S}*"{"								{ pushState(TS_ENDPRIMARY); yybegin(TS_COMPDOCUMENT); return KW_DOCUMENT; }
  "element" / {S}*({QName}|"{")						{ pushState(TS_ENDPRIMARY); yybegin(TS_COMPELEMENTORATTR); return KW_ELEMENT; }
  "attribute" / {S}*({QName}|"{")					{ pushState(TS_ENDPRIMARY); yybegin(TS_COMPELEMENTORATTR); return KW_ATTRIBUTE; }
  "text" / {S}*"{"									{ pushState(TS_ENDPRIMARY); yybegin(TS_COMPTEXT); return KW_TEXT; }
  "comment" / {S}*"{"								{ pushState(TS_ENDPRIMARY); yybegin(TS_COMPCOMMENT); return KW_COMMENT; }
  "processing-instruction" / {S}*("{"|{NCName})     { pushState(TS_ENDPRIMARY); yybegin(TS_COMPPI); return KW_PI; }
 
  // Direct contructors
  
  {XMLComment}				    { yybegin(TS_ENDPRIMARY); return XML_COMMENT; }
  {XMLPI}						{ yybegin(TS_ENDPRIMARY); return XML_PI; }
  "<"							{ startXML(); pushState(TS_ENDPRIMARY); yybegin(TS_XMLTAGNAME); return XML_TAG_OPEN; }
  
  // Function call
  {QName} / {S}*"("				 { yybegin(TS_FUNCTIONCALLLPAR); return FUNCTIONNAME; }
  {QName} / {XQueryCommentStart} { return lookahead("(", NULL, TS_FUNCTIONCALLLPAR, FUNCTIONNAME);  }  
}

<TS_COLONCOLON> "::"			{  yybegin(TS_NODETEST); return COLONCOLON; }


// Delimiters

<TS_EXPROPT, TS_ENDPRIMARY, TS_SINGLETYPEQMOREND, TS_ENDAXISSTEP, TS_OPTSTEPEXPR, TS_ENDVARREF> {
  "]"				{ 
						check(endExprSingle(), PREDICATEEXPR); 
						yybegin(TS_ENDPRIMARY); 
						return RSQUARE; 
					}
					
  ")"				{ 
  						check(endExprSingle(), PAREXPR); 
  						restoreState(); // Retrieve lexical state contination push on the stack  
  						return RPAR;
  					}  
 
}

<TS_EXPROPT, TS_ENDPRIMARY, TS_SINGLETYPEQMOREND, TS_ENDAXISSTEP, TS_OPTSTEPEXPR, TS_ENDEXPRSINGLE, TS_ENDVARREF> {
  
  "}"				{ 
   						check(endExprSingle(), CURLYEXPR, SXBLOCK, SXWHILE); 
   						restoreState();  // Retrieve lexical state contination push on the stack  
   						return RCURLY; 
   					} 
} 

<TS_ENDPRIMARY, TS_SINGLETYPEQMOREND, TS_ENDAXISSTEP, TS_OPTSTEPEXPR, TS_ENDVARREF> {
  "["							{ pushState(PREDICATEEXPR); yybegin(TS_EXPR); return LSQUARE; }  
  
  "/" 							{ yybegin(TS_STEPEXPR); return PATH_SLASH; }
  "//" 							{ yybegin(TS_STEPEXPR); return PATH_SLASHSLASH; }
}

<TS_ENDPRIMARY, TS_SINGLETYPEQMOREND, TS_ENDAXISSTEP, TS_OPTSTEPEXPR, TS_ENDEXPRSINGLE,TS_ENDVARREF,TS_GCAFTERVARNAME,TS_GCENDGROUPINGSPEC> {
  ","							{ return comma();  } 
  ";"							{ return semicolon();  }    
}

<TS_ENDVARREF> {
  ":="							{ pushState(SXASSIGN); pushState(SXASSIGNRHS); yybegin(TS_EXPRSINGLE); return ASSIGN; } // Assignement
}
 
// Operators  

<TS_ENDPRIMARY, TS_SINGLETYPEQMOREND, TS_ENDAXISSTEP, TS_OPTSTEPEXPR, TS_ENDVARREF> { 
  "or"	 							{  yybegin(TS_OPERAND); return OP_OR; }
  "and"	 							{  yybegin(TS_OPERAND); return OP_AND; }
  "to"	 							{  yybegin(TS_OPERAND); return OP_TO; }
  
  "+"	 							{  yybegin(TS_OPERAND); return OP_PLUS; }
  "-"	 							{  yybegin(TS_OPERAND); return OP_MINUS; }
  "*"	 							{  yybegin(TS_OPERAND); return OP_MULTIPLY; }
  "div"	 							{  yybegin(TS_OPERAND); return OP_DIV; }
  "idiv"  							{  yybegin(TS_OPERAND); return OP_IDIV; }
  "mod"	 							{  yybegin(TS_OPERAND); return OP_MOD; }
  "union" 							{  yybegin(TS_OPERAND); return OP_UNION; }
  "|"								{  yybegin(TS_OPERAND); return OP_PIPE; }
  "intersect"  						{  yybegin(TS_OPERAND); return OP_INTERSECT; }
  "except"  						{  yybegin(TS_OPERAND); return OP_EXCEPT; }
  "instance" 						{  yybegin(TS_IOTYPEDECL); return OP_INSTANCEOF; }
  "treat"  			 				{  yybegin(TS_TATYPEDECL); return OP_TREATAS; }
  "castable" 						{  yybegin(TS_CATYPEDECL); return OP_CASTABLEAS; }
  "cast" 			 				{  yybegin(TS_CATYPEDECL); return OP_CASTAS; }
 
  "=" 								{  yybegin(TS_OPERAND); return OP_GEQ; }
  "!="								{  yybegin(TS_OPERAND); return OP_GNEQ; }
  "<" 								{  yybegin(TS_OPERAND); return OP_GLT; }
  "<="								{  yybegin(TS_OPERAND); return OP_GLTE; }
  ">" 								{  yybegin(TS_OPERAND); return OP_GGT; }
  ">="								{  yybegin(TS_OPERAND); return OP_GGTE; }
  "ne" 								{  yybegin(TS_OPERAND); return OP_NEQ; }
  "eq" 								{  yybegin(TS_OPERAND); return OP_EQ; }
  "lt"								{  yybegin(TS_OPERAND); return OP_LT; }
  "le"								{  yybegin(TS_OPERAND); return OP_LTE; }
  "gt"								{  yybegin(TS_OPERAND); return OP_GT; }
  "ge"								{  yybegin(TS_OPERAND); return OP_GTE; }
  "is"								{  yybegin(TS_OPERAND); return OP_IS; }
  "<<"								{  yybegin(TS_OPERAND); return OP_BEFORE; }
  ">>"								{  yybegin(TS_OPERAND); return OP_AFTER; }
}
   

// Primary variable reference. Look at state for continuation.
<TS_EXPRVARREF> {QName} 		{ restoreState(); return VARREF; } 

// ForClause		::= "for" "$" VarName TypeDeclaration? PositionalVar? "in" ExprSingle  ("," "$" VarName TypeDeclaration? PositionalVar? "in" ExprSingle)*
// PositionalVar	::=  "at" "$" VarName

<TS_FORCLAUSE> 							"$"  	{ pushState(TS_ENDFORVARREF); yybegin(TS_EXPRVARREF); return DOLLAR; }
<TS_ENDFORVARREF> 						"as"	{ pushState(TS_ENDFORTYPEDECL); yybegin(TS_TYPEDECL); return KW_AS; }
<TS_ENDFORVARREF, TS_ENDFORTYPEDECL>  	"at" 	{ yybegin(TS_FORPOSVAR); return KW_AT; }  
<TS_FORPOSVAR>  					 	"$"		{ pushState(TS_FORIN); yybegin(TS_EXPRVARREF); return DOLLAR; }
<TS_ENDFORVARREF, TS_ENDFORTYPEDECL, TS_FORIN> {
  "in"			{   pushState(FLCLAUSEEXPR); yybegin(TS_EXPRSINGLE); return KW_IN;  } 
}
 
// ExprSingle Delimiters for let/for clause

<TS_ENDPRIMARY, TS_SINGLETYPEQMOREND, TS_ENDAXISSTEP, TS_OPTSTEPEXPR, TS_ENDVARREF,TS_GCAFTERVARNAME, TS_GCENDGROUPINGSPEC> {
  "for" / {S}*"$"					{ check(endExprSingle(), FLCLAUSEEXPR); checkTop(FLWORFOR, FLWORLET); popState(); pushState(FLWORFOR); yybegin(TS_FORCLAUSE); return KW_FOR; } 
  "let" / {S}*"$" 					{ check(endExprSingle(), FLCLAUSEEXPR); checkTop(FLWORFOR, FLWORLET); popState(); pushState(FLWORLET); yybegin(TS_LETCLAUSE); return KW_LET; }
  "where" 							{ 
  										check(endExprSingle(), FLCLAUSEEXPR);
  										checkTop(FLWORFOR, FLWORLET);
  										pushState(WHEREEXPR); 
  										yybegin(TS_EXPRSINGLE); 
  										return KW_WHERE; 
  									}
  "order" 							{ 
  										check(endExprSingle(), FLCLAUSEEXPR, WHEREEXPR); 
  										checkTop(FLWORFOR, FLWORLET);
  										yybegin(TS_BY); 
  										return KW_ORDER; 
  									}
  "stable"  						{ 
  										check(endExprSingle(), FLCLAUSEEXPR, WHEREEXPR);
  										checkTop(FLWORFOR, FLWORLET);
  										yybegin(TS_STABLEORDER); 
  										return KW_STABLE; 
  									}
  									
  "group"  							{
  										endExprSingle();
  										pushState(GROUPBY);
  										yybegin(TS_GROUPBY); 
  										return KW_GROUP; 
  									}					
  "return" 							{ 
  										return returnkw();
  									}  
} 

// stable by clause

<TS_STABLEORDER> 	"order" 		{ yybegin(TS_BY); return KW_ORDER; }
<TS_BY> 			"by" 			{ pushState(ORDEREXPR); yybegin(TS_EXPRSINGLE); return KW_BY; }  
<TS_ENDPRIMARY, TS_SINGLETYPEQMOREND, TS_ENDAXISSTEP, TS_OPTSTEPEXPR,TS_ENDVARREF> {
  "ascending"			{ checkTop(ORDEREXPR); yybegin(TS_ORDERMODIFIER); return KW_ASCENDING; } // The OrderExpr will be closed when finishing OrderSpec
  "descending"			{ checkTop(ORDEREXPR); yybegin(TS_ORDERMODIFIER); return KW_DESCENDING; }
}

<TS_ENDPRIMARY, TS_SINGLETYPEQMOREND, TS_ENDAXISSTEP, TS_OPTSTEPEXPR, TS_ORDERMODIFIER,TS_ENDVARREF> {
  "empty"				{ checkTop(ORDEREXPR); yybegin(TS_OMEMPTY); return KW_EMPTY; }		
}

<TS_OMEMPTY> {
  "greatest"		{ yybegin(TS_ORDERMODIFIER2); return KW_GREATEST; }
  "least"			{ yybegin(TS_ORDERMODIFIER2); return KW_LEAST; }
}

<TS_ENDPRIMARY, TS_SINGLETYPEQMOREND, TS_ENDAXISSTEP, TS_OPTSTEPEXPR, TS_ORDERMODIFIER, TS_ORDERMODIFIER2,TS_ENDVARREF>  "collation"	{ checkTop(ORDEREXPR); yybegin(TS_COLLATIONURI); return KW_COLLATION; }
<TS_COLLATIONURI> {StringLiteral} { yybegin(TS_ENDORDERSPEC); return URILITERAL; }

<TS_ORDERMODIFIER, TS_ORDERMODIFIER2, TS_ENDORDERSPEC> {
  ","			{ check(endExprSingle(), ORDEREXPR); checkTop(FLWORFOR, FLWORLET); pushState(ORDEREXPR); yybegin(TS_EXPRSINGLE); return COMMA; }  
  "return"		{ check(endExprSingle(), ORDEREXPR); checkTop(FLWORFOR, FLWORLET); pushState(RETURNEXPR); yybegin(TS_EXPRSINGLE); return KW_RETURN; }
}

<TS_IOTYPEDECL> "of" { pushState(TS_ENDPRIMARY); yybegin(TS_TYPEDECL); return OP_OF; }
<TS_TATYPEDECL> "as" { pushState(TS_ENDPRIMARY); yybegin(TS_TYPEDECL); return OP_AS; }

<TS_CATYPEDECL> "as" {  
						yybegin(TS_SINGLETYPE);// Expect a SingleType
					    return OP_AS;
					 }
					 
<TS_SINGLETYPE> {QName} { yybegin(TS_SINGLETYPEQMOREND); return ST_ATOMICTYPE; }
<TS_SINGLETYPEQMOREND> "?" { yybegin(TS_ENDPRIMARY); return OCC_OPTIONAL; }

// Let clause
// LetClause	   ::=   	"let" "$" VarName TypeDeclaration? ":=" ExprSingle ("," "$" VarName TypeDeclaration? ":=" ExprSingle)*

<TS_LETCLAUSE>  	"$"			{ pushState(TS_ENDLETVARREF); yybegin(TS_EXPRVARREF); return DOLLAR; }
<TS_ENDLETVARREF>   "as"		{ pushState(TS_ENDLETTYPEDECL); yybegin(TS_TYPEDECL); return KW_AS; }
<TS_ENDLETVARREF, TS_ENDLETTYPEDECL>  ":="	{ pushState(FLCLAUSEEXPR); yybegin(TS_EXPRSINGLE); yybegin(TS_EXPRSINGLE); return KW_LETASSIGN; } 
 
 
// group by clause (XQuery 1.1)
<TS_GROUPBY> "by" 	{ yybegin(TS_GCVARNAME); return KW_BY; }
 
<TS_GCVARNAME> {
  "$"				{ pushState(TS_GCAFTERVARNAME); yybegin(TS_EXPRVARREF); return DOLLAR; }  
}
 
<TS_GCAFTERVARNAME> {
  "collation" 		{ yybegin(TS_GCCOLLATIONURI); return KW_COLLATION; }
} 
 
<TS_GCCOLLATIONURI> {
 {StringLiteral}	{ yybegin(TS_GCENDGROUPINGSPEC); return STRINGLITERAL; }
} 
 
// ===== Quantified expression
// ("some" | "every") "$" VarName TypeDeclaration? "in" ExprSingle ("," "$" VarName TypeDeclaration? "in" ExprSingle)* "satisfies" ExprSingle

<TS_QUANTIFIEDEXPR> "$" { pushState(TS_ENDQUANTIFIEDVARREF); yybegin(TS_EXPRVARREF); return DOLLAR; }
<TS_ENDQUANTIFIEDVARREF>  "as" { pushState(TS_ENDQUANTIFIEDTYPEDECL); yybegin(TS_TYPEDECL); return KW_AS; }
<TS_ENDQUANTIFIEDVARREF, TS_ENDQUANTIFIEDTYPEDECL> "in" { pushState(QUANTIFIEDINEXPR); yybegin(TS_EXPRSINGLE); return KW_IN; } 

// ExprSingle delimiters for quantified expression
<TS_ENDPRIMARY, TS_SINGLETYPEQMOREND, TS_ENDAXISSTEP, TS_OPTSTEPEXPR, TS_ENDVARREF> "satisfies" { check(endExprSingle(), QUANTIFIEDINEXPR); pushState(QUANTIFIEDSATIFIESEXPR); yybegin(TS_EXPRSINGLE); return KW_SATIFIES; }

// ===== If expression
// "if" "(" Expr ")" "then" ExprSingle "else" ExprSingle

<TS_IFEXPR> 	"(" 		{ pushState(TS_ENDIFTEST); pushState(PAREXPR); yybegin(TS_EXPR); return LPAR; }
<TS_ENDIFTEST> 	"then" 		{ pushState(IFTHENEXPR); yybegin(TS_EXPRSINGLE); return KW_THEN; }

// ExprSingle delimiters for if expression
<TS_ENDPRIMARY, TS_SINGLETYPEQMOREND, TS_ENDAXISSTEP, TS_OPTSTEPEXPR, TS_ENDVARREF> "else" { check(endExprSingle(), IFTHENEXPR); pushState(IFELSEEXPR); yybegin(TS_EXPRSINGLE); return KW_ELSE; }

// ===== typesswitch expression
// "typeswitch" "(" Expr ")" CaseClause+ "default" ("$" VarName)? "return" ExprSingle
// "case" ("$" VarName "as")? SequenceType "return" ExprSingle

<TS_TYPESWITCHEXPR> 	"("		{  pushState(TS_ENDTSOPERAND); pushState(PAREXPR); yybegin(TS_EXPR); return LPAR; }
<TS_ENDTSOPERAND>  		"case" 	{ pushState(TS_ENDTSSEQUENCETYPE); yybegin(TS_ENDCASEKW); return KW_CASE; }
<TS_ENDCASEKW> {
  "$"				{ pushState(TS_ENDCASEVARREF); yybegin(TS_EXPRVARREF); return DOLLAR;}
  // See SequenceType section below 
}
<TS_ENDCASEVARREF> "as" { yybegin(TS_TYPEDECL);  return KW_AS; } // No need to push continuation state because it's done earlier.
<TS_ENDTSSEQUENCETYPE> "return" { yybegin(TS_EXPRSINGLE); return KW_RETURN; }

// ExprSingle delimiters for case expression
<TS_ENDPRIMARY, TS_SINGLETYPEQMOREND, TS_ENDAXISSTEP, TS_OPTSTEPEXPR, TS_ENDEXPRSINGLE,TS_ENDVARREF> {
  "case" 			{ pushState(TS_ENDTSSEQUENCETYPE); yybegin(TS_ENDCASEKW); return KW_CASE; }
  "default" 		{ yybegin(TS_ENDTSDEFAULTKW); return KW_DEFAULT; }
}

<TS_ENDTSDEFAULTKW>  "$"  { pushState(TS_ENDTSDEFAULTVARREF); yybegin(TS_EXPRVARREF); return DOLLAR;}
<TS_ENDTSDEFAULTVARREF, TS_ENDTSDEFAULTKW> "return" { pushState(TYPESWITCHDEFAULT); yybegin(TS_EXPRSINGLE); return KW_RETURN; }

// ===== Function call
// FunctionCall	   ::=   	QName "(" (ExprSingle ("," ExprSingle)*)? ")"

<TS_FUNCTIONCALLLPAR> "(" { pushState(TS_ENDPRIMARY); pushState(PAREXPR); yybegin(TS_EXPROPT); return LPAR; }
 
// ==== Ordered/Unordered expression
<TS_ORDEREDLCURLY> "{" { pushState(CURLYEXPR); yybegin(TS_EXPR); return LCURLY; }

// ==== Validate expression
<TS_VALIDATEEXPR> {
  "lax"			{ yybegin(TS_VALIDATERCURLY); return KW_LAX; }
  "strict"      { yybegin(TS_VALIDATERCURLY); return KW_STRICT; }
}

<TS_VALIDATEEXPR, TS_VALIDATERCURLY>  "{"  { pushState(TS_ENDPRIMARY); pushState(CURLYEXPR); yybegin(TS_EXPR); return LCURLY; }


// ===== Pragma
<TS_PRAGMA> {S}							{ yybegin(TS_PRAGMAQNAME); return WHITE_SPACE; }
<TS_PRAGMA, TS_PRAGMAQNAME> {QName}		{ yybegin(TS_PRAGMACONTENT); return PRAGMAQNAME; }
<TS_PRAGMACONTENT> {
  "#)"			{ yybegin(TS_PRAGMANEXT); return RPRAGMA; }
  {S} 			{ parsePragmaContent(); return PRAGMACONTENT; }
}
<TS_PRAGMANEXT> {
  "(#"  			{ yybegin(TS_PRAGMA); return LPRAGMA; }
  "{"  			  	{ pushState(CURLYEXPR); yybegin(TS_EXPROPT); return LCURLY; }
}

// SequenceType/KindTest  (Look at state stack for continuation)

// SequenceType	   			::=  ("empty-sequence" "(" ")") | (ItemType OccurrenceIndicator?)
// OccurrenceIndicator	  	::=  "?" | "*" | "+"
// ItemType	   	  	 		::=  KindTest | ("item" "(" ")") | AtomicType
// KindTest	   ::=   	DocumentTest | ElementTest | AttributeTest | SchemaElementTest | SchemaAttributeTest 
//                     | PITest | CommentTest | TextTest | AnyKindTest

<TS_TYPEDECL, TS_ENDCASEKW> { 
  "document-node" / {S}*"("					{ startSequenceType(); yybegin(TS_DOCUMENTTEST); return KT_DOCUMENTNODE; } 
  "document-node" / {XQueryCommentStart}	{ return lookaheadSeqType(true, TS_DOCUMENTTEST, KT_DOCUMENTNODE); }
  
  "element"	/ {S}*"("					{ startSequenceType(); yybegin(TS_ELEMENTTEST); return KT_ELEMENT; }
  "element" / {XQueryCommentStart}		{ return lookaheadSeqType(true, TS_ELEMENTTEST, KT_ELEMENT); }
  
  "attribute" / {S}*"("					{ startSequenceType(); yybegin(TS_ATTRIBUTETEST); return KT_ATTRIBUTE; }
  "attribute" / {XQueryCommentStart}	{ return lookaheadSeqType(true, TS_ATTRIBUTETEST, KT_ATTRIBUTE); }
  
  "processing-instruction" / {S}*"("    			{ startSequenceType(); yybegin(TS_PITEST); return KT_PI; }
  "processing-instruction" / {XQueryCommentStart}	{ return lookaheadSeqType(true, TS_PITEST, KT_PI); }
  
  "schema-element" / {S}*"("    			{ startSequenceType(); yybegin(TS_SCHEMAELEMENTTEST); return KT_SCHEMAELEMENT; }
  "schema-element" / {XQueryCommentStart}   { return lookaheadSeqType(true, TS_SCHEMAELEMENTTEST, KT_SCHEMAELEMENT); }
  
  "schema-attribute" / {S}*"("    			{ startSequenceType(); yybegin(TS_SCHEMAATTRIBUTETEST); return KT_SCHEMAATTRIBUTE; }
  "schema-attribute" / {XQueryCommentStart}	{ return lookaheadSeqType(true, TS_SCHEMAATTRIBUTETEST, KT_SCHEMAATTRIBUTE); }
 
  "comment" / {S}*"("    				{ startSequenceType(); yybegin(TS_ITEM); return KT_COMMENT; } // Same as item()
  "comment" / {XQueryCommentStart}		{ return lookaheadSeqType(true, TS_ITEM, KT_COMMENT); }
 
  "text" / {S}*"("    					{ startSequenceType(); yybegin(TS_ITEM); return KT_TEXT; } // Same as item()
  "text" / {XQueryCommentStart}			{ return lookaheadSeqType(true, TS_ITEM, KT_TEXT); }
  
  "node" / {S}*"("    					{ startSequenceType(); yybegin(TS_ITEM); return KT_ANYKIND; } // Same as item()
  "node" / {XQueryCommentStart}			{ return lookaheadSeqType(true, TS_ITEM, KT_ANYKIND); }
  
  "empty-sequence" / {S}*"(" 				{ yybegin(TS_EMPTYSEQUENCE); return ST_EMPTY; } 
  "empty-sequence" / {XQueryCommentStart}	{ return lookaheadSeqType(false, TS_EMPTYSEQUENCE, ST_EMPTY); }
  
  "item" / {S}*"(" 							{ startSequenceType(); yybegin(TS_ITEM); return ST_ITEM; }
  "item" / {XQueryCommentStart}				{ return lookaheadSeqType(true, TS_ITEM, ST_ITEM); }
  
  {QName}								{ restoreState(); return ST_ATOMICTYPE;}
  {QName} / {S}*{OccurrenceIndicator}	{ startSequenceType(); yybegin(TS_OCCINDICATOR); return ST_ATOMICTYPE;} 
  {QName} / {XQueryCommentStart}		{ return lookaheadOccurrenceIndicator(true); }
}
 
// Sequence type and kind test continuation rules

// Empty sequence
<TS_EMPTYSEQUENCE> 		"("		{ yybegin(TS_EMPTYSEQUENCELPAR); return ST_LPAR; }
<TS_EMPTYSEQUENCELPAR> 	")" 	{ endSTOrKT(); restoreState(); return ST_RPAR; }

// item()

<TS_ITEM>  "("		{ yybegin(TS_ITEMLPAR); return ST_LPAR; }
<TS_ITEMLPAR>	{
  ")"	 							{ endSTOrKT(); restoreState(); return ST_RPAR; }
  ")" /	{XQueryCommentStart} 		{ return lookaheadOccurrenceIndicator(false); }
  ")" / {S}*{OccurrenceIndicator}   { yybegin(TS_OCCINDICATOR); return ST_RPAR; }
}
 
// Occurrence Indicator

<TS_OCCINDICATOR> {
  "+"	{ 
  			restoreState(); 
  			if (endSTOrKT())
  			   return OCC_ONEORMORE;
  			
  			// That's an operator
  			yybegin(TS_OPERAND);  
  		    return OP_PLUS; 
  		}
  "*"	{ 
  			restoreState(); 
  			if (endSTOrKT())
  			   return OCC_ZEROORMORE;
  			
  			// That's an operator
  			yybegin(TS_OPERAND);  
  		    return OP_MULTIPLY;
  		}  
  
  "?"	{ restoreState(); return OCC_OPTIONAL; }
} 
 
// Document test
// "document-node" "(" (ElementTest | SchemaElementTest)? ")"

<TS_DOCUMENTTEST> 	"("	{ yybegin(TS_ELEMENTTESTORSCHEMAELEMENTTESTOPT); return ST_LPAR; } 


<TS_ELEMENTTESTORSCHEMAELEMENTTESTOPT> {
  ")"  								{ endSTOrKT(); restoreState(); return ST_RPAR; }
  ")" / {S}*{OccurrenceIndicator}	{ yybegin(TS_OCCINDICATOR); return ST_RPAR; }	
  ")" /	{XQueryCommentStart} 		{ return lookaheadOccurrenceIndicator(false); }			
  
  "element" / {S}*"("				{ pushState(TS_DOCTESTENDELEMENTTEST); startKindTest(); yybegin(TS_ELEMENTTEST); return KT_ELEMENT; }
  "schema-element" / {S}*"("		{ pushState(TS_DOCTESTENDELEMENTTEST); startKindTest(); yybegin(TS_SCHEMAELEMENTTEST); return KT_SCHEMAELEMENT; } 
}

<TS_DOCTESTENDELEMENTTEST>	{
  ")"		{ endSTOrKT(); restoreState(); return ST_RPAR; }
}

// Element Test
// "element" "(" (ElementNameOrWildcard ("," TypeName "?"?)?)? ")"

<TS_ELEMENTTEST> "(" 	{ yybegin(TS_ELEMENTNAMEORWILDCARDOPT); return ST_LPAR; }
<TS_ELEMENTNAMEORWILDCARDOPT, TS_ELEMENTTESTCOMMAORRPAR, TS_ELEMENTTESTOPTORRPAR, TS_ELEMENTTESTRPAR> {
  ")"								{ restoreState(); return ST_RPAR; }
  ")" / {S}*{OccurrenceIndicator}	{ yybegin(TS_OCCINDICATOR); return ST_RPAR; }
  ")" /	{XQueryCommentStart} 		{ return lookaheadOccurrenceIndicator(false); }
}

<TS_ELEMENTNAMEORWILDCARDOPT> {
  "*"								{ yybegin(TS_ELEMENTTESTCOMMAORRPAR); return KT_WILDCARD; }
  {QName}							{ yybegin(TS_ELEMENTTESTCOMMAORRPAR); return KT_QNAME; }
}

<TS_ELEMENTTESTCOMMAORRPAR> {
  ","								{ yybegin(TS_ELEMENTTESTTYPENAME); return KT_COMMA; }
}

<TS_ELEMENTTESTTYPENAME> 	{QName}	{ yybegin(TS_ELEMENTTESTOPTORRPAR); return KT_QNAME; }
<TS_ELEMENTTESTOPTORRPAR>	"?"		{ yybegin(TS_ELEMENTTESTRPAR); return OCC_OPTIONAL; }

// Schema Element Test
// "schema-element" "(" ElementDeclaration ")"

<TS_SCHEMAELEMENTTEST> 			"(" 		{ yybegin(TS_SET_ELEMENTDECLARATION); return ST_LPAR; }
<TS_SET_ELEMENTDECLARATION> 	{QName}		{ yybegin(TS_SET_RPAR); return KT_QNAME; }
<TS_SET_RPAR>					")"			{ restoreState(); return ST_RPAR; }
  

// Processing instruction test
// "processing-instruction" "(" (NCName | StringLiteral)? ")" 

<TS_PITEST>						"("		{ yybegin(TS_PIT_NCNAMEORSTRINGOPT); return ST_LPAR; } 
<TS_PIT_NCNAMEORSTRINGOPT> {
  {NCName}			{ yybegin(TS_PIT_RPAR); return KT_NCNAME; }
  {StringLiteral}	{ yybegin(TS_PIT_RPAR); return STRINGLITERAL; }
}
<TS_PIT_NCNAMEORSTRINGOPT, TS_PIT_RPAR> ")" 	{ restoreState(); return ST_RPAR; }

// Attribute test
// "attribute" "(" (AttribNameOrWildcard ("," TypeName)?)? ")"

<TS_ATTRIBUTETEST> 	"(" 	{ yybegin(TS_AT_NAMEORWILDCARDOPT); return ST_LPAR; }
<TS_AT_NAMEORWILDCARDOPT, TS_AT_COMMAORRPAR, TS_AT_RPAR> {
  ")"								{ restoreState(); return ST_RPAR; }
  ")" / {S}*{OccurrenceIndicator}	{ yybegin(TS_OCCINDICATOR); return ST_RPAR; }
  ")" /	{XQueryCommentStart} 		{ return lookaheadOccurrenceIndicator(false); }
}

<TS_AT_NAMEORWILDCARDOPT> {
  "*"								{ yybegin(TS_AT_COMMAORRPAR); return KT_WILDCARD; }
  {QName}							{ yybegin(TS_AT_COMMAORRPAR); return KT_QNAME; }
}

<TS_AT_COMMAORRPAR> {
  ","								{ yybegin(TS_AT_TYPENAME); return KT_COMMA; }
}

<TS_AT_TYPENAME> 	{QName}	{ yybegin(TS_AT_RPAR); return KT_QNAME; }
 
// Schema-attribute test
// "schema-attribute" "(" AttributeDeclaration ")" 
<TS_SCHEMAATTRIBUTETEST> 		"(" 		{ yybegin(TS_SAT_ATTRIBUTEDECLARATION); return ST_LPAR; }
<TS_SAT_ATTRIBUTEDECLARATION> 	{QName}		{ yybegin(TS_SAT_RPAR); return KT_QNAME; }
<TS_SAT_RPAR>					")"			{ restoreState(); return ST_RPAR; } 
 
 
// NCName state (pop state when finished)

<TS_NCNAME>  {NCName} { restoreState(); return NCNAME; }

// Computed construction

<TS_COMPDOCUMENT, TS_COMPTEXT, TS_COMPCOMMENT> 	"{"  { pushState(CURLYEXPR); yybegin(TS_EXPR); return LCURLY; } 

<TS_COMPELEMENTORATTR> {
  {QName}				 { yybegin(TS_COMPELEMENTORATTRLCURLY); return QNAME; }
  "{" 					 { pushState(TS_COMPELEMENTORATTRLCURLY); pushState(CURLYEXPR); yybegin(TS_EXPR); return LCURLY; }
}

<TS_COMPELEMENTORATTRLCURLY> "{" { pushState(CURLYEXPR); yybegin(TS_EXPROPT); return LCURLY; }

<TS_COMPPI> {
  {NCName}       { yybegin(TS_COMPELEMENTORATTRLCURLY); return NCNAME; }
  "{"			 { pushState(TS_COMPELEMENTORATTRLCURLY); pushState(CURLYEXPR); yybegin(TS_EXPR); return LCURLY; }
}

// Direct construction

// DirElemConstructor	::=   	"<" QName DirAttributeList ("/>" | (">" DirElemContent* "</" QName S? ">"))

<TS_XMLTAGNAME> {
  {QName}		{ yybegin(TS_XMLATTLIST); return XML_TAG_NAME; }
}

// Attribute
// DirAttributeList	   	::=   	(S (QName S? "=" S? DirAttributeValue)?)*

<TS_XMLATTLIST> {
  {QName}		{ yybegin(TS_XMLATTREQ); return XML_TAG_ATTRIBUTE_NAME; }
  "/>"			{ 
  					endElement(); 
					if (inXMLContent()) {
					   yybegin(TS_XMLCONTENT);
					} else {
					   endXML();
					   yybegin(popState());
					}
					return XML_EMPTY_TAG_CLOSE; 
  				}
  ">"			{ startElement(); yybegin(TS_XMLCONTENT); return XML_TAG_CLOSE; }
}

<TS_XMLATTREQ> {
  "="			{ yybegin(TS_XMLATTRVALUE); return XML_TAG_ATTRIBUTE_EQUALS; }
}

<TS_XMLATTRVALUE> {
  \"		{ yybegin(TS_XMLQUOTATTRVALUE); return XML_ATTR_QUOT; }
  '			{ yybegin(TS_XMLAPOSATTRVALUE); return XML_ATTR_APOS; }
}

<TS_XMLQUOTATTRVALUE> {
  {EscapeQuot}				{ return XML_ESCAPE_QUOT; }
  {QuotAttrContentChar}		{ return XML_ATTR_CHAR; }
  \"						{ yybegin(TS_XMLATTLIST); return XML_END_ATTR_VALUE; }
}

<TS_XMLAPOSATTRVALUE> {
  {EscapeApos}				{ return XML_ESCAPE_APOS; }
  {AposAttrContentChar}		{ return XML_ATTR_CHAR; }
  '							{ yybegin(TS_XMLATTLIST); return XML_END_ATTR_VALUE; }
}

// CommonContent	   ::=   	PredefinedEntityRef | CharRef | "{{" | "}}" | EnclosedExpr
<TS_XMLQUOTATTRVALUE, TS_XMLAPOSATTRVALUE, TS_XMLCONTENT> {
  {PredefinedEntityRef}		{ return XML_PE_REFERENCE; }
  {CharRef}					{ return XML_CHAR_REF; }
  "{{"						{ return XML_ESCAPE_START_EXPR; }
  "}}"						{ return XML_ESCAPE_CLOSE_EXPR; }
  
  "{"						{ pushState(yystate()); pushState(CURLYEXPR); yybegin(TS_EXPR); return LCURLY; }
}

// DirElemContent    ::=	DirectConstructor | CDataSection | CommonContent | ElementContentChar
// DirectConstructor ::=   	DirElemConstructor | DirCommentConstructor | DirPIConstructor
// CDataSection	     ::=   	"<![CDATA[" CDataSectionContents "]]>"

<TS_XMLCONTENT> {
  
  {XMLComment}		{ return XML_COMMENT; }
  {XMLPI}			{ return XML_PI; }
  {XMLCDATA}  		{ return XML_CDATA; }
  {ElemContentChar} { return XML_ELEM_CONTENT_CHAR; }
  "</"				{ yybegin(TS_XMLENDTAGNAME); return XML_END_TAG_OPEN; }
  "<"				{ yybegin(TS_XMLTAGNAME); return XML_TAG_OPEN; }
}

<TS_XMLENDTAGNAME>   	{QName} 	{ yybegin(TS_XMLENDTAGDELIM); return XML_TAG_NAME; }
<TS_XMLENDTAGDELIM> 	">"      	{ 
										endElement(); 
										if (inXMLContent()) {
										  yybegin(TS_XMLCONTENT);
										} else {
										  endXML();
										  yybegin(popState());
										}
										return XML_TAG_CLOSE; 
									}

// XQuery Update Facility 1.0 Expressions

// Insert
// InsertExpr	   ::=   	"insert" ("node" | "nodes") SourceExpr InsertExprTargetChoice TargetExpr
<TS_INSERTEXPR> {
  "node"	{ pushState(XUSOURCE); yybegin(TS_EXPRSINGLE); return KW_NODE; }
  "nodes"	{ pushState(XUSOURCE); yybegin(TS_EXPRSINGLE); return KW_NODES; }
}

// ExprSingle delimiters for SourceExpr
<TS_ENDPRIMARY, TS_SINGLETYPEQMOREND, TS_ENDAXISSTEP, TS_OPTSTEPEXPR, TS_ENDEXPRSINGLE,TS_ENDVARREF> {
  "as" / {S}+("first"|"last")	{ check(endExprSingle(), XUSOURCE); yybegin(TS_FIRSTORLAST); return KW_AS; }
  "before"      { check(endExprSingle(), XUSOURCE); pushState(XUINSERTTARGET); yybegin(TS_EXPRSINGLE); return KW_BEFORE; }
  "after"		{ check(endExprSingle(), XUSOURCE); pushState(XUINSERTTARGET); yybegin(TS_EXPRSINGLE); return KW_AFTER; }
}

<TS_FIRSTORLAST> {
  "first"		{ yybegin(TS_INSERTINTO); return KW_FIRST;}
  "last"		{ yybegin(TS_INSERTINTO); return KW_LAST;}
}

<TS_INSERTINTO> "into" { pushState(XUINSERTTARGET); yybegin(TS_EXPRSINGLE); return KW_INTO;}

// DeleteExpr	   ::=   	"delete" ("node" | "nodes") TargetExpr
<TS_DELETEEXPR> {
  "node"	{ pushState(XUDELETETARGET); yybegin(TS_EXPRSINGLE); return KW_NODE; }
  "nodes"	{ pushState(XUDELETETARGET); yybegin(TS_EXPRSINGLE); return KW_NODES; }
}

// ReplaceExpr	   ::=   	"replace" ("value" "of")? "node" TargetExpr "with" ExprSingle
<TS_REPLACEEXPR> {
  "value" / {S}+"of"	{ yybegin(TS_REPLACEOF); return KW_VALUE; }
}

<TS_REPLACEEXPR, TS_REPLACENODE>  "node" 		{ pushState(XUREPLACETARGET); yybegin(TS_EXPRSINGLE); return KW_NODE; }
<TS_REPLACEOF> 					  "of"		 	{ yybegin(TS_REPLACENODE); return KW_OF; }

// ExprSingle delimiters for TargetExpr
<TS_ENDPRIMARY, TS_SINGLETYPEQMOREND, TS_ENDAXISSTEP, TS_OPTSTEPEXPR, TS_ENDEXPRSINGLE,TS_ENDVARREF> {
  "with"		{ check(endExprSingle(), XUREPLACETARGET); pushState(XUREPLACESRC); yybegin(TS_EXPRSINGLE); return KW_WITH;}
}

// RenameExpr	   ::=   	"rename" "node" TargetExpr "as" NewNameExpr
<TS_RENAMEEXPR> "node" { yybegin(TS_EXPRSINGLE); return KW_NODE; }

// ExprSingle delimiters for TargetExpr
<TS_ENDPRIMARY, TS_SINGLETYPEQMOREND, TS_ENDAXISSTEP, TS_OPTSTEPEXPR, TS_ENDEXPRSINGLE,TS_ENDVARREF> {
  "as"		{ check(endExprSingle(), XURENAME); pushState(XUNEWNAMEEXPR); yybegin(TS_EXPRSINGLE); return KW_AS;}
}

// TransformExpr	   ::=   	"copy" "$" VarName ":=" ExprSingle ("," "$" VarName ":=" ExprSingle)* "modify" ExprSingle "return" ExprSingle
<TS_TRANSFORMEXPR> {
  "$"   		{ pushState(TS_ENDCOPYVARREF); yybegin(TS_EXPRVARREF); return DOLLAR; } 
}

<TS_ENDCOPYVARREF> ":=" { pushState(XUTRANSFORMASSIGN); yybegin(TS_EXPRSINGLE); return ASSIGN; }

// ExprSingle delimiters
<TS_ENDPRIMARY, TS_SINGLETYPEQMOREND, TS_ENDAXISSTEP, TS_OPTSTEPEXPR, TS_ENDEXPRSINGLE,TS_ENDVARREF> {
  "modify"		{ check(endExprSingle(), XUTRANSFORMASSIGN); pushState(XUMODIFYEXPR); yybegin(TS_EXPRSINGLE); return KW_MODIFY;}
  
  // "return" is handled in the 'for' rule
}


// ********** XQuery Scripting Expression

// Block
// BlockExpr	   ::=   	"block" Block
// Block	   	   ::=   	"{" BlockDecls BlockBody "}"
// BlockDecls	   ::=   	(BlockVarDecl ";")*
// BlockVarDecl	   ::=   	"declare" "$" VarName TypeDeclaration? (":=" ExprSingle)? ("," "$" VarName TypeDeclaration? (":=" ExprSingle)?)*

<TS_BLOCK>	"{"  {  yybegin(TS_BLOCKVARDECLOPT); return LCURLY; }
<TS_BLOCKVARDECLOPT> {
  "declare" / {S}*"$" { yybegin(TS_BLOCKVARNAME); return KW_DECLARE; }
} 
<TS_BLOCKVARNAME> "$"	{ pushState(TS_BLOCKVARTYPEDECL); yybegin(TS_EXPRVARREF); return DOLLAR; }
<TS_BLOCKVARTYPEDECL> {
  "as"				{ pushState(TS_BLOCKVARINIT); yybegin(TS_TYPEDECL); return KW_AS; }
}
<TS_BLOCKVARINIT, TS_BLOCKVARTYPEDECL> {
  ":="				{ pushState(INBLOCKVARDECLINIT); yybegin(TS_EXPRSINGLE); return ASSIGN; }
  ","				{ yybegin(TS_BLOCKVARNAME); return COMMA; }
  
  ";"				{ yybegin(TS_BLOCKVARDECLOPT); return SEPARATOR; }  
}

// Exit expression
// ExitExpr	   ::=   	"exit" "returning" ExprSingle
<TS_EXIT> "returning" { pushState(SXEXITEXPRSINGLE); yybegin(TS_EXPRSINGLE); return KW_RETURNING; }


// While expression
//	WhileExpr	   ::=   	"while" "(" ExprSingle ")" WhileBody
<TS_WHILE> "(" { 
					pushState(TS_BLOCK); // Push continuation state when closing ')'
		    		pushState(PAREXPR); 
		    		yybegin(TS_EXPRSINGLE);
		    	    return LPAR; 
		    }

<TS_SINK> . { return UNDEFINED; }

 

// Always available rules

"(:" {
    parseXQueryComment(); 
	return XQUERY_COMMENT;
}

{S}* { 
    // TODO: not always allowed

	return WHITE_SPACE;
}
 

. {
	if (Debug.debugTokenizer)
		System.out.println("!!!unexpected!!!: \"" + yytext() + "\":" + //$NON-NLS-2$//$NON-NLS-1$
			yychar + "-" + (yychar + yylength()));//$NON-NLS-1$
	recover(TS_EXPR);
	return UNDEFINED;
}



 
