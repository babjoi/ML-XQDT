package org.eclipse.wst.xquery.internal.core.codeassist;

public class ImplicitImport {

    private String fPrefix;
    private String fPath;

    public ImplicitImport(String prefix, String path) {
        fPrefix = prefix;
        fPath = path;
    }

    public String getPrefix() {
        return fPrefix;
    }

    public String getPath() {
        return fPath;
    }
}
