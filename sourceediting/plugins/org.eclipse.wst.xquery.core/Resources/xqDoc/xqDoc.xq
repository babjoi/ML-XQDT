(:
 Copyright (c) 2008, 2009 28msec Inc. and others.
 All rights reserved. This program and the accompanying materials
 are made available under the terms of the Eclipse Public License v1.0
 which accompanies this distribution, and is available at
 http://www.eclipse.org/legal/epl-v10.html

 Contributors:
     William Candillon (28msec) - initial API and implementation
:)
import module namespace util="http://www.zorba-xquery.com/zorba/util-functions";
import module namespace xq="http://www.zorba-xquery.com/zorba/xqdoc-functions";

declare namespace xqdoc="http://www.xqdoc.org/1.0";
declare namespace zorbadoc="http://www.zorba-xquery.com/zorba/doc";
declare default element namespace "http://www.w3.org/1999/xhtml";
declare variable $query as xs:string external;
declare variable $stylesheet as xs:string external;

declare function local:get-xhtml($xqdoc, $stylesheet as xs:string)
{
    <html lang="EN" xml:lang="EN">
    {(local:get-header($xqdoc, $stylesheet), local:get-body($xqdoc))}
    </html>
};

declare function local:get-header($xqdoc, $stylesheet as xs:string)
{
    <head>
        <title>Documentation for {local:get-module-uri($xqdoc)}</title>
        <link rel="stylesheet" type="text/css" href="{$stylesheet}" />
    </head>
};

declare function local:get-body($xqdoc)
{
    <body>
        <h1>{local:get-module-uri($xqdoc)}</h1>
        {(local:get-module-description($xqdoc/xqdoc:module),
           local:get-module-variables($xqdoc/xqdoc:variables),
           local:get-module-function-summary($xqdoc/xqdoc:functions),
           local:get-module-functions($xqdoc/xqdoc:functions))}
    </body>
};

declare function local:get-module-uri($xqdoc) as xs:string
{
    $xqdoc/xqdoc:module/xqdoc:uri/text()
};

declare function local:get-annotations($comment)
{
    let $annotations := $comment/xqdoc:*[local-name(.) != "description" and local-name(.) != "param"]
    return
        if(not(empty($annotations))) then
            <ul>{
            for $annotation in $annotations
            return <li><span class="bold">{local-name($annotation)}:&#32;</span> {util:parse(concat("&lt;span&gt;", $annotation/text(), "&lt;/span&gt;"))}</li>
            }</ul>
        else()
};

declare function local:get-parameter-description($comment, $name as xs:string)
{
    for $param in $comment/xqdoc:*[local-name(.) eq "param" and substring-before(./text(), ' ') eq $name]
    return substring-after($param/text(), ' ')
};

declare function local:get-parameters($function)
{
    (<h2>Arguments and Return Type</h2>,<table cellpadding="3" cellspacing="3">
    <tbody>{
    (
    <tr><th>Name</th><th>Type</th><th>Description</th></tr>,
    for $param in $function/zorbadoc:parameters/zorbadoc:parameter
    let $name := xs:string($param/@name)
    let $type := xs:string($param/@type)
    let $occurrence := xs:string($param/@occurrence)
    let $description := local:get-parameter-description($param/../../xqdoc:comment, $name)
    return <tr>
                <td><code><b>{$name}</b></code></td>
                <td><code>{if($type) then concat($type, $occurrence) else ()}</code></td>
                <td><code>{if($description) then $description else ()}</code></td>
           </tr>
    ,
    <tr><td>Return value</td><td>{substring-after($function/xqdoc:signature/text(), ") as ")}</td></tr>)
    }</tbody></table>)
};

declare function local:get-description($comment)
{
     <p>
     {
        if($comment/xqdoc:description) then
            util:parse(concat("&lt;span&gt;", replace($comment/xqdoc:description/text(), "\n", "<br />"), "&lt;/span&gt;"))
        else
            "No description available."
     }
     </p>
};

declare function local:get-module-description($module)
{
    (<h2>Module Description</h2>,
     local:get-description($module/xqdoc:comment),
     local:get-annotations($module/xqdoc:comment))
};

declare function local:get-module-variables($variables)
{
    (<h2>Variables</h2>,
    if(count($variables/xqdoc:variable)) then
        for $variable in $variables/xqdoc:variable
        return (<h3>{$variable/xqdoc:uri}</h3>,
                local:get-description($variable/xqdoc:comment),
                local:get-annotations($variable/xqdoc:comment))
    else
        <p>No variables declared.</p>
    )
};

declare function local:get-module-function-summary($functions)
{
    (<h2>Function Summary</h2>,
    if(count($functions/xqdoc:function)) then
         for $function in $functions/xqdoc:function
         let $name := $function/xqdoc:name/text(), $param-number := count($function/zorbadoc:parameters/zorbadoc:parameter)
         return <p><a href="#{$name}-{$param-number}">{$name}</a> <pre>{$function/xqdoc:signature/text()}</pre></p>
    else
        <p>No functions declared.</p>
    )
};

declare function local:get-module-functions($functions)
{
    if(count($functions/xqdoc:function)) then
        (<h2>Functions</h2>,
         for $function in $functions/xqdoc:function
         let $name := $function/xqdoc:name/text(),
             $param-number := count($function/zorbadoc:parameters/zorbadoc:parameter)
         return (<h3 id="{$name}-{$param-number}">{$name}</h3>,
                  <pre>{$function/xqdoc:signature/text()}</pre>,
                local:get-description($function/xqdoc:comment),
                local:get-parameters($function),
                local:get-annotations($function/xqdoc:comment))
         )
    else ()
};
local:get-xhtml(xq:xqdoc($query)/xqdoc:xqdoc, $stylesheet)