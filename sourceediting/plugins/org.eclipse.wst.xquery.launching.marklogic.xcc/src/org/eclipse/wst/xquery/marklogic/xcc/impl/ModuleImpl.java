/*
 * Copyright (c) 2003-2009 Mark Logic Corporation. All rights reserved.
 *
 * This program and the accompanying materials are made available 
 * under the terms of the Eclipse Public License v1.0 which 
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Mark Logic, Inc.
 */
package org.eclipse.wst.xquery.marklogic.xcc.impl;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.eclipse.wst.xquery.marklogic.io.IOHelper;
import org.eclipse.wst.xquery.marklogic.xcc.ModuleInvoke;
import org.eclipse.wst.xquery.marklogic.xcc.ModuleSpawn;
import org.eclipse.wst.xquery.marklogic.xcc.RequestOptions;
import org.eclipse.wst.xquery.marklogic.xcc.Session;
import org.eclipse.wst.xquery.marklogic.xcc.types.XName;
import org.eclipse.wst.xquery.marklogic.xcc.types.XdmVariable;


public class ModuleImpl extends RequestImpl implements ModuleInvoke, ModuleSpawn {
    public static final String OLD_STYLE_MODULE_INVOKE_PROP = "xcc.module.invoke.oldstyle";

    private final boolean spawn;
    private String moduleUri;
    private String moduleRoot;
    private boolean oldEncodingStyle = false;

    public ModuleImpl(Session session, String moduleUri, String moduleRoot, RequestOptions options, boolean spawn) {
        super(session, options);

        this.moduleUri = moduleUri;
        this.moduleRoot = moduleRoot;
        this.spawn = spawn;

        if (System.getProperty(OLD_STYLE_MODULE_INVOKE_PROP, "false").equals("true")) {
            oldEncodingStyle = true;
        }
    }

    public ModuleImpl(Session session, String moduleUri, RequestOptions options, boolean spawn) {
        this(session, moduleUri, null, options, spawn);
    }

    public void setModuleUri(String uri) {
        this.moduleUri = uri;
    }

    public String getModuleUri() {
        return moduleUri;
    }

    // ------------------------------------------------------

    public String getModuleRoot() {
        return moduleRoot;
    }

    // retained for testing, not need for new call
    public void setModuleRoot(String moduleRoot) {
        this.moduleRoot = moduleRoot;
    }

    public boolean isOldEncodingStyle() {
        return oldEncodingStyle;
    }

    public void setOldEncodingStyle(boolean oldEncodingStyle) {
        this.oldEncodingStyle = oldEncodingStyle;
    }

    // ------------------------------------------------------

    @Override
    String requestVar() {
        if (oldEncodingStyle)
            return "xquery";

        return "module";
    }

    @Override
    String serverPath() {
        if (oldEncodingStyle)
            return "/eval";

        return (spawn) ? "/spawn" : "/invoke";
    }

    @Override
    void urlEncodeXQueryString(StringBuffer sb, Logger logger) {
        if (oldEncodingStyle) {
            XdmVariable[] vars = getVariables();
            Map<String,String> qnameMap = generateQNameMap(vars);
            StringBuffer modcall = new StringBuffer();

            modcall.append("xquery version '0.9-ml'\n");
            putPrologue(modcall, vars, qnameMap);
            putFunctionCall(modcall, getModuleUri(), getSession().getContentBaseName(), vars, qnameMap);

            IOHelper.urlEncodeToStringBuffer(sb, modcall.toString());
        } else {
            IOHelper.urlEncodeToStringBuffer(sb, moduleUri);
        }
    }

    // ------------------------------------------------------
    // All the stuff from here down is to generate invoke/spawn
    // calls by generating an eval() call via the server's /eval
    // interface.  As of 3.1, there are direct /invoke and /spawn
    // access points.

    private void putPrologue(StringBuffer sb, XdmVariable[] vars, Map<String,String> qnameMap) {
        for (Iterator<Map.Entry<String,String>> it = qnameMap.entrySet().iterator(); it.hasNext();) {
            Map.Entry<String,String> entry = it.next();
            String ns = entry.getKey();
            String prefix = entry.getValue();

            sb.append("declare namespace ");
            sb.append(prefix).append("=\"").append(ns).append("\"\n");
        }

        if (qnameMap.size() != 0) {
            sb.append("\n");
        }

        for (int i = 0; i < vars.length; i++) {
            XdmVariable var = vars[i];
            String argName = generateQName(qnameMap, var.getName());

            sb.append("define variable $");
            sb.append(argName);
            sb.append(" as ");
            sb.append(var.getValue().getValueType().toString());
            sb.append(" external\n");
        }

        if (vars.length != 0) {
            sb.append("\n");
        }
    }

    private void putFunctionCall(StringBuffer sb, String uri, String contentBase, XdmVariable[] vars, Map<String,String> qnameMap) {
        String functionRoot = (spawn) ? "xdmp:spawn" : "xdmp:invoke";

        sb.append(functionRoot);
        if (contentBase != null) {
            sb.append("-in"); // this could be brittle
        }
        sb.append(" (\"").append(uri).append("\"");

        if (contentBase != null) {
            sb.append(", ");

            if (isName(contentBase)) {
                sb.append("xdmp:database (\"");
                sb.append(contentBase);
                sb.append("\")");
            } else {
                sb.append(contentBase);
            }
        }

        sb.append(", (");

        for (int i = 0; i < vars.length; i++) {
            XdmVariable var = vars[i];
            String argName = generateQName(qnameMap, var.getName());

            if (i != 0) {
                sb.append(", ");
            }
            sb.append("(xs:QName(\"").append(argName).append("\"), ");
            sb.append("$").append(argName).append(")");
        }

        sb.append("), xdmp:modules-database()");

        if (getModuleRoot() != null) {
            sb.append(", \"").append(getModuleRoot()).append("\"");
        }

        sb.append(")");
    }

    private Map<String,String> generateQNameMap(XdmVariable[] vars) {
        Map<String,String> map = new LinkedHashMap<String,String>();

        for (int i = 0; i < vars.length; i++) {
            XdmVariable var = vars[i];
            XName varName = var.getName();
            String ns = varName.getNamespace();

            if (ns == null) {
                continue;
            }

            if (map.get(ns) == null) {
                map.put(ns, "tmpq" + i);
            }
        }

        return (map);
    }

    private static String generateQName(Map<String,String> tmpQNames, XName arg) {
        String ns = (arg.getNamespace() == null) ? "" : arg.getNamespace();

        if (ns.length() == 0) {
            return (arg.getLocalname());
        }

        return ("" + tmpQNames.get(ns) + ":" + arg.getLocalname());
    }
}
