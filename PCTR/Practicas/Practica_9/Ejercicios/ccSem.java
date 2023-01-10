import java.util.concurrent.Semaphore;

/*
 * Clase que almacena tanto un identifcador como el saldo de una cuenta, ademas de los metodos de incremento y decremento del saldo de la clase
 * @see cuentaCorriente
 */

public class ccSem {
    
    /**
     * Atributo identificador de la cuenta
     */

    public int nCuenta;

    /**
     * Atributo que guarda el saldo de la cuenta
     */

    public double saldo;

    public Semaphore sem;

    /**
     * Constructor parametrizado de cuentaCorriente
     * 
     * @param nCuenta identificador de la ceunta
     * @param saldo Saldo inicial de la cuenta
     */

    public ccSem(int nCuenta, double saldo) {
        this.nCuenta = nCuenta;
        this.saldo = saldo;
        this.sem = new Semaphore(1);
    }

    /**
     * Funcion deposito que incrementa saldo madiante un for
     * 
     * @param d
     * @throws InterruptedException
     */

    public void deposito(double d) throws InterruptedException {
        sem.acquire();
        
        double n = 0;
        for(int i = 1; i < d; i++){
            this.saldo++;
            n = i;
        }
        this.saldo += d - n;

        sem.release();
    }

    /**
     * Funcion deposito que decrementa saldo mediante un for
     * 
     * @param d
     * @throws InterruptedException
     */


    public void reintegro(double d) throws InterruptedException {
        sem.acquire();

        double n = 0;
        for(int i = 1; i < d; i++){
            this.saldo--;
            n = i;
        }
        this.saldo -= d - n;

        sem.release();
    }
}
