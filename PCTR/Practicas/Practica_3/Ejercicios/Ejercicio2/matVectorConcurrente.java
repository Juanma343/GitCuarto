import java.util.Random;

public class matVectorConcurrente implements Runnable {
    public static int[][] aaiMatiz;
    public static int[] aiVector;
    public static int[] aiResult;
    private static int iN;
    private int iInicio;
    private int iFin;

    public matVectorConcurrente(int ini, int fin){
        this.iInicio = ini;
        this.iFin = fin;
    }

    public static void inicializar(int n){
        iN = n;
        aaiMatiz = new int[n][n];
        aiVector = new int[n];
        aiResult = new int[n];
        Random r = new Random(1123581321);
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                aaiMatiz[i][j] = r.nextInt(n);
            }
            aiVector[i] = r.nextInt(n);
        }
    }

    public static void imprimeResult(){
        for(int i = 0; i < matVectorConcurrente.iN ; i += 50){
            System.out.print("Posicion " + i + " = " + aiResult[i]);
        }
        
    }

    public void run(){
        for(int i = 0; i < matVectorConcurrente.iN; i++){
            for(int j = 0; j < matVectorConcurrente.iN; j++){
                aiResult[j] = aaiMatiz[i][j] * aiVector[j];
            }
        }
    }

    public static void main(String[] args) throws Exception {
        int itam = 10000;
        int inHilos = 2;
        int imaxHilos = 16;
        Thread[] ahilos;
        
        inicializar(itam);
        int iaux;
        for(int i = inHilos; i <= imaxHilos; i += 2){
            ahilos = new Thread[i];
            long lTiempo = System.nanoTime();
            iaux = 0;
            for(int j = 0; j < i; j++){
                ahilos[j] = new Thread(new matVectorConcurrente(iaux++ * itam / i, iaux * itam / i));
                ahilos[j].start();
            }
            for(int j = 0; j < i; j++){
                ahilos[j].join();
            }
            double dTiempo = ((System.nanoTime() - lTiempo) / (long)1.0e3) / 1000.0;
            System.out.println("El tiepo para " + i + " es " + dTiempo + " ms");
            Thread.sleep(5000);
        }

        // imprimeResult();
    }

}
