public class prodCon {
    public int[] vector;

    public prodCon (int[] vec) {
        vector = vec;
    }

    public synchronized void producir (int i) { 
        boolean cont = false;
        while (cont) try { wait(); } catch (InterruptedException e){}
        cont = true;
        System.out.println("Se ha producudo" + ++i);
        cont = false;
        notifyAll();
    }

    public synchronized void consumir (int i) { 
        boolean cont = false;
        while (cont) try { wait(); } catch (InterruptedException e){}
        cont = true;
        System.out.println("Se ha consumido" + ++i);
        cont = false;
        notifyAll();
    }
}
