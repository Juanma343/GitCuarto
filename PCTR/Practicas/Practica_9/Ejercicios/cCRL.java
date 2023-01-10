import java.util.concurrent.locks.ReentrantLock;

/*
 * Clase que almacena tanto un identifcador como el saldo de una cuenta, ademas de los metodos de incremento y decremento del saldo de la clase
 * @see cuentaCorriente
 */

public class cCRL {
    
    /**
     * Atributo identificador de la cuenta
     */

    public int nCuenta;

    /**
     * Atributo que guarda el saldo de la cuenta
     */

    public double saldo;

    public ReentrantLock cerrojo;

    /**
     * Constructor parametrizado de cuentaCorriente
     * 
     * @param nCuenta identificador de la ceunta
     * @param saldo Saldo inicial de la cuenta
     */

    public cCRL(int nCuenta, double saldo) {
        this.nCuenta = nCuenta;
        this.saldo = saldo;
        this.cerrojo = new ReentrantLock();
    }

    /**
     * Funcion deposito que incrementa saldo madiante un for
     * 
     * @param d
     */

    public void deposito(double d) {
        cerrojo.lock();

        double n = 0;
        for(int i = 1; i < d; i++){
            this.saldo++;
            n = i;
        }
        this.saldo += d - n;

        cerrojo.unlock();
    }

    /**
     * Funcion deposito que decrementa saldo mediante un for
     * 
     * @param d
     */


    public void reintegro(double d) {
        cerrojo.lock();

        double n = 0;
        for(int i = 1; i < d; i++){
            this.saldo--;
            n = i;
        }
        this.saldo -= d - n;

        cerrojo.unlock();
    }
}
