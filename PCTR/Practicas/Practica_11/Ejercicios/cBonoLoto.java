import java.rmi.*;
import java.rmi.registry.*;

/**
 * La clase cBonoLoto es una clase que El método main realiza una llamada al servidor para comprobar si los números de la apuesta coinciden con los números ganadores.
 * Si coinciden, se imprime un mensaje de felicitación, de lo contrario se imprime un mensaje para intentarlo otra vez.
 */

public class cBonoLoto {
    
    public static void main(String[] args) throws Exception {
        int i[] = {1, 2, 3, 4, 5, 6};
        
        iBonoLoto refObjeto = (iBonoLoto) Naming.lookup("//localhost/Servidor");

        if(refObjeto.compApuesta(i)){
            System.out.println("Has ganado la loteria");
        }
        else{
            System.out.println("Intentelo otra vez");
        }

        // System.out.println(refObjeto.compApuesta(i));
    }

}
