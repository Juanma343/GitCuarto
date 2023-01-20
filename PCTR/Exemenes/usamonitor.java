import java.util.Scanner;

import javax.management.monitor.Monitor;

public class usamonitor extends Thread{
    int sitio;
    static MonitorImpresoras monitor;

    public usamonitor(int sitio){
        this.sitio = sitio;
    }

    public void run(){
        for(int i = 0; i < 3; i++){
            monitor.imprime();
        }
    }

    public static void main(String[] args) {
        int n; int k;
        System.out.println("Introduce el numero de puestos");
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        System.out.println("Introduce el numero de impresoras");
        k = sc.nextInt();
        monitor = new MonitorImpresoras(k);

        Thread a = new usamonitor(1);
        Thread b = new usamonitor(2);
    }
    
}
