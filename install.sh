#!/bin/bash

IFS='
'
PATH=''
CP=/bin/cp
CHMOD=/bin/chmod
MKDIR=/bin/mkdir

PREFIX=$1 ; shift

[ -z "$PREFIX" ] && exit 1
if [ ! -d "$PREFIX/bin" ]
then
        ${MKDIR} -p $PREFIX/bin
        ${CHMOD} 755 $PREFIX/bin
fi

${CP} jwordpress $PREFIX/bin
${CHMOD} 755 $PREFIX/bin/jwordpress
${CP} target/jwordpress.jar $PREFIX/bin
${CHMOD} 644 $PREFIX/bin/jwordpress.jar
