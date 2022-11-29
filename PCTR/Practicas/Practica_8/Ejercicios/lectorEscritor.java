public class lectorEscritor extends Thread {
    private static boolean escribiendo = false;
    private static Object lector = new Object();
    private static Object escritor = new Object();
    private static int lectores = 0;
    private int tipo = 0;

    public lectorEscritor(int _tipo){
        tipo = _tipo;
    }

    public void run(){
        switch(tipo){
            case 1: 
                synchronized(lector){
                    if (escribiendo) { try { lector.wait(); } catch (InterruptedException e) {e.printStackTrace();} } 
                    lectores++;
                    lector.notifyAll();
                } break;
                
            case 2: 
                synchronized(lector){
                    lectores--;
                    if(lectores == 0){ lector.notifyAll(); }
                } break;
            case 3: 
                synchronized(escritor){
                    if (lectores != 0 || escribiendo) { try { escritor.wait(); } catch (InterruptedException e) {e.printStackTrace();} }
                    escribiendo = true;
                } break;
            case 4: 
                synchronized(escritor){
                    escribiendo = false;
                    if (lectores == 0) { escritor.notifyAll(); }
                    else { lector.notifyAll(); }
                }
            break;
        }
    }
}
