import java.util.concurrent.Semaphore;

public class MonitorImpresoras {
    int k;
    Semaphore[] impresoras;
    boolean[] ocupadas;

    MonitorImpresoras(int k) {
        this.k = k;
        this.impresoras = new Semaphore[k];
        for (int i = 0; i < k; i++) {
            this.impresoras[i] = new Semaphore(1);
            this.ocupadas[i] = false;
        }
    }

    public void imprime(){
        boolean libre = true;
        for(int i = 0; i < k && libre; i++){
            if(!ocupadas[i]){
                ocupadas[i] = true;
                impresoras[i].acquire();
                System.out.println("Impresora " + i + " esta imprimiendo");
                impresoras[i].release();
                libre = false;
            }
        }
        if(libre){
            int impresora = (int)Math.random() * (k-1);
            impresoras[impresora].acquire();
            System.out.println("Impresora " + impresora + " esta imprimiendo");
            impresoras[impresora].release();
        }
    }


}
