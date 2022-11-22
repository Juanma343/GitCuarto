import java.util.Random;

/**
 * calse encargada de modificar la imagen de la iamgen como
 */

public class resImagen {

    public static void main(String[] args) {

        /**
         * clase en la que se encaran los pixeles, estos unca superaran el maximo permitido y permitira mostrarlos de forma mas vidual.
         */
        class Pixel{
            private int nivel;
            /**
             * constructor de la clase
             * @param r
             */
            public Pixel(Random r){
                nivel = Math.abs(r.nextInt()) % 256;
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

        int tam = Integer.parseInt(args[0]);
        Pixel[][] picEnt = new Pixel[tam][tam];
        Random r = new Random(123456789);
        for(int i = 0; i < tam; i++){
            for(int j = 0; j < tam; j++){
                picEnt[i][j] = new Pixel(r);
            }
        }
        
        int valor;
        for(int i = 0; i < tam; i++){
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
                picEnt[i][j].asignar(valor/8);
            }
        }

        for(int i = 0; i < tam; i++){
            System.out.print("-");
            for(int j = 0; j < tam; j++){
                System.out.print(picEnt[i][j].mostrar() + "-");
            }
            System.out.print("\n");
        }
    }
}
