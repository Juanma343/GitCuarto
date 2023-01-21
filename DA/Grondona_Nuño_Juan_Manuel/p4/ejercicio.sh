#!/bin/bash
niveles=(1224 2225 2054 2056 2556 2452 2428 3256 3292)
promedio=(113.82 237.56 154.89 158.24 307.03 375.67 313.15 282.85 274.28)
minimo=(33.10 94.90 32.00 38.50 122.00 151.00 158.50 147.30 86.20)

#preguntar chat gpt bash ver violacion segmento
length=${#niveles[@]}
for (( j=0; j<${length}; j++ ));
do
    printf "Nivel = %s\nMedia = %s minimo = %s\n" ${niveles[$j]} ${promedio[$j]} ${minimo[$j]};
    ../simulador/simulador -level ${niveles[$j]};
    
    
done