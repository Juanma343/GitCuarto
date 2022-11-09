
/**
 * clase encargadas de todo el funcionmniento de los hilos
 */

class hilos{

    /**
     * variables encargadas de los turnos
     */
    public static volatile boolean wantq = false;
    public static volatile boolean wantp = false;

    /**
     * variable que se modifica en la seccion critca
     */

    public static volatile int n = 0;
    public static int nVueltas = 10000;

    class p extends Thread{
        public void run(){
            for (int i = 0; i < hilos.nVueltas; i++){
                hilos.wantp = true;
                while (hilos.wantq){}
                hilos.n++;
                System.out.println(getName());
                hilos.wantp = false;
            }
        }
    }

    class q extends Thread{
        public void run(){
            for (int i = 0; i < hilos.nVueltas; i++){
                hilos.wantq = true;
                while (hilos.wantp){}
                hilos.n--;
                System.out.println(getName());
                hilos.wantq = false;
            }
        }
    }
    
}

class tryThree{

    public static void main(String[] args) throws Exception{
        hilos h = new hilos();
        Thread h1 = h.new p();
        Thread h2 = h.new q();

        h1.start();
        h2.start();
        h1.join();
        h2.join();

        System.out.println(hilos.n);

    }
}