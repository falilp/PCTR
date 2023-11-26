/**
 * Esta clase contiene los atributos y metodos de lectorEscritor
 * @author Rafael Leal Pardo
 * @version 2022
*/

public class lectorEscritor{
    private static int lectores = 0;
    private static boolean lector = false;
    private static boolean escritor = true;
    private static boolean escribiendo = false;

    /*
     * Metodo constructor no parametrizado y que asignará 
     * los valores correspondientes a las variables.
     */
    public lectorEscritor(){
        lectores = 0;
        lector = false;
        escritor = true;
        escribiendo = false;
    }

    /**
     * Metodo iniciaLectura sincronizado (solo un hilo en la función), si cumple la condición entra en wait el hilo (Condición de guarda)
     * y al continuar incrementa lectores, lector pasa a TRUE y realiza notifyAll para despertar a los demas hilos.
     */
    public synchronized void iniciaLectura(){
        if(escribiendo){try{wait();}catch(InterruptedException excep){}}

        lectores++;
        lector = true;

        notifyAll();
    }

    /**
     * Metodo acabarLectura sincronizado (solo un hilo en la función), que reducirá el número de lectores
     * y si cumple la condición del número de lectores igual a 0 cambiará la variable escritor a TRUE
     * y realiza notifyAll para despertar a los demas hilos.
     */
    public synchronized void acabarLectura(){
        lectores--;

        if(lectores == 0){
            escritor = true;

            notifyAll();
        }
    }

    /**
     * Metodo iniciaEscritura sincronizado (solo un hilo en la función), si cumple la condición entra en wait el hilo (Condición de guarda)
     * y al continuar escribiendo pasa a TRUE.
     */
    public synchronized void iniciaEscritura(){
        if(lectores != 0 || escribiendo){try{wait();}catch(InterruptedException excep){}}

        escribiendo = true;
    }

    /**
     * Metodo acabarEscritura sincronizado (solo un hilo en la función), que cambiará la variable escribiendo a TRUE,
     * si cumple la condición de la negación del booleano lector y escritor cambiará la variable a TRUE.
     * Al final realizará notifyAll para despertar a los demas hilos.
     */
    public synchronized void acabarEscritura(){
        escribiendo = false;

        if(!lector){
            escritor = true;
        }
        notifyAll();
    }
}