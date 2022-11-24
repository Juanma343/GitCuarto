import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Clase que guarda una posicion.
 */

class posicion{
    public double x;
    public double y;

    public posicion (double x_, double y_){
        x = x_;
        y = y_;
    }
}

/**
 * clase que calcula la integral
 */

public class integCallable implements Callable<posicion> {

    /**
     * clase que devuelve una posicion.
     */

    public posicion call(){
        return new posicion((Math.random()),(Math.random()));
    }

    public static void main(String[] args) throws Exception {
        List<Future<posicion>> puntos = Collections.synchronizedList(new ArrayList<Future<posicion>>());
        ThreadPoolExecutor exec = new ThreadPoolExecutor(2, 12, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
        int area = 0, tam = 1000000;

        for (int i = 0; i < tam; i++){
            puntos.add(exec.submit(new integCallable()));
        }

        exec.shutdown();

        for (Future<posicion> pos : puntos){
            if (pos.get().y <= Math.cos(pos.get().x)){
                area++;
            }
        }
        System.out.println((double)area/tam);

    }
    
}
