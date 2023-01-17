//compillar : javac -cp .:$MPJ_HOME/lib/mpj.jar distributedIntegers.java
//ejecutar : mpjrun.sh -np 65 distributedIntegers
// puede usar el numero de procesos que  necesite entre 2 y la 10^7/2

import mpi.*;

//Nos dan el numero de primos entre 1 y 10000000

public class distributedIntegers{

    /**
     * Esta fundiion deice si un numero en concreto es primo o no.
     * 
     * @param num
     * @return boolean
     */

    public static boolean esPrimo(int num) {
        if (num < 2) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        MPI.Init(args);
        int total   = 10000000;
        int rank    = MPI.COMM_WORLD.Rank();
        int size    = MPI.COMM_WORLD.Size();
        int rango[] = new int[1];
        // boolean todos[]  = {0};
        int master  = 0;
        int Sol[]   = new int[size];
        int Solsla[]= new int[1];

        if (rank == master){
            rango[0] = total / (size - 1);
            // System.out.println(rango[0]);
            System.out.println(rango[0]);

        }

        MPI.COMM_WORLD.Bcast(rango, 0, 1, MPI.INT, master);

        if (rank != master){
            int fin;
            if (rank == size-1){
                fin = total + 1;
            }
            else {
                fin = rango[0] * rank;
            }
            for (int i = rango[0] * (rank-1); i < fin; i++){
                if (esPrimo(i)){
                    Solsla[0]++;
                }
                // System.out.println(i);
            }
            // System.out.println(rank + "_" + Solsla[0]);
        }
        
        MPI.COMM_WORLD.Reduce(Solsla, 0, Sol, 0, 1, MPI.INT, MPI.SUM, master);

        if (rank == master){
            System.out.println("El numero de primos es: " + Sol[0]);
        }
        MPI.Finalize();
    }   
}
