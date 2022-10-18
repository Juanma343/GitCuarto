package PCTR.Practicas.Practica_2.Ejercicios;

public class hebra_copy extends Thread{
    private  aux a;
    public  static  int n=0; // variable  de  clase
    private  int  nVueltas;
    public  hebra_copy(int  nVueltas , int  tipoHilo){
        this.nVueltas=nVueltas;
        if (tipoHilo == 0){
            this.a=new Tipo1();
        }
        else if (tipoHilo == 1){
            this.a=new Tipo2();
        }
        
    }

    interface aux{
        public void fun();
    }

    class Tipo1 implements aux{
        public void fun(){
            n++;
        }
    }

    class Tipo2 implements aux{
        public void fun(){
            n--;
        }
    }
    
    public  void  run(){
        for(int i=0; i<nVueltas; i++){
            a.fun();
        }
    }

}
