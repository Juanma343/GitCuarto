//compillar : javac -cp .:$MPJ_HOME/lib/mpj.jar prodInterno.java
//ejecutar : mpjrun.sh -np 2 prodInterno

import mpi.*;

/**
 * Clase que hace el producto escalar de dos vectores.
 */

public class prodInterno {
    
    public static void main(String[] args) throws Exception {
        MPI.Init(args);
        int rack = MPI.COMM_WORLD.Rank();
        int master = 0, slave = 1, tag1 = 1, tag2 = 2, tag3 = 3, unitvec = 4, unitres = 1;

        if (rack == master){
            int vec1[] = new int[4];
            int vec2[] = new int[4];
            int result[] = {0};

            for (int i = 0; i < 4; i++){
                vec1[i] = i + 1;
                vec2[i] = i + 1;
            }
            MPI.COMM_WORLD.Send(vec1, 0, unitvec, MPI.INT, slave, tag1);
            MPI.COMM_WORLD.Send(vec2, 0, unitvec, MPI.INT, slave, tag2);
            MPI.COMM_WORLD.Recv(result, 0, unitres, MPI.INT, slave, tag3);
            System.out.println("El resultado es " + result[0]);
        }
        else{
            int vec1[] = new int[4];
            int vec2[] = new int[4];
            int result[] = {0};
            
            MPI.COMM_WORLD.Recv(vec1, 0, unitvec, MPI.INT, master, tag1);
            MPI.COMM_WORLD.Recv(vec2, 0, unitvec, MPI.INT, master, tag2);
            for (int i = 0; i < 4; i++){
                result[0] += vec1[i] * vec2[i];
            }
            MPI.COMM_WORLD.Send(result, 0, unitres, MPI.INT, master, tag3);
            
        }
        MPI.Finalize();
    }
}
