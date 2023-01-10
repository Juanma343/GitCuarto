import mpi.*;

class helloWorld2 {
	static public void main(String[] args) throws MPIException {
		MPI.Init(args);
		int myrank = MPI.COMM_WORLD.Rank();
		int size   = MPI.COMM_WORLD.Size();
		if(myrank == 0) {
		char [] message = "Hello, there".toCharArray();
		for(int proceso=1; proceso<size; proceso++)
			MPI.COMM_WORLD.Send(message, 0, message.length, MPI.CHAR, proceso, 99);
		}
		else {
			char [] message = new char [20];
			MPI.COMM_WORLD.Recv(message, 0, 20, MPI.CHAR, 0, 99);
			System.out.println("received:" + new String(message) + ":");
		}
		MPI.Finalize();
	}
}