

/**
 * Clase que solo contiene el main, y es donde se declar los diferentes cajeros y cuentas. se encarga de provocar la condicion de concurso sobre el saldo de los obetos cuentaCorriente, mediante los hombetos cajeros;
 * 
 * @see java.lang.Thread
 * @see cuentaCorriente
 * @see cajero
 */

public class redCajeros {

    public static void main(String[] args) throws Exception {
        cuentaCorriente cuenta0 = new cuentaCorriente(0, 1000);
        cuentaCorriente cuenta1 = new cuentaCorriente(1, 2000);

        Thread t1 = new cajero(cuenta0, true, 100000);
        Thread t2 = new cajero(cuenta0, false, 100000);
        Thread t3 = new cajero(cuenta1, true, 100000);
        Thread t4 = new cajero(cuenta1, false, 100000);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t1.join();
        t2.join();
        t3.join();
        t4.join();
        System.out.println("Numero de cuenta: " + cuenta0.nCuenta + " Saldo: " + cuenta0.saldo);
        System.out.println("Numero de cuenta: " + cuenta1.nCuenta + " Saldo: " + cuenta1.saldo);


    }

}
