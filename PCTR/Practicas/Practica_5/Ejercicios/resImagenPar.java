import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class resImagenPar implements Runnable {
    
    public static int tam;
    public static Pixel[][] picEnt;
    public static Pixel[][] picSal;
    public static Random r;
    public int ini;
    public int fin;

    /**
     * clase en la que se encaran los pixeles, estos unca superaran el maximo permitido y permitira mostrarlos de forma mas vidual.
     */

    static class Pixel{
        private int nivel;
        /**
         * constructor de la clase incializando el elemento de manera aleatoria
         * @param r
         */
        public Pixel(Random r){
            nivel = Math.abs(r.nextInt()) % 256;
        }
        /**
         * constructor de la calse con un valor en concreto, si es mayor de 255 le hace el modulo.
         * @param v
         */
        public Pixel(int v){
            nivel = Math.abs(v) % 256;
        }
        /**
         * devuelve el valor del pixel
         * @return nivel
         */
        public int nivel(){return nivel;}
        /**
         * Da valor a nivel y s supera le hace el modulo
         * 
         * @param valor
         */
        public void asignar(int valor){nivel = Math.abs(valor) % 256;}
        /**
         * vevuelve el valor centrado en un strng
         * @return string
         */ 
        public String mostrar(){
            if (nivel < 10){
                return " " + nivel + " ";
            }
            else if(nivel < 100){
                return " " + nivel;
            }
            else{
                return String.valueOf(nivel);
            }
        }
    }
    /**
     * Constructor de la calse parametrizada
     * @param ini_ valor de ini
     * @param fin_ valor de fin
     */
    resImagenPar(int ini_, int fin_){
        ini = ini_;
        fin = fin_;
    }
    /**
     * crea los miembros estaticos
     * @param tam_ el primer parametro del maim
     */
    public static void inicializa(int tam_){
        tam = tam_;
        r = new Random(123456789); 
        picEnt = new Pixel[tam][tam];
        picSal = new Pixel[tam][tam];
        for(int i = 0; i < tam; i++){
            for(int j = 0; j < tam; j++){
                picEnt[i][j] = new Pixel(r);
                picSal[i][j] = new Pixel(0);
            }
        }
    }
    /**
     * metodo dobrescrito de la calse run
     */
    public void run(){
        int valor;
        for(int i = ini; i < fin; i++){
            for(int j = 0; j < tam; j++){
                valor = 4 * picEnt[i][j].nivel();
                if(i > 0){
                    valor -= picEnt[i-1][j].nivel();
                }else{valor -= picEnt[i][j].nivel();}
                if(j > 0){
                    valor -= picEnt[i][j-1].nivel();
                }else{valor -= picEnt[i][j].nivel();}
                if(i < tam -1){
                    valor -= picEnt[i+1][j].nivel();
                }else{valor -= picEnt[i][j].nivel();}
                if(j < tam -1){
                    valor -= picEnt[i][j+1].nivel();
                }else{valor -= picEnt[i][j].nivel();}
                picSal[i][j].asignar(valor/8);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        inicializa(Integer.parseInt(args[0]));
        ExecutorService pull;

        long tiemIn = System.nanoTime();

        Thread hilo = new Thread(new resImagenPar(0, tam));
        hilo.start();
        hilo.join();

        long tiempo = (System.nanoTime() - tiemIn) / (long)1.0e6;
        System.out.println("Para 1 tarea el tiempo es " + tiempo + " ms");

        for(int i = 2; i <= 16; i+=2){
            tiemIn = System.nanoTime();
            pull = Executors.newFixedThreadPool(i);

            for(int j = 0; j < i; j++){
                pull.execute(new resImagenPar(j * tam / i, (j + 1) * tam / i));
            }
            pull.shutdown();
            while(!pull.isTerminated()){}

            tiempo = (System.nanoTime() - tiemIn) / (long)1.0e6;
            System.out.println("Para " + i + " tarea el tiempo es " + tiempo + " ms");

        }
    }
}
