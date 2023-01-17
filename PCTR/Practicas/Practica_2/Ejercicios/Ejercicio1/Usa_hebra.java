package Ejercicio1;
import Ejercicio1.Dependencias.*;
/**
 * Clase encargada de hejeturar los hilos de la clase hebra
   Simplemenre clea dos hilos, uno del primer tipo, el cual suma la variable estatica,
   y otro del segundo tipo, que la decrementa
   
   Este ejercicio no muentra los problemas del entrelazado ya que si funcionara idealemente nos daria
   simpre n = 0 ya que son el mimo numero de iteraciones y son uno de cada tipo, pero en este caso no
   ocurre precisamente por que hay trelazado.
 * @see Ejercicos.hebra
 */

public class Usa_hebra {

    /**
     * metodo proncipal de la calse y de la cual se ejecuta todo el codigo
     */

    public static void main(String[] args) throws  Exception{
      for(int i = 100; i < 1000000; i*=10){
        hebra p = new  hebra (i , 0);
        hebra q = new  hebra (i , 1);
        p.start ();
        q.start ();
        p.join();
        q.join();
        System.out.println("La variable estatica n = " + p.n() + " Para un valor de nVuelatas de " + i);
        // hebra.n() = 0;
      }
    }   
}
