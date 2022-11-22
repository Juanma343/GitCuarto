import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Clase para el ejercicio 2. Esta clase obtiene el resultado de dos dados típicos en los juegos de rol que son los D10, es decir, dados de 10 cajas, se suelen usar para ver lo probable que es un suceso, usando uno para las decenas y otro para las unidades, de forma que te dan un número del 0 al 99, con esta clase comprobaremos si hay algún caso más probable que otro y aprovecharemos para usar este algoritmo que nos pide sobreescribir sobre un vector.
 */

public class arrSeguro extends Thread {
    
    public static int tam = 100;
    public static int[] Vector = new int[tam];
    public static Object lock = new Object();

    /**
     * Metodo sobrecargado de la clase thread que incrementa la posición del vector en la que la combinación de dado ha dado.
     */

    public void run(){
        int temp = 0;
        temp += (int)(Math.random() * 10) * 10;
        temp += (int)(Math.random() * 10);
        synchronized(lock){Vector[temp]++;}
    }

    /**
     * Metodo utilizado para mostrar los valores del vector. Ahora esta cometentada la linea que mprimi los valores ya que para esta practica lo que nos interesa si existe entrelazado o no, por lo que hago es la suma de todos los elementos de que es 1000000
     */

    public static void imprimir(){
        int sum = 0;
        for (int i = 0; i < tam; i++){
            // System.out.println(i + " = " + Vector[i]);
            sum += Vector[i];
        }
        System.out.println("La suma es " + sum);
    }

    public static void main(String[] args) {
        ThreadPoolExecutor exec = new ThreadPoolExecutor(4, 12, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
        for (int i = 0; i < 1000000; i++){
            exec.execute(new arrSeguro());
        }

        exec.shutdown();
        while(!exec.isTerminated()){}

        imprimir();
    }
}
