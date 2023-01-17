import java.util.*;

class punto{
    public punto (double x_, double y_){
        x = x_;
        y = y_;
    }

    public boolean abajo (double fy){
        if (y <= fy){
            return true;
        }
        else {
            return false;
        }
    }

    public double x;
    public double y;
}

public class intDefinidaMonteCarlo{

    public static double fa(double x){
        return Math.sin(x);
    }

    public static double fb(double x){
        return x;
    }

    public static void main(String[] args) {
        punto aleatorio;
        double area = 0;
        
        System.out.println("¿Que función prefiere? 1 o 2");
        Scanner a = new Scanner(System.in);
        int elecion = a.nextInt();
        System.out.println("¿Cuatos puntos desea?");
        // a = new Scanner(System.in);
        int n = a.nextInt();
        a.close();
        
        
        if (elecion == 1){
            if (n >= 100000000){
                System.out.println("Tamaño demasiado grande, esto puede tardar unos segundos");
            }
            for (int i = 0; i < n; i++){
                aleatorio = new punto(Math.random(), Math.random());
                if (aleatorio.abajo(fa(aleatorio.x))){
                    area++;
                }
            }
        }
        else if (elecion == 2){
            for (int i = 0; i < n; i++){
                aleatorio = new punto(Math.random(), Math.random());
                if (aleatorio.abajo(fb(aleatorio.x))){
                    area++;
                }
            }
        }

        System.out.println("El area es: " + area / n);
        
    }
}