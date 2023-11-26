package p4;

/**
 * Esta clase contiene los atributos y metodos de algDekker
 * @author Rafael Leal Pardo
 * @version 2022
*/

public class algDekker extends Thread{
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
    public algDekker(int tipoHilo){this.tipoHilo=tipoHilo;}
    
    /**
     * Metodo que realizara el algoritmo "Dekker's algorithm" que dependera de su tipo de hilo para
     * realizzar el p y el q
     */
    public void run(){
        switch(tipoHilo){
            case 1:
                {for(int i=0; i<nVueltas; i++){
                    B1 = true;
                    while(B2){
                        if(turno == 2){
                            B1 = false;
                            while(turno == 1);
                            B1 = true;
                        }
                    }
                    n++;
                    System.out.print(getName()+"\n"); 
                    turno = 2;
                    B1 = false;
                }
                break;}
            case 2: 
                {for(int i=0; i<nVueltas;i++){
                    B2 = true;
                    while(B1){
                        if(turno == 1){
                            B2 = false;
                            while(turno == 2);
                            B2 = true;
                        }
                    }
                    n--;
                    System.out.print(getName());
                    turno = 1;
                    B2 = false;
                }
                }break;
        }
    }
    
    /**
     * Metodo Main que se crearan dos objetos de la clase para despues iniciar el algoritmo "Dekker's algorithm"
     * @param args
     * @throws InterruptedException
     */
    public static void main(String [] args) throws InterruptedException{
        algDekker h1 = new algDekker(1);
        algDekker h2 = new algDekker (2);
        h1.start(); h2.start();
        h1.join(); h2.join();
        System.out.println(n);
    }
}