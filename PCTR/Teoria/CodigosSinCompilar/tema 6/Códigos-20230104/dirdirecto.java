//direccionamiento directo
Proceso P0            Proceso P1
int dato;             int x;
produce(dato);        receive(&x, P0);
send(&dato, P1);      consume(x);