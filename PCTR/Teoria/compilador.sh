#!/bin/bash

if [[ $1 = 1 ]]
then
    carpeta="$2"
    main="$3"
    shift 2
    javac /home/juanma/git/GitCuarto/PCTR/Teoria/CodigosSinCompilar/$carpeta/$*.java -d /home/juanma/git/GitCuarto/PCTR/Teoria/CodigosCompilados/$carpeta
    cd /home/juanma/git/GitCuarto/PCTR/Teoria/CodigosCompilados/$carpeta
    java $main
else
    cd /home/juanma/git/GitCuarto/PCTR/Teoria/CodigosCompilados/$carpeta
    java $main
fi