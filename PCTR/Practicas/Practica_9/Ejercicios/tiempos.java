import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

public class tiempos implements Callable<Double> {
    private static AtomicLong an = new AtomicLong(0);
    private static int n = 0;
    private static Object cerr = new Object();
    private static Semaphore sem = new Semaphore(1);
    private static ReentrantLock cerroj = new ReentrantLock();
    private int tipo;
    public static int tamMax;

    public tiempos(int i){
        tipo = i;
    }

    public static void fun1(){
        for(int i = 0; i < tamMax; i++){
            synchronized(cerr){
                n++;
            }
        }
    }

    public static void fun2() throws InterruptedException{
        for(int i = 0; i < tamMax; i++){
            sem.acquire();
            n++;
            sem.release();
        }
    }

    public static void fun3(){
        for(int i = 0; i < tamMax; i++){
            cerroj.lock();
            n++;
            cerroj.unlock();
        }
    }

    public static void fun4(){
        for(int i = 0; i < tamMax; i++){
            an.incrementAndGet();
        }
    }

    @Override
    public Double call() throws Exception {
        long inicio = System.nanoTime();
        switch(tipo){
            case 0: fun1(); break;
            case 1: fun2(); break;
            case 2: fun3(); break;
            case 3: fun4(); break;
        }
        return (double)((System.nanoTime() - inicio) / (long)1.0e6);
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int nMax = 1000000;
        List<Future<Double>> tiempos;
        ThreadPoolExecutor pool;

        for (int i = 10000; i <= nMax; i+=10000){
            tamMax = i;
            
            tiempos = Collections.synchronizedList(new ArrayList<Future<Double>>());
            pool = new ThreadPoolExecutor( 4, 4, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
            // System.out.print(i + "\t");
            for(int j = 0; j < 4; j++){
                tiempos.add(pool.submit(new tiempos(j%4)));
            }

            System.out.print(i + "\t");

            for(Future<Double> iterador:tiempos){
                System.out.print(iterador.get() + "\t");
            }

            System.out.print("\n");
        pool.shutdown();
        }
        
    }

}
