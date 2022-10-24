

public class cajero implements Runnable {
    
    cuentaCorriente usuario;

    public cajero (cuentaCorriente usu){
        this.usuario = usu;
    }

}