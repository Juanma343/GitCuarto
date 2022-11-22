import java.util.Random;

/**
 * Clase que se encarga de multiplicar dos matrices. Al llamar al main, se le para por parámetro el tamaño que se desea para las matrices, esta primeramente se inicializan con números aleatorios y posteriormente se realiza la operación
 */

public class prodMatricesSecuencial {
    
    public static void main(String[] args) {
        int tam = Integer.parseInt(args[0]);
        int[][] m1  = new int[tam][tam];
        int[][] m2  = new int[tam][tam];
        int[][] sol = new int[tam][tam];
        Random rm = new Random(123456789);

        for (int i = 0; i < tam; i++){
            for (int j = 0; j < tam; j++){
                m1[i][j] = (int)(rm.nextFloat() * 100);
                m2[i][j] = (int)(rm.nextFloat() * 100);
                sol[i][j] = 0;
            }
        }
        
        for (int i = 0; i < tam; i++){
            for (int j = 0; j < tam; j++){
                for (int k = 0; k < tam; k++){
                    sol[i][j] += m1[i][k] + m2[k][j];
                }
            }
        }

        for (int i = 0; i < tam; i++){
            System.out.print("\n");
            for (int j = 0; j < tam; j++){
                System.out.print("- "+sol[i][j]+" -");
            }
        }
    }
}
