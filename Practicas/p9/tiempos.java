import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Esta clase contiene los atributos y metodos de tiempos con Runnable
 * @author Rafael Leal Pardo
 * @version 2022
*/

public class tiempos implements Runnable{
    public enum TIPE{CERROJO,SEMAFORO,REENTRANT,ATOMIC};
    public static final int nHebras = 5;
    public static final long LongInicial = 1000;
    private TIPE tipo;
    private static int n = 0; 
    private static long sumaTiemposCerrojo = LongInicial;
    private static long sumaTiemposSemaforo = LongInicial;
    private static long sumaTiemposReentrante = LongInicial;
    private static long sumaTiemposAtomico = LongInicial;

    private static Object mutex = new Object();
    private static Semaphore sem = new Semaphore(1);
    private static ReentrantLock reent = new ReentrantLock();
    private static AtomicInteger atomicINT = new AtomicInteger();

    /**
     * Méotdo constructor parametrizado.
     * @param tip Variable TIPO el cual és un enum con CERROJO,SEMAFORO,REENTRANT y ATOMIC.
     */
    public tiempos(TIPE tip){
        this.tipo = tip;
    }

    /**
     * Método run donde las hebras segun su tipo realizaran el cerrojo, semaforo, cerrojo reentrante y variables atámicas.
     */
    public void run(){
        switch(tipo){
            case CERROJO: 
                sumaTiemposCerrojo += cerrojo(sumaTiemposCerrojo);
            case SEMAFORO:
                sumaTiemposSemaforo += semaforo(sumaTiemposSemaforo);
           case REENTRANT: 
                sumaTiemposReentrante += reentrante(sumaTiemposReentrante);
            case ATOMIC: 
                sumaTiemposAtomico += atomico(sumaTiemposAtomico);
        }
    }

    /**
     * Método cerrojo que controlará la exclusión mutua sobre la variable n mediante cerrojo sincronizado.
     * @param iter Variable long que servirá de valor límite en el for.
     * @return devolverá la diferencia entre el inicio del cronómetro y su fin.
     */
    public synchronized long cerrojo(long iter){
        long InicioCrono = System.currentTimeMillis();
        
        for(long i=0; i<iter; i++){
            synchronized(mutex){
                n++;
            }
        }
        
        long FinalizoCrono = System.currentTimeMillis();
        return (FinalizoCrono-InicioCrono);
    }

    /**
     * Método cerrojo que controlará la exclusión mutua sobre la variable n mediante semaforo.
     * @param iter Variable long que servirá de valor límite en el for.
     * @return devolverá la diferencia entre el inicio del cronómetro y su fin.
     */
    public long semaforo(long iter){
        long InicioCrono = System.currentTimeMillis();
        
        for(long i=0; i<iter; i++){
            try{sem.acquire();}catch(InterruptedException excep){}
            try{n++;}finally{sem.release();}
        }
        
        long FinalizoCrono = System.currentTimeMillis();
        return (FinalizoCrono-InicioCrono);
    }

    /**
     * Método cerrojo que controlará la exclusión mutua sobre la variable n mediante Reentrantlock.
     * @param iter Variable long que servirá de valor límite en el for.
     * @return devolverá la diferencia entre el inicio del cronómetro y su fin.
     */
    public long reentrante(long iter){
        long InicioCrono = System.currentTimeMillis();
        
        for(long i=0; i<iter; i++){
            reent.lock();
            try{n++;}finally{reent.unlock();}     
        }
        
        long FinalizoCrono = System.currentTimeMillis();
        return (FinalizoCrono-InicioCrono);
    }

    /**
     * Método cerrojo que controlará la exclusión mutua sobre la variable n mediante variable atomic.
     * @param iter Variable long que servirá de valor límite en el for.
     * @return devolverá la diferencia entre el inicio del cronómetro y su fin.
     */
    public long atomico(long iter){
        long InicioCrono = System.currentTimeMillis();
        
        for(long i=0; i<iter; i++){
            n += atomicINT.incrementAndGet();
        }

        long FinalizoCrono = System.currentTimeMillis();
        return (FinalizoCrono-InicioCrono);
    }

    /**
     * Método main que inicializará objetos de la clase, hebras con runnable 
     * @param args
     * @throws Exception
     */
    public static void main(String[] args)throws Exception{
        tiempos tiempoMutex = new tiempos(TIPE.CERROJO);tiempos tiempoSemaforo = new tiempos(TIPE.SEMAFORO);tiempos tiempoReentrate = new tiempos(TIPE.REENTRANT);tiempos tiempoAtomico = new tiempos(TIPE.ATOMIC);
        Runnable HilosMutex[] = new Runnable[nHebras];Runnable HilosSemaforo[] = new Runnable[nHebras];Runnable HilosReentrate[] = new Runnable[nHebras];Runnable HilosAtomic[] = new Runnable[nHebras];
        Thread HM[] = new Thread[nHebras];Thread HS[] = new Thread[nHebras];Thread HR[] = new Thread[nHebras];Thread HA[] = new Thread[nHebras];
       
        for(int i=0; i<nHebras; i++){
            HilosMutex[i] = tiempoMutex;HilosSemaforo[i] = tiempoSemaforo;HilosReentrate[i] = tiempoReentrate;HilosAtomic[i] = tiempoAtomico;
        }

        for(int i=0; i<nHebras; i++){
            HM[i] = new Thread(HilosMutex[i]);HS[i] = new Thread(HilosSemaforo[i]);HR[i] = new Thread(HilosReentrate[i]);HA[i] = new Thread(HilosAtomic[i]);
        }

        for(int i=0; i<nHebras; i++){
            HM[i].start();HS[i].start();HR[i].start();HA[i].start();
        }

        for(int i=0; i<nHebras; i++){
            HM[i].join();HS[i].join();HR[i].join();HA[i].join();
        }
        
        System.out.println("Suma tiempos Mutex: "+ sumaTiemposCerrojo +", Suma tiempos Semaforo: "+ sumaTiemposSemaforo +", Suma tiempos Reentrantes: "+ sumaTiemposReentrante +", Suma tiempos Atomicos: "+ sumaTiemposAtomico);
    }
}