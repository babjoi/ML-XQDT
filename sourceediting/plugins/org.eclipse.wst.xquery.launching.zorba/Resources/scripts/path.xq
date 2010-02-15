declare variable $zorbaRoot external;

let $paths :=
  (
    fn:concat("DLTK:", $zorbaRoot, "/include/zorba/modules")
  )
for $path in $paths
return
  concat("
", $path)