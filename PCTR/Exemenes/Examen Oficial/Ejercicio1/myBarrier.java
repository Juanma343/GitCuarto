//nombre: Juan Manuel Grondona Nuño
//DNI: 49193526E
import java.util.concurrent.Semaphore;

public class myBarrier {
    int n;
    Semaphore sem;
    
    public myBarrier(int n){
        this.n = n;
        this.sem = new Semaphore(n);
    }

    public synchronized void toWaitOnBarrier(){
        
        if(sem.getQueueLength() == n-1){
            sem.release(n - 1);
        }
        else{
            try {
                sem.acquire();
            } catch (InterruptedException e) {
                System.out.println("Error en toWaitOnBarrier");
            }
        }
    }

    public void resetBarrier(){
        sem = new Semaphore(n);
    }
}
