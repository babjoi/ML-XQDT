/*******************************************************************************
 * Copyright (c) 2005, 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.xquery.sse.core.internal.parser;

import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocumentRegion;

/**
 * Enumeration of {@link IStructuredDocumentRegion} statement types
 * 
 * @author <a href="villard@us.ibm.com">Lionel Villard</a>
 */
@SuppressWarnings("restriction")
public interface StatementTypes {

	final static public int STMT_UNDEFINED = -1;

	final static public int STMT_VERSIONDECL = 1;
	final static public int STMT_MODULEDECL = 2;
	final static public int STMT_DEFAULTNSDECL = 3;
	final static public int STMT_BOUNDARYSPACEDECL = 4;
	final static public int STMT_DEFAUTLCOLLATIONDECL = 5;
	final static public int STMT_BASEURIDECL = 6;
	final static public int STMT_CONSTRUCTIONDECL = 7;
	final static public int STMT_ORDERINGMODEDECL = 8;
	final static public int STMT_EMPTYORDERDECL = 9;
	final static public int STMT_COPYNSDECL = 10;
	final static public int STMT_NSDECL = 11;
	final static public int STMT_IMPORT = 12;

	final static public int STMT_VARDECL = 13;
	final static public int STMT_FUNCTIONDECL = 14;
	final static public int STMT_OPTIONDECL = 15;

	final static public int STMT_AXISSTEP = 16;

	final static public int STMT_PRIMARY_LITERAL = 20;
	final static public int STMT_VARREF = 21;
	final static public int STMT_PRIMARY_PAR = 22;
	final static public int STMT_CONTEXTITEM = 23;
	final static public int STMT_PRIMARY_FUNCTIONCALL = 24;
	final static public int STMT_PRIMARY_ORDERED = 25;
	final static public int STMT_PRIMARY_UNORDERED = 26;
	final static public int STMT_PRIMARY_DIRCONSTRUCTOR = 27;
	final static public int STMT_PRIMARY_COMPCONSTRUCTOR = 28;

	final static public int STMT_VALIDATE = 29;
	final static public int STMT_EXTENSION = 30;

	final static public int STMT_FOR = 31; // for keyword
	final static public int STMT_LET = 32; // let keyword

}
