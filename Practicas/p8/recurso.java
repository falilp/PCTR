/**
 * Esta clase contiene los atributos y metodos de recurso
 * @author Rafael Leal Pardo
 * @version 2022
*/

public class recurso{
    private int n = 0;
    
    /**
     * Método incremento de la variable n.
     */
    public void inc(){n++;}

    /**
     * Método decremento de la variable n.
     */
    public void dec(){n--;}

    /**
     * Método observador que devuelve el n.
     * @return n
     */
    public int observer(){return n;}
}
