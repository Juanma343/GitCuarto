import java.lang.Thread;
//import java.util.Scanner;

//import javax.swing.plaf.basic.BasicInternalFrameTitlePane.CloseAction;

/**
 * Clase para escalar un vector en de forma paralela, este vector es de se inicializa con su posicion en el vector y se escala por 3, ya que en ninguna parte del codigo te indica por que hay que escalar
 */

public class escalaVPar extends Thread {

    /*
     * vector que scala la clase, es estatico para que diferentes hilos modifiquen el mismo vector
     */

    private static int[] v;

    /**
     * Atributo que indica donde la hebra tiene que iniciar a escalar el vector
     */

    private int inicial;

    /**
     * Atributo que indica donde la hebra tiene que terminar de escalar el vector
     */

    private int fin;

    /**
     * Constructor de la calse parametrizado
     * @param inic inicializa el atributo inicial
     * @param fi inicializa el atributo fin
     * @param tam indica el tamaño del vector
     */

    public escalaVPar(int inic, int fi, int tam){
        this.inicial = inic;
        this.fin = fi;
        v = new int[tam];
    }

    /**
     * Inicializa el vector statico de la clase con su posicion en el vector
     * @param n indicael tamaño del vector
     */

    public static void inicializar(int n){
        v = new int[n];
        for (int i = 0; i < n; i++){
            v[i] = i;
        }
    }

    /**
     * Metodo sobrecargado de la clase runnable
     */

    public void run(){
        for (int i = inicial; i< fin; i++){
            v[i] *=(int) Math.pow((1.005), 500000000);
            // System.out.println("iteracion " + i);
        }
    }
    
    public static void main(String[] args) throws Exception {
        int n = 0;
        int num, div;
        for(int i = 1; i <= 6; i++){
            n = (int) (i * Math.pow(10, 6));;
            num = 0;
            div = 4;
            inicializar(n);
            Thread t1 = new escalaVPar(num++ * (n / div), num * n / div, n);
            Thread t2 = new escalaVPar(num++ * n / div, num * n / div, n);
            Thread t3 = new escalaVPar(num++ * n / div, num * n / div, n);
            Thread t4 = new escalaVPar(num++ * n / div, num * n / div, n);
            t1.start();
            t2.start();
            t3.start();
            t4.start();
            //t5.start();
            t1.join();
            t2.join();
            t3.join();
            t4.join();
            //t5.join();
            // System.out.println("Espera de 5 segundos");
            // sleep(5000);
        }
        System.out.println("Terminado");
    }
}
