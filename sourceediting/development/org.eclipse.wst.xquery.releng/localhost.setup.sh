#!/bin/bash

# This file will be deprecated soon (?). See ../tools/scripts/start.sh, 
# which can self-bootstrap provided that ant-contrib.jar can be found in
# one of the four places listed in ../buildAll.xml

# use this to create the setup harness for running builds locally, including caching
# a copy of the three releng projects for reuse (faster builds)

# you might also want to point symlinks at checked out projects in your Eclipse workspace instead of using cvs checkouts,
# or import the checked out projects into your workspace so you can edit, test, and commit changes / submit patches 

#default values
projRelengBranch="HEAD"; # default set below
commonRelengBranch="HEAD"; # default set below
basebuilderBranch="R35_M2";
projRelengRoot=":pserver:anonymous@dev.eclipse.org:/cvsroot/tools"; # default if not specified when building
writableBuildRoot=/opt/public/cbi/build
webRoot=/opt/public/cbi
quietCVS="-Q"
dependURL=""
javaHome=""

function usage()
{
	echo "usage: $0"
	echo "-projectid          <REQUIRED: projectid to build, eg., modeling.emf.cdo, tools.gef>"
	echo "-projNamespace      <OPTIONAL: namespace for plugins, features, and .releng project; default: org.eclipse.\$projectName>"
	echo "-projRelengRoot     <OPTIONAL: CVSROOT of org.eclipse.\$subprojectName.releng; default: $projRelengRoot>"
	echo "-projRelengName     <OPTIONAL: org.eclipse.\$subprojectName.releng; default: org.eclipse.\$subprojectName.releng>"
	echo "-projRelengPath     <OPTIONAL: path/to/org.eclipse.\$subprojectName.releng; default: org.eclipse.\$projectName/org.eclipse.\$subprojectName.releng>"
	echo "-projRelengBranch   <CVS branch of org.eclipse.\$subprojectName.releng; default: $projRelengBranch>"
	echo "-commonRelengBranch <CVS branch of org.eclipse.dash.common.releng; default: $commonRelengBranch>"
	echo "-basebuilderBranch  <CVS branch of org.eclipse.releng.basebuilder, eg. R35_M5; default: $basebuilderBranch>"
	
	echo "-writableBuildRoot  <OPTIONAL: dir where builds will occur, eg., /opt/public/modeling or /home/www-data>"
	echo "-downloadsDir   <The directory where dependent zips are downloaded; default: \$writableBuildRoot/downloads>"
	echo "-URL            <The URLs of the Eclipse driver, EMF driver, and any other zips that need to be unpacked into"
	echo "                 the eclipse install to resolve all dependencies. Enter one -URL [...] per required URL.>"
	echo "-downloadsDir and -URL are optional; if omitted, this step is skipped."     
	echo "-javaHome       <The JAVA_HOME directory; default: $JAVA_HOME or value in ../../server.properties>"
	echo ""
	echo "Example 1 - get GEF releng project, required Eclipse driver, and basebuilder:"
	echo "$0 -proj gef -sub gef -version 3.4.0 -basebuilderBranch RC2_34 \\"
	echo "  -projRelengRoot ':pserver:anonymous@dev.eclipse.org:/cvsroot/technology' \\"
	echo "  -projRelengPath 'org.eclipse.dash/athena/org.eclipse.dash.commonbuilder/org.eclipse.gef.releng' \\"
	echo "  -URL http://download.eclipse.org/eclipse/downloads/drops/R-3.4-200806172000/eclipse-SDK-3.4-linux-gtk.tar.gz \\"
	echo "  2>&1 | tee /tmp/$0.log.txt"
	echo ""
	echo "Example 2 - get only a new basebuilder version and insert SVN support:"
	echo "$0 -basebuilderBranch RC2_34"
	exit 1
}

if [[ $# -eq 0 ]]; then
	usage;
fi

while [ "$#" -gt 0 ]; do
	case $1 in
		'-writableBuildRoot') 	
			writableBuildRoot=$2;  
			echo "   $1 $2"; shift 1;;
		'projectid')
			projectid=$2;
			# if x.y -> top.proj, proj=sub
			if [[ ${projectid%.*} == ${projectid%%.*} ]]; then # two-part projectid; single-match trim .* and greedy-match trim .* are the same
				topprojectName=${projectid%%.*}; # get first chunk
				projectName=${projectid##*.}; # get last chunk
				subprojectName=${projectName}; # proj == sub
			else # assume x.y.z -> top.proj.sub 
				topprojectName=${projectid%%.*}; # get first chunk
				subprojectName=${projectid##*.}; # get last chunk
				projectName=${projectid#${topprojectName}.}; # trim first chunk
				projectName=${projectName%.${subprojectName}}; # trim last chunk
			fi				
			#echo "Got: $topprojectName / $projectName / $subprojectName";
			echo "   $1 $2";
			shift 1
			;;
		'-proj')
			projectName=$2;
			echo "   $1 $2"; shift 1;;
		'-sub')
			subprojectName=$2;
			echo "   $1 $2"; shift 1;;
		'-URL')
			if [ "x$dependURL" != "x" ]; then
			  dependURL="$dependURL "
			fi
			dependURL=$dependURL"$2";
			echo "   $1 $2"; shift 1;;
		'-downloadsDir')
			downloadsDir=$2;
			echo "   $1 $2"; shift 1;;
		'-basebuilderBranch')
			basebuilderBranch=$2;
			echo "   $1 $2"; shift 1;;
		'-projNamespace')
			projNamespace=$2;
			echo "   $1 $2"; shift 1;;
		'-projRelengRoot')
			projRelengRoot=$2;
			echo "   $1 $2"; shift 1;;
		'-projRelengName')
			projRelengName=$2;
			echo "   $1 $2"; shift 1;;
		'-projRelengPath')
			projRelengPath=$2;
			echo "   $1 $2"; shift 1;;
		'-projRelengBranch')
			projRelengBranch=$2;
			echo "   $1 $2"; shift 1;;
		'-commonRelengBranch')
			commonRelengBranch=$2;
			echo "   $1 $2"; shift 1;;
		'-javaHome')
			javaHome=$2;
			echo "   $1 $2"; shift 1;;
	esac
	shift 1
done	

if [[ ! $projNamespace ]]; then
	projNamespace="org.eclipse.${projectName}"
fi
if [[ ! $projRelengName ]]; then
	projRelengName="org.eclipse.${subprojectName}.releng"
fi
if [[ ! $projRelengPath ]]; then
	projRelengPath="${projNamespace}/${projRelengName}"
fi
if [ "x$downloadsDir" = "x" ]; then
	downloadsDir=$writableBuildRoot/downloads
fi

wgetExists=$(which wget 2>/dev/null); wgetExists=${wgetExists##no wget in*}
curlExists=$(which curl 2>/dev/null); curlExists=${curlExists##no curl in*}
if [[ ! $wgetExists ]] && [[ ! $curlExists ]]; then 
	echo "This script requires wget or curl. Please install wget or curl and try again."
	exit 1;
fi

# create build root -- this is where the build will run
# create web root -- this is where the web UI will be
# create downloads dir -- this is where downloaded deps will be cached for reuse
mkdir -p $webRoot $writableBuildRoot $downloadsDir

# 1/3 export or checkout from CVS -- you might also want to import these projects into your workspace so you can edit locally, test locally, and commit changes / submit patches 
pushd $writableBuildRoot >/dev/null
if [[ $basebuilderBranch ]]; then
  echo "[setup] [`date +%H\:%M\:%S`] Export org.eclipse.releng.basebuilder using "$basebuilderBranch;
else
  echo "[setup] [`date +%H\:%M\:%S`] Export org.eclipse.releng.basebuilder using HEAD";
fi
if [[ ! -d $writableBuildRoot/org.eclipse.releng.basebuilder_${basebuilderBranch} ]]; then
  cmd="cvs -d :pserver:anonymous@dev.eclipse.org:/cvsroot/eclipse $quietCVS ex -r $basebuilderBranch -d org.eclipse.releng.basebuilder_${basebuilderBranch} org.eclipse.releng.basebuilder"
  echo "  "$cmd; $cmd; 
  echo "[setup] [`date +%H\:%M\:%S`] Export done."
else
  echo "[setup] Export skipped ($writableBuildRoot/org.eclipse.releng.basebuilder_${basebuilderBranch} already exists)."
fi
echo ""

# get pde.build.svn plugin (http://sourceforge.net/projects/svn-pde-build/) and unpack into releng.basebuilder's root folder
pushd $writableBuildRoot >/dev/null
echo "[setup] [`date +%H\:%M\:%S`] Get org.eclipse.pde.build.svn-*.zip from Sourceforge ..."

if [[ $wgetExists ]]; then 
	wget --no-clobber http://downloads.sourceforge.net/svn-pde-build/org.eclipse.pde.build.svn-1.0.1RC2.zip
elif [[ $curlExists ]]; then
	curl http://downloads.sourceforge.net/svn-pde-build/org.eclipse.pde.build.svn-1.0.1RC2.zip -o $writableBuildRoot/org.eclipse.pde.build.svn-1.0.1RC2.zip 
else
	echo "Cannot get http://downloads.sourceforge.net/svn-pde-build/org.eclipse.pde.build.svn-1.0.1RC2.zip. Please install wget or curl."
	exit 1;
fi
unzip -qq org.eclipse.pde.build.svn-1.0.1RC2.zip -d org.eclipse.pde.build.svn
echo "[setup] [`date +%H\:%M\:%S`] Unpack pde.build.svn into $writableBuildRoot/org.eclipse.releng.basebuilder_${basebuilderBranch} ..."
pushd org.eclipse.pde.build.svn/org.eclipse.releng.basebuilder/ >/dev/null
#mkdir -p $writableBuildRoot/org.eclipse.releng.basebuilder_${basebuilderBranch}/features 
mkdir -p $writableBuildRoot/org.eclipse.releng.basebuilder_${basebuilderBranch}/plugins
for f in $(find . -maxdepth 2 -mindepth 2 -type f); do # remove "-type f" to collect features too; 
	g=${f:2}; echo "                   $g";
	if [[ -d $writableBuildRoot/org.eclipse.releng.basebuilder_${basebuilderBranch}/$g ]] || [[ -f $writableBuildRoot/org.eclipse.releng.basebuilder_${basebuilderBranch}/$g ]]; then
		rm -fr $writableBuildRoot/org.eclipse.releng.basebuilder_${basebuilderBranch}/$g;
	fi
	mv -f $g $writableBuildRoot/org.eclipse.releng.basebuilder_${basebuilderBranch}/$g
done
popd >/dev/null
rm -fr org.eclipse.pde.build.svn
popd >/dev/null
echo "[setup] [`date +%H\:%M\:%S`] SVN support added to basebuilder."

# 2/3 export or checkout from CVS
echo "[setup] [`date +%H\:%M\:%S`] Checkout org.eclipse.dash.common.releng using "$commonRelengBranch;
if [[ ! -d $writableBuildRoot/org.eclipse.dash.common.releng_${commonRelengBranch} ]]; then 
  cmd="cvs -d :pserver:anonymous@dev.eclipse.org:/cvsroot/technology $quietCVS co -r $commonRelengBranch -d org.eclipse.dash.common.releng_${commonRelengBranch} org.eclipse.dash/athena/org.eclipse.dash.commonbuilder/org.eclipse.dash.commonbuilder.releng";
  echo "  "$cmd; $cmd; 
  chmod 754 org.eclipse.dash.common.releng_${commonRelengBranch}/tools/scripts/*.sh
  echo "[setup] [`date +%H\:%M\:%S`] Checkout done."
else
  echo "[setup] Checkout skipped ($writableBuildRoot/org.eclipse.dash.common.releng_${commonRelengBranch} already exists)."
fi
echo ""

# TODO: to support a SVN repo for the project's .releng, need to do something else here 
# 3/3 export or checkout from CVS
echo "[setup] [`date +%H\:%M\:%S`] Checkout ${projRelengName} using "$projRelengBranch;
if [[ ! -d $writableBuildRoot/${projRelengName}_${projRelengBranch} ]]; then 
  cmd="cvs -d ${projRelengRoot//\'/} $quietCVS co -r $projRelengBranch -d ${projRelengName}_${projRelengBranch} $projRelengPath";
  echo "  "$cmd; $cmd; 
  echo "[setup] [`date +%H\:%M\:%S`] Checkout done."
else
  echo "[setup] Checkout skipped ($writableBuildRoot/${projRelengName}_${projRelengBranch} already exists)."
fi
echo ""

popd >/dev/null

# get URLs and cache in downloadsDir
mkdir -p $downloadsDir
pushd $downloadsDir >/dev/null
for dep in $dependURL; do
	if [[ ! -f $downloadsDir/${dep##*/} ]]; then
		echo -n "[setup] [`date +%H\:%M\:%S`] Downloading $dep into $downloadsDir..."
		if [[ $wgetExists ]]; then 
			wget -q --no-clobber $dep
		elif [[ $curlExists ]]; then
			curl $dep -o $downloadsDir/${dep##*/}
		else
			echo "Cannot get $dep. Please install wget or curl."
			exit 1;
		fi
		echo " done."
	else
		echo "[setup] Download skipped ($downloadsDir/${dep##*/} already exists)."
	fi
done
popd >/dev/null

echo "[setup] Create absolute-path symlinks..."

# TODO : deprecated path -- remove symlink to releng-common & see what breaks
cd $writableBuildRoot; 
if [[ -L releng-common ]]; then rm -f releng-common; fi
ln -s $writableBuildRoot/org.eclipse.dash.common.releng_${commonRelengBranch}/ releng-common

cd $writableBuildRoot; 
for d in org.eclipse.releng.basebuilder org.eclipse.dash.common.releng scripts; do
	if [[ -L $d ]]; then rm -f $d; fi
done
ln -s $writableBuildRoot/org.eclipse.releng.basebuilder_${basebuilderBranch}/ org.eclipse.releng.basebuilder
ln -s $writableBuildRoot/org.eclipse.dash.common.releng_${commonRelengBranch}/ org.eclipse.dash.common.releng
ln -s $writableBuildRoot/org.eclipse.dash.common.releng/tools/scripts/

echo "[setup] Check server configuration (java, ant, php, ...)"

relengCommonBuilderDir=$writableBuildRoot/org.eclipse.dash.common.releng/tools/scripts
configfile=$relengCommonBuilderDir/../../server.properties 

if [ "x$javaHome" != "x" ]; then
	export JAVA_HOME=$javaHome;
else # use default
	export JAVA_HOME=$($relengCommonBuilderDir/readProperty.sh $configfile JAVA_HOME)
	javaHome="$JAVA_HOME"
fi

if [[ ! $javaHome ]]; then
	export JAVA_HOME=$($relengCommonBuilderDir/readProperty.sh $configfile JAVA50_HOME)
	javaHome="$JAVA_HOME"
fi

if [[ ! ${ANT_HOME} ]]; then
	export ANT_HOME=$($relengCommonBuilderDir/readProperty.sh $configfile ANT_HOME);
fi
if [[ ! ${ANT_BIN} ]]; then
	ANT_BIN=$($relengCommonBuilderDir/readProperty.sh $configfile ANT_BIN);
fi
if [[ ${ANT_BIN} ]]; then
	export ANT=$ANT_BIN
else
	export ANT=$ANT_HOME"/bin/ant";
fi

# get path to PHP interpreter
if [ -x /usr/bin/php ]; then
	PHP=/usr/bin/php
elif [ -x /usr/bin/php5 ]; then
	PHP=/usr/bin/php5
elif [ -x /usr/bin/php4 ]; then
	PHP=/usr/bin/php4
else
	PHP=php
fi

if [[ $javaHome ]]; then
	if [[ ! -d $javaHome ]] && [[ ! -L $javaHome ]]; then
		echo "[setup] Error: $javaHome is not a dir or symlink. Please install Java."
	else
		echo "[setup] Java is installed in $javaHome."
	fi
fi

if [[ ! -x $ANT ]]; then
	echo "[setup] Error: cannot run $ANT. Please install Ant in \$ANT_HOME=$ANT_HOME"
else
	echo "[setup] Ant is installed as $ANT."
fi

if [[ ! -x $PHP ]]; then
	echo "[setup] Error: cannot run $PHP. Please install PHP as \$PHP=$PHP"
else
	echo "[setup] PHP is installed as $PHP."
fi

# TODO add other checks? 

echo "Done.";
