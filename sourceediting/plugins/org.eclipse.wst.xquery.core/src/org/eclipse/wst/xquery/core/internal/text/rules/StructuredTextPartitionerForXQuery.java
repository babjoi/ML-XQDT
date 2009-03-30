/*******************************************************************************
 * Copyright (c) 2008 by Buddhika Laknath <blaknath@gmail.com>  
 * This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0, and is available at
 * http://www.eclipse.org/legal/epl-v10.html 
 *******************************************************************************/

package org.eclipse.wst.xquery.core.internal.text.rules;

import org.eclipse.wst.xml.core.internal.text.rules.StructuredTextPartitionerForXML;
import org.eclipse.wst.sse.core.internal.parser.ForeignRegion;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocumentRegion;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredTextPartitioner;
import org.eclipse.wst.xml.core.text.IXMLPartitions;
import org.eclipse.wst.xquery.core.internal.text.IXQueryPartitions;
import org.eclipse.wst.xml.core.internal.regions.DOMRegionContext;
import org.eclipse.wst.sse.core.internal.provisional.text.ITextRegion;
import org.eclipse.jface.text.IDocumentPartitioner;


/**
 * Contains information specific to setting up Structured Document Partitions 
 * in XQuery documents. 
*/

public class StructuredTextPartitionerForXQuery extends StructuredTextPartitionerForXML implements IStructuredTextPartitioner{

	private final static String[] configuredContentTypes = new String[]{IXMLPartitions.XML_DEFAULT, IXMLPartitions.XML_CDATA, IXMLPartitions.XML_PI, IXMLPartitions.XML_DECLARATION, IXMLPartitions.XML_COMMENT, IXQueryPartitions.XQL_XPATH2};	
	
	/**
	 * Adds the necessary Partition types to help Identify 
	 * potential XPath2 areas.Also used for Line Styling 
	 * and Content Assistance.
	 */	
	
	public StructuredTextPartitionerForXQuery(){
		super() ;
	}

	@Override
	public String getPartitionType(ITextRegion region, int offset) {
		//System.out.println("getPartitionType " + region.getType());
		
		String result = null;
		if (region.getType() == DOMRegionContext.XML_CONTENT) {
					result = IXQueryPartitions.XQL_XPATH2;
		} else {
			result = super.getPartitionType(region, offset);
		}
		return result;
	}
	
	public String getPartitionTypeBetween(IStructuredDocumentRegion previousNode, IStructuredDocumentRegion nextNode) {

		//System.out.println("getPartitionTypeBetween " + previousNode.getFullText() );
		
		String result = null;				
		if (previousNode.getType() == DOMRegionContext.XML_CONTENT || nextNode.getType() == DOMRegionContext.XML_CONTENT )
			result = IXQueryPartitions.XQL_XPATH2;
		else
			result = super.getPartitionTypeBetween(previousNode, nextNode);
		
		return result;
	}

	protected String getPartitionType(ForeignRegion region, int offset) {
		//System.out.println("getForeignPartitionType " + region.getType() );
		
		String result = null;		
		if (region.getType() == DOMRegionContext.XML_CONTENT) {
			result = IXQueryPartitions.XQL_XPATH2;
		} else {
			result = super.getPartitionType(region, offset);
		}			
		return result;
	}
	
	/**
	 * @return
	 */	
	public static String[] getConfiguredContentTypes() {
		return configuredContentTypes;
	}

}
