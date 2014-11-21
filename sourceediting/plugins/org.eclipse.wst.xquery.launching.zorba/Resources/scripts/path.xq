import module namespace file = "http://expath.org/ns/file";

declare variable $zorbaRoot external;
declare variable $sep := file:directory-separator();

let $path := fn:concat($zorbaRoot, $sep, "share")
let $dir := file:list($path)[fn:starts-with(., "zorba")]
return
  fn:concat("DLTK:", $path, $sep, $dir, $sep, "modules")
