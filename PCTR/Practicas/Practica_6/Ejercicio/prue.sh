#!bin/bash
javac arrSeguro.java
i=30
until [ $i -lt 0 ]
do
    java arrSeguro
    ((i--))
done