import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Clase monitor de lectura escritura
 */

public class lectorEscritor {
    private boolean escribiendo;
    private int lectores;
    private ReentrantLock l;
    private Condition escri;
    private Condition lecto;


    /**
     * Constructor de la clase lectorEscritor
     */

    public lectorEscritor(){
        l = new ReentrantLock();
        escri = l.newCondition();
        lecto = l.newCondition();
        escribiendo = false;
        lectores = 0;
    }
    /**
     * metodo para iniciar la lectura
     */

    public void iniciaLectura(){        
		l.lock();
        
		try{
			while (escribiendo) { try { lecto.await(); } catch (InterruptedException e) {e.printStackTrace();} } 
			
			lectores++;
			lecto.signalAll();
        }
        finally{ l.unlock(); }
    }

    /**
     * metodo para finalizar la lectura
     */

    public void acabarLectura(){
		l.lock();
        
		try{
			lectores--;
        	if(lectores == 0){ escri.signalAll(); }
		}finally{ l.unlock(); }  
		
    }

    /**
     * metodo para iniciar la escritura
     */

    public void iniciaEscritura(){
        l.lock();

		try{
			while (lectores != 0 || escribiendo) { try { escri.await(); } catch (InterruptedException e) {e.printStackTrace();} }
        	escribiendo = true;
		}finally{ l.unlock(); }   
    }

    /**
     * metodo para finalizar la escritura
     */

    public void acabarEscritura(){
        l.lock();

		try{
			escribiendo = false;
        	escri.signalAll();
        	lecto.signalAll();
		}finally{ l.unlock(); }  
    }

}