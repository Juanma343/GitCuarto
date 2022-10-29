import java.util.Random;

public class matVector {

    public static int[][] aaiMatiz;
    public static int[] aiVector;
    public static int[] aiResult;
    private static int iN;

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
        for(int i = 0; i < matVector.iN ; i++){
            System.out.print("Posicion " + i + " = " + aiResult[i]);
        }
    }

    public static void main(String[] args) {
        int tam = 10000;

        long lTiempo = System.nanoTime();
        inicializar(tam);
        for(int i = 0; i < tam; i++){
            for(int j = 0; j < tam; j++){
                aiResult[j] = aaiMatiz[i][j] * aiVector[j];
            }
        }
        lTiempo = ((System.nanoTime() - lTiempo) / (long)1.0e6);
        System.out.println("El tiepo es " + lTiempo + " ms");

        // imprimeResult();
    }
    
}
