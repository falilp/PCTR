package p2.ej2;

/**
 * Esta clase contiene los atributos y metodos de Uso_tareaRunnable
 * @author Rafael Leal Pardo
 * @version 2022
*/
import java.util.InputMismatchException;
import java.util.Scanner;

public class Uso_tareaRunnable implements Runnable{
    public enum Accion {INCREMENTO,DECREMENTO};

    private tareaRunnable runnable;
    private int nVueltas;
    private Accion tipoHilo;

    /**
     * Metodo constructor parametrizado
     * @param runnable Objeto tareaRunnable
     * @param nVueltas Entero del numero de vueltas
     * @param tipoHilo Tipo de hilo que incrementara o decrementara
     */
    public Uso_tareaRunnable(tareaRunnable runnable,int nVueltas,Accion tipoHilo){
        this.runnable = runnable;
        this.nVueltas = nVueltas;
        this.tipoHilo = tipoHilo;
    }

    /**
     * Metodo run, depende del tipoHilo incrementara o decrementara la variable
     */
    public void run(){
        switch(tipoHilo){
            case INCREMENTO:for(int i=0;i<nVueltas;i++)runnable.inc();break;
            case DECREMENTO:for(int i=0;i<nVueltas;i++)runnable.dec();break;
            default:break;
        }
    }

    /**
     * Metodo main que realizara la tarea mediante runnable y threads
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        try(Scanner Scan = new Scanner(System.in)){
            System.out.println("Introduzca el numero de vueltas a realizar: \n");
            int nVuel = Scan.nextInt();
            tareaRunnable tareaRunn = new tareaRunnable();
            Runnable tareaRunnable1 = new Uso_tareaRunnable(tareaRunn,nVuel,Accion.INCREMENTO);
            Runnable tareaRunnable2 = new Uso_tareaRunnable(tareaRunn,nVuel,Accion.DECREMENTO);

            Thread h1 = new Thread(tareaRunnable1);
            Thread h2 = new Thread(tareaRunnable2);

            h1.start();h2.start();
            h1.join();h2.join();

            System.out.println(tareaRunn.vDato());
        }catch(InputMismatchException ERROR){
            System.out.println("No se introdujo un entero valido");
        }
        
    }
}
