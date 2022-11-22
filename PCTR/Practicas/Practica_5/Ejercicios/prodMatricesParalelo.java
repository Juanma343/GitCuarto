import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Clase encargada de multiplcar matrices en paralelo
 */

public class prodMatricesParalelo implements Runnable {
    
    /**
     * matrices a multiplicar y donde se guarda la solucion
     */

    public static int[][] m1;
    public static int[][] m2;
    public static int[][] sol;

    /**
     * tamaño de las matrices y semilla de los numeros aleatorios
     */

    public static int tam;
    public static Random rm = new Random(123456789);

    /**
     * posiciones de inicio y fin para calcular de cada hilo
     */

    public int ini;
    public int fin;

    /**
     * Se encarga del darle el tamaño a las matrices e inizializar los vectores
     * @param tam_
     */

    public static void inicializar(int tam_){
        tam = tam_;
        m1  = new int[tam][tam];
        m2  = new int[tam][tam];
        sol = new int[tam][tam];

        for (int i = 0; i < tam; i++){
            for (int j = 0; j < tam; j++){
                m1[i][j] = (int)(rm.nextFloat() * 100);
                m2[i][j] = (int)(rm.nextFloat() * 100);
                sol[i][j] = 0;
            }
        }
    }

    /**
     * metodo sobrecargado de la clase runnable
     */

    public void run(){
        for (int i = ini; i < fin; i++){
            for (int j = 0; j < tam; j++){
                for (int k = 0; k < tam; k++){
                    sol[i][j] += m1[i][k] + m2[k][j];
                }
            }
        }
    }

    /**
     * metodo encargado de mostrar la matriz
     */

    public static void mostrar(){
        for (int i = 0; i < tam; i++){
            for (int j = 0; j < tam; j++){
                System.out.print("- "+sol[i][j]+" -");
            }
            System.out.print("\n");
        }
    }

    /**
     * constructor parametrizado de la clase paralelo
     * @param ini_
     * @param fin_
     */

    prodMatricesParalelo(int ini_, int fin_){
        ini = ini_;
        fin = fin_;
    }

    // Se necesita pasar como argumento el tamaño de la matriz
    public static void main(String[] args) {
        inicializar(Integer.parseInt(args[0]));
        int nTareas = Runtime.getRuntime().availableProcessors();
        ExecutorService exec = Executors.newFixedThreadPool(nTareas);

        for (int i = 0; i < nTareas; i++){
            exec.execute(new prodMatricesParalelo(i * tam / nTareas, (i+1) * tam / nTareas));
        }

        exec.shutdown();
        while(!exec.isTerminated()){}

        mostrar();
    }

}
