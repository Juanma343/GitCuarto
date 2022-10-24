package Ejercicio1.Dependencias;
/**
 * Clase hebra del Ejercicio 1
 * se encarga de sumar o retas la variable n segun el parametro tipoHilo
 * con un extend Thread y sobrescirto la clase run()
 */


public class hebra extends Thread{
    private  int  tipoHilo;
    public  static  int n=0;
    private  int  nVueltas;

    /** 
     * Cosnstructor paremetrizado
     * @param numero de iteraciones del for
     * @param tipo de hebra, en esta decide si suma o resta la variable compartiad n
     */

    public  hebra(int  nVueltas , int  tipoHilo){
        this.nVueltas=nVueltas; this.tipoHilo=tipoHilo ;
    }
    
    /**
     * Funcion que realiza las funcioes del hilo, es uan funcion heredada de a clase Thread
     */

    public  void  run()
    {
        if(tipoHilo == 0){
            for(int i=0; i<nVueltas; i++){
                n++;
            }
        }
        else if (tipoHilo == 1){
            for(int i=0; i<nVueltas; i++){
                n--;
            }
        }  
    
    }

}
