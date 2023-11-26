import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

/**
 * Esta clase contiene los atributos y metodos de sBonoloto mediante RMI.
 * @author Rafael Leal Pardo
 * @version 2022
*/

public class sBonoloto extends UnicastRemoteObject implements iBonoLoto{
    private int nTanmanio; 
    private int bonoGanador[] = new int[nTanmanio];

    /**
     * Método constructor parametrizado.
     * @param tam Entero el cual es el tamaño del vector de enteros de números ganadores.
     * @throws RemoteException
     */
    public sBonoloto(int tam)throws RemoteException{
        super();
        nTanmanio = tam;
        Random ran = new Random();
        //Genera un vector con números aleatorios entre 1 y 49

        for(int index=0; index<nTanmanio; ++index)bonoGanador[index] = ran.nextInt(1,49);
    }

    /**
     * Método para reiniciar el servidor y generar nuevos números ganadores.
     * @throws RemoteException
     */
    public void reiniciarServidor()throws RemoteException{
        Random ran = new Random();
        //Genera un vector con números aleatorios entre 1 y 49

        for(int index=0; index<nTanmanio; ++index)bonoGanador[index] = ran.nextInt(1,49);
    }

    /**
     * Método para comprobar si una apuesta es ganadora.
     * @param apuesta[] vector con el numero de la apuesta.
     * @return true si es el ganador de la apuesta, false si no lo es.
     * @throws RemoteException
     */
    public boolean comprobar(int apuesta[])throws RemoteException{
        boolean ganador = true;

        for(int index=0; index<nTanmanio; ++index){
            if(apuesta[index] != bonoGanador[index])ganador = false;
        }
        /*
        * Compara cada número de la apuesta con los números ganadores
        * y cambia el valor de la variable "ganador" a false si alguno no coincide.
        */
        return ganador;
    }

    /**
     * Método main para iniciar el servidor de la lotería.
     * @param args Argumentos de la línea de comandos.
     * @throws Exception
    */
    public static void main(String[] args)throws Exception{
        iBonoLoto interfazRemotaBonoloto = new sBonoloto(6);

        Naming.bind("Servidor", interfazRemotaBonoloto); //Vincula el objeto de la clase sBonoloto con el nombre "Servidor"

        System.out.println("Servidor preparado...");
    }
}