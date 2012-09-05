#!/bin/bash

IFS='
'

dest=/usr
if [ ! -z "$@" ]
then
  dest=$@
fi

mkdir -p ${dest}/bin
mkdir -p ${dest}/lib

cp target/jwordpress ${dest}/bin
cp target/jwordpress-[0-9][0-9.]*-*cli.jar ${dest}/lib

chmod u=rwx,go=rx ${dest}/bin/jwordpress
chmod u=rw,go=r ${dest}/lib/jwordpress-[0-9][0-9.]*-*cli.jar
