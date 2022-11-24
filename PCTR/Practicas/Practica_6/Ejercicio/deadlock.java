import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Clase por la cunal, pese este sincronizada, se prooducen interbloqueo entre enllas.
 */

public class deadlock implements Runnable{

    public static Object lock1 = new Object();
    public static Object lock2 = new Object();
    public static Object lock3 = new Object();
    private Object fir;
    private Object sec;

    /**
     * Constructor de la clase parametrizado, al que se le pasan dos cerrojos.
     * @param a
     * @param b
     */

    public deadlock(Object a, Object b){
        fir = a;
        sec = b;
    }

    /**
     * mMetodo sobrecargado de la calse runnable.
     */

    public void run(){
        synchronized(fir){
            for(int i = 0; i < 1000000; i++){
                synchronized(sec){
                    System.out.println(i);
                }
            }
        }
    }

    public static void main(String[] args) {
        ThreadPoolExecutor exec = new ThreadPoolExecutor(3, 3, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());

        exec.execute(new deadlock(lock1, lock2));
        exec.execute(new deadlock(lock2, lock3));
        exec.execute(new deadlock(lock3, lock1));
        

        exec.shutdown();
        while(!exec.isShutdown()){}
    }
    
}
