public class usalectorEscritor extends Thread {
    private static recurso rec = new recurso();
    private static lectorEscritor lec = new lectorEscritor();
    private static long data = 0;
    private int tipo = 0;

    /**
     * constructor de la clase usalectorescrutor
     * @param i indica si el hilo es de escritura o de lectura
     */

    public usalectorEscritor(int i){
        tipo = i;
    }

    

    public void run(){
        switch(tipo){
            case 1: 
                for(long i = 0; i < 1000000; i++){
                    lec.iniciaLectura();
                    data = rec.observar();
                    lec.acabarLectura();
                }
            break;
            case 2: 
                for(long i = 0; i < 1000000; i++){
                    lec.iniciaEscritura();
                    rec.inc();
                    lec.acabarEscritura();
                }
            break;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] vect = new Thread[4];
        for (int i = 0; i < 4; i++){
            vect[i] = new usalectorEscritor( (i % 2) + 1);
            vect[i].start();
        }
        for (int i = 0; i < 4; i++){
            vect[i].join();
        }

        System.out.println(rec.observar());
    }
    
}
