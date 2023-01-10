import mpi.*;
import java.util.*;

public class usascatter {

public static void main(String args[]) throws Exception {
 MPI.Init(args);

 Random r        = new Random();
 int rank        = MPI.COMM_WORLD.Rank();
 int size        = MPI.COMM_WORLD.Size();
 int root        =  0;
 int recvsize    = 10;
 int [] recvbuf  = new int[recvsize];
 int sendsize    = size*recvsize;
 int [] sendbuf  = new int[sendsize];

 if(rank == root){
   for(int i=0; i<sendsize; i++)sendbuf[i]=r.nextInt(10);
   System.out.println("root va a enviar segmentando: "+Arrays.toString(sendbuf));
 }
 MPI.COMM_WORLD.Scatter(sendbuf, 0, recvsize, MPI.INT, recvbuf, 0, recvsize, MPI.INT, root);
 System.out.println("proceso "+rank+" imprime su buffer: "+Arrays.toString(recvbuf));

 MPI.Finalize();
 }
}