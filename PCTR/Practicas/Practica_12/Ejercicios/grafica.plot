# Codificaci�n ISO Latin-1 y terminal EPS.

set encoding iso_8859_1
set terminal postscript eps

# T�tulo de cada eje.
 
set xlabel "hilos"
set ylabel "speedup"

# Estilo de presentaci�n (puntos interpolados linealmente).

#set data style linespoints

# Creaci�n de los ficheros EPS.

set output "graphic.eps"
plot 'data.txt' using 1:2 with lines
