import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * clase enargada de ejecutar el codifdo de numeros perfectos de forma paralela
 */

public class numPerfectosParalelos implements Callable<Integer> {
    private int pri;
    private int fin;

    /**
     * Constructor parametrizado de la clase, se encarga de inicializar inicio y fin.
     * @param pri_ numero desde el cual buscar si es un numero perfecto
     * @param fin_ numero hasta el cual se busca un numero perfecto
     */

    numPerfectosParalelos(int pri_, int fin_){
        pri = pri_;
        fin = fin_;
    }
    /**
     * Metodo sobrescrito de la clase callable
     */
    public Integer call(){
    int aux;
        Integer num = 0;
        for(int i = pri; i < fin; i++){
            aux = 1;
            for(int j = 2; j < i; j++){
                if(i % j == 0){
                    aux += j;
                }
            }

            if (aux == i){
                num++;
            }
        }
        // System.out.println(num);
        return num;
    }

    public static void main(String[] args) throws Exception{
        int tam = Integer.parseInt(args[0]);
        List<Future<Integer>> listVec = Collections.synchronizedList(new ArrayList<Future<Integer>>());
        ThreadPoolExecutor pull = new ThreadPoolExecutor(4, 16, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
        int total = 0;

        listVec = Collections.synchronizedList(new ArrayList<Future<Integer>>());

        long iniTiempo = System.nanoTime();

        listVec.add(pull.submit(new numPerfectosParalelos(0, tam)));
        total = listVec.get(0).get();

        long tiempo = (System.nanoTime() - iniTiempo) / (long)1e6;
        System.out.println("Para 1 tarea el tiempo es " + tiempo + " ms con " + total + " numeros perfectos");

        for(int i = 2; i <= 16; i += 2){
            listVec = Collections.synchronizedList(new ArrayList<Future<Integer>>());
            total = 0;
            
            iniTiempo = System.nanoTime();

            
            for (int j = 0; j < i; j++){
                
                listVec.add(pull.submit(new numPerfectosParalelos(j * tam / i, (j + 1) * tam / i)));
                
            }

            for(Future<Integer> iter:listVec){
                total += iter.get();
            }

            tiempo = (System.nanoTime() - iniTiempo) / (long)1e6;
            System.out.println("Para " + i + " tarea el tiempo es " + tiempo + " ms con " + total + " numeros perfectos");
        }
        pull.shutdown();

    }

}
