package p4;

/**
 * Esta clase contiene los atributos y metodos de algDekker
 * @author Rafael Leal Pardo
 * @version 2022
*/

public class algPeterson implements Runnable{
    private int tipoHilo;
    private static volatile boolean B1 = false;
    private static volatile boolean B2 = false;
    private static volatile int nVueltas = 10000;
    private static volatile int n = 0;
    private static volatile int turno = 1;

    /**
     * Metodo constructor parametrizado
     * @param tipoHilo Inserta un entero para indicar su tipo
     */
    public algPeterson(int tipoHilo){this.tipoHilo=tipoHilo;}
    
    /**
     * Metodo que realizara el algoritmo "Peterson's algorithm" que dependera de su tipo de hilo para
     * realizzar el p y el q
     */
    public void run(){
        switch(tipoHilo){
            case 1:
                {for(int i=0; i<nVueltas; i++){
                    B1 = true;
                    turno = 1;
                    while(B2 == false || turno == 2);
                    n++;
                    System.out.print(Thread.currentThread().getName()); 
                    B1 = false;
                }
                break;}
            case 2: 
                {for(int i=0; i<nVueltas;i++){
                    B2 = true;
                    turno = 2;
                    while(B1 == false || turno == 1);
                    n--;
                    System.out.print(Thread.currentThread().getName());
                    B2 = false;
                }
                }break;
        }
    }

    /**
     * Metodo Main que se crearan dos objetos de la clase para despues iniciar el algoritmo "Peterson's algorithm"
     * @param args
     * @throws InterruptedException
     */
    public static void main(String [] args) throws InterruptedException{
        Runnable r1 =  new algPeterson(1);
        Runnable r2 =  new algPeterson(2);
        Thread h1 = new Thread(r1);
        Thread h2 = new Thread(r2);
        h1.start(); h2.start();
        h1.join(); h2.join();
        System.out.println(n);
    }
}
