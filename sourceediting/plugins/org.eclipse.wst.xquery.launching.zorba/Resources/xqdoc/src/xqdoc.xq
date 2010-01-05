import module namespace file = "http://www.zorba-xquery.com/modules/file";
import module namespace xqd = "http://www.zorba-xquery.com/modules/xqdoc";
import module namespace xqdg = "http://www.zorba-xquery.com/modules/internal/xqdoc-xhtml" at "xqdoc-xhtml.xq";

declare variable $modulePath as xs:string external;
declare variable $stylesheet as xs:string external;

let $xqdoc := xqd:xqdoc(file:path-to-uri($modulePath))/*:xqdoc
let $xhtml := xqdg:doc($xqdoc)
return block {
    replace value of node $xhtml/*:head/*:link/@href with $stylesheet
    ;
    $xhtml;
}