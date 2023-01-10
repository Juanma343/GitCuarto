# Codificaciï¿½n ISO Latin-1 y terminal EPS.

set encoding iso_8859_1
set terminal postscript eps

# Tï¿½tulo de cada eje.
 
set xlabel "n (número de iteraciones)"
set ylabel "t(n) (tiempo en milisegundos)"

# Estilo de presentaciï¿½n (puntos interpolados linealmente).

#set data style linespoints

# Creaciï¿½n de los ficheros EPS.

set output "graphic.eps"
plot 'data.txt' using 1:2 title "synchronized" with lines, \
     '' using 1:3 title "Semaphore" with lines, \
	 '' using 1:4 title "ReentrantLock" with lines, \
	 '' using 1:5 title "atomic" with lines
