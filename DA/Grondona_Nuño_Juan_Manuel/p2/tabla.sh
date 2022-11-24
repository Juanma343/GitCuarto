#!/bin/bash
niveles=(3124 3125 3054 3056 3056 3056 3052 3028 4056 4092)
pases=(0.5 0.6 0.4 0.5 0.7 1 0.5 0.6 0.3 0.5)
defenses=(30 40 40 50 40 50 40 60 70 80)
defensestypes=(30 40 30 40 20 50 40 50 70 70)
defenseremdomness=(0.5 0.6 0.4 0.5 0.7 0.5 0.5 0.6 0.3 0.5)
promedio=(312.69 303.90 99.20 380.67 555.18 474.60 122.47 573.59 209.01 326.84)
maximo=(512.02 507.92 155.50 525.32 647.89 562.11 259.21 927.12 392.41 589.80)

make
rm historial.txt
length=${#niveles[@]}
for (( j=0; j<${length}; j++ ));
do
    printf "Nivel = %s\nMedia = %s Maxima = %s\n" ${niveles[$j]} ${promedio[$j]} ${maximo[$j]}
    ../simulador/simulador -level ${niveles[$j]} -pases ${pases[$j]} -d ${defenses[$j]} -dt ${defensestypes[$j]} -dr ${defenseremdomness[$j]} -replay replay.txt | tee -a historial.txt
    # "\n" >> historial.txt
done
