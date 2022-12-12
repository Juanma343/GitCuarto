public class lectorEscritor {
    private static boolean escribiendo = false;
    private static Object lector = new Object();
    private static Object escritor = new Object();
    private static int lectores = 0;

    public lectorEscritor(){}

    public void iniciaLectura(){
        synchronized(lector){
            if (escribiendo) { try { lector.wait(); } catch (InterruptedException e) {e.printStackTrace();} } 
            lectores++;
            lector.notifyAll();
        }
    }

    public void acabarLectura(){
        synchronized(lector){
            lectores--;
            if(lectores == 0){ escritor.notifyAll(); }
        }
    }

    public void iniciaEscritura(){
        synchronized(escritor){
            if (lectores != 0 || escribiendo) { try { escritor.wait(); } catch (InterruptedException e) {e.printStackTrace();} }
            escribiendo = true;
        }
    }

    public void acabarEscritura(){
        synchronized(escritor){
            escribiendo = false;
            if (lectores == 0) { escritor.notifyAll(); }
            else { lector.notifyAll(); }
        }
    }

}
