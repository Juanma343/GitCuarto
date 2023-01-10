import mpi.*;
public class reduccion {
  public static void main(String args[]){
    MPI.Init(args);
    int rank        = MPI.COMM_WORLD.Rank();
    int size        = MPI.COMM_WORLD.Size();
    int root        = 0;
    int datos[]     = new int[1];
    int mis_datos[] = new int[1];
     
    datos[0] = 10;  
    MPI.COMM_WORLD.Reduce(datos, 0, mis_datos, 0 , 1, MPI.INT, MPI.SUM, 0);
    if(rank==0){System.out.println("reduciendo suma: "+mis_datos[0]);}
    datos[0] = 2;  
    MPI.COMM_WORLD.Reduce(datos, 0, mis_datos, 0 , 1, MPI.INT, MPI.PROD, 0);
    if(rank==0){System.out.println("reduciendo producto: "+mis_datos[0]);}
    datos[0] = rank;  
    MPI.COMM_WORLD.Reduce(datos, 0, mis_datos, 0 , 1, MPI.INT, MPI.MAX, 0);
    if(rank==0){System.out.println("reduciendo maximo: "+mis_datos[0]);}
    MPI.COMM_WORLD.Reduce(datos, 0, mis_datos, 0 , 1, MPI.INT, MPI.MIN, 0);
    if(rank==0){System.out.println("reduciendo minimo: "+mis_datos[0]);}
    MPI.Finalize();
  }
}