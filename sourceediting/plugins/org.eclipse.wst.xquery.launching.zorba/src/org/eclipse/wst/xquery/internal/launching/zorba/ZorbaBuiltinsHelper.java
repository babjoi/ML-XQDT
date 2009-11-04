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
package org.eclipse.wst.xquery.internal.launching.zorba;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.dltk.core.DLTKCore;

public class ZorbaBuiltinsHelper {

    private Map<String, String> fSources;

    public synchronized Map<String, String> getSources() {
        if (fSources == null) {
            fSources = new HashMap<String, String>();
            readModules();
        }

        return fSources;
    }

    private void readModules() {
        fSources.put("math-functions.xq",
                "module namespace math = \"http://www.zorba-xquery.com/zorba/math-functions\";\n" + "\n"
                        + "declare function math:sqrt ($arg as numeric?) as numeric? external;\n"
                        + "declare function math:exp ($arg as xs:double) as xs:double external;\n"
                        + "declare function math:log ($arg as xs:double) as xs:double external;\n"
                        + "declare function math:sin ($arg as xs:double) as xs:double external;\n"
                        + "declare function math:cos ($arg as xs:double) as xs:double external;\n"
                        + "declare function math:tan ($arg as xs:double) as xs:double external;\n"
                        + "declare function math:asin ($arg as xs:double) as xs:double external;\n"
                        + "declare function math:acos ($arg as xs:double) as xs:double external;\n"
                        + "declare function math:atan ($arg as xs:double) as xs:double external;\n");

        fSources.put("node-ref-functions.xq",
                "module namespace noderef = \"http://www.zorba-xquery.com/zorba/node-ref-functions\";\n" + "\n"
                        + "declare function noderef:node-reference ($node as node()) as xs:anyURI external;\n"
                        + "declare function noderef:node-by-reference ($ref as xs:anyURI) as node() external;\n");

        fSources.put("internal-functions.xq",
                "module namespace internal = \"http://www.zorba-xquery.com/zorba/internal-functions\";\n" + "\n"
                        + "declare function internal:read-line () as xs:string external;\n"
                        + "declare function internal:print ($args as item()*) as xs:none external;\n");

        fSources
                .put(
                        "util-functions.xq",
                        "module namespace util = \"http://www.zorba-xquery.com/zorba/util-functions\";\n"
                                + "\n"
                                + "declare function util:tidy($str as xs:string) as item() external;\n"
                                + "declare function util:tidy($str as xs:string, $tidyOptions as xs:string?) as item() external;\n"
                                + "declare function util:tdoc($uri as xs:string?) as document-node()? external;\n"
                                + "declare function util:tdoc($uri as xs:string, $tidyOptions as xs:string?) as document-node()? external;\n"
                                + "declare function util:uuid() as xs:string external;\n"
                                + "declare function util:random() as xs:integer external;\n"
                                + "declare function util:random($seed as xs:integer) as xs:integer external;\n");

        fSources
                .put(
                        "collection-functions.xq",
                        "module namespace coll = \"http://www.zorba-xquery.com/zorba/collection-functions\";\n"
                                + "\n"
                                + "declare updating function coll:import-xml ($uri as xs:anyURI) external;\n"
                                + "declare updating function coll:import-catalog ($uri as xs:anyURI) external;\n"
                                + "declare function coll:list-collections () as xs:anyURI* external;\n"
                                + "declare updating function coll:create-collection ($uri as xs:anyURI, $nodes as node()*) external;\n"
                                + "declare updating function coll:delete-collection () external;\n"
                                + "declare updating function coll:delete-collection ($uri as xs:string?) external;\n"
                                + "declare updating function coll:delete-all-collections () external;\n"
                                + "declare updating function coll:insert-nodes-first ($newnode as node()*) external;\n"
                                + "declare updating function coll:insert-nodes-first ($uri as xs:string?, $newnode  as node()*) external;\n"
                                + "declare updating function coll:insert-nodes-last ($newnode as node()*) external;\n"
                                + "declare updating function coll:insert-nodes-last ($uri as xs:string?, $newnode as node()*) external;\n"
                                + "declare updating function coll:insert-nodes-before ($target as node()+, $newnode as node()*) external;\n"
                                + "declare updating function coll:insert-nodes-before ($uri as xs:string?, $target as node()+, $newnode as node()*) external;\n"
                                + "declare updating function coll:insert-nodes-after ($target as node()+, $newnode as node()*) external;\n"
                                + "declare updating function coll:insert-nodes-after ($uri as xs:string?, $target as node()+, $newnode as node()*) external;\n"
                                + "declare updating function coll:insert-nodes-at ($position as xs:integer, $newnode as node()*) external;\n"
                                + "declare updating function coll:insert-nodes-at ($uri as xs:string?, $position as xs:integer, $newnode as node()*) external;\n"
                                + "declare updating function coll:remove-nodes ($target as node()+) external;\n"
                                + "declare updating function coll:remove-nodes ($uri as xs:string?, $target as node()+) external;\n"
                                + "declare updating function coll:remove-nodes-at ($position as xs:integer) external;\n"
                                + "declare updating function coll:remove-nodes-at ($uri as xs:string?, $position as xs:integer) external;\n"
                                + "declare function coll:nodes-count () as xs:integer? external;\n"
                                + "declare function coll:nodes-count ($uri as xs:string?) as xs:integer? external;\n"
                                + "declare function coll:node-at ($position as xs:integer) as node()? external;\n"
                                + "declare function coll:node-at ($uri as xs:string?, $position as xs:integer) as node()? external;\n"
                                + "declare function coll:index-of ($target as node()) as xs:integer external;\n"
                                + "declare function coll:index-of ($uri as xs:string?, $target as node()) as xs:integer external;\n"
                                + "declare function coll:export-xml ($uri as xs:string) external;\n"
                                + "declare function coll:export-xml ($uri as xs:string, $targeturi as xs:string) external;\n"
                                + "declare function coll:collection-exists () as xs:boolean external;\n"
                                + "declare function coll:collection-exists ($uri as xs:string?) as xs:boolean external;\n");

        fSources
                .put(
                        "rest-functions.xq",
                        "module namespace zorba-rest = \"http://www.zorba-xquery.com/zorba/rest-functions\";\n"
                                + "\n"
                                + "declare sequential function zorba-rest:get($url as xs:string) as element(zorba-rest:result) external;\n"
                                + "declare sequential function zorba-rest:get(&url as xs:string, $payload as element(zorba-rest:payload)) as element(zorba-rest:result); external;\n"
                                + "declare sequential function zorba-rest:get($url as xs:string, $payload as element(zorba-rest:payload), $headers as element(zorba-rest:headers)*) as element(zorba-rest:result); external;\n"
                                + "declare sequential function zorba-rest:getTidy($url as xs:string, $tidyOptions as xs:string) as element(zorba-rest:result) external;\n"
                                + "declare sequential function zorba-rest:getTidy($url as xs:string, $tidyOptions as xs:string, $payload as element(zorba-rest:payload)) as element(zorba-rest:result) external;\n"
                                + "declare sequential function zorba-rest:getTidy($url as xs:string, $tidyOptions as xs:string, $payload as element(zorba-rest:payload), $headers as element(zorba-rest:headers)*) as element(zorba-rest:result); external;\n"
                                + "declare sequential function zorba-rest:post($url as xs:string) as element(zorba-rest:result) external;\n"
                                + "declare sequential function zorba-rest:post($url as xs:string, $payload as element(zorba-rest:payload)) as element(zorba-rest:result) external;\n"
                                + "declare sequential function zorba-rest:post($url as xs:string, $payload as element(zorba-rest:payload), $headers as element(zorba-rest:headers)*) as element(zorba-rest:result); external;\n"
                                + "declare sequential function zorba-rest:put($url as xs:string) as element(zorba-rest:result) external;\n"
                                + "declare sequential function zorba-rest:put($url as xs:string, $payload as element(zorba-rest:payload)) as element(zorba-rest:result) external;\n"
                                + "declare sequential function zorba-rest:put($url as xs:string, $payload as element(zorba-rest:payload), $headers as element(zorba-rest:headers)*) as element(zorba-rest:result); external;\n"
                                + "declare sequential function zorba-rest:head($url as xs:string) as element(zorba-rest:result) external;\n"
                                + "declare sequential function zorba-rest:head($url as xs:string, $payload as element(zorba-rest:payload)) as element(zorba-rest:result) external;\n"
                                + "declare sequential function zorba-rest:head($url as xs:string, $payload as element(zorba-rest:payload), $headers as element(zorba-rest:headers)*) as element(zorba-rest:result); external;\n"
                                + "declare sequential function zorba-rest:delete($url as xs:string) as element(zorba-rest:result) external;\n"
                                + "declare sequential function zorba-rest:delete($url as xs:string, $payload as element(zorba-rest:payload)) as element(zorba-rest:result) external;\n"
                                + "declare sequential function zorba-rest:delete($url as xs:string, $payload as element(zorba-rest:payload), $headers as element(zorba-rest:headers)*) as element(zorba-rest:result); external;\n");

        Thread readerThread = new Thread(new Runnable() {

            public void run() {
                // IFileHandle[] modules = modulePath.getChildren();
                // BufferedReader input = null;
                // StringBuilder lines = null;
                // for (int i = 0; i < modules.length; i++) {
                // String name = modules[i].getName();
                // if (name.endsWith(".xq")) {
                // try {
                // input = new BufferedReader(new InputStreamReader(new
                // FileInputStream(modules[i].getCanonicalPath())));
                // lines = new StringBuilder();
                // String line = null;
                // try {
                // while ((line = input.readLine()) != null) {
                // lines.append(line + "\n");
                // }
                // fSources.put(name, lines.toString());
                // } catch (IOException e) {
                // if (DLTKCore.DEBUG) {
                // e.printStackTrace();
                // }
                // }
                // } catch (IOException e) {
                // if (DLTKCore.DEBUG) {
                // e.printStackTrace();
                // }
                // }
                // }
                // }
            }
        });

        try {
            readerThread.start();
            readerThread.join(10000);
        } catch (InterruptedException e) {
            if (DLTKCore.DEBUG) {
                e.printStackTrace();
            }
        }
    }

    // private void parseLines(String[] lines) {
    // String fileName = null;
    // StringBuffer sb = new StringBuffer();
    // for (int i = 0; i < lines.length; ++i) {
    // String line = lines[i];
    //
    // int index = line.indexOf(PREFIX);
    // if (index != -1) {
    // if (fileName != null) {
    // String old = (String) sources.get(fileName);
    // if (old == null)
    // sources.put(fileName, sb.toString());
    // else
    //							sources.put(fileName, old + "\n\n" + sb.toString()); //$NON-NLS-1$
    // sb.setLength(0);
    // }
    //
    // fileName = line.substring(index + PREFIX.length());
    //
    // } else {
    // sb.append(line);
    //					sb.append("\n"); //$NON-NLS-1$
    // }
    // }
    // }

}
