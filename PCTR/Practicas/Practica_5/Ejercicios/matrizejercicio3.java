import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class matrizejercicio3 implements Runnable {
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

    matrizejercicio3(int ini_, int fin_){
        ini = ini_;
        fin = fin_;
    }

    // Se necesita pasar como argumento el tamaño de la matriz
    public static void main(String[] args) throws Exception {
        inicializar(Integer.parseInt(args[0]));
        ExecutorService exec;

        long tiemInic = System.nanoTime();
        Thread hilo = new Thread(new matrizejercicio3(0, tam));
        hilo.start();
        hilo.join();
        long tiempo = (System.nanoTime() - tiemInic) / (long)1.0e6;
        System.out.println("Para 1 tarea el tiempo es "+tiempo+" ms");

        for (int i = 2; i <= 16; i+=2){
            tiemInic = System.nanoTime();
            exec = Executors.newFixedThreadPool(i);

            for (int j = 0; j < i; j++){
                exec.execute(new matrizejercicio3(j * tam / i, (j+1) * tam / i));
            }
    
            exec.shutdown();
            while(!exec.isTerminated()){}
            tiempo = (System.nanoTime() - tiemInic) / (long)1.0e6;
            System.out.println("Para "+i+" tarea el tiempo es "+tiempo+" ms");
        }
        
    }
}
