import java.util.function.Function;

/**
 * Clase del ejercicio 5 donde se hara la condicion de concurso sobre una variable que escriben en ella
 * 
 * @see java.lang.Thread
 */

public class concursoLambda {

    /**
     * Atributo a modificar
     */
    
    public static int n;

    public static void main(String[] args) throws Exception {
        Runnable suma = () -> {
            for (int i = 0; i<= 1000000; i++) concursoLambda.n++;
        };
        Runnable rest = () -> {
            for (int i = 0; i<= 1000000; i++) concursoLambda.n--;
        };

        Thread t1 = new Thread(suma);
        Thread t2 = new Thread(rest);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("Terminado\n n = " + concursoLambda.n);
    }

}