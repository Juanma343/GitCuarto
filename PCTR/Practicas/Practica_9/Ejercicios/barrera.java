import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class barrera extends Thread {

    private static CyclicBarrier bar = new CyclicBarrier(3);

    public barrera(){
        System.out.println("El hilo creado se llama:" + getName());

    }

    public void run(){
        try {
            bar.await();
            System.out.println("El hilo ejecutado se llama:" + getName());
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
        
    }

    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
        for (int i = 0; i < 3; i++){
            new barrera().start();
        }
    }
}
