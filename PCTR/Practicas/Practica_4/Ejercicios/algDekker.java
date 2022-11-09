/**
 * clase que se ecarga de las hebras del programa
 */

class hilos3{

    /**
     * variables encargadas de los turnos
     */

    public static volatile boolean wantq = false;
    public static volatile boolean wantp = false;
    public static volatile int turn = 1;

    /**
     * variable que se modifica en la seccion critca
     */

    public static volatile int n = 0;

    /**
     * clase del tipo de hebra p, la cual tiene el metodo run sobrecargado con el algoritmo de dekker. utiliza el extend de la calse thread.
     */

    class p extends Thread{
        public void run(){
            while (true){
                hilos3.wantp = true;
                while (hilos3.wantq){
                    if (hilos3.turn == 2){
                        hilos3.wantp = false;
                        while (turn == 2){}
                        hilos3.wantp = true;
                    }
                }
                hilos3.n++;
                System.out.println(getName());
                hilos3.turn = 2;
                hilos3.wantp = false;
            }
        }
    }
    
    /**
     * clase del tipo de hebra q, la cual tiene el metodo run sobrecargado con el algoritmo de dekker. utiliza el extend de la calse thread.
     */

    class q extends Thread{
        public void run(){
            while (true){
                hilos3.wantq = true;
                while (hilos3.wantp){
                    if (hilos3.turn == 1){
                        hilos3.wantq = false;
                        while (turn == 1){}
                        hilos3.wantq = true;
                    }
                }
                hilos3.n--;
                System.out.println(getName());
                hilos3.turn = 1;
                hilos3.wantq = false;
            }
        }
    }  
}

public class algDekker {
    public static void main(String[] args) throws Exception{
        hilos3 h = new hilos3();
        Thread h1 = h.new p();
        Thread h2 = h.new q();

        h1.start();
        h2.start();
        h1.join();
        h2.join();

        System.out.println(hilos3.n);

    }
}
