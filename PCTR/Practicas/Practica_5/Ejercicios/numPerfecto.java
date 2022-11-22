
/**
 * clase donde se ejecuta en main, esta se encarga de encontrar los numero perfectos entre 0 y el ragumento pasado como parametro.
 */

public class numPerfecto {
    public static void main(String[] args) {
        int tam = Integer.parseInt(args[0]);
        int num = 0;

        long inicTiempo = System.nanoTime();

        int aux;
        for(int i = 2; i < tam; i++){
            
            aux = 1;
            for(int j = 2; j < i; j++){
                if (i % j == 0){
                    aux += j;
                }
            }

            if(aux == i){
                num++;
            }

        }

        long timepo = (System.nanoTime() - inicTiempo) / (long)1e6;
        System.out.println("El tiemp ha sido " + timepo + "ms");

        System.out.println(num);
    }
}
