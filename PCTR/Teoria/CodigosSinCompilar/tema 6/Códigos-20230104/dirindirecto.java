//direccionamiento indirecto
Channel of Integer Buzon;
Proceso P0            Proceso P1
int dato;             int x;
produce(dato);        receive(&x, Buzon);
send(&dato, Buzon);   consume(x);