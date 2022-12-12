#!/bin/bash
niveles=(500 1500 2500 3500 4500 5500 5600 7500 8500 9500 10500)

rm data.txt ; 
for l in "${niveles[@]}"; 
do 
    ../simulador/simulador -level $l -silent >> data.txt ;
done