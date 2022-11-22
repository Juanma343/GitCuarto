public class heterogenea {
    public int n = 0;    // variable protejida
    public int m = 0;    // variable sin protejer

    public synchronized void incN(){n++;}
    public void incM(){m++;}
}
