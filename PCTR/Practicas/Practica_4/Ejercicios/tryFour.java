
/**
 * clase encargadas de todo el funcionmniento de los hilos
 */

class hilos2{

    /**
     * variables encargadas de los turnos
     */

    public static volatile boolean wantq = false;
    public static volatile boolean wantp = false;

    /**
     * variable que se modifica en la seccion critca
     */

    public static volatile int n = 0;
    public static int nVueltas = 100000;

    class p extends Thread{
        public void run(){
            for (int i = 0; i < hilos2.nVueltas; i++){
                hilos2.wantp = true;
                while (hilos2.wantq){
                    hilos2.wantp = true;
                    hilos2.wantp = false;
                }
                hilos2.n++;
                System.out.println(getName());
                hilos2.wantp = false;
            }
        }
    }

    class q extends Thread{
        public void run(){
            for (int i = 0; i < hilos2.nVueltas; i++){
                hilos2.wantq = true;
                while (hilos2.wantp){
                    hilos2.wantq = true;
                    hilos2.wantq = false;
                }
                hilos2.n--;
                System.out.println(getName());
                hilos2.wantq = false;
            }
        }
    }  
}

class tryFour{

    public static void main(String[] args) throws Exception{
        hilos2 h = new hilos2();
        Thread h1 = h.new p();
        Thread h2 = h.new q();

        h1.start();
        h2.start();
        h1.join();
        h2.join();

        System.out.println(hilos2.n);

    }
}