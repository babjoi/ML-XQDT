package org.eclipse.wst.xquery.core.codeassist;

public interface IXQDTCompletionConstants {

    public final static int RELEVANCE_KEYWORD = 100000;
    public final static int RELEVANCE_TEMPLATE = 1000000;
    public final static int RELEVANCE_FUNCTIONS = 10000000;
    public final static int RELEVANCE_VARIABLES = 100000000;

    public static enum CompletionPrefixType {
        NORMAL, DOLLAR, COLON, AMPERSAND
    }

}