package Dependencias;

public class tareaRunnable{
    
    private int n = 0;

    public tareaRunnable (){}
    public void inc(){this.n++;}
    public void dec(){this.n--;}
    public int vDato(){return n;}

}

/* public interface tareaRunnable extends Runnable{
    
    //private int n = 0;
    //private int tipo;

    //public tareaRunnable (int tip){this.tipo = tip;}
    // public void inc(){this.n++;}
    // public void dec(){this.n--;}
    // public int vDato(){return n;}
    public void run(){}
} */