//nombre: Juan Manuel Grondona Nuño
//DNI: 49193526E
public class usaMyBarrier extends Thread {
    static myBarrier barrera = new myBarrier(3);

    public void run() {
        System.out.println(this.getName()+" llegando a barrera...");
        barrera.toWaitOnBarrier();
        System.out.println(this.getName()+" saliendo de barrera...");

    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] hilos = new Thread[6];
        System.out.println("main creando barrera para tres hebras...");
        for (int i = 0; i < 3; i++) {
            hilos[i] = new usaMyBarrier();
            hilos[i].start();
        }
        for(int i = 0; i < 3; i++){
            hilos[i].join();
        }
        barrera.resetBarrier();
        System.out.println("main creando barrera para tres nuevas hebras...");
        for (int i = 0; i < 3; i++) {
            hilos[i] = new usaMyBarrier();
            hilos[i].start();
        }
        for(int i = 0; i < 3; i++){
            hilos[i].join();
        }
        System.out.println("main terminando...");
    }
}
