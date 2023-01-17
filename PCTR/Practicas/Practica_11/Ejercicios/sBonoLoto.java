import java.rmi.*;
import java.rmi.server.*;
import java.rmi.server.*;
import java.lang.reflect.RecordComponent;
import java.net.*;

/**
 * Esta clase representa un servidor remoto para el juego de BonoLoto.
 * Implementa la interfaz iBonoLoto, que contiene los métodos resetServidor() y compApuesta().
 */

public class sBonoLoto extends UnicastRemoteObject implements iBonoLoto {

    private double[] numeros = new double[6];

    /**
     * Esta función resetea el servidor, generando 6 números aleatorios entre 1 y 49.
     * 
     * @throws RemoteException si hay un error en la conexión remota.
     */

    public void resetServidor() throws RemoteException {
        for (int i = 0; i < 6; i++) {
            numeros[0] = Math.random() * 49 + 1;
        }
    }

    /**
     * Esta función compara una apuesta con los números de la lotería. 
     * 
     * @param apuesta Un arreglo de enteros que contiene la apuesta a comparar. 
     * @return true si la apuesta coincide con los números de la lotería, false en caso contrario. 
     * @throws RemoteException si hay un error al realizar la comparación. 
     */

    public boolean compApuesta(int[] apuesta) throws RemoteException {
        for (int i = 0; i < 6; i++) {
            if (numeros[i] != apuesta[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Esta función se encarga de reiniciar el servidor.
     * @throws RemoteException si hay un error al reiniciar el servidor.
     */

    public sBonoLoto() throws RemoteException {
        resetServidor();
    }

    public static void main(String[] args) throws Exception {

        iBonoLoto oRemoto = new sBonoLoto();

        Naming.bind("Servidor", oRemoto);

        System.out.println("Servidor Remoto Premarado");
    }
}
