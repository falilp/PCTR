import java.util.Random;

/**
 * Esta clase contiene los atributos y metodos de arrSeguro con Thread
 * @author Rafael Leal Pardo
 * @version 2022
*/

public class arrSeguro extends Thread{
    private static final int nHebras = 10;
    private static final int nTamanio = 100;
    private final int INICIO,FIN;
    private static int array[] = new int[nTamanio];
    public static Object lock = new Object ();

    
    /**
     * Metodo constructor parametrizado.
     * @param ini Entero que registra el inicio del hilo.
     * @param fin Entero que registra el fin del hilo.
     */
    public arrSeguro(int ini,int fin){
        this.INICIO = ini;
        this.FIN = fin;
    }

    /**
     * Metodo run que accederan los hilos de forma segura debido a synchronized con su cerrojo
     * que permitira la entrada a un hilo sin que interfieran los demas.
     */
    public void run(){
        Random ran = new Random();
        synchronized(lock){
            for(int index=INICIO; index<FIN; index++){
                array[index] = ran.nextInt(); 
            }
        }
    }

    /**
     * Metodo Mostrar que imprimira por pantalla el array entero.
     */
    public static void Mostrar(){
        System.out.print("El vector es: [");
        for(int index=0; index<nTamanio; index++){
            System.out.print(","+ array[index]);
        }
        System.out.println("]");
    }

    /**
     * Metodo main que iniciara los valores y threads para luego ejecutarlos
     * @param args
     * @throws Exception
     */
    public static void main(String[] args)throws Exception{
        int tVentana = nTamanio/nHebras;
        int inicio = 0;
        int fin = tVentana;
        arrSeguro vectorThreads[] = new arrSeguro[nHebras];
        
        Long inicioCrono = System.currentTimeMillis();

        for(int index=0; index<nHebras; index++){
            vectorThreads[index] = new arrSeguro(inicio,fin);
            inicio = fin+1;
            fin += tVentana;
        }

        for(int index=0; index<nHebras; index++){vectorThreads[index].start();}
        for(int index=0; index<nHebras; index++){vectorThreads[index].join();}
        
        Long finalizoCrono = System.currentTimeMillis();

        Mostrar();
        System.out.println("El tiempo utilizado es de: "+ (finalizoCrono - inicioCrono) +" ms");
    }
}