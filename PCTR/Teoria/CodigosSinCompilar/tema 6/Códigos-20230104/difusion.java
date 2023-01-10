import mpi.*;
import java.util.*;
public class difusion {

public static void main(String args[]) throws Exception {
 MPI.Init(args);
 int rank   = MPI.COMM_WORLD.Rank();
 int size   = MPI.COMM_WORLD.Size();
 int emisor = 0;
 int tbuf   = 10; 
 int [] data = new int[tbuf];

 if(rank==emisor){
 	 Random input = new Random();
 	 System.out.println("Generando vector de enteros en proceso "+rank);
 	 for(int i=0; i<data.length; i++)
 	 	 data[i]=input.nextInt(10);
 } 
 //El metodo Bcast debe llamarse desde el emisor y desde los receptores
 MPI.COMM_WORLD.Bcast(data, 0, tbuf, MPI.INT, emisor);
 
 if(rank!=emisor){
 	 System.out.println("vector recibido por proceso "+rank);
 	 for(int i=0; i<data.length; i++)
		System.out.print(data[i]+" ");
   }

  MPI.Finalize();
 }
}