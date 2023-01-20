//nombre: Juan Manuel Grondona Nuño
//DNI: 49193526E
//compillar : javac -cp .:$MPJ_HOME/lib/mpj.jar ProdVecMpj.java 
//ejecutar : mpjrun.sh -np 3 ProdVecMpj

import mpi.*;

public class ProdVecMpj {
    public static void main(String[] args) throws Exception {
        MPI.Init(args);
        int rank = MPI.COMM_WORLD.Rank();
        int master = 0;
        int tamvec = 3;
        int vec[] = new int[tamvec];
        int tammat = 9;
        int mat[] = new int[tammat];
        int recvbuf[] = new int[tamvec];
        int result[] = new int[tamvec];

        if(rank == master){
            for (int i = 0; i < tamvec; i++){
                vec[i] = i;
            }
            for (int i = 0; i < tammat; i++){
                mat[i] = i*2;
            }
        }
        MPI.COMM_WORLD.Scatter(mat, 0, tammat, MPI.INT, recvbuf, 0, tamvec, MPI.INT, 0);
        if(rank == master){
            for(int i = 0; i < tamvec; i++){
                result[rank] += vec[i] * recvbuf[rank + i];
            }
            for(int i = 1; i < tamvec; i++){
                MPI.COMM_WORLD.Recv(result[i], 0, 1, MPI.INT, i, 0);
            }
            System.out.print("Vector producto resultante: [");
            for(int i = 0; i < tamvec; i++){
                if(i == tamvec - 1){
                    System.out.print(result[i] + "]");
                    break;
                }
                else{
                    System.out.print(result[i] + ", ");
                }
            }
        }
        MPI.COMM_WORLD.Bcast(vec, 0, tamvec, MPI.INT, 0);
        if(rank != master){
            MPI.COMM_WORLD.Gather(recvbuf, 0, tamvec, MPI.INT, mat, 0, tammat, MPI.INT, 0);
            int res = 0;
            for(int i = 0; i < tamvec; i++){
                res += vec[i] * recvbuf[rank + i];
            }
            MPI.COMM_WORLD.Send(res, 0, 1, MPI.INT, master, 0);  
        }

        MPI.Finalize();
    }
}
