//import java.util.Scanner ;
import java.util.Scanner;

interface funcion {
    public abstract double f (double x);
    public abstract double fp (double x);
    public abstract double aprox();
    public abstract int n();
}

public class calculo{

    public static void main(String[] args){
        funcion funcion_;
        System.out.println("¿Que función prefiere? 1 o 2");
        Scanner en = new Scanner(System.in);
        int elecion = en.nextInt();
        boolean compro = true;
        double x;
        do{
            System.out.println("Aproxmacion inicial:");
            x = en.nextDouble();
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
        int it = en.nextInt();
        en.close();

        if (elecion == 1){
            funcion_ = new NewtonRaphsno_Fa(x, it);
        }
        else{
            funcion_ = new NewtonRaphsno_Fb(x, it);
        }

        double apr = funcion_.aprox();
        for (int i = 0; i <= funcion_.n(); i++){
            apr = apr - (funcion_.f(apr) / funcion_.fp(apr));
        }
        System.out.println("La funcion vale 0 en x = " + apr);
    }
}
class NewtonRaphsno_Fa implements funcion {
    
    public NewtonRaphsno_Fa(double x, int it){
        aproxv = x;
        nv = it;
    }

    public double f (double x){
        return Math.cos(x) - Math.pow(x, 3);
    }

    public double fp (double x){
        return (- Math.sin(x) - 3 * Math.pow(x, 2));
    }
    
    public double aprox(){
        return aproxv;
    }

    public int n(){
        return nv;
    }

    public double aproxv;
    public int nv;
}
class NewtonRaphsno_Fb implements funcion {
    
    public NewtonRaphsno_Fb(double x, int it){
        aproxv = x;
        nv = it;
    }

    public double f (double x){
        return Math.pow(x, 2) - 5;
    }

    public double fp (double x){
        return 5 * x;
    }

    public double aprox(){
        return aproxv;
    }

    public int n(){
        return nv;
    }
    
    public double aproxv;
    public int nv;
}