/**
 * Clase encargada de realizar en serie el procuto escalar de dos vectores, siendo este codigo correspondiente al ejercicio 1 de la practoca 3
 */

public class prodEscalar {
    public static int[] v1;
    public static int[] v2;
    public static int tam = 1000000;

    /**
     * Se encarga de darle tamaño a los vectors de la clase e inicializarlos
     */

    public static void inicializar(){
        v1= new int[tam];
        v2= new int[tam];
        
        for (int i = 0; i < tam; i++){
            v1[i] = 1;
            v2[i] = 1;
        }
    }

    public static void main(String[] args) {
        inicializar();
        long lTiempo = System.nanoTime();
        int resul = 0; 
        for(int i = 0; i < tam; i++){
            resul += v1[i] * v2[i];
        }
        lTiempo = (System.nanoTime() - lTiempo) / (long)1.0e6;
            System.out.println("El tiepo es " + lTiempo + "ms");
        // System.out.println("El resultado es " + resul);
    }
}
