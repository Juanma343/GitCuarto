package PCTR.Practicas.Practica_2.Ejercicios;

public class hebra extends Thread{
    private  int  tipoHilo;
    public  static  int n=0; // variable  de  clase
    private  int  nVueltas;
    public  hebra(int  nVueltas , int  tipoHilo){
        this.nVueltas=nVueltas; this.tipoHilo=tipoHilo ;
    }
    
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
