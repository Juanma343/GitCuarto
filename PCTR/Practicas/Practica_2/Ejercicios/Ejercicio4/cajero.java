
/**
 * Clase encargada se incrementar o decrementar los datos de los objetos cuentaCorriente, ademas de ser la clase qu efunciona como thread, ya que hereda de esta mediante un thead
 * 
 * @see java.lang.Thread
 */

public class cajero extends Thread {
    
    /**
     * Atrubuto que guarda la cuenta dodne se ejecutan las operaciones
     */

    cuentaCorriente usuario;

    /**
     * Atrubuto que indica si hace un deposito (true), o reintegro(false)
     */

    boolean eleccion;

    /**
     * Atrubito que indica la antidad para la operacion.
     */

    int cantidad;

    /**
     * Constructor parametrizada de cajero, el cual solo inicializa
     * 
     * @param nUs Cuenta a la que se va a realizar la operacion
     * @param elec Indica si se deposita o se reintegra
     * @param cant Cantidad para al operacion
     */

    public cajero (cuentaCorriente Us, Boolean elec, int cant){
        this.usuario = Us;
        this.eleccion = elec;
        this.cantidad = cant;
    }

    /**
     * Metodo heredado de la clase thread qu esera lo que se ejecute en el hilo
     */

    public void run(){
        if (this.eleccion){
            this.usuario.deposito(this.cantidad);
        }
        else{
            this.usuario.reintegro(this.cantidad);
        }
    }
}