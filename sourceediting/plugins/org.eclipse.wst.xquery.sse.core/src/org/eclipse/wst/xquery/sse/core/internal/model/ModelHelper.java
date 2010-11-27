package org.eclipse.wst.xquery.sse.core.internal.model;

import java.util.List;

import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocumentRegion;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.IASTNode;
import org.eclipse.wst.xquery.sse.core.internal.regions.XQueryRegions;
import org.eclipse.wst.xquery.sse.core.internal.sdregions.XQueryStructuredDocumentRegion;

/**
 * Collection of utility methods related to the XQuery model
 * 
 * @author villardl
 */
public class ModelHelper {

    /** Gets list of in-scope variable region definition */
    public static List<String> getInScopeVariables(IASTNode node) {
        if (node != null) {
            return node.getInScopeVariables();
        }

        return null;
    }

    /**
     * Test whether the given region type is a ignorable white space (whitespace and comments)
     */
    public static boolean isIgnorableWhitespace(String type) {
        return type.equals(XQueryRegions.WHITE_SPACE) || type.equals(XQueryRegions.XQUERY_COMMENT);
    }

    /** Skip white spaces and XQuery comments */
    public static XQueryStructuredDocumentRegion skipWhitespace(XQueryStructuredDocumentRegion region) {
        while (region != null && isIgnorableWhitespace(region.getType())) {
            return (XQueryStructuredDocumentRegion)region.getNext();
        }
        return region;
    }

    /**
     * Test structured document region type against given type.
     */
    public static boolean sameRegionType(IStructuredDocumentRegion region, String type) {
        return region != null && region.getType() == type;
    }

}
