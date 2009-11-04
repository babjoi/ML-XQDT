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

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.wst.xquery.marklogic.io.IOHelper;
import org.eclipse.wst.xquery.marklogic.xcc.Request;
import org.eclipse.wst.xquery.marklogic.xcc.RequestOptions;
import org.eclipse.wst.xquery.marklogic.xcc.Session;
import org.eclipse.wst.xquery.marklogic.xcc.ValueFactory;
import org.eclipse.wst.xquery.marklogic.xcc.exceptions.UnimplementedFeatureException;
import org.eclipse.wst.xquery.marklogic.xcc.types.ValueType;
import org.eclipse.wst.xquery.marklogic.xcc.types.XName;
import org.eclipse.wst.xquery.marklogic.xcc.types.XdmNode;
import org.eclipse.wst.xquery.marklogic.xcc.types.XdmSequence;
import org.eclipse.wst.xquery.marklogic.xcc.types.XdmValue;
import org.eclipse.wst.xquery.marklogic.xcc.types.XdmVariable;


public abstract class RequestImpl implements Request {
    private final Session session;
    private RequestOptions options;
    private Set<XdmVariable> variables = Collections.synchronizedSet(new LinkedHashSet<XdmVariable>());

    // ---------------------------------------------------

    public RequestImpl(Session session, RequestOptions options) {
        this.session = session;

        setOptions(options);
    }

    // ---------------------------------------------------

    abstract void urlEncodeXQueryString(StringBuffer sb, Logger logger);

    abstract String serverPath();

    abstract String requestVar();

    // ---------------------------------------------------

    public Session getSession() {
        return (session);
    }

    public void setOptions(RequestOptions options) {
        if (options == null) {
            this.options = new RequestOptions();
        } else {
            this.options = options;
        }
    }

    public RequestOptions getOptions() {
        return options;
    }

    public RequestOptions getEffectiveOptions() {
        RequestOptions eff = new RequestOptions();
        RequestOptions req = getOptions();
        RequestOptions ses = getSession().getDefaultRequestOptions();

        eff.applyEffectiveValues(new RequestOptions[] { ses, req });

        return eff;
    }

    public void setVariable(XdmVariable variable) {
        XdmValue value = variable.getValue();

        // TODO: Need to interrogate server capability to make these decisions
        if (value instanceof XdmNode) {
            throw new UnimplementedFeatureException("Setting variables of type XdmNode is not supported");
        }

        if (value instanceof XdmSequence<?>) {
            throw new UnimplementedFeatureException("Setting variables that are sequences is not supported");
        }

        synchronized (variables) {
            // "set" implies replacing a var with the same XName, add() doesn't replace
            clearVariable(variable);
            variables.add(variable);
        }
    }

    public XdmVariable setNewVariable(XName xname, XdmValue value) {
        XdmVariable variable = ValueFactory.newVariable(xname, value);

        setVariable(variable);

        return (variable);
    }

    public XdmVariable setNewVariable(String namespace, String localname, ValueType type, Object value) {
        return setNewVariable(new XName(namespace, localname), ValueFactory.newValue(type, value));
    }

    public XdmVariable setNewVariable(String localname, ValueType type, Object value) {
        return setNewVariable(null, localname, type, value);
    }

    public XdmVariable setNewStringVariable(String namespace, String localname, String value) {
        return setNewVariable(namespace, localname, ValueType.XS_STRING, value);
    }

    public XdmVariable setNewStringVariable(String localname, String value) {
        return setNewStringVariable(null, localname, value);
    }

    public XdmVariable setNewIntegerVariable(String namespace, String localname, long value) {
        return setNewVariable(namespace, localname, ValueType.XS_INTEGER, new Long(value));
    }

    public XdmVariable setNewIntegerVariable(String localname, long value) {
        return setNewIntegerVariable(null, localname, value);
    }

    public void clearVariable(XdmVariable variable) {
        variables.remove(variable);
    }

    public void clearVariables() {
        variables.clear();
    }

    public XdmVariable[] getVariables() {
        synchronized (variables) {
            XdmVariable[] vars = new XdmVariable[variables.size()];

            variables.toArray(vars);

            return vars;
        }
    }

    // -----------------------------------------------------

    String encodedQueryString(Logger logger) {
        StringBuffer sb = new StringBuffer();

        sb.append(requestVar()).append("=");
        urlEncodeXQueryString(sb, logger);

        encodeQueryOptions(sb, null);

        encodeQueryVariables(sb, logger);

        String payload = sb.toString();

        if (logger.isLoggable(Level.FINEST)) {
            logger.finest("Eval request POST payload: '" + payload + "'");
        }

        return (payload);
    }

    public void encodeQueryOptions(StringBuffer sb, RequestOptions requestOptions) {
        RequestOptions options = (requestOptions == null) ? getEffectiveOptions() : requestOptions;

        sb.append("&locale=").append(options.getLocale().toString());
        sb.append("&tzoffset=").append((options.getTimeZone().getOffset(System.currentTimeMillis())) / 1000);

        if (session.getContentBaseName() != null) {
            String dbname = session.getContentBaseName();

            if (isName(dbname)) {
                sb.append("&dbname=");
                IOHelper.urlEncodeToStringBuffer(sb, session.getContentBaseName());
            } else {
                sb.append("&dbid=").append(dbname.substring(1)); // numeric
            }
        }

        if (options.getEffectivePointInTime() != null) {
            sb.append("&timestamp=").append(options.getEffectivePointInTime().toString());
        }

        if (options.getRequestName() != null) {
            sb.append("&requestname=");
            IOHelper.urlEncodeToStringBuffer(sb, options.getRequestName());
        }

        if (options.getDefaultXQueryVersion() != null) {
            sb.append("&defaultxquery=");
            IOHelper.urlEncodeToStringBuffer(sb, options.getDefaultXQueryVersion());
        }

        if (options.getRequestTimeLimit() != -1) {
            sb.append("&timelimit=").append(options.getRequestTimeLimit());
        }
    }

    private void encodeQueryVariables(StringBuffer sb, Logger logger) {
        XdmVariable[] vars = getVariables();

        for (int i = 0; i < vars.length; i++) {
            XdmVariable var = vars[i];
            XName xname = var.getName();
            XdmValue value = var.getValue();

            sb.append("&evn").append(i).append("=");
            if (xname.getNamespace() != null) {
                sb.append(xname.getNamespace());
            }

            sb.append("&evl").append(i).append("=").append(xname.getLocalname());
            sb.append("&evt").append(i).append("=");
            IOHelper.urlEncodeToStringBuffer(sb, value.getValueType().toString());
            sb.append("&evv").append(i).append("=");
            IOHelper.urlEncodeToStringBuffer(sb, value.toString());

            // TODO: Test this output
            if (logger.isLoggable(Level.FINEST)) {
                logger.finest(" ev" + i + ": " + xname.toString() + "(" + value.getValueType() + ") "
                        + value.toString());
            }
        }
    }

    protected boolean isName(String name) {
        if (name.length() == 0)
            return true;

        if (name.charAt(0) == '#')
            return false;

        return true;
    }
}
