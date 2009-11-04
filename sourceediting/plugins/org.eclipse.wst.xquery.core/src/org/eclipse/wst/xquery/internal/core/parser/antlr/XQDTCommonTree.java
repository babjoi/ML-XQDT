/*******************************************************************************
 * Copyright (c) 2008, 2009 28msec Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Gabriel Petrovay (28msec) - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.xquery.internal.core.parser.antlr;

import org.antlr.runtime.CommonToken;
import org.antlr.runtime.Token;
import org.antlr.runtime.tree.CommonTree;
import org.eclipse.wst.xquery.internal.core.parser.visitors.NodeVisitor;

public class XQDTCommonTree extends CommonTree {

    protected int start;
    protected int stop;

    private boolean stacked;

    public XQDTCommonTree() {
    }

    public XQDTCommonTree(Token t) {
        this.token = t;
    }

    public void accept(NodeVisitor visitor) {
        visitor.beginVisit(this);
        for (int i = 0; i < getChildCount(); i++) {
            XQDTCommonTree child = getChild(i);
            child.accept(visitor);
        }
        visitor.endVisit(this);
    }

    public void setStart(int start) {
        this.start = start;
    }

    public void setStop(int stop) {
        this.stop = stop;
    }

    public int getStart() {
        return start;
    }

    public int getStop() {
        return stop;
    }

    @Override
    public XQDTCommonTree getChild(int i) {
        if (children == null || i >= children.size()) {
            return null;
        }
        return (XQDTCommonTree)children.get(i);
    }

    @Override
    public CommonToken getToken() {
        return (CommonToken)token;
    }

    public boolean isError() {
        return false;
    }

    public boolean isStacked() {
        return stacked;
    }

    public void setStacked(boolean inStack) {
        stacked = inStack;
    }
}
