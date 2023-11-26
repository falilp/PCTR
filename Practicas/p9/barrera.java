import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Esta clase contiene los atributos y metodos de barrera con implements Runnable
 * @author Rafael Leal Pardo
 * @version 2022
*/

public class barrera implements Runnable{
    private static final int nHilos = 10;
    private static int contador = 0;
    private static CyclicBarrier BARRERA = new CyclicBarrier(nHilos);

    /**
     * Método constructor vacio.
     */
    public barrera(){}

    /**
     * Método run donde las hebras accederán a la sección critica a incrementador el recurso compartido
     * donde solo podrán acceder a ella cuando todas estas hebras lleguen al await del CyclicBarrier que las 
     * contendrá.
     */
    public void run(){
        try{
            BARRERA.await();
        }catch (InterruptedException | BrokenBarrierException excep) {
            excep.printStackTrace();
        }
        ++contador;
        System.out.println(contador);
    }

    /**
     * Método main donde se realizarán la creación de los objetos de la clase y las inicializaciones de de los hilos mediante runnable junto a su inicialización.
     * @param args
     * @throws Exception
     */
    public static void main(String[] args)throws Exception{
        Runnable[] vecRunnable = new Runnable[nHilos];
        Thread[] vecThreads = new Thread[nHilos];

        for(int i=0; i<nHilos; ++i){
            vecRunnable[i] = new barrera();
        }

        for(int i=0; i<nHilos; ++i){
            vecThreads[i] = new Thread(vecRunnable[i]);
        }

        for(int i=0; i<nHilos; ++i){
            vecThreads[i].start();
        }
        for(int i=0; i<nHilos; ++i){
            vecThreads[i].join();
        }
    }
}