import java.rmi.*;
import java.rmi.server.*;

/**
 * Esta clase representa un servidor remoto que calcula aproximaciones del número pi usando el método de Monte Carlo.
 * Esta clase extiende UnicastRemoteObject e implementa la interfaz iPiMonteCarlo, lo que le permite ser accedida remotamente.
 * Contiene los métodos reset(), masPuntos(int nPuntos) y aproxActual() para reiniciar los valores, agregar puntos al cálculo y obtener la aproximación actual respectivamente. 
 * El método main() se encarga de vincular el objeto remoto con el servidor.
 */

public class sPiMonteCarlo extends UnicastRemoteObject implements iPiMonteCarlo {
    private double pi = 0;
    private int dentro = 0;
    private int tot = 0;

    /**
     * Establece los valores de los atributos pi, dentro y tot a 0. 
     * 
     * @throws RemoteException si hay un problema con la conexión remota.
     */

    public void reset() throws RemoteException{
        pi = dentro = tot = 0;
    }

    /**
     * Esta función calcula el valor aproximado de pi. 
     * Genera nPuntos números aleatorios entre 0 y 1, los cuales son usados para calcular el valor de pi. 
     * Si el punto cae dentro del área del círculo, se incrementa la variable dentro, la cual es usada para calcular el valor de pi. 
     * El resultado se almacena en la variable pi. 
     * 
     * @param nPuntos Número de puntos aleatorios a generar para calcular pi. 
     * @throws RemoteException Excepción remota que puede ser lanzada si hay un error en la conexión remota. 
     */

    public void masPuntos(int nPuntos)  throws RemoteException{
        double x, y;
        for (int i = 0; i < nPuntos; i++){
            x = Math.random();
            y = Math.random();
            if (y <= Math.sqrt(1-Math.pow(x, 2))){
                dentro++;
            }
            tot++;
        }

        pi = (double) dentro / tot * 4;
    }

    /**
     * Esta función devuelve un valor de tipo double que representa una aproximación de PI. 
     * 
     * @return Un valor de tipo double que representa una aproximación de PI. 
     * @throws RemoteException si hay algún problema con la conexión remota.
     */

    public double aproxActual() throws RemoteException{
        return pi;
    }

    /**
     * constructor de la calse
     */

    public sPiMonteCarlo() throws RemoteException{}

    public static void main(String[] args) throws Exception {
        
        iPiMonteCarlo oRemoto = new sPiMonteCarlo();

        Naming.bind("pi", oRemoto);

        System.out.println("Servidor  Remoto  Preparado");
    }

}
