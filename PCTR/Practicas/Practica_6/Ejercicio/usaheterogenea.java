import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class usaheterogenea implements Runnable {
    
    public static heterogenea obj = new heterogenea();

    public void run(){
        obj.incM();
        obj.incN();
    }

    public static void imprimir(){
        System.out.println("n = " + obj.n + "\nm = " + obj.m);
    }

    public static void main(String[] args) {

        ThreadPoolExecutor exec = new ThreadPoolExecutor(2, 12, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());

        for (int i = 0; i < 1000000; i++){
            exec.execute(new usaheterogenea());
        }
        exec.shutdown();
        while(!exec.isTerminated()){}

        imprimir();

    }

}
