package p2.ej2;

/**
 * Esta clase contiene los atributos y metodos de tareaRunnable
 * @author Rafael Leal Pardo
 * @version 2022
*/
public class tareaRunnable{
    private int contador=0;

    /**
     * Metodo constructor por defecto
     */
    public tareaRunnable(){}
    /**
     * Metodo que incrementa el contador
     */
    public void inc(){contador++;}
    /**
     * Metodo que decrementa el contador
     */
    public void dec(){contador--;}
    /**
     * Metodo que regresa el ontador 
     * @return Regresa el entero que tiene asignador el contador
     */
    public int vDato(){return contador;}
}