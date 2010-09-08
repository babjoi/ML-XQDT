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
package org.eclipse.wst.xquery.internal.core.parser.visitors;

import java.util.Stack;

import org.antlr.runtime.CommonToken;
import org.antlr.runtime.tree.CommonTree;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.compiler.problem.IProblemReporter;
import org.eclipse.wst.xquery.core.model.ast.IChildProcessor;
import org.eclipse.wst.xquery.core.model.ast.XQueryBaseURIDecl;
import org.eclipse.wst.xquery.core.model.ast.XQueryFunctionDecl;
import org.eclipse.wst.xquery.core.model.ast.XQueryFunctionDecl.XQueryFunctionKind;
import org.eclipse.wst.xquery.core.model.ast.XQueryLibraryModule;
import org.eclipse.wst.xquery.core.model.ast.XQueryMainModule;
import org.eclipse.wst.xquery.core.model.ast.XQueryModule;
import org.eclipse.wst.xquery.core.model.ast.XQueryModuleDecl;
import org.eclipse.wst.xquery.core.model.ast.XQueryModuleImport;
import org.eclipse.wst.xquery.core.model.ast.XQueryParam;
import org.eclipse.wst.xquery.core.model.ast.XQueryQueryBody;
import org.eclipse.wst.xquery.core.model.ast.XQueryStringLiteral;
import org.eclipse.wst.xquery.core.model.ast.XQueryVarDecl;
import org.eclipse.wst.xquery.core.model.ast.XQueryXmlAttributeValueText;
import org.eclipse.wst.xquery.core.model.ast.XQueryXmlElementContentText;
import org.eclipse.wst.xquery.internal.core.parser.antlr.XQDTCommonTree;
import org.eclipse.wst.xquery.internal.core.parser.antlr.XQueryParser;

public class XQDTCommonTreeVisitor implements NodeVisitor {

    private char[] fContent;
    private XQueryModule fModule = null;

    private Stack<ASTNode> fStack = new Stack<ASTNode>();

    public XQDTCommonTreeVisitor(char[] content, IProblemReporter reporter) {
        fContent = content;
    }

    public char[] getContent() {
        return fContent;
    }

    public ModuleDeclaration getModule() {
        return fModule;
    }

    public void beginVisit(CommonTree tree) {
        try {
            XQDTCommonTree xct = (XQDTCommonTree)tree;
            XQDTCommonTree child0 = null, child1 = null;
            @SuppressWarnings("unused")
            CommonToken cto = null;
            StringBuffer sb = null;
            String type = null, qn = null, str = null;

            switch (tree.getType()) {
            case XQueryParser.LibraryModule:
                fModule = new XQueryLibraryModule(fContent.length);
                push(xct, fModule);
                break;
            case XQueryParser.MainModule:
                fModule = new XQueryMainModule(fContent.length);
                push(xct, fModule);
                break;
            case XQueryParser.VersionDecl:
                fModule.setVersion(tree.getChild(0).getChild(0).getText());
                if (tree.getChildCount() == 6) {
                    fModule.setEncoding(tree.getChild(1).getChild(0).getText());
                }
                break;
            case XQueryParser.ModuleDecl:
                XQueryModuleDecl modDecl = new XQueryModuleDecl(xct.getStart(), xct.getStop() + 1);
                str = xct.getChild(0).getText();
                if (str != null && str.length() > 0) {
                    modDecl.setNamespacePrefix(str);
                }
                push(xct, modDecl);
                break;
            case XQueryParser.ModuleImport:
                if (xct.getChild(0).getType() != XQueryParser.NamespaceName && xct.getChild(0).getChildCount() == 0) {
                    break;
                }
                child0 = xct.getChild(0).getChild(0);
                XQueryModuleImport imp = new XQueryModuleImport(child0.getText(), child0.getStart(),
                        child0.getStop() + 1, xct.getStart(), xct.getStop() + 1);
                push(xct, imp);
                break;
            case XQueryParser.BaseURIDecl:
                push(xct, new XQueryBaseURIDecl(xct.getStart(), xct.getStop() + 1));
                break;
            case XQueryParser.VarDecl:
                if (xct.getChildCount() < 4) {
                    break;
                }

                boolean isConstant;
                if (xct.getChild(0).getType() == XQueryParser.VarVariableDecl) {
                    isConstant = false;
                } else if (xct.getChild(0).getType() == XQueryParser.VarConstantDecl) {
                    isConstant = true;
                } else {
                    break;
                }

                boolean isExternal = false;
                if (xct.getChild(3).getType() == XQueryParser.VarValue) {
                    if (xct.getChild(3).getType() == XQueryParser.VarDefaultValue) {
                        isExternal = true;
                    }
                } else {
                    break;
                }

                child0 = xct.getChild(1);
                if (child0.getType() == XQueryParser.QName) {
                    qn = readQNameString(child0);
                } else {
                    break;
                }
                XQueryVarDecl varDecl = new XQueryVarDecl(qn, child0.getStart(), child0.getStop() + 1, xct.getStart(),
                        xct.getStop() + 1, isExternal, isConstant);

                if (xct.getChild(2).getChildCount() == 1) {
                    child1 = xct.getChild(2).getChild(0);
                    type = readSequenceTypeString(child1.getChild(0));
                    varDecl.setType(type);
                }

                push(xct, varDecl);
                break;
            case XQueryParser.FunctionDecl:
                // the function name
                child0 = xct.getChild(0);
                str = readFunctionQNameString(child0);
                if (str == null) {
                    break;
                }

                // the return value
                if (tree.getChild(2).getChildCount() == 1) {
                    type = readSequenceTypeString(xct.getChild(2).getChild(0).getChild(0));
                }

                // check if external
                boolean isExternalFun = tree.getChildCount() == 3;
                int indexOfColon = str.indexOf(':');
                push(xct, new XQueryFunctionDecl(str.substring(0, indexOfColon), str.substring(indexOfColon + 1),
                        child0.getStart(), child0.getStop() + 1, xct.getStart(), xct.getStop() + 1, type,
                        XQueryFunctionKind.PURE, isExternalFun));
                break;
            case XQueryParser.Param:
                child0 = xct.getChild(0);
                qn = readQNameString(child0);
                if (qn == null) {
                    break;
                }

                // if we have a type declaration
                if (xct.getChildCount() == 2) {
                    // get the SequenceType node
                    child1 = xct.getChild(1).getChild(0);
                    // System.out.println();
                    type = readSequenceTypeString(child1);
                }

                XQueryFunctionDecl fun = (XQueryFunctionDecl)fStack.lastElement();

                fun.addArgument(new XQueryParam(new SimpleReference(child0.getStart(), child0.getStop() + 1, qn),
                        child0.getStart(), type));
                break;
            case XQueryParser.QueryBody:
                push(xct, new XQueryQueryBody(xct.getStart(), xct.getStop() + 1));
                break;
            case XQueryParser.DirElemConstructor:
                child1 = xct.getChild(1);
                for (int i = 0; i < child1.getChildCount(); i++) {
                    child0 = child1.getChild(i);
                    if (child0.getType() == XQueryParser.ElementContentChar
                            || child0.getType() == XQueryParser.CommonContent) {
                        fModule.addXmlElementContents(new XQueryXmlElementContentText(child0.getStart(), child0
                                .getStop(), child0.getChild(0).getText()));
                    }
                }
                break;
            case XQueryParser.DirAttributeValue:
                if (xct.getChildCount() == 0) {
                    break;
                }
                for (int i = 0; i < xct.getChildCount(); i++) {
                    child0 = xct.getChild(i);
                    if (child0.getType() == XQueryParser.AttributeValueChar
                            || child0.getType() == XQueryParser.CommonContent) {
                        fModule.addXmlAttributeValuesText(new XQueryXmlAttributeValueText(child0.getStart(), child0
                                .getStop(), child0.getChild(0).getText()));
                    }
                }
                break;
            case XQueryParser.CommonContent:
                break;
            case XQueryParser.StringLiteral:
                sb = new StringBuffer();
                if (xct.getChildCount() > 0) {
                    int start = xct.getStart() + 1;
                    for (Object child : xct.getChildren()) {
                        XQDTCommonTree childTree = (XQDTCommonTree)child;
                        for (; start < childTree.getStart(); start++) {
                            sb.append(" ");
                        }
                        sb.append(child.toString());
                        start += child.toString().length();
                    }
                    for (; start < xct.getStop(); start++) {
                        sb.append(" ");
                    }

                }
                push(xct, new XQueryStringLiteral(xct.getStart(), xct.getStop(), sb.toString()));
                break;
            default:
                break;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Unhandeled visitor exception");
        }
    }

    private String readFunctionQNameString(XQDTCommonTree node) {
        String qname = readQNameString(node);
        if (qname == null) {
            return null;
        }

        if (qname.indexOf(':') == -1) {
            return "local:" + qname;
        }

        return qname;
    }

    private String readQNameString(XQDTCommonTree node) {
        if (node == null || node.getType() != XQueryParser.QName) {
            return null;
        }

        if (node.getChildCount() == 1) {
            return node.getChild(0).getText();
        }
        if (node.getChildCount() == 3) {
            return node.getChild(0).getText() + ":" + node.getChild(2).getText();
        }

        return null;
    }

    private String readSequenceTypeString(XQDTCommonTree node) {
        if (node == null || node.getChildCount() == 0) {
            return null;
        }

        String type;
        // System.out.println();
        XQDTCommonTree child = node.getChild(0);
        switch (child.getType()) {
        case XQueryParser.AtomicType:
            type = readQNameString(child.getChild(0));
            if (type == null) {
                return null;
            }
            break;
        case XQueryParser.EmptySequenceTest:
        case XQueryParser.KindTest:
        case XQueryParser.ItemTest:
            StringBuilder strb = new StringBuilder();
            for (int i = 0; i < child.getChildCount(); i++) {
                strb.append(child.getChild(i));
            }
            type = strb.toString();
            break;
        default:
            return null;
        }

        // if we also have an occurrence indicator
        if (node.getChildCount() == 2) {
            type += node.getChild(1).getText();
        }

        return type;
    }

    public void endVisit(CommonTree tree) {
        XQDTCommonTree xct = (XQDTCommonTree)tree;
        ASTNode currentNode = null;
        ASTNode parentNode = null;

        switch (tree.getType()) {
        case XQueryParser.LibraryModule:
            fModule = (XQueryModule)fStack.pop();
            break;
        case XQueryParser.MainModule:
            fModule = (XQueryModule)fStack.pop();
            break;
        case XQueryParser.ModuleDecl:
            currentNode = pop(xct);
            if (currentNode == null) {
                break;
            }
            parentNode = fStack.peek();
            if (parentNode instanceof IChildProcessor) {
                ((IChildProcessor)parentNode).processChild(currentNode);
            }
            break;
        case XQueryParser.VersionDecl:
            break;
        case XQueryParser.ModuleImport:
            currentNode = pop(xct);
            if (currentNode == null) {
                break;
            }
            parentNode = fStack.peek();
            if (parentNode instanceof IChildProcessor) {
                ((IChildProcessor)parentNode).processChild(currentNode);
            }
            break;
        case XQueryParser.BaseURIDecl:
            currentNode = pop(xct);
            if (currentNode == null) {
                break;
            }
            parentNode = fStack.peek();
            if (parentNode instanceof IChildProcessor) {
                ((IChildProcessor)parentNode).processChild(currentNode);
            }
            break;
        case XQueryParser.VarDecl:
            currentNode = pop(xct);
            if (currentNode == null) {
                break;
            }
            parentNode = fStack.peek();
            if (parentNode instanceof IChildProcessor) {
                ((IChildProcessor)parentNode).processChild(currentNode);
            }
            break;
        case XQueryParser.FunctionDecl:
            currentNode = pop(xct);
            if (currentNode == null) {
                break;
            }
            parentNode = fStack.peek();
            if (parentNode instanceof IChildProcessor) {
                ((IChildProcessor)parentNode).processChild(currentNode);
            }
            break;
        case XQueryParser.Param:
            break;
        case XQueryParser.QueryBody:
            currentNode = pop(xct);
            if (currentNode == null) {
                break;
            }
            parentNode = fStack.peek();
            if (parentNode instanceof IChildProcessor) {
                ((IChildProcessor)parentNode).processChild(currentNode);
            }
            break;
        case XQueryParser.DirElemConstructor:
            break;
        case XQueryParser.DirAttributeValue:
            break;
        case XQueryParser.CommonContent:
            break;
        case XQueryParser.StringLiteral:
            currentNode = pop(xct);
            if (currentNode == null) {
                break;
            }
            parentNode = fStack.peek();
            if (parentNode instanceof IChildProcessor) {
                ((IChildProcessor)parentNode).processChild(currentNode);
            }
            fModule.addStringLiteral((XQueryStringLiteral)currentNode);
            break;
        default:
            break;
        }
    }

    private void push(XQDTCommonTree tree, ASTNode node) {
        fStack.push(node);
        tree.setStacked(true);
    }

    private ASTNode pop(XQDTCommonTree tree) {
        if (!tree.isStacked()) {
            return null;
        }
        tree.setStacked(false);
        return fStack.pop();
    }
}
