#!/bin/bash

IFS='
'
PATH=''
CP=/bin/cp
CHMOD=/bin/chmod
MKDIR=/bin/mkdir

if [ -z "$PREFIX" ]
then
        PREFIX=/usr/local
fi
echo "Installing to $PREFIX"
if [ ! -d "$PREFIX/bin" ]
then
        ${MKDIR} -p $PREFIX/bin
        ${CHMOD} 755 $PREFIX/bin
fi

${CP} jwordpress $PREFIX/bin
${CHMOD} 755 $PREFIX/bin/jwordpress
${CP} target/jwordpress.jar $PREFIX/bin
${CHMOD} 644 $PREFIX/bin/jwordpress.jar
