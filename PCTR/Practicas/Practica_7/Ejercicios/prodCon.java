import java.util.ArrayList;
import java.util.List;

public class prodCon {
    public static int tam = 1;
    public static List<Integer> bufer = new ArrayList<Integer>();
    public static int inptr = 0;
    public  static int outptr = 0;

    public prodCon () {}

    public synchronized void producir (int i) { 
        while (bufer.size() > tam) try { wait(); } catch (InterruptedException e){}
        System.out.println("Se ha producido" + i );
        bufer.add(inptr, i);
        inptr = (++inptr % tam);
        notifyAll();
    }

    public synchronized void consumir () { 
        while (bufer.size() <= 0) try { wait(); } catch (InterruptedException e){}
        int dat = bufer.remove(outptr);
        outptr = (++outptr % tam);
        System.out.println("Se ha consumido" + dat);
        notifyAll();
    }
}
