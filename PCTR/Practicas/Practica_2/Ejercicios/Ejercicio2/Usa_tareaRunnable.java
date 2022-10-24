import Dependencias.tareaRunnable;

public class Usa_tareaRunnable implements Runnable {

    /**
     * Atributo el cual es ejecutado simultaneamente
     * @see Dependencias.tareaRunnable
     */
    
    public static tareaRunnable t = new tareaRunnable();

    /**
     * atributo que indica si se ejecutara al funcion inc() o dec() de la calse tareaRunable
     * @see Dependencias.tareaRunnable
     */

    private boolean tipo;

    /**
     * Atributo que indica el numero de iteraciones del buble
     */

    private int nVuelatas;

    /**
     * Construcor parametrizado de la clase
     * @param tip inicializa el atributo tipo del objeto, es decir, si el bucle incrementara n o la decrementara
     * @param nVuel inicializa el atributo nVueltas de ojeto.
     */

    public Usa_tareaRunnable(boolean tip, int nVuel){
        this.tipo = tip;
        this.nVuelatas = nVuel;
    }

    /**
     * Metodo sobrescrito de la interfaz implement
     */

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

    /**
     * Main del la clase
     * @throws Exception
     */
    
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
