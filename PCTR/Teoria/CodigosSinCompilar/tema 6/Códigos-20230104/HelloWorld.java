import mpi.*;
public class HelloWorld {

public static void main(String args[]) throws Exception {
 MPI.Init(args);
 int me = MPI.COMM_WORLD.Rank();
 int size = MPI.COMM_WORLD.Size();
 //cuerpo ejecutable de la tarea...
 System.out.println("Hola desde el proceso <"+me+">");
 //fin del cuerpo ejecutable...
 MPI.Finalize();
}
} 