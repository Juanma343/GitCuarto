/*Ejemplo de servidor de sockets multihilo
 *@Antonio Tomeu
 *@version 1.0
*/


import java.net.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.io.*;

/**
 * Clase encargada de recivir los numeros por el soket e imprimir este por pantalla.
 */

public class ServidorHiloconPool
  implements Runnable
{
    Socket enchufe;

    /**
     * Constructor parametrizado de la clase que inicializa el socked
     * @param s
     */

    public ServidorHiloconPool(Socket s)
    {enchufe = s;}

    public void run()
    {
    try{
        BufferedReader entrada = new BufferedReader(
                                    new InputStreamReader(
                                        enchufe.getInputStream()));
        String datos = entrada.readLine();
        int j;
        int i = Integer.valueOf(datos).intValue();
        for(j=1; j<=20; j++){
        System.out.println("El hilo "+ Thread.currentThread().getName() +" escribiendo el dato "+i);
        Thread.sleep(1000);}
        enchufe.close();
        System.out.println("El hilo "+Thread.currentThread().getName()+"cierra su conexion...");
    } catch(Exception e) {System.out.println("Error...");}
    }//run

public static void main (String[] args)
{
    int puerto = 2031;
        try{
            ServerSocket chuff = new ServerSocket (puerto, 3000);
            ThreadPoolExecutor exec = new ThreadPoolExecutor(2, 12, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());

            while (true){
                System.out.println("Esperando solicitud de conexion...");
                Socket cable = chuff.accept();
                System.out.println("Recibida solicitud de conexion...");
                exec.execute(new ServidorHiloconPool(cable));
        }//while
      } catch (Exception e)
        {System.out.println("Error en sockets...");}
}//main

}//Servidor

