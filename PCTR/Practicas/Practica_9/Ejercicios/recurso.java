/**
 * clase encargada de aumentar o disminuir una variable
 */
public class recurso {
    private long v = 0;
    
    /**
     * metodo que incrementa la variable interna 
    */
    
    public void inc(){ v++; }

    /**
     * metodo que observa la variable interna 
     * @return devuelve 1
     */
    public long observar(){ return v; }
}
