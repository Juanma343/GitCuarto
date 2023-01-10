//compillar : javac -cp .:$MPJ_HOME/lib/mpj.jar escalMultiple.java
//ejecutar : mpjrun.sh -np 5 escalMultiple

import mpi.MPI;

public class escalMultiple {
    public static void main(String[] args) throws Exception {
        MPI.Init(args);
        int rank = MPI.COMM_WORLD.Rank();
        int master = 0;
        int data[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int tambuf = 10;

        if (rank == master){
            System.out.print("Original: [");
            for(int i = 0; i < tambuf - 1; i++){
                System.out.print(data[i] + ", ");
            }
            System.out.println(data[tambuf-1] + "]");
        }
        MPI.COMM_WORLD.Bcast(data, 0, tambuf, MPI.INT, master);
        if (rank != master){
            for(int i = 0; i < tambuf; i++){
                data[i] *= rank;
            }
            System.out.println("Rank " + rank + ": [" + data[0] + ", " + data[1] + ", " + data[2] + ", " + data[3] + ", " + data[4] + ", " + data[5] + ", " + data[6] + ", " + data[7] + ", " + data[8] + ", " + data[9] + "]");
        }
        MPI.Finalize();
    }
    
}
