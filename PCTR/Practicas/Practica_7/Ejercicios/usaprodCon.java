public class usaprodCon extends Thread {
    private static prodCon prod = new prodCon();
    private static int dato = 1;
    private int tip; // 1 == productor y 2 == consumidor

    public usaprodCon(int tipo){
        tip = tipo;
    }
    
    public void run(){
        switch (tip) {
            case 1: prod.producir(++dato); break;
            case 2: prod.consumir(); break;
        }
    }

    public static void main(String[] args) {
        new usaprodCon(1).start();
        new usaprodCon(1).start();
        new usaprodCon(1).start();
        new usaprodCon(2).start();

    }
}
