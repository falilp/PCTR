/**
 * Esta clase contiene los atributos y metodos de una hebra
 * @author Rafael Leal Pardo
 * @version 2022
 * @see Persona
 */

public class hebra extends Thread{
    public enum Accion {INCREMENTO,DECREMENTO};

    private Accion tipoHilo;
    private static int n=0;
    private int nVueltas;
    
    /**
     * Metodo constructor parametrizado
     * @param nVueltas numero de vueltas
     * @param tipoHilo numero/tipo de hilo
     */
    public hebra(int nVueltas ,Accion tipoHilo){
        this.nVueltas=nVueltas; 
        this.tipoHilo=tipoHilo ;
    }
    
    /**
     * Metodo run, dependiendo de tipoHilo y lo incrementa o decrementa el valor de n
     */
    public void run(){
        switch(tipoHilo){
            case INCREMENTO: for(int i=0; i<nVueltas; i++)n++; break;
            case DECREMENTO: for(int i=0; i<nVueltas; i++)n--; break;
        }
    }

    /**
     * Metodo para regresar n
     * @return variable n de la clase
     */
    public static int observador(){
        return n;
    }
    
}