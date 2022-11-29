import java.nio.Buffer;

import javax.swing.UIDefaults.ProxyLazyValue;

public class usaprodCon extends Thread {
    private static prodCon prod;
    public static int tam = 1000;
    public static int[] bufer = new int[tam];
    public int inptr = 0;
    public int outptr = 0;
    private int dato = 1;
    private int tip; // 1 == productor y 2 == consumidor

    public static void inicializa (){
        int[] vec = new int[100000];
        for (int i = 0; i < 100000; i++){
            vec[i] = i;
        }
        prod = new prodCon(vec);
    }

    public usaprodCon(int tipo){
        tip = tipo;
    }
    
    public void run(){
        switch (tip) {
            case 1: prod.producir(dato);
                    bufer[inptr] = dato++;
                    inptr = (++inptr % tam); break;
            case 2: int dat = bufer[inptr];
                    inptr = (++inptr % tam);
                    prod.consumir(dat); break;
        }
    }

    public static void main(String[] args) {
        inicializa();
        new usaprodCon(1).start();
        new usaprodCon(2).start();

    }
}
