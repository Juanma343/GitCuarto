package Dependencias;

/**
 * Clase en la que modidifica un atributo pribado n ediante dos metodos los cuales suman o restan uno a este atributo
 */

public class tareaRunnable{
    
    private int n = 0;

    /**
     * Constructor de la Clase por defecto
     */

    public tareaRunnable (){}

    /**
     * Funcion sin parametros que incrementa el valor de n
     */

    public void inc(){this.n++;}

    /**
     * Funcion sin parametros que decrementa el valor de n
     */

    public void dec(){this.n--;}

    /**
     * Funcion observadora del atributo privada n
     * @return Devuelve el atributo privada n
     */

    public int vDato(){return n;}

}
