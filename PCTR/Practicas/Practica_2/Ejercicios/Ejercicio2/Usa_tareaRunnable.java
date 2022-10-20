import Dependencias.tareaRunnable;

public class Usa_tareaRunnable implements Runnable {

    public static tareaRunnable t = new tareaRunnable();
    private boolean tipo;
    private int nVuelatas;

    public Usa_tareaRunnable(boolean tip, int nVuel){
        this.tipo = tip;
        this.nVuelatas = nVuel;
    }

    public void run(){
        if(this.tipo){
            for(int i = 0; i <= nVuelatas; i++){
                t.inc();
            }
        }
        else{
            for(int i = 0; i <= nVuelatas; i++){
                t.dec();
            }
        }
    }
    
    public static void main(String[] args) throws Exception {
        
        for (int i = 100; i <= 1000000; i *= 10){
            Runnable r1 = new Usa_tareaRunnable(true, i);
            Runnable r2 = new Usa_tareaRunnable(false, i);
            Thread t1 = new Thread(r1);
            Thread t2 = new Thread(r2);
            t1.start();
            t2.start();
            t1.join();
            t2.join();
            System.out.println("La variable estatica n = " + Usa_tareaRunnable.t.vDato() + " Para un valor de nVuelatas de " + i);
            Usa_tareaRunnable.t = new tareaRunnable();
        }
        
    }
}
