import java.lang.Thread;
//import java.util.Scanner;

/**
 * Clase escalaVaector que unicamente tiene un main, y lo que hace es escalar un vector en secuencial
 */

public class escalaVector  {
    
    public static void main(String[] args) throws Exception{
        // Scanner a = new Scanner(System.in);
        int a =(int) Math.pow((1.005), 500000000);
        for (int i = 1; i <= 7; i++){
            int tVec =(int) (i * Math.pow(10, 6));
            int[] v = new int [tVec];
            for(int j = 0; j < tVec; j++){
                v[j] = j * a;
                // System.out.println("iteracion " + j);
            }
            // System.out.println("Espera de 5 segundos");
            // Thread.sleep(5000);
        }
        System.out.println("Terminado");
    }
}
