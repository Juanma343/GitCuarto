import mpi.*;
import java.util.*;

public class usagather {

public static void main(String args[]) throws Exception {
 MPI.Init(args);

 Random r        = new Random();
 int rank        = MPI.COMM_WORLD.Rank();
 int size        = MPI.COMM_WORLD.Size();
 int root        =  0;
 int sendsize    = 10;
 int recvsize    = size*sendsize;
 int [] sendbuf  = new int[sendsize];
 int [] recvbuf  = new int[recvsize];

 for(int i=0; i<sendsize; i++)sendbuf[i]=r.nextInt(10);
 System.out.println("proceso "+rank+" va a enviar: "+Arrays.toString(sendbuf));
 MPI.COMM_WORLD.Gather(sendbuf, 0, sendsize, MPI.INT, recvbuf, 0, sendsize, MPI.INT, root);
 if(rank==root)System.out.println("proceso "+rank+" imprime su buffer: "+Arrays.toString(recvbuf));

 MPI.Finalize();
 }
}