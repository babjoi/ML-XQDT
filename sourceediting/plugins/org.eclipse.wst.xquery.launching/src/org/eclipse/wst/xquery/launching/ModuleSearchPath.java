package org.eclipse.wst.xquery.launching;

public class ModuleSearchPath {

    private String fPath;
    private boolean fRelative;

    public ModuleSearchPath(String path, boolean relative) {
        fPath = path;
        fRelative = relative;
    }

    public String getPath() {
        return fPath;
    }

    public boolean isRelative() {
        return fRelative;
    }
}
