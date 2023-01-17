import java.rmi.Naming;
import java.sql.Ref;
import java.util.Scanner;

/**
 * Esta clase contiene el método main para realizar una aproximación de Pi por el método de Monte Carlo.
 * El usuario puede elegir entre tres opciones: 
 * Añadir puntos
 * Obtener Pi 
 * Restaurar 
 */

public class cPiMonteCarlo {
    public static void main(String[] args) throws Exception {
        
        System.out.print("Que desea realizar:\n\t1.Añadir puntos\n\t2.Obtener Pi\n\t3.Restaurar\n\nAccion:");
        Scanner terminal = new Scanner(System.in);
        iPiMonteCarlo RefRemoto = (iPiMonteCarlo) Naming.lookup("//localhost/pi");

        int op = terminal.nextInt();

        if(op == 1){
            System.out.println("\nPuntos a añadir: ");
            RefRemoto.masPuntos(terminal.nextInt());
        }
        else if(op == 2){
            System.out.println("Pi es " + RefRemoto.aproxActual());
        }
        else if(op == 3){
            RefRemoto.reset();
        }
    }
}
