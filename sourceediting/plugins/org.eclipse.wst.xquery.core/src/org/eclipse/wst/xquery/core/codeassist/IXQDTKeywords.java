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
package org.eclipse.wst.xquery.core.codeassist;

/**
 * List of XQuery keywords
 */
public interface IXQDTKeywords {

    /**
     * Keywords present in the XQuery 3.0 specification
     * http://www.w3.org/TR/xquery-30/#id-terminal-delimitation
     */
    public static final String[] KEYWORDS_XQUERY = { "allowing", "ancestor", "ancestor-or-self", "and", "as",
            "ascending", "at", "attribute", "base-uri", "boundary-space", "by", "case", "cast", "castable", "catch",
            "child", "collation", "comment", "construction", "context", "copy-namespaces", "count", "decimal-format",
            "decimal-separator", "declare", "default", "descendant", "descendant-or-self", "descending", "digit",
            "div", "document", "document-node", "element", "else", "empty", "empty-sequence", "encoding", "end", "eq",
            "every", "except", "external", "following", "following-sibling", "for", "function", "ge", "greatest",
            "group", "grouping-separator", "gt", "idiv", "if", "import", "in", "infinity", "inherit", "instance",
            "intersect", "is", "item", "lax", "le", "least", "let", "lt", "minus-sign", "mod", "module", "NaN",
            "namespace", "namespace-node", "ne", "next", "no-inherit", "no-preserve", "node", "of", "only", "option",
            "or", "order", "ordered", "ordering", "parent", "pattern-separator", "per-mille", "percent", "preceding",
            "preceding-sibling", "preserve", "previous", "processing-instruction", "return", "satisfies", "schema",
            "schema-attribute", "schema-element", "self", "sliding", "some", "stable", "start", "strict", "strip",
            "switch", "text", "then", "to", "treat", "try", "tumbling", "type", "typeswitch", "union", "unordered",
            "validate", "variable", "version", "when", "where", "window", "xquery", "zero-digit" };

    /**
     * Additional keywords added by the XQuery Update 1.0 specification
     * http://www.w3.org/TR/xquery-update-10/
     */
    public static final String[] KEYWORDS_XQUERY_UPDATE = { "after", "before", "copy", "delete", "first", "insert",
            "into", "last", "modify", "nodes", "rename", "replace", "revalidation", "updating", "value", "with" };

    /**
     * Additional keywords added by the XQuery Full Text 1.0 specification
     * http://www.w3.org/TR/xpath-full-text-10/
     */
    public static final String[] KEYWORDS_XQUERY_FULLTEXT = { "all", "any", "contains", "content", "diacritics",
            "different", "distance", "entire", "exactly", "from", "ft_option", "ftand", "ftnot", "ftor", "insensitive",
            "language", "levels", "lowercase", "most", "no", "not", "occurs", "paragraph", "paragraphs", "phrase",
            "relationship", "same", "score", "sensitive", "sentence", "sentences", "stemming", "stop", "thesaurus",
            "times", "uppercase", "using", "weight", "wildcards", "without", "word", "words" };

    /**
     * Additional keywords added by the XQuery Scripting Extension Proposal
     */
    public static final String[] KEYWORDS_XQUERY_SCRIPTING = { "break", "continue", "exit", "loop", "returning",
            "while" };

    /**
     * Additional keywords added by the Xorba XQuery DDL extensions
     */
    public static final String[] KEYWORDS_ZORBA = { "append_only", "automatically", "check", "collection",
            "constraint", "const", "equality", "eval", "foreach", "foreign", "index", "integrity", "key", "maintained",
            "manually", "mutable", "non", "on", "queue", "range", "read-only", "unique" };

    /**
     * Additional keywords added by the MarkLogic XQuery extensions
     */
    public static final String[] KEYWORDS_MARKLOGIC = { "binary" };

    /**
     * XQuery 3.0 item types
     */
    public static final String[] XQUERY_KEYWORDS_ITEM_TYPES = { "attribute", "comment", "document-node", "element",
            "empty-sequence", "item", "node", "processing-instruction", "schema-attribute", "schema-element", "text" };

    /**
     * Reserved Function Names http://www.w3.org/TR/xquery/#id-reserved-fn-names
     */
    public static final String[] XQUERY_KEYWORDS_RESERVED_FN_NAMES = { "attribute", "comment", "document-node",
            "element", "empty-sequence", "if", "item", "namespace-node", "node", "processing-instruction",
            "schema-attribute", "schema-element", "text", "typeswitch" };

    /**
     * Entity reference names
     */
    public static final String[] EMTITY_REFERECE_NAMES = { "amp", "apos", "lt", "gt", "quot" };

    /**
     * Entity reference character
     */
    public static final String[] EMTITY_REFERECE_CHAR = { "&", "'", "<", ">", "\"" };

}