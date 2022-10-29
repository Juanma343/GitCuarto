import java.sql.Time;

//import java.lang.reflect.Constructor;

/**
 * Clase del ajercicio 1 de la practica 3 la cual crea se encarga de hacer el producto escalar de dos vectores, que en este caso son vectores de 1, por lo que el producto tendra que ser 1000000 que es el tamaño del vector
 */

public class prodEscalarParalelo extends Thread {
    public static int[] aiv1;    
    public static int[] aiv2;
    public static int[] airesult;
    public int iId;
    public int iInicio;
    public int ifin;

    /**
     * Constructor parametrizado de la clase
     * 
     * @param id indica la posicion en el vector de hilos
     * @param ini principio del fragmento del vector en el que iniciara la ejecucuion
     * @param fin final del fragmento del vector en el que terminara la ejecucuion
     */

    public prodEscalarParalelo(int id, int ini, int fin){
        this.iId = id;
        this.iInicio = ini;
        this.ifin = fin;
    }

    /**
     * Funcion que se encarga de dar tamaño a los diferentes vectores y los inicializa;
     * 
     * @param tam tamaño de los vectores a multiplicar
     * @param nHilos tamaño del vector que almacena el resultado de cada hilo
     */

    public static void inicializar(int tam, int nHilos){
        aiv1 = new int[tam];
        aiv2 = new int[tam];
        airesult = new int[nHilos];
        
        for (int i = 0; i < tam; i++){
            aiv1[i] = 1;
            aiv2[i] = 1;
        }
    }

    /**
     * Metodo sobrecargado de la clase thead, que es lo que acaba ejecutandose en un hilo
     */

    public void run(){
        for(int i = iInicio; i < ifin; i++){
            airesult[iId] += aiv1[i] * aiv2[i];
        }
    }

    public static void main(String[] args) throws Exception {
        int itam = 1000000;
        int inHilos = 2;
        int iMaxHilos = 10;
        int iresultado = 0;
        for(int j = inHilos; j <= iMaxHilos; j+=2 ){
            
            inicializar(itam, j);
            prodEscalarParalelo[] hilos = new prodEscalarParalelo[j];
            long lTiempo = System.nanoTime();
            for(int i = 0; i < j; i++){
                hilos[i] = new prodEscalarParalelo(i, i * itam / j, (i + 1) * itam / j);
                hilos[i].start();
            }

            for(int i = 0; i < j; i++){
                hilos[i].join();
            }

            for(int i = 0; i < j; i++){
                iresultado += airesult[i];
            }
            // Thread.sleep(3000);
            lTiempo = (System.nanoTime() - lTiempo) / (long)1.0e6;
            System.out.println("El tiepo para " + j + " es " + lTiempo + "ms");
        }
        

        System.out.println("El resultado es " + iresultado);

    }
}
