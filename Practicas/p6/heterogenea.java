/**
 * Esta clase contiene los atributos y metodos de heterogenea
 * @author Rafael Leal Pardo
 * @version 2022
*/

public class heterogenea{
    private int n = 0;
    private int m = 0;
    //public static Object lock = new Object();
    
    /**
     * Metodo constructor vacio
     */
    public heterogenea(){}
    
    /**
     * Metodo que incrementa la variable n de manera sincronizada
     */
    public synchronized void IncrementoN(){n++;}
    
    /**
     * Incremento de la variable n con cerrojo
     */
    //public void IncrementoN(){synchronized(lock){n++;}}

    /**
     * Meotodo que incrementa la variable m sin seguridad
     */
    public void IncrementoM(){m++;}

    /**
     * Metodo observador de n
     * @return Retorna n 
     */
    public int DevolverN(){return n;}

    /**
     * Metodo observador de m
     * @return Retorna m 
     */
    public int DevolverM(){return m;}

}