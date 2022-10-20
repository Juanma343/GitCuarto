import java.util.*;

public class NewtonRaphson {

    public static void aproxa(double x, int n){
        for(int i = 1; i <= n; i++){
            x = x - fa(x)/fpa(x);
            System.out.println("La " + i + " iteracion se aproxima a " + x);
        }
        System.out.println("La funcion vale 0 en x = " + x);
    }

    public static void aproxb(double x, int n){
        for(int i = 1; i <= n; i++){
            x = x - fb(x)/fpb(x);
            System.out.println("La " + i + " iteracion se aproxima a " + x);
        }
        System.out.println("La funcion vale 0 en x = " + x);
    }

    public static double fa (double x){
        return Math.cos(x) - Math.pow(x, 3);
    }

    public static double fpa (double x){
        return (- Math.sin(x) - 3 * Math.pow(x, 2));
    }

    public static double fb (double x){
        return Math.pow(x, 2) - 5;
    }

    public static double fpb (double x){
        return 5 * x;
    }


    public static void main (String [] args){
        System.out.println("¿Que función prefiere? 1 o 2");
        Scanner a = new Scanner(System.in);
        int elecion = a.nextInt();
        boolean compro = true;
        double x;
        do{
            System.out.println("Aproxmacion inicial:");
            //a = new Scanner(System.in);
            x = a.nextDouble();
            if (elecion == 1){
                compro = x < 0 || x > 1;
            }
            else if (elecion == 2){
                compro = x < 2 || x > 3;
            }

            if (compro){
                System.out.println("fuera de rango");
            }
        }while(compro);
        System.out.println("Iteraciones:");
        //a = new Scanner(System.in);
        int it = a.nextInt();
        a.close();

        if (elecion == 1){
            aproxa(x, it);
        }
        else if (elecion == 2){
            aproxb(x, it);
        }
    }
}