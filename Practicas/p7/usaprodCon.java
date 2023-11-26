import java.util.Random;

/**
 * Esta clase contiene los atributos y metodos de prodCon con Thread
 * @author Rafael Leal Pardo
 * @version 2022
*/

public class usaprodCon extends Thread{
    public enum TIPO{PRODUCTOR,CONSUMIDOR};
    private final TIPO tipoHilo; 
    private prodCon Monitor;

    /**
     * Metodo constructor parametrizado.
     * @param tip Enum con el tipo de hilo.
     * @param mon prodCon que es el monitor.
     */
    public usaprodCon(TIPO tip,prodCon mon){
        this.tipoHilo = tip;
        this.Monitor = mon;
    }

    /**
     * Metodo Run que accederán las hebras productoras y consumidoras, 
     * accediendo así al monitor.
     */
    public void run(){
        Random ran = new Random();
        int data = 0;
        switch(tipoHilo){
            case PRODUCTOR:
                while(true){
                    data = ran.nextInt(0,100);
                    try{Thread.sleep(100);}catch(InterruptedException e){}
                    Monitor.append(data);
                    System.out.println("Se produce el dato: "+ data);
                    try{Thread.sleep(100);}catch(InterruptedException e){}
                    Monitor.FinAppend();
                }
            case CONSUMIDOR:
                while(true){
                    try{Thread.sleep(100);}catch(InterruptedException e){}
                    data = Monitor.take();
                    System.out.println("Se consume el dato: "+ data);
                    try{Thread.sleep(100);}catch(InterruptedException e){}
                    Monitor.FinTake();
                }
            default:break;
        }
    }

    /**
     * Metodo Main que realizará la inicialización del monitor y de las hebras.
     * @param args
     * @throws Exception
     */
    public static void main(String[] args)throws Exception{
        prodCon monito = new prodCon();
        usaprodCon HiloProductor1 = new usaprodCon(TIPO.PRODUCTOR,monito);
        usaprodCon HiloProductor2 = new usaprodCon(TIPO.PRODUCTOR,monito);
        usaprodCon HiloProductor3 = new usaprodCon(TIPO.PRODUCTOR,monito);
        usaprodCon HiloConsumidor1 = new usaprodCon(TIPO.CONSUMIDOR,monito);
        usaprodCon HiloConsumidor2 = new usaprodCon(TIPO.CONSUMIDOR,monito);
        usaprodCon HiloConsumidor3 = new usaprodCon(TIPO.CONSUMIDOR,monito);

        HiloProductor1.start();HiloProductor2.start();HiloProductor3.start();HiloConsumidor1.start();HiloConsumidor2.start();HiloConsumidor3.start();
        HiloProductor1.join();HiloProductor2.join();HiloProductor3.join();HiloConsumidor1.join();HiloConsumidor2.join();HiloConsumidor3.join();
    }
}