
/**
 * Clase Encargada de de aumentar n y m stanto sincronizada comono sincronizada
 */

public class heterogenea {
    public int n = 0;    // variable protejida
    public int m = 0;    // variable sin protejer

    /**
     * incrementa n de forma sinconizada
     */

    public synchronized void incN(){n++;}

    /**
     * simplemente incrementa m
     */

    public void incM(){m++;}
}
