import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class hilos4{

    /**
     * variables encargadas de los turnos
     */

    public static volatile boolean wantq = false;
    public static volatile boolean wantp = false;
    public static volatile int last = 1;

    /**
     * variable que se modifica en la seccion critca
     */

    public static volatile int n = 0;

    /**
     * clase del tipo de hebra p, la cual tiene el metodo run sobrecargado con el algoritmo de dekker. utiliza el extend de la calse thread.
     */

    class p implements Runnable {
        public void run(){
            while (true){
                hilos4.wantp = true;
                hilos4.last = 1;
                while (hilos4.wantq && last != 2){}
                hilos4.n++;
                System.out.println(Thread.currentThread().getName());
                hilos4.wantp = false;
            }
        }
    }

    /**
     * clase del tipo de hebra q, la cual tiene el metodo run sobrecargado con el algoritmo de dekker. utiliza el extend de la calse thread.
     */

    class q implements Runnable {
        public void run(){
            while (true){
                hilos4.wantq = true;
                hilos4.last = 2;
                while (hilos4.wantp && last != 1){}
                hilos4.n--;
                System.out.println(Thread.currentThread().getName());
                hilos4.wantq = false;
            }
        }
    }  
}

public class algPeterson {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        hilos4 h = new hilos4();
        executor.execute(h.new p());
        executor.execute(h.new q());

        executor.shutdown();
        while (!executor.isTerminated()){}

        System.out.println(hilos4.n);

    }
}
