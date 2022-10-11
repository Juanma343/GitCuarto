#!/bin/bash
javac /home/juanma/git/GitCuarto/PCTR/Teoria/CodigosSinCompilar/$1/$2.java -d /home/juanma/git/GitCuarto/PCTR/Teoria/CodigosCompilados/$1
cd /home/juanma/git/GitCuarto/PCTR/Teoria/CodigosCompilados/$1
java $2