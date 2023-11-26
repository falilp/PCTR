import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Esta clase contiene los atributos y metodos de cCRL con implements Runnable.
 * @author Rafael Leal Pardo
 * @version 2022
*/

public class cCRL implements Runnable{
    public enum TIPO{DEPOSITAR,REINTEGRAR};
    private TIPO tipoThread;
    private Cuenta nCuenta;

    /**
     * Méotdo constructor parametrizado.
     * @param tip Variable TIPO el cual és un enum con DEPOSITAR y REINTEGRAR.
     * @param cue Variable de la clase Cuenta.
     */
    public cCRL(TIPO tip,Cuenta cue){
        tipoThread = tip;
        nCuenta = cue;
    }

    /**
     * Método run donde las hebras segun su tipoThread realizaran el deposito o reintegro sobre la Cuenta.
     */
    public void run(){
        Random ran = new Random();
        switch(tipoThread){
            case DEPOSITAR:
                for(int i=0; i<10; ++i){
                    nCuenta.deposito(ran.nextInt(0,100));
                    try{Thread.sleep(1000);}catch(InterruptedException e){}
                }
            case REINTEGRAR:
                for(int i=0; i<10; ++i){
                    nCuenta.reintegro(ran.nextInt(0,100));
                    try{Thread.sleep(1000);}catch(InterruptedException e){}
                }
            default:break;
        }
    }

    /**
     * Método main donde se realizarán la creación de un objeto de la clase y las inicializaciones de de los hilos mediante runnable junto a su inicialización.
     * @param args
     * @throws Exception
     */
    public static void main(String[] args)throws Exception{
        Cuenta cuen = new Cuenta(100,1000000);

        Runnable Rdeposito = new cCRL(TIPO.DEPOSITAR,cuen);
        Runnable Rreintegro = new cCRL(TIPO.REINTEGRAR,cuen);

        Thread TH1 = new Thread(Rdeposito);
        Thread TH2 = new Thread(Rreintegro);

        TH1.start();TH2.start();
        TH1.join();TH2.join();
    }
}


/**
 * Clase Cuenta donde están las variables correspondientes y métodos necesarios sobre una cuenta. 
*/

class Cuenta{
    private final ReentrantLock Key = new ReentrantLock();
    private int numeroCuenta;
    private int saldo;
    
    /**
     * Método constructor parametrizado.
     * @param Numero Variable int que representa el número de la cuenta.
     * @param sal Variable int que representa el saldo de la cuenta.
     */
    public Cuenta(int Numero,int sal){
        this.numeroCuenta = Numero;
        this.saldo = sal; 
    }

    /**
     * Método donde se realizará la exclusión mutua mediante un ReentrantLock
     * sobre la sección critica y restara la cantidad indicada al saldo de la cuenta. 
     * @param cantidad Variable int que representa la Cantidad a restar.
     */
    public void deposito(int cantidad){
        Key.lock();
        try{
            if(saldo >= cantidad){
                saldo -= cantidad;
                System.out.println("Se deposita: "+ cantidad);
                System.out.println("El estado de la cuenta es: "+ EstadoCuenta()); 
            }
        }finally{Key.unlock();}
    } 

    /**
     * Método donde se realizará la exclusión mutua mediante un ReentrantLock
     * sobre la sección critica y sumará la cantidad indicada al saldo de la cuenta.
     * @param cantidad Variable int que representa la Cantidad a sumar.
     */
    public void reintegro(int cantidad){
        Key.lock();
        try{
            saldo += cantidad;
            System.out.println("Se reintegran: "+ cantidad);
            System.out.println("El estado de la cuenta es: "+ EstadoCuenta()); 
        }finally{Key.unlock();}
    } 

    /**
     * Método observador del número de cuenta.
     * @return Regresa el número de la cuenta Corriente.
     */
    public int numeroDeCuenta(){return numeroCuenta;}

    /**
     * Método observador del saldo.
     * @return Regresa el saldo de la cuenta Corriente.
     */
    public int EstadoCuenta(){return saldo;}
}