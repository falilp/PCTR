import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.*;

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
    private final Lock Key = new ReentrantLock();
    private final Condition Con = Key.newCondition();

    /*
     * Método constructor no parametrizado y que asignará 
     * los valores correspondientes a las variables.
     */
    public lectorEscritor(){
        lectores = 0;
        lector = false;
        escritor = true;
        escribiendo = false;
    }

    /**
     * Método iniciaLectura que controlará la exclusión mutua con ReentrantLock, si cumple la condición entra en await del Condition,
     * al continuar incrementa lectores, lector pasa a TRUE y Condition realizará signalAll para despertar a los demas hilos.
     */
    public void iniciaLectura(){
        Key.lock();
        try{
            if(escribiendo){try{Con.await();}catch(InterruptedException excep){}}

            lectores++;
            lector = true;

            Con.signalAll();
        }finally{
            Key.unlock();
        }
    }

    /**
     * Meétodo acabarLectura que controlará la exclusión mutua con ReentrantLock, que reducirá el número de lectores
     * y si cumple la condición del número de lectores igual a 0 cambiará la variable escritor a TRUE
     * y Condition realizará signalAll para despertar a los demas hilos.
     */
    public void acabarLectura(){
        Key.lock();
        try{
            lectores--;

            if(lectores == 0){
                escritor = true;
            }
            Con.signalAll();
        }finally{
            Key.unlock();
        }
    }

    /**
     * Método iniciaEscritura que controlará la exclusión mutua con ReentrantLock, si cumple la condición Condition realizará await
     * y al continuar escribiendo pasa a TRUE.
     */
    public void iniciaEscritura(){
        Key.lock();
        try{
            if(lectores != 0 || escribiendo){try{Con.await();}catch(InterruptedException excep){}}

            escribiendo = true;
        }finally{
            Key.unlock();
        }
    }

    /**
     * Metodo acabarEscritura que controlará la exclusión mutua con ReentrantLock, cambiará la variable escribiendo a TRUE,
     * si cumple la condición de la negación del booleano lector y escritor cambiará la variable a TRUE.
     * Al final Condition realizará signalAll para despertar a los demas hilos.
     */
    public void acabarEscritura(){
        Key.lock();
        try{
            escribiendo = false;

            if(!lector){
                escritor = true;
            }
            Con.signalAll();
        }finally{
            Key.unlock();
        }
    }
}