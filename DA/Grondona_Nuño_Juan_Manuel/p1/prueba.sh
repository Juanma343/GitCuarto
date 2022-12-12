#!/bin/bash
niveles=(1324 1625 1854 2356 2856 3456 3552 3728 4356 4592)
for j in "${niveles[@]}"
do
    echo $j
    ../simulador/simulador -level $j -replay replay.txt
    ./../visor/bin/lin/asedio_player.x86_64 -replay replay.txt
done