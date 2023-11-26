/**
 * Esta clase contiene los atributos y metodos de usalectorEscritor con Thread
 * @author Rafael Leal Pardo
 * @version 2022
*/

public class usalectorEscritor extends Thread{
    public enum TIPO{ESCRITOR,LECTOR};
    private lectorEscritor Monitor;
    private TIPO tipoDeHilo;
    private static recurso N = new recurso();

    /**
     * Metodo Constructor Parametrizado
     * @param tip Enum co nel tipo de lector o escritor.
     * @param mon lectorEscritor monitor
     */
    public usalectorEscritor(TIPO tip,lectorEscritor mon){
        this.Monitor = mon;
        this.tipoDeHilo = tip;
    }

    /**
     * Metodo run que realizara los inicios de escritura y lectura junto a 
     * el fin de este mismo. Adema del incremento de la variable n de la clase
     * recurso.
     */
    public void run(){
        int data = 0;
        switch(tipoDeHilo){
            case ESCRITOR:
                for(int i=0; i<1000000; i++){
                    //try{Thread.sleep(100);}catch(InterruptedException e){}
                    Monitor.iniciaEscritura();
                    N.inc();
                    //try{Thread.sleep(100);}catch(InterruptedException e){}
                    Monitor.acabarEscritura();
                }
                break;
            case LECTOR:
                for(int i=0; i<1000000; i++){
                    //try{Thread.sleep(100);}catch(InterruptedException e){}
                    Monitor.iniciaLectura();
                    data = N.observer();
                    System.out.println("Numero actual = "+ data);
                    //try{Thread.sleep(100);}catch(InterruptedException e){}
                    Monitor.acabarLectura();
                }
                break;
        }
    }

    /**
     * Metodo main que inicializara el monitor junto a sus hilos escritores y lectores
     * y los ejecutara.
     * @param args
     * @throws Exception
     */
    public static void main(String[] args)throws Exception{
        lectorEscritor monito = new lectorEscritor();

        usalectorEscritor[] HiloEscritor = new usalectorEscritor[4];
        usalectorEscritor[] HiloLector = new usalectorEscritor[4];

        for(int i = 0; i < 4; i++){
            HiloEscritor[i] = new usalectorEscritor(TIPO.ESCRITOR,monito);
            HiloLector[i] = new usalectorEscritor(TIPO.LECTOR,monito);
        }

        for(int i = 0; i < 4; i++){
            HiloLector[i].start();
            HiloEscritor[i].start();
        }
        
        for(int i = 0; i < 4; i++){
            HiloLector[i].join();
            HiloEscritor[i].join();
        }
    }
}