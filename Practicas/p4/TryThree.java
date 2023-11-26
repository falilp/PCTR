package p4;

/**
 * Esta clase contiene los atributos y metodos de TryThree
 * @author Rafael Leal Pardo
 * @version 2022
*/

public class TryThree extends Thread{
    private int tipoHilo;
    private static volatile boolean B1 = false;
    private static volatile boolean B2 = false;
    private static volatile int nVueltas = 10000;
    private static volatile int n = 0;

    /**
     * Metodo constructor parametrizado
     * @param tipoHilo Inserta un entero para indicar su tipo
     */
    public TryThree(int tipoHilo){this.tipoHilo=tipoHilo;}
    
    /**
     * Metodo que realizara el algoritmo "Third attempt" que dependera de su tipo de hilo para
     * realizzar el p y el q
     */
    public void run(){
        switch(tipoHilo){
            case 1:
                {for(int i=0; i<nVueltas; i++){
                    B1 = true;
                    while(B2 == false);
                    n++;
                    System.out.print(getName()+"\n"); 
                    B1 = false;
                }
                break;}
            case 2: 
                {for(int i=0; i<nVueltas;i++){
                    B2 = true;
                    while(B1 == false);
                    n--;
                    System.out.print(getName()+"\n"); 
                    B2 = false;
                }
                }break;
        }
    }

    /**
     * Metodo Main que se crearan dos objetos de la clase para despues iniciar el algoritmo "Third attempt"
     * @param args
     * @throws InterruptedException
     */
    public static void main(String [] args) throws InterruptedException{
        TryThree h1 = new TryThree (1);
        TryThree h2 = new TryThree (2);
        h1.start(); h2.start();
        h1.join(); h2.join();
        System.out.println(n);
    }
}