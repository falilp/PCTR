import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Esta clase contiene los atributos y metodos de PiMonteCarlo mediante RMI.
 * @author Rafael Leal Pardo
 * @version 2022
*/

public class PiMonteCarlo extends UnicastRemoteObject implements iPiMonteCarlo{
    private static AtomicInteger nIntentos = new AtomicInteger(0);
    private static int nVueltas = 0;

    /**
     * Método constructor no parametrizado.
     * @throws RemoteException
     */
    public PiMonteCarlo()throws RemoteException{}

    /**
     * Método para reiniciar los contadores de intentos y vueltas.
     * @throws RemoteException
     */
    public void reset()throws RemoteException{
        nIntentos = new AtomicInteger(0);
        nVueltas = 0;

        System.out.println();
    }

    /**
     * Método para calcular una aproximación del valor de pi mediante el método de Monte Carlo. 
     * @param Vueltas Entero del número de iteraciones para realizar el cálculo.
     * @throws RemoteException
     */
    public void calcular(int Vueltas)throws RemoteException{
        float coordenadaX = 0, coordenadaY = 0;
		Random ran = new Random();
  
		for(int index=0; index<Vueltas; ++index){
			
            coordenadaX = ran.nextFloat(); //Genera coordenandas aleatorias de X.
			coordenadaY = ran.nextFloat(); //Genera coordenandas aleatorias de Y.
			
            if((Math.pow(coordenadaX, 2) + Math.pow(coordenadaY, 2)) <= 1)nIntentos.getAndIncrement();
		}

		nVueltas += Vueltas;
		
		System.out.print("Numero de Intentos: "+ nIntentos.intValue() +" Numero de Vueltas: "+ nVueltas +" Aproximacion: "+ aproximacionActual() + " Valor real: "+ Math.PI);
    }

    /**
     * Método para obtener la aproximación actual del valor de pi.
     * @return Double de la aproximación actual del valor de pi.
     * @throws RemoteException
     */
    public double aproximacionActual()throws RemoteException{
        return 4.0 * nIntentos.intValue()/nVueltas;
    }

    /**
     * Método main para iniciar el servidor de cálculo de pi mediante el método de Monte Carlo.
     * @param args Argumentos de la línea de comandos.
     * @throws Exception
     */
    public static void main(String[] args)throws Exception{
        iPiMonteCarlo remotoMonteCarlo = new PiMonteCarlo();

        Naming.bind("80", remotoMonteCarlo);

        System.out.println("Servidor preparado...");
    }
}